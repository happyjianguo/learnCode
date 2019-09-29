package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.PPosActLog;
import com.yufupos.system.modules.pos.dao.PPosActLogDao;

/**
 * POS机操作记录Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PPosActLogService extends CrudService<PPosActLogDao, PPosActLog> {

	public PPosActLog get(String id) {
		return super.get(id);
	}
	
	public List<PPosActLog> findList(PPosActLog pPosActLog) {
		return super.findList(pPosActLog);
	}
	
	public Page<PPosActLog> findPage(Page<PPosActLog> page, PPosActLog pPosActLog) {
		return super.findPage(page, pPosActLog);
	}
	
	@Transactional(readOnly = false)
	public void save(PPosActLog pPosActLog) {
		super.save(pPosActLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(PPosActLog pPosActLog) {
		super.delete(pPosActLog);
	}
	
}