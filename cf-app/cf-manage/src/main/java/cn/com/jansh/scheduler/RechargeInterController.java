package cn.com.jansh.scheduler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.service.scheduler.RechargeInterService;

/**
 * 充值接口
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value = "/rechargeinter")
public class RechargeInterController {

	private static final Logger logger = LogManager.getLogger(RechargeInterController.class);
	
	@Autowired
	private RechargeInterService rechargeInterService;
	
	/***
	 * 直冲接口
	 * @param request
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ViewObject billorder(HttpServletRequest request, @RequestBody String msg) {
		logger.info("直冲订购{}",msg);
		String janshAuth = request.getHeader("JANSHAUTH");
		if (StringUtils.isBlank(janshAuth) || janshAuth.length() != 40) {
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject ViewObject = new ViewObject();
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value());
			ViewObject.setErrorMsg("非法请求");
			return ViewObject;
		}
		return rechargeInterService.billorder(janshAuth, msg);
	}
	
	/**
	 * 订单查询接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryorder", method = RequestMethod.POST)
	public ViewObject queryOrder(HttpServletRequest request, @RequestBody String msg){
		String janshAuth = request.getHeader("JANSHAUTH");
		if (StringUtils.isBlank(janshAuth) || janshAuth.length() != 40) {
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject ViewObject = new ViewObject();
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value());
			ViewObject.setErrorMsg("非法请求");
			return ViewObject;
		}
		return rechargeInterService.queryorder(janshAuth, msg);
	}
}
