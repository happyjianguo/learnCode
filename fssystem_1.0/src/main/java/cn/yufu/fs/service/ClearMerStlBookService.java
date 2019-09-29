package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ClearMerStlBook;


public interface ClearMerStlBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearMerStlBook queryModel);
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCount(ClearMerStlBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearMerStlBook> queryList(ClearMerStlBook queryModel, int startResult, int endResult);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearMerStlBook> selectPageList(ClearMerStlBook queryModel, int startResult, int endResult);
	
	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMerStlBook queryInfo(String ClearMerStlBookId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateClearMerStlBook(ClearMerStlBook record);
	
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(ClearMerStlBook info);
	
	
	public List<ClearMerStlBook> queryList(ClearMerStlBook queryModel);
	
	/**
	 * 发送邮件更新相应的数据信息
	 * 
	 */
	public void sendMailUpdate(ClearMerStlBook queryModel);
	/**
	 * sum(consum_num) || '#' || sum(consum_amt) || '#' || sum(tran_amt) || '#' ||sum(net_amt)  || '#' || sum(fee)  as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearMerStlBook queryModel);
	
	public String getSumAmtTotal(ClearMerStlBook queryModel);
	
	public List<ClearMerStlBook> queryExcelList(ClearMerStlBook queryModel);
	
	public List<ClearMerStlBook> getExcelList(ClearMerStlBook queryModel);

}
