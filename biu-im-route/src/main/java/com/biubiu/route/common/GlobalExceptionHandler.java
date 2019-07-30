package com.biubiu.route.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biubiu.common.data.ApiResponse;
import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.ErrorDefinition;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: handle global exception
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * handle BiuException
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BiuException.class)
	public ResponseEntity<ApiResponse> handleBiuException(BiuException e) {
		log.error("handle exception:" + e);
		ApiResponse response = new ApiResponse();
		response.setStatus(ApiResponse.STATUS_FAIL);
		ErrorDefinition errorDefinition = e.getErrorDefinition();
		response.setErrorCode(errorDefinition.getErrorCode());
		response.setErrorDescription(errorDefinition.getErrorDescription());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
