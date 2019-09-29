package com.pay.bank.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.bank.bean.TBankInfoBean;
import com.pay.bank.struts.form.TBankInfoFullForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class TBankInfoDao {
	private static final Logger logger = Logger.getLogger(TBankInfoDao.class);
	private int result = 0;	
	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(TBankInfoFullForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  =" select count(1) as numbers from t_bank_info "
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
	public List<TBankInfoBean> getTBankInfoList(PageBean pageBean,TBankInfoFullForm form){
		List<String> param = new ArrayList<String>();
		List<TBankInfoBean> beans = new ArrayList<TBankInfoBean>();
		String sql  =" select bank_code, bank_name, bank_short_name, logo from t_bank_info "
			+" where 1=1 ";
		sql = getSql(form,sql,param) + " order by bank_code, bank_name asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			TBankInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TBankInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTBankInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTBankInfoList", e);
		}
		return beans;
	}

	/**
	 * 根据搜索条件返回指定的list
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<TBankInfoBean> getTBankInfoListByBankName(String bankName){
		List<String> param = new ArrayList<String>();
		List<TBankInfoBean> beans = new ArrayList<TBankInfoBean>();
		String sql  =" select bank_code, bank_name, bank_short_name, logo from t_bank_info "
			+" where 1=1 ";
		
		if(bankName!=null&&!"".equals(bankName.trim())){
			sql =sql +" and bank_name like '%"+bankName.trim()+"%' ";
		}
		
		Vector vector = null;
		try {
			
			vector = DbExec.execQuery(sql, param);
			TBankInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TBankInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTBankInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTBankInfoList", e);
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
	private String getSql(TBankInfoFullForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQueryBankCode()!=null&&form.getQueryBankCode().trim().length()>0){
				sql+=" and bank_code= '"+form.getQueryBankCode().trim()+"' ";
			}
			if(form.getQueryBankName()!=null&&form.getQueryBankName().trim().length()>0){
				sql+=" and bank_name like '%"+form.getQueryBankName().trim()+"%' ";
			}
			if(form.getQueryLogo()!=null&&form.getQueryLogo().trim().length()>0){
				sql+=" and logo= '"+form.getQueryLogo().trim()+"' ";
			}
		}
		return sql;
	}
	
	public TBankInfoBean getTBankInfoByID(String bankCode){		
		String sql  ="select bank_code, bank_name, bank_short_name, logo from t_bank_info "
			+" where 1=1 ";
		if(bankCode!=null&&!"".equals(bankCode)){
			sql=sql+" and bank_code='"+bankCode+"' ";
		}else{
			return null;
		}
		TBankInfoBean TBankInfoBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				TBankInfoBean = new TBankInfoBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return TBankInfoBean;
	}
	
	/**
	 * 添加
	 * @param TBankInfoBean
	 * @return
	 */
	public int addTBankInfo(TBankInfoFullForm TBankInfoFullForm) {
		String sql = "insert into t_bank_info(bank_code, bank_name, bank_short_name, logo) values('"
				+ TBankInfoFullForm.getBank_code()
				+ "','"
				+ TBankInfoFullForm.getBank_name()
				+ "','"
				+ TBankInfoFullForm.getBank_short_name()
				+ "','"
				+ TBankInfoFullForm.getLogo() + "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addTBankInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addTBankInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int updTBankInfo(TBankInfoFullForm TBankInfoFullForm) {
		String sql = " update t_bank_info set bank_name='" + TBankInfoFullForm.getBank_name() + "' ";
		if (TBankInfoFullForm.getBank_short_name() != null
				&& !"".equals(TBankInfoFullForm.getBank_short_name())) {
			sql = sql + ", bank_short_name='" + TBankInfoFullForm.getBank_short_name() + "' ";
		}
		if (TBankInfoFullForm.getLogo() != null
				&& !"".equals(TBankInfoFullForm.getLogo())) {
			sql = sql + ", logo='" + TBankInfoFullForm.getLogo() + "' ";
		}
		sql = sql + "  where bank_code = '" + TBankInfoFullForm.getBank_code()+ "' ";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updTBankInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updTBankInfo", e);
			result = -1;
		}
		return result;
	}

	public int delTBankInfo(String bankCode) {
		String sql = " delete from t_bank_info where 1=1 " ;
		if(bankCode!=null&&!"".equals(bankCode)){
			sql = sql + " and bank_code = '" + bankCode+ "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updTBankInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updTBankInfo", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 *  判断主键是否重复
	 * @param bankCode
	 * @return 0.不重复；1.重复 。
	 */
	public String checkTBankInfoPK(String bankCode){	
		String checkFlag="0";
		String sql  ="select bank_code, bank_name, bank_short_name, logo from t_bank_info "
			+" where bank_code=? ";
		List param = new ArrayList();
		param.add(bankCode);
		try {
			Vector vector = DbExec.execQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				 checkFlag="1";
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return checkFlag;
	}
	
	public List<TBankInfoBean> getTBankInfoList(){
		List<String> param = new ArrayList<String>();
		List<TBankInfoBean> beans = new ArrayList<TBankInfoBean>();
		String sql  =" select bank_code, bank_name, bank_short_name, logo from t_bank_info "
			+" where 1=1 order by bank_code, bank_name asc";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			TBankInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TBankInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTBankInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTBankInfoList", e);
		}
		return beans;
	}
}
