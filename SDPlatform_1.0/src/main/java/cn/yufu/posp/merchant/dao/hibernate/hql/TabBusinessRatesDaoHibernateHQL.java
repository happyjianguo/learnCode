package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusinessRatesDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(TabBusinessRatesModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(TabBusinessRatesModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ʾһ����¼
	public TabBusinessRatesModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;
	
	//��ѯҵ���¼ 
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException;
}

