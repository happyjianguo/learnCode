/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat; 

import cn.yufu.system.common.utils.SpringContextHolder;
import cn.yufu.system.modules.sys.dao.UserDao;
import cn.yufu.system.modules.sys.entity.User;

/**
 * 密码失效工具类
 * @author king
 * @version 2014-11-7
 */
public class PwdInvalidUtils {
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	
	/**
	 * 获取用户列表 修改用户密码状态
	 */
	public static void executeUpdateUserPwdIsReset(){

		new UpdateUserPwdIsResetThread().start();
	}

	/**
	 * 获取用户列表 修改用户密码状态线程
	 */
	public static class UpdateUserPwdIsResetThread extends Thread{
		
		public UpdateUserPwdIsResetThread(){}
		
		@Override
		public void run() {
			updateUserPwdIsReset();
		}
	}

	/**
	 * 获取用户列表 修改用户密码状态
	 */
	public static void updateUserPwdIsReset(){
		
		List<User> userList = userDao.findUser();
		if (userList != null) {
			System.out.println("用户数量" + userList.size());
		}
		
	    Calendar canlendar = Calendar.getInstance();
	    Date now = canlendar.getTime();	  
		
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			
			if (user != null && user.getPwdCreateTime() == null) {
				continue;
			}
			
			int datsBetween = 0;
			try {
				datsBetween = daysBetween(user.getPwdCreateTime(),now);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (datsBetween>=50) {
				user.setIsResetPwd(1);
				userDao.update(user);
				
				System.out.println("用户是否重置密码" + user.getIsResetPwd());
			}			
		}
	}
	
	/**
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws Exception  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws Exception{ 
    	
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
 
        if (smdate != null) {
            smdate=sdf.parse(sdf.format(smdate));  
		}
        bdate=sdf.parse(sdf.format(bdate)); 
        
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis(); 
        
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
}
