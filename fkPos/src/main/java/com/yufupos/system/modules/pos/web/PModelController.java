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
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.pos.entity.PFactory;
import com.yufupos.system.modules.pos.entity.PModel;
import com.yufupos.system.modules.pos.service.PFactoryService;
import com.yufupos.system.modules.pos.service.PModelService;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * 型号信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pModel")
public class PModelController extends BaseController {

	@Autowired
	private PModelService pModelService;
	@Autowired
	private PFactoryService pFactoryService;
	
	
	@ModelAttribute
	public PModel get(@RequestParam(required=false) String modelId) {
		PModel entity = null;
		if (StringUtils.isNotBlank(modelId)){
			entity = pModelService.get(modelId);
		}
		if (entity == null){
			entity = new PModel();
		}
		entity.setId(modelId);
		return entity;
	}
	
	@RequiresPermissions("pos:pModel:view")
	@RequestMapping(value = {"list", ""})
	public String list(PModel pModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PModel> page = pModelService.findPage(new Page<PModel>(request, response), pModel); 
		List<PModel> list = page.getList();
		//匹配创建者名称
		for (PModel pm : list) {
			if (pm.getCreateBy() != null) {
				pm.setCreateBy(UserUtils.get(pm.getCreateBy().getId()));
			}
		}
		model.addAttribute("page", page);
		//厂商码表
		PFactory entity=new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");		
		List<PFactory> pFactoryList=pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		return "modules/pos/pModelList";
	}

	@RequiresPermissions("pos:pModel:view")
	@RequestMapping(value = "form")
	public String form(PModel pModel, Model model) {
		//厂商码表
		PFactory entity=new PFactory();
		entity.setDelFlag("0");		
		entity.setFactoryStatus("0");		
		List<PFactory> pFactoryList=pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		if(Strings.isNullOrEmpty(pModel.getModelStatus())){
			pModel.setModelStatus("0");
		}
		model.addAttribute("pModel", pModel);
		return "modules/pos/pModelForm";
	}
	
	@RequiresPermissions("pos:pModel:view")
	@RequestMapping(value = "toShow")
	public String toShow(PModel pModel, Model model) {
		//厂商码表
		PFactory entity=new PFactory();
		entity.setDelFlag("0");		
		entity.setFactoryStatus("0");		
		List<PFactory> pFactoryList=pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		model.addAttribute("pModel", pModel);
		return "modules/pos/pModelShow";
	}


	@RequiresPermissions("pos:pModel:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PModel pModel, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//设置厂商名称
			if(pModel.getFactoryId()!=null&&Strings.isNullOrEmpty(pModel.getFactoryName())){
				Map<String,String> pfMap=this.getPFactoryMap();
				if(pfMap!=null&&pfMap.size()>0){
					pModel.setFactoryName(pfMap.get(pModel.getFactoryId()));
				}
			}
			pModelService.save(pModel);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存型号信息成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存型号信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pModel:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PModel pModel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "型号信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<PModel> page = pModelService.findPage(new Page<PModel>(request, response ,-1), pModel); 
			List<PModel> findList = pModelService.findList(pModel);
			new ExportExcel("型号信息", PModel.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出型号信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pModel/list?repage";
    }
	
	@RequiresPermissions("pos:pModel:edit")
	@RequestMapping(value = "delete")
	public String delete(PModel pModel, RedirectAttributes redirectAttributes) {
		pModelService.delete(pModel);
		addMessage(redirectAttributes, "删除型号信息成功");
		return "redirect:"+"/pos/pModel/list?repage";
	}
	
	/**
	 * 设置厂商MAP
	 * @return
	 */
	public Map<String,String> getPFactoryMap(){
		Map<String,String> map=new HashMap<String,String>();
		//获取店长码表
		PFactory pManagerSM=new PFactory();
		pManagerSM.setFactoryStatus("0");
		pManagerSM.setDelFlag("0");
		List<PFactory>  storeManagerList= pFactoryService.findList(pManagerSM);
		for(PFactory pm:storeManagerList){
			map.put(pm.getId(), pm.getFactoryName());
		}
		return map;
	}
	
	/**
	 * 验证modelId是否有效
	 * 
	 * @param sn
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pModel:edit")
	@RequestMapping(value = "checkModelId")
	public String checkModelId(String modelId) {
		if (!Strings.isNullOrEmpty(modelId)) {
			PModel pModel = new PModel();
			pModel.setModelId(modelId);
			List<PModel> list = this.pModelService.findList(pModel);
			if (list == null || list.size() < 1) {
				return "true";
			}
		}
		return "false";
	}

}