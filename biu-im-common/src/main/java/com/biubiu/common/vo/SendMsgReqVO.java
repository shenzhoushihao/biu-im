package com.biubiu.common.vo;

import com.biubiu.common.data.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: roc
 * @description: send massage VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SendMsgReqVO extends BaseRequest {

	private String sendUserId;

	private String receiveUserId;

	private String receiveUsername;

	private String content;
}
