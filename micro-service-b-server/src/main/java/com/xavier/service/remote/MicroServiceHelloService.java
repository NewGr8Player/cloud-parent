package com.xavier.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author NewGr8Player
 *
 */
@FeignClient(name = "microservicea")
public interface MicroServiceHelloService {
	/**
	 * 代理MicroServicA
	 */
	@GetMapping("who")
	String who();
}
