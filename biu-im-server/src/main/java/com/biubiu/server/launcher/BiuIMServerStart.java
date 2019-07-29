package com.biubiu.server.launcher;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.biubiu.server.config.IMServerConfiguation;
import com.biubiu.server.intializer.BiuIMServerInitializer;
import com.biubiu.server.thread.ServerNioThreadGroup;
import com.biubiu.util.IMConstants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * IM 服务器端启动
 *
 */
@Slf4j
@Component
public class BiuIMServerStart implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IMServerConfiguation imConf;

	@Autowired
	private ServerNioThreadGroup nioThreadGroup;

	/**
	 * IMServer 启动
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setServerNioThreadGroup();
		start();
	}

	private void start() {
		ServerBootstrap bootstrap = new ServerBootstrap().group(nioThreadGroup.getBoss(), nioThreadGroup.getWork())
				.channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(imConf.getImProtoPort()))
				.childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new BiuIMServerInitializer());

		try {
			ChannelFuture future = bootstrap.bind().sync();
			if (future.isSuccess()) {
				log.info("启动 IMServer 成功.");
			}
		} catch (InterruptedException e) {
			log.error("启动 IMServer 失败." + e);
		}
	}

	/**
	 * 线程组创建
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
