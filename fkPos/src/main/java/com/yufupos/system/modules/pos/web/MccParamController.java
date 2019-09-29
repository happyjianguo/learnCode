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
import com.yufupos.system.modules.pos.entity.MccParam;
import com.yufupos.system.modules.pos.service.MccParamService;

/**
 * MCCController
 * @author llg
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "/pos/mccParam")
public class MccParamController extends BaseController {

	@Autowired
	private MccParamService mccParamService;
	
	@ModelAttribute
	public MccParam get(@RequestParam(required=false) String mcc) {
		MccParam entity = null;
		if (StringUtils.isNotBlank(mcc)){
			entity = mccParamService.get(mcc);
		}
		if (entity == null){
			entity = new MccParam();
		}
		entity.setId(mcc);
		return entity;
	}
	
	@RequiresPermissions("pos:mccParam:view")
	@RequestMapping(value = {"list", ""})
	public String list(MccParam mccParam, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MccParam> page = mccParamService.findPage(new Page<MccParam>(request, response), mccParam); 
		model.addAttribute("page", page);
		return "modules/pos/mccParamList";
	}

	@RequiresPermissions("pos:mccParam:view")
	@RequestMapping(value = "form")
	public String form(MccParam mccParam, Model model) {
		model.addAttribute("mccParam", mccParam);
		return "modules/pos/mccParamForm";
	}


	@RequiresPermissions("pos:mccParam:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(MccParam mccParam, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			mccParamService.save(mccParam);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存MCC成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存MCC失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:mccParam:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MccParam mccParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "MCC"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<MccParam> page = mccParamService.findPage(new Page<MccParam>(request, response ,-1), mccParam); 
    		new ExportExcel("MCC", MccParam.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出MCC！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/mccParam/list?repage";
    }
	
	@RequiresPermissions("pos:mccParam:edit")
	@RequestMapping(value = "delete")
	public String delete(MccParam mccParam, RedirectAttributes redirectAttributes) {
		mccParamService.delete(mccParam);
		addMessage(redirectAttributes, "删除MCC成功");
		return "redirect:"+"/pos/mccParam/list?repage";
	}

}