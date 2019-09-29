package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;

public interface CfBatchrechargeMapper {

	/**
	 * 插入批量充值(批量插入)
	 * @param cfBatchrechargeEntity
	 */
	public void insertCfBatchrecharge(List<CfBatchrechargeEntity> cfBatchList);
	/**
	 * 查询批量充值记录
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryBatchrechargeli(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	/**
	 * 修改批量状态
	 * @param cfBatchrechargeEntity
	 */
	public void updataBatchrecharge(CfBatchinfoEntity cfBatchinfoEntity);
	
	/**
	 * 更新
	 * @param cfBatchrechargeEntity
	 */
	public int updateBatch(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	public void updateBatchByBizid(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	/**
	 * 取消上上传(删除)
	 * @param cfBatchinfoEntity
	 */
	public void rollback(CfBatchinfoEntity cfBatchinfoEntity);
	
	/**
	 * 查询批量充值记录
	 * @param batchid
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryStatusByBatchid(String batchid);
	
	/**
	 * 根据批量充值信息表中审批通过的批次查询批量充值表中的待充值数据
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryBatchrechargeByInfo(@Param("list")List<String> list);
	
	/**
	 * 根据批量充值表中的一条记录获取接入者报价信息
	 */
	public CfAccesspriceEntity queryAPInfoByBatchrecharge(@Param("id")String id,@Param("province")String province,@Param("status")String status);
	
	/**
	 * 根据批量充值表中的一条记录获取供应商报价信息
	 */
	public CfSupplierpriceEntity querSPInfoByBatchrecharge(@Param("province")String province,@Param("id")String id,@Param("status")String status);
}
