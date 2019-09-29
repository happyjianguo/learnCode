package com.pay.query.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.query.bean.CustBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class CustDao {
	private static final Logger logger = Logger.getLogger(CustDao.class);
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
	public int getCount(CustBean acctBean, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers from CUSTDET t where 1=1 ";
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
	public List<CustBean> getCustList(PageBean pageBean, CustBean form) {
		List<String> param = new ArrayList<String>();
		List<CustBean> beans = new ArrayList<CustBean>();
		String sql = "select t.inst_id, custcode, title, firstname, lastname, TYPEID, addrl0, addrl1, addrl2, addrl3, home_city, home_tel, work_addr1, work_addr2, work_addr3, work_city, work_tel, stmt_code, work_postcode, postcode, po_box, collection_zone, prof_code, married, sex, id_number, to_char(date_accepted,'yyyy-MM-dd') date_accepted, refuse_cheque, num_bounced1, num_bounced2, memo, usrdata1, usrdata2, usrdata3, mailshots, to_char(date_birth,'yyyy-MM-dd') date_birth, prflang, addrind, email, fax, usrdata4, cat_params, ID, latin_title, latin_firstname, latin_lastname, uc_firstname, uc_lastname, national_id, work_county, work_ctry, home_county, home_ctry, mob_tel from CUSTDET t where 1=1 ";
		sql = getSql(form, sql, param);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			CustBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CustBean(map);
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
	private String getSql(CustBean acctBean, String sql, List<String> param) {
		String custcode = acctBean.getCustcode();
		if (null != custcode && custcode.length() > 0) {
			sql += " and t.custcode = ? ";
			param.add(custcode.trim());
		}
		String firstname = acctBean.getFirstname();
		if (null != firstname && firstname.length() > 0) {
			sql += " and t.firstname = ? ";
			param.add(firstname.trim());
		}
		String lastname = acctBean.getLastname();
		if (null != lastname && lastname.length() > 0) {
			sql += " and t.lastname = ? ";
			param.add(lastname.trim());
		}
		String typeid = acctBean.getTypeid();
		if (null != typeid && typeid.length() > 0) {
			sql += " and t.typeid = ? ";
			param.add(typeid.trim());
		}
		String id_number = acctBean.getId_number();
		if (null != id_number && id_number.length() > 0) {
			sql += " and t.id_number = ? ";
			param.add(id_number.trim());
		}
		String date_birth = acctBean.getDate_birth();
		if (null != date_birth && date_birth.length() > 0) {
			sql += " and t.date_birth = ? ";
			param.add(date_birth.trim());
		}
		String id = acctBean.getId();
		if (null != id && id.length() > 0) {
			sql += " and t.id = ? ";
			param.add(id.trim());
		}
		String mob_tel = acctBean.getMob_tel();
		if (null != mob_tel && mob_tel.length() > 0) {
			sql += " and t.mob_tel = ? ";
			param.add(mob_tel.trim());
		}
		return sql;
	}
}
