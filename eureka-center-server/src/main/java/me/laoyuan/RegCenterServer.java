/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心服务器
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class RegCenterServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(RegCenterServer.class).web(true).run(args);
	}
}
