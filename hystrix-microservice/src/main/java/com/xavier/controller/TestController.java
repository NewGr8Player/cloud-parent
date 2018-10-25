package com.xavier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xavier.service.remote.HelloService;

/**
 * 测试控制器
 * 
 * @author NewGr8Player
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
