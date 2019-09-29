package cn.yufu.bak.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.bak.entity.StatDailyAccde;
import cn.yufu.bak.service.StatDailyAccdeService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

@Controller
@RequestMapping(value = "/StatDailyAccde")
public class StatDailyAccdeController {
	Log log = Log.getLog(StatDailyAccdeController.class);
	
	@Autowired
	@Qualifier("bak.StatDailyAccdeService")	
	private StatDailyAccdeService statDailyAccdeService;
	
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
				StatDailyAccde queryModel) {
		queryModel.setStartDate(getMonthDay("yyyyMMdd", -1));
		queryModel.setEndDate(getNowDt("yyyyMMdd", 0));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/statDailyAccde/statDailyAccdeList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, 
			int curPage, int pageSize, StatDailyAccde queryModel) {
		try{
			// 分页设置
			int count = statDailyAccdeService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<StatDailyAccde> list = statDailyAccdeService.selectPageByExample(queryModel, startResult, endResult);
			StatDailyAccde sumAvlBal = statDailyAccdeService.selectSumAvlBal(queryModel);
			
			model.addAttribute("statDailyAccdeList", list);
			model.addAttribute("sumAvlBal", sumAvlBal);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("StatDailyAccdeController.getList()调用出现异常。");
		}
		
		return "modules/cortexs/statDailyAccde/statDailyAccdeList";
	}

	/**
	 * 导出银联EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest req, HttpServletResponse resp, StatDailyAccde info) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			//银联交易信息
			List<StatDailyAccde> list = statDailyAccdeService.selectByExample(info);
			String titleName = "卡余额信息 (" + this.getNowDt("yyyyMMddHHmmss",0) + ")";
			String[] headers = {"统计日期","卡BIN","卡产品","卡张数","卡账户余额(元)",
						"实名账户余额(元)","积分账户余额(元)","联名卡积分账户(元)","账户总余额(元)"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "卡余额", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
			
			Row row = null;
			int headrLen = 0;
			for (StatDailyAccde bal : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				//倒序
				exportExcel.addCell(row, --headrLen, bal.getTotalBal() == null?"0.00":bal.getTotalBal().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getAcc09() == null?"0.00":bal.getAcc09().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getAcc04() == null?"0.00":bal.getAcc04().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getAcc02() == null?"0.00":bal.getAcc02().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getAcc01() == null?"0.00":bal.getAcc01().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getCardCnt() == null?"0":bal.getCardCnt().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getCrdproduct());
				exportExcel.addCell(row, --headrLen, bal.getCardBin());
				exportExcel.addCell(row, --headrLen, bal.getDailyDate());
			}
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("StatDailyAccdeController.exportExcel()调用出现异常。");
		}
		return null;
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
