package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.MerstlErrorSet;

public interface MerstlErrorSetService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(MerstlErrorSet queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<MerstlErrorSet> selectPageByExample(MerstlErrorSet queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<MerstlErrorSet> selectByExample(MerstlErrorSet queryModel);
	
	/**
	 * 添加
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> insertSelective(MerstlErrorSet queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(MerstlErrorSet queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKeySelective(MerstlErrorSet queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public MerstlErrorSet selectByPrimaryKey(String id);
	
	/**
	 * 主键删除
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String cardTypeId) throws Exception;
	
}
