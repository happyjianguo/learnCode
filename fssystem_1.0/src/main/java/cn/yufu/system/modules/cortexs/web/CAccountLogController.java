package cn.yufu.system.modules.cortexs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CAccountLog;
import cn.yufu.system.modules.cortexs.service.CAccountLogService;


/**
 * CAccountLog交易类型Controller
 * @author zbq
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "/cortexs/cAccountLog")
public class CAccountLogController extends BaseController {

	@Autowired
	private CAccountLogService cAccountLogService;
	
	@ModelAttribute
	public CAccountLog get(@RequestParam(required=false) String id) {
		CAccountLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cAccountLogService.get(id);
		}
		if (entity == null){
			entity = new CAccountLog();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CAccountLog cAccountLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CAccountLog> page = cAccountLogService.findPage(new Page<CAccountLog>(request, response), cAccountLog); 
		model.addAttribute("page", page);
		model.addAttribute("CAccountLog", cAccountLog);
		return "modules/cortexs/cAccountLogList";
	}



}