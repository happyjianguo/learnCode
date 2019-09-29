package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.CombineDate;

public interface CombineDateService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(CombineDate queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<CombineDate> queryList(CombineDate queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<CombineDate> queryList(CombineDate queryModel);
	
	/**
	 * 添加
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> save(CombineDate queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(CombineDate queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKeySelective(CombineDate queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public CombineDate selectByPrimaryKey(String id);
	
	/**
	 * 主键删除
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 获取最大 end_date
	 * @return
	 */
	public CombineDate selectMaxEndDate(CombineDate queryModel);
}
