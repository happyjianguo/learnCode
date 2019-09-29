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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.service.ClearMerStlBookService;
import cn.yufu.fs.service.ViewStlBookDetailService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;
import cn.yufu.system.modules.sys.utils.TranUtils;



@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/ClearMerStlBook")
public class ClearMerStlBookController {
	Log log = Log.getLog(ClearMerStlBookController.class);
	
	@Autowired
	@Qualifier("fs.ClearMerStlBookService")	
	private ClearMerStlBookService ClearMerStlBookService;
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService CortexService;

	@Autowired
	@Qualifier("fs.ViewStlBookDetailService")	
	private ViewStlBookDetailService ViewStlBookDetailService;	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @returnClearMerStlBook
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerStlBook queryModel) {
		queryModel.setStlDate(getNowDt("yyyyMMdd",-1));	
		//设置消费场景码表
		CortexSysParameter sysParamter=new CortexSysParameter();
		sysParamter.setParamType("CONSUMP_CATEGORY");		
		List<CortexSysParameter>  sysParamList=this.CortexService.getCortexSysParameterList(sysParamter);
		model.addAttribute("consumpCategoryList",sysParamList);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerStlBook/clearMerStlBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerStlBook queryModel) {
		// 分页设置flagOnline
		int count = ClearMerStlBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerStlBook> list = ClearMerStlBookService.queryList(queryModel, startResult, endResult);
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");		
		String scene="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ClearMerStlBook cmcb:list){
				scene=cmcb.getScene();
				if(scene!=null){
					cmcb.setSceneName(consumpCategoryMap.get(cmcb.getScene().toString()));
				}
			}
		}
		//设置消费场景码表
		CortexSysParameter sysParamter=new CortexSysParameter();
		sysParamter.setParamType("CONSUMP_CATEGORY");		
		List<CortexSysParameter>  sysParamList=this.CortexService.getCortexSysParameterList(sysParamter);
		model.addAttribute("consumpCategoryList",sysParamList);
		
		model.addAttribute("ClearMerStlBookList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";

		
		if(list!=null&&list.size()>0){
			String sumAmt=ClearMerStlBookService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				consum_num=arr[0].toString();
				consum_amt=arr[1].toString();
				tran_amt=arr[2].toString();
				net_amt=arr[3].toString();
				fee=arr[4].toString();
			}
		}
		model.addAttribute("consum_num", consum_num);
		model.addAttribute("consum_amt", consum_amt);
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("net_amt", net_amt);
		model.addAttribute("fee", fee);
		return "modules/fs/clearMerStlBook/clearMerStlBookList";
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
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearMerStlBookId) {
		ClearMerStlBook info = ClearMerStlBookService.queryInfo(ClearMerStlBookId);
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");	
		if(info.getScene()!=null&&!"".equals(info.getScene())){	
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				info.setSceneName(consumpCategoryMap.get(info.getScene().toString()));
			}
		}	
		model.addAttribute("info", info);
		
		return "modules/fs/clearMerStlBook/clearMerStlBookShow";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerStlBook info) {
		List<ClearMerStlBook> list = ClearMerStlBookService.queryExcelList(info);
		int listSize=list==null?0:list.size();
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";		
		if(list!=null&&listSize>0){
			String sumAmt=ClearMerStlBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				consum_num=arr[0].toString();
				consum_amt=arr[1].toString();
				tran_amt=arr[2].toString();
				net_amt=arr[3].toString();
				fee=arr[4].toString();
			}
		}
		//设置消费场景名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("CONSUMP_CATEGORY");		
		String scene="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ClearMerStlBook cmcb:list){
				scene=cmcb.getScene();
				if(scene!=null){
					cmcb.setSceneName(consumpCategoryMap.get(cmcb.getScene().toString()));
				}
			}
		}
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—结算初表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			if("1".equals(info.getFlagOnline())){
				headName="财务报表—线上商户结算初表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}else if("0".equals(info.getFlagOnline())){
				headName="财务报表—线下商户结算初表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearMerStlBookReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 6, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			ClearMerStlBook cce = null;		
			
			String finalStlIdTemp="";
			int sizeOfFinalStlId=0;
			int iBakOfFinalStlId=0;
			
			String merNoTemp="";
			int sizeOfMer=0;
			int iBak=0;
			
			
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 1,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 2,cce.getStartStlDate()+"-"+cce.getEndStlDate(),style);
					POIUtils.createCell(row, (short) 3,cce.getSceneName()==null||"".equals(cce.getSceneName())?"":cce.getSceneName(),style);
					POIUtils.createCell(row, (short) 4,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getFeeOrder()==null?"0":cce.getFeeOrder().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getFee()==null?"00":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getNetAmt()==null?"":cce.getNetAmt().toString(),style);
					POIUtils.createCell(row, (short) 9,"0".equals(cce.getFeestlType())?"否":"是",style);
					POIUtils.createCell(row, (short) 10,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getAccNo()==null||"".equals(cce.getAccNo())?"":cce.getAccNo(),style);
					POIUtils.createCell(row, (short) 12,cce.getPayout_date()==null||"".equals(cce.getPayout_date())?"":cce.getPayout_date(),style);//从终表获取的打款时间
					POIUtils.createCell(row, (short) 13,cce.getSeq_no()==null||"".equals(cce.getSeq_no())?"":cce.getSeq_no(),style);//从终表获取的凭证号
					POIUtils.createCell(row, (short) 14,cce.getFinal_comments()==null||"".equals(cce.getFinal_comments())?"":cce.getFinal_comments(),style);//从终表获取的备注

//					POIUtils.createCell(row, (short) 15,cce.getFmrchNo(),style);//父商户编号
//					POIUtils.createCell(row, (short) 16,cce.getFmrName(),style);//父商户名称
//					POIUtils.createCell(row, (short) 17,i+"",style);			
					//先根据结算单号合并，再根据商户号是否一致再合并
					if ("".equals(finalStlIdTemp)) {
						finalStlIdTemp = cce.getFinalStlId()==null?"":cce.getFinalStlId();
					} else {
						if (finalStlIdTemp.equals(cce.getFinalStlId()==null?"":cce.getFinalStlId())) {
							if((i==listSize-1)&&(i-iBakOfFinalStlId+1)>1){
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (2), i + startRow, (short) (2)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (9), i + startRow, (short) (9)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (10), i + startRow, (short) (10)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (11), i + startRow, (short) (11)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (12), i + startRow, (short) (12)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (13), i + startRow, (short) (13)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBakOfFinalStlId),
										(short) (14), i + startRow, (short) (14)));
								//最后一行和上一行相同，设置合计项
								POIUtils.createCell(sheet.getRow(i + startRow - (i-iBakOfFinalStlId)), (short) 10,getMerSumAmt(list, iBakOfFinalStlId,  i, "tranAmt").toString(),style);				
								
//								System.out.println("finalStlIdTemp lastAction  i:" + i + " start:"
//										+ (i + startRow - (i-iBakOfFinalStlId)) + " end:" + (i + startRow)
//										+ " n:" + (i-iBakOfFinalStlId));								
							}
						} else {
							sizeOfFinalStlId = i-iBakOfFinalStlId;
							iBakOfFinalStlId=i;

							finalStlIdTemp = cce.getFinalStlId()==null?"":cce.getFinalStlId();
							// 同一个商户有多个消费场景才进行合并
							if(sizeOfFinalStlId>1){
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (2), i + startRow-1, (short) (2)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (9), i + startRow-1, (short) (9)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (10), i + startRow-1, (short) (10)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (11), i + startRow-1, (short) (11)));								
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (12), i + startRow-1, (short) (12)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (13), i + startRow-1, (short) (13)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfFinalStlId,
										(short) (14), i + startRow-1, (short) (14)));
								
								POIUtils.createCell(sheet.getRow(i + startRow - sizeOfFinalStlId), (short) 10,getMerSumAmt(list, i - sizeOfFinalStlId,  i-1, "tranAmt").toString(),style);				

//								System.out.println("finalStlIdTemp i:" + i + " start:"
//										+ (i + startRow - sizeOfFinalStlId) + " end:" + (i + startRow-1)
//										+ " n:" + sizeOfFinalStlId);
								sizeOfFinalStlId = 0;
							}
							//第一行和第二行不同商户，设置第一行合计项
							if(i==1){
								POIUtils.createCell(sheet.getRow(i + startRow - sizeOfFinalStlId), (short) 10,getMerSumAmt(list, i - sizeOfFinalStlId,  i-1, "tranAmt").toString(),style);				
							}
							//最后一行和上一行不同商户，设置最后一行合计项
							if(i==(listSize-1)&&i!=1&&i!=listSize-1){
								POIUtils.createCell(sheet.getRow(i + startRow - sizeOfFinalStlId), (short) 10,cce.getTranAmt().toString(),style);				
							}
						}
					}					
					
					
					if ("".equals(merNoTemp)) {
						merNoTemp = cce.getMerNo();
					} else {
						if (merNoTemp.equals(cce.getMerNo())) {
							if((i==listSize-1)&&(i-iBak+1)>1){
								sheet.addMergedRegion(new Region(i + startRow - (i-iBak),
										(short) (0), i + startRow, (short) (0)));
								sheet.addMergedRegion(new Region(i + startRow - (i-iBak),
										(short) (1), i + startRow, (short) (1)));
								
//								System.out.println("lastAction  i:" + i + " start:"
//										+ (i + startRow - (i-iBak)) + " end:" + (i + startRow)
//										+ " n:" + (i-iBak));								
							}
						} else {
							sizeOfMer = i-iBak;
							iBak=i;

							merNoTemp = cce.getMerNo();
							// 同一个商户有多个消费场景才进行合并
							if(sizeOfMer>1){
								sheet.addMergedRegion(new Region(i + startRow - sizeOfMer,
										(short) (0), i + startRow-1, (short) (0)));
								sheet.addMergedRegion(new Region(i + startRow - sizeOfMer,
										(short) (1), i + startRow-1, (short) (1)));							
								
//								System.out.println("i:" + i + " start:"
//										+ (i + startRow - sizeOfMer) + " end:" + (i + startRow-1)
//										+ " n:" + sizeOfMer);
								sizeOfMer = 0;
							}
						}
					}

				}
			}
			//创建合计行
			row=sheet.createRow(listSize+startRow);
			row.setHeight((short)(25 * 20));
			POIUtils.createCell(row, (short) 0,"合计",style);
			POIUtils.createCell(row, (short) 1,"合计",style);
			POIUtils.createCell(row, (short) 2,"合计",style);
			POIUtils.createCell(row, (short) 3,"合计",style);			
			POIUtils.createCell(row, (short) 4,consum_amt,style);				
			POIUtils.createCell(row, (short) 5,consum_num,style);				
			POIUtils.createCell(row, (short) 6,"--",style);				
			POIUtils.createCell(row, (short) 7,fee,style);				
			POIUtils.createCell(row, (short) 8,net_amt,style);				
			POIUtils.createCell(row, (short) 9,"--",style);				
			POIUtils.createCell(row, (short) 10,tran_amt,style);				
			POIUtils.createCell(row, (short) 11,"--",style);				
			POIUtils.createCell(row, (short) 12,"--",style);				
			POIUtils.createCell(row, (short) 13,"--",style);				
			POIUtils.createCell(row, (short) 14,"--",style);	
			sheet.addMergedRegion(new Region(listSize+startRow,(short) (0), listSize+startRow, (short) (3)));			
			
			//审批行
			row=sheet.createRow(listSize+startRow+2);	
			row.setHeight((short)(25 * 20));
			POIUtils.createCell(row, (short) 2, "制表:",null);
			POIUtils.createCell(row, (short) 3, UserUtils.getLoginName()==null||"".equals(UserUtils.getLoginName())?"root":UserUtils.getLoginName(),null);
			POIUtils.createCell(row, (short) 4, "复核人：",null);
			POIUtils.createCell(row, (short) 6, "会计：",null);
			POIUtils.createCell(row, (short) 8, "出纳：",null);
			POIUtils.createCell(row, (short) 10, "审核：",null);
			POIUtils.createCell(row, (short) 12, "领导审批：",null);
									
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
			log.error("ClearMerStlBookController.exportExcel()调用出现异常。");
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
	public BigDecimal getMerSumAmt(List<ClearMerStlBook> list,int startRow,int endRow,String fieldName){
		BigDecimal merSumAmt=BigDecimal.ZERO;
		BigDecimal ziduan=BigDecimal.ZERO;
		ClearMerStlBook ccb=null;
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
	@RequestMapping(value = { "getDetailList" })
	public String getDetailList(HttpServletRequest req, HttpServletResponse resp, Model model, String id) {	
		ViewStlBookDetail queryModel=new ViewStlBookDetail();
		queryModel.setId(id);
		List<ViewStlBookDetail> list = ViewStlBookDetailService.queryList(queryModel, 0, 9999999);
		//设置交易类型名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
		String tranType="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ViewStlBookDetail cmcb:list){
				tranType=cmcb.getTranType();
				if(tranType!=null&&!"".equals(tranType)){
					cmcb.setTranTypeDesc(consumpCategoryMap.get(tranType));
				}
			}		
		}
		model.addAttribute("ViewStlBookDetailList", list);
		model.addAttribute("id", id);
		model.addAttribute("query", queryModel);
		//合计
		String tran_amt="0";
		String fee="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=ViewStlBookDetailService.getSumAmt(id);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("fee", fee);
		return "modules/fs/clearMerStlBook/viewStlBookDetailList";
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
	@RequestMapping(value = { "getDetailListBak" })
	public String getDetailListBak(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, String id) {
		if("".equals(curPage+"")){
			curPage=1;
		}
		if("".equals(pageSize+"")){
			pageSize=10;
		}		
		ViewStlBookDetail queryModel=new ViewStlBookDetail();
		// 分页设置
		queryModel.setId(id);
		int count = ViewStlBookDetailService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ViewStlBookDetail> list = ViewStlBookDetailService.queryList(queryModel, startResult, endResult);
		
		model.addAttribute("ViewStlBookDetailList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
		//合计
		String tran_amt="0";
		String fee="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=ViewStlBookDetailService.getSumAmt(id);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("fee", fee);
		return "modules/fs/clearMerStlBook/viewStlBookDetailList";
	}
	
	
	/**
	 * 导出PDF
	 * @param req
	 * @param resp
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "getDetailListPdf" })
	public String getDetailListPdf(HttpServletRequest req,
			HttpServletResponse resp, Model model, String id) {
		/******************** 获取数据源 ************************/
		ViewStlBookDetail queryModel = new ViewStlBookDetail();
		queryModel.setId(id);
		List<ViewStlBookDetail> ViewStlBookDetailList = ViewStlBookDetailService.queryList(queryModel, 0, 9999999);
		//合计
		String tran_amt="0";
		String fee="0";		
		if(ViewStlBookDetailList!=null&&ViewStlBookDetailList.size()>0){
			String sumAmt=ViewStlBookDetailService.getSumAmt(id);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("fee", fee);
		
		//设置交易类型名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
		String tranType="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ViewStlBookDetail cmcb:ViewStlBookDetailList){
				tranType=cmcb.getTranType();
				if(tranType!=null&&!"".equals(tranType)){
					cmcb.setTranTypeDesc(consumpCategoryMap.get(tranType));
				}
			}	
		}
		Document document = new Document(PageSize.A4, 10, 10, 20, 10);// 创建一个Document 左右上下
		try {
			String fileName = new String(("结算初表明细").getBytes("GBK"),"ISO-8859-1");
			resp.setContentType("application/pdf;charset=utf-8");
			resp.setHeader("Content-disposition", "attachment;filename="+ fileName + ".pdf");
			// 页边空白
			PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());// 创建书写器(Writer) 与
										// document对象关联，通过书写器可以将文档写入磁盘中
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);// 设置PDF版本（默认1.4）
			// 文档属性
			document.addTitle("Title@财务结算系统-结算初表明细单"); // 标题
			document.addAuthor("Author@llg");// 作者
			document.addSubject("Subject@财务结算系统-结算初表明细单");// 主题
			document.addKeywords("Keywords@结算初表");// 关键字
			document.addCreator("Creator@llg");// 创建者

			// 设置字体
			String songtizi =req.getSession().getServletContext().getRealPath("/")+ "pdfTemp/simsun.ttc,1"; // 宋体字 模板
			BaseFont chinese = BaseFont.createFont(songtizi,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);		

			Font songtiSFivefont = new Font(chinese, 9f);//宋体小五号字
			Font songtiSTitlefont = new Font(chinese, 13f);//宋体小五号字
			Font songtiSToufont = new Font(chinese, 10f);//宋体小五号字
			
			document.open();// 打开文档
			Paragraph title = new Paragraph("商户交易明细单", songtiSTitlefont);// 设置标题
			title.setAlignment(Element.ALIGN_CENTER);// 设置对齐方式
			title.setLeading(1f);// 设置行间距
			document.add(title);
			//创建段落对象
			Anchor anchorTarget = new Anchor("交易金额合计:"+tran_amt+" 		手续费合计："+fee,songtiSToufont);
			anchorTarget.setName("BackToTop");
			Paragraph paragraph1 = new Paragraph();
			paragraph1.setSpacingBefore(5f);
			paragraph1.add(anchorTarget);
			document.add(paragraph1);
			
			//PdfPTable t = new PdfPTable(6);// 建立一个pdf表格
			float[] widths = {5f,20f,15f,15f,18f,10f,10f,10f,10f,7f,10f};// 设置表格的列宽和列数 默认是4列  
	        PdfPTable t = new PdfPTable(widths);// 建立一个pdf表格  
			t.setSpacingBefore(10f);
			t.setWidthPercentage(100);// 设置表格宽度为100%
			t.setSpacingAfter(30);
			// 字段名
			PdfPCell c1 = new PdfPCell(new Phrase("NO",songtiSToufont));
			t.addCell(c1);			
			PdfPCell c2 = new PdfPCell(new Phrase("商户编号",songtiSToufont));
			t.addCell(c2);
			PdfPCell c3 = new PdfPCell(new Phrase("商户名称",songtiSToufont));
			t.addCell(c3);
			PdfPCell c4 = new PdfPCell(new Phrase("终端号",songtiSToufont));
			t.addCell(c4);		
			PdfPCell c5 = new PdfPCell(new Phrase("卡号",songtiSToufont));
			t.addCell(c5);
			PdfPCell c6 = new PdfPCell(new Phrase("交易类型",songtiSToufont));
			t.addCell(c6);
			PdfPCell c7 = new PdfPCell(new Phrase("交易日期",songtiSToufont));
			t.addCell(c7);
			PdfPCell c8 = new PdfPCell(new Phrase("交易时间",songtiSToufont));
			t.addCell(c8);
			PdfPCell c9 = new PdfPCell(new Phrase("交易金额（元）",songtiSToufont));
			t.addCell(c9);
			PdfPCell c10 = new PdfPCell(new Phrase("费率(%)",songtiSToufont));
			t.addCell(c10);				
			PdfPCell c11 = new PdfPCell(new Phrase("手续费（元）",songtiSToufont));
			t.addCell(c11);
			ViewStlBookDetail info=null;
			for (int i=0;i<ViewStlBookDetailList.size();i++) {
				info=ViewStlBookDetailList.get(i);
				t.addCell(new PdfPCell(new Phrase(String.valueOf(i+1),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getMerNo(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getMerName(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTermNo(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getCardNo(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTranTypeDesc(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTranDate(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTranTime(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTranAmt() == null ? "0" : info.getTranAmt()
						.toString(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getTimezone() == null ? "0" : info.getTimezone()
						.toString(),songtiSFivefont)));
				t.addCell(new PdfPCell(new Phrase(info.getFee() == null ? "0" : info.getFee()
						.toString(),songtiSFivefont)));
			}

			document.add(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

		return null;
	}
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "getDetailListExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest req, HttpServletResponse resp, 
			String id) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			/******************** 获取数据源 ************************/
			ViewStlBookDetail queryModel = new ViewStlBookDetail();
			queryModel.setId(id);
			List<ViewStlBookDetail> list = ViewStlBookDetailService.queryList(queryModel, 0, 9999999);
			//合计
			String tranAmt = "0";
			String fee = "0";		
			if(list != null && list.size() > 0){
				String sumAmt = ViewStlBookDetailService.getSumAmt(id);
				if(sumAmt != null && !"".equals(sumAmt) && sumAmt.contains("#")){
					String[] arr = sumAmt.split("#");
					tranAmt = arr[0].toString();
					fee = arr[1].toString();
				}
			}
			
			String titleName = "商户结算明细单  " + getNowDt("yyyy-MM-dd HH:mm:ss", 0);
			String[] headers = {"序号","商户编号","商户名称","终端号","卡号",
					"交易类型","交易日期","交易时间","交易金额（元）","费率(%)","手续费（元）"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet("商户结算明细单", "结算明细", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.writeFile(titleName).dispose();
				return null;
			}
			
			Row row = null;
			int headrLen = 0;
			int count = 0;
			String tranTypeDesc = "";
			for (ViewStlBookDetail detail : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				exportExcel.addCell(row, --headrLen, detail.getFee() == null ?"0":detail.getFee().toPlainString());
				if (StringUtils.isEmpty(detail.getTimezone())) {
					exportExcel.addCell(row, --headrLen, "0");
				} else if (detail.getTimezone().startsWith(".")) {
					exportExcel.addCell(row, --headrLen, "0" + detail.getTimezone());
				} else {
					exportExcel.addCell(row, --headrLen, detail.getTimezone());
				}
				exportExcel.addCell(row, --headrLen, detail.getTranAmt() == null ?"0.00":detail.getTranAmt().toPlainString());
				exportExcel.addCell(row, --headrLen, detail.getTranTime() == null ?"":detail.getTranTime().trim());
				exportExcel.addCell(row, --headrLen, detail.getTranDate() == null ?"":detail.getTranDate().trim());
				
				tranTypeDesc = TranUtils.getTranTypeCortexDesc(detail.getTranType());
				exportExcel.addCell(row, --headrLen, tranTypeDesc == null ?"":tranTypeDesc.trim());
				
				exportExcel.addCell(row, --headrLen, detail.getCardNo() == null ?"":detail.getCardNo().trim());
				exportExcel.addCell(row, --headrLen, detail.getTermNo() == null ?"":detail.getTermNo().trim());
				exportExcel.addCell(row, --headrLen, detail.getMerName() == null ?"":detail.getMerName().trim());
				exportExcel.addCell(row, --headrLen, detail.getMerNo() == null ?"":detail.getMerNo().trim());
				exportExcel.addCell(row, --headrLen, ++count + "");
			}
			
			Sheet sheet = exportExcel.getSheet();
			row = exportExcel.addRow();
			int rownum = exportExcel.getRownum();
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, headers.length - 1));
			exportExcel.addCell(row, (int)(headers.length/2), "交易金额合计：" + tranAmt + "    手续费合计：" + fee);
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("ClearMerStlBookController.exportExcel()调用出现异常。");
		}
		return null;
	}
}
