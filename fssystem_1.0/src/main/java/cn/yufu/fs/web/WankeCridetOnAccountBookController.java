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
import cn.yufu.fs.entity.WankeCridetOnAccountBook;
import cn.yufu.fs.service.WankeCridetOnAccountBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/WankeCridetOnAccountBook")

public class WankeCridetOnAccountBookController {
	Log log = Log.getLog(WankeCridetOnAccountBookController.class);
	
	@Autowired
	@Qualifier("fs.WankeCridetOnAccountBookService")	
	private WankeCridetOnAccountBookService WankeCridetOnAccountBookService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, WankeCridetOnAccountBook queryModel) {
//		//查询开始和结束日期
//		
//		queryModel.setEndDt(getNowDt("yyyyMMdd",0));
//		queryModel.setStartDt(getLastMonthDay("yyyyMMdd"));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);


		return "modules/fs/WankeCridetOnAccountBook/WankeCridetOnAccountBookList";
		
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, WankeCridetOnAccountBook queryModel) {
		// 分页设置
		int count = WankeCridetOnAccountBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<WankeCridetOnAccountBook> list = WankeCridetOnAccountBookService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("WankeCridetOnAccountBookList", list);
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
//			String sumAmt=WankeCridetOnAccountBookService.getSumAmt(queryModel);
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

		return "modules/fs/WankeCridetOnAccountBook/WankeCridetOnAccountBookList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, WankeCridetOnAccountBook info) {
		List<WankeCridetOnAccountBook> list = WankeCridetOnAccountBookService.queryList(info);
		int listSize=list==null?0:list.size();
//		//合计
//		String card_cnt_sum="0";
//		String card_bal_sum="0";
//		String true_bal_sum="0";
//		String inst_bal_sum="0";	
//		String total_bal_sum="0";
//		
//		if(list!=null&&list.size()>0){
//			String sumAmt=WankeCridetOnAccountBookService.getSumAmt(info);
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
			String headName="授信挂账明细单信息 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =null;
			path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/wankeCridetOnAccountBookReport.xls"; // excel模板
			
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

			WankeCridetOnAccountBook cce = null;	
			DecimalFormat df=new DecimalFormat("0.00"); //格式化，保留两位小数 
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getId(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 3,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 4,cce.getTerNo()==null||"".equals(cce.getTerNo())?"":cce.getTerNo(),style);
					POIUtils.createCell(row, (short) 5,cce.getPan()==null||"".equals(cce.getPan())?"":cce.getPan(),style);
					
					String getCardType="";
					if(cce.getCardType()==null||"".equals(cce.getCardType())){
						getCardType="";
					}else{
						if("1".equals(cce.getCardType())){
							getCardType="1-业主";
						}
						if("2".equals(cce.getCardType())){
							getCardType="2-员工";
						}
					}
					POIUtils.createCell(row, (short) 6,getCardType,style);
					POIUtils.createCell(row, (short) 7,cce.getCredetDate()==null||"".equals(cce.getCredetDate())?"":cce.getCredetDate(),style);
					POIUtils.createCell(row, (short) 8,cce.getCredetTime()==null||"".equals(cce.getCredetTime())?"":cce.getCredetTime(),style);
					POIUtils.createCell(row, (short) 9,cce.getCreditAmt()==null||"".equals(cce.getCreditAmt())?"0":df.format(cce.getCreditAmt()).toString(),style);	
					POIUtils.createCell(row, (short) 10,cce.getRepayAmt()==null||"".equals(cce.getRepayAmt())?"0":df.format(cce.getRepayAmt()).toString(),style);	
					POIUtils.createCell(row, (short) 11,cce.getRepayDate()==null||"".equals(cce.getRepayDate())?"":cce.getRepayDate(),style);
					POIUtils.createCell(row, (short) 12,cce.getRepayTime()==null||"".equals(cce.getRepayTime())?"":cce.getRepayTime(),style);
					POIUtils.createCell(row, (short) 13,cce.getDebtAmt()==null||"".equals(cce.getDebtAmt())?"0":df.format(cce.getDebtAmt()).toString(),style);
					POIUtils.createCell(row, (short) 14,cce.getIcSaleCodeid()==null||"".equals(cce.getIcSaleCodeid())?"":cce.getIcSaleCodeid(),style);
					POIUtils.createCell(row, (short) 15,cce.getIcSaleCode()==null||"".equals(cce.getIcSaleCode())?"":cce.getIcSaleCode(),style);
					POIUtils.createCell(row, (short) 16,cce.getIcSaleName()==null||"".equals(cce.getIcSaleName())?"":cce.getIcSaleName(),style);
					POIUtils.createCell(row, (short) 17,cce.getIcItemCodeid()==null||"".equals(cce.getIcItemCodeid())?"":cce.getIcItemCodeid(),style);
					POIUtils.createCell(row, (short) 18,cce.getIcItemCode()==null||"".equals(cce.getIcItemCode())?"":cce.getIcItemCode(),style);
					POIUtils.createCell(row, (short) 19,cce.getIcItemName()==null||"".equals(cce.getIcItemName())?"":cce.getIcItemName(),style);
					POIUtils.createCell(row, (short) 20,cce.getCardOwner()==null||"".equals(cce.getCardOwner())?"":cce.getCardOwner(),style);
					POIUtils.createCell(row, (short) 21,cce.getTelphone()==null||"".equals(cce.getTelphone())?"":cce.getTelphone(),style);
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
			resp.setHeader("Content-disposition","attachment;filename=" + filename + ".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("WankeCridetOnAccountBookController.exportExcel()调用出现异常。");
		}
		
		return null;
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
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String WankeCridetOnAccountBookId) {
		WankeCridetOnAccountBook info = WankeCridetOnAccountBookService.queryInfo(WankeCridetOnAccountBookId);

		model.addAttribute("info", info);
		
		return "modules/fs/WankeCridetOnAccountBook/WankeCridetOnAccountBookShow";
	}
	
}
