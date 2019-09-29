package cn.com.jansh.mapper.weixin;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.com.jansh.entity.weixin.WXBGroup;

/**
 * 粉丝分组Mapper
 * @author gll
 * @version 1.0
 */
public interface IWXBGroupMapper {
	/**
	 * 使用分组id查询分组是否存在
	 * @param groupId
	 * @return
	 */
	public List<WXBGroup> getGroupListWithId(String groupId);
	/**
	 * 通过分组id和公众号id删除分组
	 * @param groupId
	 * @param platformId
	 */
	public void deleteGroupById(@Param("groupId")String groupId, @Param("appid")String platformId);
	/**
	 * 保存分组
	 * @param t
	 */
	public void saveGroupInfo(WXBGroup t);
	public void updateGroupName(WXBGroup t);
	public List<WXBGroup> selectGroupAll();
	/**
	 * 使用公众号id查询所有分组
	 * @param appid
	 * @return
	 */
	public List<WXBGroup> selectGroupAllByPlatformId(String appid);
	/**
	 * 使用分组名称和公众号id查询分组
	 * @param groupName
	 * @param platformid
	 * @return
	 */
	public int findByGroupName(@Param("groupName")String groupName, @Param("appid")String platformid);
}
