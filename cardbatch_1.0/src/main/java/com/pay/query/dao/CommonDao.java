package com.pay.query.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.query.bean.AcctStatusBean;
import com.pay.query.bean.AcctTypeBean;
import com.pay.query.bean.CardProductBean;
import com.pay.query.bean.CardStatusBean;
import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.dao.UserCrdproductDao;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class CommonDao {
	private static final Logger logger = Logger.getLogger(CommonDao.class);
	/**
	 * 根据搜索条件返回指定的list
	 * 卡产品
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CardProductBean> getCardProductBeanList(PageBean pageBean, CardProductBean form) {
		List<String> param = new ArrayList<String>();
		List<CardProductBean> beans = new ArrayList<CardProductBean>();
		String sql = "select id,inst_id,crdproduct,descr,crdformat_id,def_typeacc,prodnum,prodseq from CRDPRODUCT t where 1=1 ";
		sql = getCommSql(form, sql, param, CardProductBean.class);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			CardProductBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CardProductBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCardProductBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCardProductBeanList", e);
		}
		return beans;
	}
	public List<CardProductBean> getCardProductBeanListBak(PageBean pageBean, CardProductBean form) {
		List<String> param = new ArrayList<String>();
		List<CardProductBean> beans = new ArrayList<CardProductBean>();
		String sql = "select id,inst_id,crdproduct,descr,crdformat_id,def_typeacc,prodnum,prodseq from CRDPRODUCT t where 1=1 ";
		sql = getCommSql(form, sql, param, CardProductBean.class);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQueryListBak(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQueryListBak(sql, param);
			}
			CardProductBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CardProductBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCardProductBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCardProductBeanList", e);
		}
		return beans;
	}
	/**
	 * 根据搜索条件返回指定的list
	 * 卡状态
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CardStatusBean> getCardStatusBeanList(PageBean pageBean, CardStatusBean form) {
		List<String> param = new ArrayList<String>();
		List<CardStatusBean> beans = new ArrayList<CardStatusBean>();
		String sql = "select statcode,sysdef,descr,actioncode,rspcode,canceled from CRDSTATUS t where 1=1 ";
		sql = getCommSql(form, sql, param, CardStatusBean.class);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			CardStatusBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CardStatusBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCardStatusBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCardStatusBeanList", e);
		}
		return beans;
	}
	/**
	 * 根据搜索条件返回指定的list
	 * 帐户状态
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AcctStatusBean> getAcctStatusBeanList(PageBean pageBean, AcctStatusBean form) {
		List<String> param = new ArrayList<String>();
		List<AcctStatusBean> beans = new ArrayList<AcctStatusBean>();
		String sql = "select statcode,descr,sysdef,priority,actioncode,rspcode,crdstatus,suspend from ACCSTATUS t where 1=1 ";
		sql = getCommSql(form, sql, param, AcctStatusBean.class);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			AcctStatusBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AcctStatusBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getAcctStatusBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getAcctStatusBeanList", e);
		}
		return beans;
	}
	/**
	 * 根据搜索条件返回指定的list
	 * 卡状态
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AcctTypeBean> getAcctTypeBeanList(PageBean pageBean, AcctTypeBean form) {
		List<String> param = new ArrayList<String>();
		List<AcctTypeBean> beans = new ArrayList<AcctTypeBean>();
		String sql = "select inst_id,typecode,classid,isocode,currcode,currcode2,descr,acclen,allowsvc from ACCTYPE t where 1=1 ";
		sql = getCommSql(form, sql, param, AcctTypeBean.class);
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			AcctTypeBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AcctTypeBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getAcctTypeBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getAcctTypeBeanList", e);
		}
		return beans;
	}

	/**
	 * 根据查询条件返回 相应的查询语句
	 * 
	 * @param cardBean
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getCommSql(Object bean, String sql, List<String> param, Class cla) {
		try {
			Field[] field = cla.getDeclaredFields();
			for (Field f : field) {
				StringBuffer sb = new StringBuffer();
				sb.append("get");
				sb.append(f.getName().substring(0, 1).toUpperCase());
				sb.append(f.getName().substring(1));
				Method method = cla.getMethod(sb.toString());
				String value = (String) method.invoke(bean);
				if (value != null && !"".equals(value) && !"null".equals(value)) {
					sql += " and t." + f.getName() + " = ? ";
					param.add(value.trim());
				}
			}
		} catch (Exception e) {
			logger.error("CommonDao getCommSql error:", e);
		}
		return sql;
	}
	/**
	 * 获取user对应的卡产品LIST
	 * @param user_code
	 * @return
	 */
	public List<CardProductBean> getUserCrdproductList(String user_code){
		if(user_code==null||"".equals(user_code)){
			return null;
		}else{
			List<CardProductBean> cpList=new ArrayList<CardProductBean>();
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean= userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr=ucBean==null?"":ucBean.getCrdproduct();		
			if(ucBean==null||cpStr==null||"".equals(cpStr)){
				//返回所有的卡产品
				CardProductBean cardProductBean = new CardProductBean();
				cpList=getCardProductBeanList(null,cardProductBean);
			}else{
				//返回user对用的卡产品
				String cpIn=cpStr;
				if(cpStr.contains(",")){
					cpIn=cpStr.replaceAll(",", "','");
				}
				String sql = "select id,inst_id,crdproduct,descr,crdformat_id,def_typeacc,prodnum,prodseq from CRDPRODUCT t where 1=1 ";
				sql=sql+" and crdproduct in ('"+cpIn+"')";
				try {
					Vector vector = DbExec.execQuery(sql, null);
					CardProductBean bean = null;
					if (vector != null && !vector.isEmpty()) {
						int size = vector.size();
						for (int i = 0; i < size; i++) {
							HashMap map = (HashMap) vector.get(i);
							bean = new CardProductBean(map);
							cpList.add(bean);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("SQLException--getUserCrdproductList", e);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception--getUserCrdproductList", e);
				}
			}
			return cpList;
		}
	}
	
	
}
