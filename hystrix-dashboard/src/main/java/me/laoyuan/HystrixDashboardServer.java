/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * hystrix监控服务器
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixDashboardServer.class).web(true).run(args);
	}
}
