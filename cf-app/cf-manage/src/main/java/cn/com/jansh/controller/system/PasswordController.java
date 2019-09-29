package cn.com.jansh.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.security.authentication.dao.LoginAuthenticationProvider;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.service.system.PubsSysBaseService;

@Controller
@RequestMapping("/password")
public class PasswordController {

	private static final Logger logger = LogManager.getLogger(PasswordController.class);
	
	@Autowired
	private IMUserService imUserService;
	@Autowired
	private LoginAuthenticationProvider enCoder;
	@Autowired
	private PubsSysBaseService baseService;

	/**
	 * 跳转修改密码页面
	 * 
	 * @param model
	 * @return 修改密码页面
	 */
	@RequestMapping("/init")
	public String init(Model model) {
		logger.info("跳转修改密码页面");
		return "/system/password";
	}

	/**
	 * 密码修改
	 * @param opwd
	 * @param pwd1
	 * @param pwd2
	 * @return
	 * @throws AppException
	 */
	@RequestMapping("/edit")
	@ExceptionHandle("/password/init")
	@OperationResult
	public String modifyPassword(String opwd, String pwd1, String pwd2) throws AppException {
		logger.info("修改密码:{},{},{}", opwd, pwd1, pwd2);
		// 校验数据是否为空

		// 获取当前用户的id
		String userid = AppUtil.getUserDetail().getUserid();

		if (StringUtils.isBlank(opwd) || StringUtils.isBlank(pwd1) || StringUtils.isBlank(pwd2)) {
			logger.error("密码不能为空");
			throw new AppException(AppErrorCode.E200006);
		}

		// 校验新密码密码是否一致
		if (!pwd1.equals(pwd2)) {
			logger.error("两次密码输入不一致!");
			throw new AppException(AppErrorCode.E200003);
		}

		String pwdString = enCoder.proccessPasswd(userid, opwd);
		IMUser user = imUserService.selectByUserid(userid);
//		if (StringUtils.isBlank(user.getPasswd()) || StringUtils.isBlank(pwdString)) {
//			logger.error("原密码输入错误");
//			throw new AppException(AppErrorCode.E200004);
//		}
		// 旧密码校验失败更新校验次数
		if (!enCoder.getPasswordEncoder().matches(pwdString, user.getPasswd())) {
			logger.error("原密码校验失败");
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("updatetime", DateUtil.getDateTime());
			int pwserrnum = Integer.parseInt(user.getPwderrnum()) + 1;
			map.put("pwserrnum", String.valueOf(pwserrnum));
			PubsSysBase pubsSysBase = baseService.querySysBaseAceBaseId("PWDERRCOUNT");
			String errNumValue = pubsSysBase.getSysBaseValue();
			int errnum = Integer.parseInt(errNumValue);
			if (pwserrnum >= errnum) {
				map.put("status", "0");
				logger.error("密码错误次数已超限，请联系管理员进行密码重置！");
			}
			imUserService.updatePwdErrNum(map);

			if (pwserrnum >= errnum) {
				logger.error("密码错误次数已超限，请联系管理员进行密码重置！");
				throw new AppException(AppErrorCode.E200005);
			} else {
				// 原密码输入错误
				throw new AppException(AppErrorCode.E200004);
			}
		}
		String pwd1String = enCoder.proccessPasswd(userid, pwd1);
		if (pwd1String.equals(pwdString)) {
			logger.error("新密码与旧密码不能相同，请重新输入！");	
			throw new AppException(AppErrorCode.E200007);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(userid, pwd1)));		
		map.put("updatetime", DateUtil.getDateTime());
		map.put("pwserrnum", "0");
		// 更新密码
		imUserService.updatePwd(map);
		return "/system/password";
	}
}
