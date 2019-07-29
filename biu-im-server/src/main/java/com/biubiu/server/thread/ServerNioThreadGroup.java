package com.biubiu.server.thread;

import org.springframework.stereotype.Component;

import io.netty.channel.EventLoopGroup;
import lombok.Data;

/**
 * 连接网络客户端和传输数据的线程组
 * 
 */
@Data
@Component
public class ServerNioThreadGroup {

	private EventLoopGroup boss = null;

	private EventLoopGroup work = null;
}
