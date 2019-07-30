package com.biubiu.common.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: roc
 * @description: select Server info VO
 */
@Data
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class IMServerResVO implements Serializable {
	private static final long serialVersionUID = 2697466624856691840L;
	private String ip;
	private Integer imProtoPort;
	private Integer serverPort;
}
