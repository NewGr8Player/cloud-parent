/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 带熔断的HelloService
 * 
 * @author laoyuan.me
 *
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
