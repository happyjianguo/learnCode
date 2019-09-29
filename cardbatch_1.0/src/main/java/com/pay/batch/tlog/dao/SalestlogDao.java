package com.pay.batch.tlog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.tlog.bean.SalestlogBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class SalestlogDao {
	private static final Logger logger = Logger.getLogger(SalestlogDao.class);
	private String sql = "";

	public List<SalestlogBean> getSalestlogList(PageBean pageBean,
			SalestlogBean querybean, String txtype) {
		List<SalestlogBean> list = new ArrayList<SalestlogBean>();
		String txncode = txtype.substring(0, 2);
		sql = "select a.id,a.pan,a.amttxn,a.stxnstat,a.father_order,a.children_order,a.verifier,a.verifytime,b.txncode as txncode, b.sub_txncode as sub_txncode from salestlog a, tlog b where 1=1 and a.tlog_id = b.id ";
		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " and b.txncode in (" + txncode+",20) ";
			if("289796".equals(txtype)){
				sql += " and b.sub_txncode in ('" + txtype.substring(2, 4) + "','" + txtype.substring(4) + "','92')";
			}else{
				sql += " and b.sub_txncode in ('" + txtype.substring(2, 4) + "','" + txtype.substring(4) + "')";
			}
			sql += " order by a.id desc";
			vector = DbExec.execQuery(sql, pageBean.getStart(),
					pageBean.getPageSize());
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					SalestlogBean bean = new SalestlogBean((HashMap) vector.get(i));
					if(bean.getId()!=null&&!"".equals(bean.getId())){
						list.add(bean);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
	
	public SalestlogBean getSalestlogByID(String id){		
		sql = "select a.id,a.tlog_id,a.org_tlog_id,a.pan,a.amttxn,a.stxnstat,a.father_order,a.children_order,a.org_f_order,a.org_c_order,a.verifier,a.verifytime,c.descr as crdproduct, a.currbill, a.amttxn_jy, a.rateset, a.curtxn, b.txncode as txncode,b.sub_txncode as sub_txncode "
				+ " from salestlog a, tlog b left join crdproduct c on b.crdproduct=c.crdproduct where 1=1 "
				+ "and a.tlog_id = b.id and a.id=? ";
		List param = new ArrayList();
		param.add(id);
		SalestlogBean salestlogBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				salestlogBean = new SalestlogBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return salestlogBean;
	}
	
	public List<SalestlogBean> getSalestlogList(
			SalestlogBean querybean, String txtype) {
		List<SalestlogBean> list = new ArrayList<SalestlogBean>();
		String txncode = txtype.substring(0, 2);
		sql = "SELECT A.ID,DECODE(b.txncode||b.sub_txncode,'2899','实时开卡','2898','非实时开卡', '2099','实时退卡','2098','非实时退卡','2897','批量充值','2896','换卡充值', '2092','充值冲正','094','换卡赎回', '093','卡余额赎回') AS descr,A.PAN,A.AMTTXN,A.STXNSTAT,A.FATHER_ORDER,A.CHILDREN_ORDER,A.ORG_F_ORDER,A.ORG_C_ORDER,A.VERIFIER,A.VERIFYTIME,B.TXNCODE AS TXNCODE,B.SUB_TXNCODE    AS SUB_TXNCODE  FROM SALESTLOG A, TLOG B WHERE 1 = 1   AND A.TLOG_ID = B.ID ";
		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " and b.txncode in (" + txncode+",20) ";
			sql += " and b.sub_txncode in ('" + txtype.substring(2, 4) + "','" + txtype.substring(4) + "')";
			sql += " order by a.id desc";
			System.out.println(sql);
			vector = DbExec.execQuery(sql);
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					SalestlogBean bean = new SalestlogBean((HashMap) vector.get(i));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public int getCount(SalestlogBean querybean, String txtype) {
		int count = 0;
		sql = "select count(*) numbers from salestlog a, tlog b where 1=1 and a.tlog_id = b.id ";
		
		String txncode = txtype.substring(0, 2);
		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " and b.txncode in (" + txncode+",20) ";
			sql += " and b.sub_txncode in ('" + txtype.substring(2, 4) + "','" + txtype.substring(4) + "')";
			vector = DbExec.execQuery(sql);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public String sqlparm(SalestlogBean querybean) {
		StringBuffer sb = new StringBuffer("");
		if (querybean != null) {
			if (null != querybean.getPan() && !"".equals(querybean.getPan().trim())
					&& !"null".equals(querybean.getPan().trim())) {
				sb.append(" and a.pan ='").append(querybean.getPan().trim())
						.append("' ");
			}
			if (null != querybean.getFather_order() && !"".equals(querybean.getFather_order().trim())
					&& !"null".equals(querybean.getFather_order().trim())) {
				sb.append(" and a.father_order ='").append(querybean.getFather_order().trim())
						.append("' ");
			}
			if (null != querybean.getStxnstat() && !"".equals(querybean.getStxnstat().trim())
					&& !"null".equals(querybean.getStxnstat().trim())) {
				sb.append(" and a.stxnstat ='").append(querybean.getStxnstat().trim())
						.append("' ");
			}
			if (null != querybean.getVerifier() && !"".equals(querybean.getVerifier().trim())
					&& !"null".equals(querybean.getVerifier().trim())) {
				sb.append(" and a.verifier ='").append(querybean.getVerifier().trim())
						.append("' ");
			}
			if (null != querybean.getStarttime() && !"".equals(querybean.getStarttime().trim())
					&& !"null".equals(querybean.getStarttime().trim())) {
				sb.append(" and a.verifytime  >='").append(querybean.getStarttime().trim())
						.append("' ");
			}
			if (null != querybean.getEndtime() && !"".equals(querybean.getEndtime().trim())
					&& !"null".equals(querybean.getEndtime().trim())) {
				sb.append(" and a.verifytime  <='").append(querybean.getEndtime().trim())
						.append("' ");
			}
	   		if(null!=querybean.getCrdproduct()&&!"".equals(querybean.getCrdproduct())&&!"null".equals(querybean.getCrdproduct())){
				 sb.append(" and b.crdproduct ='").append(querybean.getCrdproduct().trim()).append("' ");
			}  
		}
		return sb.toString();
	}

}
