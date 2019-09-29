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
			log.info("EdcTpduDaoHibernateHQLImpl.createItem()开始调用：保存一个终端TPDU信息。");

			save(tPreTpdu, ud);

			log.info("EdcTpduDaoHibernateHQLImpl.createItem()结束调用：保存一个终端TPDU信息。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.createItem()保存一个终端TPDU信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()开始调用：删除终端TPDU信息。");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((String) keys.get(i), ud), ud);
			}

			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()结束调用：删除终端TPDU信息。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.deleteItem()删除终端TPDU信息，出现异常");
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
			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TPreTpdu.class);
			cr.add(Restrictions.eq("tpdu", key));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TPreTpdu) list.get(0);

			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()结束调用：显示一条记录。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.findItemByKey()显示一条记录，出现异常");
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
			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()开始调用：查询符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from TPreTpdu t where 1=1");

			// 按机构ID查询
			if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
				bufferHql.append(" and t.tpdu = '" + tPreTpdu.getTpdu() + "'");

			// 按连接类型查询
			if (tPreTpdu.getPosLinkType() != null && !tPreTpdu.getPosLinkType().equals(""))
				bufferHql.append(" and t.posLinkType = '" + tPreTpdu.getPosLinkType() + "'");

			// 添加排序信息
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
			// // 按TPDU查询
			// if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
			// dcr.add(Restrictions.eq("tpdu",tPreTpdu.getTpdu().toString()));
			//
			// // 按连接类型查询
			// if (tPreTpdu.getPosLinkType() != null&&
			// !tPreTpdu.getPosLinkType() .equals(""))
			// dcr.add(Restrictions.like("posLinkType",
			// tPreTpdu.getPosLinkType(),MatchMode.ANYWHERE));
			//
			// // 添加排序信息
			// if (sortfield != null && sortType != null) {
			// if (sortType.equals("asc"))
			// dcr.addOrder(Order.asc(sortfield));
			// else
			// dcr.addOrder(Order.desc(sortfield));
			// } else {
			// // 按 tpdu 排序
			// dcr.addOrder(Order.desc("tpdu"));
			// }
			// list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("查询语句：hql" + hql + "");
			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()结束调用：查询符合条件的记录。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.queryAllItem()查询符合条件的记录，出现异常");
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
			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的记录数。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from TPreTpdu t where 1=1");

			// 按机构ID查询
			if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
				bufferHql.append(" and t.tpdu = '" + tPreTpdu.getTpdu() + "'");

			// 按连接类型查询
			if (tPreTpdu.getPosLinkType() != null && !tPreTpdu.getPosLinkType().equals(""))
				bufferHql.append(" and t.posLinkType = '" + tPreTpdu.getPosLinkType() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()结束调用：查询符合条件的记录数。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.queryCount()查询符合条件的记录数，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		try {
			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()开始调用：修改信息。");

			update(tPreTpdu, ud);

			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()结束调用：修改信息。");
		} catch (Exception e) {

			log.info("EdcTpduDaoHibernateHQLImpl.saveItem()修改信息，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
