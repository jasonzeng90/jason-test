package com.rockfintech.reas.springboot.common.req;

import java.io.Serializable;

/**
 * 请求基类
 * 2018年3月9日下午2:05:05
 * @author simon.sheng
 */
public class BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7696519728326931127L;

	/**
	 * 请求日志号
	 */
	private String reqId;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
}
