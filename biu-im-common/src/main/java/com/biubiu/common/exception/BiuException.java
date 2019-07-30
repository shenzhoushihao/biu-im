package com.biubiu.common.exception;

import com.biubiu.common.util.ErrorDefinition;

/**
 * 自定义异常
 */
public class BiuException extends RuntimeException {

	private static final long serialVersionUID = 83755835619088384L;

	private ErrorDefinition errorDefinition;

	public BiuException(String msg) {
		super(msg);
	}

	public BiuException(ErrorDefinition errorDefinition) {
		super(errorDefinition.getErrorDescription());
		this.errorDefinition = errorDefinition;
	}

	public ErrorDefinition getErrorDefinition() {
		return this.errorDefinition;
	}
}
