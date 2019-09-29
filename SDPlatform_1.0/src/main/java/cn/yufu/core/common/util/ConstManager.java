package cn.yufu.core.common.util;

import java.util.Map;

/**
 * @author mengfp
 * @version 2014-08-21
 */
public interface ConstManager {
	public Object getConst(Object key);

	public String getString(Object key);

	public Integer getInteger(Object key);

	public int getInt(Object key);

	public Map getConstMap();
}