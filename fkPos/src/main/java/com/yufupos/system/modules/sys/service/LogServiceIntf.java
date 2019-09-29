package com.yufupos.system.modules.sys.service;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.modules.sys.entity.Log;

public interface LogServiceIntf {
	public Page<Log> findPage(Page<Log> page, Log log);

	public int insert(Log log);
}
