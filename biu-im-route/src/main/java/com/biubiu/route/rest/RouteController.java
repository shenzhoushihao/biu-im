package com.biubiu.route.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biubiu.common.data.ApiResponse;
import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.vo.LoginReqVO;
import com.biubiu.common.vo.RegisterInfoReqVO;
import com.biubiu.common.vo.SendMsgReqVO;
import com.biubiu.route.cache.UserInfoCache;
import com.biubiu.route.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: rest api
 */
@Slf4j
@RestController
@RequestMapping("/")
public class RouteController extends AbstractController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserInfoCache userInfoCache;

	/**
	 * groupRoute
	 * 
	 * @param groupReqVO
	 * @return
	 */
	@PostMapping(value = "groupRoute")
	public ResponseEntity<ApiResponse> groupRoute(@RequestBody SendMsgReqVO groupReqVO) {
		log.info("group chat message:[{}]", groupReqVO.toString());
		accountService.pushMsgForGroup(groupReqVO);
		return createResponseEntity(true);
	}

	/**
	 * private chat
	 *
	 * @param p2pRequest
	 * @return
	 */
	@PostMapping(value = "p2pRoute")
	public ResponseEntity<ApiResponse> p2pRoute(@RequestBody SendMsgReqVO p2pRequest) {
		log.info("p2p chat message:[{}]", p2pRequest.toString());
		accountService.pushMsgForP2P(p2pRequest);
		return createResponseEntity(true);
	}

	/**
	 * account register
	 * 
	 * @param registerInfoReqVO
	 * @return
	 */
	@PostMapping(value = "registerAccount")
	public ResponseEntity<ApiResponse> registerAccount(@RequestBody RegisterInfoReqVO registerInfoReqVO) {
		return createResponseEntity(accountService.register(registerInfoReqVO));
	}

	/**
	 * login and acquire one server
	 * 
	 * @param loginReqVO
	 * @return
	 */
	@PostMapping(value = "login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginReqVO loginReqVO) {
		return createResponseEntity(accountService.login(loginReqVO));
	}

	/**
	 * account offline
	 * 
	 * @param groupReqVO
	 * @return
	 */
	@PostMapping(value = "offLine")
	public ResponseEntity<ApiResponse> offLine(@RequestBody LoginReqVO loginReqVO) {
		IMUserInfo imUserInfo = userInfoCache.getImUserInfo(loginReqVO.getUserId());
		log.info("offLine account:[{}]", imUserInfo.toString());
		accountService.offLine(loginReqVO.getUserId());
		return createResponseEntity();
	}

	/**
	 * query all online user
	 * 
	 * @return
	 */
	@PostMapping(value = "onlineUser")
	public ResponseEntity<ApiResponse> getOnlineUser() {
		return createResponseEntity(userInfoCache.onlineUser());
	}
}
