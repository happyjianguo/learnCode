package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;

public interface MerchantIdentityDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantIdentity queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantIdentity newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MerchantIdentity findItem(String newKey, UserData ud) throws OAException;

	// ����һ����¼
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MerchantIdentity newModel, UserData ud) throws OAException;

}
