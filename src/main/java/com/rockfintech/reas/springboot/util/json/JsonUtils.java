package com.rockfintech.reas.springboot.util.json;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @Description
 * @Author
 * @Date
 * @Copyright：
 */
public class JsonUtils {

	public static <T> T readValue(String content, Class<T> clazz) {
		T t =  JSON.parseObject(content,clazz);
		return t;
	}

	public static String writeValueAsString(Object o) {
		String str = JSON.toJSONString(o);
		return str;
	}


	public static String parseString(String str) {

		str = str.replace("{", "{\"");
		str = str.replace("=", "\":\"");
		str = str.replace(", ", "\",\"");
		str = str.replace("}", "\"}");
		str = str.replace("}\"", "}");
		str = str.replace("\"{", "{");

		return str;
	}

}
