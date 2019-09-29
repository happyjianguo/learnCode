package cn.yufu.bak.web;

import java.math.BigDecimal;
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

import cn.yufu.bak.entity.OrderPayTlog;
import cn.yufu.bak.service.OrderPayTlogService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

/**
 * 测试--主库， 生产--备库
 * 
 * */
@Controller
@RequestMapping(value = "/OrderPayTlog")
public class OrderPayTlogController {
	Log log = Log.getLog(OrderPayTlogController.class);
	
	@Autowired
	@Qualifier("bak.OrderPayTlogService")	
	private OrderPayTlogService orderPayTlogService;
	
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
				OrderPayTlog queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/orderPayTlog/orderPayTlogList";
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
			int curPage, int pageSize, OrderPayTlog queryModel) {
		try{
			// 分页设置
			int count = orderPayTlogService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<OrderPayTlog> list = orderPayTlogService.selectPageByExample(queryModel, startResult, endResult);
			
			model.addAttribute("orderPayTlogList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("OrderPayTlogController.getList()调用出现异常。");
		}
		
		return "modules/cortexs/orderPayTlog/orderPayTlogList";
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
	public String exportExcel(HttpServletRequest req, HttpServletResponse resp, OrderPayTlog info) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			//银联交易信息
			List<OrderPayTlog> list = orderPayTlogService.selectByExample(info);
			String titleName = "支付清算明细 (" + this.getNowDt("yyyyMMddHHmmss",0) + ")";
			String[] headers = {"TLOG_ID","终端号","卡号","支付清算单号","支付原清算单号","卡系统参考号",
					"交易日期","交易时间","交易金额(元)","交易类型","交易状态","充值来源"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "支付清算明细", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
			
			Row row = null;
			int headrLen = 0;
			String txtstatus = "";
			for (OrderPayTlog model : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				
				//倒序
				exportExcel.addCell(row, --headrLen, null == model.getCashSource()?"":model.getCashSource().trim());
				
				txtstatus = model.getTxtstatus();
				if (StringUtils.isEmpty(txtstatus)) {
					exportExcel.addCell(row, --headrLen, "");
				} else if ("7".equals(txtstatus.trim())) {
					exportExcel.addCell(row, --headrLen, "成功");
				} else {
					exportExcel.addCell(row, --headrLen, "失败");
				}
				
				exportExcel.addCell(row, --headrLen, null == model.getTranTypeDesc()?"":model.getTranTypeDesc().trim());
				exportExcel.addCell(row, --headrLen, null == model.getAmttxn()?"0.00":model.getAmttxn().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getTransactionTime()?"":model.getTransactionTime().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTransactionDate()?"":model.getTransactionDate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getRrn()?"":model.getRrn().trim());
				exportExcel.addCell(row, --headrLen, null == model.getOrgOrderId()?"":model.getOrgOrderId().trim());
				exportExcel.addCell(row, --headrLen, null == model.getOrderId()?"":model.getOrderId().trim());
				exportExcel.addCell(row, --headrLen, null == model.getPan()?"":model.getPan().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTermcode()?"":model.getTermcode().trim());
				exportExcel.addCell(row, --headrLen, null == model.getId()?"":model.getId().trim());
			}
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("OrderPayTlogController.exportExcel()调用出现异常。");
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
