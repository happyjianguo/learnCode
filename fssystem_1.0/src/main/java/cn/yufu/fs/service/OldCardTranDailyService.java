package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.OldCardTranDaily;

public interface OldCardTranDailyService {
	
	/**
	 * @return 总条数
	 *
	 */
	public int queryCount(OldCardTranDaily queryModel);
	
	/**
	 * 分页查询明细
	 * @return Administrator
	 *
	 */
	public List<OldCardTranDaily> selectPageByExample(OldCardTranDaily queryModel,
			int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<OldCardTranDaily> selectByExample(OldCardTranDaily queryModel);
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public OldCardTranDaily selectByPrimaryKey(String id);

}
