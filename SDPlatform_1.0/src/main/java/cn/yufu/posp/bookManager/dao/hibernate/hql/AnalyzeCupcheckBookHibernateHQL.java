package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckBookHibernateHQL {

	// �������м�¼����
	public int queryCount(AnalyzeCupcheckBook queryModel, UserData ud) throws OAException;

	// ���Ҽ�¼
	public List queryAll(AnalyzeCupcheckBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeCupcheckBook queryDetail(String id, UserData ud) throws OAException;

}
