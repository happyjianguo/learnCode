package cn.yufu.bak.service;

import java.util.List;
import java.util.Map;

import cn.yufu.bak.entity.Cardkindsof;

public interface CardkindsofService {
	
	/**
	 * 获取卡类型码表 按库查询
	 * @param cardkindsof
	 */
	public List<Cardkindsof> getCardTypeList(Cardkindsof cardkindsof);
	
	/**
	 * 获取卡类型码表，所有库数据去重后的数据
	 * @param cardkindsof
	 */
	public List<Cardkindsof> getDicCardTypeList(Cardkindsof cardkindsof);
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(Cardkindsof queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<Cardkindsof> queryList(Cardkindsof queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<Cardkindsof> queryList(Cardkindsof queryModel);
	
	/**
	 * 添加
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> save(Cardkindsof queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKey(Cardkindsof queryModel) throws Exception;
	
	/**
	 * 更新
	 * @param queryModel
	 * @return
	 */
	public Map<String, Object> updateByPrimaryKeySelective(Cardkindsof queryModel) throws Exception;
	
	/**
	 * 主键查询
	 * 
	 * @param cardnumber
	 * @return
	 */
	public Cardkindsof selectByPrimaryKey(String cardnumber);
	
	/**
	 * 主键删除
	 * 
	 * @param cardnumber
	 * @return
	 */
	public Map<String, Object> deleteByPrimaryKey(String cardnumber) throws Exception;

	/**
	 * 获取最大卡类型编号 + 1
	 * */
	public String getMaXCardNum();
	
}
