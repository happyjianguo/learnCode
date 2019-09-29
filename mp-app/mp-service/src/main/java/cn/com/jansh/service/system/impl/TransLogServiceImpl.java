package cn.com.jansh.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.system.OperLog;
import com.jansh.comm.util.DateUtil;
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
		if(LogList != null && LogList.size() !=0 ){
			for(int i=0;i<LogList.size();i++ ){
				LogList.get(i).setTransTime(DateUtil.formatDateTimestamp(LogList.get(i).getTransTime()));
			}
		}	
		LoggerManageModel loggerManageModel = new LoggerManageModel();
		loggerManageModel.setLoglist(LogList);
		loggerManageModel.setCount(logMapper.searchLogCount(logModel));
		return loggerManageModel;
	}
}
