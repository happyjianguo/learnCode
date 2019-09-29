/**
 * IWinningRecordMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月3日
 */
package cn.com.jansh.mapper.query;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudwinningrecordEntity;

/**
 * 中奖订单相关数据库接口
 * @author xieliangliang
 * @version 1.0
 */
public interface IWinningRecordMapper {

	/**
	 * 查询订单中奖记录相关方法
	 */
	public List<CloudwinningrecordEntity> queryAll(CloudwinningrecordEntity entity);

	/**
	 * 根据ID查询订单审核数据
	 */
	public CloudwinningrecordEntity queryByUserId(String id);

	/**
	 * 更新订单审核状态
	 */
	public void updateStatus(CloudwinningrecordEntity entity);

	/**
	 * 批量更新审核状态
	 */
	public void batchUpdate(List<CloudwinningrecordEntity> parList);

}
