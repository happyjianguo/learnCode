package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckErr;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckErrLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeCupcheckErr queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckErr queryDetail(String id, UserData ud) throws OAException;

}
