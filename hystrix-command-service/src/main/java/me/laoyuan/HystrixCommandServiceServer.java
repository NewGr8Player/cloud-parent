/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 熔断微服务
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class HystrixCommandServiceServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixCommandServiceServer.class).web(true).run(args);
	}

}
