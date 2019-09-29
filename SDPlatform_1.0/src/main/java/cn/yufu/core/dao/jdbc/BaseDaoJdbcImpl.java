package cn.yufu.core.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
spring JDBC封装 使用JdbcTemplate
扩展
org.springframe.jdbc.core.Jdbc.DaoSupport
 */
public class BaseDaoJdbcImpl extends JdbcDaoSupport implements BaseDaoJdbc 
{
   
   /**
   @roseuid 43C30BD3000F
    */
   public BaseDaoJdbcImpl() 
   {

   }
   
   /**
   @param arg
   @roseuid 43BC87DB0279
    */
   public BaseDaoJdbcImpl(DataSource datasource) 
   {
   		setDataSource(datasource);
   }
   
   /**
   @param sql
   @roseuid 43C30BD30019
    */
   public void execute(String sql) 
   {
   		getJdbcTemplate().execute(sql);
   }
   
   /**
   @param sql
   @roseuid 43C30BD30055
    */
   public void update(String sql) 
   {
   		getJdbcTemplate().update(sql);
   }
   
   /**
   @param sql
   @param ps
   @roseuid 43C30BD30087
    */
   public void update(String sql, PreparedStatementSetter ps) 
   {
   		getJdbcTemplate().update(sql, ps);
   }
   
   /**
   @param sql
   @param rch
   @return List
   @roseuid 43C30BD300D7
    */
   public List query(String sql, RowCallbackHandler rch) 
   {
   		return null;//getJdbcTemplate().query(sql, rch);
   }
   
   /**
   @param sql
   @return List
   @roseuid 43C30BD30127
    */
   public List queryForList(String sql) 
   {
   		return getJdbcTemplate().queryForList(sql);
   }
}
