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
public class GeneralUtil {
	/**
	 * 写文件
	 * @param myString
	 * @param target_file
	 */
	public static void writeFile(String myString, String target_file) {
		try {
			java.io.PrintWriter pw = new java.io.PrintWriter(
					new java.io.FileOutputStream(target_file));
			pw.println(myString);
			pw.close();
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}
}