package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.PFactory;
import com.yufupos.system.modules.pos.dao.PFactoryDao;

/**
 * 厂商信息Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PFactoryService extends CrudService<PFactoryDao, PFactory> {

	public PFactory get(String id) {
		return super.get(id);
	}
	
	public List<PFactory> findList(PFactory pFactory) {
		return super.findList(pFactory);
	}
	
	public Page<PFactory> findPage(Page<PFactory> page, PFactory pFactory) {
		return super.findPage(page, pFactory);
	}
	
	@Transactional(readOnly = false)
	public void save(PFactory pFactory) {
		super.save(pFactory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PFactory pFactory) {
		super.delete(pFactory);
	}
	
}