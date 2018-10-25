/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * 测试控制器
 * 
 * @author laoyuan.me
 *
 */
@RestController
public class TestController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("name")
	@HystrixCommand(fallbackMethod = "fallBackName", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "5"),
					@HystrixProperty(name = "maxQueueSize", value = "-1") })
	public String findName() {
		return restTemplate.getForObject("http://localhost:5468/who", String.class);
	}

	public String fallBackName() {
		return "I am fallnack name.";
	}
}
