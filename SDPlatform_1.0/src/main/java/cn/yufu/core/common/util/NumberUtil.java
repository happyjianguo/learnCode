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
public class NumberUtil {
	/**
	 * �ж��Ƿ�����
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