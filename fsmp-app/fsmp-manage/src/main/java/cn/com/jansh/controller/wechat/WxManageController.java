/**
 * WxManageController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月25日
 */
package cn.com.jansh.controller.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;

import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.model.wechat.WxManageModel;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.service.wechat.WxInterfaceService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 微信管理模块Controller
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/wxmanage")
public class WxManageController {

	private final static Logger logger = LogManager.getLogger(WxManageController.class);
	
	@Autowired
	public WxInterfaceService  wxService;
	
	@Autowired
	public WxAuthService wxAuthService;
	
	
	@RequestMapping("/init")
	public String init(Model model){
		logger.info("跳转到公众号管理界面");
		return "fsmp/wechat/wxmanage";
	}
	
	/*@RequestMapping("/func")
	public String func(Model model,String appid){
		logger.info("跳转到公众号管理界面",appid);
		model.addAttribute("appid", appid);
		return "weixin/skip";
	}*/
	
	@RequestMapping("/goauth")
	public String goauth(){
		logger.info("跳转到授权界面");
		return "fsmp/wxauth";
	}
	
	/**
	 * 获取全部授权公众号
	 * @param 微信管理model wxManageModel
	 */
	@RequestMapping("/getall")
	@ResponseBody
	public DatatablesViewPage<AuthAccount> getAllAccount(WxManageModel wxManageModel ){
		logger.info("开始获取全部公众号");
		WxManageModel showAuthAccount = wxService.getAuthorizationAll( wxManageModel);
		DatatablesViewPage<AuthAccount> page = new DatatablesViewPage<>();
		page.setAaData(showAuthAccount.getAuthAccounts());
		page.setiTotalDisplayRecords(showAuthAccount.getCount());
		page.setiTotalRecords(showAuthAccount.getCount());
		return page;
	}
	
	/**
	 * 开始默认指定公众号
	 * @param 公众号id appid
	 */
	@RequestMapping("/defaultitem")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/wxmanage/init")
	@OperationLog(value=Operation.UPDATE,key="320130")
	public AjaxObj defaultitem(String appid){
		logger.info("开始默认指定公众号:{}",appid);
		AjaxObj ajaxobj = wxService.defaultAuthorization(appid);
		return ajaxobj;
	}
	/**
	 * 取消默认指定公众号
	 * @param 公众号id appid
	 */
	@RequestMapping("/undefaultitem")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/wxmanage/init")
	@OperationLog(value=Operation.UPDATE,key="320130")
	public AjaxObj undefaultitem(String appid){
		logger.info("取消默认指定公众号:{}",appid);
		AjaxObj ajaxobj = wxService.undefaultAuthorization(appid);
		return ajaxobj;
	}
	/**
	 * 删除指定授权公众号
	 * @param 公众号id appid
	 */
	@RequestMapping("/del")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/wxmanage/init")
	@OperationLog(value=Operation.UPDATE,key="320110")
	public AjaxObj deleteAccount(String appid){
		logger.info("开始删除指定公众号");
		AjaxObj ajaxobj = wxService.removeAuthorization(appid);
		return ajaxobj;
	}
	
	/**
	 * 跳转到扫码授权页面
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/getwxauthhtml")
	@ExceptionHandle("/wxmanage/init")
	@OperationLog(value=Operation.UPDATE,key="320120")
	public String redirect(HttpServletRequest request) throws AppException{
		logger.info("进入授权页面");
		return "redirect:"+wxAuthService.getAuthURL();
	}
	
	
	/**
	 * 授权后回调页面
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/forwardauthover")
	@ExceptionHandle("/wxmanage/init")
	public String forwardAuthOver(HttpServletRequest request) throws AppException{
		String orgid = request.getParameter("orgid");
		logger.info("传递参数: "+orgid+"----------------------");
		String auth_code = request.getParameter("auth_code");
		logger.info("授权码是: "+auth_code+"----------------------");
		wxAuthService.getAuthAccountInfo(auth_code,orgid);
		return "redirect:/wxmanage/init";
	}
}
