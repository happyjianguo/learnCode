package cn.yufu.posp.logManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public interface LogDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(OperateLog queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(OperateLog newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ʾһ����¼
	public OperateLog findItem(String newKey, UserData ud) throws OAException;
	
	// �½�һ����¼
	public void createItem(OperateLog newModel, UserData ud) throws OAException;

}
