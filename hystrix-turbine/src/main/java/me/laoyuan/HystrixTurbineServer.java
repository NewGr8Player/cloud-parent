/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hystrix聚合服务
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableTurbine
public class HystrixTurbineServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixTurbineServer.class).web(true).run(args);
	}

}
