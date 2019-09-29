/**
 * TellimitService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.service.system;

import javax.servlet.http.HttpSession;

import cn.com.jansh.model.system.CheckWayModel;
import cn.com.jansh.vo.AjaxObj;

/**
 * 验证码发送频率限制服务
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudSecurityLimitService {
	
	/**
	 *	限制发送验证码频率
	 * @return 
	 *
	 */
	public AjaxObj limitSendFrequency(CheckWayModel checkWayModel, HttpSession session);
}
