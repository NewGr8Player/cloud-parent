package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 微服务B
 *
 * @author NewGr8Player
 */
@EnableEurekaClient
@SpringBootApplication
public class MicroServiceAServer {
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceAServer.class, args);
	}

}
