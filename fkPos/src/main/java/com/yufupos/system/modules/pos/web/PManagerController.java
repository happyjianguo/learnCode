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
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.service.PManagerService;

/**
 * 商户经理信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pManager")
public class PManagerController extends BaseController {

	@Autowired
	private PManagerService pManagerService;
	
	@ModelAttribute
	public PManager get(@RequestParam(required=false) String id) {
		PManager entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pManagerService.get(id);
		}
		if (entity == null){
			entity = new PManager();
		}
		return entity;
	}
	
	@RequiresPermissions("pos:pManager:view")
	@RequestMapping(value = {"list", ""})
	public String list(PManager pManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PManager> page = pManagerService.findPage(new Page<PManager>(request, response), pManager);
		List<PManager> list=page.getList();
		//设置上一级商户经理名称
		if(list!=null&&list.size()>0){
			Map<String,String> storeManagerMap=this.getStoreManagerMap();
			if(storeManagerMap!=null&&storeManagerMap.size()>0){
				for(PManager pm:list){
					pm.setFatherId(storeManagerMap.get(pm.getFatherId()));
				}				
			}
		}		
		model.addAttribute("page", page);
		return "modules/pos/pManagerList";
	}

	@RequiresPermissions("pos:pManager:view")
	@RequestMapping(value = "form")
	public String form(PManager pManager, Model model) {
		if(Strings.isNullOrEmpty(pManager.getManagerStatus())){
			pManager.setManagerStatus("0");
		}
		model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",pManager.getManagerArea()));
		model.addAttribute("pManager", pManager);
		return "modules/pos/pManagerForm";
	}

	@RequiresPermissions("pos:pManager:view")
	@RequestMapping(value = "toShow")
	public String toShow(PManager pManager, Model model) {
		model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",""));
		model.addAttribute("pManager", pManager);
		return "modules/pos/pManagerShow";
	}
	

	@RequiresPermissions("pos:pManager:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PManager pManager, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pManagerService.save(pManager);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存商户经理信息成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存商户经理信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pManager:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PManager pManager, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商户经理信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<PManager> page = pManagerService.findPage(new Page<PManager>(request, response ,-1), pManager); 
			List<PManager> list = pManagerService.findList(pManager);
			//设置上一级商户经理名称
			if(list!=null&&list.size()>0){
				Map<String,String> storeManagerMap=this.getStoreManagerMap();
				if(storeManagerMap!=null&&storeManagerMap.size()>0){
					for(PManager pm:list){
						pm.setFatherId(storeManagerMap.get(pm.getFatherId()));
					}				
				}
			}				
    		new ExportExcel("商户经理信息", PManager.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户经理信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pManager/list?repage";
    }
	
	@RequiresPermissions("pos:pManager:edit")
	@RequestMapping(value = "delete")
	public String delete(PManager pManager, RedirectAttributes redirectAttributes) {
		pManagerService.delete(pManager);
		addMessage(redirectAttributes, "删除商户经理信息成功");
		return "redirect:"+"/pos/pManager/list?repage";
	}

	/**
	 * 设置店长MAP
	 * @return
	 */
	public Map<String,String> getStoreManagerMap(){
		Map<String,String> map=new HashMap<String,String>();
		//获取店长码表
		for(PManager pm:pManagerService.findListByRoleId("1","")){
			map.put(pm.getId(), pm.getManagerName());
		}
		return map;
	}
	
}