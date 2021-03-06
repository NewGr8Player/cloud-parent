package com.xavier.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.constants.ZuulHeaders;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 网关日志过滤器
 *
 * @author NewGr8Player
 */
@Component
public class LogZuulFilter extends ZuulFilter {
	@Autowired
	private ObjectMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(LogZuulFilter.class);

	private static final Log log = LogFactory.getLog(SendResponseFilter.class);

	private static DynamicBooleanProperty INCLUDE_DEBUG_HEADER = DynamicPropertyFactory.getInstance()
			.getBooleanProperty(ZuulConstants.ZUUL_INCLUDE_DEBUG_HEADER, false);

	private static DynamicIntProperty INITIAL_STREAM_BUFFER_SIZE = DynamicPropertyFactory.getInstance()
			.getIntProperty(ZuulConstants.ZUUL_INITIAL_STREAM_BUFFER_SIZE, 8192);

	private static DynamicBooleanProperty SET_CONTENT_LENGTH = DynamicPropertyFactory.getInstance()
			.getBooleanProperty(ZuulConstants.ZUUL_SET_CONTENT_LENGTH, false);
	private boolean useServlet31 = true;

	public LogZuulFilter() {
		super();
		try {
			HttpServletResponse.class.getMethod("setContentLengthLong");
		} catch (NoSuchMethodException e) {
			useServlet31 = false;
		}
	}

	private ThreadLocal<byte[]> buffers = ThreadLocal.withInitial(
			() -> new byte[INITIAL_STREAM_BUFFER_SIZE.get()]
	);

	@Override
	public String filterType() {
		return POST_TYPE;
	}

	@Override
	public int filterOrder() {
		/* 优先级高于默认filter (SEND_RESPONSE_FILTER_ORDER - 1) */
		return SEND_RESPONSE_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		return context.getThrowable() == null && (!context.getZuulResponseHeaders().isEmpty()
				|| context.getResponseDataStream() != null || context.getResponseBody() != null);
	}

	@Override
	public Object run() {
		try {
			addResponseHeaders();
			writeResponse();

			int rCode = RequestContext.getCurrentContext().getResponseStatusCode();
			/* Request log */
			logger.info("Request URL:" + RequestContext.getCurrentContext().get(REQUEST_URI_KEY));
			logger.info("Request Params:"
					+ mapper.writeValueAsString(RequestContext.getCurrentContext().getRequest().getParameterMap()));
			String rBody = new String(buffers.get());
			/* Response log */
			logger.info("Response Code:" + rCode + ",Response Body:" + rBody);
		} catch (Exception ex) {
			ReflectionUtils.rethrowRuntimeException(ex);
		}
		return null;
	}

	private void writeResponse() throws Exception {
		RequestContext context = RequestContext.getCurrentContext();
		if (context.getResponseBody() == null && context.getResponseDataStream() == null) {
			return;
		}
		HttpServletResponse servletResponse = context.getResponse();
		if (servletResponse.getCharacterEncoding() == null) {
			servletResponse.setCharacterEncoding("UTF-8");
		}
		OutputStream outStream = servletResponse.getOutputStream();
		InputStream is = null;
		try {
			if (RequestContext.getCurrentContext().getResponseBody() != null) {
				String body = RequestContext.getCurrentContext().getResponseBody();
				writeResponse(new ByteArrayInputStream(body.getBytes(servletResponse.getCharacterEncoding())),
						outStream);
				return;
			}
			boolean isGzipRequested = false;
			final String requestEncoding = context.getRequest().getHeader(ZuulHeaders.ACCEPT_ENCODING);

			if (requestEncoding != null && HTTPRequestUtils.getInstance().isGzipped(requestEncoding)) {
				isGzipRequested = true;
			}
			is = context.getResponseDataStream();
			InputStream inputStream = is;
			if (is != null) {
				if (context.sendZuulResponse()) {
					// if origin response is gzipped, and client has not
					// requested gzip,
					// decompress stream
					// before sending to client
					// else, stream gzip directly to client
					if (context.getResponseGZipped() && !isGzipRequested) {
						// If origin tell it's GZipped but the content is ZERO
						// bytes,
						// don't try to uncompress
						final Long len = context.getOriginContentLength();
						if (len == null || len > 0) {
							try {
								inputStream = new GZIPInputStream(is);
							} catch (java.util.zip.ZipException ex) {
								log.debug("gzip expected but not " + "received assuming unencoded response "
										+ RequestContext.getCurrentContext().getRequest().getRequestURL().toString());
								inputStream = is;
							}
						} else {
							// Already done : inputStream = is;
						}
					} else if (context.getResponseGZipped() && isGzipRequested) {
						servletResponse.setHeader(ZuulHeaders.CONTENT_ENCODING, "gzip");
					}
					writeResponse(inputStream, outStream);
				}
			}
		} finally {
			/**
			 * Closing the wrapping InputStream itself has no effect on closing the underlying tcp connection since it's
			 * a wrapped stream. I guess for http keep-alive. When closing the wrapping stream it tries to reach the end
			 * of the current request, which is impossible for infinite http streams. So instead of closing the
			 * InputStream we close the HTTP response.
			 *
			 * @author Johannes Edmeier
			 */
			try {
				Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
				if (zuulResponse instanceof Closeable) {
					((Closeable) zuulResponse).close();
				}
				outStream.flush();
				// The container will close the stream for us
			} catch (IOException ex) {
				log.warn("Error while sending response to client: " + ex.getMessage());
			}
		}
	}

	private void writeResponse(InputStream zin, OutputStream out) throws Exception {
		byte[] bytes = buffers.get();
		int bytesRead = -1;
		while ((bytesRead = zin.read(bytes)) != -1) {
			out.write(bytes, 0, bytesRead);
		}
	}

	private void addResponseHeaders() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse servletResponse = context.getResponse();
		if (INCLUDE_DEBUG_HEADER.get()) {
			@SuppressWarnings("unchecked")
			List<String> rd = (List<String>) context.get(ROUTING_DEBUG_KEY);
			if (rd != null) {
				StringBuilder debugHeader = new StringBuilder();
				for (String it : rd) {
					debugHeader.append("[[[" + it + "]]]");
				}
				servletResponse.addHeader(X_ZUUL_DEBUG_HEADER, debugHeader.toString());
			}
		}
		List<Pair<String, String>> zuulResponseHeaders = context.getZuulResponseHeaders();
		if (zuulResponseHeaders != null) {
			for (Pair<String, String> it : zuulResponseHeaders) {
				servletResponse.addHeader(it.first(), it.second());
			}
		}
		// Only inserts Content-Length if origin provides it and origin response
		// is not
		// gzipped
		if (SET_CONTENT_LENGTH.get()) {
			Long contentLength = context.getOriginContentLength();
			if (contentLength != null && !context.getResponseGZipped()) {
				if (useServlet31) {
					servletResponse.setContentLengthLong(contentLength);
				} else {
					// Try and set some kind of content length if we can safely
					// convert the Long to an int
					if (isLongSafe(contentLength)) {
						servletResponse.setContentLength(contentLength.intValue());
					}
				}
			}
		}
	}

	private boolean isLongSafe(long value) {
		return value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE;
	}

}
