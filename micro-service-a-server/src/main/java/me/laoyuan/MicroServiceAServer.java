/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 微服务B
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class MicroServiceAServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MicroServiceAServer.class).web(true).run(args);
	}

}
