/**
 * GameInitServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.service.game.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.mapper.game.IGameInitMapper;
import cn.com.jansh.model.game.CloudGameInitModel;
import cn.com.jansh.service.game.GameInitService;

/**
 * 游戏活动管理实现
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class GameInitServiceImpl implements GameInitService {
	
	private static final Logger logger = LogManager.getLogger(GameInitServiceImpl.class);

	@Autowired
	private IGameInitMapper gameInitMapper;
	
	/**
	 * 获取有效活动数据的CODE和名称集合数据
	 */
	@Override
	public List<CloudGameInitEntity> getGameNameAndCode(List<String> li) {
		logger.info("获取活动名称和代码数据集合{}",li);
		return gameInitMapper.getGameNameAndCode(li);
	}

	/**
	 * 获取所有活动的数据
	 */
	@Override
	public List<CloudGameInitEntity> queryAllData(CloudGameInitEntity entity) {
		logger.info("获取所有活动的数据,{}",entity);
		List<CloudGameInitEntity> list = gameInitMapper.queryAllData(entity);
		
		for(int i=0; i<list.size(); i++){
			
			//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			
			if(StringUtils.isNotBlank(list.get(i).getUpdatetime())) {
				list.get(i).setUpdatetime(DateUtil.formatDateTimestamp(list.get(i).getUpdatetime()));
			}
		}
	
		return list;
	}

	/**
	 * 新增活动
	 * @throws AppException 
	 */
	@Override
	public void adddata(CloudGameInitModel gameinitModel) throws AppException {
		logger.info("新增活动数据,{}",gameinitModel);
		//确定ID是否存在
		if(StringUtils.isBlank(gameinitModel.getId())) {
			//参数ID不能为空
			logger.error("参数ID不能为空！");
			throw new AppException(AppErrorCode.E240002);
		}
		CloudGameInitEntity entityData = gameInitMapper.queryById(gameinitModel.getId());
		if (null != entityData) {
			// 用户已存在
			logger.error("此ID已存在,{}",entityData);
			throw new AppException(AppErrorCode.E240001);
		}
		
		CloudGameInitEntity entity = new CloudGameInitEntity();
		entity.setId(gameinitModel.getId()); //ID
		entity.setIndexnum(gameinitModel.getIndexnum()); //序号
		entity.setChannel(gameinitModel.getChannel()); //渠道
		entity.setPlayname(gameinitModel.getPlayname()); //游戏名称
		entity.setDeploypath(gameinitModel.getDeploypath()); //路径
		entity.setStatus(gameinitModel.getStatus()); //状态
		entity.setCreatetime(DateUtil.getDateTimestamp()); //创建时间
		entity.setUpdatetime(DateUtil.getDateTimestamp()); //更新时间
		entity.setOperator(AppUtil.getUserDetail().getUsername()); //操作人
		entity.setAppactionurl(gameinitModel.getAppactionurl());//APP渠道活动数据接口
		entity.setWxactionurl(gameinitModel.getWxactionurl());//微信渠道活动数据接口
		entity.setApprewardurl(gameinitModel.getApprewardurl());//APP渠道获奖接口
		entity.setWxrewardurl(gameinitModel.getWxrewardurl());//微信渠道获奖接口
		logger.info("新增活动数据,{}",entity);
		gameInitMapper.adddata(entity); 
	}

	/**
	 * 根据ID查询活动数据
	 */
	@Override
	public CloudGameInitEntity queryById(String id) {
		logger.info("根据ID获取活动的数据,{}",id);
		return gameInitMapper.queryById(id);
	}

	/**
	 * 更新活动数据
	 */
	@Override
	public void updateById(String id) {
		logger.info("根据ID更新活动数据,{}",id);
		gameInitMapper.updateById(id);
	}

	/**
	 * 删除活动数据
	 */
	@Override
	public void deleteById(String id) {
		logger.info("删除活动数据,ID为{}",id);
		gameInitMapper.deleteById(id);
	}

	/**
	 * 更新活动数据
	 */
	@Override
	public void updateInfo(CloudGameInitEntity modelToEntity) {
		logger.info("更新活动数据{}",modelToEntity);
		gameInitMapper.updateInfo(modelToEntity);
	}

	/**
	 * 删除活动数据
	 */
	@Override
	public void delGameInfo(String id) {
			
	}

	/**
	 * 判断此模版有没有被游戏使用
	 */
	@Override
	public List<CloudgameparamEntity> queryByTempid(String tempid) {
		logger.info("判断此模版有没有被游戏使用{}",tempid);
		return gameInitMapper.queryByTempid(tempid);
	}
}
