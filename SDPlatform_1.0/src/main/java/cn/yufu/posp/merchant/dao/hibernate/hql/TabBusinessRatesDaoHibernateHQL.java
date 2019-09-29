package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusinessRatesDaoHibernateHQL {

	// 查找记录总数
	public int querySum(TabBusinessRatesModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(TabBusinessRatesModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 显示一条记录
	public TabBusinessRatesModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;
	
	//查询业务记录 
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException;
}

