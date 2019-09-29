package cn.com.jansh.component;
/**
 * OfferPublicController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.exception.AppException;

import cn.com.jansh.service.component.DisParkService;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信信息获取接口
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/dispark")
public class DisParkController {
	
	private static final Logger logger = LogManager.getLogger(DisParkController.class);
	
	@Autowired
	private DisParkService clgameparamService;
	
	/**
	 * 获取微信信息
	 * @param request
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/offertoken")
	@ResponseBody
	public JsonVO offerToken(HttpServletRequest request) throws AppException{
		logger.info("开始获取信息");
		return clgameparamService.offerComponentToken(request);
	}
}
