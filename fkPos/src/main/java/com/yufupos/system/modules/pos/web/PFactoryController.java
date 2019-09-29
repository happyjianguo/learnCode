package com.yufupos.system.modules.pos.web;

import java.util.HashMap;
import java.util.List;
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

import com.google.common.base.Strings;
import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.pos.entity.PFactory;
import com.yufupos.system.modules.pos.service.PFactoryService;

/**
 * 厂商信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pFactory")
public class PFactoryController extends BaseController {

	@Autowired
	private PFactoryService pFactoryService;
	
	@ModelAttribute
	public PFactory get(@RequestParam(required=false) String id) {
		PFactory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pFactoryService.get(id);
		}
		if (entity == null){
			entity = new PFactory();
		}
		return entity;
	}
	
	@RequiresPermissions("pos:pFactory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PFactory pFactory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PFactory> page = pFactoryService.findPage(new Page<PFactory>(request, response), pFactory); 
		model.addAttribute("page", page);
		return "modules/pos/pFactoryList";
	}

	@RequiresPermissions("pos:pFactory:view")
	@RequestMapping(value = "form")
	public String form(PFactory pFactory, Model model) {
		if(Strings.isNullOrEmpty(pFactory.getFactoryStatus())){
			pFactory.setFactoryStatus("0");
		}
		
		model.addAttribute("pFactory", pFactory);
		return "modules/pos/pFactoryForm";
	}
	
	@RequiresPermissions("pos:pFactory:view")
	@RequestMapping(value = "toShow")
	public String toShow(PFactory pFactory, Model model) {
		model.addAttribute("pFactory", pFactory);
		return "modules/pos/pFactoryShow";
	}


	@RequiresPermissions("pos:pFactory:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PFactory pFactory, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pFactoryService.save(pFactory);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存厂商信息成功");			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存厂商信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pFactory:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PFactory pFactory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "厂商信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<PFactory> page = pFactoryService.findPage(new Page<PFactory>(request, response ,-1), pFactory); 
			List<PFactory> findList = pFactoryService.findList(pFactory);
			new ExportExcel("厂商信息", PFactory.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出厂商信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pFactory/list?repage";
    }
	
	@RequiresPermissions("pos:pFactory:edit")
	@RequestMapping(value = "delete")
	public String delete(PFactory pFactory, RedirectAttributes redirectAttributes) {
		pFactoryService.delete(pFactory);
		addMessage(redirectAttributes, "删除厂商信息成功");
		return "redirect:"+"/pos/pFactory/list?repage";
	}

}