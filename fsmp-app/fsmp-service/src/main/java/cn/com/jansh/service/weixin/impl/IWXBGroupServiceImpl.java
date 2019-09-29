package cn.com.jansh.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.weixin.WXBGroup;
import cn.com.jansh.mapper.weixin.IWXBGroupMapper;
import cn.com.jansh.mapper.weixin.IWXBUserMapper;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.service.weixin.IWXBGroupService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.HttpClientUtil;
import cn.com.jansh.vo.AjaxObj;
/**
 * 粉丝分组接口Impl
 * @author gll
 * @version 1.0
 */
@Service
@SuppressWarnings("unchecked")
public class IWXBGroupServiceImpl implements IWXBGroupService {
	private static final Logger logger = LogManager.getLogger(IWXBGroupServiceImpl.class);
	@Autowired
	private IWXBUserMapper wxUserMapper;
	@Autowired
	private IWXBGroupMapper wxGroupMapper;
	@Autowired
	private GlobalProperties globalProperties;//获取微信路径
	@Autowired
	private WxAuthService wxAuthService;//获取accesstoken
	/**
	 * 获取组信息
	 */
	@Override
	public List<WXBGroup> queryGroupList(String appid) {
		return wxGroupMapper.selectGroupAllByPlatformId(appid);
	}
	/**
	 * 增加新组
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public AjaxObj addNewGroup(String appid ,String groupName) {
		AjaxObj ajaxObj = new AjaxObj();
		Map<String, Object> group = new HashMap<String, Object>();
		Map<String, Object> newName = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		WXBGroup groupModel = new WXBGroup();
		String addGroupData = "";
		int count=wxGroupMapper.findByGroupName(groupName,appid);
		if (0<count) {
			ajaxObj.setSuccess(false);
			ajaxObj.setResult(0);
			ajaxObj.setMsg("分组名称不能重复");
			return ajaxObj;
		}
		try {
//			 获取AccessToken
			String accessToken = wxAuthService.getAuthAccessToken(appid);
			String addGroupUrl = globalProperties.getApiWeixinURL() + "groups/create?access_token=" + accessToken;
//			 上传用的数据
			newName.put("name", groupName);
			group.put("group", newName);
			addGroupData = mapper.writeValueAsString(group);
			String res = HttpClientUtil.httpPost(addGroupUrl, addGroupData);
			Map groupMap = new ObjectMapper().readValue(res, Map.class);
			if (null != groupMap.get("errcode")) {
				if (!"0".equals(groupMap.get("errcode").toString())) {
					ajaxObj.setResult(0);
					ajaxObj.setMsg("新建分组失败");
					logger.warn("新建分组失败!" + groupMap.get("errmsg").toString());
					return ajaxObj;
				}
			} else {
				if (null != groupMap.get("group")) {
					Map<String, Object> newGroupInfo = (Map<String, Object>) groupMap.get("group");
					String newGroupId = newGroupInfo.get("id").toString();
					List<WXBGroup> groupModelLst = wxGroupMapper.getGroupListWithId(newGroupId);
					if (groupModelLst != null && groupModelLst.size() > 0) {
						wxGroupMapper.deleteGroupById(newGroupId, appid);
					}
					if (newGroupInfo != null) {
						groupModel.setGroupId(newGroupId);
						groupModel.setGroupName(newGroupInfo.get("name").toString());
						groupModel.setCount("0");
						groupModel.setAppid(appid);
						groupModel.setUpdateTime(DateUtil.getDate());
						groupModel.setCreateTime(DateUtil.getDate());
						wxGroupMapper.saveGroupInfo(groupModel);
					}
					ajaxObj.setResult(1);
					ajaxObj.setMsg("新建分组成功");
				} else {
					ajaxObj.setResult(0);
					ajaxObj.setMsg("新建分组失败");
				}

			}
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("新建分组失败");
			logger.warn("新建分组失败：", e);
		}
		return ajaxObj;
	}

	/**
	 * 更新组名
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public AjaxObj updateGroupName(String appid ,String modifyGroupId, String newGroupName) {
		WXBGroup groupModel = new WXBGroup();
		groupModel.setGroupName(newGroupName);
		groupModel.setGroupId(modifyGroupId);
		groupModel.setAppid(appid);
		AjaxObj ajaxObj = new AjaxObj();
		int count=wxGroupMapper.findByGroupName(newGroupName,appid);
		if (0<count) {
			ajaxObj.setSuccess(false);
			ajaxObj.setResult(0);
			ajaxObj.setMsg("修改分组名失败,该分组名称已存在!");
			return ajaxObj;
		}
		Map<String, Object> group = new HashMap<String, Object>();
		Map<String, Object> newName = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String modifyGroupData = "";
		Map<String, String> maps = null;
		try {
			// 获取AccessToken
			String accesstoken=wxAuthService.getAuthAccessToken(appid);	
			String modifyGroupUrl = globalProperties.getApiWeixinURL() + "groups/update?access_token=" +accesstoken ;
			// 上传用的数据
			newName.put("id", modifyGroupId);
			newName.put("name", newGroupName);
			group.put("group", newName);
			modifyGroupData = mapper.writeValueAsString(group);
			String res = HttpClientUtil.httpPost(modifyGroupUrl, modifyGroupData);
			Map addGroupMap = new ObjectMapper().readValue(res, Map.class);
			if (null != addGroupMap.get("errcode")) {
				if (!"0".equals(addGroupMap.get("errcode").toString())) {
					ajaxObj.setResult(0);
					ajaxObj.setMsg("修改分组名失败!");
					System.out.println("修改分组名失败!" + addGroupMap.get("errmsg").toString());
					logger.warn("修改分组名失败!" + addGroupMap.get("errmsg").toString());
					return ajaxObj;
				} else {
					wxGroupMapper.updateGroupName(groupModel);
					ajaxObj.setResult(1);
					ajaxObj.setMsg("修改分组名成功!!!");
				}
			} else {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("修改分组名失败!!!");
			}
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("修改分组名失败!!!");
		}
		return ajaxObj;
	}
	/**
	 * 根据groupId删除组别
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public AjaxObj delGroupById(String appid,Integer groupId) {
		AjaxObj ajaxObj = new AjaxObj();
		String sendNewsStr = "";
		String groupSendMsgUrl = "";
		Map<String, String> maps = null;
		try {
			// 获取AccessToken
			String accesstoken=wxAuthService.getAuthAccessToken(appid);
			groupSendMsgUrl = globalProperties.getApiWeixinURL() + "groups/delete?access_token=" + accesstoken;
			Map<String, Integer> mapid = new HashMap<String, Integer>();
			mapid.put("id", groupId);
			Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
			map.put("group", mapid);
			ObjectMapper mapper = new ObjectMapper();
			sendNewsStr = mapper.writeValueAsString(map);
			// 发送报文
			String res = HttpClientUtil.httpPost(groupSendMsgUrl, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if ((sendResMap != null && sendResMap.get("errcode") != null) && !"0".equals(sendResMap.get("errcode").toString())) {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("删除分组失败!");
				logger.warn("删除分组失败!" + sendResMap.get("errmsg").toString());
				return ajaxObj;
			} else {
				wxGroupMapper.deleteGroupById(String.valueOf(groupId),appid);
				wxUserMapper.setDefaultGroupId(String.valueOf(groupId), appid);//设置当前分组的为默认未分组状态
				ajaxObj.setResult(1);
				ajaxObj.setMsg("删除分组成功!!!");
			}
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("删除分组失败!!!");
			logger.warn("删除分组失败：", e);
		}
		return ajaxObj;
	}
	/**
	 * 移动用户到新分组
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public AjaxObj moveUserToNewGroup(String touser, String toGroupId, String appid) {

		AjaxObj ajaxObj = new AjaxObj();
		String sendNewsStr = "";
		String groupSendMsgUrl = "";
		Map<String, Object> userMap = new HashMap<String, Object>();
		Map<String, String> maps = null;
		try {
			userMap = new ObjectMapper().readValue(touser, Map.class);
			List<String> userLst = (List<String>) userMap.get("userOpenIdList");
			if (1>userLst.size()) {
				ajaxObj.setMsg("请至少选择一个用户，再进行移动分组");
				ajaxObj.setSuccess(false);
				ajaxObj.setResult(0);
				return ajaxObj;
			}
			Map<String, Object> openIdMap = new HashMap<String, Object>();
			Map<String, Object> param = new HashMap<String, Object>();
			// 获取AccessToken
			String accessToken = wxAuthService.getAuthAccessToken(appid);
			if (userLst != null) {
				if (userLst.size() == 1) {
					groupSendMsgUrl = globalProperties.getApiWeixinURL() + "groups/members/update?access_token=" +accessToken ;
					openIdMap.put("openid", userLst.get(0));
					openIdMap.put("to_groupid", toGroupId);
				} else {
					groupSendMsgUrl = globalProperties.getApiWeixinURL() + "groups/members/batchupdate?access_token=" + accessToken;
					openIdMap.put("openid_list", userLst);
					openIdMap.put("to_groupid", toGroupId);
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			sendNewsStr = mapper.writeValueAsString(openIdMap);
			// 发送报文
			String res = HttpClientUtil.httpPost(groupSendMsgUrl, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if ((sendResMap != null && sendResMap.get("errcode") != null)
					&& !"0".equals(sendResMap.get("errcode").toString())) {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("移动用户分组失败!");
				logger.warn("移动用户分组失败!" + sendResMap.get("errmsg").toString());
				return ajaxObj;
			} else {
				param.put("userLst", userLst);
				param.put("toGroupId", toGroupId);
				wxUserMapper.modifyUserListGroupId(param);
				ajaxObj.setResult(1);
				ajaxObj.setMsg("移动用户分组成功!!!");
			}
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("移动用户分组失败!!!");
			logger.warn("移动用户分组失败：", e);
		}
		return ajaxObj;
	}
}
