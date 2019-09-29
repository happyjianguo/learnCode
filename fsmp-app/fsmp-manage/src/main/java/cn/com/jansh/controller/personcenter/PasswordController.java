package cn.com.jansh.controller.personcenter;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;

import cn.com.jansh.model.component.PassWordModel;
import cn.com.jansh.service.system.IMUserService;
/**
 * 密码修改
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping("/password")
public class PasswordController {

	private static final Logger logger = LogManager.getLogger(PasswordController.class);
	
	@Autowired
	private IMUserService userService;

	/**
	 * 跳转修改密码页面
	 * 
	 * @param model
	 * @return 修改密码页面
	 */
	@RequestMapping("/init")
	public String init(Model model) {
		logger.info("跳转修改密码页面");
		return "/sysmanage/personalcenter/password";
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
	@SecurityRequest(repeatRequest=true)
	@OperationResult(value=Operation.UPDATE,msgCode="340200")
	@OperationLog(value=Operation.UPDATE,key="340100")
	public String modifyPassword(@Valid PassWordModel passWordModel) throws AppException {
		logger.info("修改密码:");
		userService.editpassword(passWordModel);
		return "/sysmanage/personalcenter/password";
	}
}
