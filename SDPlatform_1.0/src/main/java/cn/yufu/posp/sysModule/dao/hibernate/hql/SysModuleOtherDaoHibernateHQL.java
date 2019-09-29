package cn.yufu.posp.sysModule.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public interface SysModuleOtherDaoHibernateHQL {
	// 查找记录总数
	public int querySum(SysModuleModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(SysModuleModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException;

	// 显示一条记录
	public SysModuleModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException;

}
