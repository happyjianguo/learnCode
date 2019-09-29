package com.pay.query.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.query.bean.LsBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class LsDao {
	private static final Logger logger = Logger.getLogger(LsDao.class);
	private int result = 0;
	private String sql = "";

	private String sql()
	{
		return "SELECT '1' sales_type,sa.id,sa.tlog_id,sa.pan,sa.amttxn,sa.father_order,sa.children_order,sa.verifier,sa.verifytime,sa.stxnstat,op.sales_point,op.area,op.batch_stat FROM salestlog sa ,OPENCRDBATCH op WHERE sa.father_order = op.father_order AND sa.children_order = op.children_order "+
		"UNION ALL "+
		"SELECT '2' sales_type,sa.id,sa.tlog_id,sa.pan,sa.amttxn,sa.father_order,sa.children_order,sa.verifier,sa.verifytime,sa.stxnstat,ca.sales_point,ca.area,ca.batch_stat FROM salestlog sa ,cashinbatch ca WHERE sa.father_order = ca.father_order AND sa.children_order = ca.children_order "+
		"UNION ALL "+
		"SELECT '3' sales_type,sa.id,sa.tlog_id,sa.pan,sa.amttxn,sa.father_order,sa.children_order,sa.verifier,sa.verifytime,sa.stxnstat,re.sales_point,re.area,re.batch_stat FROM salestlog sa ,redeembal re WHERE sa.father_order = re.father_order AND sa.children_order = re.children_order ";
	}
	/**
	 * 查询分页总条数
	 * 
	 * @param acctBean
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(LsBean lsBean, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers from ( "+sql()+" ) t where 1=1 ";
		sql = getSql(lsBean, sql, param);
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
	public List<LsBean> getLsList(PageBean pageBean, LsBean form) {
		List<String> param = new ArrayList<String>();
		List<LsBean> beans = new ArrayList<LsBean>();
		String sql = "select * from ( "+sql()+" ) t where 1=1 ";
		sql = getSql(form, sql, param);
		sql=sql+" order by t.verifytime desc ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			LsBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new LsBean(map);
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
	 * @param lsBean
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(LsBean lsBean, String sql, List<String> param) {
		String sales_type = lsBean.getSales_type();
		if (null != sales_type && sales_type.length() > 0) {
			sql += " and t.sales_type = ? ";
			param.add(sales_type.trim());
		}
		String pan = lsBean.getPan();
		if (null != pan && pan.length() > 0) {
			sql += " and t.pan = ? ";
			param.add(pan.trim());
		}
		String father_order = lsBean.getFather_order();
		if (null != father_order && father_order.length() > 0) {
			sql += " and t.father_order = ? ";
			param.add(father_order.trim());
		}
		String children_order = lsBean.getChildren_order();
		if (null != children_order && children_order.length() > 0) {
			sql += " and t.children_order = ? ";
			param.add(children_order.trim());
		}
		String stxnstat = lsBean.getStxnstat();
		if (null != stxnstat && stxnstat.length() > 0) {
			sql += " and t.stxnstat = ? ";
			param.add(stxnstat.trim());
		}
		String batch_stat = lsBean.getBatch_stat();
		if (null != batch_stat && batch_stat.length() > 0) {
			sql += " and t.batch_stat = ? ";
			param.add(batch_stat.trim());
		}
		String id = lsBean.getId();
		if (null != id && id.length() > 0) {
			sql += " and t.id = ? ";
			param.add(id.trim());
		}
		String tlog_id = lsBean.getTlog_id();
		if (null != tlog_id && tlog_id.length() > 0) {
			sql += " and t.tlog_id = ? ";
			param.add(tlog_id.trim());
		}
		String verifytime = lsBean.getVerifytime();
		if (null != verifytime && verifytime.length() > 0) {
			sql += " and substr(t.verifytime,0,8) = ? ";
			param.add(verifytime.trim());
		}
		String verifier = lsBean.getVerifier();
		if (null != verifier && verifier.length() > 0) {
			sql += " and t.verifier like ? ";
			param.add("%"+verifier.trim()+"%");
		}		
		
		return sql;
	}
}
