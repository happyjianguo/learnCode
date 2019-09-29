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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.SuspiciousOrderEarlyWarning;
import cn.yufu.fs.service.SuspiciousOrderEarlyWarningService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value="/SuspiciousOrderEarlyWarning")
public class SuspiciousOrderEarlyWarningController {
	
	Log log = Log.getLog(SuspiciousOrderEarlyWarningController.class);
	
	@Autowired
	@Qualifier("fs.SuspiciousOrderEarlyWarningService")
	private SuspiciousOrderEarlyWarningService suspiciousOrderEarlyWarningService;
	
	/**
	 * 加载初始页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param queryModel
	 * @return
	 */
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response, 
			Model model, SuspiciousOrderEarlyWarning queryModel) {
		queryModel.setEndDate(getNowDt("yyyyMMdd",-1));		
		queryModel.setBeginDate(getLastMonthDay("yyyyMMdd"));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/question/suspiciousOrderEarlyWarningList";
	}
	
	/**
	 * 分页查询明细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, SuspiciousOrderEarlyWarning queryModel) {
		// 分页设置
		int count = suspiciousOrderEarlyWarningService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<SuspiciousOrderEarlyWarning> list = suspiciousOrderEarlyWarningService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("suspiciousOrderEarlyWarningList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		
		return "modules/fs/question/suspiciousOrderEarlyWarningList";
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
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest request,HttpServletResponse response, 
			Model model, SuspiciousOrderEarlyWarning info) {
		List<SuspiciousOrderEarlyWarning> list = suspiciousOrderEarlyWarningService.queryList(info);
		int listSize = (list == null ? 0 : list.size());
		
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String headName="预警订单报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path =request.getSession().getServletContext().getRealPath("/")+ "reportTemp/SuspiciousOrderEarlyWarningReport.xls"; // excel模板
			InputStream inputStream = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(inputStream);
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
            POIUtils.createCell(row, (short) 4, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow = 3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			SuspiciousOrderEarlyWarning soen = null;	
			for (int i = 0; i < listSize; i++) {
				soen = list.get(i);
				if(soen!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,soen.getOrdercode()==null||"".equals(soen.getOrdercode())?"":soen.getOrdercode(),style);
					POIUtils.createCell(row, (short) 2,soen.getPurchaseAmt()==null||"".equals(soen.getPurchaseAmt())?"0":soen.getPurchaseAmt().toString(),style);
					POIUtils.createCell(row, (short) 3,soen.getPurchaseDate()==null||"".equals(soen.getPurchaseDate())?"":soen.getPurchaseDate(),style);
					POIUtils.createCell(row, (short) 4,soen.getSuspiciousDate()==null||"".equals(soen.getSuspiciousDate())?"":soen.getSuspiciousDate(),style);
					POIUtils.createCell(row, (short) 5,soen.getProvisions()==null||"".equals(soen.getProvisions())?"0":soen.getProvisions().toString(),style);
					//POIUtils.createCell(row, (short) 6,soen.getProvisionsRate()==null||"".equals(soen.getProvisionsRate())?"0":soen.getProvisionsRate().toString(),style);
				}
			}
							
			/**************************** 输出流 *****************************************/
			OutputStream os = response.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(request, headName);
			response.setHeader("Content-disposition","attachment;filename=" + filename + ".xls");
			work.write(os);
			if(inputStream!=null){
				inputStream.close();
			}
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("SuspiciousOrderEarlyWarningController.exportExcel()调用出现异常。");
		}
		
		return null;
	}

}
