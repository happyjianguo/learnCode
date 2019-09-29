package cn.yufu.posp.bank.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface BankInfoDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(BankInfoId queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(BankInfoId newQueryModel, int startIndex,int maxresults, String sortfield, String sortType, UserData ud)throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public BankInfoId findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(BankInfoId newModel, UserData ud) throws OAException;

	// ����
	public List<Banktype> findMerchantName(UserData ud);

	// �����������ͱ���ҵ�������������
	public String findTypeNameById(String bankType, UserData ud);
}
