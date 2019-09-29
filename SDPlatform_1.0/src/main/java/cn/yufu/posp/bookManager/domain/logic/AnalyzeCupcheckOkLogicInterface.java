package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckOk;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckOkLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeCupcheckOk queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckOk queryDetail(String id, UserData ud) throws OAException;

}
