package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;

public class UserGroupDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements UserGroupDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("user");

	public void createUserGroup(OaGroupInfo newModel, UserData ud) throws OAException {
		try {
			log.info("UserGroupDaoHibernateHQLImpl.createUserGroup()��ʼ���ã�������ɫ��Ϣ��");

			save(newModel, ud);

			log.info("UserGroupDaoHibernateHQLImpl.createUserGroup()�������ã�������ɫ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.createUserGroup()������ɫ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		// TODO Auto-generated method stub

	}

	public void createUserGroupAccess(OaGroupAccess newModel, UserData ud) throws OAException {
		try {
			log.info("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()��ʼ���ã�����һ����ɫȨ�ޡ�");

			save(newModel, ud);

			log.info("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()�������ã�����һ����ɫȨ�ޡ�");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()����һ����ɫȨ�ޣ������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	public void deleteUserGroup(OaGroupInfoId newKeys, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupDaoHibernateHQLImpl.deleteUserGroup()��ʼ���ã�ɾ����ɫ��Ϣ��");
			List list = findByHQL("from OaGroupInfo where id.groupId='" + newKeys.getGroupId() + "' and id.clientId='" + newKeys.getClientId() + "'", ud);
			if (list.size() > 0) {
				delete(list.get(0), ud);
			}
			saveOrUpdateOrDeleteBySQL("delete from GROUP_ACCESS where CLIENT_ID='" + newKeys.getClientId() + "' and GROUP_ID='" + newKeys.getGroupId() + "'", ud);
			log.info("UserGroupDaoHibernateHQLImpl.deleteUserGroup()�������ã�ɾ����ɫ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.deleteUserGroup()ɾ����ɫ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	// �õ��˵��б�
	public String getMenu(UserData ud) throws OAException {
		StringBuffer str = new StringBuffer();
		String sql = "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cm.menu_sort ,v.opt_num ";
		// if(!SystemConstants.mainMerchantId.equals(ud.getClientId())){
		// sql =
		// "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cast(t.menu_id as int),v.opt_num";
		// }
		List list = findBySQL(sql, ud);
		String nowid = "";
		Object[] obj = null;

		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			obj[0] = obj[0].toString().split(";")[obj[0].toString().split(";").length - 1];
			// �ж��Ƿ��Ѿ��任�˵�
			if ("".equals(nowid)) {
				str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
				str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				nowid = (String) obj[0];
			} else {
				if (nowid.equals((String) obj[0])) {
					str.append("<input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				} else {
					// ������һ���˵��б��tr
					str.append("</td></tr>");
					// ��ʼ�µ�tr
					str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
					str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
					nowid = (String) obj[0];
				}
			}

		}
		str.append("</td></tr>");
		return str.toString();
	}

	// �õ��˵��б�,������ʾ��ʽ����ѡ��δѡ��
	public String getZjMenu(UserData ud) throws OAException {
		StringBuffer str = new StringBuffer();
		String sql = "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cm.menu_sort ,v.opt_num";
		// if(!SystemConstants.mainMerchantId.equals(ud.getClientId())){
		// sql =
		// "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cast(t.menu_id as int),v.opt_num";
		// }
		List list = findBySQL(sql, ud);
		List list1 = queryGroupAccess(ud);
		HashMap acc = new HashMap();
		OaGroupAccess oga = null;
		for (int j = 0; j < list1.size(); j++) {
			oga = (OaGroupAccess) list1.get(j);
			acc.put(oga.getId().getMenuId() + "_" + oga.getId().getScreenId(), "yes");
		}
		String nowid = "";
		Object[] obj = null;
		String ifok = "";
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			obj[0] = obj[0].toString().split(";")[obj[0].toString().split(";").length - 1];
			// �ж��Ƿ��Ѿ��任�˵�
			if ("".equals(nowid)) {
				if (acc.get(obj[0] + "_" + obj[1]) != null) {
					ifok = "checked";
				} else {
					ifok = "";
				}
				str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
				str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + "/>" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				nowid = (String) obj[0];
			} else {
				if (nowid.equals((String) obj[0])) {
					if (acc.get(obj[0] + "_" + obj[1]) != null) {
						ifok = "checked";
					} else {
						ifok = "";
					}
					str.append("<input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				} else {
					if (acc.get(obj[0] + "_" + obj[1]) != null) {
						ifok = "checked";
					} else {
						ifok = "";
					}
					// ������һ���˵��б��tr
					str.append("</td></tr>");
					// ��ʼ�µ�tr
					str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
					str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
					nowid = (String) obj[0];
				}
			}

		}
		str.append("</td></tr>");
		return str.toString();
	}

	public List queryUserGroup(OaGroupInfo queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()��ʼ���ã���ѯ��ɫ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaGroupInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// ������ID��ѯ
			if (queryModel.getCreateUser() != null && !queryModel.getCreateUser().equals(""))
				dcr.add(Restrictions.like("id.groupId", queryModel.getCreateUser(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("id.groupId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()�������ã���ѯ��ɫ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroup()��ѯ��ɫ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;

	}

	// ��ѯ��ɫȨ��
	public List queryGroupAccess(UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()��ʼ���ã���ѯ��ɫȨ�ޡ�");
			String hql = "from OaGroupAccess o where o.id.groupId='" + ud.getGroupId() + "' and o.id.clientId='" + ud.getClientId() + "'";
			list = findByHQL(hql, ud);
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()�������ã���ѯ��ɫȨ�ޡ�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroup()��ѯ��ɫȨ�ޣ����ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public List getRoleRightList(String groupId, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.getRoleRightList()��ʼ���ã���ѯ��ɫȨ�ޡ�");
			String hql = "from OaGroupAccess o where o.id.groupId='" + groupId + "' and o.id.clientId = '" + ud.getClientId() + "' order by o.id.menuId";
			list = findByHQL(hql, ud);
			log.info("UserGroupDaoHibernateHQLImpl.getRoleRightList()�������ã���ѯ��ɫȨ�ޡ�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.getRoleRightList()��ѯ��ɫȨ�ޣ����ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public OaGroupInfo queryUserGroupByKey(OaGroupInfoId newKey, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		OaGroupInfo newModel = null;
		try {

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()��ʼ���ã���ѯһ����ɫȨ�ޡ�");

			String hql = "from OaGroupInfo f where f.id.groupId = '" + newKey.getGroupId() + "' and f.id.clientId='" + newKey.getClientId() + "'";

			List list = findByHQL(hql, ud);
			if (list.size() > 0) {
				newModel = (OaGroupInfo) list.get(0);
			}

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()�������ã���ѯһ����ɫȨ�ޡ�");
		} catch (Exception e) {
			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()��ѯһ����ɫȨ�ޣ������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return newModel;
	}

	public int queryUserGroupCount(OaGroupInfo queryModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		int i = 0;
		List list = null;
		try {

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()��ʼ���ã���ѯ��ɫȨ��������");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaGroupInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// ������ID��ѯ
			if (queryModel.getCreateUser() != null && !queryModel.getCreateUser().equals(""))
				dcr.add(Restrictions.like("id.groupId", queryModel.getCreateUser(), MatchMode.ANYWHERE));

			list = findBYCriteria(dcr, ud);
			if (list != null) {
				i = list.size();
			}

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()�������ã���ѯ��ɫȨ��������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()��ѯ��ɫȨ�����������ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return i;
	}

	public void saveUserGroup(OaGroupInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupDaoHibernateHQLImpl.saveUserGroup()��ʼ���ã�����һ����ɫ��Ϣ��");

			saveOrUpdate(newModel, ud);
			saveOrUpdateOrDeleteBySQL("delete from GROUP_ACCESS where CLIENT_ID='" + newModel.getId().getClientId() + "' and GROUP_ID='" + newModel.getId().getGroupId() + "'", ud);
			log.info("UserGroupDaoHibernateHQLImpl.saveUserGroup()�������ã�����һ����ɫ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.saveUserGroup()����һ����ɫ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
