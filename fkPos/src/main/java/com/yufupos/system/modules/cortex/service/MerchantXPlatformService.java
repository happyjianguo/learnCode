package com.yufupos.system.modules.cortex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.cortex.dao.MerchantXPlatformDao;
import com.yufupos.system.modules.cortex.entity.MerchantXPlatform;

/**
 * 商户补充信息Service
 * 
 * @author ZQK
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class MerchantXPlatformService extends CrudService<MerchantXPlatformDao, MerchantXPlatform> {
	
	public MerchantXPlatform get(String id) {
		return super.get(id);
	}
	
	public List<MerchantXPlatform> getOneData(MerchantXPlatform merchantX) {
		return super.getOneData(merchantX);
	}
	
	public List<MerchantXPlatform> findList(MerchantXPlatform merchantX) {
		return super.findList(merchantX);
	}
	
	public Page<MerchantXPlatform> findPage(Page<MerchantXPlatform> page, MerchantXPlatform merchantX) {
		return super.findPage(page, merchantX);
	}
	
}
