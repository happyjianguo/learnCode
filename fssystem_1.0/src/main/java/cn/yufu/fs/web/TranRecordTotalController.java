/**
 *包名:cn.yufu.fs.web
 *描述:package cn.yufu.fs.web;
 */
package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.service.CardkindsofService;
import cn.yufu.bak.service.MerchantXService;
import cn.yufu.bak.service.TermposXService;
import cn.yufu.fs.entity.TranRecordTotal;
import cn.yufu.fs.service.TranRecordTotalService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

/**
 * TranRecordTotalController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年01月26日
 * 描述:交易统计查询
 */
@SuppressWarnings({ "deprecation", "unused" })
@Controller
@RequestMapping(value="/tranRecordTotal")
public class TranRecordTotalController {
	
	//日志
	Log log = Log.getLog(TranRecordTotalController.class);
	
	@Autowired
	@Qualifier("fs.TranRecordTotalService")
	private TranRecordTotalService tranRecordTotalService;
	
	@Autowired
	@Qualifier("bak.CardkindsofService")
	private CardkindsofService cardkindsofService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	@Autowired
	@Qualifier("bak.TermposXService")
	private TermposXService termposXService;
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 */
	@RequestMapping(value = "page")
	public String page(HttpServletRequest req, HttpServletResponse resp, 
			Model model, TranRecordTotal queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/transactionRecords/tranRecordTotalList";
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
	@RequestMapping(value = "list")
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, 
			int curPage, int pageSize, TranRecordTotal queryModel) {
		String isBanlance = queryModel.getIsBanlance();
		Cardkindsof cardkindsof = new Cardkindsof();
		List<Cardkindsof> isBanlanceList = null;
		if (StringUtils.isNotEmpty(isBanlance)) {
			cardkindsof.setStlFlag(isBanlance);//是否结算，
			isBanlanceList = cardkindsofService.getCardTypeList(cardkindsof);
		}
		List<String> cardTypeList = new ArrayList<String>();
		if (isBanlanceList != null) {
			for (Cardkindsof card : isBanlanceList) {
				cardTypeList.add(card.getCardnumber());
			}
		}
		queryModel.setCardTypeList(cardTypeList);
		//获取分页总数
		String queryType = queryModel.getQueryType();
		int count = tranRecordTotalService.queryCount(queryModel);
		
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);	
		model.addAttribute("queryModel", queryModel);
		
		List<TranRecordTotal> list = null;
		if (StringUtils.isNotBlank(queryType)) {
			if ("0".equals(queryType)) { //以商户号为统计条件
				list = tranRecordTotalService.getDataByMrch(queryModel, startResult, endResult);
				getMerchantMsg(list);
				model.addAttribute("tranRecordTotaList", list);
				return "modules/fs/transactionRecords/tranRecordTotalListMrch";
			}else if ("1".equals(queryType)) { //以终端号为统计条件
				list = tranRecordTotalService.getDataByTerm(queryModel, startResult, endResult);
				getTerminalLoc(list);
				model.addAttribute("tranRecordTotaList", list);
				return "modules/fs/transactionRecords/tranRecordTotalListTerm";
			}else if ("2".equals(queryType)) { //以商户终端为统计条件
				list = tranRecordTotalService.getDataByMrchAndTerm(queryModel, startResult, endResult);
				getMerchantMsgAndTerLoc(list);
				model.addAttribute("tranRecordTotaList", list);
				return "modules/fs/transactionRecords/tranRecordTotalListMrchAndTerm";
			}else if ("3".equals(queryType)) { //以卡号为统计条件
				list = tranRecordTotalService.getDataByPan(queryModel, startResult, endResult);
				getMerchantMsg(list);
				model.addAttribute("tranRecordTotaList", list);
				return "modules/fs/transactionRecords/tranRecordTotalListPan";
			}else if ("4".equals(queryType)) { //以卡类型为统计条件
				list = tranRecordTotalService.getDataByCardType(queryModel, startResult, endResult);
				cardkindsof = new Cardkindsof();
				//得到卡类型码表
				List<Cardkindsof> cardInfo = cardkindsofService.getDicCardTypeList(cardkindsof);
				model.addAttribute("cardTypeList", cardInfo);
				
				model.addAttribute("tranRecordTotaList", list);
				return "modules/fs/transactionRecords/tranRecordTotalListCardType";
			}
		}
		return "modules/fs/transactionRecords/tranRecordTotalList";
	}
	
	//匹配商户名称 与 终端号
	private void getMerchantMsgAndTerLoc(List<TranRecordTotal> list) {
		if (list == null) list = new ArrayList<TranRecordTotal>();
		//存商户号与商户名称的键值对
		Map<String, String> merchantMap = new HashMap<String, String>();
		//存终端号与终端位置的键值对
		Map<String, String> terminalMap = new HashMap<String, String>();
		
		String merchantnumber = "";
		List<String> mrchtName = null;
		String terminalNo = "";
		List<String> terminalLocation = null;
		for (TranRecordTotal tranRecordTotal : list) {
			//匹配商户名
			merchantnumber = tranRecordTotal.getMerchantnumber();
			if (merchantMap.containsKey(merchantnumber)) {
				//已存入
				tranRecordTotal.setMerchantName(merchantMap.get(merchantnumber));
			}else{
				mrchtName = merchantXService.getMrchtName(merchantnumber);
				if (mrchtName != null) {
					tranRecordTotal.setMerchantName(mrchtName.get(0));
					merchantMap.put(merchantnumber, mrchtName.get(0));
				}else{
					tranRecordTotal.setMerchantName("");
					merchantMap.put(merchantnumber, "");
				}
			}
			//匹配终端号
			terminalNo = tranRecordTotal.getTerminalnumber();
			if (terminalMap.containsKey(terminalNo)) {
				//已存入
				tranRecordTotal.setTerminallocation(terminalMap.get(terminalNo));
			}else{
				terminalLocation = termposXService.getTerminalLoc(terminalNo);
				if (terminalLocation != null) {
					tranRecordTotal.setTerminallocation(terminalLocation.get(0));
					terminalMap.put(terminalNo, terminalLocation.get(0));
				}else{
					tranRecordTotal.setTerminallocation("");
					terminalMap.put(terminalNo, "");
				}
			}
		}
	}
	
	//匹配商户名称
	private void getMerchantMsg(List<TranRecordTotal> list) {
		if (list == null) list = new ArrayList<TranRecordTotal>();
		//存商户号与商户名称的键值对
		Map<String, String> merchantMap = new HashMap<String, String>();
		String merchantnumber = "";
		List<String> mrchtName = null;
		for (TranRecordTotal tranRecordTotal : list) {
			merchantnumber = tranRecordTotal.getMerchantnumber();
			if (merchantMap.containsKey(merchantnumber)) {
				//已存入
				tranRecordTotal.setMerchantName(merchantMap.get(merchantnumber));
			}else{
				mrchtName = merchantXService.getMrchtName(merchantnumber);
				if (mrchtName != null) {
					tranRecordTotal.setMerchantName(mrchtName.get(0));
					merchantMap.put(merchantnumber, mrchtName.get(0));
				}else{
					tranRecordTotal.setMerchantName("");
					merchantMap.put(merchantnumber, "");
				}
			}
		}
	}
	
	//匹配终端位置
	private void getTerminalLoc(List<TranRecordTotal> list) {
		if (list == null) list = new ArrayList<TranRecordTotal>();
		//存终端号与终端位置的键值对
		Map<String, String> terminalMap = new HashMap<String, String>();
		String terminalNo = "";
		List<String> terminalLocation = null;
		for (TranRecordTotal tranRecordTotal : list) {
			terminalNo = tranRecordTotal.getTerminalnumber();
			if (terminalMap.containsKey(terminalNo)) {
				//已存入
				tranRecordTotal.setTerminallocation(terminalMap.get(terminalNo));
			}else{
				terminalLocation = termposXService.getTerminalLoc(terminalNo);
				if (terminalLocation != null) {
					tranRecordTotal.setTerminallocation(terminalLocation.get(0));
					terminalMap.put(terminalNo, terminalLocation.get(0));
				}else{
					tranRecordTotal.setTerminallocation("");
					terminalMap.put(terminalNo, "");
				}
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "getCardType")
	public List<Cardkindsof> getCardType(Cardkindsof cardkindsof){
		//得到卡类型码表
		List<Cardkindsof> cardTypeList = cardkindsofService.getDicCardTypeList(cardkindsof);
		if (cardTypeList == null) cardTypeList = new ArrayList<Cardkindsof>();
		return cardTypeList;
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
	@RequestMapping(value = "exportExcel")
	@ResponseBody
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, 
			TranRecordTotal queryModel) {
		List<TranRecordTotal> list = null;
		String path = "";
		//导出符合查询条件的全部信息
		String queryType = queryModel.getQueryType();
		String isBanlance = queryModel.getIsBanlance();
		Cardkindsof cardkindsof = new Cardkindsof();
		List<Cardkindsof> isBanlanceList = null;
		if (StringUtils.isNotEmpty(isBanlance)) {
			cardkindsof.setStlFlag(isBanlance);//是否结算，
			isBanlanceList = cardkindsofService.getCardTypeList(cardkindsof);
		}
		List<String> cardTypeList = new ArrayList<String>();
		if (isBanlanceList != null) {
			for (Cardkindsof card : isBanlanceList) {
				cardTypeList.add(card.getCardnumber());
			}
		}
		queryModel.setCardTypeList(cardTypeList);
		
		if (cardTypeList != null) {
			if (StringUtils.isNotBlank(queryType)) {
				if ("0".equals(queryType)) { //以商户号为统计条件
					list = tranRecordTotalService.getExcelDataByMrch(queryModel);
					path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tranRecordTotalMrch.xls"; // excel模板
					getMerchantMsg(list);
				}else if ("1".equals(queryType)) { //以终端号为统计条件
					list = tranRecordTotalService.getExcelDataByTerm(queryModel);
					getTerminalLoc(list);
					path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tranRecordTotalTerm.xls"; // excel模板
				}else if ("2".equals(queryType)) { //以商户终端为统计条件
					list = tranRecordTotalService.getExcelDataByMrchAndTerm(queryModel);
					path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tranRecordTotalMrchAndTerm.xls"; // excel模板
					getMerchantMsgAndTerLoc(list);
				}else if ("3".equals(queryType)) { //以卡号为统计条件
					list = tranRecordTotalService.getExcelDataByPan(queryModel);
					path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tranRecordTotalPan.xls"; // excel模板
					getMerchantMsg(list);
				}else if ("4".equals(queryType)) { //以卡类型为统计条件
					list = tranRecordTotalService.getExcelDataByCardType(queryModel);
					path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tranRecordTotalCardType.xls"; // excel模板
				}
			}
		}else{
			list = new ArrayList<TranRecordTotal>();
		}
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="交易流水统计信息 (" + this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
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
			TranRecordTotal model = null;
			for (int j = 0; j <list.size(); j++) {
				model = list.get(j);
				if(model != null){
					row = sheet.createRow(startRow+j);
					row.setHeight((short)(25 * 20));
					if (StringUtils.isNotBlank(queryType)) {
						if ("0".equals(queryType)) { //以商户号为统计条件
							getPOIRowDataByMrch(style, j+1, row, model);
						}else if ("1".equals(queryType)) { //以终端号为统计条件
							getPOIRowDataByTerm(style, j+1, row, model);
						}else if ("2".equals(queryType)) { //以商户终端为统计条件
							getPOIRowDataByMrchAndTerm(style, j+1, row, model);
						}else if ("3".equals(queryType)) { //以卡号为统计条件
							getPOIRowDataByPan(style, j+1, row, model);
						}else if ("4".equals(queryType)) { //以卡类型为统计条件
							getPOIRowDataByCardType(style, j+1, row, model);
						}
					}
				}
			}
			
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			work.write(os);
			if(in != null){
				in.close();
			}
			os.close();
		}catch (Exception e) {
			e.printStackTrace();
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TranRecordTotalController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
	//封装数据
	private void getPOIRowDataByMrch(HSSFCellStyle style, int startResult, HSSFRow row,
			TranRecordTotal model) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) 5,model.getPerfee()==null?"0":model.getPerfee().toPlainString(),style);
		POIUtils.createCell(row, (short) 4,model.getTransactionCount()==null?"0":model.getTransactionCount().toPlainString(),style);
		POIUtils.createCell(row, (short) 3,model.getTransactionmoney()==null?"0":model.getTransactionmoney().toPlainString(),style);
		POIUtils.createCell(row, (short) 2,model.getMerchantName()==null||"".equals(model.getMerchantName())?"":model.getMerchantName(),style);
		POIUtils.createCell(row, (short) 1,model.getMerchantnumber()==null||"".equals(model.getMerchantnumber())?"":model.getMerchantnumber(),style);
		POIUtils.createCell(row, (short) 0,(startResult)+"",style);
	}
	
	//封装数据
	private void getPOIRowDataByTerm(HSSFCellStyle style, int startResult, HSSFRow row,
			TranRecordTotal model) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) 5,model.getPerfee()==null?"0":model.getPerfee().toPlainString(),style);
		POIUtils.createCell(row, (short) 4,model.getTransactionCount()==null?"0":model.getTransactionCount().toPlainString(),style);
		POIUtils.createCell(row, (short) 3,model.getTransactionmoney()==null?"0":model.getTransactionmoney().toPlainString(),style);
		POIUtils.createCell(row, (short) 2,model.getTerminallocation()==null||"".equals(model.getTerminallocation())?"":model.getTerminallocation(),style);
		POIUtils.createCell(row, (short) 1,model.getTerminalnumber()==null||"".equals(model.getTerminalnumber())?"":model.getTerminalnumber(),style);
		POIUtils.createCell(row, (short) 0,(startResult)+"",style);
	}
	
	//封装数据
	private void getPOIRowDataByMrchAndTerm(HSSFCellStyle style, int startResult, HSSFRow row,
			TranRecordTotal model) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) 7,model.getPerfee()==null?"0":model.getPerfee().toPlainString(),style);
		POIUtils.createCell(row, (short) 6,model.getTransactionCount()==null?"0":model.getTransactionCount().toPlainString(),style);
		POIUtils.createCell(row, (short) 5,model.getTransactionmoney()==null?"0":model.getTransactionmoney().toPlainString(),style);
		POIUtils.createCell(row, (short) 4,model.getTerminallocation()==null||"".equals(model.getTerminallocation())?"":model.getTerminallocation(),style);
		POIUtils.createCell(row, (short) 3,model.getTerminalnumber()==null||"".equals(model.getTerminalnumber())?"":model.getTerminalnumber(),style);
		POIUtils.createCell(row, (short) 2,model.getMerchantName()==null||"".equals(model.getMerchantName())?"":model.getMerchantName(),style);
		POIUtils.createCell(row, (short) 1,model.getMerchantnumber()==null||"".equals(model.getMerchantnumber())?"":model.getMerchantnumber(),style);
		POIUtils.createCell(row, (short) 0,(startResult)+"",style);
	}
	
	//封装数据
	private void getPOIRowDataByPan(HSSFCellStyle style, int startResult, HSSFRow row,
			TranRecordTotal model) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) 5,model.getTransactionCount()==null?"0":model.getTransactionCount().toPlainString(),style);
		POIUtils.createCell(row, (short) 4,model.getTransactionmoney()==null?"0":model.getTransactionmoney().toPlainString(),style);
		POIUtils.createCell(row, (short) 3,model.getCardnumber()==null||"".equals(model.getCardnumber())?"":model.getCardnumber(),style);
		POIUtils.createCell(row, (short) 2,model.getMerchantName()==null||"".equals(model.getMerchantName())?"":model.getMerchantName(),style);
		POIUtils.createCell(row, (short) 1,model.getMerchantnumber()==null||"".equals(model.getMerchantnumber())?"":model.getMerchantnumber(),style);
		POIUtils.createCell(row, (short) 0,(startResult)+"",style);
	}
	
	//封装数据
	private void getPOIRowDataByCardType(HSSFCellStyle style, int startResult, HSSFRow row,
			TranRecordTotal model) {
		//倒叙填充单元格数据
		POIUtils.createCell(row, (short) 4,model.getTransactionCount()==null?"0":model.getTransactionCount().toPlainString(),style);
		POIUtils.createCell(row, (short) 3,model.getTransactionmoney()==null?"0":model.getTransactionmoney().toPlainString(),style);
		POIUtils.createCell(row, (short) 2,model.getCardtypename()==null||"".equals(model.getCardtypename())?"":model.getCardtypename(),style);
		POIUtils.createCell(row, (short) 1,model.getCardtype()==null?"":model.getCardtype().toString(),style);
		POIUtils.createCell(row, (short) 0,(startResult)+"",style);
	}
	
}
