package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import cn.yufu.fs.entity.OldCrdfeelog;
import cn.yufu.fs.service.OldCrdfeelogService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.sqlserver.entity.ReMemberCardSale;
import cn.yufu.system.modules.sqlserver.service.ReMemberCardSaleService;

/**
 * 老福卡扣款明细Controller
 * @author ZQK
 * @version 2017-07-31
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/OldCrdfeelog")
public class OldCrdfeelogController {
	Log log = Log.getLog(OldCrdfeelogController.class);
	
	@Autowired
	@Qualifier("fs.OldCrdfeelogService")
	private OldCrdfeelogService crdfeelogService;
	
	@Autowired
	private ReMemberCardSaleService reMemberCardSaleService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, 
			OldCrdfeelog queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/crdfeeManager/crdfeelogList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest req, HttpServletResponse resp, Model model, 
			int curPage, int pageSize, OldCrdfeelog queryModel) {
		try{
			String year = queryModel.getYear();
			String month = queryModel.getMonth();
			int flag = 0;
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					queryModel.setBegainLocalDate(year+month+"00");
					queryModel.setEndLocalDate(year+month+"31");
				}else{
					queryModel.setBegainLocalDate(year+"0000");
					queryModel.setEndLocalDate(year+"1231");
				}
				flag = 1;
			}
			BigDecimal feeSum = crdfeelogService.getFeeSum(queryModel);
			if (feeSum == null) {
				feeSum = new BigDecimal(0);
			}
			// 分页设置
			int count = crdfeelogService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<OldCrdfeelog> list = crdfeelogService.queryList(queryModel, startResult, endResult);
			if (list == null || list.size() == 0) {
				list = new ArrayList<>();
			}
			for (OldCrdfeelog oldCrdfeelog : list) {
				//展示到月份
				if (StringUtils.isNotBlank(oldCrdfeelog.getLoacleDate())) {
					oldCrdfeelog.setLoacleDate(oldCrdfeelog.getLoacleDate().substring(0, 4)+"-"+oldCrdfeelog.getLoacleDate().substring(4, 6));
				}
			}
			if (flag == 1) {
				queryModel.setYear(year);
				queryModel.setMonth(month);
			}
			model.addAttribute("count", count);
			model.addAttribute("feeSum", feeSum);
			model.addAttribute("crdfeelogList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("queryModel", queryModel);
	
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("OldCrdfeelogController.list()调用出现异常。");
			e.printStackTrace();
		}
		return "modules/fs/crdfeeManager/crdfeelogList";
	}
	
	/**
	 * 跳转退款页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toRefund" })
	public String toRefund(HttpServletRequest req, HttpServletResponse resp, 
			Model model, OldCrdfeelog queryModel) {
		List<OldCrdfeelog> list = null;
		if (StringUtils.isNotEmpty(queryModel.getRefundIDs())) {
			List<String> ids = new ArrayList<String>();
			String refundIDs = queryModel.getRefundIDs();
			String[] split = refundIDs.split("\\|");
			for (String string : split) {
				ids.add(string);
			}
			queryModel.setIds(ids);
			model.addAttribute("refundIDs", refundIDs);
			list = crdfeelogService.selectByPrimaryKey(queryModel);
		}
		model.addAttribute("crdfeelogList", list);		
		return "modules/fs/crdfeeManager/crdfeelogForm";
	}
	
	/**
	 * 退款
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"refund"})
	@ResponseBody
	public String refund(HttpServletRequest req, HttpServletResponse resp, 
			Model model, OldCrdfeelog info) {
		Map<String, Object> map = new HashMap<String, Object>();
		info.setComments(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		info.setComments1("1");
		List<String> ids = new ArrayList<String>();
		String refundIDs = info.getRefundIDs();
		String[] split = refundIDs.split("\\|");
		for (String string : split) {
			ids.add(string);
		}
		info.setIds(ids);
		List<OldCrdfeelog> list = crdfeelogService.selectByPrimaryKey(info);
		if (null == list || list.size() == 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "退款数据不可为空-FK。");
			return JsonUtil.getJsonString(map);
		}
		
		try {
			ReMemberCardSale reMemberCardSale = null;
			for (OldCrdfeelog oldCrdfeelog : list) {
				reMemberCardSale = new ReMemberCardSale();
				reMemberCardSale.setIsNewRecord(true);
				copyData(reMemberCardSale, oldCrdfeelog);
				reMemberCardSaleService.save(reMemberCardSale);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap<String, Object>();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "退款失败-远程服务异常。");
		}
		try {
			map = crdfeelogService.refund(info);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "退款失败-FK。");
			return JsonUtil.getJsonString(map);
		}
		return JsonUtil.getJsonString(map);
	}
	
	//数据copy
	private void copyData(ReMemberCardSale reMemberCardSale, OldCrdfeelog oldCrdfeelog) {
		reMemberCardSale.setOracleID(oldCrdfeelog.getId());
		reMemberCardSale.setLRUID(oldCrdfeelog.getMerchantnumber());
		reMemberCardSale.setsSerialNum(oldCrdfeelog.getTerminalnumber());
		reMemberCardSale.setaCardnum(oldCrdfeelog.getPan());
		reMemberCardSale.setaSaleInegral(oldCrdfeelog.getFee());
		reMemberCardSale.setOldaRegDate(oldCrdfeelog.getTransactiondate());
		reMemberCardSale.setOldaRegTime(oldCrdfeelog.getTransactiontime());
		reMemberCardSale.setlOrdNum(oldCrdfeelog.getLotno());	//批次
		reMemberCardSale.setlInvNum(oldCrdfeelog.getSerialnumber());	//流水
		reMemberCardSale.setRefOldid(new BigDecimal(oldCrdfeelog.getReferencenumber()));
		reMemberCardSale.setBstate(0);
		Date date = new Date();
		String da = DateUtils.formatDate(date, "yyyyMMdd");
		String ti = DateUtils.formatDate(date, "HHmmss");
		reMemberCardSale.setaRegDate(da);
		reMemberCardSale.setaRegTime(ti);
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, OldCrdfeelog info) {
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
		List<OldCrdfeelog> list = crdfeelogService.queryList(info);
		try{
			String headName="老福卡扣款明细 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/oldCrdFeeLog.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
		
			HSSFCellStyle style = work.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            
            String[] title = {"序号","卡号","扣款月份","扣款商户号","扣款终端号","扣款余额(元)","收取金额(元)",
            		"交易日期","交易时间","流水ID"};
            
            moreSheetExcelReport(work, headName, "老福卡扣款明细", style, list, 50000, 
            		title, req, resp, in);
            
		}catch (Exception e) {
			e.printStackTrace();
			log.error("OldCrdfeelogController.exportExcel()调用出现异常。");
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
			HSSFCellStyle style,List<OldCrdfeelog> list, int count,String[] title,
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
								//单元格宽度
								sheet.setColumnWidth(k, 20 * 256); 
								POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
							}
						} 
					}
					if (row == null) {
						style = styleBak;
					}
					//分页数据
					List<OldCrdfeelog> subList = list.subList(startResult, endResult);
					OldCrdfeelog cce = null;
					for (int j = 0; j <subList.size(); j++) {
						cce = subList.get(j);
						if(cce!=null){
							row = sheet.createRow(startRow+j);
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
								//单元格宽度
								sheet.setColumnWidth(k, 20 * 256); 
								POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
							}
						} 
					}
					if (row == null) {
						style = styleBak;
					}
					//分页数据
					List<OldCrdfeelog> subList = list.subList(startResult, endResult);
					OldCrdfeelog cce = null;
					for (int j = 0; j <subList.size(); j++) {
						cce = subList.get(j);
						if(cce!=null){
							row = sheet.createRow(startRow+j);
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
									//单元格宽度
									sheet.setColumnWidth(k, 20 * 256); 
									POIUtils.createCell(rowTitle, (short) k,title[k],map.get(k));
								}
							} 
						}
						if (row == null) {
							style = styleBak;
						}
						//分页数据
						List<OldCrdfeelog> subList = list.subList(startResult, endResult);
						OldCrdfeelog cce = null;
						for (int j = 0; j <subList.size(); j++) {
							cce = subList.get(j);
							if(cce!=null){
								row = sheet.createRow(startRow+j);
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
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
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
			OldCrdfeelog cce, int titleTotal) {
		//展示到月份
		if (StringUtils.isNotBlank(cce.getLoacleDate())) {
			cce.setLoacleDate(cce.getLoacleDate().substring(0, 4)+"-"+cce.getLoacleDate().substring(4, 6));
		}
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTlogid()==null?"0":cce.getTlogid().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTransactiontime()==null||"".equals(cce.getTransactiontime())?"":cce.getTransactiontime(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTransactiondate()==null||"".equals(cce.getTransactiondate())?"":cce.getTransactiondate(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getFee()==null?"0":cce.getFee().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getAvlbal()==null?"0":cce.getAvlbal().toString(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getTerminalnumber()==null||"".equals(cce.getTerminalnumber())?"":cce.getTerminalnumber(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getMerchantnumber()==null||"".equals(cce.getMerchantnumber())?"":cce.getMerchantnumber(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getLoacleDate()==null||"".equals(cce.getLoacleDate())?"":cce.getLoacleDate(),style);
		POIUtils.createCell(row, (short) (--titleTotal),cce.getPan(),style);
		POIUtils.createCell(row, (short) (--titleTotal),(startResult)+"",style);
	}
	
}