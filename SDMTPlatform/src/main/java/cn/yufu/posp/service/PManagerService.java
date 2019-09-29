/**
 *包名:cn.yufu.posp.service
 *描述:package cn.yufu.posp.service;
 */
package cn.yufu.posp.service;

import java.util.List;

import cn.yufu.posp.entity.PManager;

/**
 * PManagerService.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月6日
 * 描述:商户经理Service
 */
public interface PManagerService {

	//查询店长或者普通员工
	public List<PManager> findListByRoleId(String role);

	//获取店长或商户顾问LIST
	public List<PManager> findListByRoleIdAndArea(String roleId, String managerArea);
}
