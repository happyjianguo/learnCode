package com.pay.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
/**
 * 利用java反射机制为javabean赋值
 * 
 * */
public class RecordMethod {
	private HashMap record;
	private Class objClass;
	private Object obj;
	public RecordMethod()
	{
		
	}
   public RecordMethod(HashMap record,Class objClass,Object obj)
    {
	   this.record=record;
	   this.objClass=objClass;
	   this.obj=obj;
    }
    public void recordset()
    {
    	try{
    		 Method method = HashMap.class.getMethod("get",new Class[] { Object.class });		
    		 Field[] field=objClass.getDeclaredFields();		
            for(Field f:field){            	 
            	 String str = (String) method.invoke(record, f.getName());
            	 setGetMethod(objClass,f.getName()).invoke(obj, str);                
              } 
        }catch(Exception e){
    	   e.printStackTrace();
        }
    }
	private  Method getGetMethod(Class objectClass, String fieldName) {
			StringBuffer sb = new StringBuffer();
			sb.append("get");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			try {
				return objectClass.getMethod(sb.toString());
			} catch (Exception e) {
	            e.printStackTrace();
	            return null;
			}
		}
	private  Method setGetMethod(Class objectClass, String fieldName) {
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			try {
				return objectClass.getMethod(sb.toString(),new Class[] { String.class });
			} catch (Exception e) {
	            e.printStackTrace();
	            return null;
			}
		}
	//利用反射根据bean中的字段 设置session
	public void sessionSet(HttpSession session ,Class objClass,Object obj){
		Field[] field=objClass.getDeclaredFields();		
		Method  m;
		String str="";
        for(Field f:field){            	 
        	try {
				m=getGetMethod(objClass,f.getName());
			    str=(String)m.invoke(obj);
				if(str!=null&&!"".equals(str)&&!"null".equals(str)){
					session.setAttribute(f.getName(), str);
				}
			} catch (Exception e) {
	            e.printStackTrace();
	           
			}
        	
        	
        	              
          } 
	}
	//利用反射根据bean中的字段 得到session的值
	public void sessionGet(HttpSession session ,Class objClass,Object obj){
		Field[] field=objClass.getDeclaredFields();		
		for(Field f:field){   
			String str=(String)session.getAttribute(f.getName());
			if(str!=null&&!"".equals(str)&&!"null".equals(str)){
				try {
				  setGetMethod(objClass,f.getName()).invoke(obj, str); 
				} catch (Exception e) {
		            e.printStackTrace();
		           
				}
			}			
		}
	}
	//利用反射根据bean中的字段 删除session的值
	public void sessionRemove(HttpSession session ,Class objClass){
		Field[] field=objClass.getDeclaredFields();	
		for(Field f:field){
			String str=(String)session.getAttribute(f.getName());
			if(str!=null&&!"".equals(str)&&!"null".equals(str)){				
				session.removeAttribute(f.getName());
			}
		}
	}
}
