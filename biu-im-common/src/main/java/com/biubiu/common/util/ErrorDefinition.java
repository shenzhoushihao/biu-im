package com.biubiu.common.util;

import lombok.Getter;

/**
 * 错误码定义
 */
@Getter
public enum ErrorDefinition {

	START_FAILURE("error_001", "IM start failure."),
	SERVER_NOT_FOUND("error_002", "no server is available."),
	ACCOUNT_NOT_FOUND("error_003", "account not found, please register."),
	ACCOUNT_NOT_MATCH("error_004", "account not match, please check."),
	REDIS_SCAN_FAILURE("error_005", "redis scan keys failure."),
	USER_OFF_LINE("error_006", "user off line."),
	NO_EXTRA_SERVER("error_007", "no extra server."),
	CLIENT_OFF_LINE("error_0078", "client off line.");

	private String errorCode;
	private String errorDescription;

	private ErrorDefinition(String errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
