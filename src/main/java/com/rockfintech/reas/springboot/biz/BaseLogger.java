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
 * @Title: BaseLogger.java
 * @Package com.rockfintech.reas.core.common.bi
 * @date 2015年2月5日 下午4:58:31
 * @version V1.0
 */
package com.rockfintech.reas.springboot.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description Log基类,所有的类默认继承此类,可以直接使用 logger 记录日志,例如 logger.error("error");
 * @Date 2015年2月5日 下午4:58:31
 * @Copyright：上海钜石信息科技有限公司
 */
public class BaseLogger {

	public Logger logger = LoggerFactory.getLogger(getClass());

}
