package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.List;
import java.util.Map;

import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface CardBinAreaDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(CardBinArea queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(CardBinArea newQueryModel, int startIndex,int maxresults, String sortfield, String sortType, UserData ud)throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public CardBinArea findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(CardBinArea newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(CardBinArea newModel, UserData ud) throws OAException;
	
	public Map getAreaCodeMap() throws OAException ;
	
	public CardBinArea findItemById(String newKey, UserData ud) throws OAException;
}
