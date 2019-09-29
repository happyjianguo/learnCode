package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;

public interface MerchantSwitchDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MerchantSwitchModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MerchantSwitchModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public MerchantSwitchModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// 封装商户名称
	public String findMerchantName(String merchantId, UserData ud);
}
