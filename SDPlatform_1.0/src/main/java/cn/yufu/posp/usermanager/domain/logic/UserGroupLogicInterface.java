package cn.yufu.posp.usermanager.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;

public interface UserGroupLogicInterface {
	public HashMap queryUser(OaGroupInfo newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// �����µ�֪ͨͨ����Ϣ
	public void createUser(OaGroupInfo newModel, UserData ud) throws OAException;

	// ����֪ͨͨ����Ϣ
	public void saveUser(OaGroupInfo newModel, UserData ud) throws OAException;

	// ɾ��֪ͨͨ����Ϣ
	public void deleteUser(OaGroupInfoId newKeys, UserData ud) throws OAException;

	public String getMenu(UserData ud) throws OAException;

	public String getZjMenu(UserData ud) throws OAException;

	public void createUserGroupAccess(OaGroupAccess newModel, UserData ud) throws OAException;

	/**
	 * ��key��֪ͨͨ����Ϣ
	 */
	public OaGroupInfo queryUserByKey(OaGroupInfoId newKey, UserData ud) throws OAException;
}
