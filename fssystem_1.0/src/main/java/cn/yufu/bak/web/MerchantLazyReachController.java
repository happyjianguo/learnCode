package cn.yufu.bak.web;

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

import cn.yufu.bak.entity.MerchantX;
import cn.yufu.bak.service.MerchantService;
import cn.yufu.bak.service.MerchantXService;
import cn.yufu.fs.entity.TClearMerstlBook;
import cn.yufu.fs.service.TClearMerstlBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;

@Controller
@RequestMapping(value="/MerchantLazyReach")
public class MerchantLazyReachController {
	
	Log log = Log.getLog(MerchantLazyReachController.class);

	@Autowired
	@Qualifier("fs.TClearMerstlBookService")
	private TClearMerstlBookService tClearMerstlBookService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	@Autowired
	@Qualifier("bak.MerchantService")
	private MerchantService merchantService;
	
	/**
	 * 加载初始页(无交易商户)
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param queryModel
	 * @return
	 */
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, TClearMerstlBook queryModel) {
		queryModel.setStartStlDate(getLastMonthDay("yyyyMMdd"));		
		queryModel.setEndStlDate(getNowDt("yyyyMMdd",0));
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/merchantLazyReachList";
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
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, TClearMerstlBook queryModel) {
		//获取这段时间内活跃的商户号
		List<String> mrchnoList = tClearMerstlBookService.getMrchnoList(queryModel);
		//存活跃商户号
		List<String> index =  new ArrayList<>();
		mrchnoList = (mrchnoList == null || mrchnoList.size() == 0) ? null : mrchnoList;
		if (mrchnoList != null && mrchnoList.size() >= 1000) {
			List<MerchantX> mrchList = merchantXService.selectMrchList();
			if (mrchList != null && mrchList.size() != 0) {
				for (int i = 0; i < mrchList.size(); i++) {
					MerchantX merchantX = mrchList.get(i);
					if (index.size() != 0 && merchantX.getMrchno().equals(index.get(0))) {
						mrchList.remove(i);
					}else {
						for (String mrchno : mrchnoList) {
							if (merchantX.getMrchno().equals(mrchno)) {
								//清空
								index.clear();
								mrchList.remove(i);
								//存入
								index.add(mrchno);
								break;
							}
						}
					}
				}
			}else {
				mrchList = new ArrayList<>();
			}
			// 分页设置
			Page page = new Page(curPage, pageSize, mrchList.size());
			curPage = page.getCurPage();
			page.style1();
			String url = request.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			if (startResult <= 0) {
				startResult = 0;
			}
			if (endResult > mrchList.size()) {
				endResult = mrchList.size();
			}
			List<MerchantX> resultList = new ArrayList<>();
			if (startResult < endResult) {
				for(int i = startResult; i < endResult; i++){
					resultList.add(mrchList.get(i));
				}
			}
			model.addAttribute("lazyMerchantList", getMerchantLazyList(resultList));
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);
			model.addAttribute("query", queryModel);
			return "modules/cortexs/merchantLazyReachList";
		}else {
			// 分页设置
			int count = merchantXService.getLazyMerchantCounts(mrchnoList);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = request.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			//获取这段时间内无交易的列表数据
			List<MerchantX> mrchList = merchantXService.selectPageMrchList(mrchnoList, startResult, endResult);
			model.addAttribute("lazyMerchantList", getMerchantLazyList(mrchList));
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);
			model.addAttribute("query", queryModel);
		}
		return "modules/cortexs/merchantLazyReachList";
	}
	
	/**
	 * 封装数据(备库)(商户状态)
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return MerchantX
	 */
	private List<MerchantX> getMerchantLazyList(List<MerchantX> mrchList){
		if (mrchList == null || mrchList.size() == 0) {
			return null;
		}
		for (MerchantX merchantX : mrchList) {
			//匹配商户状态
			try {
				List<Integer> mrchstat = merchantService.getMrchstat(merchantX.getMrchno());
				if (mrchstat == null) {
					merchantX.setMrchstat("");
				}else{ //0 可用 1 不可用 2 黑名单 3 销户
					if (mrchstat.get(0) == 0) {
						merchantX.setMrchstat("可用");
					}else if (mrchstat.get(0) == 1) {
						merchantX.setMrchstat("不可用");
					}else if (mrchstat.get(0) == 2) {
						merchantX.setMrchstat("黑名单");
					}else {
						merchantX.setMrchstat("销户");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("MerchantService的getMrchstat方法出现异常。");
			}
		}
		
		return mrchList;
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
	
}
