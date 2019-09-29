package cn.yufu.posp.sysModule.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public interface SysModuleOtherDaoHibernateHQL {
	// ���Ҽ�¼����
	public int querySum(SysModuleModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(SysModuleModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public SysModuleModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException;

}
