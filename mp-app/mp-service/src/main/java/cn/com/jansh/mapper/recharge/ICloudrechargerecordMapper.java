/**
 * ICloudrechargerecordMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月25日
 */
package cn.com.jansh.mapper.recharge;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudrechargerecordEntity;

/**
 * 充值记录流水表操作接口
 * @author xieliangliang
 * @version 1.0
 */
public interface ICloudrechargerecordMapper {

	/**
	 * 保存充值记录流水表
	 */
	public void saveRecordData(CloudrechargerecordEntity clodrechEntity);

	/**
	 * 查询充值记录
	 */
	public List<CloudrechargerecordEntity> queryAll(CloudrechargerecordEntity rgdentity);

}
