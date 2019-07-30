package com.biubiu.client.initializer;

import com.biubiu.client.handler.BiuIMClientHandler;
import com.biubiu.common.protocol.IMDto;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class BiuIMClientInitializer extends ChannelInitializer<Channel> {

	private final BiuIMClientHandler biuIMClientHandler = new BiuIMClientHandler();

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// If the write() method is not called within 10 seconds,
		// the userEventTrigger() method is fired once.
		ch.pipeline().addLast(new IdleStateHandler(0, 10, 0))
				.addLast(new ProtobufVarint32FrameDecoder())
				.addLast(new ProtobufDecoder(IMDto.IMDtoProtocol.getDefaultInstance()))
				.addLast(new ProtobufVarint32LengthFieldPrepender())
				.addLast(new ProtobufEncoder())
				.addLast(new StringDecoder()).addLast(biuIMClientHandler);
	}
}