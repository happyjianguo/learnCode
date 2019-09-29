package cn.yufu.system.modules.cortexs.web;

import java.util.Date;

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
import cn.yufu.system.modules.cortexs.entity.CrdStatus;
import cn.yufu.system.modules.cortexs.entity.Crddet;
import cn.yufu.system.modules.cortexs.entity.Crdpin;
import cn.yufu.system.modules.cortexs.entity.Crdstatchglog;
import cn.yufu.system.modules.cortexs.service.CrdStatusService;
import cn.yufu.system.modules.cortexs.service.CrddetService;
import cn.yufu.system.modules.cortexs.service.CrdpinService;
import cn.yufu.system.modules.cortexs.service.CrdstatchglogService;
import cn.yufu.system.modules.sys.entity.User;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * TLOG交易类型Controller
 * @author LLG
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "/cortexs/crdStatChgLog")
public class CrdStatChgLogController extends BaseController {

	@Autowired
	private CrdStatusService tTranTypeService;
	
	@Autowired
	private CrddetService crddetService;
	
	@Autowired
	private CrdpinService crdpinService;
	
	@Autowired
	private CrdstatchglogService crdstatchglogService;
	
	@ModelAttribute
	public CrdStatus get(@RequestParam(required=false) String id) {
		CrdStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tTranTypeService.get(id);
		}
		if (entity == null){
			entity = new CrdStatus();
		}
		entity.setId(id);
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Crdstatchglog tTranType, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (tTranType != null && tTranType.getCrdNo() != null) {
			
			Page<Crdstatchglog> page = crdstatchglogService.findPage(new Page<Crdstatchglog>(request, response), tTranType); 
			model.addAttribute("page", page);
			model.addAttribute("Crdstatchglog", tTranType);
		}
		return "modules/cortexs/crdstatchglogList";
	}

	@RequestMapping(value = "form")
	public String form(CrdStatus tTranType, Model model) {
		model.addAttribute("crdStatus", tTranType);
		return "modules/cortexs/crdStatusForm";
	}

	@RequestMapping(value = "save")
	public String save(CrdStatus tTranType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tTranType)){
			return form(tTranType, model);
		}
		//tTranTypeService.save(tTranType);
		Crddet crddet = new Crddet();
		crddet.setStatCode(tTranType.getStatCode());
		crddet.setPan(tTranType.getPan());
		crddetService.save(crddet);
		
		Crdpin crdpin = new Crdpin();
		crdpin.setPan(tTranType.getPan());
		crdpin.setCrddetId(tTranType.getPan());
		crdpinService.save(crdpin);
		
		Crdstatchglog crdstatchglog = new Crdstatchglog();
		User user = UserUtils.getUser();
		//PrincipalBean pb = UserUtils.getPrincipal();
		crdstatchglog.setContent(tTranType.getComments());
		crdstatchglog.setCrdNo(tTranType.getPan());
		crdstatchglog.setCurStatus(tTranType.getCurrentStatus());
		crdstatchglog.setOriStatus(tTranType.getStatCode());
		crdstatchglog.setUpdateTime(new Date());
		crdstatchglog.setTranType("变更卡状态");
		crdstatchglog.setLoginName(user.getLoginName());
		crdstatchglog.setIp(user.getLoginIp());
		
		crdstatchglogService.save(crdstatchglog);
		
		addMessage(redirectAttributes, "保存TLOG交易类型成功");
		return "redirect:/cortexs/crdStatus/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(CrdStatus tTranType, RedirectAttributes redirectAttributes) {
		tTranTypeService.delete(tTranType);
		addMessage(redirectAttributes, "删除TLOG交易类型成功");
		return "redirect:/cortexs/tTranType/?repage";
	}

}