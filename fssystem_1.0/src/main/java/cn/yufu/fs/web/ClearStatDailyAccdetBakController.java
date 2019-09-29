package cn.yufu.fs.web;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yufu.fs.entity.ClearStatDailyAccdetBak;
import cn.yufu.fs.service.ClearStatDailyAccdetBakService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;

@Controller
@RequestMapping(value = "/StatDailyAccdetBak")
public class ClearStatDailyAccdetBakController {
	Log log = Log.getLog(ClearStatDailyAccdetBakController.class);
	
	@Autowired
	@Qualifier("fs.ClearStatDailyAccdetBakService")	
	private ClearStatDailyAccdetBakService clearStatDailyAccdetBakService;
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = "page")
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model,
			ClearStatDailyAccdetBak queryModel) {
		queryModel.setEndDailyDate(getNowDt("yyyyMMdd",-1));
		//queryModel.setBegainDailyDate(getBeforeMonthDay("yyyyMMdd",-1));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("queryModel", queryModel);

		return "modules/fs/clearStatDailyAccdet/clearStatDailyAccdetBakList";
		
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
	@RequestMapping(value = "getList")
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, 
			int curPage, int pageSize, ClearStatDailyAccdetBak queryModel) {
		// 分页设置
		int count = clearStatDailyAccdetBakService.queryCount(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//分页数据
		List<ClearStatDailyAccdetBak> list = clearStatDailyAccdetBakService.selectPageList(queryModel, startResult, endResult);		
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		model.addAttribute("ClearStatDailyAccdetBakList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("queryModel", queryModel);
		
		//总卡片数与总余额
		ClearStatDailyAccdetBak sumAndBal = clearStatDailyAccdetBakService.TotalCardSumAndBal(queryModel);
		if (sumAndBal == null) {
			sumAndBal = new ClearStatDailyAccdetBak();
			sumAndBal.setCardSum(new BigDecimal(0));
			sumAndBal.setCardBal(new BigDecimal(0));
		}
		model.addAttribute("sumAndBal", sumAndBal);
		
		return "modules/fs/clearStatDailyAccdet/clearStatDailyAccdetBakList";
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
	 * 取对应月日期
	 * @param pattern
	 * @return
	 */
	public String getBeforeMonthDay(String pattern,int flag) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, flag); // -1 得到前一个月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
	
}
