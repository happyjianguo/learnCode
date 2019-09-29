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

import cn.yufu.bak.entity.CardKindComesource;
import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.service.CardKindComesourceService;
import cn.yufu.bak.service.CardkindsofService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/CardKindComesource")
public class CardKindComesourceController {
	Log log = Log.getLog(CardKindComesourceController.class);
	
	@Autowired
	@Qualifier("bak.CardKindComesourceService")	
	private CardKindComesourceService cardKindComesourceService;
	
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
				CardKindComesource queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		//卡类型
		List<Cardkindsof> queryList = cardkindsofService.queryList(new Cardkindsof());
		model.addAttribute("cardTypeList", queryList);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/codeTable/cardKindComesourceList";
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
			int curPage, int pageSize, CardKindComesource queryModel) {
		try{
			// 分页设置
			int count = cardKindComesourceService.queryCnt(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<CardKindComesource> list = cardKindComesourceService.queryList(queryModel, startResult, endResult);
			//卡类型
			List<Cardkindsof> queryList = cardkindsofService.queryList(new Cardkindsof());
			model.addAttribute("cardTypeList", queryList);
			Map<String, String> cardkindname = new HashMap<String, String>();
			for (CardKindComesource cc : list) {
				if (cardkindname.containsKey(cc.getCardnumber())) {
					cc.setCardkindname(cardkindname.get(cc.getCardnumber()));
					continue;
				}
				for (Cardkindsof cardkindsof : queryList) {
					if (cc.getCardnumber().equals(cardkindsof.getCardnumber())) {
						cc.setCardkindname(cardkindsof.getCardkindname());
						cardkindname.put(cc.getCardnumber(), cardkindsof.getCardkindname());
					}
				}
			}
			
			model.addAttribute("cardKindComesourceList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			log.error("CardKindComesourceController.getList()调用出现异常。");
		}
		
		return "modules/cortexs/codeTable/cardKindComesourceList";
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
			Model model, CardKindComesource queryModel) {
		//卡类型
		List<Cardkindsof> queryList = cardkindsofService.queryList(new Cardkindsof());
		model.addAttribute("cardTypeList", queryList);
		
		if (StringUtils.isNotBlank(queryModel.getId())) {
			//更新
			queryModel = cardKindComesourceService.selectByPrimaryKey(queryModel.getId());
		}	
		model.addAttribute("info", queryModel);		
		return "modules/cortexs/codeTable/cardKindComesourceShow";
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
			CardKindComesource info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CardKindComesource model = new CardKindComesource();
			model.setDataBaseType(info.getDataBaseType());
			model.setOldKindid(info.getOldKindid());
			List<CardKindComesource> list = cardKindComesourceService.queryList(model);
			String dataBaseType = info.getDataBaseType();
			if ("1".equals(dataBaseType)) {
				info.setDatabasesourcename("yufu库1");
			} if ("2".equals(dataBaseType)) {
				info.setDatabasesourcename("yufu库2");
			} if ("3".equals(dataBaseType)) {
				info.setDatabasesourcename("Cortex库");
			} if ("4".equals(dataBaseType)) {
				info.setDatabasesourcename("yufu库3");
			}
			if (StringUtils.isEmpty(info.getId())) {
				if (null != list && !list.isEmpty()) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "该数据来源下卡产品已存在，请您重新输入");
					return JsonUtil.getJsonString(map);
				}
				String maxID = cardKindComesourceService.getMaxId();
				info.setId(maxID);
				map = cardKindComesourceService.save(info);
			} else {
				if (null != list && !list.isEmpty()) {
					for (CardKindComesource cardKindComesource : list) {
						if (!info.getId().equals(cardKindComesource.getId())) {
							map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
							map.put(SysConst.RESULT_MSG, "该数据来源下卡产品已存在，请您重新输入");
							return JsonUtil.getJsonString(map);
						}
					}
				}
				map = cardKindComesourceService.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存卡类型来源信息失败。");
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
			Model model, CardKindComesource cardKindComesource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = cardKindComesourceService.deleteByPrimaryKey(cardKindComesource.getId());
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除卡类型来源信息失败。");
		}
		return map;
	}
	
}
