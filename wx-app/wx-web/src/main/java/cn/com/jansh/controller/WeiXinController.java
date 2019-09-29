/**
 * WeiXinController.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:聂凤鑫 2015-6-9
 */
package cn.com.jansh.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.weixin.mp.aes.AesException;

import cn.com.jansh.dao.entity.WxbPlatformUser;
import cn.com.jansh.service.WeixinService;
import cn.com.jansh.utils.AESUtils;

/**
 * 微信主动请求报文入口
 * 
 */
@Controller
@RequestMapping(value = "/wxmsg")
public class WeiXinController {

	private static Logger logger = LogManager.getLogger(WeiXinController.class);

	@Autowired
	private WeixinService weixinPostServiceImpl;

	/**
	 * 微信首次链接验证
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{requrl}", method = RequestMethod.GET)
	public String msgGET(String signature, String timestamp, String nonce, String echostr, @PathVariable("requrl")String requrl) {
		logger.info("##########check signature ...");
		String result = "";
		if (signature == null || timestamp == null || nonce == null || echostr == null) {
			result = "check signature error! parameter is null";
		} else {
			WxbPlatformUser platformUser = weixinPostServiceImpl.queryPlatformUser(signature, timestamp, nonce, requrl);
			if (platformUser != null) {
				result = echostr;// 请求验证成功，返回随机码
			} else {
				result = "check signature error!";
			}
		}
		return result;
	}

	/**
	 * 微信主动请求控制器POST
	 * 
	 * @param xmlMsg
	 * @return
	 * @throws XmlMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/{requrl}", method = RequestMethod.POST)
	public String doPost(@RequestBody String xmlMsg, String msg_signature,String encrypt_type, String timestamp, 
						String nonce, @PathVariable("requrl")String requrl) 
									throws XmlMappingException, IOException {
		logger.info("微信请求报文：{}",xmlMsg,"msg_signature:{}",msg_signature,encrypt_type,"timestamp:{}",timestamp,"nonce{}",nonce);
		String jiemi = null;
		String rJiemi = null;
		String rJiami = null;
		if (StringUtils.isNotBlank(xmlMsg)) {
			// 验证服务器地址的有效性
//			WxbPlatformUser platformUser = weixinPostServiceImpl.queryPlatformUser(signature, timestamp, nonce, requrl);
//			if (platformUser != null) {
//				logger.info("weixin name:{}, requrl:{}",platformUser.getWxname(),platformUser.getRequrl());
				//WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
//				return weixinPostServiceImpl.getHandlePost(xmlMsg,platformUser.getAppid());
				
//			} else {
//				logger.info("signature is error!");
//				return "signature is error";
//			}
			//对消息进行解密
			try {
				jiemi = AESUtils.wxJieMi(msg_signature,timestamp,nonce,xmlMsg);
			} catch (AesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rJiemi= weixinPostServiceImpl.getHandlePost(jiemi,requrl);
			try {
				rJiami = AESUtils.wxJiaMi(timestamp, nonce, rJiemi);
			} catch (AesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return "msg is blank";
		}
		return rJiami;
	}
}
