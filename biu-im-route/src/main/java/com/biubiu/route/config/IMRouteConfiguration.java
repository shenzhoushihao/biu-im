package com.biubiu.route.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author: roc
 * @description: IM route configuration
 */
@Data
@Configuration
public class IMRouteConfiguration {

	/**
	 * zookeeper connection address
	 */
	@Value("${biu-im.zookeeper.addr:127.0.0.1:2181}")
	private String zkAddr;

	/**
	 * zookeeper connection timed out
	 */
	@Value("${biu-im.zookeeper.connect.timeout:3000}")
	private int zkConnectTimeout;

	/**
	 * route strategy
	 */
	@Value("${biu-im.route.strategy:loop}")
	private String routeStrategy;
}
