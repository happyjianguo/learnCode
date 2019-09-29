package cn.yufu.posp.usermanager.web.action;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.web.form.OaUserForm;

public class Test {
	 public static void main(String args[]){
		 System.out.println("sssss");
		   OaUserForm pForm = new OaUserForm();
			//新建一个Model
			OaUserInfo noticeModel = new OaUserInfo();
		   try {
			BeanUtils.copyProperties(noticeModel, pForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("jjjjj");
	   }
}
