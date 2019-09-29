package cn.yufu.posp.jgmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public interface JgDaoHibernateHQL

{
	// ���
	// ���һ�������
	public int queryJgCount(JgModel newQueryModel, UserData ud) throws OAException;

	// ���һ���
	public List queryJg(JgModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ������
	public void deleteJg(List newKeys, UserData ud) throws OAException;

	// ɾ���û�
	public void deleteUser(List<String> newKeys, UserData ud) throws OAException;

	// �½�����
	public void createJg(JgModel newModel, UserData ud) throws OAException;

	// ��KEY���һ���
	public JgModel queryJgByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveJg(JgModel newModel, UserData ud) throws OAException;

}
