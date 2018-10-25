/**
 * Copyright (c)2018, NewGr8Player.  All Rights Reserved.
 */
package com.xavier.service.remote;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * HelloService熔断实现
 * 
 * @author NewGr8Player
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
