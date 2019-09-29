package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.SuspiciousOrderEarlyWarning;

public interface SuspiciousOrderEarlyWarningService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(SuspiciousOrderEarlyWarning queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<SuspiciousOrderEarlyWarning> queryList(SuspiciousOrderEarlyWarning queryModel, int startResult, int endResult);
	
	/**
	 * 报表导出
	 * 
	 * @param queryModel
	 * @return
	 */
	public List<SuspiciousOrderEarlyWarning> queryList(SuspiciousOrderEarlyWarning queryModel);
	
}
