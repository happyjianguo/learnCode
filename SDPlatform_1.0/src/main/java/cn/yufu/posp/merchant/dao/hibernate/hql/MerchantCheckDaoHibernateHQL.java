package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public interface MerchantCheckDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MerchantBaseBo queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MerchantBaseBo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ѯ��Ʒ��������
	public List<String> getMccsName(String mcc);

	public String findMccName(String mcc, UserData ud);

	// ��ʾһ����¼
	public MerchantBaseBo findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException;
}
