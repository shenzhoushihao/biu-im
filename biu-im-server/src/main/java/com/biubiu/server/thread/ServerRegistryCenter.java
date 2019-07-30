package com.biubiu.server.thread;

import com.biubiu.common.util.IMConstants;
import com.biubiu.route.utils.SpringBeanApplicationContext;
import com.biubiu.route.utils.ZkUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: server register center
 */
@Slf4j
public class ServerRegistryCenter implements Runnable {

	private ZkUtil zkUtil;
	private String ip;
	private int imProtoPort;
	private int serverPort;

	public ServerRegistryCenter(String ip, int imProtoPort, int serverPort) {
		this.ip = ip;
		this.imProtoPort = imProtoPort;
		this.serverPort = serverPort;
		zkUtil = SpringBeanApplicationContext.getBean(ZkUtil.class);
	}

	@Override
	public void run() {
		// create parent node
		zkUtil.createRootNode();
		String path = IMConstants.ZK_ROOT + "/ip" + IMConstants.HASHTAG_SEPARATOR + ip + IMConstants.COLON_SEPARATOR
				+ imProtoPort + IMConstants.COLON_SEPARATOR + serverPort;
		zkUtil.createNode(path);
		log.info("register zookeeper success，path=[{}]", path);
	}
}
