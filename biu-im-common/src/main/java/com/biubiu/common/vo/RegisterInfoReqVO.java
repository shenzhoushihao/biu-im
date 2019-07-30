package com.biubiu.common.vo;

import com.biubiu.common.data.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: roc
 * @description: register info VO for request
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RegisterInfoReqVO extends BaseRequest {
	private String username;
}