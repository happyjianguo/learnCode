package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.List;

import cn.yufu.fs.entity.OldCrdfeelogBefore;

public interface OldCrdfeelogBeforeService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(OldCrdfeelogBefore queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<OldCrdfeelogBefore> queryList(OldCrdfeelogBefore queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<OldCrdfeelogBefore> queryList(OldCrdfeelogBefore queryModel);
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public OldCrdfeelogBefore selectByPrimaryKey(String cardTypeId);
	
	/**
	 * 获取总收取金额
	 * 
	 * @param queryModel
	 * @return
	 */
	public BigDecimal getFeeSum(OldCrdfeelogBefore queryModel);
	
}
