package com.biubiu.exception;

import com.biubiu.util.Errordefinition;

/**
 * 自定义异常
 */
public class BiuException extends RuntimeException {

	private static final long serialVersionUID = 83755835619088384L;

	private Errordefinition errordefinition;

	public BiuException(String msg) {
		super(msg);
	}

	public BiuException(Errordefinition errordefinition) {
		super(errordefinition.getErrorDescription());
		this.errordefinition = errordefinition;
	}

	public Errordefinition getErrordefinition() {
		return this.errordefinition;
	}
}
