package cn.yufu.SDMTPlatform.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;

import cn.yufu.SDMTPlatform.commons.Page;
import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.HttpServletRequestUtil;
import cn.yufu.SDMTPlatform.commons.utils.JsonUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantExcel;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.SysDict;
import cn.yufu.SDMTPlatform.service.MerchantSDMTService;
import cn.yufu.cortex.dao.MerchantMapper;
import cn.yufu.cortex.dao.MerchantXMapper;
import cn.yufu.cortex.dao.MrchAccXMapper;
import cn.yufu.cortex.entity.Area;
import cn.yufu.cortex.entity.Merchant;
import cn.yufu.cortex.entity.MerchantExample;
import cn.yufu.cortex.entity.MerchantX;
import cn.yufu.cortex.entity.MerchantXExample;
import cn.yufu.cortex.entity.MerchantXRecord;
import cn.yufu.cortex.entity.MrchAccX;
import cn.yufu.cortex.entity.MrchAccXExample;
import cn.yufu.cortex.entity.NumDescr;
import cn.yufu.cortex.entity.SysParameter;
import cn.yufu.cortex.entity.TBankInfo;
import cn.yufu.cortex.service.AreaService;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.cortex.service.NumDescrService;
import cn.yufu.cortex.service.SysParameterService;
import cn.yufu.cortex.service.TBankInfoService;
import cn.yufu.cortexBak.service.MerchantBakService;
import cn.yufu.posp.dao.MerchantBaseMapper;
import cn.yufu.posp.dao.MerchantExtraMapper;
import cn.yufu.posp.entity.MerchantBase;
import cn.yufu.posp.entity.MerchantExtra;
import cn.yufu.posp.entity.PManager;
import cn.yufu.posp.service.MerchantBaseService;
import cn.yufu.posp.service.PManagerService;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.utils.DateUtils;
import cn.yufu.utils.HttpRequestUtil;
import cn.yufu.utils.MACUtil;
import cn.yufu.utils.MacUtils;
import cn.yufu.utils.POIUtils;
import cn.yufu.utils.excel.ImportExcel;
import cn.yufu.yufuOld.service.RUService;
import cn.yufu.yufuOld1.service.RU1Service;
import cn.yufu.yufuOld2.service.RU2Service;
import cn.yufu.yufuOldSwitch.service.RUSwitchService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("deprecation")
@Controller("sdmtpf.MerchantSDMTController")
public class MerchantSDMTController {
	Log log = Log.getLog(MerchantSDMTController.class);

	@Autowired
	@Qualifier("sdmtpf.MerchantSDMTService")
	private MerchantSDMTService MerchantSDMTService;
	@Autowired
	@Qualifier("cortex.AreaService")
	private AreaService AreaService;
	@Autowired
	@Qualifier("cortex.NumDescrService")
	private NumDescrService NumDescrService;
	@Autowired
	@Qualifier("cortex.SysParameterService")
	private SysParameterService SysParameterService;
	@Autowired
	@Qualifier("posp.MerchantBaseService")	
	private MerchantBaseService MerchantBaseService;
	@Autowired
	@Qualifier("cortexBak.MerchantBakService")	
	private MerchantBakService MerchantBakService;
	@Autowired
	@Qualifier("yufuOld.RUService")	
	private RUService RUService;
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService CortexService;
	@Autowired
	@Qualifier("yufuOldSwitch.RUSwitchService")	
	private RUSwitchService RUSwitchService;
	@Autowired
	@Qualifier("yufuOld1.RU1Service")	
	private RU1Service RU1Service;	
	@Autowired
	@Qualifier("yufuOld2.RU2Service")	
	private RU2Service RU2Service;
	@Autowired
	@Qualifier("cortex.TBankInfoService")	
	private TBankInfoService TBankInfoService;	
	
	@Autowired
	@Qualifier("posp.pManagerService")
	private PManagerService pManagerService;
	
	@Autowired
	@Qualifier("cortex.MerchantDao")
	private MerchantMapper MerchantDao;
	@Autowired
	@Qualifier("cortex.MrchAccXDao")
	private MrchAccXMapper MrchAccXDao;
	@Autowired
	@Qualifier("cortex.MerchantXDao")
	private MerchantXMapper MerchantXDao;
	@Autowired
	@Qualifier("posp.MerchantBaseDao")
	private MerchantBaseMapper MerchantBaseDao;
	@Autowired
	@Qualifier("posp.MerchantExtraDao")
	private MerchantExtraMapper MerchantExtraDao;
	
	/*********************** EXCEL导出模板 ********************************/
	private HSSFWorkbook workbook = null;
	private HSSFCellStyle titleStyle = null;
	private HSSFCellStyle dataStyle = null;
	private HSSFCellStyle headStyle = null;
	
	/**
	 * 导入商户数据
	 * TODO
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("SDMTPlatform:merchant:add")
	@RequestMapping(value = "/merchantSDMT/import", method = RequestMethod.POST)
	public String importFile(HttpServletRequest req, HttpServletResponse resp, MultipartFile file,RedirectAttributes redirectAttributes) {
		log.debug("批量导入商户数据");
		/***
		 * 1,解析Excel 得到List<MerchantExcel>
		 * 2，遍历，并处理list｛
		 * 		1，判断MerchantExcel是否为空
		 * 		2，判断id是否为空
		 * 		3，检验Excel中的数据是否合法
		 * 		4，MerchantExcel转MerchantSDMT
		 * 		5，入库｛
		 * 				1，如果t_merchant存在，其他三个库不都存在，(?SD_FLAG为1，则更新stdmpt表和fkuit表（记日志）；SD_FLAG不为1，则更新stdmpt表)
		 * 				2，如果t_merchant存在，其他三个库都不存在，则更新stdmpt表
		 * 				3，如果t_merchant存在，其他三个库都存在，则更新stdmpt表和fkuit表(记日志）
		 * 				4，如果t_merchant不存在，其他三个库不都存在，则不新增（*）
		 * 				5，如果t_merchant不存在，其他三个库都存在，则只新增t_merchant表并将同步标志置为1（记日志）
		 * 				6，如果t_merchant不存在，其他三个库都不存在，则正常新增
		 * 				7，入库

		 * 				｝
		 * ｝
		 * 3、提示批量导入商户结果
		 */
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MerchantExcel> list = ei.getDataList(MerchantExcel.class);
			MerchantSDMT merchant = null;
			for (int i=0; i < list.size(); i++) {
//			for (MerchantExcel entity : list) {
				MerchantExcel entity = list.get(i);
				try {
					//是否为空，如果没有，直接跳过
					if (valiBoolean(entity)){
						Map<String,Object> maps = checkMerchantExcel(entity);
						boolean flags = (Boolean) maps.get("flag");
						StringBuilder checkcontext = (StringBuilder) maps.get("content");
						log.debug("判断输入值是否合法");
						//判断输入值是否合法
						if(flags){
							//合法
							merchant = excelSwitchEntity(entity);
							
							String merchantId = merchant.getMerchantId();
							Map<String,Object> map = new HashMap<String,Object>();
							
							MerchantXRecord merchantXRecord = null;
							
							boolean flagSdmtpf = false;	//75临时表标志
							boolean flagFkqz = false;//福卡前置标志
							boolean flagX = false;//X平台标志
							boolean flagLfk = false;//福卡系统标志

							if (merchantId != null && !"".equals(merchantId)) {
								Map<String,Boolean> flagsmap = isExistMid(merchantId);
								
								flagSdmtpf = flagsmap.get("flagSdmtpf");
								flagFkqz = flagsmap.get("flagFkqz");
								flagX = flagsmap.get("flagX");
								flagLfk = flagsmap.get("flagLfk");
								
								if(flagSdmtpf){
					//				t_merchant中存在
									
									if(flagFkqz && flagX && flagLfk){
					//					其他三个库都存在
					//					则更新stdmpt表和fkuit表
//										equalsInfo(merchant, merchantId);//设置标志位
										merchant.setIsTerminalAddFlag(" ");
										merchantXRecord = this.switchLog(req,resp,MerchantSDMTService.queryInfo(merchantId),merchant);
										map = MerchantSDMTService.extendEdit(merchant,merchantXRecord);
									}else{
										if(flagFkqz || flagX || flagLfk){
						//					其他三个库不都存在
											if(flagFkqz){
						//						更新stdmpt表和fkuit表
												merchantXRecord = this.switchLog(req,resp,MerchantSDMTService.queryInfo(merchantId),merchant);
												map = MerchantSDMTService.extendEdit(merchant,merchantXRecord);
											}else{
						//						更新stdmpt表
												map = MerchantSDMTService.edit(merchant);
											}
										}
									}
									
									if(flagFkqz == false && flagX == false && flagLfk == false){
					//					其他三个库都不存在
					//					则更新stdmpt表
										map = MerchantSDMTService.edit(merchant);
									}
								}else{
					//				t_merchant中不存在
									
									if(flagFkqz && flagX && flagLfk){
					//					其他三个库都存在
					//					则新增stdmpt表，并设置标志位
										this.equalsInfo(merchant, merchantId);//设置标志位
										merchant.setIsTerminalAddFlag(" ");
										merchantXRecord = this.switchLog(req,resp,this.toMerchant(merchantId),merchant);
										map = MerchantSDMTService.save(merchant,merchantXRecord,true);
									}else{
										if(flagFkqz || flagX || flagLfk){
											//					其他三个库不都存在
											//不做处理
											map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
											map.put(SysConst.RESULT_MSG, "保存商户失败，三个系统中没有同时存在。");
										}
									}
									
									
									if(flagFkqz == false && flagX == false && flagLfk == false){
					//					其他三个库都不存在
					//					则正常新增stdmpt表
										map = MerchantSDMTService.save(merchant,merchantXRecord,false);
									}
								}
//								if(MerchantBaseService.queryList(merchantId).size() > 0 
//										&& this.MerchantBakService.queryList(merchantId).size() > 0
//										&& (this.RUSwitchService.queryList(merchantId).size() > 0
//												&& this.RUService.queryList(merchantId).size() > 0
//												&& this.RU1Service.queryList(merchantId).size() > 0
//												&& this.RU2Service.queryList(merchantId).size() > 0) ){
//									equalsInfo(merchant, merchantId);
//									
//									//插入
//									map = MerchantSDMTService.save(merchant,merchantXRecord,true);
//									
//								}else{
//									//插入
//									map = MerchantSDMTService.save(merchant,merchantXRecord,false);
//								}
							}
							String flag = (String) map.get(SysConst.RESULT);
							/**
							 * =0,成功
							 * =-1,失败
							 */
							if("-1".equals(flag)){
								//商户已存在
								failureMsg.append("\n商户编号" + entity.getMerchantId()+ " 已存在; ");
								failureNum++;
							}if("0".equals(flag)){
								//成功
								successNum++;
							}
						}else{
							//不合法
							failureMsg.append("\n商户编号:" + entity.getMerchantId()+ ",失败原因:"+checkcontext.toString());
							failureNum++;
						}
					}else{
						if(Strings.isNullOrEmpty(entity.getMerchantId())){
							failureMsg.append("\n失败原因:第"+ (i+3)+"行，商户编号为必填项");
							failureNum++;
						}else{
							failureMsg.append("\n商户编号:" + entity.getMerchantId()+ ",失败原因:所有项均为必填项 ");
							failureNum++;
						}
					}
				}catch (Exception ex) {
					failureMsg.append("\n商户编号 " + entity.getMerchantId() + " 导入失败："+ ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条商户信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条商户信息"+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入商户信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:/merchantSDMT/page";
//		return "redirect:merchantSDMT/page";
	}
	/**
	 * 判断obj对象属性是否为空,通过name只能过滤不需要验空属性
	 * @param obj name【】
	 * @return
	 * @throws IllegalAccessException
	 */
	public boolean checkObjFieldIsNull(Object obj,String ...name) throws IllegalAccessException {
	    boolean flag = false;
        List<String> list = java.util.Arrays.asList(name);
	    for(Field f : obj.getClass().getDeclaredFields()){
	        f.setAccessible(true);
        	if(!list.contains(f.getName()))
	        {
        		if(f.get(obj) == null){
	        		flag = true;
	        		return flag;
	        	}
	        }
	    }
	    return flag;
	}

	/*********************************************************1*****************************************************/
	/**
	 * 判断MerchantExcel是否为空
	 * @param entity
	 * @return
	 */
	public Boolean valiBoolean(MerchantExcel entity){ 
		log.debug("验证字段是否为空");
		if(null == entity){
			return false;
		}
		List<String> li = new LinkedList<String>();
		li.add(entity.getMerchantId());
		li.add(entity.getMrchtName());
		li.add(entity.getMerchantEname());
		li.add(entity.getAbbrCname());
		li.add(entity.getAbbrEname());
		li.add(entity.getMccId());
		li.add(entity.getAddress());
		li.add(entity.getProvince());
		li.add(entity.getCityNo());
		li.add(entity.getZone());
		li.add(entity.getTelephone());
		li.add(entity.getManager());
		li.add(entity.getAccName());
		li.add(entity.getAccNo());
		li.add(entity.getIsBjAcct());
		li.add(entity.getBis());
		li.add(entity.getBankName());
		li.add(entity.getAccNickName());
		li.add(entity.getShortNickName());
		li.add(entity.getBankNo());
		li.add(entity.getIndividual());
		li.add(entity.getLastSettleDate().toString());
		li.add(entity.getTypeYf().toString());
		li.add(entity.getAgent());
		li.add(entity.getIdType().toString());
		li.add(entity.getIdNo());
		li.add(entity.getIdValidity().toString());
		li.add(entity.getLegalRep());
		li.add(entity.getLrIdType().toString());
		li.add(entity.getLrIdNo());
		li.add(entity.getLrIdValidity().toString());
		li.add(entity.getBusLicNo());
		li.add(entity.getBusLicValidity().toString());
		li.add(entity.getTaxId());
		li.add(entity.getTaxIdValidity().toString());
		li.add(entity.getOrgId());
		li.add(entity.getOrgValidity().toString());
		li.add(entity.getEnterpriseNo());
		li.add(entity.getContractStartDate());
		li.add(entity.getContractRenewalDate());
		li.add(entity.getMerchantCompanyName());
		li.add(entity.getRenewalType());
		li.add(entity.getGuaranteeLastDate());
		li.add(entity.getGuaranteeAmt().toString());
		li.add(entity.getMarketContactPerson());
		li.add(entity.getMarketContactMobile());
//		li.add(entity.getFinancialContactPerson());
//		li.add(entity.getFinancialContactMobile());
		li.add(entity.getMerchantDeposit().toString());
		li.add(entity.getMerchantArea());
		li.add(entity.getStoreManager());
		li.add(entity.getMerchantAdvisor());
		li.add(entity.getContractEndDate());
//		li.add(entity.getMerchant_x_acc_type1());
//		li.add(entity.getMerchant_x_code());
//		li.add(entity.getMerchant_x_reg_amt().toString());
//		li.add(entity.getMerchant_x_type());
		
		for(int i=0;i<li.size();i++){
			if(Strings.isNullOrEmpty(li.get(i))){
				return false;
			}
		}
		return true;
	}
	/*********************************************************1*****************************************************/
	
	/*********************************************************2*****************************************************/
	public MerchantSDMT excelSwitchEntity(MerchantExcel entity){ 
		MerchantSDMT merchant = new MerchantSDMT();
		merchant.setMerchantId(entity.getMerchantId().trim());
		merchant.setMrchtName(entity.getMrchtName().trim());
		merchant.setMerchantEname(entity.getMerchantEname().trim());
		merchant.setAbbrCname(entity.getAbbrCname().trim());
		merchant.setAbbrEname(entity.getAbbrEname().trim());
		merchant.setMccId(entity.getMccId().split("_")[1]);
		merchant.setAddress(entity.getAddress().trim());
		merchant.setProvince(entity.getProvince().split("_")[1]);
		merchant.setCityNo(entity.getCityNo().split("_")[1]);
		merchant.setZone(entity.getZone().split("_")[1]);
		merchant.setTelephone(entity.getTelephone().trim());
		merchant.setManager(entity.getManager().trim());
		merchant.setAccName(entity.getAccName().trim());
		merchant.setAccNo(entity.getAccNo().trim());
		merchant.setIsBjAcct(entity.getIsBjAcct().split("_")[1]);
		merchant.setBis(entity.getBis().split("_")[1]);
		merchant.setBankName(entity.getBankName().trim());
		merchant.setAccNickName(entity.getAccNickName().trim());
		merchant.setShortNickName(entity.getShortNickName().trim());
		merchant.setBankNo(entity.getBankNo().trim());
		merchant.setIndividual(entity.getIndividual().split("_")[1]);
		merchant.setLastSettleDate(Integer.parseInt(entity.getLastSettleDate()));
		merchant.setTypeYf(Integer.parseInt(entity.getTypeYf().split("_")[1]));
		merchant.setAgent(entity.getAgent().trim());
		merchant.setIdType(Integer.parseInt(entity.getIdType().split("_")[1]));
		merchant.setIdNo(entity.getIdNo().trim());
		merchant.setIdValidity(Integer.parseInt(entity.getIdValidity().trim()));
		merchant.setLegalRep(entity.getLegalRep().trim());
		merchant.setLrIdType(Integer.parseInt(entity.getLrIdType().split("_")[1]));
		merchant.setLrIdNo(entity.getLrIdNo().trim());
		merchant.setLrIdValidity(Integer.parseInt(entity.getLrIdValidity().trim()));
		merchant.setBusLicNo(entity.getBusLicNo().trim());
		merchant.setBusLicValidity(Integer.parseInt(entity.getBusLicValidity().trim()));
		merchant.setTaxId(entity.getTaxId().trim());
		merchant.setTaxIdValidity(Integer.parseInt(entity.getTaxIdValidity().trim()));
		merchant.setOrgId(entity.getOrgId().trim());
		merchant.setOrgValidity(Integer.parseInt(entity.getOrgValidity().trim()));
		merchant.setEnterpriseNo(entity.getEnterpriseNo().trim());
		merchant.setContractStartDate(entity.getContractStartDate().trim());
		merchant.setContractRenewalDate(entity.getContractRenewalDate().trim());
		merchant.setMerchantCompanyName(entity.getMerchantCompanyName().trim());
		merchant.setRenewalType(entity.getRenewalType().split("_")[1]);
		merchant.setGuaranteeLastDate(entity.getGuaranteeLastDate().trim());
		merchant.setGuaranteeAmt(new BigDecimal(entity.getGuaranteeAmt().trim()));
		merchant.setMarketContactPerson(entity.getMarketContactPerson().trim());
		merchant.setMarketContactMobile(entity.getMarketContactMobile().trim());
//		merchant.setFinancialContactPerson(entity.getFinancialContactPerson().trim());
//		merchant.setFinancialContactMobile(entity.getFinancialContactMobile().trim());
		merchant.setMerchantDeposit(new BigDecimal(entity.getMerchantDeposit().trim()));
		merchant.setMerchantArea(entity.getMerchantArea().split("_")[1]);
		merchant.setMerchantAdvisor(entity.getMerchantAdvisor().split("_")[1]);
		merchant.setStoreManager(entity.getStoreManager().split("_")[1]);
		merchant.setContractEndDate(entity.getContractEndDate().trim());
		merchant.setFmrchno(entity.getFmrchno().trim());
		merchant.setManName(entity.getManName().trim());
		merchant.setIdType1(new BigDecimal(entity.getIdType1().split("_")[1]));
		merchant.setIdNo1(entity.getIdNo1().trim());
		merchant.setIdDeadline1(new BigDecimal(entity.getIdDeadline1().trim()));
		merchant.setAccXName(entity.getAccXName());
		merchant.setAddDate(Long.parseLong(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())));
		String merchant_x_reg_amt = StringUtils.isNotBlank(entity.getMerchant_x_reg_amt()) ? entity.getMerchant_x_reg_amt() : "0";
		merchant.setMerchant_x_reg_amt(new BigDecimal(merchant_x_reg_amt));
		if(Strings.isNullOrEmpty(entity.getMerchant_x_operate())){
			merchant.setMerchant_x_operate("");
		}else{
			merchant.setMerchant_x_operate(entity.getMerchant_x_operate());
		}
		if(Strings.isNullOrEmpty(entity.getMerchant_x_type().toString())){
			merchant.setMerchant_x_type("12");
		}else{
			merchant.setMerchant_x_type(entity.getMerchant_x_type().split("_")[1]);
		}
		if(Strings.isNullOrEmpty(entity.getMerchant_x_code().toString())){
			merchant.setMerchant_x_code("RMB");
		}else{
			merchant.setMerchant_x_code(entity.getMerchant_x_code().split("_")[1]);
		}
		if(Strings.isNullOrEmpty(entity.getMerchant_x_acc_type1().toString())){
			merchant.setMerchant_x_acc_type1("12");
		}else{
			merchant.setMerchant_x_acc_type1(entity.getMerchant_x_acc_type1().split("_")[1]);
		}
		return merchant;
	}
	/*********************************************************2*****************************************************/
	
	/*********************************************************3*****************************************************/
	public Map<String,Object> checkMerchantExcel(MerchantExcel entity){
		Map<String,Object> map = new HashMap<String,Object>();
		Boolean flag = true;
		StringBuilder content = new StringBuilder();
		//按照页面输入进行校验,所有下拉框均不校验
		/**
		 * 父商户号 
		 * 1、判断是否为空
		 * 		不空
		 * 		1、判断是否为15位
		 * 		2、判断是否为数字
		 * 		3、判断是否为商户号
		 */
		if(!(Strings.isNullOrEmpty(entity.getFmrchno().trim()))){
			if(isNumeric(entity.getFmrchno().trim())){
				if(!(equalNum(entity.getFmrchno().trim(),15))){
					content.append("父商户号必须为15位数字，");
					flag = false;
				}else{
					if(!(this.MerchantBakService.queryList(entity.getFmrchno().trim()).size() > 0)){
						if (!(this.MerchantSDMTService.checkMerchantId(entity.getFmrchno().trim()) > 0)) {
							content.append("父商户编号不存在，");
							flag = false;
						}else{
							content.append("父商户编号在X平台未同步，");
							flag = false;
						}
					}
				}
			}else{
				content.append("父商户号必须为数字，");
				flag = false;
			}
		}
		/**
		 * 商户编号
		 * 1，判断是否为数字
		 * 2，判断是否为15位
		 */
		if(isNumeric(entity.getMerchantId().trim())){
			if(!(equalNum(entity.getMerchantId().trim(),15))){
				content.append("商户编号必须为15位数字，");
				flag = false;
			}else{
//				MerchantSDMT merchant =MerchantSDMTService.queryInfo(entity.getMerchantId().trim());
				boolean flagSdmtpf = false;	//75临时表标志
				boolean flagFkqz = false;//福卡前置标志
				boolean flagX = false;//X平台标志
				boolean flagLfk = false;//福卡系统标志
				if (entity.getMerchantId().trim() != null && !"".equals(entity.getMerchantId().trim())) {
					Map<String,Boolean> flagsmap = isExistMid(entity.getMerchantId().trim());
					flagSdmtpf = flagsmap.get("flagSdmtpf");
					flagFkqz = flagsmap.get("flagFkqz");
					flagX = flagsmap.get("flagX");
					flagLfk = flagsmap.get("flagLfk");
					if(flagSdmtpf){
//							存在 OK
//							return "1";
					}else{
						if(flagFkqz && flagX && flagLfk){
							
						}else{
							if(flagFkqz || flagX || flagLfk){
								if(flagFkqz){
									content.append("商户编号在收单系统单个已存在，");
									flag = false;
								}else if(flagX){
									content.append("该商户编号在X平台单个已存在，");
									flag = false;
								}else if(flagLfk){
									content.append("该商户编号在老福卡系统单个已存在，");
									flag = false;
								}
							}
						}
					}
				}
					// 先校验中间库，中间库不重复再校验其他库,一旦有一个重复则返回1.
//					if (this.MerchantSDMTService.checkMerchantId(entity.getMerchantId().trim()) > 0) {
//						content.append("商户编号已存在，");
//						flag = false;
//					}else{
//						if(MerchantBaseService.queryList(entity.getMerchantId().trim()).size() > 0 
//								&& this.MerchantBakService.queryList(entity.getMerchantId().trim()).size() > 0
//								&& (this.RUSwitchService.queryList(entity.getMerchantId().trim()).size() > 0
//										&& this.RUService.queryList(entity.getMerchantId().trim()).size() > 0
//										&& this.RU1Service.queryList(entity.getMerchantId().trim()).size() > 0
//										&& this.RU2Service.queryList(entity.getMerchantId().trim()).size() > 0) ){
//							
//						}else{
//							// 校验75库，75库不重复再校验其他库,一旦有一个重复则返回2.
//							if (MerchantBaseService.queryList(entity.getMerchantId().trim()).size() > 0) {
//								content.append("商户编号在收单系统或其他系统单个已存在，");
//								flag = false;
//							//校验备库，备库不重复再校验其他库,一旦有一个重复则返回3
//							} else if (this.MerchantBakService.queryList(entity.getMerchantId().trim()).size() > 0) {
//								content.append("该商户编号在X平台或其他系统单个已存在，");
//								flag = false;
//							//校验福卡库，福卡库不重复再校验其他库,一旦有一个重复则返回4
//							} else if (this.RUSwitchService.queryList(entity.getMerchantId().trim()).size() > 0
//									|| this.RUService.queryList(entity.getMerchantId().trim()).size() > 0
//									|| this.RU1Service.queryList(entity.getMerchantId().trim()).size() > 0
//									|| this.RU2Service.queryList(entity.getMerchantId().trim()).size() > 0) {
//								content.append("该商户编号在老福卡系统或其他系统单个已存在，");
//								flag = false;
//							}
//						}
//					} 
//				if(null != merchant){
//					content.append("商户编号已存在，");
//					flag = false;
//				}
			}
		}else{
			content.append("商户编号必须为数字，");
			flag = false;
		}
		//商户名称
		/**
		 * 商户名称
		 * 1,不能大于30位
		 */
		if(!(isNum(entity.getMrchtName().trim(),30))){
			content.append("商户名称不能大于30位，");
			flag = false;
		}
		/**
		 * 商户名称(英)
		 * 1,判断是否为字母
		 * 2,不能大于20位
		 */
		if(isChars(entity.getMerchantEname().trim())){
			if(!(isNum(entity.getMerchantEname().trim(),20))){
				content.append("商户名称(英)不能大于20位，");
				flag = false;
			}
		}else{
			content.append("商户名称(英)必须为英文，");
			flag = false;
		}
		/**
		 * 简称(中)
		 * 1,不能大于4位
		 */
		if(!(isNum(entity.getAbbrCname().trim(),4))){
			content.append("简称(中)不能大于4位，");
			flag = false;
		}
		/**
		 * 简称(英)
		 * 1，是否为英文
		 * 1,不能大于8位
		 */
		if(isChars(entity.getAbbrEname().trim())){
			if(!(isNum(entity.getAbbrEname().trim(),8))){
				content.append("简称(英)不能大于8位，");
				flag = false;
			}
		}else{
			content.append("简称(英)必须为英文，");
			flag = false;
		}
		/**
		 * 地址
		 * 1,不能超过30位
		 */
		if(!(isNum(entity.getAddress().trim(),30))){
			content.append("地址不能大于8位，");
			flag = false;
		}
		/**
		 * 企业经营范围
		 * 1,不能超过165位
		 */
		if(!(isNum(entity.getMerchant_x_operate().trim(),165))){
			content.append("企业经营范围不能大于165位，");
			flag = false;
		}
		/**
		 * 注册资本金
		 * 1，必须是数字
		 * 2，不能超过18位
		 */
		if(!Strings.isNullOrEmpty(entity.getMerchant_x_reg_amt().toString().trim())){
			if(!isNumberMoney(entity.getMerchant_x_reg_amt().toString().trim(),18)){
				content.append("注册资本金为数字，最大18位，且保留两位小数，");
				flag = false;
			}
		}
		/**
		 * 联系电话
		 * 1，必须是数字
		 * 2，不能超过20位
		 */
		if(isNumeric(entity.getTelephone().trim())){
			if(!(isNum(entity.getTelephone().trim(),20))){
				content.append("联系电话不能大于20位数字，");
				flag = false;
			}
		}else{
			content.append("联系电话必须为数字，");
			flag = false;
		}
		/**
		 * 联系人
		 * 1，不能超过4位
		 */
		if(!(isNum(entity.getManager().trim(),4))){
			content.append("联系人不能大于4位，");
			flag = false;
		}
		/**
		 * 结算账户开户名
		 * 1，不能超过30位
		 */
		if(!(isNum(entity.getAccName().trim(),30))){
			content.append("结算账户开户名不能超过30位，");
			flag = false;
		}
		/**
		 * 结算帐号
		 * 1，不能超过20位
		 */
		if(!(isNum(entity.getAccNo().trim(),30))){
			content.append("结算帐号不能超过30位，");
			flag = false;
		}
		/**
		 * 开户银行名称
		 * 1,不能超过30位
		 */
		if(!(isNum(entity.getBankName().trim(),30))){
			content.append("开户银行名称不能超过30位，");
			flag = false;
		}
		/**
		 * 商户账号全称
		 * 1，不能超过15位
		 */
		if(!(isNum(entity.getAccNickName().trim(),30))){
			content.append("商户账号全称不能超过30位，");
			flag = false;
		}
		/**
		 * 商户账号简称
		 * 1，不能超过15位
		 */
		if(!(isNum(entity.getShortNickName().trim(),30))){
			content.append("商户账号简称不能超过30位，");
			flag = false;
		}
		/**
		 * 开户银行行号
		 * 1，不能超过16位
		 */
		if(!(isNum(entity.getBankNo().trim(),16))){
			content.append("开户银行行号不能超过16位，");
			flag = false;
		}
		/**
		 * 上次结算日期
		 */
		if(!(isDate(entity.getLastSettleDate().trim()))){
			content.append("上次结算日期不合法，");
			flag = false;
		}
		/**
		 * 办理手续经办人姓名
		 * 1,不能超过6个字符
		 */
		if(!(isNum(entity.getAgent().trim(),6))){
			content.append("办理手续经办人姓名不能超过6位，");
			flag = false;
		}
		/**
		 * 办理手续经办人证件号码
		 * ,不能超过20位
		 */
		if(!(isNum(entity.getIdNo().trim(),20))){
			content.append("办理手续经办人证件号码不能超过20位，");
			flag = false;
		}
		/**
		 * 办理手续经办人证件有效期
		 */
		if(!(isDate(entity.getIdValidity().trim()))){
			content.append("办理手续经办人证件有效期不合法，");
			flag = false;
		}
		/**
		 * 法定代表人（负责人）姓名
		 * ,不能超过6位
		 */
		if(!(isNum(entity.getLegalRep().trim(),6))){
			content.append("法定代表人（负责人）姓名不能超过6位，");
			flag = false;
		}
		/**
		 * 法定代表人证件号码
		 * ,不能超过20位
		 */
		if(!(isNum(entity.getLrIdNo().trim(),20))){
			content.append("法定代表人证件号码不能超过20位，");
			flag = false;
		}
		/**
		 * 法定代表人证件有效期
		 */
		if(!(isDate(entity.getLrIdValidity().trim()))){
			content.append("法定代表人证件有效期不合法，");
			flag = false;
		}
		/**
		 * 营业执照号
		 * ,不能超过20位
		 */
		if(isNumericChar(entity.getBusLicNo().trim())){
			if(!(isNum(entity.getBusLicNo().trim(),30))){
				content.append("营业执照号不能超过30位，");
				flag = false;
			}
		}else{
			content.append("营业执照号不能有汉字，");
			flag = false;
		}
		
		/**
		 * 营业执照年检时间
		 */
		if(!(isDate(entity.getBusLicValidity().trim()))){
			content.append("营业执照年检时间不合法，");
			flag = false;
		}
		/**
		 * 税务登记证编号
		 * ,不能超过20位
		 */
		if(isNumericChar(entity.getTaxId().trim())){
			if(!(isNum(entity.getTaxId().trim(),30))){
				content.append("税务登记证编号不能超过30位，");
				flag = false;
			}
		}else{
			content.append("税务登记证编号不能有汉字，");
			flag = false;
		}
		
		/**
		 *税务登记证年检时间
		 */
		if(!(isDate(entity.getTaxIdValidity().trim()))){
			content.append("税务登记证年检时间不合法，");
			flag = false;
		}
		/**
		 * 组织机构证编号
		 * ,不能超过20位
		 */
		if(isNumericChar(entity.getOrgId().trim())){
			if(!(isNum(entity.getOrgId().trim(),30))){
				content.append("组织机构证编号不能超过30位，");
				flag = false;
			}
		}else{
			content.append("组织机构证编号不能有汉字，");
			flag = false;
		}
		
		/**
		 *组织机构证年检时间
		 */
		if(!(isDate(entity.getOrgValidity().trim()))){
			content.append("组织机构证年检时间不合法，");
			flag = false;
		}
		/**
		 * 企业账号
		 * ,不能超过20位
		 */
		if(isNumeric(entity.getEnterpriseNo().trim())){
			if(!(isNum(entity.getEnterpriseNo().trim(),28))){
				content.append("企业账号不能超过28位，");
				flag = false;
			}
		}else{
			content.append("企业帐号必须为数字，");
			flag = false;
		}
			
		/**
		 * 合同签订日期
		 */
		if(!(isDate(entity.getContractStartDate().trim()))){
			content.append("合同签订日期不合法，");
			flag = false;
		}
		/**
		 * 合同续约日期
		 */
		if(!(isDate(entity.getContractRenewalDate().trim()))){
			content.append("合同续约日期不合法，");
			flag = false;
		}
		/**
		 * 商户公司名称
		 * 1,不能超过50位
		 */
		if(!(isNum(entity.getMerchantCompanyName().trim(),50))){
			content.append("商户公司名称不能超过50位，");
			flag = false;
		}
		/**
		 * 保函到期日期
		 */
		if(!(isDate(entity.getGuaranteeLastDate().trim()))){
			content.append("保函到期日期不合法，");
			flag = false;
		}
		/**
		 * 保函金额
		 */
		if(isNum(entity.getGuaranteeAmt().trim(),10)){
			if(!(isMoney(entity.getGuaranteeAmt().trim()))){
				content.append("保函金额不合法，");
				flag = false;
			}
		}else{
			content.append("保函金额不合法超过10位，");
			flag = false;
		}
		/**
		 *市场联系人
		 *1，不能超过50 
		 */
		if(!(isNum(entity.getMarketContactPerson().trim(),50))){
			content.append("市场联系人不能超过50，");
			flag = false;
		}
		/**
		 * 市场联系人电话
		 * 1，必须是数字
		 * 2，不能超过14位
		 */
		if(isNumeric(entity.getMarketContactMobile().trim())){
			if(!(isNum(entity.getMarketContactMobile().trim(),14))){
				content.append("市场联系人电话必须为14位数字，");
				flag = false;
			}
		}else{
			content.append("市场联系人电话必须为数字，");
			flag = false;
		}
		/**
		 * 财务联系人
		 * 1，不能超过50
		 */
		/*if(!(isNum(entity.getFinancialContactPerson().trim(),20))){
			content.append("财务联系人不能超过50，");
			flag = false;
		}*/
		/**
		 * 财务联系人电话
		 * 1，必须是数字
		 * 2，不能超过14位
		 */
		/*if(isNumeric(entity.getFinancialContactMobile().trim())){
			if(!(isNum(entity.getFinancialContactMobile().trim(),14))){
				content.append("财务联系人电话必须为14位数字，");
				flag = false;
			}
		}else{
			content.append("财务联系人电话必须为数字，");
			flag = false;
		}*/
		/**
		 * 押金
		 */
		if(isNum(entity.getMerchantDeposit().trim(),10)){
			if(!(isMoney(entity.getMerchantDeposit().trim()))){
				content.append("押金不合法，");
				flag = false;
			}
		}else{
			content.append("押金不能超过10位，");
			flag = false;
		}
		/**
		 * 合同结束日期
		 */
		if(!(isDate(entity.getContractEndDate().trim()))){
			content.append("合同结束日期不合法，");
			flag = false;
		}
		//区域负责人
		try{
			String managerarea =entity.getStoreManager().trim().split("_")[1];
			List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("1", entity.getMerchantArea().split("_")[1]);
			if(!check(storeManagerList,managerarea)){
				content.append("区域负责人不合法，");
				flag = false;
			}
		}catch(Exception eee){
			content.append("区域负责人不合法，");
			flag = false;
		}
		//商户顾问
		try{
			String manageradvarea =entity.getMerchantAdvisor().trim().split("_")[1];
			List<PManager> merchantAdvisorList = pManagerService.findListByRoleIdAndArea("0", entity.getMerchantArea().trim().split("_")[1]);
			if(!check(merchantAdvisorList,manageradvarea)){
				content.append("商户顾问不合法，");
				flag = false;
			}
		}catch(Exception eee){
			content.append("商户顾问不合法，");
			flag = false;
		}
		
		/**
		 * 营业执照名称
		 * 1,不能超过6个字符
		 */
		if(!(isNum(entity.getAccXName().trim(),20))){
			content.append("营业执照名称不能超过20位，");
			flag = false;
		}
		/**
		 * 实际控制人姓名
		 * 1,不能超过6个字符
		 */
		if(!(isNum(entity.getManName().trim(),6))){
			content.append("实际控制人姓名不能超过6位，");
			flag = false;
		}
		/**
		 * 控股股东或实际控制人证件号码
		 * ,不能超过20位
		 */
		if(!(isNum(entity.getIdNo1().trim(),20))){
			content.append("控股股东或实际控制人证件号码不能超过20位，");
			flag = false;
		}
		/**
		 * 控股股东或实际控制人证件有效期 idDeadline1
		 */
		if(!(isDate(entity.getIdDeadline1().trim()))){
			content.append("控股股东或实际控制人证件有效期不合法，");
			flag = false;
		}
		map.put("flag", flag);
		map.put("content",content);
		return map;
	}
	/*********************************************************3*****************************************************/
	/*********************************************************4*****************************************************/
	public  Boolean check(List<PManager> li,String manager) {
		for(int i=0; i<li.size();i++){
			if(manager.equals(li.get(i).getId())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证number（x,y）类型数据格式
	 * @param str
	 * @param i,目标位数
	 * @return
	 */
	public static boolean isNumberMoney(String str,int i){
		if(!(Strings.isNullOrEmpty(str))){
			if( i < str.length() ){
				return false; 
			}else{
				Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
				Matcher match = pattern.matcher(str);
				if (match.matches() == false) {
					return false;
				} else {
					int rn = str.length();
					int pos = str.indexOf(".");
					if(pos > 0){
						//有小数点
						if(pos > i-3 || pos+1 == rn)
							return false;
					}else{
						//wu
						if(rn > i-3)
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断是否为注册资本金
	 * @param str
	 * @return
	 */
	public boolean isNumericMoney(String str){ 
		java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
	       java.util.regex.Matcher match=pattern.matcher(str);   
	       if(match.matches()==false)   
	       {   
	          return false;   
	       }   
	       else   
	       {   
	          return true;   
	       }   
	}
	public boolean isnumber(String reg) {
		int rn = reg.length();
		int pos = reg.indexOf(".");
		if(rn == 0){
			return false;
		}else{
			if(pos > 0){
//				有小数点
				if(pos > 15)
					return false;
			}else{
				//wu
				if(rn > 15)
					return false;
			}
		}
		return true;
	}
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}
	/**
	 * 判断是否为数字或者字母
	 * @param str
	 * @return
	 */
	public boolean isNumericChar(String str){ 
		Pattern pattern = Pattern.compile("[A-Za-z0-9]+"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	/**
	 * 判断是否为字母
	 * @param str
	 * @return
	 */
	public boolean isChars(String str){ 
		Pattern pattern = Pattern.compile("^[A-Za-z]+$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	/**
	 * 判断是否为金额
	 * @param str
	 * @return
	 */
	public boolean isMoney(String str){ 
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	/*********************************************************4*****************************************************/
	/*********************************************************5*****************************************************/
	/**
	 * 位数判断
	 * @param str
	 * @return
	 */
	public boolean isNum(String str,int i){ 
	   int stri = str.length();
	   if( i < stri ){
	       return false; 
	   } 
	   return true; 
	}
	/*********************************************************5*****************************************************/
	/*********************************************************6*****************************************************/
	/**
	 * 位数判断
	 * @param str
	 * @return
	 */
	public boolean equalNum(String str,int i){ 
	   int stri = str.length();
	   if( i != stri ){
	       return false; 
	   } 
	   return true; 
	}
	/*********************************************************6*****************************************************/
	/*********************************************************7*****************************************************/
	/**
	 * yyyymmdd日期判断
	 * @param str
	 * @return
	 */
	public boolean isDate(String str) {
		if(!isNumeric(str)){
			return false;
		}
		int stri = str.length();
		if (8 != stri) {
			return false;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = format.parse(str);
			System.out.println(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/*********************************************************7*****************************************************/
	/**
	 * 下载导入商户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return TODO
	 */
	@RequiresPermissions("SDMTPlatform:merchant:view")
	@RequestMapping(value = "/merchantSDMT/template")
	public void importFileTemplate(HttpServletRequest req,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		log.debug("下载导入商户数据模板");
		try{
			workbook = new HSSFWorkbook();// excel文件对象
			HSSFSheet sheet1 = workbook.createSheet("商户信息");// 工作表对象
			// 设置标题样式
			this.setHeadCellStyles(workbook, sheet1);
			// 设置列头样式
			this.setTitleCellStyles(workbook, sheet1);
			// 设置数据样式
			this.setDataCellStyles(workbook, sheet1);
			// 创建一个隐藏页、隐藏数据集和名称管理
			this.creatHideSheet(workbook);
			// 创建标题和列头数据
			String headName = "商户信息";
			this.creatAppRowHead(sheet1, headName);
			// 设置下拉框
			this.createSelectValidate(sheet1);
			
			/**************************** 输出流 *****************************************/
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = POIUtils.encodeFileName(req, headName+System.currentTimeMillis());
			OutputStream os = response.getOutputStream();// 取得输出流
			response.setHeader("Content-disposition", "attachment;filename="
					+ filename + ".xls");
			workbook.write(os);
			os.close();

			System.out.println("导出成功!");
//			return null;
		}catch(Exception e){
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
//		return "redirect:merchantSDMT/page";
	}
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"\n":"");
			/*sb.append(message).append(messages.length>1?"<br/>":"");*/		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	/**
	 * HEAD样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setHeadCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		headStyle.setFont(font);
	}
	/**
	 * 列头样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setTitleCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		titleStyle = workbook.createCellStyle();

		// 设置边框
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置背景色
		titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置居中
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		titleStyle.setFont(font);// 选择需要用到的字体格式
		// 设置自动换行
		titleStyle.setWrapText(true);
		// 设置列宽 ,第一个参数代表列id(从0开始),第2个参数代表宽度值
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 7000);//MCC
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);//省份
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);//城市
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);//区域
//		sheet.setColumnWidth(12, 4000);
		
		sheet.setColumnWidth(12, 7000);
		sheet.setColumnWidth(13, 7000);
		sheet.setColumnWidth(14, 7000);//是否是北京开户行
		sheet.setColumnWidth(15, 4000);//结算银行
		sheet.setColumnWidth(16, 4000);
		sheet.setColumnWidth(17, 4000);
		sheet.setColumnWidth(18, 4000);
		sheet.setColumnWidth(19, 7000);
		sheet.setColumnWidth(20, 4000);//是否单独结算
		sheet.setColumnWidth(21, 4000);
		sheet.setColumnWidth(22, 4000);//商户类型
		sheet.setColumnWidth(23, 7000);//办理手续经办人姓名：
		sheet.setColumnWidth(24, 7000);//办理手续经办人证件类型
		sheet.setColumnWidth(25, 7000);
		sheet.setColumnWidth(26, 9000);
		sheet.setColumnWidth(27, 9000);
		sheet.setColumnWidth(28, 7000);//法定代表人证件类型
		sheet.setColumnWidth(29, 7000);
		sheet.setColumnWidth(30, 7000);
		sheet.setColumnWidth(31, 7000);
		sheet.setColumnWidth(32, 7000);
		sheet.setColumnWidth(33, 7000);
		sheet.setColumnWidth(34, 7000);
		sheet.setColumnWidth(35, 7000);
		sheet.setColumnWidth(36, 7000);
		sheet.setColumnWidth(37, 7000);
		
		sheet.setColumnWidth(38, 4000);
		sheet.setColumnWidth(39, 4000);
		sheet.setColumnWidth(40, 4000);
		sheet.setColumnWidth(41, 4000);//续约方式
		sheet.setColumnWidth(42, 4000);
		sheet.setColumnWidth(43, 4000);
		sheet.setColumnWidth(44, 4000);
		sheet.setColumnWidth(45, 7000);
//		sheet.setColumnWidth(46, 4000);
//		sheet.setColumnWidth(47, 7000);
		sheet.setColumnWidth(46, 4000);
		sheet.setColumnWidth(47, 4000);//区域
		sheet.setColumnWidth(48, 4000);//区域负责人
		sheet.setColumnWidth(49, 4000);//商户顾问
		sheet.setColumnWidth(50, 7000);
		sheet.setColumnWidth(51, 4000);//父商户号
		
		sheet.setColumnWidth(52, 4000);//营业执照名称
		sheet.setColumnWidth(53, 9000);//控股股东或实际控制人姓名
		sheet.setColumnWidth(54, 7000);//控股股东或实际控制人证件种类
		sheet.setColumnWidth(55, 7000);//控股股东或实际控制人证件号码
		sheet.setColumnWidth(56, 7000);//控股股东或实际控制人证件有效期截止日
		
		sheet.setColumnWidth(57, 7000);//企业经营范围
		sheet.setColumnWidth(58, 4000);//商户分类
		sheet.setColumnWidth(59, 7000);//注册资本金
		sheet.setColumnWidth(60, 7000);//注册资本金币种
		sheet.setColumnWidth(61, 7000);//结算账户类型
	}
	/**
	 * 数据样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setDataCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		dataStyle = workbook.createCellStyle();

		// 设置边框
		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置背景色
		dataStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置居中
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		dataStyle.setFont(font);// 选择需要用到的字体格式
		// 设置自动换行
		dataStyle.setWrapText(true);
	}
	/**
	 * 创建隐藏页和数据域
	 * @param workbook
	 * @param hideSheetName
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void creatHideSheet(HSSFWorkbook workbook) throws FileNotFoundException, IOException {
		/****************************************************** 创建省市区 ***************************************************/
		HSSFSheet factoryAndModelSheet = workbook.createSheet("factoryAndModelSheet");// 隐藏一些信息
		/*************************************************** 省-市 start ***************************************************/
		//省份
		List<Area> proviceList = AreaService.queryList("0", "1");
		List<String> rowList = null;
		String provinceid = "";
		String provinceCity = "";
		Name name;
		for(int i=0;i<proviceList.size();i++){
			HSSFRow pfModelRow = factoryAndModelSheet.createRow(i);
			rowList = new ArrayList<String>();
			provinceid = proviceList.get(i).getId().toString();
			provinceCity = proviceList.get(i).getProvinceCity();
			rowList.add(provinceCity + "_" + provinceid);
			List<Area> cityList = AreaService.queryList(provinceid, "1");
			// 添加“省--->市” 名称
			name = workbook.createName();
			name.setNameName( provinceCity+ "_" + provinceid);
			for(int j=0; j<cityList.size(); j++){
				Area area = cityList.get(j);
				rowList.add(area.getProvinceCity()+"_"+area.getId().toString());
			}
			this.creatRow(pfModelRow, rowList);
			name.setRefersToFormula("factoryAndModelSheet!$B$" + (i + 1) + ":$"
					+ this.getcellColumnFlag(cityList.size() + 1) + "$"
					+ (i + 1));
		}
		name = workbook.createName();
		name.setNameName("provice");
		name.setRefersToFormula("factoryAndModelSheet!$A$1:$A$"+ proviceList.size());
		/*************************************************** 省-市 end ***************************************************/
		/*************************************************** 市-区 start ***************************************************/
		//市
		int sm = proviceList.size()+1;
		List<String> cityNList = null;
		List<Area> cityareaList = new LinkedList<Area>();
		String cityid = "";
		String cityCity = "";
		//将所有的市加之cityareaList
		for(int i=0;i<proviceList.size();i++){
			provinceid = proviceList.get(i).getId().toString();
			List<Area> cityList = AreaService.queryList(provinceid, "1");
			cityareaList.addAll(cityList);
		}
		for(int i=0;i<cityareaList.size();i++){
			HSSFRow pfModelRow = factoryAndModelSheet.createRow(sm+i);
			cityNList = new ArrayList<String>();
			cityid = cityareaList.get(i).getId().toString();
			cityCity = cityareaList.get(i).getProvinceCity();
			cityNList.add(cityCity + "_" + cityid);
			List<Area> cityList = AreaService.queryList(cityid, "1");
			// 添加“市--->区” 名称
			name = workbook.createName();
			name.setNameName( cityCity+ "_" + cityid);
			for(int j=0; j<cityList.size(); j++){
				Area area = cityList.get(j);
				cityNList.add(area.getProvinceCity()+"_"+area.getId().toString());
			}
			this.creatRow(pfModelRow, cityNList);
			name.setRefersToFormula("factoryAndModelSheet!$B$" + (sm+i + 1) + ":$"
					+ this.getcellColumnFlag(cityList.size() + 1) + "$"
					+ (sm+i + 1));
		}
		name = workbook.createName();
		name.setNameName("citycityname");
		name.setRefersToFormula("factoryAndModelSheet!$A$"+sm+":$A$"+ cityareaList.size());
		/*************************************************** 市-区 end ***************************************************/
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("factoryAndModelSheet"),true);
		
		/*********************************************** 创建区域、商户顾问、店长下拉框***************************************/
		HSSFSheet zoneAndModelSheet = workbook.createSheet("zoneAndModelSheet");// 隐藏一些信息
		//设置区域下拉框List
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		List<String> zonerowList = null;
		String zoneValue = "";
		String zoneLabel = "";
		for(int i=0;i<merchantAreaList.size();i++){
			HSSFRow pfModelRow = zoneAndModelSheet.createRow(i);
			zonerowList = new ArrayList<String>();
			zoneValue = merchantAreaList.get(i).getValue().toString();
			zoneLabel = merchantAreaList.get(i).getLabel().toString();
			zonerowList.add(zoneLabel +"_"+ zoneValue);
			//商户顾问
			List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("0", zoneValue);
			// 添加“区域--->商户顾问” 名称
			name = workbook.createName();
			name.setNameName(zoneLabel.trim() +"_"+ zoneValue.trim());
			for (PManager pManager : storeManagerList) {
				zonerowList.add(pManager.getManagerName().trim()+"_"+pManager.getId().trim());
			}
			this.creatRow(pfModelRow, zonerowList);
			name.setRefersToFormula("zoneAndModelSheet!$B$" + (i + 1) + ":$"
					+ this.getcellColumnFlag(storeManagerList.size() + 1) + "$"
					+ (i + 1));
			
		}
		name = workbook.createName();
		name.setNameName("storeManagerfactory");
		name.setRefersToFormula("zoneAndModelSheet!$A$1:$A$"+ merchantAreaList.size());
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("zoneAndModelSheet"),true);
		
		
		/**************** 创建其他码表sheet：MCC、是否是北京开户行、结算银行、是否单独结算、商户类型、证件类型、预约方式*****************/
		HSSFSheet otherSheet = workbook.createSheet("otherSheet");
		//设置MCC和地址码表
		HSSFRow mccRow = otherSheet.createRow(0);
		List<String> mccRowList = new ArrayList<String>();
//		//下拉框list超过255poi报错
//		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");//${model.id}---${model.descr}
//		mccList = mccList.subList(0, 20);
//		mccRowList.add("MCC");
//		for (NumDescr numDescr : mccList) {
//			mccRowList.add(numDescr.getDescr() + "_" + numDescr.getId());
//		}
		mccRowList.add("MCC");
		mccRowList.add("VETINARY 服务" + "_" + "742");
		this.creatRow(mccRow, mccRowList);
		name = workbook.createName();
		name.setNameName("MCC");
		name.setRefersToFormula("otherSheet!$B$1:$"+ this.getcellColumnFlag(mccRowList.size()) + "$1");
		
		//是否是北京开户行
		HSSFRow isBjAcctRow = otherSheet.createRow(1);
		List<String> isBjAcctRowList = new ArrayList<String>();
		List<SysDict> isBjAcctList = isTrueOrFalse();
		isBjAcctRowList.add("isBjAcct");
		for (SysDict dict : isBjAcctList) {
			isBjAcctRowList.add(dict.getValue()+"_"+dict.getId());
		}
		this.creatRow(isBjAcctRow, isBjAcctRowList);
		name = workbook.createName();
		name.setNameName("isBjAcct");
		name.setRefersToFormula("otherSheet!$B$2:$"+ this.getcellColumnFlag(isBjAcctRowList.size()) + "$2");
		
		//结算银行
		HSSFRow bisRow = otherSheet.createRow(2);
		List<String> bisRowRowList = new ArrayList<String>();
		List<TBankInfo> bankList=TBankInfoService.queryList("");//${model.bankCode}---${model.bankName}
		bisRowRowList.add("BIS");
		for (TBankInfo tBankInfo : bankList) {
			bisRowRowList.add(tBankInfo.getBankName()+"_"+tBankInfo.getBankCode());
		}
		this.creatRow(bisRow, bisRowRowList);
		name = workbook.createName();
		name.setNameName("BIS");
		name.setRefersToFormula("otherSheet!$B$3:$"+ this.getcellColumnFlag(bisRowRowList.size()) + "$3");
		
		//是否单独结算
		HSSFRow individualRow = otherSheet.createRow(3);
		List<String> individualRowList = new ArrayList<String>();
		List<SysDict> individualList = isTrueOrFalse();
		individualRowList.add("individual");
		for (SysDict sysDict : individualList) {
			individualRowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(individualRow, individualRowList);
		name = workbook.createName();
		name.setNameName("individual");
		name.setRefersToFormula("otherSheet!$B$4:$"+ this.getcellColumnFlag(individualRowList.size()) + "$4");
		//商户类型 typeYf
		HSSFRow typeYfRow = otherSheet.createRow(4);
		List<String> typeYfRowList = new ArrayList<String>();
		List<SysParameter> typeYfList = SysParameterService.queryList("CARDBATCH_MER_TYPE", "");
		typeYfRowList.add("typeYf");
		for (SysParameter SysParameter : typeYfList) {
			typeYfRowList.add(SysParameter.getParamName()+"_"+SysParameter.getParamValue());
		}
		this.creatRow(typeYfRow, typeYfRowList);
		name = workbook.createName();
		name.setNameName("typeYf");
		name.setRefersToFormula("otherSheet!$B$5:$"+ this.getcellColumnFlag(typeYfRowList.size()) + "$5");
		
		//办理手续经办人证件类型
		HSSFRow idTypeRow = otherSheet.createRow(5);
		List<String> idTypeRowList = new ArrayList<String>();
		List<SysDict> idTypeList = IDType();
		idTypeRowList.add("idType");
		for (SysDict sysDict : idTypeList) {
			idTypeRowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(idTypeRow, idTypeRowList);
		name = workbook.createName();
		name.setNameName("idType");
		name.setRefersToFormula("otherSheet!$B$6:$"+ this.getcellColumnFlag(idTypeRowList.size()) + "$6");
		
		//法定代表人证件类型
		HSSFRow lrIdTypeRow = otherSheet.createRow(6);
		List<String> lrIdTypeRowList = new ArrayList<String>();
		List<SysDict> lrIdTypeList = IDType();//value
		lrIdTypeRowList.add("lrIdType");
		for (SysDict sysDict : lrIdTypeList) {
			lrIdTypeRowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(lrIdTypeRow, lrIdTypeRowList);
		name = workbook.createName();
		name.setNameName("lrIdType");
		name.setRefersToFormula("otherSheet!$B$7:$"+ this.getcellColumnFlag(lrIdTypeRowList.size()) + "$7");
		
		//设置预约方式下拉框 RENEWAL_TYPE
		HSSFRow renewalTypeRow = otherSheet.createRow(7);
		List<String> renewalTypeRowList = new ArrayList<String>();
		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("RENEWAL_TYPE"));
//		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("RENEWAL_TYPE"));
		renewalTypeRowList.add("RENEWAL_TYPE");
		for (SysDict sysDict : renewalTypeList) {
			renewalTypeRowList.add(sysDict.getLabel()+"_"+sysDict.getValue());
		}
		this.creatRow(renewalTypeRow, renewalTypeRowList);
		name = workbook.createName();
		name.setNameName("RENEWAL_TYPE");
		name.setRefersToFormula("otherSheet!$B$8:$"+ this.getcellColumnFlag(renewalTypeRowList.size()) + "$8");
		
		//控股股东或实际控制人证件种类
		HSSFRow idType1Row = otherSheet.createRow(8);
		List<String> idType1RowList = new ArrayList<String>();
		List<SysDict> idType1List = IDType();//value
		idType1RowList.add("idType1");
		for (SysDict sysDict : idType1List) {
			idType1RowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(idType1Row, idType1RowList);
		name = workbook.createName();
		name.setNameName("idType1");
		name.setRefersToFormula("otherSheet!$B$9:$"+ this.getcellColumnFlag(idType1RowList.size()) + "$9");
		
		//设置商户分类	merchant_x_type
		HSSFRow merchant_x_typeRow = otherSheet.createRow(9);
		List<String> merchant_x_typeRowList = new ArrayList<String>();
		List<SysDict> merchant_x_typeList = merchant_x_Type();
		merchant_x_typeRowList.add("MERCHANT_X_TYPE");
		for (SysDict sysDict : merchant_x_typeList) {
			merchant_x_typeRowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(merchant_x_typeRow, merchant_x_typeRowList);
		name = workbook.createName();
		name.setNameName("MERCHANT_X_TYPE");
		name.setRefersToFormula("otherSheet!$B$10:$"+ this.getcellColumnFlag(merchant_x_typeRowList.size()) + "$10");
		//设置注册资本金币种 merchant_x_code
		HSSFRow merchant_x_codeRow = otherSheet.createRow(10);
		List<String> merchant_x_codeRowList = new ArrayList<String>();
		List<SysParameter> merchant_x_codeList = SysParameterService.queryList("MERCHANT_X_CODE_TYPE", "");
		sortStringMethod(merchant_x_codeList);
		merchant_x_codeRowList.add("MERCHANT_X_CODE");
		for (SysParameter SysParameter : merchant_x_codeList) {
			merchant_x_codeRowList.add(SysParameter.getParamName()+"_"+SysParameter.getParamValue());
		}
		this.creatRow(merchant_x_codeRow, merchant_x_codeRowList);
		name = workbook.createName();
		name.setNameName("MERCHANT_X_CODE");
		name.setRefersToFormula("otherSheet!$B$11:$"+ this.getcellColumnFlag(merchant_x_codeRowList.size()) + "$11");
		//设置结算账户类型 merchant_x_acc_type1
		HSSFRow merchant_x_acc_type1Row = otherSheet.createRow(11);
		List<String> merchant_x_acc_type1RowList = new ArrayList<String>();
		List<SysDict> merchant_x_acc_type1List = merchant_x_acc_type1Type();
		merchant_x_acc_type1RowList.add("MERCHANT_X_ACC_TYPE1");
		for (SysDict sysDict : merchant_x_acc_type1List) {
			merchant_x_acc_type1RowList.add(sysDict.getValue()+"_"+sysDict.getId());
		}
		this.creatRow(merchant_x_acc_type1Row, merchant_x_acc_type1RowList);
		name = workbook.createName();
		name.setNameName("MERCHANT_X_ACC_TYPE1");
		name.setRefersToFormula("otherSheet!$B$12:$"+ this.getcellColumnFlag(merchant_x_acc_type1RowList.size()) + "$12");
				
		workbook.setSheetHidden(workbook.getSheetIndex("otherSheet"), true);
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
	 * readProperties
	 * 
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
	 * 创建标题应用列头
	 * @param userinfosheet1
	 * @param userName
	 */
	public void creatAppRowHead(HSSFSheet userinfosheet1, String headName) {
		// 设置标题
		HSSFRow rowHead = userinfosheet1.createRow(0);
		userinfosheet1.addMergedRegion(new Region(0, (short) 0, 0, (short) 61));
		POIUtils.createCell(rowHead, (short) 0, headName, headStyle);
		rowHead.setHeight((short) (27 * 20));

		// 设置表头
		HSSFRow row = userinfosheet1.createRow(1);

		HSSFCell merchantIdCell = row.createCell(0);
		merchantIdCell.setCellValue("商户编号");
		merchantIdCell.setCellStyle(titleStyle);

		HSSFCell mrchtNameCell = row.createCell(1);
		mrchtNameCell.setCellValue("商户名称");
		mrchtNameCell.setCellStyle(titleStyle);

		HSSFCell merchantEnameCell = row.createCell(2);
		merchantEnameCell.setCellValue("商户名称(英)");
		merchantEnameCell.setCellStyle(titleStyle);

		HSSFCell abbrCnameCell = row.createCell(3);
		abbrCnameCell.setCellValue("简称(中)");
		abbrCnameCell.setCellStyle(titleStyle);

		HSSFCell abbrEnameCell = row.createCell(4);
		abbrEnameCell.setCellValue("简称(英)");
		abbrEnameCell.setCellStyle(titleStyle);

		HSSFCell mccIdCell = row.createCell(5);
		mccIdCell.setCellValue("MCC");
		mccIdCell.setCellStyle(titleStyle);

		HSSFCell addressCell = row.createCell(6);
		addressCell.setCellValue("地址");
		addressCell.setCellStyle(titleStyle);

		HSSFCell provinceCell = row.createCell(7);
		provinceCell.setCellValue("省份");
		provinceCell.setCellStyle(titleStyle);

		HSSFCell cityNoCell = row.createCell(8);
		cityNoCell.setCellValue("城市");
		cityNoCell.setCellStyle(titleStyle);
		
		HSSFCell zoneCell = row.createCell(9);
		zoneCell.setCellValue("区");
		zoneCell.setCellStyle(titleStyle);
		
		HSSFCell telephoneCell = row.createCell(10);
		telephoneCell.setCellValue("联系电话");
		telephoneCell.setCellStyle(titleStyle);
		
		HSSFCell managerCell = row.createCell(11);
		managerCell.setCellValue("联系人");
		managerCell.setCellStyle(titleStyle);
		
//		HSSFCell addDateCell = row.createCell(12);
//		addDateCell.setCellValue("增加时间");
//		addDateCell.setCellStyle(titleStyle);
		
		HSSFCell accNameCell = row.createCell(12);
		accNameCell.setCellValue("结算账户开户名");
		accNameCell.setCellStyle(titleStyle);
		
		HSSFCell accNoCell = row.createCell(13);
		accNoCell.setCellValue("结算账号");
		accNoCell.setCellStyle(titleStyle);
		
		HSSFCell isBjAcctCell = row.createCell(14);
		isBjAcctCell.setCellValue("是否是北京开户行");
		isBjAcctCell.setCellStyle(titleStyle);
		
		HSSFCell bisCell = row.createCell(15);
		bisCell.setCellValue("结算银行");
		bisCell.setCellStyle(titleStyle);
		
		HSSFCell bankNameCell = row.createCell(16);
		bankNameCell.setCellValue("开户银行名称");
		bankNameCell.setCellStyle(titleStyle);
		
		HSSFCell accNickNameCell = row.createCell(17);
		accNickNameCell.setCellValue("商户账号全称");
		accNickNameCell.setCellStyle(titleStyle);
		
		HSSFCell shortNickNameCell = row.createCell(18);
		shortNickNameCell.setCellValue("商户账号简称");
		shortNickNameCell.setCellStyle(titleStyle);
		
		HSSFCell bankNoCell = row.createCell(19);
		bankNoCell.setCellValue("开户银行行号");
		bankNoCell.setCellStyle(titleStyle);
		
		HSSFCell individualCell = row.createCell(20);
		individualCell.setCellValue("是否单独结算");
		individualCell.setCellStyle(titleStyle);
		
		HSSFCell lastSettleDateCell = row.createCell(21);
		lastSettleDateCell.setCellValue("上次结算日期");
		lastSettleDateCell.setCellStyle(titleStyle);
		
		HSSFCell typeYfCell = row.createCell(22);
		typeYfCell.setCellValue("商户类型");
		typeYfCell.setCellStyle(titleStyle);
		
		HSSFCell agentCell = row.createCell(23);
		agentCell.setCellValue("办理手续经办人姓名");
		agentCell.setCellStyle(titleStyle);
		
		HSSFCell idTypeCell = row.createCell(24);
		idTypeCell.setCellValue("办理手续经办人证件类型");
		idTypeCell.setCellStyle(titleStyle);
		
		HSSFCell idNoCell = row.createCell(25);
		idNoCell.setCellValue("办理手续经办人证件号码");
		idNoCell.setCellStyle(titleStyle);
		
		HSSFCell idValidityCell = row.createCell(26);
		idValidityCell.setCellValue("办理手续经办人证件有效期");
		idValidityCell.setCellStyle(titleStyle);
		
		HSSFCell legalRepCell = row.createCell(27);
		legalRepCell.setCellValue("法定代表人（负责人）姓名");
		legalRepCell.setCellStyle(titleStyle);
		
		HSSFCell lrIdTypeCell = row.createCell(28);
		lrIdTypeCell.setCellValue("法定代表人证件类型");
		lrIdTypeCell.setCellStyle(titleStyle);
		
		HSSFCell lrIdNoCell = row.createCell(29);
		lrIdNoCell.setCellValue("法定代表人证件号码");
		lrIdNoCell.setCellStyle(titleStyle);
		
		HSSFCell lrIdValidityCell = row.createCell(30);
		lrIdValidityCell.setCellValue("法定代表人证件有效期");
		lrIdValidityCell.setCellStyle(titleStyle);
		
		HSSFCell busLicNoCell = row.createCell(31);
		busLicNoCell.setCellValue("营业执照号");
		busLicNoCell.setCellStyle(titleStyle);
		
		HSSFCell busLicValidityCell = row.createCell(32);
		busLicValidityCell.setCellValue("营业执照年检时间");
		busLicValidityCell.setCellStyle(titleStyle);
		
		HSSFCell taxIdCell = row.createCell(33);
		taxIdCell.setCellValue("税务登记证编号");
		taxIdCell.setCellStyle(titleStyle);
		
		HSSFCell taxIdValidityCell = row.createCell(34);
		taxIdValidityCell.setCellValue("税务登记证年检时间");
		taxIdValidityCell.setCellStyle(titleStyle);
		
		HSSFCell orgIdCell = row.createCell(35);
		orgIdCell.setCellValue("组织机构证编号");
		orgIdCell.setCellStyle(titleStyle);
		
		HSSFCell orgValidityCell = row.createCell(36);
		orgValidityCell.setCellValue("组织机构证年检时间");
		orgValidityCell.setCellStyle(titleStyle);
		
		HSSFCell enterpriseNoCell = row.createCell(37);
		enterpriseNoCell.setCellValue("企业账号");
		enterpriseNoCell.setCellStyle(titleStyle);
		
		HSSFCell contractStartDateCell = row.createCell(38);
		contractStartDateCell.setCellValue("合同签订日期");
		contractStartDateCell.setCellStyle(titleStyle);
		
		HSSFCell contractRenewalDateCell = row.createCell(39);
		contractRenewalDateCell.setCellValue("合同续约日期");
		contractRenewalDateCell.setCellStyle(titleStyle);
		
		HSSFCell merchantCompanyNameCell = row.createCell(40);
		merchantCompanyNameCell.setCellValue("商户公司名称");
		merchantCompanyNameCell.setCellStyle(titleStyle);
		
		HSSFCell renewalTypeCell = row.createCell(41);
		renewalTypeCell.setCellValue("续约方式");
		renewalTypeCell.setCellStyle(titleStyle);
		
		HSSFCell guaranteeLastDateCell = row.createCell(42);
		guaranteeLastDateCell.setCellValue("保函到期日期");
		guaranteeLastDateCell.setCellStyle(titleStyle);
		
		HSSFCell guaranteeAmtCell = row.createCell(43);
		guaranteeAmtCell.setCellValue("保函金额");
		guaranteeAmtCell.setCellStyle(titleStyle);
		
		HSSFCell marketContactPersonCell = row.createCell(44);
		marketContactPersonCell.setCellValue("市场联系人");
		marketContactPersonCell.setCellStyle(titleStyle);
		
		HSSFCell marketContactMobileCell = row.createCell(45);
		marketContactMobileCell.setCellValue("市场联系人电话");
		marketContactMobileCell.setCellStyle(titleStyle);
		
		/*HSSFCell financialContactPersonCell = row.createCell(46);
		financialContactPersonCell.setCellValue("财务联系人");
		financialContactPersonCell.setCellStyle(titleStyle);
		
		HSSFCell financialContactMobileCell = row.createCell(47);
		financialContactMobileCell.setCellValue("财务联系人电话");
		financialContactMobileCell.setCellStyle(titleStyle);*/
		
		HSSFCell merchantDepositCell = row.createCell(46);
		merchantDepositCell.setCellValue("押金");
		merchantDepositCell.setCellStyle(titleStyle);
		
		HSSFCell merchantAreaCell = row.createCell(47);
		merchantAreaCell.setCellValue("区域");
		merchantAreaCell.setCellStyle(titleStyle);
		
		HSSFCell storeManagerCell = row.createCell(48);
		storeManagerCell.setCellValue("区域负责人");
		storeManagerCell.setCellStyle(titleStyle);
		
		HSSFCell merchantAdvisorCell = row.createCell(49);
		merchantAdvisorCell.setCellValue("商户顾问");
		merchantAdvisorCell.setCellStyle(titleStyle);
		
		HSSFCell contractEndDateCell = row.createCell(50);
		contractEndDateCell.setCellValue("合同结束日期");
		contractEndDateCell.setCellStyle(titleStyle);
		
		HSSFCell fmernoCell = row.createCell(51);
		fmernoCell.setCellValue("父商户号");
		fmernoCell.setCellStyle(titleStyle);
		
		HSSFCell accXNameCell = row.createCell(52);
		accXNameCell.setCellValue("营业执照名称");
		accXNameCell.setCellStyle(titleStyle);
		HSSFCell manNameCell = row.createCell(53);
		manNameCell.setCellValue("实际控制人姓名");
		manNameCell.setCellStyle(titleStyle);
		HSSFCell idType1Cell = row.createCell(54);
		idType1Cell.setCellValue("实际控制人证件种类");
		idType1Cell.setCellStyle(titleStyle);
		HSSFCell idNo1Cell = row.createCell(55);
		idNo1Cell.setCellValue("实际控制人证件号码");
		idNo1Cell.setCellStyle(titleStyle);
		HSSFCell idDeadline1Cell = row.createCell(56);
		idDeadline1Cell.setCellValue("实际控制人证件有效期");
		idDeadline1Cell.setCellStyle(titleStyle);
		
		HSSFCell merchant_x_operateCell = row.createCell(57);
		merchant_x_operateCell.setCellValue("企业经营范围");
		merchant_x_operateCell.setCellStyle(titleStyle);
		HSSFCell merchant_x_typeCell = row.createCell(58);
		merchant_x_typeCell.setCellValue("商户分类");
		merchant_x_typeCell.setCellStyle(titleStyle);
		HSSFCell merchant_x_reg_amtCell = row.createCell(59);
		merchant_x_reg_amtCell.setCellValue("注册资本金(单位：万)");
		merchant_x_reg_amtCell.setCellStyle(titleStyle);
		HSSFCell merchant_x_codeCell = row.createCell(60);
		merchant_x_codeCell.setCellValue("注册资本金币种");
		merchant_x_codeCell.setCellStyle(titleStyle);
		HSSFCell merchant_x_acc_type1Cell = row.createCell(61);
		merchant_x_acc_type1Cell.setCellValue("结算账户类型");
		merchant_x_acc_type1Cell.setCellStyle(titleStyle);
	}
	/**
	 * 添加下拉框限制	
	 * @param sheet1
	 */
	public void createSelectValidate(HSSFSheet sheet1) {
		//MCC，第六列
		DVConstraint constraintMcc = DVConstraint.createFormulaListConstraint("MCC");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsMcc = new CellRangeAddressList(2, 499,5, 5);
		DataValidation data_validation_Mcc = new HSSFDataValidation(regionsMcc, constraintMcc);
		sheet1.addValidationData(data_validation_Mcc);
		
		//省，第八列
		DVConstraint constraintPosType = DVConstraint.createFormulaListConstraint("provice");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsPosType = new CellRangeAddressList(2, 499,7, 7);
		DataValidation data_validation_PosType = new HSSFDataValidation(regionsPosType, constraintPosType);
		sheet1.addValidationData(data_validation_PosType);
		
		//市，第九列
		DVConstraint constraintcityNo = DVConstraint.createFormulaListConstraint("INDIRECT($H$3:$H$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionscityNo = new CellRangeAddressList(2, 499,8, 8);
		DataValidation data_validation_cityNo = new HSSFDataValidation(regionscityNo, constraintcityNo);
		sheet1.addValidationData(data_validation_cityNo);
		
		//区，第十列
		DVConstraint constraintmanager = DVConstraint.createFormulaListConstraint("INDIRECT($I$3:$I$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmanager = new CellRangeAddressList(2, 499,9, 9);
		DataValidation data_validation_manager = new HSSFDataValidation(regionsmanager, constraintmanager);
		sheet1.addValidationData(data_validation_manager);
		
		//是否北京开户行，第十六列(15)
		DVConstraint constraintisBjAcct = DVConstraint.createFormulaListConstraint("isBjAcct");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsisBjAcct = new CellRangeAddressList(2, 499,14, 14);
		DataValidation data_validation_isBjAcct = new HSSFDataValidation(regionsisBjAcct, constraintisBjAcct);
		sheet1.addValidationData(data_validation_isBjAcct);
		
		//结算银行，第十七列(16)
		DVConstraint constraintbis = DVConstraint.createFormulaListConstraint("BIS");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsbis = new CellRangeAddressList(2, 499,15, 15);
		DataValidation data_validation_bis = new HSSFDataValidation(regionsbis, constraintbis);
		sheet1.addValidationData(data_validation_bis);
		
		//是否单独结算，第二十二列(21)
		DVConstraint constraintindividual = DVConstraint.createFormulaListConstraint("individual");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsindividual = new CellRangeAddressList(2, 499,20, 20);
		DataValidation data_validation_individual = new HSSFDataValidation(regionsindividual, constraintindividual);
		sheet1.addValidationData(data_validation_individual);
		
		//商户类型，24(23)
		DVConstraint constrainttypeYf = DVConstraint.createFormulaListConstraint("typeYf");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionstypeYf = new CellRangeAddressList(2, 499,22, 22);
		DataValidation data_validation_typeYf = new HSSFDataValidation(regionstypeYf, constrainttypeYf);
		sheet1.addValidationData(data_validation_typeYf);
		
		//办理手续经办人证件类型，26(25)
		DVConstraint constraintidType = DVConstraint.createFormulaListConstraint("idType");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsidType = new CellRangeAddressList(2, 499,24, 24);
		DataValidation data_validation_idType = new HSSFDataValidation(regionsidType, constraintidType);
		sheet1.addValidationData(data_validation_idType);
		
		//法定代表人证件类型，30(29)
		DVConstraint constraintlrIdType = DVConstraint.createFormulaListConstraint("lrIdType");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionslrIdType = new CellRangeAddressList(2, 499,28, 28);
		DataValidation data_validation_lrIdType = new HSSFDataValidation(regionslrIdType, constraintlrIdType);
		sheet1.addValidationData(data_validation_lrIdType);
		
		//续约方式，43(42)
		DVConstraint constraintrenewalType = DVConstraint.createFormulaListConstraint("RENEWAL_TYPE");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsrenewalType = new CellRangeAddressList(2, 499,41, 41);
		DataValidation data_validation_renewalType = new HSSFDataValidation(regionsrenewalType, constraintrenewalType);
		sheet1.addValidationData(data_validation_renewalType);
		
		//区域，51(48)
		DVConstraint constraintmerchantArea = DVConstraint.createFormulaListConstraint("storeManagerfactory");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchantArea = new CellRangeAddressList(2, 499,47, 47);
		DataValidation data_validation_merchantArea = new HSSFDataValidation(regionsmerchantArea, constraintmerchantArea);
		sheet1.addValidationData(data_validation_merchantArea);
		
		
		//商户顾问，52(49)
		DVConstraint constraintmerchantAdvisor = DVConstraint.createFormulaListConstraint("INDIRECT($AV$3:$AV$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchantAdvisor = new CellRangeAddressList(2, 499,48, 48);
		DataValidation data_validation_merchantAdvisor = new HSSFDataValidation(regionsmerchantAdvisor, constraintmerchantAdvisor);
		sheet1.addValidationData(data_validation_merchantAdvisor);
		
		//区域负责人，53(50)
		DVConstraint constraintstoreManager = DVConstraint.createFormulaListConstraint("INDIRECT($AV$3:$AV$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsstoreManager = new CellRangeAddressList(2, 499,49, 49);
		DataValidation data_validation_storeManager = new HSSFDataValidation(regionsstoreManager, constraintstoreManager);
		sheet1.addValidationData(data_validation_storeManager);
		
		//控股股东或实际控制人证件种类，55, 7000
		DVConstraint constraintidType1 = DVConstraint.createFormulaListConstraint("idType1");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsidType1 = new CellRangeAddressList(2, 499,54, 54);
		DataValidation data_validation_idType1 = new HSSFDataValidation(regionsidType1, constraintidType1);
		sheet1.addValidationData(data_validation_idType1);
		
		//商户分类，58, 7000
		DVConstraint constraintidmerchant_x_type = DVConstraint.createFormulaListConstraint("MERCHANT_X_TYPE");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchant_x_type = new CellRangeAddressList(2, 499,58, 58);
		DataValidation data_validation_merchant_x_type = new HSSFDataValidation(regionsmerchant_x_type, constraintidmerchant_x_type);
		sheet1.addValidationData(data_validation_merchant_x_type);
		//注册资本金币种，60, 7000
		DVConstraint constraintmerchant_x_code = DVConstraint.createFormulaListConstraint("MERCHANT_X_CODE");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchant_x_code = new CellRangeAddressList(2, 499,60, 60);
		DataValidation data_validation_merchant_x_code = new HSSFDataValidation(regionsmerchant_x_code, constraintmerchant_x_code);
		sheet1.addValidationData(data_validation_merchant_x_code);
		//结算账户类型，61, 7000
		DVConstraint merchant_x_acc_type1 = DVConstraint.createFormulaListConstraint("MERCHANT_X_ACC_TYPE1");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchant_x_acc_type1 = new CellRangeAddressList(2, 499,61, 61);
		DataValidation data_validation_merchant_x_acc_type1 = new HSSFDataValidation(regionsmerchant_x_acc_type1, merchant_x_acc_type1);
		sheet1.addValidationData(data_validation_merchant_x_acc_type1);
	}
	
	/*********************************************************1*****************************************************/
	
	/**
	 * 商户类型
	 * @return
	 */
//	private List<SysDict> typeYf() {
//		String[] typeYfSt = {"1,商场类","2,超市类","3,餐饮类","4,食品类","5,娱乐休闲","6,美容美发","7,健康体检","8,体育健身"
//		,"9,汽车保养","10,珠宝钟表","11,票务类","12,旅游酒店","13,摄影服务","14,电器类","15,加油站类","16,其它类"};
//		return sysDictValue(typeYfSt);
//	}

	/**
	 * 返回证件类型
	 * @return
	 */
	public List<SysDict> IDType() {
		String[] idTypeSt = {"1,居民身份证","2,户口本","3,军人身份证","4,武装警察身份证"
				,"5,往来内地通行证","6,往来大陆通行证","7,护照","8,其他"};
		return sysDictValue(idTypeSt);
	}
	/**
	 * 返回商户分类
	 * @return
	 */
	public List<SysDict> merchant_x_Type() {
		String[] idTypeSt = {"12,单位","11,自然人"};
		return sysDictValue(idTypeSt);
	}
	/**
	 * 返回结算账户类型
	 * @return
	 */
	public List<SysDict> merchant_x_acc_type1Type() {
		String[] idTypeSt = {"12,银行账户","11,支付账户"};
		return sysDictValue(idTypeSt);
	}
	/**
	 * 返回是或否
	 * @return
	 */
	public List<SysDict> isTrueOrFalse() {
		String[] isBjAcctSt = {"0,否","1,是"};
		return sysDictValue(isBjAcctSt);
	}
	public List<SysDict> sysDictValue(String[] string) {
		List<SysDict> SysDictList = new LinkedList<SysDict>();
		for(int i=0; i<string.length;i++){
			String[] strings = string[i].split(",");
			SysDict SysisBjAcct = new SysDict();
			for(int j=0;j<strings.length;j++){
				if(j==0){
					SysisBjAcct.setId(strings[j]);
				}else{
					SysisBjAcct.setValue(strings[j]);
				}
			}
			if(null !=SysisBjAcct){
				SysDictList.add(SysisBjAcct);
			}
		}
		return SysDictList;
	}
	
	/**************************************************************************************************************/
	
	
	/*********************************************************2*****************************************************/
	
	/**
	 * 创建一列数据
	 * 
	 * @param currentRow
	 * @param textList
	 */
	public void creatRow(HSSFRow currentRow, List<String> textList) {
		if (textList != null && textList.size() > 0) {
			int i = 0;
			for (String cellValue : textList) {
				HSSFCell userNameLableCell = currentRow.createCell(i++);
				userNameLableCell.setCellValue(cellValue);
			}
		}
	}
	// 根据数据值确定单元格位置（比如：28-AB）
		private String getcellColumnFlag(int num) {
			String columFiled = "";
			int chuNum = 0;
			int yuNum = 0;
			if (num >= 1 && num <= 26) {
				columFiled = this.doHandle(num);
			} else {
				chuNum = num / 26;
				yuNum = num % 26;

				columFiled += this.doHandle(chuNum);
				columFiled += this.doHandle(yuNum);
			}
			return columFiled;
		}

		private String doHandle(final int num) {
			String[] charArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
					"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
					"W", "X", "Y", "Z" };
			return charArr[num - 1].toString();
		}
		
	/**************************************************************************************************************/
		
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, MerchantSDMT queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "sdmtpf/merchantSDMTList";
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
	@RequestMapping(value = { "merchantSDMT/getList" })
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
		 //if-else
		 if(!(null ==list || list.size()==0)){
			for(int i=0; i<list.size(); i++){	
				if(list.get(i).getAccName().length() > 8)//结算账户开户名
					list.get(i).setAccName(list.get(i).getAccName().substring(0,6)+"…");
				;
				if(list.get(i).getBankName().length() > 8)//开户银行名称
					list.get(i).setBankName(list.get(i).getBankName().substring(0,6)+"…");
			}
		}
		//三目运算符
		for(int i = 0; i< list.size(); i++){
			list.get(i).setAccName((list.get(i).getAccName().length() > 8) ? list.get(i).getAccName().substring(0,6)+"…" : list.get(i).getAccName());
			;
			list.get(i).setBankName((list.get(i).getBankName().length() > 8) ? list.get(i).getBankName().substring(0,6)+"…" : list.get(i).getBankName());
		}
		*/
		
		model.addAttribute("merchantSDMTList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		return "sdmtpf/merchantSDMTList";
	}
	
	/**
	 * 跳转添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = { "merchantSDMT/toAdd" })
	public String toAdd(HttpServletRequest req, HttpServletResponse resp,Model model,MerchantSDMT info) throws FileNotFoundException, IOException {
		//设置MCC和地址码表
		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");
		model.addAttribute("mccList", mccList);		
		//省份
		List<Area> proviceList = AreaService.queryList("0", "1");
		model.addAttribute("proviceList", proviceList);	
		//结算银行
		List<TBankInfo> bankList=TBankInfoService.queryList("");
		model.addAttribute("bankList", bankList);
		//商户类型
		List<SysParameter> typeYfList=SysParameterService.queryList("CARDBATCH_MER_TYPE", "");
		model.addAttribute("typeYfList", typeYfList);
		//注册资本金币种
		List<SysParameter> merchant_x_code_typeList=SysParameterService.queryList("MERCHANT_X_CODE_TYPE", "");
		sortStringMethod(merchant_x_code_typeList);
		model.addAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		//设置区域下拉框 MERCHANT_AREA sys_dict	
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		model.addAttribute("merchantAreaList", merchantAreaList);
		//设置预约方式下拉框 RENEWAL_TYPE sys_dict
		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("RENEWAL_TYPE"));
//		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("RENEWAL_TYPE"));
		model.addAttribute("renewalTypeList", renewalTypeList);
		//设置区域负责人下拉框
//		List<PManager> storeManagerList = pManagerService.findListByRoleId("1");
//		model.addAttribute("storeManagerList", storeManagerList);
		//普通员工
//		List<PManager> managerList = pManagerService.findListByRoleId("0");
//		model.addAttribute("managerList", managerList);
		
		try {
			Integer defaultDate = Integer.valueOf(DateUtil.getFormatTimeString(DateUtil.getDateFromString("20070101", "yyyyMMdd"),"yyyyMMdd"));
			info.setIdValidity(defaultDate);
			info.setLrIdValidity(defaultDate);
			info.setTaxIdValidity(defaultDate);
			info.setOrgValidity(defaultDate);
			info.setBusLicValidity(defaultDate);
			
			Integer defaultDateO = Integer.valueOf(DateUtil.getFormatTimeString(new Date(),"yyyyMMdd"));
			info.setContractStartDate(defaultDateO+"");
			info.setContractRenewalDate(defaultDateO+"");
			info.setGuaranteeLastDate(defaultDateO+"");
			info.setContractEndDate(defaultDateO+"");
			info.setIdDeadline1(new BigDecimal(defaultDateO));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		info.setAddDate(Long.valueOf(DateUtil.getDefaultFormateTimeString(new Date())));

		info.setLastSettleDate(Integer.valueOf(DateUtil.getNowII()));
		model.addAttribute("info", info);	
		return "sdmtpf/merchantSDMTAdd";
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
	@RequestMapping(value = { "merchantSDMT/findListByRoleId" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getManagerList(HttpServletRequest req,
			HttpServletResponse resp, Model model, String roleId,String managerArea) {
		List<PManager> pmList = pManagerService.findListByRoleIdAndArea(roleId, managerArea);
		return JsonUtil.getJsonString(pmList);
	}
	/**
	 * 添加信息
	 * 				1，如果t_merchant存在，其他三个库都不存在，则更新stdmpt表
	 * 				2，如果t_merchant存在，其他三个库都存在，则更新stdmpt表和fkuit表(记日志)
	 * 				3，如果t_merchant存在，其他三个库不都存在，(?SD_FLAG为1，则更新stdmpt表和fkuit表（记日志）；SD_FLAG不为1，则更新stdmpt表)
	 * 				4，如果t_merchant不存在，其他三个库都存在，则新增t_merchant表并将同步标志置为1，并改变SD系统中的商户信息（记日志）
	 * 				5，如果t_merchant不存在，其他三个库都不存在，则正常新增
	 * 				6，如果t_merchant不存在，其他三个库不都存在，则不新增（*）
	 * 				info.setIsTerminalAddFlag(" ");
	 * 				7，入库
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/save" }, method = { RequestMethod.POST })
	@ResponseBody
	public String add(HttpServletRequest req, HttpServletResponse resp, Model model,MerchantSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		MerchantXRecord merchantXRecord = null;
		String merchantId = info.getMerchantId();
		boolean flagSdmtpf = false;	//75临时表标志
		boolean flagFkqz = false;//福卡前置标志
		boolean flagX = false;//X平台标志
		boolean flagLfk = false;//福卡系统标志
		try {
			if (merchantId != null && !"".equals(merchantId)) {
				Map<String,Boolean> flagsmap = isExistMid(merchantId);
				flagSdmtpf = flagsmap.get("flagSdmtpf");
				flagFkqz = flagsmap.get("flagFkqz");
				flagX = flagsmap.get("flagX");
				flagLfk = flagsmap.get("flagLfk");
				
				if(flagSdmtpf){
	//				t_merchant中存在
					
					if(flagFkqz && flagX && flagLfk){
	//					其他三个库都存在
	//					则更新stdmpt表和fkuit表
//						equalsInfo(info, merchantId);//设置标志位
						info.setIsTerminalAddFlag(" ");
//						MerchantSDMTService.queryInfo(merchantId);
						merchantXRecord = this.switchLog(req,resp,MerchantSDMTService.queryInfo(merchantId),info);
						map = MerchantSDMTService.extendEdit(info,merchantXRecord);
					}else{
						if(flagFkqz || flagX || flagLfk){
		//					其他三个库不都存在
							if(flagFkqz){
		//						更新stdmpt表和fkuit表
								merchantXRecord = this.switchLog(req,resp,MerchantSDMTService.queryInfo(merchantId),info);
								map = MerchantSDMTService.extendEdit(info,merchantXRecord);
							}else{
		//						更新stdmpt表
								map = MerchantSDMTService.edit(info);
							}
						}
					}
					
					if(flagFkqz == false && flagX == false && flagLfk == false){
	//					其他三个库都不存在
	//					则更新stdmpt表
						map = MerchantSDMTService.edit(info);
					}
				}else{
	//				t_merchant中不存在
					
					if(flagFkqz && flagX && flagLfk){
	//					其他三个库都存在
	//					则新增stdmpt表，并设置标志位
						this.equalsInfo(info, merchantId);//设置标志位
						info.setIsTerminalAddFlag(" ");
						merchantXRecord = this.switchLog(req,resp,this.toMerchant(merchantId),info);
						map = MerchantSDMTService.save(info,merchantXRecord,true);
					}else{
						if(flagFkqz || flagX || flagLfk){
		//					其他三个库不都存在
							//不做处理
							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
							map.put(SysConst.RESULT_MSG, "保存商户失败，三个系统中没有同时存在。");
						}
					}
					
					if(flagFkqz == false && flagX == false && flagLfk == false){
	//					其他三个库都不存在
	//					则正常新增stdmpt表
						map = MerchantSDMTService.save(info,merchantXRecord,false);
					}
					
				}
			}
		} catch (Exception e) {
			log.info("保存商户异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存商户失败。");
		}
		
		
		
//		try {
//			if (merchantId != null && !"".equals(merchantId)) {
//				if(MerchantBaseService.queryList(merchantId).size() > 0 
//						&& this.MerchantBakService.queryList(merchantId).size() > 0
//						&& (this.RUSwitchService.queryList(merchantId).size() > 0
//								&& this.RUService.queryList(merchantId).size() > 0
//								&& this.RU1Service.queryList(merchantId).size() > 0
//								&& this.RU2Service.queryList(merchantId).size() > 0) ){
//					
//					equalsInfo(info, merchantId);
//					
//					
//					map = MerchantSDMTService.save(info,merchantXRecord,true);
//					
//				}else{
//					map = MerchantSDMTService.save(info,merchantXRecord,false);
//				}
//			}
//			
//		} catch (Exception e) {
//			log.info("保存商户异常：{}", e);
//			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
//			map.put(SysConst.RESULT_MSG, "保存商户失败。");
//		}
		return JsonUtil.getJsonString(map);

	}
	
	/**
	 * 通过福卡前置库反查出merchant
	 * @param merchantId
	 * @return
	 */
	private MerchantSDMT toMerchant(String merchantId){
		
		MerchantSDMT merchant = new MerchantSDMT();
		
		MerchantBase merchantBase = new MerchantBase();
		merchantBase.setMerchantId(merchantId);
		log.debug("查询MERCHANT_BASE表");
		MerchantBase mBase = MerchantBaseDao.selectByMerchantId(merchantBase);
		merchant.setMerchantId(merchantId);
		merchant.setMrchtName(mBase.getMerchantCname());
		merchant.setMccId(mBase.getMcc());
		merchant.setAbbrCname(mBase.getAbbrCname());
		merchant.setMerchantEname(mBase.getMerchantEname());
		merchant.setAbbrEname(mBase.getAbbrEname());
		merchant.setAddress(mBase.getAddressChn());
		merchant.setManager(mBase.getManager());
		merchant.setTelephone(mBase.getTelephone());
		
		MerchantExtra merchantExtra = new MerchantExtra();		
		merchantExtra.setMerchantId(merchantId);
		log.debug("查询MERCHANT_EXTRA表");
		MerchantExtra mExtra = MerchantExtraDao.selectByMerchantId(merchantExtra);
		merchant.setAccName(mExtra.getRcvName());
		merchant.setAccNo(mExtra.getRcvAcct1());
		merchant.setBankName(mExtra.getRcvBank());
		
		MerchantXExample merchantXExample = new MerchantXExample();
		MerchantXExample.Criteria criteriaXme = merchantXExample.createCriteria();
		criteriaXme.andMrchnoEqualTo(merchantId);
		log.debug("查询MERCHANT_X表");
		List<MerchantX> lixm = MerchantXDao.selectByExample(merchantXExample);
		if(!(null == lixm || lixm.size()==0)){ 
			MerchantX cMerchantX = lixm.get(0);
			if(null !=cMerchantX){
				merchant.setProvince(cMerchantX.getProvince()+"");
				merchant.setCityNo(cMerchantX.getCityNo()+"");
				merchant.setZone(cMerchantX.getZone()+"");
				merchant.setTypeYf(cMerchantX.getTypeYf());
				merchant.setAgent(cMerchantX.getAgent());
				merchant.setIdType(cMerchantX.getIdType());
				merchant.setIdNo(cMerchantX.getIdNo());
				merchant.setIdValidity(Integer.parseInt(DateUtil.getFormatTimeString(cMerchantX.getIdValidity(),"yyyyMMdd")));
				merchant.setBusLicNo(cMerchantX.getBusLicNo());
				merchant.setBusLicValidity(Integer.parseInt(DateUtil.getFormatTimeString(cMerchantX.getBusLicValidity(),"yyyyMMdd")));
				merchant.setTaxId(cMerchantX.getTaxId());
				merchant.setTaxIdValidity(Integer.parseInt(DateUtil.getFormatTimeString(cMerchantX.getTaxIdValidity(),"yyyyMMdd")));
				merchant.setOrgId(cMerchantX.getOrgId());
				merchant.setOrgValidity(Integer.parseInt(DateUtil.getFormatTimeString(cMerchantX.getOrgValidity(),"yyyyMMdd")));
				merchant.setLegalRep(cMerchantX.getLegalRep());
				merchant.setLrIdNo(cMerchantX.getLrIdNo());
				merchant.setLrIdType(cMerchantX.getLrIdType());
				merchant.setLrIdValidity(Integer.parseInt(DateUtil.getFormatTimeString(cMerchantX.getLrIdValidity(),"yyyyMMdd")));
				merchant.setAddDate(cMerchantX.getAddDate());
			}
		}
		
		MrchAccXExample mrchAccXExample = new MrchAccXExample();
		MrchAccXExample.Criteria criteriaMax = mrchAccXExample.createCriteria();
		criteriaMax.andMrchnoEqualTo(merchantId);
		log.debug("查询MRCH_ACC_X表");
		List<MrchAccX> lima = MrchAccXDao.selectByExample(mrchAccXExample);
		if(!(null == lima || lima.size()==0)){
			MrchAccX mrchAccX = lima.get(0);
			if(null !=mrchAccX){
				merchant.setBankNo(mrchAccX.getBankNo());
				merchant.setAccNickName(mrchAccX.getAccNickName());
				merchant.setShortNickName(mrchAccX.getShortNickName());
				merchant.setIndividual(mrchAccX.getIndividual());
				merchant.setLastSettleDate(Integer.parseInt(DateUtil.getFormatTimeString(mrchAccX.getLastSettleDate(),"yyyyMMdd")));
				merchant.setIsBjAcct(mrchAccX.getIsBjAcct());
				merchant.setBis(mrchAccX.getBis());
				merchant.setSeqMrchAccXId(mrchAccX.getId());
			}
		}
		
		MerchantExample merchantExample = new MerchantExample();
		MerchantExample.Criteria criteriame = merchantExample.createCriteria();
		criteriame.andMrchnoEqualTo(merchantId);
		log.debug("查询MERCHANT表");
		List<Merchant> lim = MerchantDao.selectByExample(merchantExample);
		if(!(null == lim || lim.size()==0)){
			Merchant merchantO = lim.get(0);
			if(null !=merchantO){
				merchant.setSeqMrchId(merchantO.getId());
			}
		}
		return merchant;
	}
	private void equalsInfo(MerchantSDMT info, String merchantId) {
		//标志位赋值
		info.setSdFlag("1");
		info.setxFlag("1");
		info.setYufuFlag("1");
		info.setxBakFlag("1");
		
		MrchAccXExample mrchAccXExample = new MrchAccXExample();
		MrchAccXExample.Criteria criteriaMax = mrchAccXExample.createCriteria();
		criteriaMax.andMrchnoEqualTo(merchantId);
		log.debug("查询MRCH_ACC_X表");
		List<MrchAccX> lima = MrchAccXDao.selectByExample(mrchAccXExample);
		if(!(null == lima || lima.size()==0)){
			MrchAccX mrchAccX = lima.get(0);
			if(null !=mrchAccX){
				info.setSeqMrchAccXId(mrchAccX.getId());
			}
		}
		
		MerchantExample merchantExample = new MerchantExample();
		MerchantExample.Criteria criteriame = merchantExample.createCriteria();
		criteriame.andMrchnoEqualTo(merchantId);
		log.debug("查询MERCHANT表");
		List<Merchant> lim = MerchantDao.selectByExample(merchantExample);
		if(!(null == lim || lim.size()==0)){
			Merchant merchantO = lim.get(0);
			if(null !=merchantO){
				info.setSeqMrchId(merchantO.getId());
			}
		}
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
	@RequestMapping(value = { "merchantSDMT/edit" })
	public String toEdit(HttpServletRequest req, HttpServletResponse resp, Model model,String merchantId) throws FileNotFoundException, IOException {
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
		return "sdmtpf/merchantSDMTEdit";
	}
	
	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = { "merchantSDMT/show" })
	public String show(HttpServletRequest req, HttpServletResponse resp, Model model,String merchantId) throws FileNotFoundException, IOException {
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
		//设置预约方式下拉框 RENEWAL_TYPE sys_dict
		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("RENEWAL_TYPE"));
//		List<SysDict> renewalTypeList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("RENEWAL_TYPE"));
		model.addAttribute("renewalTypeList", renewalTypeList);
		//商户类型
		List<SysParameter> typeYfList=SysParameterService.queryList("CARDBATCH_MER_TYPE", "");
		model.addAttribute("typeYfList", typeYfList);
		//注册资本金币种
		List<SysParameter> merchant_x_code_typeList=SysParameterService.queryList("MERCHANT_X_CODE_TYPE", "");
		sortStringMethod(merchant_x_code_typeList);
		model.addAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		//设置区域负责人下拉框
		List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("1", merchantArea);
		model.addAttribute("storeManagerList", storeManagerList);
		//普通员工
		List<PManager> managerList = pManagerService.findListByRoleIdAndArea("0", merchantArea);
		model.addAttribute("managerList", managerList);
		return "sdmtpf/merchantSDMTShow";
	}
	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/edit" }, method = { RequestMethod.POST })
	@ResponseBody
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model,MerchantSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = MerchantSDMTService.edit(info);
		} catch (Exception e) {
			log.info("修改商户异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "修改商户失败。");
		}
		return JsonUtil.getJsonString(map);

	}
	/**
	 * 获取X平台MCC码表
	 * @param req
	 * @param resp
	 * @param model
	 * @param descrtype 类别
	 * @param lang	语言
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/getMccList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getMccList(HttpServletRequest req, HttpServletResponse resp,Model model, String descrtype, String lang) {
		List<NumDescr> info = NumDescrService.queryList(descrtype, lang);
		return JsonUtil.getJsonString(info);
	}
	

	/**
	 * 获取X平台地址码表
	 * @param req
	 * @param resp
	 * @param model
	 * @param fid	级别
	 * @param isuse	是否有效
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/getAreaList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getAreaList(HttpServletRequest req, HttpServletResponse resp,Model model, String fid, String isuse) {
		List<Area> info = AreaService.queryList(fid, isuse);
		return JsonUtil.getJsonString(info);
	}	
	
	public Map<String,Boolean> isExistMid(String merchantId){
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean flagSdmtpf = false;	//75临时表标志
		boolean flagFkqz = false;//福卡前置标志
		boolean flagX = false;//X平台标志
		boolean flagLfk = false;//福卡系统标志
		// 先校验中间库，中间库不重复再校验其他库,一旦有一个重复则返回1.
		if (this.MerchantSDMTService.checkMerchantId(merchantId) > 0) {
			flagSdmtpf=true;
		// 校验75库，75库无.
		}
		if (MerchantBaseService.queryList(merchantId).size() > 0) {
			flagFkqz=true;
		} 
		if (this.MerchantBakService.queryList(merchantId).size() > 0) {
			flagX=true;
		} 
		if (this.RUSwitchService.queryList(merchantId).size() > 0
				&& this.RUService.queryList(merchantId).size() > 0
				&& this.RU1Service.queryList(merchantId).size() > 0
				&& this.RU2Service.queryList(merchantId).size() > 0) {
			flagLfk=true;
		}
		map.put("flagSdmtpf", flagSdmtpf);
		map.put("flagFkqz", flagFkqz);
		map.put("flagX", flagX);
		map.put("flagLfk", flagLfk);
		return map;
	}
	/**
	 * 判断该商户号是否重复
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId
	 * @return	空，参数无效；0，没有重复；1，中间库重复；2.收单系统重复；3.X平台重复；4.老福卡系统重复
	 * 				1，如果t_merchant存在，其他三个库不都存在，(?SD_FLAG为1，则更新stdmpt表和fkuit表；SD_FLAG不为1，则更新stdmpt表)
	 * 				2，如果t_merchant存在，其他三个库都不存在，则更新stdmpt表
	 * 				3，如果t_merchant存在，其他三个库都存在，则更新stdmpt表和fkuit表
	 * 				4，如果t_merchant不存在，其他三个库不都存在，则不新增（*）
	 * 				5，如果t_merchant不存在，其他三个库都存在，则只新增t_merchant表并将同步标志置为1
	 * 				6，如果t_merchant不存在，其他三个库都不存在，则正常新增
	 * 				7，入库
	 */
	@RequestMapping(value = { "merchantSDMT/checkMerchantId" }, method = { RequestMethod.POST })
	@ResponseBody
	public String checkMerchantId(HttpServletRequest req,
			HttpServletResponse resp, Model model, String merchantId) {
		String falg = "0";
		boolean flagSdmtpf = false;	//75临时表标志
		boolean flagFkqz = false;//福卡前置标志
		boolean flagX = false;//X平台标志
		boolean flagLfk = false;//福卡系统标志
		if (merchantId != null && !"".equals(merchantId)) {
			Map<String,Boolean> map = isExistMid(merchantId);
			flagSdmtpf = map.get("flagSdmtpf");
			flagFkqz = map.get("flagFkqz");
			flagX = map.get("flagX");
			flagLfk = map.get("flagLfk");
			if(flagSdmtpf){
//				存在 OK
//				return "1";
			}else{
				if(flagFkqz && flagX && flagLfk){
					
				}else{
					if(flagFkqz || flagX || flagLfk){
						if(flagFkqz){
							return "2";
						}else if(flagX){
							return "3";
						}else if(flagLfk){
							return "4";
						}
					}
				}
			}
		}else{
			falg = "";
		}
		return falg;
	}
//	@RequestMapping(value = { "merchantSDMT/checkMerchantId" }, method = { RequestMethod.POST })
//	@ResponseBody
//	public String checkMerchantId(HttpServletRequest req,
//			HttpServletResponse resp, Model model, String merchantId) {
//		String falg = "0";
//		if (merchantId != null && !"".equals(merchantId)) {
//			// 先校验中间库，中间库不重复再校验其他库,一旦有一个重复则返回1.
//			if (this.MerchantSDMTService.checkMerchantId(merchantId) > 0) {
//				return "1";
//			} else{
//				if(MerchantBaseService.queryList(merchantId).size() > 0 
//						&& this.MerchantBakService.queryList(merchantId).size() > 0
//						&& (this.RUSwitchService.queryList(merchantId).size() > 0
//								&& this.RUService.queryList(merchantId).size() > 0
//								&& this.RU1Service.queryList(merchantId).size() > 0
//								&& this.RU2Service.queryList(merchantId).size() > 0) ){
//					
//				}else{
//					// 校验75库，75库不重复再校验其他库,一旦有一个重复则返回2.
//					if (MerchantBaseService.queryList(merchantId).size() > 0) {
//						return "2";
//					//校验备库，备库不重复再校验其他库,一旦有一个重复则返回3
//					} else if (this.MerchantBakService.queryList(merchantId).size() > 0) {
//						return "3";
//					//校验福卡库，福卡库不重复再校验其他库,一旦有一个重复则返回4
//					} else if (this.RUSwitchService.queryList(merchantId).size() > 0
//							|| this.RUService.queryList(merchantId).size() > 0
//							|| this.RU1Service.queryList(merchantId).size() > 0
//							|| this.RU2Service.queryList(merchantId).size() > 0) {
//						return "4";
//					}
//				}
//			}
//			
//		} else {
//			falg = "";
//		}
//		return falg;
//	}
	
//	public String checkMerchantId(HttpServletRequest req,
//			HttpServletResponse resp, Model model, String merchantId) {
//		String falg = "0";
//		if (merchantId != null && !"".equals(merchantId)) {
//			// 先校验中间库，中间库不重复再校验其他库,一旦有一个重复则返回1.
//			if (this.MerchantSDMTService.checkMerchantId(merchantId) > 0) {
//				return "1";
//				// 校验75库，75库不重复再校验其他库,一旦有一个重复则返回2.
//			} else if (MerchantBaseService.queryList(merchantId).size() > 0) {
//				return "2";
//				//校验备库，备库不重复再校验其他库,一旦有一个重复则返回3
//			} else if (this.MerchantBakService.queryList(merchantId).size() > 0) {
//				return "3";
//				//校验福卡库，福卡库不重复再校验其他库,一旦有一个重复则返回4
//			} else if (this.RUSwitchService.queryList(merchantId).size() > 0
//					|| this.RUService.queryList(merchantId).size() > 0
//					|| this.RU1Service.queryList(merchantId).size() > 0
//					|| this.RU2Service.queryList(merchantId).size() > 0) {
//				return "4";
//			}
//		} else {
//			falg = "";
//		}
//		return falg;
//	}
	/**
	 * 判断父商户是否存在 
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/checkMerchantfId" }, method = { RequestMethod.POST })
	@ResponseBody
	public String checkMerchantfId(HttpServletRequest req,
			HttpServletResponse resp, Model model, String fmrchno) {
		String falg = "0";
		if (fmrchno != null && !"".equals(fmrchno)) {
			if(!(this.MerchantBakService.queryList(fmrchno).size() > 0)){
				if (!(this.MerchantSDMTService.checkMerchantId(fmrchno) > 0)) {
					return "3";
				}else{
					return "1";
				}
			}
		} else {
			falg = "";
		}
		return falg;
	}
	/**
	 * 同步商户账号信息到各系统
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId	商户编号
	 * @param synchronousType	系统类型
	 * @return
	 */
	@RequestMapping(value = { "merchantSDMT/synchronous" }, method = { RequestMethod.POST })
	@ResponseBody
	public String synchronous(HttpServletRequest req, HttpServletResponse resp,Model model, String merchantId, String synchronousType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String checkMrchFlag=this.checkMerchantId(req, resp, model, merchantId);
		if(checkMrchFlag!=null&&!"".equals(checkMrchFlag)&&!"0".equals(checkMrchFlag)&&!"1".equals(checkMrchFlag)){
			String infoStr="";
			if("2".equals(checkMrchFlag)){
				infoStr="收单系统已存在该商户号的商户";				
			}else if("3".equals(checkMrchFlag)){
				infoStr="X平台已存在该商户号的商户";
			}else if("4".equals(checkMrchFlag)){
				infoStr="老福卡系统已存在该商户号的商户";
			}
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, infoStr);					
		}else{
			// flag 空：参数无效；-1.失败；0.成功
			String flag = "0";
			// 获取中间表商户实体，再同步
			MerchantSDMT info = MerchantSDMTService.queryInfo(merchantId);
			if (merchantId != null && !"".equals(merchantId)&& synchronousType != null && !"".equals(synchronousType)&& info != null) {
				try {
					if ("SD".equals(synchronousType)) {
						//192.168.6.12:1521:fksd Posp改为75,先删除，后新增
						MerchantBaseService.synchronousPospMerchant(info);
					} else if ("X".equals(synchronousType)) {//ok
						log.info("开始同步X平台主库");
						//主库
						String seqMerchantIdAndSeqMrchAccXId=CortexService.synchronousCortexMerchant(info);
						//备库
						log.info("开始同步X平台备库");
						this.MerchantBakService.synchronousCortexBakMerchant(info, seqMerchantIdAndSeqMrchAccXId);
						//同步成功后把seqMerchantId和SeqMrchAccXId保存到中间库
						info.setSeqMrchId(Long.valueOf(seqMerchantIdAndSeqMrchAccXId.split("#")[0].toString()));
						info.setSeqMrchAccXId(Long.valueOf(seqMerchantIdAndSeqMrchAccXId.split("#")[1].toString()));
					} else if ("YUFU".equals(synchronousType)) {
						//jdbc:sqlserver://192.168.10.24:1433;databaseName=switch 表名RU
						this.RUSwitchService.synchronousYufuOldSwitchMerchant(info);
						//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu; 表名RU
						this.RUService.synchronousYufuOldMerchant(info);
						//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu1 表名RU
						this.RU1Service.synchronousYufuOldMerchant(info);
						//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu2 表名RU
						this.RU2Service.synchronousYufuOldMerchant(info);
					} 				
					map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
					map.put(SysConst.RESULT_MSG, "商户("+info.getMerchantId()+")同步成功。");
					
				} catch (Exception e) {
					flag="-1";
					String errorStr=e.getMessage();
					if(errorStr.length()>150){
						errorStr=errorStr.substring(0,150);
					}
					if(errorStr.contains("###")){
						errorStr=errorStr.split("###")[1].toString();
					}
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "商户("+info.getMerchantId()+")同步失败，失败原因："+errorStr);				
//					map.put(SysConst.RESULT_MSG, "商户("+info.getMerchantId()+")同步失败，失败原因："+"系统罢工了请稍后再试");				
				}
				// 同步后修改中间表同步状态字段
				if ("0".equals(flag)) { // 同步成功
					if ("SD".equals(synchronousType)) {
						info.setSdFlag("1");
					} else if ("X".equals(synchronousType)) {
						info.setxFlag("1");
						info.setxBakFlag("1");
					} else if ("YUFU".equals(synchronousType)) {
						info.setYufuFlag("1");
					} 
				} else if ("-1".equals(flag)) { // 同步失败
					String errMsg=map.get(SysConst.RESULT_MSG) == null ? "": map.get(SysConst.RESULT_MSG).toString();
					if ("SD".equals(synchronousType)) {
						info.setSdFlag("2");
						info.setSdError(errMsg);
					} else if ("X".equals(synchronousType)) {
						info.setxFlag("2");
						info.setxError(errMsg);
						info.setxBakFlag("2");
						info.setxBakError(errMsg);					
					} else if ("YUFU".equals(synchronousType)) {
						info.setYufuFlag("2");
						info.setYufuError(errMsg);
					} 
				}
				log.info("更新临时表");
				this.MerchantSDMTService.edit(info);
			}				
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
			merchantXRecord.setModifyModule(ms.get("MERCHANT_TERMINAL_MODULE_NAME"));
		}else{
			merchantXRecord.setModifyModule(" ");
		}
		
		//修改模块
//		merchantXRecord.setModifyModule("商户终端-商户同步");
//		/
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
