package cn.yufu.core.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;


public interface BaseDaoJdbc 
{
   
   /**
   @param sql
   @roseuid 43C0CFE5002C
    */
   public void execute(String sql);
   
   /**
   @param sql
   @roseuid 43C0CE6F01C9
    */
   public void update(String sql);
   
   /**
   @param sql
   @param ps
   @roseuid 43C0CEA20367
    */
   public void update(String sql, PreparedStatementSetter ps);
   
   /**
   @param sql
   @param rch
   @return List
   @roseuid 43C0CD9000A6
    */
   public List query(String sql, RowCallbackHandler rch);
   
   /**
   @param sql
   @return List
   @roseuid 43C0D16D03B5
    */
   public List queryForList(String sql);
}
