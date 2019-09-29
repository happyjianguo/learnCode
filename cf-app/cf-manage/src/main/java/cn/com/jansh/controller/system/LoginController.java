package cn.com.jansh.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.core.service.sys.PropertiesService;

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
	@RequestMapping(value = "/dologin")
	public String dologin(HttpServletRequest request, HttpServletResponse response ,Model model) {
		logger.info("init dologin.");
		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		model.addAttribute("optenable", properties.getOptenable());
		if("admin".equals(username)&&"admin".equals(password)){
			return "main/wellcome";
		}
		return "login/login";
	}
	protected String obtainPassword(HttpServletRequest request) {
		String password = request.getParameter("password");
		return password == null ? "" : password;
	}

	protected String obtainUsername(HttpServletRequest request) {
		String username = request.getParameter("username");
		return username == null ? "" : username;
	}
}
