package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckStatics;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckStaticsLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(AnalyzeCupcheckStatics queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeCupcheckStatics queryDetail(String id, UserData ud) throws OAException;

}
