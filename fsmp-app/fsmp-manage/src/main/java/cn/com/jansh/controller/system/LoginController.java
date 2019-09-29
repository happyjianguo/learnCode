package cn.com.jansh.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.core.service.sys.PropertiesService;

import cn.com.jansh.model.system.GeetestConfig;
import cn.com.jansh.model.system.GeetestLib;

/**
 * 登录
 * @author duanmuyn
 * @version 1.0
 */
@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private PropertiesService properties;
	/*@Autowired
	private EmailService emailService;*/

	/**
	 * 登陆
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String init(Model model,HttpServletRequest request) {
		logger.info("init login.");
		model.addAttribute("optenable", properties.getOptenable());
		
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
		//自定义userid
		String userid = "test";
		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(userid);
		//将服务器状态设置到session中
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//将userid设置到session中
		request.getSession().setAttribute("userid", userid);
		String resStr = gtSdk.getResponseStr();
		resStr= resStr.replaceAll("\"", "'");
		request.setAttribute("resStr", resStr);
		return "login/login";
	}

	/**
	 * 用户注册
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register")
	public String register(Model model){
		logger.info("欢迎注册.");
		return "login/authover";
	}
	
	
	/*@RequestMapping("/getpasswordkey")
	public String passwordkey(String opwd, String pwd1, String pwd2) throws AppException {
		try {
			emailService.sendmail("duanmuyingnan@163.com", "支付管理平台接入信息","支付管理平台接入信息【支付平台】  商户您好，欢迎使用坚石支付平台，以下信息请保密。您的机构码为：，秘钥为： 。为了交易数据传输的安全性，请您注意保密。开发前务必仔细阅读接口文档的各种注意事项以及报文交互的数据格式；运行代码示例请参考SDK开发包，感谢您的配合。再次感谢您的使用。谢谢！");
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "";
	}*/
}
