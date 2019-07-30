package com.biubiu.server.service;

import com.biubiu.common.vo.ChatReqVO;

public interface MessageService {

	/**
	 * send message
	 * 
	 * @param chatReqVO
	 * @return
	 */
	boolean sendMsg(ChatReqVO chatReqVO);
}
