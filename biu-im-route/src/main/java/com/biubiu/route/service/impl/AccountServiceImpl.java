package com.biubiu.route.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.ErrorDefinition;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.vo.ChatReqVO;
import com.biubiu.common.vo.IMServerResVO;
import com.biubiu.common.vo.LoginReqVO;
import com.biubiu.common.vo.RegisterInfoReqVO;
import com.biubiu.common.vo.RegisterInfoResVO;
import com.biubiu.common.vo.SendMsgReqVO;
import com.biubiu.route.cache.ServerCache;
import com.biubiu.route.cache.UserInfoCache;
import com.biubiu.route.common.BiuIMUtil;
import com.biubiu.route.handler.MessageHandler;
import com.biubiu.route.handler.RouteHandler;
import com.biubiu.route.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: account manager
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BiuIMUtil biuIMUtil;

	@Autowired
	private ServerCache serverCache;

	@Autowired
	private UserInfoCache userInfoCache;

	@Autowired
	private RouteHandler routeHandler;

	@Autowired
	private MessageHandler messageHandler;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public RegisterInfoResVO register(RegisterInfoReqVO registerInfoReqVO) {
		RegisterInfoResVO registerInfoResVO = new RegisterInfoResVO();
		String userId = createUserId();
		String key = String.format(IMConstants.ACCOUNT_PREFIX, userId);
		String username = redisTemplate.boundValueOps(key).get();

		if (StringUtils.isBlank(username)) {
			username = registerInfoReqVO.getUsername();
			redisTemplate.boundValueOps(key).set(username);
			redisTemplate.boundValueOps(username).set(key);
			log.info("register username:{}, success.", username);
		} else {
			log.info("have username:{}, not register", username);
		}
		registerInfoResVO.setUserId(userId);
		registerInfoResVO.setUsername(username);
		return registerInfoResVO;
	}

	@Override
	public boolean login(LoginReqVO loginReqVO) {
		String key = String.format(IMConstants.ACCOUNT_PREFIX, loginReqVO.getUserId());
		String username = redisTemplate.boundValueOps(key).get();
		if (StringUtils.isBlank(username)) {
			throw new BiuException(ErrorDefinition.ACCOUNT_NOT_FOUND);
		}
		if (!username.equals(loginReqVO.getUsername())) {
			throw new BiuException(ErrorDefinition.ACCOUNT_NOT_MATCH);
		}
		// login success, add login status
		boolean loginStatus = userInfoCache.saveUserLoginStatus(loginReqVO.getUserId());
		if (loginStatus) {
			// get one server,bind user
			selectServer(loginReqVO.getUserId());
		}
		return loginStatus;
	}

	public boolean selectServer(String userId) {
		List<String> allServerList = serverCache.getAllServerList();
		String routeServer = routeHandler.routeServer(allServerList, "key");
		// save user route info
		saveRouteInfo(userId, routeServer);
		log.info("user:{}, get one server:{}.", userId, routeServer);
		return true;
	}

	private void saveRouteInfo(String userId, String server) {
		// key: im-route:{userId}, value: 172.17.2.48:8200:8300
		String key = String.format(IMConstants.ROUTE_PREFIX, userId);
		redisTemplate.boundValueOps(key).set(server);
	}

	@Override
	public void pushMsgForP2P(SendMsgReqVO sendMsgReqVO) {
		IMServerResVO routeInfo = loadRouteRelatedByUserId(sendMsgReqVO.getReceiveUserId());
		IMUserInfo imUserInfo = userInfoCache.getImUserInfo(sendMsgReqVO.getReceiveUserId());
		sendMsgReqVO.setReceiveUsername(imUserInfo.getUsername());
		pushMessage(sendMsgReqVO, routeInfo);
	}

	@Override
	public void pushMsgForGroup(SendMsgReqVO sendMsgReqVO) {
		// acquire all routes
		Map<String, IMServerResVO> serverResVOMap = loadRouteRelated();
		for (Map.Entry<String, IMServerResVO> serverResVO : serverResVOMap.entrySet()) {
			String userId = serverResVO.getKey();
			IMServerResVO routeInfo = serverResVO.getValue();
			IMUserInfo imUserInfo = userInfoCache.getImUserInfo(userId);
			if (userId.equals(sendMsgReqVO.getReceiveUserId())) {
				// filter myself
				log.warn("group send message, filter myself, sender-user:{}", imUserInfo.toString());
				continue;
			}
			sendMsgReqVO.setReceiveUserId(userId);
			sendMsgReqVO.setReceiveUsername(imUserInfo.getUsername());
			pushMessage(sendMsgReqVO, routeInfo);
		}
	}

	@Override
	public void offLine(String userId) {
		// delete route
		String key = String.format(IMConstants.ROUTE_PREFIX, userId);
		redisTemplate.delete(key);
		// delete login status
		userInfoCache.removeLoginStatus(userId);
	}

	private void pushMessage(SendMsgReqVO sendMsgReqVO, IMServerResVO routeInfo) {
		ChatReqVO chatReqVO = ChatReqVO.builder()
				.sendUserId(sendMsgReqVO.getSendUserId())
				.receiveUsername(sendMsgReqVO.getReceiveUsername())
				.receiveUserId(sendMsgReqVO.getReceiveUserId())
				.content(sendMsgReqVO.getContent())
				.build();
		messageHandler.sendMsg(chatReqVO, routeInfo);
	}

	private Map<String, IMServerResVO> loadRouteRelated() {
		Map<String, IMServerResVO> routes = new HashMap<>(128);
		Set<String> routeKeySet = biuIMUtil.scan(String.format(IMConstants.ROUTE_PREFIX, IMConstants.STAR_SEPARATOR));
		if (!CollectionUtils.isEmpty(routeKeySet)) {
			for (String routeKey : routeKeySet) {
				String userId = routeKey.substring(routeKey.indexOf(IMConstants.COLON_SEPARATOR));
				String serverInfo = redisTemplate.boundValueOps(routeKey).get();
				String[] server = serverInfo.split(IMConstants.COLON_SEPARATOR);
				IMServerResVO imServerResVO = new IMServerResVO(server[0], Integer.parseInt(server[1]),
						Integer.parseInt(server[2]));
				routes.put(userId, imServerResVO);
			}
		}
		return routes;
	}

	private IMServerResVO loadRouteRelatedByUserId(String userId) {
		String serverRoute = redisTemplate.boundValueOps(String.format(IMConstants.ROUTE_PREFIX, userId)).get();
		if (StringUtils.isNotBlank(serverRoute)) {
			log.info("user:{} already off line, timestap:{}.", userId, System.currentTimeMillis());
			throw new BiuException(ErrorDefinition.USER_OFF_LINE);
		}
		// acquire server info
		String[] serverInfo = serverRoute.split(IMConstants.COLON_SEPARATOR);
		return new IMServerResVO(serverInfo[0], Integer.parseInt(serverInfo[1]), Integer.parseInt(serverInfo[2]));
	}

	/**
	 * create userid
	 * 
	 * @return
	 */
	private String createUserId() {
		return UUID.randomUUID().toString().toLowerCase().replace("-", "");
	}
}
