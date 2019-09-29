package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.PSimActLog;
import com.yufupos.system.modules.pos.dao.PSimActLogDao;

/**
 * SIM卡操作记录Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PSimActLogService extends CrudService<PSimActLogDao, PSimActLog> {

	public PSimActLog get(String id) {
		return super.get(id);
	}
	
	public List<PSimActLog> findList(PSimActLog pSimActLog) {
		return super.findList(pSimActLog);
	}
	
	public Page<PSimActLog> findPage(Page<PSimActLog> page, PSimActLog pSimActLog) {
		return super.findPage(page, pSimActLog);
	}
	
	@Transactional(readOnly = false)
	public void save(PSimActLog pSimActLog) {
		super.save(pSimActLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(PSimActLog pSimActLog) {
		super.delete(pSimActLog);
	}
	
}