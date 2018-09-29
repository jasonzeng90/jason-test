/*
 * /*********************************************************************
 * ====================================================================== CHANGE
 * HISTORY LOG
 * ---------------------------------------------------------------------- MOD.
 * NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * |2015年2月6日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: AbstractBO.java
 * @Package com.rockfintech.reas.core.common.biz
 * @date 2015年2月6日 下午4:27:50
 * @version V1.0
 */
package com.rockfintech.reas.xabank.biz;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Description 业务服务层基类
 * @Date 2015年2月6日 下午4:27:50
 * @Copyright：上海钜石信息科技有限公司
 */
public abstract class AbstractBO extends BaseLogger implements InitializingBean {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

}
