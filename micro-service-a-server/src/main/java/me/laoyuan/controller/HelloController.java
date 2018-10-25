/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * 
 * @author laoyuan.me
 *
 */
@RestController
public class HelloController {
	@GetMapping("who")
	public String who() {
		return "I am MicroServiceA";
	}

}
