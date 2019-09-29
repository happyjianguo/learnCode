package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
public interface CfBatchcurrbusilogMapper {
	/**
	 * 查询所有流水
	 * @return
	 */
	public List<CfBatchrechargeEntity> query(CfBatchrechargeEntity cfBatchrechargeEntity,@Param("start")int start,@Param("length")int length);
	/***
	 * 查询总页数
	 * @param cfCurrbusilogEntity
	 * @return
	 */
	public String searchLogCount(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	/**
	 * 查询所有
	 * @param cfBatchrechargeEntity
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryall(CfBatchrechargeEntity cfBatchrechargeEntity);
}
