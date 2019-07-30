package com.biubiu.common.data;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author: roc
 * @description: 数据返回实体
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse {

	public static final String STATUS_OK = "OK";
	public static final String STATUS_FAIL = "FAIL";

	/**
	 * 返回状态
	 */
	private String status;
	/**
	 * 返回数据总数量
	 */
	private Integer totalCount;
	/**
	 * 返回数据列表
	 */
	private List<?> data;
	/**
	 * 返回错误码
	 */
	private String errorCode;
	/**
	 * 返回错误描述
	 */
	private String errorDescription;
	/**
	 * 返回数据列表
	 */
	private Collection<?> entities;
	/**
	 * 返回单个数据
	 */
	private Object entity;
	/**
	 * 分页查询时,是否第一页
	 */
	private Boolean first;
	/**
	 * 分页查询时,是否最后一页
	 */
	private Boolean last;
	/**
	 * 分页查询时,一页的记录个数
	 */
	private Integer size;
	/**
	 * 分页查询时,一页实际的记录个数
	 */
	private Integer number;
	/**
	 * 分页查询时,当前页的记录个数
	 */
	private Integer numberOfElements;
	/**
	 * 分页查询时,总共多少页
	 */
	private Integer totalPages;
	/**
	 * 分页查询时,总共多少记录
	 */
	private Long totalElements;
}
