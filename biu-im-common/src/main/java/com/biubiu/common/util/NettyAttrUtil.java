package com.biubiu.common.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * netty attr
 */
public class NettyAttrUtil {

	private static final AttributeKey<String> ATTR_KEY_READER_TIME = AttributeKey.valueOf("readerTime");
	private static final AttributeKey<String> ATTR_KEY_ROUTE_ADDR = AttributeKey.valueOf("routeAddress");

	public static void updateReaderTime(Channel channel, Long time) {
		channel.attr(ATTR_KEY_READER_TIME).set(time.toString());
	}

	public static void setRouteAddress(Channel channel, String url) {
		channel.attr(ATTR_KEY_ROUTE_ADDR).set(url);
	}

	public static Long getReaderTime(Channel channel) {
		String value = getAttribute(channel, ATTR_KEY_READER_TIME);
		if (value != null) {
			return Long.valueOf(value);
		}
		return null;
	}

	public static String getRouteAddress(Channel channel) {
		return getAttribute(channel, ATTR_KEY_ROUTE_ADDR);
	}

	private static String getAttribute(Channel channel, AttributeKey<String> key) {
		Attribute<String> attr = channel.attr(key);
		return attr.get();
	}
}
