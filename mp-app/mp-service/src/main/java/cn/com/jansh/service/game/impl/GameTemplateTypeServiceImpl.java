/**
 * GameTemplateTypeServiceImpl.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年2月15日
 */
package cn.com.jansh.service.game.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;
import cn.com.jansh.mapper.game.GameTemplateTypeMapper;
import cn.com.jansh.service.game.GameTemplateTypeService;

/**
 * 游戏模版分类接口实现类
 * @author gll
 * @version 1.0
 */
@Service
public class GameTemplateTypeServiceImpl implements GameTemplateTypeService {

	private static final Logger logger = LogManager.getLogger(GameTemplateTypeServiceImpl.class);
	
	@Autowired
	private GameTemplateTypeMapper gameTemplateTypeMapper;
	
	/**
	 * 根据查询条件初始化查询游戏模版分类 
	 * @param cloudGameTemplateTypeEntity
	 * @return
	 */
	@Override
	public List<CloudGameTemplateTypeEntity> queryAllData(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity) {
		logger.info("获取所有活动的数据,{}",cloudGameTemplateTypeEntity);
		return gameTemplateTypeMapper.selectDataByEntity(cloudGameTemplateTypeEntity);
	}

	/**
	 * 新增游戏模版数据
	 */
	@Override
	public void insertdate(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity) {
		logger.info("新增游戏模版数据,{}",cloudGameTemplateTypeEntity);
		gameTemplateTypeMapper.insertdate(cloudGameTemplateTypeEntity);
	}

	/**
	 * 修改游戏模版数据
	 */
	@Override
	public void updatedata(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity) {
		logger.info("修改游戏模版数据,{}",cloudGameTemplateTypeEntity);
		gameTemplateTypeMapper.updatedata(cloudGameTemplateTypeEntity);
	}

	/**
	 * 判断此分类名有没有被使用
	 */
	@Override
	public CloudGameTemplateTypeEntity selectByName(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity) {
		logger.info("判断此分类名有没有被使用,{}",cloudGameTemplateTypeEntity);
		return gameTemplateTypeMapper.selectByName(cloudGameTemplateTypeEntity);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deletedata(CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity) {
		logger.info("删除数据,{}",cloudGameTemplateTypeEntity);
		gameTemplateTypeMapper.deletedata(cloudGameTemplateTypeEntity);
	}

	/**
	 * 判断此分类名有没有被模版使用
	 */
	@Override
	public List<GameTemplate> selectActionByTmptypeid(String tmptypeid) {
		logger.info("判断此分类名有没有被模版使用,{}",tmptypeid);
		return gameTemplateTypeMapper.selectActionByTmptypeid(tmptypeid);
	}

}
