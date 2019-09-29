package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;

public interface CfCurrbusilogService {

	/**
	 * 将充值信息存入充值记录表
	 * @param rechargeToCurrbusilog
	 * @param cfAccesspriceEntity 
	 */
	public void insert(CfCurrbusilogEntity rechargeToCurrbusilog);

	/**
	 * 查询数据库中所有的流水记录（带分页）
	 * @param cfCurrbusilogEntity 
	 * 
	 * @return
	 */
	public List<CfCurrbusilogEntity> queryCurrbusilog(CfCurrbusilogEntity cfCurrbusilogEntity,String start,String length);

	/**
	 * 查询总页数
	 * 
	 * @param currbusilogModel
	 * @return
	 */
	public String searchLogCount(CfCurrbusilogEntity cfCurrbusilogEntity);

	/**
	 * 查询数据库中的所有流水记录（不带分页）
	 */
	public List<CfCurrbusilogEntity> queryAllCurrbusilog(CfCurrbusilogEntity cfCurrbusilogEntity);


}
