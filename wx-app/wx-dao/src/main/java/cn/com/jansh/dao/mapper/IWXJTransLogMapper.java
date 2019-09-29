package cn.com.jansh.dao.mapper;

import java.util.List;
import java.util.Map;

import cn.com.jansh.dao.entity.WXJTransLog;

public interface IWXJTransLogMapper {
	
	/**
	 * 保存记录
	 * TODO
	 * @param wXJTransLog
	 * @return
	 */
	public int insertTransLog(WXJTransLog wXJTransLog);
	
}
