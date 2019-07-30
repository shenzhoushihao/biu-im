package com.biubiu.server.service.impl;

import org.springframework.stereotype.Service;

import com.biubiu.common.protocol.IMDto.IMDtoProtocol;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.util.SessionSocketHolder;
import com.biubiu.common.vo.ChatReqVO;
import com.biubiu.server.service.MessageService;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Override
	public boolean sendMsg(ChatReqVO chatReqVO) {
		NioSocketChannel socketChannel = SessionSocketHolder.getChannel(chatReqVO.getReceiveUserId());

		if (socketChannel == null) {
			log.info("client [ %s ] offline.", chatReqVO.getReceiveUserId());
			return false;
		}

		IMDtoProtocol protocol = IMDtoProtocol.newBuilder()
				.setUserId(chatReqVO.getReceiveUserId())
				.setUsername(chatReqVO.getReceiveUsername())
				.setMessage(chatReqVO.getContent())
				.setType(IMConstants.CommandType.MSG)
				.setTimestap(String.valueOf(System.currentTimeMillis()))
				.build();

		ChannelFuture future = socketChannel.writeAndFlush(protocol);
		future.addListener((ChannelFutureListener) channelFuture -> log.info(
				"server send message from {} to {} success,message:{}.", chatReqVO.getSendUserId(),
				chatReqVO.getReceiveUserId(), chatReqVO.toString()));
		return true;
	}
}
