package cn.com.jansh.service;

import org.springframework.stereotype.Service;

import cn.com.jansh.dao.entity.WxbPlatformUser;
/**
 * 微信报文处理并返回
 * @author duanmuyn
 */
@Service
public interface WeixinService {
	
	/**
	 * 处理报文请求及处理后返回报文
	 * @param xmlMsg
	 * @return
	 */
	public String getHandlePost(String xmlMsg,String appid);
	
	/**
	 * 验证服务器地址的有效性,并返回微信公众号信息
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param requrl
	 * @return
	 */
	public WxbPlatformUser queryPlatformUser(String signature, String timestamp, String nonce, String requrl);
}
