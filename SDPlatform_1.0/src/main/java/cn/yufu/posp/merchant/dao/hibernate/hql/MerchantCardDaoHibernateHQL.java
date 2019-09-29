package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;

public interface MerchantCardDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantCardModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantCardModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MerchantCardModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantCardModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MerchantCardModel newModel, UserData ud) throws OAException;

	// ��װ�̻�����
	public String findMerchantName(String merchantId, UserData ud);

	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException;
}
