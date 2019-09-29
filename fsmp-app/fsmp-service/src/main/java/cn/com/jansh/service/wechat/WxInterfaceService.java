/**
 * WxInterfaceService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月25日
 */
package cn.com.jansh.service.wechat;

import cn.com.jansh.model.wechat.WxManageModel;
import cn.com.jansh.vo.AjaxObj;

/**
 * 微信管理接口
 * @author Mr.wong
 * @version 1.0
 */
public interface WxInterfaceService {

	/**
	 * 删除授权公众号accesstoken
	 * @return
	 * 
	 */
	public AjaxObj removeAuthorization(String appid);
	/**
	 * 获取当前用户or机构下全部授权公众号信息
	 * @return
	 * 
	 */
	public WxManageModel getAuthorizationAll(WxManageModel wxManageModel);
	/**
	 * 默认指定公众号
	 * @param appid
	 * @return
	 */
	public AjaxObj defaultAuthorization(String appid);
	/**
	 * 取消默认指定公众号
	 * @param appid
	 * @return
	 */
	public AjaxObj undefaultAuthorization(String appid);
}
