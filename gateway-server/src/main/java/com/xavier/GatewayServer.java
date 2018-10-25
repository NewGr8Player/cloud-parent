package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关启动程序
 *
 * @author NewGr8Player
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayServer {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServer.class, args);
	}
}
