package cn.yufu.fs.web;

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

import cn.yufu.bak.service.MerchantXService;
import cn.yufu.fs.entity.TransactionRecordsHisT;
import cn.yufu.fs.service.TransactionRecordsHisTService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value="/TransactionRecordsHisT")
public class TransactionRecordsHisTController {
	
	Log log = Log.getLog(TransactionRecordsHisTController.class);
	
	@Autowired
	@Qualifier("fs.TransactionRecordsHisTService")
	private TransactionRecordsHisTService transactionRecordsHisTService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, TransactionRecordsHisT queryModel) {
		queryModel.setTransactiondateStart(getMonthDay("yyyyMMdd", -1));
		queryModel.setTransactiondateEnd(getNowDt("yyyyMMdd", 0));
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/transactionRecords/transactionRecordsHisList";
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
			Model model, int curPage, int pageSize, TransactionRecordsHisT queryModel) {
		// 分页设置
		int counts = transactionRecordsHisTService.queryCount(queryModel);
		
		Page page = new Page(curPage, pageSize, counts);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取实名认证列表数据
		List<TransactionRecordsHisT> list = transactionRecordsHisTService.selectPageList(queryModel, startResult, endResult);
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		
		//匹配商户状态及交易类型
		for (TransactionRecordsHisT TransactionRecordsHisTHisT : list) {
			if (StringUtils.isNotBlank(TransactionRecordsHisTHisT.getMerchantnumber())) {
				List<String> name = merchantXService.getMrchtName(TransactionRecordsHisTHisT.getMerchantnumber());
				if (name != null && name.size() > 0) {
					TransactionRecordsHisTHisT.setMerchantName(name.get(0));
				}
			}
		}
		
		model.addAttribute("transactionRecordsHisList", list);
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/transactionRecords/transactionRecordsHisList";
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
}
