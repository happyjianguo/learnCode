package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.WankeInterestAccrual;

public interface WankeInterestAccrualService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(WankeInterestAccrual queryModel);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<WankeInterestAccrual> selectPageByExample(WankeInterestAccrual queryModel, int startResult, int endResult);
    
	/**
	 * 查询全部信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<WankeInterestAccrual> selectByExample(WankeInterestAccrual queryModel);
    
	/**
	 * 查询一条信息
	 * 
	 * @param id
	 * @return
	 */
	public WankeInterestAccrual selectByPrimaryKey(String id);
}
