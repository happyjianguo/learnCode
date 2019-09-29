package cn.com.jansh.service.weixin;

import java.util.List;

import cn.com.jansh.entity.wechat.AuthAccount;

/**
 * 微信遮挡service
 * @author gll
 * @version 1.0
 */
public interface WXDMService {

	/**
	 * 获取当前用户or机构下全部授权公众号信息
	 * @return
	 */
	public List<AuthAccount> getAppidList();

}
