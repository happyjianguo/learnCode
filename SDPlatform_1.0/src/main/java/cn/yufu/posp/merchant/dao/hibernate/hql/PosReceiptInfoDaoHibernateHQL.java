package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

public interface PosReceiptInfoDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(PosReceiptInfoModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(PosReceiptInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	

	// ��ʾһ����¼
	public PosReceiptInfoModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException;

	
	
	
}
