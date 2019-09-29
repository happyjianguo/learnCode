/**
 * Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.modules.sys.dao.LogDao;
import com.yufupos.system.modules.sys.entity.Log;

/**
 * 日志Service
 * @author king
 * @version 2014-05-16
 */
@Service("logService")
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> implements LogServiceIntf{
	@Autowired LogDao logDao;
	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}

	@Override
	public int insert(Log log) {
		return logDao.insert(log);
	}
	
}
