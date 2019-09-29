/**
 * RechargeRecordServiceImple.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月2日
 */
package cn.com.jansh.service.query.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.recharge.CloudrechargerecordEntity;
import cn.com.jansh.mapper.recharge.ICloudrechargerecordMapper;
import cn.com.jansh.service.query.RechargeRecordService;

/**
 * 充值记录查询实现
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class RechargeRecordServiceImple implements RechargeRecordService {
	
	private static final Logger logger = LogManager.getLogger(RechargeRecordServiceImple.class);
	
	@Autowired
	private ICloudrechargerecordMapper rechgerMapper;

	/**
	 * 查询充值记录
	 */
	@Override
	public List<CloudrechargerecordEntity> queryAll(CloudrechargerecordEntity rgdentity) {
		logger.info("充值记录查询: {}",rgdentity);
		return rechgerMapper.queryAll(rgdentity);
	}

}
