package cn.com.jansh.controller.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.entity.weixin.NewsMaterialDetai;
import cn.com.jansh.entity.weixin.WXDMenu;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.INewsMaterialService;
import cn.com.jansh.service.weixin.IWXDMenuService;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信菜单管理
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/menu")
public class WXDMenuController {

	private static final Logger logger = LogManager.getLogger(WXDMenuController.class);
	
	@Autowired
	private IWXDMenuService wxdMenuService;
	
	@Autowired
	private INewsMaterialService newsMaterialService;
	
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String manager(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("菜单初始化");
			List<WXDMenu> menus = wxdMenuService.getAllMenus(appid);
			model.addAttribute("menus", menus);
			model.addAttribute("appid3", appid);
			return "/weixin/menuManager";
		}else{
			logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
			return "fsmp/wechat/skipwxmanage";
		}
	}

	/**
	 * 按钮数据保存
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value="saveAndRelease")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/menu/init")
	@OperationLog(value=Operation.CREATE,key="350300")
	public JsonVO saveAndRelease(String data){
		logger.info("菜单保存数据："+data);
		JsonVO jsvo = new JsonVO();
		try {
			if(wxdMenuService.updateMenus(data)){
				jsvo.setMsg("菜单发布成功！");
				jsvo.setSuccess(true);
			}else{
				jsvo.setMsg("菜单发布失败！");
				jsvo.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("菜单保存数据失败{}",e);
			jsvo.setMsg("菜单发布失败！");
			jsvo.setSuccess(false);
		} 
		return jsvo;
	}
	
	/**
	 * 根据素材名称检索素材列表
	 * @param platformId 
	 * @param msgid
	 * @return
	 */
	@RequestMapping("/selectMaterialByMsgid")
	@ResponseBody
	public AjaxObj selectMaterialByMsgid(String platformId ,String msgid) {
		AjaxObj ajaxObj = new AjaxObj();
		List<NewsMaterial> newsMateriallst = new ArrayList<NewsMaterial>();
		logger.info("根据素材名称检索素材列表{}",msgid);
		try {
			NewsMaterial newsmaterialmodel =newsMaterialService.queryNewsMaterialByMessageId(msgid);
			if(null ==newsmaterialmodel){
				ajaxObj.setMsg("没有获取到素材");
				ajaxObj.setObj(newsMateriallst);
				ajaxObj.setResult(1);
				return ajaxObj;
			}
			newsMateriallst.add(newsmaterialmodel);
			for(NewsMaterial materialModel : newsMateriallst){
				String materialId = materialModel.getMaterialId();
				List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByMaterialId(materialId);
				materialModel.setDetailModels(detailModels);
			}
			ajaxObj.setMsg("获取素材成功");
			ajaxObj.setObj(newsMateriallst);
			ajaxObj.setResult(1);
		} catch (Exception e) {
			logger.error("获取素材失败{}",e);
			ajaxObj.setMsg("获取素材失败");
			ajaxObj.setResult(0);
		}
		logger.info("获取素材成功");
		return ajaxObj;
	}
	
	/**
	 * 获取公众号消息列表中的图文类型
	 * 
	 * @param appid
	 * @return
	 */
	@RequestMapping("/selectMaterialByMsgidAndplatformId")
	@ResponseBody
	public AjaxObj selectMaterialByMsgidAndplatformId(String appid) {
		logger.info("获取公众号消息列表中的图文类型");
		AjaxObj ajaxObj = new AjaxObj();
		List<NewsMaterial> newsMateriallst = new ArrayList<NewsMaterial>();
		try {
			Map<String,String> map =new HashMap<String,String>();
			map.put("appid", appid);
			newsMateriallst =newsMaterialService.selectMaterialByMsgidAndplatformId(map);
			for(NewsMaterial materialModel : newsMateriallst){
				String materialId = materialModel.getMaterialId();
				List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByMaterialId(materialId);
				materialModel.setDetailModels(detailModels);
			}
			ajaxObj.setMsg("获取素材成功");
			ajaxObj.setObj(newsMateriallst);
			ajaxObj.setResult(1);
		} catch (Exception e) {
			logger.error("获取素材失败{}",e);
			ajaxObj.setMsg("获取素材失败");
			ajaxObj.setResult(0);
		}
		return ajaxObj;
	}
}
