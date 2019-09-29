package com.pay.system.dept.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.dept.bean.DeptBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 
 * @TODO �û����������ɾ���ġ��鹦�� 
 *
 * @author �Ʊ�
 * @created on 2007-12-20  11:00:37
 * @version 1.0
 */
public class DeptDao {
    private static final Logger logger = Logger.getLogger(DeptDao.class);
    private String sql = "";
    private int result = 0;

    /**
     * 
     * @TODO ����û�����Ϣ
     *
     * @param deptBean
     * @return ��ӽ��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public int checkdept(DeptBean deptBean){
    	result=0;
    	ArrayList paramscheck = new ArrayList();
        sql="select dept_no from Mondeptinfo where dept_no=?";
        paramscheck.add(deptBean.getDeptno());
        try{
        	 Vector deptVector = DbExec.execQuery(sql, paramscheck);
             if (deptVector != null && !deptVector.isEmpty()) {
            	 result=-2;
                 return result;
             }
        }catch(Exception e){
        	 result = -1;
             logger.error("SQLException:" + deptBean, e);
        }
        paramscheck = new ArrayList();
        sql="select dept_name from Mondeptinfo where dept_name=?";
        paramscheck.add(deptBean.getDeptname());
        try{
        	 Vector deptVector = DbExec.execQuery(sql, paramscheck);
             if (deptVector != null && !deptVector.isEmpty()) {
            	 result=-3;
                 return result;
             }
        }catch(Exception e){
        	 result = -1;
             logger.error("SQLException:" + deptBean, e);
        }
        return result;
    }
    public int addDept(DeptBean deptBean) {
    	result=checkdept(deptBean);
    	if(result<0) return result;
    	sql = "insert into Mondeptinfo (dept_no,dept_name,dept_desc,dept_level,dept_upperno) values(?,?,?,?,?)";

        ArrayList params = new ArrayList();

        params.add(deptBean.getDeptno());
        params.add(deptBean.getDeptname());
        params.add(deptBean.getDeptdesc());
        params.add(deptBean.getDept_level());
        params.add(deptBean.getDept_upperno());        
       
        try {
            result = DbExec.execUpdate(sql, params);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + deptBean, e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + deptBean, e);
        }
        return result;
    }

    /**
     * 
     * @TODO �����û����Ż������û�����Ϣ (�޸��û���ǰʹ��)
     *
     * @param deptno
     * @return �û������
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public DeptBean getDeptByDeptno(String deptno) {
        sql = "select a.* from Mondeptinfo a  where a.dept_no=?";
        List condList = new ArrayList();
        condList.add(deptno);
        DeptBean deptBean = null;
        try {
            Vector deptVector = DbExec.execQuery(sql, condList);
            if (deptVector != null && !deptVector.isEmpty()) {
                deptBean = new DeptBean((HashMap) deptVector.get(0));
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + deptno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + deptno + "|", e);
        }
        return deptBean;
    }
   

    /**
     * 
     * @TODO �޸��û�����Ϣ
     *
     * @param deptBean
     * @return �޸Ľ��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified
     * History:
     * </pre>
     */
    public int updateDept(DeptBean deptBean) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        String sql1 = "update Mondeptinfo set dept_name=?,dept_desc=?,dept_level=?,dept_upperno=? where dept_no=?";
        String[] param1 = { deptBean.getDeptname(), deptBean.getDeptdesc(),deptBean.getDept_level(),deptBean.getDept_upperno(), deptBean.getDeptno() };

        sqlList.add(sql1);
        paramList.add(param1);

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + deptBean.toString(), e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + deptBean.toString(), e);
        }
        return result;
    }

    /**
     * 
     * @TODO ����û�����Ϣ����
     *
     * @return �û�������
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-11-18
     * LastModified:
     * History:
     * </pre>
     */
    public int getCount(String roleno) {
    	int count = 0;
    	if(roleno!=null && roleno.equals("00")){
    		sql = "select count(*) as numbers from Mondeptinfo a  order by dept_no ";
    	}else{
    		sql = "select count(*) as numbers from Mondeptinfo a  where dept_no != '000000000' order by dept_no ";
    	}
        
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql);
            if (deptVector != null && !deptVector.isEmpty()) {
				HashMap map = (HashMap) deptVector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return count;
    }

    /**
     * 
     * @TODO ����û�����Ϣ�б���ҳ��
     *
     * @return �û����б�
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-11-18
     * LastModified:
     * History:
     * </pre>
     */
    public List getDeptListPage(String roleno, PageBean pageBean) {
    	if(roleno!=null && roleno.equals("00")){
    		sql = "select a.* from Mondeptinfo a order by dept_no ";
    	}else{
    		sql = "select a.* from Mondeptinfo a where dept_no != '000000000' order by dept_no ";
    	}
        
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }

    /**
     * 
     * @TODO ����û�����Ϣ�б�
     *
     * @return �û����б�
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public List getDeptList(String roleno) {
    	if(roleno!=null && roleno.equals("00")){
    		sql = "select * from Mondeptinfo a  order by dept_no ";
    	}else{
    		sql = "select * from Mondeptinfo a  where dept_no != '000000000' order by dept_no ";
    	}
        
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql);
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }

    /**
     * 
     * @TODO ɾ���û�����Ϣ��������û��鱻�����ĳЩ�û��飩
     * @param deptno
     * @return ִ�н��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public int deleteDept(String deptno) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        Vector usercodeVector = getUsercodeByDeptno(deptno);
        //������û����Ѿ��������ĳЩ�û�
       if (usercodeVector != null && !usercodeVector.isEmpty()) {
        	return -2;
        } 

        //������û����Ѿ��������û���
        Vector deptVector = getChildDeptByDeptno(deptno);
        if (deptVector != null && !deptVector.isEmpty()) {
        	return -4;
        }
        String sql = "delete from Mondeptinfo where dept_no=?";
        String[] param = { deptno };
        sqlList.add(sql);
        paramList.add(param);

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + deptno + "|", e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + deptno + "|", e);
        }
        return result;
    }

    /**
     * 
     * @TODO �жϸ��û����Ƿ��Ѿ����������û� 
     *  
     * @param deptno
     * @return �û���ż���
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-17
     * LastModified: 2009-11-2 ���� ��Monuserinfo����û��user_deptno�ֶ� Ӧ�޸�Ϊdept_no��
     * History:
     * </pre>
     */
    public Vector getUsercodeByDeptno(String deptno) {
        //sql = "select user_code from Monuserinfo where user_deptno=? order by 1";
    	sql = "select user_code from Monuserinfo where dept_no=? order by user_code asc";
        List condList = new ArrayList();
        condList.add(deptno);
        Vector usercodeVector = null;
        try {
            usercodeVector = DbExec.execQuery(sql, condList);
        } catch (SQLException e) {
            logger.error("SQLException:" + deptno + "|", e);
        }catch (Exception e) {
            logger.error("SQLException:" + deptno + "|", e);
        }
        return usercodeVector;
    }

    

    /**
     * 
     * @TODO �жϸ��û����Ƿ����������û��� 
     *  
     * @param deptno
     * @return �û��鼯��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-11-10
     * LastModified:
     * History:
     * </pre>
     */
    public Vector getChildDeptByDeptno(String deptno) {
        sql = "select dept_no from Mondeptinfo where dept_upperno=?";
        List condList = new ArrayList();
        condList.add(deptno);
        Vector deptVector = null;
        try {
        	deptVector = DbExec.execQuery(sql, condList);
        } catch (SQLException e) {
            logger.error("SQLException:" + deptno + "|", e);
        }catch (Exception e) {
            logger.error("SQLException:" + deptno + "|", e);
        }
        return deptVector;
    }
    
    /**
     * 
     * @TODO ��ýṹ������Ϣs
     *  
     * @param deptno
     * @return 
     * @author ����
     * @version 1.0
     * <pre>
     * Created on:2009-11-3
     * LastModified:
     * History:
     * </pre>
     */
    public List getLevel() {
        sql = "select t.dept_level from Mondeptinfo t where t.dept_level != '1' group by t.dept_level";
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql);
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }
    
    
    /**
     * 
     * @TODO ���û��鼶���ѯ�ṹ��Ϣ
     *  
     * @param deptno
     * @return 
     * @author ����
     * @version 1.0
     * <pre>
     * Created on:2009-11-3
     * LastModified:
     * History:
     * </pre>
     */
    public List getDeptInfoByLevel(String dept_level) {
        sql = "select t.* from Mondeptinfo t where t.dept_level < ? and t.dept_no !='000000000'";
        List list = new ArrayList();
        List paramList = new ArrayList();
        paramList.add(dept_level);
        try {
            Vector deptVector = DbExec.execQuery(sql,paramList);
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return list;
    }
    
    /**
     * 
     * @TODO ��õ�½�û����û���������û����
     *  
     * @param deptno
     * @return 
     * @author ����
     * @version 1.0
     * <pre>
     * Created on:2009-11-3
     * LastModified:2010-03-07 qhg ���ɵ��û���ŵ��ַ��� ÿ���û���Ŷ��ӵ����ţ���������������sql���  
     * History:
     * </pre>
     */
    public String getDeptNoByUser(String dept_no){
    	sql = "select t.* from Mondeptinfo t where t.dept_no !='000000000'";
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql);
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
    	List result = getNodes(dept_no,list);
    	StringBuffer sbf = new StringBuffer("");
		if(result.size() == 0){
			sbf.append("'").append(dept_no).append("'");
		}else{
			for(int i=0;i<result.size();i++){
				DeptBean dept = (DeptBean)result.get(i);
				if(i+1 ==  result.size()){
					sbf.append("'").append(dept.getDeptno()).append("'").append(",").append("'").append(dept_no).append("'");
				}else{
					sbf.append("'").append(dept.getDeptno()).append("'").append(",");
				}
			}
		}
		return sbf.toString();
    }
    public static List getNodes(String dept_no,List allNode)
	{	
		List child= new ArrayList();
		List result = new ArrayList();
		for(int x=0;x<allNode.size();x++)
		{
			DeptBean node = (DeptBean)allNode.get(x);
			if(!node.getDept_level().equals("-1") && node.getDept_upperno().equals(dept_no))
				child.add(node);
			
		}
		result.addAll(child);
		for(int y=0;y<child.size();y++)
		{
			DeptBean node1 = (DeptBean)child.get(y);
			result.addAll(getNodes(node1.getDeptno(),allNode));
		}			
		return result;
	}
    
    /**
     * 
     * @TODO ����û�����Ϣ�б�
     *
     * @return �û����б�
     * @author ����
     * @version 1.0
     * <pre>
     * Created on:2009-11-3
     * LastModified:
     * History:
     * </pre>
     */
    public List getDeptListByUser(String dept_no_node) {
        sql = "select * from Mondeptinfo a ";
        sql = sql + "where a.dept_no in ("+dept_no_node+")";
        List list = new ArrayList();
        try {
            Vector deptVector = DbExec.execQuery(sql);
            if (deptVector != null && !deptVector.isEmpty()) {
                for (int i = 0; i < deptVector.size(); i++) {
                    DeptBean deptBean = new DeptBean((HashMap) deptVector.get(i));
                    list.add(deptBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }
    
    /**
     * 
     * @TODO �����û����Ż������û�����Ϣ (�޸��û���ǰʹ��)
     *
     * @param deptno
     * @return �û������
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public DeptBean getDeptByLevel(String dept_level, String dept_upperno, String dept_no) {
    	sql = "select * from Mondeptinfo t where t.dept_level < ? and t.dept_no != '000000000' and t.dept_no = ?";
    	String sql2 = "select * from Mondeptinfo t where t.dept_level <= ? and t.dept_upperno = ?";
    	String sql3 = "select * from Mondeptinfo t where t.dept_upperno = ? and t.dept_no = ?";
    	List condList = new ArrayList();
    	DeptBean deptBean = null;
    	int count = 0;
    	try {
    		//���ϼ��û���Ϊ-1�������û���Ϊһ���û��飬��ֻ�����޸ģ�����������
    		if ("-1".equals(dept_upperno)) {
				deptBean = new DeptBean();
    			condList.add(dept_upperno);
    	    	condList.add(dept_no);
    			Vector deptVector = DbExec.execQuery(sql3, condList);
    			if (deptVector != null && !deptVector.isEmpty()) {
    				//�����ǰ�������޸ģ����ж������뼶���Ƿ����������ߣ�������ߣ��򷵻ش���
    				condList = new ArrayList();
        	    	condList.add(dept_level);
        	    	condList.add(dept_no);
        	    	Vector vector = DbExec.execQuery(sql2, condList);
            		if (vector != null && !vector.isEmpty()) {
            			deptBean = new DeptBean((HashMap) deptVector.get(0));
            			deptBean.setResult("-1");
            			return deptBean;
            		}
            		deptBean.setResult("1");
    			}
    		} else {//���ϼ��û��鲻��-1�������ж���������ϼ��û���ļ����Ƿ��������ļ����
	    		condList = new ArrayList();
	        	condList.add(dept_level);
	        	condList.add(dept_upperno);
	    		Vector deptVector = DbExec.execQuery(sql, condList);
	    		if (deptVector != null && !deptVector.isEmpty()) {
	    			//����ߣ����ж������뼶���Ƿ����������ߣ�������ߣ��򷵻ش���
	    			condList = new ArrayList();
	    	    	condList.add(dept_level);
	    	    	condList.add(dept_no);
	    	    	Vector vector = DbExec.execQuery(sql2, condList);
	        		if (vector != null && !vector.isEmpty()) {
	        			deptBean = new DeptBean((HashMap) deptVector.get(0));
	        			deptBean.setResult("-1");
	        			return deptBean;
	        		}
	    			deptBean = new DeptBean((HashMap) deptVector.get(0));
	    			deptBean.setResult("0");
	    		}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		logger.error("SQLException:" + dept_level + "|", e);
    	}catch (Exception e) {
    		e.printStackTrace();
    		logger.error("Exception:" + dept_level + "|", e);
    	}
    	return deptBean;
    }

}
