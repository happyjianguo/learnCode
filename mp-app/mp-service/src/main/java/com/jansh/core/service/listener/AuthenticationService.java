package com.jansh.core.service.listener;

import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.IMUserLog;

public interface AuthenticationService {

	/**
	 * 插入日志
	 *
	 *
	 */
	public void insertLog(IMUserLog imUserLog);

	/**
	 * 更新校验密码错误次数
	 * 
	 * @param map
	 * @return
	 */
	public int updatePwdErr(IMUser imuser);

	/**
	 * 更新校验密码错误次数
	 * 
	 * @param map
	 * @return
	 */
	public void updateErrNumSafe(String userId, String operadate);

	/**
	 * 获取用户信息
	 * 
	 * @param map
	 * @return
	 */
	public void queryUser(String username);
}
