package com.biubiu.common.util;

import okhttp3.MediaType;

/**
 * constant
 * 
 */
public class IMConstants {

	/**
	 * 服务器端网络连接线程组名称
	 */
	public static final String SERVER_BOSS_THREAD_GROUP = "biu-im-server-boss-group";

	/**
	 * 服务器端工作线程组名称
	 */
	public static final String SERVER_WORK_THREAD_GROUP = "biu-im-server-work-group";

	/**
	 * 客户端工作线程组名称
	 */
	public static final String CLIENT_WORK_THREAD_GROUP = "biu-im-client-work-group";

	/**
	 * 心跳维持消息
	 */
	public static final String CLIENT_PING = "client:biu-ping";

	/**
	 * 心跳实例名称
	 */
	public static final String HEARTBEAT = "heartBeat";

	/**
	 * zookeeper根路径地址
	 */
	public static final String ZK_ROOT = "/biu-im-route";
	/**
	 * account prefix
	 */
	public static final String ACCOUNT_PREFIX = "im-account:%s";
	/**
	 * route prefix
	 */
	public static final String ROUTE_PREFIX = "im-route:%s";
	/**
	 * separator: #
	 */
	public static final String HASHTAG_SEPARATOR = "#";
	/**
	 * separator: *
	 */
	public static final String STAR_SEPARATOR = "*";
	/**
	 * separator: :
	 */
	public static final String COLON_SEPARATOR = ":";
	/**
	 * login status prefix
	 */
	public static final String LOGIN_STATUS = "login-status";

	/**
	 * custom message type
	 */
	public static class CommandType {
		/**
		 * login mark
		 */
		public static final int LOGIN = 1;
		/**
		 * server mark
		 */
		public static final int MSG = 2;

		/**
		 * ping mark
		 */
		public static final int PING = 3;

		/**
		 * server heart beat
		 */
		public static final String BIU_SERVER_HEART = "biu-server-heart";

		/**
		 * client heart beat
		 */
		public static final String BIU_CLIENT_HEART = "biu-client-heart";

		private CommandType() {
		}
	}

	/**
	 * customize route type
	 */
	public static class RouteType {
		/**
		 * polling
		 */
		public static final String LOOP = "loop";
		/**
		 * random
		 */
		public static final String RANDOM = "random";
		/**
		 * polling load class
		 */
		public static final String LOOP_CLASS = "com.biubiu.route.handler.algorithm.LoopRouteHandler";
		/**
		 * random load class
		 */
		public static final String RANDOM_CLASS = "com.biubiu.route.handler.algorithm.RandomRouteHandler";

		private RouteType() {
		}
	}

	public static final MediaType APPLICATION_JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

	private IMConstants() {
	}
}
