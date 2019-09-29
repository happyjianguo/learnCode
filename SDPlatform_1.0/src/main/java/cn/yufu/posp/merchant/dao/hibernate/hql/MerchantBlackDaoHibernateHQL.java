package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;

public interface MerchantBlackDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantBlackModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantBlackModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MerchantBlackModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException;

	// ��װ�̻�����
	public String findMerchantName(String merchantId, UserData ud);
}
