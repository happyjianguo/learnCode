package com.yufupos.system.modules.bak.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.bak.dao.TermposXBakDao;
import com.yufupos.system.modules.bak.entity.TermposXBak;

/**
 * 报表展示Service
 * 
 * @author zqk
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class TermposXBakService extends CrudService<TermposXBakDao, TermposXBak> {
	
	public TermposXBak get(String id) {
		return super.get(id);
	}
	
	public List<TermposXBak> getOneData(TermposXBak termposX) {
		return super.getOneData(termposX);
	}
	
	public List<TermposXBak> findList(TermposXBak termposX) {
		return super.findList(termposX);
	}
	
	public Page<TermposXBak> findPage(Page<TermposXBak> page, TermposXBak termposX) {
		return super.findPage(page, termposX);
	}
	
}
