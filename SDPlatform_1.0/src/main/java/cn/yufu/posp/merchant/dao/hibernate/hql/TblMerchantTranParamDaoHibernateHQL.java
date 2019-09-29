package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;


public interface TblMerchantTranParamDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(TblMerchantTranParamModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(TblMerchantTranParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ʾһ����¼
	public TblMerchantTranParamModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;
	
	public String checkMerchantId(String merchantId, UserData ud) throws OAException;
		
	public String findMerchantName(String merchantId, UserData ud);
}

