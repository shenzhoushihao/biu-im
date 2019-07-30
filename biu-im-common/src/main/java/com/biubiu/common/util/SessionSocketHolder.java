package com.biubiu.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.biubiu.common.data.IMUserInfo;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: roc
 * @description:
 */
public class SessionSocketHolder {
	private static final Map<String, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);
	private static final Map<String, String> SESSION_MAP = new ConcurrentHashMap<>(16);

	public static void saveSession(String userId, String username) {
		SESSION_MAP.put(userId, username);
	}

	public static void removeSession(String userId) {
		SESSION_MAP.remove(userId);
	}

	public static void putChannel(String id, NioSocketChannel socketChannel) {
		CHANNEL_MAP.put(id, socketChannel);
	}

	public static NioSocketChannel getChannel(String id) {
		return CHANNEL_MAP.get(id);
	}

	public static Map<String, NioSocketChannel> getChannelMap() {
		return CHANNEL_MAP;
	}

	public static void removeChannel(NioSocketChannel nioSocketChannel) {
		CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel)
				.forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
	}

	/**
	 * query register user info for NioSocketChannel
	 * 
	 * @param nioSocketChannel
	 * @return
	 */
	public static IMUserInfo getIMUserForSocketChannel(NioSocketChannel nioSocketChannel) {
		for (Map.Entry<String, NioSocketChannel> entry : CHANNEL_MAP.entrySet()) {
			NioSocketChannel socketChannel = entry.getValue();
			if (nioSocketChannel == socketChannel) {
				String userId = entry.getKey();
				String userName = SESSION_MAP.get(userId);
				return new IMUserInfo(userId, userName);
			}
		}
		return null;
	}
}
