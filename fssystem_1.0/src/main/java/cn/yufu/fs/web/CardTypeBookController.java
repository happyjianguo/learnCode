package cn.yufu.fs.web;

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

import cn.yufu.fs.entity.CardTypeBook;
import cn.yufu.fs.service.CardTypeBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/CardShareBenefit")
public class CardTypeBookController {
	Log log = Log.getLog(CardTypeBookController.class);
	
	@Autowired
	@Qualifier("fs.CardTypeBookService")	
	private CardTypeBookService cardTypeBookService;
	
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
				CardTypeBook queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("query", queryModel);
		
		return "modules/fs/shareBenefitReport/cardTypeBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, CardTypeBook queryModel) {
		try{
			// 分页设置
			int count = cardTypeBookService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<CardTypeBook> list = cardTypeBookService.queryList(queryModel, startResult, endResult);
			
			model.addAttribute("cardTypeBookList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CardTypeBookController.getList()调用出现异常。");
		}
		
		return "modules/fs/shareBenefitReport/cardTypeBookList";
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
			Model model,String cardTypeId) {
		CardTypeBook info = new CardTypeBook();
		if (StringUtils.isNotBlank(cardTypeId)) {
			//更新
			info = cardTypeBookService.selectByPrimaryKey(cardTypeId);
			model.addAttribute("flag", "1");
		}else {
			model.addAttribute("flag", "0");
		}
		model.addAttribute("info", info);		
		return "modules/fs/shareBenefitReport/cardTypeBookShow";
	}
	
	/**
	 * 添加信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"save"})
	@ResponseBody
	public String save(HttpServletRequest req, HttpServletResponse resp, 
			Model model,CardTypeBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CardTypeBook result = cardTypeBookService.selectByPrimaryKey(info.getCardTypeId());
			if ("0".equals(info.getFlag())) {
				//执行添加
				if (result != null) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "卡BIN已存在，无法进行添加操作!");
					return JsonUtil.getJsonString(map);
				}
				map = cardTypeBookService.save(info);
			}else{
				if (result == null) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "卡BIN不存在，无法进行更新操作!");
					return JsonUtil.getJsonString(map);
				}else{
					map = cardTypeBookService.updateByPrimaryKey(info);
				}
			}
		} catch (Exception e) {
			log.info("保存卡类型码表信息失败：", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存卡类型码表信息失败。");
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
	@RequestMapping(value = {"delete"})
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest req, HttpServletResponse resp, 
			Model model,String cardTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = cardTypeBookService.deleteByPrimaryKey(cardTypeId);
		} catch (Exception e) {
			log.info("删除卡类型码表信息失败：", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除卡类型码表信息失败。");
		}
		return map;
	}
	
}
