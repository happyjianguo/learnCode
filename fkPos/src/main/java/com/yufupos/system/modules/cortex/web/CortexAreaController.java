package com.yufupos.system.modules.cortex.web;

import java.util.ArrayList;
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
import com.yufupos.system.common.SelectEntity;
import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.cortex.entity.CortexArea;
import com.yufupos.system.modules.cortex.service.CortexAreaService;

/**
 * 省市区信息Controller
 * @author llg
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "/cortex/cortexArea")
public class CortexAreaController extends BaseController {

	@Autowired
	private CortexAreaService cortexAreaService;
	
	@ModelAttribute
	public CortexArea get(@RequestParam(required=false) String id) {
		CortexArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cortexAreaService.get(id);
		}
		if (entity == null){
			entity = new CortexArea();
		}
		return entity;
	}
	
	@RequiresPermissions("cortex:cortexArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(CortexArea cortexArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CortexArea> page = cortexAreaService.findPage(new Page<CortexArea>(request, response), cortexArea); 
		model.addAttribute("page", page);
		return "modules/cortex/cortexAreaList";
	}

	@RequiresPermissions("cortex:cortexArea:view")
	@RequestMapping(value = "form")
	public String form(CortexArea cortexArea, Model model) {
		model.addAttribute("cortexArea", cortexArea);
		return "modules/cortex/cortexAreaForm";
	}


	@RequiresPermissions("cortex:cortexArea:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(CortexArea cortexArea, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			cortexAreaService.save(cortexArea);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存省市区信息成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存省市区信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("cortex:cortexArea:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CortexArea cortexArea, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "省市区信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<CortexArea> page = cortexAreaService.findPage(new Page<CortexArea>(request, response ,-1), cortexArea); 
    		new ExportExcel("省市区信息", CortexArea.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出省市区信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/cortex/cortexArea/list?repage";
    }
	
	@RequiresPermissions("cortex:cortexArea:edit")
	@RequestMapping(value = "delete")
	public String delete(CortexArea cortexArea, RedirectAttributes redirectAttributes) {
		cortexAreaService.delete(cortexArea);
		addMessage(redirectAttributes, "删除省市区信息成功");
		return "redirect:"+"/cortex/cortexArea/list?repage";
	}
	
	/**
	 * 获取cortexArea 
	 * @param req
	 * @param resp
	 * @param model
	 * @param depth 级别
	 * @param fid  上一级ID
	 * @return
	 */
	@RequestMapping(value = { "getCortexAreaList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getCortexAreaList(HttpServletRequest req,
			HttpServletResponse resp, Model model,String depth, String fid) {
		CortexArea cortexArea=new CortexArea();
		cortexArea.setIsuse("1");
		if(!Strings.isNullOrEmpty(depth)){
			cortexArea.setDepth(Integer.valueOf(depth));
		}
		if(!Strings.isNullOrEmpty(fid)){
			cortexArea.setFid(Integer.valueOf(fid));
		}		
		List<CortexArea> list=this.cortexAreaService.findList(cortexArea);
		List<SelectEntity> pmList = new ArrayList<SelectEntity>();
		SelectEntity pm = null;
		for (CortexArea pm1 : list) {
			pm = new SelectEntity();
			pm.setValue(pm1.getId());
			pm.setName(pm1.getProvinceCity());
			pmList.add(pm);
		}
		return JsonUtil.getJsonString(pmList);
	}

}