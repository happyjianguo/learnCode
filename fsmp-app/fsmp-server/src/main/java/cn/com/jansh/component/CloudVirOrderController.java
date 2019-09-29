/**
 * CloudVirOrderController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.component;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.service.component.CvOrderService;
import cn.com.jansh.vo.JsonVO;

/**
 * 话费流量充值订单接口
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/virorder")
public class CloudVirOrderController {

	private static final Logger logger = LogManager.getLogger(CloudVirOrderController.class);
	
	@Autowired
	private CvOrderService orderService;
	
	/**
	 * 接收流量充值订单
	 * @param request
	 * @param cloudVirOrderModelJson
	 * @return
	 */
	@RequestMapping("/receive")
	@ResponseBody
	public JsonVO receiveVirOrder(HttpServletRequest request ,@RequestBody String cloudVirOrderModelJson){
		logger.info("接收到流量充值订单: "+cloudVirOrderModelJson);
		return orderService.saveOrder(cloudVirOrderModelJson, request.getHeader("sign"));
	}
}
