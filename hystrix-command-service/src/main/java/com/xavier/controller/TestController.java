package com.xavier.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 测试控制器
 * 
 * @author NewGr8Player
 *
 */
@RestController
public class TestController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("name")
	@HystrixCommand(fallbackMethod = "fallBackName")
	public String findName() {
		return restTemplate.getForObject("http://localhost:5468/who", String.class);
	}

	public String fallBackName() {
		return "I am fallnack name.";
	}
}
