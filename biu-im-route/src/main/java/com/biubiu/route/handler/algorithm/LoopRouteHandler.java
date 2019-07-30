package com.biubiu.route.handler.algorithm;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.CollectionUtils;

import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.ErrorDefinition;
import com.biubiu.route.handler.RouteHandler;

/**
 * @author: roc
 * @description: loop route
 */
public class LoopRouteHandler implements RouteHandler {

	private AtomicLong index = new AtomicLong();

	@Override
	public String routeServer(List<String> serverList, String key) {
		if (CollectionUtils.isEmpty(serverList)) {
			throw new BiuException(ErrorDefinition.NO_EXTRA_SERVER);
		}
		Long position = index.incrementAndGet() % serverList.size();
		if (position < 0) {
			position = 0L;
		}
		return serverList.get(position.intValue());
	}
}
