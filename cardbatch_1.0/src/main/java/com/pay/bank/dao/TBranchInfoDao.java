package com.pay.bank.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.bank.bean.TBranchInfoBean;
import com.pay.bank.struts.form.TBankInfoFullForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class TBranchInfoDao {
	private static final Logger logger = Logger.getLogger(TBranchInfoDao.class);
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
		String sql  =" select count(1) as numbers from t_branch_info "
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
	public List<TBranchInfoBean> getTBranchInfoList(PageBean pageBean,TBankInfoFullForm form){
		List<String> param = new ArrayList<String>();
		List<TBranchInfoBean> beans = new ArrayList<TBranchInfoBean>();
		String sql  =" select branch_code, branch_name, bank_code,bank_code||'#'||branch_code as bankCodeAndBranchCode from t_branch_info "
			+" where 1=1 ";
		sql = getSql(form,sql,param) + " order by bank_code, branch_code asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			TBranchInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TBranchInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTBranchInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTBranchInfoList", e);
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
			if(form.getBank_code()!=null&&form.getBank_code().trim().length()>0){
				sql+=" and bank_code= '"+form.getBank_code().trim()+"' ";
			}
		}
		return sql;
	}
	
	public TBranchInfoBean getTBranchInfoByID(String branchCode){		
		String sql  ="select branch_code, branch_name, bank_code from t_branch_info "
			+" where 1=1 ";
		if(branchCode!=null&&!"".equals(branchCode)){
			sql=sql+" and branch_code='"+branchCode+"' ";
		}
		TBranchInfoBean TBranchInfoBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				TBranchInfoBean = new TBranchInfoBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return TBranchInfoBean;
	}
	
	public List<TBranchInfoBean> getTBranchInfoList(String bankCode){
		List<TBranchInfoBean> beans = new ArrayList<TBranchInfoBean>();
		String sql  =" select branch_code, branch_name, bank_code from t_branch_info "
			+" where 1=1 ";
		if(bankCode!=null&&!"".equals(bankCode)){
			sql=sql+" and bank_code='"+bankCode+"' ";
		}
		Vector vector = null;
		try {
				vector = DbExec.execQuery(sql, null);
			TBranchInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TBranchInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTBranchInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTBranchInfoList", e);
		}
		return beans;
	}

	
	/**
	 * 添加
	 * @param TBranchInfoBean
	 * @return
	 */
	public int addTBranchInfo(TBankInfoFullForm TBankInfoFullForm) {
		String sql = "insert into t_branch_info(branch_code, branch_name, bank_code) values('"
				+ TBankInfoFullForm.getBranch_code()
				+ "','"
				+ TBankInfoFullForm.getBranch_name()
				+ "','"
				+ TBankInfoFullForm.getBank_code() + "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addTBranchInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addTBranchInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int updTBranchInfo(TBankInfoFullForm TBankInfoFullForm) {
		String sql = " update t_branch_info set branch_name='" + TBankInfoFullForm.getBranch_name() + "' ";
		sql = sql + "  where branch_code = '" + TBankInfoFullForm.getBranch_code()+ "' ";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updTBranchInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updTBranchInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int delTBranchInfo(String branchCode,String bankCode) {
		String sql = " delete from t_branch_info where 1=1 " ;		
		if((branchCode==null||"".equals(branchCode))&&(bankCode==null||"".equals(bankCode))){
			return -1;
		}else{
			if(branchCode!=null&&!"".equals(branchCode)){
				sql = sql + " and branch_code = '" + branchCode+ "' ";
			}
			if(bankCode!=null&&!"".equals(bankCode)){
				sql = sql + " and bank_code = '" + bankCode+ "' ";
			}
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
	public String checkTBranchInfoPK(String branchCode){	
		String checkFlag="0";
		String sql  ="select branch_code, branch_name, bank_code from t_branch_info "
			+" where branch_code=? ";
		List param = new ArrayList();
		param.add(branchCode);
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
}
