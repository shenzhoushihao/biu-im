package com.biubiu.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biubiu.common.data.ApiResponse;
import com.biubiu.common.vo.ChatReqVO;
import com.biubiu.route.rest.AbstractController;
import com.biubiu.server.service.MessageService;

/**
 * 
 * @author roc
 * @description message controller
 */
@RestController
@RequestMapping(value = "/")
public class IndexController extends AbstractController {

	@Autowired
	private MessageService messageService;

	/**
	 * send message by server
	 * 
	 * @param chatReqVO
	 * @return
	 */
	@PostMapping(value = "sendMsg")
	public ResponseEntity<ApiResponse> sendMsg(@RequestBody ChatReqVO chatReqVO) {
		return createResponseEntity(messageService.sendMsg(chatReqVO));
	}
}
