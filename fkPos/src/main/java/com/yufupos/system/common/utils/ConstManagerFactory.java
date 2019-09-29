package com.yufupos.system.common.utils;

/**
 * @author mengfp
 * @date 2014年9月12日
 */

public class ConstManagerFactory {

	public static ConstManager getConstManagerImpl() {
		return ConstManagerImpl.getInstance();
	}
}
