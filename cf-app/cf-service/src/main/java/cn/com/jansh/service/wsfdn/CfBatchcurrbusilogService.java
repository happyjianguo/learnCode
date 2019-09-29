package cn.com.jansh.service.wsfdn;

import java.util.List;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
/**
 * 批量充值service
 * @author gll
 *
 */
public interface CfBatchcurrbusilogService {
	/**
	 * 查询总页数
	 * 
	 * @param cfBatchrechargeEntity
	 * @return
	 */
	public String searchLogCount(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	/**
	 * 查询数据库中所有的流水记录（带分页）
	 * @param cfCurrbusilogEntity 
	 * 
	 * @return
	 */
	public List<CfBatchrechargeEntity> batchcurrbusilog(CfBatchrechargeEntity cfBatchrechargeEntity,String start,String length);
	/**
	 * 查询数据库中所有的流水记录（带分页）
	 * @param cfCurrbusilogEntity 
	 * 
	 * @return
	 */
	
	/**
	 * 不带分页
	 * @param cfBatchrechargeEntity
	 * @return
	 */
	public List<CfBatchrechargeEntity> batchcurrbusilog(CfBatchrechargeEntity cfBatchrechargeEntity);
}
