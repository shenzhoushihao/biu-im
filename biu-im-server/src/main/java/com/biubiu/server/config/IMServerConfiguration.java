package com.biubiu.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class IMServerConfiguration {

	/**
	 * server port
	 */
	@Value("${server.port}")
	private int serverPort;
	/**
	 * protocol port
	 */
	@Value("${biu-im.protocol.port:8200}")
	private int imProtoPort;
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
	 * offine address
	 */
	@Value("${biu-im.offline.address:127.0.0.1:9300/offLine}")
	private String offLineAddress;
	/**
	 * heart beat time
	 */
	@Value("${biu-im.heartbeat.time:30}")
	private long heartBeatTime;

	@Value("${netty.server.bossgroup.size:2}")
	private int bossThreadGroupSize;

	@Value("${netty.server.workgroup.size:4}")
	private int workThreadGroupSize;

	@Value("${netty.server.tcp.buf:1024}")
	private int tcpBuffer;

	@Value("${netty.server.receive.buf:65536}")
	private int receiveBuffer;

	public boolean checkConfig() {
		if (imProtoPort == 0) {
			log.error("没有指定IM通讯的端口号: [biu-im.protocol.port], Sorry, 应用服务退出!");
			return false;
		}
		log.info("[IM通讯的端口号>>biu-im.protocol.port]:" + imProtoPort);
		return true;
	}
}
