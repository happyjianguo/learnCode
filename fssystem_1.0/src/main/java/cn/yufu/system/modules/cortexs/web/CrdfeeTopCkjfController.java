package cn.yufu.system.modules.cortexs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.excel.ExportExcel;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CrdfeeTopCkjf;
import cn.yufu.system.modules.cortexs.service.CrdfeeTopCkjfService;

/**
 * 财卡积分充值明细Controller
 * @author ZQK
 * @version 2018-06-01
 */
@Controller
@RequestMapping(value = "/cortexs/crdfeeTopCkjf")
public class CrdfeeTopCkjfController extends BaseController {

	@Autowired
	private CrdfeeTopCkjfService crdfeeTopCkjfService;
	
	@RequestMapping(value = {"page"})
	public String page(CrdfeeTopCkjf crdfeeTopCkjf, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeTopCkjf> page = new Page<CrdfeeTopCkjf>(request, response);
		model.addAttribute("page", page);
		model.addAttribute("crdfeeTopCkjf", crdfeeTopCkjf);
		return "modules/cortexs/crdfeeDetail/crdfeeTopCkjfList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdfeeTopCkjf crdfeeTopCkjf, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeTopCkjf> page = crdfeeTopCkjfService.findPage(new Page<CrdfeeTopCkjf>(request, response), crdfeeTopCkjf); 
		model.addAttribute("page", page);
		model.addAttribute("crdfeeTopCkjf", crdfeeTopCkjf);
		return "modules/cortexs/crdfeeDetail/crdfeeTopCkjfList";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrdfeeTopCkjf crdfeeTopCkjf, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "财卡积分充值明细信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CrdfeeTopCkjf> list = crdfeeTopCkjfService.findList(crdfeeTopCkjf);
            new ExportExcel("财卡积分充值明细信息", CrdfeeTopCkjf.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出财卡积分充值明细信息！失败信息："+e.getMessage());
		}
		return "redirect:/cortexs/crdfeeTopCkjf/?repage";
    }
}