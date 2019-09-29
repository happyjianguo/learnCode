/**
 *包名:cn.yufu.posp.dao
 *描述:package cn.yufu.posp.dao;
 */
package cn.yufu.posp.dao;

import org.springframework.stereotype.Repository;

import cn.yufu.posp.entity.MerchantX;

/**
 * MerchantXMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月1日
 * 描述:商户扩展表
 */
@Repository("posp.MerchantXDao")
public interface MerchantXMapper {

	public void insertSelective(MerchantX merchantX);

	public void delete(MerchantX merchantX);

	public void update(MerchantX merchantX);

	public MerchantX selectByid(MerchantX merchantX);

}
