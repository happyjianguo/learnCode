package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.service.MerStlFinalBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.NumberToCN;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/MerStlFinalBook")
@SuppressWarnings("deprecation")
public class MerStlFinalBookController {
	Log log = Log.getLog(MerStlFinalBookController.class);
	private static ConcurrentMap<String, Templates> templateCache = new ConcurrentHashMap<String, Templates>();
	
	@Autowired
	@Qualifier("fs.MerStlFinalBookService")	
	private MerStlFinalBookService merStlFinalBookService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, 
						ClearMerStlFinalBook queryModel) {
		queryModel.setStlDate(getNowDt("yyyyMMdd",0));	
		queryModel.setStlDate1(getNowDt("yyyyMMdd",0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("query", queryModel);
		//获取省份LIST
		CortexArea cortexArea=new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList=this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		
		return "modules/fs/clearMerStlFinalBook/merStlFinalBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerStlFinalBook queryModel) {
		try{
			// 分页设置
			int count = merStlFinalBookService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			String stlDate = queryModel.getStlDate();
			String stlDate1 = queryModel.getStlDate1();
		
			
			List<ClearMerStlFinalBook> list = merStlFinalBookService.queryList(queryModel, startResult, endResult);
			//设置消费场景码表
			CortexSysParameter sysParamter=new CortexSysParameter();
			sysParamter.setParamType("CONSUMP_CATEGORY");		
			List<CortexSysParameter>  sysParamList=this.CortexService.getCortexSysParameterList(sysParamter);
			model.addAttribute("consumpCategoryList",sysParamList);
			
			//获取省份LIST
			CortexArea cortexArea=new CortexArea();
			cortexArea.setFid(0l);
			cortexArea.setIsuse("1");
			List<CortexArea> provinceList=this.CortexService.getCortexAreaList(cortexArea);
			model.addAttribute("provinceList", provinceList);
			
			model.addAttribute("ClearMerStlFinalBookList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			queryModel.setStlDate(stlDate);
			queryModel.setStlDate1(stlDate1);
			model.addAttribute("query", queryModel);
			//model.addAttribute("queryCityCode", queryModel.getCityCode());
	
			//合计
			String consum_num="0";
			String consum_amt="0";
			String tran_amt="0";
			String net_amt="0";	
			String fee="0";
			
			if(list!=null&&list.size()>0){
				String sumAmt=merStlFinalBookService.getSumAmt(queryModel);
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
			
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearMerStlFinalBookController.getList()调用出现异常。");
		}
		
		return "modules/fs/clearMerStlFinalBook/merStlFinalBookList";
	}
	
	/**
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param idListStr
	 * @param flag
	 * @return
	 */
	@RequestMapping(value = { "shenhe" })
	@ResponseBody
	public String shenhe(HttpServletRequest req, HttpServletResponse resp, Model model,String idListStr,String flag) {
		String info="";
		int result = -2;
		try {
			if(idListStr!=null&&!"".equals(idListStr)&&flag!=null&&!"".equals(flag)){
				List<ClearMerStlFinalBook> list=new ArrayList<ClearMerStlFinalBook>();
				ClearMerStlFinalBook fb=null;				
				if(idListStr.contains(",")){
					String[] strList=idListStr.split(",");
					for(int i=0;i<strList.length;i++){
						fb=new ClearMerStlFinalBook();
						fb.setId(strList[i]);
						list.add(fb);
					}
				}else{
					fb=new ClearMerStlFinalBook();
					fb.setId(idListStr);
					list.add(fb);
				}
				result = merStlFinalBookService.updatePayoutStatus(flag, list);
			}
			
			if(result==-2){
				info="参数无效";
			}else if(result!=-2&&result<0){
				info="数据库异常，操作失败";
			}else{
				if(flag!=null){
					if("0".equals(flag)){
						info="审核成功";	
					}else if("1".equals(flag)){
						info="确认打款成功";
					}else{
						info="作废成功";	
					}
				}

			}
		} catch (Exception e) {
			log.info("结算终表审核异常：{}", e);
			info="数据库异常，操作失败"+e.getMessage();
		}
		return info;	
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerStlFinalBook info) {
		List<ClearMerStlFinalBook> list = merStlFinalBookService.queryList(info);
		List<ClearMerStlFinalBook> listSum =merStlFinalBookService.selectGroupSumByExample(info);
		Map<Long ,ClearMerStlFinalBook> mapSum=new HashMap<Long, ClearMerStlFinalBook>();
		for (ClearMerStlFinalBook clearMerStlFinalBook : listSum) {
			mapSum.put(clearMerStlFinalBook.getCityCode(), clearMerStlFinalBook);
		}
		
		//String size=list==null?"0":String.valueOf(list.size());
		//合计
		ClearMerStlFinalBook clearMerStlFinalSumAll =new ClearMerStlFinalBook();
		
		if(list!=null&&list.size()>0){
			String sumAmt=merStlFinalBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				clearMerStlFinalSumAll.setConsumNum(new BigDecimal(arr[0].toString()));
				clearMerStlFinalSumAll.setConsumAmt(new BigDecimal(arr[1].toString()));
				clearMerStlFinalSumAll.setTranAmt(new BigDecimal(arr[2].toString()));
				clearMerStlFinalSumAll.setNetAmt(new BigDecimal(arr[3].toString()));
				clearMerStlFinalSumAll.setFee(new BigDecimal(arr[4].toString()));
			}
		}	
		
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName=null;
			if("1".equals(info.getFlagOnline())){
				headName="财务报表—线上商户结算终表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			
			}else if("0".equals(info.getFlagOnline())){
				headName="财务报表—商户结算终表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}else{
				headName="财务报表—全部商户结算终表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearMerStlFinalBookReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 7, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();	
			ClearMerStlFinalBook cce = null;
			
			String payoutStatus="";
			Long cityCode=0l;
			Long newCityCode=0l;
			String merNo="";
			String newMerNo="";
			String startStlDate =null;
			String endStlDate=null;
			String newStartStlDate="";
			String newEndStlDate="";
			BigDecimal tranAmtSum=new BigDecimal(0);
			
			ClearMerStlFinalBook clearMerStlFinalSum =new ClearMerStlFinalBook();
			
			int changeRowNum=0;//增加城市合计  的行数
			int trantAmtSumRow=startRow;//每次合计实际结算金额合计的开始行号
			for (int i = 0; i <list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){
					if(i==0){
						cityCode=newCityCode=list.get(i).getCityCode();
					}
					newCityCode=cce.getCityCode();
									
					if(cityCode-newCityCode!=0){
						clearMerStlFinalSum = mapSum.get(cityCode);
						changeRowNum=changeRowNum+1;
						citySum(sheet, style, startRow, clearMerStlFinalSum,i+changeRowNum-1);					
						cityCode=newCityCode;
					}
					if("0".equals(cce.getPayoutStatus())){
						payoutStatus="未打款";
					}else if("1".equals(cce.getPayoutStatus())){
						payoutStatus="审核通过";
					}else if("2".equals(cce.getPayoutStatus())){
						payoutStatus="审核拒绝";
					}else if("3".equals(cce.getPayoutStatus())){
						payoutStatus="打款成功";
					}else if("8".equals(cce.getPayoutStatus())){
						payoutStatus="作废";
					}
					row = sheet.createRow(startRow+i+changeRowNum);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 1,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 2,cce.getProvinceName()==null||"".equals(cce.getProvinceName())?"":cce.getProvinceName(),style);
					POIUtils.createCell(row, (short) 3,cce.getCityName()==null||"".equals(cce.getCityName())?"":cce.getCityName(),style);
					POIUtils.createCell(row, (short) 4,"0".equals(cce.getBjFlag())?"外省":"北京",style);
					
					POIUtils.createCell(row, (short) 5,"1".equals(cce.getExelusiveCardFlag())?"是":"否",style);
					
					POIUtils.createCell(row, (short) 6,cce.getCardTypeName()==null?"":cce.getCardTypeName().toString(),style);
					POIUtils.createCell(row, (short) 7,"0".equals(cce.getStlNeedFlag())?"不需要":"需要",style);
					POIUtils.createCell(row, (short) 8,cce.getStartStlDate()+"-"+cce.getEndStlDate(),style);
					POIUtils.createCell(row, (short) 9,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 10,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 12,cce.getNetAmt()==null?"0":cce.getNetAmt().toString(),style);
					
					BigDecimal tranAmt=cce.getTranAmt();
					
					//得到 本条记录 的结算开始和结束日期和商户号
					startStlDate =cce.getStartStlDate();
					endStlDate = cce.getEndStlDate();
					merNo=cce.getMerNo();
					//得到下一条记录的结算起始和结束日期商户号
					if(i<=list.size()-2){
						newEndStlDate=list.get(i+1).getEndStlDate();
						newStartStlDate=list.get(i+1).getStartStlDate();
						newMerNo=list.get(i+1).getMerNo();
					}
				
					if(merNo.equals(newMerNo)&&"1".equals(cce.getMergeMoneyFlag())){//如果是同一个商户
						if(trantAmtSumRow==0){
							trantAmtSumRow=startRow+i+changeRowNum;
						}
						//如果结算开始和终止日期相同则将数据相加
						if(newStartStlDate.equals(startStlDate)&&newEndStlDate.equals(endStlDate)){
							tranAmtSum=tranAmtSum.add(tranAmt);
							POIUtils.createCell(row, (short) 13,null,style);
							
						}else{//如果商户号相同，结算开始或结束日期不同
							
							//将专属卡和普通卡合并显示
							mergeCellValue(sheet, row, style, startRow, tranAmtSum, 
									changeRowNum, trantAmtSumRow, i, tranAmt);
							//置空
							tranAmtSum=new BigDecimal(0);
							trantAmtSumRow=0;
							merNo=newMerNo;
							startStlDate =newStartStlDate;
							endStlDate=newEndStlDate;
							merNo=newMerNo;
						}
						if(i==list.size()-1){
							tranAmt=new BigDecimal(0);
							mergeCellValue(sheet, row, style, startRow, tranAmtSum,
									changeRowNum, trantAmtSumRow, list.size()-1, tranAmt);
						}
						
					}else{//如果商户号不同则导出
						if(trantAmtSumRow==0){
							trantAmtSumRow=startRow+i+changeRowNum;
						}
						
						mergeCellValue(sheet, row, style, startRow,tranAmtSum,
								changeRowNum, trantAmtSumRow, i, tranAmt);
						//置空
						tranAmtSum=new BigDecimal(0);
						trantAmtSumRow=0;
						merNo=newMerNo;
						startStlDate =newStartStlDate;
						endStlDate=newEndStlDate;
						merNo=newMerNo;
					}
					
					POIUtils.createCell(row, (short) 14,cce.getAccNo()==null||"".equals(cce.getAccNo())?"":cce.getAccNo(),style);
					POIUtils.createCell(row, (short) 15,cce.getAccBankName()==null||"".equals(cce.getAccBankName())?"":cce.getAccBankName(),style);
					//开户名称修改为结算账户名称即将accountName--->	payoutJunl				
					POIUtils.createCell(row, (short) 16,cce.getPayoutJunl()==null||"".equals(cce.getPayoutJunl())?"":cce.getPayoutJunl(),style);
					POIUtils.createCell(row, (short) 17,cce.getBankUnion()==null||"".equals(cce.getBankUnion())?"":cce.getBankUnion(),style);
					POIUtils.createCell(row, (short) 18,cce.getBankCode()==null||"".equals(cce.getBankCode())?"":cce.getBankCode(),style);
					POIUtils.createCell(row, (short) 19,"0".equals(cce.getBeijingFlag())?"否":"是",style);
					POIUtils.createCell(row, (short) 20,cce.getStlDate()==null||"".equals(cce.getStlDate())?"":cce.getStlDate(),style);
					POIUtils.createCell(row, (short) 21,payoutStatus,style);
				}
			}
		
			//得到最后一个城市合计信息并 设置最后一个城市的合计
			if(list.size()>0){
				clearMerStlFinalSum =mapSum.get(list.get(list.size()-1).getCityCode());
				citySum(sheet, style, startRow,clearMerStlFinalSum, list.size()+changeRowNum);
				//设置合计交易金额、手续费金额和条数
				clearMerStlFinalSumAll.setCityName("全国");
				citySum(sheet, style, startRow, clearMerStlFinalSumAll, list.size()+changeRowNum+1);
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
			e.printStackTrace();
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearMerStlFinalBookController.exportExcel()调用出现异常。");
		}
		
		return null;
	}

	/**
	 * 将专属卡和普通卡结算金额合并显示
	 * @param sheet
	 * @param row
	 * @param style
	 * @param startRow
	 * @param cce
	 * @param tranAmtSum   合计值
	 * @param changeRowNum    城市合计的行数
	 * @param trantAmtSumRow  合计影响的行数
	 * @param i   i
	 * @param tranAmt  
	 */
	private void mergeCellValue(HSSFSheet sheet, HSSFRow row, HSSFCellStyle style,
			int startRow, BigDecimal tranAmtSum, int changeRowNum,
			int trantAmtSumRow, int i, BigDecimal tranAmt) {
		
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(trantAmtSumRow, startRow+i+changeRowNum, (short) 13,  (short) 13));
		//得到指定的行
		HSSFRow hssfRow = sheet.getRow((short) trantAmtSumRow);
		//得到指定的列
		HSSFCell cell = hssfRow.getCell((short)13);
		if(cell!=null){
			tranAmtSum=tranAmtSum.add(tranAmt);
			cell.setCellValue(tranAmtSum.toString());
		}else{
			POIUtils.createCell(row, (short) 13,tranAmt.toString(),style);
		}
	
	}
	
	/**
	 * 最终合计表合计信息 
	 * @param sheet
	 * @param style
	 * @param startRow   开始的行数
	 * @param cityName    
	 * @param consumAmtTotal
	 * @param consumNumTotal
	 * @param feeTotal
	 * @param netAmtTotal
	 * @param tranAmtTotal
	 * @param i
	 */
	private void finalSum(HSSFSheet sheet, HSSFCellStyle style, int startRow, String cityName,
			String consumAmtTotal, String consumNumTotal, String feeTotal,
			String netAmtTotal, String tranAmtTotal, int i) {
		HSSFRow row;
		//创建每个城市合计行 
		
		row = sheet.createRow(startRow+i);
		row.setHeight((short)(25 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(startRow+i, startRow+i, (short) 0,  (short) 2));
		POIUtils.createCell(row, (short) 0,cityName+"合计",style);
		POIUtils.createCell(row, (short) 1,null,style);
		POIUtils.createCell(row, (short) 2,null,style);
				
		POIUtils.createCell(row, (short) 3,consumAmtTotal.toString(),style);
		POIUtils.createCell(row, (short) 4,consumNumTotal.toString(),style);
		POIUtils.createCell(row, (short) 5,feeTotal.toString(),style);
		POIUtils.createCell(row, (short) 6,netAmtTotal.toString(),style);
		POIUtils.createCell(row, (short) 7,tranAmtTotal.toString(),style);
		
	}
	
	//每个城市合计信息
	private void citySum(HSSFSheet sheet, HSSFCellStyle style, int startRow, ClearMerStlFinalBook clearMerStlFinalSum, int i) {
		HSSFRow row;
		//创建每个城市合计行 
		
		row = sheet.createRow(startRow+i);
		row.setHeight((short)(25 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(startRow+i, startRow+i, (short) 0,  (short) 1));
		POIUtils.createCell(row, (short) 0,clearMerStlFinalSum.getCityName()+"合计",style);
		POIUtils.createCell(row, (short) 1,null,style);
		POIUtils.createCell(row, (short) 2,null,style);
		POIUtils.createCell(row, (short) 3,null,style);
		POIUtils.createCell(row, (short) 4,null,style);
		POIUtils.createCell(row, (short) 5,null,style);
		POIUtils.createCell(row, (short) 6,null,style);
		POIUtils.createCell(row, (short) 7,null,style);
		POIUtils.createCell(row, (short) 8,null,style);
		
		POIUtils.createCell(row, (short) 9,clearMerStlFinalSum.getConsumAmt().toString(),style);
		POIUtils.createCell(row, (short) 10,clearMerStlFinalSum.getConsumNum().toString(),style);
		POIUtils.createCell(row, (short) 11,clearMerStlFinalSum.getFee().toString(),style);
		POIUtils.createCell(row, (short) 12,clearMerStlFinalSum.getNetAmt().toString(),style);
		POIUtils.createCell(row, (short) 13,clearMerStlFinalSum.getTranAmt().toString(),style);
		
		POIUtils.createCell(row, (short) 14,null,style);
		POIUtils.createCell(row, (short) 15,null,style);
		POIUtils.createCell(row, (short) 16,null,style);
		POIUtils.createCell(row, (short) 17,null,style);
		POIUtils.createCell(row, (short) 18,null,style);
		POIUtils.createCell(row, (short) 19,null,style);
		POIUtils.createCell(row, (short) 20,null,style);
		POIUtils.createCell(row, (short) 21,null,style);
	}
	
	/**
	 * 返回LIST的字段fieldName从startRow到endRow的合计
	 * @param list
	 * @param startRow
	 * @param endRow
	 * @param fieldName
	 * @return
	 */
	public BigDecimal getMerSumAmt(List<ClearMerStlFinalBook> list,int startRow,int endRow,String fieldName){
		BigDecimal merSumAmt=BigDecimal.ZERO;
		BigDecimal ziduan=BigDecimal.ZERO;
		ClearMerStlFinalBook ccb=null;
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
	
	@RequestMapping(value = { "batchPrint" })
	public void batchPrint(HttpServletRequest req,HttpServletResponse resp, Model model,ClearMerStlFinalBook info,String merNo,
			String merName,String stlDate,String payoutDate,String payoutJunl,String seqNo,String payoutStatus){
		info.setMerNo(merNo);
		info.setMerName(merName);
		info.setStlDate(stlDate);
		info.setPayoutDate(payoutDate);
		info.setPayoutJunl(payoutJunl);
		info.setSeqNo(seqNo);
		info.setPayoutStatus(payoutStatus);		
		try {
			List<ClearMerStlFinalBook> list = merStlFinalBookService.queryList(info);
			/* set the response buff 512K */
			resp.setBufferSize(1024 * 512);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print("<html xmlns='http://www.w3.org/1999/xhtml'>");
			
			resp.getWriter().print("<head>");
			resp.getWriter().print("<SCRIPT LANGUAGE='JavaScript'>");
			resp.getWriter().print("function printpage_zzjs() {alert('11111111');}");			
			resp.getWriter().print("</script>");			
			resp.getWriter().print("</head>");
			
			resp.getWriter().print("<body>");
			int idx = 1;
			for(ClearMerStlFinalBook cmsfb:list){
				// 当前处理的序列号
				//设置XML
				float height = 200f;//336.9f;
				int page = idx / 10;
				String yPos=((idx - 1) * height + page * 0.3f)+"";
				StringWriter wrt = new StringWriter();
				StreamResult out = new StreamResult(wrt);
				Transformer trans = privateGetTransformer("merStl",req);
				trans.setParameter("yPos", yPos);
				trans.setParameter("seq", String.valueOf(idx));
				trans.setParameter("merName", cmsfb.getMerName()+"("+cmsfb.getMerNo()+")");
				trans.setParameter("stlDate", cmsfb.getStartStlDate()+"-"+cmsfb.getEndStlDate());
				trans.setParameter("consumAmt", "￥"+(cmsfb.getConsumAmt()==null?"0":cmsfb.getConsumAmt().toString()));
				trans.setParameter("fee", "￥"+(cmsfb.getFee()==null?"0":cmsfb.getFee().toString()));
				trans.setParameter("tranAmt", "￥"+(cmsfb.getTranAmt()==null?"0":cmsfb.getTranAmt().toString()));
				trans.setParameter("tranAmtToUpperCase", NumberToCN.number2CNMontrayUnit(cmsfb.getTranAmt()==null?BigDecimal.ZERO:cmsfb.getTranAmt()));
				
				String xmlStr=getXmlData(cmsfb);
				Source xmlSource = new StreamSource(new StringReader(xmlStr));
				trans.transform(xmlSource, out);
				String html = wrt.toString();
				wrt = null;
				resp.getWriter().write(html);
				if (idx % 5 == 0)
					resp.flushBuffer();
				idx++;

			}
			resp.flushBuffer();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				resp.getWriter().print("</body></html>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 从缓存中获得Templates实例
	 * 
	 * @param xsltFileName
	 * @return
	 */
	protected Transformer privateGetTransformer(String xsltFileName,HttpServletRequest req) {
		Templates templates = (Templates) templateCache.get(xsltFileName);
		if (templates == null) {
			templates = createTemplates(xsltFileName,req);
			Templates oldTemplate = (Templates) templateCache.putIfAbsent(xsltFileName, templates);
			if (oldTemplate != null)
				templates = oldTemplate;
		}
		Transformer transformer = null;
		try {
			transformer = templates.newTransformer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transformer;
	}

	/**
	 * 根据模板xslt文件创建Template
	 * 
	 * @param xsltFileName
	 * @return
	 * @throws TransformerFactoryConfigurationError
	 */
	private Templates createTemplates(String xsltFileName,HttpServletRequest req) throws TransformerFactoryConfigurationError {
		SAXReader reader = new SAXReader();
		try {
			Document stylesheet = reader.read(req.getSession().getServletContext().getRealPath("/")+ "xslTemp/merStl.xsl");
			Source xslSource = new DocumentSource(stylesheet);
			TransformerFactory transFact = TransformerFactory.newInstance();
			return transFact.newTemplates(xslSource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getXmlData(ClearMerStlFinalBook cmsfb) {
		StringBuilder str = new StringBuilder();
		str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		str.append("<?xml-stylesheet type=\"text/xsl\" href=\"xslTemp/merStl.xsl\"?>");
		str.append("<createDate>").append("("+getNowDt("yyyyMMdd",0)+")").append("</createDate>");
//		str.append("<merName>").append(cmsfb.getMerName()).append("</merName>");
//		str.append("<stlDate>").append(cmsfb.getStartStlDate()+"-"+cmsfb.getEndStlDate()).append("</stlDate>");
//		str.append("<consumAmt>").append(cmsfb.getConsumAmt()==null?"0":cmsfb.getConsumAmt().toString()).append("</consumAmt>");
//		str.append("<fee>").append(cmsfb.getFee()==null?"0":cmsfb.getFee().toString()).append("</fee>");
//		str.append("<tranAmt>").append(cmsfb.getTranAmt()==null?"0":cmsfb.getTranAmt().toString()).append("</tranAmt>");
//		str.append("<tranAmtToUpperCase>").append(cmsfb.getMerName()).append("</tranAmtToUpperCase>");
		
		return str.toString();
	}
	
/********************************************************已结算报表*****************************************************************/	
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "pageBak" })
	public String pageBak(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerStlFinalBook queryModel) {
		queryModel.setStlDate(getNowDt("yyyyMMdd",-1));
		//queryModel.setPayoutDate(getNowDt("yyyyMMdd",-1));	
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearMerStlFinalBook/clearMerStlFinalBookBakList";
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
	@RequestMapping(value = { "getListBak" })
	public String getListBak(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerStlFinalBook queryModel) {
		//获取所有已打款结算终表信息
		queryModel.setPayoutStatus("3");

		// 分页设置
		int count = merStlFinalBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearMerStlFinalBook> list = merStlFinalBookService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("ClearMerStlFinalBookList", list);
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
			String sumAmt=merStlFinalBookService.getSumAmt(queryModel);
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
		return "modules/fs/clearMerStlFinalBook/clearMerStlFinalBookBakList";
	}
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcelBak" })
	@ResponseBody
	public String exportExcelBak(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerStlFinalBook info) {
		//获取所有已打款结算终表信息
		info.setPayoutStatus("3");
		
		List<ClearMerStlFinalBook> list = merStlFinalBookService.queryList(info);
		//String size=list==null?"0":String.valueOf(list.size());
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=merStlFinalBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				consum_num=arr[0].toString();
				consum_amt=arr[1].toString();
				tran_amt=arr[2].toString();
				net_amt=arr[3].toString();
				fee=arr[4].toString();
			}
		}		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—已结算报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearMerStlFinalBookBakReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 7, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();	
			ClearMerStlFinalBook cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){				
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getStartStlDate()+"-"+cce.getEndStlDate(),style);
					POIUtils.createCell(row, (short) 4,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getNetAmt()==null?"0":cce.getNetAmt().toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					POIUtils.createCell(row, (short) 9,cce.getStlDate()==null||"".equals(cce.getStlDate())?"":cce.getStlDate(),style);
					POIUtils.createCell(row, (short) 10,cce.getPayoutDate()==null||"".equals(cce.getPayoutDate())?"":cce.getPayoutDate(),style);
				}
			}			
			
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			//设置合计交易金额、手续费金额和条数
			merBakSum(sheet, style, startRow, "全部", consum_amt, consum_num,
					fee, net_amt, tran_amt, list.size());
//			String sumStr="合计     消费金额："+consum_amt+"    消费笔数："+consum_num+"    手续费："+fee+"    净额 ："+net_amt+"   实际结算金额："+tran_amt;
//			POIUtils.createCell(row, (short) 3, sumStr,null);
//			row.setHeight((short)(25 * 20));	
			
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
			log.error("ClearMerStlFinalBookController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	//已结算信息合计
	private void merBakSum(HSSFSheet sheet, HSSFCellStyle style, int startRow, String cityName,
			String consumAmtTotal, String consumNumTotal, String feeTotal,
			String netAmtTotal, String tranAmtTotal, int i) {
		HSSFRow row;
		//创建每个城市合计行 
		
		row = sheet.createRow(startRow+i);
		row.setHeight((short)(25 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(startRow+i, startRow+i, (short) 0,  (short) 3));
		POIUtils.createCell(row, (short) 0,cityName+"合计",style);
		POIUtils.createCell(row, (short) 1,null,style);
		POIUtils.createCell(row, (short) 2,null,style);
		POIUtils.createCell(row, (short) 3,null,style);
		
		POIUtils.createCell(row, (short) 4,consumAmtTotal.toString(),style);
		POIUtils.createCell(row, (short) 5,consumNumTotal.toString(),style);
		POIUtils.createCell(row, (short) 6,feeTotal.toString(),style);
		POIUtils.createCell(row, (short) 7,netAmtTotal.toString(),style);
		POIUtils.createCell(row, (short) 8,tranAmtTotal.toString(),style);
		
		POIUtils.createCell(row, (short) 9,null,style);
		POIUtils.createCell(row, (short) 10,null,style);
		
	}
	
	/**
	 * 通过商户号获取终端LIST
	 * @param req
	 * @param resp
	 * @param model
	 * @param merNo
	 * @return
	 */
	@RequestMapping(value = { "getAreaListByFid" })
	@ResponseBody
	public String getAreaListByFid(HttpServletRequest req,HttpServletResponse resp, Model model, String fid) {
		if(fid!=null&&!"".equals(fid)){
			CortexArea cortexArea=new CortexArea();
			cortexArea.setFid(Long.valueOf(fid));
			cortexArea.setIsuse("1");
			return JsonUtil.getJsonString(CortexService.getCortexAreaList(cortexArea));
		}else{
			return null;
		}
	}

/********************************************************结算终表按省份和城市合计报表*****************************************************************/	
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "pagePC" })
	public String pagePC(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMerStlFinalBook queryModel) {
		queryModel.setStlDate(getNowDt("yyyyMMdd",0));
		queryModel.setStlDate1(getNowDt("yyyyMMdd",0));
		model.addAttribute("query", queryModel);
		//获取省份LIST
		CortexArea cortexArea=new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList=this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		
		return "modules/fs/clearMerStlFinalBook/clearMerStlFinalBookPCList";
	}

	/**
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getListPC" })
	public String getListPC(HttpServletRequest req, HttpServletResponse resp, Model model,ClearMerStlFinalBook queryModel) {
		List<ClearMerStlFinalBook> list = merStlFinalBookService.selectGroupSumByExample(queryModel);		
		model.addAttribute("ClearMerStlFinalBookList", list);	
		model.addAttribute("query", queryModel);
		
		//获取省份LIST
		CortexArea cortexArea=new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList=this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";
		
		if(list!=null&&list.size()>0){
			String sumAmt=merStlFinalBookService.getSumAmt(queryModel);
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
		return "modules/fs/clearMerStlFinalBook/clearMerStlFinalBookPCList";
	}
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcelPC" })
	@ResponseBody
	public String exportExcelPC(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerStlFinalBook info) {		
		List<ClearMerStlFinalBook> list = merStlFinalBookService.selectGroupSumByExample(info);
		//合计
		String consum_num="0";
		String consum_amt="0";
		String tran_amt="0";
		String net_amt="0";	
		String fee="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=merStlFinalBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				consum_num=arr[0].toString();
				consum_amt=arr[1].toString();
				tran_amt=arr[2].toString();
				net_amt=arr[3].toString();
				fee=arr[4].toString();
			}
		}		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—已结算合计报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			if("1".equals(info.getFlagOnline())){
				headName="财务报表—线上商户已结算合计报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}else if("0".equals(info.getFlagOnline())){
				headName="财务报表—线下商户已结算合计报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
				
			}
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearMerStlFinalBookPCReport.xls"; // excel模板
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
			ClearMerStlFinalBook cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){				
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getProvinceName()==null||"".equals(cce.getProvinceName())?"":cce.getProvinceName(),style);
					POIUtils.createCell(row, (short) 2,cce.getCityName()==null||"".equals(cce.getCityName())?"":cce.getCityName(),style);
					POIUtils.createCell(row, (short) 3,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 4,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getNetAmt()==null?"0":cce.getNetAmt().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
				}
			}			
			
			//设置合计交易金额、手续费金额和条数
//			row = sheet.createRow(list.size()+startRow);
			//设置合计交易金额、手续费金额和条数
			finalSum(sheet, style, startRow, "全部", consum_amt, consum_num,
					fee, net_amt, tran_amt, list.size());	
			
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
			log.error("ClearMerStlFinalBookController.exportExcel()调用出现异常。");
		}
		
		return null;
	}	
	
}
