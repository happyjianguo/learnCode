package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;

public interface CfBatchinfoMapper {

	/**
	 * 插入批量充值信息
	 * @param cfBatchrechargeEntity
	 */
	public void insertCfBatchinfo(CfBatchinfoEntity cfBatchinfoEntity);
	
	/**
	 * 更新批量充值信息
	 * @param batchid
	 */
	public void updataCfBatchinfo(CfBatchinfoEntity cfBatchinfoEntity);
	
	/**
	 * 批量更新充值状态
	 * @param cfBatchinfoEntity
	 */
	public int updataBatchCfBatchinfo( Map<String, Object> map);
	/**
	 * 删除批量充值信息
	 * @param batchid
	 */
	public void deleteCfBatchinfo(String batchid);
	
	/**
	 * 查询批次信息
	 * @param status
	 */
	public List<CfBatchinfoEntity> queryCfBatchinfo(String status);
	
	/**
	 * 取消上上传
	 * @param cfBatchinfoEntity
	 */
	public void rollback(CfBatchinfoEntity cfBatchinfoEntity);

	/**
	 * 通过acid查询
	 * @param acid
	 * @return
	 */
	public List<CfBatchinfoEntity> queryCfBatchinfoByAcid(String acid);
	
	/**
	 * 通过id查询批量充值信息
	 * @param id
	 * @return
	 */
	public CfBatchinfoEntity queryBatchinfo(String id);
	
	/**
	 * 通过acid查询
	 * @param acid
	 * @return
	 */
	public List<CfBatchinfoEntity> queryCfBatchinfoByBatchname(String batchname);
}
