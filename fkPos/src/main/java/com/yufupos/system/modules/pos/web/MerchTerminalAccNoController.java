package com.yufupos.system.modules.pos.web;

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

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.pos.entity.MerchTerminalAccNo;
import com.yufupos.system.modules.pos.service.MerchTerminalAccNoService;

/**
 * 商户终端入账银行账号信息Controller
 * @author ZQK
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "/pos/merchTerminalAccNo")
public class MerchTerminalAccNoController extends BaseController {

	@Autowired
	private MerchTerminalAccNoService merchTerminalAccNoService;
	
	@RequiresPermissions("pos:MerchTerminalAccNo:view")
	@RequestMapping(value = {"page"})
	public String page(MerchTerminalAccNo merchTerminalAccNo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MerchTerminalAccNo> page = new Page<MerchTerminalAccNo>(request, response); 
		model.addAttribute("page", page);
		return "modules/pos/merchTerminalAccNoList";
	}
	
	@RequiresPermissions("pos:MerchTerminalAccNo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MerchTerminalAccNo merchTerminalAccNo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MerchTerminalAccNo> page = merchTerminalAccNoService.findPage(new Page<MerchTerminalAccNo>(request, response), merchTerminalAccNo); 
		model.addAttribute("page", page);
		return "modules/pos/merchTerminalAccNoList";
	}
	
	@RequiresPermissions("pos:MerchTerminalAccNo:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MerchTerminalAccNo merchTerminalAccNo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商户结算账号"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<MerchTerminalAccNo> list = merchTerminalAccNoService.findList(merchTerminalAccNo);
			new ExportExcel("商户结算账号", MerchTerminalAccNo.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出SIM卡信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/merchTerminalAccNo/list?repage";
    }
	
}