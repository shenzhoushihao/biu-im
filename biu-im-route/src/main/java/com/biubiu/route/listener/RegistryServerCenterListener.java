package com.biubiu.route.listener;

import com.biubiu.common.util.IMConstants;
import com.biubiu.route.cache.ServerCache;
import com.biubiu.route.utils.SpringBeanApplicationContext;
import com.biubiu.route.utils.ZkUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: server registry listener
 */
@Slf4j
public class RegistryServerCenterListener implements Runnable {

	private ZkUtil zkUtil;

	private ServerCache serverCache;

	public RegistryServerCenterListener() {
		zkUtil = SpringBeanApplicationContext.getBean(ZkUtil.class);
		serverCache = SpringBeanApplicationContext.getBean(ServerCache.class);
	}

	/**
	 * registry listener server service
	 */
	@Override
	public void run() {
		zkUtil.subscribeEvent(IMConstants.ZK_ROOT, serverCache.updateCache());
		log.info("listener: registry server service.");
	}
}
