package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public interface MerchantDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantBaseModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantBaseModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ѯ��Ʒ��������
	public List<String> getMccsName(String mcc);

	public String findMccName(String mcc, UserData ud);

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MerchantBaseModel findItem(String newKey, UserData ud) throws OAException;
	
	// ����һ����¼
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// ajax ��ѯǩԼ�кź�������
	public List<MerchantBaseModel> findSignBankInfo(String bankId,UserData ud);

	// ajax ��ѯ�̻�����
	public List<MerchantBaseModel> findMccInfo(String mcc,UserData ud);
	// ��ѯ����
	// public List findAllJG(UserData ud);

	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException;
	
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException;

	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud);
}
