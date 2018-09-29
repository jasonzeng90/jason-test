package com.rockfintech.reas.xabank.common.res;

import java.io.Serializable;

/**
 * 应答基类
 * 2018年3月9日下午2:55:14
 * @author simon.sheng
 */
public class BaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3139953403087817453L;
	
	/**
	 * 请求日志号
	 */
	private String reqId;
	
	/**
	 * 应答码
	 */
	private String code;

	/**
	 * 应答信息
	 */
	private String message;

}
