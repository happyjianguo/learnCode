package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.MerchantDoubleRate;
import cn.yufu.system.modules.cortexs.dao.MerchantDoubleRateDao;

/**
 * 商户双费率Service
 * @author LLG
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class MerchantDoubleRateService extends CrudService<MerchantDoubleRateDao, MerchantDoubleRate> {

	public MerchantDoubleRate get(String id) {
		return super.get(id);
	}
	
	public List<MerchantDoubleRate> findList(MerchantDoubleRate merchantDoubleRate) {
		return super.findList(merchantDoubleRate);
	}
	
	public Page<MerchantDoubleRate> findPage(Page<MerchantDoubleRate> page, MerchantDoubleRate merchantDoubleRate) {
		return super.findPage(page, merchantDoubleRate);
	}
	
	@Transactional(readOnly = false)
	public void save(MerchantDoubleRate merchantDoubleRate) {
		super.save(merchantDoubleRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(MerchantDoubleRate merchantDoubleRate) {
		super.delete(merchantDoubleRate);
	}
	
}