package cn.yufu.posp.bank.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface BanktypeDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(Banktype queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(Banktype newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public Banktype findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(Banktype newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(Banktype newModel, UserData ud) throws OAException;

}
