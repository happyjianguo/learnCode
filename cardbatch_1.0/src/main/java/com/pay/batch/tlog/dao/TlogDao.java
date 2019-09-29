package com.pay.batch.tlog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.tlog.bean.TlogBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class TlogDao {
	private static final Logger logger = Logger.getLogger(TlogDao.class);
    private String sql = "";
    
    public List<TlogBean> getTlogList(PageBean pageBean,TlogBean querybean) {
    	List<TlogBean> list = new ArrayList<TlogBean>();
    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,";
    	sql+="(to_char(a.datelocal, 'yyyymmdd') || ' ' || replace(lpad(a.timelocal,6),' ','0')) as datelocal,";
    	sql+=" a.txnsrc,a.fncode,a.txncode,a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
    	sql+=" from tlog a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
//    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,to_char(a.datelocal, 'yyyymmdd') ||' '|| a.timelocal as datelocal,a.txnsrc,a.fncode,a.txncode,";
//    	sql+=" a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
//    	sql+=" from tlog a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
		try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			sql += " order by id desc";
			if(pageBean==null){
				vector = DbExec.execQuery(sql);
			}else{
				vector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
			}			
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					TlogBean bean = new TlogBean((HashMap) vector.get(i));
					//增加交易类型
					bean.setQueryTxnType(bean.getFncode().trim()+"-"+bean.getTxncode().trim()+"-"+bean.getSub_txncode().trim());
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
    public List<TlogBean> getTlogListCurrent(PageBean pageBean,TlogBean querybean) {
    	List<TlogBean> list = new ArrayList<TlogBean>();
    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,";
    	sql+="(to_char(a.datelocal, 'yyyymmdd') || ' ' || replace(lpad(a.timelocal,6),' ','0')) as datelocal,";
    	sql+=" a.txnsrc,a.fncode,a.txncode,a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
    	sql+=" from t_log a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
//    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,to_char(a.datelocal, 'yyyymmdd') ||' '|| a.timelocal as datelocal,a.txnsrc,a.fncode,a.txncode,";
//    	sql+=" a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
//    	sql+=" from tlog a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
    	try {
    		Vector vector = null;
    		sql+=sqlparm(querybean);
    		sql += " order by id desc";
    		if(pageBean==null){
    			vector = DbExec.execQuery(sql);
    		}else{
    			vector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
    		}			
    		if (vector != null && !vector.isEmpty()) {
    			for (int i = 0; i < vector.size(); i++) {
    				TlogBean bean = new TlogBean((HashMap) vector.get(i));
    				//增加交易类型
    				bean.setQueryTxnType(bean.getFncode().trim()+"-"+bean.getTxncode().trim()+"-"+bean.getSub_txncode().trim());
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
    public List<TlogBean> getTlogListBak(PageBean pageBean,TlogBean querybean) {
    	List<TlogBean> list = new ArrayList<TlogBean>();
    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,";
    	sql+="(to_char(a.datelocal, 'yyyymmdd') || ' ' || replace(lpad(a.timelocal,6),' ','0')) as datelocal,";
    	sql+=" a.txnsrc,a.fncode,a.txncode,a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
    	sql+=" from tlog a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
//    	sql=" select a.id,a.stanorg,a.rrn ,a.termseq,a.pan,a.crdacptid,a.termcode,to_char(a.datelocal, 'yyyymmdd') ||' '|| a.timelocal as datelocal,a.txnsrc,a.fncode,a.txncode,";
//    	sql+=" a.sub_txncode,a.txnstatus,a.amttxn,a.rspcode,b.descr as crdproduct,a.amttax ";
//    	sql+=" from tlog a left join crdproduct b on a.crdproduct=b.crdproduct where 1=1 ";
		try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			sql += " order by id desc";
			if(pageBean==null){
				vector = DbExec.execQueryListBak(sql);
			}else{
				vector = DbExec.execQueryListBak(sql, pageBean.getStart(), pageBean.getPageSize());
			}			
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					TlogBean bean = new TlogBean((HashMap) vector.get(i));
					//增加交易类型
					bean.setQueryTxnType(bean.getFncode().trim()+"-"+bean.getTxncode().trim()+"-"+bean.getSub_txncode().trim());
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
    public int getCount(TlogBean querybean) {
    	int count=0;    	
    	sql="select count(a.id) numbers from tlog a where 1=1 ";
        
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
    public int getCountCurrent(TlogBean querybean) {
    	int count=0;    	
    	sql="select count(a.id) numbers from t_log a where 1=1 ";
    	
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
    public int getCountBak(TlogBean querybean) {
    	int count=0;    	
    	sql="select count(a.id) numbers from tlog a where 1=1 ";
        
    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			vector = DbExec.execQueryListBak(sql);			
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
    public String sqlparm(TlogBean querybean){
    	 StringBuffer sb=new StringBuffer("");
    	 if(querybean!=null){
    		 if(null!=querybean.getRrn()&&!"".equals(querybean.getRrn())&&!"null".equals(querybean.getRrn())){
    			 sb.append(" and a.rrn ='").append(querybean.getRrn()).append("' ");
    		 }    		 
    		 if(null!=querybean.getPan()&&!"".equals(querybean.getPan())&&!"null".equals(querybean.getPan())){
    			 sb.append(" and a.pan ='").append(querybean.getPan()).append("' ");
    		 }
    		 if(null!=querybean.getCrdacptid()&&!"".equals(querybean.getCrdacptid())&&!"null".equals(querybean.getCrdacptid())){
    			 sb.append(" and a.crdacptid ='").append(querybean.getCrdacptid()).append("' ");
    		 }  
    		 if(null!=querybean.getTermcode()&&!"".equals(querybean.getTermcode())&&!"null".equals(querybean.getTermcode())){
    			 sb.append(" and a.termcode ='").append(querybean.getTermcode()).append("' ");
    		 } 
//    		 if(null!=querybean.getAiid()&&!"".equals(querybean.getAiid())&&!"null".equals(querybean.getAiid())){
//    			 sb.append(" and a.aiid ='").append(querybean.getAiid()).append("' ");
//    		 } 
    		 //本地时间查询条件
    		 if(null!=querybean.getQueryDTStart()&&!"".equals(querybean.getQueryDTStart())&&!"null".equals(querybean.getQueryDTStart())){
    			 sb.append(" and to_char(a.datelocal,'yyyymmdd')||replace(lpad(a.timelocal,6),' ','0')>='").append(querybean.getQueryDTStart()).append("' ");
    		 } 
    		 if(null!=querybean.getQueryDTEnd()&&!"".equals(querybean.getQueryDTEnd())&&!"null".equals(querybean.getQueryDTEnd())){
    			 sb.append(" and to_char(a.datelocal,'yyyymmdd')||replace(lpad(a.timelocal,6),' ','0')<='").append(querybean.getQueryDTEnd()).append("' ");
    		 }     		 
    		 
    		 if(null!=querybean.getTxnsrc()&&!"".equals(querybean.getTxnsrc())&&!"null".equals(querybean.getTxnsrc())){
    			 sb.append(" and a.txnsrc ='").append(querybean.getTxnsrc()).append("' ");
    		 } 
    		 if(null!=querybean.getTxncode()&&!"".equals(querybean.getTxncode())&&!"null".equals(querybean.getTxncode())){
    			 sb.append(" and a.txncode ='").append(querybean.getTxncode()).append("' ");
    		 }
    		 if(null!=querybean.getTxnstatus()&&!"".equals(querybean.getTxnstatus())&&!"null".equals(querybean.getTxnstatus())){
    			 sb.append(" and a.txnstatus ='").append(querybean.getTxnstatus()).append("' ");
    		 } 
    		 if(null!=querybean.getCrdproduct()&&!"".equals(querybean.getCrdproduct())&&!"null".equals(querybean.getCrdproduct())){
    			 sb.append(" and a.crdproduct ='").append(querybean.getCrdproduct()).append("' ");
    		 } 
    		//增加交易类型查询
    		 if(null!=querybean.getQueryTxnType()&&!"".equals(querybean.getQueryTxnType())&&!"null".equals(querybean.getQueryTxnType())){
    			
    			 if(null!=querybean.getFncode()&&!"".equals(querybean.getFncode())&&!"null".equals(querybean.getFncode())){
        			 sb.append(" and a.fncode ='").append(querybean.getFncode()).append("' ");
        		 }
    			 if(null!=querybean.getTxncode()&&!"".equals(querybean.getTxncode())&&!"null".equals(querybean.getTxncode())){
        			 sb.append(" and a.txncode ='").append(querybean.getTxncode()).append("' ");
        		 } 
        		 if(null!=querybean.getSub_txncode()&&!"".equals(querybean.getSub_txncode())&&!"null".equals(querybean.getSub_txncode())){
        			 sb.append(" and a.sub_txncode ='").append(querybean.getSub_txncode()).append("' ");
        		 } 
    		 }
    		 
    	 }
    	 return sb.toString();
    }
    public TlogBean getTlogInfo(String id) {
        sql = "select t.* from tlog t where  t.id = ? ";
        List<String> paramList = new ArrayList<String>();
        paramList.add(id);
        TlogBean bean=new TlogBean();
        try {
            Vector verctor = DbExec.execQuery(sql,paramList);
            if (verctor != null && !verctor.isEmpty()) {
            	bean = new TlogBean((HashMap) verctor.get(0));                    
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return bean;
    }
	public int getCountRollingPoor(TlogBean querybean) {
    	int count=0;    	
    	sql="select count(a.id) numbers from tlog a where 1=1 and a.fncode = '200' and a.txnstatus = 7 and (a.txncode = 0 or a.txncode = 20) ";
    	try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			vector = DbExec.execQueryListBak(sql);			
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
	public List<TlogBean> getTlogListRollingPoor(PageBean pageBean, TlogBean querybean) {
    	List<TlogBean> list = new ArrayList<TlogBean>();
    	sql="select a.pan,a.amttxn *(decode(a.txncode || a.sub_txncode, 000, 1, 2000, -1, 2001, -1, 0)) amttxn,";
    	sql+="a.crdacptid,a.termcode,to_char(a.datelocal, 'yyyymmdd') datelocal,a.timelocal,";
    	sql+="decode(a.txncode || a.sub_txncode, 000,'消费', 2000,'消费撤销', 2001, '退货','其他') txncode";
    	sql+=" from tlog a where 1=1 and a.fncode = '200' and a.txnstatus = 7 and (a.txncode = 0 or a.txncode = 20)";
    	try {
    		Vector vector = null;
    		sql+=sqlparm(querybean);
    		sql += " order by id desc";
    		if(pageBean==null){
    			vector = DbExec.execQueryListBak(sql);
    		}else{
    			vector = DbExec.execQueryListBak(sql, pageBean.getStart(), pageBean.getPageSize());
    		}			
    		if (vector != null && !vector.isEmpty()) {
    			for (int i = 0; i < vector.size(); i++) {
    				TlogBean bean = new TlogBean((HashMap) vector.get(i));
    				//增加交易类型
//    				bean.setQueryTxnType(bean.getFncode().trim()+"-"+bean.getTxncode().trim()+"-"+bean.getSub_txncode().trim());
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
	public Map<String,String> getTlogListRollingPoortotal(PageBean pageBean, TlogBean querybean) {
		Map<String,String> map = new HashMap<String,String>();
		sql="select ";
		sql+="sum((decode(a.txncode || a.sub_txncode, 000, 1, 0))) as h, ";
		sql+="sum(a.amttxn * (decode(a.txncode || a.sub_txncode, 000, 1, 0))) b, ";
		sql+="sum((decode(a.txncode || a.sub_txncode, 2000, 1, 0))) as c, ";
		sql+="sum(a.amttxn * (decode(a.txncode || a.sub_txncode, 2000, 1, 0))) as d, ";
		sql+="sum((decode(a.txncode || a.sub_txncode, 2001, 1, 0))) as e, ";
		sql+="sum(a.amttxn * (decode(a.txncode || a.sub_txncode, 2001, 1, 0))) as f, ";
		sql+="sum(a.amttxn * (decode(a.txncode || a.sub_txncode, 000,1, 2000,-1, 2001,-1, 0))) as g ";
		sql+=" from tlog a where 1=1 and a.fncode = '200' and a.txnstatus = 7 and (a.txncode = 0 or a.txncode = 20)";
		try {
			Vector vector = null;
			sql+=sqlparm(querybean);
			sql += " order by id desc";
			if(pageBean==null){
				vector = DbExec.execQueryListBak(sql);
			}else{
				vector = DbExec.execQueryListBak(sql, pageBean.getStart(), pageBean.getPageSize());
			}	
			if (vector != null && !vector.isEmpty()) {
    			for (int i = 0; i < vector.size(); i++) {
    				map=(Map<String, String>) vector.get(i);
    			}
    		}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return map;
	}
}
