/**
 *包名:cn.yufu.cortex.service
 *描述:package cn.yufu.cortex.service;
 */
package cn.yufu.cortex.service;

import java.util.List;

/**
 * CortexopencrdbatchService.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月17日
 * 描述:根据订单号查询卡号、查询备付金余额
 */
public interface CortexopencrdbatchService {

	/**
	 * 根据订单号在主库中查出订单号对应卡号
	 * @param ordercode
	 * @return
	 */
	public List<String> getCard(String ordercode);
	/**
	 * 
	 * 得到备付金余额
	 * @param ordercode
	 * @return
	 */
	public String getCover(String ordercode);
	/**
	 * 
	 * 根据商户号得到商户名称
	 * @param ordercode
	 * @return
	 */
	public String getMerchantName(String merchantnumber);
}
