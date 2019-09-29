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

import cn.yufu.fs.entity.ClientAccBalance;
import cn.yufu.fs.service.ClientAccBalanceService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/ClientAccBalance")

public class ClientAccBalanceController {
	Log log = Log.getLog(ClientAccBalanceController.class);
	
	@Autowired
	@Qualifier("fs.ClientAccBalanceService")	
	private ClientAccBalanceService ClientAccBalanceService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClientAccBalance queryModel) {
		queryModel.setDailyDate(getNowDt("yyyyMMdd",-1));		
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clientAccBalance/clientAccBalanceList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClientAccBalance queryModel) {
		// 分页设置
		int count = ClientAccBalanceService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClientAccBalance> list = ClientAccBalanceService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("ClientAccBalanceList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);		
		//合计
		String sumCardBal="0";
		String sumWaitStlAmt="0";
		String sumCustTotlAmt="0";
		String sumErrAmt="0";
		if(list!=null&&list.size()>0){
			String sumAmt=ClientAccBalanceService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				sumCardBal=sumAmt.split("#")[0].toString();
				sumWaitStlAmt=sumAmt.split("#")[1].toString();	
				sumCustTotlAmt=sumAmt.split("#")[2].toString();	
				sumErrAmt=sumAmt.split("#")[3].toString();	
			}
		}
		model.addAttribute("sumCardBal", sumCardBal);
		model.addAttribute("sumCustTotlAmt", sumCustTotlAmt);
		model.addAttribute("sumWaitStlAmt", sumWaitStlAmt);
		model.addAttribute("sumErrAmt", sumErrAmt);

		return "modules/fs/clientAccBalance/clientAccBalanceList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClientAccBalance info) {
		List<ClientAccBalance> list = ClientAccBalanceService.queryList(info);
		int listSize=list==null?0:list.size();
		//合计
		String sumCardBal="0";
		String sumWaitStlAmt="0";
		String sumCustTotlAmt="0";
		String sumErrAmt="0";
		if(list!=null&&list.size()>0){
			String sumAmt=ClientAccBalanceService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				sumCardBal=sumAmt.split("#")[0].toString();
				sumWaitStlAmt=sumAmt.split("#")[1].toString();	
				sumCustTotlAmt=sumAmt.split("#")[2].toString();	
				sumErrAmt=sumAmt.split("#")[3].toString();	
			}
		}			
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—备付金报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clientAccBalanceReport.xls"; // excel模板
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

			ClientAccBalance cce = null;	
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getDailyDate()==null||"".equals(cce.getDailyDate())?"":cce.getDailyDate(),style);
					POIUtils.createCell(row, (short) 2,cce.getCardBal()==null||"".equals(cce.getCardBal())?"0":cce.getCardBal().toString(),style);
					POIUtils.createCell(row, (short) 3,cce.getWaitStlAmt()==null||"".equals(cce.getWaitStlAmt())?"0":cce.getWaitStlAmt().toString(),style);
					POIUtils.createCell(row, (short) 4,cce.getErrAmt()==null||"".equals(cce.getErrAmt())?"0":cce.getErrAmt().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getCustTotlAmt()==null||"".equals(cce.getCustTotlAmt())?"0":cce.getCustTotlAmt().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getComments()==null||"".equals(cce.getComments())?"":cce.getComments(),style);
				}
			}
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			POIUtils.createCell(row, (short) 4, "   合计    卡余额："+sumCardBal+"    未结算金额:"+sumWaitStlAmt+"    对账差错金额:"+sumErrAmt+"    客户资金帐户总余额:"+sumCustTotlAmt,null);	
									
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
			log.error("ClientAccBalanceController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
}
