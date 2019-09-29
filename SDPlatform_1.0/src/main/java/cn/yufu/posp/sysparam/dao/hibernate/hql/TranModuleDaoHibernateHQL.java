package cn.yufu.posp.sysparam.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public interface TranModuleDaoHibernateHQL

{
	// ���Ҽ�¼����
	public int querySum(TranModuleInf queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(TranModuleInf newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public TranModuleInf findItem(TranModuleInf newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException;

}
