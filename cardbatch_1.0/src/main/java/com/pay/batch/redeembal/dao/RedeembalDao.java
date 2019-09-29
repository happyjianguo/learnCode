package com.pay.batch.redeembal.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.redeembal.bean.RedeembalBean;
import com.pay.batch.redeembal.struts.form.RedeembalForm;
import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.dao.UserCrdproductDao;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class RedeembalDao {
	private static final Logger logger = Logger.getLogger(RedeembalDao.class);
	private String sql = "";
	private int result = 0;

	public List<RedeembalBean> getRedeembalList(PageBean pageBean,
			RedeembalForm querybean,String user_code) {
    	//获取用户对应卡产品str
    	String cpIn="";
    	String sqlStr="";
		if(user_code!=null&&!"".equals(user_code)){
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean= userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr=ucBean==null?"":ucBean.getCrdproduct();		
			if(!"".equals(cpStr)){
				if(cpStr.contains(",")){
					cpIn=cpStr.replaceAll(",", "','");
				}else{
					cpIn=cpStr;
				}
				sqlStr=" and b.crdproduct in ('"+cpIn+"')";	
			}
		}
		
		List<RedeembalBean> list = new ArrayList<RedeembalBean>();
		sql = "select a.verno_ctx, a.id, a.txtime, a.stan, a.pan, a.amt, a.father_order, a.children_order, a.acct_period, substr(a.sales_point,instr(a.sales_point,',')+1) as sales_point, a.area, a.id_type, a.id_number, a.cell_phone, a.phone, a.rb_type, a.bank_name, a.branch_name, a.bank_pan, a.card_acceptor_name, a.card_acceptor_id, a.interest, a.fee, a.ispaid, a.summary, substr(a.operator,instr(a.operator,',')+1) as operator, a.mrcht_id, a.batch_stat, a.descr, a.txncode, a.currbill, a.amttxn, a.rateset,a.crdproduct, a.curtxn,"
				+" to_number(substr(b.acctdata, 2+1,12))/100.00 as act1, "
				+" to_number(substr(b.acctdata, 2+15,12))/100.00 as act2, "
				+" to_number(substr(b.acctdata, 2+15+14,12))/100.00 as act3,"
				+" to_number(substr(b.acctdata, 2+15+14+14,12))/100.00 as act4,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14,12))/100.00 as act5,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14,12))/100.00 as act6,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14,12))/100.00 as act7,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14+14,12))/100.00 as act8,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14+14+14,12))/100.00 as act9"
				+" from redeembal a, salestlog b where a.father_order = b.father_order";
		sql=sql+sqlStr;
		
		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " order by a.id desc";
			vector = DbExec.execQuery(sql, pageBean.getStart(),
					pageBean.getPageSize());

			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					RedeembalBean bean = new RedeembalBean(
							(HashMap) vector.get(i));
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

	public List<RedeembalBean> getRedeembalList(RedeembalForm querybean,String user_code) {

		List<RedeembalBean> list = new ArrayList<RedeembalBean>();
    	//获取用户对应卡产品str
    	String cpIn="";
    	String sqlStr="";
		if(user_code!=null&&!"".equals(user_code)){
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean= userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr=ucBean==null?"":ucBean.getCrdproduct();		
			if(!"".equals(cpStr)){
				if(cpStr.contains(",")){
					cpIn=cpStr.replaceAll(",", "','");
				}else{
					cpIn=cpStr;
				}
				sqlStr=" and b.crdproduct in ('"+cpIn+"')";	
			}
		}		
		sql = "select a.*,"
				+" to_number(substr(b.acctdata, 2+1,12))/100.00 as act1, "
				+" to_number(substr(b.acctdata, 2+15,12))/100.00 as act2, "
				+" to_number(substr(b.acctdata, 2+15+14,12))/100.00 as act3,"
				+" to_number(substr(b.acctdata, 2+15+14+14,12))/100.00 as act4,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14,12))/100.00 as act5,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14,12))/100.00 as act6,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14,12))/100.00 as act7,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14+14,12))/100.00 as act8,"
				+" to_number(substr(b.acctdata, 2+15+14+14+14+14+14+14+14,12))/100.00 as act9"
				+" from redeembal a, salestlog b where a.father_order = b.father_order";
		sql=sql+sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " order by a.id desc";
			vector = DbExec.execQuery(sql);

			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					RedeembalBean bean = new RedeembalBean(
							(HashMap) vector.get(i));
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

	public int getCount(RedeembalForm querybean,String user_code) {
    	//获取用户对应卡产品str
    	String cpIn="";
    	String sqlStr="";
		if(user_code!=null&&!"".equals(user_code)){
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean= userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr=ucBean==null?"":ucBean.getCrdproduct();		
			if(!"".equals(cpStr)){
				if(cpStr.contains(",")){
					cpIn=cpStr.replaceAll(",", "','");
				}else{
					cpIn=cpStr;
				}
				sqlStr=" and crdproduct in ('"+cpIn+"')";	
			}
		}
		int count = 0;
		sql = "select count(*) numbers from redeembal a where 1=1 ";
		sql=sql+sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
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

	public String getSumAmt(RedeembalForm querybean,String user_code) {
    	//获取用户对应卡产品str
    	String cpIn="";
    	String sqlStr="";
		if(user_code!=null&&!"".equals(user_code)){
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean= userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr=ucBean==null?"":ucBean.getCrdproduct();		
			if(!"".equals(cpStr)){
				if(cpStr.contains(",")){
					cpIn=cpStr.replaceAll(",", "','");
				}else{
					cpIn=cpStr;
				}
				sqlStr=" and crdproduct in ('"+cpIn+"')";	
			}
		}
		String count = "0";
		sql = "select sum(amt) as amt_each_crd  from redeembal a where 1=1 ";
		sql=sql+sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			vector = DbExec.execQuery(sql);

			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = (String) map.get("amt_each_crd");
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	
	public String sqlparm(RedeembalForm querybean) {
		StringBuffer sb = new StringBuffer("");
		if (querybean != null) {
			if (null != querybean.getPan() && !"".equals(querybean.getPan())
					&& !"null".equals(querybean.getPan())) {
	   			 String pan=querybean.getPan().trim();
				 if(pan.length()>15){
					 pan=pan.substring(0, 15);
				 }				
				sb.append(" and a.pan ='").append(pan).append("' ");
			}
			if (null != querybean.getBatch_stat()
					&& !"".equals(querybean.getBatch_stat())
					&& !"null".equals(querybean.getBatch_stat())) {
				sb.append(" and a.batch_stat ='")
						.append(querybean.getBatch_stat().trim()).append("' ");
			}
			if (null != querybean.getFather_order()
					&& !"".equals(querybean.getFather_order())
					&& !"null".equals(querybean.getFather_order())) {
				sb.append(" and a.father_order ='")
						.append(querybean.getFather_order().trim())
						.append("' ");
			}
			if (null != querybean.getSales_point()
					&& !"".equals(querybean.getSales_point())
					&& !"null".equals(querybean.getSales_point())) {
				sb.append(" and a.sales_point like '%")
						.append(querybean.getSales_point().trim())
						.append("%' ");
			}
			if (null != querybean.getStarttime()
					&& !"".equals(querybean.getStarttime())
					&& !"null".equals(querybean.getStarttime())) {
				sb.append(" and a.txtime >='")
						.append(querybean.getStarttime().trim()).append("' ");
			}
			if (null != querybean.getEndtime()
					&& !"".equals(querybean.getEndtime())
					&& !"null".equals(querybean.getEndtime())) {
				sb.append(" and a.txtime <='")
						.append(querybean.getEndtime().trim()).append("' ");
			}
			
			//审批日期
			if(null!=querybean.getDescrStartDate()
   				 	&&!"".equals(querybean.getDescrStartDate())
   				 	&&!"null".equals(querybean.getDescrStartDate())){
				sb.append(" and REGEXP_SUBSTR(a.descr,'[^,]+',1,2,'i') >='")
   			 		.append(querybean.getDescrStartDate().trim()).append("' ");
			}
   		 	if(null!=querybean.getDescrEndDate()
   		 			&&!"".equals(querybean.getDescrEndDate())
   		 			&&!"null".equals(querybean.getDescrEndDate())){
   		 		sb.append(" and REGEXP_SUBSTR(a.descr,'[^,]+',1,2,'i') <='")
   		 			.append(querybean.getDescrEndDate().trim()).append("' ");
   		 	}
			
			if (null != querybean.getStart_period()
					&& !"".equals(querybean.getStart_period())
					&& !"null".equals(querybean.getStart_period())) {
				sb.append(" and a.Acct_period >=")
						.append(querybean.getStart_period().trim()).append(" ");
			}
			if (null != querybean.getEnd_period()
					&& !"".equals(querybean.getEnd_period())
					&& !"null".equals(querybean.getEnd_period())) {
				sb.append(" and a.Acct_period <=")
						.append(querybean.getEnd_period().trim()).append(" ");
			}
			if (null != querybean.getProvince()
					&& !"".equals(querybean.getProvince())
					&& !"null".equals(querybean.getProvince())) {

				if (null != querybean.getCity_no()
						&& !"".equals(querybean.getCity_no())
						&& !"null".equals(querybean.getCity_no())) {
					sb.append(" and a.area ='")
							.append(querybean.getProvince().trim()).append(",")
							.append(querybean.getCity_no()).append("' ");
				} else {
					sb.append(" and a.area like '")
							.append(querybean.getProvince().trim())
							.append(",%'");
				}
			}
			if (null != querybean.getOperator()
					&& !"".equals(querybean.getOperator())
					&& !"null".equals(querybean.getOperator())) {
				sb.append(" and a.Operator like '%")
						.append(querybean.getOperator().trim()).append("%' ");
			}
	   		if(null!=querybean.getCrdproduct()&&!"".equals(querybean.getCrdproduct())&&!"null".equals(querybean.getCrdproduct())){
				 sb.append(" and crdproduct ='").append(querybean.getCrdproduct().trim()).append("' ");
			} 
			if (null != querybean.getRb_type()
					&& !"".equals(querybean.getRb_type())
					&& !"null".equals(querybean.getRb_type())) {
				sb.append(" and a.rb_type ='")
						.append(querybean.getRb_type().trim()).append("' ");
			}	   		
		}
		return sb.toString();
	}

	public RedeembalBean getRedeembalInfo(String id) {
		sql = "select a.verno_ctx, a.id, a.txtime, a.stan, a.pan, a.amt, a.father_order, a.children_order, a.acct_period, a.sales_point, a.area, a.id_type, a.id_number, a.cell_phone, a.phone, a.rb_type, a.bank_name, a.branch_name, a.bank_pan, a.card_acceptor_name, a.card_acceptor_id, a.interest, a.fee, a.ispaid, a.summary, a.operator, a.mrcht_id, a.batch_stat, a.descr, a.txncode, a.currbill, a.amttxn, a.rateset, c.descr as crdproduct, a.curtxn from redeembal a left join crdproduct c on a.crdproduct=c.crdproduct where a.id = ? ";

		List<String> paramList = new ArrayList<String>();
		paramList.add(id);
		RedeembalBean bean = new RedeembalBean();
		try {
			Vector verctor = DbExec.execQuery(sql, paramList);
			if (verctor != null && !verctor.isEmpty()) {
				bean = new RedeembalBean((HashMap) verctor.get(0));
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		} catch (Exception e) {
			logger.error("SQLException", e);
		}
		return bean;
	}

	public int updRedeembalDescr(String id, String desc) {
		sql = "update redeembal t set descr = ? where  t.id = ? ";

		List<String> paramList = new ArrayList<String>();
		paramList.add(desc);
		paramList.add(id);
		try {
			result = DbExec.execUpdate(sql, paramList);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: update redeembal", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: update redeembal", e);
			result = -1;
		}
		return result;
	}
}
