package cn.com.jansh.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.core.web.util.AppUtil;
/**
 * 主页面控制器
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/main")
public class MainController {

	private static final Logger logger = LogManager.getLogger(MainController.class);

	@RequestMapping("/show")
	public String showMain(Model mode,HttpServletRequest request) {
		logger.info("main page");
		String userId = AppUtil.getUserDetail().getUserid();
		request.setAttribute("userid", userId);
		return "main/wellcome";
	}

}
