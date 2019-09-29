package cn.com.jansh.controller.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String showMain(Model mode) {
		logger.info("main page");
		return "main/wellcome";
	}
}
