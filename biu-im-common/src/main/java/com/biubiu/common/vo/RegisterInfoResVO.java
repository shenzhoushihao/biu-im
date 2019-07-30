package com.biubiu.common.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author: roc
 * @description: register info VO for response
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RegisterInfoResVO implements Serializable {

	private static final long serialVersionUID = 7346407987234543345L;
	private String userId;
	private String username;
}