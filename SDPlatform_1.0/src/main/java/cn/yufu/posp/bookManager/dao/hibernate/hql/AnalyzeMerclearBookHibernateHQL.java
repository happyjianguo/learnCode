package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerclearBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻���ֵ�
 * 
 * @author King
 * 
 */
public interface AnalyzeMerclearBookHibernateHQL {

	// �������м�¼����
	public int queryCount(AnalyzeMerclearBook queryModel, UserData ud) throws OAException;

	// ���Ҽ�¼
	public List queryAll(AnalyzeMerclearBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerclearBook queryDetail(String id, UserData ud) throws OAException;

}
