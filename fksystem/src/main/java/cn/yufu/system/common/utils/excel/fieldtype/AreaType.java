/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.common.utils.excel.fieldtype;

import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.sys.entity.Area;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author king
 * @version 2013-03-10
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
