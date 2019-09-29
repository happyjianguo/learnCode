package cn.com.jansh.mapper.system;

import java.util.List;

import com.jansh.core.entity.sys.IMUserLog;

public interface IMUserLogMapper {

	public void insert(IMUserLog imUserLog);
	
	public List<IMUserLog> selectAllLogs();
	
	public void updateLog(IMUserLog imUserLog);
	
	public void deleteLog(String logId);
}
