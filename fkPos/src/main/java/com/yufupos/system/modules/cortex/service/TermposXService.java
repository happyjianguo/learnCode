package com.yufupos.system.modules.cortex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.cortex.dao.TermposXDao;
import com.yufupos.system.modules.cortex.entity.TermposX;

/**
 * 报表展示Service
 * 
 * @author zqk
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class TermposXService extends CrudService<TermposXDao, TermposX> {
	
	public TermposX get(String id) {
		return super.get(id);
	}
	
	public List<TermposX> getOneData(TermposX termposX) {
		return super.getOneData(termposX);
	}
	
	public List<TermposX> findList(TermposX termposX) {
		return super.findList(termposX);
	}
	
	public Page<TermposX> findPage(Page<TermposX> page, TermposX termposX) {
		return super.findPage(page, termposX);
	}
	
}
