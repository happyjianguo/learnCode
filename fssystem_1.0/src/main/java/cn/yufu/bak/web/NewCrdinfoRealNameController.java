package cn.yufu.bak.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.bak.entity.NewCrdinfo;
import cn.yufu.bak.service.NewCrdinfoService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value="/NewCrdinfoRealName")
public class NewCrdinfoRealNameController {
	
	Log log = Log.getLog(NewCrdinfoRealNameController.class);
	
	@Autowired
	@Qualifier("bak.NewCrdinfoService")
	private NewCrdinfoService newCrdinfoService;
	
	/**
	 * 加载初始页(实名认证信息)
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param queryModel
	 * @return
	 */
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, NewCrdinfo queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		return "modules/cortexs/newCrdinfoRealNameList";
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
	@RequestMapping(value = { "list" })
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, NewCrdinfo queryModel) {
		// 分页设置
		int counts = newCrdinfoService.getCounts(queryModel);
		Page page = new Page(curPage, pageSize, counts);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取实名认证列表数据
		List<NewCrdinfo> list = newCrdinfoService.selectPageList(queryModel, startResult, endResult);
		
		model.addAttribute("newCrdinfoList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		return "modules/cortexs/newCrdinfoRealNameList";
	}
	
	/**
	 * 导出EXCEL
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest request,HttpServletResponse response, 
				int curPage, int pageSize, NewCrdinfo info) {
		//获取实名认证列表数据
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		List<NewCrdinfo> list = newCrdinfoService.getExcelData(info, startResult, endResult);
		int listSize = (list == null ? 0 : list.size());
		
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String headName="新福卡实名认证信息报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path =request.getSession().getServletContext().getRealPath("/")+ "reportTemp/NewCrdinfoRealName.xls"; // excel模板
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

			NewCrdinfo soen = null;	
			for (int i = 0; i < listSize; i++) {
				soen = list.get(i);
				if(soen!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,soen.getPan()==null||"".equals(soen.getPan())?"":soen.getPan(),style);
					POIUtils.createCell(row, (short) 2,soen.getCustName()==null||"".equals(soen.getCustName())?"":soen.getCustName(),style);
					POIUtils.createCell(row, (short) 3,soen.getCellPhone()==null||"".equals(soen.getCellPhone())?"":soen.getCellPhone(),style);
					POIUtils.createCell(row, (short) 4,soen.getIdNum()==null||"".equals(soen.getIdNum())?"":soen.getIdNum(),style);
					POIUtils.createCell(row, (short) 5,soen.getAddress()==null||"".equals(soen.getAddress())?"":soen.getAddress(),style);
					POIUtils.createCell(row, (short) 6,soen.getMailBox()==null||"".equals(soen.getMailBox())?"":soen.getMailBox(),style);
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
			log.error("NewCrdinfoRealNameController.exportExcel()调用出现异常。");
		}
		
		return null;
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
	@RequestMapping(value = { "exportExcelAll" })
	@ResponseBody
	public String exportExcelAll(HttpServletRequest request,HttpServletResponse response, 
			NewCrdinfo info) {
		//导出符合查询条件的全部信息
		List<NewCrdinfo> list = newCrdinfoService.getExcelData(info, 0, 2099999999);
		if (list == null || list.size() == 0) list = new ArrayList<>();
		try{
			String headName="新福卡实名认证信息报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = request.getSession().getServletContext().getRealPath("/")+ "reportTemp/NewCrdinfoRealName.xls"; // excel模板
			InputStream inputStream = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(inputStream);
			
			//设置表头和制表时间
			HSSFCellStyle style = work.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            
            String[] title = {"序号","福卡卡号","姓名","手机号","身份证号","地址","邮箱"};
            
            moreSheetExcelReport(work, headName, "新福卡", style, list, 50000, 
            		title, request, response, inputStream);
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
			HSSFCellStyle style,List<NewCrdinfo> list, int count,String[] title,
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
					NewCrdinfo model = null;
					//分页数据
					List<NewCrdinfo> subList = list.subList(startResult, endResult);
					
					for (int j = 0; j < subList.size(); j++) {
						model = subList.get(j);
						if(model != null){
							row = sheet.createRow(startRow + j);
							row.setHeight((short)(25 * 20));
							++startResult;
							getPOIRowData(style, startResult, row, model, title.length);
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
					NewCrdinfo model = null;
					//分页数据
					List<NewCrdinfo> subList = list.subList(startResult, endResult);
					for (int j = 0; j < subList.size(); j++) {
						model = subList.get(j);
						if(model != null){
							row = sheet.createRow(startRow + j);
							row.setHeight((short)(25 * 20));
							++startResult;
							getPOIRowData(style, startResult, row, model, title.length);
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
						NewCrdinfo model = null;
						//分页数据
						List<NewCrdinfo> subList = list.subList(startResult, endResult);
						for (int j = 0; j < subList.size(); j++) {
							model = subList.get(j);
							if(model != null){
								row = sheet.createRow(startRow + j);
								row.setHeight((short)(25 * 20));
								++startResult;
								getPOIRowData(style, startResult, row, model, title.length);
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
			NewCrdinfo model, int titleTotal) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) (--titleTotal),model.getMailBox()==null||"".equals(model.getMailBox())?"":model.getMailBox(),style);
		POIUtils.createCell(row, (short) (--titleTotal),model.getAddress()==null||"".equals(model.getAddress())?"":model.getAddress(),style);
		POIUtils.createCell(row, (short) (--titleTotal),model.getIdNum()==null||"".equals(model.getIdNum())?"":model.getIdNum(),style);
		POIUtils.createCell(row, (short) (--titleTotal),model.getCellPhone()==null||"".equals(model.getCellPhone())?"":model.getCellPhone(),style);
		POIUtils.createCell(row, (short) (--titleTotal),model.getCustName()==null||"".equals(model.getCustName())?"":model.getCustName(),style);
		POIUtils.createCell(row, (short) (--titleTotal),model.getPan()==null||"".equals(model.getPan())?"":model.getPan(),style);
		POIUtils.createCell(row, (short) (--titleTotal),(startResult)+"",style);
		
	}
}
