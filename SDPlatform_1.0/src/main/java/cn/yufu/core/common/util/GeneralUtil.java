/*
 * �������� 2006-1-19 guozl
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package cn.yufu.core.common.util;

/**
 * @author guozl
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class GeneralUtil {
	/**
	 * д�ļ�
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