/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import me.laoyuan.service.remote.HelloService;

/**
 * 测试控制器
 * 
 * @author laoyuan.me
 *
 */
@RestController
public class TestController {
	@Autowired
	private HelloService helloService;

	@GetMapping("whoa")
	public String whoa() {
		return helloService.whoa();
	}
	@GetMapping("who")
	public String who() {
		return helloService.who();
	}
}
