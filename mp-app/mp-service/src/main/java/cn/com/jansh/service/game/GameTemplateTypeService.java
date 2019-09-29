/**
 * GameTemplateTypeService.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年2月15日
 */
package cn.com.jansh.service.game;

import java.util.List;

import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;

/**
 * 游戏模版分类接口
 * @author gll
 * @version 1.0
 */
public interface GameTemplateTypeService {

	/**
	 * 根据查询条件初始化查询游戏模版分类
	 * @param cloudGameTemplateTypeEntity
	 * @return
	 */
	public List<CloudGameTemplateTypeEntity> queryAllData(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity);

	/**
	 * 新增游戏模版数据
	 * @param cloudGameTemplateTypeEntity
	 */
	public void insertdate(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity);

	/**
	 * 修改游戏模版数据
	 * @param modelToEntity
	 */
	public void updatedata(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity);

	/**
	 * 判断此分类名有没有被使用
	 * @param modelToEntity
	 * @return
	 */
	public CloudGameTemplateTypeEntity selectByName(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity);

	/**
	 * 删除数据
	 * @param modelToEntity
	 */
	public void deletedata(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity);

	/**
	 * 判断此分类名有没有被模版使用
	 * @param tmptypeid
	 * @return
	 */
	public List<GameTemplate> selectActionByTmptypeid(String tmptypeid);

}
