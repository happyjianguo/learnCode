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
����������ݷ�����Ľӿ� ʹ��O/R Mapping Hibernate ��HQL
 */
public interface BaseDaoHibernateHQL
{

   /**
   ���浥��ʵ������Ӧ���ݿ��м�¼��
   @param entity
   @roseuid 439D44E802EC
    */
   public void save(Object entity,UserData ud);

   /**
   ����һ��ʵ������Ӧ���ݿ��м�¼��
   @param entities
   @roseuid 43BE0E29009C
    */
   public void saveAll(Collection entities,UserData ud);

   /**
   ɾ������ʵ������Ӧ���ݿ��м�¼��
   @param entity
   @roseuid 439D44ED032F
    */
   public void delete(Object entity,UserData ud);

   /**
   ɾ��һ��ʵ������Ӧ���ݿ��м�¼��
   @param entities
   @roseuid 43BE0E4D02C4
    */
   public void deleteAll(Collection entities,UserData ud);

   /**
   ���µ���ʵ������Ӧ���ݿ��м�¼��
   @param entity
   @roseuid 439D4511010A
    */
   public void update(Object entity,UserData ud);

   /**
    * ����һ��ʵ��
    * @param entities
    */
   public void updateAll(Collection entities,UserData ud);

   /**
   ����HQL��������һ�����ʵ������Ӧ���ݿ��м�¼��
   @param hqlQueryString
   @return List
   @roseuid 439D45140335
    */
   public List findByHQL(String hqlQueryString,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @return
    */
   public List findByCriteria(Criteria criteria,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param order
    * @return
    */
   public List findByCriteria(Criteria criteria, Order order,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param order
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Order order, int firstResult , int maxResults,UserData ud);

   /**
    * ����criteria����һ�����ʵ��
   @param criteria
   @param property - ��������Լ��������Լ������һ�ַ�ʽ��ͬRestriction��
   @return List
   @roseuid 43C08DDA00A3
    */
   public List findByCriteria(Criteria criteria, Criterion criterion,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param criterion
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, int firstResult , int maxResults,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param criterion
    * @param order
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param criterion
    * @param order
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Criterion criterion, Order order, int firstResult , int maxResults,UserData ud);

   /**
    * ����criteria����һ�����ʵ��
   @param criteria - createCriteria()
   @param restrictions - һ������Լ��
   @return List
   @roseuid 43C0894D02CF
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param criterions
    * @param orders
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, int firstResult , int maxResults,UserData ud);

   /**
    * ����criteria����һ�����ʵ��
   @param criteria
   @param restrictionSql - sql�����ַ���
   @return List
   @roseuid 43C08BDB0063
    */
   public List findByCriteria(Criteria criteria, String sql,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
    * @param criteria
    * @param sql
    * @param firstResult
    * @param maxResults
    * @return
    */
   public List findByCriteria(Criteria criteria, String sql, int firstResult , int maxResults,UserData ud);

   /**
    * ����criteria����һ�����ʵ��
   @param criteria
   @param restrictions - һ��Restriction
   @param properties - һ��Property
   @param orders - һ��Order
   @param projections - һ��Projection
   @return List
   @roseuid 43C094D20135
    */
   public List findByCriteria(Criteria criteria, Collection criterions, Collection orders, Collection projections,UserData ud);
   /**
    * ����criteria����һ�����ʵ��
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
    * ����criteria����һ�����ʵ��
   ����һ��������ѯ
   @param sess
   @param entityClass
   @return Criteria
   @roseuid 43C07FA40355
    */
   public Criteria createCriteria(Session sess, Class entityClass,UserData ud);

   /**
   ����һ��Query
   @param sess
   @param queryStr
   @return Query
   @roseuid 43C080070267
    */
   public Query createQuery(Session sess, String queryStr,UserData ud);

}
