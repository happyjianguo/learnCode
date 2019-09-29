package com.pay.query.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.query.bean.RecErrorBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class RecErrorDao {
	private static final Logger logger = Logger.getLogger(RecErrorDao.class);
	private int result = 0;
	private String sql = "";

	/**
	 * 查询分页总条数
	 * 
	 * @param recErrorBean
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(RecErrorBean recErrorBean, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers from rec_error t where 1=1 ";
		sql = getSql(recErrorBean, sql, param);
		try {
			Vector vector = DbExec.execQueryBak(sql, param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		}
		return count;
	}

	/**
	 * 根据搜索条件返回指定的list
	 * 
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<RecErrorBean> getRecErrorList(PageBean pageBean, RecErrorBean form) {
		List<String> param = new ArrayList<String>();
		List<RecErrorBean> beans = new ArrayList<RecErrorBean>();
		String sql = "select t.* from rec_error t where 1=1 ";
		sql = getSql(form, sql, param);
		sql=sql+" order by t.acct_period desc  ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQueryBak(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQueryBak(sql, param);
			}
			RecErrorBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new RecErrorBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getTerminalList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getTerminalList", e);
		}
		return beans;
	}

	/**
	 * 根据查询条件返回 相应的查询语句
	 * 
	 * @param recErrorBean
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(RecErrorBean recErrorBean, String sql, List<String> param) {
		String table_name = recErrorBean.getTable_name();
		if (null != table_name && table_name.length() > 0) {
			sql += " and t.table_name = ? ";
			param.add(table_name.trim());
		}
		String father_order = recErrorBean.getFather_order();
		if (null != father_order && father_order.length() > 0) {
			sql += " and t.father_order = ? ";
			param.add(father_order.trim());
		}
		String children_order = recErrorBean.getChildren_order();
		if (null != children_order && children_order.length() > 0) {
			sql += " and t.children_order = ? ";
			param.add(children_order.trim());
		}
		String acct_period = recErrorBean.getAcct_period();
		if (null != acct_period && acct_period.length() > 0) {
			sql += " and t.acct_period = ? ";
			param.add(acct_period.trim());
		}
		String errdesc = recErrorBean.getErrdesc();
		if (null != errdesc && errdesc.length() > 0) {
			sql += " and t.errdesc = ? ";
			param.add(errdesc.trim());
		}
		String errcode = recErrorBean.getErrcode();
		if (null != errcode && errcode.length() > 0) {
			sql += " and t.errcode = ? ";
			param.add(errcode.trim());
		}

		return sql;
	}
}
