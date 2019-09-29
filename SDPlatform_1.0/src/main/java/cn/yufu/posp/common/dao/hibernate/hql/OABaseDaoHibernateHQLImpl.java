package cn.yufu.posp.common.dao.hibernate.hql;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.impl.SessionImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.core.dao.hibernate.hql.BaseDaoHibernateHQLImpl;

public class OABaseDaoHibernateHQLImpl extends BaseDaoHibernateHQLImpl {
	protected static final Log daoLog = LogFactory.getLog("dao");

	protected static final Log exceptionLog = LogFactory.getLog("exception");

	protected static final Log debugLog = LogFactory.getLog("debug");

	/**
	 * @param entity
	 * @roseuid 43C30BD00082
	 */

	public void saveOrUpdate(Object entity, UserData ud) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(String query, UserData ud) {
		((SessionImpl) getSession()).delete(query);

	}

	public List findBYCriteria(DetachedCriteria dCriterial, int firstIndex,
			int maxResult, UserData ud) {
		return getHibernateTemplate().findByCriteria(dCriterial, firstIndex,
				maxResult);
	}

	public List findBYCriteria(DetachedCriteria dCriterial, UserData ud) {
		return getHibernateTemplate().findByCriteria(dCriterial);
	}

	public void lock(Object o, UserData ud) {
		getHibernateTemplate().lock(o, LockMode.READ);
	}

	public List findByHQL(String searchString, int index, int maxResults,
			UserData ud) {
		Query query = this.getSession().createQuery(searchString);
		query.setFirstResult(index);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public List findBySQL(String searchString, UserData ud) {
		Query query = this.getSession().createSQLQuery(searchString);
		return query.list();
	}

	public List findBySQL(String searchString, String tObj, Class newClass,
			int index, int maxResults, UserData ud) {
		SQLQuery query = this.getSession().createSQLQuery(searchString);
		query.addEntity(tObj, newClass);
		query.setFirstResult(index);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public List findBySQL(String searchString, String tObj, Class newClass,
			UserData ud) {
		SQLQuery query = this.getSession().createSQLQuery(searchString);
		query.addEntity(tObj, newClass);

		return query.list();
	}

	/**
	 * 
	 * @param searchString
	 * @param index
	 * @param maxResults
	 * @param ud
	 * @return
	 */
	public List findBySQL(String searchString, int index, int maxResults,
			UserData ud) {
		SQLQuery query = this.getSession().createSQLQuery(searchString);
		query.setFirstResult(index);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public void saveOrUpdateOrDeleteBySQL(String sql, UserData ud) {
		Connection con = this.getSession().connection();
		try {
			Statement st = con.createStatement();
			st.execute(sql);
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.getSession().createSQLQuery(sql);
	}

	/**
	 * ·â×°getHibernateTemplate().find(hqlStr,objs)
	 * 
	 * @author yanhua
	 * @param hqlStr
	 * @param objs
	 * @param ud
	 * @return
	 */
	public List findByHQL(String hqlStr, Object[] objs, UserData ud) {
		List list = null;

		list = getHibernateTemplate().find(hqlStr, objs);

		return list;
	}

	public List call(String sqlStr, String[] strs) {
		Query query = this.getSession().createSQLQuery(sqlStr);
		for (int i = 0; i < strs.length; i++) {
			query.setString(i + 1, strs[i]);
		}
		return query.list();
	}
}
