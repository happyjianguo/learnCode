package cn.yufu.core.dao.hibernate.sql;

import java.util.List;

public interface BaseDaoHibernateSQL {

	/**
	 * ����sql����һ�����ʵ�� session.createSQLQuery(sql) .addEntity("entity",
	 * entity.class);
	 * 
	 * @param sql
	 *            - SQL��ѯ���
	 * @param entityClass
	 *            - �־û�����.class
	 * @return List
	 * @roseuid 43C0B0E8022E
	 */
	public List executeSqlQuery(String sql, Class entityClass);

	/**
	 * ����sql����һ�����ʵ��
	 * 
	 * @param sql
	 * @param alias
	 * @param entityClass
	 * @return
	 */
	public List executeSqlQuery(String sql, String alias, Class entityClass);

	/**
	 * ����sql����һ�����ʵ��
	 * 
	 * @param sql
	 * @param entityClass
	 * @param maxResults
	 *            - ����¼��
	 * @return List
	 * @roseuid 43C0B1A500F9
	 */
	public List executeSqlQuery(String sql, String alias, Class entityClass, int firstResult, int maxResults);

	/**
	 * ִ��ӳ���ļ��ж�����ɵĲ�ѯ ����ҪaddEntity()����
	 * 
	 * @param sqlQueryName
	 * @return List
	 * @roseuid 43C0B53A0198
	 */
	public List executeSqlByNamedQuery(String sqlQueryName);

	/**
	 * ִ��ӳ���ļ��ж�����ɵĲ�ѯ �趨��ʼ�� �����
	 * 
	 * @param sqlQueryName
	 * @param maxResults
	 * @return List
	 * @roseuid 43C0B65D0359
	 */
	public List executeSqlByNamedQuery(String sqlQueryName, int firstResult, int maxResults);
}
