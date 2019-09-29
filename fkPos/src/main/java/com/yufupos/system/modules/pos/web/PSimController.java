package com.yufupos.system.modules.pos.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import com.yufupos.system.modules.pos.entity.PSim;
import com.yufupos.system.modules.pos.entity.PSimActLog;
import com.yufupos.system.modules.pos.entity.PSimExcel;
import com.yufupos.system.modules.pos.entity.PSimReport;
import com.yufupos.system.modules.pos.service.PSimActLogService;
import com.yufupos.system.modules.pos.service.PSimService;
import com.yufupos.system.modules.sys.entity.Dict;
import com.yufupos.system.modules.sys.entity.User;
import com.yufupos.system.modules.sys.service.UserService;
import com.yufupos.system.modules.sys.utils.DictUtils;
import com.yufupos.system.modules.sys.utils.LogUtils;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * SIM卡信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/pSim")
public class PSimController extends BaseController {

	@Autowired
	private PSimService pSimService;
	@Autowired
	private PSimActLogService  pSimActLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	@ModelAttribute
	public PSim get(@RequestParam(required=false) String id) {
		PSim entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pSimService.get(id);
		}
		if (entity == null){
			entity = new PSim();
		}
		return entity;
	}
	
	@RequiresPermissions("pos:pSim:view")
	@RequestMapping(value = {"list", ""})
	public String list(PSim pSim, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PSim> page = pSimService.findPage(new Page<PSim>(request, response), pSim); 
		List<PSim> list = page.getList();
		//匹配采购者名称和入库者名称
		for (PSim ps : list) {
			//入库着
			if (StringUtils.isNotBlank(ps.getInstockBy())) {
				User user = com.yufupos.system.modules.sys.utils.UserUtils.get(ps.getInstockBy());
				if (user != null) {
					ps.setInstockBy(user.getName());
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/pos/pSimList";
	}
	
	@RequiresPermissions("pos:pSim:view")
	@RequestMapping(value = {"listSum", ""})
	public String listSum(PSim pSim, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("listSum", pSimService.findSimSum(pSim));
		return "modules/pos/pSimListSum";
	}
	
	@RequiresPermissions("pos:pSim:view")
    @RequestMapping(value = "exportSum", method=RequestMethod.POST)
    public String exportSumFile(PSim pSim, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "SIM卡统计信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<PSimReport> list = pSimService.findSimSum(pSim);
            List<Dict> dictList = DictUtils.getDictList("SIM_COMMUNICATION");
			for (PSimReport psr : list) {
				for (Dict dict : dictList) {
					if (dict.getValue().trim().equals(psr.getSimCommunication().trim())) {
						psr.setSimCommunication(dict.getLabel().trim());
					}
				}
			}
            new ExportExcel("SIM卡统计信息 "+DateUtils.getDate("yyyy-MM-dd HH:mm:ss"), PSimReport.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出SIM卡统计信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pSim/listSum?repage";
    }	

	@RequiresPermissions("pos:pSim:view")
	@RequestMapping(value = "form")
	public String form(PSim pSim, Model model) {
		pSim.setPurchaseDate(DateUtils.getDate("yyyyMMdd"));
		pSim.setPurchaseBy(UserUtils.getPrincipal().getName());		
		pSim.setSimStatus("IDLE");
		model.addAttribute("pSim", pSim);
		return "modules/pos/pSimForm";
	}


	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(PSim pSim, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean  isNew = pSim.getIsNewRecord();	
			if(isNew){
				PSim pp = new PSim();
				pp.setSimCommunication(pSim.getSimCommunication());
				if(!Strings.isNullOrEmpty(pSim.getPhoneNumber())){
					pp.setPhoneNumber(pSim.getPhoneNumber());
				}
				if(!Strings.isNullOrEmpty(pSim.getSerialNumber())){
					pp.setSerialNumber(pSim.getSerialNumber());
				}			
				List<PSim> list = this.pSimService.findList(pp);
				if (list!= null && list.size() > 0) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "该运营商该手机号或SIM卡串号有重复，请重新输入");
				}else{					
					if(Strings.isNullOrEmpty(pSim.getInstockBy())){
						pSim.setInstockBy(UserUtils.getPrincipal().getId());
						pSim.setInstockDate(DateUtils.getDate("yyyyMMdd"));
					}
					if (StringUtils.isEmpty(pSim.getPurchaseBy())) {
						pSim.setPurchaseBy(UserUtils.getPrincipal().getName());
					}
					pSimService.save(pSim);
					//新增日志： 入库
					PSimActLog pSimActLog=new PSimActLog();
					pSimActLog.setSimActType("INSTOCK");
					pSimActLog.setSimActMsg(pSim.getRemarks());
					pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
					this.pSimActLogService.save(pSimActLog);
					map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
					map.put(SysConst.RESULT_MSG, "保存SIM卡信息成功");
				}
			}
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存SIM卡信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:pSim:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PSim pSim, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "SIM卡信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<PSim> page = pSimService.findPage(new Page<PSim>(request, response ,-1), pSim); 
			List<PSim> list = pSimService.findList(pSim);
			for (PSim pp : list) {
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
			new ExportExcel("SIM卡信息", PSim.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出SIM卡信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/pSim/list?repage";
    }
	
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "delete")
	public String delete(PSim pSim, RedirectAttributes redirectAttributes) {
		pSimService.delete(pSim);
		addMessage(redirectAttributes, "删除SIM卡信息成功");
		return "redirect:"+"/pos/pSim/list?repage";
	}
	
	/**
	 * 下载导入POS机数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pSim:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletRequest req,HttpServletResponse resp, RedirectAttributes redirectAttributes) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			String headName="SIM卡信息 ";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "excelExportTemp/SIM_EXPORT_TEMP.xls"; // excel模板
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
		return "redirect:"+"/pos/pSim/list?repage";
	}	

	/**
	 * 导入POS机数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + "/pos/pSim/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PSimExcel> list = ei.getDataList(PSimExcel.class);
			PSim pSim = null;
			String simCom="";
			BigDecimal simDeposit = null;
			for (PSimExcel entity : list) {
				try {
					// 运营商、手机号和串号为必填项，如果没有，直接跳过
					if (entity != null
							&& !Strings.isNullOrEmpty(entity.getSimCommunication())&&(!Strings.isNullOrEmpty(entity.getPhoneNumber())||!Strings.isNullOrEmpty(entity.getSerialNumber()))) {
						simCom=entity.getSimCommunication().substring(3);
						String simDepositStr = entity.getSimDeposit();
						try {
							simDeposit = new BigDecimal(simDepositStr);
						} catch (Exception e) {
							failureMsg.append("<br/>" + entity.getSimDeposit() + " 不是数字; ");
							failureNum++;
							continue;
						}
						if ("true".equals(this.checkSIM(simCom,entity.getPhoneNumber(),entity.getSerialNumber()))) {
							BeanValidators.validateWithException(validator,entity);
							pSim = new PSim();
							pSim.setSimCommunication(simCom);
							pSim.setPhoneNumber(entity.getPhoneNumber());
							pSim.setSerialNumber(entity.getSerialNumber());	
							pSim.setPurchaseDate(entity.getPurchaseDate());
							pSim.setPurchaseBy(entity.getPurchaseBy());
							pSim.setInstockBatch(entity.getInstockBatch());
							pSim.setSimDeposit(simDeposit);
							pSim.setRemarks(entity.getRemarks());
							//添加入库者\入库时间\状态
							pSim.setInstockBy(UserUtils.getPrincipal().getId());
							pSim.setInstockDate(DateUtils.getDate("yyyyMMdd"));
							pSim.setSimStatus("IDLE");					
							this.pSimService.save(pSim);
							//添加日志：入库
							PSimActLog pSimActLog=new PSimActLog();
							pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
							pSimActLog.setSimActType("INSTOCK");
							pSimActLog.setSimActMsg(pSim.getRemarks());
							this.pSimActLogService.save(pSimActLog);
							successNum++;
						} else {
							failureMsg.append("<br/>" + entity.getSimCommunication()+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber()
									+ " 已存在; ");
							failureNum++;
						}
					}else{
						failureMsg.append("<br/>"+ entity.getSimCommunication()+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber()
								+ ",失败原因:运营商为必填项，手机号或SIM卡串号至少填一个 ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg
							.append("<br/> " + entity.getSimCommunication()+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber() + " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						//failureNum++;
					}
					failureNum++;
				} catch (Exception ex) {
					failureMsg.append("<br/> " + entity.getSimCommunication()+"|"+entity.getPhoneNumber()+"|"+entity.getSerialNumber() + " 导入失败："
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
		return "redirect:"+"/pos/pSim/list?repage";
	}
	
	/**
	 * 验证运营商、手机号或SIM卡串号是否重复
	 * 
	 * @param sn
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "checkSIM")
	public String checkSIM(String simCommunication,String phoneNumber,String serialNumber) {
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
			if (list == null || list.size() < 1) {
				return "true";
			}
		}
		return "false";
	}
	
	/**
	 * 验证POS与SIM
	 * 
	 * @param terminalId
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "checkPosSim")
	public String checkPosSim(String posSn) {
		String msg="参数不能为空";
		if (!Strings.isNullOrEmpty(posSn)) {
			PSim pp = new PSim();
			pp.setPosSn(posSn);
			List<PSim> list = this.pSimService.findPosSim(pp);
			if (list == null || list.size() < 1) {
				msg = "0";
			}else{
				PSim pResult=list.get(0);
				String sn=pResult.getId();
				if(Strings.isNullOrEmpty(sn)){
					msg = "1";
				}else{
					msg="该POS机("+posSn+")已绑定SIM卡（id:"+sn+"），且SIM卡状态为"+DictUtils.getDictLabel(pResult.getSimStatus(), "SIM_STATUS", "")+"。确定要把该SIM卡转挂到POS机（"+posSn+"）上?";
				}
			}
		}
		return msg;
	}
		
	

	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PSim pSim, Model model) {
		model.addAttribute("pSim", pSim);
		return "modules/pos/pSimUpdate";
	}
	
	/**
	 * 修好
	 * @param pSim
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pSim:malfunc")
	@RequestMapping(value = { "toMalfuncFine" })
	public String toMalfuncFine(PSim pSim,HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		String info="";
		try {
			pSim.setSimStatus(pSim.getRepairBeforeStatus());
			this.pSimService.save(pSim);
			//新增日志： 修好
			PSimActLog pSimActLog = new PSimActLog();
			pSimActLog.setPosSn(pSim.getPosSn());
			pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
			pSimActLog.setSimActType("REPFINE");
			this.pSimActLogService.save(pSimActLog);			
			info="状态修改成功";				
		} catch (Exception e) {
			LogUtils.saveLog(request, PSimController.class, e, "修好状态修改失败");
			info="数据库异常，操作失败"+e.getMessage();
		}
		addMessage(redirectAttributes, info);
		return "redirect:" + "/pos/pSim/list?repage";
	}	
	
	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "toAction")
	public String toAction(PSim pSim, Model model,String actFlag) {
		String returnJsp="";		
		if("outStock".equals(actFlag)){//出库
			returnJsp="modules/pos/pSimOutStock";
		}else if("malfunc".equals(actFlag)){//报修
			returnJsp="modules/pos/pSimMalfunc";
		}else if("scrap".equals(actFlag)){//报废
			returnJsp="modules/pos/pSimScrap";
		}
		model.addAttribute("pSim", pSim);
		return returnJsp;
	}	
	

	@RequiresPermissions("pos:pSim:edit")
	@RequestMapping(value = "toShow")
	public String toShow(PSim pSim, Model model) {
		//入库着
		if (StringUtils.isNotBlank(pSim.getInstockBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pSim.getInstockBy());
			if (user != null) {
				pSim.setInstockBy(user.getName());
			}
		}
		//出库者
		if (StringUtils.isNotBlank(pSim.getOutstockBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pSim.getOutstockBy());
			if (user != null) {
				pSim.setOutstockBy(user.getName());
			}
		}
		//报修者
		if (StringUtils.isNotBlank(pSim.getRepairBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pSim.getRepairBy());
			if (user != null) {
				pSim.setRepairBy(user.getName());
			}
		}
		//报废者
		if (StringUtils.isNotBlank(pSim.getScrappedBy())) {
			User user = com.yufupos.system.modules.sys.utils.UserUtils.get(pSim.getScrappedBy());
			if (user != null) {
				pSim.setScrappedBy(user.getName());
			}
		}
		model.addAttribute("pSim", pSim);
		return "modules/pos/pSimShow";
	}
	
	/**
	 * 出库： 校验是否存在该POS机：1.不存在，直接报不错在；2.存在：2.1如果该POS机已绑定POS，先解绑，再绑定；2.2如果该POS机没有绑定POS，直接绑定POS。
	 * @param pSim
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pSim:outStock")
	@RequestMapping(value = "outStock")
	@ResponseBody
	public String outStock(PSim pSim, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String posSn=pSim.getPosSn();
			if (!Strings.isNullOrEmpty(posSn)) {
				PSim pp = new PSim();
				pp.setPosSn(posSn);
				List<PSim> list = this.pSimService.findPosSim(pp);
				if (list == null || list.size() < 1) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "该POS机不存在，请确认后再操作");
				}else{
					PSim pResult=list.get(0);
					String oldSimId=pResult.getId();
					if(Strings.isNullOrEmpty(oldSimId)){
						pSim.setOutstockBy(UserUtils.getPrincipal().getId());
						pSim.setOutstockDate(DateUtils.getDate("yyyyMMdd"));
						pSim.setSimStatus("USE");
						pSimService.save(pSim);
						//新增日志： 出库
						PSimActLog pSimActLog = new PSimActLog();
						pSimActLog.setPosSn(pSim.getPosSn());
						pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
						pSimActLog.setSimActType("BTERM");
						pSimActLog.setSimActMsg("SIM卡关联POS机");
						pSimActLog.setRemarks("SIM卡关联POS机");
						this.pSimActLogService.save(pSimActLog);
					}else{
						//1.先解绑该POS机对应的老sim卡
						PSim ppOld =pSimService.get(oldSimId);
						String oldSimSn=ppOld.getPosSn();
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
							pSimActLog.setSimActMsg("新SIM卡关联，老SIM卡解绑");
							pSimActLog.setRemarks("新SIM卡关联，老SIM卡解绑");
							this.pSimActLogService.save(pSimActLog);
						}
						//2.该POS机绑定新的SIM卡
						pSim.setOutstockBy(UserUtils.getPrincipal().getId());
						pSim.setOutstockDate(DateUtils.getDate("yyyyMMdd"));
						pSim.setSimStatus("USE");
						pSimService.save(pSim);
						//新增日志： 出库
						PSimActLog pSimActLog = new PSimActLog();
						pSimActLog.setPosSn(pSim.getPosSn());
						pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
						pSimActLog.setSimActType("BTERM");
						pSimActLog.setSimActMsg("新SIM卡关联POS机");
						pSimActLog.setRemarks("新SIM卡关联POS机");
						this.pSimActLogService.save(pSimActLog);
						
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
	 * @param pSim
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pSim:malfunc")
	@RequestMapping(value = "malfunc")
	@ResponseBody
	public String malfunc(PSim pSim, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String posSn = pSim.getPosSn();
			pSim.setRepairBy(UserUtils.getPrincipal().getId());
			pSim.setRepairDate(DateUtils.getDate("yyyyMMdd"));
			pSim.setRepairBeforeStatus("IDLE");
			pSim.setSimStatus("MALFUNC");
			pSim.setPosSn("");
			pSimService.save(pSim);
			//新增日志： 报修
			PSimActLog pSimActLog = new PSimActLog();
			pSimActLog.setPosSn(posSn);
			pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());
			pSimActLog.setSimActType("REPAIR");
			pSimActLog.setSimActMsg(pSim.getRepairRemarks());
			this.pSimActLogService.save(pSimActLog);
			
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
	 * @param pSim
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pos:pSim:scrap")
	@RequestMapping(value = "scrap")
	@ResponseBody
	public String scrap(PSim pSim, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String posSn = pSim.getPosSn();
			pSim.setScrappedBy(UserUtils.getPrincipal().getId());
			pSim.setScrappedDate(DateUtils.getDate("yyyyMMdd"));
			pSim.setSimStatus("SCRAP");
			pSim.setPosSn("");
			pSimService.save(pSim);
			//新增日志： 报废
			PSimActLog pSimActLog = new PSimActLog();
			pSimActLog.setPosSn(posSn);
			pSimActLog.setSimId(pSim.getSimCommunication()+"|"+pSim.getPhoneNumber()+"|"+pSim.getSerialNumber());			
			pSimActLog.setSimActType("SCRAPPED");
			pSimActLog.setSimActMsg(pSim.getScrappedRemarks());
			this.pSimActLogService.save(pSimActLog);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存POS机信息成功");
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存POS机信息失败");
		}
		return JsonUtil.getJsonString(map);
	}	
	
}