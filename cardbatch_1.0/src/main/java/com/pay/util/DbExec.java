package com.pay.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

/**
 * 
 * @TODO ���ݿ⹫��ִ�з��� �汾һ
 * 
 * @author Ԭ��
 * @created on 2007-12-19 ����11:06:19 LastModified:2010-02-23 qhg ���
 *          ���˴���java�쳣�Լ�SQLException����������쳣 ��DbExec �ⲿ����Exception�������׳�Exception
 *          ������SQLException д����־ �������µĹ���
 * 
 * @version 1.0
 */
public class DbExec {
	private static final Logger logger = Logger.getLogger(DbExec.class);
	public static final int DB_ERROR_INPUTNULL = -1000;
	public static final int DB_ERROR_GETCONN = -1001;
	public static final int DB_ERROR_FINDRECORD = -1002;
	public static final int DB_ERROR_MAKEPSTAT = -1003;
	public static final int DB_ERROR_REPEATRECORD = -239;
	public static final String SQL_USERLOG = "insert into user_log(userid,username,userdsr,oprdt,remark) values(?,?,?,?,?)";

	public static Vector execQueryFkqz(String sql, List params, int start,
			int recordSize) throws SQLException, Exception {

		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionFkqz();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param[:"+(i+1)+"]"+params.get(i));
				}
			}
			pstmt.setMaxRows(start + recordSize - 1);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs
											.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else if (columnType[i] == 2005) {
								java.sql.Clob clob = rs.getClob(columnName[i]);
								Reader inStream = clob.getCharacterStream();
								char[] c = new char[(int) clob.length()];
								inStream.read(c);

								record.put(columnName[i], new String(c));

								inStream.close();
							} else {
								record.put(columnName[i].toLowerCase(), rs
										.getString(columnName[i]));
							}

						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);

				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}
	/**
	 * 
	 * @TODO ִ�в�ѯ���
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param params
	 *            ��ѯ�������
	 * @return ���������Ϊkey,��ѯ���Ϊvalue��HashMap��List
	 * @throws SQLException
	 */
	public static Vector execQueryFkqz(String sql, List params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionFkqz();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param["+(i+1)+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}

					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}
	/**
	 * 
	 * @param sql
	 *            ִ����� �磺"insert into tab valuse(?,?,?)"
	 * @param param
	 *            ������� �磺Object[] obj = {"1","2","3"}; param.add(obj);
	 * @return ִ�н��
	 * @throws SQLException
	 * </pre>
	 */
	public static int execBatchFkqz(String sql, List param) throws SQLException,
	Exception {
		int result = 0;
		String mySql = sql;
		if (mySql == null)
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnectionFkqz();
		if (conn == null)
			return DB_ERROR_GETCONN;
		conn.setAutoCommit(false);
		StringBuffer sb = new StringBuffer();
		sb.append(mySql);
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		try {
			for (int i = 0; i < param.size(); i++) {
				Object[] obj = (Object[]) param.get(i);

				if (obj == null || obj.length == 0)
					return DB_ERROR_INPUTNULL;

				for (int j = 0; j < obj.length; j++) {
					pstmt.setObject(j + 1, obj[j]);
				}
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException ne) {
			try {
				result = -1;
				conn.rollback();
				logger.error("SQLException:" + sql + "|", ne);
				throw ne;
			} catch (SQLException e) {
				logger.error("SQLException:" + sql + "|", e);
				throw e;
			}
		} catch (Exception e) {
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return result;
	}
	
	public static int execUpdateFkqz(String sql, List params) throws SQLException,Exception {
		String mySql = sql;
		if (sql == null)
			return DB_ERROR_INPUTNULL;

		int result = 0;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionFkqz();
		} catch (SQLException e1) {
			result = DB_ERROR_GETCONN;
			logger.error("SQLException:" + sql + "|", e1);
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		PreparedStatement pstmt = null;
		try {
			if (conn == null) {
				result = DB_ERROR_GETCONN;
				throw new SQLException("" + DB_ERROR_GETCONN);
			}
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString());
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			if (conn != null)
				conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return result;
	}
	/**
	 * 
	 * @param sql
	 *            ִ����� �磺"insert into tab valuse(?,?,?)"
	 * @param param
	 *            ������� �磺Object[] obj = {"1","2","3"}; param.add(obj);
	 * @return ִ�н��
	 * @throws SQLException
	 * @author Ԭ��
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:2007-12-19  ����11:07:39
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static int execBatch(String sql, List param) throws SQLException,
			Exception {
		int result = 0;
		String mySql = sql;
		if (mySql == null)
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnection();
		if (conn == null)
			return DB_ERROR_GETCONN;
		conn.setAutoCommit(false);
		StringBuffer sb = new StringBuffer();
		sb.append(mySql);
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		try {
			for (int i = 0; i < param.size(); i++) {
				Object[] obj = (Object[]) param.get(i);

				if (obj == null || obj.length == 0)
					return DB_ERROR_INPUTNULL;

				for (int j = 0; j < obj.length; j++) {
					pstmt.setObject(j + 1, obj[j]);
				}
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException ne) {
			try {
				result = -1;
				conn.rollback();
				logger.error("SQLException:" + sql + "|", ne);
				throw ne;
			} catch (SQLException e) {
				logger.error("SQLException:" + sql + "|", e);
				throw e;
			}
		} catch (Exception e) {
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return result;
	}

	/**
	 * 
	 * @TODO
	 * 
	 * @param sql
	 *            String sql1 = "..."; sql.add(sql1);
	 * @param param
	 *            Object[] obj = {"1","2","3"}; param.add(obj);
	 * @return
	 * @throws SQLException
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:Apr 19, 2008  3:24:11 PM
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static int execBatch(List sql, List param) throws SQLException,
			Exception {
		int result = 0;
		List sqls = sql;
		List params = param;
		if (sqls == null || sqls.isEmpty())
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnection();
		if (conn == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		conn.setAutoCommit(false);
		PreparedStatement[] pstmts = new PreparedStatement[sqls.size()];
		try {
			for (int i = 0; i < sqls.size(); i++) {
				try {
					pstmts[i] = conn.prepareStatement((String) sqls.get(i));
				} catch (SQLException e) {
					result = DB_ERROR_MAKEPSTAT;
					throw new SQLException("" + DB_ERROR_MAKEPSTAT);
				}
				Object[] obj = (Object[]) params.get(i);
				if (obj != null && obj.length > 0) {
					for (int j = 0; j < obj.length; j++) {
						pstmts[i].setObject(j + 1, obj[j]);
					}
				}
				try {
					pstmts[i].executeUpdate();
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmts);
		}

		return result;
	}

	public static int execBatchKeep(List sql, List param) throws SQLException,
			Exception {
		int result = 0;
		List sqls = sql;
		List params = param;
		if (sqls == null || sqls.isEmpty())
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnectionKeep();
		if (conn == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		conn.setAutoCommit(false);
		PreparedStatement[] pstmts = new PreparedStatement[sqls.size()];
		try {
			for (int i = 0; i < sqls.size(); i++) {
				try {
					pstmts[i] = conn.prepareStatement((String) sqls.get(i));
				} catch (SQLException e) {
					result = DB_ERROR_MAKEPSTAT;
					throw new SQLException("" + DB_ERROR_MAKEPSTAT);
				}
				Object[] obj = (Object[]) params.get(i);
				if (obj != null && obj.length > 0) {
					for (int j = 0; j < obj.length; j++) {
						pstmts[i].setObject(j + 1, obj[j]);
					}
				}
				try {
					pstmts[i].executeUpdate();
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmts);
		}

		return result;
	}

	/**
	 * 
	 * @TODO
	 * 
	 * @param sql
	 *            String sql1 = "..."; sql.add(sql1);
	 * @param param
	 *            Object[] obj = {"1","2","3"}; Object[typeobj] =
	 *            "string","string","int"; param.add(obj);type.add(typeobj);
	 * @return
	 * @throws SQLException
	 * @author ������
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:2009-3-24
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static int execBatch(List sql, List param, List type)
			throws SQLException, Exception {
		int result = 0;
		List sqls = sql;
		List params = param;
		if (sqls == null || sqls.isEmpty())
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnection();
		if (conn == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		conn.setAutoCommit(false);
		PreparedStatement[] pstmts = new PreparedStatement[sqls.size()];
		int i = 0;
		try {
			for (i = 0; i < sqls.size(); i++) {
				try {
					pstmts[i] = conn.prepareStatement((String) sqls.get(i));
				} catch (SQLException e) {
					result = DB_ERROR_MAKEPSTAT;
					throw new SQLException("" + DB_ERROR_MAKEPSTAT);
				}
				Object[] obj = (Object[]) params.get(i);
				Object[] typeobj = (Object[]) type.get(i);
				if (obj != null && obj.length > 0) {
					for (int j = 0; j < obj.length; j++) {
						if ((typeobj[j]).toString().equalsIgnoreCase("string")) {
							pstmts[i].setObject(j + 1, obj[j]);
						} else if ((typeobj[j]).toString().equalsIgnoreCase(
								"int")) {
							pstmts[i].setInt(j + 1, Integer.parseInt(obj[j]
									.toString().trim()));
						} else if ((typeobj[j]).toString().equalsIgnoreCase(
								"double")) {
							pstmts[i].setDouble(j + 1, Double
									.parseDouble(obj[j].toString().trim()));
						}

					}
				}
				try {

					pstmts[i].executeUpdate();
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			conn.rollback();
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql.get(i) + "|" + param.get(i), e);
			throw e;
		} finally {
			DbConn.close(conn, pstmts);
		}

		return result;
	}

	/**
	 * 
	 * @param sql
	 *            �������
	 * @param params
	 *            ���²���
	 * @return ���½��
	 * @throws SQLException
	 * @author Ԭ��
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:2007-12-19  ����11:08:22
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static int execUpdate(String sql, List params) throws SQLException,
			Exception {
		String mySql = sql;
		if (sql == null)
			return DB_ERROR_INPUTNULL;

		int result = 0;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			result = DB_ERROR_GETCONN;
			logger.error("SQLException:" + sql + "|", e1);
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		PreparedStatement pstmt = null;
		try {
			if (conn == null) {
				result = DB_ERROR_GETCONN;
				throw new SQLException("" + DB_ERROR_GETCONN);
			}
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString());
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			if (conn != null)
				conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return result;
	}
	
	public static int execCardUpdate(String sql, List params) throws SQLException,Exception {
		String mySql = sql;
		if (sql == null)
			return DB_ERROR_INPUTNULL;

		int result = 0;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionCard();
		} catch (SQLException e1) {
			result = DB_ERROR_GETCONN;
            logger.error("SQLException:" + sql + "|", e1);
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		PreparedStatement pstmt = null;
		try {
			if (conn == null) {
				result = DB_ERROR_GETCONN;
				throw new SQLException("" + DB_ERROR_GETCONN);
			}
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString());
			//System.out.println(sb.toString());
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					//System.out.println("param["+i + 1+"]:"+params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			if (conn != null)
				conn.rollback();
            logger.error("SQLException:" + sql + "|", e);
			throw e;
		}catch(Exception e){//���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		}  finally {
			DbConn.close(conn, pstmt);
		}
		return result;
	}
	
	
	public static int execUpdateKeep(String sql, List params) throws SQLException,
	Exception {
		String mySql = sql;
		if (sql == null)
			return DB_ERROR_INPUTNULL;
		
		int result = 0;
		
		Connection conn = null;
		try {
			conn = DbConn.getConnectionKeep();
		} catch (SQLException e1) {
			result = DB_ERROR_GETCONN;
			logger.error("SQLException:" + sql + "|", e1);
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		PreparedStatement pstmt = null;
		try {
			if (conn == null) {
				result = DB_ERROR_GETCONN;
				throw new SQLException("" + DB_ERROR_GETCONN);
			}
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString());
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			if (conn != null)
				conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return result;
		}
	/**
	 * 
	 * @TODO ִ�и������
	 * 
	 * @param sql
	 *            �������
	 * @return ���½��
	 * @throws SQLException
	 * @author Ԭ��
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  ����11:08:57
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static int execUpdate(String sql) throws SQLException, Exception {
		int returnint = DbExec.execUpdate(sql, null);

		return returnint;
	}

	/**
	 * 
	 * @TODO ִ�в�ѯ���
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param params
	 *            ��ѯ�������
	 * @return ���������Ϊkey,��ѯ���Ϊvalue��HashMap��List
	 * @throws SQLException
	 * @author Ԭ��
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:2007-12-19  ����11:09:28
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static Vector execQuery(String sql, List params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param["+(i+1)+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}

					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}
	
	public static Vector execQueryListBak(String sql, List params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionBak();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param["+(i+1)+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}

					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}

	public static Vector execQueryKeep(String sql, List params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionKeep();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param["+(i+1)+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}

					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}

	public static Vector execQueryFk(String sql, List params)
	throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;
		
		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;
		
		Connection conn = null;
		try {
			conn = DbConn.getConnectionFk();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param["+(i+1)+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();
			ResultSetMetaData rsmd = rs.getMetaData();
		
			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}
		
			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);
		
							record.put(columnName[i], new String(c));
		
							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}
		
					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		
		return returnVector;
		}
	
	/**
	 * 
	 * @TODO ִ�в�ѯ���
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param params
	 *            ��ѯ�������
	 * @param flagmode
	 *            �����������ӷ�����ͬ�ı�־
	 * @return ���������Ϊkey,��ѯ���Ϊvalue��HashMap��List
	 * @throws SQLException
	 * @author qiuhg
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  ����11:09:28
	 * LastModified:2010-12-15	qhg ���  
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static Vector execQuery(String sql, List params, String flagmode)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();

			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004
								|| columnType[i] == 2005) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}
					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}

	/**
	 * 
	 * @TODO ִ�в�����������Ĳ�ѯ���
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @return ִ�н�� ���û��Ҫ���ҵ�ֵ�򷵻�null
	 * @throws SQLException
	 * @author Ԭ��
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  ����11:10:09
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static Vector execQuery(String sql) throws SQLException, Exception {
		Vector returnVector = DbExec.execQuery(sql, null);
		return returnVector;
	}
	
	public static Vector execQueryListBak(String sql) throws SQLException, Exception {
		Vector returnVector = DbExec.execQueryListBak(sql, null);
		return returnVector;
	}

	public static Vector execQueryKeep(String sql) throws SQLException,Exception {
		Vector returnVector = DbExec.execQueryKeep(sql, null);
		return returnVector;
	}
	public static Vector execQueryFk(String sql) throws SQLException,Exception {
		Vector returnVector = DbExec.execQueryFk(sql, null);		
		return returnVector;
		}
	/**
	 * 
	 * @TODO ִ�в�ѯ��� ���¼����7������ start=1,recordSize=3 ���ؼ�¼���ĵ�1��2��3������
	 *       start=3,recordSize=3 ���ؼ�¼���ĵ�3��4��5������ start=4,recordSize=3
	 *       ���ؼ�¼���ĵ�4,5,6������ start=7,recordSize=3 ���ؼ�¼���ĵ�7������
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param params
	 *            ��ѯ����
	 * @param start
	 *            �������ʼ�� 1
	 * @param recordSize
	 *            �������С
	 * @return
	 * @throws SQLException
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:Dec 26, 2007  12:54:24 PM
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * 
	 * </pre>
	 */
	public static Vector execQuery(String sql, List params, int start,
			int recordSize) throws SQLException, Exception {

		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param[:"+(i+1)+"]"+params.get(i));
				}
			}
			pstmt.setMaxRows(start + recordSize - 1);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs
											.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else if (columnType[i] == 2005) {
								java.sql.Clob clob = rs.getClob(columnName[i]);
								Reader inStream = clob.getCharacterStream();
								char[] c = new char[(int) clob.length()];
								inStream.read(c);

								record.put(columnName[i], new String(c));

								inStream.close();
							} else {
								record.put(columnName[i].toLowerCase(), rs
										.getString(columnName[i]));
							}

						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);

				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}
	
	public static Vector execQueryListBak(String sql, List params, int start,
			int recordSize) throws SQLException, Exception {

		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionBak();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param[:"+(i+1)+"]"+params.get(i));
				}
			}
			pstmt.setMaxRows(start + recordSize - 1);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs
											.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else if (columnType[i] == 2005) {
								java.sql.Clob clob = rs.getClob(columnName[i]);
								Reader inStream = clob.getCharacterStream();
								char[] c = new char[(int) clob.length()];
								inStream.read(c);

								record.put(columnName[i], new String(c));

								inStream.close();
							} else {
								record.put(columnName[i].toLowerCase(), rs
										.getString(columnName[i]));
							}

						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);

				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}
	
	public static Vector execQueryKeep(String sql, List params, int start,
			int recordSize) throws SQLException, Exception {

		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionKeep();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("testMySql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					// System.out.println("param[:"+(i+1)+"]"+params.get(i));
				}
			}
			pstmt.setMaxRows(start + recordSize - 1);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs
											.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else if (columnType[i] == 2005) {
								java.sql.Clob clob = rs.getClob(columnName[i]);
								Reader inStream = clob.getCharacterStream();
								char[] c = new char[(int) clob.length()];
								inStream.read(c);

								record.put(columnName[i], new String(c));

								inStream.close();
							} else {
								record.put(columnName[i].toLowerCase(), rs
										.getString(columnName[i]));
							}

						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);

				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}

	/**
	 * 
	 * @TODO ��ò�ѯ����� ���¼����7������ start=1,recordSize=3 ���ؼ�¼���ĵ�1��2��3������
	 *       start=3,recordSize=3 ���ؼ�¼���ĵ�3��4��5������ start=4,recordSize=3
	 *       ���ؼ�¼���ĵ�4,5,6������ start=7,recordSize=3 ���ؼ�¼���ĵ�7������
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param start
	 *            ��ʼ����
	 * @param recordSize
	 *            ��¼������
	 * @return
	 * @throws SQLException
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 *  Created on:Dec 26, 2007  1:39:05 PM
	 * LastModified:2010-02-23	qhg ��� ���˴���java�쳣�Լ�SQLException����������쳣
	 * History:2009-8-24	zl
	 * </pre>
	 */
	public static Vector execQuery(String sql, int start, int recordSize)
			throws SQLException, Exception {
		Vector returnVector = DbExec.execQuery(sql, null, start, recordSize);
		return returnVector;
	}
	
	public static Vector execQueryListBak(String sql, int start, int recordSize)
			throws SQLException, Exception {
		Vector returnVector = DbExec.execQueryListBak(sql, null, start, recordSize);
		return returnVector;
	}

	public static Vector execQueryKeep(String sql, int start, int recordSize)
			throws SQLException, Exception {
		Vector returnVector = DbExec
				.execQueryKeep(sql, null, start, recordSize);
		return returnVector;
	}

	public static int findRecord(String sql) throws Exception {
		return findRecord(sql, null);
	}

	public static int findRecord(String sql, List params) throws Exception {
		String mySql = sql;
		if (mySql == null)
			return DbExec.DB_ERROR_INPUTNULL;

		int returnInt = 0;
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return DbExec.DB_ERROR_GETCONN;
		}
		try {
			if (conn == null)
				return DbExec.DB_ERROR_GETCONN;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn
					.prepareStatement(sb.toString(),
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // �޼�¼
				returnInt++;
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			returnInt = DbExec.DB_ERROR_FINDRECORD;
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnInt;
	}

	public static boolean execProcedure(String procName) throws Exception {
		boolean returnBool = false;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:", e1);
			return returnBool;
		}
		try {
			if (conn == null)
				return false;
			CallableStatement cs = conn
					.prepareCall(procName, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			returnBool = cs.execute();
			conn.commit();
		} catch (SQLException e) {
			returnBool = false;
			conn.rollback();
			logger.error("SQLException:", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			returnBool = false;
			conn.rollback();
			logger.error("Exception:", e);
			throw e;
		} finally {
			if (conn != null) {
				if (!conn.getAutoCommit())
					conn.setAutoCommit(true);
				conn.close();
			}
		}

		return returnBool;
	}

	/**
	 * 
	 * @TODO ִ�д���������ĺ���
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @return ���غ���ֵ
	 * @throws SQLException
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-12-01
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static int execFunction(String procName, List params)
			throws Exception {
		int returnValue = 0;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			logger.error("SQLException:", e1);
			return -1;
		}
		try {
			if (conn == null)
				return -1;
			CallableStatement cs = conn
					.prepareCall(procName, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					cs.setObject(i + 2, params.get(i));
				}
			}
			cs.execute();
			returnValue = cs.getInt(1);
		} catch (SQLException e) {
			returnValue = -1;
			logger.error("SQLException:", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			// conn.rollback();
			returnValue = -1;
			logger.error("Exception:", e);
			throw e;
		} finally {// ԭ��δ����������ӹر� ��������� 2010-02-23 qhg
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return returnValue;
	}

	/**
	 * 
	 * @TODO ��ȡ�������
	 * 
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:Apr 22, 2008  8:13:14 PM
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String getChannelId() {
		String returnStr = "";
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			return returnStr;
		}
		try {
			if (conn == null)
				return returnStr;
			StringBuffer sb = new StringBuffer();
			sb
					.append("select output_code_1 from ngp_find_msg where key='NGP_CHANNEL_ID';");
			conn.setAutoCommit(false);
			pstmt = conn
					.prepareStatement(sb.toString(),
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // �м�¼
				returnStr = StringUtils.trimDbData(rs
						.getString("output_code_1"));
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			returnStr = "";
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			returnStr = "";
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			logger.error("Exception:", e);

		} finally {
			try {
				DbConn.close(conn, pstmt);
			} catch (SQLException e) {
			}
		}

		return returnStr;
	}

	public static String getDayOverJsFileTime() {
		String returnStr = "";
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnection();
		} catch (SQLException e1) {
			return returnStr;
		}
		try {
			if (conn == null)
				return returnStr;
			StringBuffer sb = new StringBuffer();
			sb
					.append("select end_time from monend_fact fact,ngp_time ngptime where ngptime.end_date = fact.end_date and step_id='1' and sub_step_id='1020'");
			conn.setAutoCommit(false);
			pstmt = conn
					.prepareStatement(sb.toString(),
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // �м�¼
				returnStr = StringUtils.trimDbData(rs.getString("end_time"));
				if (returnStr.length() == 14)
					returnStr = returnStr.substring(8);
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			returnStr = "";
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			returnStr = "";
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			logger.error("Exception:", e);

		} finally {
			try {
				DbConn.close(conn, pstmt);
			} catch (SQLException e) {
			}
		}

		return returnStr;
	}
	
	

	/**
	 * ִ��˫����Դ���ݴ��������Ŀǰֻ����̻���������˿��ǣ���������
	 * 
	 * @TODO
	 * @return
	 * @throws SQLException
	 * @author YP
	 * @version 1.0
	 */
	public static int execBatchDouble(List sql, List param, List sqlBlob,
			List paramsBlob) throws SQLException, Exception {
		int result = 0;
		List sqls = sql;
		List params = param;
		String parmsFlg = "0";
		if (params != null && params.size() > 0) {
			parmsFlg = "1";
		}
		if (sqls == null || sqls.isEmpty())
			return DB_ERROR_INPUTNULL;

		Connection conn = DbConn.getConnection();
		if (conn == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		Connection connBak = DbConn.getConnectionBak();
		if (connBak == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		conn.setAutoCommit(false);
		connBak.setAutoCommit(false);
		PreparedStatement[] pstmts = new PreparedStatement[sqls.size()];
		PreparedStatement[] pstmtsBak = new PreparedStatement[sqls.size()];

		try {
			for (int i = 0; i < sqls.size(); i++) {
				try {
					pstmts[i] = conn.prepareStatement((String) sqls.get(i));
					pstmtsBak[i] = connBak.prepareStatement((String) sqls
							.get(i));
					// System.out.println("FIS Test Sql---sql["+i+"]:"+
					// sqls.get(i));
				} catch (SQLException e) {
					result = DB_ERROR_MAKEPSTAT;
					throw new SQLException("" + DB_ERROR_MAKEPSTAT);
				}
				if ("1".equals(parmsFlg)) {
					List obj = (List) params.get(i);
					if (obj != null && obj.size() > 0) {
						for (int j = 0; j < obj.size(); j++) {
							pstmts[i].setObject(j + 1, obj.get(j));
							pstmtsBak[i].setObject(j + 1, obj.get(j));
							// System.out.println("FIS Test params---param["+j+"]---:"+obj.get(j));
						}
					}
				}
				try {
					pstmts[i].executeUpdate();
					pstmtsBak[i].executeUpdate();
				} catch (SQLException e) {
					connBak.rollback();
					throw e;
				}
			}
			// �������������֮��ִ��blob�ֶεĸ��¹���
			// execBlob(conn,connBak,sqlBlob,paramsBlob);
			connBak.commit();
			conn.commit();
		} catch (SQLException e) {
			result = -1;
			connBak.rollback();
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			result = -1;
			connBak.rollback();
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(connBak, pstmtsBak);
			DbConn.close(conn, pstmts);
		}
		return result;
	}

	/**
	 * ִ�������뱸��Blob�ֶεĸ���
	 * 
	 * @param conn
	 * @param connBak
	 * @param sqlBlob
	 * @param paramsBlob
	 */
	private static void execBlob(Connection conn, Connection connBak,
			List sqlBlob, List paramsBlob) {
		String sql = "";
		String merchant_id = "";
		InputStream input = null;
		PreparedStatement pstmts = null;
		PreparedStatement pstmtsBak = null;
		ResultSet rs = null;
		ResultSet rsBak = null;
		for (int i = 1; i < paramsBlob.size(); i++) {
			input = (InputStream) paramsBlob.get(i);
			if (input != null) {
				try {
					sql = (String) sqlBlob.get(i - 1);
					merchant_id = (String) paramsBlob.get(0);
					pstmts = conn.prepareStatement(sql);
					pstmtsBak = conn.prepareStatement(sql);
					pstmts.setObject(1, merchant_id);
					pstmtsBak.setObject(1, merchant_id);

					int intBytes = 0;
					byte buffer[] = new byte[8192];
					// ��������blob�ֶ�
					rs = pstmts.getResultSet();
					BLOB blob = (BLOB) rs.getBlob(1);
					OutputStream os = blob.getBinaryOutputStream();
					while ((intBytes = input.read(buffer, 0, 8192)) != -1) {
						os.write(buffer, 0, intBytes);
					}
					os.flush();
					os.close();
					// ���±��ÿ�blob�ֶ�
					rsBak = pstmtsBak.getResultSet();
					BLOB blobBak = (BLOB) rsBak.getBlob(1);
					OutputStream osBak = blobBak.getBinaryOutputStream();
					while ((intBytes = input.read(buffer, 0, 8192)) != -1) {
						osBak.write(buffer, 0, intBytes);
					}
					osBak.flush();
					osBak.close();
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("SQLException: --execBlob--" + sql + "|", e);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IOException: --execBlob--" + sql + "|", e);
				}
			}
		}
	}

	public static Vector execQueryBak(String sql, List<String> params,
			int start, int pageSize) throws SQLException, Exception {

		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionBak();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			pstmt.setMaxRows(start + pageSize - 1);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs
											.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else if (columnType[i] == 2005) {
								java.sql.Clob clob = rs.getClob(columnName[i]);
								Reader inStream = clob.getCharacterStream();
								char[] c = new char[(int) clob.length()];
								inStream.read(c);

								record.put(columnName[i], new String(c));

								inStream.close();
							} else {
								record.put(columnName[i].toLowerCase(), rs
										.getString(columnName[i]));
							}

						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);

				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}

	public static Vector execQueryBak(String sql, List<String> params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionBak();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			// if (!rs.next()) // �޼�¼
			// returnVector = null;
			// else { // �м�¼
			// rs.previous();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else if (columnType[i] == 2005) {
							java.sql.Clob clob = rs.getClob(columnName[i]);
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							inStream.read(c);

							record.put(columnName[i], new String(c));

							inStream.close();
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}

					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}

	/**
	 * ִ��Xƽ̨���ݿ��ѯ����ȡ������BIN list�����´￨BIN list
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector execCardQuery(String sql, List params)
			throws SQLException, Exception {
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionCard();
		} catch (SQLException e1) {
			logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(mySql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // ��¼��
			String[] columnName = new String[numberOfColumns]; // ����
			int[] columnType = new int[numberOfColumns];// �ֶ�����
			for (int i = 0; i < numberOfColumns; i++) {
				columnName[i] = rsmd.getColumnLabel(i + 1);
				columnType[i] = rsmd.getColumnType(i + 1);
			}

			while (rs.next()) {
				HashMap record = new HashMap();
				for (int i = 0; i < numberOfColumns; i++) {
					try {
						if (columnType[i] == -4 || columnType[i] == 2004
								|| columnType[i] == 2005) {
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(rs
										.getBinaryStream(columnName[i]));
								byte bytes[] = new byte[1024];
								int endflag = 0;
								StringBuffer sb1 = new StringBuffer();
								do {
									endflag = in.read(bytes);
									sb1.append((new BASE64Encoder())
											.encode(bytes));
								} while (endflag > 0);
								record.put(columnName[i], sb1.toString());
							} catch (Exception e) {
							} finally {
								if (in != null)
									in.close();
							}
						} else {
							record.put(columnName[i].toLowerCase(), rs
									.getString(columnName[i]));
						}
					} catch (Exception e) {
						record.put(columnName[i], "");
					}
				}
				returnVector.add(record);
			}
			// }
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		} catch (Exception e) {// ���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}
	public static Vector execCardQuery(String sql, int start, int recordSize)
		throws SQLException,Exception {
		Vector returnVector = DbExec.execCardQuery(sql, null, start, recordSize);
		
		return returnVector;
	}
	public static Vector execCardQuery(String sql, List params, int start,
			int recordSize) throws SQLException,Exception {
		
		String mySql = sql;
		if (mySql == null)
			return null;

		Vector resultVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			conn = DbConn.getConnectionCard();
		} catch (SQLException e1) {
            logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			StringBuffer sb = new StringBuffer();
			sb.append(mySql);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//System.out.println(mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					//System.out.println("params["+i+1+"]:"+params.get(i));
				}
			}
			pstmt.setMaxRows(start + recordSize - 1);
			
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) // �޼�¼
				resultVector = null;
			else { // �м�¼
				rs.previous();
				int rsStart = start;

				ResultSetMetaData rsmd = rs.getMetaData();

				int numberOfColumns = rsmd.getColumnCount(); // ��¼��
				String[] columnName = new String[numberOfColumns]; // ����
				int[] columnType = new int[numberOfColumns];// �ֶ�����
				for (int i = 0; i < numberOfColumns; i++) {
					columnName[i] = rsmd.getColumnLabel(i + 1);
					columnType[i] = rsmd.getColumnType(i + 1);
				}
				rs.absolute(rsStart);
				do {
					HashMap record = new HashMap();
					for (int i = 0; i < numberOfColumns; i++) {
						try {
							if (columnType[i] == -4 || columnType[i] == 2004
									|| columnType[i] == 2005) {
								BufferedInputStream in = null;
								try {
									in = new BufferedInputStream(rs.getBinaryStream(columnName[i]));
									byte bytes[] = new byte[1024];
									int endflag = 0;
									StringBuffer sb1 = new StringBuffer();
									do {
										endflag = in.read(bytes);
										sb1.append((new BASE64Encoder())
												.encode(bytes));
									} while (endflag > 0);
									record.put(columnName[i], sb1.toString());
								} catch (Exception e) {
								} finally {
									if (in != null)
										in.close();
								}
							} else {
								record.put(columnName[i].toLowerCase(), rs.getString(columnName[i]));
							}
						} catch (Exception e) {
							record.put(columnName[i], "");
						}
					}
					resultVector.add(record);
				
					
				} while (rs.next());
			}
			rs.close();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
            logger.error("SQLException:" + sql + "|", e);
			throw e;
		} catch(Exception e){//���˴���java�쳣�Լ�SQLException����������쳣 2010-02-23 qhg			
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}
		return resultVector;
	}

}
