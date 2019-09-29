package cn.yufu.system.modules.cortexs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.TTranType;
import cn.yufu.system.modules.cortexs.service.TTranTypeService;

/**
 * TLOG交易类型Controller
 * @author LLG
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "/cortexs/tTranType")
public class TTranTypeController extends BaseController {

	@Autowired
	private TTranTypeService tTranTypeService;
	
	@ModelAttribute
	public TTranType get(@RequestParam(required=false) String id) {
		TTranType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tTranTypeService.get(id);
		}
		if (entity == null){
			entity = new TTranType();
		}
		entity.setId(id);
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TTranType tTranType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TTranType> page = tTranTypeService.findPage(new Page<TTranType>(request, response), tTranType); 
		model.addAttribute("page", page);
		model.addAttribute("TTranType", tTranType);
		return "modules/cortexs/tTranTypeList";
	}

	@RequestMapping(value = "form")
	public String form(TTranType tTranType, Model model) {
		model.addAttribute("tTranType", tTranType);
		return "modules/cortexs/tTranTypeForm";
	}

	@RequestMapping(value = "save")
	public String save(TTranType tTranType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tTranType)){
			return form(tTranType, model);
		}
		tTranTypeService.save(tTranType);
		addMessage(redirectAttributes, "保存TLOG交易类型成功");
		return "redirect:/cortexs/tTranType/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TTranType tTranType, RedirectAttributes redirectAttributes) {
		tTranTypeService.delete(tTranType);
		addMessage(redirectAttributes, "删除TLOG交易类型成功");
		return "redirect:/cortexs/tTranType/?repage";
	}

}