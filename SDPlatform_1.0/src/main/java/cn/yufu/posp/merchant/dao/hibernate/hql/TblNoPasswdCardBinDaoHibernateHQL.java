package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;


public interface TblNoPasswdCardBinDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(TblNoPasswdCardBinModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(TblNoPasswdCardBinModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ʾһ����¼
	public TblNoPasswdCardBinModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException;
	//ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	public String findfirstCardBinByKey(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException;
		
}

