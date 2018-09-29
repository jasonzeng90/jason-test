/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Fan.W
 * 
 * @mail
 * 
 * @createtime 2017年10月24日 下午2:06:48
 */
package com.rockfintech.reas.xabank.vo.mq;

import java.util.Map;

/**
 * 调用方充值提现回调队列bean
 * 
 * @title
 * @description
 * @since JDK1.8
 */
public class RabbitMqCommon {
	/***
	 * 存放回调参数
	 */
	private Map<String, Object> callbackMap;

	/***
	 * 指定交换机名称
	 */
	private String exchange;
	/***
	 * 指定队列名称
	 */
	private String queueName;
	/***
	 * 设置消息过期时间
	 */
	private String expireTime;
	/***
	 * 回调次数
	 */
	private int count;

	/**
	 * 地址
	 */
	private String url;
	/**
	 * 业务名称
	 */
	private String businessName;
    /**
     * 新增参数,用于标识每个消息的唯一ID
     */
    private String messageId;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count 要设置的 count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return exchange
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * @param exchange 要设置的 exchange
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName 要设置的 queueName
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime 要设置的 expireTime
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @return callbackMap
	 */
	public Map<String, Object> getCallbackMap() {
		return callbackMap;
	}

	/**
	 * @param callbackMap 要设置的 callbackMap
	 */
	public void setCallbackMap(Map<String, Object> callbackMap) {
		this.callbackMap = callbackMap;
	}

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
