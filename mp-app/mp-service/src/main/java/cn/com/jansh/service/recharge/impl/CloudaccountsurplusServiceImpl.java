/**
 * CloudaccountsurplusServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月24日
 */
package cn.com.jansh.service.recharge.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.mapper.recharge.ICloudaccountsurplusMapper;
import cn.com.jansh.service.recharge.CloudaccountsurplusService;

/**
 * 账户余额实现类
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class CloudaccountsurplusServiceImpl implements CloudaccountsurplusService {

	private static final Logger logger = LogManager.getLogger(CloudaccountsurplusServiceImpl.class);
	
	@Autowired
	private ICloudaccountsurplusMapper cloudaccountsurplusMapper;
	
	/**
	 * 根据机构ID查询账户余额信息
	 */
	@Override
	public CloudaccountsurplusEntity queryByOrgid(String orgid) {
		logger.info("根据机构ID: {}查询账户信息",orgid);
		return cloudaccountsurplusMapper.queryByOrgid(orgid);
	}

	/**
	 * 新增充值记录
	 */
	@Override
	public void insertNewAmount(CloudaccountsurplusEntity casEntity) {
		logger.info("审核通过，信息充值记录：{}",casEntity);
		cloudaccountsurplusMapper.insertNewAmount(casEntity);
	}

	/**
	 * 根据机构ID更新账户余额信息
	 */
	@Override
	public void updateAccByOrgid(CloudaccountsurplusEntity casEntity) {
		logger.info("审核通过，信息充值记录：{}",casEntity);
		cloudaccountsurplusMapper.updateAccByOrgid(casEntity);
	}

	/**
	 * 账户余额查询
	 */
	@Override
	public List<CloudaccountsurplusEntity> queryAll(CloudaccountsurplusEntity accountSurplusEntity) {
		logger.info("查询账户余额：{}",accountSurplusEntity);
		
		List<CloudaccountsurplusEntity> list = cloudaccountsurplusMapper.queryAll(accountSurplusEntity);
		
		for(int i=0; i<list.size(); i++){
			
			//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			
			if(StringUtils.isNotBlank(list.get(i).getUpdatetime())) {
				list.get(i).setUpdatetime(DateUtil.formatDateTimestamp(list.get(i).getUpdatetime()));
			}
		}
		
		return list;
	}

}
