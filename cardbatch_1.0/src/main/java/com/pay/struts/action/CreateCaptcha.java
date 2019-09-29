package com.pay.struts.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.util.RandImgCreater;
//生成验证码图片 
public class CreateCaptcha extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		RandImgCreater creater = new RandImgCreater();
		creater.createRandImage();
		String captcha = creater.getCode();//取得验证码;
		session.setAttribute("captcha", captcha);
		ServletOutputStream out = response.getOutputStream();
		showImage(creater, out);
		return null;
	}
	
	private void showImage(RandImgCreater creater,ServletOutputStream out){
		ByteArrayInputStream bais = creater.getStream();
		byte [] image = new byte[bais.available()];
		int len = 0;
		try {
			while((len=bais.read(image))!=-1)
			   {
			   out.write(image,0,len);
			   }
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
