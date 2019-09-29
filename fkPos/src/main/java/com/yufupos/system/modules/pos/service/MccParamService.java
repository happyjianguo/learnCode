package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.MccParam;
import com.yufupos.system.modules.pos.dao.MccParamDao;

/**
 * MCCService
 * @author llg
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class MccParamService extends CrudService<MccParamDao, MccParam> {

	public MccParam get(String id) {
		return super.get(id);
	}
	
	public List<MccParam> findList(MccParam mccParam) {
		return super.findList(mccParam);
	}
	
	public Page<MccParam> findPage(Page<MccParam> page, MccParam mccParam) {
		return super.findPage(page, mccParam);
	}
	
	@Transactional(readOnly = false)
	public void save(MccParam mccParam) {
		super.save(mccParam);
	}
	
	@Transactional(readOnly = false)
	public void delete(MccParam mccParam) {
		super.delete(mccParam);
	}
	
}