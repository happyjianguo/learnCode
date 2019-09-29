package cn.yufu.core.dao.hibernate.hql;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.yufu.posp.common.domain.model.UserData;

/**
定义基础数据访问类的接口 使用O/R Mapping Hibernate 的HQL
 */
public interface BaseDaoHibernateHQL
{

   /**
   保存单个实例（对应数据库中记录）
   @param entity
   @roseuid 439D44E802EC
    */
   public void save(Object entity,UserData ud);

   /**
   保存一组实例（对应数据库中记录）
   @param entities
   @roseuid 43BE0E29009C
    */
   public void saveAll(Collection entities,UserData ud);

   /**
   删除单个实例（对应数据库中记录）
   @param entity
   @roseuid 439D44ED032F
    */
   public void delete(Object entity,UserData ud);

   /**
   删除一组实例（对应数据库中记录）
   @param entities
   @roseuid 43BE0E4D02C4
    */
   public void deleteAll(Collection entities,UserData ud);

   /**
   更新单个实例（对应数据库中记录）
   @param entity
   @roseuid 439D4511010A
    */
   public void update(Object entity,UserData ud);

   /**
    * 更新一组实例
    * @param entities
    */
   public void updateAll(Collection entities,UserData ud);

   /**
   根据HQL检索返回一组对象实例（对应数据库中记录）
   @param hqlQueryString
   @return List
   @roseuid 439D45140335
    */
   public List findByHQL(String hqlQueryString,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @return
    */
   public List findByCriteria(Criteria criteria,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param order
    * @return
    */
   public List findByCriteria(Criteria criteria, Order order,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param order
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Order order, int firstResult , int maxResults,UserData ud);

   /**
    * 根据criteria返回一组对象实例
   @param criteria
   @param property - 单个条件约束（创建约束的另一种方式，同Restriction）
   @return List
   @roseuid 43C08DDA00A3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param criterion
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, int firstResult , int maxResults,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param criterion
    * @param order
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param criterion
    * @param order
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order, int firstResult , int maxResults,UserData ud);

   /**
    * 根据criteria返回一组对象实例
   @param criteria - createCriteria()
   @param restrictions - 一组条件约束
   @return List
   @roseuid 43C0894D02CF
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param criterions
    * @param orders
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, int firstResult , int maxResults,UserData ud);

   /**
    * 根据criteria返回一组对象实例
   @param criteria
   @param restrictionSql - sql条件字符串
   @return List
   @roseuid 43C08BDB0063
    */
   public List findByCriteria(Criteria criteria, String sql,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param sql
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, String sql, int firstResult , int maxResults,UserData ud);

   /**
    * 根据criteria返回一组对象实例
   @param criteria
   @param restrictions - 一组Restriction
   @param properties - 一组Property
   @param orders - 一组Order
   @param projections - 一组Projection
   @return List
   @roseuid 43C094D20135
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, Collection projections,UserData ud);
   /**
    * 根据criteria返回一组对象实例
    * @param criteria
    * @param criterions
    * @param orders
    * @param projections
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, Collection projections, int firstResult , int maxResults,UserData ud);

   /**
    * 根据criteria返回一组对象实例
   创建一个条件查询
   @param sess
   @param entityClass
   @return Criteria
   @roseuid 43C07FA40355
    */
   public Criteria createCriteria(Session sess, Class entityClass,UserData ud);

   /**
   创建一个Query
   @param sess
   @param queryStr
   @return Query
   @roseuid 43C080070267
    */
   public Query createQuery(Session sess, String queryStr,UserData ud);

}
