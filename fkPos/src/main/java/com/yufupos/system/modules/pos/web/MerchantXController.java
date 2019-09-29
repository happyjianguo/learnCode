package com.yufupos.system.modules.pos.web;

import java.io.UnsupportedEncodingException;
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

import com.yufupos.system.common.SelectEntity;
import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.config.Global;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.pos.entity.MerchantBase;
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.service.MerchantBaseService;
import com.yufupos.system.modules.pos.service.PManagerService;
import com.yufupos.system.modules.sys.entity.Dict;
import com.yufupos.system.modules.sys.utils.DictUtils;
import com.yufupos.system.modules.sys.utils.UserUtils;

import cn.yufu.system.common.shiro.PrincipalBean;

/**
 * 商户补充信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/merchantX")
public class MerchantXController extends BaseController {

	@Autowired
	private MerchantBaseService MerchantBaseService;
	
	@Autowired
	private PManagerService pManagerService;
	
	@ModelAttribute
	public MerchantBase get(@RequestParam(required=false) String merchantId) {
		MerchantBase entity = null;
		if (StringUtils.isNotBlank(merchantId)){
			entity = MerchantBaseService.get(merchantId);
		}
		if (entity == null){
			entity = new MerchantBase();
		}else{
			entity.setId(merchantId); //执行更新
		}
		return entity;
	}
	
	@RequiresPermissions("pos:merchantX:view")
	@RequestMapping(value = {"list", ""})
	public String list(MerchantBase MerchantBase, HttpServletRequest request, HttpServletResponse response, Model model) {
		//获取当前登录对象
		PrincipalBean principal = UserUtils.getPrincipal();
		String name = principal.getName();
		//获取商户经理集合
		List<PManager> mchManagerList = pManagerService.findListByRoleId("","");
		String config = Global.getConfig("regionManager");
		try {
			config = new String(config.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//如果是大区经理登录,不做任何限制
		if (StringUtils.isNotBlank(config)) {
			String[] configName = config.split(",");
	        for(String filter : configName) {
	        	if (name.equals(filter)) {
					if (StringUtils.isNotBlank(MerchantBase.getMerchantArea())) {
						//设置门店负责人下拉框
						List<SelectEntity> storeManagerList = getList("1",MerchantBase);
						model.addAttribute("storeManagerList", storeManagerList);
						
						//设置商户顾问下拉框
						List<SelectEntity> merchantAdvisorList = getList("",MerchantBase);
						model.addAttribute("merchantAdvisorList", merchantAdvisorList);
					}
					
					Page<MerchantBase> page = MerchantBaseService.findPage(new Page<MerchantBase>(request, response), MerchantBase); 
					List<MerchantBase> list = page.getList();
					if (list == null || list.size() == 0) {
						
					}else {
						for (MerchantBase merchant : list) {
							if (StringUtils.isNotBlank(merchant.getStoreManager())) {
								PManager pManager = pManagerService.get(merchant.getStoreManager());
								merchant.setStoreManager(pManager.getManagerName());
								if (StringUtils.isNotBlank(merchant.getMerchantAdvisor())) {
									pManager = pManagerService.get(merchant.getMerchantAdvisor());
									merchant.setMerchantAdvisor(pManager.getManagerName());
								}
							}
						}
					}
					model.addAttribute("page", page);
					
					model.addAttribute("merchantBase", MerchantBase);
					
					return "modules/pos/merchantXList";
				}
	        }
		}
		
		//获取商户经理信息 0  店长 1商户顾问
		List<SelectEntity> merchantAdvisorList = new ArrayList<SelectEntity>();
		if (StringUtils.isEmpty(MerchantBase.getStoreManager())) {
			for (int i = 0; i < mchManagerList.size(); i++) {
				PManager pManager = mchManagerList.get(i);
				//是商户经理登录
				if (name.equals(pManager.getManagerName())) {
					//获取登录的经理信息
					if ("1".equals(pManager.getManagerRole())) {
						//店长登录
						MerchantBase.setMerchantArea(pManager.getManagerArea());
						MerchantBase.setStoreManager(pManager.getId());
						//设置商户顾问下拉框
						if (StringUtils.isNotBlank(MerchantBase.getStoreManager())) {
							merchantAdvisorList = getList("",MerchantBase);
						}
						model.addAttribute("merchantAdvisorList", merchantAdvisorList);
						
						model.addAttribute("storeManager", pManager.getManagerName());
						model.addAttribute("result",0);
						model.addAttribute("allotAuth",0);
						break;
					} else {
						//普通员工登录
						//获取门店负责人
						PManager manager = pManagerService.get(pManager.getFatherId());
						
						MerchantBase.setMerchantArea(pManager.getManagerArea());
						MerchantBase.setStoreManager(manager.getId());
						if (StringUtils.isEmpty(MerchantBase.getMerchantAdvisor())) {
							MerchantBase.setMerchantAdvisor(pManager.getId());
						}
						model.addAttribute("result",1);
						model.addAttribute("allotAuth",0);
						model.addAttribute("storeManager", manager.getManagerName());
						model.addAttribute("merchantAdvisor", pManager.getManagerName());
					}
					break;
				}
				if (i == mchManagerList.size() - 1) {
					//非商户经理登录
					if (StringUtils.isNotBlank(MerchantBase.getMerchantArea())) {
						//设置门店负责人下拉框
						List<SelectEntity> storeManagerList = getList("1",MerchantBase);
						model.addAttribute("storeManagerList", storeManagerList);
						
						//设置商户顾问下拉框
						merchantAdvisorList = getList("",MerchantBase);
						model.addAttribute("merchantAdvisorList", merchantAdvisorList);
					}
					
					Page<MerchantBase> page = MerchantBaseService.findPage(new Page<MerchantBase>(request, response), MerchantBase); 
					List<MerchantBase> list = page.getList();
					if (list == null || list.size() == 0) {
						
					}else {
						for (MerchantBase merchant : list) {
							if (StringUtils.isNotBlank(merchant.getStoreManager())) {
								PManager pm = pManagerService.get(merchant.getStoreManager());
								merchant.setStoreManager(pm.getManagerName());
								if (StringUtils.isNotBlank(merchant.getMerchantAdvisor())) {
									pManager = pManagerService.get(merchant.getMerchantAdvisor());
									merchant.setMerchantAdvisor(pm.getManagerName());
								}
							}
						}
					}
					model.addAttribute("page", page);
					
					model.addAttribute("merchantBase", MerchantBase);
					model.addAttribute("allotAuth",1);
					return "modules/pos/merchantXList";
				}
			}
		} else{
			for (int i = 0; i < mchManagerList.size(); i++) {
				PManager pManager = mchManagerList.get(i);
				if (name.equals(pManager.getManagerName())) {
					PManager manager = pManagerService.get(MerchantBase.getStoreManager());
					model.addAttribute("storeManager", manager.getManagerName());
					//设置商户顾问下拉框
					if (StringUtils.isNotBlank(MerchantBase.getStoreManager())) {
						merchantAdvisorList = getList("",MerchantBase);
					}
					model.addAttribute("merchantAdvisorList", merchantAdvisorList);
					model.addAttribute("result",0);
					model.addAttribute("allotAuth",0);
					
					//判断是否是普通员工登录
					if (StringUtils.isEmpty(MerchantBase.getStoreManagerAllot())) {
						manager = pManagerService.get(MerchantBase.getMerchantAdvisor());
						model.addAttribute("merchantAdvisor", manager.getManagerName());
						model.addAttribute("result",1);
						model.addAttribute("allotAuth",0);
					}
					break;
				}
				if (i == mchManagerList.size() - 1) {
					//非商户经理登录
					if (StringUtils.isNotBlank(MerchantBase.getMerchantArea())) {
						//设置门店负责人下拉框
						List<SelectEntity> storeManagerList = getList("1",MerchantBase);
						model.addAttribute("storeManagerList", storeManagerList);
						
						//设置商户顾问下拉框
						merchantAdvisorList = getList("",MerchantBase);
						model.addAttribute("merchantAdvisorList", merchantAdvisorList);
					}
					
					Page<MerchantBase> page = MerchantBaseService.findPage(new Page<MerchantBase>(request, response), MerchantBase); 
					List<MerchantBase> list = page.getList();
					if (list == null || list.size() == 0) {
						
					}else {
						for (MerchantBase merchant : list) {
							if (StringUtils.isNotBlank(merchant.getStoreManager())) {
								PManager pm = pManagerService.get(merchant.getStoreManager());
								merchant.setStoreManager(pm.getManagerName());
								if (StringUtils.isNotBlank(merchant.getMerchantAdvisor())) {
									pManager = pManagerService.get(merchant.getMerchantAdvisor());
									merchant.setMerchantAdvisor(pm.getManagerName());
								}
							}
						}
					}
					model.addAttribute("page", page);
					
					model.addAttribute("merchantBase", MerchantBase);
					model.addAttribute("allotAuth",1);
					return "modules/pos/merchantXList";
				}
			}
		}
		
		Page<MerchantBase> page = MerchantBaseService.findPage(new Page<MerchantBase>(request, response), MerchantBase); 
		List<MerchantBase> list = page.getList();
		for (MerchantBase merchant : list) {
			if (StringUtils.isNotBlank(merchant.getStoreManager())) {
				PManager pManager = pManagerService.get(merchant.getStoreManager());
				merchant.setStoreManager(pManager.getManagerName());
				if (StringUtils.isNotBlank(merchant.getMerchantAdvisor())) {
					pManager = pManagerService.get(merchant.getMerchantAdvisor());
					merchant.setMerchantAdvisor(pManager.getManagerName());
				}
			}
		}
		model.addAttribute("page", page);
		
		//获取区域列表
		List<Dict> dictList = DictUtils.getDictList("MERCHANT_AREA");
		for (Dict dict : dictList) {
			if (dict.getValue().equals(MerchantBase.getMerchantArea())) {
				model.addAttribute("merchantArea", dict.getLabel());
			}
		}
		model.addAttribute("merchantBase", MerchantBase);
		
		return "modules/pos/merchantXListBak";
	}
	
	@RequiresPermissions("pos:merchantX:view")
	@RequestMapping(value = "form")
	public String form(MerchantBase merchantBase, Model model) {
		//获取当前登录对象
		PrincipalBean principal = UserUtils.getPrincipal();
		String name = principal.getName();
		List<PManager> mchManagerList = pManagerService.findListByRoleId("","");
		//如果是大区经理登录,不做任何限制
		if (name.equals("系统管理员")||name.equals("大区经理")
				|| name.equals("杨浩")) {
			//店长
			model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",merchantBase.getMerchantArea()));
			//普通员工及店长
			model.addAttribute("managerList", pManagerService.findListByRoleId("",merchantBase.getMerchantArea()));

			model.addAttribute("merchantBase", merchantBase);
			return "modules/pos/merchantXForm";
		}else{
			for (int i = 0; i < mchManagerList.size(); i++) {
				PManager pManager = mchManagerList.get(i);
				if (name.equals(pManager.getManagerName())) {
					break;
				}
				if (i == mchManagerList.size() - 1) {
					//店长
					model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",merchantBase.getMerchantArea()));
					//普通员工及店长
					model.addAttribute("managerList", pManagerService.findListByRoleId("",merchantBase.getMerchantArea()));

					model.addAttribute("merchantBase", merchantBase);
					return "modules/pos/merchantXForm";
				}
			}
		}
		//普通员工及店长
		model.addAttribute("managerList", pManagerService.findListByRoleId("",merchantBase.getMerchantArea()));
		
		//获取区域列表
		List<Dict> dictList = DictUtils.getDictList("MERCHANT_AREA");
		for (Dict dict : dictList) {
			if (dict.getValue().equals(merchantBase.getMerchantArea())) {
				model.addAttribute("merchantArea", dict.getLabel());
			}
		}
		if (StringUtils.isNotBlank(merchantBase.getStoreManager())) {
			PManager manager = pManagerService.get(merchantBase.getStoreManager());
			model.addAttribute("storeManager", manager.getManagerName());
		}
		
		model.addAttribute("merchantBase", merchantBase);
		return "modules/pos/merchantXFormBak";
	}
	
	@RequiresPermissions("pos:merchantX:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(MerchantBase MerchantBase, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MerchantBaseService.save(MerchantBase);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存商户补充信息成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存商户补充信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:merchantX:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MerchantBase merchantBase, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商户补充信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<MerchantBase> page = MerchantBaseService.findPage(new Page<MerchantBase>(request, response ,-1), merchantBase); 
			List<MerchantBase> findList = MerchantBaseService.findList(merchantBase);
			new ExportExcel("商户补充信息", MerchantBase.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户补充信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/merchantX/list?repage";
    }
	
	@RequiresPermissions("pos:merchantX:edit")
	@RequestMapping(value = "delete")
	public String delete(MerchantBase MerchantBase, RedirectAttributes redirectAttributes) {
		MerchantBaseService.delete(MerchantBase);
		addMessage(redirectAttributes, "删除商户补充信息成功");
		return "redirect:"+"/pos/merchantX/list?repage";
	}
	
	/**
	 * 根据区域获取区域下的店长 storeManager 或  商户顾问 merchantAdvisor
	 * @author Administrator
	 *
	 */
	@RequestMapping(value = "getSMgrOrMAdvisor")
	@ResponseBody
	public List<SelectEntity> getSMgrOrMAdvisor(String roleId, MerchantBase merchantBase) {
		return getList(roleId, merchantBase);
	}
	
	/**
	 * 分配商户
	 * @param merchantIds 商户编号
	 * @param merchantArea 负责人区域
	 * @param storeManager 门店负责人
	 * @param merchantAdvisor 商户顾问
	 * @author Administrator
	 *
	 */
	@RequiresPermissions("pos:merchantX:edit")
	@RequestMapping(value = "allotMerToMgr")
	@ResponseBody
	public Map<String, String> allotMerToMgr(String merchantIds,String merchantArea,
			String storeManager, String merchantAdvisor) {
		Map<String,String> map = new HashMap<String,String>();
		MerchantBase merchantBase = new MerchantBase();
		merchantBase.setStoreManager(storeManager);
		merchantBase.setMerchantArea(merchantArea);
		merchantBase.setMerchantAdvisor(merchantAdvisor);
		try {
			//得到要分配的商户编号
			String merchantIdsStr[] = StringUtils.split(merchantIds, '|');
			for (int i = 0; i < merchantIdsStr.length; i++) {
				String merchantId = merchantIdsStr[i];
				merchantBase.setMerchantId(merchantId);
				merchantBase.setAllotDate(DateUtils.getDate("yyyyMMdd"));
				merchantBase.setSharer(UserUtils.getPrincipal().getName());
				MerchantBaseService.updateStoreManager(merchantBase);
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "分配商户成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "分配商户失败");
		}
		return map;
	}
	
	/**
	 * 根据区域获取区域下的店长 storeManager 1 或  商户顾问 merchantAdvisor  0/1
	 * @author Administrator
	 *
	 */
	private List<SelectEntity> getList(String roleId, MerchantBase merchantBase) {
		List<SelectEntity> pmList = new ArrayList<SelectEntity>();
		SelectEntity pm = null;
		List<PManager> storeManagerList = pManagerService.findListByRoleId(roleId,merchantBase.getMerchantArea());
		for (PManager pManager : storeManagerList) {
			pm = new SelectEntity();
			pm.setValue(pManager.getId());
			pm.setName(pManager.getManagerName());
			pmList.add(pm);
		}
		return pmList;
		/*if (StringUtils.isEmpty(merchantBase.getStoreManager())) {
			return pmList;
		} else {
			List<PManager> merchantAdvisorList = pManagerService.findListByRoleId("0",merchantBase.getMerchantArea());
			for (PManager pManager : merchantAdvisorList) {
				pm = new SelectEntity();
				pm.setValue(pManager.getId());
				pm.setName(pManager.getManagerName());
				pmList.add(pm);
			}
		}*/
	}
	
}