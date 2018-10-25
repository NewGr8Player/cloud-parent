package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hystrix聚合服务
 * 
 * @author NewGr8Player
 *
 */
@EnableTurbine
@SpringBootApplication
public class HystrixTurbineServer {
	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineServer.class,args);
	}

}
