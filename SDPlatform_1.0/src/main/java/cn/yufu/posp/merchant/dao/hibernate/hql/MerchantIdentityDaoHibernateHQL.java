package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;

public interface MerchantIdentityDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MerchantIdentity queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MerchantIdentity newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public MerchantIdentity findItem(String newKey, UserData ud) throws OAException;

	// 查找一条记录
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(MerchantIdentity newModel, UserData ud) throws OAException;

}
