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

import cn.yufu.bak.service.MerchantXService;
import cn.yufu.fs.entity.CardTypeBook;
import cn.yufu.fs.entity.TransactionSplitting;
import cn.yufu.fs.service.CardTypeBookService;
import cn.yufu.fs.service.TransactionSplittingService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.cortexs.entity.Crdfeerule;
import cn.yufu.system.modules.cortexs.service.CrdfeeruleService;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/TransactionSplitting")
public class TransactionSplittingController {
	Log log = Log.getLog(TransactionSplittingController.class);
	
	@Autowired
	@Qualifier("fs.TransactionSplittingService")	
	private TransactionSplittingService transactionSplittingService;
	
	@Autowired
	@Qualifier("fs.CardTypeBookService")	
	private CardTypeBookService cardTypeBookService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	@Autowired
	private CrdfeeruleService crdfeeruleService;
	
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
				TransactionSplitting queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		//卡描述
		List<Crdfeerule> crdfeeruleList = crdfeeruleService.getDateByIID(new Crdfeerule());
		model.addAttribute("crdfeeruleList", crdfeeruleList);
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/shareBenefitReport/transactionSplittingList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, TransactionSplitting queryModel) {
		try{
			// 分页设置
			int count = transactionSplittingService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<TransactionSplitting> list = transactionSplittingService.queryList(queryModel, startResult, endResult);
			TransactionSplitting sumAmt = transactionSplittingService.getSumAmt(queryModel);
			if (list == null || list.size() == 0) {
				list = new ArrayList<>();
			}
			//匹配分润比例（卡类型码表）
			for (TransactionSplitting transactionSplitting : list) {
				if (StringUtils.isNotBlank(transactionSplitting.getCardBin())) {
					CardTypeBook cardTypeBook = cardTypeBookService.selectByPrimaryKey(transactionSplitting.getCardBin());
					if (cardTypeBook != null) {
						transactionSplitting.setFeeOrderCard(cardTypeBook.getFeeOrder());
					}
				}
				//匹配商户名称
				if (StringUtils.isNotBlank(transactionSplitting.getMerchantnumber())) {
					List<String> mrchtName = merchantXService.getMrchtName(transactionSplitting.getMerchantnumber());
					if (mrchtName != null && mrchtName.size() > 0) {
						transactionSplitting.setMerchantName(mrchtName.get(0));
					}
				}
			}
			//显示合计金额
			if (sumAmt != null) {
				BigDecimal transactionmoney = sumAmt.getTransactionmoney();	//消费金额(元)
				if (transactionmoney == null) transactionmoney = new BigDecimal(0);
				transactionmoney = transactionmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
				String comments = sumAmt.getComments();	//笔数
				if (comments == null) comments = "0";
				BigDecimal perfee = sumAmt.getPerfee();	//手续费(元)
				if (perfee == null) perfee = new BigDecimal(0);
				perfee = perfee.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal splitAmt = sumAmt.getSplitAmt();	//分润金额(元)
				if (splitAmt == null) splitAmt = new BigDecimal(0);
				splitAmt = splitAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				model.addAttribute("sumTransactionmoney", transactionmoney);
				model.addAttribute("sumComments", comments);
				model.addAttribute("sumPerfee", perfee);
				model.addAttribute("sumSplitAmt", splitAmt);
			}
			List<Crdfeerule> crdfeeruleList = crdfeeruleService.getDateByIID(new Crdfeerule());
			model.addAttribute("crdfeeruleList", crdfeeruleList);
			
			model.addAttribute("transactionSplittingList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("queryModel", queryModel);
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TransactionSplittingController.getList()调用出现异常。");
			e.printStackTrace();
		}
		
		return "modules/fs/shareBenefitReport/transactionSplittingList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, TransactionSplitting queryModel) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="卡类型分润报表("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/transactionSplitting.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 5, headName,style);
			row.setHeight((short)(27 * 20));
			
			//导出符合查询条件的全部信息
			List<TransactionSplitting> list = transactionSplittingService.queryList(queryModel);
			if (list == null) list = new ArrayList<>();
			//显示合计
			TransactionSplitting sumAmt = transactionSplittingService.getSumAmt(queryModel);

			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();	
			TransactionSplitting cce = null;
			String paymentDay = "";
			String feeOrderCard = "";
			for (int i = 0; i <list.size(); i++) {
				cce = list.get(i);
				paymentDay = "";
				if(cce!=null){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerchantnumber(),style);
					//匹配商户名称
					if (StringUtils.isNotBlank(cce.getMerchantnumber())) {
						List<String> mrchtName = merchantXService.getMrchtName(cce.getMerchantnumber());
						if (mrchtName != null && mrchtName.size() > 0) {
							cce.setMerchantName(mrchtName.get(0));
						}
					}
					POIUtils.createCell(row, (short) 2,cce.getMerchantName()==null||"".equals(cce.getMerchantName())?"":cce.getMerchantName(),style);
					POIUtils.createCell(row, (short) 3,cce.getCardBin()==null||"".equals(cce.getCardBin())?"":cce.getCardBin(),style);
					paymentDay = cce.getMinPaymentDay() + "-" + cce.getMaxPaymentDay();
					POIUtils.createCell(row, (short) 4,paymentDay,style);
					POIUtils.createCell(row, (short) 5,cce.getTransactionmoney()==null?"0":cce.getTransactionmoney().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getComments()==null?"":cce.getComments(),style);
					POIUtils.createCell(row, (short) 7,cce.getPerfee()==null?"0":cce.getPerfee().toString(),style);
					//匹配分润比例（卡类型码表）
					CardTypeBook cardTypeBook = cardTypeBookService.selectByPrimaryKey(cce.getCardBin());
					if (cardTypeBook != null) {
						BigDecimal feeOrder = cardTypeBook.getFeeOrder();
						if (feeOrder != null) {
							feeOrder = feeOrder.multiply(new BigDecimal(100));
							feeOrderCard = feeOrder.toString() + "%";
						}
					}
					POIUtils.createCell(row, (short) 8,feeOrderCard==null ||"".equals(feeOrderCard) ?"0":feeOrderCard,style);
					POIUtils.createCell(row, (short) 9,cce.getSplitAmt()==null?"0":cce.getSplitAmt().toString(),style);
				}
			}
			//显示合计金额与笔数
			if (sumAmt != null) {
				BigDecimal transactionmoney = sumAmt.getTransactionmoney();	//消费金额(元)
				if (transactionmoney == null) transactionmoney = new BigDecimal(0);
				transactionmoney = transactionmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
				String comments = sumAmt.getComments();	//笔数
				if (comments == null) comments = "0";
				BigDecimal perfee = sumAmt.getPerfee();	//手续费(元)
				if (perfee == null) perfee = new BigDecimal(0);
				perfee = perfee.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal splitAmt = sumAmt.getSplitAmt();	//分润金额(元)
				if (splitAmt == null) splitAmt = new BigDecimal(0);
				splitAmt = splitAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				String sumView = "消费金额(元)：" + transactionmoney + "  笔数："
						+ comments + "  手续费(元)：" + perfee + "  分润金额(元)：" + splitAmt;
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
	
}
