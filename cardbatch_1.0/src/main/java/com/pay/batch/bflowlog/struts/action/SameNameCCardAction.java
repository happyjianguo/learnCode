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

import com.pay.batch.bflowlog.bean.BFlowBean;
import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.dao.BFlowDao;
import com.pay.batch.bflowlog.dao.BFlowLogDao;
import com.pay.batch.bflowlog.struts.form.BFlowLogForm;
import com.pay.batch.bflowlog.tool.CallSocketOfHousekeep;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

/**
 * ͬ������
 * @author Administrator
 *
 */
public class SameNameCCardAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(SameNameCCardAction.class);

	public ActionForward getBFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		// �����ҳ����
		int count = bflowlogDao.getSameNameCount(null, null);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List bflowlogList = bflowlogDao.querySameNameList(pageBean, null, null);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		request.setAttribute("queryflag", "0"); // ��ʾ���ǲ�ѯ
		return mapping.findForward("showSNCCardPanFlowLogList");
	}

	public ActionForward queryBFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		String pan = ParamUtils.getParameter(request, "batchflag");
		String stat = ParamUtils.getParameter(request, "issucess");		
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// �����ҳ����
		int count = bflowlogDao.getSameNameCount(pan,stat);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List bflowlogList = bflowlogDao.querySameNameList(pageBean, pan,stat);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		return mapping.findForward("showSNCCardPanFlowLogList");
	}

	public ActionForward showBFlowLogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pan = ParamUtils.getParameter(request, "pan");
		String panflagno = ParamUtils.getParameter(request, "panflagno");
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		BFlowLogBean bflowlogBean = bflowlogDao.getSameNameInfo(pan,panflagno);
		BFlowLogForm bflowlogForm=new BFlowLogForm();
        try {
			BeanUtils.copyProperties(bflowlogForm, bflowlogBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("bflowlogForm", bflowlogForm);	
		return mapping.findForward("showSNCCardPanFlowLogInfo");
	}
	
	public ActionForward preAddPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pan = ParamUtils.getParameter(request, "pan");
		String panflagno = ParamUtils.getParameter(request, "panflagno");

		BFlowLogDao bflowlogDao = new BFlowLogDao();
		BFlowLogBean bflowlogBean = bflowlogDao.getSameNameInfo(pan,panflagno);
		BFlowLogForm bflowlogForm=new BFlowLogForm();
        try {
			BeanUtils.copyProperties(bflowlogForm, bflowlogBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("bflowlogForm", bflowlogForm);	
		return mapping.findForward("addSNCCardPanInfo");
	}

	public ActionForward addPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String sendbuf = "";
		String panflagno = ParamUtils.getParameter(request, "panflagno"); // 11λ������
		String pan=ParamUtils.getParameter(request, "batchflag"); // 16λ��������
		String yeardate = ParamUtils.getParameter(request, "yeardate"); // ����Ч��
		
		sendbuf = "0008 " + panflagno + " " + pan+" "+yeardate;
		
		//������Ϣ���浽���ݿ�
		BFlowDao bflowDao=new BFlowDao();
		if(bflowDao.getBFlowBean(panflagno)==null){
			BFlowBean bflowBean=new BFlowBean();
			bflowBean.setPanflagno(panflagno);
			bflowBean.setBatchflag("snCCardbatch");
			bflowBean.setSendbuf(sendbuf);
			bflowDao.addBFlowBean(bflowBean);	
		}
		
		CallSocketOfHousekeep callsocket = new CallSocketOfHousekeep();
		String ip = SystemConfig.getValue("socketipOfHousekeep");
		String sport = SystemConfig.getValue("socketportOfHousekeep");
		int port = 0;
		try {
			port = Integer.parseInt(sport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = callsocket.execShell(ip, port, sendbuf);
			System.out.println("callsocket.execShell  ip:"+ip+"  port:"+port);
			System.out.println("sendbuf:"+sendbuf);
		} catch (Exception e) {
			e.printStackTrace();
			result = -2;
		}

		String info = "";
		String flushdo = "closewindow";

		if (UserUtils.getUserName()!=null) {

			if (result >= 0) {
				info = "shell���óɹ����ű�����ִ�У������ĵȴ�������ţ���" + panflagno + "��";
				request.setAttribute("result", "0");
			} else {
				info = "����ʧ�ܣ�����ţ���" + panflagno + "��";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		BFlowLogBean bean = new BFlowLogBean();
		bean.setPanflagno(panflagno);
		bean.setBatchflag("mcpanbatch");
		request.setAttribute("bFlowLogBean", bean);

		return mapping.findForward("submitSNCCardinfo");
	}
	
	public ActionForward addPanInfoAgain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String info = "";
		String sendbuf = "";
		String panflagno = ParamUtils.getParameter(request, "panflagno"); 
		if(panflagno!=null&&!"".equals(panflagno)){
			BFlowDao bflowDao=new BFlowDao();
			BFlowBean bflowBean=bflowDao.getBFlowBean(panflagno);
			if(bflowBean!=null&&bflowBean.getSendbuf()!=null&&!"".equals(bflowBean.getSendbuf())){
				sendbuf =bflowBean.getSendbuf();
				
				CallSocketOfHousekeep callsocket = new CallSocketOfHousekeep();
				String ip = SystemConfig.getValue("socketipOfHousekeep");
				String sport = SystemConfig.getValue("socketportOfHousekeep");
				int port = 0;
				try {
					port = Integer.parseInt(sport);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int result = 0;
				try {
					result = callsocket.execShell(ip, port, sendbuf);
					System.out.println("callsocket.execShell  ip:"+ip+"  port:"+port);
					System.out.println("sendbuf:"+sendbuf);
				} catch (Exception e) {
					e.printStackTrace();
					result = -2;
				}
				
				String flushdo = "closewindow";

				if (session != null && session.getAttribute("usercode") != null) {
					if (result >= 0) {
						info = "shell���óɹ����ű�����ִ�У������ĵȴ�������ţ���" + panflagno + "��";
						request.setAttribute("result", "0");
					} else {
						info = "����ʧ�ܣ�����ţ���" + panflagno + "��";
						request.setAttribute("result", "1");
					}
					request.setAttribute("info", StringUtils.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				} else {
					request.setAttribute("info", "������ʱ�������µ�¼��");
					request.setAttribute("flushdo", flushdo);
					request.setAttribute("result", "1");
				}
				BFlowLogBean bean = new BFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag("mcpanbatch");
				request.setAttribute("bFlowLogBean", bean);

				return mapping.findForward("submitMCinfo");
			}else{
				info="�����ݿ���δ�ҵ������Ϊ"+panflagno+"���͸�cortexϵͳ�ķ��ͱ���";
				request.setAttribute("info",info);
				request.setAttribute("flushdo", "closewindow");				
				return mapping.findForward("submitinfo");				
			}
		}else{
			info="δ�ҵ���Ӧ�������"+panflagno;
			request.setAttribute("info",info);			
			request.setAttribute("flushdo", "closewindow");			
			return mapping.findForward("submitinfo");
		}
		
	}
}
