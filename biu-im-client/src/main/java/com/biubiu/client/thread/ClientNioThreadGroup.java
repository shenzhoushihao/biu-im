package com.biubiu.client.thread;

import org.springframework.stereotype.Component;

import io.netty.channel.EventLoopGroup;
import lombok.Data;

/**
 * 客户端传输数据的线程组
 * 
 */
@Data
@Component
public class ClientNioThreadGroup {

	private EventLoopGroup group = null;
}
