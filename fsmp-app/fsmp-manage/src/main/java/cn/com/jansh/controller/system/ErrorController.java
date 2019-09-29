package cn.com.jansh.controller.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误处理
 * @author nie
 *
 */
@Controller
@RequestMapping(value="/error")
public class ErrorController {
	
	private static final Logger logger = LogManager.getLogger(ErrorController.class);

	@RequestMapping(value="/accessdenied")
	public String accessDenied() {
		logger.info("==== access denied");
		return "/error/accessdenied";
	}
	
	@RequestMapping(value="/apperror")
	public String apperror() {
		logger.info("==== apperror");
		return "/error/apperror";
	}

}
