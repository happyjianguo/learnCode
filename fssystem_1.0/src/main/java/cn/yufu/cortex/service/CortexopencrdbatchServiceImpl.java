/**
 *包名:cn.yufu.cortex.service
 *描述:package cn.yufu.cortex.service;
 */
package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.cortex.dao.CortexopencrdbatchMapper;
import cn.yufu.system.common.utils.Log;

/**
 * CortexopencrdbatchServiceImpl.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月17日
 * 描述:根据订单号查询卡号、查询备付金余额
 */
@Service("cortex.CortexopencrdbatchService")
public class CortexopencrdbatchServiceImpl implements CortexopencrdbatchService {

	Log log = Log.getLog(CortexopencrdbatchServiceImpl.class);
	
	@Autowired
	@Qualifier("cortex.CortexopencrdbatchDAO")
	private CortexopencrdbatchMapper CortexopencrdbatchDAO;
	
	/**
	 * 根据订单号在主库中查出订单号对应卡号
	 * @param ordercode
	 * @return
	 */
	@Override
	public List<String> getCard(String ordercode) {
		log.debug("根据订单号在主库中查出订单号对应卡号", ordercode);
		return CortexopencrdbatchDAO.getCard(ordercode);
	}

	/**
	 * 
	 * 得到备付金余额
	 * @param ordercode
	 * @return
	 */
	@Override
	public String getCover(String ordercode) {
		log.debug("得到备付金余额", ordercode);
		return CortexopencrdbatchDAO.getCover(ordercode);
	}
	/**
	 * 
	 * 根据商户号得到商户名称
	 * @param ordercode
	 * @return
	 */
	@Override
	public String getMerchantName(String merchantnumber){
		log.debug("根据商户号得到商户名称", merchantnumber);
		return CortexopencrdbatchDAO.getMerchantName(merchantnumber);
	}
}
