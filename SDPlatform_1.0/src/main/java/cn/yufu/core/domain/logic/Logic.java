package cn.yufu.core.domain.logic;

import java.util.HashMap;

import cn.yufu.core.common.exception.ThtfException;

/**
 * ҵ��ʵ�ַ���
 */
public class Logic extends Template implements LogicInterface {

	/**
	 * @roseuid 
	 */
	public Logic() {

	}

	/**
	 * @param hm
	 * @return HashMap
	 * @roseuid 
	 */
	public HashMap creat(final HashMap hm) throws ThtfException {
		// ����ʹ���������ڲ��� ��ʵ��execute
		return perform(new CallBack() {
			public java.util.HashMap execute() {
				// ҵ����
				java.util.HashMap hmReturn = new java.util.HashMap();
				hmReturn.put("2", hm.get("1"));
				return hmReturn;
			}
		});

	}
}
