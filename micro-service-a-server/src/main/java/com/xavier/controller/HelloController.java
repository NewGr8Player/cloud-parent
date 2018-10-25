package com.xavier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * 
 * @author NewGr8Player
 *
 */
@RestController
public class HelloController {
	@GetMapping("who")
	public String who() {
		return "I am MicroServiceA";
	}

}
