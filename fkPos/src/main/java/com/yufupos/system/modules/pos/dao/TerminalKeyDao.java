package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.TerminalKey;

/**
 * 终端主密钥Controller
 * @author ZQK
 * @version 2018-06-12
 */
@MyBatisDao
public interface TerminalKeyDao extends CrudDao<TerminalKey> {
	
}