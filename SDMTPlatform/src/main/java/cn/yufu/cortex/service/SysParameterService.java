package cn.yufu.cortex.service;

import java.util.List;

import cn.yufu.cortex.entity.SysParameter;

public interface SysParameterService {
	
	public List<SysParameter> queryList(String paramType,String paramValue) ;

}
