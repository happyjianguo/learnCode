package cn.yufu.core.common.util;

/**
 * @author mengfp
 * @version 2014-08-21
 */
public class ConstManagerFactory {

	public static ConstManager getConstManagerImpl() {
		return ConstManagerImpl.getInstance();
	}
}
