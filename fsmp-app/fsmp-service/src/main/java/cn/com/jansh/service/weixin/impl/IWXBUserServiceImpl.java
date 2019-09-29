package cn.com.jansh.service.weixin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.jansh.entity.weixin.WXBGroup;
import cn.com.jansh.entity.weixin.WXBUser;
import cn.com.jansh.mapper.weixin.IWXBGroupMapper;
import cn.com.jansh.mapper.weixin.IWXBUserMapper;
import cn.com.jansh.service.weixin.IWXBUserService;
import cn.com.jansh.utils.DateUtil;
/**
 * 粉丝查询接口Impl
 * @version 1.0
 */
@Service
public class IWXBUserServiceImpl implements IWXBUserService {
	@Autowired
	private IWXBUserMapper iwxbusermapper;
	@Autowired
	private IWXBGroupMapper groupMapper;
	/**
	 * 初始化查询wxbuser
	 */
	@Override
	public List<WXBUser> getUserList(String groupId, String nickName,String openId, String appid, String startDate, String endDate) {
		if(StringUtils.isNotBlank(endDate)){ 
			endDate=DateUtil.addDate(endDate, 1);//增加一天
		}
		startDate=startDate+" 00:00:00";
		endDate=endDate+" 23:59:59";
		List<WXBUser> userList = new ArrayList<WXBUser>();
		if (groupId == null || "".equals(groupId)) {
			if ((nickName != null && !"".equals(nickName)) || (openId != null && !"".equals(openId))) {
				userList = iwxbusermapper.queryUserByUserName(nickName,openId, startDate, endDate, appid);
			} else {
				userList = this.selectUserList(startDate, endDate, appid);
			}
		} else {
			List<WXBGroup> groupList = groupMapper.selectGroupAll();
//			if (null != groupId && !"".equals(groupId)) {
				if ("allUser".equals(groupId)) {
					if ((nickName != null && !"".equals(nickName)) || (openId != null && !"".equals(openId))) {
						userList = iwxbusermapper.queryUserByUserName(nickName,openId, startDate, endDate, appid);
					} else {
						userList = this.selectUserList(startDate, endDate, appid);
					}
				} else {
					if (groupList != null && groupList.size() > 0) {
						for (WXBGroup groupMod : groupList) {
							if (groupMod.getGroupId().equals(groupId)) {
								userList = iwxbusermapper.queryUserListByGroupId(groupId, nickName,openId, startDate, endDate, appid);
							}
						}

					}
				}
//			}
		}
		return userList;
	}
	/**
	 * 更新平台ID获取用户信息
	 */
	@Override
	public List<WXBUser> queryUserList(String appid) {
		List<WXBUser> list = iwxbusermapper.getUserListBySubtype(appid);
		return list;
	}
	/**
	 * 更新平台appid获取用户信息
	 */
	public List<WXBUser> selectUserList(String startDate, String endDate,String appid) {
		List<WXBUser> list = iwxbusermapper.getUserListBySubtypeandAppid(startDate,endDate,appid);
		return list;
	}
	/**
	 * 更新用户分组
	 */
	@Override
	public void modifyUserGroup(List<String> userOpenId, String groupId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userOpenId", userOpenId);
		param.put("groupId", groupId);
		iwxbusermapper.modifyUserListGroupId(param);
	}
}
