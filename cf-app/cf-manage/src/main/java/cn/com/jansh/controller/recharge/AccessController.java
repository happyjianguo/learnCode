package cn.com.jansh.controller.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.core.util.StringUtil;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.model.AccessclientModel;

/**
 * 变更密钥
 */
@Controller
@RequestMapping(value="/getrandom")
public class AccessController {

	private static final Logger logger = LogManager.getLogger(AccessController.class);
	
	@RequestMapping(value = "/getrandom")
	@ResponseBody
	public ViewObject init(AccessclientModel accessclientModel,Model model) {
		logger.info("获取随机数");
		return new ViewObject(StringUtil.randomCharNum(8));
	}
}
