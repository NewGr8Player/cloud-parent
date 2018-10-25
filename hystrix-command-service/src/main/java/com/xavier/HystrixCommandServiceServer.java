package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 熔断微服务
 *
 * @author NewGr8Player
 */
@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class HystrixCommandServiceServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixCommandServiceServer.class).web(true).run(args);
		SpringApplication.run(HystrixCommandServiceServer.class, args);
	}

}
