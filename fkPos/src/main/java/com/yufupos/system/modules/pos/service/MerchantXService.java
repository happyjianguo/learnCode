package com.yufupos.system.modules.pos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.MerchantBaseDao;
import com.yufupos.system.modules.pos.dao.MerchantXDao;
import com.yufupos.system.modules.pos.entity.MerchantBase;

/**
 * 商户补充信息Service
 * 
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class MerchantXService extends CrudService<MerchantBaseDao, MerchantBase> {
	
	@Autowired
	private MerchantXDao merchantXDao;	
	
	public MerchantBase get(String id) {
		return super.get(id);
	}

	public List<MerchantBase> findList(MerchantBase MerchantBase) {
		return super.findList(MerchantBase);
	}

	public Page<MerchantBase> findPage(Page<MerchantBase> page,
			MerchantBase MerchantBase) {
		return super.findPage(page, MerchantBase);
	}

	@Transactional(readOnly = false)
	public void save(MerchantBase MerchantBase) {
		super.save(MerchantBase);
	}

	@Transactional(readOnly = false)
	public void delete(MerchantBase MerchantBase) {
		super.delete(MerchantBase);
	}
	
	@Transactional(readOnly = false)
	public void updateTypeYf(String merchantId,Integer typeYf) {
		merchantXDao.updateTypeYf(merchantId, typeYf);
	}
	
	@Transactional(readOnly = false)
	public void updateCreateDate(String merchantId,Date createDate) {
		merchantXDao.updateCreateDate(merchantId, createDate);
	}
	
}