package com.yufupos.system.modules.pos.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.bak.entity.TermposXBak;
import com.yufupos.system.modules.bak.service.TermposXBakService;
import com.yufupos.system.modules.cortex.entity.CortexArea;
import com.yufupos.system.modules.cortex.entity.MerchantXPlatform;
import com.yufupos.system.modules.cortex.service.CortexAreaService;
import com.yufupos.system.modules.cortex.service.MerchantXPlatformService;
import com.yufupos.system.modules.pos.entity.AreaCodeInfo;
import com.yufupos.system.modules.pos.entity.MccParam;
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.entity.ReportShow;
import com.yufupos.system.modules.pos.service.AreaCodeInfoService;
import com.yufupos.system.modules.pos.service.MccParamService;
import com.yufupos.system.modules.pos.service.MerchantXService;
import com.yufupos.system.modules.pos.service.PManagerService;
import com.yufupos.system.modules.pos.service.ReportShowService;
import com.yufupos.system.modules.sys.utils.SysParameterUtils;

/**
 * 报表展示Controller
 * @author zqk
 * @version 2017-06-09
 */
@Controller
@RequestMapping(value = "/pos/edcReportShow")
public class ReportShowController extends BaseController {
	
	@Autowired
	private ReportShowService reportShowService;
	
	@Autowired
	private MccParamService mccParamService;
	
	@Autowired
	private AreaCodeInfoService areaCodeInfoService;
	
	@Autowired
	private TermposXBakService termposXBakService;
	
	@Autowired
	private PManagerService pManagerService;
	
	@Autowired
	private MerchantXPlatformService merchantXPlatformService;
	
	@Autowired
	private MerchantXService merchantXService;
	
	@Autowired
	private CortexAreaService cortexAreaService;
	
	@ModelAttribute
	public ReportShow get(@RequestParam(required=false) String terminalId, 
			@RequestParam(required=false) String merchantId) {
		ReportShow entity = null;
		List<ReportShow> list = null;
		if (StringUtils.isNotBlank(terminalId) || StringUtils.isNotBlank(merchantId)){
			list = reportShowService.getOneData(terminalId, merchantId);
		}
		if (list == null || list.size() == 0){
			entity = new ReportShow();
		} else {
			entity = list.get(0);
			entity.setId(merchantId);
		}
		return entity;
	}
	
	@RequiresPermissions("pos:edcReportShow:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportShow reportShow, HttpServletRequest request, HttpServletResponse response, Model model) {
		//设置省份
		model.addAttribute("provinceList", cortexAreaService.findList(new CortexArea(null,1)));
		if (StringUtils.isNotBlank(reportShow.getTerminalProvince())) {
			//设置城市
			Integer provinceId=Strings.isNullOrEmpty(reportShow.getTerminalProvince())?null:Integer.valueOf(reportShow.getTerminalProvince());
			model.addAttribute("cityList", cortexAreaService.findList(new CortexArea(provinceId,2)));		
			if (StringUtils.isNotBlank(reportShow.getTerminalCity())) {
				//设置区域
				Integer cityId=Strings.isNullOrEmpty(reportShow.getTerminalCity())?null:Integer.valueOf(reportShow.getTerminalCity());
				model.addAttribute("zoneList", cortexAreaService.findList(new CortexArea(cityId,null)));
			}
		}
		
		Map<String,String> mccMap = this.getMap("mcc");
		String contractflag = reportShow.getContractflag();
		if (StringUtils.isNotBlank(contractflag)) {
			if ("1".equals(contractflag)) {
				//已到期
				reportShow.setContractTime(this.getNowDt("yyyyMMdd", 0));
			}else {
				//未到期或者将续约
				reportShow.setContractTime(this.getAfterMonthDay("yyyyMMdd"));
			}
		}
		
		//当查询条件费率为空时
		if (StringUtils.isEmpty(reportShow.getTimezone())) {
			Page<ReportShow> page = reportShowService.findPage(new Page<ReportShow>(request, response), reportShow); 			
			getPageList(mccMap, page.getList());
			model.addAttribute("page", page);
			return "modules/pos/edcReportShowList";
		}
		
		//当查询条件费率不为空时
		List<ReportShow> queryList = new ArrayList<ReportShow>();
		List<ReportShow> resultList = new ArrayList<ReportShow>();
		Page<ReportShow> pageAll = new Page<ReportShow>(request, response);
		//得到满足查询条件（除了费率）的所有数据
		List<ReportShow> findAllList = reportShowService.findAllList(reportShow);
		
		//得到所有与费率相匹配的数据
		if(findAllList != null && findAllList.size() > 0){
			queryList = getAllList(reportShow, mccMap, queryList, findAllList, null);
		}
		//得到分页数据
		int start = (pageAll.getPageNo() - 1) * pageAll.getPageSize();
		if (start <= 0) {
			start = 0;
		}
		int end = pageAll.getPageNo() * pageAll.getPageSize();
		if (end > queryList.size()) {
			end = queryList.size();
		}
		for(int i = start; i < end; i++){
			resultList.add(queryList.get(i));
		}
		//设置总条数
		pageAll.setCount(queryList.size());
		pageAll.setList(resultList);
		model.addAttribute("page", pageAll);
		return "modules/pos/edcReportShowList";
	}

	/**
	 * 详细列表
	 * @return 详细界面
	 */
	@RequiresPermissions("pos:edcReportShow:view")
	@RequestMapping(value = "form")
	public String form(ReportShow reportShow, Model model) {
		//获取省市区集合
		Map<String, String> areaMap = getAreaMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//店长
		model.addAttribute("storeManagerList", pManagerService.findListByRoleId("1",reportShow.getMerchantArea()));
		//设置MCC名称和地址名称
		if(reportShow != null){
			Map<String,String> mccMap=this.getMap("mcc");
			reportShow.setMcc(mccMap.get(reportShow.getMcc()));
			
			TermposXBak termposX = new TermposXBak();
			termposX.setTermcode(reportShow.getTerminalId());
			termposX.setMrchno(reportShow.getMerchantId());
			//根据商户号和终端号,匹配费率和停用日期
			List<TermposXBak> oneData = termposXBakService.getOneData(termposX);
			if (oneData != null && oneData.size() > 0) {
				termposX = oneData.get(0);
				if (StringUtils.isEmpty(termposX.getTimezone())) {
					reportShow.setTimezone("0");
				} else {
					reportShow.setTimezone(termposX.getTimezone());
				}
			}else {
				reportShow.setTimezone("0");
			}
			MerchantXPlatform merchantX = new MerchantXPlatform();
			merchantX.setMrchno(reportShow.getMerchantId());
			if (reportShow.getTypeYf() == null || reportShow.getCreateDate() == null) {
				//根据商户号去6.222库匹配商户类型typeYf
				List<MerchantXPlatform> listX = merchantXPlatformService.getOneData(merchantX);
				if (listX != null && listX.size() > 0 && reportShow.getTypeYf() == null) {
					reportShow.setTypeYf(listX.get(0).getTypeYf());
					//更新数据库中的商户类型
					merchantXService.updateTypeYf(reportShow.getMerchantId(), reportShow.getTypeYf());
				}
				if (listX != null && listX.size() > 0 && StringUtils.isEmpty(reportShow.getCreateTime())) {
					String addDate = listX.get(0).getAddDate()+"";
					addDate = addDate.substring(0, 8);
					Date parse = null;
					try {
						parse = sdf.parse(addDate);
					} catch (ParseException e) {}
					reportShow.setCreateDate(parse);
					//更新数据库中的商户创建时间
					merchantXService.updateCreateDate(reportShow.getMerchantId(), reportShow.getCreateDate());
				}
			}
			String dictLabel = SysParameterUtils.getSysParameterLabel(reportShow.getTypeYf()+"", "CARDBATCH_MER_TYPE", "");
			reportShow.setMerchantType(dictLabel);
			
			//更改省市区为对应的名称
			reportShow.setTerminalProvince(areaMap.get(reportShow.getTerminalProvince()));
			reportShow.setTerminalCity(areaMap.get(reportShow.getTerminalCity()));
			reportShow.setTerminalZone(areaMap.get(reportShow.getTerminalZone()));
		}
		model.addAttribute("reportShow", reportShow);
		return "modules/pos/edcReportShowForm";
	}
	
	/**
	 * 导出Excel
	 * @return list界面
	 */
	@RequiresPermissions("pos:edcReportShow:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ReportShow reportShow, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Map<String,String> map = new HashMap<>();
		//获取省市区集合
		Map<String, String> areaMap = getAreaMap();
		//每Excel多少条数据
		//int count = 100;
		
		//List<ReportShow> excelData = new ArrayList<>();
		try {
            String fileName = "商户综合信息"+DateUtils.getDate()+".xlsx";
            String contractflag = reportShow.getContractflag();
    		if (StringUtils.isNotBlank(contractflag)) {
    			if ("1".equals(contractflag)) {
    				//已到期
    				reportShow.setContractTime(this.getNowDt("yyyyMMdd", 0));
    			}else {
    				//未到期或者将续约
    				reportShow.setContractTime(this.getAfterMonthDay("yyyyMMdd"));
    			}
    		}
    		Map<String,String> mccMap = this.getMap("mcc");
			List<PManager> roleId = pManagerService.findListByRoleId(null,reportShow.getMerchantArea());
			//匹配数据
			for (PManager pManager : roleId) {
				map.put(pManager.getId(), pManager.getManagerName());
			}
			
			//当查询条件费率为空时
			if (StringUtils.isEmpty(reportShow.getTimezone())) {
				List<ReportShow> list = reportShowService.findList(reportShow);
				for (ReportShow rs : list) {
					TermposXBak termposX = new TermposXBak();
					termposX.setTermcode(rs.getTerminalId());
					termposX.setMrchno(rs.getMerchantId());
					// 根据商户号和终端号,匹配费率
					List<TermposXBak> oneData = termposXBakService.getOneData(termposX);
					if (oneData != null && oneData.size() > 0) {
						termposX = oneData.get(0);
						if (StringUtils.isEmpty(termposX.getTimezone())) {
							rs.setTimezone("0");
						} else {
							rs.setTimezone(termposX.getTimezone());
						}
					}else {
						rs.setTimezone("0");
					}
					MerchantXPlatform merchantX = new MerchantXPlatform();
					merchantX.setMrchno(rs.getMerchantId());
					if (rs.getTypeYf() == null || rs.getCreateDate() == null) {
						//根据商户号去6.222库匹配商户类型typeYf
						List<MerchantXPlatform> listX = merchantXPlatformService.getOneData(merchantX);
						if (listX != null && listX.size() > 0 && rs.getTypeYf() == null) {
							rs.setTypeYf(listX.get(0).getTypeYf());
							//更新数据库中的商户类型
							merchantXService.updateTypeYf(rs.getMerchantId(), rs.getTypeYf());
						}
						if (listX != null && listX.size() > 0 && StringUtils.isEmpty(rs.getCreateTime())) {
							String addDate = listX.get(0).getAddDate()+"";
							addDate = addDate.substring(0, 8);
							Date parse = null;
							try {
								parse = sdf.parse(addDate);
							} catch (ParseException e) {}
							rs.setCreateDate(parse);
							//更新数据库中的商户创建时间
							merchantXService.updateCreateDate(rs.getMerchantId(), rs.getCreateDate());
						}
					}
					String dictLabel = SysParameterUtils.getSysParameterLabel(rs.getTypeYf()+"", "CARDBATCH_MER_TYPE", "");
					rs.setMerchantType(dictLabel);
					//匹配门店负责人
					rs.setStoreManager(map.get(rs.getStoreManager()));
					//匹配商户顾问
					rs.setMerchantAdvisor(map.get(rs.getMerchantAdvisor()));
					//匹配MCC
					rs.setMcc(mccMap.get(rs.getMcc()));
					//更改省市区为对应的名称
					rs.setTerminalProvince(areaMap.get(rs.getTerminalProvince()));
					rs.setTerminalCity(areaMap.get(rs.getTerminalCity()));
					rs.setTerminalZone(areaMap.get(rs.getTerminalZone()));
				}
				new ExportExcel("商户综合信息", ReportShow.class).setDataList(list).write(request, response, fileName).dispose();
			    return null;
			}
			//当查询条件费率不为空时
			List<ReportShow> queryList = new ArrayList<ReportShow>();
			//得到满足查询条件（除了费率）的所有数据
			List<ReportShow> findAllList = reportShowService.findAllList(reportShow);

			//得到所有与费率相匹配的数据
			if(findAllList != null && findAllList.size() > 0){
				queryList = getAllList(reportShow, mccMap, queryList, findAllList, areaMap);
			}
			new ExportExcel("商户综合信息", ReportShow.class).setDataList(queryList).write(request, response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户综合信息！失败信息：" + e.getMessage());
		}
		return "redirect:"+"/pos/edcReportShow/list?repage";
    }
	
	/**
	 * 根据类型获取MAP
	 * @return
	 */
	private Map<String,String> getMap(String mapType){
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
	
	private List<ReportShow> getAllList(ReportShow reportShow, Map<String, String> mccMap, List<ReportShow> queryList,
			List<ReportShow> findAllList, Map<String, String> areaMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for(int i = 0; i < findAllList.size(); i++){
			ReportShow show = findAllList.get(i);
			TermposXBak termposX = new TermposXBak();
			termposX.setTermcode(show.getTerminalId());
			termposX.setMrchno(show.getMerchantId());
			// 根据商户号和终端号,匹配费率
			List<TermposXBak> oneData = termposXBakService.getOneData(termposX);
			if (oneData != null && oneData.size() > 0) {
				termposX = oneData.get(0);
				if (StringUtils.isEmpty(termposX.getTimezone())) {
					show.setTimezone("0");
				} else {
					show.setTimezone(termposX.getTimezone());
				}
			}else {
				show.setTimezone("0");
			}
			if (reportShow.getTimezone().equals(show.getTimezone())) {
				show.setMcc(mccMap.get(show.getMcc()));
				MerchantXPlatform merchantX = new MerchantXPlatform();
				merchantX.setMrchno(show.getMerchantId());
				if (show.getTypeYf() == null || show.getCreateDate() == null) {
					//根据商户号去6.222库匹配商户类型typeYf
					List<MerchantXPlatform> listX = merchantXPlatformService.getOneData(merchantX);
					if (listX != null && listX.size() > 0 && show.getTypeYf() == null) {
						show.setTypeYf(listX.get(0).getTypeYf());
						//更新数据库中的商户类型
						merchantXService.updateTypeYf(show.getMerchantId(), show.getTypeYf());
					}
					if (listX != null && listX.size() > 0 && StringUtils.isEmpty(show.getCreateTime())) {
						String addDate = listX.get(0).getAddDate()+"";
						addDate = addDate.substring(0, 8);
						Date parse = null;
						try {
							parse = sdf.parse(addDate);
						} catch (ParseException e) {}
						show.setCreateDate(parse);
						merchantXService.updateCreateDate(show.getMerchantId(), show.getCreateDate());
					}
				}
				String dictLabel = SysParameterUtils.getSysParameterLabel(show.getTypeYf()+"", "CARDBATCH_MER_TYPE", "");
				show.setMerchantType(dictLabel);
				if (areaMap != null) {
					//更改省市区为对应的名称
					show.setTerminalProvince(areaMap.get(show.getTerminalProvince()));
					show.setTerminalCity(areaMap.get(show.getTerminalCity()));
					show.setTerminalZone(areaMap.get(show.getTerminalZone()));
				}
				//费率匹配，添加
				queryList.add(show);
			}
		}
		
		return queryList;
	}
	
	private List<ReportShow> getPageList(Map<String, String> mccMap, List<ReportShow> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for(ReportShow rShow:list){
			TermposXBak termposX = new TermposXBak();
			termposX.setTermcode(rShow.getTerminalId());
			termposX.setMrchno(rShow.getMerchantId());
			// 根据商户号和终端号,匹配费率
			List<TermposXBak> oneData = termposXBakService.getOneData(termposX);
			if (oneData != null && oneData.size() > 0) {
				termposX = oneData.get(0);
				if (StringUtils.isEmpty(termposX.getTimezone())) {
					rShow.setTimezone("0");
				} else {
					rShow.setTimezone(termposX.getTimezone());
				}
			}else {
				rShow.setTimezone("0");
			}
			rShow.setMcc(mccMap.get(rShow.getMcc()));
			
			MerchantXPlatform merchantX = new MerchantXPlatform();
			merchantX.setMrchno(rShow.getMerchantId());
			if (rShow.getTypeYf() == null || rShow.getCreateDate() == null) {
				//根据商户号去6.222库匹配商户类型typeYf
				List<MerchantXPlatform> listX = merchantXPlatformService.getOneData(merchantX);
				if (listX != null && listX.size() > 0 && rShow.getTypeYf() == null) {
					rShow.setTypeYf(listX.get(0).getTypeYf());
					//更新数据库中的商户类型
					merchantXService.updateTypeYf(rShow.getMerchantId(), rShow.getTypeYf());
				}
				if (listX != null && listX.size() > 0 && StringUtils.isEmpty(rShow.getCreateTime())) {
					String addDate = listX.get(0).getAddDate()+"";
					addDate = addDate.substring(0, 8);
					Date parse = null;
					try {
						parse = sdf.parse(addDate);
					} catch (ParseException e) {}
					rShow.setCreateDate(parse);
					merchantXService.updateCreateDate(rShow.getMerchantId(), rShow.getCreateDate());
				}
			}
			
			String dictLabel = SysParameterUtils.getSysParameterLabel(rShow.getTypeYf()+"", "CARDBATCH_MER_TYPE", "");
			rShow.setMerchantType(dictLabel);
		}
		
		return list;
	}
	
	/**
	 * 获取省、市、区集合
	 * @author Administrator
	 *
	 */
	private Map<String, String> getAreaMap() {
		Map<String, String> map = new HashMap<String, String>();
		CortexArea cortexArea=new CortexArea();
		cortexArea.setIsuse("1");
		List<CortexArea> list = cortexAreaService.findList(cortexArea);
		for (CortexArea area : list) {
			map.put(area.getId(), area.getProvinceCity());
		}
		return map;
	}
}