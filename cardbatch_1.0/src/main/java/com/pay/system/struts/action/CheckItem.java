package com.pay.system.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class CheckItem extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String itemno = ParamUtils.getParameter(request, "itemno").trim();
		String flashdo = "resultpage";
		
		//�û�����
		if ("0101".equals(itemno)) {
			flashdo = "101.do";//�û�����
		} else if ("0102".equals(itemno)) {
			flashdo = "102.do";//�û������
		} else if ("0103".equals(itemno)) {
			flashdo = "103.do";//��ɫ����
		} else if ("0104".equals(itemno)) {
			flashdo = "104.do";//Ȩ�޹���
			
		//��ز�ѯ
		} else if ("0201".equals(itemno)) {
			flashdo = "201.do";//���׼��
		} else if ("0202".equals(itemno)) {
			flashdo = "202.do";//��ʷ��ˮ��ѯ
			//����
		} else if ("0211".equals(itemno)) {
			flashdo = "211.do";//�̻���ϸ
		} else if ("0212".equals(itemno)) {
			flashdo = "212.do";//�̻�����
		} else if ("0213".equals(itemno)) {
			flashdo = "213.do";//EPS����
		} else if ("0214".equals(itemno)) {
			flashdo = "214.do";//��ϵͳ����
		} else if ("0215".equals(itemno)) {
			flashdo = "215.do";//EPS�������嵥
		} else if ("0216".equals(itemno)) {
			flashdo = "216.do";//EPSʱ�����ϸ
		} else if ("0217".equals(itemno)) {
			flashdo = "217.do";//��ϵͳ�������嵥
		} else if ("0218".equals(itemno)) {
			flashdo = "218.do";//��ϵͳʱ�����ϸ
		} else if ("0219".equals(itemno)) {
			flashdo = "219.do";//���̻�����ͳ��ʱ���
		} else if ("0220".equals(itemno)) {
			flashdo = "220.do";//ICS������ϸ
			
		//���׿ع�
		} else if ("0301".equals(itemno)) {
			flashdo = "301.do";//�����Ա����
		} else if ("0302".equals(itemno)) {
			flashdo = "302.do";//���ι���
		} else if ("0303".equals(itemno)) {
			flashdo = "303.do";//����������
		} else if ("0304".equals(itemno)) {
			flashdo = "304.do";//������������
			
		//�̻��ն˹���
		} else if ("0401".equals(itemno)) {
			flashdo = "401.do";//�̻�����
		} else if ("0402".equals(itemno)) {
			flashdo = "402.do";//δ�����̻�����
		} else if ("0403".equals(itemno)) {
			flashdo = "403.do";//�Ѽ����̻�����
			
		} else if ("0411".equals(itemno)) {
			flashdo = "411.do";//�ն˵���
		} else if ("0412".equals(itemno)) {
			flashdo = "412.do";//δ�����ն˹���
		} else if ("0413".equals(itemno)) {
			flashdo = "413.do";//�Ѽ����ն˹���			
		
		//ϵͳ����
		}else if("0501".equals(itemno)){
			flashdo="501.do";//ϵͳ����
			
		//����Ŀ����
		}else {
			request.setAttribute("result", "-1");
			request.setAttribute("info", StringUtils.outerToInner("�޴�Ȩ��!!"));
			request.setAttribute("flushdo", "/jsppage/common/right.jsp");
		}
		return mapping.findForward(flashdo);
	}
}
