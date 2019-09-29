package cn.yufu.fs.web;


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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.MerchantOrg;
import cn.yufu.cortex.service.MerchantOrgService;
import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.service.ClearMerStlFinalBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/ShareBenefitReport")

public class ShareBenefitReportController {
	Log log = Log.getLog(ShareBenefitReportController.class);
	
	@Autowired
	@Qualifier("fs.ClearMerStlFinalBookService")	
	private ClearMerStlFinalBookService ClearMerStlFinalBookService;
	
	@Autowired
	@Qualifier("cortex.MerchantOrgService")	
	private MerchantOrgService merchantOrgService;
	
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
		queryModel.setEndStlDate(getNowDt("yyyyMMdd", 0));
		queryModel.setStartStlDate(getMonthDay("yyyyMMdd", -1));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		List<MerchantOrg> list = merchantOrgService.getOrgNameAndID();
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		model.addAttribute("merchantOrgList", list);
		model.addAttribute("query", queryModel);
		
		return "modules/fs/shareBenefitReport/shareBenefitReportList";
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
	@RequestMapping(value = "getList")
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearMerStlFinalBook queryModel) {
		try{
			// 分页设置
			int count = ClearMerStlFinalBookService.shareBenefitReportCount(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<ClearMerStlFinalBook> list = ClearMerStlFinalBookService.shareBenefitReport(queryModel, startResult, endResult);
			ClearMerStlFinalBook sum = ClearMerStlFinalBookService.shareBenefitSum(queryModel);
			if (list != null && list.size() > 0) {
				for (ClearMerStlFinalBook clearMerStlFinalBook : list) {
					if (StringUtils.isNotBlank(clearMerStlFinalBook.getMerchantOrg())) {
						MerchantOrg key = merchantOrgService.selectByPrimaryKey(clearMerStlFinalBook.getMerchantOrg());
						if (key != null) {
							clearMerStlFinalBook.setMerchantOrg(key.getOrgName());
						}
					}
				}
			}
			//显示合计金额与笔数
			if (sum != null) {
				BigDecimal consumAmt = sum.getConsumAmt();	//消费金额(元)
				if (consumAmt == null) consumAmt = new BigDecimal(0);
				consumAmt = consumAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal consumNum = sum.getConsumNum();	//笔数
				if (consumNum == null) consumNum = new BigDecimal(0);
				BigDecimal fee = sum.getFee();	//手续费(元)
				if (fee == null) fee = new BigDecimal(0);
				fee = fee.setScale(2, BigDecimal.ROUND_HALF_UP);
				String merchantAmt = sum.getMerchantAmt();	//分润金额(元)
				if (merchantAmt == null) merchantAmt = "0";
				
				model.addAttribute("sumConsumAmt", consumAmt);
				model.addAttribute("sumConsumNum", consumNum);
				model.addAttribute("sumFee", fee);
				model.addAttribute("sumMerchantAmt", merchantAmt);
			}
			List<MerchantOrg> merchantOrgList = merchantOrgService.getOrgNameAndID();
			if (merchantOrgList == null || merchantOrgList.size() == 0) {
				merchantOrgList = new ArrayList<>();
			}
			model.addAttribute("merchantOrgList", merchantOrgList);
			
			model.addAttribute("shareBenefitReportList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ShareBenefitReportController.getList()调用出现异常。");
		}
		
		return "modules/fs/shareBenefitReport/shareBenefitReportList";
	}
	
	/**
	 * 取前月日期
	 * @param pattern
	 * @return
	 */
	public String getMonthDay(String pattern,int flag) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, flag); 
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
		//导出符合查询条件的全部信息
		List<ClearMerStlFinalBook> list = ClearMerStlFinalBookService.shareBenefitReport(info);
		//合计金额、笔数显示
		ClearMerStlFinalBook sum = ClearMerStlFinalBookService.shareBenefitSum(info);
		if (list == null) {
			list = new ArrayList<>();
		}
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="分润报表—商户结算终表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/shareBenefitReport.xls"; // excel模板
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
			ClearMerStlFinalBook cce = null;
			String paymentDays = "";
			for (int i = 0; i <list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){
					if (StringUtils.isNotBlank(cce.getMerchantOrg())) {
						MerchantOrg key = merchantOrgService.selectByPrimaryKey(cce.getMerchantOrg());
						if (key != null) {
							cce.setMerchantOrg(key.getOrgName());
						}
					}
					row = sheet.createRow(startRow + i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getMerchantOrg()==null||"".equals(cce.getMerchantOrg())?"":cce.getMerchantOrg(),style);
					paymentDays = cce.getStartStlDate()+"-"+cce.getEndStlDate();
					POIUtils.createCell(row, (short) 4,paymentDays,style);
					POIUtils.createCell(row, (short) 5,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 7,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getMerchantAmt()==null||"".equals(cce.getMerchantAmt())?"":cce.getMerchantAmt(),style);
				}
			}
			//显示合计金额与笔数
			if (sum != null) {
				BigDecimal consumAmt = sum.getConsumAmt();	//消费金额(元)
				if (consumAmt == null) consumAmt = new BigDecimal(0);
				consumAmt = consumAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal consumNum = sum.getConsumNum();	//笔数
				if (consumNum == null) consumNum = new BigDecimal(0);
				BigDecimal fee = sum.getFee();	//手续费(元)
				if (fee == null) fee = new BigDecimal(0);
				fee = fee.setScale(2, BigDecimal.ROUND_HALF_UP);
				String merchantAmt = sum.getMerchantAmt();	//分润金额(元)
				if (merchantAmt == null) merchantAmt = "0";
				
				String sumView = "消费金额(元)：" + consumAmt + "  笔数："
						+ consumNum + "  手续费(元)：" + fee + "  分润金额(元)："
						+ merchantAmt;
				int sumRow = startRow + list.size();
				row = sheet.createRow(sumRow);
				row.setHeight((short)(25 * 20));
				sheet.addMergedRegion(new CellRangeAddress(sumRow, sumRow, 0, 8));// 开始行，结束行，开始列，结束列  
				POIUtils.createCell(row, (short) 0,sumView,style);
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
			log.error("ShareBenefitReportController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
}
