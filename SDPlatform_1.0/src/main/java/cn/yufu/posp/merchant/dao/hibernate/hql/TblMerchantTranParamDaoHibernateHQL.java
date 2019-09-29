package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;


public interface TblMerchantTranParamDaoHibernateHQL {

	// 查找记录总数
	public int querySum(TblMerchantTranParamModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(TblMerchantTranParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 显示一条记录
	public TblMerchantTranParamModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;
	
	public String checkMerchantId(String merchantId, UserData ud) throws OAException;
		
	public String findMerchantName(String merchantId, UserData ud);
}

