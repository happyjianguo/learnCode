package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.TerminalKey;
import com.yufupos.system.modules.pos.dao.TerminalKeyDao;

/**
 * 终端主密钥Controller
 * @author ZQK
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class TerminalKeyService extends CrudService<TerminalKeyDao, TerminalKey> {

	public TerminalKey get(TerminalKey terminalKey) {
		return super.get(terminalKey);
	}
	
	public List<TerminalKey> findList(TerminalKey terminalKey) {
		return super.findList(terminalKey);
	}
	
	public Page<TerminalKey> findPage(Page<TerminalKey> page, TerminalKey terminalKey) {
		return super.findPage(page, terminalKey);
	}
	
	@Transactional(readOnly = false)
	public void save(TerminalKey terminalKey) {
		super.save(terminalKey);
	}
	
	@Transactional(readOnly = false)
	public void delete(TerminalKey terminalKey) {
		super.delete(terminalKey);
	}
	
}