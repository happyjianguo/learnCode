package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfRechargeEntity;

/**
 * 单笔充值
 * @author duanmuyn
 */
public interface CfRechargeMapper {
	/**
	 * 插入充值信息
	 * @param cfRechargeEntity
	 */
	public void insert(CfRechargeEntity cfRechargeEntity);
	
	/**
	 * 通过status查询充值信息
	 * @param status
	 */
	public List<CfRechargeEntity> selectByStatus(String status);

	/**
	 * 更新status状态
	 * @param id
	 */
	public int updateStatus(CfRechargeEntity cfRechargeEntity);
	
	public int updateStatusByBizid(CfRechargeEntity cfRechargeEntity);
	/**
	 * 根据bizid查询单笔充值
	 * @param bizid
	 * @return
	 */
	public CfRechargeEntity queryByidAndSysid(@Param("orderid")String orderid,@Param("sysid")String sysid);
	
	/**
	 * 查询待发送的回调数据
	 * @param source
	 * @param cbstatus
	 * @return
	 */
	public List<CfRechargeEntity> queryCallBack(@Param("source")String source,@Param("cbstatus")String cbstatus,@Param("status")String status);
}

