package com.pay.batch.bmanger.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

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
public class BMangerDao {
    private static final Logger logger = Logger.getLogger(BMangerDao.class);
    private String sql = "";
    private int result = 0;

    
    
    public int addBManger(BMangerBean bmangerBean) {    	
    	sql = "insert into MonBatchinfo (batchflag,Batchname,batchfile,batchflagfile,addtime,demo,backuppath,logfilepath,dept_no) values(?,?,?,?,?,?,?,?,?)";

        ArrayList params = new ArrayList();
        params.add(bmangerBean.getBatchflag());
        params.add(bmangerBean.getBatchname());
        params.add(bmangerBean.getBatchfile());
        params.add(bmangerBean.getBatchflagfile());
         java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.add(sdf.format(new Date()));
        params.add(bmangerBean.getDemo());
        params.add(bmangerBean.getBackuppath());
        params.add(bmangerBean.getLogfilepath());
        params.add(bmangerBean.getDept_no());     

        try {
            result = DbExec.execUpdate(sql, params);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + bmangerBean, e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + bmangerBean, e);
        }
        return result;
    }

   

    /**
     * 
     * @TODO 修改角色信息
     *
     * @param roleBean
     * @return 修改结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  11:05:49
     * LastModified
     * History:
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
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + bmangerBean.toString(), e);
        } catch (Exception e) {
            result = -1;
            logger.error("Exception:" + bmangerBean.toString(), e);
        }
        return result;
    }

    
    public List<BMangerBean> getBMangerList(String role_no, String dept_no_node,PageBean pageBean) {
    	List<BMangerBean> list = new ArrayList<BMangerBean>();
    	sql="select Id,Batchflag,Batchname,batchfile,batchflagfile,addtime,demo,Backuppath,Logfilepath,dept_no from monbatchinfo ";
		try {
			Vector bMangerVector = null;
			if(role_no!=null && role_no.equals("00")){
				 sql += " order by id desc";
				 bMangerVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}else{
				sql += " where dept_no in ("+dept_no_node+") order by id desc";
				bMangerVector = DbExec.execQueryKeep(sql, pageBean.getStart(), pageBean.getPageSize());
			}
			
			if (bMangerVector != null && !bMangerVector.isEmpty()) {
				for (int i = 0; i < bMangerVector.size(); i++) {
					BMangerBean bmangerBean = new BMangerBean((HashMap) bMangerVector.get(i));
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
    	sql="select count(*) numbers from monbatchinfo ";
        
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
   
    public int deleteBManger(String id,String flag) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        Vector usercodeVector =null;
         String sql0="select count(*) numbers from MonBatchflwlog where Batchflag="+flag;
         int count=0;
         try {
 			 Vector vector = null;
 			 vector = DbExec.execQueryKeep(sql0);
 			 
 			
 			if (vector != null && !vector.isEmpty()) {
 				HashMap map = (HashMap) vector.get(0);
 				count = Integer.parseInt(((String) map.get("numbers")).trim());
 			}
 		} catch (SQLException e) {
 			logger.error(e);
 		}catch (Exception e) {
 			logger.error(e);
 		}
        if (count>0) {
        	result = -2;
        } else {
	        String sql1 = "delete from MonBatchinfo where id=?";
	        String[] param1 = { id };	       
	        sqlList.add(sql1);
	        paramList.add(param1);	       
	
	        try {
	            result = DbExec.execBatch(sqlList, paramList);
	        } catch (SQLException e) {
	            result = -1;
	            logger.error("SQLException:" + id + "|", e);
	        }catch (Exception e) {
	            result = -1;
	            logger.error("Exception:" + id + "|", e);
	        }
        }
        return result;
    }
    public BMangerBean getBMangerInfo(String id) {
        sql = "select t.* from monbatchinfo t where t.id = ? ";
        List list = new ArrayList();
        List paramList = new ArrayList();
        paramList.add(id);
        BMangerBean deptBean=new BMangerBean();
        try {
            Vector bmangerVector = DbExec.execQueryKeep(sql,paramList);
            if (bmangerVector != null && !bmangerVector.isEmpty()) {
                deptBean = new BMangerBean((HashMap) bmangerVector.get(0));                    
               }
            
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return deptBean;
    }
    public BMangerBean getBMangerInfoPan(String batchflag) {
        sql = "select t.* from monbatchinfo t where t.Batchflag = ? ";
        List list = new ArrayList();
        List paramList = new ArrayList();
        paramList.add(batchflag);
        BMangerBean deptBean=new BMangerBean();
        try {
            Vector bmangerVector = DbExec.execQueryKeep(sql,paramList);
            if (bmangerVector != null && !bmangerVector.isEmpty()) {
                deptBean = new BMangerBean((HashMap) bmangerVector.get(0));                    
             }
            
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return deptBean;
    }
    public BMangerBean getBMangerInfoPanKeep(String batchflag) {
        sql = "select t.* from monbatchinfo t where t.Batchflag = ? ";
        List list = new ArrayList();
        List paramList = new ArrayList();
        paramList.add(batchflag);
        BMangerBean deptBean=new BMangerBean();
        try {
            Vector bmangerVector = DbExec.execQueryKeep(sql,paramList);
            if (bmangerVector != null && !bmangerVector.isEmpty()) {
                deptBean = new BMangerBean((HashMap) bmangerVector.get(0));                    
             }
            
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return deptBean;
    }
}
