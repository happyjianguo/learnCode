package cn.yufu.posp.usermanager.domain.logic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.dao.hibernate.hql.OaUserHibernateHQL;
import cn.yufu.posp.usermanager.dao.hibernate.hql.UserGroupDaoHibernateHQL;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;

public class UserLogic extends BaseLogic implements UserLogicInterface {
	private static final Log log = LogFactory.getLog("user");
	OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");

	public void createUser(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserLogic.createUser()��ʼ���ã������µ��û���Ϣ");

			OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");

			UserDao.createUser(newModel, ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserLogic.createUser()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void deleteUser(OaUserInfoId newKeys, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserLogic.deleteUsers()��ʼ���ã�ɾ��֪ͨͨ�档");

			OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");

			UserDao.deleteUser(newKeys, ud);

			log.info("UserLogic.deleteUsers()�������ã�ɾ��֪ͨͨ�档");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserLogic.deleteUsers()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public HashMap queryUser(OaUserInfo queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		PageInfoModel page = pageInfo;
		HashMap pageMap = new HashMap();
		try {
			log.info("UserLogic.queryUser()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");

			// �õ���¼��������

			totalCount = UserDao.queryUserCount(queryModel, ud);
			// System.out.println(totalCount+"|||||||");
			// UserDao.queryUserCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;
			List list = UserDao.queryUser(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			pageMap.put("pageInfo", page);

			log.info("UserLogic.queryUser()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserLogic.queryUser()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return pageMap;
	}

	public OaUserInfo queryUserByKey(OaUserInfoId newKey, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		OaUserInfo newModel = new OaUserInfo();

		try {
			log.info("UserLogic.queryUserByKey()��ʼ���ã����ָ���û���Ϣ��");

			newModel = UserDao.queryUserByKey(newKey, ud);

			log.info("UserLogic.queryUserByKey()�������ã����ָ���û���Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserLogic.queryUserByKey()���ָ���û���Ϣ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return newModel;
	}

	public void saveUser(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserLogic.createUser()��ʼ���ã������µ��û���Ϣ");

			OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");
			OaUserInfo newModel1 = UserDao.queryUserByKey(newModel.getId(), ud);

			newModel1.setUserName(newModel.getUserName());
			newModel1.setGroupId(newModel.getGroupId());
			newModel1.setPassword(newModel.getPassword());
			newModel1.setUpdateUser(ud.getUserId());
			newModel1.setUpdateTimestamp(new Date());
			newModel1.setStatus(newModel.getStatus());

			// System.out.println("��newModel.getPasswordAttempts()==" +
			// newModel.getPasswordAttempts() + "��");
			// System.out.println("��newModel.getPasswordAttemptsLimit()==" +
			// newModel.getPasswordAttemptsLimit() + "��");
			// System.out.println("��newModel.getPasswordExpiry()==" +
			// newModel.getPasswordExpiry() + "��");
			newModel1.setPasswordAttempts(newModel.getPasswordAttempts());
			newModel1.setPasswordAttemptsLimit(newModel.getPasswordAttemptsLimit());
			newModel1.setPasswordExpiry(newModel.getPasswordExpiry());

			UserDao.saveUser(newModel1, ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserLogic.createUser()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	public OaUserInfo userLogin(OaUserInfoId newKey, UserData ud) throws OAException {
		return null;
	}

	public List getMenu(UserData ud) throws OAException {
		OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");
		return UserDao.queryZjMenu(ud);
	}

	public List getGroupAccess(UserData ud) throws OAException {
		OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");
		return UserDao.queryGroupAccessList(ud);
	}

	public HashMap getGroupAccessMap(UserData ud) throws OAException {
		OaUserHibernateHQL UserDao = (OaUserHibernateHQL) getBean("UserDao");
		return UserDao.queryGroupAccess(ud);
	}

}
