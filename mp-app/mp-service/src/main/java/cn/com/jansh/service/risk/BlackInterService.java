/**
 * BlackInterService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月25日
 */
package cn.com.jansh.service.risk;

import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;

/**
 * 黑名单接口
 * @author gll
 * @version 1.0
 */
public interface BlackInterService {

	/**
	 * 获取黑名单接口
	 * @param janshAuth
	 * @param msg
	 * @return
	 * @throws AppException 
	 */
	@SuppressWarnings("rawtypes")
	public ViewObject acquireblack(String janshAuth, String msg) throws AppException;

}
