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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.CustomMerInfo;
import cn.yufu.fs.service.CustomMerInfoService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

/**
 * MerchantTranRecordController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:ZQK
 * 时间:2017年9月20日
 * 描述:消费订单下单
 */
@Controller
@RequestMapping(value="/CustomMerInfo")
public class CustomMerInfoController {
	
	//日志
	Log log = Log.getLog(CustomMerInfoController.class);
	
	@Autowired
	@Qualifier("fs.CustomMerInfoService")
	private CustomMerInfoService customMerInfoService;
	
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
			Model model, CustomMerInfo queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/OrdersConsumption/customMerInfoList";
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
			int curPage, int pageSize, CustomMerInfo queryModel) {
		
		//获取分页总数
		int count = customMerInfoService.countByExample(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		List<CustomMerInfo> list = customMerInfoService.selectPageByExample(queryModel, startResult, endResult);
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		
		model.addAttribute("customMerInfoList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);	
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/OrdersConsumption/customMerInfoList";
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
	 * 跳转修改或添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, 
			Model model,String batchId) {
		CustomMerInfo info = new CustomMerInfo();
		if (StringUtils.isNotBlank(batchId)) {
			//更新
			info = customMerInfoService.selectByPrimay(batchId);
		}
		model.addAttribute("info", info);		
		return "modules/fs/OrdersConsumption/customMerInfoShow";
	}
	
	/**
	 * 添加信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(HttpServletRequest req, HttpServletResponse resp, 
			CustomMerInfo info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StringUtils.isNotBlank(info.getBatchId())) {
				//更新
				customMerInfoService.updateByPrimay(info);
			}else{
				//插入
				customMerInfoService.insert(info);
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存消费订单表信息成功。");
		} catch (Exception e) {
			log.info("保存消费订单信息失败：", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存消费订单表信息失败。");
		}
		return JsonUtil.getJsonString(map);
	}
	
	/**
	 * 删除信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest req, HttpServletResponse resp, 
			String batchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			customMerInfoService.deleteByPrimay(batchId);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除消费订单信息成功。");
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除消费订单信息失败。");
		}
		return map;
	}
}
