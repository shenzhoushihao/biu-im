package com.biubiu.server.launcher;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.biubiu.common.util.IMConstants;
import com.biubiu.server.config.IMServerConfiguration;
import com.biubiu.server.intializer.BiuIMServerInitializer;
import com.biubiu.server.thread.ServerNioThreadGroup;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: start IM server
 */
@Slf4j
@Component
public class BiuIMServerStart implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IMServerConfiguration imConf;

	@Autowired
	private ServerNioThreadGroup nioThreadGroup;

	/**
	 * IMServer start
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setServerNioThreadGroup();
		start();
	}

	private void start() {
		ServerBootstrap bootstrap = new ServerBootstrap().group(nioThreadGroup.getBoss(), nioThreadGroup.getWork())
				.channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(imConf.getImProtoPort()))
				.option(ChannelOption.SO_BACKLOG, imConf.getTcpBuffer())
				.option(ChannelOption.SO_RCVBUF, imConf.getReceiveBuffer())
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new BiuIMServerInitializer());

		try {
			ChannelFuture future = bootstrap.bind().sync();
			if (future.isSuccess()) {
				log.info("start IMServer success.");
			}
		} catch (InterruptedException e) {
			log.error("start IMServer failure." + e);
		}
	}

	/**
	 * create thread group
	 */
	private void setServerNioThreadGroup() {
		if (nioThreadGroup.getBoss() == null) {
			nioThreadGroup.setBoss(new NioEventLoopGroup(imConf.getBossThreadGroupSize(),
					new DefaultThreadFactory(IMConstants.SERVER_BOSS_THREAD_GROUP, true)));
		}
		if (nioThreadGroup.getWork() == null) {
			nioThreadGroup.setWork(new NioEventLoopGroup(imConf.getWorkThreadGroupSize(),
					new DefaultThreadFactory(IMConstants.SERVER_WORK_THREAD_GROUP, true)));
		}
	}
}
