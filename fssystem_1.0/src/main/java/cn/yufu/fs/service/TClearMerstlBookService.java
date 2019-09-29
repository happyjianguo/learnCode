package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.TClearMerstlBook;

public interface TClearMerstlBookService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(TClearMerstlBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<TClearMerstlBook> queryList(TClearMerstlBook queryModel, int startResult, int endResult);
	
	/**
	 * 查询所有信息
	 * 
	 * @param queryModel
	 * @return TClearMerstlBook
	 */
	public List<TClearMerstlBook> queryAllList(TClearMerstlBook queryModel);
	
	
	/**
	 * 获取一段时间内所有具有交易的商户的商户号信息
	 * 
	 * @param queryModel
	 * @return
	 */
	public List<String> getMrchnoList(TClearMerstlBook queryModel);
}
