/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 * 
 * @author laoyuan.me
 *
 */
@Configuration
public class RestTemplateConfig {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
