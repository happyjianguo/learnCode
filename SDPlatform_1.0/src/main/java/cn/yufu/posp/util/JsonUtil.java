/**
 *����:cn.yufu.posp.util
 *����:package cn.yufu.posp.util;
 */
package cn.yufu.posp.util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * JsonUtil.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��20��
 * ����:
 */
public class JsonUtil {
	private static Logger log = LogManager.getLogger(JsonUtil.class);

	/**
	 * ����number���������nullת�� �����ʹ������: List<Order> orderList = new
	 * ArrayList<Order>(); orderList.add(new Order()); JSONObject json = new
	 * JSONObject(); json.elementOpt("order",
	 * orderList,JsonUtil.NUMBER_NULL_JSONCONF);
	 * ͨ�����ϵĴ���,order�����е�Integer���������null�ģ��ͻ��Զ�ת��json��null������Ĭ��Ϊ0
	 */
	public final static JsonConfig NUMBER_NULL_JSONCONF = createNumberNullValueJsonConfig();
	// ����number��null��������
	public final static JsonConfig DEFAULT_AND_DATTE_JSONCONF = createDefaultAndDateJsonConfig();

	/**
	 * ��һ��JSON �����ַ���ʽ�еõ�һ��java�������磺 {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			log.error("ת��object�쳣:", e);
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	/**
	 * ��һ��JSON �����ַ���ʽ�еõ�һ��java��������beansList��һ��ļ��ϣ����磺 {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            �������Ե����� (key : ����������, value : ������������class) eg: ("beansList" :
	 *            Bean.class)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject(String jsonString, Class clazz, Map map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			log.error("json string convert to obj error:", e);
			return null;
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * ��һ��JSON����õ�һ��java�������飬���磺 [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] getObjectArray(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * ��һ��JSON����õ�һ��java�������飬���磺 [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] getObjectArray(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * ��һ��JSON����õ�һ��java���󼯺�
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getObjectList(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * ��һ��JSON����õ�һ��java���󼯺ϣ����ж����а����м�������
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            �������Ե�����
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getObjectList(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹��� ���磺{"id" : "johncon", "name" : "Сǿ"}
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getMap(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * ��json�����еõ���Ӧjava���� json���磺["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	private static void setDataFormat2JAVA() {
		// �趨����ת����ʽ
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
	}

	/**
	 * �Ѷ���ת��Ϊjson�ַ��� ��������ΪĬ�ϵ�: YYYY-MM-DD HH:MM:SS
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj) {
		if (obj == null)
			return "{}";
		return getJsonString(obj, DEFAULT_AND_DATTE_JSONCONF);
	}

	/**
	 * ȡjson�ַ���
	 * 
	 * @param obj
	 * @param cfg
	 * @return
	 */
	public static String getJsonString(Object obj, JsonConfig cfg) {
		if (obj != null) {

			if (isArray(obj)) {
				JSONArray jsonArray = JSONArray.fromObject(obj, cfg);
				return jsonArray.toString();
			} else {

				JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
				return jsonObject.toString();
			}
		}
		return "{}";
	}

	/**
	 * �����Ƿ�������
	 * 
	 * @param obj
	 * @return
	 */
	private static boolean isArray(Object obj) {
		return obj instanceof Collection || obj.getClass().isArray();
	}

	/**
	 * ����number���������nullת��
	 * 
	 * @return
	 */
	public static JsonConfig createNumberNullValueJsonConfig() {
		JsonConfig conf = new JsonConfig();
		registerDefaultNullValueProcessor(conf);
		return conf;

	}

	/**
	 * ����ȫ��
	 * 
	 * @return
	 */
	public static JsonConfig createDefaultAndDateJsonConfig() {
		JsonConfig conf = new JsonConfig();
		registerDefaultNullValueProcessor(conf);
		registerDateValueProcessor(conf);
		return conf;
	}

	/**
	 * ����������ת��
	 * 
	 * @param conf
	 */
	public static void registerDateValueProcessor(JsonConfig conf) {
		conf.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
	}

	/**
	 * ע��json�Ĵ�����
	 * 
	 * @param conf
	 */
	public static void registerDefaultNullValueProcessor(JsonConfig conf) {
		conf.registerDefaultValueProcessor(Number.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(AtomicInteger.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(BigDecimal.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(BigInteger.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Byte.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Double.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Float.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Integer.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Long.class, new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Short.class, new DefaultNullValueProcessor());
	}

	/**
	 * null��ֵ���⴦����
	 * 
	 * @author zhaoxin
	 *
	 */
	public static class DefaultNullValueProcessor implements DefaultValueProcessor {
		@SuppressWarnings("rawtypes")
		public Object getDefaultValue(Class type) {
			return JSONNull.getInstance();
		}
	}
}
