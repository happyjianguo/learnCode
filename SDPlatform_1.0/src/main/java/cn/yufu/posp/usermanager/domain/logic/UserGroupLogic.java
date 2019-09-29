package cn.yufu.posp.usermanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.dao.hibernate.hql.UserGroupDaoHibernateHQL;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;

public class UserGroupLogic extends BaseLogic implements UserGroupLogicInterface {
	private static final Log log = LogFactory.getLog("user");

	public void createUser(OaGroupInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupLogic.createUser()开始调用：创建新的角色信息");

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");

			NoticeDao.createUserGroup(newModel, ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.createUser()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	public void createUserGroupAccess(OaGroupAccess newModel, UserData ud) throws OAException {
		try {
			log.info("UserGroupLogic.createUserGroupAccess()开始调用：创建新的角色信息");

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");

			NoticeDao.createUserGroupAccess(newModel, ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.createUserGroupAccess()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void deleteUser(OaGroupInfoId newKeys, UserData ud) throws OAException {
		try {
			log.info("UserGroupLogic.deleteGroup()开始调用：删除角色。");

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");

			NoticeDao.deleteUserGroup(newKeys, ud);

			log.info("UserGroupLogic.deleteGroup()结束调用：删除角色。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.deleteUser()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	public HashMap queryUser(OaGroupInfo queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		PageInfoModel page = pageInfo;
		HashMap pageMap = new HashMap();
		try {
			log.info("UserGroupLogic.queryNotice()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");

			// 得到记录的总条数

			totalCount = NoticeDao.queryUserGroupCount(queryModel, ud);
			// NoticeDao.queryUserGroupCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = NoticeDao.queryUserGroup(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			pageMap.put("pageInfo", page);

			log.info("UserGroupLogic.queryUser()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.queryUser()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return pageMap;
	}

	public String getMenu(UserData ud) throws OAException {
		String str = null;
		UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");
		str = NoticeDao.getMenu(ud);
		return str;
	}

	public String getZjMenu(UserData ud) throws OAException {
		String str = null;
		UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");
		str = NoticeDao.getZjMenu(ud);
		return str;
	}

	public OaGroupInfo queryUserByKey(OaGroupInfoId newKey, UserData ud) throws OAException {
		OaGroupInfo newModel = new OaGroupInfo();

		try {
			log.info("UserGroupLogic.queryUserByKey()开始调用：获得指定角色信息。");

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");
			newModel = NoticeDao.queryUserGroupByKey(newKey, ud);

			// 延时加载
			// newModel.getAccessoryInfos().iterator();

			log.info("UserGroupLogic.queryUserByKey()结束调用：获得指定角色信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.queryUserByKey()获得指角色信息，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return newModel;
	}

	public void saveUser(OaGroupInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupLogic.createNotice()开始调用：创建新的通知通告信息");

			UserGroupDaoHibernateHQL NoticeDao = (UserGroupDaoHibernateHQL) getBean("UserGroupDao");

			NoticeDao.saveUserGroup(newModel, ud);
			// NoticeDao

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupLogic.saveGroup()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
