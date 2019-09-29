/**
 * WinningrecordService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月3日
 */
package cn.com.jansh.service.query;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudwinningrecordEntity;

/**
 * 订单中奖记录接口类
 * @author xieliangliang
 * @version 1.0
 */
public interface WinningrecordService {

	/**
	 * 查询订单记录
	 */
	public List<CloudwinningrecordEntity> queryAll(CloudwinningrecordEntity entity);

	/**
	 * 根据ID查询
	 */
	public CloudwinningrecordEntity queryByUserId(String id);

	/**
	 * 更新审核状态
	 */
	public void updateStatus(CloudwinningrecordEntity entity);

	/**
	 * 批量更新审核状态
	 */
	public void batchUpdate(List<CloudwinningrecordEntity> parList);

}
