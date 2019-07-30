package com.biubiu.common.vo;

import com.biubiu.common.data.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: roc
 * @description: login info VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LoginReqVO extends BaseRequest {
	private String userId;
	private String username;
}
