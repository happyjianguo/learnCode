package cn.com.jansh.core.service.sys;

import cn.com.jansh.core.entity.sys.IMUser;

/**
 * 系统管理-用户管理
 *
 */

public interface DefUserService {

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
}
