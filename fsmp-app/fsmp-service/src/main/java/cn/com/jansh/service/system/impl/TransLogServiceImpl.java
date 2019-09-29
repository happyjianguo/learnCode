package cn.com.jansh.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.system.OperLog;
import cn.com.jansh.entity.system.LoggerManageModel;
import cn.com.jansh.mapper.system.TransLogMapper;
import cn.com.jansh.service.system.TransLogService;

@Service
public class TransLogServiceImpl implements TransLogService {
	@Autowired
	private TransLogMapper logMapper;

	@Override
	public LoggerManageModel searchLog(LoggerManageModel logModel) {

		List<OperLog> LogList = logMapper.searchLog(logModel);
		LoggerManageModel loggerManageModel = new LoggerManageModel();
		loggerManageModel.setLoglist(LogList);
		loggerManageModel.setCount(logMapper.searchLogCount(logModel));
		return loggerManageModel;
	}
}
