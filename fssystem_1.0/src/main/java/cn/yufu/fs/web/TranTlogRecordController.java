/**
 *包名:cn.yufu.fs.web
 *描述:package cn.yufu.fs.web;
 */
package cn.yufu.fs.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

/**
 * TranRecordTotalController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年04月16日
 * 描述:非实时新老卡交易流水查询
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value="/tranTlogRecord")
public class TranTlogRecordController {
	
	//日志
	Log log = Log.getLog(TranTlogRecordController.class);
	
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
		//得到卡类型码表 (不区分库，所以是所有库去重之后的码表)
		List<Cardkindsof> cardTypeList = cardkindsofService.getDicCardTypeList(null);
		model.addAttribute("cardTypeList", cardTypeList);
				
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/transactionRecords/tranTlogRecordList";
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
		Cardkindsof cardkindsof = new Cardkindsof();
		//得到卡类型码表 (不区分库，所以是所有库去重之后的码表)
		List<Cardkindsof> cardTypeList = cardkindsofService.getDicCardTypeList(cardkindsof);
		model.addAttribute("cardTypeList", cardTypeList);
		
		//获取分页总数
		int count = tranRecordTotalService.queryTlogCount(queryModel);
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
		
		List<TranRecordTotal> list = tranRecordTotalService.queryTlogList(queryModel, startResult, endResult);
		//得到合计金额值
		String sumAmt = tranRecordTotalService.getSumAmt(queryModel);
		if (null != sumAmt) {
			model.addAttribute("sumAmt", sumAmt);
		}else{
			model.addAttribute("sumAmt", "0");
		}
		getMerchantMsgAndTerLoc(list);
		model.addAttribute("tranTlogRecordList", list);
		return "modules/fs/transactionRecords/tranTlogRecordList";
			
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
	
	//匹配商户名称 与 终端号
	private void getMercMsgAndTerLoc(TranRecordTotal model, Map<String, String> merchantMap,
			Map<String, String> terminalMap) {
		if (model == null) return;
		
		String merchantnumber = "";
		List<String> mrchtName = null;
		String terminalNo = "";
		List<String> terminalLocation = null;
		//匹配商户名
		merchantnumber = model.getMerchantnumber();
		if (merchantMap.containsKey(merchantnumber)) {
			//已存入
			model.setMerchantName(merchantMap.get(merchantnumber));
		}else{
			mrchtName = merchantXService.getMrchtName(merchantnumber);
			if (mrchtName != null) {
				model.setMerchantName(mrchtName.get(0));
				merchantMap.put(merchantnumber, mrchtName.get(0));
			}else{
				model.setMerchantName("");
				merchantMap.put(merchantnumber, "");
			}
		}
		//匹配终端号
		terminalNo = model.getTerminalnumber();
		if (terminalMap.containsKey(terminalNo)) {
			//已存入
			model.setTerminallocation(terminalMap.get(terminalNo));
		}else{
			terminalLocation = termposXService.getTerminalLoc(terminalNo);
			if (terminalLocation != null) {
				model.setTerminallocation(terminalLocation.get(0));
				terminalMap.put(terminalNo, terminalLocation.get(0));
			}else{
				model.setTerminallocation("");
				terminalMap.put(terminalNo, "");
			}
		}
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
			TranRecordTotal info) {
		//存商户号与商户名称的键值对
		Map<String, String> merchantMap = new HashMap<String, String>();
		//存终端号与终端位置的键值对
		Map<String, String> terminalMap = new HashMap<String, String>();
		
		try{
			//导出符合查询条件的全部信息
			List<TranRecordTotal> list = tranRecordTotalService.queryTlogExcel(info);
			
			String titleName="非实时新老卡交易流水报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
            String[] headers = {"序号","商户号","商户名称","卡号","终端号","终端位置","交易金额",
            		"交易日期","交易时间","流水号","批次号","参考号", "手续费", "交易类型"};
            
            ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "非实时交易流水", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
            
			//得到合计金额值
			String sumAmt = tranRecordTotalService.getSumAmt(info);
			
			Row row = null;
			int count = 0;
			int headrLen = 0;
			for (TranRecordTotal model : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				getMercMsgAndTerLoc(model, merchantMap, terminalMap);
				
				//倒叙填充单元格数据
				exportExcel.addCell(row, --headrLen, null == model.getTransactiontype()?"":model.getTransactiontype().trim());
				exportExcel.addCell(row, --headrLen, null == model.getPerfee()?"0":model.getPerfee().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getReferencenumber()?"":model.getReferencenumber().trim());
				exportExcel.addCell(row, --headrLen, null == model.getLotno()?"":model.getLotno().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getSerialnumber()?"":model.getSerialnumber().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getTransactiontime()?"":model.getTransactiontime().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTransactiondate()?"":model.getTransactiondate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTransactionmoney()?"0":model.getTransactionmoney().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getTerminallocation()?"":model.getTerminallocation().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTerminalnumber()?"":model.getTerminalnumber());
				exportExcel.addCell(row, --headrLen, null == model.getCardnumber()?"":model.getCardnumber().trim());
				exportExcel.addCell(row, --headrLen, null == model.getMerchantName()?"":model.getMerchantName().trim());
				exportExcel.addCell(row, --headrLen, null == model.getMerchantnumber()?"":model.getMerchantnumber().trim());
				exportExcel.addCell(row, --headrLen, ++count + "");
			}
			
			Sheet sheet = exportExcel.getSheet();
			row = exportExcel.addRow();
			int rownum = exportExcel.getRownum();
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, headers.length - 1));
			exportExcel.addCell(row, (int)(headers.length/2), "交易金额合计：   " + sumAmt + "  元");
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
