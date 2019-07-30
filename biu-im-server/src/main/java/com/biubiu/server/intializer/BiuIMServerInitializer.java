package com.biubiu.server.intializer;

import com.biubiu.common.protocol.IMDto;
import com.biubiu.server.handler.BiuIMServerHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class BiuIMServerInitializer extends ChannelInitializer<Channel> {

	private BiuIMServerHandler biuIMServerHandler = new BiuIMServerHandler();

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// If the ChannelRead() method is not called within 12 seconds,
		// the userEventTrigger() method is fired once.
		ch.pipeline().addLast(new IdleStateHandler(12, 0, 0))
		        .addLast(new ProtobufVarint32FrameDecoder())
				.addLast(new ProtobufDecoder(IMDto.IMDtoProtocol.getDefaultInstance()))
				.addLast(new ProtobufVarint32LengthFieldPrepender())
				.addLast(new ProtobufEncoder())
				.addLast(new StringDecoder())
				.addLast(biuIMServerHandler);
	}
}