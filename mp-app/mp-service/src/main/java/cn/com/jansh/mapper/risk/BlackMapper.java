package cn.com.jansh.mapper.risk;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.risk.BlacklistEntity;

/**
 * 黑名单Mapper
 * 
 * @author gll
 *
 */
public interface BlackMapper {

	/**
	 * 查询所有黑名单
	 * @param blacklistEntity
	 * @return
	 */
	public List<BlacklistEntity> selectByOne(BlacklistEntity blacklistEntity);

	/**
	 * 根据黑名单值和活动查询黑名单
	 * @param map
	 * @return
	 */
	public BlacklistEntity selectBlackByValueAndAction(Map<String, String> map);

	/**
	 * 插入黑名单数据
	 * @param blackEntity
	 */
	public void insertBlack(BlacklistEntity blackEntity);

	/**
	 * 通过黑名单ID查询黑名单
	 * @param map
	 * @return
	 */
	public BlacklistEntity selectBlackByBlackId(Map<String, String> map);

	/**
	 * 修改数据
	 * @param blackEntity
	 */
	public void updateblack(BlacklistEntity blackEntity);

	/**
	 * 删除数据
	 * @param map
	 */
	public void deleteblack(Map<String, String> map);

	/**
	 * 黑名单接口，查询黑名单
	 * @param map
	 * @return
	 */
	public BlacklistEntity selectBlackInter(Map<String, String> map);
	/**
	 * 通过黑名单值查询黑名单
	 * @param actionid 
	 * @return
	 */
	public BlacklistEntity selectByblackvalue(@Param("blackvalue")String blackvalue);

	/**
	 * 根据当前时间、过期时间对比，定时刷新黑名单状态
	 * @param map
	 */
	public void updateBlackListByovertime(Map<String, String> map);

}
