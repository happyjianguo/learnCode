package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;

public interface MerchantBlackDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MerchantBlackModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MerchantBlackModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public MerchantBlackModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException;

	// 封装商户名称
	public String findMerchantName(String merchantId, UserData ud);
}
