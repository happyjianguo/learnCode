package cn.yufu.bak.service;

import java.math.BigDecimal;
import java.util.List;

import cn.yufu.bak.entity.ViewTLogMrchno;

public interface ViewTLogMrchnoService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(ViewTLogMrchno queryModel);
	
	
	/**
	 * 主键查询
	 * 
	 * @param queryModel
	 * @return
	 */
	public ViewTLogMrchno selectByPrimaryKey(BigDecimal id);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewTLogMrchno> selectPageByExample(ViewTLogMrchno queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<ViewTLogMrchno> selectByExample(ViewTLogMrchno queryModel);

}
