package com.pay.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 
 * @TODO 数据库公用连接数据源
 * 
 * @author 袁鹏
 * @created on 2007-12-19 上午11:00:26
 * @version 1.0
 */
public class DbConn {
	private static final Logger logger = Logger.getLogger(DbConn.class);

	private static Context ctx = null;
	private static Context ctx_bak = null;
	private static String server_url = "";
	private static String initial_context = "";
	private static String ds_jndi = "";
	private static String ds_jndi_fkqz = "";
	private static String ds_jndi_bak = "";
	private static String ds_jndiCard="";
	private static String ds_jndiKeep="";
	private static String ds_jndiFk="";
	public DbConn() {
	}

	private static Context getContext()throws SQLException {
		Context ctt = null;

		try {
			if (SystemConfig.getValue("server_url") != null)
				server_url = SystemConfig.getValue("server_url");
			if (SystemConfig.getValue("initial_context") != null)
				initial_context = SystemConfig.getValue("initial_context");
			Properties proper = new Properties();
			//proper.put(Context.INITIAL_CONTEXT_FACTORY, initial_context); //weblogic 部署要用
			proper.put(Context.PROVIDER_URL, server_url);

			ctt = new InitialContext(proper);
		} catch (NamingException ne) {
			logger.error("get context error", ne);
		}

		return ctt;
	}

	/**
	 * 
	 * @TODO 从数据源获取连接,获取福卡前置数据库连接
	 * ds_jndi_fkqz
	 * @return 数据库连接
	 * @throws SQLException
	 * @author GL
	 * @version 1.1
	 */
	public static Connection getConnectionFkqz() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx == null) {
				DbConn.ctx = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndi_fkqz") != null) {
				ds_jndi_fkqz = SystemConfig.getValue("ds_jndi_fkqz");
			}
			if (ds_jndi_fkqz == null || ds_jndi_fkqz.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx.lookup(ds_jndi_fkqz);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	/**
	 * 
	 * @TODO 从数据源获取连接
	 * 
	 * @return 数据库连接
	 * @throws SQLException
	 * @author 袁鹏
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  上午11:02:16
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx == null) {
				DbConn.ctx = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndi") != null) {
				ds_jndi = SystemConfig.getValue("ds_jndi");
			}
			if (ds_jndi == null || ds_jndi.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx.lookup(ds_jndi);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	public static Connection getConnectionBak() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx_bak == null) {
				DbConn.ctx_bak = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndi_bak") != null) {
				ds_jndi_bak = SystemConfig.getValue("ds_jndi_bak");
			}
			if (ds_jndi_bak == null || ds_jndi_bak.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx_bak.lookup(ds_jndi_bak);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource bak error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	public static Connection getConnectionRun() throws SQLException {
		Connection conn = null;

		try {
			String sDBDriver = "oracle.jdbc.driver.OracleDriver";   
			Class.forName(sDBDriver);   
			conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.224:1521:cortex","OPS$YUFU_CHINA_PS_RUN","Cortex123"); 
		
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	/**
	 * 该方法为获取备用库链接，      add 2014-11
	 * @return
	 * @throws SQLException
	 */
	
	public static Connection getConnectionRunBak() throws SQLException {
		Connection conn = null;
		
		try {
			String sDBDriver = "oracle.jdbc.driver.OracleDriver";   
			Class.forName(sDBDriver);   
			conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.213:1521:cortex","OPS$YUFU_CHINA_PS_RUN","cortex"); 
			
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static Connection getConnectionCard() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx == null) {
				DbConn.ctx = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndiCard") != null) {
				ds_jndiCard = SystemConfig.getValue("ds_jndiCard");
			}
			if (ds_jndiCard == null || ds_jndiCard.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx.lookup(ds_jndiCard);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	public static Connection getConnectionKeep() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx == null) {
				DbConn.ctx = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndiKeep") != null) {
				ds_jndiKeep = SystemConfig.getValue("ds_jndiKeep");
			}
			if (ds_jndiKeep == null || ds_jndiKeep.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx.lookup(ds_jndiKeep);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	public static Connection getConnectionFk() throws SQLException {
		Connection conn = null;

		try {
			if (DbConn.ctx == null) {
				DbConn.ctx = DbConn.getContext();
				//DbConn.ctx=new InitialContext();
			}
			if (SystemConfig.getValue("ds_jndiFk") != null) {
				ds_jndiFk = SystemConfig.getValue("ds_jndiFk");
			}
			if (ds_jndiFk == null || ds_jndiFk.length() == 0) {
				return null;
			}
			DataSource ds = (DataSource) ctx.lookup(ds_jndiFk);
			conn = ds.getConnection();
			
			
		} catch (NamingException nee) {
			logger.error("get datasource error", nee);
		} catch (SQLException sne) {
			logger.error("sql error", sne);
			throw sne;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}	
	/**
	 * 
	 * @TODO 释放数据库连接
	 * 
	 * @param conn1
	 * @param st1
	 * @return conn是否正常释放
	 * @throws SQLException
	 * @author 袁鹏
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  上午11:02:56
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static boolean close(Connection conn1, Statement st1)
			throws SQLException {
		boolean result = true;

		if (st1 != null)
			st1.close();
		try {
			if (conn1 != null) {
				if (!conn1.getAutoCommit())
					conn1.setAutoCommit(true);
				conn1.close();
			}
		} catch (SQLException er) {
			logger.error("close conn error", er);
			result = false;
			throw er;
		}

		return result;
	}

	/**
	 * 
	 * @TODO 释放数据库连接
	 * 
	 * @param conn1
	 * @param pst1
	 * @return conn是否正常释放
	 * @throws SQLException
	 * @author 袁鹏
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  上午11:03:25
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static boolean close(Connection conn1, PreparedStatement pst1)
			throws SQLException {
		boolean result = true;

		if (pst1 != null)
			pst1.close();
		try {
			if (conn1 != null) {
				if (!conn1.getAutoCommit())
					conn1.setAutoCommit(true);
				conn1.close();
			}
		} catch (SQLException er) {
			logger.error("close conn error", er);

			result = false;
			throw er;
		}

		return result;
	}

	public static boolean close(Connection conn, PreparedStatement[] psts)
			throws SQLException {
		boolean result = true;

		if (psts != null && psts.length > 0) {
			for (int i = 0; i < psts.length; i++) {
				if (psts[i] != null)
					psts[i].close();
			}
		}
		try {
			if (conn != null) {
				if (!conn.getAutoCommit())
					conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException er) {
			logger.error("close conn error", er);

			result = false;
			throw er;
		}

		return result;
	}

	public static boolean close(Connection conn1, CallableStatement cst1)
			throws SQLException {
		boolean result = true;

		if (cst1 != null)
			cst1.close();
		try {
			if (conn1 != null) {
				if (!conn1.getAutoCommit())
					conn1.setAutoCommit(true);
				conn1.close();
			}
		} catch (SQLException er) {
			logger.error("close conn error", er);

			result = false;
			throw er;
		}

		return result;
	}
}