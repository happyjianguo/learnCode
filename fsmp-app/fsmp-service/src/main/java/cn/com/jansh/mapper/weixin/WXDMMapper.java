package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.wechat.AuthAccount;
/**
 * 微信遮挡Mapper
 * @author gll
 * @version 1.0
 */
public interface WXDMMapper {

	/**
	 * 开始获取全部授权公众号信息！
	 * @param map
	 * @return
	 */
	public List<AuthAccount> getAppidList(Map<String, Object> map);
}
