package com.yufupos.system.modules.pos.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import cn.yufu.system.common.shiro.UserUtils;

import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.POIUtils;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.utils.excel.ImportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.pos.entity.PFactory;
import com.yufupos.system.modules.pos.entity.PModel;
import com.yufupos.system.modules.pos.entity.PModelSel;
import com.yufupos.system.modules.pos.entity.PPos;
import com.yufupos.system.modules.pos.entity.PPosActLog;
import com.yufupos.system.modules.pos.entity.PPosExcel;
import com.yufupos.system.modules.pos.entity.PPosReport;
import com.yufupos.system.modules.pos.entity.PSim;
import com.yufupos.system.modules.pos.entity.PSimActLog;
import com.yufupos.system.modules.pos.entity.PosSimExcel;
import com.yufupos.system.modules.pos.service.PFactoryService;
import com.yufupos.system.modules.pos.service.PModelService;
import com.yufupos.system.modules.pos.service.PPosActLogService;
import com.yufupos.system.modules.pos.service.PPosService;
import com.yufupos.system.modules.pos.service.PSimActLogService;
import com.yufupos.system.modules.pos.service.PSimService;
import com.yufupos.system.modules.sys.entity.Dict;
import com.yufupos.system.modules.sys.entity.User;
import com.yufupos.system.modules.sys.service.UserService;
import com.yufupos.system.modules.sys.utils.DictUtils;
import com.yufupos.system.modules.sys.utils.LogUtils;

/**
 * POS机信息Controller
 * 
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pPos")
public class PPosController extends BaseController {
	
	@Autowired
	private PPosService pPosService;
	@Autowired
	private PModelService pModelService;
	@Autowired
	private PFactoryService pFactoryService;
	@Autowired 
	private PPosActLogService pPosActLogService;	
	@Autowired
	private PSimService pSimService;
	@Autowired
	private PSimActLogService  pSimActLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@ModelAttribute
	public PPos get(@RequestParam(required = false) String posSn) {
		PPos entity = null;
		if (StringUtils.isNotBlank(posSn)) {
			entity = pPosService.get(posSn);
		}
		if (entity == null) {
			entity = new PPos();
		}
		entity.setId(posSn);
		return entity;
	}

	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = { "list", "" })
	public String list(PPos pPos, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<PPos> page = pPosService.findPage(
				new Page<PPos>(request, response), pPos);
		model.addAttribute("page", page);
		// 厂商码表
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		// 型号码表
		PModel pm = new PModel();
		pm.setDelFlag("0");
		pm.setModelStatus("0");
		if (pPos != null && !Strings.isNullOrEmpty(pPos.getFactoryId())) {
			pm.setFactoryId(pPos.getFactoryId());
		}
		List<PModel> list = this.pModelService.findList(pm);
		model.addAttribute("pModelList", list);

		return "modules/pos/pPosList";
	}

	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = { "listSum", "" })
	public String listSum(PPos pPos, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 厂商码表
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		// 型号码表
		PModel pm = new PModel();
		pm.setDelFlag("0");
		pm.setModelStatus("0");
		if (pPos != null && !Strings.isNullOrEmpty(pPos.getFactoryId())) {
			pm.setFactoryId(pPos.getFactoryId());
		}
		List<PModel> list = this.pModelService.findList(pm);
		model.addAttribute("pModelList", list);		
		//设置POS统计信息
		List<PPosReport> listSum=this.pPosService.findPosSum(pPos);
		model.addAttribute("listSum", listSum);		
		return "modules/pos/pPosListSum";
	}

	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = "exportSum", method = RequestMethod.POST)
	public String exportSumFile(PPos pPos, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "POS机统计信息" + DateUtils.getDate("yyyyMMddHHmmss")+ ".xlsx";
			List<PPosReport> listSum=this.pPosService.findPosSum(pPos);
			new ExportExcel("POS机统计信息", PPosReport.class).setDataList(listSum)
					.write(request, response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出POS机统计信息！失败信息：" + e.getMessage());
		}
		return "redirect:"  + "/pos/pPos/listSum?repage";
	}
	
	
	
	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = "form")
	public String form(PPos pPos, Model model) {
		// 厂商码表
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);

		pPos.setPosStatus("IDLE");//状态：默认 空闲
		pPos.setLayOutFlag("YUFU");//布放类型：默认 裕福YUFU
		pPos.setPosScanFlag("1");//是否支持扫码：默认1-支持扫码
		pPos.setPosType("FIXED");//POS类型： 默认 FIXED 固定
		pPos.setWarrantyTimeLimit("2");//报修时间：默认 2年
		pPos.setPurchaseDate(DateUtils.getDate("yyyyMMdd"));
		pPos.setPurchaseBy(UserUtils.getPrincipal().getName());
		
		model.addAttribute("pPos", pPos);
		return "modules/pos/pPosForm";
	}

	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PPos pPos, Model model) {
		// 厂商码表
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		// 型号码表
		PModel pm = new PModel();
		pm.setDelFlag("0");
		pm.setModelStatus("0");
		pm.setFactoryId(pPos.getFactoryId());
		List<PModel> list = this.pModelService.findList(pm);
		model.addAttribute("pModelList", list);
		
		model.addAttribute("pPos", pPos);
		return "modules/pos/pPosUpdate";
	}
	
	@RequiresPermissions("pos:pPos:malfunc")
	@RequestMapping(value = { "toMalfuncFine" })
	public String toMalfuncFine(PPos pPos,HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		String info="";
		try {
			pPos.setPosStatus(pPos.getRepairBeforeStatus());
			this.pPosService.save(pPos);
			//新增日志： 修好
			PPosActLog pPosActLog = new PPosActLog();
			pPosActLog.setPosSn(pPos.getPosSn());
			pPosActLog.setPosActType("REPFINE");
			this.pPosActLogService.save(pPosActLog);			
			
			info="状态修改成功";				
		} catch (Exception e) {
			LogUtils.saveLog(request, PPosController.class, e, "修好状态修改失败");
			info="数据库异常，操作失败"+e.getMessage();
		}
		addMessage(redirectAttributes, info);
		return "redirect:"  + "/pos/pPos/list?repage";
	}	
	
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "toAction")
	public String toAction(PPos pPos, Model model,String actFlag) {
		String returnJsp="";		
		if("outStock".equals(actFlag)){//出库
			returnJsp="modules/pos/pPosOutStock";
		}else if("malfunc".equals(actFlag)){//报修
			returnJsp="modules/pos/pPosMalfunc";
		}else if("scrap".equals(actFlag)){//报废
			returnJsp="modules/pos/pPosScrap";
		}
		model.addAttribute("pPos", pPos);
		return returnJsp;
	}	
	

	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "toShow")
	public String toShow(PPos pPos, Model model) {
		// 厂商码表
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = pFactoryService.findList(entity);
		model.addAttribute("pFactoryList", pFactoryList);
		// 型号码表
		PModel pm = new PModel();
		pm.setDelFlag("0");
		pm.setModelStatus("0");
		pm.setFactoryId(pPos.getFactoryId());
		List<PModel> list = this.pModelService.findList(pm);
		model.addAttribute("pModelList", list);
		//入库着
		if (StringUtils.isNotBlank(pPos.getInstockBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pPos.getInstockBy());
			if (user != null) {
				pPos.setInstockBy(user.getName());
			}
		}
		//出库者
		if (StringUtils.isNotBlank(pPos.getOutstockBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pPos.getOutstockBy());
			if (user != null) {
				pPos.setOutstockBy(user.getName());
			}
		}
		//报修者
		if (StringUtils.isNotBlank(pPos.getRepairBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pPos.getRepairBy());
			if (user != null) {
				pPos.setRepairBy(user.getName());
			}
		}
		//报废者
		if (StringUtils.isNotBlank(pPos.getScrappedBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pPos.getScrappedBy());
			if (user != null) {
				pPos.setScrappedBy(user.getName());
			}
		}
		model.addAttribute("pPos", pPos);
		return "modules/pos/pPosShow";
	}
	
	/**
	 * 出库： 校验是否存在该终端：1.不存在，直接报不错在；2.存在：2.1如果该终端已绑定POS，先解绑，再绑定；2.2如果该终端没有绑定POS，直接绑定POS。
	 * @param pPos
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pPos:outStock")
	@RequestMapping(value = "outStock")
	@ResponseBody
	public String outStock(PPos pPos, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String terminalId=pPos.getTerminalId();
			if (!Strings.isNullOrEmpty(terminalId)) {
				PPos pp = new PPos();
				pp.setTerminalId(terminalId);
				List<PPos> list = this.pPosService.findTermPos(pp);
				if (list == null || list.size() < 1) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "该终端不存在，请确认后再操作");
				}else{
					PPos pResult=list.get(0);
					String sn=pResult.getPosSn();
					if(Strings.isNullOrEmpty(sn)){
						pPos.setOutstockBy(UserUtils.getPrincipal().getId());
						pPos.setOutstockDate(DateUtils.getDate("yyyyMMdd"));
						pPos.setPosStatus("USE");
						pPosService.save(pPos);
						//新增日志： 出库
						PPosActLog pPosActLog = new PPosActLog();
						pPosActLog.setPosSn(pPos.getPosSn());
						pPosActLog.setTerminalId(pPos.getTerminalId());			
						pPosActLog.setPosActType("BTERM");
						this.pPosActLogService.save(pPosActLog);
					}else{
						//1.先解绑该终端对应的老POS
						PPos ppOld = new PPos();
						ppOld.setPosSn(sn);
						List<PPos> listOld=this.pPosService.findList(ppOld);
						ppOld=listOld.get(0);
						if(ppOld!=null){
							ppOld.setPosStatus("IDLE");
							ppOld.setTerminalId("");
							ppOld.setId(sn);
							ppOld.setIsNewRecord(false);
							this.pPosService.save(ppOld);	
							
							//新增日志： 解绑
							PPosActLog pPosActLog = new PPosActLog();
							pPosActLog.setPosSn(sn);
							pPosActLog.setTerminalId(terminalId);			
							pPosActLog.setPosActType("UNBTERM");
							this.pPosActLogService.save(pPosActLog);
						}
						//2.该终端绑定新的POS
						pPos.setOutstockBy(UserUtils.getPrincipal().getId());
						pPos.setOutstockDate(DateUtils.getDate("yyyyMMdd"));
						pPos.setPosStatus("USE");
						pPosService.save(pPos);
						//新增日志： 出库
						PPosActLog pPosActLog = new PPosActLog();
						pPosActLog.setPosSn(pPos.getPosSn());
						pPosActLog.setTerminalId(pPos.getTerminalId());			
						pPosActLog.setPosActType("BTERM");
						this.pPosActLogService.save(pPosActLog);
						
					}
					map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
					map.put(SysConst.RESULT_MSG, "保存POS机信息成功");
				}
			}

		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机信息失败");
		}
		return JsonUtil.getJsonString(map);
	}
	
	/**
	 * 报修
	 * @param pPos
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pPos:malfunc")
	@RequestMapping(value = "malfunc")
	@ResponseBody
	public String malfunc(PPos pPos, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String terminalId = pPos.getTerminalId();
			//解绑SIM卡
			PSim pp = new PSim();
			pp.setPosSn(pPos.getPosSn());
			List<PSim> list = this.pSimService.findPosSim(pp);
			if (list != null && list.size() > 0) {
				PSim pResult = list.get(0);
				String oldSimId = pResult.getId();
				if (StringUtils.isNotEmpty(oldSimId)) {
					PSim ppOld = pSimService.get(oldSimId);
					String oldSimSn = "";
					if(ppOld!=null){
						oldSimSn = ppOld.getPosSn();
						ppOld.setSimStatus("IDLE");
						ppOld.setPosSn("");
						ppOld.setIsNewRecord(false);
						this.pSimService.save(ppOld);	
						//新增日志： 解绑
						PSimActLog pSimActLog = new PSimActLog();
						pSimActLog.setPosSn(oldSimSn);
						pSimActLog.setSimId(ppOld.getSimCommunication()+"|"+(Strings.isNullOrEmpty(ppOld.getPhoneNumber())?"":ppOld.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(ppOld.getSerialNumber())?"":ppOld.getSerialNumber()));							
						pSimActLog.setSimActType("UNBTERM");
						pSimActLog.setSimActMsg("POS机报修，SIM卡解绑");
						pSimActLog.setRemarks("POS机报修，SIM卡解绑");
						this.pSimActLogService.save(pSimActLog);
					}
				}
			}
			
			pPos.setRepairBy(UserUtils.getPrincipal().getId());
			pPos.setRepairDate(DateUtils.getDate("yyyyMMdd"));
			pPos.setRepairBeforeStatus("IDLE");
			pPos.setTerminalId("");
			pPos.setPosStatus("MALFUNC");
			pPosService.save(pPos);
			//新增日志： 报修
			PPosActLog pPosActLog = new PPosActLog();
			pPosActLog.setTerminalId(terminalId);
			pPosActLog.setPosSn(pPos.getPosSn());
			pPosActLog.setPosActType("REPAIR");
			pPosActLog.setPosActMsg(pPos.getRepairRemarks());
			this.pPosActLogService.save(pPosActLog);
			
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存POS机信息成功");
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机信息失败");
		}
		return JsonUtil.getJsonString(map);
	}
	
	/**
	 * 报废：校验该POS机是否已绑定终端，绑定了终端给出提示。
	 * @param pPos
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pPos:scrap")
	@RequestMapping(value = "scrap")
	@ResponseBody
	public String scrap(PPos pPos, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String terminalId = pPos.getTerminalId();
			//解绑SIM卡
			PSim pp = new PSim();
			pp.setPosSn(pPos.getPosSn());
			List<PSim> list = this.pSimService.findPosSim(pp);
			if (list != null && list.size() > 0) {
				PSim pResult = list.get(0);
				String oldSimId = pResult.getId();
				if (StringUtils.isNotEmpty(oldSimId)) {
					PSim ppOld = pSimService.get(oldSimId);
					String oldSimSn = ppOld.getPosSn();
					if(ppOld!=null){
						ppOld.setSimStatus("IDLE");
						ppOld.setPosSn("");
						ppOld.setIsNewRecord(false);
						this.pSimService.save(ppOld);	
						//新增日志： 解绑
						PSimActLog pSimActLog = new PSimActLog();
						pSimActLog.setPosSn(oldSimSn);
						pSimActLog.setSimId(ppOld.getSimCommunication()+"|"+(Strings.isNullOrEmpty(ppOld.getPhoneNumber())?"":ppOld.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(ppOld.getSerialNumber())?"":ppOld.getSerialNumber()));							
						pSimActLog.setSimActType("UNBTERM");
						pSimActLog.setSimActMsg("POS机报废，SIM卡解绑");
						pSimActLog.setRemarks("POS机报废，SIM卡解绑");
						this.pSimActLogService.save(pSimActLog);
					}
				}
			}
			
			pPos.setScrappedBy(UserUtils.getPrincipal().getId());
			pPos.setScrappedDate(DateUtils.getDate("yyyyMMdd"));
			pPos.setScrappedRemarks(pPos.getScrappedRemarks());			
			pPos.setPosStatus("SCRAP");
			pPos.setTerminalId("");
			pPosService.save(pPos);
			//新增日志： 报废
			PPosActLog pPosActLog = new PPosActLog();
			pPosActLog.setPosSn(pPos.getPosSn());
			pPosActLog.setTerminalId(terminalId);
			pPosActLog.setPosActType("SCRAPPED");
			pPosActLog.setPosActMsg(pPos.getScrappedRemarks());
			pPosActLog.setRemarks("POS机报废");
			this.pPosActLogService.save(pPosActLog);
			
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存POS机信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机信息失败");
		}
		return JsonUtil.getJsonString(map);
	}	
	
	/**
	 * 验证终端与POS状态
	 * 
	 * @param terminalId
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "checkTermPos")
	public String checkTermPos(String terminalId) {
		String msg="";
		if (!Strings.isNullOrEmpty(terminalId)) {
			PPos pp = new PPos();
			pp.setTerminalId(terminalId);
			List<PPos> list = this.pPosService.findTermPos(pp);
			if (list == null || list.size() < 1) {
				msg = "0";
			}else{
				PPos pResult=list.get(0);
				String sn=pResult.getPosSn();
				if(Strings.isNullOrEmpty(sn)){
					msg = "1";
				}else{
					msg="该终端("+terminalId+")已绑定POS机（SN:"+sn+"），且POS机状态为"+DictUtils.getDictLabel(pResult.getPosStatus(), "POS_STATUS", "")+"。确定要把该POS转挂到终端（"+terminalId+"）上?";
				}
			}
		}
		return msg;
	}
	
	
	
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PPos pPos, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 设置厂商名称
			if (pPos.getFactoryId() != null) {
				Map<String, String> pfMap = this.getPFactoryMap();
				if (pfMap != null && pfMap.size() > 0) {
					pPos.setFactoryName(pfMap.get(pPos.getFactoryId()));
				}
			}
			//新增日志： 入库
			boolean  isNew=pPos.getIsNewRecord();
			if(isNew){
				PPosActLog pPosActLog=new PPosActLog();
				pPosActLog.setPosSn(pPos.getPosSn());
				pPosActLog.setPosActType("INSTOCK");
				pPosActLog.setPosActMsg(pPos.getRemarks());
				if (StringUtils.isEmpty(pPos.getPurchaseBy())) {
					pPos.setPurchaseBy(UserUtils.getPrincipal().getName());
				}
				this.pPosActLogService.save(pPosActLog);
				//入库操作，新增入库者和入库时间
				pPos.setInstockBy(UserUtils.getPrincipal().getId());
				pPos.setInstockDate(DateUtils.getDate("yyyyMMdd"));
			}			
			pPosService.save(pPos);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存POS机信息成功");
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机信息失败");
		}
		return JsonUtil.getJsonString(map);
	}
	
	

	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(PPos pPos, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "POS机信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			//Page<PPos> page = pPosService.findPage(new Page<PPos>(request,response, -1), pPos);
			List<PPos> list = pPosService.findList(pPos);
			for (PPos pp : list) {
				//入库着
				if (StringUtils.isNotBlank(pp.getInstockBy())) {
					User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pp.getInstockBy());
					if (user != null) {
						pp.setInstockBy(user.getName());
					}
				}
				//出库者
				if (StringUtils.isNotBlank(pp.getOutstockBy())) {
					User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pp.getOutstockBy());
					if (user != null) {
						pp.setOutstockBy(user.getName());
					}
				}
				//报修者
				if (StringUtils.isNotBlank(pp.getRepairBy())) {
					User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pp.getRepairBy());
					if (user != null) {
						pp.setRepairBy(user.getName());
					}
				}
				//报废者
				if (StringUtils.isNotBlank(pp.getScrappedBy())) {
					User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pp.getScrappedBy());
					if (user != null) {
						pp.setScrappedBy(user.getName());
					}
				}
			}
			new ExportExcel("POS机信息", PPos.class).setDataList(list)
					.write(request, response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出POS机信息！失败信息：" + e.getMessage());
		}
		return "redirect:"  + "/pos/pPos/list?repage";
	}

	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "delete")
	public String delete(PPos pPos, RedirectAttributes redirectAttributes) {
		pPosService.delete(pPos);
		addMessage(redirectAttributes, "删除POS机信息成功");
		return "redirect:"  + "/pos/pPos/list?repage";
	}

	/**
	 * 设置厂商MAP
	 * 
	 * @return
	 */
	public Map<String, String> getPFactoryMap() {
		Map<String, String> map = new HashMap<String, String>();
		// 获取店长码表
		PFactory pManagerSM = new PFactory();
		pManagerSM.setFactoryStatus("0");
		pManagerSM.setDelFlag("0");
		List<PFactory> storeManagerList = pFactoryService.findList(pManagerSM);
		for (PFactory pm : storeManagerList) {
			map.put(pm.getId(), pm.getFactoryName());
		}
		return map;
	}

	/**
	 * 根据厂商ID获取型号LIST
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param factoryId
	 * @return
	 */
	@RequestMapping(value = { "getPModelList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getPModelList(HttpServletRequest req,
			HttpServletResponse resp, Model model, String factoryId) {
		PModel entity = new PModel();
		entity.setFactoryId(factoryId);
		entity.setDelFlag("0");
		entity.setModelStatus("0");
		List<PModel> list = this.pModelService.findList(entity);
		List<PModelSel> pmList = new ArrayList<PModelSel>();
		PModelSel pm = null;
		for (PModel pm1 : list) {
			pm = new PModelSel();
			pm.setModelId(pm1.getModelId());
			pmList.add(pm);
		}
		return JsonUtil.getJsonString(pmList);
	}

	/**
	 * 导入POS机数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + "/pos/pPos/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PPosExcel> list = ei.getDataList(PPosExcel.class);
			PPos pPos = null;
			for (PPosExcel entity : list) {
				try {
					// SN、厂商和型号为必填项，如果没有，直接跳过
					if (entity != null
							&& !Strings.isNullOrEmpty(entity.getPosSn())
							&& !Strings.isNullOrEmpty(entity.getFactoryName())
							&& !Strings.isNullOrEmpty(entity.getModelId())
							&& !Strings.isNullOrEmpty(entity.getPosType())
							&& !Strings.isNullOrEmpty(entity.getPosScanFlag())
							&& !Strings.isNullOrEmpty(entity.getLayOutFlag())) {
						if ("true".equals(this.checkSN(entity.getPosSn()))) {
							BeanValidators.validateWithException(validator,
									entity);
							pPos = new PPos();
							pPos.setPosSn(entity.getPosSn().trim());
							pPos.setFactoryId(entity.getFactoryName().split("_")[1]);
							pPos.setFactoryName(entity.getFactoryName().split("_")[0]);
							pPos.setModelId(entity.getModelId());
							pPos.setPosType(entity.getPosType().split("_")[1]);
							pPos.setPosScanFlag(entity.getPosScanFlag().split("_")[1]);
							pPos.setLayOutFlag(entity.getLayOutFlag().split("_")[1]);
							pPos.setPurchaseDate(entity.getPurchaseDate());
							pPos.setPurchaseBy(entity.getPurchaseBy());
							pPos.setWarrantyTimeLimit(entity.getWarrantyTimeLimit());
							pPos.setRemarks(entity.getRemarks());
							pPos.setInstockBatch(entity.getInstockBatch());
							//添加入库者\入库时间\状态
							pPos.setInstockBy(UserUtils.getPrincipal().getId());
							pPos.setInstockDate(DateUtils.getDate("yyyyMMdd"));
							pPos.setPosStatus("IDLE");					
							this.pPosService.save(pPos);
							//添加日志：入库
							PPosActLog pPosActLog=new PPosActLog();
							pPosActLog.setPosSn(pPos.getPosSn());
							pPosActLog.setPosActType("INSTOCK");
							pPosActLog.setPosActMsg(pPos.getRemarks());
							this.pPosActLogService.save(pPosActLog);
							successNum++;
						} else {
							failureMsg.append("<br/>SN" + entity.getPosSn()
									+ " 已存在; ");
							failureNum++;
						}
					}else{
						failureMsg.append("<br/>SN:" + entity.getPosSn()
								+ ",失败原因:SN、厂商、型号、设备类型、是否支持扫码、布放类型为必填项 ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg
							.append("<br/>SN " + entity.getPosSn() + " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
					}
					failureNum++;
				} catch (Exception ex) {
					failureMsg.append("<br/>SN " + entity.getPosSn() + " 导入失败："
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
		return "redirect:" + "/pos/pPos/list?repage";
	}
	
	/**
	 * 下载导入POS机数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletRequest req,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			workbook = new HSSFWorkbook();// excel文件对象
			HSSFSheet sheet1 = workbook.createSheet("POS机信息");// 工作表对象
			// 设置标题样式
			this.setHeadCellStyles(workbook, sheet1);
			// 设置列头样式
			this.setTitleCellStyles(workbook, sheet1);
			// 设置数据样式
			this.setDataCellStyles(workbook, sheet1);
			// 创建一个隐藏页、隐藏数据集和名称管理
			this.creatHideSheet(workbook);
			// 创建标题和列头数据
			String headName = "POS机信息";
			this.creatAppRowHead(sheet1, headName);
			// 设置下拉框
			this.createSelectValidate(sheet1);

			/**************************** 输出流 *****************************************/
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = POIUtils.encodeFileName(req, headName);
			OutputStream os = response.getOutputStream();// 取得输出流
			response.setHeader("Content-disposition", "attachment;filename="
					+ filename + ".xls");
			workbook.write(os);
			os.close();

			System.out.println("导出成功!");
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + "/pos/pPos/list?repage";
	}
	
	/**
	 * 下载导入POS机、SIM卡数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pPos:view")
	@RequestMapping(value = "importSim/templateSim")
	public String importSimFileTemplate(HttpServletRequest req,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String headName="POS机关联SIM卡信息 ";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "excelExportTemp/POS_SIM_EXPORT_TEMP.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));

			/**************************** 输出流 *****************************************/
			OutputStream os = response.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			response.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
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
		return "redirect:" + "/pos/pPos/list?repage";
	}
	
	/**
	 * 导入POS机、SIM卡数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "importSim", method = RequestMethod.POST)
	public String importSimFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + "/pos/pPos/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PosSimExcel> list = ei.getDataList(PosSimExcel.class);
			String simCom="";
			for (PosSimExcel entity : list) {
				try {
					// 运营商、手机号或SIM卡串号、SN码为必填项，如果没有，直接跳过
					if (entity != null
							&& !Strings.isNullOrEmpty(entity.getSimCommunication())
							&& (!Strings.isNullOrEmpty(entity.getPhoneNumber()) || !Strings.isNullOrEmpty(entity.getSerialNumber()))
							&& !Strings.isNullOrEmpty(entity.getPosSn())) {
						simCom = entity.getSimCommunication().substring(3);
						//出库者ID
						String outstockDate = Strings.isNullOrEmpty(entity.getOutstockDate())?DateUtils.getDate("yyyyMMdd"):entity.getOutstockDate().trim(); // 出库时间
						String outstockBy = "";  // 出库者
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
						//检查SN号
						List<PPos> simSN = checkSimSN(entity.getPosSn());
						//SN不存在
						if (simSN == null || simSN.size() == 0) {
							failureMsg.append("<br/>SN" + entity.getPosSn()
							+ " 不存在; ");
							failureNum++;
							continue;
						}else {
							List<PSim> sim = this.checkSIM(simCom,entity.getPhoneNumber(),entity.getSerialNumber());
							//SIM卡不存在
							if (sim == null || sim.size() == 0) {
								failureMsg.append("<br/>"+entity.getSimCommunication()+" | "+entity.getPhoneNumber()
								+" | "+entity.getSerialNumber()+" SIM卡信息不存在; ");
								failureNum++;
								continue;
							}else {
								//出库
								//存在，判断POS机状态
								PPos pResult = simSN.get(0);
								String posStatus = pResult.getPosStatus();
								//存在，判断SIM卡状态
								PSim sResult = sim.get(0);
								String simStatus = sResult.getSimStatus();
								
								if ("SCRAP".equals(posStatus) || "MALFUNC".equals(posStatus)) {
									failureMsg.append("<br/>SN:"+entity.getPosSn()+"的POS机出现故障，或已报废; ");
									failureNum++;
									continue;
								}
								if ("SCRAP".equals(simStatus) || "MALFUNC".equals(simStatus)) {
									failureMsg.append("<br/>"+entity.getSimCommunication()+" | "+entity.getPhoneNumber()
									+" | "+entity.getSerialNumber()+" SIM卡出现故障，或已报废; ");
									failureNum++;
									continue;
								}
								//判断POS机是否已与某SIM卡关联
								PSim pp = new PSim();
								pp.setPosSn(pResult.getPosSn());
								List<PSim> listSim = this.pSimService.findPosSim(pp);
								//未关联
								if ((listSim != null && listSim.size() != 0) && StringUtils.isEmpty(listSim.get(0).getId())) {
									//如果SIM卡为空闲，直接关联
									if ("IDLE".equals(simStatus)) {
										sResult.setOutstockBy(outstockBy);
										sResult.setOutstockDate(outstockDate);
										sResult.setSimStatus("USE");
										sResult.setPosSn(pResult.getPosSn());
										pSimService.usablePSim(sResult);
										//新增日志： 出库
										PSimActLog pSimActLog = new PSimActLog();
										pSimActLog.setPosSn(pResult.getPosSn());
										pSimActLog.setSimId(simCom + "|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber());
										pSimActLog.setSimActType("BTERM");
										pSimActLog.setSimActMsg("SIM卡关联POS机");
										pSimActLog.setRemarks("SIM卡关联POS机");
										this.pSimActLogService.save(pSimActLog);
										successNum++;
									}else if ("USE".equals(simStatus)) {
										String oldSimSn = sResult.getPosSn();
										sResult.setSimStatus("IDLE");
										sResult.setPosSn("");
										sResult.setOutstockBy("");
										sResult.setOutstockDate("");
										this.pSimService.usablePSim(sResult);	
										
										//新增日志： 解绑
										PSimActLog pSimActLog = new PSimActLog();
										pSimActLog.setPosSn(oldSimSn);
										pSimActLog.setSimId(simCom+"|"+(Strings.isNullOrEmpty(entity.getPhoneNumber())?"":entity.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(entity.getSerialNumber())?"":entity.getSerialNumber()));							
										pSimActLog.setSimActType("UNBTERM");
										pSimActLog.setSimActMsg("SIM卡关联新POS机，SIM卡解绑");
										pSimActLog.setRemarks("SIM卡关联新POS机，SIM卡解绑");
										this.pSimActLogService.save(pSimActLog);
										
										//该POS机绑定新的SIM卡
										sResult.setOutstockBy(outstockBy);
										sResult.setOutstockDate(outstockDate);
										sResult.setSimStatus("USE");
										sResult.setPosSn(pResult.getPosSn());
										pSimService.usablePSim(sResult);
										
										//新增日志： 出库
										PSimActLog pSimActLogUSE = new PSimActLog();
										pSimActLogUSE.setPosSn(pResult.getPosSn());
										pSimActLogUSE.setSimId(simCom+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber());
										pSimActLogUSE.setSimActType("BTERM");
										pSimActLogUSE.setSimActMsg("新SIM卡关联POS机");
										pSimActLogUSE.setRemarks("新SIM卡关联POS机");
										this.pSimActLogService.save(pSimActLogUSE);
										successNum++;
									}
								}else{
									//如果SIM卡为空闲，直接关联
									if ("IDLE".equals(simStatus)) {
										//已关联
										PSim pSim = listSim.get(0);
										//先把原来的SIM卡解绑
										String oldSimSn = pSim.getPosSn();
										pSim.setSimStatus("IDLE");
										pSim.setPosSn("");
										pSim.setOutstockBy("");
										pSim.setOutstockDate("");
										this.pSimService.usablePSim(pSim);	
										
										//新增日志： 解绑
										PSimActLog pSimActLog = new PSimActLog();
										pSimActLog.setPosSn(oldSimSn);
										pSimActLog.setSimId(simCom+"|"+(Strings.isNullOrEmpty(entity.getPhoneNumber())?"":entity.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(entity.getSerialNumber())?"":entity.getSerialNumber()));							
										pSimActLog.setSimActType("UNBTERM");
										pSimActLog.setSimActMsg("新SIM卡关联，老SIM卡解绑");
										pSimActLog.setRemarks("新SIM卡关联，老SIM卡解绑");
										this.pSimActLogService.save(pSimActLog);
										
										sResult.setOutstockBy(outstockBy);
										sResult.setOutstockDate(outstockDate);
										sResult.setSimStatus("USE");
										sResult.setPosSn(pResult.getPosSn());
										pSimService.usablePSim(sResult);
										
										//新增日志： 出库
										PSimActLog pSimActLogUSE = new PSimActLog();
										pSimActLogUSE.setPosSn(pResult.getPosSn());
										pSimActLogUSE.setSimId(simCom + "|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber());
										pSimActLogUSE.setSimActType("BTERM");
										pSimActLogUSE.setSimActMsg("新SIM卡关联POS机");
										pSimActLogUSE.setRemarks("新SIM卡关联POS机");
										this.pSimActLogService.save(pSimActLogUSE);
										successNum++;
									}else if ("USE".equals(simStatus)) {
										//判断是否一致
										if (entity.getPosSn().equals(sResult.getPosSn())) {
											failureMsg.append("<br/>SN:"+entity.getPosSn()+"的POS机已与对应的SIM卡绑定; ");
											failureNum++;
											continue;
										}else {
											//已关联
											PSim pSim = listSim.get(0);
											//先把原来的SIM卡解绑
											String oldSimSn = pSim.getPosSn();
											pSim.setSimStatus("IDLE");
											pSim.setPosSn("");
											pSim.setOutstockBy("");
											pSim.setOutstockDate("");
											this.pSimService.usablePSim(pSim);	
											
											//新增日志： 解绑
											PSimActLog pSimActLog = new PSimActLog();
											pSimActLog.setPosSn(oldSimSn);
											pSimActLog.setSimId(simCom+"|"+(Strings.isNullOrEmpty(entity.getPhoneNumber())?"":entity.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(entity.getSerialNumber())?"":entity.getSerialNumber()));							
											pSimActLog.setSimActType("UNBTERM");
											pSimActLog.setSimActMsg("新SIM卡关联，老SIM卡解绑");
											pSimActLog.setRemarks("新SIM卡关联，老SIM卡解绑");
											this.pSimActLogService.save(pSimActLog);
											
											oldSimSn = sResult.getPosSn();
											sResult.setSimStatus("IDLE");
											sResult.setPosSn("");
											sResult.setOutstockBy("");
											sResult.setOutstockDate("");
											this.pSimService.usablePSim(sResult);	
											
											//新增日志： 解绑
											PSimActLog pSimActLogUNB = new PSimActLog();
											pSimActLogUNB.setPosSn(oldSimSn);
											pSimActLogUNB.setSimId(simCom+"|"+(Strings.isNullOrEmpty(entity.getPhoneNumber())?"":entity.getPhoneNumber())+"|"+(Strings.isNullOrEmpty(entity.getSerialNumber())?"":entity.getSerialNumber()));							
											pSimActLogUNB.setSimActType("UNBTERM");
											pSimActLogUNB.setSimActMsg("SIM卡关联新POS机，SIM卡解绑");
											pSimActLogUNB.setRemarks("SIM卡关联新POS机，SIM卡解绑");
											this.pSimActLogService.save(pSimActLogUNB);
											
											//该POS机绑定新的SIM卡
											sResult.setOutstockBy(outstockBy);
											sResult.setOutstockDate(outstockDate);
											sResult.setSimStatus("USE");
											sResult.setPosSn(pResult.getPosSn());
											pSimService.usablePSim(sResult);
											
											//新增日志： 出库
											PSimActLog pSimActLogUSE = new PSimActLog();
											pSimActLogUSE.setPosSn(pResult.getPosSn());
											pSimActLogUSE.setSimId(simCom+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber());
											pSimActLogUSE.setSimActType("BTERM");
											pSimActLogUSE.setSimActMsg("新SIM卡关联POS机");
											pSimActLogUSE.setRemarks("新SIM卡关联POS机");
											this.pSimActLogService.save(pSimActLogUSE);
											successNum++;
										}
									}
								}
							}	
						}
					}else{
						failureMsg.append("<br/> 失败原因:运营商、手机号或SIM卡串号、SN码为必填项 ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg
							.append("<br/> " + entity.getPosSn() + " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
					}
					failureNum++;
				} catch (Exception ex) {
					failureMsg.append("<br/> " + entity.getPosSn() + " 导入失败："
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
		return "redirect:" + "/pos/pPos/list?repage";
	}
	
	/**
	 * 验证SN是否有效
	 * 
	 * @param sn
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pPos:edit")
	@RequestMapping(value = "checkSN")
	public String checkSN(String sn) {
		if (!Strings.isNullOrEmpty(sn)) {
			PPos pp = new PPos();
			pp.setPosSn(sn.trim());
			List<PPos> list = this.pPosService.findList(pp);
			if (list == null || list.size() < 1) {
				return "true";
			}
		}
		return "false";
	}
	
	public List<PPos> checkSimSN(String sn) {
		if (!Strings.isNullOrEmpty(sn)) {
			PPos pp = new PPos();
			pp.setPosSn(sn.trim());
			List<PPos> list = this.pPosService.findList(pp);
			return list;
		}
		return null;
	}
	
	/**
	 * 验证SIM卡是否有效
	 * 
	 */
	
	public List<PSim> checkSIM(String simCommunication,String phoneNumber,String serialNumber) {
		if (!Strings.isNullOrEmpty(simCommunication)&&(!Strings.isNullOrEmpty(phoneNumber)||!Strings.isNullOrEmpty(serialNumber))) {
			PSim pp = new PSim();
			pp.setSimCommunication(simCommunication);
			if(!Strings.isNullOrEmpty(phoneNumber)){
				pp.setPhoneNumber(phoneNumber);
			}
			if(!Strings.isNullOrEmpty(serialNumber)){
				pp.setSerialNumber(serialNumber);
			}			
			List<PSim> list = this.pSimService.findList(pp);
			return list;
		}
		return null;
	}

	/*********************** EXCEL导出模板 ********************************/
	private HSSFWorkbook workbook = null;
	private HSSFCellStyle titleStyle = null;
	private HSSFCellStyle dataStyle = null;
	private HSSFCellStyle headStyle = null;

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
		sheet.setColumnWidth(0, 7000);
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 7000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 7000);
		sheet.setColumnWidth(10, 7000);

		

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
	 * 创建隐藏页和数据域
	 * 
	 * @param workbook
	 * @param hideSheetName
	 */
	public void creatHideSheet(HSSFWorkbook workbook) {
		/**************** 创建厂商和型号sheet,行信息:厂商,型号1,型号2,型号3 *****************/
		HSSFSheet factoryAndModelSheet = workbook
				.createSheet("factoryAndModelSheet");// 隐藏一些信息
		// 获取厂商LIST
		PFactory entity = new PFactory();
		entity.setDelFlag("0");
		entity.setFactoryStatus("0");
		List<PFactory> pFactoryList = this.pFactoryService.findList(entity);
		// 获取型号LIST
		PModel pm = new PModel();
		pm.setDelFlag("0");
		pm.setModelStatus("0");
		List<String> rowList = null;
		String factoryId = "";
		String factoryName = "";
		Name name;
		for (int i = 0; i < pFactoryList.size(); i++) {
			HSSFRow pfModelRow = factoryAndModelSheet.createRow(i);
			rowList = new ArrayList<String>();
			factoryId = pFactoryList.get(i).getId();
			factoryName = pFactoryList.get(i).getFactoryName();
			rowList.add(factoryName + "_" + factoryId);
			pm.setFactoryId(factoryId);
			List<PModel> pModelList = this.pModelService.findList(pm);
			// 添加“厂商---》型号” 名称
			name = workbook.createName();
			name.setNameName(factoryName + "_" + factoryId);
			for (PModel pModel : pModelList) {
				rowList.add(pModel.getModelId());
			}
			this.creatRow(pfModelRow, rowList);
			name.setRefersToFormula("factoryAndModelSheet!$B$" + (i + 1) + ":$"
					+ this.getcellColumnFlag(pModelList.size() + 1) + "$"
					+ (i + 1));
		}
		// 添加“厂商” 名称
		name = workbook.createName();
		name.setNameName("factory");
		name.setRefersToFormula("factoryAndModelSheet!$A$1:$A$"
				+ pFactoryList.size());
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("factoryAndModelSheet"),
				true);
		
		/**************** 创建设备类型、是否支持扫码sheet *****************/
		HSSFSheet posTypeAndScanFlag = workbook
				.createSheet("posTypeAndScanFlag");// 隐藏一些信息
		// 获取设备类型LIST
		List<String> posTypeRowList = new ArrayList<String>();
		List<Dict> posTypeList = DictUtils.getDictList("POS_TYPE");
		for (Dict dict : posTypeList) {
			posTypeRowList.add(dict.getLabel() + "_" + dict.getValue());
		}
		// 获取是否支持扫码LIST
		List<Dict> posScanFlagList = DictUtils.getDictList("POS_SCAN_FLAG");
		List<String> posRowList = null;
		Name posName;
		for (int i = 0; i < posTypeRowList.size(); i++) {
			//得到一行
			HSSFRow scanFlagRow = posTypeAndScanFlag.createRow(i);
			posRowList = new ArrayList<String>();
			posRowList.add(posTypeRowList.get(i));
			posName = workbook.createName();
			//引用名
			posName.setNameName(posTypeRowList.get(i));
			
			// 添加“设备类型---》是否支持扫码” 名称
			for (Dict dict : posScanFlagList) {
				posRowList.add(dict.getLabel() + "_" + dict.getValue());
			}
			//创建一行数据
			this.creatRow(scanFlagRow, posRowList);
			if (posTypeRowList.get(i).indexOf("INTELLECT") >= 0) {
				posName.setRefersToFormula("posTypeAndScanFlag!$C$"+(i+1)+":$C$"+(i+1));
			}else{
				posName.setRefersToFormula("posTypeAndScanFlag!$B$" + (i + 1) + ":$"
						+ this.getcellColumnFlag(posRowList.size() + 1) + "$"
						+ (i + 1));
			}
		}
		// 添加“设备类型” 名称
		posName = workbook.createName();
		posName.setNameName("POS_TYPE");
		posName.setRefersToFormula("posTypeAndScanFlag!$A$1:$A$"
				+ posTypeRowList.size());
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("posTypeAndScanFlag"),
				true);

		/**************** 创建其他码表sheet：布放类型 *****************/
		HSSFSheet otherSheet = workbook.createSheet("otherSheet");
		// 设备类型
		/*HSSFRow posTypeRow = otherSheet.createRow(0);
		List<String> posTypeRowList = new ArrayList<String>();
		List<Dict> posTypeList = DictUtils.getDictList("POS_TYPE");
		posTypeRowList.add("POS_TYPE");
		for (Dict dict : posTypeList) {
			posTypeRowList.add(dict.getLabel() + "_" + dict.getValue());
		}
		this.creatRow(posTypeRow, posTypeRowList);
		name = workbook.createName();
		name.setNameName("POS_TYPE");
		name.setRefersToFormula("otherSheet!$B$1:$D$1");*/
		// 是否支持扫码
		/*HSSFRow posScanFlagRow = otherSheet.createRow(1);
		List<String> posScanFlagRowList = new ArrayList<String>();
		List<Dict> posScanFlagList = DictUtils.getDictList("POS_SCAN_FLAG");
		posScanFlagRowList.add("POS_SCAN_FLAG");
		for (Dict dict : posScanFlagList) {
			posScanFlagRowList.add(dict.getLabel() + "_" + dict.getValue());
		}
		this.creatRow(posScanFlagRow, posScanFlagRowList);
		name = workbook.createName();
		name.setNameName("POS_SCAN_FLAG");
		name.setRefersToFormula("otherSheet!$B$2:$C$2");*/
		// 布放类型
		HSSFRow layOutFlagRow = otherSheet.createRow(2);
		List<String> layOutFlagRowList = new ArrayList<String>();
		List<Dict> layOutFlagList = DictUtils.getDictList("LAY_OUT_FLAG");
		layOutFlagRowList.add("LAY_OUT_FLAG");
		for (Dict dict : layOutFlagList) {
			layOutFlagRowList.add(dict.getLabel() + "_" + dict.getValue());
		}
		this.creatRow(layOutFlagRow, layOutFlagRowList);
		name = workbook.createName();
		name.setNameName("LAY_OUT_FLAG");
		name.setRefersToFormula("otherSheet!$B$3:$C$3");
		workbook.setSheetHidden(workbook.getSheetIndex("otherSheet"), true);
	}

	/**
	 * 创建标题应用列头
	 * 
	 * @param userinfosheet1
	 * @param userName
	 */
	public void creatAppRowHead(HSSFSheet userinfosheet1, String headName) {
		// 设置标题
		HSSFRow rowHead = userinfosheet1.createRow(0);
		userinfosheet1.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));
		POIUtils.createCell(rowHead, (short) 0, headName, headStyle);
		rowHead.setHeight((short) (27 * 20));

		// 设置表头
		HSSFRow row = userinfosheet1.createRow(1);

		HSSFCell serialNumberCell = row.createCell(0);
		serialNumberCell.setCellValue("SN码");
		serialNumberCell.setCellStyle(titleStyle);

		HSSFCell titleCell = row.createCell(1);
		titleCell.setCellValue("厂商");
		titleCell.setCellStyle(titleStyle);

		HSSFCell proposeTimeCell = row.createCell(2);
		proposeTimeCell.setCellValue("型号");
		proposeTimeCell.setCellStyle(titleStyle);

		HSSFCell systemCell = row.createCell(3);
		systemCell.setCellValue("设备类型");
		systemCell.setCellStyle(titleStyle);

		HSSFCell groupCell = row.createCell(4);
		groupCell.setCellValue("是否支持扫描");
		groupCell.setCellStyle(titleStyle);

		HSSFCell Cell5 = row.createCell(5);
		Cell5.setCellValue("布放类型");
		Cell5.setCellStyle(titleStyle);

		HSSFCell Cell6 = row.createCell(6);
		Cell6.setCellValue("采购日期");
		Cell6.setCellStyle(titleStyle);

		HSSFCell Cell7 = row.createCell(7);
		Cell7.setCellValue("采购者");
		Cell7.setCellStyle(titleStyle);

		HSSFCell groupCell8 = row.createCell(8);
		groupCell8.setCellValue("保修时间");
		groupCell8.setCellStyle(titleStyle);

		HSSFCell groupCell9 = row.createCell(9);
		groupCell9.setCellValue("入库批次号");
		groupCell9.setCellStyle(titleStyle);
		
		HSSFCell groupCell10 = row.createCell(10);
		groupCell10.setCellValue("备注");
		groupCell10.setCellStyle(titleStyle);
	}

	/**
	 * 添加下拉框限制
	 * 
	 * @param sheet1
	 */
	public void createSelectValidate(HSSFSheet sheet1) {
		// 第二列:厂商
		DVConstraint constraint = DVConstraint
				.createFormulaListConstraint("factory");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(2, 499, 1, 1);
		DataValidation data_validation_list = new HSSFDataValidation(regions,
				constraint);
		sheet1.addValidationData(data_validation_list);

		// 第三列:型号
		DVConstraint constraintModel = DVConstraint
				.createFormulaListConstraint("INDIRECT($B$3:$B$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsModel = new CellRangeAddressList(2, 499, 2,
				2);
		DataValidation data_validation_Model = new HSSFDataValidation(
				regionsModel, constraintModel);
		sheet1.addValidationData(data_validation_Model);

		// 第四列:设备类型
		DVConstraint constraintPosType = DVConstraint
				.createFormulaListConstraint("POS_TYPE");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsPosType = new CellRangeAddressList(2, 499,
				3, 3);
		DataValidation data_validation_PosType = new HSSFDataValidation(
				regionsPosType, constraintPosType);
		sheet1.addValidationData(data_validation_PosType);

		// 第五列:是否支持扫码
		DVConstraint constraintPosScanFlag = DVConstraint
				.createFormulaListConstraint("INDIRECT($D$3:$D$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsPosScanFlag = new CellRangeAddressList(2,
				499, 4, 4);
		DataValidation data_validation_PosScanFlag = new HSSFDataValidation(
				regionsPosScanFlag, constraintPosScanFlag);
		sheet1.addValidationData(data_validation_PosScanFlag);

		// 第六列:布放类型
		DVConstraint constraintLayOutFlag = DVConstraint
				.createFormulaListConstraint("LAY_OUT_FLAG");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsLayOutFlag = new CellRangeAddressList(2,
				499, 5, 5);
		DataValidation data_validation_LayOutFlag = new HSSFDataValidation(
				regionsLayOutFlag, constraintLayOutFlag);
		sheet1.addValidationData(data_validation_LayOutFlag);

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

}