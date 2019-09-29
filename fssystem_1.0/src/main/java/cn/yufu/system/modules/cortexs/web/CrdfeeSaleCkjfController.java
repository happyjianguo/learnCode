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
import cn.yufu.system.modules.cortexs.entity.CrdfeeSaleCkjf;
import cn.yufu.system.modules.cortexs.service.CrdfeeSaleCkjfService;

/**
 * 财卡积分消费明细Controller
 * @author ZQK
 * @version 2018-06-01
 */
@Controller
@RequestMapping(value = "/cortexs/crdfeeSaleCkjf")
public class CrdfeeSaleCkjfController extends BaseController {

	@Autowired
	private CrdfeeSaleCkjfService crdfeeSaleCkjfService;
	
	@RequestMapping(value = {"page"})
	public String page(CrdfeeSaleCkjf crdfeeSaleCkjf, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeSaleCkjf> page = new Page<CrdfeeSaleCkjf>(request, response);
		model.addAttribute("page", page);
		model.addAttribute("crdfeeSaleCkjf", crdfeeSaleCkjf);
		return "modules/cortexs/crdfeeDetail/crdfeeSaleCkjfList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdfeeSaleCkjf crdfeeSaleCkjf, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeSaleCkjf> page = crdfeeSaleCkjfService.findPage(new Page<CrdfeeSaleCkjf>(request, response), crdfeeSaleCkjf); 
		model.addAttribute("page", page);
		model.addAttribute("crdfeeSaleCkjf", crdfeeSaleCkjf);
		return "modules/cortexs/crdfeeDetail/crdfeeSaleCkjfList";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrdfeeSaleCkjf crdfeeSaleCkjf, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "财卡积分消费明细信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CrdfeeSaleCkjf> list = crdfeeSaleCkjfService.findList(crdfeeSaleCkjf);
            new ExportExcel("财卡积分消费明细信息", CrdfeeSaleCkjf.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出财卡积分消费明细信息！失败信息："+e.getMessage());
		}
		return "redirect:/cortexs/crdfeeSaleCkjf/?repage";
    }
}