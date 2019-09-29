/**
 * IGameparamMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月1日
 */
package cn.com.jansh.mapper.game;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.game.CloudgameparamEntity;

/**
 * 活动参数
 * @author gll
 * @version 1.0
 */
public interface IGameparamMapper {

	/**
	 * 根据APPID和活动状态获取活动信息
	 * @param map
	 * @return
	 */
	public List<CloudgameparamEntity> getGameNameAndCode(Map<String, String> map);

	/**
	 * 根据ORGID和活动状态获取活动信息
	 * @param map
	 * @return
	 */
	public List<CloudgameparamEntity> getGameNameAndCodeByOrgid(Map<String, String> map);

	/**
	 * 通过gameid查询gameparam
	 * @param gameid
	 * @return
	 */
	public CloudgameparamEntity getGameByGameid(String gameid);

}
