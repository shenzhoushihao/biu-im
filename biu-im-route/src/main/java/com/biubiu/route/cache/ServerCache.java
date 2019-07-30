package com.biubiu.route.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biubiu.common.util.IMConstants;
import com.biubiu.route.utils.ZkUtil;

/**
 * 
 * @author: roc
 * @description: registry server list cache
 *
 */
@Component
public class ServerCache {

	@Autowired
	private ZkUtil zkUtil;

	private final ConcurrentHashMap<String, String> serverInfoCache = new ConcurrentHashMap<>(128);

	/**
	 * update all cache:offer consumer interface
	 * 
	 * @return
	 */
	public Consumer<List<String>> updateCache() {
		return (List<String> currentChilds) -> updateCache(currentChilds);
	}

	/**
	 * query all server list
	 * 
	 * @return
	 */
	public List<String> getAllServerList() {
		if (serverInfoCache.size() == 0) {
			List<String> allNode = zkUtil.getAllNode();
			for (String node : allNode) {
				// 172.17.2.48:8200:8300
				String key = node.substring(node.indexOf(IMConstants.HASHTAG_SEPARATOR));
				addCache(key);
			}
		}
		return serverInfoCache.keySet().stream().collect(Collectors.toList());
	}

	/**
	 * update all cache: delete first, then add
	 *
	 * @param currentChilds
	 */
	private void updateCache(List<String> currentChilds) {
		serverInfoCache.clear();
		for (String currentChild : currentChilds) {
			String key = currentChild.substring(currentChild.indexOf(IMConstants.HASHTAG_SEPARATOR));
			// 172.17.2.48:8200:8300
			addCache(key);
		}
	}

	private void addCache(String key) {
		serverInfoCache.put(key, key);
	}
}
