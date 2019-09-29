package cn.com.jansh.service.scheduler;

import java.util.Map;

public interface QCellCoreService {

	/**
	 * 查询归属地
	 * @param phone
	 * @return
	 */
	public Map<String,String> resultQCellCore(String phone);
}
