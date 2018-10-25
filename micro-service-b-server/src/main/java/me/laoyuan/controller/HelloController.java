/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.laoyuan.service.remote.MicroServiceHelloService;

/**
 * 测试控制器
 * 
 * @author laoyuan.me
 *
 */
@RestController
public class HelloController {
	@Autowired
	private MicroServiceHelloService microServiceHelloService;

	@GetMapping("who")
	public String who() {
		return "I am MicroServiceB";
	}

	@GetMapping("whoa")
	public String whoa() {
		return microServiceHelloService.who();
	}
}
