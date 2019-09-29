package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.modules.pos.dao.EdcTerminalDao;
import com.yufupos.system.modules.pos.dao.EdcTerminalXDao;
import com.yufupos.system.modules.pos.dao.EdcTerminalXUTDao;
import com.yufupos.system.modules.pos.entity.EdcTerminal;
import com.yufupos.system.modules.pos.entity.EdcTerminalX;

/**
 * 终端信息Service
 * @author llg
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class EdcTerminalService extends CrudService<EdcTerminalDao, EdcTerminal> {
	
	@Autowired
	private EdcTerminalDao edcTerminalDao;
	
	@Autowired
	private EdcTerminalXDao edcTerminalXDao;
	
	@Autowired
	private EdcTerminalXUTDao edcTerminalXUTDao;
	
	public EdcTerminal get(String id) {
		return super.get(id);
	}
	
	public List<EdcTerminalX> getEdcTerminalX(String id) {
		return edcTerminalXDao.getEdcTerminalX(id);
	}
	
	public List<EdcTerminal> getOneData(String terminalId, String merchantId) {
		EdcTerminal edcTerminal = new EdcTerminal();
		edcTerminal.setTerminalId(terminalId);
		edcTerminal.setMerchantId(merchantId);
		return super.getOneData(edcTerminal);
	}
	
	public List<EdcTerminal> findList(EdcTerminal edcTerminal) {
		return super.findList(edcTerminal);
	}
	
	public Page<EdcTerminal> findPage(Page<EdcTerminal> page, EdcTerminal edcTerminal) {
		return super.findPage(page, edcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void save(EdcTerminal edcTerminal) {
		if (StringUtils.isNotBlank(edcTerminal.getUpgradeVersion())) {
			int parseInt = 0;
			try {
				parseInt = Integer.parseInt(edcTerminal.getUpgradeVersion());
			} catch (NumberFormatException e) {}
			if (parseInt != 0) {
				edcTerminal.setHistoryVersion((parseInt - 1) + "");
			}
		}else{
			edcTerminal.setUpgradeVersion("0");
			edcTerminal.setHistoryVersion("");
		}
		List<EdcTerminalX> edcTerminalX = this.getEdcTerminalX(edcTerminal.getTerminalId());
		if (edcTerminalX == null || edcTerminalX.size() == 0) {
			edcTerminal.preInsert();
			edcTerminalXDao.insert(edcTerminal);
		}else {
			edcTerminal.preUpdate();
			edcTerminalXDao.update(edcTerminal);
		}
		if (StringUtils.isEmpty(edcTerminal.getFlagMerchantX())) {
			edcTerminalDao.update(edcTerminal);
		}
	}
	
	@Transactional(readOnly = false)
	public void disableTerminal(EdcTerminal edcTerminal) {
		edcTerminal.preUpdate();
		edcTerminalDao.disableTerminal(edcTerminal);
		EdcTerminalX edcTerminalX = null;
		edcTerminalX = edcTerminalXUTDao.get(edcTerminal.getTerminalId());
		if (edcTerminalX == null || StringUtils.isEmpty(edcTerminalX.getTerminalId())) {
			edcTerminal.preInsert();
			edcTerminalXDao.insert(edcTerminal);
		}else {
			edcTerminalXDao.disableTerminal(edcTerminal);
		}
	}
	
	@Transactional(readOnly = false)
	public void usableTerminal(EdcTerminal edcTerminal) {
		edcTerminal.preUpdate();
		edcTerminalDao.usableTerminal(edcTerminal);
		edcTerminalXDao.usableTerminal(edcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void updateSetAddr(EdcTerminal edcTerminal) {
		edcTerminal.preUpdate();
		edcTerminalDao.updateSetAddr(edcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdcTerminal edcTerminal) {
		super.delete(edcTerminal);
	}
	
	@Transactional(readOnly = false)
	public void upgradeTerminal(String terminalId,String upgradeDate) throws Exception{
		EdcTerminalX edcTerminalX = null;
		edcTerminalX = edcTerminalXUTDao.get(terminalId);
		if (edcTerminalX == null) {
			edcTerminalX = new EdcTerminalX();
			edcTerminalX.setUpgradeDate(upgradeDate);
			edcTerminalX.setTerminalId(terminalId);
			edcTerminalX.setUpgradeVersion("0");
			edcTerminalX.setHistoryVersion("");
			edcTerminalX.preInsert();
			edcTerminalXUTDao.insert(edcTerminalX);
		}else {
			edcTerminalX.setUpgradeDate(upgradeDate);
			if (StringUtils.isEmpty(edcTerminalX.getUpgradeVersion())) {
				edcTerminalX.setUpgradeVersion("0");
				edcTerminalX.setHistoryVersion("");
			}else {
				edcTerminalX.setHistoryVersion(edcTerminalX.getUpgradeVersion());
				int parseInt = Integer.parseInt(edcTerminalX.getUpgradeVersion());
				edcTerminalX.setUpgradeVersion(parseInt+1+"");
				if (edcTerminalX.getUpgradeVersion().length() > 8) {
					edcTerminalX.setUpgradeVersion("1");
					edcTerminalX.setHistoryVersion("0");
				}
			}
			edcTerminalX.preUpdate();
			edcTerminalXUTDao.update(edcTerminalX);
		}
	}
	
	@Transactional(readOnly = false)
	public void reset() throws Exception{
		edcTerminalXUTDao.resetHistoryVersion();
	}
	
	@Transactional(readOnly = false)
	public void importUpdate(EdcTerminalX edcTerminalX) {
		edcTerminalXDao.importUpdate(edcTerminalX);
	}
	
	@Transactional(readOnly = false)
	public void importInsert(EdcTerminalX edcTerminalX) {
		edcTerminalXDao.importInsert(edcTerminalX);
	}
}