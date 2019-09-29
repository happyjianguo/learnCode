package com.yufupos.system.modules.pos.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yufupos.system.modules.pos.entity.AreaCodeInfo;
import com.yufupos.system.modules.pos.service.AreaCodeInfoService;

/**
 * 收单地址Controller
 * @author llg
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "/pos/areaCodeInfo")
public class AreaCodeInfoController extends BaseController {

	@Autowired
	private AreaCodeInfoService areaCodeInfoService;
	
	@ModelAttribute
	public AreaCodeInfo get(@RequestParam(required=false) String areaCode) {
		AreaCodeInfo entity = null;
		if (StringUtils.isNotBlank(areaCode)){
			entity = areaCodeInfoService.get(areaCode);
		}
		if (entity == null){
			entity = new AreaCodeInfo();
		}
		entity.setId(areaCode);
		return entity;
	}
	
	@RequiresPermissions("pos:areaCodeInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(AreaCodeInfo areaCodeInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AreaCodeInfo> page = areaCodeInfoService.findPage(new Page<AreaCodeInfo>(request, response), areaCodeInfo); 
		model.addAttribute("page", page);
		return "modules/pos/areaCodeInfoList";
	}

	@RequiresPermissions("pos:areaCodeInfo:view")
	@RequestMapping(value = "form")
	public String form(AreaCodeInfo areaCodeInfo, Model model) {
		model.addAttribute("areaCodeInfo", areaCodeInfo);
		return "modules/pos/areaCodeInfoForm";
	}


	@RequiresPermissions("pos:areaCodeInfo:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(AreaCodeInfo areaCodeInfo, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			areaCodeInfoService.save(areaCodeInfo);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存收单地址成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存收单地址失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:areaCodeInfo:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AreaCodeInfo areaCodeInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收单地址"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<AreaCodeInfo> page = areaCodeInfoService.findPage(new Page<AreaCodeInfo>(request, response ,-1), areaCodeInfo); 
    		new ExportExcel("收单地址", AreaCodeInfo.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出收单地址！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/areaCodeInfo/list?repage";
    }
	
	@RequiresPermissions("pos:areaCodeInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(AreaCodeInfo areaCodeInfo, RedirectAttributes redirectAttributes) {
		areaCodeInfoService.delete(areaCodeInfo);
		addMessage(redirectAttributes, "删除收单地址成功");
		return "redirect:"+"/pos/areaCodeInfo/list?repage";
	}

}