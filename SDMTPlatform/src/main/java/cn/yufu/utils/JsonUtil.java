/**
 *包名:cn.yufu.utils
 *描述:package cn.yufu.utils;
 */
package cn.yufu.utils;

import java.util.LinkedList;
import java.util.List;

import cn.yufu.SDMTPlatform.entity.SysDict;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JsonUtil.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月27日
 * 描述:TODO
 */
public class JsonUtil {

	/**
	 * sysdict专用，json转list
	 * @param sr
	 * @return
	 */
	public static List<SysDict> jsonToList(String sr) {
		JSONObject jsonObject = JSONObject.fromObject(sr);
        List<SysDict> list = new LinkedList<SysDict>();
		String errorCode = jsonObject.getString("errorCode");
		if("000000".equals(errorCode)){
			JSONArray array = JSONArray.fromObject(jsonObject.getString("data")); 
			for(int i=0;i<array.size();i++){
				JSONObject jsonObjects = JSONObject.fromObject(array.get(i).toString());
				list.add((SysDict)JSONObject.toBean(jsonObjects,SysDict.class));
			}
		}
		return list;
	}
}
