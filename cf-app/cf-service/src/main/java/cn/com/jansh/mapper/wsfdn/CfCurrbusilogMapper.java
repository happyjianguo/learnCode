package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;

/**
 * cf_currbusilog 表
 * @author gll
 *
 */
public interface CfCurrbusilogMapper {

	/**
	 * 插入充值记录
	 * @param rechargeToCurrbusilog
	 */
	public void insert(CfCurrbusilogEntity cfCurrbusilogEntity);
	
	/**
	 * 更新充值记录
	 * @param cfCurrbusilogEntity
	 */
	public int update(CfCurrbusilogEntity cfCurrbusilogEntity);

	/**
	 * 查询所有流水
	 * @return
	 */
	public List<CfCurrbusilogEntity> query(CfCurrbusilogEntity cfCurrbusilogEntity,@Param("start")int start,@Param("length")int length);

	public List<CfCurrbusilogEntity> queryreport(CfCurrbusilogEntity cfCurrbusilogEntity);

	/**
	 * 查询单个流水信息通过流水号
	 * @param systransno
	 * @return
	 */
	public CfCurrbusilogEntity queryCurrbusilogByid(String bizid);
	
	/***
	 * 查询总页数
	 * @param cfCurrbusilogEntity
	 * @return
	 */
	public String searchLogCount(CfCurrbusilogEntity cfCurrbusilogEntity);
	
	public String searchReportCount(CfCurrbusilogEntity cfCurrbusilogEntity);
	/**
	 * 查询状态为status的订单
	 * @param status
	 * @return
	 */
	public List<CfCurrbusilogEntity> selectByStatus(List<String> li);

	/**
	 * 查询所有流水（不带分页）
	 * @return
	 */
	public List<CfCurrbusilogEntity> queryall(CfCurrbusilogEntity cfCurrbusilogEntity);
	
	/**
	 * 查询订单编号为spno的订单
	 * @param orderid
	 * @return
	 */
	public CfCurrbusilogEntity selectByorderid(String orderid);
	



	
}
