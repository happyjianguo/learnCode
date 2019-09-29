package cn.yufu.system.modules.cortexs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.Crdfeerule;
import cn.yufu.system.modules.cortexs.service.CrdfeeruleService;

/**
 * 扣款费率管理Controller
 * @author ZQK
 * @version 2017-07-16
 */
@Controller
@RequestMapping(value = "/cortexs/crdFeeRule")
public class CrdfeeruleController extends BaseController {

	@Autowired
	private CrdfeeruleService crdfeeruleService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Crdfeerule crdfeerule, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<Crdfeerule> page = crdfeeruleService.findPage(new Page<Crdfeerule>(request, response), crdfeerule); 
		model.addAttribute("page", page);
		model.addAttribute("crdfeerule", crdfeerule);
		return "modules/cortexs/crdfeeruleList";
	}
	
	@RequiresPermissions("cortexs:crdFeeRule:edit")
	@RequestMapping(value = "show")
	public String show(Crdfeerule qyeryModel, Model model) {
		model.addAttribute("crdfeerule", crdfeeruleService.get(qyeryModel));
		return "modules/cortexs/crdfeeruleShow";
	}
	
	@RequiresPermissions("cortexs:crdFeeRule:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		model.addAttribute("crdfeerule", new Crdfeerule());
		return "modules/cortexs/crdfeeruleForm";
	}
	
	@RequiresPermissions("cortexs:crdFeeRule:edit")
	@RequestMapping(value = "save")
	public String save(Crdfeerule crdfeerule, Model model,RedirectAttributes redirectAttributes) {
		crdfeerule.setOper(UserUtils.getPrincipal().getName());
		try {
			crdfeeruleService.save(crdfeerule);
			addMessage(redirectAttributes, "保存扣款费率成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存扣款费率失败");
		}
		return "redirect:/cortexs/crdFeeRule/?repage";
	}
	
	@RequestMapping(value = "checkIID")
	@ResponseBody
	public Map<String, Object> checkIID(Crdfeerule crdfeerule, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Crdfeerule> list = crdfeeruleService.getDateByIID(crdfeerule);
		if (list == null || list.size() == 0) {
			map.put("result", "0");
			map.put("desc", "卡BIN不存在，请重新输入!");
		}else{
			map.put("result", "1");
			map.put("desc", list.get(0));
		}
		return map;
	}
}