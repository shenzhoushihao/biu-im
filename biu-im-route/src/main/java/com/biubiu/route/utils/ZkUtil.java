package com.biubiu.route.utils;

import java.util.List;
import java.util.function.Consumer;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.biubiu.common.util.IMConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: zookeeper utils
 */
@Slf4j
@Component
public class ZkUtil {

	@Autowired
	private ZkClient zkClient;

	public void subscribeEvent(String path, Consumer<List<String>> consumer) {
		zkClient.subscribeChildChanges(path, (String parentPath, List<String> currentChilds) -> {
			log.info("clear or update local cache,parentPath=【{}】,currentChilds=【{}】", parentPath,
					currentChilds.toString());
			// update cache : first delete,then add
			consumer.accept(currentChilds);
		});
	}

	/**
	 * query all server node
	 * 
	 * @return
	 */
	public List<String> getAllNode() {
		List<String> children = zkClient.getChildren(IMConstants.ZK_ROOT);
		log.info("registry center: query all server nodes,【{}】", JSON.toJSONString(children));
		return children;
	}

	/**
	 * create parent node
	 */
	public void createRootNode() {
		boolean exists = zkClient.exists(IMConstants.ZK_ROOT);
		if (exists) {
			return;
		}
		// create root
		zkClient.createPersistent(IMConstants.ZK_ROOT);
		log.info("create parent node success.");
	}

	/**
	 * write assign node,tmp dir
	 * 
	 * @param path
	 */
	public void createNode(String path) {
		zkClient.createEphemeral(path);
	}
}
