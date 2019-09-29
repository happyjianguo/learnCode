package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public interface MerchantDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MerchantBaseModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MerchantBaseModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 查询商品类型名称
	public List<String> getMccsName(String mcc);

	public String findMccName(String mcc, UserData ud);

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public MerchantBaseModel findItem(String newKey, UserData ud) throws OAException;
	
	// 查找一条记录
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// ajax 查询签约行号和主机号
	public List<MerchantBaseModel> findSignBankInfo(String bankId,UserData ud);

	// ajax 查询商户类型
	public List<MerchantBaseModel> findMccInfo(String mcc,UserData ud);
	// 查询机构
	// public List findAllJG(UserData ud);

	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException;
	
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException;

	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud);
}
