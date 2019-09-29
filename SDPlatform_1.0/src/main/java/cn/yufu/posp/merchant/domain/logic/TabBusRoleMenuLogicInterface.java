package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusRoleMenuLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(TabBusRoleMenuModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TabBusRoleMenuModel model, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;
		
	//��ȡҵ���ɫ�˵��б�
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException;
	
	//��֤ҵ���ɫ�������Ƶ�Ψһ��
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel key, UserData ud) throws OAException;
		
}
