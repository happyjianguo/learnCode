package cn.yufu.system.common.utils.excel;

import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 导出时，根据ID获取对应的描述
 * 
 * */
public class ExcelDescUtils {

	public static String getDescById(String value, String type, String defaultValue) {
		String result = "";
		if (StringUtils.isEmpty(type)) return defaultValue;
		switch (type) {
			case Global.IS_EXISTS:
				if ("0".equals(value)) {
					result = "存在";
				}else{
					result = "不存在";
				}
				break;
			case Global.USER_ID:
				result = UserUtils.get(value).getName();
				break;
			default:
				break;
		}
		return result;
	}

}
