package com.pay.userCrdproduct.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.struts.form.UserCrdproductForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class UserCrdproductDao {
	private static final Logger logger = Logger.getLogger(UserCrdproductDao.class);
	private int result = 0;	
	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(UserCrdproductForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  =" select count(1) as numbers from monuser_crdproduct "
			+" where 1=1 ";
		sql = getSql(form,sql,param);
		try {		
			Vector vector = DbExec.execQuery(sql,param);
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
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<UserCrdproductBean> getUserCrdproductList(PageBean pageBean,UserCrdproductForm form){
		List<String> param = new ArrayList<String>();
		List<UserCrdproductBean> beans = new ArrayList<UserCrdproductBean>();
		String sql  =" select user_code, user_name, crdproduct from monuser_crdproduct where 1=1 ";
		sql = getSql(form,sql,param) + " order by user_code, crdproduct asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			UserCrdproductBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new UserCrdproductBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getUserCrdproductList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getUserCrdproductList", e);
		}
		return beans;
	}

	/**
	 * 根据查询条件返回 相应的查询语句
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(UserCrdproductForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getUser_code()!=null&&form.getUser_code().trim().length()>0){
				sql+=" and user_code= '"+form.getUser_code().trim()+"' ";
			}
			if(form.getUser_name()!=null&&form.getUser_name().trim().length()>0){
				sql+=" and user_name like '%"+form.getUser_name().trim()+"%' ";
			}
		}
		return sql;
	}
	
	public UserCrdproductBean getUserCrdproductByID(String user_code){		
		String sql  ="select user_code, user_name, crdproduct from monuser_crdproduct where 1=1 ";
		if(user_code!=null&&!"".equals(user_code)){
			sql=sql+" and user_code='"+user_code+"' ";
		}else{
			return null;
		}
		UserCrdproductBean UserCrdproductBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				UserCrdproductBean = new UserCrdproductBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return UserCrdproductBean;
	}
	
	/**
	 * 添加
	 * @param UserCrdproductBean
	 * @return
	 */
	public int addUserCrdproduct(UserCrdproductForm UserCrdproductForm) {
		String sql = "insert into monuser_crdproduct(user_code, user_name, crdproduct) "
				+"values('"
				+ UserCrdproductForm.getUser_code()
				+ "','"
				+ UserCrdproductForm.getUser_name()
				+ "','"
				+ UserCrdproductForm.getCrdproduct()
				+ "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addUserCrdproduct", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addUserCrdproduct", e);
			result = -1;
		}
		return result;
	}
	
	public int updUserCrdproduct(UserCrdproductForm UserCrdproductForm) {
		if(UserCrdproductForm!=null&&!"".equals(UserCrdproductForm.getCrdproduct())){
			String sql = " update monuser_crdproduct set crdproduct='" + UserCrdproductForm.getCrdproduct() + "' ";
			sql = sql + "  where user_code = '" + UserCrdproductForm.getUser_code()+ "' ";
			try {
				result = DbExec.execUpdate(sql, null);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQLException: updUserCrdproduct", e);
				result = -1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception: updUserCrdproduct", e);
				result = -1;
			}
			return result;
		}else{
			return -2;
		}
	}

	public int delUserCrdproduct(String user_code) {
		String sql = " delete from monuser_crdproduct where 1=1 " ;
		if(user_code!=null&&!"".equals(user_code)){
			sql = sql + " and user_code = '" + user_code+ "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updUserCrdproduct", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updUserCrdproduct", e);
			result = -1;
		}
		return result;
	}
}
