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

import cn.yufu.fs.entity.MerstlErrorSet;
import cn.yufu.fs.service.MerstlErrorSetService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.IdGen;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/MerstlErrorSet")
public class MerstlErrorSetController {
	Log log = Log.getLog(MerstlErrorSetController.class);
	
	@Autowired
	@Qualifier("fs.MerstlErrorSetService")	
	private MerstlErrorSetService merstlErrorSetService;
	
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
				MerstlErrorSet queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("query", queryModel);
		
		return "modules/fs/checkModifyView/merstlErrorSetList";
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
			int curPage, int pageSize, MerstlErrorSet queryModel) {
		try{
			// 分页设置
			int count = merstlErrorSetService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<MerstlErrorSet> list = merstlErrorSetService.selectPageByExample(queryModel, startResult, endResult);
			
			model.addAttribute("merstlErrorSetList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("MerstlErrorSetController.getList()调用出现异常。");
		}
		
		return "modules/fs/checkModifyView/merstlErrorSetList";
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
			Model model, MerstlErrorSet queryModel) {
		if (StringUtils.isNotBlank(queryModel.getId())) {
			//更新
			queryModel = merstlErrorSetService.selectByPrimaryKey(queryModel.getId());
			if (queryModel.getErrorCardNum().startsWith("-")) {
				queryModel.setNumFlag("1");
			}
			if (queryModel.getErrorCardAmt().startsWith("-")) {
				queryModel.setAmtFlag("1");
			}
		}	
		model.addAttribute("info", queryModel);		
		return "modules/fs/checkModifyView/merstlErrorSetShow";
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
			Model model, MerstlErrorSet info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			setNumAndAmt(info);
			if (StringUtils.isEmpty(info.getId())) {
				info.setId(IdGen.uuid());
				map = merstlErrorSetService.insertSelective(info);
			}else{
				map = merstlErrorSetService.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存卡余额调整信息失败。");
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
			Model model,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = merstlErrorSetService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除卡余额调整信息失败。");
		}
		return map;
	}
	
	private void setNumAndAmt(MerstlErrorSet info){
		if ("1".equals(info.getNumFlag())) {	//下调
			info.setErrorCardNum("-" + info.getErrorCardNum());
		}
		if ("1".equals(info.getAmtFlag())) {	//下调
			info.setErrorCardAmt("-" + info.getErrorCardAmt());
		}
	}
}
