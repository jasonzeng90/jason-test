/*
 * /*********************************************************************
 * ====================================================================== CHANGE
 * HISTORY LOG
 * ---------------------------------------------------------------------- MOD.
 * NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * |2015年2月5日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: BaseException.java
 * @Package com.rockfintech.reas.core.common.exception
 * @date 2015年2月5日 下午2:07:57
 * @version V1.0
 */
package com.rockfintech.reas.xabank.exception;

/**
 * @Description 异常基类 其它层异常需继承此基类
 * @Date 2015年2月5日 下午2:07:57
 * @Copyright：上海钜石信息科技有限公司
 */
public class BaseException extends Exception {

	/**
	 * @Fields serialVersionUID :      
	 */

	private static final long serialVersionUID = -6077014216304140329L;

	public String messageCode;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String messageCode, String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String messageCode, String message) {

		super(message);
		this.messageCode = messageCode;
	}

}
