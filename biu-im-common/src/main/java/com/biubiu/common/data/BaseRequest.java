package com.biubiu.common.data;

import lombok.Data;

@Data
public class BaseRequest {
	/**
	 * unique request number
	 */
	private String reqNo;

	/**
	 * timeStamp of the current request, unit: milliseconds
	 */
	private Long timeStamp;

	public BaseRequest() {
		this.setTimeStamp(System.currentTimeMillis());
	}
}
