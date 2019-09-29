/*
 * 创建日期 2006-1-19 guozl
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package cn.yufu.core.common.util;

/**
 * @author guozl
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class NumberUtil {
	/**
	 * 判断是否数字
	 * @param number
	 * @return
	 */
	public static boolean isNumeric(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException sqo) {
			return false;
		}
	}

}