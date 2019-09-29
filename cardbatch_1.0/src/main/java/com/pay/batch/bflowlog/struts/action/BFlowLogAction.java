//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
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
import com.pay.batch.bflowlog.bean.CrdformatMapBean;
import com.pay.batch.bflowlog.dao.BFlowDao;
import com.pay.batch.bflowlog.dao.BFlowLogDao;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.dao.IidPinChkDao;
import com.pay.batch.bflowlog.struts.form.BFlowLogForm;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.batch.bflowlog.struts.form.IidPinChkForm;
import com.pay.batch.bflowlog.tool.CallSocketOfHousekeep;
import com.pay.batch.bmanger.bean.BMangerBean;
import com.pay.batch.bmanger.dao.BMangerDao;
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
 * MyEclipse Struts Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class BFlowLogAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(BFlowLogAction.class);

	public ActionForward getBFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
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
		return mapping.findForward("showBFlowLogList");
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
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		BFlowLogBean queryBean = new BFlowLogBean();
		queryBean.setExecdate(execdate);
		queryBean.setDatasource("0");// 批处理
		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node, queryBean);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List bflowlogList = bflowlogDao.queryBFlowLogList(roleno, dept_no_node,
				pageBean, queryBean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		request.setAttribute("queryflag", "1"); // 表示是查询
		request.setAttribute("execdate", execdate);
		return mapping.findForward("showBFlowLogList");
	}

	public ActionForward showBFlowLogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DeptDao deptDao = new DeptDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
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
		return mapping.findForward("showBFlowLogInfo");
	}

	public ActionForward showPanFlowLogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DeptDao deptDao = new DeptDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
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
		String errMsg = execinfo;
		if (errMsg != null && !"".equals(errMsg) && errMsg.contains("err")) {
			String errNo = errMsg.substring(errMsg.indexOf("err") + 3, errMsg
					.indexOf("err") + 5);
			SysParameterDao sysParameterDao = new SysParameterDao();
			SysParameterBean sysParameterBean = sysParameterDao
					.getSysParameterByTypeAndValue("HOUSEKEEP_ERR_NO", errNo);
			if (sysParameterBean != null) {
				errMsg = sysParameterBean.getParam_name();
			}
			errMsg = "失败原因:" + errMsg;
		} else {
			errMsg = "";
		}
		request.setAttribute("errMsg", errMsg);
		return mapping.findForward("showPanFlowLogInfo");
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
		// String flushdo = "/bflowlog.do?method=preShowBFlowLogList";
		String flushdo = "closewindow";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {

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
		return mapping.findForward("submitinfo");
	}

	public ActionForward preShowPanFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		BFlowLogBean bean = new BFlowLogBean();
		bean.setDatasource("1");// 发卡来源
		BFlowLogForm bflowlogForm = (BFlowLogForm) form;
		bean.setPanflagno(bflowlogForm.getPanflagno());
		// 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// 构造分页对象
		int count = bflowlogDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, cPage);

		List bflowlogList = null;
		if (count > 0)
			bflowlogList = bflowlogDao.queryBFlowLogList(roleno, dept_no_node,
					pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (bflowlogList != null && !bflowlogList.isEmpty()) {
			request.setAttribute("bflowlogList", bflowlogList);
		}
		if (request.getAttribute("info") != null) {
			BFlowLogForm BFlowLogForm = (BFlowLogForm) form;
			BFlowLogForm.setPanflagno("");
			request.setAttribute("info", request.getAttribute("info")
					.toString());
		}
		return mapping.findForward("showPanFlowLogList");
	}

	public ActionForward queryShowPanFlowLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BFlowLogDao bflowlogDao = new BFlowLogDao();
		HttpSession session = request.getSession();
		String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
		String dept_no_node = (String) session.getAttribute("dept_no_node");
		String panflagno = ParamUtils.getParameter(request, "panflagno");
		BFlowLogBean bean = new BFlowLogBean();
		bean.setDatasource("1");// 发卡来源
		bean.setPanflagno(panflagno);
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
		if (request.getAttribute("info") != null) {
			BFlowLogForm BFlowLogForm = (BFlowLogForm) form;
			BFlowLogForm.setPanflagno("");
			request.setAttribute("info", request.getAttribute("info")
					.toString());
		}
		return mapping.findForward("showPanFlowLogList");
	}

	public ActionForward preAddPanInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<BFlowLogBean> lst = crdformatMapDao.getCrdformatMapListByForm("0");
		request.setAttribute("kabinList", lst);
		return mapping.findForward("addPanInfo");
	}

	@SuppressWarnings("rawtypes")
	public ActionForward addPanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("批量发卡后台添加开始……");
		String sendbuf = "";
		String flushdo = "closewindow";
		String kabin = ParamUtils.getParameter(request, "batchflag"); // 卡bin
		if (kabin != null && !"".equals(kabin) && kabin.contains("|")) {
			kabin = kabin.substring(0, kabin.indexOf("|"));
		}
		if(!StringUtils.isNotEmptyStr(kabin)){
			request.setAttribute("info", "卡BIN不能为空，请选择卡BIN！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("submitinfo");
		}
		System.out.println("卡bin处理结束……");
		CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
		CrdformatMapForm crdformatMapForm = new CrdformatMapForm();
		crdformatMapForm.setQueryIid(kabin);
		List<CrdformatMapBean> lii = CrdformatMapDao.getCrdformatMapList(null, crdformatMapForm); // 卡BIN映射
		if ((null == lii || lii.size() == 0)) {
			// 无卡BIN映射
			System.out.println("无卡BIN映射，请添加卡BIN映射！");
			request.setAttribute("info", "无卡BIN映射，请添加卡BIN映射！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("submitinfo");
		} else {
			IidPinChkDao iidPinChkDao = new IidPinChkDao();
			IidPinChkForm iForm = new IidPinChkForm();
			iForm.setQueryiid(kabin);
			List li = iidPinChkDao.getIidPinChkList(null, iForm); // 发卡密码映射
			if ((null == li || li.size() == 0)) {
				// 无发卡密码映射
				System.out.println("无发卡密码映射，请添加发卡密码映射！");
				request.setAttribute("info", "无发卡密码映射，请添加发卡密码映射！");
				request.setAttribute("flushdo", flushdo);
				request.setAttribute("result", "1");
				return mapping.findForward("submitinfo");
			} else {
				String pannum = ParamUtils.getParameter(request, "execinfo"); // 卡数量
				String batchflagname = ParamUtils.getParameter(request, "batchflagname");
				String flagnum = ParamUtils.getParameter(request, "flagnum"); // 卡号是否允许带4
				// 0 不允许
				// 1 允许
				// String passnum= ParamUtils.getParameter(request, "passnum");
				// //是否添加密码处理 0 处理 1不处理
				String yeardate = ParamUtils.getParameter(request, "yeardate"); // 卡有效期
				String defaultDate = SystemConfig.getValue("default_date");
				BMangerDao bMangerDao = new BMangerDao();
				System.out.println("通过batchflagname得到bMangerBean，batchflagname："+batchflagname);
				BMangerBean bMangerBean = bMangerDao.getBMangerInfoPan(batchflagname);
				System.out.println("通过batchflagname得到bMangerBean，bMangerBean："+bMangerBean.toString());
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				String panflagno = sdf.format(new Date());
				sendbuf = bMangerBean.getSocketflag() + " " + panflagno + " " + kabin + " " + pannum + " " + flagnum;
				if (null != yeardate && !"".equals(yeardate) && !"null".equals(yeardate)) {
					yeardate = yeardate.replaceAll("-", "");
					sendbuf = sendbuf + " " + yeardate;
				} else {
					sendbuf = sendbuf + " " + defaultDate;
				}
				System.out.println("报文："+sendbuf);
				// 发送信息保存到数据库
				BFlowDao bflowDao = new BFlowDao();
				BFlowBean bflowBean = new BFlowBean();
				bflowBean.setPanflagno(panflagno);
				bflowBean.setBatchflag("panbatch");
				bflowBean.setSendbuf(sendbuf);
				bflowDao.addBFlowBean(bflowBean);

				CallSocketOfHousekeep callsocket = new CallSocketOfHousekeep();
				String ip = SystemConfig.getValue("socketipOfHousekeep");
				String sport = SystemConfig.getValue("socketportOfHousekeep");
				int port = 0;
				try {
					port = Integer.parseInt(sport);
				} catch (Exception e) {
					System.out.println("socketipOfHousekeep+端口转换异常！");
					e.printStackTrace();
				}
				int result = 0;
				try {
					System.out.println("socket请求开始");
					result = callsocket.execShell(ip, port, sendbuf);
					System.out.println("socket请求结束");
				} catch (Exception e) {
					System.out.println("socket请求异常");
					e.printStackTrace();
					result = -2;
				}

				String info = "";
				if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
					if (result >= 0) {
						info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【" + panflagno + "】";
						request.setAttribute("result", "0");
					} else {
						info = "调用失败！受理号：【" + panflagno + "】";
						request.setAttribute("result", "1");
					}
					System.out.println(info);
					request.setAttribute("info", StringUtils.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				} else {
					System.out.println("操作超时，请重新登录！");
					request.setAttribute("info", "操作超时，请重新登录！");
					request.setAttribute("flushdo", flushdo);
					request.setAttribute("result", "1");
				}
				BFlowLogBean bean = new BFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag(bMangerBean.getBatchflag());
				request.setAttribute("bFlowLogBean", bean);
			}
		}
		return mapping.findForward("submitBFlowinfo");
	}

	public ActionForward addPanInfoAgain(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String sendbuf = "";
		String panflagno = ParamUtils.getParameter(request, "panflagno");
		if (panflagno != null && !"".equals(panflagno)) {
			BFlowDao bflowDao = new BFlowDao();
			BFlowBean bflowBean = bflowDao.getBFlowBean(panflagno);
			if (bflowBean != null && bflowBean.getSendbuf() != null
					&& !"".equals(bflowBean.getSendbuf())) {
				sendbuf = bflowBean.getSendbuf();
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
					System.out.println("callsocket.execShell  ip:" + ip
							+ "  port:" + port);
					System.out.println("sendbuf:" + sendbuf);
				} catch (Exception e) {
					e.printStackTrace();
					result = -2;
				}

				String flushdo = "closewindow";

				if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
					if (result >= 0) {
						info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【" + panflagno + "】";
						request.setAttribute("result", "0");
					} else {
						info = "调用失败！受理号：【" + panflagno + "】";
						request.setAttribute("result", "1");
					}
					request
							.setAttribute("info", StringUtils
									.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				} else {
					request.setAttribute("info", "操作超时，请重新登录！");
					request.setAttribute("flushdo", flushdo);
					request.setAttribute("result", "1");
				}
				BFlowLogBean bean = new BFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag("panbatch");
				request.setAttribute("bFlowLogBean", bean);

				return mapping.findForward("submitBFlowinfo");
			} else {
				info = "在数据库中未找到受理号为" + panflagno + "发送给cortex系统的发送报文";
				request.setAttribute("info", info);
				request.setAttribute("flushdo", "closewindow");
				return mapping.findForward("submitinfo");
			}
		} else {
			info = "未找到对应的受理号" + panflagno;
			request.setAttribute("info", info);
			request.setAttribute("flushdo", "closewindow");
			return mapping.findForward("submitinfo");
		}

	}

}
