/**
 * PaymentOptionController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.component;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.service.component.CyInterfaceService;

/**
 * 平台币购买
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping("/currency")
public class CurrencyController {

	private static final Logger logger = LogManager.getLogger(CurrencyController.class);
	
	@Autowired
	public CyInterfaceService cyService;
	/**
	 * 银联在线充值回调-后台通知结果
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/payresp")
	public void UnionBack(@RequestBody String reqBody,HttpServletResponse resp) throws Exception {
		logger.info("银联交易回调:{}", reqBody);
		resp.getWriter().print("ok");
		cyService.payBack(reqBody);
	}
	
}
