package com.pay.terminal.dao;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.WankeMerBookBean;
import com.pay.terminal.bean.Enckeybean;
import com.pay.terminal.bean.MrchAccxBean;
import com.pay.terminal.bean.MrchListBean;
import com.pay.terminal.bean.PosdefBean;
import com.pay.terminal.bean.TerminalBean;
import com.pay.terminal.bean.TerminalExportBean;
import com.pay.terminal.bean.Termpos_xBean;
import com.pay.terminal.bean.Termposbean;
import com.pay.terminal.struts.form.TerminalForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 实现业务逻辑操作
 * 
 * 
 */
public class TerminalDao {
	private static final Logger logger = Logger.getLogger(TerminalDao.class);
	private int result = 0;
	private String sql="";

	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(TerminalForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql ="";
		if("0".equals(form.getYard_mer_type())){
			sql  ="select count(*) numbers from t_wanke_mer_book t0,termpos t1, MERCHANT T3, termpos_x t2 left join mrch_acc_x t4 on t2.settle_mrch_acc_id=t4.id where t1.id=t2.id AND T3.ID=T1.MERCHANT_ID AND t0.ter_no=T1.TERMCODE";
		}else{
			sql  ="select count(*) numbers from termpos t1, MERCHANT T3, termpos_x t2 left join mrch_acc_x t4 on t2.settle_mrch_acc_id=t4.id where t1.id=t2.id AND T3.ID=T1.MERCHANT_ID  ";
		}
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
	public List<TerminalBean> getTerminalList(PageBean pageBean,TerminalForm form){
		List<String> param = new ArrayList<String>();
		List<TerminalBean> beans = new ArrayList<TerminalBean>();
		String sql ="";
		if("0".equals(form.getYard_mer_type())){
			sql  ="select t1.id,t1.typeid,t1.termcode,t1.testflag,t1.statusid,t1.currcode,t1.termno,t1.location,t1.conaccno,t1.concur,t1.poschic,t1.poschac,t1.poscrc,t1.posoe,t1.poscdoc,t1.postoc,t1.pospcc,t1.timezone,t1.cat_params,t1.merchant_id,t1.termtype,"
					+ "t2.id as termpos_id,t2.pos_tel,t2.batch_no,t2.add_date,t2.location as x_location,t2.state,t2.city_no,t2.province,t2.zone,nvl2(T2.SETTLE_MRCH_ACC_ID,T4.ACCNO || '(' || T4.ACC_NAME || ')','') as settle_mrch_acc_id,t2.termcode as x_termcode,t2.timezone as x_timezone,t2.inst_id,t2.mrchno||'('||t3.name||')' as mrchno,t2.term_stat,t4.id as m_acc_id "
					+ " from t_wanke_mer_book t0,termpos t1, MERCHANT T3, termpos_x t2 left join mrch_acc_x t4 on t2.settle_mrch_acc_id=t4.id where t1.id=t2.id AND T3.ID=T1.MERCHANT_ID  AND t0.ter_no=T1.TERMCODE";
		}else{
			sql  ="select t1.id,t1.typeid,t1.termcode,t1.testflag,t1.statusid,t1.currcode,t1.termno,t1.location,t1.conaccno,t1.concur,t1.poschic,t1.poschac,t1.poscrc,t1.posoe,t1.poscdoc,t1.postoc,t1.pospcc,t1.timezone,t1.cat_params,t1.merchant_id,t1.termtype,"
					+ "t2.id as termpos_id,t2.pos_tel,t2.batch_no,t2.add_date,t2.location as x_location,t2.state,t2.city_no,t2.province,t2.zone,nvl2(T2.SETTLE_MRCH_ACC_ID,T4.ACCNO || '(' || T4.ACC_NAME || ')','') as settle_mrch_acc_id,t2.termcode as x_termcode,t2.timezone as x_timezone,t2.inst_id,t2.mrchno||'('||t3.name||')' as mrchno,t2.term_stat,t4.id as m_acc_id "
					+ " from termpos t1, MERCHANT T3, termpos_x t2 left join mrch_acc_x t4 on t2.settle_mrch_acc_id=t4.id where t1.id=t2.id AND T3.ID=T1.MERCHANT_ID  ";
			
		}
		sql = getSql(form,sql,param) + " order by t3.mrchno desc,t1.termno asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			TerminalBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalBean(map);
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
	 * excel list
	 * @param form
	 * @return
	 */
	public List<TerminalExportBean> getTerminalList(TerminalForm form){
		List<String> param = new ArrayList<String>();
		List<TerminalExportBean> beans = new ArrayList<TerminalExportBean>();
		String sql  ="select t1.id,t1.termcode,t1.termno,t1.location,decode(t2.term_stat,'0','可用','停用') as term_stat,t2.mrchno || '(' || t3.name || ')' as mrchno,"
				+ "nvl2(t2.settle_mrch_acc_id,t4.accno || '(' || t4.acc_name || ')','') as settle_mrch_acc_id, t2.timezone as x_timezone,to_char(t2.add_date,'yyyy-MM-dd') as add_date, "
				+ "to_char(t2.disabled_date,'yyyy-MM-dd') as disabled_date,to_char(t2.enable_date,'yyyy-MM-dd') as enable_date from termpos t1, MERCHANT T3, termpos_x t2 left join mrch_acc_x t4 on t2.settle_mrch_acc_id=t4.id where t1.id=t2.id AND T3.ID=T1.MERCHANT_ID  ";
		sql = getSql(form,sql,param) + " order by t3.mrchno desc,t1.termno asc ";
		Vector vector = null;
		try {

			vector = DbExec.execQuery(sql, param);
			TerminalExportBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalExportBean(map);
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
	 * 通过商户ID获取对应的所有终端号
	 * @param merchantId	商户ID
	 * @return	
	 */
	public List<TerminalBean> getTerminalListByMerchantId(String merchantId){
		List<TerminalBean> beans = new ArrayList<TerminalBean>();
		String sql  ="select b.termcode, b.timezone, c.param_name as location from termpos a, termpos_x b ";
			sql=sql+" left join sys_parameter c on b.consump_category = c.param_value and c.param_type = 'CONSUMP_CATEGORY' ";
			sql=sql+" where a.termcode = b.termcode and a.merchant_id='"+merchantId+"' order by b.termcode desc";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, null);
			TerminalBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new TerminalBean(map);
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
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(TerminalForm form,String sql,List<String> param){
		if(form.getMrchnoQ()!=null&&form.getMrchnoQ().trim().length()>0){
			sql+=" and T2.MRCHNO= '"+form.getMrchnoQ().trim()+"' ";
		}
		if(form.getMrchNameQ()!=null&&form.getMrchNameQ().trim().length()>0){
			sql+=" and T3.NAME like '%"+form.getMrchNameQ().trim()+"%' ";
		}
		if(form.getTermcode()!=null&&form.getTermcode().trim().length()>0){
			sql+=" and T2.TERMCODE='"+form.getTermcode().trim()+"' ";
		}
		if (null != form.getAddDT_startdate()&&form.getAddDT_startdate().trim().length()>0) {
			sql += " and T2.ADD_DATE>= to_date('"+form.getAddDT_startdate().trim()+"','yyyy-MM-dd') ";
		}
		if (null != form.getAddDT_enddate()&&form.getAddDT_enddate().trim().length()>0) {
			sql += " and T2.ADD_DATE<= to_date('"+form.getAddDT_enddate().trim()+"','yyyy-MM-dd') ";
		}
		if(form.getState()!=null&&form.getState().trim().length()>0){
			sql+=" and T2.STATE= '"+form.getState().trim()+"' ";
		}
		if(form.getX_location()!=null&&form.getX_location().trim().length()>0){
			sql+=" and T2.LOCATION like '%"+form.getX_location().trim()+"%' ";
		}
		if (null != form.getProvince() &&form.getProvince().trim().length()>0) {
			sql += " and T2.PROVINCE = '"+form.getProvince().trim()+"' ";
		}
		if (null != form.getCity_no()&&form.getCity_no().trim().length()>0) {
			sql += " and T2.CITY_NO = '"+form.getCity_no().trim()+"' ";
		}
		if (null != form.getZone() &&form.getZone().trim().length()>0) {
			sql += " and T2.ZONE = '"+form.getZone().trim()+"' ";
		}
		if (null != form.getTerm_stat() &&form.getTerm_stat().trim().length()>0) {
			sql += " and T2.TERM_STAT = '"+form.getTerm_stat().trim()+"' ";
		}
		if(null!=form.getAccIsNull()&&"1".equals(form.getAccIsNull())){
			sql += " and t2.settle_mrch_acc_id is not null ";
		}
		if(null!=form.getAccIsNull()&&"2".equals(form.getAccIsNull())){
			sql += " and t2.settle_mrch_acc_id is null ";
		}	
		if (null != form.getTimeZoneQ() &&form.getTimeZoneQ().trim().length()>0) {
			sql += " and t2.timezone = '"+form.getTimeZoneQ().trim()+"' ";
		}
		if (null != form.getYard_mer_type()&& !"".equals(form.getYard_mer_type())&&form.getYard_mer_type().trim().length()>0) {
			sql += " and t0.mer_type = '"+form.getYard_mer_type().trim()+"' ";
		}
		return sql;
	}
	/**
	 * 通过商户编号或商户ID获取商户，都不输入的话获取所有的商户
	 * @param mrchNo 商户编号
	 * @param mrchId 商户ID
	 * @return
	 */
	public List<MrchListBean> getMrchListBeanList(String mrchNo,String mrchId,String flg,String mustActive){
		List<MrchListBean> mrchList = new ArrayList<MrchListBean>();
		String sql = "select id,mrchno||'('||name||')' as name,mrchno,inst_id from merchant where 1=1 ";
		if(mustActive!=null&&"yes".equals(mustActive)){
			sql=sql+" and mrchstat=0  ";
		}
		//获取不同结算类型的商户
		//结算商户---0            交易商户---1    正常商户---2
        if(flg!=null&&flg.trim().length()>0){
        	sql =sql +" and mrchno IN (SELECT a.mrchno FROM MRCH_CLASS a WHERE a.classify<>'"+flg.trim()+"') ";
        }
		if(mrchNo!=null&&!"".equals(mrchNo)){
			sql=sql+" and mrchno='"+mrchNo+"' ";
		}
		if(mrchId!=null&&!"".equals(mrchId)){
			sql=sql+" and id='"+mrchId+"' ";
		}
		sql=sql+" order by mrchno ";
		try {
			Vector vector = DbExec.execQuery(sql);
			MrchListBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new MrchListBean(map);
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
	 * 根据商户号设置该商户号下所有终端的状态为“停用”
	 * @param mrchNo
	 * @return
	 */
	public int updateTermStatByMrchNo(String mrchNo,String mrchId) {
		if(mrchNo!=null&&mrchNo.trim().length()>0){
			List sqlList = new ArrayList();	// 用于存放对应的sql语句
			sqlList.add(" UPDATE TERMPOS_X T SET T.TERM_STAT='1' WHERE T.MRCHNO= '"+mrchNo+"' ");
			sqlList.add(" UPDATE TERMPOS T SET T.statusid=1 WHERE T.MERCHANT_ID= '"+mrchId+"' ");
			try {
				result = DbExec.execBatchDouble(sqlList, null,null,null);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQLException: updTermStatByMrchNo", e);
				result = -1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception: updTermStatByMrchNo", e);
				result = -1;
			}
		}else{
			result = -1;
		}
		return result;
	}
	
	public List<MrchAccxBean> getMrchAccxBeanList(String mrchno){
		List<MrchAccxBean> mcaccxList = new ArrayList<MrchAccxBean>();
		
		String sql = "select id,'('||id||')'||acc_name||'('||accno||')' as name, inst_id,mrchno from mrch_acc_x where mrchno=? order by mrchno";
		/*String sql = "select id,acc_name||'('||accno||')' as name, inst_id,mrchno from mrch_acc_x where mrchno=? order by mrchno";*/
		List params = new ArrayList();
		params.add(mrchno);
		try {
			Vector vector = DbExec.execQuery(sql,params);
			MrchAccxBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new MrchAccxBean(map);
					mcaccxList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getMrchAccxBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getMrchAccxBeanList", e);
		}
		return mcaccxList;
	}
	/**
	 * 通过商户账号编码获取商户账号
	 * @param accId 
	 * @return
	 */
	public List<MrchAccxBean> getMrchAccxBeanListbyAccId(String accId,String mrchNo){
		List<MrchAccxBean> mcaccxList = new ArrayList<MrchAccxBean>();
		List params = new ArrayList();
		String sql = "select id,'('||id||')'||acc_name||'('||accno||')' as name, inst_id,mrchno from mrch_acc_x ";
		if(accId!=null&&accId.trim().length()>0){
			 sql = "select id,'('||id||')'||acc_name||'('||accno||')' as name, inst_id,mrchno from mrch_acc_x where id=? ";
		}		
		if(accId!=null&&accId.trim().length()>0){
			params.add(accId);
		}
		if((accId==null||"".equals(accId))&&mrchNo!=null&&mrchNo.trim().length()>0){
			 sql = "select id,'('||id||')'||acc_name||'('||accno||')' as name, inst_id,mrchno from mrch_acc_x where mrchno=? ";
		}
		if((accId==null||"".equals(accId))&&mrchNo!=null&&mrchNo.trim().length()>0){
			params.add(mrchNo);
		}
		try {
			Vector vector = DbExec.execQuery(sql,params);
			MrchAccxBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new MrchAccxBean(map);
					mcaccxList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getMrchAccxBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getMrchAccxBeanList", e);
		}
		return mcaccxList;
	}
	/**
	 * 查询posdef表信息
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<PosdefBean> getPosdefBeanList() {
		List<PosdefBean> posdefList = new ArrayList<PosdefBean>();
		String sql = "select typeid,typeid||'-'||vendor as vendor, termtype from posdef order by typeid";
		try {
			Vector vector = DbExec.execQuery(sql);
			PosdefBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new PosdefBean(map);
					posdefList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getPosdefBeanList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getPosdefBeanList", e);
		}
		return posdefList;
	}
    /**
     * 通过商户号获取终端序列号最大值加1
     * @param mrchno 
     * @return
     */
	public String getCurrentTermNoByMrchId(String mrchId) {
		String maxTermNo="0";
		List<Termposbean> mrchList = new ArrayList<Termposbean>();
		String sql = "SELECT nvl(max(a.termno),'0') as termno FROM TERMPOS a WHERE a.merchant_id='"+mrchId.trim()+"' ";
		try {
			Vector vector = DbExec.execQuery(sql);
			Termposbean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new Termposbean(map);
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
		if(mrchList!=null&&mrchList.size()>0){
			maxTermNo=(Integer.valueOf(mrchList.get(0).getTermno()) +1)+"";
		}
		return maxTermNo;
	}
	
	
	public TerminalBean getTerminalByTermcode(String termcode){
		sql = "select t1.*,t2.*,t3.*  from termpos t1,termpos_x t2,enckey t3 where t1.termcode = t2.termcode and t3.refcode='POS'||t1.termcode and t1.termcode = ? ";
		List param = new ArrayList();
		param.add(termcode);
		TerminalBean terminalBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				terminalBean = new TerminalBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return terminalBean;
	}
	
	public int checkTermCode(String termcode) {
		int count = 0;
		if(termcode!=null&&termcode.trim().length()>0){
			sql = "select count(id) as numbers from termpos where termcode = ? ";
			List param = new ArrayList();
			param.add(termcode.trim());
			try {
				Vector vector = DbExec.execQuery(sql,param);
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
	
	public int checkTermno(String merchant_id, String termno) {
		int count = 0;
		if(merchant_id!=null&&merchant_id.trim().length()>0&&termno!=null&&termno.trim().length()>0){
			sql = "select count(a.id) as numbers from termpos a,merchant b where a.termno = ? AND a.merchant_id=b.id AND b.mrchno=?";
			List param = new ArrayList();
			param.add(termno.trim());
			param.add(merchant_id.trim());
			try {
				Vector vector = DbExec.execQuery(sql,param);
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
	
	public TerminalBean getTerminalByID(String id){
		
		String sql  ="select "
				+ " t1.id,t1.typeid,t1.termcode,t1.testflag,t1.statusid,t1.currcode,t1.termno,t1.location,t1.conaccno,t1.concur,t1.poschic,t1.poschac,t1.poscrc,t1.posoe,t1.poscdoc,t1.postoc,t1.pospcc,t1.timezone,t1.cat_params,t1.merchant_id,t1.termtype,"
				+ " t2.id as termpos_id,t2.pos_tel,t2.batch_no,t2.add_date,t2.location as x_location,t2.state,t2.city_no,t2.province,t2.zone,t2.settle_mrch_acc_id,t2.termcode as x_termcode,t2.timezone as x_timezone,t2.inst_id,t2.mrchno,t2.term_stat, "
				+ " t3.keytype,t3.keystatus,t3.tstflag,t3.refcode,t3.keyseqno,t3.keyvalue,t3.prevalue,t3.checkvalue,t3.actdate,t3.acttime,t3.expiry,t3.longkeyval,t3.id as enckey_id,t2.consump_category,t2.ENABLE_DATE,t2.DISABLED_DATE "
				+ " from termpos t1, termpos_x t2, enckey t3"
				+ " where t1.id = t2.id and t3.refcode='POS'||t1.termcode and t1.id = ? ";
		List param = new ArrayList();
		param.add(id);
		TerminalBean terminalBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				terminalBean = new TerminalBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return terminalBean;
	}
	
	/**
	 * 新增终端时，从序列termpos_sequence中获取id
	 * @return
	 */
	public String getTerminalid() {
		sql = "select termpos_sequence.nextval as id from dual";
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
			logger.error("SQLException--getTerminalid", e);
		} catch (Exception e) {
			logger.error("Exception--getTerminalid", e);
		}
		return strret;
	}
	
	/**
	 * 新增终端时，从序列enckey_sequence中获取id
	 * @return
	 */
	public String getEnckeylid() {
		sql = "select enckey_sequence.nextval as id from dual";
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
			logger.error("SQLException--getEnckeylid", e);
		} catch (Exception e) {
			logger.error("Exception--getEnckeylid", e);
		}
		return strret;
	}
	
	/**
	 * 终端信息添加
	 * @param merchantBean
	 * @param mrchmtdbean
	 * @param mrchaccbean
	 * @param merchant_xBean
	 * @param mrch_acc_xBean
	 * @return
	 */
	public int addTerminalInfo(Termposbean termposBean,Termpos_xBean termpos_xBean,Enckeybean enckeybean,WankeMerBookBean wanke_MerBookBean){
		List sqlList = new ArrayList();	// 用于存放对应的sql语句
		List paramsList = new ArrayList(); //用于存放对应sql的params的List值
		addtermposInfo(termposBean, sqlList, paramsList);
		termpos_xBean.setId(termposBean.getId());
		enckeybean.setRefcode("POS"+termposBean.getTermcode());
		addenckeyInfo(enckeybean, sqlList, paramsList);
		addTermpos_xInfo(termpos_xBean, sqlList, paramsList);
		//添加更新万科商户表信息
		String flag="add";
		if(wanke_MerBookBean.getYard_mer_type()!=null&&"0".equals(wanke_MerBookBean.getYard_mer_type())){
				// 判断商户存在万科商户表
				List<WankeMerBookBean> wankeList = findWanKeTer(wanke_MerBookBean.getYard_mer_no());
	    	        if(wankeList!=null){
	    	        	if(wankeList.size()>0){
	    	        		if(null!=wankeList.get(wankeList.size()-1).getYard_ter_no()&&!"".equals(wankeList.get(wankeList.size()-1).getYard_ter_no())){
	    	        			addWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList);
	    	        		}else{
	    	        			updAddWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList,flag);
	    	        		}
	    	            }
	    	        }
		}		
		
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList, null, null);
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
	/**
	 * 获取待修改的万科商户信息
	 * 
	 * @param id
	 * @return
	 */
	public List<WankeMerBookBean> findWanKeTer(String mer_no) {
		List<WankeMerBookBean> beans = new ArrayList<WankeMerBookBean>();
		sql = "select t.id as yard_id,t.mer_no as yard_mer_no,t.mer_name as yard_mer_name,t.mer_type as yard_mer_type,t.mer_type_name as yard_mer_type_name,"
				+"t.ter_no as yard_ter_no,t.scene_name as yard_scene_name,t.scene_id as yard_scene_id"
			+" from t_wanke_mer_book t where t.mer_no='"
			+mer_no+"' order by t.id desc";

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
            logger.error("SQLException--findWanKeTer", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--findWanKeTer", e);
		}
		return beans;
	}
	public void addtermposInfo(Termposbean termposbean,List sqlList,List paramsList){
		sql="insert into termpos (ID, TYPEID, TERMCODE, TESTFLAG, STATUSID, CURRCODE, TERMNO, LOCATION, CONACCNO, CONCUR, POSCHIC, POSCHAC, POSCRC, POSOE, POSCDOC, POSTOC, POSPCC, TIMEZONE, CAT_PARAMS, MERCHANT_ID, TERMTYPE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		//从序列中获取商户id
		String termposid = getTerminalid();
		termposbean.setId(termposid);
		params.add(termposid);
		params.add(termposbean.getTypeid());
		params.add(termposbean.getTermcode());
		params.add(termposbean.getTestflag());
		params.add(termposbean.getStatusid());
		params.add(termposbean.getCurrcode());
		params.add(termposbean.getTermno());
		params.add(termposbean.getLocation());
		params.add(termposbean.getConaccno());
		params.add(termposbean.getConcur());
		params.add(termposbean.getPoschic());
		params.add(termposbean.getPoschac());
		params.add(termposbean.getPoscrc());
		params.add(termposbean.getPosoe());
		params.add(termposbean.getPoscdoc());
		params.add(termposbean.getPostoc());
		params.add(termposbean.getPospcc());
		params.add(termposbean.getTimezone());
		params.add(termposbean.getCat_params());
		params.add(termposbean.getMerchant_id());
		params.add(termposbean.getTermtype());
		

		paramsList.add(params);
	}
	
	public void addenckeyInfo(Enckeybean enckeybean,List sqlList,List paramsList){
		sql="insert into ENCKEY (KEYTYPE, KEYSTATUS, TSTFLAG, REFCODE, KEYSEQNO, KEYVALUE, PREVALUE, CHECKVALUE, ACTDATE, ACTTIME, EXPIRY, LONGKEYVAL, ID) values(?,?,?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,to_date(?,'yyyy/mm/dd'),?,?)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		
		params.add(3);
		params.add(0);
		params.add(0);
		params.add(enckeybean.getRefcode());
		params.add(1);
		params.add("260ADEB6F102825B62D13FE21E56ED2D");
		params.add(" ");
		params.add("7A9EE0BA6D0350A0");
		params.add(enckeybean.getActdate());
		params.add(enckeybean.getActtime());
		params.add("2099/12/31");
		params.add(" ");
		//从序列中获取id
		params.add(getEnckeylid());
		paramsList.add(params);
	}
	
	public void addTermpos_xInfo( Termpos_xBean  termpos_xBean,List sqlList,List paramsList){
		sql="insert into TERMPOS_X (ID,POS_TEL,BATCH_NO, ADD_DATE, LOCATION,STATE, CITY_NO,PROVINCE, ZONE, SETTLE_MRCH_ACC_ID, ";
		sql=sql+" TERMCODE,TIMEZONE,MRCHNO,INST_ID,TERM_STAT,consump_category,DISABLED_DATE,ENABLE_DATE,UPDATE_X_DATE)";
		sql=sql+"  values(?,?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd'),to_date(?, 'yyyy-mm-dd'),sysdate)";
		sqlList.add(sql);
		ArrayList params = new ArrayList();
		
		params.add(termpos_xBean.getId());
		params.add(termpos_xBean.getPos_tel());
		params.add(termpos_xBean.getBatch_no());
		params.add(termpos_xBean.getAdd_date());
		params.add(termpos_xBean.getLocation());
		params.add(termpos_xBean.getState());
		params.add(termpos_xBean.getCity_no());
		params.add(termpos_xBean.getProvince());
		params.add(termpos_xBean.getZone());
		params.add(termpos_xBean.getSettle_mrch_acc_id());
		params.add(termpos_xBean.getTermcode());
		params.add(termpos_xBean.getX_timezone());
		params.add(termpos_xBean.getMrchno());
		params.add(termpos_xBean.getInst_id());
		params.add(termpos_xBean.getTerm_stat());
		params.add(termpos_xBean.getConsump_category());
		params.add(termpos_xBean.getDisabled_date());
		params.add(termpos_xBean.getEnable_date());
	
		paramsList.add(params);
	}
	
	
	/**
	 * 更新万科商户表信息
	 * 
	 * @param mrchaccbean
	 * @param sqlList
	 * @param paramsList
	 */
		public void updAddWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
				List paramsList,String flag) {
			
			ArrayList params = new ArrayList();
			if("add".equals(flag)){
				sql = " update t_wanke_mer_book set  " 
						+ " MER_TYPE=?,"
						+ " MER_TYPE_NAME=?,"
						+ " TER_NO=?,"
						+ " SCENE_ID=?,"
						+ " SCENE_NAME=?"
						+ " where MER_NO=? ";
				sqlList.add(sql);	
				params.add(wanke_MerBookBean.getYard_mer_type());
				params.add(wanke_MerBookBean.getYard_mer_type_name());
				params.add(wanke_MerBookBean.getYard_ter_no());
				params.add(wanke_MerBookBean.getYard_scene_id());
				params.add(wanke_MerBookBean.getYard_scene_name());
				params.add(wanke_MerBookBean.getYard_mer_no());
			}
			
			paramsList.add(params);
		}
		
		/**
		 * 更新万科商户表信息
		 * 
		 * @param mrchaccbean
		 * @param sqlList
		 * @param paramsList
		 */
			public void modUpdateWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
					List paramsList,String flag) {
				
				ArrayList params = new ArrayList();
			
				if("mod".equals(flag)){
					sql = " update t_wanke_mer_book set  " 
							+ " SCENE_ID=?,"
							+ " SCENE_NAME=?"
							+ " where TER_NO=? ";
					sqlList.add(sql);	
					params.add(wanke_MerBookBean.getYard_scene_id());
					params.add(wanke_MerBookBean.getYard_scene_name());
					params.add(wanke_MerBookBean.getYard_ter_no());
				}
				
				if("del".equals(flag)){
					sql = " delete from t_wanke_mer_book   " 
							+ " where ID=? ";
					sqlList.add(sql);	
					params.add(wanke_MerBookBean.getYard_id());
				}
				

				paramsList.add(params);
			}
		/**
		 * 添加万科商户表信息
		 * 
		 * @param mrchaccbean
		 * @param sqlList
		 * @param paramsList
		 */
		public void addWankeMerBookBean(WankeMerBookBean wanke_MerBookBean, List sqlList,
				List paramsList) {		
			sql="insert into t_wanke_mer_book (ID, MER_NO, MER_NAME, MER_TYPE, MER_TYPE_NAME, TER_NO, SCENE_ID, SCENE_NAME) "
					+ "values(?,?,?,?,?,?,?,?)";
				
			sqlList.add(sql);
			ArrayList params = new ArrayList();
			params.add(getYardId());
			params.add(wanke_MerBookBean.getYard_mer_no());
			params.add(wanke_MerBookBean.getYard_mer_name());
			params.add(wanke_MerBookBean.getYard_mer_type());

			params.add(wanke_MerBookBean.getYard_mer_type_name());
			
			params.add(wanke_MerBookBean.getYard_ter_no());
			params.add(wanke_MerBookBean.getYard_scene_id());
			params.add(wanke_MerBookBean.getYard_scene_name());
			
			
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

		
	public int updTerminalInfo(Termposbean termposBean,Termpos_xBean termpos_xBean,Enckeybean enckeybean,WankeMerBookBean wanke_MerBookBean) {
		List sqlList = new ArrayList();	// 用于存放对应的sql语句
		List paramsList = new ArrayList(); //用于存放对应sql的params的List值
		
		String termpos_id = termposBean.getId();	
		enckeybean.setRefcode("POS"+termposBean.getTermcode());
		
		updTermposbean(termposBean, sqlList, paramsList);
		
		termpos_xBean.setId(termpos_id);
		updTermposxBean(termpos_xBean, sqlList, paramsList);
		
		updEnckeybean(enckeybean, sqlList, paramsList);
		
		//修改万科商户表信息
		String flag="";
			if("0".equals(wanke_MerBookBean.getYard_mer_type())){
				flag="mod";
				modUpdateWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList,flag);
			}else{
				flag="del";
				modUpdateWankeMerBookBean(wanke_MerBookBean, sqlList, paramsList,flag);
			}
		
		try {
			result = DbExec.execBatchDouble(sqlList, paramsList,null,null);
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
	
	/**
	 * 获取待修改的万科商户信息
	 * 
	 * @param id
	 * @return
	 */
	public List<WankeMerBookBean> getTermYardId(WankeMerBookBean beans ) {
		List<WankeMerBookBean> beansId = new ArrayList<WankeMerBookBean>();
		sql = "select t.id as yard_id,t.mer_no as yard_mer_no,t.mer_name as yard_mer_name,t.mer_type as yard_mer_type,t.mer_type_name as yard_mer_type_name,"
				+"t.ter_no as yard_ter_no,t.scene_name as yard_scene_name,t.scene_id as yard_scene_id"
			+" from t_wanke_mer_book t where t.mer_no='"
			+beans.getYard_mer_no()+"' "
					+ "and t.ter_no='"
					+beans.getYard_ter_no()+"' "
					+ "order by t.id desc";

		Vector vector = null;
		try {

			vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					WankeMerBookBean bean = new WankeMerBookBean((HashMap) vector.get(i));
					beansId.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getYardId", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getYardId", e);
		}
		return beansId;
	}
	
	public void updTermposbean(Termposbean termposbean,List sqlList,List paramsList) {
		
		String addSql =  "";
		if(termposbean.getMerchant_id()==null){
			addSql =  " update termpos set"
				+" TYPEID=?,     "
				+" TERMCODE=?,   "
				+" TESTFLAG=?,   "
				+" STATUSID=?,   "
				+" CURRCODE=?,   "
				+" TERMNO=?,     "
				+" LOCATION=?,   "
				+" CONACCNO=?,   "
				+" CONCUR=?,     "
				+" POSCHIC=?,    "
				+" POSCHAC=?,    "
				+" POSCRC=?,     "
				+" POSOE=?,      "
				+" POSCDOC=?,    "
				+" POSTOC=?,     "
				+" POSPCC=?,     "
				+" TIMEZONE=?,   "
				+" CAT_PARAMS=?, "
				+" TERMTYPE=?    "
				+" where id=? ";
		}else{
			addSql =  " update termpos set"
				+" TYPEID=?,     "
				+" TERMCODE=?,   "
				+" TESTFLAG=?,   "
				+" STATUSID=?,   "
				+" CURRCODE=?,   "
				+" TERMNO=?,     "
				+" LOCATION=?,   "
				+" CONACCNO=?,   "
				+" CONCUR=?,     "
				+" POSCHIC=?,    "
				+" POSCHAC=?,    "
				+" POSCRC=?,     "
				+" POSOE=?,      "
				+" POSCDOC=?,    "
				+" POSTOC=?,     "
				+" POSPCC=?,     "
				+" TIMEZONE=?,   "
				+" CAT_PARAMS=?, "
				+" MERCHANT_ID=?,"
				+" TERMTYPE=?    "
				+" where id=? ";
		}
		
		//将添加的sql语句存放于保存sql语句的sqlList中
		sqlList.add(addSql);
		
		ArrayList params = new ArrayList();
		params.add(termposbean.getTypeid());
		params.add(termposbean.getTermcode());
		params.add(termposbean.getTestflag());
		params.add(termposbean.getStatusid());
		params.add(termposbean.getCurrcode());
		params.add(termposbean.getTermno());
		params.add(termposbean.getLocation());
		params.add(termposbean.getConaccno());
		params.add(termposbean.getConcur());
		params.add(termposbean.getPoschic());
		params.add(termposbean.getPoschac());
		params.add(termposbean.getPoscrc());
		params.add(termposbean.getPosoe());
		params.add(termposbean.getPoscdoc());
		params.add(termposbean.getPostoc());
		params.add(termposbean.getPospcc());
		params.add(termposbean.getTimezone());
		params.add(termposbean.getCat_params());
		if(termposbean.getMerchant_id()!=null){
			params.add(termposbean.getMerchant_id());
		}
		params.add(termposbean.getTermtype());
		params.add(termposbean.getId());

		paramsList.add(params);
	}
	
	public void updTermposxBean(Termpos_xBean termpos_xBean,List sqlList,List paramsList) {
		
		String addSql =  "";
		String mrchno=termpos_xBean.getMrchno();
		if(mrchno!=null&&mrchno.trim().length()>0){
			addSql =  " update termpos_x set     "
				+ " POS_TEL=?,                       "
				+ " BATCH_NO=?,                      "
				+ " ADD_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " LOCATION=?,                      "
				+ " STATE=?,                         "
				+ " CITY_NO=?,                       "
				+ " PROVINCE=?,                      "
				+ " ZONE=?,                          "
				+ " SETTLE_MRCH_ACC_ID=?,            "
				+ " TERMCODE=?,                      "
				+ " TIMEZONE=?,                      "
				+ " INST_ID=?,                       "
				+ " MRCHNO=?,                        "
				+ " TERM_STAT=?,                     "
				+ " consump_category=?,  	         "
				+ " DISABLED_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " ENABLE_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " UPDATE_X_DATE=sysdate"
				+ " where id=? ";
		}else{
			addSql =  " update termpos_x set     "
				+ " POS_TEL=?,                       "
				+ " BATCH_NO=?,                      "
				+ " ADD_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " LOCATION=?,                      "
				+ " STATE=?,                         "
				+ " CITY_NO=?,                       "
				+ " PROVINCE=?,                      "
				+ " ZONE=?,                          "
				+ " SETTLE_MRCH_ACC_ID=?,            "
				+ " TERMCODE=?,                      "
				+ " TIMEZONE=?,                      "
				+ " TERM_STAT=?,                     "
				+ " consump_category=?,  	         "
				+ " DISABLED_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " ENABLE_DATE=to_date(?,'yyyy-mm-dd'),"
				+ " UPDATE_X_DATE=sysdate"
				+ " where id=? ";
		}
		//将添加的sql语句存放于保存sql语句的sqlList中
		sqlList.add(addSql);
		
		ArrayList params = new ArrayList();
		
		params.add(termpos_xBean.getPos_tel());
		params.add(termpos_xBean.getBatch_no());
		params.add(termpos_xBean.getAdd_date());
		params.add(termpos_xBean.getLocation());
		params.add(termpos_xBean.getState());
		params.add(termpos_xBean.getCity_no());
		params.add(termpos_xBean.getProvince());
		params.add(termpos_xBean.getZone());
		params.add(termpos_xBean.getSettle_mrch_acc_id());
		params.add(termpos_xBean.getTermcode());
		params.add(termpos_xBean.getX_timezone());
		if(mrchno!=null&&mrchno.trim().length()>0){
			params.add(termpos_xBean.getInst_id());
			params.add(termpos_xBean.getMrchno());
		}
		params.add(termpos_xBean.getTerm_stat());
		params.add(termpos_xBean.getConsump_category());		
		params.add(termpos_xBean.getDisabled_date());		
		params.add(termpos_xBean.getEnable_date());		
		
		params.add(termpos_xBean.getId());

		paramsList.add(params);
	}
	
	public void updEnckeybean(Enckeybean enckeybean,List sqlList,List paramsList) {
		
		String addSql =  " update enckey set"
				+" KEYTYPE=?,   "
				+" KEYSTATUS=?, "
				+" TSTFLAG=?,   "
				+" REFCODE=?,   "
				+" KEYSEQNO=?,  "
				+" KEYVALUE=?,  "
				+" PREVALUE=?,  "
				+" CHECKVALUE=?,"
				+" ACTDATE=to_date(?, 'yyyy-mm-dd'),   "
				+" ACTTIME=?,   "
				+" EXPIRY=to_date(?, 'yyyy/mm/dd'),    "
				+" LONGKEYVAL=? "
				+" where id=? ";
		
		//将添加的sql语句存放于保存sql语句的sqlList中
		sqlList.add(addSql);
		
		ArrayList params = new ArrayList();
		
		params.add(3);
		params.add(0);
		params.add(0);
		params.add(enckeybean.getRefcode());
		params.add(1);
		params.add("260ADEB6F102825B62D13FE21E56ED2D");
		params.add(" ");
		params.add("7A9EE0BA6D0350A0");
		params.add(enckeybean.getActdate());
		params.add(enckeybean.getActtime());
		params.add("2099/12/31");
		params.add(" ");
		params.add(enckeybean.getId());

		paramsList.add(params);
	}
	
	/**
	 * 批量修改商户对应终端的费率和消费场景
	 * @param mrchNo   商户号
	 * @param merchant_id	商户ID
	 * @param timeZone			费率
	 * @param consump_category		消费场景
	 * @return	0.成功；-1数据库执行SQL异常；-2参数异常。
	 */
	public int updTerminalInfo(String mrchNo, String merchant_id,
			String timeZone, String consump_category,String term_stat,String disabled_date,String enable_date, String terminalListStr) {
		List sqlList = new ArrayList(); // 用于存放对应的sql语句
		if (mrchNo != null && !"".equals(mrchNo) && merchant_id != null
				&& !"".equals(merchant_id)&&terminalListStr!=null&&!"".equals(terminalListStr)) {
			String terminalInStr=terminalListStr;
			if(terminalListStr.contains(",")){
				terminalInStr=terminalListStr.replaceAll(",", "','");
			}
			String sql1="update TERMPOS_X t set t.mrchno='"+mrchNo+"' ";
			String sql2="update TERMPOS t set t.merchant_id='"+merchant_id+"' ";
			if(timeZone!=null&&!"".equals(timeZone)){
				sql1=sql1+",t.timezone='" + timeZone+"' ";
				sql2=sql2+",t.timezone='"+timeZone+"' ";				
			}
			if(consump_category!=null&&!"".equals(consump_category)){
				sql1=sql1+",t.consump_category='" + consump_category+"' ";
			}
			if(term_stat!=null&&!"".equals(term_stat)){
				sql1=sql1+",t.term_stat='" + term_stat+"' ";
				sql2=sql2+",t.statusid='"+term_stat+"' ";
			}
			if(disabled_date!=null&&!"".equals(disabled_date)){
				sql1=sql1+",t.disabled_date=to_date('" + disabled_date+"','YYYY-MM-dd') ";
			}
			if(enable_date!=null&&!"".equals(enable_date)){
				sql1=sql1+",t.enable_date=to_date('" + enable_date+"','YYYY-MM-dd') ";
			}
			sql1 = sql1+ " where t.mrchno='" + mrchNo + "' and t.TERMCODE in ('"+terminalInStr+"')";			
			sql2 = sql2+ " where t.merchant_id='"+merchant_id+"' and t.TERMCODE in ('"+terminalInStr+"')";
			sqlList.add(sql1);
			sqlList.add(sql2);	
			try {
				result = DbExec.execBatchDouble(sqlList, null, null, null);
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
		} else {
			return -2;
		}

	}
}	
