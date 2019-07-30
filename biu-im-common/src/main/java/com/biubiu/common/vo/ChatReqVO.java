package com.biubiu.common.vo;

import com.biubiu.common.data.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ChatReqVO extends BaseRequest {

	private String sendUserId;

	private String sendUsername;

	private String receiveUserId;

	private String receiveUsername;

	private String content;
}
