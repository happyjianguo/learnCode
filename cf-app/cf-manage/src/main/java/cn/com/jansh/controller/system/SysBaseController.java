package cn.com.jansh.controller.system;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.service.system.PubsSysBaseService;

/**
 * 系统参数
 * 
 */
@Controller
@RequestMapping(value = "/base")
public class SysBaseController {
	private static final Logger logger = LogManager.getLogger(SysBaseController.class);
	@Autowired
	private PubsSysBaseService baseService;

	/**
	 * 
	 * 跳转到数据字典界面
	 *
	 */
	@RequestMapping("/init")
	public String init() {
		return "/sysmanage/paramanage/baseManage";
	}

	/**
	 * 修改初始化页面
	 * 
	 * @param base
	 * @return
	 */
	@RequestMapping("/updateinit")
	public String updateInit(PubsSysBase base) {
		return "/sysmanage/paramanage/basemanageedit";
	}

	/**
	 * 更新参数
	 * @throws AppException 
	 *
	 */
	@RequestMapping("/update")
	@OperationResult(value=Operation.UPDATE)
	@ExceptionHandle("/base/updateinit")
	public String update(PubsSysBase base) throws AppException {
		try {
			baseService.updateSysBase(base);
		} catch (Exception e) {
			logger.error("更新失败:{}", e);
			throw new AppException(AppErrorCode.E120003);
		}
		return init();
	}

	/**
	 * 获取全部系统参数
	 * 
	 * @throws AppException
	 *
	 */
	@RequestMapping("/pubssysbaselist")
	@ResponseBody
	public ViewObject querySysBases(PubsSysBase base) throws AppException {
		List<PubsSysBase> bases = null;
		try {
			bases = baseService.queryAllSysBase(base.getSysBaseId());
		} catch (Exception e) {
			logger.error("获取系统参数异常{}", e);
			throw new AppException(AppErrorCode.E120004);
		}
		return new ViewObject(bases);
	}
}
