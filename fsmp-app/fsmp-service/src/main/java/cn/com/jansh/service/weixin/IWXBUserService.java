package cn.com.jansh.service.weixin;

import java.util.List;
import cn.com.jansh.entity.weixin.WXBUser;
/**
 * 粉丝查询接口
 * @author gll
 * @version 1.0
 */
public interface IWXBUserService  {
	
	/**
	 * 初始化查询wxbuser
	 * @param groupFlg
	 * @param nickName
	 * @param openId
	 * @param startDate
	 * @param endDate
	 * @param endDate2
	 * @return
	 */
	public List<WXBUser> getUserList(String groupFlg, String nickName,String openId,String appid, String startDate, String endDate);
	/**
	 * 粉丝移动接口
	 * @param userOpenId
	 * @param groupId
	 */
	public void modifyUserGroup(List<String> userOpenId, String groupId);
	/**
	 * 通过公众号id查询所有粉丝
	 * @param platformId
	 * @return
	 */
	public List<WXBUser> queryUserList(String platformId);
}
