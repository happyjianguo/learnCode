package cn.yufu.SDMTPlatform.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.SysDict;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTExcel;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTKey;
import cn.yufu.SDMTPlatform.service.MerchantSDMTService;
import cn.yufu.SDMTPlatform.service.TerminalSDMTService;
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
import cn.yufu.cortex.service.AreaService;
import cn.yufu.cortex.service.CortexTerminalService;
import cn.yufu.cortex.service.NumDescrService;
import cn.yufu.cortex.service.SysParameterService;
import cn.yufu.cortexBak.service.CortexBakTerminalService;
import cn.yufu.cortexBak.service.MerchantBakService;
import cn.yufu.posp.dao.MerchantBaseMapper;
import cn.yufu.posp.dao.MerchantExtraMapper;
import cn.yufu.posp.entity.MerchantBase;
import cn.yufu.posp.entity.MerchantExtra;
import cn.yufu.posp.entity.PManager;
import cn.yufu.posp.service.MerchantBaseService;
import cn.yufu.posp.service.PManagerService;
import cn.yufu.posp.service.PospTerminalService;
import cn.yufu.utils.HttpRequestUtil;
import cn.yufu.utils.POIUtils;
import cn.yufu.utils.excel.ImportExcel;
import cn.yufu.yufuOld.service.RUService;
import cn.yufu.yufuOld.service.YufuOldTerminalService;
import cn.yufu.yufuOld1.service.RU1Service;
import cn.yufu.yufuOld1.service.YufuOld1TerminalService;
import cn.yufu.yufuOld2.service.RU2Service;
import cn.yufu.yufuOld2.service.YufuOld2TerminalService;
import cn.yufu.yufuOldSwitch.service.RUSwitchService;
import cn.yufu.yufuOldSwitch.service.YufuOldSwitchTerminalService;
import net.sf.json.JSONArray;

@SuppressWarnings("deprecation")
@Controller("sdmtpf.TerminalSDMTController")
public class TerminalSDMTController {
	Log log = Log.getLog(TerminalSDMTController.class);
	
	@Autowired
	@Qualifier("sdmtpf.MerchantSDMTService")
	private MerchantSDMTService MerchantSDMTService;
	@Autowired
	@Qualifier("sdmtpf.TerminalSDMTService")
	private TerminalSDMTService TerminalSDMTService;
	@Autowired
	@Qualifier("cortex.AreaService")
	private AreaService AreaService;
	@Autowired
	@Qualifier("cortex.NumDescrService")
	private NumDescrService NumDescrService;
	@Autowired
	@Qualifier("posp.TerminalService")
	private PospTerminalService PospTerminalService;	
	@Autowired
	@Qualifier("cortex.CortexTerminalService")
	private CortexTerminalService CortexTerminalService;	
	@Autowired
	@Qualifier("cortexBak.CortexBakTerminalService")
	private CortexBakTerminalService CortexBakTerminalService;	
	@Autowired
	@Qualifier("yufuOldSwitch.YufuOldSwitchTerminalService")
	private YufuOldSwitchTerminalService YufuOldSwitchTerminalService;	
	@Autowired
	@Qualifier("yufuOld.YufuOldTerminalService")
	private YufuOldTerminalService YufuOldTerminalService;	
	@Autowired
	@Qualifier("yufuOld1.YufuOld1TerminalService")
	private YufuOld1TerminalService YufuOld1TerminalService;	
	@Autowired
	@Qualifier("yufuOld2.YufuOld2TerminalService")
	private YufuOld2TerminalService YufuOld2TerminalService;	
	@Autowired
	@Qualifier("cortex.SysParameterService")
	private SysParameterService SysParameterService;
	@Autowired
	@Qualifier("posp.pManagerService")
	private PManagerService pManagerService;
	
	@Autowired
	@Qualifier("posp.MerchantBaseService")	
	private MerchantBaseService MerchantBaseService;
	@Autowired
	@Qualifier("cortexBak.MerchantBakService")	
	private MerchantBakService MerchantBakService;
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
	@Qualifier("yufuOld.RUService")	
	private RUService RUService;
	
	@Autowired
	@Qualifier("posp.MerchantBaseDao")
	private MerchantBaseMapper MerchantBaseDao;
	@Autowired
	@Qualifier("posp.MerchantExtraDao")
	private MerchantExtraMapper MerchantExtraDao;
	@Autowired
	@Qualifier("cortex.MerchantDao")
	private MerchantMapper MerchantDao;
	@Autowired
	@Qualifier("cortex.MerchantXDao")
	private MerchantXMapper MerchantXDao;
	@Autowired
	@Qualifier("cortex.MrchAccXDao")
	private MrchAccXMapper MrchAccXDao;
	
	/*********************** EXCEL导出模板 ********************************/
	private HSSFWorkbook workbook = null;
	private HSSFCellStyle titleStyle = null;
	private HSSFCellStyle dataStyle = null;
	private HSSFCellStyle headStyle = null;
	/**
	 * 导入终端数据
	 * TODO
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("SDMTPlatform:terminal:add")
	@RequestMapping(value = "/terminalSDMT/import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,RedirectAttributes redirectAttributes) {
		log.debug("批量导入终端数据");
		/***
		 * 1,解析Excel 得到List<TerminalSDMTExcel>
		 * 2，遍历，并处理list｛
		 * 		1，判断TerminalSDMTExcel是否为空
		 * 		2，判断id是否为空
		 * 		3，检验Excel中的数据是否合法
		 * 		4，TerminalSDMTExcel转TerminalSDMT
		 * 		5，入库｛
		 * 				1，判断是否存在：存在则跳过
		 * 				2，入库
		 * 				｝
		 * ｝
		 * 3、提示批量导入商户结果
		 */
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TerminalSDMTExcel> list = ei.getDataList(TerminalSDMTExcel.class);
			TerminalSDMT Terminal = null;
			for (int i=0; i < list.size(); i++) {
				TerminalSDMTExcel entity = list.get(i);
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
							Terminal = excelSwitchEntity(entity);
							
							boolean flagSdmtpf = false;	//75临时表标志
							boolean flagFkqz = false;//福卡前置标志
							boolean flagX = false;//X平台标志
							boolean flagLfk = false;//福卡系统标志
							String flag = "0";
							/**
							 * 1、先判断商户号是否存在于临时表
							 * 2、如果不存在，将其加入临时表
							 */
							String merchantId = Terminal.getMerchantId();
							/*if (merchantId != null && !"".equals(merchantId)) {
								Map<String,Boolean> mapb = isExistMid(merchantId);
								flagSdmtpf = mapb.get("flagSdmtpf");
								flagFkqz = mapb.get("flagFkqz");
								flagX = mapb.get("flagX");
								flagLfk = mapb.get("flagLfk");
								//空，参数无效；0，没有重复；1，与中间库终端号重复；2.与老福卡系统终端号重复;3.与X平台终端号重复
								String checkMrchFlag=this.checkTerminalKey(null, null, null, merchantId, Terminal.getTermCode());
								if("1".equals(checkMrchFlag)){
									//1，与中间库终端号重复；
									flag = "-3";
								}else if("2".equals(checkMrchFlag)){
									//2.与老福卡系统终端号重复
									flag = "-4";
								}else if("3".equals(checkMrchFlag)){
									//与X平台终端号重复
									flag = "-5";
								}else if("0".equals(checkMrchFlag)){
									//没有重复
									successNum++;
									// 先校验中间库.
									if (!flagSdmtpf) {
										// 不存在
										if((flagFkqz&&flagX&&flagLfk)){
											log.debug("保存终端异常：");
//											failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败:此商户在临时表中不存在");
											try{
												//得到临时表数据
												MerchantSDMT merchant = toMerchant(merchantId);
												MerchantXRecord merchantXRecord = null;
												try {
													//插入
//														Map<String,Object> map2 = TerminalSDMTService.save(Terminal);
													Map<String, Object> map1 = MerchantSDMTService.saveMerchantAndTermial(merchant,merchantXRecord,true,Terminal);
													flag = (String) map1.get(SysConst.RESULT);
												} catch (Exception e) {
													log.debug("保存终端异常：{}", e);
													failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e.getMessage());
												}
											}catch(Exception e1){
												log.debug("保存老商户至临时表异常：{}", e1);
												failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e1.getMessage());
											}
										}
									}else{
										//中间库中存在
										try {
											//插入
											Map<String,Object> maptminal = TerminalSDMTService.save(Terminal);
											flag = (String) maptminal.get(SysConst.RESULT);
										} catch (Exception e) {
											log.debug("保存终端异常：{}", e);
											failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e.getMessage());
										}
									}
								}else{
									//参数无效
									flag = "-6";
								}
							}else{
								flag = "-2";
							}*/
							if (merchantId != null && !"".equals(merchantId)) {
								Map<String,Boolean> mapb = isExistMid(merchantId);
								flagSdmtpf = mapb.get("flagSdmtpf");
								flagFkqz = mapb.get("flagFkqz");
								flagX = mapb.get("flagX");
								flagLfk = mapb.get("flagLfk");
								// 先校验中间库.
								if (!flagSdmtpf) {
									// 不存在
									if((flagFkqz&&flagX&&flagLfk)){
										log.debug("保存终端异常：");
//										failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败:此商户在临时表中不存在");
										try{
											//得到临时表数据
											MerchantSDMT merchant = toMerchant(merchantId);
											MerchantXRecord merchantXRecord = null;
											try {
												//插入
//													Map<String,Object> map2 = TerminalSDMTService.save(Terminal);
												Map<String, Object> map1 = MerchantSDMTService.saveMerchantAndTermial(merchant,merchantXRecord,true,Terminal);
												flag = (String) map1.get(SysConst.RESULT);
											} catch (Exception e) {
												log.debug("保存终端异常：{}", e);
												failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e.getMessage());
											}
										}catch(Exception e1){
											log.debug("保存老商户至临时表异常：{}", e1);
											failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e1.getMessage());
										}
									}
								}else{
									//中间库中存在
									try {
										//插入
										Map<String,Object> maptminal = TerminalSDMTService.save(Terminal);
										flag = (String) maptminal.get(SysConst.RESULT);
									} catch (Exception e) {
										log.debug("保存终端异常：{}", e);
										failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ e.getMessage());
									}
								}
							}else{
								flag = "-2";
							}
							
							//插入
//							Map<String,Object> mapterminal = TerminalSDMTService.save(Terminal);
//							String flag = (String) mapterminal.get(SysConst.RESULT);
							/**
							 * =0,成功
							 * =-1,失败
							 * =-2.商户号为空
							 * 
									flag = "-3";与中间库终端号重复
									flag = "-4";与老福卡系统终端号重复
									flag = "-5";与X平台终端号重复
									flag = "-6";终端号参数无效
							 */
							if("-6".equals(flag)){
								//商户号为空
								failureMsg.append("\n终端编号" + entity.getTermCode()+ "终端号参数无效; ");
								failureNum++;
							}else  if("-5".equals(flag)){
								//商户号为空
								failureMsg.append("\n终端编号" + entity.getTermCode()+ "与X平台终端号重复; ");
								failureNum++;
							}else  if("-4".equals(flag)){
								//商户号为空
								failureMsg.append("\n终端编号" + entity.getTermCode()+ "与老福卡系统终端号重复; ");
								failureNum++;
							}else  if("-3".equals(flag)){
								//商户号为空
								failureMsg.append("\n终端编号" + entity.getTermCode()+ "与中间库终端号重复; ");
								failureNum++;
							}else  if("-2".equals(flag)){
								//商户号为空
								failureMsg.append("\n终端编号" + entity.getTermCode()+ " 商户号为空; ");
								failureNum++;
							}else if("-1".equals(flag)){
								//商户已存在
								failureMsg.append("\n终端编号" + entity.getTermCode()+ " 已存在; ");
								failureNum++;
							}else if("0".equals(flag)){
								//成功
								successNum++;
							}
						}else{
							//不合法
							failureMsg.append("\n终端编号:" + entity.getTermCode()+ ",失败原因:"+checkcontext.toString());
							failureNum++;
						}
					}else{
						if(Strings.isNullOrEmpty(entity.getMerchantId()) || Strings.isNullOrEmpty(entity.getTermCode())){
							failureMsg.append("\n失败原因:第"+ (i+3)+"行，商户、终端编号为必填项");
							failureNum++;
						}else{
							failureMsg.append("\n终端编号:" + entity.getTermCode()+ ",失败原因:所有项均为必填项 ");
							failureNum++;
						}
					}
				}
				catch (Exception ex) {
					failureMsg.append("\n终端编号 " + entity.getTermCode() + " 导入失败："+ ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条终端信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条终端信息"+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入终端信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:/terminalSDMT/page";
	}
	/******************************************************1*******************************************************/
	/**
	 * 判断MerchantExcel是否为空
	 * @param entity
	 * @return
	 */
	public Boolean valiBoolean(TerminalSDMTExcel entity){ 
		log.debug("验证字段是否为空");
		if(null == entity){
			return false;
		}
		List<String> li = new LinkedList<String>();
		li.add(entity.getMerchantId());
		li.add(entity.getTermCode());
		li.add(entity.getEdcType());
		li.add(entity.getSoftVer());
		li.add(entity.getDownloadFlag());
		li.add(entity.getTermAddress());
		li.add(entity.getProvince());
		li.add(entity.getCityNo());
		li.add(entity.getZone());
		li.add(entity.getActDate());
		li.add(entity.getActTime());
		li.add(entity.getAddDate());
		li.add(entity.getTermTel());
		li.add(entity.getState());
		li.add(entity.getxTimezone());
		li.add(entity.getConsumpCategory());
		li.add(entity.getUpgradeDate());
		li.add(entity.getStoreContacts());
		li.add(entity.getStorePhone());
		li.add(entity.getTerminalArea());
		li.add(entity.getMerchantAdvisor());
		li.add(entity.getTerminalPosition());
		for(int i=0;i<li.size();i++){
			if(Strings.isNullOrEmpty(li.get(i))){
				return false;
			}
		}
		return true;
	}
	/******************************************************1*******************************************************/
	/*********************************************************2*****************************************************/
	public TerminalSDMT excelSwitchEntity(TerminalSDMTExcel entity){ 
		TerminalSDMT Terminal = new TerminalSDMT();
		Terminal.setMerchantId(entity.getMerchantId().trim());
		Terminal.setTermCode(entity.getTermCode().trim());
		Terminal.setEdcType(entity.getEdcType().trim().split("_")[1]);
		Terminal.setSoftVer(entity.getSoftVer().trim());
		Terminal.setDownloadFlag(entity.getDownloadFlag().trim().split("_")[1]);
		Terminal.setTermAddress(entity.getTermAddress().trim());
		Terminal.setProvince(entity.getProvince().trim().split("_")[1]);
		Terminal.setCityNo(entity.getCityNo().trim().split("_")[1]);
		Terminal.setZone(entity.getZone().trim().split("_")[1]);
		Terminal.setActDate(Integer.parseInt(entity.getActDate().trim()));
		Terminal.setActTime(Long.parseLong(entity.getActTime().trim()));
		Terminal.setAddDate(entity.getAddDate().trim());
		Terminal.setTermTel(entity.getTermTel().trim());
		Terminal.setState(Integer.parseInt(entity.getState().trim()));
		Terminal.setxTimezone(entity.getxTimezone().trim());
		Terminal.setConsumpCategory(entity.getConsumpCategory().trim().split("_")[1]);
		
		Terminal.setUpgradeDate(entity.getUpgradeDate().trim());
		Terminal.setStoreContacts(entity.getStoreContacts().trim());
		Terminal.setStorePhone(entity.getStorePhone().trim());
		Terminal.setTerminalArea(entity.getTerminalArea().trim().split("_")[1]);
		Terminal.setMerchantAdvisor(entity.getMerchantAdvisor().trim().split("_")[1]);
		Terminal.setUpgradeVersion("0");
		Terminal.setTerminalPosition(entity.getTerminalPosition().trim());
		return Terminal;
	}
	/*********************************************************2*****************************************************/
	/*********************************************************3*****************************************************/
	/**
	 * 验证必输项是否为空 
	 * @param entity
	 * @return
	 */
	public Map<String,Object> checkMerchantExcel(TerminalSDMTExcel entity){
		Map<String,Object> map = new HashMap<String,Object>();
		Boolean flag = true;
		StringBuilder content = new StringBuilder();
		//按照页面输入进行校验,所有下拉框均不校验
		/**
		 * 商户编号
		 * 1，判断是否为数字
		 * 2，判断是否为15位
		 */
		String merchantIds = entity.getMerchantId().trim();
		if(isNumeric(entity.getMerchantId().trim())){
			if(!(equalNum(entity.getMerchantId().trim(),15))){
				content.append("商户编号必须为15位数字，");
				flag = false;
			}else {
				boolean flagSdmtpf = false;	//75临时表标志
				boolean flagFkqz = false;//福卡前置标志
				boolean flagX = false;//X平台标志
				boolean flagLfk = false;//福卡系统标志
				if (merchantIds != null && !"".equals(merchantIds)) {
					Map<String,Boolean> map1 = isExistMid(merchantIds);
					flagSdmtpf = map1.get("flagSdmtpf");
					flagFkqz = map1.get("flagFkqz");
					flagX = map1.get("flagX");
					flagLfk = map1.get("flagLfk");
					if(!flagSdmtpf){
						//临时表无
						if(!(flagFkqz && flagX && flagLfk)){
							content.append("商户编号不存在，");
							flag = false;
//							if(flagFkqz || flagX || flagLfk){
//							//至少有一个平台有
//								content.append("商户编号没有同时存在于福卡前置、X平台、老福卡系统不存在，");
//								flag = false;
//							}else{
//							//都没有
//								content.append("商户编号不存在，");
//								flag = false;
//							}
						}
					}
				}
			}
		}else{
			content.append("商户编号必须为数字，");
			flag = false;
		}
		/**
		 * 终端编号
		 * 1，判断终端设备类型
		 * 1，判断是否为数字
		 * 2，判断是否为8位
		 * 
		 */
		if("APP".equals(entity.getEdcType().trim().split("_")[1])){
			if(isNumerAndC(entity.getTermCode().trim())){
				if(!(isNum(entity.getTermCode().trim(),8))){
					content.append("终端编号不能超过8位，");
					flag = false;
				}else{
					String termCode = entity.getTermCode().trim();
					String merchantId = entity.getMerchantId().trim();
					if (termCode != null && !"".equals(termCode)) {
						if (TerminalSDMTService.checkTerminal(merchantId, termCode) > 0) {
							content.append("终端编号在中间库已存在，");
							flag = false;
						} else if (YufuOldSwitchTerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOldTerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOld1TerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOld2TerminalService.getTerminalListByTermCode(termCode) > 0) {
							content.append("终端编号在老福卡系统已存在，");
							flag = false;
						} else if (CortexBakTerminalService
								.getTerminalListByTermCode(termCode) > 0) {
							content.append("终端编号在X平台已存在，");
							flag = false;
						}
					}
				}
			}else{
				content.append("终端设备类型为APP,终端编号必须由大写首字母+数字组成，");
				flag = false;
			}
		}else{
			//非APP
			if(isNumeric(entity.getTermCode().trim())){
				if(!(isNum(entity.getTermCode().trim(),8))){
					content.append("终端编号不能超过8位，");
					flag = false;
				}else{
					String termCode = entity.getTermCode().trim();
					String merchantId = entity.getMerchantId().trim();
					if (termCode != null && !"".equals(termCode)) {
						if (TerminalSDMTService.checkTerminal(merchantId, termCode) > 0) {
							content.append("终端编号在中间库已存在，");
							flag = false;
						} else if (YufuOldSwitchTerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOldTerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOld1TerminalService.getTerminalListByTermCode(termCode) > 0
								|| YufuOld2TerminalService.getTerminalListByTermCode(termCode) > 0) {
							content.append("终端编号在老福卡系统已存在，");
							flag = false;
						} else if (CortexBakTerminalService
								.getTerminalListByTermCode(termCode) > 0) {
							content.append("终端编号在X平台已存在，");
							flag = false;
						}
					}
				}
			}else{
				content.append("终端设备类型为非APP,终端编号必须由数字组成，");
				flag = false;
			}
		}
		/**
		 * 终端软件版本
		 * 1，判断是否为10位
		 */
		if(!("common".equals(entity.getSoftVer().trim()))){
			content.append("终端类型必须为common，");
			flag = false;
		}
		
		/**
		 * 终端所在位置
		 * 1，判断是否为30位
		 */
		if(!(isNum(entity.getTermAddress().trim(),30))){
			content.append("终端所在位置不能超过30位，");
			flag = false;
		}
		/**
		 * 密钥生效日期
		 */
		if(!(isDate(entity.getActDate().trim()))){
			content.append("密钥生效日期不合法，");
			flag = false;
		}
		/**
		 * 密钥生效时间
		 * 1，判断是否为数字
		 * 2，判断是否为8位
		 */
		if(isNumeric(entity.getActTime().trim())){
			if(!(equalNum(entity.getActTime().trim(),6))){
				content.append("密钥生效时间必须为6位数字，");
				flag = false;
			}
		}else{
			content.append("密钥生效时间必须为数字，");
			flag = false;
		}
		/**
		 * 增加时间
		 */
		if(!(isDate(entity.getAddDate().trim()))){
			content.append("增加时间不合法，");
			flag = false;
		}
		/**
		 * 终端使用的电话号码
		 * 1，判断是否为数字
		 * 2，判断是否为20位
		 */
		if(isNumeric(entity.getTermTel().trim())){
			if(!(isNum(entity.getTermTel().trim(),20))){
				content.append("终端使用的电话号码不能超过20位，");
				flag = false;
			}
		}else{
			content.append("终端使用的电话号码必须为数字，");
			flag = false;
		}
		/**
		 * 裕福原有状态
		 * 1，判断是否为数字
		 * 2，判断是否为5位
		 */
		if(isNumeric(entity.getState().trim())){
			if(!(isNum(entity.getState().trim(),5))){
				content.append("裕福原有状态不能超过5位，");
				flag = false;
			}
		}else{
			content.append("裕福原有状态必须为数字，");
			flag = false;
		}
		/**
		 * 费率(单位：%)
		 * 1，判断是否为10位
		 */
		if(!(isNum(entity.getxTimezone().trim(),10))){
			content.append("费率(单位：%)不能超过10位，");
			flag = false;
		}
		/**
		 * 升级日期
		 */
		if(!(isDate(entity.getUpgradeDate().trim()))){
			content.append("增加时间不合法，");
			flag = false;
		}
		/**
		 * 门店电话
		 * 1，判断是否为数字
		 * 2，判断是否为16位
		 */
		if(isNumeric(entity.getStorePhone().trim())){
			if(!(isNum(entity.getStorePhone().trim(),16))){
				content.append("门店电话不能超过16位，");
				flag = false;
			}
		}else{
			content.append("门店电话必须为数字，");
			flag = false;
		}
		/**
		 * 门店联系人
		 * 1，判断是否为30位
		 */
		if(!(isNum(entity.getStoreContacts().trim(),30))){
			content.append("门店联系人不能超过30位，");
			flag = false;
		}
		/**
		 * 终端装机地址
		 * 1，判断是否为30位
		 */
		if(!(isNum(entity.getTerminalPosition().trim(),30))){
			content.append("终端装机地址不能超过30位，");
			flag = false;
		}
		//终端所选区域是否和商户在同一个区域
//		try{
//			MerchantSDMT merchant =MerchantSDMTService.queryInfo(entity.getMerchantId().trim());
//			if(null == merchant){
//				content.append("此商户号不存在，");
//				flag = false;
//			}else{
//				if(merchant.getMerchantArea().trim().equals(entity.getTerminalArea().trim().split("_")[1])){
//				}else{
//					content.append("终端所选区域是否和商户不在同一个区域，");
//					flag = false;
//				}
//			}
//		}catch(Exception eee){
//			content.append("终端区域不合法，");
//			flag = false;
//		}
		//商户顾问
		try{
			String manager =entity.getMerchantAdvisor().trim().split("_")[1];
			List<PManager> storeManagerList = pManagerService.findListByRoleIdAndArea("0", entity.getTerminalArea().trim().split("_")[1]);
			if(!check(storeManagerList,manager)){
				content.append("商户顾问不合法，");
				flag = false;
			}
		}catch(Exception eee){
			content.append("商户顾问不合法，");
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
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public boolean isNumerAndC(String str){ 
		Pattern pattern = Pattern.compile("[A-Z]+[0-9]*"); 
//		/^[A-Z]+[0-9]*$/g; 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
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
	/*********************************************************4*****************************************************/
	/**
	 * 下载导入终端数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return TODO
	 */
	@RequiresPermissions("SDMTPlatform:terminal:view")
	@RequestMapping(value = "/terminalSDMT/template")
	public void importFileTemplate(HttpServletRequest req,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		log.debug("下载导入终端数据模板");
		try{
			workbook = new HSSFWorkbook();// excel文件对象
			HSSFSheet sheet1 = workbook.createSheet("终端信息");// 工作表对象
			// 设置标题样式
			this.setHeadCellStyles(workbook, sheet1);
			// 设置列头样式
			this.setTitleCellStyles(workbook, sheet1);
			// 设置数据样式
			this.setDataCellStyles(workbook, sheet1);
			// 创建一个隐藏页、隐藏数据集和名称管理
			this.creatHideSheet(workbook);
			// 创建标题和列头数据
			String headName = "终端信息";
			this.creatAppRowHead(sheet1, headName);
			// 设置下拉框
			this.createSelectValidate(sheet1);
			
			/**************************** 输出流 *****************************************/
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = POIUtils.encodeFileName(req, headName);
			OutputStream os = response.getOutputStream();// 取得输出流
			response.setHeader("Content-disposition", "attachment;filename="+ filename + ".xls");
			workbook.write(os);
			os.close();

			System.out.println("导出成功!");
//			return null;
		}catch(Exception e){
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
//		return "redirect:/terminalSDMT/page";
	}
	/****************************************************设置Excel start****************************************************/
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
		sheet.setColumnWidth(0, 7000);//商户编号
//		sheet.setColumnWidth(1, 7000);//商户名称
		sheet.setColumnWidth(1, 4000);//终端编号
		sheet.setColumnWidth(2, 4000);//终端设备型号
		sheet.setColumnWidth(3, 4000);//终端软件版本
		sheet.setColumnWidth(4, 7000);//参数下载标志
		sheet.setColumnWidth(5, 7000);//终端所在位置
		sheet.setColumnWidth(6, 4000);//省份
		sheet.setColumnWidth(7, 4000);//城市
		sheet.setColumnWidth(8, 4000);//区
		sheet.setColumnWidth(9, 4000);//密钥生效日期
		sheet.setColumnWidth(10, 4000);//密钥生效时间
		sheet.setColumnWidth(11, 4000);//增加时间
		sheet.setColumnWidth(12, 7000);//终端使用的电话号码
		sheet.setColumnWidth(13, 7000);//裕福原有状态
		sheet.setColumnWidth(14, 7000);//费率(单位：%)
		sheet.setColumnWidth(15, 4000);//消费场景
		
		sheet.setColumnWidth(16, 4000);//升级日期
		sheet.setColumnWidth(17, 4000);//门店联系人
		sheet.setColumnWidth(18, 7000);//门店电话
		sheet.setColumnWidth(19, 4000);//区域
		sheet.setColumnWidth(20, 4000);//商户顾问
		sheet.setColumnWidth(21, 7000);//终端装机地址
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
		
		/*********************************************** 创建区域、商户顾问、**********************************************/
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
		
		
		/********************************* 创建其他码表sheet：终端设备型号、参数下载标志、消费场景**********************************/
		HSSFSheet otherSheet = workbook.createSheet("otherSheet");
		//终端设备型号
		HSSFRow edcTypeRow = otherSheet.createRow(0);
		List<String> edcTypeRowList = new ArrayList<String>();
		//下拉框list超过255poi报错
		List<SysDict>  edcTypeList = edcTypeTypeList();//${model.id}---${model.descr}
		edcTypeRowList.add("edcType");
		for (SysDict sysDict : edcTypeList) {
			edcTypeRowList.add(sysDict.getLabel() + "_" + sysDict.getValue());
		}
		this.creatRow(edcTypeRow, edcTypeRowList);
		name = workbook.createName();
		name.setNameName("edcType");
		name.setRefersToFormula("otherSheet!$B$1:$"+ this.getcellColumnFlag(edcTypeRowList.size()) + "$1");
		
		//参数下载标志
		HSSFRow downloadFlagRow = otherSheet.createRow(1);
		List<String> downloadFlagRowList = new ArrayList<String>();
		List<SysDict> downloadFlagList = downloadFlagList();
		downloadFlagRowList.add("downloadFlag");
		for (SysDict sysDict : downloadFlagList) {
			downloadFlagRowList.add(sysDict.getLabel() + "_" + sysDict.getValue());
		}
		this.creatRow(downloadFlagRow, downloadFlagRowList);
		name = workbook.createName();
		name.setNameName("downloadFlag");
		name.setRefersToFormula("otherSheet!$B$2:$"+ this.getcellColumnFlag(downloadFlagRowList.size()) + "$2");
		
		//消费场景
		HSSFRow consumpCategoryRow = otherSheet.createRow(2);
		List<String> consumpCategoryRowRowList = new ArrayList<String>();
		List<SysParameter> consumpList=SysParameterService.queryList("CONSUMP_CATEGORY", "");
		consumpCategoryRowRowList.add("consumpCategory");
		for (SysParameter sysParameter : consumpList) {
			consumpCategoryRowRowList.add(sysParameter.getParamName()+"_"+sysParameter.getParamValue());
		}
		this.creatRow(consumpCategoryRow, consumpCategoryRowRowList);
		name = workbook.createName();
		name.setNameName("consumpCategory");
		name.setRefersToFormula("otherSheet!$B$3:$"+ this.getcellColumnFlag(consumpCategoryRowRowList.size()) + "$3");
		workbook.setSheetHidden(workbook.getSheetIndex("otherSheet"), true);
	}
	/**
	 * 创建标题应用列头
	 * @param userinfosheet1
	 * @param userName
	 */
	public void creatAppRowHead(HSSFSheet userinfosheet1, String headName) {
		// 设置标题
		HSSFRow rowHead = userinfosheet1.createRow(0);
		userinfosheet1.addMergedRegion(new Region(0, (short) 0, 0, (short) 21));
		POIUtils.createCell(rowHead, (short) 0, headName, headStyle);
		rowHead.setHeight((short) (27 * 21));

		// 设置表头
		HSSFRow row = userinfosheet1.createRow(1);

		HSSFCell merchantIdCell = row.createCell(0);
		merchantIdCell.setCellValue("商户编号");
		merchantIdCell.setCellStyle(titleStyle);

		HSSFCell merchantEnameCell = row.createCell(1);
		merchantEnameCell.setCellValue("终端编号");
		merchantEnameCell.setCellStyle(titleStyle);

		HSSFCell abbrCnameCell = row.createCell(2);
		abbrCnameCell.setCellValue("终端设备型号");
		abbrCnameCell.setCellStyle(titleStyle);

		HSSFCell abbrEnameCell = row.createCell(3);
		abbrEnameCell.setCellValue("终端类型");
		abbrEnameCell.setCellStyle(titleStyle);

		HSSFCell mccIdCell = row.createCell(4);
		mccIdCell.setCellValue("参数下载标志");
		mccIdCell.setCellStyle(titleStyle);

		HSSFCell addressCell = row.createCell(5);
		addressCell.setCellValue("终端所在位置");
		addressCell.setCellStyle(titleStyle);

		HSSFCell provinceCell = row.createCell(6);
		provinceCell.setCellValue("省份");
		provinceCell.setCellStyle(titleStyle);

		HSSFCell cityNoCell = row.createCell(7);
		cityNoCell.setCellValue("城市");
		cityNoCell.setCellStyle(titleStyle);
		
		HSSFCell zoneCell = row.createCell(8);
		zoneCell.setCellValue("区");
		zoneCell.setCellStyle(titleStyle);
		
		HSSFCell telephoneCell = row.createCell(9);
		telephoneCell.setCellValue("密钥生效日期");
		telephoneCell.setCellStyle(titleStyle);
		
		HSSFCell managerCell = row.createCell(10);
		managerCell.setCellValue("密钥生效时间");
		managerCell.setCellStyle(titleStyle);
		
		HSSFCell accNameCell = row.createCell(11);
		accNameCell.setCellValue("增加时间");
		accNameCell.setCellStyle(titleStyle);
		
		HSSFCell accNoCell = row.createCell(12);
		accNoCell.setCellValue("终端使用的电话号码");
		accNoCell.setCellStyle(titleStyle);
		
		HSSFCell isBjAcctCell = row.createCell(13);
		isBjAcctCell.setCellValue("裕福原有状态");
		isBjAcctCell.setCellStyle(titleStyle);
		
		HSSFCell bisCell = row.createCell(14);
		bisCell.setCellValue("费率(单位：%)");
		bisCell.setCellStyle(titleStyle);
		
		HSSFCell bankNameCell = row.createCell(15);
		bankNameCell.setCellValue("消费场景");
		bankNameCell.setCellStyle(titleStyle);
		
		HSSFCell accNickNameCell = row.createCell(16);
		accNickNameCell.setCellValue("升级日期");
		accNickNameCell.setCellStyle(titleStyle);
		
		HSSFCell shortNickNameCell = row.createCell(17);
		shortNickNameCell.setCellValue("门店联系人");
		shortNickNameCell.setCellStyle(titleStyle);
		
		HSSFCell bankNoCell = row.createCell(18);
		bankNoCell.setCellValue("门店电话");
		bankNoCell.setCellStyle(titleStyle);
		
		HSSFCell individualCell = row.createCell(19);
		individualCell.setCellValue("区域");
		individualCell.setCellStyle(titleStyle);
		
		HSSFCell lastSettleDateCell = row.createCell(20);
		lastSettleDateCell.setCellValue("商户顾问");
		lastSettleDateCell.setCellStyle(titleStyle);
		
		HSSFCell terminalPostionCell = row.createCell(21);
		terminalPostionCell.setCellValue("终端装机地址");
		terminalPostionCell.setCellStyle(titleStyle);
	}
	/**
	 * 添加下拉框限制	
	 * @param sheet1
	 */
	public void createSelectValidate(HSSFSheet sheet1) {
		//终端设备型号，3
		DVConstraint constraintMcc = DVConstraint.createFormulaListConstraint("edcType");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsMcc = new CellRangeAddressList(2, 499,2, 2);
		DataValidation data_validation_Mcc = new HSSFDataValidation(regionsMcc, constraintMcc);
		sheet1.addValidationData(data_validation_Mcc);
		
		//参数下载标志，第5列
		DVConstraint constraintdownloadFlag = DVConstraint.createFormulaListConstraint("downloadFlag");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsdownloadFlag = new CellRangeAddressList(2, 499,4, 4);
		DataValidation data_validation_downloadFlag = new HSSFDataValidation(regionsdownloadFlag, constraintdownloadFlag);
		sheet1.addValidationData(data_validation_downloadFlag);
		
		//省，第7列
		DVConstraint constraintPosType = DVConstraint.createFormulaListConstraint("provice");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsPosType = new CellRangeAddressList(2, 499,6, 6);
		DataValidation data_validation_PosType = new HSSFDataValidation(regionsPosType, constraintPosType);
		sheet1.addValidationData(data_validation_PosType);
		
		//市，第8列
		DVConstraint constraintcityNo = DVConstraint.createFormulaListConstraint("INDIRECT($G$3:$G$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionscityNo = new CellRangeAddressList(2, 499,7, 7);
		DataValidation data_validation_cityNo = new HSSFDataValidation(regionscityNo, constraintcityNo);
		sheet1.addValidationData(data_validation_cityNo);
		
		//区，第9列
		DVConstraint constraintmanager = DVConstraint.createFormulaListConstraint("INDIRECT($H$3:$H$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmanager = new CellRangeAddressList(2, 499,8, 8);
		DataValidation data_validation_manager = new HSSFDataValidation(regionsmanager, constraintmanager);
		sheet1.addValidationData(data_validation_manager);
		
		//消费场景，16
		DVConstraint constraintisBjAcct = DVConstraint.createFormulaListConstraint("consumpCategory");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsisBjAcct = new CellRangeAddressList(2, 499,15, 15);
		DataValidation data_validation_isBjAcct = new HSSFDataValidation(regionsisBjAcct, constraintisBjAcct);
		sheet1.addValidationData(data_validation_isBjAcct);
		
		
		//区域，20	
		DVConstraint constraintmerchantArea = DVConstraint.createFormulaListConstraint("storeManagerfactory");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchantArea = new CellRangeAddressList(2, 499,19, 19);
		DataValidation data_validation_merchantArea = new HSSFDataValidation(regionsmerchantArea, constraintmerchantArea);
		sheet1.addValidationData(data_validation_merchantArea);
		
		
		//商户顾问，21
		DVConstraint constraintmerchantAdvisor = DVConstraint.createFormulaListConstraint("INDIRECT($T$3:$T$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsmerchantAdvisor = new CellRangeAddressList(2, 499,20, 20);
		DataValidation data_validation_merchantAdvisor = new HSSFDataValidation(regionsmerchantAdvisor, constraintmerchantAdvisor);
		sheet1.addValidationData(data_validation_merchantAdvisor);
	}
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
	public List<SysDict> sysDictValue(String[] string) {
		List<SysDict> SysDictList = new LinkedList<SysDict>();
		for(int i=0; i<string.length;i++){
			String[] strings = string[i].split(",");
			SysDict SysisBjAcct = new SysDict();
			for(int j=0;j<strings.length;j++){
				if(j==0){
					SysisBjAcct.setValue(strings[j]);
				}else{
					SysisBjAcct.setLabel(strings[j]);
				}
			}
			if(null !=SysisBjAcct){
				SysDictList.add(SysisBjAcct);
			}
		}
		return SysDictList;
	}		
	/**
	 * 返回终端设备型号
	 * @return
	 */
	public List<SysDict> edcTypeTypeList() {
		String[] idTypeSt = {"STD,STD","APP,APP","CMB,CMB","CITIC,CITIC","CITIC1,CITIC1"
				,"HYPT7,HYPT7","SPECS900,SPECS900","STAR,STAR","SHARE,SHARE"};
		return sysDictValue(idTypeSt);
	}
	/**
	 * 返回参数下载标志
	 * @return
	 */
	public List<SysDict> downloadFlagList() {
		String[] isBjAcctSt = {"0,需要安装","1,无需安装"};
		return sysDictValue(isBjAcctSt);
	}		
	/****************************************************设置Excel end****************************************************/
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "terminalSDMT/page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, TerminalSDMT queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "sdmtpf/terminalSDMTList";
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
	@RequestMapping(value = { "terminalSDMT/getList" })
	public String getInfo(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, TerminalSDMT queryModel) {
		// 分页设置
		if("".equals(queryModel.getAddDate())){
			queryModel.setAddDate(null);
		}
		int count = TerminalSDMTService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<TerminalSDMT> list = TerminalSDMTService.queryList(queryModel, startResult, endResult);

		model.addAttribute("terminalSDMTList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		return "sdmtpf/terminalSDMTList";
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
	 * 跳转添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = { "terminalSDMT/toAdd" })
	public String toAdd(HttpServletRequest req, HttpServletResponse resp,Model model,TerminalSDMT info) throws FileNotFoundException, IOException {
		//设置MCC和地址码表
		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");
		model.addAttribute("mccList", mccList);		
		List<Area> proviceList = AreaService.queryList("0", "1");
		model.addAttribute("proviceList", proviceList);	
		List<SysParameter> consumpList=SysParameterService.queryList("CONSUMP_CATEGORY", "");
		model.addAttribute("consumpList", consumpList);	
		//设置区域下拉框 MERCHANT_AREA sys_dict	
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		model.addAttribute("merchantAreaList", merchantAreaList);
		try {
			Integer defaultDate = Integer.valueOf(DateUtil.getFormatTimeString(DateUtil.getDateFromString("20140421", "yyyyMMdd"),"yyyyMMdd"));
			info.setActDate(defaultDate);
			info.setActTime(181037l);
			info.setxTimezone("5");
			info.setConsumpCategory("1");			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		info.setAddDate(DateUtil.getNowII());
		info.setUpgradeDate(DateUtil.getNowII());
		model.addAttribute("info", info);	
		return "sdmtpf/terminalSDMTAdd";
	}
	/**
	 * 通过福卡前置库反查出merchant
	 * @param merchantId
	 * @return
	 */
	public MerchantSDMT toMerchant(String merchantId){
		
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
//		merchant.setAccNo(mExtra.getRcvAcct1());
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
				merchant.setEnterpriseNo(cMerchantX.getAccno());
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
				merchant.setAccNo(mrchAccX.getAccno());
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
		
		//标志位赋值
		merchant.setSdFlag("1");
		merchant.setxFlag("1");
		merchant.setYufuFlag("1");
		merchant.setxBakFlag("1");
		
		//是否是通过终端同步添加的老商户
		merchant.setIsTerminalAddFlag("1");
		return merchant;
	}
	/**
	 * 添加信息
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "terminalSDMT/save" }, method = { RequestMethod.POST })
	@ResponseBody
	public String add(HttpServletRequest req, HttpServletResponse resp, Model model,TerminalSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flagSdmtpf = false;	//75临时表标志
		boolean flagFkqz = false;//福卡前置标志
		boolean flagX = false;//X平台标志
		boolean flagLfk = false;//福卡系统标志
		/**
		 * 1、先判断商户号是否存在于临时表
		 * 2、如果不存在，将其加入临时表
		 */
		String merchantId = info.getMerchantId();
		if (merchantId != null && !"".equals(merchantId)) {
			Map<String,Boolean> mapb = isExistMid(merchantId);
			flagSdmtpf = mapb.get("flagSdmtpf");
			flagFkqz = mapb.get("flagFkqz");
			flagX = mapb.get("flagX");
			flagLfk = mapb.get("flagLfk");
			// 先校验中间库.
			if (!flagSdmtpf) {
				// 不存在
				if((flagFkqz&&flagX&&flagLfk)){
					log.debug("保存终端异常");
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "保存终端失败，此商户不存在。");
					
					try{
						MerchantSDMT merchant = toMerchant(merchantId);
						MerchantXRecord merchantXRecord = null;
//						Map<String, Object> map1 = MerchantSDMTService.save(merchant,merchantXRecord,true);
//						Map<String, Object> map = MerchantSDMTService.save(merchant,merchantXRecord,true);
						
//						if("0".equals(map1.get("result"))){
							try {
								map = MerchantSDMTService.saveMerchantAndTermial(merchant,merchantXRecord,true,info);
//								map = TerminalSDMTService.save(info);
							} catch (Exception e) {
								log.debug("保存终端异常：{}", e);
								map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
								map.put(SysConst.RESULT_MSG, "保存终端失败。");
							}
//						}else{
//							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
//							map.put(SysConst.RESULT_MSG, "保存保存老商户至临时表失败。");
//						}
					}catch(Exception e1){
						log.debug("保存老商户至临时表异常：{}", e1);
						map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
						map.put(SysConst.RESULT_MSG, "保存保存老商户至临时表失败。");
					}
				}
			}else{
				//中间库中存在
				try {
					map = TerminalSDMTService.save(info);
				} catch (Exception e) {
					log.debug("保存终端异常：{}", e);
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "保存终端失败。");
				}
			}
		}
		
		return JsonUtil.getJsonString(map);
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
	@RequestMapping(value = { "terminalSDMT/edit" })
	public String toEdit(HttpServletRequest req, HttpServletResponse resp, Model model,String termCode,String merchantId) throws FileNotFoundException, IOException {
		TerminalSDMTKey key=new TerminalSDMTKey();
		key.setMerchantId(merchantId);
		key.setTermCode(termCode);
		TerminalSDMT info = TerminalSDMTService.queryInfo(key);
		model.addAttribute("info", info);
		//设置MCC和地址码表
		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");
		model.addAttribute("mccList", mccList);		
		List<Area> proviceList = AreaService.queryList("0", "1");
		model.addAttribute("proviceList", proviceList);	
		model.addAttribute("cityList", AreaService.queryList(info.getProvince(), "1"));	
		model.addAttribute("zoneList", AreaService.queryList(info.getCityNo(), "1"));	
		List<SysParameter> consumpList=SysParameterService.queryList("CONSUMP_CATEGORY", "");
		model.addAttribute("consumpList", consumpList);	
		//设置区域下拉框 MERCHANT_AREA sys_dict	
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		model.addAttribute("merchantAreaList", merchantAreaList);
		//普通员工
		String merchantArea = info.getTerminalArea();
		List<PManager> managerList = pManagerService.findListByRoleIdAndArea("0", merchantArea);
		model.addAttribute("managerList", managerList);
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
				//临时表有
			}
		}
		if(flagSdmtpf){
			MerchantSDMT merchant=MerchantSDMTService.queryInfo(merchantId);
			if(merchant!=null){
				model.addAttribute("merchantName", merchant.getMrchtName());	
			}
		}else{
			if(flagFkqz&&flagX&&flagLfk){
				MerchantBase merchantBase = new MerchantBase();
				merchantBase.setMerchantId(merchantId);
				MerchantBase mBase = MerchantBaseDao.selectByMerchantId(merchantBase);
				if(mBase!=null){
					model.addAttribute("merchantName", mBase.getMerchantCname());	
				}
			}
		}
		return "sdmtpf/terminalSDMTEdit";
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
	@RequestMapping(value = { "terminalSDMT/show" })
	public String show(HttpServletRequest req, HttpServletResponse resp, Model model,String termCode,String merchantId) throws FileNotFoundException, IOException {
		TerminalSDMTKey key=new TerminalSDMTKey();
		key.setMerchantId(merchantId);
		key.setTermCode(termCode);
		TerminalSDMT info = TerminalSDMTService.queryInfo(key);
		model.addAttribute("info", info);
		//设置MCC和地址码表
		List<NumDescr> mccList = NumDescrService.queryList("ab", "ZH");
		model.addAttribute("mccList", mccList);		
		List<Area> proviceList = AreaService.queryList("0", "1");
		model.addAttribute("proviceList", proviceList);	
		model.addAttribute("cityList", AreaService.queryList(info.getProvince(), "1"));	
		model.addAttribute("zoneList", AreaService.queryList(info.getCityNo(), "1"));	
		List<SysParameter> consumpList=SysParameterService.queryList("CONSUMP_CATEGORY", "");
		model.addAttribute("consumpList", consumpList);	
		//设置区域下拉框 MERCHANT_AREA sys_dict 
		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(sedPost("MERCHANT_AREA"));
//		List<SysDict> merchantAreaList = cn.yufu.utils.JsonUtil.jsonToList(HttpRequestUtil.sedPost("MERCHANT_AREA"));
		model.addAttribute("merchantAreaList", merchantAreaList);
		
		String merchantArea = info.getTerminalArea();
		List<PManager> managerList = pManagerService.findListByRoleIdAndArea("0", merchantArea);
		//普通员工 
		model.addAttribute("managerList", managerList);
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
				//临时表有
			}
		}
		if(flagSdmtpf){
			MerchantSDMT merchant=MerchantSDMTService.queryInfo(merchantId);
			if(merchant!=null){
				model.addAttribute("merchantName", merchant.getMrchtName());	
			}
		}else{
			if(flagFkqz&&flagX&&flagLfk){
				MerchantBase merchantBase = new MerchantBase();
				merchantBase.setMerchantId(merchantId);
				MerchantBase mBase = MerchantBaseDao.selectByMerchantId(merchantBase);
				if(mBase!=null){
					model.addAttribute("merchantName", mBase.getMerchantCname());	
				}
			}
		}
		return "sdmtpf/terminalSDMTShow";
	}	
	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "terminalSDMT/edit" }, method = { RequestMethod.POST })
	@ResponseBody
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model,TerminalSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = TerminalSDMTService.edit(info);
		} catch (Exception e) {
			log.debug("修改终端异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "修改终端失败。");
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
	@RequestMapping(value = { "terminalSDMT/getMccList" }, method = { RequestMethod.POST })
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
	@RequestMapping(value = { "terminalSDMT/getAreaList" }, method = { RequestMethod.POST })
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
	 * 获取商户名称
	 * 1、以前的老商户添加新终端
	 * 		判断前置、X平台、福卡系统有没有此商户
	 * 			如果全都有，则通过校验，新增时将此商户添加至75临时表中
	 * 			否则不通过，不新增
	 * 		
	 * 2、之后的新商户添加新终端
	 * 		判断75临时表中有无此商户，如果有，通过，反之，不通过
	 * @param merchantId
	 * @return
	 */
	public String merchantidisExist(String merchantId){
		String data="";
		MerchantSDMT merchant = null;
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
				//临时表有
				merchant=MerchantSDMTService.queryInfo(merchantId);
				if(null != merchant){
					data = JsonUtil.getJsonString(merchant);
				}
			}else{
				//临时表无
				if(flagFkqz && flagX && flagLfk){
				//其他三个平台都有
					MerchantBase merchantBase = new MerchantBase();
					merchantBase.setMerchantId(merchantId);
					MerchantBase mBase = MerchantBaseDao.selectByMerchantId(merchantBase);
					merchant = new MerchantSDMT();
					merchant.setMrchtName(mBase.getMerchantCname());
					if(null != merchant){
						data = JsonUtil.getJsonString(merchant);
					}
				}else{
				//至少有一个平台有
					if(flagFkqz || flagX || flagLfk){
						List<String> list = new ArrayList<String>();
						list.add( "merchantIdIsNull" );
						JSONArray jsonArray2 = JSONArray.fromObject( list );
						data = jsonArray2.toString();
					}
				}
			}
		} 
		return data;
	}
	/**
	 * 获取商户名称
	 * 1、以前的老商户添加新终端
	 * 		判断前置、X平台、福卡系统有没有此商户
	 * 			如果全都有，则通过校验，新增时将此商户添加至75临时表中
	 * 			否则不通过，不新增
	 * 		
	 * 2、之后的新商户添加新终端
	 * 		判断75临时表中有无此商户，如果有，通过，反之，不通过
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = { "terminalSDMT/getMerchantName" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getMerchantName(HttpServletRequest req, HttpServletResponse resp,Model model, String merchantId) {
		return merchantidisExist(merchantId);
	}
	
	/**
	 * 判断该商户下终端号是否重复
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId
	 * @return 空，参数无效；0，没有重复；1，与中间库终端号重复；2.与老福卡系统终端号重复;3.与X平台终端号重复
	 */
	@RequestMapping(value = { "terminalSDMT/checkTerminalKey" }, method = { RequestMethod.POST })
	@ResponseBody
	public String checkTerminalKey(HttpServletRequest req,
			HttpServletResponse resp, Model model, String merchantId,
			String termCode) {
		String falg = "0";
		if (termCode != null && !"".equals(termCode)) {
			if (TerminalSDMTService.checkTerminal(merchantId, termCode) > 0) {
				falg = "1";
			} else if (YufuOldSwitchTerminalService.getTerminalListByTermCode(termCode) > 0
					|| YufuOldTerminalService.getTerminalListByTermCode(termCode) > 0
					|| YufuOld1TerminalService.getTerminalListByTermCode(termCode) > 0
					|| YufuOld2TerminalService.getTerminalListByTermCode(termCode) > 0) {
				falg = "2";
			} else if (CortexBakTerminalService
					.getTerminalListByTermCode(termCode) > 0) {
				falg = "3";
			}
		}
		return falg;
	}	

	/**
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param merchantId
	 * @param termCode
	 * @param synchronousType
	 * @return
	 */
	@RequestMapping(value = { "terminalSDMT/synchronous" }, method = { RequestMethod.POST })
	@ResponseBody
	public String synchronous(HttpServletRequest req, HttpServletResponse resp,Model model,String merchantId, String termCode, String synchronousType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String checkMrchFlag=this.checkTerminalKey(req, resp, model, merchantId, termCode);
		if(checkMrchFlag!=null&&!"".equals(checkMrchFlag)&&!"0".equals(checkMrchFlag)&&!"1".equals(checkMrchFlag)){
			String infoStr="";
			if("3".equals(checkMrchFlag)){
				infoStr="X平台主库已存在该终端号的终端";	
				log.debug(infoStr);
			}else if("2".equals(checkMrchFlag)){
				infoStr="老福卡系统已存在该终端号的终端";
				log.debug(infoStr);
			}
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, infoStr);					
		}else{
			// flag 空：参数无效；-1.失败；0.成功
			String flag = "0";
			String errorStr="";
			// 获取中间表商户实体，再同步
			log.info("根据商户号查询商户");
			MerchantSDMT merchant = MerchantSDMTService.queryInfo(merchantId);		
			TerminalSDMTKey key=new TerminalSDMTKey();
			key.setMerchantId(merchantId);
			key.setTermCode(termCode);
			log.info("根据商户号、终端号查询终端");
			TerminalSDMT info = TerminalSDMTService.queryInfo(key);
			if (merchantId != null && !"".equals(merchantId)&&termCode!=null&&!"".equals(termCode)&& synchronousType != null && !"".equals(synchronousType)&& info != null&&merchant!=null) {			
				try {
					if ("SD".equals(synchronousType)) {
						if(!"1".equals(merchant.getSdFlag())){
							flag="-1";
						}else{
							//192.168.6.12:1521:fksd Posp改为75
							/*info.getTermAddress();*/
							this.PospTerminalService.synchronousPospTerminal(info);
						}					     
					} else if ("X".equals(synchronousType)) {
						if(!"1".equals(merchant.getxFlag())||!"1".equals(merchant.getxBakFlag())){
							flag="-1";
						}else{
							log.info("同步终端");
							//主库
							String seqTermPosIdAndTermNoAndseqEnckeyId=CortexTerminalService.synchronousCortexTerminal(merchant, info);
							//备库
							this.CortexBakTerminalService.synchronousCortexBakTerminal(merchant, info, seqTermPosIdAndTermNoAndseqEnckeyId);
							//同步成功后，添加收单系统与FIS系统映射表
							this.PospTerminalService.synchronousEdcNewfkterminalOrm(info);
						}
					} else if ("YUFU".equals(synchronousType)) {
						if(!"1".equals(merchant.getYufuFlag())){
							flag="-1";
						}else{
							//jdbc:sqlserver://192.168.10.24:1433;databaseName=switch 表名Terminals
							this.YufuOldSwitchTerminalService.synchronousYufuOldSwitchTerminal(info);
							//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu; 表名Terminals
							this.YufuOldTerminalService.synchronousYufuOldTerminal(info);						
							//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu1 表名Terminals
							this.YufuOld1TerminalService.synchronousYufuOld1Terminal(info);
							//jdbc:sqlserver://192.168.10.24:1433;databaseName=yufu2 表名Terminals
							this.YufuOld2TerminalService.synchronousYufuOldTerminal(info);
							//同步成功后，添加收单系统与老福卡系统映射表 
							this.PospTerminalService.synchronousEdcTerminalOrm(info);
						}
					} 	
					if("0".equals(flag)){
						map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
						map.put(SysConst.RESULT_MSG, "终端("+info.getMerchantId()+","+info.getTermCode()+")同步成功。");	
					}else{
						errorStr="请先同步商户信息，再同步终端信息。";
						log.debug(errorStr);
						map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
						map.put(SysConst.RESULT_MSG, "终端("+info.getMerchantId()+","+info.getTermCode()+")同步失败，失败原因："+errorStr);
					}				
				} catch (Exception e) {
					flag="-1";
					errorStr=e.getMessage();
					if(errorStr.length()>150){
						errorStr=errorStr.substring(0,150);
					}
					if(errorStr.contains("###")){
						errorStr=errorStr.split("###")[1].toString();
					}
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
//					map.put(SysConst.RESULT_MSG, "终端("+info.getMerchantId()+","+info.getTermCode()+")同步失败，失败原因："+"系统罢工了请稍后再试");				
					map.put(SysConst.RESULT_MSG, "终端("+info.getMerchantId()+","+info.getTermCode()+")同步失败，失败原因："+errorStr);				
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
				this.TerminalSDMTService.edit(info);
			}		
		}		

		return JsonUtil.getJsonString(map);
	}
	
}
