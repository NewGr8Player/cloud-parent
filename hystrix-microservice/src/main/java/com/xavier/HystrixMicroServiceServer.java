/**
 * Copyright (c)2018, NewGr8Player.  All Rights Reserved.
 */
package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 熔断微服务
 * 
 * @author NewGr8Player
 *
 */
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class HystrixMicroServiceServer {
	public static void main(String[] args) {
		SpringApplication.run(HystrixMicroServiceServer.class,args);
	}

}
