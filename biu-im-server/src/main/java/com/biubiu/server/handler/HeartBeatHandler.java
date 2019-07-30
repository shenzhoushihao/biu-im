package com.biubiu.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.util.NettyAttrUtil;
import com.biubiu.common.util.SessionSocketHolder;
import com.biubiu.server.config.IMServerConfiguration;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HeartBeatHandler {

	@Autowired
	private IMServerConfiguration imServerConfig;

	@Autowired
	private RouteProxyHandler routeProxyHandler;

	public void process(ChannelHandlerContext ctx) {
		long heartBeatTime = imServerConfig.getHeartBeatTime() * 1000;
		Long lastReadTime = NettyAttrUtil.getReaderTime(ctx.channel());
		long now = System.currentTimeMillis();
		if (lastReadTime != null && now - lastReadTime > heartBeatTime) {
			IMUserInfo userInfo = SessionSocketHolder.getIMUserForSocketChannel((NioSocketChannel) ctx.channel());
			if (userInfo != null) {
				log.warn("client [{}] heartbeat timeout [{}] ms，need to close connection.", userInfo.getUsername(),
						(now - lastReadTime));
			}
			routeProxyHandler.userOffLine(userInfo, (NioSocketChannel) ctx.channel());
			ctx.channel().close();
		}
	}
}
