package cn.yufu.posp.client.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface OaClientComputerInfoDaoHibernateHQL {
	// 查找记录总数
	public int querySum(OaClientComputerInfoModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(OaClientComputerInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

	// 显示一条记录
	public OaClientComputerInfoModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

}
