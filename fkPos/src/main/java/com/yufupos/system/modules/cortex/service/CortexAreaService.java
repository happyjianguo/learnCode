package com.yufupos.system.modules.cortex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.cortex.entity.CortexArea;
import com.yufupos.system.modules.cortex.dao.CortexAreaDao;

/**
 * 省市区信息Service
 * @author llg
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class CortexAreaService extends CrudService<CortexAreaDao, CortexArea> {

	public CortexArea get(String id) {
		return super.get(id);
	}
	
	public List<CortexArea> findList(CortexArea cortexArea) {
		return super.findList(cortexArea);
	}
	
	public Page<CortexArea> findPage(Page<CortexArea> page, CortexArea cortexArea) {
		return super.findPage(page, cortexArea);
	}
	
	@Transactional(readOnly = false)
	public void save(CortexArea cortexArea) {
		super.save(cortexArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(CortexArea cortexArea) {
		super.delete(cortexArea);
	}
	
}