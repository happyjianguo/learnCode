package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import cn.com.jansh.entity.weixin.WXBUser;

/**
 * 粉丝Mapper
 * @author gll
 * @version 1.0
 */
public interface IWXBUserMapper {

	/**
	 * 根据openID查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	public List<WXBUser> getUserInfoByOpenId(String openId);
	/**
	 * 根据openID查询用户信息
	 * @param startDate 
	 * @param endDate 
	 * 
	 * @param openId
	 * @return
	 */
	public List<WXBUser> getUserListBySubtypeandAppid(@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("appid") String appid);

	/**
	 * 根据条件查询
	 * 
	 * @param searchUserName
	 * @param subScriptType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<WXBUser> queryUserByUserName(@Param("nickName") String nickName,@Param("openId") String openId,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("appid") String appid);

	/**
	 * 根据条件查询
	 * 
	 * @param groupId
	 * @param userName
	 * @param subScriptType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<WXBUser> queryUserListByGroupId(@Param("groupId") String groupId, @Param("nickName") String nickName,@Param("openId") String openId,
			@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("appid") String appid);

	/**
	 * 默认查询
	 * 
	 * @return
	 */
	public List<WXBUser> getUserListBySubtype(String appid);//正在关注的人
	/**
	 * 设置当前分组的为默认未分组状态
	 * @param groupId
	 * @param appid
	 */
	public void setDefaultGroupId(@Param("groupId")String groupId, @Param("appid")String appid);

	public void modifyUserListGroupId(Map<String, Object> param);

	public List<WXBUser> queryWXBUserPager(Map<String, Object> params);

}
