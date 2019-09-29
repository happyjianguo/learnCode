/**
 *包名:cn.yufu.fs.web
 *描述:package cn.yufu.fs.web;
 */
package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
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

import cn.yufu.fs.entity.FukaSalepoint;
import cn.yufu.fs.entity.StatisticsSellCard;
import cn.yufu.fs.service.StatisticsSellCardService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtil;

/**
 * StatisticsSellCardController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:网点售卡统计
 */
@Controller
@RequestMapping(value="/StatisticsSellCard")
public class StatisticsSellCardController {
	
	Log log = Log.getLog(StatisticsSellCardController.class);
	
	@Autowired
	@Qualifier("fs.StatisticsSellCardService")
	private StatisticsSellCardService StatisticsSellCardService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, StatisticsSellCard queryModel) {
		log.debug("售卡网点统计", queryModel);
		queryModel.setStlDate(getNowDt("yyyyMMdd",-1));	
		/**
		 * 下拉框两种展示模式
		 * 1、下拉框选择展示所有售卡网点，格式=分公司编码：分公司名-售卡网点编码：售卡网点名
		 * 2、两个下拉框联动展示，分公司名————售卡网点名
		 */
		List<FukaSalepoint>  sysParamList= StatisticsSellCardService.getFukaSalePoint();
		model.addAttribute("consumpCategoryList",sysParamList);
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/StatisticsSellCard/statisticsSellCardList";
	}
	
	/**
	 * 查询
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize,StatisticsSellCard queryModel){
		// 分页设置flagOnline
		if(StringUtil.isEmpty(queryModel.getEndStlDate()) || StringUtil.isEmpty(queryModel.getStartStlDate())){
			queryModel.setStartStlDate("");
			queryModel.setEndStlDate("");
		}
		//合计备付金总金额和售卡总金额
		StatisticsSellCard statisticsSellCard = StatisticsSellCardService.getSumAmt(queryModel);
		String sale_amt = "0";
		String tran_amt = "0";
		if( null != statisticsSellCard ){
			if(null !=statisticsSellCard.getCardtotalprice()){
				sale_amt=statisticsSellCard.getCardtotalprice().toString();
			}
			if(null !=statisticsSellCard.getProvisions()){
				tran_amt =statisticsSellCard.getProvisions();
			}
		}
		//售卡总金额
		model.addAttribute("sale_amt", sale_amt);
		//备付金剩余总金额
		model.addAttribute("tran_amt",tran_amt);
		List<StatisticsSellCard> lst = StatisticsSellCardService.queryExcelList(queryModel);
		int count = lst.size();
//		int count = StatisticsSellCardService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		//查询数据
		List<StatisticsSellCard> list = StatisticsSellCardService.queryList(queryModel, startResult, endResult);
		/**
		 * 下拉框两种展示模式
		 * 1、下拉框选择展示所有售卡网点，格式=分公司编码：分公司名-售卡网点编码：售卡网点名
		 * 2、两个下拉框联动展示，分公司名————售卡网点名
		 */
		List<FukaSalepoint>  sysParamList= StatisticsSellCardService.getFukaSalePoint();
		model.addAttribute("consumpCategoryList",sysParamList);
		model.addAttribute("StatisticsSellCardList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		/*时间格式yyyy-MM-dd HH:mm(2017-05-19 14:08)转yyyyMMdd(20170507)*/
		if(!(StringUtil.isEmpty(queryModel.getEndStlDate()) || StringUtil.isEmpty(queryModel.getStartStlDate()))){
			try {
				queryModel.setStartStlDate(DateUtils.strToDateFormats(queryModel.getStartStlDate()+":00"));
				queryModel.setEndStlDate(DateUtils.strToDateFormats(queryModel.getEndStlDate()+":00"));
			} catch (ParseException e) {
				queryModel.setStartStlDate("");
				queryModel.setEndStlDate("");
			}
		}
		model.addAttribute("query", queryModel);
		return "modules/fs/StatisticsSellCard/statisticsSellCardList";
	}
	/**
	 * yyyy-MM-dd HH:mm:ss转yyyyMMdd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getFormatTimeString(String date) throws ParseException {
		String pattern = "yyyyMMdd";
		SimpleDateFormat sDateFormat = DateUtil.getDateFormat(pattern);
		Date dates = DateUtil.getDateFromString(date, "yyyy-MM-dd HH:mm:ss");
		// 单实例,SimpleDateFormat非线程安全
		synchronized (sDateFormat) {
			return sDateFormat.format(dates);
		}
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, StatisticsSellCard info){
		//导出Excel查询
		List<StatisticsSellCard> list = StatisticsSellCardService.queryExcelList(info);
		if(null != info){
			try {
				info.setStartStlDate(getFormatTimeString(info.getStartStlDate()));
				info.setEndStlDate(getFormatTimeString(info.getEndStlDate()));
			} catch (ParseException e) {
				info.setStartStlDate("");
				info.setEndStlDate("");
			}
		}
		//合计备付金总金额和售卡总金额
		StatisticsSellCard statisticsSellCard = StatisticsSellCardService.getSumAmt(info);
		String sale_amt = "0";
//		String tran_amt = "0";
		if( null != statisticsSellCard ){
			if(null !=statisticsSellCard.getCardtotalprice()){
				sale_amt=statisticsSellCard.getCardtotalprice().toString();
			}
			if(null !=statisticsSellCard.getProvisions()){
//				tran_amt =statisticsSellCard.getProvisions();
			}
		}
		int listSize=list==null?0:list.size();
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="售卡网点统计初表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：	
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/statisticsSellCardReport.xls"; // excel模板
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

			StatisticsSellCard cce = null;		
			
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					//向Excel对象中填充数据
					POIUtils.createCell(row, (short) 0,cce.getAdminName()==null||"".equals(cce.getAdminName())?"":cce.getAdminName(),style);
					POIUtils.createCell(row, (short) 1,cce.getAdminRealname()==null||"".equals(cce.getAdminRealname())?"":cce.getAdminRealname(),style);
					POIUtils.createCell(row, (short) 2,cce.getPointid()==null?"0":cce.getPointid().toString(),style);
					POIUtils.createCell(row, (short) 3,cce.getPointName()==null||"".equals(cce.getPointName())?"":cce.getPointName(),style);
					POIUtils.createCell(row, (short) 4,cce.getOutcardverifytime().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getCardtotalprice()==null||"".equals(cce.getProvisions())?"0":cce.getCardtotalprice().toString(),style);
					/*POIUtils.createCell(row, (short) 6,cce.getProvisions()==null||"".equals(cce.getProvisions())?"0":cce.getProvisions(),style);*/
				}
			}
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			POIUtils.createCell(row, (short) 4, "   合计    售卡总金额："+sale_amt);	
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
			log.error("StatisticsSellCardController.exportExcel()调用出现异常。");
		}
		
		return null;
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
