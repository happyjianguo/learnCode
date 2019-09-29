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
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.Crdopen;
import cn.yufu.system.modules.cortexs.service.CrdopenService;
import cn.yufu.system.modules.cortexs.service.OpencrdbatchService;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 口令码表DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "/cortexs/Crdopen")
public class CrdopenController extends BaseController {
	
	@Autowired
	private CrdopenService crdopenService;
	
	@Autowired
	private OpencrdbatchService opencrdbatchService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Crdopen crdopen, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		//操作员list
		List<String> operatorList = opencrdbatchService.getOperator();
		model.addAttribute("operatorList", operatorList);
		
		//订单号list
		/*if (StringUtils.isNotBlank(crdopen.getOperator())) {
			List<String> orderList = opencrdbatchService.getOrderByOper(crdopen.getOperator());
			model.addAttribute("orderList", orderList);
		}*/
		
		Page<Crdopen> page = crdopenService.findPage(new Page<Crdopen>(request, response), crdopen); 
		model.addAttribute("page", page);
		model.addAttribute("crdopen", crdopen);
		return "modules/cortexs/codeTable/crdopenList";
	}
	
	@RequiresPermissions("cortexs:crdopen:edit")
	@RequestMapping(value = "show")
	public String show(Crdopen queryModel, Model model) {
		Crdopen crdopen = crdopenService.get(queryModel);
		
		//操作员list
		List<String> operatorList = opencrdbatchService.getOperator();
		model.addAttribute("operatorList", operatorList);
		//订单号list
		/*List<String> orderList = opencrdbatchService.getOrderByOper(crdopen.getOperator());
		model.addAttribute("orderList", orderList);*/
		
		model.addAttribute("crdopen", crdopen);
		return "modules/cortexs/codeTable/crdopenForm";
	}
	
	@RequiresPermissions("cortexs:crdopen:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		//操作员list
		List<String> operatorList = opencrdbatchService.getOperator();
		model.addAttribute("operatorList", operatorList);
		
		model.addAttribute("crdopen", new Crdopen());
		return "modules/cortexs/codeTable/crdopenForm";
	}
	
	@RequiresPermissions("cortexs:crdopen:edit")
	@RequestMapping(value = "save")
	public String save(Crdopen crdopen, Model model, RedirectAttributes redirectAttributes) {
		crdopen.setCreateBy(UserUtils.getUser());
		crdopen.setUpdateBy(UserUtils.getUser());
		try {
			crdopenService.saveCrdopen(crdopen);
			addMessage(redirectAttributes, "保存民生订单信息成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存民生订单信息失败");
		}
		return "redirect:/cortexs/Crdopen/?repage";
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(Crdopen crdopen, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			crdopenService.delete(crdopen);
			map.put("result", "0");
			map.put("desc", "删除民生订单信息成功!");
		} catch (Exception e) {
			map.put("result", "1");
			map.put("desc", "删除民生订单信息失败!");
		}
		return map;
	}
	
	@RequestMapping(value = "getOrder")
	@ResponseBody
	public List<String> getOrder(String operator) {
		List<String> list = null;
		if (StringUtils.isNotBlank(operator)) {
			//订单号list
			list = opencrdbatchService.getOrderByOper(operator);
		}
		return list;
	}
	
}