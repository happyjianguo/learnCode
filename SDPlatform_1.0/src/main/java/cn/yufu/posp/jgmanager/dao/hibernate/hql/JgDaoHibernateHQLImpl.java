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
	 *���һ�������
	 */
	public int queryJgCount(JgModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from JgModel o where 1=1");

			// ������ID��ѯ
			if (queryModel.getJgId() != null && !queryModel.getJgId().equals(""))
				bufferHql.append(" and o.jgId = '" + queryModel.getJgId() + "'");

			// ��������
			if (queryModel.getJgName() != null && !queryModel.getJgName().equals(""))
				bufferHql.append(" and o.jgName like '%" + queryModel.getJgName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("JgDaoHibernateHQLImpl.queryJgCount()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgCount()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *���һ���
	 */
	public List queryJg(JgModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJg()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(JgModel.class);

			// ������ID��ѯ
			if (queryModel.getJgId() != null && !queryModel.getJgId().equals(""))
				dcr.add(Restrictions.idEq(queryModel.getJgId()));

			// ��������
			if (queryModel.getJgName() != null && !queryModel.getJgName().equals(""))
				dcr.add(Restrictions.like("jgName", queryModel.getJgName(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("jgId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("queryJg()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJg()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *ɾ������
	 */
	public void deleteJg(List newKeys, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.deleteJg()���ÿ�ʼ��ɾ��������");

			for (int i = 0; i < newKeys.size(); i++) {

				delete(queryJgByKey((String) newKeys.get(i), ud), ud);
			}

			log.info("JgDaoHibernateHQLImpl.deleteJg()�������ã�ɾ��������");
		} catch (Exception e) {
			log.error("JgDaoHibernateHQLImpl.deleteJg()ɾ�������������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 * ɾ����ɫ
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

			log.error("JgDaoHibernateHQLImpl.savenoticeAdvice()����һ���ϱ���Ϣ����������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	/**
	 * ���� clientIdɾ���û�
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

			log.error("JgDaoHibernateHQLImpl.saveuser(UserModel advice, UserData ud)����һ���û���Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	/**
	 *�½�����
	 */
	public void createJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.createJg()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);
			// �����û�
			OaUserInfo noticeModel = new OaUserInfo();
			noticeModel.setPassword("2e30ba3eed21d0ad408e9eb8ffd7a018");
			noticeModel.setLastPasswordReset(new Date());
			noticeModel.setCreateTimestamp(new Date());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			noticeModel.setPasswordExpiry(df.parse("2046-12-31"));
			noticeModel.setCreateUser(ud.getUserId());
			noticeModel.setUserName("��ʼ�û�");
			noticeModel.setGroupId("system");
			noticeModel.setPasswordAttempts(0l);
			noticeModel.setPasswordAttemptsLimit(20l);
			noticeModel.setStatus("A");
			noticeModel.setUserType("03");
			noticeModel.setId(new OaUserInfoId(newModel.getJgId(), "system"));
			save(noticeModel, ud);
			// ������ʼ��ɫ
			OaGroupInfo groupModel = new OaGroupInfo();
			// form��Ϣ���Ƶ� model
			groupModel.setGroupDescription("��ʼ��ɫ");
			groupModel.setCreateUser(ud.getUserId());
			groupModel.setUpdateTimestamp(new Date());
			groupModel.setCreateTimestamp(new Date());
			groupModel.setId(new OaGroupInfoId(newModel.getJgId(), "system"));
			save(groupModel, ud);
			// Ϊ��ʼ��ɫ��ֵ
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

			log.info("JgDaoHibernateHQLImpl.createJg()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.createJg()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *��KEY���һ���
	 */
	public JgModel queryJgByKey(String newKey, UserData ud) throws OAException {
		JgModel officeSuppliesTypeModel = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgByKey()��ʼ���ã�ͨ����Ż�����");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(JgModel.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (JgModel) list.get(0);

			log.info("JgDaoHibernateHQLImpl.queryJgByKey()�������ã�ͨ����Ż�����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()ͨ����Ż����������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *�������
	 */
	public void saveJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.saveJg()��ʼ���ã����������");

			update(newModel, ud);

			log.info("JgDaoHibernateHQLImpl.saveJg()�������ã����������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.saveJg()������������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
