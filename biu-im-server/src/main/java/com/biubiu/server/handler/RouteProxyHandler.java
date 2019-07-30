package com.biubiu.server.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.util.SessionSocketHolder;
import com.biubiu.server.config.IMServerConfiguration;

import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Component
public class RouteProxyHandler {

	@Autowired
	private OkHttpClient okHttpClient;

	@Autowired
	private IMServerConfiguration imServerConfig;

	/**
	 * client off line
	 * 
	 * @param userInfo
	 * @param channel
	 */
	public void userOffLine(IMUserInfo userInfo, NioSocketChannel channel) {
		if (userInfo != null) {
			log.info("client [{}] offline from [server:routeHandler]", userInfo.getUsername());
			SessionSocketHolder.removeSession(userInfo.getUserId());
			// 清除路由关系
			clearRouteInfo(userInfo);
		}
		SessionSocketHolder.removeChannel(channel);
	}

	/**
	 * clear route info
	 * 
	 * @param userInfo
	 * @param url
	 */
	private void clearRouteInfo(IMUserInfo userInfo) {
		String address = imServerConfig.getOffLineAddress();
		String jsonString = JSONObject.toJSONString(userInfo);
		RequestBody requestBody = RequestBody.create(IMConstants.APPLICATION_JSON_MEDIA_TYPE, jsonString);
		Request request = new Request.Builder().url(address).post(requestBody).build();

		Response response = null;
		try {
			response = okHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new IOException("client offline,unexpected code " + response);
			}
		} catch (IOException e) {
			log.error("client offline is failure, throw e:{}", e);
			throw new BiuException("client offline is failure. throw e:" + e);
		} finally {
			if (response != null) {
				response.body().close();
			}
		}
	}
}
