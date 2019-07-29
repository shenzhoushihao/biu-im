package com.biubiu.client.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.biubiu.client.config.IMClientConfiguation;
import com.biubiu.client.initializer.BiuIMClientInitializer;
import com.biubiu.client.thread.ClientNioThreadGroup;
import com.biubiu.exception.BiuException;
import com.biubiu.util.Errordefinition;
import com.biubiu.util.IMConstants;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * IM 客户端启动
 */
@Slf4j
@Component
public class BiuIMClientStart implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IMClientConfiguation imConf;

	@Autowired
	private ClientNioThreadGroup nioThreadGroup;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setClientNioThreadGroup();
		start();
	}

	private void setClientNioThreadGroup() {
		if (nioThreadGroup.getGroup() == null) {
			nioThreadGroup.setGroup(new NioEventLoopGroup(imConf.getClientThreadGroupSize(),
					new DefaultThreadFactory(IMConstants.CLIENT_WORK_THREAD_GROUP)));
		}
	}

	private void start() {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(nioThreadGroup.getGroup()).channel(NioSocketChannel.class)
				.handler(new BiuIMClientInitializer());

		try {
			ChannelFuture future = bootstrap.connect(imConf.getImProtocolHost(), imConf.getImProtoPort()).sync();
			if (future.isSuccess()) {
				log.info("启动 IMClient 成功");
			}
		} catch (InterruptedException e) {
			log.error("启动 IMClient 失败," + e);
			throw new BiuException(Errordefinition.START_FAILURE);
		}
	}
}
