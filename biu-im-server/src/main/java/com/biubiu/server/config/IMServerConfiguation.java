package com.biubiu.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class IMServerConfiguation {

	@Value("${biu-im.protocol.port:8200}")
	private int imProtoPort;

	@Value("${netty.server.bosstgroup.size:2}")
	private int bossThreadGroupSize;

	@Value("${netty.server.workgroup.size:4}")
	private int workThreadGroupSize;

	public boolean checkConfig() {
		if (imProtoPort == 0) {
			log.error("没有指定IM通讯的端口号: [biu-im.protocol.port], Sorry, 应用服务退出!");
			return false;
		}
		log.info("[IM通讯的端口号>>biu-im.protocol.port]:" + imProtoPort);
		return true;
	}
}
