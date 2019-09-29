package cn.yufu.posp.usermanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;

public interface UserLogicInterface {
	// ��ѯ֪ͨͨ����Ϣ�б�
	public HashMap queryUser(OaUserInfo newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// �����µ�֪ͨͨ����Ϣ
	public void createUser(OaUserInfo newModel, UserData ud) throws OAException;

	// ����֪ͨͨ����Ϣ
	public void saveUser(OaUserInfo newModel, UserData ud) throws OAException;

	// ɾ��֪ͨͨ����Ϣ
	public void deleteUser(OaUserInfoId newKeys, UserData ud) throws OAException;

	public List getMenu(UserData ud) throws OAException;

	public List getGroupAccess(UserData ud) throws OAException;

	public HashMap getGroupAccessMap(UserData ud) throws OAException;

	/**
	 * ��key��֪ͨͨ����Ϣ
	 */
	public OaUserInfo queryUserByKey(OaUserInfoId newKey, UserData ud) throws OAException;

	public OaUserInfo userLogin(OaUserInfoId newKey, UserData ud) throws OAException;

}
