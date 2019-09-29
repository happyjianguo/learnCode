package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerstlBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻������
 * 
 * @author King
 * 
 */
public interface AnalyzeMerstlBookHibernateHQL {

	// �������м�¼����
	public int queryCount(AnalyzeMerstlBook queryModel, UserData ud) throws OAException;

	// ���Ҽ�¼
	public List queryAll(AnalyzeMerstlBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerstlBook queryDetail(String id, UserData ud) throws OAException;

}
