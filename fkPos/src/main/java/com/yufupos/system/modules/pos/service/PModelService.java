package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.PModel;
import com.yufupos.system.modules.pos.dao.PModelDao;

/**
 * 型号信息Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PModelService extends CrudService<PModelDao, PModel> {

	public PModel get(String id) {
		return super.get(id);
	}
	
	public List<PModel> findList(PModel pModel) {
		return super.findList(pModel);
	}
	
	public Page<PModel> findPage(Page<PModel> page, PModel pModel) {
		return super.findPage(page, pModel);
	}
	
	@Transactional(readOnly = false)
	public void save(PModel pModel) {
		super.save(pModel);
	}
	
	@Transactional(readOnly = false)
	public void delete(PModel pModel) {
		super.delete(pModel);
	}
	
}