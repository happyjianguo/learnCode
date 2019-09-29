package cn.yufu.posp.jgmanager.dao.hibernate.hql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccessId;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;

public class JgDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements JgDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("jg");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public JgDaoHibernateHQLImpl() {

	}

	/**
	 *查找机构总数
	 */
	public int queryJgCount(JgModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from JgModel o where 1=1");

			// 按机构ID查询
			if (queryModel.getJgId() != null && !queryModel.getJgId().equals(""))
				bufferHql.append(" and o.jgId = '" + queryModel.getJgId() + "'");

			// 按机构名
			if (queryModel.getJgName() != null && !queryModel.getJgName().equals(""))
				bufferHql.append(" and o.jgName like '%" + queryModel.getJgName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("JgDaoHibernateHQLImpl.queryJgCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *查找机构
	 */
	public List queryJg(JgModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJg()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(JgModel.class);

			// 按机构ID查询
			if (queryModel.getJgId() != null && !queryModel.getJgId().equals(""))
				dcr.add(Restrictions.idEq(queryModel.getJgId()));

			// 按机构名
			if (queryModel.getJgName() != null && !queryModel.getJgName().equals(""))
				dcr.add(Restrictions.like("jgName", queryModel.getJgName(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("jgId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("queryJg()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJg()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *删除机构
	 */
	public void deleteJg(List newKeys, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.deleteJg()调用开始：删除机构。");

			for (int i = 0; i < newKeys.size(); i++) {

				delete(queryJgByKey((String) newKeys.get(i), ud), ud);
			}

			log.info("JgDaoHibernateHQLImpl.deleteJg()结束调用：删除机构。");
		} catch (Exception e) {
			log.error("JgDaoHibernateHQLImpl.deleteJg()删除机构，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param newKeys
	 * @param ud
	 * @throws OAException
	 */
	public void deleteUserGroup(OaGroupInfoId newKeys, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {

			List list = findByHQL("from OaGroupInfo where id.groupId='" + newKeys.getGroupId() + "' and id.clientId='" + newKeys.getClientId() + "'", ud);
			if (list.size() > 0) {
				delete(list.get(0), ud);
			}
			saveOrUpdateOrDeleteBySQL("delete from GROUP_ACCESS where CLIENT_ID='" + newKeys.getClientId() + "' and GROUP_ID='" + newKeys.getGroupId() + "'",
					ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.savenoticeAdvice()更新一个上报信息意见，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	/**
	 * 根据 clientId删除用户
	 * 
	 * @param newKeys
	 * @param ud
	 * @throws OAException
	 */
	@SuppressWarnings("unchecked")
	public void deleteUser(List<String> newKeys, UserData ud) throws OAException {
		try {
			for (int i = 0; i < newKeys.size(); i++) {
				List<OaUserInfo> list = findByHQL("from OaUserInfo where  id.clientId='" + newKeys.get(i) + "'", ud);
				if (list.size() > 0) {
					delete(list.get(0), ud);
					OaUserInfo model = list.get(0);
					OaGroupInfoId id = new OaGroupInfoId();
					id.setClientId(model.getId().getClientId());
					id.setGroupId(model.getGroupId());
					deleteUserGroup(id, ud);
				}
			}

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.saveuser(UserModel advice, UserData ud)更新一个用户信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	/**
	 *新建机构
	 */
	public void createJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.createJg()开始调用：保存一个信息。");

			save(newModel, ud);
			// 创建用户
			OaUserInfo noticeModel = new OaUserInfo();
			noticeModel.setPassword("2e30ba3eed21d0ad408e9eb8ffd7a018");
			noticeModel.setLastPasswordReset(new Date());
			noticeModel.setCreateTimestamp(new Date());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			noticeModel.setPasswordExpiry(df.parse("2046-12-31"));
			noticeModel.setCreateUser(ud.getUserId());
			noticeModel.setUserName("初始用户");
			noticeModel.setGroupId("system");
			noticeModel.setPasswordAttempts(0l);
			noticeModel.setPasswordAttemptsLimit(20l);
			noticeModel.setStatus("A");
			noticeModel.setUserType("03");
			noticeModel.setId(new OaUserInfoId(newModel.getJgId(), "system"));
			save(noticeModel, ud);
			// 创建初始角色
			OaGroupInfo groupModel = new OaGroupInfo();
			// form信息复制到 model
			groupModel.setGroupDescription("初始角色");
			groupModel.setCreateUser(ud.getUserId());
			groupModel.setUpdateTimestamp(new Date());
			groupModel.setCreateTimestamp(new Date());
			groupModel.setId(new OaGroupInfoId(newModel.getJgId(), "system"));
			save(groupModel, ud);
			// 为初始角色赋值
			OaGroupAccessId ogai = new OaGroupAccessId();
			OaGroupAccess oga = new OaGroupAccess();
			ogai.setClientId(newModel.getJgId() + "");
			oga.setCreateTimestamp(new Date());
			ogai.setGroupId("system");
			ogai.setMenuId(";1;101;1001;");
			ogai.setScreenId("00");
			oga.setUseCorporateDataOnly("N");
			oga.setCreateUser(ud.getUserId());
			oga.setId(ogai);
			save(oga, ud);

			log.info("JgDaoHibernateHQLImpl.createJg()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.createJg()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *按KEY查找机构
	 */
	public JgModel queryJgByKey(String newKey, UserData ud) throws OAException {
		JgModel officeSuppliesTypeModel = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgByKey()开始调用：通过编号机构。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(JgModel.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (JgModel) list.get(0);

			log.info("JgDaoHibernateHQLImpl.queryJgByKey()结束调用：通过编号机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()通过编号机构，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *保存机构
	 */
	public void saveJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.saveJg()开始调用：保存机构。");

			update(newModel, ud);

			log.info("JgDaoHibernateHQLImpl.saveJg()结束调用：保存机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.saveJg()保存机构出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
