package com.biubiu.util;

import lombok.Getter;

/**
 * 错误码定义
 *
 */
@Getter
public enum Errordefinition {

	START_FAILURE("error_001", "IM start failure.");

	private String errorCode;
	private String errorDescription;

	private Errordefinition(String errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
