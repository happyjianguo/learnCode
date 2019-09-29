package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;

public interface CfBatchinfoService {

	/**
	 * 查询批次信息
	 * @param status
	 */
	public List<CfBatchinfoEntity> queryCfBatchinfo(String status);

	/**
	 * 查询批次信息
	 * @param acid
	 * @return
	 */
	public List<CfBatchinfoEntity> queryBatchinfoByAccessclient(String acid);
	/**按批次名称查询*/
	public List<CfBatchinfoEntity> queryBatchinfoByBatchname(String batchname);
	
	
}
