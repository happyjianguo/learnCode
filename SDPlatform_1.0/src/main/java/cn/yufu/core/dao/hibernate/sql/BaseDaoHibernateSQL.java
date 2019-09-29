package cn.yufu.core.dao.hibernate.sql;

import java.util.List;

public interface BaseDaoHibernateSQL {

	/**
	 * 根据sql返回一组对象实例 session.createSQLQuery(sql) .addEntity("entity",
	 * entity.class);
	 * 
	 * @param sql
	 *            - SQL查询语句
	 * @param entityClass
	 *            - 持久化对象.class
	 * @return List
	 * @roseuid 43C0B0E8022E
	 */
	public List executeSqlQuery(String sql, Class entityClass);

	/**
	 * 根据sql返回一组对象实例
	 * 
	 * @param sql
	 * @param alias
	 * @param entityClass
	 * @return
	 */
	public List executeSqlQuery(String sql, String alias, Class entityClass);

	/**
	 * 根据sql返回一组对象实例
	 * 
	 * @param sql
	 * @param entityClass
	 * @param maxResults
	 *            - 最大记录数
	 * @return List
	 * @roseuid 43C0B1A500F9
	 */
	public List executeSqlQuery(String sql, String alias, Class entityClass, int firstResult, int maxResults);

	/**
	 * 执行映射文件中定义完成的查询 不需要addEntity()方法
	 * 
	 * @param sqlQueryName
	 * @return List
	 * @roseuid 43C0B53A0198
	 */
	public List executeSqlByNamedQuery(String sqlQueryName);

	/**
	 * 执行映射文件中定义完成的查询 设定初始数 最大数
	 * 
	 * @param sqlQueryName
	 * @param maxResults
	 * @return List
	 * @roseuid 43C0B65D0359
	 */
	public List executeSqlByNamedQuery(String sqlQueryName, int firstResult, int maxResults);
}
