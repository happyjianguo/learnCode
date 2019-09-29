package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.EdcTerminalDao;
import com.yufupos.system.modules.pos.entity.EdcTerminal;

/**
 * 终端补充信息Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class EdcTerminalXService extends CrudService<EdcTerminalDao, EdcTerminal> {

	public EdcTerminal get(String id) {
		return super.get(id);
	}
	
	public List<EdcTerminal> findList(EdcTerminal EdcTerminal) {
		return super.findList(EdcTerminal);
	}
	
	public Page<EdcTerminal> findPage(Page<EdcTerminal> page, EdcTerminal EdcTerminal) {
		return super.findPage(page, EdcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void save(EdcTerminal EdcTerminal) {
		super.save(EdcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdcTerminal EdcTerminal) {
		super.delete(EdcTerminal);
	}
	
}