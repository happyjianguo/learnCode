package com.pay.batch.depositaudit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.depositaudit.bean.DepositBean;
import com.pay.batch.depositaudit.struts.form.DepositForm;
//import com.pay.batch.deposit.bean.DepositBean;
//import com.pay.batch.deposit.struts.form.DepositForm;
import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.dao.UserCrdproductDao;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class DepositDao {
	 private static final Logger logger = Logger.getLogger(DepositDao.class);
    private String sql = "";
    private int result = 0;
    
    public List<DepositBean> getDepositList(PageBean pageBean,DepositForm querybean,String user_code) {
    	List<DepositBean> list = new ArrayList<DepositBean>();
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
    	sql="select verno_ctx, id, txnsrc, txncode, time, stan, pan_start, pan_end, pan_count, amt_each_crd, father_order, children_order, org_f_order, org_c_order, acct_period, pay_type, pay_desc, payer_name, substr(sales_point,instr(sales_point,',')+1) as sales_point, area, id_type, id_number, cell_phone, phone, address, cashin_type, ispaid, summary, substr(operator,instr(operator,',')+1) as operator, mrcht_id, batch_stat, reserved1, reserved2, reserved3, descr, currbill, amttxn, rateset, crdproduct, curtxn from cashinbatch where 1=1 ";
		sql=sql+sqlStr;

    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			sql += " order by id desc";
			vector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					DepositBean bean = new DepositBean((HashMap) vector.get(i));
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
    public List<DepositBean> getDepositList(DepositForm querybean,String user_code) {
    	List<DepositBean> list = new ArrayList<DepositBean>();
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
		sql="select t.id,t.txncode,t.cashin_type,t.time,t.stan,"
    			+ "t.pan_start,t.pan_end,t.pan_count,t.amt_each_crd,"
    			+ "t.father_order,t.children_order,t.pay_type,t.pay_desc,"
    			+ "t.sales_point,t.area,t.operator,t.summary,t.mrcht_id,t.ispaid,"
    			+ "t.batch_stat,t.acct_period,t.descr,t.id_type,t.id_number,t.cell_phone,t.phone,t.address "
    			+ "from cashinbatch t where 1=1 ";
    	sql=sql+sqlStr;

		try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			sql += " order by t.id desc";
			vector = DbExec.execQuery(sql);
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					DepositBean bean = new DepositBean((HashMap) vector.get(i));
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
    public int getCount(DepositForm querybean,String user_code) {
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
    	int count=0;    	
    	sql="select count(*) numbers from cashinbatch where 1=1 ";
		sql=sql+sqlStr;
        
    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			vector = DbExec.execQuery(sql);			
			
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
        return count;
    }
    
    public String getSumAmt(DepositForm querybean,String user_code) {
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
    	String count="0";    	
    	sql="select sum(pan_count*amt_each_crd) as amt_each_crd from cashinbatch where 1=1 ";
		sql=sql+sqlStr;
        
    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			vector = DbExec.execQuery(sql);			
			
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = (String) map.get("amt_each_crd");
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
        return count;
    }
    
    public String getSumPanCount(DepositForm querybean) {
    	String count="0";
    	sql="select sum(pan_count) as pan_count from cashinbatch where 1=1 ";
    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			vector = DbExec.execQuery(sql);			
			
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = (String) map.get("pan_count");
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
        return count;
    }
    
    public String sqlparm(DepositForm querybean){
    	 StringBuffer sb=new StringBuffer("");
    	 if(querybean!=null){
    		 if(null!=querybean.getFather_order()&&!"".equals(querybean.getFather_order())&&!"null".equals(querybean.getFather_order())){
    			 sb.append(" and father_order ='").append(querybean.getFather_order().trim()).append("' ");
    		 }		
    		 
    		 if(null!=querybean.getTime()&&!"".equals(querybean.getTime())&&!"null".equals(querybean.getTime())){
    			 sb.append(" and Time ='").append(querybean.getTime().trim()).append("' ");
    		 }
    		 if(null!=querybean.getStartdate()&&!"".equals(querybean.getStartdate())&&!"null".equals(querybean.getStartdate())){
    			 sb.append(" and Time >='").append(querybean.getStartdate().trim()).append("' ");
    		 }
    		 if(null!=querybean.getEnddate()&&!"".equals(querybean.getEnddate())&&!"null".equals(querybean.getEnddate())){
    			 sb.append(" and Time <='").append(querybean.getEnddate().trim()).append("' ");
    		 }
    		 
    		//审批日期
    		 if(null!=querybean.getDescrStartDate()&&!"".equals(querybean.getDescrStartDate())&&!"null".equals(querybean.getDescrStartDate())){
    			 sb.append(" and REGEXP_SUBSTR(descr,'[^,]+',1,2,'i') >='").append(querybean.getDescrStartDate().trim()).append("' ");
    		 }
    		 if(null!=querybean.getDescrEndDate()&&!"".equals(querybean.getDescrEndDate())&&!"null".equals(querybean.getDescrEndDate())){
    			 sb.append(" and REGEXP_SUBSTR(descr,'[^,]+',1,2,'i') <='").append(querybean.getDescrEndDate().trim()).append("' ");
    		 }
    		 
    		 if(null!=querybean.getPay_type()&&!"".equals(querybean.getPay_type())&&!"null".equals(querybean.getPay_type())){
    			 sb.append(" and Pay_type ='").append(querybean.getPay_type().trim()).append("' ");
    		 }
    		 if(null!=querybean.getBatch_stat()&&!"".equals(querybean.getBatch_stat())&&!"null".equals(querybean.getBatch_stat())){
    			 sb.append(" and batch_stat ='").append(querybean.getBatch_stat().trim()).append("' ");
    		 }
    		 
    		 if(null!=querybean.getSales_point()&&!"".equals(querybean.getSales_point())&&!"null".equals(querybean.getSales_point())){
    			 sb.append(" and sales_point like '%").append(querybean.getSales_point().trim()).append("%' ");
    		 }
    		 if(null!=querybean.getPan_start()&&!"".equals(querybean.getPan_start())&&!"null".equals(querybean.getPan_start())){
    			 String panStart=querybean.getPan_start().trim();
    			 if(panStart.length()>15){
    				 panStart=panStart.substring(0, 15);
    			 }
    			 sb.append(" and Pan_start ='").append(panStart).append("' ");
    		 }
    		 if(null!=querybean.getPan_end()&&!"".equals(querybean.getPan_end())&&!"null".equals(querybean.getPan_end())){
    			 String panEnd=querybean.getPan_end().trim();
    			 if(panEnd.length()>15){
    				 panEnd=panEnd.substring(0, 15);
    			 }
    			 sb.append(" and Pan_end ='").append(panEnd).append("' ");
    		 }
    		 if(null!=querybean.getAmt_each_crd()&&!"".equals(querybean.getAmt_each_crd())&&!"null".equals(querybean.getAmt_each_crd())){
    			 sb.append(" and Amt_each_crd =").append(querybean.getAmt_each_crd().trim()).append(" ");
    		 }
    		 if(null!=querybean.getStart_period()&&!"".equals(querybean.getStart_period())&&!"null".equals(querybean.getStart_period())){
    			 sb.append(" and Acct_period >=").append(querybean.getStart_period().trim()).append(" ");
    		 }
    		 if(null!=querybean.getEnd_period()&&!"".equals(querybean.getEnd_period())&&!"null".equals(querybean.getEnd_period())){
    			 sb.append(" and Acct_period <=").append(querybean.getEnd_period().trim()).append(" ");
    		 }
    		 if(null!=querybean.getProvince()&&!"".equals(querybean.getProvince())&&!"null".equals(querybean.getProvince())){
    			 
    			 if(null!=querybean.getCity_no()&&!"".equals(querybean.getCity_no())&&!"null".equals(querybean.getCity_no())){
    				 sb.append(" and area ='").append(querybean.getProvince().trim()).append(",").append(querybean.getCity_no()).append("' ");
    			 } else {
    				 sb.append(" and area like '").append(querybean.getProvince().trim()).append(",%'");
    			 }
    		 }
    		 if(null!=querybean.getOperator()&&!"".equals(querybean.getOperator())&&!"null".equals(querybean.getOperator())){
    			 sb.append(" and LOWER(Operator) like '%").append(querybean.getOperator().toLowerCase().trim()).append("%' ");
    		 }
    		 if(null!=querybean.getCrdproduct()&&!"".equals(querybean.getCrdproduct())&&!"null".equals(querybean.getCrdproduct())){
    			 sb.append(" and crdproduct ='").append(querybean.getCrdproduct().trim()).append("' ");
    		 }
    	 }
    	 return sb.toString();
    }
    
    public DepositBean getDepositInfo(String id) {
        sql = "select a.verno_ctx, a.id, a.txnsrc, a.txncode, a.time, a.stan, a.pan_start, a.pan_end, a.pan_count, a.amt_each_crd, a.father_order, a.children_order, a.org_f_order, a.org_c_order, a.acct_period, a.pay_type, a.pay_desc, a.payer_name, a.sales_point, a.area, a.id_type, a.id_number, a.cell_phone, a.phone, a.address, a.cashin_type, a.ispaid, a.summary, a.operator, a.mrcht_id, a.batch_stat, a.reserved1, a.reserved2, a.reserved3, a.descr, a.currbill, a.amttxn, a.rateset, c.descr as crdproduct, a.curtxn from cashinbatch a left join crdproduct c on a.crdproduct=c.crdproduct where  a.id = ? ";
        List<String> paramList = new ArrayList<String>();
        paramList.add(id);
        List lst;
        DepositBean bean=new DepositBean();
        try {
            Vector verctor = DbExec.execQuery(sql,paramList);
            if (verctor != null && !verctor.isEmpty()) {
            	bean = new DepositBean((HashMap) verctor.get(0));                    
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return bean;
    }
    
    public int updCashInBatchDescr(String id, String desc) {
        sql = "update cashinbatch t set descr = ? where  t.id = ? ";
        
        List<String> paramList = new ArrayList<String>();
        paramList.add(desc);
        paramList.add(id);
        try {
			result = DbExec.execUpdate(sql, paramList);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: update cashinbatch", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: update cashinbatch", e);
			result = -1;
		}
		return result;
    }
}
