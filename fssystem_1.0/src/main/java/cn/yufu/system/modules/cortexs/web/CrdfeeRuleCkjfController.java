package cn.yufu.system.modules.cortexs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcel;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CrdfeeRuleCkjf;
import cn.yufu.system.modules.cortexs.service.CrdfeeRuleCkjfService;

/**
 * 积分卡BIN管理Controller
 * @author ZQK
 * @version 2018-06-01
 */
@Controller
@RequestMapping(value = "/cortexs/crdfeeRuleCkjf")
public class CrdfeeRuleCkjfController extends BaseController {

	@Autowired
	private CrdfeeRuleCkjfService crdfeeRuleCkjfService;
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdfeeRuleCkjf crdfeeRuleCkjf, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdfeeRuleCkjf> page = crdfeeRuleCkjfService.findPage(new Page<CrdfeeRuleCkjf>(request, response), crdfeeRuleCkjf); 
		model.addAttribute("page", page);
		model.addAttribute("crdfeeRuleCkjf", crdfeeRuleCkjf);
		return "modules/cortexs/codeTable/crdfeeRuleCkjfList";
	}
	
	@RequiresPermissions("cortexs:crdfeeRuleCkjf:edit")
	@RequestMapping(value = "form")
	public String form(Model model, CrdfeeRuleCkjf crdfeeRuleCkjf) {
		if (StringUtils.isNotEmpty(crdfeeRuleCkjf.getId())) {
			//修改
			crdfeeRuleCkjf = crdfeeRuleCkjfService.get(crdfeeRuleCkjf);
		}
		model.addAttribute("crdfeeRuleCkjf", crdfeeRuleCkjf);
		return "modules/cortexs/codeTable/crdfeeRuleCkjfForm";
	}
	
	@RequiresPermissions("cortexs:crdfeeRuleCkjf:edit")
	@RequestMapping(value = "save")
	public String save(CrdfeeRuleCkjf crdfeeRuleCkjf, Model model, 
			RedirectAttributes redirectAttributes) {
		try {
			CrdfeeRuleCkjf verify = crdfeeRuleCkjfService.get(crdfeeRuleCkjf);
			String date = DateUtils.getDate("yyyyMMdd");
			if (StringUtils.isEmpty(crdfeeRuleCkjf.getId())) {
				crdfeeRuleCkjf.setIsNewRecord(true);
				crdfeeRuleCkjf.setInserttime(date);
				if (null != verify) {
					addMessage(redirectAttributes, "保存积分卡BIN失败，卡BIN不可重复！");
					return "redirect:/cortexs/crdfeeRuleCkjf/?repage";
				}
			}
			crdfeeRuleCkjf.setUpdattime(date);
			crdfeeRuleCkjfService.save(crdfeeRuleCkjf);
			addMessage(redirectAttributes, "保存积分卡BIN成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存积分卡BIN失败，请您稍后再试！");
		}
		return "redirect:/cortexs/crdfeeRuleCkjf/?repage";
	}
	
	@RequiresPermissions("cortexs:crdfeeRuleCkjf:edit")
	@RequestMapping(value = "delete")
	public String delete(CrdfeeRuleCkjf crdfeeRuleCkjf, Model model, 
			RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isEmpty(crdfeeRuleCkjf.getId())) {
				addMessage(redirectAttributes, "删除积分卡BIN失败，ID不可为空");
				return "redirect:/cortexs/crdfeeRuleCkjf/?repage";
			} 
			crdfeeRuleCkjfService.delete(crdfeeRuleCkjf);
			addMessage(redirectAttributes, "删除积分卡BIN成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "删除积分卡BIN失败，请您稍后再试");
		}
		return "redirect:/cortexs/crdfeeRuleCkjf/?repage";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrdfeeRuleCkjf crdfeeRuleCkjf, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分卡BIN信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CrdfeeRuleCkjf> list = crdfeeRuleCkjfService.findList(crdfeeRuleCkjf);
            new ExportExcel("积分卡BIN信息", CrdfeeRuleCkjf.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出积分卡BIN信息！失败信息："+e.getMessage());
		}
		return "redirect:/cortexs/crdfeeRuleCkjf/?repage";
    }
}