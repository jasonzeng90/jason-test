/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年11月1日 上午11:31:24
 */
package com.rockfintech.reas.xabank.exception;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 断言
 * 
 * @title
 * @description
 * @since JDK1.8
 */
public class Assert {

	/**
	 * 字符串转化
	 * 
	 * eg: null ---> "" "str" ---> "str
	 * 
	 * @param str
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月15日 下午12:23:38
	 */
	public static String convertStr(String str) {

		if (str == null) {

			return "";
		} else {

			return str;
		}
	}

	/**
	 * 非空校验
	 * 
	 * @param o 对象
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:36:43
	 */
	public static void isNotNull(Object o, String code, String msg) throws BizException {

		if (o == null) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 空校验
	 *
	 * @param o 对象
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:36:43
	 */
	public static void isNull(Object o, String code, String msg) throws BizException {

		if (o != null) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 非空校验
	 * 
	 * @param str 被校验字符串
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:37:14
	 */
	public static void isNotNull(String str, String code, String msg) throws BizException {

		if (StringUtils.isBlank(str)) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 空校验
	 *
	 * @param str 被校验字符串
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:37:14
	 */
	public static void isNull(String str, String code, String msg) throws BizException {

		if (StringUtils.isNotBlank(str)) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 正数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:37:37
	 */
	public static void isPositiveNum(int num, String code, String msg) throws BizException {

		if (num <= 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 正数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:37:59
	 */
	public static void isPositiveNum(Double num, String code, String msg) throws BizException {

		if (num <= 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 正数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:38:05
	 */
	public static void isPositiveNum(BigDecimal num, String code, String msg) throws BizException {

		if (num == null || num.compareTo(BigDecimal.ZERO) <= 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 非正数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:41:30
	 */
	public static void isNotPositiveNum(int num, String code, String msg) throws BizException {

		if (num > 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 相等字符校验
	 * 
	 * @param str
	 * @param tag
	 * @param code
	 * @param msg
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:47:51
	 */
	public static void isEquelStr(String str, String tag, String code, String msg) throws BizException {

		isNotNull(str, code, msg);

		if (!str.equals(tag)) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 相等字符校验
	 * 
	 * @param str
	 * @param tag
	 * @param code
	 * @param msg
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:47:51
	 */
	public static void isNotEquelStr(String str, String tag, String code, String msg) throws BizException {

		isNotNull(str, code, msg);

		if (str.equals(tag)) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 非负数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:41:30
	 */
	public static void isUnMinusNum(int num, String code, String msg) throws BizException {

		if (num < 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 非负数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:41:30
	 */
	public static void isUnMinusNum(Double num, String code, String msg) throws BizException {

		if (num < 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 非负数校验
	 * 
	 * @param num 被校验数字
	 * @param code 错误码
	 * @param msg 错误信息
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年11月1日 上午11:41:30
	 */
	public static void isUnMinusNum(BigDecimal num, String code, String msg) throws BizException {

		if (num == null || num.compareTo(BigDecimal.ZERO) < 0) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 布尔判断
	 * 
	 * @param b
	 * @param code
	 * @param msg
	 * @throws BizException
	 */
	public static void isTrue(boolean b, String code, String msg) throws BizException {

		if (!b) {

			throw new BizException(code, msg);
		}
	}

	/**
	 * 纯数字校验
	 * 
	 * @param value 目标值
	 * @param code
	 * @param msg
	 * @throws BizException
	 */
	public static void isNumber(String value, String code, String msg) throws BizException {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if (!pattern.matcher(value.toString()).matches()) {
			throw new BizException(code, msg);
		}
	}

	/**
	 * 目标值 为纯数字 且长度小于等于目标长度
	 * 
	 * @param value 目标值
	 * @param length 目标长度
	 * @param code
	 * @param msg
	 * @throws BizException
	 */
	public static void CheckDigitalLength(String value, int length, String code, String msg) throws BizException {
		isNumber(value, code, msg);
		if (value.length() > length) {
			throw new BizException(code, msg);
		}
	}

}
