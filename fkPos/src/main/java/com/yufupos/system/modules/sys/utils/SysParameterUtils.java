/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yufupos.system.common.utils.CacheUtils;
import com.yufupos.system.common.utils.SpringContextHolder;
import com.yufupos.system.modules.cortex.dao.SysParameterDao;
import com.yufupos.system.modules.cortex.entity.SysParameter;

/**
 * 商户类型工具类
 * @author zqk
 * @version 2017-08-02
 */
public class SysParameterUtils {
	
	private static SysParameterDao sysParameterDao = SpringContextHolder.getBean(SysParameterDao.class);

	public static final String CACHE_SYSPARAM_MAP = "sysParameterMap";
	
	public static String getSysParameterLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (SysParameter sysParameter : getSysParameterList(type)){
				if (type.equals(sysParameter.getParamType()) && value.equals(sysParameter.getParamValue())){
					return sysParameter.getParamName();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getSysParameterLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getSysParameterLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getSysParameterValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (SysParameter sysParameter : getSysParameterList(type)){
				if (type.equals(sysParameter.getParamType()) && label.equals(sysParameter.getParamName())){
					return sysParameter.getParamValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<SysParameter> getSysParameterList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<SysParameter>> sysParameterMap = (Map<String, List<SysParameter>>)CacheUtils.get(CACHE_SYSPARAM_MAP);
		if (sysParameterMap==null){
			sysParameterMap = Maps.newHashMap();
			for (SysParameter sysParameter : sysParameterDao.findAllList(new SysParameter())){
				List<SysParameter> sysParameterList = sysParameterMap.get(sysParameter.getParamType());
				if (sysParameterList != null){
					sysParameterList.add(sysParameter);
				}else{
					sysParameterMap.put(sysParameter.getParamType(), Lists.newArrayList(sysParameter));
				}
			}
			CacheUtils.put(CACHE_SYSPARAM_MAP, sysParameterMap);
		}
		List<SysParameter> sysParameterList = sysParameterMap.get(type);
		if (sysParameterList == null){
			sysParameterList = Lists.newArrayList();
		}
		return sysParameterList;
	}
	
}
