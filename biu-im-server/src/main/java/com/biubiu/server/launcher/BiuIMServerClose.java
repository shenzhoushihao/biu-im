package com.biubiu.server.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.biubiu.server.thread.ServerNioThreadGroup;

import lombok.extern.slf4j.Slf4j;

/**
 * IM 服务器端关闭
 */
@Slf4j
@Component
public class BiuIMServerClose implements ApplicationListener<ContextClosedEvent> {

	@Autowired
	private ServerNioThreadGroup nioThreadGroup;

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		close();
	}

	private void close() {
		if (nioThreadGroup.getWork() != null) {
			nioThreadGroup.getWork().shutdownGracefully().syncUninterruptibly();
		}
		if (nioThreadGroup.getBoss() != null) {
			nioThreadGroup.getBoss().shutdownGracefully().syncUninterruptibly();
		}
		log.info("关闭 IMServer 成功");
	}
}
