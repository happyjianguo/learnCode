package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.ClearUnMerStlBook;
import cn.yufu.fs.service.ClearUnMerStlBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/ClearUnMerStlBook")

public class ClearUnMerStlBookController {
	Log log = Log.getLog(ClearUnMerStlBookController.class);
	
	@Autowired
	@Qualifier("fs.ClearUnMerStlBookService")	
	private ClearUnMerStlBookService ClearUnMerStlBookService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearUnMerStlBook queryModel) {
		queryModel.setEndunstlDate(getNowDt("yyyyMMdd",-1));			
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearUnMerStlBook/clearUnMerStlBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearUnMerStlBook queryModel) {
		// 分页设置
		int count = ClearUnMerStlBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearUnMerStlBook> list = ClearUnMerStlBookService.queryList(queryModel, startResult, endResult);		
		
		model.addAttribute("ClearUnMerStlBookList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		
		//合计
		String tran_num="0";
		String tran_amt="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=ClearUnMerStlBookService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				tran_num=arr[1].toString();
			}
		}
		model.addAttribute("tran_num", tran_num);
		model.addAttribute("tran_amt", tran_amt);
		return "modules/fs/clearUnMerStlBook/clearUnMerStlBookList";
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
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearUnMerStlBookId) {
		ClearUnMerStlBook info = ClearUnMerStlBookService.queryInfo(ClearUnMerStlBookId);
		model.addAttribute("info", info);		
		return "modules/fs/clearUnMerStlBook/clearUnMerStlBookShow";
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
		calendar.add(Calendar.DATE, flag);
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearUnMerStlBook info) {
		List<ClearUnMerStlBook> list = ClearUnMerStlBookService.queryList(info);
		int listSize=list==null?0:list.size();
		//合计
		String tran_amt="0";
		String tran_num="0";	
		if(list!=null&&listSize>0){
			String sumAmt=ClearUnMerStlBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				tran_num=arr[1].toString();
			}
		}

		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—未结算报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearUnMerStlBookReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 3, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			ClearUnMerStlBook cce = null;	
			String startunstlDate="";
			String endunstlDate="";
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){	
					startunstlDate=cce.getStartunstlDate()==null||"".equals(cce.getStartunstlDate())?"":cce.getStartunstlDate();
					endunstlDate=cce.getEndunstlDate()==null||"".equals(cce.getEndunstlDate())?"":cce.getEndunstlDate();
					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					
					POIUtils.createCell(row, (short) 3,"0".equals(cce.getStlNeedFlag())?"不需要":"需要",style);
					POIUtils.createCell(row, (short) 4,cce.getLaststlDate()==null||"".equals(cce.getLaststlDate())?"":cce.getLaststlDate(),style);
					POIUtils.createCell(row, (short) 5,startunstlDate+"-"+endunstlDate,style);
					POIUtils.createCell(row, (short) 6,cce.getTranNum()==null?"0":cce.getTranNum().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
				}
			}
			//设置合计交易金额、手续费金额和条数
//			row = sheet.createRow(list.size()+startRow);
//			row.setHeight((short)(27 * 20));
//			POIUtils.createCell(row, (short) 2, "合计       消费笔数："+tran_num+"   消费金额："+tran_amt,null);	
			unMerSum(sheet, style, startRow, "全部",  tran_num, tran_amt, list.size());						
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename="+filename+".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearUnMerStlBookController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
		
	private void unMerSum(HSSFSheet sheet, HSSFCellStyle style, int startRow, String cityName,
			String netAmtTotal, String tranAmtTotal, int i) {
		HSSFRow row;
		//创建合计行 
		
		row = sheet.createRow(startRow+i);
		row.setHeight((short)(27 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(startRow+i, startRow+i, (short) 0,  (short) 5));
		POIUtils.createCell(row, (short) 0,cityName+"合计",style);
		POIUtils.createCell(row, (short) 1,null,style);
		POIUtils.createCell(row, (short) 2,null,style);
		POIUtils.createCell(row, (short) 3,null,style);
		POIUtils.createCell(row, (short) 4,null,style);
		POIUtils.createCell(row, (short) 5,null,style);
		POIUtils.createCell(row, (short) 6,netAmtTotal.toString(),style);
		POIUtils.createCell(row, (short) 7,tranAmtTotal.toString(),style);
		
	}
}
