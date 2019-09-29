package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.EdcTerminal;

/**
 * 终端信息DAO接口
 * @author llg
 * @version 2017-04-19
 */
@MyBatisDao
public interface EdcTerminalDao extends CrudDao<EdcTerminal> {
	
	void disableTerminal(EdcTerminal edcTerminal);
	
	void usableTerminal(EdcTerminal edcTerminal);
	
	void updateSetAddr(EdcTerminal edcTerminal);
	
}