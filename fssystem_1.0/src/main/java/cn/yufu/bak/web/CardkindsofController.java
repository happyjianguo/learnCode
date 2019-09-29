package cn.yufu.bak.web;

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

import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.service.CardkindsofService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/Cardkindsof")
public class CardkindsofController {
	Log log = Log.getLog(CardkindsofController.class);
	
	@Autowired
	@Qualifier("bak.CardkindsofService")	
	private CardkindsofService cardkindsofService;
	
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
				Cardkindsof queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		List<Cardkindsof> queryList = cardkindsofService.queryList(queryModel);
		
		model.addAttribute("query", queryModel);
		model.addAttribute("cardTypeList", queryList);
		
		return "modules/cortexs/codeTable/cardkindsofList";
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
			int curPage, int pageSize, Cardkindsof queryModel) {
		try{
			// 分页设置
			int count = cardkindsofService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<Cardkindsof> list = cardkindsofService.queryList(queryModel, startResult, endResult);
			List<Cardkindsof> queryList = cardkindsofService.queryList(queryModel);
			
			model.addAttribute("cardTypeList", queryList);
			model.addAttribute("cardkindsofList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("CardkindsofController.getList()调用出现异常。");
		}
		
		return "modules/cortexs/codeTable/cardkindsofList";
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
			Model model, Cardkindsof queryModel) {
		if (StringUtils.isNotBlank(queryModel.getCardnumber())) {
			//更新
			queryModel = cardkindsofService.selectByPrimaryKey(queryModel.getCardnumber());
		}	
		model.addAttribute("info", queryModel);		
		return "modules/cortexs/codeTable/cardkindsofShow";
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
			Cardkindsof info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Cardkindsof model = new Cardkindsof();
			model.setCardkindname(info.getCardkindname());
			List<Cardkindsof> list = cardkindsofService.queryList(model);
			
			if (StringUtils.isEmpty(info.getCardnumber())) {
				if (null != list && !list.isEmpty()) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "卡类型已存在，请您重新输入");
					return JsonUtil.getJsonString(map);
				}
				String maXCardNum = cardkindsofService.getMaXCardNum();
				info.setCardnumber(maXCardNum);
				map = cardkindsofService.save(info);
			} else {
				if (null != list && !list.isEmpty()) {
					for (Cardkindsof cardkindsof : list) {
						if (model.getCardkindname().equals(cardkindsof.getCardkindname())
								&& !info.getCardnumber().equals(cardkindsof.getCardnumber())) {
							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
							map.put(SysConst.RESULT_MSG, "卡类型已存在，请您重新输入");
							return JsonUtil.getJsonString(map);
						}
					}
				}
				map = cardkindsofService.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存卡类型信息失败。");
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
			Model model, Cardkindsof cardkindsof) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = cardkindsofService.deleteByPrimaryKey(cardkindsof.getCardnumber());
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除卡类型信息失败。");
		}
		return map;
	}
	
}
