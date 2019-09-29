package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckErr;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckErrLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(AnalyzeCupcheckErr queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeCupcheckErr queryDetail(String id, UserData ud) throws OAException;

}
