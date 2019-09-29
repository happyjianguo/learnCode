package cn.yufu.system.modules.cortexs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CrdfeelogBefore;
import cn.yufu.system.modules.cortexs.service.CrdfeelogBeforeService;

/**
 * 新福卡扣款明细预收Controller
 * @author ZQK
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "/cortexs/crdfeelogBefore")
public class CrdfeelogBeforeController extends BaseController {
	
	@Autowired
	private CrdfeelogBeforeService crdfeelogBeforeService;
	
	@ModelAttribute
	public CrdfeelogBefore get(@RequestParam(required=false) String crdFeeLogId) {
		CrdfeelogBefore entity = null;
		if (StringUtils.isNotBlank(crdFeeLogId)){
			entity = crdfeelogBeforeService.get(crdFeeLogId);
		}
		if (entity == null){
			entity = new CrdfeelogBefore();
		}else {
			entity.setId(crdFeeLogId); //更新
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdfeelogBefore crdfeelog, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String year = crdfeelog.getYear();
		String month = crdfeelog.getMonth();
		int flag = 0;
		if (StringUtils.isNotBlank(year)) {
			if (StringUtils.isNotBlank(month)) {
				crdfeelog.setBegainLocalDate(year+month+"00");
				crdfeelog.setEndLocalDate(year+month+"31");
			}else{
				crdfeelog.setBegainLocalDate(year+"0000");
				crdfeelog.setEndLocalDate(year+"1231");
			}
			flag = 1;
		}
		BigDecimal feeSum = crdfeelogBeforeService.getFeeSum(crdfeelog);
		if (feeSum == null) {
			feeSum = new BigDecimal(0);
		}
		Page<CrdfeelogBefore> page = crdfeelogBeforeService.findPage(new Page<CrdfeelogBefore>(request, response), crdfeelog); 
		List<CrdfeelogBefore> list = page.getList();
		double result = 0;
		DecimalFormat dFormat = new DecimalFormat("#.00");
		for (CrdfeelogBefore crd : list) {
			//匹配未有交易的年数
			if (StringUtils.isNotBlank(crd.getNoTranYear())) {
				result = Double.parseDouble(crd.getNoTranYear())/12;
				String format = dFormat.format(result);
				if (format.charAt(0) == '.') {
					format = "0" + format;
				}
				crd.setNoTranYear(format);
			}
			//展示到月份
			if (StringUtils.isNotBlank(crd.getLocalDate())) {
				crd.setLocalDate(crd.getLocalDate().substring(0, 4)+"-"+crd.getLocalDate().substring(4, 6));
			}
		}
		
		if (flag == 1) {
			crdfeelog.setYear(year);
			crdfeelog.setMonth(month);
		}
		model.addAttribute("feeSum", feeSum);
		model.addAttribute("count", page.getCount());
		model.addAttribute("page", page);
		model.addAttribute("crdfeelog", crdfeelog);
		return "modules/cortexs/crdfeelogBeforeList";
	}
	
	@RequestMapping(value = "show")
	public String show(CrdfeelogBefore crdfeelog, Model model) {
		String stat = "";
		double result = 0;
		DecimalFormat dFormat = new DecimalFormat("#.00");
		//匹配未有交易的年数
		if (StringUtils.isNotBlank(crdfeelog.getNoTranYear())) {
			result = Double.parseDouble(crdfeelog.getNoTranYear())/12;
			String format = dFormat.format(result);
			if (format.charAt(0) == '.') {
				format = "0" + format;
			}
			crdfeelog.setNoTranYear(format);
		}
		//展示到月份
		if (StringUtils.isNotBlank(crdfeelog.getLocalDate())) {
			crdfeelog.setLocalDate(crdfeelog.getLocalDate().substring(0, 4)+"-"+crdfeelog.getLocalDate().substring(4, 6));
		}
		//匹配状态
		if (crdfeelog.getTxnstatus() == 7) {
			stat = "7";
		}else if (StringUtils.isNotEmpty(crdfeelog.getTxnstatus()+"")) {
			stat = "0";
		}
		crdfeelog.setReserved4(stat);
		model.addAttribute("crdfeelog", crdfeelog);
		return "modules/cortexs/crdfeelogBeforeShow";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, CrdfeelogBefore info) {
		String year = info.getYear();
		String month = info.getMonth();
		if (StringUtils.isNotBlank(year)) {
			if (StringUtils.isNotBlank(month)) {
				info.setBegainLocalDate(year+month+"00");
				info.setEndLocalDate(year+month+"31");
			}else{
				info.setBegainLocalDate(year+"0000");
				info.setEndLocalDate(year+"1231");
			}
		}
		//导出符合查询条件的全部信息
		List<CrdfeelogBefore> list = crdfeelogBeforeService.findList(info);
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		try{
			String headName="新福卡扣款明细 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/newCrdFeeLog.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			
			//设置表头和制表时间
			HSSFCellStyle style = work.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            
            String[] title = {"序号","卡号","扣款月份","扣款商户号","扣款终端号","扣款余额(元)","收取金额(元)",
            		"费率(%)","交易日期","交易时间","交易类型","交易状态","收取规则ID","流水ID","未有交易年数"};
            
            moreSheetExcelReport(work, headName, "新福卡扣款明细", style, list, 50000, 
            		title, req, resp, in);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 导出EXCEL
	 * @param 数据集合:list
	 * @param 单个sheet展示的最大数据数:count
	 * @param 样板样式：style
	 * @param HSSFWorkbook work
	 * @param 标题数组 title
	 * @return
	 */
	private void moreSheetExcelReport(HSSFWorkbook work,String headName, String sheetName,
			HSSFCellStyle style,List<CrdfeelogBefore> list, int count,String[] title,
			HttpServletRequest req,HttpServletResponse resp,InputStream in) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			HSSFRow rowTitle = null;
			HSSFCellStyle styleBak = null;
			Map<Integer, HSSFCellStyle> map = new HashMap<>();
			//总页数
			int num = (int)list.size()/count;
			//是否除尽
			int remainder = (int)list.size() % count;
			int startResult = 0;
			int endResult = 0;
			
			if (remainder != 0) {
				//多页
				for(int i = 0 ; i <= num; i++){
					startResult = i * count;
					endResult = (i + 1) * count;
					if (endResult >= list.size()) {
						endResult = list.size();
					}
					// 得到excel的第1个sheet
					HSSFSheet sheet = null;
					try {
						sheet = work.getSheetAt(i);
					} catch (Exception e) {
						// 生成一个表格  
						sheet = work.createSheet();  
						work.setSheetName(i, sheetName+(i+1));   
					}
					//合并单元格
		            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, title.length-1));// 开始行，结束行，开始列，结束列  
					//设置表头和制表时间
					HSSFRow row = sheet.createRow((short) 0);
		            POIUtils.createCell(row, (short) 0, headName, style);
					row.setHeight((short)(27 * 20));
					
					int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
					//获取样式
					rowTitle = sheet.getRow(2);
					//存数据的开始行
					row = sheet.getRow(startRow);
					try {
						//获取列标题格式
						for(int m = 0; m < title.length; m++){
							 map.put(m, rowTitle.getCell(m).getCellStyle());
						}
						//存数据的格式
						style = row.getCell(2).getCellStyle();
						styleBak = style;
					} catch (Exception e) {
						if (rowTitle == null) {
							rowTitle = sheet.createRow(2);
							rowTitle.setHeight((short)(30 * 20));
						}
						for (int k=0; k < title.length; k++){
							if (k == 0) {
								sheet.setColumnWidth(k, 5 * 256); 
								POIUtils.createCell(rowTitle, (short) 0,title[k],map.get(k));
							}else{
								sheet.setColumnWidth(k, 20 * 256); 
								POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
							}
						} 
					}
					if (row == null) {
						style = styleBak;
					}
					CrdfeelogBefore cce = null;
					//分页数据
					List<CrdfeelogBefore> subList = list.subList(startResult, endResult);
					for (int j = 0; j < subList.size(); j++) {
						cce = subList.get(j);
						if(cce!=null){
							row = sheet.createRow(startRow + j);
							row.setHeight((short)(25 * 20));
							++startResult;
							getPOIRowData(style, startResult, row, cce,title.length);
						}
					}
				}
			}else {
				//一页数据
				if (num == 0) {
					// 得到excel的第1个sheet
					HSSFSheet sheet = null;
					try {
						sheet = work.getSheetAt(0);
					} catch (Exception e) {
						// 生成一个表格  
						sheet = work.createSheet();  
						work.setSheetName(1, sheetName+(1));   
					}
					//合并单元格
		            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, title.length-1));// 开始行，结束行，开始列，结束列  
					//设置表头和制表时间
					HSSFRow row = sheet.createRow((short) 0);
		            POIUtils.createCell(row, (short) 0, headName, style);
					row.setHeight((short)(27 * 20));
					
					int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
					//获取样式
					rowTitle = sheet.getRow(2);
					//存数据的开始行
					row = sheet.getRow(startRow);
					try {
						//获取列标题格式
						for(int m = 0; m < title.length; m++){
							 map.put(m, rowTitle.getCell(m).getCellStyle());
						}
						//存数据的格式
						style = row.getCell(2).getCellStyle();
						styleBak = style;
					} catch (Exception e) {
						if (rowTitle == null) {
							rowTitle = sheet.createRow(2);
							rowTitle.setHeight((short)(30 * 20));
						}
						for (int k=0; k < title.length; k++){
							if (k == 0) {
								sheet.setColumnWidth(k, 5 * 256); 
								POIUtils.createCell(rowTitle, (short) 0,title[k],map.get(k));
							}else{
								sheet.setColumnWidth(k, 20 * 256); 
								POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
							}
						} 
					}
					if (row == null) {
						style = styleBak;
					}
					CrdfeelogBefore cce = null;
					//分页数据
					List<CrdfeelogBefore> subList = list.subList(startResult, endResult);
					for (int j = 0; j < subList.size(); j++) {
						cce = subList.get(j);
						if(cce!=null){
							row = sheet.createRow(startRow + j);
							row.setHeight((short)(25 * 20));
							++startResult;
							getPOIRowData(style, startResult, row, cce,title.length);
						}
					}
				}else{
					//多页数据
					for(int i = 0 ; i < num; i++){
						startResult = i * count;
						endResult = (i + 1) * count;
						if (endResult >= list.size()) {
							endResult = list.size();
						}
						// 得到excel的第1个sheet
						HSSFSheet sheet = null;
						try {
							sheet = work.getSheetAt(i);
						} catch (Exception e) {
							// 生成一个表格  
							sheet = work.createSheet();  
							work.setSheetName(i, sheetName+(i+1));   
						}
						//合并单元格
			            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, title.length-1));// 开始行，结束行，开始列，结束列  
						//设置表头和制表时间
						HSSFRow row = sheet.createRow((short) 0);
			            POIUtils.createCell(row, (short) 0, headName, style);
						row.setHeight((short)(27 * 20));
						
						int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
						//获取样式
						rowTitle = sheet.getRow(2);
						//存数据的开始行
						row = sheet.getRow(startRow);
						try {
							//获取列标题格式
							for(int m = 0; m < title.length; m++){
								 map.put(m, rowTitle.getCell(m).getCellStyle());
							}
							//存数据的格式
							style = row.getCell(2).getCellStyle();
							styleBak = style;
						} catch (Exception e) {
							if (rowTitle == null) {
								rowTitle = sheet.createRow(2);
								rowTitle.setHeight((short)(30 * 20));
							}
							for (int k=0; k < title.length; k++){
								if (k == 0) {
									sheet.setColumnWidth(k, 5 * 256); 
									POIUtils.createCell(rowTitle, (short) 0,title[k],map.get(k));
								}else{
									sheet.setColumnWidth(k, 20 * 256); 
									POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
								}
							} 
						}
						if (row == null) {
							style = styleBak;
						}
						CrdfeelogBefore cce = null;
						//分页数据
						List<CrdfeelogBefore> subList = list.subList(startResult, endResult);
						for (int j = 0; j < subList.size(); j++) {
							cce = subList.get(j);
							if(cce!=null){
								row = sheet.createRow(startRow + j);
								row.setHeight((short)(25 * 20));
								++startResult;
								getPOIRowData(style, startResult, row, cce,title.length);
							}
						}
					}
				}
			}
			
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName).trim();
			resp.setHeader("Content-disposition","attachment;filename="+filename+".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//封装数据
	private void getPOIRowData(HSSFCellStyle style, int startResult, HSSFRow row,
			CrdfeelogBefore cce, int titleTotal) {
		double result = 0;
		DecimalFormat dFormat = new DecimalFormat("#.00");
		String format = "";
		//匹配未有交易的年数
		if (StringUtils.isNotBlank(cce.getNoTranYear())) {
			result = Double.parseDouble(cce.getNoTranYear())/12;
			format = dFormat.format(result);
			if (format.charAt(0) == '.') {
				format = "0" + format;
			}
		}
		//展示到月份
		if (StringUtils.isNotBlank(cce.getLocalDate())) {
			cce.setLocalDate(cce.getLocalDate().substring(0, 4)+"-"+cce.getLocalDate().substring(4, 6));
		}
		//匹配状态
		String stat = "";
		if (cce.getTxnstatus() == 7) {
			stat = "成功";
		}else if (StringUtils.isNotEmpty(cce.getTxnstatus() + "")) {
			stat = "失败";
		}
		//匹配交易类型
		String tranType = "";
		if (0 == cce.getTxncode()) {
			tranType = "消费";
		} else if (20 == cce.getTxncode()) {
			tranType = "退款";
		}
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) (--titleTotal),format,style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTlogId()==null?"0":cce.getTlogId().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getFeeRule()==null?"0":cce.getFeeRule().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),stat,style);
		POIUtils.createCell(row, (short) (--titleTotal),tranType,style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTranTime()==null||"".equals(cce.getTranTime())?"":cce.getTranTime(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTranDate()==null||"".equals(cce.getTranDate())?"":cce.getTranDate(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getRate()==null||"".equals(cce.getRate())?"":cce.getRate(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getFee()==null?"0":cce.getFee().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getAvlbal()==null?"0":cce.getAvlbal().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTermcode()==null||"".equals(cce.getTermcode())?"":cce.getTermcode(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getMerchantno()==null||"".equals(cce.getMerchantno())?"":cce.getMerchantno(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getLocalDate()==null||"".equals(cce.getLocalDate())?"":cce.getLocalDate(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getPan(),style);
		POIUtils.createCell(row, (short) (--titleTotal),(startResult)+"",style);
	}
	
}