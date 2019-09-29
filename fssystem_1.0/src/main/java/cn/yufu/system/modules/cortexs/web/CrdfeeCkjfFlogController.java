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
import cn.yufu.system.modules.cortexs.entity.CrdfeeCkjfFlog;
import cn.yufu.system.modules.cortexs.service.CrdfeeCkjfFlogService;

/**
 * 财卡过期积分扣款明细Controller
 * @author ZQK
 * @version 2018-06-01
 */
@Controller
@RequestMapping(value = "/cortexs/crdfeeCkjfFlog")
public class CrdfeeCkjfFlogController extends BaseController {

	@Autowired
	private CrdfeeCkjfFlogService crdfeeCkjfFlogService;
	
	@RequestMapping(value = {"page"})
	public String page(CrdfeeCkjfFlog crdfeeCkjfFlog, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeCkjfFlog> page = new Page<CrdfeeCkjfFlog>(request, response);
		model.addAttribute("page", page);
		model.addAttribute("crdfeeCkjfFlog", crdfeeCkjfFlog);
		return "modules/cortexs/crdfeeDetail/crdfeeCkjfFlogList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdfeeCkjfFlog crdfeeCkjfFlog, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeCkjfFlog> page = crdfeeCkjfFlogService.findPage(new Page<CrdfeeCkjfFlog>(request, response), crdfeeCkjfFlog); 
		model.addAttribute("page", page);
		model.addAttribute("crdfeeCkjfFlog", crdfeeCkjfFlog);
		return "modules/cortexs/crdfeeDetail/crdfeeCkjfFlogList";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrdfeeCkjfFlog CrdfeeCkjfFlog, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "财卡过期积分扣款明细信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CrdfeeCkjfFlog> list = crdfeeCkjfFlogService.findList(CrdfeeCkjfFlog);
            new ExportExcel("财卡过期积分扣款明细信息", CrdfeeCkjfFlog.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出财卡过期积分扣款明细信息！失败信息："+e.getMessage());
		}
		return "redirect:/cortexs/crdfeeCkjfFlog/?repage";
    }
}