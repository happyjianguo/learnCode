package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfRechargeEntity;

/**
 * 单笔充值
 * @author duanmuyn
 *
 */
public interface CfRechargeService {
	/**
	 * 插入充值信息
	 * @param cfRechargeEntity
	 */
	public void insert(CfRechargeEntity cfRechargeEntity);
	
	/**
	 * 通过状态查询出充值记录列表
	 * @param status
	 */
	public List<CfRechargeEntity> selectRechargeByStatus(String status);
	
}

