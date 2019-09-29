package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.List;
import java.util.Map;

import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface EdcCardBinAreaDaoHibernateHQL {

	// 查找记录总数
	public int querySum(EdcCardBinArea queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(EdcCardBinArea newQueryModel, int startIndex,int maxresults, String sortfield, String sortType, UserData ud)throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public EdcCardBinArea findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(EdcCardBinArea newModel, UserData ud) throws OAException;
	
	public Map getAreaCodeMap() throws OAException ;
	
	public EdcCardBinArea findItemById(String newKey,String merchantId,String terminalId, UserData ud) throws OAException;
}
