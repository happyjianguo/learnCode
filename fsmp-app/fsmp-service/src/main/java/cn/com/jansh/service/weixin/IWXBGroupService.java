package cn.com.jansh.service.weixin;

import java.util.List;
import cn.com.jansh.entity.weixin.WXBGroup;
import cn.com.jansh.vo.AjaxObj;
/**
 * 粉丝分组接口
 * @author gll
 * @version 1.0
 */
public interface IWXBGroupService  {
	/**
	 * 通过appid 获取分组信息
	 * @param platformId
	 * @return
	 */
	public List<WXBGroup> queryGroupList(String appid);
	/**
	 * 添加分组
	 * @param groupName
	 * @param addGroupName
	 * @return
	 */
	public AjaxObj addNewGroup(String appid, String addGroupName);
	/**
	 * 修改分组
	 * @param appid
	 * @param modifyGroupId
	 * @param newGroupName
	 * @return
	 */
	public AjaxObj updateGroupName(String appid ,String modifyGroupId, String newGroupName);
	/**
	 * 删除分组
	 * @param groupId
	 * @return
	 */
	public AjaxObj delGroupById(String appid,Integer groupId);
	/**
	 * 移动用户到分组
	 * @param touser
	 * @param toGroupId
	 * @param appid
	 * @return
	 */
	public AjaxObj moveUserToNewGroup(String touser, String toGroupId, String appid);
}
