package com.pay.merchant.dao;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.bean.BranchBean;
import com.pay.merchant.bean.CountryBean;
import com.pay.merchant.bean.CrdroutingBean;
import com.pay.merchant.bean.CurrencyBean;
import com.pay.merchant.bean.FullMerchantBean;
import com.pay.merchant.bean.InstBean;
import com.pay.merchant.bean.MCCBean;
import com.pay.merchant.bean.MerchantBean;
import com.pay.merchant.bean.MerchantExportBean;
import com.pay.merchant.bean.Merchant_xBean;
import com.pay.merchant.bean.MrchAccExportBean;
import com.pay.merchant.bean.Mrch_acc_xBean;
import com.pay.merchant.bean.Mrch_classBean;
import com.pay.merchant.bean.Mrchaccbean;
import com.pay.merchant.bean.Mrchmtdbean;
import com.pay.merchant.bean.OfficialBean;
import com.pay.merchant.bean.TaxBean;
import com.pay.merchant.bean.WankeMerBookBean;
import com.pay.merchant.struts.form.MerchantForm;
import com.pay.merchant.struts.form.Mrch_acc_xForm;
import com.pay.terminal.dao.TerminalDao;
import com.pay.util.DbExec;
import com.pay.util.PageBean;
import com.pay.util.StringUtils;

public class Merchantdao {
	private static final Logger logger = Logger.getLogger(Merchantdao.class);
	private String sql = "";
	private int result = 0;

	/**
	 * 查询分页总条数
	 * 
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(MerchantForm form, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = " select count(*) numbers  from merchant t1, merchant_x t4 left join sys_parameter a on t4.type_yf = a.param_value  and a.param_type = 'CARDBATCH_MER_TYPE'"
				+ " where t1.id = t4.merchant_id ";
		sql = getSql(form, sql, param);
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
	
	public int getCount(Mrch_acc_xForm form, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "SELECT count(T1.id) as numbers FROM MRCH_ACC_X T1, MERCHANT A WHERE T1.MERCHANT_ID = A.ID ";
		
		sql = getSql(form, sql, param);
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
	public List<FullMerchantBean> getMerchantList(PageBean pageBean,
			MerchantForm form) {
		List<String> param = new ArrayList<String>();
		List<FullMerchantBean> beans = new ArrayList<FullMerchantBean>();
		String sql = " select t1.id,t1.mrchno,t4.mrcht_name,t4.type_yf,a.param_name as type_yf_desc,t1.mrchstat,t1.acptbus,t1.contact3,"
			+" t1.telno1,t4.add_date,t4.address,t4.province,t4.city_no,t4.zone,t4.merchant_org,t4.merchant_fenrunorg "
			+"  from merchant t1, merchant_x t4 left join sys_parameter a on t4.type_yf = a.param_value  and a.param_type = 'CARDBATCH_MER_TYPE' "
			+" where t1.id = t4.merchant_id";
		sql = getSql(form, sql, param);
		sql += "  ORDER BY t4.add_date desc ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(),
						pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			FullMerchantBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new FullMerchantBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantList", e);
		}
		return beans;
	}
	/**
	 * 导出EXCEL LIST
	 * @param form
	 * @return
	 */
	public List<MerchantExportBean> getMerchantList(MerchantForm form) {
		List<String> param = new ArrayList<String>();
		List<MerchantExportBean> beans = new ArrayList<MerchantExportBean>();
		String sql = "select t1.mrchno,t4.mrcht_name,t4.mrch_snippet,t4.type_yf,DECODE(t1.mrchstat,'0','可用','1','不可用','黑名单') as mrchstat,t1.acptbus || '(' || t8.descr || ')' as acptbus,t1.contact3,t1.telno1,t4.add_date,to_char(t4.DISABLED_DATE,'yyyy-MM-dd') as DISABLED_DATE,to_char(t4.ENABLE_DATE,'yyyy-MM-dd') as ENABLE_DATE "
			+" FROM MERCHANT T1, MRCHMTD T2, MRCHACC T3, MERCHANT_X T4,INST T5,OFFICIAL T6,branch T7,numdescr T8"
			+"  WHERE T1.ID = T2.MERCHANT_ID   AND T1.ID = T3.MERCHANT_ID   AND T1.ID = T4.MERCHANT_ID   AND T1.INST_ID=T5.ID   AND T1.INST_ID=T6.INST_ID   AND T1.OFFICIAL=T6.OFFICIAL   AND T1.brncode=T7.brncode ";
		sql +=" AND t1.ACPTBUS=t8.id  and T8.descrtype='ab' and (( T8.lang = 'ZH' ))";
		sql = getSql(form, sql, param);
		sql += "  ORDER BY t4.add_date desc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			MerchantExportBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantExportBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantList", e);
		}
		return beans;
	}

	public List<MerchantExportBean> getExportMerchantList(MerchantForm form) {

		List<String> param = new ArrayList<String>();
		List<MerchantExportBean> beans = new ArrayList<MerchantExportBean>();
		String sql = "select t1.mrchno mrchno,t4.mrcht_name name,t4.type_yf type_yf,DECODE(t1.mrchstat,'0','可用','1','不可用','黑名单') as mrchstat,t4.mrch_snippet mrch_snippet,t1.acptbus || '(' || t8.descr || ')' as acptbus,t1.contact3 contact3,t1.telno1 telno1,t4.add_date add_date,to_char(t4.DISABLED_DATE,'yyyy-MM-dd') as disabled_date,to_char(t4.ENABLE_DATE,'yyyy-MM-dd') as enable_date "
			+" FROM MERCHANT T1, MRCHMTD T2, MRCHACC T3, MERCHANT_X T4,INST T5,OFFICIAL T6,branch T7,numdescr T8"
			+"  WHERE T1.ID = T2.MERCHANT_ID   AND T1.ID = T3.MERCHANT_ID   AND T1.ID = T4.MERCHANT_ID   AND T1.INST_ID=T5.ID   AND T1.INST_ID=T6.INST_ID   AND T1.OFFICIAL=T6.OFFICIAL   AND T1.brncode=T7.brncode ";
		sql +=" AND t1.ACPTBUS=t8.id  and T8.descrtype='ab' and (( T8.lang = 'ZH' ))";
		sql = getSql(form, sql, param);
		sql += "  ORDER BY t4.add_date desc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			MerchantExportBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantExportBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantList", e);
		}
		return beans;
	
	}
	
	public List<Mrch_acc_xBean> getMerchantList(PageBean pageBean,
			Mrch_acc_xForm form) {
		List<String> param = new ArrayList<String>();
		List<Mrch_acc_xBean> beans = new ArrayList<Mrch_acc_xBean>();
		String sql = "SELECT t1.ID, t1.inst_id, t1.mrchno||'('||a.name||')' as mrchno, t1.accno, t1.acc_name, t1.bank_no, t1.bank_name, t1.acc_add_date, t1.acc_nick_name, t1.short_nick_name, t1.individual, t1.last_settle_date, t1.merchant_id, t1.acc_introd,t1.merchant_x_acc_type1 FROM mrch_acc_x T1, MERCHANT A WHERE T1.MERCHANT_ID = A.ID ";
		sql = getSql(form, sql, param);
		sql = sql+" order by T1.last_settle_date desc ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(),
						pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			Mrch_acc_xBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new Mrch_acc_xBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantList", e);
		}
		return beans;
	}

	public List<MrchAccExportBean> getMerchantList(Mrch_acc_xForm form) {
		List<String> param = new ArrayList<String>();
		List<MrchAccExportBean> beans = new ArrayList<MrchAccExportBean>();
		String sql = "select t1.acc_name,t1.accno,t1.mrchno || '(' || a.name || ')' as mrchno,t1.acc_nick_name,t1.short_nick_name, t1.bank_no,"
			+"t1.bank_name, to_char(t1.acc_add_date,'yyyy-MM-dd') as acc_add_date,decode(t1.individual,'1','是','否') as individual,"
			+"to_char(t1.last_settle_date,'yyyy-MM-dd') as last_settle_date,decode(t1.merchant_x_acc_type1,'12','银行账户','支付账户') as merchant_x_acc_type1,"
			+ "decode(t1.pay_account_type,'00','对公账户','对私账户') as pay_account_type,t1.acc_x_province,t1.acc_x_city_no,t1.acc_x_text"
			+" from mrch_acc_x t1, merchant a WHERE T1.MERCHANT_ID = A.ID ";
		sql = getSql(form, sql, param);
		sql = sql+" order by T1.last_settle_date desc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			MrchAccExportBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MrchAccExportBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantList", e);
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
	private String getSql(MerchantForm form, String sql, List<String> param) {

		String mrchno = form.getMrchno();
		if (null != mrchno && mrchno.length() > 0) {	
			List<WankeMerBookBean> wankeList = findWanKe(mrchno);
			if(wankeList!=null){
				//原先商户号存在万科商户表
				if(wankeList.size()>0){
					sql += " and t1.mrchno in (select distinct t0.mer_no  from t_wanke_mer_book t0 where t0.mer_no='"+mrchno.trim()+"') ";
					}else{
						//原先商户号不存在万科商户表
						sql += " and t1.mrchno= '"+mrchno.trim()+"' ";
					}
			}else{	
			//原先商户号不存在万科商户表
				sql += " and t1.mrchno= '"+mrchno.trim()+"' ";
			}
		}
		if (null != form.getType_yf() && !"".equals(form.getType_yf())&&form.getType_yf().trim().length()>0) {
			sql += " and t4.type_yf= '"+form.getType_yf().trim()+"' ";
		}
		if (null != form.getMrchstat() && !"".equals(form.getMrchstat())&&form.getMrchstat().trim().length()>0) {
			sql += " and T1.MRCHSTAT = '"+form.getMrchstat().trim()+"' ";
		}
		if (null != form.getAddDT_startdate() && !"".equals(form.getAddDT_startdate())&&form.getAddDT_startdate().trim().length()>0) {
			sql += " and T4.ADD_DATE>= to_number('"+form.getAddDT_startdate().trim()+"') ";
		}
		if (null != form.getAddDT_enddate() && !"".equals(form.getAddDT_enddate())&&form.getAddDT_enddate().trim().length()>0) {
			sql += " and T4.ADD_DATE<= to_number('"+form.getAddDT_enddate().trim()+"') ";
		}
		if (null != form.getContact3() && !"".equals(form.getContact3())&&form.getContact3().trim().length()>0) {
			sql += " and T4.CONTACT3 LIKE '%"+form.getContact3().trim()+"%'";
		}
		if (null != form.getTelno1() && !"".equals(form.getTelno1())&&form.getTelno1().trim().length()>0) {
			sql += " and T4.TELNO1 = '"+form.getTelno1().trim()+"' ";
		}
		if (null != form.getAddress() && !"".equals(form.getAddress())&&form.getAddress().trim().length()>0) {
			sql += " and T4.ADDRESS LIKE '%"+form.getAddress().trim()+"%' ";
		}
		if (null != form.getMrcht_name() && !"".equals(form.getMrcht_name())&&form.getMrcht_name().trim().length()>0) {
			sql += " and T4.MRCHT_NAME LIKE '%"+form.getMrcht_name().trim()+"%' ";
		}
		if (null != form.getProvince() && !"".equals(form.getProvince())&&form.getProvince().trim().length()>0) {
			sql += " and T4.PROVINCE = '"+form.getProvince().trim()+"' ";
		}
		if (null != form.getCity_no() && !"".equals(form.getCity_no())&&form.getCity_no().trim().length()>0) {
			sql += " and T4.CITY_NO = '"+form.getCity_no().trim()+"' ";
		}
		if (null != form.getZone() && !"".equals(form.getZone())&&form.getZone().trim().length()>0) {
			sql += " and T4.ZONE = '"+form.getZone().trim()+"' ";
		}
		if (null != form.getName() && !"".equals(form.getName())&&form.getName().trim().length()>0) {
			sql += " and t1.name like '%"+form.getName().trim()+"%' ";
		}
		if (null != form.getType_yf() && !"".equals(form.getType_yf())&&form.getType_yf().trim().length()>0) {
			sql += " and T4.type_yf = '"+form.getType_yf().trim()+"' ";
		}		
		if (null != form.getYard_mer_type()&& !"".equals(form.getYard_mer_type())&&form.getYard_mer_type().trim().length()>0) {
			sql += " and t1.mrchno in (select distinct t0.mer_no  from t_wanke_mer_book t0 where t0.mer_type='"+form.getYard_mer_type().trim()+"')";
		}
		return sql;
	}
	
	private String getSql(Mrch_acc_xForm form, String sql, List<String> param) {

		String mrchno = form.getMrchnoQ();
		if (null != mrchno && mrchno.trim().length() > 0) {
			sql += " and t1.mrchno= '"+mrchno.trim()+"' ";
		}
		String mrchNameQ = form.getMrchNameQ();
		if (null != mrchNameQ && mrchNameQ.trim().length() > 0) {
			sql += " and a.NAME like '%"+mrchNameQ.trim()+"%' ";
		}
		String accno = form.getAccno();
		if (null != accno && accno.trim().length() > 0) {
			sql += " and t1.ACCNO like '%"+accno.trim()+"%' ";
		}
		String bank_name = form.getBank_name();
		if (null != bank_name && bank_name.trim().length() > 0) {
			sql += " and t1.BANK_NAME like '%"+bank_name.trim()+"%' ";
		}
		
		return sql;
	}
    /**
     * 输入商户号获取父商户，不输入商户号获取所有商户
     * @param mrchno 
     * @return
     */
	public List<MerchantBean> getMerchantBeanList(String mrchno,String inst_id,String mustActive) {
		List<MerchantBean> mrchList = new ArrayList<MerchantBean>();

		String sql = "select id,mrchno||'('||name||')' as name,mrchno from merchant where 1=1 ";
		if(mrchno!=null&&!"".equals(mrchno)){
			sql =sql +" and mrchno=(SELECT a.fmrchno FROM MRCH_CLASS a WHERE a.mrchno='"+mrchno+"') ";
		}
		if(inst_id!=null&&!"".equals(inst_id)){
			sql =sql +" and inst_id='"+inst_id+"'  ";
		}		
		if(mustActive!=null&&"yes".equals(mustActive)){
			sql =sql +" and mrchstat=0 ";
		}
		sql =sql +" order by mrchno";
		try {
			Vector vector = DbExec.execQuery(sql);
			MerchantBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantBean(map);
					mrchList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantBeanList", e);
		}
		return mrchList;
	}
	/**
	 * 通过卡BIN获取卡BIN描述
	 * @param iid
	 * @return
	 */
	public List<CrdroutingBean> getCrdroutingByIid(String iid) {
		List<CrdroutingBean> mrchList = new ArrayList<CrdroutingBean>();
		String sql = "select verno_ctx,iid,descr from crdrouting where 1=1 ";
		if(iid!=null&&!"".equals(iid)&&iid.trim().length()>0){
			sql =sql +"and iid="+iid.trim();
			try {
				Vector vector = DbExec.execQuery(sql);
				CrdroutingBean bean = null;
				if (vector != null && !vector.isEmpty()) {
					int size = vector.size();
					for (int i = 0; i < size; i++) {
						HashMap map = (HashMap) vector.get(i);
						bean = new CrdroutingBean(map);
						mrchList.add(bean);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQLException--getMerchantBeanList", e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception--getMerchantBeanList", e);
			}
		}
		return mrchList;
	}

    /**
     * 输入商户号mrchno获取商户或输入商户名称模糊查询商户
     * @param mrchno 
     * @return
     */
	public List<MerchantBean> getMerchantBeanListByMrchNoOrName(String mrchno,String mrchName,String flg,String mrchNoOrName,String mustActive,String merNoJudgeIsFmerNoDecide) {
		List<MerchantBean> mrchList = new ArrayList<MerchantBean>();
		//获取商户状态为可用的商户
		String sql = "select id,mrchno||'('||name||')' as name,mrchno,inst_id from merchant where 1=1 ";
		if(mustActive!=null&&"yes".equals(mustActive)){
			sql =sql +" and mrchstat=0 ";
		}
		//获取不同结算类型的商户
		//结算商户---0            交易商户---1    正常商户---2
        if(flg!=null&&flg.trim().length()>0){
        	sql =sql +" and mrchno IN (SELECT a.mrchno FROM MRCH_CLASS a WHERE a.classify<>'"+flg.trim()+"') ";
        }
		if(mrchno!=null&&!"".equals(mrchno)&&mrchno.trim().length()>0){
			sql =sql +" and lower(mrchno)='"+mrchno.toLowerCase()+"' ";
		}
		if(mrchName!=null&&!"".equals(mrchName)&&mrchName.trim().length()>0){
			sql =sql +" and lower(name) like '%"+mrchName.toLowerCase()+"%' ";
		}
		if(mrchNoOrName!=null&&mrchNoOrName.trim().length()>0){
			sql =sql +" and (lower(name) like '%"+ mrchNoOrName.toLowerCase()+"%' or lower(mrchno) like '%"+mrchNoOrName.toLowerCase()+"%') ";
		}
		//根据商户编号判断该商户是否按照父商户结算，如果是：返回父商户编号，否则返回空字符。
		if(merNoJudgeIsFmerNoDecide!=null&&merNoJudgeIsFmerNoDecide.trim().length()>0){
			String fmerNo=this.getFmerNoByMrchNo(merNoJudgeIsFmerNoDecide);
			String isFmerFlag="0";
			if(!"".equals(fmerNo)&&fmerNo.contains("#")){
				isFmerFlag=fmerNo.split("#")[0];
				fmerNo=fmerNo.split("#")[1];
				if("0".equals(isFmerFlag)){
		        	sql =sql +" and mrchno IN ('"+merNoJudgeIsFmerNoDecide+"') ";
				}else{
		        	sql =sql +" and mrchno IN ('"+fmerNo+"') ";					
				}
			}

		}
		sql =sql +" order by name desc ";
		try {
			Vector vector = DbExec.execQuery(sql);
			MerchantBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantBean(map);
					mrchList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantBeanList", e);
		}
		return mrchList;
	}
	
	
	
	/**
	 * 查询商户所属机构信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<InstBean> getInstBeanList() {
		List<InstBean> instList = new ArrayList<InstBean>();
		String sql = "select * from inst order by id";
		try {
			Vector vector = DbExec.execQuery(sql);
			InstBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new InstBean(map);
					instList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getBranchBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getBranchBeanList", e);
		}
		return instList;
	}

	/**
	 * 查询MCC信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<MCCBean> getMCCList() {
		List<MCCBean> MCCList = new ArrayList<MCCBean>();
		String sql = "select id,descr from numdescr where descrtype='ab' and (( lang = 'ZH' )) order by id";
		try {
			Vector vector = DbExec.execQuery(sql);
			MCCBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MCCBean(map);
					MCCList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMCCList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMCCList", e);
		}
		return MCCList;
	}
	
	/**
	 * 获取MCC  MAP
	 * @return
	 */
	public Map<String,String> getMccMap(){
		Map<String, String> listMap = new HashMap<String, String>();
		List<MCCBean> list= getMCCList();
		for(MCCBean mcc:list){
			listMap.put(mcc.getId(), mcc.getDescr());
		}
		return listMap;
	}

	/**
	 * 查询国家代码
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CountryBean> getCountryList() {
		List<CountryBean> CountryList = new ArrayList<CountryBean>();
		String sql = "select A.ISOCODE as id, b.descr as descr from country a, descr b where A.DESCRTAG=B.DESCRTAG and B.LANG='ZH' ORDER BY nlssort(descr, 'NLS_SORT=SCHINESE_PINYIN_M')";
		try {
			Vector vector = DbExec.execQuery(sql);
			CountryBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CountryBean(map);
					CountryList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCountryList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCountryList", e);
		}
		return CountryList;
	}

	/**
	 * 根据机构号查询分支机构编号信息
	 * 
	 * @param inst_id
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<BranchBean> getBranchBeanList(String inst_id) {
		List<BranchBean> branchList = new ArrayList<BranchBean>();
		sql = "select * from branch where inst_code = ? ";
		List params = new ArrayList();
		params.add(inst_id);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			BranchBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new BranchBean(map);
					branchList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getBranchBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getBranchBeanList", e);
		}
		return branchList;
	}

	/**
	 * 查询税务表信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<TaxBean> getTaxcodeJs(String currcode) {
		List<TaxBean> taxList = new ArrayList<TaxBean>();
		sql = "select taxcode,descr from tax where currcode = ? order by taxcode";
		List params = new ArrayList();
		params.add(currcode);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			TaxBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new TaxBean(map);
					taxList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getTaxcodeJs", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getTaxcodeJs", e);
		}
		return taxList;
	}

	/**
	 * 查询official表中合同签署信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<OfficialBean> getOfficialBeanList() {
		List<OfficialBean> officialList = new ArrayList<OfficialBean>();
		sql = "select official,descr from official ";
		try {
			Vector vector = DbExec.execQuery(sql);
			OfficialBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new OfficialBean(map);
					officialList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getOfficialList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getOfficialList", e);
		}
		return officialList;
	}

	/**
	 * 根据上级城市id号获取下级地区
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getCityByFid(String fid) {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		sql = "select id as aid,province_city from area where fid = ? and isuse = '1' order by aid";
		List params = new ArrayList();
		params.add(fid);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}
	
	/**
	 * 根据上级城市id号获取下级地区
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getAreaXCityByFid(String fid) {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		sql = "select id as aid,province_city from area_x where fid = ? and isuse = '1' order by aid";
		List params = new ArrayList();
		params.add(fid);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}

	/**
	 * 根据depth get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getCityByDepth(String depth) {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		sql = "select id as aid,province_city from area where depth = ? and isuse = '1' order by aid";
		List params = new ArrayList();
		params.add(depth);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}

	/**
	 * 根据id get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getCityName(String cityid) {
		// isuse为目前的可用标识
		sql = "SELECT t.id as aid,t.province_city as province_city FROM area t WHERE t.id= ? AND t.isuse='1' ";
		List params = new ArrayList();
		params.add(cityid);
		AreaBean bean = null;
		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return bean.getProvince_city();
	}
	/**
	 * 根据id get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAreaXCityName(String cityid) {
		// isuse为目前的可用标识
		sql = "SELECT t.id as aid,t.province_city as province_city FROM area_x t WHERE t.id= ? AND t.isuse='1' ";
		List params = new ArrayList();
		params.add(cityid);
		AreaBean bean = null;
		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return bean.getProvince_city();
	}
	
	/**
	 * 根据depth get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getBJCity() {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		sql = "SELECT t.id,t.province_city FROM area t WHERE trim(t.province_city)='北京市'  AND t.isuse='1' ";
		List params = new ArrayList();
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}
	/**
	 * 根据depth get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getAreaXBJCity() {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		sql = "SELECT t.id,t.province_city FROM area_x t WHERE trim(t.province_city)='北京市'  AND t.isuse='1' ";
		List params = new ArrayList();
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}
	
	/**
	 * 查询official表中合同签署信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<OfficialBean> getOfficialJs(String inst_id) {
		List<OfficialBean> officialList = new ArrayList<OfficialBean>();
		sql = "select official,descr from official where inst_id = ? ";
		List params = new ArrayList();
		params.add(inst_id);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			OfficialBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new OfficialBean(map);
					officialList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getOfficialJs", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getOfficialJs", e);
		}
		return officialList;
	}

	
	/**
	 * 查询currency表 获取currencode信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CurrencyBean> getCurrencyBeanList() {
		List<CurrencyBean> currencyList = new ArrayList<CurrencyBean>();
		// String sql =
		// "select currcode,alphacode from currency t order by currcode";
		String sql = "select t.currcode,t.alphacode||d.descr as descr from currency t, descr d where t.descrtag = d.descrtag and d.lang='ZH' order by descr";
		try {
			Vector vector = DbExec.execQuery(sql);
			CurrencyBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CurrencyBean(map);
					currencyList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCurrencyBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCurrencyBeanList", e);
		}
		return currencyList;
	}

	/**
	 * 根据商户所属机构号获取分支机构编号
	 * 
	 * @param inst_id
	 * @return
	 */
	public List<BranchBean> getBrncodeJs(String inst_id) {
		sql = "select * from branch where inst_id = ? ";
		List params = new ArrayList();
		params.add(inst_id);
		List list = new ArrayList();
		try {
			Vector roleVector = DbExec.execQuery(sql, params);
			if (roleVector != null && !roleVector.isEmpty()) {
				for (int i = 0; i < roleVector.size(); i++) {
					BranchBean branchBean = new BranchBean(
							(HashMap) roleVector.get(i));
					list.add(branchBean);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException--getBrncodeJs", e);
		} catch (Exception e) {
			logger.error("Exception--getBrncodeJs", e);
		}
		return list;
	}

	/**
	 * 获取待修改的商户信息
	 * 
	 * @param id
	 * @return
	 */
	public FullMerchantBean getMerchantById(String id) {
		sql = "select b.id,b.inst_id,a.mrchno,a.mrcht_name,a.address,a.add_date,a.city_no,a.province,a.zone,a.state,a.type_yf,a.agent,"
			+"a.id_type,a.id_no,a.id_validity,a.bus_lic_no,a.bus_lic_validity,a.tax_id,a.tax_id_validity,a.org_id,a.org_validity,"
			+"a.telno1,a.contact3,a.accno,a.accno as mx_accno,a.merchant_id,a.bus_lic_pic,a.tax_id_pic,a.org_id_pic,a.legal_rep,"
			+"a.lr_id_type,a.lr_id_no,a.lr_id_validity,a.fs_cycle,a.fs_cycle_param,a.last_settle_date,a.is_consump_category,"
			+"a.amt_consump_category,a.is_fmrchno_decide,a.fs_kp_cycle,a.fs_kp_cycle_param,a.last_kp_date,a.merge_money_flag,a.mrch_snippet,a.is_bj,a.is_card_type_group,a.is_kp,"
			+"a.MERCHANT_ORG,b.acptbus,b.mrchstat,c.classify,a.DISABLED_DATE,a.ENABLE_DATE,a.man_name,a.id_type1,a.id_no1,a.id_deadline1,a.acc_x_name,a.settlement_person,a.merchant_email,"
			+"a.is_send_billdetail,a.merchant_x_operate,a.merchant_x_type,a.merchant_x_reg_amt,a.merchant_x_code,a.merchant_fenrunorg,a.combine_flag"
			+" from merchant_x a, merchant b, mrch_class c where a.merchant_id = b.id and a.mrchno = c.mrchno  and b.id='"
			+id+"'";
		FullMerchantBean fullMerchant = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				fullMerchant = new FullMerchantBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return fullMerchant;
	}

	/**
	 * 获取待修改的万科商户信息
	 * 
	 * @param id
	 * @return
	 */
	public List<WankeMerBookBean> findWanKe(String merno) {
		List<WankeMerBookBean> beans = new ArrayList<WankeMerBookBean>();
		sql = "select t.id as yard_id,t.mer_no as yard_mer_no,t.mer_name as yard_mer_name,t.mer_type as yard_mer_type,t.mer_type_name as yard_mer_type_name,"
				+"t.ter_no as yard_ter_no,t.scene_name as yard_scene_name,t.scene_id as yard_scene_id"
			+" from t_wanke_mer_book t where t.mer_no='"
			+merno+"'";

		Vector vector = null;
		try {

			vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					WankeMerBookBean bean = new WankeMerBookBean((HashMap) vector.get(i));
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--findWanKe", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--findWanKe", e);
		}
		return beans;
	}
	
	public Mrch_acc_xBean getMrchaccxById(String id) {
		
		sql = "select id, inst_id, mrchno, accno, acc_name, bank_no, bank_name, acc_add_date, acc_nick_name, short_nick_name, individual"
				+ ", last_settle_date, merchant_id, acc_introd, account_id, is_bj_acct, bis,merchant_x_acc_type1,pay_account_type,"
				+ "acc_x_province,acc_x_city_no,acc_x_text from mrch_acc_x where id = ? ";
		List params = new ArrayList();
		params.add(id);

		Mrch_acc_xBean mrch_acc_xBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				mrch_acc_xBean = new Mrch_acc_xBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return mrch_acc_xBean;
	}
	/**
	 * 通过商户编号获取商户补充表信息
	 * @param id
	 * @return
	 */
	public Merchant_xBean getMerchantXById(String id) {
		sql = "select t1.inst_id,t1.mrchno,t1.mrcht_name,t1.address,t1.add_date,t1.city_no,t1.province,t1.zone,"
			+"t1.state,t1.type_yf,t1.agent,t1.id_type,t1.id_no,t1.id_validity,t1.bus_lic_no,t1.bus_lic_validity,"
			+"t1.tax_id,t1.tax_id_validity,t1.org_id,t1.org_validity,t1.telno1,t1.postcode,t1.contact3,t1.accno,"
			+"t1.merchant_id,t1.bus_lic_pic,t1.tax_id_pic,t1.org_id_pic,t1.legal_rep,t1.lr_id_type,t1.lr_id_no,"
			+"t1.lr_id_validity,t1.fs_cycle,t1.fs_cycle_param,t1.last_settle_date,t1.is_consump_category,t1.MERCHANT_ORG,"
			+"t1.amt_consump_category,t1.is_fmrchno_decide,t1.fs_kp_cycle,t1.fs_kp_cycle_param "
			+ "from merchant_x t1  where t1.merchant_id = '"+id+"' ";
		Merchant_xBean merchant_xBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				merchant_xBean = new Merchant_xBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return merchant_xBean;
	}
	/**
	 * 通过商户编号获取商户分级表信息
	 * @param id
	 * @return
	 */
	public Mrch_classBean getMrchClassByMrchno(String mrchno) {
		sql = "select t1.inst_id, t1.mrchno, t1.classify, t1.fmrchno from mrch_class t1 where 1=1 "
			 + " and t1.mrchno = ? ";
		List params = new ArrayList();
		params.add(mrchno);

		Mrch_classBean mrch_classBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				mrch_classBean = new Mrch_classBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return mrch_classBean;
	}	
	
	public String getMerchantIdByMrchno(String mrchno) {

		sql = "select t1.id from merchant t1 where t1.mrchno = ? ";
		String id = "";
		List params = new ArrayList();
		params.add(mrchno);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				HashMap hm = (HashMap)vector.get(0);
				id = (String)hm.get("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return id;
	}

	/**
	 * 新增商户时，从序列merchant_sequence中获取id
	 * 
	 * @return
	 */
	public String getMerchantid() {
		sql = "select merchant_sequence.nextval as id from dual";
		ArrayList params = new ArrayList();
		String strret = "";
		try {
			Vector areaVector = DbExec.execQuery(sql, null);
			if (areaVector != null && !areaVector.isEmpty()) {
				for (int i = 0; i < areaVector.size(); i++) {
					strret = (String) ((HashMap) areaVector.get(i)).get("id");
					if (strret == null) {
						strret = "1";
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException--getMerchantid", e);
		} catch (Exception e) {
			logger.error("Exception--getMerchantid", e);
		}
		return strret;
	}
	
	public String getMrchaccxid() {
		sql = "select MRCH_ACC_X_SEQUENCE.nextval as id from dual";
		ArrayList params = new ArrayList();
		String strret = "";
		try {
			Vector areaVector = DbExec.execQuery(sql, null);
			if (areaVector != null && !areaVector.isEmpty()) {
				for (int i = 0; i < areaVector.size(); i++) {
					strret = (String) ((HashMap) areaVector.get(i)).get("id");
					if (strret == null) {
						strret = "1";
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException--getMerchantid", e);
		} catch (Exception e) {
			logger.error("Exception--getMerchantid", e);
		}
		return strret;
	}

	/**
	 * 商户信息添加
	 * 
	 * @param merchantBean
	 * @param mrchmtdbean
	 * @param mrchaccbean
	 * @param merchant_xBean
	 * @param mrch_acc_xBean
	 * @return
	 */
	public int addMerchantInfo(MerchantBean merchantBean,
			Mrchmtdbean mrchmtdbean, Mrchaccbean mrchaccbean,
			Merchant_xBean merchant_xBean, Mrch_classBean mrch_classBean,WankeMerBookBean wanke_MerBookBean) {
		List sqlList = new ArrayList(); // 用于存放对应的sql语句
		List paramsList = new ArrayList(); // 用于存放对应sql的params的List值
		addMerchantbean(merchantBean, sqlList, paramsList);
		List sqlBlob = new ArrayList(); // 用于更新blob字段的sql语句
		List paramsBlob = new ArrayList(); // 用于存放对应Blob的params的List值
		String merchant_id = merchantBean.getId();
		mrchmtdbean.setMerchant_id(merchant_id);
		mrchaccbean.setMerchant_id(merchant_id);
		merchant_xBean.setMerchant_id(merchant_id);
		//mrch_acc_xBean.setMerchant_id(merchant_id);
		addMrchaccbean(mrchaccbean, sqlList, paramsList);
		addMrchmtdbean(mrchmtdbean, sqlList, paramsList);
		addMerchantXBean(merchant_xBean, sqlList, paramsList, sqlBlob,
				paramsBlob);
//		addMrch_acc_xBean(mrch_acc_xBean, sqlList, paramsList);
		addMrch_classBean(mrch_classBean, sqlList, paramsList);
		//添加万科商户表
				if(wanke_MerBookBean.getYard_mer_type()!=null){
					if("0".equals(wanke_MerBookBean.getYard_mer_type())){
						addWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList);
					}
				}
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList, sqlBlob,
					paramsBlob);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addMerchantInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addMerchantInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int addMrchaccxInfo(Mrch_acc_xBean mrch_acc_xBean) {
		List sqlList = new ArrayList(); // 用于存放对应的sql语句
		List paramsList = new ArrayList(); // 用于存放对应sql的params的List值
		
		addMrch_acc_xBean(mrch_acc_xBean, sqlList, paramsList);
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList, null,
					null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addMrchaccxInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addMrchaccxInfo", e);
			result = -1;
		}
		return result;
	}

	public int updateMerchantInfo(MerchantBean merchantBean,
			Mrchmtdbean mrchmtdbean, Mrchaccbean mrchaccbean,
			Merchant_xBean merchant_xBean,Mrch_classBean mrch_classBean,WankeMerBookBean wanke_MerBookBean) {
		List sqlList = new ArrayList(); // 用于存放对应的sql语句
		List paramsList = new ArrayList(); // 用于存放对应sql的params的List值
		String merchant_id = merchantBean.getId();
		updMerchantbean(merchantBean, sqlList, paramsList);
		
		//商户状态设置为“不可用”或“黑名单”时，停用商户下挂着的所有终端
		if(merchantBean.getMrchstat()!=null&&!"0".equals(merchantBean.getMrchstat())){
			TerminalDao terminalDao = new TerminalDao();
			terminalDao.updateTermStatByMrchNo(merchantBean.getMrchno(),merchantBean.getId());
		}
		
		List sqlBlob = new ArrayList(); // 用于更新blob字段的sql语句
		List paramsBlob = new ArrayList(); // 用于存放对应Blob的params的List值
		merchant_xBean.setMerchant_id(merchant_id);
		updMerchantXBean(merchant_xBean, sqlList, paramsList, sqlBlob,
				paramsBlob);

		mrchmtdbean.setMerchant_id(merchant_id);
		updMrchmtdbean(mrchmtdbean, sqlList, paramsList);

		mrchaccbean.setMerchant_id(merchant_id);
		updMrchaccbean(mrchaccbean, sqlList, paramsList);

		updMrch_classBean(mrch_classBean, sqlList, paramsList);
		//修改万科商户表信息
				if(wanke_MerBookBean.getYard_mer_type()!=null){
					List<WankeMerBookBean> wankeList = findWanKe(wanke_MerBookBean.getYard_mer_no());
					//商户号存在万科商户表
					if("0".equals(wanke_MerBookBean.getYard_mer_type())){
						//原先商户号存在万科商户表
						if(wankeList.size()==1){
							updWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList);
						}
						//原先商户号不存在万科商户表
						if(wankeList.size()==0){
						addWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList);
						}
					}
					//商户号不存在万科商户表
					if(!"0".equals(wanke_MerBookBean.getYard_mer_type())){
						delWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList);
					}
				}
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList, sqlBlob,
					paramsBlob);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updateMerchantInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updateMerchantInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int updateMrchaccxInfo(Mrch_acc_xBean mrch_acc_xBean) {
		List sqlList = new ArrayList(); // 用于存放对应的sql语句
		List paramsList = new ArrayList(); // 用于存放对应sql的params的List值

		updMrch_acc_xBean(mrch_acc_xBean, sqlList, paramsList);
		
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList, null,
					null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updateMrchaccxInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updateMrchaccxInfo", e);
			result = -1;
		}
		return result;
	}

	/**
	 * 增加Merchant表信息
	 * 
	 * @param merchantBean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addMerchantbean(MerchantBean merchantBean, List sqlList,
			List paramsList) {
		String addSql = "insert into merchant (INST_ID, MRCHNO, HEAD_OFFICE, NAME, TRADING_AS, ADDRESS1, ADDRESS2,"
				+ " CITY, STATE, POSTCODE, COUNTRYCODE, PHY_ADDRESS1, PHY_ADDRESS2, PHY_CITY, PHY_STATE, PHY_POSTCODE, PHY_COUNTRYCODE,"
				+ " REG_ADDRESS1, REG_ADDRESS2, REG_CITY, REG_STATE, REG_POSTCODE, REG_COUNTRYCODE, CURRCODE, MRCHTYPE, ACPTBUS, CONTACT1,"
				+ " CONTACT2, CONTACT3, TELNO1, TELNO2, TELNO3, FAXNO, EMAIL, TELEX, SORTCODE, BRNCODE, ACCNO, ACCNM, TAXCODE, STMTFREQ, "
				+ " STMTTO, STMTTO_HO, PAYMTYPE, PAYMTO, POSFLAG, VIPFLAG, MSC_CHARGE, MSC_CALC, MSC_TABLE, MSC, CONTRNO, CONTRDATE, CONTRCNX,"
				+ " CONTRSIGN, OFFICIAL, MRCHSTAT, RNC, TAXREG, RETENAMT, RETENPC, ZONEGEOG, ZONECOMER, ZONEPOSTAL, USRDATA1, USRDATA2, USRDATA3,"
				+ " MEMO, CYCBEGIN, CYCLEN, NO_IMPRNTRS, CONACCNO, CONCUR, PAYOFFSET, ALLOWINST, PPPYMNTATTR, CAT_PARAMS, ID, VERNO_CTX)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),to_date(?, 'yyyy-mm-dd'),?,?"
				+ ",?,?,?,?,?,?,?,?,?,?,?,empty_clob(),to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,'0')";

		// 将添加的sql语句存放于保存sql语句的sqlList中
		sqlList.add(addSql);

		ArrayList params = new ArrayList();
		params.add(merchantBean.getInst_id());
		params.add(merchantBean.getMrchno());
		params.add(merchantBean.getHead_office());
		params.add(merchantBean.getName());
		params.add(merchantBean.getTrading_as());
		params.add(merchantBean.getAddress1());
		params.add(merchantBean.getAddress2());
		params.add(merchantBean.getCity());
		params.add(merchantBean.getState());
		params.add(merchantBean.getPostcode());
		params.add(merchantBean.getCountrycode());
		params.add(merchantBean.getPhy_address1());
		params.add(merchantBean.getPhy_address2());
		params.add(merchantBean.getPhy_city());
		params.add(merchantBean.getPhy_state());
		params.add(merchantBean.getPhy_postcode());
		params.add(merchantBean.getPhy_countrycode());
		params.add(merchantBean.getReg_address1());
		params.add(merchantBean.getReg_address2());
		params.add(merchantBean.getReg_city());
		params.add(merchantBean.getReg_state());
		params.add(merchantBean.getReg_postcode());
		params.add(merchantBean.getReg_countrycode());
		params.add(merchantBean.getCurrcode());
		params.add(merchantBean.getMrchtype());
		params.add(merchantBean.getAcptbus());
		params.add(merchantBean.getContact1());
		params.add(merchantBean.getContact2());
		params.add(merchantBean.getContact3());
		params.add(merchantBean.getTelno1());
		params.add(merchantBean.getTelno2());
		params.add(merchantBean.getTelno3());
		params.add(merchantBean.getFaxno());
		params.add(merchantBean.getEmail());
		params.add(merchantBean.getTelex());
		params.add(merchantBean.getSortcode());
		params.add(merchantBean.getBrncode());
		params.add(merchantBean.getAccno());
		params.add(merchantBean.getAccnm());
		params.add(merchantBean.getTaxcode());
		params.add(merchantBean.getStmtfreq());
		params.add(merchantBean.getStmtto());
		params.add(merchantBean.getStmtto_ho());
		params.add(merchantBean.getPaymtype());
		params.add(merchantBean.getPaymto());
		params.add(merchantBean.getPosflag());
		params.add(merchantBean.getVipflag());
		params.add(merchantBean.getMsc_charge());
		params.add(merchantBean.getMsc_calc());
		params.add(merchantBean.getMsc_table());
		params.add(merchantBean.getMsc());
		params.add(merchantBean.getContrno());
		params.add(merchantBean.getContrdate());
		params.add(merchantBean.getContrcnx());
		params.add(merchantBean.getContrsign());
		params.add(merchantBean.getOfficial());
		params.add(merchantBean.getMrchstat());
		params.add(merchantBean.getRnc());
		params.add(merchantBean.getTaxreg());
		params.add(merchantBean.getRetenamt());
		params.add(merchantBean.getRetenpc());
		params.add(merchantBean.getZonegeog());
		params.add(merchantBean.getZonecomer());
		params.add(merchantBean.getZonepostal());
		params.add(merchantBean.getUsrdata1());
		params.add(merchantBean.getUsrdata2());
		params.add(merchantBean.getUsrdata3());
		// params.add(merchantBean.getMemo());
		params.add(merchantBean.getCycbegin());
		params.add(merchantBean.getCyclen());
		params.add(merchantBean.getNo_imprntrs());
		params.add(merchantBean.getConaccno());
		params.add(merchantBean.getConcur());
		params.add(merchantBean.getPayoffset());
		params.add(merchantBean.getAllowinst());
		params.add(merchantBean.getPppymntattr());
		params.add(merchantBean.getCat_params());
		String merchantid = getMerchantid();
		params.add(merchantid);
		merchantBean.setId(merchantid);
		// 将对应的merchant填写信息存放于保存参数信息的paramsList中
		paramsList.add(params);
	}

	public void updMerchantbean(MerchantBean merchantBean, List sqlList,
			List paramsList) {
		String addSql = " update merchant set                   "
				+ " INST_ID=?,                           "
				+ " MRCHNO=?,                            "
				+ " HEAD_OFFICE=?,                        "
				+ " NAME=?,                               "
				+ " TRADING_AS=?,                         "
				+ " ADDRESS1=?,                           "
				+ " ADDRESS2=?,                           "
				+ " CITY=?,                               "
				+ " STATE=?,                              "
				+ " POSTCODE=?,                           "
				+ " COUNTRYCODE=?,                        "
				+ " PHY_ADDRESS1=?,                       "
				+ " PHY_ADDRESS2=?,                       "
				+ " PHY_CITY=?,                           "
				+ " PHY_STATE=?,                          "
				+ " PHY_POSTCODE=?,                       "
				+ " PHY_COUNTRYCODE=?,                    "
				+ " REG_ADDRESS1=?,                       "
				+ " REG_ADDRESS2=?,                       "
				+ " REG_CITY=?,                           "
				+ " REG_STATE=?,                          "
				+ " REG_POSTCODE=?,                       "
				+ " REG_COUNTRYCODE=?,                    "
				+ " CURRCODE=?,                           "
				+ " MRCHTYPE=?,                           "
				+ " ACPTBUS=?,                            "
				+ " CONTACT1=?,                           "
				+ " CONTACT2=?,                           "
				+ " CONTACT3=?,                           "
				+ " TELNO1=?,                             "
				+ " TELNO2=?,                             "
				+ " TELNO3=?,                             "
				+ " FAXNO=?,                              "
				+ " EMAIL=?,                              "
				+ " TELEX=?,                              "
				+ " SORTCODE=?,                           "
				+ " BRNCODE=?,                            "
				+ " ACCNO=?,                              "
				+ " ACCNM=?,                              "
				+ " TAXCODE=?,                            "
				+ " STMTFREQ=?,                           "
				+ " STMTTO=?,                             "
				+ " STMTTO_HO=?,                          "
				+ " PAYMTYPE=?,                           "
				+ " PAYMTO=?,                             "
				+ " POSFLAG=?,                            "
				+ " VIPFLAG=?,                            "
				+ " MSC_CHARGE=?,                         "
				+ " MSC_CALC=?,                           "
				+ " MSC_TABLE=?,                          "
				+ " MSC=?,                                "
				+ " CONTRNO=?,                            "
				+ " CONTRDATE=to_date(?, 'yyyy-mm-dd'),   "
				+ " CONTRCNX=to_date(?, 'yyyy-mm-dd'),    "
				+ " CONTRSIGN=?,                          "
				+ " OFFICIAL=?,                           "
				+ " MRCHSTAT=?,                           "
				+ " RNC=?,                                "
				+ " TAXREG=?,                             "
				+ " RETENAMT=?,                           "
				+ " RETENPC=?,                            "
				+ " ZONEGEOG=?,                           "
				+ " ZONECOMER=?,                          "
				+ " ZONEPOSTAL=?,                         "
				+ " USRDATA1=?,                           "
				+ " USRDATA2=?,                           "
				+ " USRDATA3=?,                           "
				// +" MEMO=empty_clob(),                    "
				+ " CYCBEGIN=to_date(?, 'yyyy-mm-dd'),    "
				+ " CYCLEN=?,                             "
				+ " NO_IMPRNTRS=?,                        "
				+ " CONACCNO=?,                           "
				+ " CONCUR=?,                             "
				+ " PAYOFFSET=?,                          "
				+ " ALLOWINST=?,                          "
				+ " PPPYMNTATTR=?,                        "
				+ " CAT_PARAMS=?                          "
				+ " where id=?                            ";

		// 将添加的sql语句存放于保存sql语句的sqlList中
		sqlList.add(addSql);

		ArrayList params = new ArrayList();
		params.add(merchantBean.getInst_id());
		params.add(merchantBean.getMrchno());
		params.add(merchantBean.getHead_office());
		params.add(merchantBean.getName());
		params.add(merchantBean.getTrading_as());
		params.add(merchantBean.getAddress1());
		params.add(merchantBean.getAddress2());
		params.add(merchantBean.getCity());
		params.add(merchantBean.getState());
		params.add(merchantBean.getPostcode());
		params.add(merchantBean.getCountrycode());
		params.add(merchantBean.getPhy_address1());
		params.add(merchantBean.getPhy_address2());
		params.add(merchantBean.getPhy_city());
		params.add(merchantBean.getPhy_state());
		params.add(merchantBean.getPhy_postcode());
		params.add(merchantBean.getPhy_countrycode());
		params.add(merchantBean.getReg_address1());
		params.add(merchantBean.getReg_address2());
		params.add(merchantBean.getReg_city());
		params.add(merchantBean.getReg_state());
		params.add(merchantBean.getReg_postcode());
		params.add(merchantBean.getReg_countrycode());
		params.add(merchantBean.getCurrcode());
		params.add(merchantBean.getMrchtype());
		params.add(merchantBean.getAcptbus());
		params.add(merchantBean.getContact1());
		params.add(merchantBean.getContact2());
		params.add(merchantBean.getContact3());
		params.add(merchantBean.getTelno1());
		params.add(merchantBean.getTelno2());
		params.add(merchantBean.getTelno3());
		params.add(merchantBean.getFaxno());
		params.add(merchantBean.getEmail());
		params.add(merchantBean.getTelex());
		params.add(merchantBean.getSortcode());
		params.add(merchantBean.getBrncode());
		params.add(merchantBean.getAccno());
		params.add(merchantBean.getAccnm());
		params.add(merchantBean.getTaxcode());
		params.add(merchantBean.getStmtfreq());
		params.add(merchantBean.getStmtto());
		params.add(merchantBean.getStmtto_ho());
		params.add(merchantBean.getPaymtype());
		params.add(merchantBean.getPaymto());
		params.add(merchantBean.getPosflag());
		params.add(merchantBean.getVipflag());
		params.add(merchantBean.getMsc_charge());
		params.add(merchantBean.getMsc_calc());
		params.add(merchantBean.getMsc_table());
		params.add(merchantBean.getMsc());
		params.add(merchantBean.getContrno());
		params.add(merchantBean.getContrdate());
		params.add(merchantBean.getContrcnx());
		params.add(merchantBean.getContrsign());
		params.add(merchantBean.getOfficial());
		params.add(merchantBean.getMrchstat());
		params.add(merchantBean.getRnc());
		params.add(merchantBean.getTaxreg());
		params.add(merchantBean.getRetenamt());
		params.add(merchantBean.getRetenpc());
		params.add(merchantBean.getZonegeog());
		params.add(merchantBean.getZonecomer());
		params.add(merchantBean.getZonepostal());
		params.add(merchantBean.getUsrdata1());
		params.add(merchantBean.getUsrdata2());
		params.add(merchantBean.getUsrdata3());
		// params.add(merchantBean.getMemo());
		params.add(merchantBean.getCycbegin());
		params.add(merchantBean.getCyclen());
		params.add(merchantBean.getNo_imprntrs());
		params.add(merchantBean.getConaccno());
		params.add(merchantBean.getConcur());
		params.add(merchantBean.getPayoffset());
		params.add(merchantBean.getAllowinst());
		params.add(merchantBean.getPppymntattr());
		params.add(merchantBean.getCat_params());
		// String merchantid = getMerchantid();
		params.add(merchantBean.getId());
		// merchantBean.setId(merchantid);
		// 将对应的merchant填写信息存放于保存参数信息的paramsList中
		paramsList.add(params);
	}

	/**
	 * 添加商户交易汇总信息数据
	 * 
	 * @param mrchmtdbean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addMrchmtdbean(Mrchmtdbean mrchmtdbean, List sqlList,
			List paramsList) {
		sql = "insert into Mrchmtd (MERCHANT_ID, CURRCODE, BTCHCNTMTD, BTCHCNTPM, BTCHCNTYTD, BTCHDRMTD, BTCHDRPM, BTCHDRYTD, BTCHCRMTD, BTCHCRPM, BTCHCRYTD, BTCHCOMMTD, BTCHCOMPM, BTCHCOMYTD)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(mrchmtdbean.getMerchant_id());
		params.add(mrchmtdbean.getCurrcode());
		params.add(mrchmtdbean.getBtchntmtd());
		params.add(mrchmtdbean.getBtchntpm());
		params.add(mrchmtdbean.getBtchcntytd());
		params.add(mrchmtdbean.getBtchdrmtd());
		params.add(mrchmtdbean.getBtchdrpm());
		params.add(mrchmtdbean.getBtchdrytd());
		params.add(mrchmtdbean.getBtchcrmtd());
		params.add(mrchmtdbean.getBtchcrpm());
		params.add(mrchmtdbean.getBtchcrytd());
		params.add(mrchmtdbean.getBtchcommtd());
		params.add(mrchmtdbean.getBtchcompm());
		params.add(mrchmtdbean.getBtchcomytd());
		paramsList.add(params);
	}

	public void updMrchmtdbean(Mrchmtdbean mrchmtdbean, List sqlList,
			List paramsList) {
		sql = " update Mrchmtd set  " + " CURRCODE=?,         "
				+ " BTCHCNTMTD=?,       " + " BTCHCNTPM=?,        "
				+ " BTCHCNTYTD=?,       " + " BTCHDRMTD=?,        "
				+ " BTCHDRPM=?,         " + " BTCHDRYTD=?,        "
				+ " BTCHCRMTD=?,        " + " BTCHCRPM=?,         "
				+ " BTCHCRYTD=?,        " + " BTCHCOMMTD=?,       "
				+ " BTCHCOMPM=?,        " + " BTCHCOMYTD=?        "
				+ " where merchant_id=? ";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(mrchmtdbean.getCurrcode());
		params.add(mrchmtdbean.getBtchcntmtd());
		params.add(mrchmtdbean.getBtchcntpm());
		params.add(mrchmtdbean.getBtchcntytd());
		params.add(mrchmtdbean.getBtchdrmtd());
		params.add(mrchmtdbean.getBtchdrpm());
		params.add(mrchmtdbean.getBtchdrytd());
		params.add(mrchmtdbean.getBtchcrmtd());
		params.add(mrchmtdbean.getBtchcrpm());
		params.add(mrchmtdbean.getBtchcrytd());
		params.add(mrchmtdbean.getBtchcommtd());
		params.add(mrchmtdbean.getBtchcompm());
		params.add(mrchmtdbean.getBtchcomytd());
		params.add(mrchmtdbean.getMerchant_id());

		paramsList.add(params);
	}

	/**
	 * 添加商户账户信息
	 * 
	 * @param mrchaccbean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addMrchaccbean(Mrchaccbean mrchaccbean, List sqlList,
			List paramsList) {
		sql = "insert into Mrchacc (MERCHANT_ID, CURRCODE,  DATE_LAST_STMT, CLOSING_BAL, CURRENT_BAL, LAST_POST_BAL, LAST_POST_COM,LAST_POST_TAX)"
				+ "values (?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(mrchaccbean.getMerchant_id());
		params.add(mrchaccbean.getCurrcode());
		params.add(mrchaccbean.getDate_last_stmt());
		params.add(mrchaccbean.getClosing_bal());
		params.add(mrchaccbean.getCurrent_bal());
		params.add(mrchaccbean.getLast_post_bal());
		params.add(mrchaccbean.getLast_post_com());
		params.add(mrchaccbean.getLast_post_tax());
		paramsList.add(params);
	}

	public void updMrchaccbean(Mrchaccbean mrchaccbean, List sqlList,
			List paramsList) {
		sql = " update Mrchacc set  " + " CURRCODE=?,         "
				+ " DATE_LAST_STMT=to_date(?, 'yyyy-mm-dd'),   "
				+ " CLOSING_BAL=?,      " + " CURRENT_BAL=?,      "
				+ " LAST_POST_BAL=?,    " + " LAST_POST_COM=?,    "
				+ " LAST_POST_TAX=?     " + " where merchant_id=? ";
		sqlList.add(sql);
		ArrayList params = new ArrayList();

		params.add(mrchaccbean.getCurrcode());
		params.add(mrchaccbean.getDate_last_stmt());
		params.add(mrchaccbean.getClosing_bal());
		params.add(mrchaccbean.getCurrent_bal());
		params.add(mrchaccbean.getLast_post_bal());
		params.add(mrchaccbean.getLast_post_com());
		params.add(mrchaccbean.getLast_post_tax());
		params.add(mrchaccbean.getMerchant_id());
		paramsList.add(params);
	}

	/**
	 * 添加商户补充信息
	 * 
	 * @param merchant_xBean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addMerchantXBean(Merchant_xBean merchant_xBean, List sqlList,
			List paramsList, List sqlBlob, List paramsBlob) {
		sql = "insert into merchant_x (inst_id,mrchno,mrcht_name,address,add_date,city_no,province,zone,state,type_yf,agent,id_type,id_no,id_validity,bus_lic_no,bus_lic_validity"
				+ ",tax_id,tax_id_validity,org_id,org_validity,bus_lic_pic,tax_id_pic,org_id_pic,telno1,postcode,contact3,accno,merchant_id,legal_rep,"
				+" lr_id_type,lr_id_no,lr_id_validity, fs_cycle, fs_cycle_param, last_settle_date, is_consump_category, amt_consump_category, is_fmrchno_decide,"
				+"FS_KP_CYCLE,FS_KP_CYCLE_PARAM,LAST_KP_DATE,merge_money_flag, mrch_snippet,IS_BJ,IS_CARD_TYPE_GROUP,IS_KP,DISABLED_DATE,ENABLE_DATE,merchant_org,man_name,"
				+ "id_type1,id_no1,id_deadline1,acc_x_name,update_x_date,settlement_person,merchant_email,is_send_billdetail,"
				+ "merchant_x_operate,merchant_x_type,merchant_x_reg_amt,merchant_x_code,merchant_fenrunorg,combine_flag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "to_date(?, 'yyyy-mm-dd'),?,to_date(?, 'yyyy-mm-dd'),?,to_date(?, 'yyyy-mm-dd'),?,to_date(?, 'yyyy-mm-dd'), "
				+"?,?,?,?,?,?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),"
				+ "to_date(?, 'yyyy-mm-dd'),?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,sysdate,?,?,?,?,?,?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(merchant_xBean.getInst_id());
		params.add(merchant_xBean.getMrchno());
		params.add(merchant_xBean.getMrcht_name());
		params.add(merchant_xBean.getAddress());
		params.add(merchant_xBean.getAdd_date());
		params.add(merchant_xBean.getCity_no());
		params.add(merchant_xBean.getProvince());
		params.add(merchant_xBean.getZone());
		params.add(merchant_xBean.getState());
		params.add(merchant_xBean.getType_yf());
		params.add(merchant_xBean.getAgent());
		params.add(merchant_xBean.getId_type());
		params.add(merchant_xBean.getId_no());
		params.add(merchant_xBean.getId_validity());
		params.add(merchant_xBean.getBus_lic_no());
		params.add(merchant_xBean.getBus_lic_validity());
		params.add(merchant_xBean.getTax_id());
		params.add(merchant_xBean.getTax_id_validity());
		params.add(merchant_xBean.getOrg_id());
		params.add(merchant_xBean.getOrg_validity());
		params.add(merchant_xBean.getBus_lic_pic());
		params.add(merchant_xBean.getTax_id_pic());
		params.add(merchant_xBean.getOrg_id_pic());
		params.add(merchant_xBean.getTelno1());
		params.add(merchant_xBean.getPostcode());
		params.add(merchant_xBean.getContact3());
		params.add(merchant_xBean.getAccno());
		params.add(merchant_xBean.getMerchant_id());
		params.add(merchant_xBean.getLegal_rep());
		params.add(merchant_xBean.getLr_id_type());
		params.add(merchant_xBean.getLr_id_no());
		params.add(merchant_xBean.getLr_id_validity());
		//结算信息
		params.add(merchant_xBean.getFs_cycle());
		params.add(merchant_xBean.getFs_cycle_param());
		params.add(merchant_xBean.getLast_settle_date());
		params.add(merchant_xBean.getIs_consump_category());
		params.add(merchant_xBean.getAmt_consump_category());
		params.add(merchant_xBean.getIs_fmrchno_decide());	
		params.add(merchant_xBean.getFs_kp_cycle());
		params.add(merchant_xBean.getFs_kp_cycle_param());	
		params.add(merchant_xBean.getLast_kp_date());
		

		params.add(merchant_xBean.getMerge_money_flag());
		params.add(merchant_xBean.getMrch_snippet());
		
		params.add(merchant_xBean.getIs_bj());	
		params.add(merchant_xBean.getIs_card_type_group());	
		params.add(merchant_xBean.getIs_kp());	
		params.add(merchant_xBean.getDisabled_date());	
		params.add(merchant_xBean.getEnable_date());	
		params.add(merchant_xBean.getMerchant_org());
		params.add(merchant_xBean.getMan_name());
		params.add(merchant_xBean.getId_type1());
		params.add(merchant_xBean.getId_no1());
		params.add(merchant_xBean.getId_deadline1());
		params.add(merchant_xBean.getAcc_x_name());
		params.add(merchant_xBean.getSettlement_person());
		params.add(merchant_xBean.getMerchant_email());
		params.add(merchant_xBean.getIs_send_billdetail());
		
		params.add(merchant_xBean.getMerchant_x_operate());
		params.add(merchant_xBean.getMerchant_x_type());
		params.add(merchant_xBean.getMerchant_x_reg_amt());
		params.add(merchant_xBean.getMerchant_x_code());
		params.add(merchant_xBean.getMerchant_fenrunorg());
		params.add(merchant_xBean.getCombine_flag());
		//params.add(new Date());
		paramsList.add(params);
	}

	public void updMerchantXBean(Merchant_xBean merchant_xBean, List sqlList,
			List paramsList, List sqlBlob, List paramsBlob) {

		sql = " update merchant_x s set" + " s.inst_id=?,           "
				+ " s.mrchno=?,            " + " s.mrcht_name=?,        "
				+ " s.address=?,           " + " s.add_date=?,          "
				+ " s.city_no=?,           " + " s.province=?,          "
				+ " s.zone=?,              " + " s.state=?,             "
				+ " s.type_yf=?,           " + " s.agent=?,             "
				+ " s.id_type=?,           " + " s.id_no=?,             "
				+ " s.id_validity=to_date(?, 'yyyy-mm-dd'),       "
				+ " s.bus_lic_no=?,        "
				+ " s.bus_lic_validity=to_date(?, 'yyyy-mm-dd'),  "
				+ " s.tax_id=?,            "
				+ " s.tax_id_validity=to_date(?, 'yyyy-mm-dd'),   "
				+ " s.org_id=?,            "
				+ " s.org_validity=to_date(?, 'yyyy-mm-dd'),      "
				+ " s.bus_lic_pic=?,       " + " s.tax_id_pic=?,        "
				+ " s.org_id_pic=?,        " + " s.telno1=?,            "
				+ " s.postcode=?,          " + " s.contact3=?,          "
				+ " s.legal_rep=?,         " + " s.lr_id_type=?,            "
				+ " s.lr_id_no=?,          " + " s.lr_id_validity=to_date(?, 'yyyy-mm-dd'), "
				+ " s.accno=? ,             "+ " s.mrch_snippet=?,          "
				+ " s.merge_money_flag=?,      "
				//结算信息
				+ " s.fs_cycle=?, "
				+ " s.fs_cycle_param=?, "
				+ " s.last_settle_date=to_date(?, 'yyyy-mm-dd'), "
				+ " s.is_consump_category=?,"
				+ " s.amt_consump_category=?,"
				+ " s.is_fmrchno_decide=?,  "
				+ " s.fs_kp_cycle=?, "
				+ " s.fs_kp_cycle_param=?, "				
				+ " s.last_kp_date=to_date(?, 'yyyy-mm-dd'), "
				+ " s.is_bj=?,  "
				+ " s.is_card_type_group=?,  "
				+ " s.is_kp=?,  "
				+ " s.DISABLED_DATE=to_date(?, 'yyyy-mm-dd'),  "
				+ " s.ENABLE_DATE=to_date(?, 'yyyy-mm-dd'),  "
				+ " s.MERCHANT_ORG=?,  "
				+ " s.man_name=?,      "
				+ " s.id_type1=?, "
				+ " s.id_no1=?, "
				+ " s.id_deadline1=to_date(?, 'yyyy-mm-dd'),"
				+ " s.acc_x_name=?, "
				+ " s.update_x_date=sysdate, "
				+ " s.settlement_person=?, "
				+ " s.merchant_email=?, "
				+ " s.is_send_billdetail=?, "
				+ " s.merchant_x_operate=?, "
				+ " s.merchant_x_type=?, "
				+ " s.merchant_x_reg_amt=?, "
				+ " s.merchant_x_code=?, "
				+ " s.merchant_fenrunorg=?, "
				+ " s.combine_flag=? "
				+ " where s.merchant_id=?  ";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(merchant_xBean.getInst_id());
		params.add(merchant_xBean.getMrchno());
		params.add(merchant_xBean.getMrcht_name());
		params.add(merchant_xBean.getAddress());
		params.add(merchant_xBean.getAdd_date());
		params.add(merchant_xBean.getCity_no());
		params.add(merchant_xBean.getProvince());
		params.add(merchant_xBean.getZone());
		params.add(merchant_xBean.getState());
		params.add(merchant_xBean.getType_yf());
		params.add(merchant_xBean.getAgent());
		params.add(merchant_xBean.getId_type());
		params.add(merchant_xBean.getId_no());
		params.add(merchant_xBean.getId_validity());
		params.add(merchant_xBean.getBus_lic_no());
		params.add(merchant_xBean.getBus_lic_validity());
		params.add(merchant_xBean.getTax_id());
		params.add(merchant_xBean.getTax_id_validity());
		params.add(merchant_xBean.getOrg_id());
		params.add(merchant_xBean.getOrg_validity());
		params.add(merchant_xBean.getBus_lic_pic());
		params.add(merchant_xBean.getTax_id_pic());
		params.add(merchant_xBean.getOrg_id_pic());
		params.add(merchant_xBean.getTelno1());
		params.add(merchant_xBean.getPostcode());
		params.add(merchant_xBean.getContact3());
		params.add(merchant_xBean.getLegal_rep());
		params.add(merchant_xBean.getLr_id_type());
		params.add(merchant_xBean.getLr_id_no());
		params.add(merchant_xBean.getLr_id_validity());		
		params.add(merchant_xBean.getAccno());
		
		params.add(merchant_xBean.getMrch_snippet());
		params.add(merchant_xBean.getMerge_money_flag());
		//结算信息
		params.add(merchant_xBean.getFs_cycle());
		params.add(merchant_xBean.getFs_cycle_param());
		params.add(merchant_xBean.getLast_settle_date());
		params.add(merchant_xBean.getIs_consump_category());
		params.add(merchant_xBean.getAmt_consump_category());
		params.add(merchant_xBean.getIs_fmrchno_decide());	
		params.add(merchant_xBean.getFs_kp_cycle());
		params.add(merchant_xBean.getFs_kp_cycle_param());
		params.add(merchant_xBean.getLast_kp_date());
		params.add(merchant_xBean.getIs_bj());	
		params.add(merchant_xBean.getIs_card_type_group());	
		params.add(merchant_xBean.getIs_kp());	
		params.add(merchant_xBean.getDisabled_date());	
		params.add(merchant_xBean.getEnable_date());
		
		params.add(merchant_xBean.getMerchant_org());
		params.add(merchant_xBean.getMan_name());
		params.add(merchant_xBean.getId_type1());
		params.add(merchant_xBean.getId_no1());
		params.add(merchant_xBean.getId_deadline1());
		params.add(merchant_xBean.getAcc_x_name());
		params.add(merchant_xBean.getSettlement_person());
		params.add(merchant_xBean.getMerchant_email());
		params.add(merchant_xBean.getIs_send_billdetail());
		params.add(merchant_xBean.getMerchant_x_operate());
		params.add(merchant_xBean.getMerchant_x_type());
		params.add(merchant_xBean.getMerchant_x_reg_amt());
		params.add(merchant_xBean.getMerchant_x_code());
		params.add(merchant_xBean.getMerchant_fenrunorg());
		params.add(merchant_xBean.getCombine_flag());
		
		params.add(merchant_xBean.getMerchant_id());

		paramsList.add(params);

	}

	/**
	 * 添加商户账户补充信息
	 * 
	 * @param mrch_acc_xBean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addMrch_acc_xBean(Mrch_acc_xBean mrch_acc_xBean, List sqlList,
			List paramsList) {
		sql = "insert into mrch_acc_x (id,inst_id,mrchno,accno,acc_name,bank_no,bank_name,acc_add_date,acc_nick_name,"
				+ "short_nick_name,individual,last_settle_date,merchant_id,ACC_INTROD,is_bj_acct, bis,merchant_x_acc_type1"
				+ ",pay_account_type,acc_x_province,acc_x_city_no,acc_x_text) values "
				+ "(?,?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?)";
		sqlList.add(sql);
		List params = new ArrayList();
		params.add(this.getMrchaccxid());
		params.add(mrch_acc_xBean.getInst_id());
		params.add(mrch_acc_xBean.getMrchno());
		params.add(mrch_acc_xBean.getAccno());
		params.add(mrch_acc_xBean.getAcc_name());
		params.add(mrch_acc_xBean.getBank_no());
		params.add(mrch_acc_xBean.getBank_name());
		params.add(mrch_acc_xBean.getAcc_add_date());
		params.add(mrch_acc_xBean.getAcc_nick_name());
		params.add(mrch_acc_xBean.getShort_nick_name());
		params.add(mrch_acc_xBean.getIndividual());
		params.add(mrch_acc_xBean.getLast_settle_date());
		params.add(mrch_acc_xBean.getMerchant_id());
		params.add(mrch_acc_xBean.getAcc_introd());
		params.add(mrch_acc_xBean.getIs_bj_acct());
		params.add(mrch_acc_xBean.getBis());
		params.add(mrch_acc_xBean.getMerchant_x_acc_type1());
		params.add(mrch_acc_xBean.getPay_account_type());
		params.add(mrch_acc_xBean.getAcc_x_province());
		params.add(mrch_acc_xBean.getAcc_x_city_no());
		params.add(mrch_acc_xBean.getAcc_x_text());
		
		paramsList.add(params);
	}

	public void updMrch_acc_xBean(Mrch_acc_xBean mrch_acc_xBean, List sqlList,
			List paramsList) {
		String mrchno=mrch_acc_xBean.getMrchno();
		if(mrchno==null){
			sql = " update mrch_acc_x set " + " accno=?,            "
			+ " acc_name=?,         " + " bank_no=?,          "
			+ " bank_name=?,        "
			+ " acc_add_date=to_date(?, 'yyyy-mm-dd'),     "
			+ " acc_nick_name=?,    " + " short_nick_name=?,  "
			+ " individual=?,       "
			+ " last_settle_date=to_date(?, 'yyyy-mm-dd'),  "
			+ " ACC_INTROD=?,"
			+ " is_bj_acct=?, "
			+ " bis=?, "		
			+ " merchant_x_acc_type1=?, "	
			+ " pay_account_type=?, "	
			+ " acc_x_province=?, "	
			+ " acc_x_city_no=?, "	
			+ " acc_x_text=? "	
			+ " where id=? ";
		}else{
			sql = " update mrch_acc_x set " + " inst_id=?,          "
			+ " mrchno=?,           " + " accno=?,            "
			+ " acc_name=?,         " + " bank_no=?,          "
			+ " bank_name=?,        "
			+ " acc_add_date=to_date(?, 'yyyy-mm-dd'),     "
			+ " acc_nick_name=?,    " + " short_nick_name=?,  "
			+ " individual=?,       "
			+ " last_settle_date=to_date(?, 'yyyy-mm-dd'),  "
			+ " merchant_id=?, "
			+ " ACC_INTROD=?, "
			+ " is_bj_acct=?, "
			+ " bis=?, "				
			+ " merchant_x_acc_type1=?, "	
			+ " pay_account_type=?, "	
			+ " acc_x_province=?, "	
			+ " acc_x_city_no=?, "	
			+ " acc_x_text=? "
			+ " where id=? ";
		}

		sqlList.add(sql);
		List params = new ArrayList();
		// params.add(mrch_acc_xBean.getId());
		if(mrchno!=null&&mrchno.trim().length()>0){
			params.add(mrch_acc_xBean.getInst_id());
			params.add(mrch_acc_xBean.getMrchno());
		}
		params.add(mrch_acc_xBean.getAccno());
		params.add(mrch_acc_xBean.getAcc_name());
		params.add(mrch_acc_xBean.getBank_no());
		params.add(mrch_acc_xBean.getBank_name());
		params.add(mrch_acc_xBean.getAcc_add_date());
		params.add(mrch_acc_xBean.getAcc_nick_name());
		params.add(mrch_acc_xBean.getShort_nick_name());
		params.add(mrch_acc_xBean.getIndividual());
		params.add(mrch_acc_xBean.getLast_settle_date());
		if(mrchno!=null&&mrchno.trim().length()>0){
			params.add(mrch_acc_xBean.getMerchant_id());
		}
		params.add(mrch_acc_xBean.getAcc_introd());
		params.add(mrch_acc_xBean.getIs_bj_acct());
		params.add(mrch_acc_xBean.getBis());		
		params.add(mrch_acc_xBean.getMerchant_x_acc_type1());		
		params.add(mrch_acc_xBean.getPay_account_type());
		params.add(mrch_acc_xBean.getAcc_x_province());
		params.add(mrch_acc_xBean.getAcc_x_city_no());
		params.add(mrch_acc_xBean.getAcc_x_text());
		params.add(mrch_acc_xBean.getId());
		paramsList.add(params);
	}

	public void addMrch_classBean(Mrch_classBean mrch_classBean, List sqlList,
			List paramsList) {
		sql = "insert into mrch_class (INST_ID, MRCHNO, CLASSIFY, FMRCHNO) "
				+ "values (?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(mrch_classBean.getInst_id());
		params.add(mrch_classBean.getMrchno());
		params.add(mrch_classBean.getClassify());
		params.add(mrch_classBean.getFmrchno());

		paramsList.add(params);
	}
	
	/**
	 * 添加万科商户表
	 * 
	 * @param mrchaccbean
	 * @param sqlList
	 * @param paramsList
	 */
	public void addWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
			List paramsList) {
		sql = "insert into t_wanke_mer_book(ID,MER_NO,MER_NAME, MER_TYPE,MER_TYPE_NAME)"
				+ "values (?,?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(getYardId());
		params.add(wanke_MerBookBean.getYard_mer_no());
		params.add(wanke_MerBookBean.getYard_mer_name());
		params.add(wanke_MerBookBean.getYard_mer_type());
		params.add(wanke_MerBookBean.getYard_mer_type_name());
		paramsList.add(params);
	}
	
	public String getYardId() {
		sql = " select MAX(ID) as id from t_wanke_mer_book";
		ArrayList params = new ArrayList();
		int count = 0;
		String strret = "";
		String ID = "";
		try {
			Vector areaVector = DbExec.execQuery(sql, null);
			if (areaVector != null && !areaVector.isEmpty()) {
				for (int i = 0; i < areaVector.size(); i++) {
					strret = (String) ((HashMap) areaVector.get(i)).get("id");
					if (strret == null) {
						ID = "0000000000000001";
					}else{
						count = Integer.parseInt(strret);
						ID=new DecimalFormat("0000000000000000").format(count+1);
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException--getYardId", e);
		} catch (Exception e) {
			logger.error("Exception--getYardId", e);
		}
		return ID;
	}
	
	public void updMrch_classBean(Mrch_classBean mrch_classBean, List sqlList,
			List paramsList) {
		String fmrchno=mrch_classBean.getFmrchno();
		if(fmrchno==null||"".equals(fmrchno)){
			sql = " update mrch_class set  " + " INST_ID=?,         "
			+ " MRCHNO=?,"
			+ " CLASSIFY=?   " 
			+ " where MRCHNO=? ";
		}else{
			sql = " update mrch_class set  " + " INST_ID=?,         "
			+ " MRCHNO=?,"
			+ " CLASSIFY=?,    " 
			+ " FMRCHNO=?     "
			+ " where MRCHNO=? ";
		}
		sqlList.add(sql);
		ArrayList params = new ArrayList();

		params.add(mrch_classBean.getInst_id());
		params.add(mrch_classBean.getMrchno());
		params.add(mrch_classBean.getClassify());
		if(fmrchno!=null&&!"".equals(fmrchno)){
			params.add(mrch_classBean.getFmrchno());
		}
		params.add(mrch_classBean.getMrchno());
		paramsList.add(params);
	}
	
	//修改
	public void updWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
			List paramsList) {
		sql = " update t_wanke_mer_book set  " 
				+ " MER_NAME=?,"
				+ " MER_TYPE=?,"
				+ " MER_TYPE_NAME=?"
				+ " where MER_NO=? ";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		params.add(wanke_MerBookBean.getYard_mer_name());
		
		params.add(wanke_MerBookBean.getYard_mer_type());

		params.add(wanke_MerBookBean.getYard_mer_type_name());
		
		params.add(wanke_MerBookBean.getYard_mer_no());
		paramsList.add(params);
	}
	//删除
	public void delWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
			List paramsList) {
		sql = " delete from t_wanke_mer_book   " 
				+ " where MER_NO=? ";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		
		params.add(wanke_MerBookBean.getYard_mer_no());
		paramsList.add(params);
	}
	/**
	 * 校验商户号是否重复
	 * @param mrchNo
	 * @return
	 */
	public int checkMrchNo( String mrchNo) {
		int count = 0;
		if(mrchNo!=null&&mrchNo.trim().length()>0){
			sql = "select count(*) as numbers FROM merchant_x t WHERE t.mrchno='"+mrchNo.trim()+"'";
			List param = new ArrayList();
			
			try {
				Vector vector = DbExec.execQuery(sql);
				if (vector != null && !vector.isEmpty()) {
					HashMap map = (HashMap) vector.get(0);
					count = Integer.parseInt(((String) map.get("numbers")).trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
		}
		return count;
	}
	
	/**
	 * 校验商户号是否重复
	 * @param mrchNo
	 * @return
	 */
	public int checkAccNo( String accno) {
		int count = 0;
		if(accno!=null&&accno.trim().length()>0){
			sql = "SELECT count(*) as numbers FROM mrch_acc_x t WHERE t.accno = '"+accno.trim()+"'";
			List param = new ArrayList();
			
			try {
				Vector vector = DbExec.execQuery(sql);
				if (vector != null && !vector.isEmpty()) {
					HashMap map = (HashMap) vector.get(0);
					count = Integer.parseInt(((String) map.get("numbers")).trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
		}
		return count;
	}
	
	/**
	 * 根据商户编号判断该商户是否按照父商户结算，如果是：返回父商户编号，否则返回空字符。
	 * @param mrchNo
	 * @return
	 */
	public String getFmerNoByMrchNo(String mrchNo){
		String FmerNo="";
		if(mrchNo!=null&&mrchNo.trim().length()>0){
			sql = "select t.is_fmrchno_decide||'#'||b.fmrchno as fmrchno from merchant_x t,mrch_class b where t.mrchno=b.mrchno and t.mrchno='"+mrchNo.trim()+"'";
			List param = new ArrayList();
			
			try {
				Vector vector = DbExec.execQuery(sql);
				if (vector != null && !vector.isEmpty()) {
					HashMap map = (HashMap) vector.get(0);
					FmerNo = (String) map.get("fmrchno");
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
		}		
		
		return FmerNo;
	}
	/**
	 * 系统参数获取
	 * @return
	 */
	public Map<String,String> getDefaultParams() {
		Map<String,String> defaultParams=new HashMap<String,String>();		
		sql = "select b.inst_id, b.brncode, c.official from inst a, branch b, official c "
			+" where a.id = b.inst_id and a.id = c.inst_id order by a.id asc ";
		String sql1 = "select t.taxcode,t.currcode from TAX t where t.taxcode='T' and t.currcode='156' ";
		String sql2=" select t.taxcode,t.currcode from TAX t ";
		FullMerchantBean fullMerchant = null;
		try {
			//获取inst_id  brncode  official
			Vector vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				fullMerchant = new FullMerchantBean((HashMap) vector.get(0));
				defaultParams.put("inst_id", fullMerchant.getInst_id());
				defaultParams.put("brncode", fullMerchant.getBrncode());
				defaultParams.put("official", fullMerchant.getOfficial());				
			}
			//获取taxcode  currcode
			vector = DbExec.execQuery(sql1, null);
			if (vector != null && !vector.isEmpty()) {
				fullMerchant = new FullMerchantBean((HashMap) vector.get(0));
				defaultParams.put("taxcode", fullMerchant.getTaxcode());
				defaultParams.put("currcode", fullMerchant.getCurrcode());
			}else{
				vector = DbExec.execQuery(sql2, null);
				if (vector != null && !vector.isEmpty()) {
					fullMerchant = new FullMerchantBean((HashMap) vector.get(0));
					defaultParams.put("taxcode", fullMerchant.getTaxcode());
					defaultParams.put("currcode", fullMerchant.getCurrcode());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return defaultParams;
	}	
	
	/**
	 * 设置商户机构，根据商户号
	 * @return
	 */
	public int allotMerchantOrg(String mrchno, String merchantOrg) {
		List sqlList = new ArrayList<>();
		String sql = "update  merchant_x  set merchant_org = '" + merchantOrg + "'"
					+"WHERE mrchno = '" + mrchno + "'";
		sqlList.add(sql);
		try {
			result = DbExec.execBatchDouble(sqlList,null,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: allotMerchantOrg", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: allotMerchantOrg", e);
			result = -1;
		}
		return result;
	}
	public int allotSettlement_person(String mrchno, String settlement_person) {
		List sqlList = new ArrayList<>();
		String sql = "update  merchant_x  set settlement_person = '" + settlement_person + "'"
				+"WHERE mrchno = '" + mrchno + "'";
		sqlList.add(sql);
		try {
			result = DbExec.execBatchDouble(sqlList,null,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: allotSettlement_person", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: allotSettlement_person", e);
			result = -1;
		}
		return result;
	}
	public int allotMerchant_fenrunorg(String mrchno, String merchant_fenrunorg) {
		List sqlList = new ArrayList<>();
		String sql = "update  merchant_x  set merchant_fenrunorg = '" + merchant_fenrunorg + "'"
				+"WHERE mrchno = '" + mrchno + "'";
		sqlList.add(sql);
		try {
			result = DbExec.execBatchDouble(sqlList,null,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: allotMerchant_fenrunorg", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: allotMerchant_fenrunorg", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 * 更新商户机构，根据机构ID
	 * @return
	 */
	public int deleteMerchantOrd(String merchantOrg) {
		List sqlList = new ArrayList<>();
		String sql = "update  merchant_x  set merchant_org = ''"
					+ " WHERE 1=1 ";
		if (StringUtils.isNotEmptyStr(merchantOrg)) {
			sql += " AND MERCHANT_ORG = '" + merchantOrg + "'"; 
		}else {
			return -1;
		}
		sqlList.add(sql);
		try {
			result = DbExec.execBatchDouble(sqlList,null,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: allotMerchantOrg", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: allotMerchantOrg", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 * 查询某机构下的商户信息
	 * @return
	 */
	public List<FullMerchantBean> detailMerchant(String merchantOrg) {
		List<FullMerchantBean> beans = new ArrayList<FullMerchantBean>();
		String sql = "SELECT MRCHNO, MRCHT_NAME FROM merchant_x  " 
				+ " WHERE MERCHANT_ORG IS NOT NULL ";
		if (StringUtils.isNotEmptyStr(merchantOrg)) {
			sql += " AND MERCHANT_ORG = '" + merchantOrg + "'"; 
		}else{
			return null;
		}
		sql += " GROUP BY MRCHNO, MRCHT_NAME ";
		try {
			Vector vector = DbExec.execQuery(sql,null);
			FullMerchantBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new FullMerchantBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: detailMerchant", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: detailMerchant", e);
		}
		return beans;
	}
	public List<FullMerchantBean> detailFenrunMerchant(String merchant_fenrunorg) {
		List<FullMerchantBean> beans = new ArrayList<FullMerchantBean>();
		String sql = "SELECT MRCHNO, MRCHT_NAME FROM merchant_x  " 
				+ " WHERE MERCHANT_FENRUNORG IS NOT NULL ";
		if (StringUtils.isNotEmptyStr(merchant_fenrunorg)) {
			sql += " AND MERCHANT_FENRUNORG = '" + merchant_fenrunorg + "'"; 
		}else{
			return null;
		}
		sql += " GROUP BY MRCHNO, MRCHT_NAME ";
		try {
			Vector vector = DbExec.execQuery(sql,null);
			FullMerchantBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new FullMerchantBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: detailMerchant", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: detailMerchant", e);
		}
		return beans;
	}
	
}
