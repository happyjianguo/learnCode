/**
 * RechargeRecordService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月2日
 */
package cn.com.jansh.service.query;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudrechargerecordEntity;

/**
 * 查询充值记录使用接口
 * @author xieliangliang
 * @version 1.0
 */
public interface RechargeRecordService {
	
	
	/**
	 * 查询充值记录
	 */
	public List<CloudrechargerecordEntity> queryAll(CloudrechargerecordEntity rgdentity);

}
