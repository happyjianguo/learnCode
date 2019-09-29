package cn.yufu.system.modules.cortexs.web;

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

import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.cortexs.entity.Crdrouting;
import cn.yufu.system.modules.cortexs.service.CrdroutingService;

/**
 * 卡BIN信息Controller
 * @author LLG
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/cortexs/crdrouting")
public class CrdroutingController extends BaseController {

	@Autowired
	private CrdroutingService crdroutingService;
	
	@ModelAttribute
	public Crdrouting get(@RequestParam(required=false) String id) {
		Crdrouting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = crdroutingService.get(id);
		}
		if (entity == null){
			entity = new Crdrouting();
		}
		return entity;
	}
	
	@RequiresPermissions("cortexs:crdrouting:view")
	@RequestMapping(value = {"list", ""})
	public String list(Crdrouting crdrouting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Crdrouting> page = crdroutingService.findPage(new Page<Crdrouting>(request, response), crdrouting); 
		model.addAttribute("page", page);
		return "modules/cortexs/crdroutingList";
	}

	@RequiresPermissions("cortexs:crdrouting:view")
	@RequestMapping(value = "form")
	public String form(Crdrouting crdrouting, Model model) {
		model.addAttribute("crdrouting", crdrouting);
		return "modules/cortexs/crdroutingForm";
	}

	@RequiresPermissions("cortexs:crdrouting:edit")
	@RequestMapping(value = "save")
	public String save(Crdrouting crdrouting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, crdrouting)){
			return form(crdrouting, model);
		}
		crdroutingService.save(crdrouting);
		addMessage(redirectAttributes, "保存卡BIN信息成功");
		return "redirect:"+Global.getAdminPath()+"/cortexs/crdrouting/?repage";
	}
	
	@RequiresPermissions("cortexs:crdrouting:edit")
	@RequestMapping(value = "delete")
	public String delete(Crdrouting crdrouting, RedirectAttributes redirectAttributes) {
		crdroutingService.delete(crdrouting);
		addMessage(redirectAttributes, "删除卡BIN信息成功");
		return "redirect:"+Global.getAdminPath()+"/cortexs/crdrouting/?repage";
	}

}