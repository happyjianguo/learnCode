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
import com.yufupos.system.modules.pos.entity.TerminalKey;
import com.yufupos.system.modules.pos.service.TerminalKeyService;

/**
 * 终端主密钥Controller
 * @author ZQK
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "/pos/terminalKey")
public class TerminalKeyController extends BaseController {

	@Autowired
	private TerminalKeyService terminalKeyService;
	
	@RequiresPermissions("pos:terminalKey:view")
	@RequestMapping(value = {"page"})
	public String page(TerminalKey terminalKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TerminalKey> page = new Page<TerminalKey>(request, response);
		model.addAttribute("page", page);
		return "modules/pos/terminalKeyList";
	}
	
	@RequiresPermissions("pos:terminalKey:view")
	@RequestMapping(value = {"list", ""})
	public String list(TerminalKey terminalKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TerminalKey> page = terminalKeyService.findPage(new Page<TerminalKey>(request, response), terminalKey); 
		model.addAttribute("page", page);
		return "modules/pos/terminalKeyList";
	}
	
	@RequiresPermissions("pos:terminalKey:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TerminalKey terminalKey, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "终端主密钥信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<TerminalKey> findList = terminalKeyService.findList(terminalKey);
			new ExportExcel("终端主密钥信息", TerminalKey.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出终端主密钥信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/terminalKey/list?repage";
    }

}