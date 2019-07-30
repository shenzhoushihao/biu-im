package com.biubiu.route.handler.algorithm;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.IMConstants;
import com.biubiu.common.vo.ChatReqVO;
import com.biubiu.common.vo.IMServerResVO;
import com.biubiu.route.handler.MessageHandler;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Component("messageHandler")
public class MessageProcessor implements MessageHandler {

	@Autowired
	private OkHttpClient okHttpClient;

	private static final String HTTP_HEAD = "http://";
	private static final String HTTP_TAIL = "/sendMsg";

	@Override
	public void sendMsg(ChatReqVO chatReqVO, IMServerResVO routeInfo) {
		// push message
		String url = HTTP_HEAD + routeInfo.getIp() + IMConstants.COLON_SEPARATOR + routeInfo.getServerPort()
				+ HTTP_TAIL;
		String jsonString = JSONObject.toJSONString(chatReqVO);
		RequestBody requestBody = RequestBody.create(IMConstants.APPLICATION_JSON_MEDIA_TYPE, jsonString);
		Request request = new Request.Builder().url(url).post(requestBody).build();

		Response response = null;
		try {
			response = okHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				log.error("send message is failure. Unexpected code:{}", response);
				throw new BiuException("send message is failure. Unexpected code:" + response);
			}
		} catch (IOException e) {
			log.error("send message is failure. Throw e:{}", e);
			throw new BiuException("send message is failure. Throw e:" + e);
		} finally {
			if (response != null) {
				response.body().close();
			}
		}
	}
}
