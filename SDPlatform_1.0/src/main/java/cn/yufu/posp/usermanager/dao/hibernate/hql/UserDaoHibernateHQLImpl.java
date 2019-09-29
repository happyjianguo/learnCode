package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;
import cn.yufu.posp.usermanager.domain.model.Test;

public class UserDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements OaUserHibernateHQL {
	private static final Log log = LogFactory.getLog("user");

	public void createUser(OaUserInfo newModel, UserData ud) throws OAException {
		try {
			log.info("UserDaoHibernateHQLImpl.createUser()��ʼ���ã�����һ���û���Ϣ��");

			save(newModel, ud);

			log.info("UserDaoHibernateHQLImpl.createUser()�������ã�����һ���û���Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.createUser()����һ���û���Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public void deleteUser(OaUserInfoId newKeys, UserData ud) throws OAException {
		try {
			log.info("UserDaoHibernateHQLImpl.saveuser()��ʼ���ã�ɾ��һ���û���Ϣ��");
			List list = findByHQL("from OaUserInfo where id.userId='" + newKeys.getUserId() + "' and id.clientId='" + newKeys.getClientId() + "'", ud);
			if (list.size() > 0)
				delete(list.get(0), ud);
			log.info("UserDaoHibernateHQLImpl.saveuser()�������ã�ɾ��һ���û���Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.saveuser()ɾ��һ���û���Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public List queryUser(OaUserInfo queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {

		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryUser()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaUserInfo.class);

			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));
			// ������ID��ѯ
			if (queryModel.getUserId() != null && !queryModel.getUserId().equals(""))
				dcr.add(Restrictions.like("id.userId", queryModel.getUserId(), MatchMode.ANYWHERE));

			// ��������
			if (queryModel.getUserName() != null && !queryModel.getUserName().equals(""))
				dcr.add(Restrictions.like("userName", queryModel.getUserName(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("id.userId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("UserDaoHibernateHQLImpl.queryUser()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryUser()ͨ����ѯ������ҳ��ѯ��Ϣ�����ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/*
	 * public List queryMenu(UserData ud) throws OAException {
	 * 
	 * List list = null; try { //log.info(
	 * "queryAllValidUser(Long inforId, UserData ud)��ʼ���ã������ύ�˵�ID�������ź�InforId�õ����һ�������"
	 * ); String hql = "from Test o"; list = findByHQL(hql, ud); } catch
	 * (Exception e) {
	 * 
	 * throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS); } return
	 * list; }
	 */

	// ��Ȩ�޵�����ת������ĸ
	public String getZmString(String str) {
		String rstr = str;
		rstr = rstr.replaceAll(";", "");
		rstr = rstr.replaceAll("0", "0");
		rstr = rstr.replaceAll("1", "1");
		rstr = rstr.replaceAll("2", "2");
		rstr = rstr.replaceAll("3", "3");
		rstr = rstr.replaceAll("4", "4");
		rstr = rstr.replaceAll("5", "5");
		rstr = rstr.replaceAll("6", "6");
		rstr = rstr.replaceAll("7", "7");
		rstr = rstr.replaceAll("8", "8");
		rstr = rstr.replaceAll("9", "9");
		return rstr;
	}

	// ��ѯ��ɫȨ��
	public HashMap queryGroupAccess(UserData ud) throws OAException {
		HashMap hm = new HashMap();
		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryGroupAccess()��ʼ���ã���ѯ��ɫȨ�ޡ�");
			String hql = "from OaGroupAccess o where o.id.groupId='" + ud.getGroupId() + "' and o.id.clientId='" + ud.getClientId() + "'";
			list = findByHQL(hql, ud);
			OaGroupAccess oga = null;
			String str[] = null;
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					oga = (OaGroupAccess) list.get(i);
					str = oga.getId().getMenuId().split(";");
					hm.put(getZmString("x" + str[str.length - 1] + "x" + oga.getId().getScreenId()), "yes");
				}
			}
			log.info("UserDaoHibernateHQLImpl.queryGroupAccess()�������ã���ѯ��ɫȨ�ޡ�");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryGroupAccess()��ѯ��ɫȨ�ޣ����ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return hm;
	}

	public List queryZjMenu(UserData ud) throws OAException {
		List list = null;
		List list1 = new ArrayList();

		try {
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()��ʼ���ã���ѯ�˵���Ϣ��");
			// String hql =
			// "select t.id,t.pid,t.name,t.url from test t,group_access o where o.group_id='"+ud.getGroupId()+"' and o.client_id='"+ud.getClientId()+"' and o.menu_id like '%;'||t.id||';%' group by t.id,t.pid,t.name,t.url";
			String hql = "select distinct m.MENU_ID,m.MENU_PARENT_ID,m.MENU_NAME,m.MENU_URL,m.MENU_SORT from GROUP_ACCESS r,CONSOLE_MENU m where r.group_id='" + ud.getGroupId()
					+ "'and  r.menu_id=m.menu_id  and r.client_id='" + ud.getClientId() + "' order by m.MENU_SORT";
			// System.out.println(hql);
			list = findBySQL(hql, ud);
			Object[] obj = null;
			Test t = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				t = new Test();
				t.setId((String) obj[0]);
				t.setPid((String) obj[1]);
				t.setName((String) obj[2]);
				t.setUrl((String) obj[3]);
				list1.add(t);
			}
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()�������ã���ѯ�˵���Ϣ��");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()��ѯ�˵���Ϣ�������쳣��");
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list1;

	}

	public OaUserInfo queryUserByKey(OaUserInfoId newKey, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		OaUserInfo newModel = null;
		try {

			log.info("UserDaoHibernateHQLImpl.queryUserByKey()��ʼ���ã���������ѯ��");

			String hql = "from OaUserInfo f where f.id.userId = '" + newKey.getUserId() + "' and f.id.clientId='" + newKey.getClientId() + "'";
			// String hql = "from OaUserInfo f where f.id.userId = '" +
			// newKey.getUserId()+"'";

			List list = findByHQL(hql, ud);
			if (list.size() > 0) {
				newModel = (OaUserInfo) list.get(0);
			}

			log.info("UserDaoHibernateHQLImpl.queryUserByKey()�������ã���������ѯ��");
		} catch (Exception e) {
			log.error("UserDaoHibernateHQLImpl.queryUserByKey()��������ѯ�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return newModel;
	}

	public int queryUserCount(OaUserInfo queryModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		int i = 0;
		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryUserCount()��ʼ���ã���ѯ�û���Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaUserInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// ������ID��ѯ
			if (queryModel.getUserId() != null && !queryModel.getUserId().equals(""))
				dcr.add(Restrictions.like("id.userId", queryModel.getUserId(), MatchMode.ANYWHERE));

			// ��������
			if (queryModel.getUserName() != null && !queryModel.getUserName().equals(""))
				dcr.add(Restrictions.like("userName", queryModel.getUserName(), MatchMode.ANYWHERE));

			list = findBYCriteria(dcr, ud);
			if (list != null) {
				i = list.size();
			}
			log.info("UserDaoHibernateHQLImpl.queryUserCount()�������ã���ѯ�û���Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryUserCount()���ó����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return i;
	}

	public void saveUser(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserDaoHibernateHQLImpl.saveUser()��ʼ���ã�����һ���û���Ϣ��");

			update(newModel, ud);

			log.info("UserDaoHibernateHQLImpl.saveUser()�������ã�����һ���û���Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.saveUser()����һ���û���Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public String userLogin(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		List list = findByHQL("from OaUserInfo where id=" + newModel.getId().getUserId() + " and password=" + newModel.getPassword(), ud);
		if (list != null) {
			// �õ�Ȩ����Ϣ�б�

		} else {
			// ���벻��ȷ
		}
		return null;
	}

	public void getFile(String groupid) throws Exception {

		String filename = "D:\\" + groupid + ".xml";
		File write = new File(filename);
		FileWriter fw = new FileWriter(write);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			String fenhang = "\r\n";
			StringBuffer bufferWriter = new StringBuffer();
			bufferWriter.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
			bufferWriter.append(fenhang);
			bufferWriter.append("<root text=\"ROOT\" open-action=\"true\">");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"XT\" text=\"ϵͳ����\">");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"xt1\" href=\"javascript:goframe('../../User.do?method=queryUser')\" text=\"�û�����\" target=\"body\" />");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"xt2\" href=\"javascript:goframe('../../UserGroup.do?method=queryUserGroup')\" text=\"��ɫ����\" target=\"body\" />");
			bufferWriter.append(fenhang);
			bufferWriter.append("</menu>");
			bufferWriter.append(fenhang);
			bufferWriter.append("</root>");

			bw.write(bufferWriter.toString());
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
			fw.close();
		}
	}

	public static void main(String args[]) {
		try {
			new UserDaoHibernateHQLImpl().getFile("llll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ѯ��ɫȨ��
	public List queryGroupAccessList(UserData ud) {
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

		}
		return list;
	}
}
