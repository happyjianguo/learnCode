/**
 *包名:cn.yufu.SDMTPlatform.web
 *描述:package cn.yufu.SDMTPlatform.web;
 */
package cn.yufu.SDMTPlatform.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import cn.yufu.SDMTPlatform.commons.Page;
import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.HttpServletRequestUtil;
import cn.yufu.SDMTPlatform.commons.utils.JsonUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.SysDict;
import cn.yufu.SDMTPlatform.service.MerchantSDMTService;
import cn.yufu.cortex.entity.Area;
import cn.yufu.cortex.entity.MerchantXRecord;
import cn.yufu.cortex.entity.NumDescr;
import cn.yufu.cortex.entity.SysParameter;
import cn.yufu.cortex.entity.TBankInfo;
import cn.yufu.cortex.service.AreaService;
import cn.yufu.cortex.service.NumDescrService;
import cn.yufu.cortex.service.SysParameterService;
import cn.yufu.cortex.service.TBankInfoService;
import cn.yufu.posp.entity.PManager;
import cn.yufu.posp.service.PManagerService;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.utils.DateUtils;
import cn.yufu.utils.HttpRequestUtil;
import cn.yufu.utils.MACUtil;
import cn.yufu.utils.MacUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * MerchantChangeSDMTController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月30日
 */
@Controller("sdmtpf.MerchantChangeSDMTController")
public class MerchantChangeSDMTController {
	Log log = Log.getLog(MerchantChangeSDMTController.class);
	
	@Autowired
	@Qualifier("sdmtpf.MerchantSDMTService")
	private MerchantSDMTService MerchantSDMTService;
	
	@Autowired
	@Qualifier("posp.pManagerService")
	private PManagerService pManagerService;
	
	@Autowired
	@Qualifier("cortex.TBankInfoService")	
	private TBankInfoService TBankInfoService;	
	
	@Autowired
	@Qualifier("cortex.NumDescrService")
	private NumDescrService NumDescrService;
	
	@Autowired
	@Qualifier("cortex.SysParameterService")
	private SysParameterService SysParameterService;
	
	@Autowired
	@Qualifier("cortex.AreaService")
	private AreaService AreaService;
	
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return 
	 */
	@RequestMapping(value = { "merchantChangeSDMT/page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, MerchantSDMT queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "sdmtpf/merchantChangeSDMTList";
	}
	
	/**
	 * 分页查询明细
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "merchantChangeSDMT/getList" })
	public String getInfo(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, MerchantSDMT queryModel) {
		// 分页设置
		int count = MerchantSDMTService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<MerchantSDMT> list = MerchantSDMTService.queryList(queryModel, startResult, endResult);
		;
		//防止结算账户开户名和开户银行名称长度太长页面不美观
		/*
		 if(!(null ==list || list.size()==0)){
			for(int i=0; i<list.size(); i++){	
				if(list.get(i).getAccName().length() > 8)//结算账户开户名
					list.get(i).setAccName(list.get(i).getAccName().substring(0,6)+"…");
				;
				if(list.get(i).getBankName().length() > 8)//开户银行名称
					list.get(i).setBankName(list.get(i).getBankName().substring(0,6)+"…");
			}
		}
		*/
		model.addAttribute("merchantSDMTList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		return "sdmtpf/merchantChangeSDMTList";
	}
	
	/**
	 * 跳转修改页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = { "merchantChangeSDMT/edit" })
	public String toEdit(HttpServletRequest req, HttpServletResponse resp, Model model,String merchantId) throws FileNotFoundException, IOException {
		log.info("编辑商户拓展信息");
//		MerchantSDMT info = MerchantSDMTService.queryInfo(merchantId);
//		String merchantArea = info.getMerchantArea();
//		model.addAttribute("info", info);
//		//设置区域下拉框 MERCHANT_AREA sys_dict	
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
////		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
//		model.addAttribute("merchantAreaList", merchantAreaList);
//		//设置预约方式下拉框 RENEWAL_TYPE sys_dict
//		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("RENEWAL_TYPE"));
////		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("RENEWAL_TYPE"));
//		model.addAttribute("renewalTypeList", renewalTypeList);
//		//设置区域负责人下拉框
//		List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("1", merchantArea);
////		List<PManager> storeManagerList = pManagerService.findListByRoleId("1");
//		model.addAttribute("storeManagerList", storeManagerList);
//		//普通员工
//		List<PManager> managerList = pManagerService.findListByRoleIdAndArea("0", merchantArea);
////		List<PManager> managerList = pManagerService.findListByRoleId("0");
//		model.addAttribute("managerList", managerList);
		
		MerchantSDMT info = MerchantSDMTService.queryInfo(merchantId);
		String merchantArea = info.getMerchantArea();
		model.addAttribute("info", info);
		//设置MCC和地址码表
		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");
		model.addAttribute("mccList", mccList);		
		List<Area> proviceList = AreaService.queryList("0", "1");
		model.addAttribute("proviceList", proviceList);	
		model.addAttribute("cityList", AreaService.queryList(info.getProvince(), "1"));	
		model.addAttribute("zoneList", AreaService.queryList(info.getCityNo(), "1"));	
		List<TBankInfo> bankList=TBankInfoService.queryList("");
		model.addAttribute("bankList", bankList);
		//设置区域下拉框 MERCHANT_AREA sys_dict	
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		model.addAttribute("merchantAreaList", merchantAreaList);
		//商户类型
		List<SysParameter> typeYfList=SysParameterService.queryList("CARDBATCH_MER_TYPE", "");
		model.addAttribute("typeYfList", typeYfList);
		//注册资本金币种
		List<SysParameter> merchant_x_code_typeList=SysParameterService.queryList("MERCHANT_X_CODE_TYPE", "");
		sortStringMethod(merchant_x_code_typeList);
		model.addAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		//设置预约方式下拉框 RENEWAL_TYPE sys_dict
		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("RENEWAL_TYPE"));
//		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("RENEWAL_TYPE"));
		model.addAttribute("renewalTypeList", renewalTypeList);
		//设置区域负责人下拉框
		List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("1", merchantArea);
//		List<PManager> storeManagerList = pManagerService.findListByRoleId("1");
		model.addAttribute("storeManagerList", storeManagerList);
		//普通员工
		List<PManager> managerList = pManagerService.findListByRoleIdAndArea("0", merchantArea);
//		List<PManager> managerList = pManagerService.findListByRoleId("0");
		model.addAttribute("managerList", managerList);
		
		try {
			/**
			 * 时间设置默认值
			 */
			Integer defaultDate = Integer.valueOf(DateUtil.getFormatTimeString(new Date(),"yyyyMMdd"));
			
			if(Strings.isNullOrEmpty(info.getContractStartDate())){ info.setContractStartDate(defaultDate+""); }
			if(Strings.isNullOrEmpty(info.getContractRenewalDate())){ info.setContractRenewalDate(defaultDate+""); }
			if(Strings.isNullOrEmpty(info.getGuaranteeLastDate())){ info.setGuaranteeLastDate(defaultDate+""); }
			if(Strings.isNullOrEmpty(info.getContractEndDate())){ info.setContractEndDate(defaultDate+""); }
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
//		catch (ParseException e) {
//			e.printStackTrace();
//		}
		model.addAttribute("info", info);
		
		return "sdmtpf/merchantChangeSDMTEdit";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortStringMethod(List<SysParameter> list){  
	    Collections.sort(list, new Comparator(){  
	        public int compare(Object o1, Object o2) {  
	        	SysParameter stu1=(SysParameter)o1;  
	        	SysParameter stu2=(SysParameter)o2;  
	            return stu1.getParamId().compareTo(stu2.getParamId());  
	        }             
	    }); 
	}
	public String sedPost(String type) throws FileNotFoundException, IOException {
		String url = "";
//		String url = "http://192.168.10.75:9007/a/sys/dict/getDictList";
        String para = "type="+type;
      //得到动态获取终端主密钥所需参数和URL
		Map<String,String> ms = readProperties();
		if(!ms.isEmpty()){
			url = ms.get("SYS_DICT_HTTP_URL");
		}else{
			url = "";
		}
		return HttpRequestUtil.sendPost(url, para, false);
	}
	/**
	 * 从配置文件中读取发送http所需URL和param
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,String> readProperties() throws FileNotFoundException, IOException {
		
		Map<String,String> map = new HashMap<String,String>();
		
		Properties pps = new Properties();
		
		String ur = this.getClass().getResource("/").getPath();
//		ur = ur.substring(1);
		
		log.info("properties文件地址："+ur);
		
        pps.load(new FileInputStream(ur+"sendHttpParam.properties"));
        
        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
        while(enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = pps.getProperty(strKey);
            if(!Strings.isNullOrEmpty(strKey)){
            	map.put(strKey, strValue);
            }
        }
        return map;
     }
	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "merchantChangeSDMT/editsaave" }, method = { RequestMethod.POST })
	@ResponseBody
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model,MerchantSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MerchantSDMT merchant = MerchantSDMTService.queryInfo(info.getMerchantId());
			MerchantXRecord merchantXRecord = new MerchantXRecord();
			
//			如果是已同步商户
			if("1".equals(merchant.getSdFlag())){
				
				merchantXRecord = this.switchLog(req,resp,merchant,info);
				
				//更新中间表
				info.setIsTerminalAddFlag(" ");
				map = MerchantSDMTService.extendEdit(info,merchantXRecord);
				
			}else{
				
				map = MerchantSDMTService.edit(info);
				
			}
		} catch (Exception e) {
			log.info("修改商户商户扩展信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "修改商户扩展信息失败。");
		}
		return JsonUtil.getJsonString(map);

	}

	/**
	 * 日志参数拼装
	 * @param req
	 * @param resp
	 * @param obj
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private MerchantXRecord switchLog(HttpServletRequest req, HttpServletResponse resp, MerchantSDMT...obj) throws FileNotFoundException, IOException {
		MerchantXRecord merchantXRecord = new MerchantXRecord();
		
		merchantXRecord.setMrchName(obj[0].getMrchtName());
		merchantXRecord.setMrchno(obj[0].getMerchantId());
		merchantXRecord.setCreateTime(DateUtils.formatDateTime(new Date()));
		//获取远程客户端信息
		String remoteAddr = this.getRemoteAddr(req);
		merchantXRecord.setRemoteAddr(remoteAddr);
		merchantXRecord.setUserAgent(req.getHeader("user-agent"));
		merchantXRecord.setRemoteMac(this.getMAC(remoteAddr));
		merchantXRecord.setCreateBy(UserUtils.getPrincipal().getName());
//		商户终端-修改商户信息
		Map<String,String> ms = readProperties();
		if(!ms.isEmpty()){
			merchantXRecord.setModifyModule(ms.get("MERCHANT_TERMINAL_MODIFY_MODULE_NAME"));
		}else{
			merchantXRecord.setModifyModule(" ");
		}
		//修改模块
//		merchantXRecord.setModifyModule("商户终端-修改商户信息");
		//原数据
		merchantXRecord.setOriginalRecord(getJsonString(obj[0],new JsonConfig()));
		//修改记录
		merchantXRecord.setModifyRecord(getJsonString(obj[1],new JsonConfig()));
		return merchantXRecord;
	}
	/**
	 * 取json字符串
	 * 
	 * @param obj
	 * @param cfg
	 * @return
	 */
	public static String getJsonString(Object obj, JsonConfig cfg) {
		if (obj != null) {
			cfg.setExcludes(new String[]{"createBy","currentUser","page"});
			JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
			return jsonObject.toString();
		}
		return "{}";
	}
	/**
	 * 获得用户远程地址
	 */
	public String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (!Strings.isNullOrEmpty(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (!Strings.isNullOrEmpty(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (!Strings.isNullOrEmpty(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	/**
	 * 取MAC字符串
	 * 
	 * @param obj
	 * @param cfg
	 * @return
	 */
	private  String getMAC(String remoteAddr) {
		String os = MacUtils.getOSName();
		if (os.startsWith("windows")) {
			return MACUtil.getMACAddressInWin(remoteAddr);
		} else if (os.startsWith("linux")) {
			return MACUtil.getMACAddressInLinux(remoteAddr);
		} 
		return null;
	}
}
