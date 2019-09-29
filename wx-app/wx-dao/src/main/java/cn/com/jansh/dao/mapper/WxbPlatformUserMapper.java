package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxbPlatformUser;

public interface WxbPlatformUserMapper {
	
	/**
	 * 根据REQURL查询公众号信息
	 * @param requrl
	 * @return
	 */
	public WxbPlatformUser selectPlatformByRequrl(String requrl);
}
