package com.pay.batch.bflowlog.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.bean.MakeCardBean;
import com.pay.batch.bflowlog.dao.MakeCardDao;
import com.pay.batch.bflowlog.struts.form.MakeCardForm;
import com.pay.batch.bflowlog.tool.CallSocket;
import com.pay.batch.bflowlog.tool.CallSocketOfHousekeep;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

/**
 * Ǯ�����ƿ�
 * @author Administrator
 *
 */
public class MakeCardAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(MakeCardAction.class);

	public ActionForward getMakeCardList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MakeCardDao makeCardDao = new MakeCardDao();
		// �����ҳ����
		int count = makeCardDao.getCount(null, null);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List<MakeCardBean> makeCardList = makeCardDao.getMakeCardList(pageBean, null);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (makeCardList != null && !makeCardList.isEmpty()) {
			request.setAttribute("makeCardList", makeCardList);
		}
		request.setAttribute("queryflag", "0"); // ��ʾ���ǲ�ѯ
		return mapping.findForward("showMakeCardList");
	}

	public ActionForward queryMakeCardList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MakeCardDao makeCardDao = new MakeCardDao();	
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		MakeCardForm makeCardForm=(MakeCardForm)form;
		// �����ҳ����
		int count = makeCardDao.getCount(makeCardForm, null);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List<MakeCardBean> makeCardList = makeCardDao.getMakeCardList(pageBean, makeCardForm);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (makeCardList != null && !makeCardList.isEmpty()) {
			request.setAttribute("makeCardList", makeCardList);
		}
		return mapping.findForward("showMakeCardList");
	}

	public ActionForward showMakeCardInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MakeCardDao makeCardDao = new MakeCardDao();	
		List<MakeCardBean> makeCardList=makeCardDao.getMakeCardList("00");
		if (makeCardList != null && !makeCardList.isEmpty()) {
			request.setAttribute("makeCardList", makeCardList);
		}
		return mapping.findForward("addMakeCardInfo");
	}
	

	public ActionForward addPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String sendbuf = "";
		String panflagno = ParamUtils.getParameter(request, "batch"); // 11λ������
		
		if(panflagno==null||"".equals(panflagno)){
			MakeCardForm makeCardForm=(MakeCardForm)form;
			panflagno=makeCardForm.getBatch();
		}
		sendbuf="0009 "+panflagno.trim();
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
		request.setAttribute("bFlowLogBean", bean);
		return mapping.findForward("submitMakeCardinfo");
	}

}
