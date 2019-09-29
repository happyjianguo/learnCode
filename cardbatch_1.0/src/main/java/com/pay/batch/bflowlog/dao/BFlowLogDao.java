package com.pay.batch.bflowlog.dao;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bmanger.bean.BMangerBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 
 * @TODO 角色类的增、删、改、查功能 
 *
 * @author 黄斌
 * @created on 2007-12-20  11:00:37
 * @version 1.0
 */
public class BFlowLogDao {
    private static final Logger logger = Logger.getLogger(BFlowLogDao.class);
    private String sql = "";
    private int result = 0;   

    /**
     * 
     * @TODO 
     *
     * @param roleBean
     * @return 修改结果
     
     * </pre>
     */
    public int updateBManger(BMangerBean bmangerBean) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        String sql1 = "update MonBatchinfo set batchflag = ?, Batchname = ?, batchfile = ?, batchflagfile = ?, demo = ?, backuppath = ?, logfilepath = ?, dept_no = ? where id=?";
        String[] param1 = {bmangerBean.getBatchflag(),bmangerBean.getBatchname(),bmangerBean.getBatchfile(),bmangerBean.getBatchflagfile(),
        		bmangerBean.getDemo(),bmangerBean.getBackuppath(),bmangerBean.getLogfilepath(),bmangerBean.getDept_no(),bmangerBean.getId()};

        sqlList.add(sql1);
        paramList.add(param1);

        try {
            result = DbExec.execBatchKeep(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + bmangerBean.toString(), e);
        } catch (Exception e) {
            result = -1;
            logger.error("Exception:" + bmangerBean.toString(), e);
        }
        return result;
    }

    
    public List<BFlowLogBean> getBFlowLogList(String role_no, String dept_no_node,PageBean pageBean) {
    	java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyyMMdd");
    	String nowdate=sdf.format(new Date());
    	List<BFlowLogBean> list = new ArrayList<BFlowLogBean>();
    	sql="select b.id,b.batchflag,b.execdate,b.issucess,b.execinfo,b.addtime,b.srcstartflag,b.srcendflag,b.descstartflag,b.execflag,"
    			+ "b.descendflag,c.dept_no,c.batchname,b.panflagno "
    			+ "from monbatchinfo c,MonBatchflwlog b "
    			+ "where c.batchflag = b.batchflag and substr(b.execdate,1,8)='"+nowdate+"' and datasource=0";
		try {
			Vector bFlowLogVector = null;
			if(role_no!=null && role_no.equals("00")){
				 sql += " order by id desc";
				 bFlowLogVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}else{
				sql += " and dept_no in ("+dept_no_node+") order by id desc";
				bFlowLogVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}
			
			if (bFlowLogVector != null && !bFlowLogVector.isEmpty()) {
				for (int i = 0; i < bFlowLogVector.size(); i++) {
					BFlowLogBean bmangerBean = new BFlowLogBean((HashMap) bFlowLogVector.get(i));
					list.add(bmangerBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
    }
    public List<BFlowLogBean> queryBFlowLogList(String role_no, String dept_no_node,PageBean pageBean,BFlowLogBean bean) {
    	List<BFlowLogBean> list = new ArrayList<BFlowLogBean>();
    	sql="select b.id,b.batchflag,b.execdate,b.issucess,b.execinfo,b.addtime,b.srcstartflag,b.srcendflag,b.descstartflag,"
    			+ "b.descendflag,c.dept_no,c.batchname,b.panflagno "
    			+ "from monbatchinfo c,MonBatchflwlog b "
    			+ "where c.batchflag = b.batchflag ";
		try {
			Vector bFlowLogVector = null;
			if(bean.getExecdate()!=null&&!"".equals(bean.getExecdate())&&!"null".equals(bean.getExecdate())){
				sql += " and substr(b.execdate,1,8)='"+bean.getExecdate()+"'";
			}
			//数据来源
			if(bean.getDatasource()!=null&&!"".equals(bean.getDatasource())&&!"null".equals(bean.getDatasource())){
				sql += " and b.datasource='"+bean.getDatasource()+"'";
			}
			//受理号
			if(bean.getPanflagno()!=null&&!"".equals(bean.getPanflagno())&&!"null".equals(bean.getPanflagno())){
				sql += " and b.panflagno ='"+bean.getPanflagno().trim()+"'";
			}
			//类型：万事达虚拟卡 socketflag 为0007
			if(bean.getBatchflag()!=null&&!"".equals(bean.getBatchflag())&&!"null".equals(bean.getBatchflag())){
				sql += " and c.socketflag ='"+bean.getBatchflag().trim()+"'";
			}else{
				sql += " and c.socketflag <>'0007'";
			}			
			if(role_no!=null && role_no.equals("00")){
//				 sql += " order by id desc";
				 sql += " order by b.panflagno desc";
				 bFlowLogVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}else{
				sql += " and dept_no in ("+dept_no_node+") order by b.panflagno desc";
//				sql += " and dept_no in ("+dept_no_node+") order by id desc";
				bFlowLogVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}
			
			if (bFlowLogVector != null && !bFlowLogVector.isEmpty()) {
				for (int i = 0; i < bFlowLogVector.size(); i++) {
					BFlowLogBean bmangerBean = new BFlowLogBean((HashMap) bFlowLogVector.get(i));
					list.add(bmangerBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
    }
    
    /**
     * 查询卡BIN LIST
     * @param isMCBin	0：福卡卡BIN；1.万事达卡卡BIN ;2.获取所有的卡bin
     * @return
     */
    public List<BFlowLogBean> getBinList(String isMCBin) {    	
    	List<BFlowLogBean> list = new ArrayList<BFlowLogBean>();
    	if("1".equals(isMCBin)){
    		sql="select f.iid as batchflag,  f.iid||'|'||p.descr as batchname from crdformat f,crdproduct p where f.id=p.crdformat_id and p.usrdata1='VCN'";
    	}else if("0".equals(isMCBin)){
    		sql="select f.iid as batchflag,  f.iid||'|'||p.descr as batchname from crdformat f,crdproduct p where f.id=p.crdformat_id and p.usrdata1!='VCN'";    		
    	}else{
    		sql="select f.iid as batchflag,  f.iid||'|'||p.descr as batchname from crdformat f,crdproduct p where f.id=p.crdformat_id ";    		   		
    	}
		try {
			Vector bFlowLogVector = null;
			bFlowLogVector = DbExec.execCardQuery(sql, null);
			if (bFlowLogVector != null && !bFlowLogVector.isEmpty()) {
				for (int i = 0; i < bFlowLogVector.size(); i++) {
					BFlowLogBean bmangerBean = new BFlowLogBean((HashMap) bFlowLogVector.get(i));
					list.add(bmangerBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
    }
    
    
    public int getCount(String roleno,String dept_no_node) {
    	int count=0;
    	java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyyMMdd");
    	String nowdate=sdf.format(new Date());
    	sql="select count(*) numbers from monbatchinfo b,MonBatchflwlog f where f.batchflag = b.batchflag  and substr(f.execdate,1,8)='"+nowdate+"'   and datasource=0";
        
    	try {
			Vector vector = null;
			if(roleno!=null && roleno.equals("00")){
				
				vector = DbExec.execQueryKeep(sql);
			}else{
				sql += " and dept_no in ("+dept_no_node+") ";
				vector = DbExec.execQueryKeep(sql);
			}
			
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
    public int getCount(String roleno,String dept_no_node,BFlowLogBean bean) {
    	int count=0;
    	
    	sql="select count(*) numbers from monbatchinfo b,MonBatchflwlog f where f.batchflag = b.batchflag  ";
        
    	try {
			Vector vector = null;
			if(bean.getExecdate()!=null&&!"".equals(bean.getExecdate())&&!"null".equals(bean.getExecdate())){
				sql += " and substr(f.execdate,1,8)='"+bean.getExecdate()+"'";
			}
			//数据来源
			if(bean.getDatasource()!=null&&!"".equals(bean.getDatasource())&&!"null".equals(bean.getDatasource())){
				sql += " and f.datasource='"+bean.getDatasource()+"'";
			}
			//受理号
			if(bean.getPanflagno()!=null&&!"".equals(bean.getPanflagno())&&!"null".equals(bean.getPanflagno())){
				sql += " and f.panflagno ='"+bean.getPanflagno().trim()+"'";
			}
			//类型：万事达虚拟卡 socketflag 为0007
			if(bean.getBatchflag()!=null&&!"".equals(bean.getBatchflag())&&!"null".equals(bean.getBatchflag())){
				sql += " and b.socketflag ='"+bean.getBatchflag().trim()+"'";
			}else{
				sql += " and b.socketflag <>'0007'";
			}
			
			if(roleno!=null && roleno.equals("00")){				
				vector = DbExec.execQueryKeep(sql);
			}else{
				sql += " and dept_no in ("+dept_no_node+") ";
				vector = DbExec.execQueryKeep(sql);
			}
			
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
    
    public BFlowLogBean getBFlowLogInfo(String id) {
        sql = "select t.*,b.dept_no,b.batchname,b.batchfile,b.batchflagfile,b.demo,b.backuppath,b.logfilepath,srcstartflagname,srcendflagname,descstartflagname,descendflagname,b.socketflag from monbatchinfo b,MonBatchflwlog t where t.batchflag = b.batchflag  and t.id = ? ";
        List paramList = new ArrayList();
        paramList.add(id);
        BFlowLogBean flowlogBean=new BFlowLogBean();
        try {
            Vector bflowlogVector = DbExec.execQueryKeep(sql,paramList);
            if (bflowlogVector != null && !bflowlogVector.isEmpty()) {
            	flowlogBean = new BFlowLogBean((HashMap) bflowlogVector.get(0));                    
              }
             
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return flowlogBean;
    }
    /**
     * @return 0 执行成功 -1 执行状态失败  -2 记录为空 2 是初始化状态 
     * */
    public int getBFlowLogInfoStatus(String panflagno,String batchflag,BFlowLogBean flowlogBean) {
        sql = "select t.* from MonBatchflwlog t where  t.batchflag = ? and panflagno=?";
        List list = new ArrayList();
        List paramList = new ArrayList();        
        paramList.add(batchflag);
        paramList.add(panflagno);
       
        int result=-1;
        try {
            Vector bflowlogVector = DbExec.execQueryKeep(sql,paramList);
            if (bflowlogVector != null && !bflowlogVector.isEmpty()) {
            	flowlogBean = new BFlowLogBean((HashMap) bflowlogVector.get(0)); 
            	if("0".equals(flowlogBean.getIssucess())){
            		result=0; //执行成功
            	}else if("2".equals(flowlogBean.getIssucess())){
            		result=2; //执行成功 发卡程序仍在进行
            	}else{
            		result=-1; //执行失败
            	}
              }else{
            	  result=-2; //条数为空
              }
             
        } catch (SQLException e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }catch (Exception e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }
        return result;
    }

    /**
     * @return 0 执行成功 -1 执行状态失败  -2 记录为空 2 是初始化状态 
     * */
    public int getBFlowLogInfoStatusOfNC(String panflagno,String batchflag,BFlowLogBean flowlogBean) {
        sql = "select  a.stat as issucess from renewcrd a  where 1 = 1 and a.father_order='"+panflagno+"' ";       
        int result=-1;
        try {
            Vector bflowlogVector = DbExec.execQuery(sql, null);
            if (bflowlogVector != null && !bflowlogVector.isEmpty()) {
            	flowlogBean = new BFlowLogBean((HashMap) bflowlogVector.get(0)); 
            	if("01".equals(flowlogBean.getIssucess())){
            		result=0; //执行成功
            	}else if("00".equals(flowlogBean.getIssucess())){
            		result=2; //执行成功 发卡程序仍在进行
            	}else{
            		result=-1; //执行失败
            	}
              }else{
            	  result=-2; //条数为空
              }
             
        } catch (SQLException e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }catch (Exception e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }
        return result;
    }
    
    /**
     * 获取同名换卡订单号，卡号，和初始有效期
     * @param id
     * @return
     */
    public BFlowLogBean getSameNameInfo(String pan,String panflagno) {
    	if(pan==null||"".equals(pan)){
    		return null;
    	}
    	sql="select a.father_order as panflagno ,a.pan as batchflag,to_char(b.expdate,'yyyyMMdd') as  addtime,a.xtxnime as  execdate,a.stat as issucess,to_char(a.crd_expdate,'yyyyMMdd') as srcstartflag from renewcrd a left join crddet b on a.pan=b.pan where 1=1 ";
    	sql=sql+" and a.pan='"+pan+"' ";
    	if(panflagno!=null&&!"".equals(panflagno)){
    		sql=sql+" and a.father_order='"+panflagno+"' ";
    	}
        BFlowLogBean flowlogBean=new BFlowLogBean();
        try {
            Vector bflowlogVector = DbExec.execQuery(sql,null);
            if (bflowlogVector != null && !bflowlogVector.isEmpty()) {
            	flowlogBean = new BFlowLogBean((HashMap) bflowlogVector.get(0));                    
              }
             
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return flowlogBean;
    }
    
    /**
     * 同名换卡LIST
     * @param pageBean
     * @param pan
     * @return
     */
    public List<BFlowLogBean> querySameNameList(PageBean pageBean,String pan,String stat) {
    	List<BFlowLogBean> list = new ArrayList<BFlowLogBean>();
    	sql="select a.father_order as panflagno ,a.pan as batchflag,to_char(b.expdate,'yyyyMMdd') as  addtime,a.xtxnime as  execdate,a.stat as issucess from renewcrd a left join crddet b on a.pan=b.pan where 1=1 ";
		try {
			Vector bFlowLogVector = null;			
			if(pan!=null && !"".equals(pan)){
				if(pan.length()==15){
					sql += " and substr(a.pan,0,15) = '"+pan.trim()+"' ";				
				}else{
					sql += " and a.pan = '"+pan.trim()+"' ";				
				}
			}
			if(stat!=null && !"".equals(stat)){
				sql += " and a.stat='"+stat.trim()+"' ";
			}
			sql+="order BY execdate desc";
			bFlowLogVector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
			
			if (bFlowLogVector != null && !bFlowLogVector.isEmpty()) {
				for (int i = 0; i < bFlowLogVector.size(); i++) {
					BFlowLogBean bmangerBean = new BFlowLogBean((HashMap) bFlowLogVector.get(i));
					list.add(bmangerBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
    }
    /**
     * 同名换卡LIST count
     * @param pageBean
     * @param pan
     * @return
     */
    public int getSameNameCount(String pan,String stat) {
    	int count=0;
    	sql="select count(*) as numbers from renewcrd a left join crddet b on a.pan=b.pan where 1=1 ";
        
    	try {
			Vector vector = null;
			if(pan!=null && !"".equals(pan)){
				if(pan.length()==15){
					sql += " and substr(a.pan,0,15) = '"+pan.trim()+"' ";				
				}else{
					sql += " and a.pan = '"+pan.trim()+"' ";				
				}			}
			if(stat!=null && !"".equals(stat)){
				sql += " and a.stat='"+stat.trim()+"' ";
			}
			vector = DbExec.execQuery(sql,null);			
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
    
    /**
     * @return 0 执行成功 -1 执行状态失败  -2 记录为空 2 是初始化状态 
     * */
    public int getMakeCardStat(String panflagno,BFlowLogBean flowlogBean) {
        sql = "select stat as issucess from makecard where batch='"+panflagno+"'";       
        int result=-1;
        try {
            Vector bflowlogVector = DbExec.execCardQuery(sql, null);
            if (bflowlogVector != null && !bflowlogVector.isEmpty()) {
            	flowlogBean = new BFlowLogBean((HashMap) bflowlogVector.get(0)); 
            	if("01".equals(flowlogBean.getIssucess())){
            		result=0; //执行成功
            	}else if("00".equals(flowlogBean.getIssucess())){
            		result=2; //执行成功 发卡程序仍在进行
            	}else{
            		result=-1; //执行失败
            	}
              }else{
            	  result=-2; //条数为空
              }
             
        } catch (SQLException e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }catch (Exception e) {
        	  result=-1; //执行失败
             logger.error("SQLException", e);
        }
        return result;
    }
}
