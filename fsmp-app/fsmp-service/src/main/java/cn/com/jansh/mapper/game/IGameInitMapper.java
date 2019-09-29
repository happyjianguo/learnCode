/**
 * IGameInitMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.mapper.game;


import cn.com.jansh.entity.component.CloudgameinitEntity;

/**
 * 游戏活动数据库接口
 * @author xieliangliang
 * @version 1.0
 */
public interface IGameInitMapper {
	/**
	 * 根据ID查询是否存在记录
	 */
	public CloudgameinitEntity queryById(String id);
}
