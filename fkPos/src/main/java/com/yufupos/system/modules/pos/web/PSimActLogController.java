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
import com.yufupos.system.modules.pos.entity.PSimActLog;
import com.yufupos.system.modules.pos.service.PSimActLogService;
import com.yufupos.system.modules.sys.entity.User;
import com.yufupos.system.modules.sys.service.UserService;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * SIM卡操作记录Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pSimActLog")
public class PSimActLogController extends BaseController {

	@Autowired
	private PSimActLogService pSimActLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@ModelAttribute
	public PSimActLog get(@RequestParam(required=false) String id) {
		PSimActLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pSimActLogService.get(id);
		}
		if (entity == null){
			entity = new PSimActLog();
		}
		return entity;
	}
	
	@RequiresPermissions("pos:pSimActLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(PSimActLog pSimActLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		//创建者ID
		String nameStr = "";
		if (pSimActLog.getCreateBy() != null) {
			nameStr = pSimActLog.getCreateBy().getName();
		}
		if (StringUtils.isEmpty(nameStr)) {
			pSimActLog.setCreateBy(null);
		}else{
			List<User> name = userService.getByName(nameStr);
			if (name == null || name.size() == 0) {
				return "modules/pos/pSimActLogList";
			}else {
				pSimActLog.setQueryCreateByName(name);
				pSimActLog.setCreateBy(null);
			}
		}
		Page<PSimActLog> page = pSimActLogService.findPage(new Page<PSimActLog>(request, response), pSimActLog); 
		List<PSimActLog> list = page.getList();
		//匹配创建者名称
		for (PSimActLog psa : list) {
			if (psa.getCreateBy() != null) {
				psa.setCreateBy(UserUtils.get(psa.getCreateBy().getId()));
			}
		}
		User user = new User();
		user.setName(nameStr);
		pSimActLog.setCreateBy(user);
		model.addAttribute("page", page);
		return "modules/pos/pSimActLogList";
	}

	@RequiresPermissions("pos:pSimActLog:view")
	@RequestMapping(value = "form")
	public String form(PSimActLog pSimActLog, Model model) {
		if (pSimActLog.getCreateBy() != null) {
			pSimActLog.setCreateBy(UserUtils.get(pSimActLog.getCreateBy().getId()));
		}
		model.addAttribute("pSimActLog", pSimActLog);
		return "modules/pos/pSimActLogForm";
	}


	@RequiresPermissions("pos:pSimActLog:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PSimActLog pSimActLog, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pSimActLogService.save(pSimActLog);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存SIM卡操作记录成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存SIM卡操作记录失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pSimActLog:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PSimActLog pSimActLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "SIM卡操作记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<PSimActLog> page = pSimActLogService.findPage(new Page<PSimActLog>(request, response ,-1), pSimActLog); 
			List<PSimActLog> findList = pSimActLogService.findList(pSimActLog);
			new ExportExcel("SIM卡操作记录", PSimActLog.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出SIM卡操作记录！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pSimActLog/list?repage";
    }
	
	@RequiresPermissions("pos:pSimActLog:edit")
	@RequestMapping(value = "delete")
	public String delete(PSimActLog pSimActLog, RedirectAttributes redirectAttributes) {
		pSimActLogService.delete(pSimActLog);
		addMessage(redirectAttributes, "删除SIM卡操作记录成功");
		return "redirect:"+"/pos/pSimActLog/list?repage";
	}

}