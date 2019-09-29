package cn.com.jansh.controller.weixin;

import java.util.List;

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
import cn.com.jansh.entity.weixin.WXBGroup;
import cn.com.jansh.entity.weixin.WXBUser;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.IWXBGroupService;
import cn.com.jansh.service.weixin.IWXBUserService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.DateVO;
import cn.com.jansh.vo.JsonVO;
/**
 * 微信粉丝用户分组
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/userGroup")
public class WXDUserGroupController {
	
	private static final Logger logger = LogManager.getLogger(WXDMenuController.class);
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private IWXBUserService wxBUserService;
	@Autowired
	private IWXBGroupService wxBGroupService;
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	/**
	 * 初始化
	 * @param appid5
	 * @param model
	 * @return
	 */
	@RequestMapping("/init")
	public String manager(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("跳转到用户分组管理页面");
			DateVO dateVO = new DateVO();
			dateVO.setEndDay(DateUtil.getDateDay());
			dateVO.setStartDay(DateUtil.getFirstDay());
			model.addAttribute("dateVO",dateVO);
			model.addAttribute("appid",appid);
			return "weixin/userGroup";
		}else{
			logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
			return "fsmp/wechat/skipwxmanage";
		}
	}
	/**
	 * 取得初始化画面列表中的数据
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/getData")
	public JsonVO getData(Model model) {
		logger.info("取得初始化画面列表中的数据");
		JsonVO jsvo = new JsonVO();
		// 从系统Session中获取当前使用的平台ID
//		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		List<WXBUser> userList = wxBUserService.queryUserList(userDetail.getPlatformid());
//		model.addAttribute("userList", userList);
//		jsvo.setData(userList);
		jsvo.setSuccess(true);
		logger.info("跳转到用户分组管理页面");
		return jsvo;
	}


	/**
	 * 根据查询条件进行用户数据查询
	 * @param groupFlg
	 * @param searchUserName
	 * @param bindStatus
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("/ajax/getUserList")
	@ResponseBody
	public JsonVO userList(String groupFlg,String openId, String nickName,String appid, String startDate, String endDate) {
		logger.info("根据查询条件进行用户数据查询");
		JsonVO jsvo = new JsonVO();
		List<WXBUser> UserList = wxBUserService.getUserList(groupFlg, nickName, openId,appid, startDate, endDate);
		jsvo.setData(UserList);
		jsvo.setSuccess(true);
		return jsvo;
	}
	/**
	 * 获取分组信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/getGroupList")
	@ResponseBody
	private List<WXBGroup> getGroupList(String groupSelType,String appid) {
		logger.info("获取分组信息");
		List<WXBGroup> groupList = wxBGroupService.queryGroupList(appid);
		if ("init".equals(groupSelType)) {
			WXBGroup allUser = new WXBGroup();
			allUser.setGroupId("allUser");
			allUser.setGroupName("全部用户");
			groupList.add(allUser);
		}
		return groupList;
	}

	/**
	 * 添加新的分组
	 * @param groupName
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/ajax/addNewGroup")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/userGroup/init")
	@OperationLog(value=Operation.CREATE,key="350700")
	public AjaxObj addNewGroup(String appid ,String addGroupName) throws Exception {
		logger.info("添加新的分组");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = wxBGroupService.addNewGroup(appid,addGroupName);
		return ajaxObj;
	}
	/**
	 * 修改组名
	 * 
	 * @param modifyGroupId
	 * @param newGroupName
	 * @return
	 */
	@RequestMapping("/ajax/modifyGroupName")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/userGroup/init")
	@OperationLog(value=Operation.UPDATE,key="350701")
	public AjaxObj modifyGroupName(String appid,String oldGroupId, String newGroupName) throws Exception {
		logger.info("修改组名");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = wxBGroupService.updateGroupName(appid,oldGroupId, newGroupName);
		return ajaxObj ;

	}

	// 删除分组
	@RequestMapping("/ajax/delgroup")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/userGroup/init")
	@OperationLog(value=Operation.DELETE,key="350702")
	private AjaxObj delGroup(String appid , Integer delGroupId) throws Exception {
		logger.info("删除分组");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = wxBGroupService.delGroupById(appid,delGroupId);
		return ajaxObj;
	}

	// 移动分组
	@RequestMapping("/ajax/moveUserToNewGroup")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/userGroup/init")
	@OperationLog(value=Operation.DEFULT,key="350703")
	private AjaxObj moveUserToNewGroup(String touser, String toGroupId, String appid) throws Exception {
		logger.info("移动用户分组");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = wxBGroupService.moveUserToNewGroup(touser, toGroupId, appid);
//		getGroupList("init",appid);
		return ajaxObj;
//		return JsonUtil.obj2json(ajaxObj);
	}
}
