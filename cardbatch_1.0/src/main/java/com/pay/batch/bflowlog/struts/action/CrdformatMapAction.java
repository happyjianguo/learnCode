package com.pay.batch.bflowlog.struts.action;

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

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bflowlog.bean.CrdformatMapBean;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/**
 * ���´￨���⿨
 * 
 * @author Administrator
 * 
 */
public class CrdformatMapAction extends BaseDispatchAction {

	private static final Logger logger = Logger
			.getLogger(CrdformatMapAction.class);

	public ActionForward getCrdformatMapList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
			 // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			// �����ҳ����
			int count = CrdformatMapDao.getCount(null, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);

			List crdformatMapList = null;
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			if(count>0){
				crdformatMapList=CrdformatMapDao.getCrdformatMapList(pageBean, null);
			}
			if (crdformatMapList != null && !crdformatMapList.isEmpty()) {
				request.setAttribute("crdformatMapList", crdformatMapList);
			}
			request.setAttribute("queryflag", "0"); // ��ʾ���ǲ�ѯ
		} catch (Exception e) {
			logger.error("CrdformatMapAction--getCrdformatMapList--Exception:",
					e);
		}
		return mapping.findForward("showCrdformatMapList");
	}

	public ActionForward queryCrdformatMapList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// ������ҳǰ���Ѳ�ѯ��������Ϊ��
		try {
			// ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
			// ����ն���Ϣ�б��¼
			CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			
			int count = CrdformatMapDao.getCount(crdformatMapForm, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);

			request.setAttribute("pageBean", pageBean);
			List crdformatMapList = null;
			if (count > 0)
				crdformatMapList = CrdformatMapDao.getCrdformatMapList(
						pageBean, crdformatMapForm);
			if (crdformatMapList != null && !crdformatMapList.isEmpty()) {
				request.setAttribute("crdformatMapList", crdformatMapList);
			}
		} catch (Exception e) {
			logger.error(
					"CrdformatMapAction--queryCrdformatMapList--Exception:", e);
		}
		return mapping.findForward("showCrdformatMapList");
	}

	public ActionForward preQueryCrdformatMap(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String iid = ParamUtils.getParameter(request, "id");
		String func_flag = ParamUtils.getParameter(request, "funcFlag");

		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		CrdformatMapBean crdformatMapBean = crdformatMapDao
				.getCrdformatMapByID(iid, func_flag);		
		if (crdformatMapBean != null) {
			CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
			try {
				BeanUtils.copyProperties(crdformatMapForm, crdformatMapBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
			setTwoKaBinListBak(request);
		}
		return mapping.findForward("showCrdformatMapInfo");
	}

	/**
	 * ��ʼ�������̻�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddCrdformatMap(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// ��ȡ������crdformatMap List
		setTwoKaBinList(request);
		return mapping.findForward("addCrdformatMap");
	}

	/**
	 * ����̻���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addCrdformatMapInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {		
		try{
			String info = "";
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
			CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
			CrdformatMapBean crdformatMapBean = new CrdformatMapBean();
			String iidStr=crdformatMapForm.getIid();
			String iid="";
			String needDt="0";
			if(iidStr!=null&&!"".equals(iidStr)&&iidStr.contains("|")){
				String str[]=iidStr.split("\\|");
				if(str.length>1){
					needDt=str[1].toString();
					if(needDt!=null&&!"".equals(needDt)){
						needDt="1";
					}
				}
				iid=str[0].toString();
			}
			String iidMap=crdformatMapForm.getIid_map();
			if(iidMap!=null&&!"".equals(iidMap)&&iidMap.contains("|")){
				iidMap=iidMap.split("\\|")[0].toString();
			}			
			crdformatMapBean.setIid(iid);
			crdformatMapBean.setIid_map(iidMap);
			crdformatMapBean.setFunc_flag(crdformatMapForm.getFunc_flag());
			crdformatMapBean.setIsuse(crdformatMapForm.getIsuse());
			crdformatMapBean.setNeedDt(needDt);
			
			String flushdo = "closewindow";
	        if (UserUtils.getUserName()!=null) {     
				int result = CrdformatMapDao.addCrdformatMap(crdformatMapBean);
		        if (result >= 0) {
					info = "��bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")ӳ����ӳɹ���";
		            request.setAttribute("result", "0");
		        } else {
					info = "���ݿ��쳣����bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")ӳ�����ʧ�ܣ�";
		            request.setAttribute("result", "1");
		        }
		        request.setAttribute("info", StringUtils.outerToInner(info));
		        request.setAttribute("flushdo", flushdo);
	        } else {
	        	 request.setAttribute("info", "������ʱ�������µ�¼��");
		        request.setAttribute("flushdo", flushdo);
	            request.setAttribute("result", "1");
	        }
	        
		} catch (Exception e) {
			logger.error(
					"CrdformatMapAction--addCrdformatMapInfo--Exception:", e);
		}
		
       return mapping.findForward("submitinfo");	

       }

	/**
	 * @TODO ׼���޸��ն���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward preModCrdformatMap(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String iid = ParamUtils.getParameter(request, "id");
		String func_flag = ParamUtils.getParameter(request, "funcFlag");
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		CrdformatMapBean crdformatMapBean = crdformatMapDao
				.getCrdformatMapByID(iid, func_flag);
		if (crdformatMapBean != null) {
			CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
			try {
				BeanUtils.copyProperties(crdformatMapForm, crdformatMapBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
			setTwoKaBinListBak(request);
		}		
		return mapping.findForward("editCrdformatMapInfo");
	}

	public ActionForward modCrdformatMapInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
		CrdformatMapBean crdformatMapBean = new CrdformatMapBean();
		String iidStr=crdformatMapForm.getIid();
		String iid="";
		String needDt="0";
		if(iidStr!=null&&!"".equals(iidStr)&&iidStr.contains("|")){
			String str[]=iidStr.split("\\|");
			if(str.length>1){
				needDt=str[1].toString();
				if(needDt!=null&&!"".equals(needDt)){
					needDt="1";
				}
			}
			iid=str[0].toString();
		}	else{
			iid=iidStr;
		}
		
		crdformatMapBean.setIid(iid);
		if(crdformatMapForm.getIid_map()!=null){
			crdformatMapBean.setIid_map(crdformatMapForm.getIid_map());
		}
		crdformatMapBean.setFunc_flag(crdformatMapForm.getFunc_flag());
		crdformatMapBean.setIsuse(crdformatMapForm.getIsuse());
		crdformatMapBean.setNeedDt(needDt);
		
		String flushdo = "/crdformatMap.do?method=getCrdformatMapList";
        if (UserUtils.getUserName()!=null) {    
    		int result = crdformatMapDao.updCrdformatMap(crdformatMapBean);
	        if (result >= 0) {
				info = "��bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")ӳ���޸ĳɹ���";
	            request.setAttribute("result", "0");
	        } else {
				info = "���ݿ��쳣����bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")ӳ���޸�ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	 request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("submitinfo");	

	}
	/**
	 * ����������bin LIST
	 * @param request
	 */
	public void setTwoKaBinList(HttpServletRequest request){
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<CrdformatMapBean> kaBinList=crdformatMapDao.getCrdformatMapHasNeedDtList(null);
		List<CrdformatMapBean> kaBinListTwo=crdformatMapDao.getCrdformatMapList(null);
		request.setAttribute("kaBinList", kaBinList);
		request.setAttribute("kaBinListTwo", kaBinListTwo);
	}
	/**
	 * ����������bin LIST
	 * @param request
	 */
	public void setTwoKaBinListBak(HttpServletRequest request){
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<CrdformatMapBean> kaBinList=crdformatMapDao.getCrdformatMapList(null);
		List<CrdformatMapBean> kaBinListTwo=crdformatMapDao.getCrdformatMapList(null);
		request.setAttribute("kaBinList", kaBinList);
		request.setAttribute("kaBinListTwo", kaBinListTwo);
	}	
	/**
	 * ɾ��CrdformatMap
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delCrdformatMapInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		String iidStr= ParamUtils.getParameter(request, "id");
		String iid="";
		String funcFlag="0";
		if(iidStr!=null&&!"".equals(iidStr)&&iidStr.contains("|")){
			String str[]=iidStr.split("\\|");
			if(str.length>1){
				funcFlag=str[1].toString();
			}
			iid=str[0].toString();
		}else{
			iid=iidStr;
		}

		int result = crdformatMapDao.delCrdformatMap(iid,funcFlag);
		if (result >= 0) {
			info = "��bin("+iid+","+funcFlag+")ӳ��ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣����bin("+iid+","+funcFlag+")ӳ��ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetCrdformatMapList");
	}	
}
