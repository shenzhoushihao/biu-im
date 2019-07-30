package com.biubiu.route.service;

import com.biubiu.common.vo.LoginReqVO;
import com.biubiu.common.vo.RegisterInfoReqVO;
import com.biubiu.common.vo.RegisterInfoResVO;
import com.biubiu.common.vo.SendMsgReqVO;

public interface AccountService {
	/**
	 * account register
	 * 
	 * @param registerInfoReqVO account info
	 * @return
	 * @throws Exception
	 */
	RegisterInfoResVO register(RegisterInfoReqVO registerInfoReqVO);

	/**
	 * account login
	 * 
	 * @param loginReqVO login info
	 * @return true:success false:failure
	 * @throws Exception
	 */
	boolean login(LoginReqVO loginReqVO);

	/**
	 * private chat push message
	 * 
	 * @param sendMsgVO
	 * @throws Exception
	 */
	void pushMsgForP2P(SendMsgReqVO sendMsgReqVO);

	/**
	 * group chat push message
	 * 
	 * @param sendMsgVO
	 * @throws Exception
	 */
	void pushMsgForGroup(SendMsgReqVO sendMsgReqVO);

	/**
	 * user offline
	 * 
	 * @param userId
	 * @throws Exception
	 */
	void offLine(String userId);
}
