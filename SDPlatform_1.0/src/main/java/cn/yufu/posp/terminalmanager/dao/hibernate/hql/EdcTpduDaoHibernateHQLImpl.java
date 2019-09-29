package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session; //import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;

public class EdcTpduDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcTpduDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTpdu");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcTpduDaoHibernateHQLImpl() {
	}

	public void createItem(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ���ն�TPDU��Ϣ��");

			save(tPreTpdu, ud);

			log.info("EdcTpduDaoHibernateHQLImpl.createItem()�������ã�����һ���ն�TPDU��Ϣ��");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.createItem()����һ���ն�TPDU��Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն�TPDU��Ϣ��");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((String) keys.get(i), ud), ud);
			}

			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն�TPDU��Ϣ��");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()ɾ���ն�TPDU��Ϣ�������쳣");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public TPreTpdu findItemByKey(String key, UserData ud) throws OAException {
		TPreTpdu model = null;
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TPreTpdu.class);
			cr.add(Restrictions.eq("tpdu", key));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TPreTpdu) list.get(0);

			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ����¼��");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()��ʾһ����¼�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(TPreTpdu tPreTpdu, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<TPreTpdu> list = new ArrayList<TPreTpdu>();
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ���������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from TPreTpdu t where 1=1");

			// ������ID��ѯ
			if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
				bufferHql.append(" and t.tpdu = '" + tPreTpdu.getTpdu() + "'");

			// ���������Ͳ�ѯ
			if (tPreTpdu.getPosLinkType() != null && !tPreTpdu.getPosLinkType().equals(""))
				bufferHql.append(" and t.posLinkType = '" + tPreTpdu.getPosLinkType() + "'");

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.tpdu desc "));
			}

			String hql = bufferHql.toString();
			List<TPreTpdu> tPreTpduList = new ArrayList<TPreTpdu>();
			tPreTpduList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > tPreTpduList.size())
				endIndex = tPreTpduList.size();

			list = tPreTpduList.subList(startIndex, endIndex);

			// DetachedCriteria dcr = DetachedCriteria.forClass(TPreTpdu.class);
			//
			// // ��TPDU��ѯ
			// if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
			// dcr.add(Restrictions.eq("tpdu",tPreTpdu.getTpdu().toString()));
			//
			// // ���������Ͳ�ѯ
			// if (tPreTpdu.getPosLinkType() != null&&
			// !tPreTpdu.getPosLinkType() .equals(""))
			// dcr.add(Restrictions.like("posLinkType",
			// tPreTpdu.getPosLinkType(),MatchMode.ANYWHERE));
			//
			// // ���������Ϣ
			// if (sortfield != null && sortType != null) {
			// if (sortType.equals("asc"))
			// dcr.addOrder(Order.asc(sortfield));
			// else
			// dcr.addOrder(Order.desc(sortfield));
			// } else {
			// // �� tpdu ����
			// dcr.addOrder(Order.desc("tpdu"));
			// }
			// list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("��ѯ��䣺hql" + hql + "");
			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ���������ļ�¼��");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()��ѯ���������ļ�¼�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ���������ļ�¼����");

			StringBuffer bufferHql = new StringBuffer("select count(*) from TPreTpdu t where 1=1");

			// ������ID��ѯ
			if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
				bufferHql.append(" and t.tpdu = '" + tPreTpdu.getTpdu() + "'");

			// ���������Ͳ�ѯ
			if (tPreTpdu.getPosLinkType() != null && !tPreTpdu.getPosLinkType().equals(""))
				bufferHql.append(" and t.posLinkType = '" + tPreTpdu.getPosLinkType() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()�������ã���ѯ���������ļ�¼����");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()��ѯ���������ļ�¼���������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸���Ϣ��");

			update(tPreTpdu, ud);

			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()�������ã��޸���Ϣ��");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()�޸���Ϣ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
