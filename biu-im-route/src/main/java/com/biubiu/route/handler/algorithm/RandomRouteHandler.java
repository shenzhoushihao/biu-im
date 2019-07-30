package com.biubiu.route.handler.algorithm;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.util.CollectionUtils;

import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.ErrorDefinition;
import com.biubiu.route.handler.RouteHandler;

/**
 * @author: roc
 * @description: random route
 */
public class RandomRouteHandler implements RouteHandler {

	@Override
	public String routeServer(List<String> serverList, String key) {
		if (CollectionUtils.isEmpty(serverList)) {
			throw new BiuException(ErrorDefinition.NO_EXTRA_SERVER);
		}
		int offset = ThreadLocalRandom.current().nextInt(serverList.size());

		return serverList.get(offset);
	}
}
