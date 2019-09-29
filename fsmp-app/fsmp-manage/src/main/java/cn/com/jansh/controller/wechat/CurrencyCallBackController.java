/**
 * CurrencyCallBackController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月27日
 */
package cn.com.jansh.controller.wechat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 银联支付成功后（返回商户）
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/curcb")
public class CurrencyCallBackController {

	private static final Logger logger = LogManager.getLogger(CurrencyCallBackController.class);
	
	/**
	 * 跳转到初始化页面
	 * @return
	 */
	@RequestMapping("/init")
	public String payinit(){
		logger.info("银联支付成功后返回充值页面");
		return "fsmp/currency/callback";
	}
}
