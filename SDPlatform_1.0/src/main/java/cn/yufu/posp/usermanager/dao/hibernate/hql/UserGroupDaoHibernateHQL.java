package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;

public interface UserGroupDaoHibernateHQL {

	public int queryUserGroupCount(OaGroupInfo newQueryModel, UserData ud) throws OAException;

	public List queryGroupAccess(UserData ud) throws OAException;

	// 查询通知通告信息
	public List queryUserGroup(OaGroupInfo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 创建新的通知通告信息
	public void createUserGroup(OaGroupInfo newModel, UserData ud) throws OAException;

	public void createUserGroupAccess(OaGroupAccess newModel, UserData ud) throws OAException;

	public OaGroupInfo queryUserGroupByKey(OaGroupInfoId newModel, UserData ud) throws OAException;

	// 创建新的通知通告信息
	public void saveUserGroup(OaGroupInfo newModel, UserData ud) throws OAException;

	// 删除通知通告信息
	public void deleteUserGroup(OaGroupInfoId newKeys, UserData ud) throws OAException;

	public String getMenu(UserData ud) throws OAException;

	public String getZjMenu(UserData ud) throws OAException;

}
