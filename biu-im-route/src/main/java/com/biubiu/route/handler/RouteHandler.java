package com.biubiu.route.handler;

import java.util.List;

/**
 * @author: roc
 * @description: route handler
 */
public interface RouteHandler {

	/**
	 * Routing in a batch of servers
	 * 
	 * @param serverList
	 * @param key
	 * @return
	 */
	String routeServer(List<String> serverList, String key);
}