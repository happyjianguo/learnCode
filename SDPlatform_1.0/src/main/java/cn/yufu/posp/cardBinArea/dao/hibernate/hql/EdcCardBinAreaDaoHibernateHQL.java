package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.List;
import java.util.Map;

import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface EdcCardBinAreaDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(EdcCardBinArea queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(EdcCardBinArea newQueryModel, int startIndex,int maxresults, String sortfield, String sortType, UserData ud)throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public EdcCardBinArea findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(EdcCardBinArea newModel, UserData ud) throws OAException;
	
	public Map getAreaCodeMap() throws OAException ;
	
	public EdcCardBinArea findItemById(String newKey,String merchantId,String terminalId, UserData ud) throws OAException;
}
