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

import cn.yufu.bak.entity.ViewAccnoAvlBal;
import cn.yufu.bak.service.ViewAccnoAvlBalService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

@Controller
@RequestMapping(value = "/ViewAccnoAvlBal")
public class ViewAccnoAvlBalController {
	Log log = Log.getLog(ViewAccnoAvlBalController.class);
	
	@Autowired
	@Qualifier("bak.ViewAccnoAvlBalService")	
	private ViewAccnoAvlBalService viewAccnoAvlBalService;
	
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
				ViewAccnoAvlBal queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/viewAccnoAvlBal/viewAccnoAvlBalList";
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
			int curPage, int pageSize, ViewAccnoAvlBal queryModel) {
		try{
			// 分页设置
			int count = viewAccnoAvlBalService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<ViewAccnoAvlBal> list = viewAccnoAvlBalService.selectPageByExample(queryModel, startResult, endResult);
			
			model.addAttribute("viewAccnoAvlBalList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("ViewAccnoAvlBalController.getList()调用出现异常。");
		}
		
		return "modules/cortexs/viewAccnoAvlBal/viewAccnoAvlBalList";
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
	public String exportExcel(HttpServletRequest req, HttpServletResponse resp, ViewAccnoAvlBal info) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			//银联交易信息
			List<ViewAccnoAvlBal> list = viewAccnoAvlBalService.selectByExample(info);
			String titleName = "卡账户总余额信息 (" + this.getNowDt("yyyyMMddHHmmss",0) + ")";
			String[] headers = {"卡BIN","卡张数","账户总余额(元)"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "卡账户总余额", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
			
			Row row = null;
			int headrLen = 0;
			for (ViewAccnoAvlBal bal : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				//倒序
				exportExcel.addCell(row, --headrLen, bal.getTotalBal() == null?"0.00":bal.getTotalBal().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getCardNum() == null?"0":bal.getCardNum().toPlainString());
				exportExcel.addCell(row, --headrLen, bal.getCardbin());
			}
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("ViewAccnoAvlBalController.exportExcel()调用出现异常。");
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
