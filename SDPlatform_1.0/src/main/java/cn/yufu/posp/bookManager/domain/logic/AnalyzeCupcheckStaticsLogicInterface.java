package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckStatics;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckStaticsLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeCupcheckStatics queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckStatics queryDetail(String id, UserData ud) throws OAException;

}
