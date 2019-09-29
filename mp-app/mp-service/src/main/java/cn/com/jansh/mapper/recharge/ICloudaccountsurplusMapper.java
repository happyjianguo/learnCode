/**
 * ICloudaccountsurplusMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月24日
 */
package cn.com.jansh.mapper.recharge;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;

/**
 * 账户余额数据接口
 * @author xieliangliang
 * @version 1.0
 */
public interface ICloudaccountsurplusMapper {

	/**
	 * 根据机构ID查询账户余额信息
	 */
	public CloudaccountsurplusEntity queryByOrgid(String orgid);

	/**
	 * 新增充值记录账户余额
	 */
	public void insertNewAmount(CloudaccountsurplusEntity casEntity);

	/**
	 * 根据机构ID更新账户余额信息
	 */
	public void updateAccByOrgid(CloudaccountsurplusEntity casEntity);

	/**
	 * 查询账户余额
	 */
	public List<CloudaccountsurplusEntity> queryAll(CloudaccountsurplusEntity accountSurplusEntity);

}
