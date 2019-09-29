package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.OldCrdfeelog;

public interface OldCrdfeelogService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(OldCrdfeelog queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<OldCrdfeelog> queryList(OldCrdfeelog queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<OldCrdfeelog> queryList(OldCrdfeelog queryModel);
	
	
	/**
	 * 主键查询
	 * 
	 * @param ids
	 * @return
	 */
	public List<OldCrdfeelog> selectByPrimaryKey(OldCrdfeelog queryModel);
	
	/**
	 * 退款
	 * 
	 * @param ids
	 * @return
	 */
	public Map<String, Object> refund(OldCrdfeelog crdfeelog);
	
	/**
	 * 获取总收取金额
	 * 
	 * @param queryModel
	 * @return
	 */
	public BigDecimal getFeeSum(OldCrdfeelog queryModel);
	
}
