/*
 *
 * 本类中
 * Criterion (Restrictions Property) 用于限制结果集
 * Order 用于结果集排序
 * Projections 用于投影 聚合 分组等
 */
package cn.yufu.core.dao.hibernate.hql;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.yufu.posp.common.domain.model.UserData;

/**
实现方法中均处理异常
扩展 org.springframework.orm.hibernate.support.HibernateDaoSupport
 */
public class BaseDaoHibernateHQLImpl extends HibernateDaoSupport implements BaseDaoHibernateHQL
{

	public Restrictions restrictions;
	public Property property;
	public Projections projections;
	public Session session;

   /**
   @roseuid 43BE2F3901DD
    */
   public BaseDaoHibernateHQLImpl()
   {

   }

   /**
   @roseuid 43BE2F3901DD
    */
   public BaseDaoHibernateHQLImpl(SessionFactory sessionFactory)
   {
   		setSessionFactory(sessionFactory);
   }

   /**
   @param entity
   @roseuid 43C30BD00082
    */
   public void save(Object entity,UserData ud)
   {
   		getHibernateTemplate().save(entity);
   }
   
   public void merge(Object entity,UserData ud)
   {
	   getHibernateTemplate().merge(entity);
   }

   /**
   @param entities
   @roseuid 43C30BD000BE
    */
   public void saveAll(Collection entities,UserData ud)
   {
   		getHibernateTemplate().saveOrUpdateAll(entities);
   }

   /**
   @param entity
   @roseuid 43C30BD000F1
    */
   public void delete(Object entity,UserData ud)
   {
   		getHibernateTemplate().delete(entity);
   }

   /**
   @param entities
   @roseuid 43C30BD00123
    */
   public void deleteAll(Collection entities,UserData ud)
   {
   		getHibernateTemplate().deleteAll(entities);
   }

   /**
   @param entity
   @roseuid 43C30BD00155
    */
   public void update(Object entity,UserData ud)
   {
   		getHibernateTemplate().update(entity);
   }

   /**
   @param entities
   @roseuid 43C30BD00155
    */
   public void updateAll(Collection entities,UserData ud)
   {
   		getHibernateTemplate().saveOrUpdateAll(entities);
   }

   /**
   @param entities
   @roseuid 43C30BD00155
    */
   public void bulkUpdate(String hqlQueryString,UserData ud)
   {
   		getHibernateTemplate().bulkUpdate(hqlQueryString);
   }

   /**
   @param entities
   @roseuid 43C30BD00155
    */
   public void bulkUpdate(String hqlQueryString, Object entity,UserData ud)
   {
   		getHibernateTemplate().bulkUpdate(hqlQueryString, entity);
   }

   /**
   @param entities
   @roseuid 43C30BD00155
    */
   public void bulkUpdate(String hqlQueryString, Collection entities,UserData ud)
   {
   		getHibernateTemplate().bulkUpdate(hqlQueryString, entities);
   }

   /**
   @param hqlQueryString
   @return List
   @roseuid 43C30BD00187
    */
   public List findByHQL(String hqlQueryString,UserData ud)
   {
   		return getHibernateTemplate().find(hqlQueryString);
   }
   
   /**
   @param hqlQueryString
   @return List
   @roseuid 43C30BD00187
    */
   public int updateByHQL(String hqlQueryString,UserData ud)
   {
   		return getHibernateTemplate().bulkUpdate(hqlQueryString);
   }
 

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria,UserData ud)
   {
   		return criteria.list();
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Order order,UserData ud)
   {
   		return findByCriteria(criteria, order, -1, -1,ud);
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Order order, int firstResult , int maxResults,UserData ud)
   {
   		criteria.addOrder(order);
   		if(firstResult != -1 && maxResults != -1 )
   			criteria.setFirstResult(firstResult).setMaxResults(maxResults);
   		return criteria.list();
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion,UserData ud)
   {
   		return findByCriteria(criteria, criterion, -1, -1,ud);
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, int firstResult , int maxResults,UserData ud)
   {
   		criteria.add(criterion);
   		if(firstResult != -1 && maxResults != -1 )
   			criteria.setFirstResult(firstResult).setMaxResults(maxResults);
   		return criteria.list();
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order,UserData ud)
   {
   		criteria.add(criterion).addOrder(order);
   		return criteria.list();
   }

   /**
   @param criteria
   @param restriction
   @return List
   @roseuid 43C30BD001C3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order, int firstResult , int maxResults,UserData ud)
   {
   		criteria.add(criterion).addOrder(order).setFirstResult(firstResult).setMaxResults(maxResults);
   		return criteria.list();
   }

   /**
   @param criteria
   @param restrictions
   @return List
   @roseuid 43C30BD00231
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders,UserData ud)
   {
   		return findByCriteria(criteria, criterions, orders, -1, -1,ud);
   }

   /**
   @param criteria
   @param restrictions
   @return List
   @roseuid 43C30BD00231
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, int firstResult , int maxResults,UserData ud)
   {
   		Iterator it = criterions.iterator();
   		while(it.hasNext())
   		{
   			criteria.add((Criterion)it.next());
   		}
   		Iterator ordersit = orders.iterator();
		while(ordersit.hasNext())
		{
			criteria.addOrder((Order)ordersit.next());
		}

		if(firstResult != -1 && maxResults != -1 )
			criteria.setFirstResult(firstResult).setMaxResults(maxResults);

		return criteria.list();
   }

   /**
   @param criteria
   @param restrictionSql
   @return List
   @roseuid 43C30BD00303
    */
   public List findByCriteria(Criteria criteria, String sql,UserData ud)
   {
   		criteria.add(Restrictions.sqlRestriction(sql));
   		return criteria.list();
   }

   /**
   @param criteria
   @param restrictionSql
   @return List
   @roseuid 43C30BD00303
    */
   public List findByCriteria(Criteria criteria, String sql, int firstResult , int maxResults,UserData ud)
   {
   		criteria.add(Restrictions.sqlRestriction(sql));
   		criteria.setFirstResult(firstResult).setMaxResults(maxResults);
   		return criteria.list();
   }

   /**
   @param criteria
   @param restrictions
   @param properties
   @param orders
   @param projections
   @return List
   @roseuid 43C30BD0037B
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, Collection projections,UserData ud)
   {
   		return findByCriteria(criteria, criterions, orders, projections , -1, -1,ud);
   }

   /**
   @param criteria
   @param restrictions
   @param properties
   @param orders
   @param projections
   @return List
   @roseuid 43C30BD0037B
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, Collection projections, int firstResult , int maxResults,UserData ud)
   {
   		Iterator projectionsit = projections.iterator();
   		ProjectionList pl = Projections.projectionList();
		while(projectionsit.hasNext())
		{
			pl.add((Projection)projectionsit.next());
		}
		criteria.setProjection(pl);

		Iterator criterionsit = criterions.iterator();
		while(criterionsit.hasNext())
		{
			criteria.add((Criterion)criterionsit.next());
		}

		Iterator ordersit = orders.iterator();
		while(ordersit.hasNext())
		{
			criteria.addOrder((Order)ordersit.next());
		}

		if(firstResult != -1 && maxResults != -1 )
			criteria.setFirstResult(firstResult).setMaxResults(maxResults);

		return criteria.list();
   }

   /**
   @param sess
   @param entityClass
   @return Criteria
   @roseuid 43C30BD1008E
    */
   public Criteria createCriteria(Session session, Class entityClass,UserData ud)
   {
   		return session.createCriteria(entityClass);
   }

   /**
   @param sess
   @param queryStr
   @return Query
   @roseuid 43C30BD10106
    */
   public Query createQuery(Session sess, String queryStr,UserData ud)
   {
   		return sess.createQuery(queryStr);
   }

}
