/**
 *包名:cn.yufu.cortex.dao
 *描述:package cn.yufu.cortex.dao;
 */
package cn.yufu.cortex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * CortexopencrdbatchMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月17日
 * 描述:根据订单号查询卡号、查询备付金余额
 */
@Repository("cortex.CortexopencrdbatchDAO")
public interface CortexopencrdbatchMapper {

	/**
	 * 
	 * 根据订单号在主库中查出订单号对应卡号
	 * @param ordercode
	 * @return
	 */
	public List<String> getCard(@Param("ordercode") String ordercode);

	/**
	 * 
	 * 得到备付金余额
	 * @param ordercode
	 * @return
	 */
	public String getCover(@Param("ordercode") String ordercode);

	/**
	 * 
	 * 根据商户号得到商户名称
	 * @param ordercode
	 * @return
	 */
	public String getMerchantName(@Param("merchantnumber") String merchantnumber);

}
