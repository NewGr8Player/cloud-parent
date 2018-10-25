package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务器
 *
 * @author NewGr8Player
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServer {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServer.class, args);
	}
}
