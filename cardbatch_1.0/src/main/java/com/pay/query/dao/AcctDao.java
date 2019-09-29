package com.pay.query.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.query.bean.AcctBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class AcctDao {
	private static final Logger logger = Logger.getLogger(AcctDao.class);
	private int result = 0;
	private String sql = "";

	/**
	 * 查询分页总条数
	 * 
	 * @param acctBean
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(AcctBean acctBean, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers from accdet t where 1=1 ";
		sql = getSql(acctBean, sql, param);
		try {
			Vector vector = DbExec.execQuery(sql, param);
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
	public List<AcctBean> getAcctList(PageBean pageBean, AcctBean form) {
		List<String> param = new ArrayList<String>();
		List<AcctBean> beans = new ArrayList<AcctBean>();
		String sql = "select t.inst_id,accno,t.currcode,branch_id,t.typecode,AT.Descr typecode_name,t.classid,t.statcode ,s.descr statcode_name,vipflag,blkamt,avlbal,clrbal,unclrbal,credit_limit,custdet_id,t.id from accdet t,ACCTYPE at,ACCSTATUS S  where t.statcode=s.statcode and t.typecode=at.typecode and t.inst_id = at.inst_id  ";
		sql = getSql(form, sql, param);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			AcctBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AcctBean(map);
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
	 * @param acctBean
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(AcctBean acctBean, String sql, List<String> param) {
		String accno = acctBean.getAccno();
		if (null != accno && accno.length() > 0) {
			sql += " and t.accno like ? ";
			param.add(accno.trim());
		}
		String typecode = acctBean.getTypecode();
		if (null != typecode && typecode.length() > 0) {
			sql += " and t.typecode = ? ";
			param.add(typecode.trim());
		}
		String statcode = acctBean.getStatcode();
		if (null != statcode && statcode.length() > 0) {
			sql += " and t.statcode = ? ";
			param.add(statcode.trim());
		}
		String custdet_id = acctBean.getCustdet_id();
		if (null != custdet_id && custdet_id.length() > 0) {
			sql += " and t.custdet_id = ? ";
			param.add(custdet_id.trim());
		}
		String classid = acctBean.getClassid();
		if (null != classid && classid.length() > 0) {
			sql += " and t.classid = ? ";
			param.add(classid.trim());
		}
		String branch_id = acctBean.getBranch_id();
		if (null != branch_id && branch_id.length() > 0) {
			sql += " and t.branch_id = ? ";
			param.add(branch_id.trim());
		}
		String id = acctBean.getId();
		if (null != id && id.length() > 0) {
			sql += " and t.id = ? ";
			param.add(id.trim());
		}
		return sql;
	}
}
