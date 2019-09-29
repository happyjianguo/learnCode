package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearStatDailyAccdetBak;

public interface ClearStatDailyAccdetBakService {
	
	/**
	 * @return 总条数
	 *
	 */
	public int queryCount(ClearStatDailyAccdetBak queryModel);
	
	/**
	 * 分页查询明细
	 * @return Administrator
	 *
	 */
	public List<ClearStatDailyAccdetBak> selectPageList(ClearStatDailyAccdetBak queryModel,
			int startResult, int endResult);
	/**
	 * @return 总卡片数和总余额
	 *
	 */
	public ClearStatDailyAccdetBak TotalCardSumAndBal(ClearStatDailyAccdetBak queryModel);
	
}
