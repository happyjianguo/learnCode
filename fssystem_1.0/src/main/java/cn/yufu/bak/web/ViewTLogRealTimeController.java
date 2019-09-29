package cn.yufu.bak.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.entity.ViewTLogRealTime;
import cn.yufu.bak.service.CardkindsofService;
import cn.yufu.bak.service.MerchantXService;
import cn.yufu.bak.service.ViewTLogRealTimeService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

/**
 * 实时交易流水
 * @author ZQK 2018/11/01
 *
 */
@Controller
@RequestMapping(value="/viewTLogRealTime")
public class ViewTLogRealTimeController {
	
	Log log = Log.getLog(ViewTLogRealTimeController.class);
	
	@Autowired
	@Qualifier("bak.ViewTLogRealTimeService")
	private ViewTLogRealTimeService viewTLogRealTimeService;
	
	@Autowired
	@Qualifier("bak.CardkindsofService")
	private CardkindsofService cardkindsofService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	/**
	 * 加载初始页(实名认证信息)
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param queryModel
	 * @return
	 */
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, ViewTLogRealTime queryModel) {
		Cardkindsof cardkindsof = new Cardkindsof();
		cardkindsof.setDataBaseType("3"); //Cortex库
		//得到卡类型码表
		List<Cardkindsof> cardTypeList = cardkindsofService.getCardTypeList(cardkindsof);
		model.addAttribute("cardTypeList", cardTypeList);
		
		queryModel.setBegainTrueName(getNowDt("yyyyMMdd", 0));
		queryModel.setEndTrueName(getNowDt("yyyyMMdd", 0));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		return "modules/cortexs/viewTLogRealTimeList";
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
			Model model, int curPage, int pageSize, ViewTLogRealTime queryModel) {
		//存商户号与商户名称的键值对
		Cardkindsof cardkindsof = new Cardkindsof();
		cardkindsof.setDataBaseType("3"); //Cortex库
		//得到卡类型码表
		List<Cardkindsof> cardTypeList = cardkindsofService.getCardTypeList(cardkindsof);
		model.addAttribute("cardTypeList", cardTypeList);

		// 分页设置
		int counts = viewTLogRealTimeService.queryCount(queryModel);
		Page page = new Page(curPage, pageSize, counts);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取交易流水分页数据
		List<ViewTLogRealTime> list = viewTLogRealTimeService.getPageList(queryModel, startResult, endResult);
		//得到合计金额值
		String sumAmt = viewTLogRealTimeService.getSumAmt(queryModel);
		if (sumAmt != null) {
			model.addAttribute("sumAmt", sumAmt);
		}else{
			model.addAttribute("sumAmt", "0.00");
		}
		
		if (list == null || list.size() == 0) list = new ArrayList<>();
		
		model.addAttribute("viewTLogRealTimeList", list);
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		return "modules/cortexs/viewTLogRealTimeList";
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
	 * 取前一月日期
	 * @param pattern
	 * @return
	 */
	public String getMonthDay(String pattern, int flag) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, flag); // 得到前一月
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
	public String exportExcel(HttpServletRequest req, HttpServletResponse resp, 
			ViewTLogRealTime info) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			//导出符合查询条件的全部信息
			List<ViewTLogRealTime> list = viewTLogRealTimeService.getAllList(info);
			String titleName="实时交易流水报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			String[] headers = {"序号", "商户号", "商户名称", "卡号", "终端号", "终端位置", 
					"支付清算号", "原支付清算号", "交易金额", "交易日期", "交易时间", "流水号",
					"批次号", "参考号", "手续费", "交易类型"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "实时交易流水", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
			//得到合计金额值
			String sumAmt = viewTLogRealTimeService.getSumAmt(info);
			
			Row row = null;
			int count = 0;
			int headrLen = 0;
			for (ViewTLogRealTime model : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				
				//倒叙填充单元格数据
				exportExcel.addCell(row, --headrLen, null == model.getTranTypeDesc()?"":model.getTranTypeDesc().trim());
				exportExcel.addCell(row, --headrLen, null == model.getAmttxn()?"0":model.getAmttxn().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getReferencenumber()?"":model.getReferencenumber().trim());
				exportExcel.addCell(row, --headrLen, null == model.getLotno()?"":model.getLotno().trim());
				exportExcel.addCell(row, --headrLen, null == model.getSerialnumber()?"":model.getSerialnumber().toString());
				exportExcel.addCell(row, --headrLen, null == model.getTransactiontime()?"":model.getTransactiontime().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTransactiondate()?"":model.getTransactiondate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTransactionmoney()?"0":model.getTransactionmoney().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getOrgOrderId()?"":model.getOrgOrderId().trim());
				exportExcel.addCell(row, --headrLen, null == model.getOrderId()?"":model.getOrderId().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTerminallocation()?"":model.getTerminallocation().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTerminalnumber()?"":model.getTerminalnumber().trim());
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
			log.error("ViewTLogRealTimeController.exportExcel()调用出现异常。");
		}
		return null;
	}

}
