/*
 * /********************************************************************* ======================================================================
 * CHANGE HISTORY LOG ---------------------------------------------------------------------- MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ---------------------------------------------------------------------- |2014年11月12日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: LogInteceptor.java
 * @Package com.rockfintech.reas.business.aspectj
 * @author liubing@buyforyou.cn
 * @date 2014年11月12日 下午2:38:28
 * @version V1.0
 */
package com.rockfintech.reas.springboot.aspectj;

import com.alibaba.fastjson.JSON;

import com.rockfintech.reas.springboot.checker.EnumType;
import com.rockfintech.reas.springboot.checker.NotNull;
import com.rockfintech.reas.springboot.checker.NumberType;
import com.rockfintech.reas.springboot.checker.TextType;
import com.rockfintech.reas.springboot.common.constant.ConstError;
import com.rockfintech.reas.springboot.exception.BizException;
import com.rockfintech.reas.springboot.util.ReflectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description Controller拦截AOP输出
 * @Author liubing
 * @Date 2014年11月12日 下午2:38:28
 * @Copyright：上海钜石信息科技有限公司
 */
@Aspect
@Component
public class ControllerInteceptor {

	private static Logger logger = LoggerFactory.getLogger(ControllerInteceptor.class);

	@Autowired
	private HttpServletRequest request;

	// 正长度判断为getByte方式，解决中文歧义
	private static final String ENCODING = "UTF-8";

	// @Autowired
	// private AccessHelper accessHelper;

	/**
	 * 拦截类的入口--拦截所有controller类
	 */
	@Pointcut("execution(public * com.rockfintech.reas.springboot.controller.*.*(..)) ")
	public void pointCut() {
	}

	// @Before("pointCut()")
	// public void before() {
	// log.info("被拦截方法调用之前调用此方法，输出此语句");
	// }
	//
	// @After("pointCut()")
	// public void after() {
	// logger.info("被拦截方法调用之后调用此方法，输出此语句");
	// }

	@SuppressWarnings("rawtypes")
	@Around("pointCut()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getName(); // 拦截类
		String methodName = pjp.getSignature().getName() + "()";

		StringBuffer params = new StringBuffer();
		Object[] args = pjp.getArgs();
		if (args != null) {
			Map<String, String> resultMap = new HashMap<String, String>();
			for (int i = 0; i < args.length; i++) {
				Object obj = args[i];
				params.append(JSON.toJSONString(obj));
				// 校验对象中的属性
				resultMap = checking(obj);

			}

			logger.info("{}.{} 业务请求报文:{}", new Object[] { className, methodName, params });

			if ("true".equals(resultMap.get("paramError"))) {
				throw new BizException(ConstError.ER000101_CODE, ConstError.ER000101_MESSAGE, resultMap.get("errorField"));
			}
		}

		Object obj = null;
		// 此处返回的是拦截的方法的返回值，如果不执行此方法，则不会执行被拦截的方法，利用环绕通知可以很好的做权限管理
		obj = pjp.proceed();
		Object logObj = obj;
		if (obj != null) {
			if (obj instanceof List && ((List) obj).size() > 10) {
				logObj = ((List) obj).size();
			}
		}

		logger.info("{}.{} 业务响应报文:{} ", new Object[] { className, methodName, JSON.toJSONString(logObj) });

		return obj;
	}

	/**
	 * 校验对象的属性值
	 * 
	 * @param obj
	 * @return true 校验不通过 false 校验通过
	 * @throws Exception
	 */
	private Map<String, String> checking(Object obj) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();

		Field[] fields = ReflectionUtils.getDeclaredField(obj);
		for (Field field : fields) {
			String filedName = field.getName();
			Object singleParam = ReflectionUtils.getInvoke(obj, filedName, false);
			if (field.isAnnotationPresent(NotNull.class)) {
				if (singleParam == null || "".equals(singleParam)) {
					logger.debug(filedName + " cannot be null");
					resultMap.put("paramError", "true");
					resultMap.put("errorField", filedName);
					break;
				}
			} else if (field.isAnnotationPresent(TextType.class)) {
				TextType textType = field.getAnnotation(TextType.class);
				boolean notNull = textType.notNull();
				if (notNull) {
					if (singleParam == null || "".equals(singleParam)) {
						logger.debug(filedName + " cannot be empty");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
				if (singleParam != null && !"".equals(singleParam)) {
					int max = textType.maxLength();
					int min = textType.minLength();
					if (singleParam.toString().getBytes(ENCODING).length < min) {
						logger.debug(filedName + " minLength is [" + min + "], but input ["
								+ singleParam.toString().length() + "]");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					} else if (singleParam.toString().getBytes(ENCODING).length > max) {
						logger.debug(filedName + " maxLength is [" + max + "], but input ["
								+ singleParam.toString().length() + "]");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
			} else if (field.isAnnotationPresent(EnumType.class)) {
				EnumType enumType = field.getAnnotation(EnumType.class);
				boolean notNull = enumType.notNull();
				if (notNull) {
					if (singleParam == null || "".equals(singleParam)) {
						logger.debug(filedName + " cannot be empty");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
				if (singleParam != null && !"".equals(singleParam)) {
					String[] allows = enumType.allows();
					Arrays.sort(allows);
					int index = Arrays.binarySearch(allows, singleParam.toString());
					if (index < 0) {
						logger.debug(filedName + " must in [" + Arrays.toString(allows) + "], but input ["
								+ singleParam.toString() + "] is not in");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
			} else if (field.isAnnotationPresent(NumberType.class)) {
				NumberType numberType = field.getAnnotation(NumberType.class);
				boolean notNull = numberType.notNull();
				if (notNull) {
					if (singleParam == null || "".equals(singleParam)) {
						logger.debug(filedName + " cannot be empty");
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
				if (singleParam != null && !"".equals(singleParam)) {
					int length = numberType.length();
					Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
					// Pattern pattern =
					// Pattern.compile("^[-\\+]?(([1-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2})))$");
					if (!pattern.matcher(singleParam.toString()).matches()
							|| singleParam.toString().length() > length) {
						resultMap.put("paramError", "true");
						resultMap.put("errorField", filedName);
						break;
					}
				}
			}
		}
		return resultMap;
	}
}
