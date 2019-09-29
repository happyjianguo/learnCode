package com.jansh.comm.util;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final Logger logger = LogManager.getLogger(JsonUtil.class);

	public static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String obj2json(Object obj) throws JsonProcessingException {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("object:{}转换成json字符串异常：{}", obj, e);
			throw e;
		}
	}

	/**
	 * json字符串转换成Map<String, String>类型
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> readMapString(String json) throws Exception {
		try {
			return mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
		} catch (Exception e) {
			logger.error("读取json字符串:{},异常:{}", json, e);
			throw e;
		}
	}

	/**
	 * json字符串转换成Map<String, Object>类型
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> readMapObject(String json) throws Exception {
		try {
			return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			logger.error("读取json字符串:{},异常:{}", json, e);
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T readObject(String json, TypeReference valueTypeRef) throws Exception {
		try {
			return (T) mapper.readValue(json, valueTypeRef);
		} catch (Exception e) {
			logger.error("读取json字符串:{},异常:{}", json, e);
			throw e;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T readObject(String json, Class clazz) throws Exception {
		try {
			return (T) mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("读取json字符串:{},异常:{}", json, e);
			throw e;
		}
	}
}
