package com.biubiu.server.intializer;

import com.biubiu.server.handle.BiuIMServerHandle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class BiuIMServerInitializer extends ChannelInitializer<Channel> {

	private BiuIMServerHandle biuIMServerHandle = new BiuIMServerHandle();

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
		ch.pipeline()
				// 11 秒没有向客户端发送消息就发生心跳
				.addLast(new IdleStateHandler(11, 0, 0)).addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
				.addLast(new StringDecoder()).addLast(biuIMServerHandle);
	}

}
