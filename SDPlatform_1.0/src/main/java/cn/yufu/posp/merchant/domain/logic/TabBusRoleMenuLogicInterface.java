package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusRoleMenuLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(TabBusRoleMenuModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TabBusRoleMenuModel model, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;
		
	//获取业务角色菜单列表
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException;
	
	//验证业务角色中文名称的唯一性
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel key, UserData ud) throws OAException;
		
}
