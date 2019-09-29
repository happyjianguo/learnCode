/**
 * WinningrecordServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月3日
 */
package cn.com.jansh.service.query.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;

import cn.com.jansh.entity.recharge.CloudwinningrecordEntity;
import cn.com.jansh.mapper.query.IWinningRecordMapper;
import cn.com.jansh.service.query.WinningrecordService;

/**
 * 订单中奖记录实现类
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class WinningrecordServiceImpl implements WinningrecordService {
	
	private static final Logger logger = LogManager.getLogger(WinningrecordServiceImpl.class);

	@Autowired
	private IWinningRecordMapper winningRecordMapper;
	
	/**
	 * 查询中间订单记录
	 */
	@Override
	public List<CloudwinningrecordEntity> queryAll(CloudwinningrecordEntity entity) {
		
		logger.info("查询订单记录,{}",entity);
		
		List<CloudwinningrecordEntity> list = winningRecordMapper.queryAll(entity);
		
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
	 * 根据ID查询订单审核数据
	 */
	@Override
	public CloudwinningrecordEntity queryByUserId(String id) {
		logger.info("根据ID查询订单记录,{}",id);
		return winningRecordMapper.queryByUserId(id);
	}

	/**
	 * 更新审核记录
	 */
	@Override
	public void updateStatus(CloudwinningrecordEntity entity) {
		logger.info("更新订单充值记录,{}",entity);
		winningRecordMapper.updateStatus(entity);
	}

	/**
	 * 批量数据审核
	 */
	@Override
	public void batchUpdate(List<CloudwinningrecordEntity> parList) {
		winningRecordMapper.batchUpdate(parList);
	}
}
