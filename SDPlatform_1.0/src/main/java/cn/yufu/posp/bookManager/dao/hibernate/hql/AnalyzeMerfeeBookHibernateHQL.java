package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerfeeBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻�������Ӧ�յ�
 * 
 * @author King
 * 
 */
public interface AnalyzeMerfeeBookHibernateHQL {

	// �������м�¼����
	public int queryCount(AnalyzeMerfeeBook queryModel, UserData ud) throws OAException;

	// ���Ҽ�¼
	public List queryAll(AnalyzeMerfeeBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerfeeBook queryDetail(String id, UserData ud) throws OAException;

}
