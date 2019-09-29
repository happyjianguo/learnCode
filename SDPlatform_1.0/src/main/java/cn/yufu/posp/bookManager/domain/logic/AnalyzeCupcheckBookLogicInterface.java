package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckBookLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeCupcheckBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckBook queryDetail(String id, UserData ud) throws OAException;

}
