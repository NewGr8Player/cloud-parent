/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 微服务B
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MicroServiceBServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MicroServiceBServer.class).web(true).run(args);
	}

}
