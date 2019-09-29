/**
 * IGameInitMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.mapper.game;

import java.util.List;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudgameparamEntity;

/**
 * 游戏活动数据库接口
 * @author xieliangliang
 * @version 1.0
 */
public interface IGameInitMapper {

	/**
	 * 获取活动的名称和代码
	 * @param li 
	 */
	public List<CloudGameInitEntity> getGameNameAndCode(List<String> li);
	/**
	 * 获取所有数据
	 */
	public List<CloudGameInitEntity> queryAllData(CloudGameInitEntity entity);

	/**
	 * 新增活动数据
	 */
	public void adddata(CloudGameInitEntity entity);

	/**
	 * 根据ID查询是否存在记录
	 */
	public CloudGameInitEntity queryById(String id);

	/**
	 * 根据ID更新活动数据
	 */
	public void updateById(String id);

	/**
	 * 根据ID删除活动信息
	 */
	public void deleteById(String id);

	/**
	 * 更新活动数据
	 */
	public void updateInfo(CloudGameInitEntity modelToEntity);
	/**
	 * 判断此模版有没有被游戏使用
	 * @param tempid
	 * @return
	 */
	public List<CloudgameparamEntity> queryByTempid(String tempid);

}
