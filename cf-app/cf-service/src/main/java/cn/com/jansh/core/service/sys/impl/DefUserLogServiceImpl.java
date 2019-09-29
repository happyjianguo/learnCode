package cn.com.jansh.core.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.IMUserLog;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.service.sys.DefUserLogService;

/**
 * 日志管理业务
 *
 * @author Mr.wong
 */
@Service
public class DefUserLogServiceImpl implements DefUserLogService {

	@Autowired
	private SysCoreMapper sysCoreMapper;

	/**
	 * 插入操作日志
	 */
	@Override
	public void insertLog(IMUserLog imUserLog) {
		sysCoreMapper.insertUserLog(imUserLog);
	}

}
