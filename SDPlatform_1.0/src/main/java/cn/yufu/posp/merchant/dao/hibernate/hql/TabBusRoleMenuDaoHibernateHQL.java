package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;

public interface TabBusRoleMenuDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(TabBusRoleMenuModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(TabBusRoleMenuModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ��ʾһ����¼
	public TabBusRoleMenuModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException;
	
	//��ȡҵ���ɫ�˵��б�
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException;
	
	//��֤ҵ���ɫ�������Ƶ�Ψһ��
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel tabBusRoleMenuModel, UserData ud) throws OAException;
				
}

