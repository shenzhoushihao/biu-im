package com.biubiu.client.handle;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 网络处理
 *
 */
@Slf4j
@ChannelHandler.Sharable
public class BiuIMClientHandle extends SimpleChannelInboundHandler<String> {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("client端: 触发心跳");
		// ctx.writeAndFlush(Unpooled.copiedBuffer("client biu
		// heartbeat$$".getBytes()));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 通道上线开始");
		ctx.writeAndFlush(Unpooled.copiedBuffer("client biu-biu$$".getBytes()));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info("client端: msg[{}]", msg);
		// ctx.writeAndFlush(Unpooled.copiedBuffer("server biu-biu$$".getBytes()));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 发送完成.");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 通道下线触发");
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.warn("client端: 异常通道关闭");
		ctx.channel().close();
	}
}
