package cn.com.jansh.component;
/**
 * WxManageController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:wfy 2016年10月19日
 */
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.service.wechat.WxAuthService;
/**
 * 第三方回调
 * @author wfy
 * @version 1.0
 */
@Controller
@RequestMapping("/wxauth")
public class WxAuthController {

	@Autowired
	public WxAuthService wxAuthService;
	
	private final static Logger logger = LogManager.getLogger(WxAuthController.class);
	
	
	/**
	 * 微信第三方票据回调uri
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getticket")
	@ResponseBody
	public String getTicket(@RequestBody String obj,HttpServletRequest request) throws Exception{
		logger.info("==============微信第三方票据回调");
		/**
		 * 从授权URL获取参数、数据包参数encrypt
		 */
		String msgSignature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String encrypt = wxAuthService.getXMLdata(obj,"Encrypt");
		
		/*第一步 通过以上参数获取票据*/
		wxAuthService.updateTicket(msgSignature, timestamp, nonce, encrypt);
		return "success";
	}
	
}
