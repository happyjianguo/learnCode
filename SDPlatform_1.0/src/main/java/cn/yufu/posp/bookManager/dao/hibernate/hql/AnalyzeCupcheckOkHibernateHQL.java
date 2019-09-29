package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckOk;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckOkHibernateHQL {

	// �������м�¼����
	public int queryCount(AnalyzeCupcheckOk queryModel, UserData ud) throws OAException;

	// ���Ҽ�¼
	public List queryAll(AnalyzeCupcheckOk queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckOk queryDetail(String id, UserData ud) throws OAException;

}
