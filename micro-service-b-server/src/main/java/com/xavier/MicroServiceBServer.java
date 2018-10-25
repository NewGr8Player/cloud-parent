package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 微服务B
 *
 * @author NewGr8Player
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MicroServiceBServer {
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceBServer.class, args);
	}

}
