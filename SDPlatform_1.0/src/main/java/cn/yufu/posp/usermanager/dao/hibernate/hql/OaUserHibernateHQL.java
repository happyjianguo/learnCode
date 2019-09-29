package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;

public interface OaUserHibernateHQL {

	/**
	 * @param OaUserInfo
	 * @param ud
	 * @return int
	 * @throws cn.yufu.posp.common.common.exception.OAException
	 * @roseuid 4484F1680399
	 */
	public int queryUserCount(OaUserInfo newQueryModel, UserData ud) throws OAException;

	// public List queryMenu(UserData ud) throws OAException;

	// ��ѯ֪ͨͨ����Ϣ
	public List queryUser(OaUserInfo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// �����µ�֪ͨͨ����Ϣ
	public void createUser(OaUserInfo newModel, UserData ud) throws OAException;

	public OaUserInfo queryUserByKey(OaUserInfoId newKey, UserData ud) throws OAException;

	// �����µ�֪ͨͨ����Ϣ
	public void saveUser(OaUserInfo newModel, UserData ud) throws OAException;

	// �õ�Ȩ���б�
	public List queryZjMenu(UserData ud) throws OAException;

	// ɾ��֪ͨͨ����Ϣ
	public void deleteUser(OaUserInfoId newKeys, UserData ud) throws OAException;

	public String userLogin(OaUserInfo newModel, UserData ud) throws OAException;

	public HashMap queryGroupAccess(UserData ud) throws OAException;

	public List queryGroupAccessList(UserData ud);

}
