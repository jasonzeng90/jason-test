package com.rockfintech.reas.xabank.util.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @title Spring环境工具
 * @description 封装与Spring环境信息交互工具函数
 * @since Java7
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	/** Spring应用上下文环境 */
	private static ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 按bean-id与类型从spring环境中取得bean实例
	 *
	 * @param id bean-id
	 * @param type 类型
	 * @return 获得的实例
	 * */
	public static <T> T getBean(String id, Class<T> type) {
		return applicationContext.getBean(id, type);
	}

	/**
	 * 按bean类型从spring环境中取得bean实例
	 *
	 * @param type 类型
	 * @return 实例
	 */
	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	/**
	 * 按bean类型从spring环境中取得bean实例列表
	 *
	 * @param type 类型
	 * @return 实例表单
	 */
	public static <T> Map<String, T> getBeans(Class<T> type) {
		return applicationContext.getBeansOfType(type);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 取得配置参数
	 *
	 * @param name 参数名
	 * @return 参数值
	 */
	public static String getProperty(String name) {


		return applicationContext.getEnvironment().getProperty(name);
	}

}
