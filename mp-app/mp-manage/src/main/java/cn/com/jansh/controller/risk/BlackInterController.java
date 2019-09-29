/**
 * BlackInterController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月25日
 */
package cn.com.jansh.controller.risk;

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
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.service.risk.BlackInterService;

/**
 * 黑名单接口
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "blackinter")
public class BlackInterController {
	
	private static final Logger logger = LogManager.getLogger(BlackInterController.class);
	
	@Autowired
	private BlackInterService blackInterService;//黑名单接口service
	/**
	 * 获取黑名单接口
	 * @param request
	 * @param msg
	 * @return
	 * @throws AppException 
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/acquireblack",method = RequestMethod.POST)
	public ViewObject acquireblack(HttpServletRequest request,@RequestBody String msg) throws AppException{
		logger.info("获取黑名单接口:{}",msg);
		String janshAuth = request.getHeader("JANSHAUTH");
		if(StringUtils.isBlank(janshAuth) || janshAuth.length() != 40){
			logger.error("http header 属性JANSHAUTH error :{}",janshAuth);
			ViewObject viewObject = new ViewObject();
			viewObject.setErrorCode(AppCommonsCode.BLACKINTER_ERROR_REQUEST.value());
			viewObject.setErrorMsg("非法请求");
			return viewObject;
		}
		return blackInterService.acquireblack(janshAuth,msg);
	}
}
