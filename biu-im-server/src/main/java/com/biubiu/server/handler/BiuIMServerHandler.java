package com.biubiu.server.handler;

import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.protocol.IMDto.IMDtoProtocol;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.util.NettyAttrUtil;
import com.biubiu.common.util.SessionSocketHolder;
import com.biubiu.route.utils.SpringBeanApplicationContext;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class BiuIMServerHandler extends SimpleChannelInboundHandler<IMDtoProtocol> {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			if (idleStateEvent.state() == IdleState.READER_IDLE) {
				log.info("server heartbeat: checking whether the client is alive periodically");
				HeartBeatHandler heartBeatHandler = SpringBeanApplicationContext.getBean(HeartBeatHandler.class);
				heartBeatHandler.process(ctx);
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("server: channel active, timestap{}.", System.currentTimeMillis());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, IMDtoProtocol msg) throws Exception {
		log.info("server: receive msg:[{}]", msg);

		if (IMConstants.CommandType.LOGIN == msg.getType()) {
			// save client & Channel connection
			SessionSocketHolder.putChannel(msg.getUserId(), (NioSocketChannel) ctx.channel());
			SessionSocketHolder.saveSession(msg.getUserId(), msg.getUsername());
			log.info("userId: [{}],username: [{}] online success.", msg.getUserId(), msg.getUsername());
		}

		if (IMConstants.CommandType.PING == msg.getType()) {
			NettyAttrUtil.updateReaderTime(ctx.channel(), System.currentTimeMillis());
			// respond to the client message
			IMDtoProtocol heartBeat = getHeartBeatBean();
			ChannelFuture channelFuture = ctx.writeAndFlush(heartBeat);
			channelFuture.addListener((ChannelFutureListener) future -> {
				if (!future.isSuccess()) {
					log.error("failed to connect to server, try close connection.");
					future.channel().close();
				}
			});
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// triggered after the business judgment is taken offline,channelInactive
		IMUserInfo userInfo = SessionSocketHolder.getIMUserForSocketChannel((NioSocketChannel) ctx.channel());
		if (userInfo != null) {
			log.warn("user: [{}] channelInactive off line start.", userInfo.getUsername());
			userOffLine(userInfo, (NioSocketChannel) ctx.channel());
			ctx.channel().close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("server: an exception:{}", cause.getCause());
		ctx.channel().close();
	}

	/**
	 * user offline
	 * 
	 * @param userInfo
	 * @param channel
	 */
	private void userOffLine(IMUserInfo userInfo, NioSocketChannel channel) {
		log.info("user: [{}] off line, end", userInfo.getUsername());
		SessionSocketHolder.removeChannel(channel);
		SessionSocketHolder.removeSession(userInfo.getUserId());
		// clear route
		RouteProxyHandler routeProxyHandler = SpringBeanApplicationContext.getBean(RouteProxyHandler.class);
		routeProxyHandler.userOffLine(userInfo, channel);
	}

	/**
	 * acquire heart bean
	 * 
	 * @return
	 */
	public IMDtoProtocol getHeartBeatBean() {
		return IMDtoProtocol.newBuilder().setMessage(IMConstants.CommandType.BIU_SERVER_HEART)
				.setType(IMConstants.CommandType.PING).build();
	}
}
