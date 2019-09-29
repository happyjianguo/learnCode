package cn.com.jansh.mapper.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.jansh.entity.system.OperLog;
import cn.com.jansh.entity.system.LoggerManageModel;

@Repository
public interface TransLogMapper {


	public int searchLogCount(LoggerManageModel logModel);

	public List<OperLog> searchLog(LoggerManageModel logModel);

}
