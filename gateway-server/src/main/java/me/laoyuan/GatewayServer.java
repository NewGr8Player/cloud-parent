/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关启动程序
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayServer.class).web(true).run(args);
	}
}
