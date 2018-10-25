/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author laoyuan.me
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
