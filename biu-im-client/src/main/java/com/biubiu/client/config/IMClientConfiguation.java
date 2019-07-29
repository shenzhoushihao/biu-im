package com.biubiu.client.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class IMClientConfiguation {

	@Value("${biu-im.protocol.host:127.0.0.1}")
	private String imProtocolHost;

	@Value("${biu-im.protocol.port:8200}")
	private int imProtoPort;

	@Value("${netty.client.workgroup.size:4}")
	private int clientThreadGroupSize;

	public boolean checkConfig() {
		if (StringUtils.isBlank(imProtocolHost)) {
			log.error("没有指定IM服务端的地址: [biu-im.protocol.host], Sorry, 应用服务退出!");
			return false;
		}
		log.info("[IM服务端的地址>>biu-im.protocol.host]:" + imProtocolHost);
		if (imProtoPort == 0) {
			log.error("没有指定IM通讯的端口号: [biu-im.protocol.port], Sorry, 应用服务退出!");
			return false;
		}
		log.info("[IM通讯的端口号>>biu-im.protocol.port]:" + imProtoPort);
		return true;
	}
}
