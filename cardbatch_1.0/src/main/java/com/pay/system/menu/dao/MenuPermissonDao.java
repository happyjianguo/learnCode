package com.pay.system.menu.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.menu.bean.MenuBean;
import com.pay.system.menu.bean.ResourceModel;
import com.pay.system.user.bean.UserBean;
import com.pay.system.user.dao.UserDao;
import com.pay.util.DbExec;
import com.pay.util.SystemConfig;

public class MenuPermissonDao {
	 	private static final Logger logger = Logger.getLogger(MenuPermissonDao.class);
	    private String sql = "";
	    private int result = 0;
	    //得到所有管理员的菜单
	    public List getMenuByMenuno() {
	        sql = "select m.MENU_NO,m.MENU_NAME,m.MENU_LEVEL,MENU_PARENTNO,MENU_PATH,MENU_DESC,USER_CODE from MonMenuInfo m,MonRoleMenu rm,MonUserInfo  u where  m.MENU_NO = rm.MENU_NO and rm.role_no=u.role_no order by rm.menu_no asc";
	        List beanList = new ArrayList(); 	        
	        MenuBean menuBean = null;
	        try {
	            Vector menuVector = DbExec.execQuery(sql);
	            if (menuVector != null && !menuVector.isEmpty()) {
	            	for(int i=0;i<menuVector.size();i++){
	            	  menuBean = new MenuBean((HashMap) menuVector.get(i));
	            	  beanList.add(menuBean); 
	            	}
	            }
	        } catch (SQLException e) {
	            logger.error("SQLException:", e);
	        }catch (Exception e) {
	            logger.error("Exception:", e);
	        }
	        return beanList;
	    }
	    //得到所有某一个管理员的菜单
	    public List getMenuByMenuno(String usercode) {
	        sql = "select m.MENU_NO,m.MENU_NAME,m.MENU_LEVEL,MENU_PARENTNO,MENU_PATH,MENU_DESC,USER_CODE from MonMenuInfo m,MonRoleMenu rm,MonUserInfo  u where  m.MENU_NO = rm.MENU_NO and rm.role_no=u.role_no and u.user_code='"+usercode+"' order by rm.menu_no desc";
	        List beanList = new ArrayList();	        
	        MenuBean menuBean = null;
	        try {
	            Vector menuVector = DbExec.execQuery(sql);
	            if (menuVector != null && !menuVector.isEmpty()) {
	            	for(int i=0;i<menuVector.size();i++){
	            	  menuBean = new MenuBean((HashMap) menuVector.get(i));
	            	  beanList.add(menuBean); 
	            	}
	            }
	        } catch (SQLException e) {
	            logger.error("SQLException:", e);
	        }catch (Exception e) {
	            logger.error("Exception:", e);
	        }
	        return beanList;
	    }
	  //根据菜单集合得到所有某一个管理员的菜单
	    public List getMenuByMenuno(List lstmenu,String usercode) {	       
	        List beanList = new ArrayList();	        
	        MenuBean menuBean = null;
	        try {	            
	            if (lstmenu != null && !lstmenu.isEmpty()) {
	            	for(int i=0;i<lstmenu.size();i++){
	            	  menuBean = (MenuBean)lstmenu.get(i);
	            	  beanList.add(menuBean); 
	            	}
	            }	       
	        }catch (Exception e) {
	            logger.error("Exception:", e);
	        }
	        return beanList;
	    }
	    //从读出的所有数据中整理某一用户的所有菜单
	   private  List getListMenuByUsercode(String usercode,List beanList){
	    	if(beanList==null||beanList.isEmpty()) return new ArrayList();
	    	List<MenuBean> parentList=new ArrayList<MenuBean>();
	    	List childList=new ArrayList();
	    	List<String> parentMenu_nos = Arrays.asList(SystemConfig.getValue("menu_nos").split("\\|"));
	    	Map<Integer,MenuBean> menubeans = new TreeMap<Integer,MenuBean>();
	    	for(int i=0;i<beanList.size();i++){
	    		MenuBean bean=(MenuBean)beanList.get(i);
	    		
	    		if(bean.getUsercode().equals(usercode)){ //得到某一用户
	    			if("0".equals(bean.getMenuparent())){
	    				
	    				int index = parentMenu_nos.indexOf(bean.getMenuno());
	    				if(index>-1){
	    					menubeans.put(index, bean);
	    				}
	    			}else{
	    				childList.add(bean);
	    			}
	    		}
	    	}
	    	Set<Map.Entry<Integer,MenuBean>> set = menubeans.entrySet();
	    	for(Map.Entry<Integer,MenuBean> entry:set){
	    		parentList.add(entry.getValue());
	    	}
	    	for(int i=0;i<parentList.size();i++){
	    		MenuBean bean=(MenuBean)parentList.get(i);
	    		List childPidlst=this.getListChildMenuByParentNo(childList, bean.getMenuno());
	    		bean.setLst(childPidlst);
	    	}
	    	return parentList;
	    }
	    //根据父id得到子菜单集合
	    private List getListChildMenuByParentNo(List childList,String parentNo){
	    	if(childList==null||childList.isEmpty()||parentNo==null) return new ArrayList(); 
	    	List childPidList=new ArrayList();
	    	for(int i=0;i<childList.size();i++){
	    		MenuBean bean=(MenuBean)childList.get(i);
	    		if(parentNo.equals(bean.getMenuparent())){
	    			ResourceModel mode=new ResourceModel(bean);
	    			childPidList.add(mode);
	    		}
	    	}
	    	return childPidList;
	    }
	    //根据数据库集合 生成权限字符串
	    private String getStrPermisson(String usercode){
	    	List lst=getMenuByMenuno(usercode);
			List parentList=getListMenuByUsercode(usercode,lst);
			StringBuffer sb=new StringBuffer("[");
			for(int i=0;i<parentList.size();i++){
				MenuBean bean=(MenuBean)parentList.get(i);
				ResourceModel mode=new ResourceModel(bean);				
				sb.append(mode.toJSONString());
				if(i<(parentList.size()-1)){
					sb.append(",");
				}
			}
			sb.append("]");
			
			return sb.toString();
	    }
	  //根据数据库集合 生成权限字符串
	    private String getStrPermisson(List lstallmenu,String usercode){
	    	List lst=getMenuByMenuno(lstallmenu,usercode);
			List parentList=getListMenuByUsercode(usercode,lst);
			StringBuffer sb=new StringBuffer("[");
			for(int i=0;i<parentList.size();i++){
				MenuBean bean=(MenuBean)parentList.get(i);
				ResourceModel mode=new ResourceModel(bean);				
				sb.append(mode.toJSONString());
				if(i<(parentList.size()-1)){
					sb.append(",");
				}
			}
			sb.append("]");
			//System.out.println(sb.toString());
			return sb.toString();
	    }
	    //生成权限文件 根据用户Usercode 
	    public int createPerFile(){
	    			
			List lstallmenu=getMenuByMenuno();
			List lstalluser=new UserDao().showAllUserList();
			for(int i=0;i<lstalluser.size();i++){
				UserBean userBean=(UserBean)lstalluser.get(i);
				String usercode=userBean.getUsercode();
				String fileName =SystemConfig.getValue("confurl")+"/WEB-INF/permissions/"+ usercode + ".json";					
			
			//得到权限的字符串
			String jsonmenu=getStrPermisson(lstallmenu,usercode);			
			PrintWriter  pw =null;
			try {					
					pw = new  PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, false), "GBK"), true);
					pw.print(jsonmenu);					
			}catch (Exception e){
					e.printStackTrace();
			} finally {
				if(pw != null){
				try {
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}					
				}	
			}
	    	return 0;
	    }

}
