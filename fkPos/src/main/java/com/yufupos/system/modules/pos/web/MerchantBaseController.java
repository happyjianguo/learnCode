package com.yufupos.system.modules.pos.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.pos.entity.AreaCodeInfo;
import com.yufupos.system.modules.pos.entity.EdcTerminal;
import com.yufupos.system.modules.pos.entity.MccParam;
import com.yufupos.system.modules.pos.entity.MerchantBase;
import com.yufupos.system.modules.pos.entity.MerchantX;
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.entity.PPos;
import com.yufupos.system.modules.pos.entity.PPosActLog;
import com.yufupos.system.modules.pos.entity.ReportShow;
import com.yufupos.system.modules.pos.service.AreaCodeInfoService;
import com.yufupos.system.modules.pos.service.EdcTerminalService;
import com.yufupos.system.modules.pos.service.MccParamService;
import com.yufupos.system.modules.pos.service.MerchantBaseService;
import com.yufupos.system.modules.pos.service.PManagerService;
import com.yufupos.system.modules.pos.service.PPosActLogService;
import com.yufupos.system.modules.pos.service.PPosService;
import com.yufupos.system.modules.pos.service.ReportShowService;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * 商户信息Controller
 * @author llg
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "/pos/merchantBase")
public class MerchantBaseController extends BaseController {

	@Autowired
	private MerchantBaseService merchantBaseService;
	@Autowired
	private MccParamService mccParamService;
	@Autowired
	private AreaCodeInfoService areaCodeInfoService;
	@Autowired
	private PManagerService pManagerService;
	@Autowired
	private ReportShowService reportShowService;
	@Autowired
	private EdcTerminalService edcTerminalService;
	@Autowired 
	private PPosActLogService pPosActLogService;	
	@Autowired
	private PPosService pPosService;
	
	@ModelAttribute
	public MerchantBase get(@RequestParam(required=false) String merchantId) {
		MerchantBase entity = null;
		if (StringUtils.isNotBlank(merchantId)){
			entity = merchantBaseService.get(merchantId);
		}
		if (entity == null){
			entity = new MerchantBase();
		}else {
			entity.setId(merchantId); //执行更新
		}
		return entity;
	}
	
	@RequiresPermissions("pos:merchantBase:view")
	@RequestMapping(value = {"list", ""})
	public String list(MerchantBase merchantBase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,String> mccMap=this.getMap("mcc");
		Map<String,String> areaMap=this.getMap("area");
		String contractflag = merchantBase.getContractflag();
		if (StringUtils.isNotBlank(contractflag)) {
			if ("1".equals(contractflag)) {
				//已到期
				merchantBase.setContractTime(this.getNowDt("yyyyMMdd", 0));
			}else {
				//未到期或者将续约
				merchantBase.setContractTime(this.getAfterMonthDay("yyyyMMdd"));
			}
		}
		String cityCname = merchantBase.getCityCname();
		String mcc = merchantBase.getMcc();
		if (StringUtils.isNotBlank(cityCname)) {
			Iterator<Entry<String, String>> iterator = areaMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				try {
					if (entry.getValue().equals(cityCname)) {
						merchantBase.setCityCname(entry.getKey());
					}
				} catch (Exception e) {}
			}
		}
		
		if (StringUtils.isNotBlank(mcc)) {
			Iterator<Entry<String, String>> iterator = mccMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				try {
					if (entry.getValue().trim().equals(mcc.trim())) {
						merchantBase.setMcc(entry.getKey());
					}
				} catch (Exception e) {}
			}
		}
		Page<MerchantBase> page = merchantBaseService.findPage(new Page<MerchantBase>(request, response), merchantBase); 
		merchantBase.setCityCname(cityCname);
		merchantBase.setMcc(mcc);
		List<MerchantBase> list=page.getList();
		if(list!=null&&list.size()>0){
			for(MerchantBase mb:list){
				mb.setMcc(mccMap.get(mb.getMcc()));
				mb.setCityCname(areaMap.get(Strings.isNullOrEmpty(mb.getCityCname())?"":mb.getCityCname().trim()));
			}
		}		
		model.addAttribute("page", page);
		return "modules/pos/merchantBaseList";
	}

	@RequiresPermissions("pos:merchantBase:view")
	@RequestMapping(value = "form")
	public String form(MerchantBase merchantBase, Model model) {
		//店长
		model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",merchantBase.getMerchantArea()));
		//普通员工及店长
		model.addAttribute("managerList", pManagerService.findListByRoleId("", merchantBase.getMerchantArea()));
		//设置MCC名称和地址名称
		if(merchantBase!=null){
			Map<String,String> mccMap=this.getMap("mcc");
			Map<String,String> areaMap=this.getMap("area");
			merchantBase.setMcc(mccMap.get(merchantBase.getMcc()));
			merchantBase.setCityCname(areaMap.get(Strings.isNullOrEmpty(merchantBase.getCityCname())?"":merchantBase.getCityCname().trim()));			
		}
		model.addAttribute("merchantBase", merchantBase);
		return "modules/pos/merchantBaseForm";
	}
	
	@RequiresPermissions("pos:merchantBase:edit")
	@RequestMapping(value = "disable")
	public String disable(String merchantIds, Model model) {
		MerchantBase merchantBase = new MerchantBase();
		merchantBase.setDisableDate(getNowDt("yyyyMMdd", 0));
		merchantBase.setMerchantId(merchantIds);
		System.out.println(merchantIds);
		model.addAttribute("merchantBase", merchantBase);
		return "modules/pos/merchantBaseDisable";
	}
	
	@RequestMapping(value = "disableSave")
	@ResponseBody
	public String disableSave(MerchantBase merchantForm, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//得到要停用的商户编号
			String merchantIdsStr[] = StringUtils.split(merchantForm.getMerchantId(), '|');
			for (int i = 0; i < merchantIdsStr.length; i++) {
				MerchantBase merchantBase = new MerchantBase();
				String merchantId = merchantIdsStr[i];
				merchantBase.setMerchantId(merchantId);
				merchantBase.setMerchantStat("N"); //冻结
				merchantBase.setDisableDate(merchantForm.getDisableDate());
				merchantBase.setUpdateTime(DateUtils.getDate("HHmmss"));
				//停用商户
				merchantBaseService.disableMerchant(merchantBase);
				//根据商户号得到终端与POS
				List<ReportShow> list = reportShowService.getOneData(null, merchantId);
				if (list != null && list.size() > 0) {
					for (ReportShow reportShow : list) {
						try {
							if (StringUtils.isNotBlank(reportShow.getTerminalId())) {
								//停用终端
								EdcTerminal edcTerminal = new EdcTerminal();
								edcTerminal.setTerminalId(reportShow.getTerminalId());
								edcTerminal.setTerminalStat("N");
								edcTerminal.setDisableDate(merchantForm.getDisableDate());
								edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
								edcTerminalService.disableTerminal(edcTerminal);
							}
							try {
								if (StringUtils.isNotBlank(reportShow.getPosSn())) {
									//停用POS机：解绑，恢复为空闲状态
									PPos pPos = new PPos();
									pPos.setPosSn(reportShow.getPosSn());
									List<PPos> listOld=this.pPosService.findList(pPos);
									pPos = listOld.get(0);
									String terminalId = pPos.getTerminalId();
									if(pPos!=null){
										pPos.setPosStatus("IDLE");
										pPos.setTerminalId("");
										this.pPosService.disablePPos(pPos);	
										//新增日志： 解绑
										PPosActLog pPosActLog = new PPosActLog();
										pPosActLog.setMerchantId(reportShow.getMerchantId());
										pPosActLog.setPosSn(reportShow.getPosSn());
										pPosActLog.setTerminalId(terminalId);			
										pPosActLog.setPosActType("UNBTERM");
										pPosActLog.setPosActMsg("商户停用，POS机解绑");
										pPosActLog.setRemarks("商户停用，POS机解绑");
										this.pPosActLogService.save(pPosActLog);
									}
								}
							} catch (Exception e) {
								map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
								map.put(SysConst.RESULT_MSG, "POS机解绑失败");
								return JsonUtil.getJsonString(map);
							}
						} catch (Exception e) {
							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
							map.put(SysConst.RESULT_MSG, "停用终端失败");
							return JsonUtil.getJsonString(map);
						}
					}
				}
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "停用商户成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "停用商户失败");
		}
		return JsonUtil.getJsonString(map);
	}

	/**
	 * @param merchantBase
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:merchantBase:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(MerchantBase merchantBase, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String> mccMap = this.getMap("mcc");
		int flag = 0;
		if (StringUtils.isEmpty(merchantBase.getFlagMerchantX())) {
			if (StringUtils.isNotBlank(merchantBase.getMcc())) {
				Iterator<Entry<String, String>> iterator = mccMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> entry = iterator.next();
					if (merchantBase.getMcc().trim().equals(entry.getValue()==null?"":entry.getValue().trim())) {
						merchantBase.setMcc(entry.getKey());
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "MCC不存在!");
					return JsonUtil.getJsonString(map);
				}
			}else{
				merchantBase.setMcc("");
			}
		}
		String merchantStat = merchantBase.getMerchantStat();
		try {
			//是否停用
			if (StringUtils.isNotBlank(merchantBase.getDisableDate()) && "Y".equals(merchantStat) 
					&& StringUtils.isEmpty(merchantBase.getUsableDate())) {
				//停用商户
				merchantBase.setMerchantStat("N"); //冻结
				merchantBase.setUpdateTime(DateUtils.getDate("HHmmss"));
				merchantBaseService.disableMerchant(merchantBase);
				//根据商户号得到终端与POS
				List<ReportShow> list = reportShowService.getOneData(null, merchantBase.getMerchantId());
				if (list != null && list.size() > 0) {
					for (ReportShow reportShow : list) {
						//停用终端
						EdcTerminal edcTerminal = new EdcTerminal();
						if (StringUtils.isNotBlank(reportShow.getTerminalId())) {
							edcTerminal.setTerminalId(reportShow.getTerminalId());
							edcTerminal.setTerminalStat("N");
							edcTerminal.setDisableDate(merchantBase.getDisableDate());
							edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
							edcTerminalService.disableTerminal(edcTerminal);
						}
						if (StringUtils.isNotBlank(reportShow.getPosSn())) {
							//停用POS机：解绑，恢复为空闲状态
							PPos pPos = new PPos();
							pPos.setPosSn(reportShow.getPosSn());
							List<PPos> listOld=this.pPosService.findList(pPos);
							pPos = listOld.get(0);
							String terminalId = pPos.getTerminalId();
							if(pPos!=null){
								pPos.setPosStatus("IDLE");
								pPos.setTerminalId("");
								this.pPosService.disablePPos(pPos);	
								//新增日志： 解绑
								PPosActLog pPosActLog = new PPosActLog();
								pPosActLog.setMerchantId(reportShow.getMerchantId());
								pPosActLog.setPosSn(reportShow.getPosSn());
								pPosActLog.setTerminalId(terminalId);			
								pPosActLog.setPosActType("UNBTERM");
								pPosActLog.setPosActMsg("商户停用，POS机解绑");
								pPosActLog.setRemarks("商户停用，POS机解绑");
								this.pPosActLogService.save(pPosActLog);
							}
						}
					}
				}
			}
			//是否启用
			List<MerchantX> merchantById = merchantBaseService.getMerchantById(merchantBase);
			flag = 0;
			for (MerchantX merchantX : merchantById) {
				if (merchantBase.getUsableDate().equals(merchantX.getUsableDate())) {
					flag = 1;
				}
				if (StringUtils.isNotBlank(merchantBase.getMerchantArea())) {
					if (StringUtils.isNotBlank(merchantX.getMerchantArea())) {
						if (merchantBase.getMerchantArea().equals(merchantX.getMerchantArea())) {
						}else {
							merchantBase.setAllotDate(DateUtils.getDate("yyyyMMdd"));
							merchantBase.setSharer(UserUtils.getPrincipal().getName());
						}
					}else {
						merchantBase.setAllotDate(DateUtils.getDate("yyyyMMdd"));
						merchantBase.setSharer(UserUtils.getPrincipal().getName());
					}
				}else {
					if (StringUtils.isNotBlank(merchantX.getMerchantArea())) {
						merchantBase.setAllotDate(DateUtils.getDate("yyyyMMdd"));
						merchantBase.setSharer(UserUtils.getPrincipal().getName());
					}
				}
			}
			if (StringUtils.isNotBlank(merchantBase.getUsableDate())
					&& "N".equals(merchantStat)) {
				if (flag != 1) {
					//启用商户
					merchantBase.setMerchantStat("Y"); 
					merchantBase.setUpdateTime(DateUtils.getDate("HHmmss"));
					merchantBaseService.usableMerchant(merchantBase);
					//根据商户号得到终端与POS
					List<ReportShow> list = reportShowService.getOneData(null, merchantBase.getMerchantId());
					if (list != null && list.size() > 0) {
						for (ReportShow reportShow : list) {
							//启用终端
							EdcTerminal edcTerminal = new EdcTerminal();
							if (StringUtils.isNotBlank(reportShow.getTerminalId())) {
								edcTerminal.setTerminalId(reportShow.getTerminalId());
								edcTerminal.setTerminalStat("Y");
								edcTerminal.setUsableDate(merchantBase.getUsableDate());
								edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
								edcTerminalService.usableTerminal(edcTerminal);
							}
						}
					}
				}
			}
			merchantBase.setUpdateOper(UserUtils.getPrincipal().getLoginName());
			merchantBase.setUpdateDateStr(DateUtils.getDate("yyyyMMdd"));
			merchantBase.setUpdateTime(DateUtils.getDate("HHmmss"));
			if (merchantById == null || merchantById.size() == 0) {
				merchantBase.setIsNewRecord(true);
			}
			merchantBaseService.save(merchantBase);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存商户信息成功");			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存商户信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:merchantBase:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MerchantBase merchantBase, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商户信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            String contractflag = merchantBase.getContractflag();
    		if (StringUtils.isNotBlank(contractflag)) {
    			if ("1".equals(contractflag)) {
    				//已到期
    				merchantBase.setContractTime(this.getNowDt("yyyyMMdd", 0));
    			}else {
    				//未到期或者将续约
    				merchantBase.setContractTime(this.getAfterMonthDay("yyyyMMdd"));
    			}
    		}
            //Page<MerchantBase> page = merchantBaseService.findPage(new Page<MerchantBase>(request, response ,-1), merchantBase); 
            List<MerchantBase> findList = merchantBaseService.findList(merchantBase);
            new ExportExcel("商户信息", MerchantBase.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/merchantBase/list?repage";
    }
	
	@RequiresPermissions("pos:merchantBase:edit")
	@RequestMapping(value = "delete")
	public String delete(MerchantBase merchantBase, RedirectAttributes redirectAttributes) {
		merchantBaseService.delete(merchantBase);
		addMessage(redirectAttributes, "删除商户信息成功");
		return "redirect:"+"/pos/merchantBase/list?repage";
	}
	
	/**
	 * 根据类型获取MAP
	 * @return
	 */
	public Map<String,String> getMap(String mapType){
		Map<String,String> map=new HashMap<String,String>();
		if("mcc".equals(mapType)){
			List<MccParam> list=this.mccParamService.findList(null);
			for(MccParam entity:list){
				map.put(entity.getMcc(), entity.getMccName());
			}
		}else if("area".equals(mapType)){
			List<AreaCodeInfo> list=this.areaCodeInfoService.findList(null);
			for(AreaCodeInfo entity:list){
				map.put(entity.getAreaCode(), entity.getAreaName());
			}
		}
		return map;
	}
	
	
	/**
	 * 获取店长或商户顾问LIST
	 * @param req
	 * @param resp
	 * @param model
	 * @param roleId 角色
	 * @param managerArea  地址
	 * @return
	 */
	@RequestMapping(value = { "getManagerList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getManagerList(String roleId,String managerArea,String storeManager) {
		List<SelectEntity> pmList = new ArrayList<SelectEntity>();
		SelectEntity pm = null;
		for (PManager pm1 : pManagerService.findListByRoleId(roleId, managerArea)) {
			pm = new SelectEntity();
			pm.setValue(pm1.getId());
			pm.setName(pm1.getManagerName());
			pmList.add(pm);
		}
		return JsonUtil.getJsonString(pmList);
	}
	
	/**
	 * 取后两月日期
	 * @param pattern
	 * @return
	 */
	public String getAfterMonthDay(String pattern) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 2); // 得到后两月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
	
	/**
	 * 获取当前时间
	 * @param pattern	时间格式
	 * @return
	 */
	public String getNowDt(String pattern,int flag){
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, flag);
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
}