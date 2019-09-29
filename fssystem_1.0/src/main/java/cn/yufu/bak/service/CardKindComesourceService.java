package cn.yufu.bak.service;

import java.util.List;
import java.util.Map;

import cn.yufu.bak.entity.CardKindComesource;

public interface CardKindComesourceService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(CardKindComesource queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<CardKindComesource> queryList(CardKindComesource queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<CardKindComesource> queryList(CardKindComesource queryModel);
	
	/**
	 * 添加
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> save(CardKindComesource queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(CardKindComesource queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKeySelective(CardKindComesource queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param cardnumber
	 * @return
	 */
	public CardKindComesource selectByPrimaryKey(String cardnumber);
	
	/**
	 * 主键删除
	 * 
	 * @param cardnumber
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String cardnumber) throws Exception;

	/**
	 * 获取最大ID + 1
	 * */
	public String getMaxId();
	
}
