/*
 * /*********************************************************************
 * ====================================================================== CHANGE
 * HISTORY LOG
 * ---------------------------------------------------------------------- MOD.
 * NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * |2015年3月16日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: DaoException.java
 * @Package com.rockfintech.reas.core.common.exception
 * @date 2015年3月16日 下午6:10:34
 * @version V1.0
 */
package com.rockfintech.reas.springboot.exception;

/**
 * @Description DAO层异常
 * @Date 2015年3月16日 下午6:10:34
 * @Copyright：上海钜石信息科技有限公司
 */
public class DaoException extends BaseException {

	public DaoException() {
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DaoException(String message) {
		super(message);
	}

}
