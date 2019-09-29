package cn.yufu.core.dao.hibernate.sql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BaseDaoHibernateSQLImpl extends HibernateDaoSupport implements BaseDaoHibernateSQL 
{
   
   /**
   @roseuid 43C35F4B0062
    */
   public BaseDaoHibernateSQLImpl() 
   {
    
   }
   
   /**
   @param sql
   @param entityClass
   @return List
   @roseuid 43C35B8E0110
    */
   public List executeSqlQuery(String sql, Class entityClass) 
   {
   		return executeSqlQuery(sql, null, entityClass, -1, -1);
   }
   
   /**
   @param sql
   @param entityClass
   @return List
   @roseuid 43C35B8E0110
    */
   public List executeSqlQuery(String sql, String alias, Class entityClass) 
   {
   		return executeSqlQuery(sql, alias, entityClass, -1, -1);
   }
   
   /**
   @param sql
   @param entityClass
   @param maxResults
   @return List
   @roseuid 43C35B8F00B8
    */
   public List executeSqlQuery(String sql, String alias, Class entityClass,int firstResult, int maxResults) 
   {  		
   		SQLQuery sqlquery;
   		if(alias.equals(null))	
   			//sqlquery = getSession().createSQLQuery(sql).addEntity(entityClass);
   			sqlquery = null;
   		else
   			sqlquery = getSession().createSQLQuery(sql).addEntity(alias, entityClass);
   		if(firstResult != -1 && maxResults != -1)
   			sqlquery.setFirstResult(firstResult).setMaxResults(maxResults);
   		
   		return sqlquery.list();
   }
   
   /**
   @param sqlQueryName
   @return List
   @roseuid 43C35B900240
    */
   public List executeSqlByNamedQuery(String sqlQueryName) 
   {
   		return executeSqlByNamedQuery(sqlQueryName, -1, -1);
   }
   
   /**
   @param sqlQueryName
   @param maxResults
   @return List
   @roseuid 43C35B9100CF
    */
   public List executeSqlByNamedQuery(String sqlQueryName, int firstResult, int maxResults) 
   {
   		Query query = getSession().getNamedQuery(sqlQueryName);
   		if(firstResult != -1 && maxResults != -1)
   			query.setFirstResult(firstResult).setMaxResults(maxResults);
		return query.list();
   }
}
