package cn.yufu.system.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.utils.CacheUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.sys.entity.SysClient;
import cn.yufu.system.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/sys/cleanCache")
public class CacheController extends BaseController {

	@RequestMapping(value = "list")
	public String list(SysClient sysClient, Model model, RedirectAttributes redirectAttributes) {
		return "modules/sys/cleanCache";
	}

	@RequestMapping(value = "user")
	public String save(SysClient sysClient, Model model, RedirectAttributes redirectAttributes) {
		CacheUtils.removeAll(UserUtils.USER_CACHE);
		addMessage(redirectAttributes, "清除用户缓存成功！");
		return "redirect:" + adminPath + "/sys/cleanCache/list";
	}
}
