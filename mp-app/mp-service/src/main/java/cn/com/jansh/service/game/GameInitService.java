/**
 * GameInitService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.service.game;

import java.util.List;

import com.jansh.core.exception.AppException;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.model.game.CloudGameInitModel;

/**
 * 游戏活动管理接口
 * @author xieliangliang
 * @version 1.0
 */
public interface GameInitService {
	
	/**
	 * 获取游戏的CODE和名字下拉列表
	 * @param li 
	 */
	public List<CloudGameInitEntity> getGameNameAndCode(List<String> li);

	/**
	 * 获取所有活动的数据
	 */
	public List<CloudGameInitEntity> queryAllData(CloudGameInitEntity entity);

	/**
	 * 新增活动信息
	 */
	public void adddata(CloudGameInitModel gameinitModel) throws AppException;
	
	/**
	 * 根据ID查询活动信息
	 */
	public CloudGameInitEntity queryById(String id);
	
	/**
	 * 更新活动数据信息
	 */
	public void updateById(String id);
	
	/**
	 * 删除活动信息
	 */
	public void deleteById(String id);

	/**
	 * 更新活动数据
	 */
	public void updateInfo(CloudGameInitEntity modelToEntity);

	/**
	 * 删除活动数据
	 */
	public void delGameInfo(String id);

	/**
	 * 判断此模版有没有被游戏使用
	 * @param tempid
	 * @return
	 */
	public List<CloudgameparamEntity> queryByTempid(String tempid);

}
