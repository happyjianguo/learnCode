package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

public interface PosReceiptInfoDaoHibernateHQL {

	// 查找记录总数
	public int querySum(PosReceiptInfoModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(PosReceiptInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	

	// 显示一条记录
	public PosReceiptInfoModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException;

	
	
	
}
