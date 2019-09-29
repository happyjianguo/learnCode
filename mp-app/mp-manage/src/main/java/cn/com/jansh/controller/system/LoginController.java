package cn.com.jansh.controller.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.core.service.sys.PropertiesService;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private PropertiesService properties;
	
	@RequestMapping(value = "/login")
	public String init(Model model) {
		logger.info("init login.");
		model.addAttribute("optenable", properties.getOptenable());
		return "login/login";
	}

}
