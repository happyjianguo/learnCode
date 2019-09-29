package com.yufupos.system.modules.pos.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.pos.entity.PPosActLog;
import com.yufupos.system.modules.pos.service.PPosActLogService;
import com.yufupos.system.modules.sys.entity.User;
import com.yufupos.system.modules.sys.service.UserService;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * POS机操作记录Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pPosActLog")
public class PPosActLogController extends BaseController {

	@Autowired
	private PPosActLogService pPosActLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@ModelAttribute
	public PPosActLog get(@RequestParam(required=false) String id) {
		PPosActLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pPosActLogService.get(id);
		}
		if (entity == null){
			entity = new PPosActLog();
		}
		return entity;
	}
	
	@RequiresPermissions("pos:pPosActLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(PPosActLog pPosActLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		//创建者ID
		String nameStr = "";
		if (pPosActLog.getCreateBy() != null) {
			nameStr = pPosActLog.getCreateBy().getName();
		}
		if (StringUtils.isEmpty(nameStr)) {
			pPosActLog.setCreateBy(null);
		}else{
			List<User> name = userService.getByName(nameStr);
			if (name == null || name.size() == 0) {
				return "modules/pos/pPosActLogList";
			}else {
				pPosActLog.setQueryCreateByName(name);
				pPosActLog.setCreateBy(null);
			}
		}
		Page<PPosActLog> page = pPosActLogService.findPage(new Page<PPosActLog>(request, response), pPosActLog); 
		List<PPosActLog> list = page.getList();
		//匹配创建者名称
		for (PPosActLog pm : list) {
			if (pm.getCreateBy() != null) {
				pm.setCreateBy(UserUtils.get(pm.getCreateBy().getId()));
			}
		}
		User user = new User();
		user.setName(nameStr);
		pPosActLog.setCreateBy(user);
		model.addAttribute("page", page);
		return "modules/pos/pPosActLogList";
	}

	@RequiresPermissions("pos:pPosActLog:view")
	@RequestMapping(value = "form")
	public String form(PPosActLog pPosActLog, Model model) {
		if (pPosActLog.getCreateBy() != null) {
			pPosActLog.setCreateBy(UserUtils.get(pPosActLog.getCreateBy().getId()));
		}
		model.addAttribute("pPosActLog", pPosActLog);
		return "modules/pos/pPosActLogForm";
	}


	@RequiresPermissions("pos:pPosActLog:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PPosActLog pPosActLog, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pPosActLogService.save(pPosActLog);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存POS机操作记录成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机操作记录失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pPosActLog:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PPosActLog pPosActLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "POS机操作记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<PPosActLog> page = pPosActLogService.findPage(new Page<PPosActLog>(request, response ,-1), pPosActLog); 
			List<PPosActLog> findList = pPosActLogService.findList(pPosActLog);
			new ExportExcel("POS机操作记录", PPosActLog.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出POS机操作记录！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pPosActLog/list?repage";
    }
	
	@RequiresPermissions("pos:pPosActLog:edit")
	@RequestMapping(value = "delete")
	public String delete(PPosActLog pPosActLog, RedirectAttributes redirectAttributes) {
		pPosActLogService.delete(pPosActLog);
		addMessage(redirectAttributes, "删除POS机操作记录成功");
		return "redirect:"+"/pos/pPosActLog/list?repage";
	}

}