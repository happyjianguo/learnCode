/**
 * ClgameparamService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */
package cn.com.jansh.service.component;

import javax.servlet.http.HttpServletRequest;

import com.jansh.core.exception.AppException;

import cn.com.jansh.vo.JsonVO;

/**
 * 游戏配置表接口
 * @author Mr.wong
 * @version 1.0
 */
public interface DisParkService {

	/** 
	 * 提供第三方平台接口调用权限 
	 * @return 
	 * @throws AppException 
	 **/
	public JsonVO offerComponentToken(HttpServletRequest request) throws AppException;
}
