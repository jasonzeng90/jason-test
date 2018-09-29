/*
 * /********************************************************************* ======================================================================
 * CHANGE HISTORY LOG ---------------------------------------------------------------------- MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ---------------------------------------------------------------------- |2015年2月5日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: BizException.java
 * @Package com.rockfintech.reas.core.common.exception
 * @date 2015年2月5日 下午2:12:45
 * @version V1.0
 */
package com.rockfintech.reas.xabank.exception;

/**
 * @Description 业务层异常
 * @Date 2015年2月5日 下午2:12:45
 * @Copyright：上海钜石信息科技有限公司
 */
public class BizException extends BaseException {

	private static final long serialVersionUID = 7946304343713696205L;

	public BizException() {
		super();
	}
	public BizException(BizExceptionCode code) {
		super(code.desc);
		this.messageCode = code.code;
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String messageCode, String message, Throwable cause) {
		super(message, cause);
		this.messageCode = messageCode;
	}

	public BizException(String messageCode, String message, Object... args) {
		super(String.format(message, args));
		this.messageCode = messageCode;
	}

}
