package com.biubiu.client.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.biubiu.client.thread.ClientNioThreadGroup;

import lombok.extern.slf4j.Slf4j;

/**
 * IM 客户端关闭
 */
@Slf4j
@Component
public class BiuIMClientClose implements ApplicationListener<ContextClosedEvent> {

	@Autowired
	private ClientNioThreadGroup nioThreadGroup;

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		close();
	}

	public void close() {
		if (nioThreadGroup.getGroup() != null) {
			nioThreadGroup.getGroup().shutdownGracefully().syncUninterruptibly();
		}
		log.info("关闭 IMClient 成功");
	}
}
