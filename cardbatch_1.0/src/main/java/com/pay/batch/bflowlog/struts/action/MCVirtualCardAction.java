package com.pay.batch.bflowlog.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bflowlog.bean.BFlowBean;
import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.dao.BFlowDao;
import com.pay.batch.bflowlog.dao.BFlowLogDao;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.struts.form.BFlowLogForm;
import com.pay.batch.bflowlog.tool.CallSocketOfHousekeep;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.system.dept.dao.DeptDao;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

/**
 * 万事达卡虚拟卡
 * @author Administrator
 *
 */
public class MCVirtualCardAction extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(MCVirtualCardAction.class);
	public ActionForward getBFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		session.removeAttribute("execdate");
		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List bflowlogList = bflowlogDao.getBFlowLogList(roleno, dept_no_node,
				pageBean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		request.setAttribute("queryflag", "0"); // 表示不是查询
		return mapping.findForward("showMCPanFlowLogList");
	}

	public ActionForward queryBFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String execdate = ParamUtils.getParameter(request, "execdate");
		if (execdate == null || "".equals(execdate.trim())
				|| "null".equals(execdate.trim())) {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyyMMdd");
			execdate = (String) session.getAttribute("execdate");
			if (execdate == null || "".equals(execdate.trim())
					|| "null".equals(execdate.trim()))
				execdate = sdf.format(new Date());
		}
		session.setAttribute("execdate", execdate);
		execdate = execdate.replaceAll("-", "");
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		BFlowLogBean queryBean = new BFlowLogBean();
		queryBean.setExecdate(execdate);
		queryBean.setDatasource("0");// 批处理
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node, queryBean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List bflowlogList =  null;
		if(count>0)
			bflowlogList= bflowlogDao.queryBFlowLogList(roleno, dept_no_node,pageBean, queryBean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		request.setAttribute("queryflag", "1"); // 表示是查询
		request.setAttribute("execdate", execdate);
		return mapping.findForward("showMCPanFlowLogList");
	}

	public ActionForward showBFlowLogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DeptDao deptDao = new DeptDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		List deptList = null;
		if (roleno != null && roleno.equals("00")) {
			deptList = deptDao.getDeptList(roleno);
		} else {
			deptList = deptDao.getDeptListByUser(dept_no_node);
		}
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		BFlowLogBean bflowlogBean = bflowlogDao.getBFlowLogInfo(id);
		request.setAttribute("deptnoList", deptList);
		request.setAttribute("bflowlogBean", bflowlogBean);
		return mapping.findForward("showMCBFlowLogInfo");
	}

	public ActionForward showPanFlowLogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DeptDao deptDao = new DeptDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		List deptList = null;
		if (roleno != null && roleno.equals("00")) {
			deptList = deptDao.getDeptList(roleno);
		} else {
			deptList = deptDao.getDeptListByUser(dept_no_node);
		}
		BFlowLogDao bflowlogDao = new BFlowLogDao();

		BFlowLogBean bflowlogBean = bflowlogDao.getBFlowLogInfo(id);
		String execinfo = bflowlogBean.getExecinfo();
		String execinfos[] = execinfo.split(",");

		bflowlogBean.setLogfilepath(execinfos[0]);
		String fileinfo = "";
		List<String> lstexecinfo = new ArrayList<String>();
		for (int i = 1; i < execinfos.length; i++) {
			fileinfo += execinfos[i] + ",";
			lstexecinfo.add(execinfos[i]);
		}
		bflowlogBean.setExecinfo(fileinfo);
		request.setAttribute("deptnoList", deptList);
		request.setAttribute("bflowlogBean", bflowlogBean);
		request.setAttribute("lstexecinfo", lstexecinfo);
		request.setAttribute("panflagno", bflowlogBean.getPanflagno());
		String errMsg=execinfo;
		if(errMsg!=null&&!"".equals(errMsg)&&errMsg.contains("err")){
			String errNo=errMsg.substring(errMsg.indexOf("err")+3, errMsg.indexOf("err")+5);
			SysParameterDao sysParameterDao=new SysParameterDao();
			SysParameterBean sysParameterBean=sysParameterDao.getSysParameterByTypeAndValue("HOUSEKEEP_ERR_NO", errNo);
			if(sysParameterBean!=null){
				errMsg=sysParameterBean.getParam_name();				
			}
			errMsg="失败原因:"+errMsg;
		}else{
			errMsg="";
		}
		request.setAttribute("errMsg", errMsg);
		return mapping.findForward("showMCPanFlowLogInfo");
	}

	public ActionForward execshell(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String sendbuf = "";
		String logid = ParamUtils.getParameter(request, "logid");
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		BFlowLogBean bflowlogBean = bflowlogDao.getBFlowLogInfo(logid);
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyMMddHHmmss");
		String panflagno = sdf.format(new Date());
		sendbuf = bflowlogBean.getSocketflag() + " "
				+ bflowlogBean.getSrcstartflag() + " "
				+ bflowlogBean.getSrcendflag() + " " + panflagno;
		System.out.println("-------------" + sendbuf + "--------------------");
		// //创建多线程调用shell脚本
		// DepreSingle.getInstance().init();
		// Thread t=new Thread(new CallShellThread(command,logfile));
		// t.start();
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
		} catch (Exception e) {
			e.printStackTrace();
			result = -2;
		}

		String info = "";
		String flushdo = "closewindow";
		if (UserUtils.getUserName()!=null) {
			if (result >= 0) {
				info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【" + panflagno + "】";
				request.setAttribute("result", "0");
			} else {
				info = "调用失败！受理号：【" + panflagno + "】";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "操作超时，请重新登录！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		BFlowLogBean bean = new BFlowLogBean();
		bean.setPanflagno(panflagno);
		bean.setBatchflag(bflowlogBean.getBatchflag());
		request.setAttribute("bFlowLogBean", bean);

		return mapping.findForward("submitMCinfo");
	}

	public ActionForward preShowPanFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		BFlowLogBean bean = new BFlowLogBean();
		bean.setDatasource("1");// 发卡来源
		bean.setBatchflag("0007");//类型：万事达虚拟卡 socketflag 为0007
		
		BFlowLogForm bflowlogForm=(BFlowLogForm)form;
		bean.setPanflagno(bflowlogForm.getPanflagno());
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		
		List bflowlogList = null;
		if(count>0)
			bflowlogList =bflowlogDao.queryBFlowLogList(roleno, dept_no_node,
					pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		if(request.getAttribute("info")!=null){
			BFlowLogForm BFlowLogForm=(BFlowLogForm)form;
			BFlowLogForm.setPanflagno("");
			request.setAttribute("info", request.getAttribute("info").toString());
		}
		return mapping.findForward("showMCPanFlowLogList");
	}

	public ActionForward queryShowPanFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		String panflagno = ParamUtils.getParameter(request, "panflagno");
		BFlowLogBean bean = new BFlowLogBean();
		bean.setDatasource("1");// 发卡来源
		bean.setPanflagno(panflagno);
		bean.setBatchflag("0007");//类型：万事达虚拟卡 socketflag 为0007

		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List bflowlogList = bflowlogDao.queryBFlowLogList(roleno, dept_no_node,
				pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}

		return mapping.findForward("showMCPanFlowLogList");
	}

	public ActionForward preAddPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<BFlowLogBean> lst = crdformatMapDao.getCrdformatMapListByForm("1");
		request.setAttribute("kabinList", lst);
		return mapping.findForward("addMCPanInfo");
	}

	public ActionForward addPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String sendbuf = "";
		String kabin = ParamUtils.getParameter(request, "batchflag"); // 福卡卡bin
        if(kabin!=null&&!"".equals(kabin)&&kabin.contains("|")){
        	
        	kabin=kabin.substring(0, kabin.indexOf("|"));
        }
		String mcVirtualBin=ParamUtils.getParameter(request, "srcstartflag"); // 万事达卡卡bin
		if(mcVirtualBin!=null&&!"".equals(mcVirtualBin)&&mcVirtualBin.contains("|")){
			mcVirtualBin=mcVirtualBin.substring(0,mcVirtualBin.indexOf("|"));
		}
		String pannum = ParamUtils.getParameter(request, "execinfo"); // 卡数量
		//String flagnum = ParamUtils.getParameter(request, "flagnum"); // 卡号是否允许带4  :0 不允许 ; 1 允许
		String yeardate = ParamUtils.getParameter(request, "yeardate"); // 福卡有效期（非必填）
		String mcyeardate = ParamUtils.getParameter(request, "addtime"); // 虚拟卡有效期（必填）
        String defaultDate=SystemConfig.getValue("default_date");
        if(yeardate==null||"".equals(yeardate)){
        	yeardate=defaultDate;
        }        
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		String panflagno = sdf.format(new Date());
		sendbuf = "0007 " + panflagno + " " + kabin+" "+mcVirtualBin+ " " + pannum + " " + yeardate+ " " + mcyeardate;
		
		//发送信息保存到数据库
		BFlowDao bflowDao=new BFlowDao();
		BFlowBean bflowBean=new BFlowBean();
		bflowBean.setPanflagno(panflagno);
		bflowBean.setBatchflag("mcpanbatch");
		bflowBean.setSendbuf(sendbuf);
		bflowDao.addBFlowBean(bflowBean);		
		
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
				info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【" + panflagno + "】";
				request.setAttribute("result", "0");
			} else {
				info = "调用失败！受理号：【" + panflagno + "】";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "操作超时，请重新登录！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		BFlowLogBean bean = new BFlowLogBean();
		bean.setPanflagno(panflagno);
		bean.setBatchflag("mcpanbatch");
		request.setAttribute("bFlowLogBean", bean);

		return mapping.findForward("submitMCinfo");
	}
	
	public ActionForward addPanInfoAgain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
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
				if (UserUtils.getUserName()!=null) {
					if (result >= 0) {
						info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【" + panflagno + "】";
						request.setAttribute("result", "0");
					} else {
						info = "调用失败！受理号：【" + panflagno + "】";
						request.setAttribute("result", "1");
					}
					request.setAttribute("info", StringUtils.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				} else {
					request.setAttribute("info", "操作超时，请重新登录！");
					request.setAttribute("flushdo", flushdo);
					request.setAttribute("result", "1");
				}
				BFlowLogBean bean = new BFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag("mcpanbatch");
				request.setAttribute("bFlowLogBean", bean);

				return mapping.findForward("submitMCinfo");
			}else{
				info="在数据库中未找到受理号为"+panflagno+"发送给cortex系统的发送报文";
				request.setAttribute("info",info);
				request.setAttribute("flushdo", "closewindow");
				return mapping.findForward("submitinfo");				
			}
		}else{
			info="未找到对应的受理号"+panflagno;
			request.setAttribute("info",info);		
			request.setAttribute("flushdo", "closewindow");
			return mapping.findForward("submitinfo");
		}
		
	}
}
