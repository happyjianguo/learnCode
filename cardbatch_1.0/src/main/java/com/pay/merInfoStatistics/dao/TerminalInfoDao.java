package com.pay.merInfoStatistics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.directwebremoting.util.Logger;

import com.pay.merInfoStatistics.bean.TerminalInfo;
import com.pay.merInfoStatistics.bean.TerminalInfoExcel;
import com.pay.merInfoStatistics.struts.form.TerminalInfoForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class TerminalInfoDao {
	
	private static final Logger logger = Logger.getLogger(TerminalInfoDao.class);
	
	private int result = 0;

	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(TerminalInfoForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  =" select count(t.id) as numbers from mis_terminal t, mis_merchant m where t.merchantid=m.id ";
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
	public List<TerminalInfo> getTerminalInfoList(PageBean pageBean,TerminalInfoForm form){
		List<String> param = new ArrayList<String>();
		List<TerminalInfo> beans = new ArrayList<TerminalInfo>();
		String sql  =" select t.id,t.merchantid||'('||m.name||')' as merchantid,t.model,t.type,t.mobilenumber,t.snnumber,t.installdate,t.name,t.phonenumber,t.status,t.deposite from mis_terminal t, mis_merchant m where t.merchantid=m.id";
		sql = getSql(form,sql,param) + " order by t.id asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			TerminalInfo bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalInfo(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getMerchantInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getMerchantInfoList", e);
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
	private String getSql(TerminalInfoForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQid()!=null&&form.getQid().trim().length()>0){
				sql+=" and t.id= '"+form.getQid().trim()+"' ";
			}
			if(form.getQmerchantid()!=null&&form.getQmerchantid().trim().length()>0){
				sql+=" and t.merchantid= '"+form.getQmerchantid().trim()+"' ";
			}
			if(form.getQmerchantname()!=null&&form.getQmerchantname().trim().length()>0){
				sql+=" and m.name like '%"+form.getQmerchantname()+"%' ";
			}
			if(form.getQmodel()!=null&&form.getQmodel().trim().length()>0){
				sql+=" and t.model= '"+form.getQmodel().trim()+"' ";
			}
			if(form.getQtype()!=null&&form.getQtype().trim().length()>0){
				sql+=" and t.type= '"+form.getQtype().trim()+"' ";
			}
			if(form.getQmobilenumber()!=null&&form.getQmobilenumber().trim().length()>0){
				sql+=" and t.mobilenumber= '"+form.getQmobilenumber().trim()+"' ";
			}
			if(form.getQsnnumber()!=null&&form.getQsnnumber().trim().length()>0){
				sql+=" and t.snnumber= '"+form.getQsnnumber().trim()+"' ";
			}
			if (form.getQinstalldate_startdate()!=null&&form.getQinstalldate_startdate().trim().length()>0) {
				sql+=" and t.installdate >= '"+form.getQinstalldate_startdate().trim()+"' ";
			}
			if (form.getQinstalldate_enddate()!=null&&form.getQinstalldate_enddate().trim().length()>0) {
				sql+=" and t.installdate <= '"+form.getQinstalldate_enddate().trim()+"' ";
			}
			if(form.getQstatus()!=null&&form.getQstatus().trim().length()>0){
				sql+=" and t.status = '"+form.getQstatus().trim()+"' ";
			}
		}
		return sql;
	}
	
	public TerminalInfo getTerminalInfoByID(String id){		
		String sql ="select * from mis_terminal where 1=1 ";
		if(id != null && !"".equals(id)){
			sql=sql + " and id='" + id + "' ";
		}else{
			return null;
		}
		TerminalInfo bean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				bean = new TerminalInfo((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return bean;
	}
	
	/**
	 * 添加
	 * @param TerminalInfo
	 * @return
	 */
	public int addTerminalInfo(TerminalInfoForm form) {
		String sql = "insert into mis_terminal(id,merchantid,address,detailaddress,model,type,mobilenumber,snnumber,installdate,disabledate,updatedate,name,phonenumber,status,deposite) values('"
				+ form.getId()
				+ "','"
				+ form.getMerchantid()
				+ "','"
				+ form.getAddress()
				+ "','"
				+ form.getDetailaddress()
				+ "','"
				+ form.getModel()
				+ "','"
				+ form.getType()
				+ "','"
				+ form.getMobilenumber()
				+ "','"
				+ form.getSnnumber()
				+ "','"
				+ form.getInstalldate()
				+ "','"
				+ form.getDisabledate()
				+ "','"
				+ form.getUpdatedate()
				+ "','"
				+ form.getName()
				+ "','"
				+ form.getPhonenumber()
				+ "','"
				+ form.getStatus()
				+ "','"
				+ form.getDeposite() + "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addTerminalInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addTerminalInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int updTerminalInfo(TerminalInfoForm form) {
		String sql = " update mis_terminal set merchantid='" + form.getMerchantid() + "' ";
		
		if (form.getAddress() != null
				&& !"".equals(form.getAddress())) {
			sql = sql + ", address='" + form.getAddress() + "' ";
		}
		if (form.getDetailaddress() != null
				&& !"".equals(form.getDetailaddress())) {
			sql = sql + ", detailaddress='" + form.getDetailaddress() + "' ";
		}
		if (form.getModel() != null
				&& !"".equals(form.getModel())) {
			sql = sql + ", model='" + form.getModel() + "' ";
		}
		if (form.getType() != null
				&& !"".equals(form.getType())) {
			sql = sql + ", type='" + form.getType() + "' ";
		}
		if (form.getMobilenumber() != null) {
			sql = sql + ", mobilenumber='" + form.getMobilenumber() + "' ";
		}
		if (form.getSnnumber() != null
				&& !"".equals(form.getSnnumber())) {
			sql = sql + ", snnumber='" + form.getSnnumber() + "' ";
		}
		if (form.getInstalldate() != null
				&& !"".equals(form.getInstalldate())) {
			sql = sql + ", installdate='" + form.getInstalldate() + "' ";
		}
		if (form.getDisabledate() != null
				&& !"".equals(form.getDisabledate())) {
			sql = sql + ", disabledate='" + form.getDisabledate() + "' ";
		}
		if (form.getUpdatedate() != null
				&& !"".equals(form.getUpdatedate())) {
			sql = sql + ", updatedate='" + form.getUpdatedate() + "' ";
		}
		if (form.getName() != null
				&& !"".equals(form.getName())) {
			sql = sql + ", name='" + form.getName() + "' ";
		}
		if (form.getPhonenumber() != null
				&& !"".equals(form.getPhonenumber())) {
			sql = sql + ", phonenumber='" + form.getPhonenumber() + "' ";
		}
		if (form.getStatus() != null
				&& !"".equals(form.getStatus())) {
			sql = sql + ", status='" + form.getStatus() + "' ";
		}
		if (form.getDeposite() != null
				&& !"".equals(form.getDeposite())) {
			sql = sql + ", deposite='" + form.getDeposite() + "' ";
		}

		sql = sql + "  where id = '" + form.getId() + "' ";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updTerminalInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updTerminalInfo", e);
			result = -1;
		}
		return result;
	}

	public int delTerminalInfo(String id) {
		String sql = " delete from mis_terminal where 1=1 " ;
		if(id != null && !"".equals(id)){
			sql = sql + " and id = '" + id + "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: delTerminalInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: delTerminalInfo", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 *  判断主键是否重复
	 * @param id
	 * @return 0.不重复；1.重复 。
	 */
	public String checkTerminalInfoPK(String id){	
		String checkFlag="0";
		String sql  ="select id,merchantid,address,detailaddress,model,type,mobilenumber,snnumber,installdate,disabledate,updatedate,name,phonenumber,status,deposite from mis_terminal "
			+" where id=? ";
		List param = new ArrayList();
		param.add(id);
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
	
	public List<TerminalInfoExcel> getTerminalInfoList(TerminalInfoForm form){
		List<String> param = new ArrayList<String>();
		List<TerminalInfoExcel> beans = new ArrayList<TerminalInfoExcel>();
		String sql  =" select t.id,t.merchantid||'('||m.name||')' as merchantid,t.address,t.detailaddress,t.model,t.type,t.mobilenumber,t.snnumber,t.installdate,t.disabledate,t.updatedate,t.name,t.phonenumber,decode(t.status,'0','可用','1','不可用','黑名单') as status,t.deposite from mis_terminal t, mis_merchant m where t.merchantid=m.id";
		sql = getSql(form,sql,param) + " order by t.id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			TerminalInfoExcel bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalInfoExcel(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTerminalInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTerminalInfoList", e);
		}
		return beans;
	}

	public List<TerminalInfo> getTerminalInfoListByMerchantInfoId(String id) {
		List<TerminalInfo> beans = new ArrayList<TerminalInfo>();
		String sql  =" select * from mis_terminal where merchantid='"+id+"' order by id asc";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, null);
			TerminalInfo bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalInfo(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getTerminalInfoListByMerchantInfoId", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getTerminalInfoListByMerchantInfoId", e);
		}
		return beans;
	}

	public int updTerminalInfo(String merchantid,
			String qprovince,String qcity,String qarea,
			String qtype,String area,String fullname,
			String type,String qname,String qfullname,
			String status,String province,String qstatus,
			String city,
			String qid) {
		if (merchantid !=null && !"".equals(merchantid) && qid != null && !"".equals(qid)) {
			String terminalInfoInStr = qid;
			if(terminalInfoInStr.contains(",")){
				terminalInfoInStr = terminalInfoInStr.replaceAll(",", "','");
			}
			
			String sql = " update mis_terminal set merchantid='" + merchantid + "' ";
			if (qprovince != null && !"".equals(qprovince)) {
				sql = sql + ", address='" + qprovince + "' ";
			}
			if (qcity != null && !"".equals(qcity)) {
				sql = sql + ", detailaddress='" + qcity + "' ";
			}
			if (qarea != null && !"".equals(qarea)) {
				sql = sql + ", model='" + qarea + "' ";
			}
			
			if (qtype != null && !"".equals(qtype)) {
				sql = sql + ", type='" + qtype + "' ";
			}
			if (area != null && !"".equals(area)) {
				sql = sql + ", mobilenumber='" + area + "' ";
			}
			if (fullname != null && !"".equals(fullname)) {
				sql = sql + ", snnumber='" + fullname + "' ";
			}
			
			if (type != null && !"".equals(type)) {
				sql = sql + ", installdate='" + type + "' ";
			}
			if (qname != null && !"".equals(qname)) {
				sql = sql + ", disabledate='" + qname + "' ";
			}
			if (qfullname != null && !"".equals(qfullname)) {
				sql = sql + ", updatedate='" + qfullname + "' ";
			}			

			if (status != null && !"".equals(status)) {
				sql = sql + ", name='" + status + "' ";
			}
			if (province != null && !"".equals(province)) {
				sql = sql + ", phonenumber='" + province + "' ";
			}
			if (qstatus != null && !"".equals(qstatus)) {
				sql = sql + ", status='" + qstatus + "' ";
			}			

			if (city != null && !"".equals(city)) {
				sql = sql + ", deposite='" + city + "' ";
			}	
			
			sql = sql + "  where id in ('"+terminalInfoInStr+"') ";
			try {
				result = DbExec.execUpdate(sql, null);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQLException: updTerminalInfo", e);
				result = -1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception: updTerminalInfo", e);
				result = -1;
			}
			return result;
			
		} else {
			return -2;
		}
	}
	
}
