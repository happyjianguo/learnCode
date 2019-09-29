package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.CortexMerchantX;
import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.entity.CortexTermposX;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearCheckErr;
import cn.yufu.fs.service.ClearCheckErrService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/ClearCheckErr")
public class ClearCheckErrController {
	Log log = Log.getLog(ClearCheckErrController.class);
	
	@Autowired
	@Qualifier("fs.ClearCheckErrService")	
	private ClearCheckErrService ClearCheckErrService;
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService CortexService;
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearCheckErr queryModel) {
		queryModel.setEndDt(getNowDt("yyyyMMddHHmmss",+1));
		queryModel.setStartDt(getLastMonthDay("yyyyMMddHHmmss"));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearCheckErr/clearCheckErrList";
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
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearCheckErr queryModel) {
		// 分页设置
		int count = ClearCheckErrService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearCheckErr> list = ClearCheckErrService.queryList(queryModel, startResult, endResult);
		//设置交易类型名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
		String tranType="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ClearCheckErr cmcb:list){
				tranType=cmcb.getTranType();
				if(tranType!=null&&!"".equals(tranType)){
					cmcb.setTranTypeDesc(consumpCategoryMap.get(tranType));
				}
			}			
		}	
		
		model.addAttribute("ClearCheckErrList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
		String coretranSumAmt="0";
		String acqtranSumAmt="0";
		String sumFee="0";
		if(list!=null&&list.size()>0){
			String sumAmt=ClearCheckErrService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				coretranSumAmt=sumAmt.split("#")[0].toString();
				sumFee=sumAmt.split("#")[1].toString();			
				acqtranSumAmt=sumAmt.split("#")[2].toString();			
			}
		}
		model.addAttribute("coretranSumAmt", coretranSumAmt);
		model.addAttribute("acqtranSumAmt", acqtranSumAmt);
		model.addAttribute("sumFee", sumFee);
		
		return "modules/fs/clearCheckErr/clearCheckErrList";
	}
	
	/**
	 * 跳转添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toAdd" })
	public String toAdd(HttpServletRequest req, HttpServletResponse resp,Model model,ClearCheckErr info) {	
		List<CortexSysParameter> list=CortexService.getSysParamListByParamType("FS_TRAN_TYPE");
		model.addAttribute("fsTranTypeList", list);
		return "modules/fs/clearCheckErr/clearCheckErrAdd";
	}
	/**
	 * 添加信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "addClearCheckErr" })
	@ResponseBody
	public String add(HttpServletRequest req, HttpServletResponse resp, Model model,ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ClearCheckErrService.save(info);
		} catch (Exception e) {
			log.info("保存差错流水信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存差错流水信息失败。");
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
	 */
	@RequestMapping(value = { "toEdit" })
	public String toEdit(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearCheckErrId) {
		ClearCheckErr info = ClearCheckErrService.queryInfo(ClearCheckErrId);
		model.addAttribute("info", info);
		//获取终端码表
		List<CortexTermposX> lst=CortexService.getCortexTermposXList(info.getMerNo(),null);
		model.addAttribute("termPosList",lst);
		
		List<CortexSysParameter> list=CortexService.getSysParamListByParamType("FS_TRAN_TYPE");
		model.addAttribute("fsTranTypeList", list);
		
		return "modules/fs/clearCheckErr/clearCheckErrEdit";
	}
	
	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "edit" }, method = { RequestMethod.POST })
	@ResponseBody
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model,ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			info.setAcqtranAmt(info.getCoretranAmt());
			map = ClearCheckErrService.edit(info);
		} catch (Exception e) {
			log.info("更新差错流水信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "更新差错流水信息失败。");
		}
		return JsonUtil.getJsonString(map);
	}	
	
	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearCheckErrId) {
		ClearCheckErr info = ClearCheckErrService.queryInfo(ClearCheckErrId);
		if(info!=null&&info.getTranType()!=null&&!"".equals(info.getTranType())){
			Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");	
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				info.setTranTypeDesc(consumpCategoryMap.get(info.getTranType()));		
			}
		}
		model.addAttribute("info", info);
		
		return "modules/fs/clearCheckErr/clearCheckErrShow";
	}

	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toFirstCheck" })
	public String toFirstCheck(HttpServletRequest req,
			HttpServletResponse resp, Model model, String ClearCheckErrId) {
		ClearCheckErr info = ClearCheckErrService.queryInfo(ClearCheckErrId);
		if (info!=null&&info.getTranType() != null && !"".equals(info.getTranType())) {
			Map<String, String> consumpCategoryMap = this.CortexService
					.getSysParamMapByParamType("FS_TRAN_TYPE");
			if (consumpCategoryMap != null && consumpCategoryMap.size() > 0) {
				info
						.setTranTypeDesc(consumpCategoryMap.get(info
								.getTranType()));
			}
		}
		model.addAttribute("info", info);
		return "modules/fs/clearCheckErr/clearCheckErrFirstCheck";
	}
	/**
	 * 第一次审核
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "firstCheck" }, method = { RequestMethod.POST })
	@ResponseBody
	public String firstCheck(HttpServletRequest req, HttpServletResponse resp, Model model,ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			info.setStatus("1");
			info.setOperor1(UserUtils.getLoginName()==null||"".equals(UserUtils.getLoginName())?"root":UserUtils.getLoginName());
			info.setOperDt1(getNowDt("yyyyMMddHHmmss",0));
			
			map = ClearCheckErrService.edit(info);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "审核成功");
		} catch (Exception e) {
			log.info("更新差错流水信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "审核失败。");
		}
		return JsonUtil.getJsonString(map);
	}	
	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toSecondCheck" })
	public String toSecondCheck(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearCheckErrId) {
		ClearCheckErr info = ClearCheckErrService.queryInfo(ClearCheckErrId);
		if(info!=null&&info.getTranType()!=null&&!"".equals(info.getTranType())){
			Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");	
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				info.setTranTypeDesc(consumpCategoryMap.get(info.getTranType()));			
			}
		}		
		model.addAttribute("info", info);		
		return "modules/fs/clearCheckErr/clearCheckErrSecondCheck";
	}
	/**
	 * 第二次审核
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "secondCheck" }, method = { RequestMethod.POST })
	@ResponseBody
	public String secondCheck(HttpServletRequest req, HttpServletResponse resp, Model model,ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			info.setStatus("2");
			info.setOperor2(UserUtils.getLoginName()==null||"".equals(UserUtils.getLoginName())?"root":UserUtils.getLoginName());
			info.setOperDt2(getNowDt("yyyyMMddHHmmss",0));
			
			map = ClearCheckErrService.edit(info);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "审核成功");
		} catch (Exception e) {
			log.info("更新差错流水信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "审核失败。");
		}
		return JsonUtil.getJsonString(map);
	}	
	
	/**
	 * 作废
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "invalid" }, method = { RequestMethod.POST })
	@ResponseBody
	public String invalid(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearCheckErrId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ClearCheckErr info=this.ClearCheckErrService.queryInfo(ClearCheckErrId);
			info.setStatus("3");			
			map = ClearCheckErrService.edit(info);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "作废成功");
		} catch (Exception e) {
			log.info("更新差错流水信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "更新差错流水信息失败。");
		}
		return JsonUtil.getJsonString(map);
	}	
	
	/**
	 * 通过商户号获取商户名称
	 * @param req
	 * @param resp
	 * @param model
	 * @param merNo
	 * @return
	 */
	@RequestMapping(value = { "checkCortexMerchantXByMerNo" })
	@ResponseBody
	public String checkCortexMerchantXByMerNo(HttpServletRequest req,HttpServletResponse resp, Model model, String merNo) {
		String merName = "";
		CortexMerchantX merchant=this.CortexService.getCortexMerchantXByMerNo(merNo);
		if(merchant!=null){
			merName=merchant.getMrchtName();
		}
		return merName;
	}

	/**
	 * 通过商户号获取终端LIST
	 * @param req
	 * @param resp
	 * @param model
	 * @param merNo
	 * @return
	 */
	@RequestMapping(value = { "getTerminalListByMerNo" })
	@ResponseBody
	public String getTerminalListByMerNo(HttpServletRequest req,HttpServletResponse resp, Model model, String merNo,String termCode) {
		return JsonUtil.getJsonString(CortexService.getCortexTermposXList(merNo,termCode));
	}
	
	/**
	 * 取前一月日期
	 * @param pattern
	 * @return
	 */
	public String getLastMonthDay(String pattern) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一月
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
		calendar.add(Calendar.MONTH, flag); // 得到前一月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearCheckErr info) {
		List<ClearCheckErr> list = ClearCheckErrService.queryList(info);
		String sumAmt=ClearCheckErrService.getSumAmt(info);
		//String size=list==null?"0":String.valueOf(list.size());
		//合计
		String coretranSumAmt="0";
		String acqtranSumAmt="0";
		String sumFee="0";
		if(list!=null&&list.size()>0){
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				coretranSumAmt=sumAmt.split("#")[0].toString();
				sumFee=sumAmt.split("#")[1].toString();			
				acqtranSumAmt=sumAmt.split("#")[2].toString();			
			}
		}
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—差错流水表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearCheckErrReport.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet =work.getSheetAt(0);

			//设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			HSSFCellStyle style = work.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            POIUtils.createCell(row, (short) 9, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();			
			ClearCheckErr cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){
					String stlFlag=cce.getStlFlag();
					if("0".equals(stlFlag)){
						stlFlag="初登记";
					}else if("1".equals(stlFlag)){
						stlFlag="需要结算";
					}else{
						stlFlag="无需结算";
					}
					String adjustFlag=cce.getAdjustFlag();
					if("0".equals(adjustFlag)){
						adjustFlag="初登记";
					}else if("1".equals(adjustFlag)){
						adjustFlag="需要调账";
					}else{
						adjustFlag="无需调账";
					}					
					String status=cce.getStatus();
					if("0".equals(status)){
						status="初登记";
					}else if("1".equals(status)){
						status="一审完成";
					}else if("2".equals(status)){
						status="二审完成";
					}else{
						status="作废";						
					}
					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));

					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getTermNo()==null||"".equals(cce.getTermNo())?"":cce.getTermNo(),style);
					POIUtils.createCell(row, (short) 4,cce.getCardNo()==null||"".equals(cce.getCardNo())?"":cce.getCardNo(),style);
					POIUtils.createCell(row, (short) 5,"0".equals(cce.getTranType())?"消费":"退货",style);
					POIUtils.createCell(row, (short) 6,cce.getTranDate()==null||"".equals(cce.getTranDate())?"":cce.getTranDate(),style);
					POIUtils.createCell(row, (short) 7,cce.getTranTime()==null||"".equals(cce.getTranTime())?"":cce.getTranTime(),style);
					POIUtils.createCell(row, (short) 8,cce.getCoretranAmt()==null?"":cce.getCoretranAmt().toString(),style);
					POIUtils.createCell(row, (short) 9,cce.getAcqtranAmt()==null?"":cce.getAcqtranAmt().toString(),style);
					POIUtils.createCell(row, (short) 10,cce.getFee()==null?"":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getCheckTime(),style);
					POIUtils.createCell(row, (short) 12,stlFlag,style);
					POIUtils.createCell(row, (short) 13,adjustFlag,style);
					POIUtils.createCell(row, (short) 14,"D".equals(cce.getDcFlag())?"借记":"贷记",style);
					POIUtils.createCell(row, (short) 15,"0".equals(cce.getGenType())?"系统录入":"手工录入",style);
					POIUtils.createCell(row, (short) 16,status,style);
					POIUtils.createCell(row, (short) 17,cce.getCorebatNo()==null?"":cce.getCorebatNo().toString(),style);
					POIUtils.createCell(row, (short) 18,cce.getCorejonlNo()==null?"":cce.getCorejonlNo().toString(),style);
					POIUtils.createCell(row, (short) 19,cce.getCoreRrn()==null||"".equals(cce.getCoreRrn())?"":cce.getCoreRrn(),style);
					POIUtils.createCell(row, (short) 20,cce.getAcqbatNo()==null?"":cce.getAcqbatNo().toString(),style);
					POIUtils.createCell(row, (short) 21,cce.getAcqjonlNo()==null?"":cce.getAcqjonlNo().toString(),style);
					POIUtils.createCell(row, (short) 22,cce.getAcqRrn()==null||"".equals(cce.getAcqRrn())?"":cce.getAcqRrn(),style);
					POIUtils.createCell(row, (short) 23,cce.getComments()==null?"":cce.getComments().toString(),style);						
					POIUtils.createCell(row, (short) 24,cce.getComments1()==null?"":cce.getComments1().toString(),style);						
					POIUtils.createCell(row, (short) 25,cce.getComments2()==null?"":cce.getComments2().toString(),style);						
				}
			}	
			
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			POIUtils.createCell(row, (short) 2, "合计     卡核心交易金额："+coretranSumAmt+"	  收单交易金额："+acqtranSumAmt+"  交易手续费："+sumFee,null);	
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}			
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearCheckErrController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
}
