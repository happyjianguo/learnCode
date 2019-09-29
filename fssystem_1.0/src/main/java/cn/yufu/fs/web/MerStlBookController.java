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
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/MerStlBookList")
public class MerStlBookController {
	Log log = Log.getLog(MerStlBookController.class);
	
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
		CortexSysParameter sysParamter = new CortexSysParameter();
		sysParamter.setParamType("CONSUMP_CATEGORY");		
		List<CortexSysParameter> sysParamList = this.CortexService.getCortexSysParameterList(sysParamter);
		model.addAttribute("consumpCategoryList",sysParamList);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerStlBook/merStlBookList";
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
		int count = ClearMerStlBookService.queryCount(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerStlBook> list = ClearMerStlBookService.selectPageList(queryModel, startResult, endResult);
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
			String sumAmt=ClearMerStlBookService.getSumAmtTotal(queryModel);
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
		return "modules/fs/clearMerStlBook/merStlBookList";
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
		List<ClearMerStlBook> list = ClearMerStlBookService.getExcelList(info);
		int listSize=list==null?0:list.size();
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";		
		if(list!=null&&listSize>0){
			String sumAmt=ClearMerStlBookService.getSumAmtTotal(info);
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
				headName="财务报表—商户结算初表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
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
		List<ViewStlBookDetail> list = ViewStlBookDetailService.getList(queryModel, 0, 9999999);
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
			String sumAmt = ViewStlBookDetailService.getSumAmtTotal(id);
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

}
