/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yufupos.system.common.config.Global;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.sys.entity.SysClient;
import com.yufupos.system.modules.sys.service.SysClientService;

/**
 * 接入工程客户端Controller
 * @author mengfp
 * @version 2015-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysClient")
public class SysClientController extends BaseController {

	@Autowired
	private SysClientService sysClientService;
	
	@ModelAttribute
	public SysClient get(@RequestParam(required=false) String id) {
		SysClient entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysClientService.get(id);
		}
		if (entity == null){
			entity = new SysClient();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysClient:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysClient sysClient, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysClient> page = sysClientService.findPage(new Page<SysClient>(request, response), sysClient); 
		model.addAttribute("page", page);
		return "modules/sys/sysClientList";
	}

	@RequiresPermissions("sys:sysClient:view")
	@RequestMapping(value = "form")
	public String form(SysClient sysClient, Model model) {
		model.addAttribute("sysClient", sysClient);
		return "modules/sys/sysClientForm";
	}

	@RequiresPermissions("sys:sysClient:edit")
	@RequestMapping(value = "save")
	public String save(SysClient sysClient, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysClient)){
			return form(sysClient, model);
		}
		sysClientService.save(sysClient);
		addMessage(redirectAttributes, "保存客户端成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysClient/?repage";
	}
	
	@RequiresPermissions("sys:sysClient:edit")
	@RequestMapping(value = "delete")
	public String delete(SysClient sysClient, RedirectAttributes redirectAttributes) {
		sysClientService.delete(sysClient);
		addMessage(redirectAttributes, "删除客户端成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysClient/?repage";
	}

}