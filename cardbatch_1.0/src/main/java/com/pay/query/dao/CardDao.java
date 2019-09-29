package com.pay.query.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.query.bean.CardBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class CardDao {
	private static final Logger logger = Logger.getLogger(CardDao.class);
	private int result = 0;
	private String sql = "";

	/**
	 * 查询分页总条数
	 * 
	 * @param cardBean
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getCount(CardBean cardBean, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers FROM CRDDET T, CRDPRODUCT CP, CRDSTATUS CS, ACCDET AC, CUSTDET CU, CRDINFO CI,BRANCH B WHERE T.CRDPRODUCT_ID = CP.ID AND T.STATCODE = CS.STATCODE AND AC.ID = T.ACCDET_ID AND T.BRANCH_ID = B.ID AND CP.INST_ID = B.INST_ID  AND CU.ID = T.CUSTDET_ID AND CI.PAN(+) = T.PAN ";
		sql = getSql(cardBean, sql, param);
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

	public int getCount(CardBean cardBean, HttpSession session,String realCardOrNoRealCard) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql="";
		if(realCardOrNoRealCard!=null&&!"".equals(realCardOrNoRealCard)){
			//非实名卡
			if("0".equals(realCardOrNoRealCard)){
				sql = "select count(t.id) numbers from crddet t, crdproduct cp, crdstatus cs, accdet ac where t.crdproduct_id = cp.id and t.statcode = cs.statcode  and ac.id = t.accdet_id ";				
			}else{//实名卡
				sql = "select count(t.id) numbers from crddet t, crdproduct cp, crdstatus cs, accdet ac, crdinfo ci,custdet cu where t.crdproduct_id = cp.id and t.statcode = cs.statcode and ac.id = t.accdet_id and ci.pan(+) = t.pan and cu.id = t.custdet_id";								
			}
		}
		sql = getSql(cardBean, sql, param);
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

	public String getSumAmt(CardBean cardBean, HttpSession session,String realCardOrNoRealCard) {
		List<String> param = new ArrayList<String>();
		String count = "0";
		String sql="";
		if(realCardOrNoRealCard!=null&&!"".equals(realCardOrNoRealCard)){
			//非实名卡
			if("0".equals(realCardOrNoRealCard)){
				sql = "select sum(ac.avlbal) numbers from crddet t, crdproduct cp, crdstatus cs, accdet ac where t.crdproduct_id = cp.id and t.statcode = cs.statcode  and ac.id = t.accdet_id ";				
			}else{//实名卡
				sql = "select sum(ac.avlbal) numbers from crddet t, crdproduct cp, crdstatus cs, accdet ac, crdinfo ci,custdet cu where t.crdproduct_id = cp.id and t.statcode = cs.statcode and ac.id = t.accdet_id and ci.pan(+) = t.pan and cu.id = t.custdet_id";								
			}
		}
		sql = getSql(cardBean, sql, param);
		try {
			Vector vector = DbExec.execQuery(sql, param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = ((String) map.get("numbers")).trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getSumAmt", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQLException--getSumAmt", e);
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
	public List<CardBean> getCardList(PageBean pageBean, CardBean form) {
		List<String> param = new ArrayList<String>();
		List<CardBean> beans = new ArrayList<CardBean>();
		String sql = "SELECT T.BRANCH_ID, CRDPRODUCT_ID, CP.DESCR CRDPRODUCT_NAME, T.CLASSID, T.PAN, SEQNO, TO_CHAR(EXPDATE, 'yyyy-MM-dd') EXPDATE, TO_CHAR(EFFDATE, 'yyyy-MM-dd') EFFDATE, CYCLEN, TO_CHAR(CYCBEGIN, 'yyyy-MM-dd') CYCBEGIN, AMTAUTH, AMTREM, T.BLKAMT, T.STATCODE, CS.DESCR STATCODE_NAME, BATCH, CVV, LANG, CU.TITLE, CU.FIRSTNAME, CU.LASTNAME, RENEW, CORP, TO_CHAR(CU.DATE_BIRTH, 'yyyy-MM-dd') DATE_BIRTH, EMBOSSNAME, ADDITIONALNO, TO_CHAR(DATE_CREATED, 'yyyy-MM-dd') DATE_CREATED, T.ID, PAN_DISPLAY, T.ACCDET_ID, AC.AVLBAL, T.CUSTDET_ID, CU.TYPEID, CI.* FROM CRDDET T, CRDPRODUCT CP, CRDSTATUS CS, ACCDET AC, CUSTDET CU, CRDINFO CI,BRANCH B  WHERE T.CRDPRODUCT_ID = CP.ID AND T.STATCODE = CS.STATCODE AND AC.ID = T.ACCDET_ID AND T.BRANCH_ID = B.ID AND CP.INST_ID = B.INST_ID  AND CU.ID = T.CUSTDET_ID AND CI.PAN(+) = T.PAN ";
		sql = getSql(form, sql, param);
		sql = sql+" ORDER BY EFFDATE DESC ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			CardBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CardBean(map);
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

	public List<CardBean> getCardList(PageBean pageBean, CardBean form,String realCardOrNoRealCard) {
		List<String> param = new ArrayList<String>();
		List<CardBean> beans = new ArrayList<CardBean>();
		String sql="";
		if(realCardOrNoRealCard!=null&&!"".equals(realCardOrNoRealCard)){
			//非实名卡
			if("0".equals(realCardOrNoRealCard)){
				sql="select cp.descr crdproduct_name,t.pan,to_char(t.effdate, 'yyyy-MM-dd') effdate,cs.descr statcode_name,"
				+" t.batch,to_char(t.date_created, 'yyyy-MM-dd') date_created,t.id, t.accdet_id,t.pan_display,ac.avlbal "
				+" from crddet t, crdproduct cp, crdstatus cs, accdet ac where t.crdproduct_id = cp.id and t.statcode = cs.statcode  and ac.id = t.accdet_id ";				
			}else{//实名卡
				sql="select cp.descr crdproduct_name,t.pan,to_char(t.effdate, 'yyyy-MM-dd') effdate,cs.descr statcode_name,"
				+" t.batch,	t.id,t.pan_display,	t.accdet_id,ac.avlbal,t.custdet_id,ci.cust_name,ci.cell_phone "	
				+ " from crddet t, crdproduct cp, crdstatus cs, accdet ac, crdinfo ci,custdet cu where t.crdproduct_id = cp.id and t.statcode = cs.statcode and ac.id = t.accdet_id and ci.pan(+) = t.pan and cu.id = t.custdet_id";								
			}
		}		
		sql = getSql(form, sql, param);
		sql = sql+" ORDER BY EFFDATE DESC ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			CardBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new CardBean(map);
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
	 * @param cardBean
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(CardBean cardBean, String sql, List<String> param) {
		//String pan = cardBean.getPan();
		String pan_start = cardBean.getPan_start();
		String pan_end = cardBean.getPan_end();
		if (null != pan_start&&null != pan_end){
			if (pan_start.length()>0&&pan_end.length()==0) {			
				sql += " and t.pan >= ? ";				
				param.add(pan_start.trim());
			}else if(pan_start.length()==0&&pan_end.length()>0){
				sql += " and t.pan <= ? ";				
				param.add(pan_end.trim());
			}else if(pan_start.length()>0&&pan_end.length()>0){
				sql += " and t.pan >= ? ";
				sql += " and t.pan <= ? ";
				param.add(pan_start.trim());
				param.add(pan_end.trim());
			}else{	
			}
		}
		
		String crdproduct_id = cardBean.getCrdproduct_id();
		if (null != crdproduct_id && crdproduct_id.length() > 0) {
			sql += " and t.crdproduct_id = ? ";
			param.add(crdproduct_id.trim());
		}
		String statcode = cardBean.getStatcode();
		if (null != statcode && statcode.length() > 0) {
			sql += " and t.statcode = ? ";
			param.add(statcode.trim());
		}
		String custdet_id = cardBean.getCustdet_id();
		if (null != custdet_id && custdet_id.length() > 0) {
			sql += " and t.custdet_id = ? ";
			param.add(custdet_id.trim());
		}
		String batch = cardBean.getBatch();
		if (null != batch && batch.length() > 0) {
			sql += " and t.batch = ? ";
			param.add(batch.trim());
		}
		String effdate = cardBean.getEffdate();
		if (null != effdate && effdate.length() > 0) {
			sql += " and to_char(t.effdate,'yyyy-MM-dd') = ? ";
			param.add(effdate.trim());
		}
		
		String effdateStart = cardBean.getEffdateStart();
		if (null != effdateStart && effdateStart.length() > 0) {
			sql += " and to_char(t.effdate,'yyyy-MM-dd') >= ? ";
			param.add(effdateStart.trim());
		}		
		String effdateEnd = cardBean.getEffdateEnd();
		if (null != effdateEnd && effdateEnd.length() > 0) {
			sql += " and to_char(t.effdate,'yyyy-MM-dd') <= ? ";
			param.add(effdateEnd.trim());
		}		
		String id = cardBean.getId();
		if (null != id && id.length() > 0) {
			sql += " and t.id = ? ";
			param.add(id.trim());
		}
		String typeid = cardBean.getTypeid();
		if (null != typeid && typeid.length() > 0) {
			sql += " and cu.typeid = ? ";
			param.add(typeid.trim());
		}
		String cust_name = cardBean.getCust_name();
		if (null != cust_name && cust_name.length() > 0) {
			sql += " and cust_name like ? ";
			param.add("%"+cust_name.trim()+"%");
		}
		String id_num = cardBean.getId_num();
		if (null != id_num && id_num.length() > 0) {
			sql += " and id_num = ? ";
			param.add(id_num.trim());
		}
		String cell_phone = cardBean.getCell_phone();
		if (null != cell_phone && cell_phone.length() > 0) {
			sql += " and cell_phone = ? ";
			param.add(cell_phone.trim());
		}
		return sql;
	}
}
