package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import cn.yufu.fs.entity.WankeInterestAccrual;
import cn.yufu.fs.service.WankeInterestAccrualService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/WankeInterestAccrual")
public class WankeInterestAccrualController {
	Log log = Log.getLog(WankeInterestAccrualController.class);
	
	@Autowired
	@Qualifier("fs.WankeInterestAccrualService")	
	private WankeInterestAccrualService wankeInterestAccrualService;
	
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
			WankeInterestAccrual queryModel) {
		queryModel.setBegainTransactiondate(getMonthDay("yyyyMMdd", -1));
		queryModel.setEndTransactiondate(getNowDt("yyyyMMdd", 0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/crdfeeManager/wankeInterestAccrualList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, 
			Model model, int curPage, int pageSize, WankeInterestAccrual queryModel) {
		try{
			// 分页设置
			int count = wankeInterestAccrualService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<WankeInterestAccrual> list = wankeInterestAccrualService.selectPageByExample(queryModel, startResult, endResult);
			
			model.addAttribute("wankeInterestAccrualList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("queryModel", queryModel);
	
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("WankeInterestAccrualController.getList()调用出现异常。");
			e.printStackTrace();
		}
		
		return "modules/fs/crdfeeManager/wankeInterestAccrualList";
	}
	
	@RequestMapping(value = "show")
	public String show(String id, Model model) {
		model.addAttribute("wankeInterestAccrual", wankeInterestAccrualService.selectByPrimaryKey(id));
		return "modules/fs/crdfeeManager/wankeInterestAccrualShow";
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
			Model model, WankeInterestAccrual info) {
		//导出符合查询条件的全部信息
		List<WankeInterestAccrual> list = wankeInterestAccrualService.selectByExample(info);
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="计息表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/wankeInterestAccrual.xls"; // excel模板
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
			WankeInterestAccrual wankeInterestAccrual = null;
			for (int i = 0; i <list.size(); i++) {
				wankeInterestAccrual = list.get(i);
				if(wankeInterestAccrual!=null){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,wankeInterestAccrual.getTransactiondate()==null?"0":wankeInterestAccrual.getTransactiondate().toString(),style);
					POIUtils.createCell(row, (short) 2,wankeInterestAccrual.getCardaccount()==null?"0":wankeInterestAccrual.getCardaccount().toString(),style);
					POIUtils.createCell(row, (short) 3,wankeInterestAccrual.getStockFund()==null?"0":wankeInterestAccrual.getStockFund().toString(),style);
					POIUtils.createCell(row, (short) 4,wankeInterestAccrual.getRechargeAmt()==null?"0":wankeInterestAccrual.getRechargeAmt().toString(),style);
					POIUtils.createCell(row, (short) 5,wankeInterestAccrual.getFixedDeposit()==null?"0":wankeInterestAccrual.getFixedDeposit().toString(),style);
					POIUtils.createCell(row, (short) 6,wankeInterestAccrual.getNetAmount()==null?"0":wankeInterestAccrual.getNetAmount().toString(),style);
					POIUtils.createCell(row, (short) 7,wankeInterestAccrual.getLittleAmount()==null?"0":wankeInterestAccrual.getLittleAmount().toString(),style);
					POIUtils.createCell(row, (short) 8,wankeInterestAccrual.getCurrentInterestBase()==null?"0":wankeInterestAccrual.getCurrentInterestBase().toString(),style);
					POIUtils.createCell(row, (short) 9,wankeInterestAccrual.getAgreementDepositBase()==null?"0":wankeInterestAccrual.getAgreementDepositBase().toString(),style);
					POIUtils.createCell(row, (short) 10,wankeInterestAccrual.getAgreementCurrentInterest()==null?"0":wankeInterestAccrual.getAgreementCurrentInterest().toString(),style);
					POIUtils.createCell(row, (short) 11,wankeInterestAccrual.getAgreementDepositInterest()==null?"0":wankeInterestAccrual.getAgreementDepositInterest().toString(),style);
					POIUtils.createCell(row, (short) 12,wankeInterestAccrual.getGrossInterest()==null?"0":wankeInterestAccrual.getGrossInterest().toString(),style);
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
			e.printStackTrace();
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("WankeInterestAccrualController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
}
