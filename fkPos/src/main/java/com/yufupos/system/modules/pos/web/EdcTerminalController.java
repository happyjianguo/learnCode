package com.yufupos.system.modules.pos.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.beanvalidator.BeanValidators;
import com.yufupos.system.common.config.Global;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.POIUtils;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.utils.excel.ImportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.cortex.entity.CortexArea;
import com.yufupos.system.modules.cortex.service.CortexAreaService;
import com.yufupos.system.modules.pos.entity.EdcTerminal;
import com.yufupos.system.modules.pos.entity.EdcTerminalPosExcel;
import com.yufupos.system.modules.pos.entity.PPos;
import com.yufupos.system.modules.pos.entity.PPosActLog;
import com.yufupos.system.modules.pos.entity.ReportShow;
import com.yufupos.system.modules.pos.service.EdcTerminalService;
import com.yufupos.system.modules.pos.service.PManagerService;
import com.yufupos.system.modules.pos.service.PPosActLogService;
import com.yufupos.system.modules.pos.service.PPosService;
import com.yufupos.system.modules.pos.service.ReportShowService;
import com.yufupos.system.modules.sys.entity.User;
import com.yufupos.system.modules.sys.service.UserService;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * 终端信息Controller
 * @author llg
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "/pos/edcTerminal")
public class EdcTerminalController extends BaseController {

	@Autowired
	private EdcTerminalService edcTerminalService;
	@Autowired
	private CortexAreaService cortexAreaService;	
	@Autowired
	private PManagerService pManagerService;
	@Autowired
	private ReportShowService reportShowService;
	@Autowired
	private PPosService pPosService;
	@Autowired 
	private PPosActLogService pPosActLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@ModelAttribute
	public EdcTerminal get(@RequestParam(required=false) String terminalId, 
			@RequestParam(required=false) String merchantId) {
		EdcTerminal entity = null;
		List<EdcTerminal> list = null;
		if (StringUtils.isNotBlank(terminalId)){
			list = edcTerminalService.getOneData(terminalId, merchantId);
		}
		if (list == null || list.size() == 0){
			entity = new EdcTerminal();
		}  else {
			entity = list.get(0);
			entity.setId(terminalId);
		}
		return entity;
	}
	
	@RequiresPermissions("pos:edcTerminal:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdcTerminal edcTerminal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdcTerminal> page = edcTerminalService.findPage(new Page<EdcTerminal>(request, response), edcTerminal); 
		model.addAttribute("page", page);
		return "modules/pos/edcTerminalList";
	}

	@RequiresPermissions("pos:edcTerminal:view")
	@RequestMapping(value = "form")
	public String form(EdcTerminal edcTerminal, Model model) {
		//设置省份
		model.addAttribute("provinceList", cortexAreaService.findList(new CortexArea(null,1)));
		//设置城市
		Integer provinceId=Strings.isNullOrEmpty(edcTerminal.getTerminalProvince())?null:Integer.valueOf(edcTerminal.getTerminalProvince());
		model.addAttribute("cityList", cortexAreaService.findList(new CortexArea(provinceId,2)));		
		//设置区域
		Integer cityId=Strings.isNullOrEmpty(edcTerminal.getTerminalCity())?null:Integer.valueOf(edcTerminal.getTerminalCity());
		model.addAttribute("zoneList", cortexAreaService.findList(new CortexArea(cityId,null)));
		//设置商户顾问	
		if(!Strings.isNullOrEmpty(edcTerminal.getTerminalArea())){
			model.addAttribute("managerList", pManagerService.findListByRoleId(null, edcTerminal.getTerminalArea()));
		}
		model.addAttribute("edcTerminal", edcTerminal);
		return "modules/pos/edcTerminalForm";
	}

	@RequiresPermissions("pos:edcTerminal:edit")
	@RequestMapping(value = "disable")
	public String disable(String terminalIds, Model model) {
		EdcTerminal edcTerminal = new EdcTerminal();
		edcTerminal.setDisableDate(DateUtils.getDate("yyyyMMdd"));
		edcTerminal.setTerminalId(terminalIds);
		model.addAttribute("edcTerminal", edcTerminal);
		return "modules/pos/edcTerminalDisable";
	}
	
	@RequestMapping(value = "disableSave")
	@ResponseBody
	public String disableSave(EdcTerminal edcTerminalForm, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//得到要停用的终端编号
			String terminalIdsStr[] = StringUtils.split(edcTerminalForm.getTerminalId(), '|');
			for (int i = 0; i < terminalIdsStr.length; i++) {
				EdcTerminal edcTerminal = new EdcTerminal();
				String terminalId = terminalIdsStr[i];
				edcTerminal.setTerminalId(terminalId);
				edcTerminal.setTerminalStat("N"); //冻结
				edcTerminal.setDisableDate(edcTerminalForm.getDisableDate());
				edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
				//停用终端
				edcTerminalService.disableTerminal(edcTerminal);
				//根据终端号得到终端与POS
				List<ReportShow> list = reportShowService.getOneData(terminalId, null);
				if (list != null && list.size() > 0) {
					for (ReportShow reportShow : list) {
						try {
							if (StringUtils.isNotBlank(reportShow.getPosSn())) {
								//停用POS机：解绑，恢复为空闲状态
								PPos pPos = new PPos();
								pPos.setPosSn(reportShow.getPosSn());
								List<PPos> listOld=this.pPosService.findList(pPos);
								pPos = listOld.get(0);
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
									pPosActLog.setPosActMsg("终端停用，POS机解绑");
									pPosActLog.setRemarks("终端停用，POS机解绑");
									this.pPosActLogService.save(pPosActLog);
								}
							}
						} catch (Exception e) {
							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
							map.put(SysConst.RESULT_MSG, "POS机解绑失败");
							return JsonUtil.getJsonString(map);
						}
					}
				}
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "停用终端成功");			
		} catch (Exception e) {
			//e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "停用终端失败");
		}
		return JsonUtil.getJsonString(map);
	}
	
	@RequiresPermissions("pos:edcTerminal:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(EdcTerminal edcTerminal) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//是否停用
			if (StringUtils.isNotBlank(edcTerminal.getDisableDate()) && "Y".equals(edcTerminal.getTerminalStat()) 
					&& StringUtils.isEmpty(edcTerminal.getUsableDate())) {
				//停用终端
				edcTerminal.setTerminalStat("N"); //冻结
				edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
				//停用终端
				edcTerminalService.disableTerminal(edcTerminal);
				//根据终端号得到终端与POS
				List<ReportShow> list = reportShowService.getOneData(edcTerminal.getTerminalId(), null);
				if (list != null && list.size() > 0) {
					for (ReportShow reportShow : list) {
						if (StringUtils.isNotBlank(reportShow.getPosSn())) {
							//停用POS机：解绑，恢复为空闲状态
							PPos pPos = new PPos();
							pPos.setPosSn(reportShow.getPosSn());
							List<PPos> listOld=this.pPosService.findList(pPos);
							pPos = listOld.get(0);
							if(pPos!=null){
								pPos.setPosStatus("IDLE");
								pPos.setTerminalId("");
								this.pPosService.disablePPos(pPos);	
								//新增日志： 解绑
								PPosActLog pPosActLog = new PPosActLog();
								pPosActLog.setMerchantId(reportShow.getMerchantId());
								pPosActLog.setPosSn(reportShow.getPosSn());
								pPosActLog.setTerminalId(edcTerminal.getTerminalId());			
								pPosActLog.setPosActType("UNBTERM");
								pPosActLog.setPosActMsg("终端停用，POS机解绑");
								pPosActLog.setRemarks("终端停用，POS机解绑");
								this.pPosActLogService.save(pPosActLog);
							}
						}
					}
				}
			}
			
			if (StringUtils.isNotBlank(edcTerminal.getUsableDate())
					&& "N".equals(edcTerminal.getTerminalStat())) {
				//是否启用
				int falg = 0;
				EdcTerminal terminal = edcTerminalService.get(edcTerminal.getTerminalId());
				if (terminal != null) {
					if (edcTerminal.getUsableDate().equals(terminal.getUsableDate())) {
						falg = 1;
					}
				}
				if (falg != 1) {
					//启用终端
					edcTerminal.setTerminalStat("Y"); 
					edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
					edcTerminalService.usableTerminal(edcTerminal);
				}
			}
			edcTerminal.setUpdateOper(UserUtils.getPrincipal().getLoginName());
			edcTerminal.setUpdateDateStr(DateUtils.getDate("yyyyMMdd"));
			edcTerminal.setUpdateTime(DateUtils.getDate("HHmmss"));
			edcTerminalService.save(edcTerminal);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存终端信息成功");			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存终端信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:edcTerminal:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EdcTerminal edcTerminal, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "终端信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<EdcTerminal> page = edcTerminalService.findPage(new Page<EdcTerminal>(request, response, -1), edcTerminal); 
            List<EdcTerminal> findList = edcTerminalService.findList(edcTerminal);
            new ExportExcel("终端信息", EdcTerminal.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出终端信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminal/list?repage";
    }
	
	@RequiresPermissions("pos:edcTerminal:edit")
	@RequestMapping(value = "delete")
	public String delete(EdcTerminal edcTerminal, RedirectAttributes redirectAttributes) {
		edcTerminalService.delete(edcTerminal);
		addMessage(redirectAttributes, "删除终端信息成功");
		return "redirect:"+"/pos/edcTerminal/list?repage";
	}
	
	/**
	 * 下载导入POS机数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:edcTerminal:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletRequest req,HttpServletResponse resp, RedirectAttributes redirectAttributes) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			String headName="终端POS关联信息 ";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "excelExportTemp/TERM_POS_TEMP.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			HSSFWorkbook work = new HSSFWorkbook(in);
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
			System.out.println("导出成功!");
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminal/list?repage";
	}	

	/**
	 * 导入POS机数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:edcTerminal:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+"/pos/edcTerminal/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdcTerminalPosExcel> list = ei.getDataList(EdcTerminalPosExcel.class);
			String termReCode = "0";
			String termRePosSn = "";
			String posReCode = "0";
			String posReTerminal = "";
			String setAddr = "";
			for (EdcTerminalPosExcel entity : list) {
				try {
					if (entity != null && !Strings.isNullOrEmpty(entity.getTerminalId()) 
							&& !Strings.isNullOrEmpty(entity.getPosSn())) {
						String terminalId = entity.getTerminalId();
						String posSn = entity.getPosSn();
						setAddr = entity.getSetAddr();
						//检查POS是否已挂终端
						PPos result = this.pPosService.get(posSn);
						if (result != null) {
							if(terminalId.equals(result.getTerminalId())){
								failureMsg.append("<br/>"+ entity.getTerminalId()+"|"+entity.getPosSn()
								+ ",失败原因:终端号、SN码已关联 ");
								failureNum++;
								continue;
							}
						}
						String outstockDate = Strings.isNullOrEmpty(entity.getOutstockDate())?DateUtils.getDate("yyyyMMdd"):entity.getOutstockDate().trim(); // 出库时间
						String outstockBy = "";  // 出库者
						//出库者ID
						if (Strings.isNullOrEmpty(entity.getOutstockBy())) {
							outstockBy = UserUtils.getPrincipal().getId();
						}else{
							List<User> name = userService.getByName(entity.getOutstockBy());
							if (name == null || name.size() == 0) {
								failureMsg.append("<br/>" + "出库者 " + entity.getOutstockBy() + " 不存在; ");
								failureNum++;
								continue;
							}else {
								outstockBy = name.get(0).getId();
							}
						}
						Map<String,String> checkMap = this.checkTermPos(terminalId, posSn);
						termReCode = checkMap.get("termReCode");
						posReCode = checkMap.get("posReCode");
						termRePosSn = checkMap.get("termRePosSn");//终端对应的老POS
						posReTerminal = checkMap.get("posReTerminal");//POS对应的老终端						
						if("0".equals(termReCode)){
							failureMsg.append("<br/>"+ terminalId+"|"+posSn+ ",失败原因:终端不存在或状态为非正常 ");
							failureNum++;
							continue;							
						}
						if("0".equals(posReCode)){
							failureMsg.append("<br/>"+ terminalId+"|"+posSn+ ",失败原因:SN码对应的POS不存在或状态为作废或报修 ，不能绑定终端");
							failureNum++;
							continue;							
						}
						PPos pPos=null;
						PPos oldPPos=null;						
						//终端存在，没有挂POS
						if("1".equals(termReCode)){
							//POS存在，没有挂终端
							if("1".equals(posReCode)){
								//终端直接挂POS
								pPos=this.pPosService.get(posSn);
								pPos.setOutstockBy(outstockBy);
								pPos.setOutstockDate(outstockDate);
								pPos.setPosStatus("USE");
								pPos.setId(posSn);
								pPos.setPosSn(posSn);
								pPos.setTerminalId(terminalId);
								pPos.setIsNewRecord(false);
								pPosService.save(pPos);
								//新增日志： 出库
								PPosActLog pPosActLog = new PPosActLog();
								pPosActLog.setPosSn(posSn);
								pPosActLog.setTerminalId(terminalId);			
								pPosActLog.setPosActType("BTERM");
								pPosActLog.setPosActMsg("终端关联POS机，出库");
								this.pPosActLogService.save(pPosActLog);								
							}else if("2".equals(posReCode)){//POS存在，已挂终端
								//POS先解绑对应旧终端，再绑定新终端								
								//新增日志： 解绑
								PPosActLog pPosActLog = new PPosActLog();
								pPosActLog.setPosSn(posSn);
								pPosActLog.setTerminalId(posReTerminal);			
								pPosActLog.setPosActType("UNBTERM");
								this.pPosActLogService.save(pPosActLog);
								//终端直接挂POS
								pPos=this.pPosService.get(posSn);
								pPos.setOutstockBy(outstockBy);
								pPos.setOutstockDate(outstockDate);
								pPos.setPosStatus("USE");
								pPos.setId(posSn);
								pPos.setPosSn(posSn);
								pPos.setTerminalId(terminalId);
								pPos.setIsNewRecord(false);
								pPosService.save(pPos);
								//新增日志： 出库
								PPosActLog pPosActLog1 = new PPosActLog();
								pPosActLog1.setPosSn(posSn);
								pPosActLog1.setTerminalId(terminalId);			
								pPosActLog1.setPosActType("BTERM");
								pPosActLog1.setPosActMsg("终端关联POS机,先解绑在绑定");
								this.pPosActLogService.save(pPosActLog1);								
							}
						}else if("2".equals(termReCode)){//终端存在，已挂POS
							//解绑旧POS
							oldPPos=this.pPosService.get(termRePosSn);
							oldPPos.setPosStatus("IDLE");
							oldPPos.setId(termRePosSn);
							oldPPos.setPosSn(termRePosSn);
							oldPPos.setTerminalId("");
							oldPPos.setIsNewRecord(false);
							pPosService.save(oldPPos);							
							//新增日志： 解绑
							PPosActLog pPosActLog = new PPosActLog();
							pPosActLog.setPosSn(termRePosSn);
							pPosActLog.setTerminalId(terminalId);			
							pPosActLog.setPosActType("UNBTERM");
							pPosActLog.setPosActMsg("终端关联POS机,解绑");
							this.pPosActLogService.save(pPosActLog);
							//POS存在，没有挂终端
							if("1".equals(posReCode)){
								//挂新POS
								pPos=this.pPosService.get(posSn);
								pPos.setOutstockBy(outstockBy);
								pPos.setOutstockDate(outstockDate);
								pPos.setPosStatus("USE");
								pPos.setId(posSn);
								pPos.setPosSn(posSn);
								pPos.setTerminalId(terminalId);
								pPos.setIsNewRecord(false);
								pPosService.save(pPos);
								//新增日志： 出库
								PPosActLog pPosActLog1 = new PPosActLog();
								pPosActLog1.setPosSn(posSn);
								pPosActLog1.setTerminalId(terminalId);			
								pPosActLog1.setPosActType("BTERM");
								pPosActLog1.setPosActMsg("终端关联POS机,出库");
								this.pPosActLogService.save(pPosActLog1);								
							}else if("2".equals(posReCode)){//POS存在，已挂终端
								//POS先解绑对应旧终端，再绑定新终端
								//新增日志： 解绑
								PPosActLog pPosActLog2 = new PPosActLog();
								pPosActLog2.setPosSn(posSn);
								pPosActLog2.setTerminalId(posReTerminal);			
								pPosActLog2.setPosActType("UNBTERM");
								pPosActLog2.setPosActMsg("终端关联POS机,解绑旧终端");
								this.pPosActLogService.save(pPosActLog2);
								//终端直接挂POS
								pPos=this.pPosService.get(posSn);
								pPos.setOutstockBy(outstockBy);
								pPos.setOutstockDate(outstockDate);
								pPos.setPosStatus("USE");
								pPos.setId(posSn);
								pPos.setPosSn(posSn);
								pPos.setTerminalId(terminalId);
								pPos.setIsNewRecord(false);
								pPosService.save(pPos);
								//新增日志： 出库
								PPosActLog pPosActLog1 = new PPosActLog();
								pPosActLog1.setPosSn(posSn);
								pPosActLog1.setTerminalId(terminalId);			
								pPosActLog1.setPosActType("BTERM");
								pPosActLog1.setPosActMsg("终端关联POS机，关联新终端");
								this.pPosActLogService.save(pPosActLog1);
							}
						}	
						if (StringUtils.isNotBlank(setAddr)) {
							//更新终端基础表的安装地点字段
							EdcTerminal edcTerminal = new EdcTerminal();
							edcTerminal.setSetAddr(setAddr);
							edcTerminal.setTerminalId(terminalId);
							edcTerminalService.updateSetAddr(edcTerminal);
						}
						successNum++;						
					}else{
						failureMsg.append("<br/>"+ entity.getTerminalId()+"|"+entity.getPosSn()
								+ ",失败原因:终端号、SN码为必填项 ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/> " + entity.getTerminalId()+"|"+entity.getPosSn()+ " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/> " +entity.getTerminalId()+"|"+entity.getPosSn() + " 导入失败："
							+ ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条POS机，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条POS机"
					+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入POS机失败！失败信息：" + e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminal/list?repage";
	}	
	
	/**
	 * termReCode:
	 * 0.终端不存在或状态非正常；
	 * 1.终端存在，没有挂POS；
	 * 2.终端存在，已挂POS。
	 * posReCode
	 * 0.POS不存在或状态为作废或报修；
	 * 1.POS存在，没有挂终端；
	 * 2.POS存在，已挂终端； 
	 * @param terminalId posSn
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "checkTermPos")
	public Map<String,String> checkTermPos(String terminalId,String posSn) {
		Map<String,String> map=new HashMap<String,String>();
		String termReCode="0";
		String termRePosSn="";
		String posReCode="0";
		String posReTerminal="";		
		if (!Strings.isNullOrEmpty(terminalId)&&!Strings.isNullOrEmpty(posSn)) {
			//获取终端POS信息
			EdcTerminal pp = new EdcTerminal();
			pp.setTerminalId(terminalId);			
			List<EdcTerminal> list = this.edcTerminalService.findList(pp);
			if (list != null && list.size()>0) {
				EdcTerminal termResult=list.get(0);
				//状态为正常
				if("Y".equals(termResult.getTerminalStat())){
					//判断是否已挂POS
					if(Strings.isNullOrEmpty(termResult.getPosSn())){
						termReCode="1";
					}else{
						termReCode="2";
						termRePosSn=termResult.getPosSn();
					}
				}
			}
			//获取POS终端信息
			PPos pPos=new PPos();
			pPos.setPosSn(posSn);
			List<PPos> posList=this.pPosService.findList(pPos);
			if(posList != null && posList.size()>0){
				PPos pPosResult=posList.get(0);
				//状态为正常
				if("IDLE".equals(pPosResult.getPosStatus())||"USE".equals(pPosResult.getPosStatus())){
					//判断是否已挂POS
					if(Strings.isNullOrEmpty(pPosResult.getTerminalId())){
						posReCode="1";
					}else{
						posReCode="2";
						posReTerminal=pPosResult.getTerminalId();
					}					
				}				
			}			
		}
		map.put("termReCode", termReCode);
		map.put("termRePosSn", termRePosSn);		
		map.put("posReCode", posReCode);
		map.put("posReTerminal", posReTerminal);
		return map;
	}

}