package com.jansh.core.service.sys;

/**
 * 系统参数服务接口
 *
 * @author Mr.wong
 *
 */
public interface DefSysBaseService {

	/**
	 * 通过系统参数名获取参数值
	 */
	public String querySysBaseValue(String baseId);

	/**
	 * 获取重置密码
	 */
	public String querySysBaseInitPwd();
}
