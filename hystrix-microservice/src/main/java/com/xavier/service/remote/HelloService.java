/**
 * Copyright (c)2018, NewGr8Player.  All Rights Reserved.
 */
package com.xavier.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 带熔断的HelloService
 *
 * @author NewGr8Player
 */
@FeignClient(name = "microserviceb", fallback = FallbackHelloService.class)
public interface HelloService {
	/**
	 * 代理MicroServicB
	 */
	@GetMapping("whoa")
	String whoa();

	/**
	 * 代理MicroServicB
	 */
	@GetMapping("who")
	String who();
}
