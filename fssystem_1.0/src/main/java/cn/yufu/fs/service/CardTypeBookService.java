package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.CardTypeBook;

public interface CardTypeBookService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(CardTypeBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<CardTypeBook> queryList(CardTypeBook queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<CardTypeBook> queryList(CardTypeBook queryModel);
	
	/**
	 * 添加
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> save(CardTypeBook queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(CardTypeBook queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public CardTypeBook selectByPrimaryKey(String cardTypeId);
	
	/**
	 * 主键删除
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String cardTypeId) throws Exception;
	
}
