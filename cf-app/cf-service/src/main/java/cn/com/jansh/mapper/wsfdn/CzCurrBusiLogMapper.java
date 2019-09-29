package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.wsfdn.CzCurrBusiLog;
import cn.com.jansh.entity.wsfdn.CzCurrBusiLogModel;

public interface CzCurrBusiLogMapper {

	/**
	 * 插入流水信息
	 * @param czCurrBusiLog
	 */
	public void insert(CzCurrBusiLog czCurrBusiLog);

	/**
	 * 更新流水信息
	 * @param czCurrBusiLog
	 * @return
	 */
	public int update(CzCurrBusiLog czCurrBusiLog);

	public List<CzCurrBusiLog> searchLog(Map<String,String> map,int page,int pagesize);
	
	public int searchLogCount(Map<String,String> map);
	
	public List<CzCurrBusiLog> queryBuyerInfoUncheckSize(CzCurrBusiLogModel czCurrBusiLogModel);
	

}
