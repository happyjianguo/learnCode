package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;

public interface TabBusRoleMenuDaoHibernateHQL {

	// 查找记录总数
	public int querySum(TabBusRoleMenuModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(TabBusRoleMenuModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 显示一条记录
	public TabBusRoleMenuModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;
	
	//获取业务角色菜单列表
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException;
	
	//验证业务角色中文名称的唯一性
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel tabBusRoleMenuModel, UserData ud) throws OAException;
				
}

