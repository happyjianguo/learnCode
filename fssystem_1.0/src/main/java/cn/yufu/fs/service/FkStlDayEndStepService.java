package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.FkStlDayEndStep;


public interface FkStlDayEndStepService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(FkStlDayEndStep queryModel);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public FkStlDayEndStep queryInfo(String FkStlDayEndStepId);
	
	public List<FkStlDayEndStep> queryList(FkStlDayEndStep queryModel);

}
