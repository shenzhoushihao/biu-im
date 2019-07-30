package com.biubiu.route.handler;

import com.biubiu.common.vo.ChatReqVO;
import com.biubiu.common.vo.IMServerResVO;

/**
 * 
 * @author: roc
 * @description: message handler
 */
public interface MessageHandler {

	/**
	 * send message
	 * 
	 * @param chatReqVO
	 * @param routeInfo
	 */
	void sendMsg(ChatReqVO chatReqVO, IMServerResVO routeInfo);
}
