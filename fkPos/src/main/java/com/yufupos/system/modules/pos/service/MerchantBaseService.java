package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.pos.dao.MerchantBaseDao;
import com.yufupos.system.modules.pos.dao.MerchantXDao;
import com.yufupos.system.modules.pos.entity.MerchantBase;
import com.yufupos.system.modules.pos.entity.MerchantX;

/**
 * 商户基本信息Service
 * @author llg
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class MerchantBaseService extends CrudService<MerchantBaseDao, MerchantBase> {
	
	@Autowired
	private MerchantBaseDao merchantBaseDao;
	
	@Autowired
	private MerchantXDao merchantXDao;	
	
	public MerchantBase get(String id) {
		return super.get(id);
	}
	
	public List<MerchantBase> findList(MerchantBase merchantBase) {
		return super.findList(merchantBase);
	}
	
	public Page<MerchantBase> findPage(Page<MerchantBase> page, MerchantBase merchantBase) {
		return super.findPage(page, merchantBase);
	}
	
	@Transactional(readOnly = false)
	public void save(MerchantBase merchantBase) {		
		if (merchantBase.getIsNewRecord()) {
			merchantBase.preInsert();
			merchantXDao.insert(merchantBase);
		}else{
			merchantBase.preUpdate();
			merchantXDao.update(merchantBase);
		}
		if (StringUtils.isEmpty(merchantBase.getFlagMerchantX())) {
			merchantBaseDao.update(merchantBase);
		}
	}
	
	@Transactional(readOnly = false)
	public void disableMerchant(MerchantBase merchantBase) {
		merchantBase.preUpdate();
		merchantBaseDao.disableMerchant(merchantBase);
		//若补充信息表中没有对应的数据，执行插入
		List<MerchantX> list = this.getMerchantById(merchantBase);
		if (list != null && list.size() > 0) {
			merchantXDao.disableMerchant(merchantBase);
		}else {
			merchantBase.preInsert();
			merchantXDao.insert(merchantBase);
		}
	}
	
	public List<MerchantX> getMerchantById(MerchantBase merchantBase) {
		return merchantXDao.getMerchantById(merchantBase.getMerchantId());
	}
	
	@Transactional(readOnly = false)
	public void usableMerchant(MerchantBase merchantBase) {
		merchantBase.preUpdate();
		merchantBaseDao.usableMerchant(merchantBase);
		merchantXDao.usableMerchant(merchantBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(MerchantBase merchantBase) {
		super.delete(merchantBase);
	}
	
	@Transactional(readOnly = false)
	public void updateStoreManager(MerchantBase merchantBase) throws Exception {
		MerchantBase base = merchantXDao.get(merchantBase.getMerchantId());
		if (base == null) {
			merchantBase.setRenewalType("AUTO");
			merchantBase.preInsert();
			merchantXDao.insert(merchantBase);
		} else {
			base.setMerchantArea(merchantBase.getMerchantArea());
			base.setStoreManager(merchantBase.getStoreManager());
			base.setMerchantAdvisor(merchantBase.getMerchantAdvisor());
			base.setAllotDate(merchantBase.getAllotDate());
			base.setSharer(merchantBase.getSharer());
			base.preUpdate();
			int result = merchantXDao.update(base);
			if (result <= 0) {
				throw new Exception("分配商户时更新信息失败");
			}
		}
	}
}