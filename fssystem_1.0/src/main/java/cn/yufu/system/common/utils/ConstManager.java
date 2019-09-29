package cn.yufu.system.common.utils;

import java.util.Map;

/**
 * @author mengfp
 * @date 2014年9月12日
 */
public interface ConstManager {
	public Object getConst(Object key);

	public String getString(Object key);

	public Integer getInteger(Object key);

	public int getInt(Object key);

	public Map getConstMap();
}