/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务器
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServer.class).web(true).run(args);
	}
}
