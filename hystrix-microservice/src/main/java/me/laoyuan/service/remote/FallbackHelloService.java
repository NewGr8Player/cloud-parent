/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.service.remote;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * HelloService熔断实现
 * 
 * @author laoyuan.me
 *
 */
@Component
public class FallbackHelloService implements HelloService {
	@Override
	public String whoa() {
		return "FallbackHelloService:whoa" + new Date();
	}

	@Override
	public String who() {
		return "FallbackHelloService:who" + new Date();
	}

}
