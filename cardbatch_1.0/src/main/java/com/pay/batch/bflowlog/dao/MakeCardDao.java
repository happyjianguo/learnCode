package com.pay.batch.bflowlog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.batch.bflowlog.bean.MakeCardBean;
import com.pay.batch.bflowlog.struts.form.MakeCardForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class MakeCardDao {
	private static final Logger logger = Logger.getLogger(MakeCardDao.class);

	/**
	 * 查询分页总条数
	 * 
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(MakeCardForm form, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(id) as numbers from MAKECARD where 1=1 ";
		sql = getSql(form, sql, param);
		try {
			Vector vector = DbExec.execCardQuery(sql, param);
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
	public List<MakeCardBean> getMakeCardList(PageBean pageBean,
			MakeCardForm form) {
		List<String> param = new ArrayList<String>();
		List<MakeCardBean> beans = new ArrayList<MakeCardBean>();
		String sql = "select verno_ctx, id, txtime, stan, cust_order, pan, telphone, address, name, batch, org_batch, fee, operator, stat, acct_period, reserved1, reserved2, reserved3,to_char(crd_expdate,'yyyyMMdd') as crd_expdate from makecard where 1=1 ";
		sql = getSql(form, sql, param) + " order by txtime desc ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execCardQuery(sql, param, pageBean.getStart(),
						pageBean.getPageSize());
			} else {
				vector = DbExec.execCardQuery(sql, param);
			}
			MakeCardBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MakeCardBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMakeCardList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMakeCardList", e);
		}
		return beans;
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
	public List<MakeCardBean> getMakeCardList(String stat) {
		List<MakeCardBean> beans = new ArrayList<MakeCardBean>();
		String sql = "select distinct BATCH from makecard where stat='" + stat
				+ "'";
		Vector vector = null;
		try {
			vector = DbExec.execCardQuery(sql, null);
			MakeCardBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MakeCardBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMakeCardList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMakeCardList", e);
		}
		return beans;
	}

	/**
	 * 根据查询条件返回 相应的查询语句
	 * 
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(MakeCardForm form, String sql, List<String> param) {
		if (form != null) {
			if (form.getQueryBatch() != null
					&& form.getQueryBatch().trim().length() > 0) {
				sql += " and batch= '" + form.getQueryBatch().trim() + "' ";
			}
			if (form.getQueryCustOrder() != null
					&& form.getQueryCustOrder().trim().length() > 0) {
				sql += " and cust_order= '" + form.getQueryCustOrder().trim()
						+ "' ";
			}
			if (form.getQueryPan() != null
					&& form.getQueryPan().trim().length() > 0) {
				sql += " and pan= '" + form.getQueryPan().trim() + "' ";
			}
			if (form.getQueryStat() != null
					&& form.getQueryStat().trim().length() > 0) {
				sql += " and stat= '" + form.getQueryStat().trim() + "' ";
			}
		}
		return sql;
	}

	public MakeCardBean getMakeCardByID(String id) {
		String sql = "select verno_ctx, id, txtime, stan, cust_order, pan, telphone, address, name, batch, org_batch, fee, operator, stat, acct_period, reserved1, reserved2, reserved3,to_char(crd_expdate,'yyyyMMdd') as crd_expdate from makecard where id='"
				+ id + "'";
		MakeCardBean MakeCardBean = null;
		try {
			Vector vector = DbExec.execCardQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				MakeCardBean = new MakeCardBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}
		return MakeCardBean;
	}

}
