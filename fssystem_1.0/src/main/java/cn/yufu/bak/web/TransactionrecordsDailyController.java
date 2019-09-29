package cn.yufu.bak.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import cn.yufu.bak.entity.TransactionrecordsDaily;
import cn.yufu.bak.service.MerchantXService;
import cn.yufu.bak.service.TransactionrecordsDailyService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/TransactionrecordsDaily")
public class TransactionrecordsDailyController {
	Log log = Log.getLog(TransactionrecordsDailyController.class);
	
	@Autowired
	@Qualifier("bak.TransactionrecordsDailyService")	
	private TransactionrecordsDailyService transactionrecordsDailyService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
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
	public String pageOfPaiMingTotal(HttpServletRequest req, HttpServletResponse resp, Model model, 
			TransactionrecordsDaily queryModel) {
		queryModel.setStartDt(getLastMonthDay("yyyyMMdd"));
		queryModel.setEndDt(getNowDt("yyyyMMdd",0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/transactionRecords/transactionRecordsDailyList";
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
	@RequestMapping(value = { "list" })
	public String getListOfPaiMingTotal(HttpServletRequest req, HttpServletResponse resp, 
			Model model, int curPage, int pageSize, TransactionrecordsDaily queryModel) {
		// 分页设置
		int count = transactionrecordsDailyService.queryCount(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<TransactionrecordsDaily> list = transactionrecordsDailyService.selectPageList(queryModel, startResult, endResult);
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		
		//匹配商户名称
		for (TransactionrecordsDaily transactionrecords : list) {
			if (StringUtils.isNotBlank(transactionrecords.getMerchantnumber())) {
				List<String> name = merchantXService.getMrchtName(transactionrecords.getMerchantnumber());
				if (name != null && name.size() > 0) {
					transactionrecords.setMerchantName(name.get(0));
				}
			}
		}
		//获取总笔数，总交易金额
		TransactionrecordsDaily numAndAmt = transactionrecordsDailyService.getNumAndAmt(queryModel);
		if (numAndAmt == null) {
			numAndAmt = new TransactionrecordsDaily();
			numAndAmt.setTranNum(new BigDecimal(0));
			numAndAmt.setTransactionmoneySum(new BigDecimal(0));
		}
		model.addAttribute("numAndAmt", numAndAmt);
		
		model.addAttribute("transactionRecordsList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("queryModel", queryModel);

		return "modules/fs/transactionRecords/transactionRecordsDailyList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, TransactionrecordsDaily info) {
		List<TransactionrecordsDaily> list = transactionrecordsDailyService.selectAllList(info);		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName = "财务报表—商户消费汇总表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/transactionrecordsDaily.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet = work.getSheetAt(0);
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
            POIUtils.createCell(row, (short) 2, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow = 3;//表头往上多少行,开始循环填数据行数，从0开始	
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();
			TransactionrecordsDaily cce = null;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){	
					if (StringUtils.isNotBlank(cce.getMerchantnumber())) {
						List<String> name = merchantXService.getMrchtName(cce.getMerchantnumber());
						if (name != null && name.size() > 0) {
							cce.setMerchantName(name.get(0));
						}
					}
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerchantnumber(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerchantName()==null||"".equals(cce.getMerchantName())?"":cce.getMerchantName(),style);
					POIUtils.createCell(row, (short) 3,cce.getTransactionmoneySum()==null?"0":cce.getTransactionmoneySum().toString(),style);
					POIUtils.createCell(row, (short) 4,cce.getTranNum()==null?"0":cce.getTranNum().toString(),style);
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
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TransactionrecordsDailyController.exportExcelOfPaiMingTotal()调用出现异常。");
		}
		
		return null;
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
		calendar.add(Calendar.DATE, flag); // 得到前一月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
}
