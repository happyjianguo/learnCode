package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;

public interface MerchantSwitchDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantSwitchModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantSwitchModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MerchantSwitchModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// ��װ�̻�����
	public String findMerchantName(String merchantId, UserData ud);
}
