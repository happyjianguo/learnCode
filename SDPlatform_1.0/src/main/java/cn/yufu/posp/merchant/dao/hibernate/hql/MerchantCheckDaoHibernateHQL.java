package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public interface MerchantCheckDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MerchantBaseBo queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MerchantBaseBo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 查询商品类型名称
	public List<String> getMccsName(String mcc);

	public String findMccName(String mcc, UserData ud);

	// 显示一条记录
	public MerchantBaseBo findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException;
}
