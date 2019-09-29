package cn.yufu.fs.web;

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

import cn.yufu.fs.entity.CombineDate;
import cn.yufu.fs.service.CombineDateService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.IdGen;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/CombineDate")
public class CombineDateController {
	Log log = Log.getLog(CombineDateController.class);
	
	@Autowired
	@Qualifier("fs.CombineDateService")	
	private CombineDateService combineDateService;
	
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
				CombineDate queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("query", queryModel);
		
		return "modules/fs/clearMerStlFinalBook/combineDateList";
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
			int curPage, int pageSize, CombineDate queryModel) {
		try{
			// 分页设置
			int count = combineDateService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<CombineDate> list = combineDateService.queryList(queryModel, startResult, endResult);
			
			model.addAttribute("combineDateList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("CombineDateController.getList()调用出现异常。");
		}
		
		return "modules/fs/clearMerStlFinalBook/combineDateList";
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
			Model model, CombineDate queryModel) {
		if (StringUtils.isNotBlank(queryModel.getId())) {
			//更新
			queryModel = combineDateService.selectByPrimaryKey(queryModel.getId());
		}	
		model.addAttribute("info", queryModel);		
		return "modules/fs/clearMerStlFinalBook/combineDateShow";
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
			Model model,CombineDate info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Date date = new Date();
			info.setUpdateBy(UserUtils.getPrincipal().getId());
			info.setUpdateDate(date);
			
			CombineDate maxEndDate = combineDateService.selectMaxEndDate(new CombineDate());
			if (null != maxEndDate && 
					info.getStartDate().compareTo(maxEndDate.getEndDate()) <= 0) {
				map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
				map.put(SysConst.RESULT_MSG, "开始日期，必须大于已存在的最大结束日期");
				return JsonUtil.getJsonString(map);
			}
			if (StringUtils.isEmpty(info.getId())) {
				info.setId(IdGen.uuid());
				info.setDoneFlag("0");
				info.setCreateBy(UserUtils.getPrincipal().getId());
				info.setCreateDate(date);
				map = combineDateService.save(info);
			}else{
				map = combineDateService.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存节假日结算合并信息失败。");
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
			map = combineDateService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除节假日结算合并信息失败。");
		}
		return map;
	}
	
}
