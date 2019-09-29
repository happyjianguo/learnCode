package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerClearBook;
import cn.yufu.fs.service.ClearMerClearBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/ClearMerClearBook")
public class ClearMerClearBookController {
	Log log = Log.getLog(ClearMerClearBookController.class);
	
	@Autowired
	@Qualifier("fs.ClearMerClearBookService")	
	private ClearMerClearBookService ClearMerClearBookService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerClearBook queryModel) {
		queryModel.setStartDt(getNowDt("yyyyMMdd",-1));
		queryModel.setEndDt(getNowDt("yyyyMMdd",-1));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerClearBook/clearMerClearBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerClearBook queryModel) {
		// 分页设置
		int count = ClearMerClearBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerClearBook> list = ClearMerClearBookService.queryList(queryModel, startResult, endResult);
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");		
		String scene="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ClearMerClearBook cmcb:list){
				scene=cmcb.getScene();
				if(scene!=null){
					cmcb.setSceneName(consumpCategoryMap.get(cmcb.getScene().toString()));
				}
			}
		}
		model.addAttribute("ClearMerClearBookList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
		String tranNumSum="0";
		String tranAmtSum="0";
		String refNumSum="0";
		String refAmtSum="0";	
		String netAmtSum="0";
		String stlAmtSum="0";
		String feeSum="0";
		
		if(list!=null&&list.size()>0){
			String sumAmt=ClearMerClearBookService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tranNumSum=arr[0].toString();
				tranAmtSum=arr[1].toString();
				refNumSum=arr[2].toString();
				refAmtSum=arr[3].toString();
				netAmtSum=arr[4].toString();
				stlAmtSum=arr[5].toString();
				feeSum=arr[6].toString();
			}
		}
		model.addAttribute("tranNumSum", tranNumSum);
		model.addAttribute("tranAmtSum", tranAmtSum);
		model.addAttribute("refNumSum", refNumSum);
		model.addAttribute("refAmtSum", refAmtSum);
		model.addAttribute("netAmtSum", netAmtSum);
		model.addAttribute("stlAmtSum", stlAmtSum);
		model.addAttribute("feeSum", feeSum);
		return "modules/fs/clearMerClearBook/clearMerClearBookList";
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
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearMerClearBookId) {
		ClearMerClearBook info = ClearMerClearBookService.queryInfo(ClearMerClearBookId);
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");	
		if(info.getScene()!=null&&!"".equals(info.getScene())){		
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				info.setSceneName(consumpCategoryMap.get(info.getScene().toString()));
			}
		}
		model.addAttribute("info", info);
		
		return "modules/fs/clearMerClearBook/clearMerClearBookShow";
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
		calendar.add(Calendar.DATE, flag); // 得到前一月
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerClearBook info) {
		List<ClearMerClearBook> list = ClearMerClearBookService.queryList(info);
		//String size=list==null?"0":String.valueOf(list.size());
		//合计
		String tranNumSum="0";
		String tranAmtSum="0";
		String refNumSum="0";
		String refAmtSum="0";	
		String netAmtSum="0";
		String stlAmtSum="0";
		String feeSum="0";
		
		if(list!=null&&list.size()>0){
			String sumAmt=ClearMerClearBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tranNumSum=arr[0].toString();
				tranAmtSum=arr[1].toString();
				refNumSum=arr[2].toString();
				refAmtSum=arr[3].toString();
				netAmtSum=arr[4].toString();
				stlAmtSum=arr[5].toString();
				feeSum=arr[6].toString();
			}
		}
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");		
		String scene="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ClearMerClearBook cmcb:list){
				scene=cmcb.getScene();
				if(scene!=null){
					cmcb.setSceneName(consumpCategoryMap.get(cmcb.getScene().toString()));
				}
			}
		}
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—商户汇总表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearMerClearBookReport.xls"; // excel模板
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
			ClearMerClearBook cce = null;
			String stlFlag="";
			String feeType="";
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){
					stlFlag=cce.getStlFlag();
					if("0".equals(stlFlag)){
						stlFlag="初登记";
					}else if("1".equals(stlFlag)){
						stlFlag="无需结算";
					}else if("2".equals(stlFlag)){
						stlFlag="已结算";
					}

					if("0".equals(cce.getFeeType())){
						feeType="否";
					}else if("1".equals(cce.getFeeType())){
						feeType="是";
					}
					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));

					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getFmrchNo()==null||"".equals(cce.getFmrchNo())?"":cce.getFmrchNo(),style);
					POIUtils.createCell(row, (short) 4,cce.getFmrchName()==null||"".equals(cce.getFmrchName())?"":cce.getFmrchName(),style);
					POIUtils.createCell(row, (short) 5,cce.getTranDate()==null||"".equals(cce.getTranDate())?"":cce.getTranDate(),style);
					POIUtils.createCell(row, (short) 6,cce.getSceneName()==null||"".equals(cce.getSceneName())?"":cce.getSceneName(),style);
					POIUtils.createCell(row, (short) 7,cce.getCardTypeName()==null||"".equals(cce.getCardTypeName())?"":cce.getCardTypeName(),style);
					POIUtils.createCell(row, (short) 8,cce.getTranNum()==null?"0":cce.getTranNum().toString(),style);
					POIUtils.createCell(row, (short) 9,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					POIUtils.createCell(row, (short) 10,cce.getRefNum()==null?"0":cce.getRefNum().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getRefAmt()==null?"00":cce.getRefAmt().toString(),style);
					POIUtils.createCell(row, (short) 12,cce.getNetAmt()==null?"":cce.getNetAmt().toString(),style);
					POIUtils.createCell(row, (short) 13,cce.getStlAmt()==null?"0":cce.getStlAmt().toString(),style);
					POIUtils.createCell(row, (short) 14,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 15,stlFlag==null||"".equals(stlFlag)?"":stlFlag,style);
					POIUtils.createCell(row, (short) 16,cce.getStlBookId()==null||"".equals(cce.getStlBookId())?"":cce.getStlBookId(),style);
					POIUtils.createCell(row, (short) 17,cce.getStlDate()==null||"".equals(cce.getStlDate())?"":cce.getStlDate(),style);
					POIUtils.createCell(row, (short) 18,feeType,style);
					POIUtils.createCell(row, (short) 19,cce.getComments()==null||"".equals(cce.getComments())?"":cce.getComments(),style);					
				}
			}	
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			String sumStr="合计   消费总笔数："+tranNumSum+"  	 消费总金额："+tranAmtSum+"    退货总笔数："+refNumSum+"    退货总金额："+refAmtSum
			+"    净额："+netAmtSum+"    结算金额："+stlAmtSum+"    交易手续费："+feeSum;
			POIUtils.createCell(row, (short) 4, sumStr,null);
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
			log.error("ClearMerClearBookController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
	
	/**
	 * 返回LIST的字段fieldName从startRow到endRow的合计
	 * @param list
	 * @param startRow
	 * @param endRow
	 * @param fieldName
	 * @return
	 */
	public BigDecimal getMerSumAmt(List<ClearMerClearBook> list,int startRow,int endRow,String fieldName){
		BigDecimal merSumAmt=BigDecimal.ZERO;
		BigDecimal ziduan=BigDecimal.ZERO;
		ClearMerClearBook ccb=null;
		for(;startRow<=endRow;startRow++){
			ccb=list.get(startRow);
			if("fee".equals(fieldName)){
				ziduan=ccb.getFee();
			}else if("tranAmt".equals(fieldName)){
				ziduan=ccb.getTranAmt();
			}
			merSumAmt=merSumAmt.add(ziduan);
		}
		return merSumAmt;
	}
	
	
	/****************************************商户消费排名查询************************************************/
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "pageOfPaiMing" })
	public String pageOfPaiMing(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerClearBook queryModel) {
		queryModel.setStartDt(getLastMonthDay("yyyyMMdd"));
		queryModel.setEndDt(getNowDt("yyyyMMdd",0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerClearBook/clearMerClearBookListOfPaiMing";
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
	@RequestMapping(value = { "getListOfPaiMing" })
	public String getListOfPaiMing(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerClearBook queryModel) {
		// 分页设置
		int count = ClearMerClearBookService.countPaiMingByExample(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerClearBook> list = ClearMerClearBookService.selectPaiMingPageByExample (queryModel, startResult, endResult);
		
		model.addAttribute("ClearMerClearBookListOfPaiMing", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);

		return "modules/fs/clearMerClearBook/clearMerClearBookListOfPaiMing";
	}
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcelOfPaiMing" })
	@ResponseBody
	public String exportExcelOfPaiMing(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerClearBook info) {
		List<ClearMerClearBook> list = ClearMerClearBookService.selectPaiMingByExample(info);		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—商户消费排名表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/paimingReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 2, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始	
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();
			ClearMerClearBook cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
				}
			}	
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
			log.error("ClearMerClearBookController.exportExcelOfPaiMing()调用出现异常。");
		}
		
		return null;
	}
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "pageOfPaiMingTotal" })
	public String pageOfPaiMingTotal(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerClearBook queryModel) {
		queryModel.setStartDt(getLastMonthDay("yyyyMMdd"));
		queryModel.setEndDt(getNowDt("yyyyMMdd",0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerClearBook/clearBookListOfPaiMingTotal";
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
	@RequestMapping(value = { "getListOfPaiMingTotal" })
	public String getListOfPaiMingTotal(HttpServletRequest req, HttpServletResponse resp, 
			Model model, int curPage, int pageSize, ClearMerClearBook queryModel) {
		// 分页设置
		int count = ClearMerClearBookService.countPaiMingTotal(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerClearBook> list = ClearMerClearBookService.selectPaiMingPageTotal (queryModel, startResult, endResult);
		
		model.addAttribute("ClearMerClearBookListOfPaiMing", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);

		return "modules/fs/clearMerClearBook/clearBookListOfPaiMingTotal";
	}
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcelOfPaiMingTotal" })
	@ResponseBody
	public String exportExcelOfPaiMingTotal(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerClearBook info) {
		List<ClearMerClearBook> list = ClearMerClearBookService.selectPaiMingTotal(info);		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName = "财务报表—商户消费汇总表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/paimingReport.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet = work.getSheetAt(0);
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
            POIUtils.createCell(row, (short) 2, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow = 3;//表头往上多少行,开始循环填数据行数，从0开始	
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();
			ClearMerClearBook cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
				}
			}	
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
			log.error("ClearMerClearBookController.exportExcelOfPaiMingTotal()调用出现异常。");
		}
		
		return null;
	}
	
}
