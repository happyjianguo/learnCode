package com.pay.batch.bflowlog.dao;

import java.util.Date;
import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.pay.batch.bflowlog.bean.CrdEtcPoolBean;
import com.pay.batch.bmanger.bean.BMangerBean;
import com.pay.util.DbConn;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 
 * @TODO 角色类的增、删、改、查功能
 * 
 * @author 黄斌
 * @created on 2007-12-20 11:00:37
 * @version 1.0
 */
public class CrdEtcPoolDao {
	private static final Logger logger = Logger.getLogger(CrdEtcPoolDao.class);
	private String sql = "";
	private int result = 0;
	public static final int DB_ERROR_INPUTNULL = -1000;
	public static final int DB_ERROR_GETCONN = -1001;
	public static final int DB_ERROR_FINDRECORD = -1002;
	public static final int DB_ERROR_MAKEPSTAT = -1003;
	public static final int DB_ERROR_REPEATRECORD = -239;

	public int InsertEtcPanTotal(CrdEtcPoolBean bean, int tempSub,
			int tempSubBak, BigInteger startBak) throws SQLException, Exception {
		final int batchSize = 1000;
		int count = 0, resultConunt = 0;
		StringBuffer sqlBak = new StringBuffer();
		sqlBak.append("insert into crdetcpool(verno_ctx,etc_pan, flag,crdbatch_batch,batchtask_ticket_no,"
				+ "insertdate,inserttime,updatedate,updatetime) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		Connection connection = null;
		try {
			connection = DbConn.getConnectionCard();
		} catch (SQLException e1) {
			result = DB_ERROR_GETCONN;
			logger.error("SQLException:", e1);
			throw new SQLException("" + DB_ERROR_GETCONN);
		}
		if (connection == null) {
			result = DB_ERROR_GETCONN;
			throw new SQLException("" + DB_ERROR_GETCONN);
		}

		// 关闭事务自动提交
		connection.setAutoCommit(false);
		Long startTime = System.currentTimeMillis();
		PreparedStatement ps = connection.prepareStatement(sqlBak.toString());

		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
				"HHmmss");
		String crdetcpanTemp = null;
		for (int i = 0; i < tempSub; i++) {
			bean.setEtc_pan(String.valueOf(startBak));

			// 判断此卡号是否已经存在数据库中。直接拉低数据插入时间。
			crdetcpanTemp = getCrdEtcPanCrdEtcPool(bean.getEtc_pan(),i,tempSub);
			if (null == crdetcpanTemp) {

				bean.setInserttime(sdf1.format(new Date()));
				ps.setString(1, bean.getVerno_ctx());
				ps.setString(2, bean.getEtc_pan());
				ps.setString(3, bean.getFlag());

				ps.setString(4, bean.getCrdbatch_batch());
				ps.setString(5, bean.getBatchtask_ticket_no());
				ps.setString(6, bean.getInsertdate());

				ps.setString(7, bean.getInserttime());
				ps.setString(8, bean.getUpdatedate());
				ps.setString(9, bean.getUpdatetime());
			}else{
				tempSub=-1;
				break;
			}
				ps.addBatch();
				startBak = startBak.add(BigInteger.ONE);
						
				if (++count % batchSize == 0) {
					ps.executeBatch();
					count = 0;
				}
				if (i == tempSubBak) {
					break;
				}
			
		}
		// 执行批量更新
		ps.executeBatch();

		connection.commit();
		ps.close();
		connection.close();
		resultConunt = tempSub;
		return resultConunt;

	}

	// public int InsertEtcPanTotal(CrdEtcPoolBean bean, int tempSub,int
	// tempSubBak,
	// BigInteger startBak) {
	// int total = 0;
	// ArrayList<String> sqlList =new ArrayList<String>();
	// String sqlBak = "";
	// java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
	// "HHmmss");
	//
	// for (int i = 0; i < tempSub; i++) {
	// bean.setEtc_pan(String.valueOf(startBak));
	// bean.setInserttime(sdf1.format(new Date()));
	// sqlBak =
	// "insert into crdetcpool(verno_ctx,etc_pan, flag,crdbatch_batch,batchtask_ticket_no,"
	// + "insertdate,inserttime,updatedate,updatetime) values("
	// + bean.getVerno_ctx()
	// + ",'"
	// + bean.getEtc_pan()
	// + "','"
	// + bean.getFlag()
	// + "',"
	// + bean.getCrdbatch_batch()
	// + ","
	// + bean.getBatchtask_ticket_no()
	// + ",'"
	// + bean.getInsertdate()
	// + "','"
	// + bean.getInserttime()
	// + "','"
	// + bean.getUpdatedate()
	// + "','"
	// + bean.getUpdatetime() + "')";
	// sqlList.add(sqlBak);
	// startBak = startBak.add(BigInteger.ONE);
	// if (i == tempSubBak) {
	// break;
	// }
	// }
	//
	// try {
	// total = execCardUpdateTotal(sqlList, tempSub, bean);
	// } catch (SQLException e) {
	// logger.error(e);
	// } catch (Exception e) {
	// logger.error(e);
	// }
	// return total;
	// }
	//
	// public static int execCardUpdateTotal(List mySql, int tempSub,
	// CrdEtcPoolBean bean) throws SQLException, Exception {
	// if (mySql.size() == 0)
	// return DB_ERROR_INPUTNULL;
	//
	// int result = 0,resultConunt=0;
	//
	// Connection conn = null;
	// try {
	// conn = DbConn.getConnectionCard();
	// } catch (SQLException e1) {
	// result = DB_ERROR_GETCONN;
	// logger.error("SQLException:", e1);
	// throw new SQLException("" + DB_ERROR_GETCONN);
	// }
	// if (conn == null) {
	// result = DB_ERROR_GETCONN;
	// throw new SQLException("" + DB_ERROR_GETCONN);
	// }
	//
	// conn.setAutoCommit(false);
	// PreparedStatement[] pstmts = new PreparedStatement[mySql.size()];
	//
	// try {
	// for(int i=0;i<mySql.size();i++){
	// try {
	// pstmts[i] = conn.prepareStatement((String) mySql.get(i));
	// } catch (SQLException e) {
	// result = DB_ERROR_MAKEPSTAT;
	// throw new SQLException("" + DB_ERROR_MAKEPSTAT);
	// }
	//
	// try {
	// pstmts[i].executeUpdate();
	// pstmts[i].close();
	// } catch (SQLException e) {
	// conn.rollback();
	// throw e;
	// }
	// }
	// conn.commit();
	// resultConunt=mySql.size();
	// } catch (SQLException e) {
	// if (conn != null)
	// conn.rollback();
	// logger.error("SQLException:", e);
	// throw e;
	// } catch (Exception e) {// 过滤处理java异常以及SQLException以外的所有异常 2010-02-23 qhg
	//
	// conn.rollback();
	// logger.error("Exception:", e);
	// throw e;
	// } finally {
	// DbConn.close(conn, pstmts);
	// }
	// return resultConunt;
	// }

	public static int getSelectCount(CrdEtcPoolBean bean) {
		int count = 0;

		String sql = "select count(*) numbers from crdetcpool t where 1=1";

		try {
			Vector vector = null;
			if (bean.getStart_etc_pan() != null
					&& !"".equals(bean.getStart_etc_pan())
					&& !"null".equals(bean.getStart_etc_pan())) {
				sql += " and t.etc_pan >='" + bean.getStart_etc_pan() + "'";
			}
			if (bean.getEnd_etc_pan() != null
					&& !"".equals(bean.getEnd_etc_pan())
					&& !"null".equals(bean.getEnd_etc_pan())) {
				sql += " and t.etc_pan <='" + bean.getEnd_etc_pan() + "'";
			}
			vector = DbExec.execCardQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public int InsertEtcPanOne(CrdEtcPoolBean bean) {
		int total = 0;
		String sql = "insert into crdetcpool(verno_ctx,etc_pan, flag,crdbatch_batch,batchtask_ticket_no,"
				+ "insertdate,inserttime,updatedate,updatetime) values("
				+ bean.getVerno_ctx()
				+ ",'"
				+ bean.getEtc_pan()
				+ "','"
				+ bean.getFlag()
				+ "',"
				+ bean.getCrdbatch_batch()
				+ ","
				+ bean.getBatchtask_ticket_no()
				+ ",'"
				+ bean.getInsertdate()
				+ "','"
				+ bean.getInserttime()
				+ "','"
				+ bean.getUpdatedate()
				+ "','" + bean.getUpdatetime() + "')";
		try {
			total = DbExec.execCardUpdate(sql, null);
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return total;
	}

	public int getCount(String roleno, String dept_no_node, CrdEtcPoolBean bean) {
		int count = 0;

		sql = "select count(*) numbers from crdetcpool t where 1=1";

		try {
			Vector vector = null;
			if (bean.getStart_etc_pan() != null
					&& !"".equals(bean.getStart_etc_pan())
					&& !"null".equals(bean.getStart_etc_pan())) {
				sql += " and t.etc_pan >='" + bean.getStart_etc_pan() + "'";
			}
			if (bean.getEnd_etc_pan() != null
					&& !"".equals(bean.getEnd_etc_pan())
					&& !"null".equals(bean.getEnd_etc_pan())) {
				sql += " and t.etc_pan <='" + bean.getEnd_etc_pan() + "'";
			}
			vector = DbExec.execCardQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public List<CrdEtcPoolBean> queryCrdEtcPoolList(String role_no,
			String dept_no_node, PageBean pageBean, CrdEtcPoolBean bean) {
		List<CrdEtcPoolBean> list = new ArrayList<CrdEtcPoolBean>();
		sql = "select t.verno_ctx,t.id,t.etc_pan,t.flag,t.crdbatch_batch,t.batchtask_ticket_no,"
				+ "t.insertdate,t.inserttime,t.updatedate,t.updatetime"
				+ " from crdetcpool t where 1=1";
		try {
			Vector CrdEtcPoolVector = null;
			if (bean.getStart_etc_pan() != null
					&& !"".equals(bean.getStart_etc_pan())
					&& !"null".equals(bean.getStart_etc_pan())) {
				sql += " and t.etc_pan >='" + bean.getStart_etc_pan() + "'";
			}
			if (bean.getEnd_etc_pan() != null
					&& !"".equals(bean.getEnd_etc_pan())
					&& !"null".equals(bean.getEnd_etc_pan())) {
				sql += " and t.etc_pan <='" + bean.getEnd_etc_pan() + "'";
			}

			sql += " order by id desc";
			CrdEtcPoolVector = DbExec.execCardQuery(sql, pageBean.getStart(),
					pageBean.getPageSize());

			if (CrdEtcPoolVector != null && !CrdEtcPoolVector.isEmpty()) {
				for (int i = 0; i < CrdEtcPoolVector.size(); i++) {
					CrdEtcPoolBean bmangerBean = new CrdEtcPoolBean(
							(HashMap) CrdEtcPoolVector.get(i));
					list.add(bmangerBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public String getCrdEtcPan(String crdetcpan) {
		String crdetcpanResult = null;
		sql = "select etc_pan  from crdetcpool  where 1=1";
		try {
			Vector vector = null;
			if (crdetcpan != null && !"".equals(crdetcpan)
					&& !"null".equals(crdetcpan)) {
				sql += " and etc_pan ='" + crdetcpan + "'";
			}

			vector = DbExec.execCardQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				crdetcpanResult = ((String) map.get("etc_pan")).trim();
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return crdetcpanResult;
	}

	public String getCrdEtcPanCrdEtcPool(String crdetcpan,int ii,int tempSub) {
		String crdetcpanResult = null;
		sql = "select etc_pan  from crdetcpool  where 1=1";
		try {
			Vector vector = null;
			if (crdetcpan != null && !"".equals(crdetcpan)
					&& !"null".equals(crdetcpan)) {
				sql += " and etc_pan ='" + crdetcpan + "'";
			}

			vector = execCardQueryCrdEtcPool(sql, null,ii,tempSub);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				crdetcpanResult = ((String) map.get("etc_pan")).trim();
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return crdetcpanResult;
	}
	/**
	 * 执行X平台数据库查询：获取福卡卡BIN list和万事达卡BIN list
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector execCardQueryCrdEtcPool(String sql, List params,int ii,int tempSub) throws SQLException ,Exception{
		String mySql = sql;
		if (sql == null)
			return null;

		Vector returnVector = new Vector();
		PreparedStatement pstmt = null;

		Connection conn = null;
		try {
			if(ii==0){
				conn = DbConn.getConnectionCard();
			}
			
		} catch (SQLException e1) {
            logger.error("SQLException:" + sql + "|", e1);
			return null;
		}
		try {
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			pstmt = conn
					.prepareStatement(mySql, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			//System.out.println("test sql:"+mySql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
					//System.out.println("test sql param["+i + 1+"]:"+params.get(i));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount(); // 记录数
			String[] columnName = new String[numberOfColumns]; // 列名
			int[] columnType = new int[numberOfColumns];// 字段类型
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
			ii=ii+1;
			if(ii==tempSub){
				
				rs.close();
			}
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
            logger.error("SQLException:" + sql + "|", e);
			e.printStackTrace();
		}catch(Exception e){//过滤处理java异常以及SQLException以外的所有异常 2010-02-23 qhg
			conn.rollback();
			logger.error("Exception:" + sql + "|", e);
			throw e;
		} finally {
			DbConn.close(conn, pstmt);
		}

		return returnVector;
	}
	
	/**
	 * 获取总条数
	 * @return
	 */
	public int getCount(String flag) {
		int count = 0;
		sql = "select count(*) numbers from crdetcpool t where 1=1";
		if(flag!=null&&!"".equals(flag)){
			sql=sql+" and t.flag='"+flag+"'";
		}
		try {
			Vector vector = DbExec.execCardQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	
	
}
