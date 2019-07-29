package com.biubiu.client.initializer;

import com.biubiu.client.handle.BiuIMClientHandle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class BiuIMClientInitializer extends ChannelInitializer<Channel> {

	private final BiuIMClientHandle biuIMClientHandle = new BiuIMClientHandle();

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
		// 10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
		ch.pipeline().addLast(new IdleStateHandler(0, 10, 0)).addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
				.addLast(new StringDecoder()).addLast(biuIMClientHandle);
	}

}
