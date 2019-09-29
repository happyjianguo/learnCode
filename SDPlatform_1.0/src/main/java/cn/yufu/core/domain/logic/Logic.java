package cn.yufu.core.domain.logic;

import java.util.HashMap;

import cn.yufu.core.common.exception.ThtfException;

/**
 * 业务实现范例
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
		// 这里使用了匿名内部类 来实现execute
		return perform(new CallBack() {
			public java.util.HashMap execute() {
				// 业务处理
				java.util.HashMap hmReturn = new java.util.HashMap();
				hmReturn.put("2", hm.get("1"));
				return hmReturn;
			}
		});

	}
}
