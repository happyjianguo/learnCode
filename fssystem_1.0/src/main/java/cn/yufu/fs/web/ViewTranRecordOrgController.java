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
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.ViewTranRecordOrg;
import cn.yufu.fs.service.ViewTranRecordOrgService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

/**
 * 机构/卡BIN分润Controller
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月07日
 * 描述:机构/卡BIN分润Controller
 * */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value="/viewTranRecordOrg")
public class ViewTranRecordOrgController {
	
	Log log = Log.getLog(ViewTranRecordOrgController.class);
	
	@Autowired
	@Qualifier("fs.ViewTranRecordOrgService")
	private ViewTranRecordOrgService viewTranRecordOrgService;
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, ViewTranRecordOrg queryModel) {
		queryModel.setTransactiondateStart(getMonthDay("yyyyMMdd", -1));
		queryModel.setTransactiondateEnd(getNowDt("yyyyMMdd", 0));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/shareBenefitReport/viewTranRecordOrgList";
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
	@RequestMapping(value = "list")
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, ViewTranRecordOrg queryModel) {
		// 分页设置
		int counts = viewTranRecordOrgService.queryCount(queryModel);
		
		Page page = new Page(curPage, pageSize, counts);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取实名认证列表数据
		List<ViewTranRecordOrg> list = viewTranRecordOrgService.selectPageList(queryModel, startResult, endResult);
		if (list == null || list.size() == 0) list = new ArrayList<>();
		model.addAttribute("viewTranRecordOrgList", list);
		
		//金额汇总
		ViewTranRecordOrg sumAmt = viewTranRecordOrgService.getSumAmt(queryModel);
		model.addAttribute("sumAmt", sumAmt);
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/shareBenefitReport/viewTranRecordOrgList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, 
			ViewTranRecordOrg queryModel) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="机构-卡BIN 分润报表("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/viewTranRecordOrg.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet = work.getSheetAt(0);

			//设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			HSSFCellStyle style = work.createCellStyle();
			DataFormat format = work.createDataFormat();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            
            POIUtils.createCell(row, (short) 5, headName,style);
			row.setHeight((short)(27 * 20));
			
			//导出符合查询条件的全部信息
			List<ViewTranRecordOrg> list = viewTranRecordOrgService.selectByExample(queryModel);
			if (list == null) list = new ArrayList<>();
			//显示合计
			ViewTranRecordOrg sumAmt = viewTranRecordOrgService.getSumAmt(queryModel);

			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();	
			style.setDataFormat(format.getFormat("@")); //文本格式
			
			ViewTranRecordOrg model = null;
			for (int i = 0; i <list.size(); i++) {
				model = list.get(i);
				if(null != model){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,model.getMerchantnumber(),style);
					POIUtils.createCell(row, (short) 2,model.getMrchtName(),style);
					POIUtils.createCell(row, (short) 3,model.getTransactionmoney() == null ? "0" : model.getTransactionmoney().toPlainString(),style);
					POIUtils.createCell(row, (short) 4,model.getTranTotal() == null ? "0" : model.getTranTotal(),style);
					POIUtils.createCell(row, (short) 5,model.getFeeOrder() == null ? "0" : model.getFeeOrder().toPlainString(),style);
					POIUtils.createCell(row, (short) 6,model.getPerfee() == null ? "0" : model.getPerfee().toPlainString(),style);
					POIUtils.createCell(row, (short) 7,model.getFeeRetention() == null ? "0" : model.getFeeRetention().toPlainString(),style);
					POIUtils.createCell(row, (short) 8,model.getAmtRetention() == null ? "0" : model.getAmtRetention().toPlainString(),style);
					POIUtils.createCell(row, (short) 9,model.getAmtShare() == null ? "0" : model.getAmtShare().toPlainString(),style);
				}
			}
			
			//显示合计金额与笔数
			if (null != sumAmt) {
				BigDecimal perfee = sumAmt.getPerfee();	//手续费收取总金额(元)
				if (perfee == null) perfee = new BigDecimal(0);
				perfee = perfee.setScale(2, BigDecimal.ROUND_HALF_UP);

				BigDecimal amtRetention = sumAmt.getAmtRetention();	//手续费留底总金额(元)
				if (amtRetention == null) amtRetention = new BigDecimal(0);
				amtRetention = amtRetention.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				BigDecimal amtShare = sumAmt.getAmtShare();	//分润总金额(元)
				if (amtShare == null) amtShare = new BigDecimal(0);
				amtShare = amtShare.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				String sumView = "手续费收取总金额(元)：" + perfee + 
						"  手续费留底总金额(元)：" + amtRetention + "  分润总金额(元)：" + amtShare;
				int sumRow = startRow + list.size();
				row = sheet.createRow(sumRow);
				row.setHeight((short)(25 * 20));
				sheet.addMergedRegion(new CellRangeAddress(sumRow, sumRow, 0, 9));// 开始行，结束行，开始列，结束列  
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
			log.error("TransactionSplittingController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
	/**
	 * 取后月日期
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
	
}
