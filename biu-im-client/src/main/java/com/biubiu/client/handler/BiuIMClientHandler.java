package com.biubiu.client.handler;

import com.biubiu.common.protocol.IMDto.IMDtoProtocol;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.util.NettyAttrUtil;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * network handler
 */
@Slf4j
@ChannelHandler.Sharable
public class BiuIMClientHandler extends SimpleChannelInboundHandler<IMDtoProtocol> {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			log.info("client端: 10秒内没有向服务器端发送消息,触发心跳.");
			if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
				IMDtoProtocol heartBeat = getHeartBeatBean();
				ChannelFuture channelFuture = ctx.writeAndFlush(heartBeat);
				channelFuture.addListener((ChannelFutureListener) future -> {
					if (!future.isSuccess()) {
						log.error("client端:IO error,close Channel");
						future.channel().close();
					}
				});
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 与服务端连接成功.timestap:{}.", System.currentTimeMillis());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, IMDtoProtocol msg) throws Exception {
		log.info("client端: msg[{}]", msg);

		// 心跳更新时间
		if (IMConstants.CommandType.PING == msg.getType()) {
			log.info("client端: 收到服务端心跳->msg:[{}]", msg);
			NettyAttrUtil.updateReaderTime(ctx.channel(), System.currentTimeMillis());
		} else {
			// 回调消息
			//callBackMsg(msg.getMessage());

			log.info(msg.getMessage());
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 发送完成.");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("client端: 与服务端断开成功.");
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.warn("client端: 异常通道关闭");
		ctx.channel().close();
	}

	/**
	 * acquire heart bean
	 * 
	 * @return
	 */
	public IMDtoProtocol getHeartBeatBean() {
		return IMDtoProtocol.newBuilder().setMessage(IMConstants.CommandType.BIU_CLIENT_HEART)
				.setType(IMConstants.CommandType.PING).build();
	}
}
