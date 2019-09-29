//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.batch.bmanger.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bmanger.bean.BMangerBean;
import com.pay.batch.bmanger.dao.BMangerDao;
import com.pay.batch.bmanger.struts.form.BMangerForm;
import com.pay.system.dept.dao.DeptDao;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class BMangerAction extends DispatchAction {

    private static final Logger logger = Logger.getLogger(BMangerAction.class);

    
    public ActionForward getBMangerList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        BMangerDao bmangerDao = new BMangerDao();
        HttpSession session = request.getSession();
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		 String dept_no_node = (String)session.getAttribute("dept_no_node");
         // �����ҳ����
        int count=bmangerDao.getCount(roleno,dept_no_node);
		 PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
        List bmangerList = bmangerDao.getBMangerList(roleno,dept_no_node,pageBean);
         // �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
       if (bmangerList != null && !bmangerList.isEmpty()) {
            request.setAttribute("bmangerList", bmangerList);
        }
       return mapping.findForward("showBMangerList");
    }

   

    /**
     * 
     * @TODO ����û�����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward addBManger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	BMangerForm bmangerForm = (BMangerForm) form;
       BMangerBean bmangerBean = new BMangerBean();
        try {
            BeanUtils.copyProperties(bmangerBean, bmangerForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        BMangerDao bmangerDao = new BMangerDao();

        String info = "";
        String flushdo = "/bmanger.do?method=getBMangerList";
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("deptno") != null) {
	        int result = bmangerDao.addBManger(bmangerBean);
	        if (result >= 0) {
	            info = "��������ӳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ�����쳣���û������ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }	
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
           request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼���޸��û�����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward preModBMangerInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        BMangerForm bmangerForm = (BMangerForm) form;
        String id = ParamUtils.getParameter(request, "id");
        DeptDao deptDao = new DeptDao();
        HttpSession session = request.getSession();
        String deptno = (String)session.getAttribute("deptno");
		 String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		 String dept_no_node = (String)session.getAttribute("dept_no_node");
        List deptList = null;   
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        BMangerDao bmangerDao = new BMangerDao();
        
        BMangerBean deptBean = bmangerDao.getBMangerInfo(id);     
        try {
            if (deptBean != null) {
                BeanUtils.copyProperties(bmangerForm, deptBean);              
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        request.setAttribute("deptnoList", deptList);
        return mapping.findForward("modBManger");
    }
    public ActionForward preAddBManger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        BMangerForm bmangerForm = (BMangerForm) form;
        
        
        DeptDao deptDao = new DeptDao();
        HttpSession session = request.getSession();
        String deptno = (String)session.getAttribute("deptno");
		 String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		 String dept_no_node = (String)session.getAttribute("dept_no_node");
        List deptList = null;   
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        request.setAttribute("deptnoList", deptList);
        return mapping.findForward("preAddBManger");
    }
    public ActionForward showBMangerInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 BMangerForm bmangerForm = (BMangerForm) form;
        String id = ParamUtils.getParameter(request, "id");
        DeptDao deptDao = new DeptDao();
        HttpSession session = request.getSession();
        String deptno = (String)session.getAttribute("deptno");
		 String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		 String dept_no_node = (String)session.getAttribute("dept_no_node");
        List deptList = null;   
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        BMangerDao bmangerDao = new BMangerDao();
        
        BMangerBean bmangerBean = bmangerDao.getBMangerInfo(id);     
//        try {
//            if (deptBean != null) {
//            	
//                BeanUtils.copyProperties(bmangerForm, deptBean);              
//            }
//        } catch (IllegalAccessException e) {
//            logger.error("IllegalAccessException", e);
//        } catch (InvocationTargetException e) {
//            logger.error("InvocationTargetException", e);
//        }
        request.setAttribute("deptnoList", deptList);
         request.setAttribute("bmangerBean", bmangerBean);
        return mapping.findForward("showBMangerInfo");
    }
    /**
     * 
     * @TODO �޸��û�����Ϣ 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward modBManger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        BMangerForm bmangerForm = (BMangerForm) form;
        BMangerBean bmangerBean = new BMangerBean();
        try {
            BeanUtils.copyProperties(bmangerBean, bmangerForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
       BMangerDao bmangerDao = new BMangerDao();

        String info = "";
        String flushdo = "/bmanger.do?method=getBMangerList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = bmangerDao.updateBManger(bmangerBean);
	        if (result >= 0) {
	            info = "�޸ĳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "�޸�ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO �����û�����ɾ���û�����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward delBMangerInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String id = ParamUtils.getParameter(request, "id");
        String batchflag= ParamUtils.getParameter(request, "batchflag");
        BMangerDao bmangerDao = new BMangerDao();

        String info = "";
        String flushdo = "/bmanger.do?method=getBMangerList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = bmangerDao.deleteBManger(id, batchflag);
	        if (result >= 0) {
	            info = "ɾ���ɹ���";
	            request.setAttribute("result", "0");
	        } else if (result == -2) {
	            info = "ɾ��ʧ�ܣ��ñ�ʶ�Ѿ�����־�޷�ɾ��������ɾ���ñ�־����־��";
	            request.setAttribute("result", "1");
	        } else {
	            info = "ɾ��ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

}
