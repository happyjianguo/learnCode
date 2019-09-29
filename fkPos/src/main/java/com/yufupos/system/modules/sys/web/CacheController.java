package com.yufupos.system.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yufupos.system.common.utils.CacheUtils;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.sys.entity.SysClient;
import com.yufupos.system.modules.sys.utils.DictUtils;
import com.yufupos.system.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/sys/cleanCache")
public class CacheController extends BaseController {

	@RequestMapping(value = "list")
	public String list(SysClient sysClient, Model model, RedirectAttributes redirectAttributes) {
		return "modules/sys/cleanCache";
	}

	@RequestMapping(value = "user")
	public String save(SysClient sysClient, Model model, RedirectAttributes redirectAttributes) {
		CacheUtils.removeAll(UserUtils.USER_CACHE);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		addMessage(redirectAttributes, "清除缓存成功！");
		return "redirect:/sys/cleanCache/list";
	}
}
