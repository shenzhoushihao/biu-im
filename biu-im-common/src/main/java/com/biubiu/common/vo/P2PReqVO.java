package com.biubiu.common.vo;

import javax.validation.constraints.NotNull;

import com.biubiu.common.data.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: roc
 * @description: private chat
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class P2PReqVO extends BaseRequest {

	@NotNull(message = "userId not null.")
	private String userId;

	@NotNull(message = "userId not null.")
	private String receiveUserId;

	@NotNull(message = "mcontent not null.")
	private String content;
}
