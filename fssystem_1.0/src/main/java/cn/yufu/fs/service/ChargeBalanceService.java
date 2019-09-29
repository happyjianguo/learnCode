package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ChargeBalance;

public interface ChargeBalanceService {
	/**
	 * 获取主键
	 */
	public String getSequenceId();
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(ChargeBalance queryModel);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ChargeBalance> selectByExample(ChargeBalance queryModel);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ChargeBalance> selectPageByExample(ChargeBalance queryModel, int startResult, int endResult);
	
	/**
	 * 插入
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> insert(ChargeBalance queryModel) throws Exception;
	
	/**
	 * 修改
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKeySelective(ChargeBalance queryModel) throws Exception;
	
	/**
	 * 修改
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(ChargeBalance queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param id
	 * @return
	 */
	public ChargeBalance selectByPrimaryKey(String id);
	
	/**
	 * 主键删除--逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 作废
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> cancelByPrimaryKey(String id) throws Exception;
}
