package cn.yufu.fs.web;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
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
import cn.yufu.fs.entity.ClearTransactionAccount;
import cn.yufu.fs.service.ClearTransactionAccountService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/ClearTransactionAccount")

public class ClearTransactionAccountController {
	Log log = Log.getLog(ClearTransactionAccountController.class);
	
	@Autowired
	@Qualifier("fs.ClearTransactionAccountService")	
	private ClearTransactionAccountService ClearTransactionAccountService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearTransactionAccount queryModel) {
		//查询开始和结束日期
		
		queryModel.setEndDt(getNowDt("yyyyMMdd",0));
		queryModel.setStartDt(getLastMonthDay("yyyyMMdd"));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);


		return "modules/fs/ClearTransactionAccount/ClearTransactionAccountList";
		
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearTransactionAccount queryModel) {
		// 分页设置
		int count = ClearTransactionAccountService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearTransactionAccount> list = ClearTransactionAccountService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("ClearTransactionAccountList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
//		String card_cnt_sum="0";
//		String card_bal_sum="0";
//		String true_bal_sum="0";
//		String inst_bal_sum="0";	
//		String total_bal_sum="0";
//		
//		if(list!=null&&list.size()>0){
//			String sumAmt=ClearTransactionAccountService.getSumAmt(queryModel);
//			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
//				String[] arr=sumAmt.split("#");
//				card_cnt_sum=arr[0].toString();
//				card_bal_sum=arr[1].toString();
//				true_bal_sum=arr[2].toString();
//				inst_bal_sum=arr[3].toString();
//				total_bal_sum=arr[4].toString();
//			}
//		}
//		model.addAttribute("card_cnt_sum", card_cnt_sum);
//		model.addAttribute("card_bal_sum", card_bal_sum);
//		model.addAttribute("true_bal_sum", true_bal_sum);
//		model.addAttribute("inst_bal_sum", inst_bal_sum);
//		model.addAttribute("total_bal_sum", total_bal_sum);

		return "modules/fs/ClearTransactionAccount/ClearTransactionAccountList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearTransactionAccount info) {
		List<ClearTransactionAccount> list = ClearTransactionAccountService.queryList(info);
		int listSize=list==null?0:list.size();
//		//合计
//		String card_cnt_sum="0";
//		String card_bal_sum="0";
//		String true_bal_sum="0";
//		String inst_bal_sum="0";	
//		String total_bal_sum="0";
//		
//		if(list!=null&&list.size()>0){
//			String sumAmt=ClearTransactionAccountService.getSumAmt(info);
//			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
//				String[] arr=sumAmt.split("#");
//				card_cnt_sum=arr[0].toString();
//				card_bal_sum=arr[1].toString();
//				true_bal_sum=arr[2].toString();
//				inst_bal_sum=arr[3].toString();
//				total_bal_sum=arr[4].toString();
//			}
//		}
//		model.addAttribute("card_cnt_sum", card_cnt_sum);
//		model.addAttribute("card_bal_sum", card_bal_sum);
//		model.addAttribute("true_bal_sum", true_bal_sum);
//		model.addAttribute("inst_bal_sum", inst_bal_sum);
//		model.addAttribute("total_bal_sum", total_bal_sum);				
//		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—账户交易信息报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =null;
			path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearTransactionAccountReport.xls"; // excel模板
			
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
            POIUtils.createCell(row, (short) 4, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			ClearTransactionAccount cce = null;	
			DecimalFormat df=new DecimalFormat("0.00"); //格式化，保留两位小数 
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getCrdproduct()==null||"".equals(cce.getCrdproduct())?"":cce.getCrdproduct(),style);
					POIUtils.createCell(row, (short) 4,cce.getIid()==null||"".equals(cce.getIid())?"":cce.getIid(),style);
					POIUtils.createCell(row, (short) 5,cce.getTranAmt()==null||"".equals(cce.getTranAmt())?"0":df.format(cce.getTranAmt()).toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getTranNum()==null||"".equals(cce.getTranNum())?"0":cce.getTranNum().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getCardaccountmoney()==null||"".equals(cce.getCardaccountmoney())?"0":df.format(cce.getCardaccountmoney()).toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getTrueaccountmoney()==null||"".equals(cce.getTrueaccountmoney())?"0":df.format(cce.getTrueaccountmoney()).toString(),style);
					POIUtils.createCell(row, (short) 9,cce.getIntegrationaccountmoney()==null||"".equals(cce.getIntegrationaccountmoney())?"0":df.format(cce.getIntegrationaccountmoney()).toString(),style);
					POIUtils.createCell(row, (short) 10,cce.getCoBrandedAccountmoney()==null||"".equals(cce.getCoBrandedAccountmoney())?"0":df.format(cce.getCoBrandedAccountmoney()).toString(),style);	
					POIUtils.createCell(row, (short) 11,cce.getTransactiondate()==null||"".equals(cce.getTransactiondate())?"":cce.getTransactiondate().toString(),style);
					
				}
			}
//			//设置合计交易金额、手续费金额和条数
//			row = sheet.createRow(list.size()+startRow);
//			row.setHeight((short)(27 * 20));
//			String sumStr="合计    卡张数："+card_cnt_sum+"		卡账户余额:"+card_bal_sum+"		实名账户余额:"+true_bal_sum+"		积分账户余额:"+inst_bal_sum+"		账户总余额:"+total_bal_sum;
//			POIUtils.createCell(row, (short) 3,	sumStr,null);	
//			
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
			log.error("ClearTransactionAccountController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
}
