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

//综合管理平台权限控制
import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bflowlog.bean.BFlowBean;
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.dao.BFlowDao;
import com.pay.batch.bflowlog.dao.CrdEtcPoolDao;
import com.pay.batch.bflowlog.dao.SixBFlowLogDao;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.dao.IidPinChkDao;
import com.pay.batch.bflowlog.struts.form.IidPinChkForm;
import com.pay.batch.bflowlog.struts.form.SixBFlowLogForm;
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
//换数据库
import com.pay.batch.bflowlog.tool.CallSocketOfHousekeep;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class SixBFlowLogAction extends BaseDispatchAction {

    private static final Logger logger = Logger.getLogger(SixBFlowLogAction.class);

    
    public ActionForward getSixBFlowLogList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        HttpSession session = request.getSession();
        String execdate = ParamUtils.getParameter(request, "execdate");
        String deptno = (String)session.getAttribute("deptno");
		 //String roleno = (String)session.getAttribute("roleno");
      //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno"); 
        String dept_no_node = (String)session.getAttribute("dept_no_node");
		 session.removeAttribute("execdate");
         // 构造分页对象
        int count=sixbflowlogDao.getCount(roleno,dept_no_node);
		 PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
        List sixbflowlogList = sixbflowlogDao.getBFlowLogList(roleno,dept_no_node,pageBean);
         // 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
       if (sixbflowlogList != null && !sixbflowlogList.isEmpty()) {
            request.setAttribute("sixbflowlogList", sixbflowlogList);
        }
        request.setAttribute("queryflag", "0"); //表示不是查询
       return mapping.findForward("showSixBFlowLogList");
    }
    public ActionForward querySixBFlowLogList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        HttpSession session = request.getSession();
        
        String execdate = ParamUtils.getParameter(request, "execdate");
        if(execdate==null||"".equals(execdate.trim())||"null".equals(execdate.trim())){
        	java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyyMMdd");
           execdate=(String)session.getAttribute("execdate");
        	if(execdate==null||"".equals(execdate.trim())||"null".equals(execdate.trim()))
        	   execdate=sdf.format(new Date());
        	
        		
        }
        session.setAttribute("execdate", execdate);
        execdate=execdate.replaceAll("-", "");
        String deptno = (String)session.getAttribute("deptno");
		 //String roleno = (String)session.getAttribute("roleno");
      //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno"); 
        String dept_no_node = (String)session.getAttribute("dept_no_node");
		 SixBFlowLogBean queryBean=new SixBFlowLogBean();
		 queryBean.setExecdate(execdate);
		 queryBean.setDatasource("0");//批处理
         // 构造分页对象
        int count=sixbflowlogDao.getCount(roleno,dept_no_node,queryBean);
		 PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
        List sixbflowlogList = sixbflowlogDao.queryBFlowLogList(roleno,dept_no_node,pageBean,queryBean);
         // 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
       if (sixbflowlogList != null && !sixbflowlogList.isEmpty()) {
            request.setAttribute("sixbflowlogList", sixbflowlogList);
        }
       request.setAttribute("queryflag", "1"); //表示是查询
       request.setAttribute("execdate", execdate);
       return mapping.findForward("showSixBFlowLogList");
    }
   
    public ActionForward showBFlowLogInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 //SixBFlowLogForm bflowlogForm = (SixBFlowLogForm) form;
        String id = ParamUtils.getParameter(request, "id");
        DeptDao deptDao = new DeptDao();
        HttpSession session = request.getSession();
        String deptno = (String)session.getAttribute("deptno");
		 //String roleno = (String)session.getAttribute("roleno");
      //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno"); 
        String dept_no_node = (String)session.getAttribute("dept_no_node");
        List deptList = null;   
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        
        SixBFlowLogBean sixbflowlogBean = sixbflowlogDao.getBFlowLogInfo(id);     

        request.setAttribute("deptnoList", deptList);
        request.setAttribute("sixbflowlogBean", sixbflowlogBean);
        return mapping.findForward("showBFlowLogInfo");
    }
    public ActionForward showSixPanFlowLogInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 //SixBFlowLogForm bflowlogForm = (SixBFlowLogForm) form;
        String id = ParamUtils.getParameter(request, "id");
        DeptDao deptDao = new DeptDao();
        HttpSession session = request.getSession();
        String deptno = (String)session.getAttribute("deptno");
		// String roleno = (String)session.getAttribute("roleno");
      //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
		 String dept_no_node = (String)session.getAttribute("dept_no_node");
        List deptList = null;   
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        
        SixBFlowLogBean sixbflowlogBean = sixbflowlogDao.getBFlowLogInfo(id);  
        String execinfo= sixbflowlogBean.getExecinfo();
        String execinfos[]=execinfo.split(",");
         
        sixbflowlogBean.setLogfilepath(execinfos[0]);
        String fileinfo="";
        List<String> lstexecinfo=new ArrayList<String>();
        for(int i=1;i<execinfos.length;i++){
        	fileinfo+=execinfos[i]+",";
        	lstexecinfo.add(execinfos[i]);
         }
        sixbflowlogBean.setExecinfo(fileinfo);
        request.setAttribute("deptnoList", deptList);
        request.setAttribute("sixbflowlogBean", sixbflowlogBean);
        request.setAttribute("lstexecinfo", lstexecinfo);
		request.setAttribute("panflagno", sixbflowlogBean.getPanflagno());
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
        return mapping.findForward("showSixPanFlowLogInfo");
    }
    public ActionForward execshell(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 String sendbuf="";
        String logid= ParamUtils.getParameter(request, "logid");         
        SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();         
        SixBFlowLogBean sixbflowlogBean = sixbflowlogDao.getBFlowLogInfo(logid); 
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyMMddHHmmss");
        String panflagno=sdf.format(new Date());
        sendbuf="0010"+" "+sixbflowlogBean.getSrcstartflag() +" "+sixbflowlogBean.getSrcendflag()+" "+panflagno;
        System.out.println("-------------"+sendbuf+"--------------------");
//    	 //创建多线程调用shell脚本
//    	 DepreSingle.getInstance().init();
//		 Thread t=new Thread(new CallShellThread(command,logfile));
//		 t.start();
         //换数据库
         CallSocketOfHousekeep callsocket = new CallSocketOfHousekeep();
		 String ip = SystemConfig.getValue("socketipOfHousekeep");
		 String sport = SystemConfig.getValue("socketportOfHousekeep");
		 int port=0;
		 try{
			 port=Integer.parseInt(sport);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 int result =0;
		 try{
		  result = callsocket.execShell(ip, port, sendbuf);
		 }catch(Exception e){
			 e.printStackTrace();
			 result =-2;
		 }
       

        String info = "";
        //String flushdo = "/bflowlog.do?method=preShowBFlowLogList";
        String flushdo = "closewindow";
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {    
	        
	        if (result >= 0) {
	            info = "shell调用成功，脚本正在执行，请耐心等待！受理号：【"+panflagno+"】";
	            request.setAttribute("result", "0");
	        } else {
	            info = "调用失败！受理号：【"+panflagno+"】";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	 request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        SixBFlowLogBean bean=new SixBFlowLogBean();
        bean.setPanflagno(panflagno);
        bean.setBatchflag(sixbflowlogBean.getBatchflag());
        request.setAttribute("sixbFlowLogBean", bean);
       
        
        return mapping.findForward("submitsixinfo");
    }
 
    public ActionForward preShowSixPanFlowLogList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        HttpSession session = request.getSession();       
       // String roleno = (String)session.getAttribute("roleno");
        //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno");
        String dept_no_node = (String)session.getAttribute("dept_no_node");
		SixBFlowLogBean bean=new SixBFlowLogBean();
		bean.setDatasource("1");//发卡来源
		SixBFlowLogForm bflowlogForm=(SixBFlowLogForm)form;
		bean.setPanflagno(bflowlogForm.getPanflagno());
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// 构造分页对象
		int count = sixbflowlogDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		
		List sixbflowlogList = null;
		if(count>0)
			sixbflowlogList =sixbflowlogDao.queryBFlowLogList(roleno, dept_no_node,
					pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (sixbflowlogList != null && !sixbflowlogList.isEmpty()) {
			request.setAttribute("sixbflowlogList", sixbflowlogList);
		}
		if(request.getAttribute("info")!=null){
			SixBFlowLogForm SixBFlowLogForm=(SixBFlowLogForm)form;
			SixBFlowLogForm.setPanflagno("");
			request.setAttribute("info", request.getAttribute("info").toString());
		}
    	return mapping.findForward("showSixPanFlowLogList");
    }
    public ActionForward queryShowPanFlowLogList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 SixBFlowLogDao sixbflowlogDao = new SixBFlowLogDao();
        HttpSession session = request.getSession();       
        
        String deptno = (String)session.getAttribute("deptno");
		// String roleno = (String)session.getAttribute("roleno");
      //权限控制
        String roleno = session.getAttribute("roleno") == null ? "00"
				: (String) session.getAttribute("roleno"); 
        String dept_no_node = (String)session.getAttribute("dept_no_node");
		 String panflagno = ParamUtils.getParameter(request, "panflagno");
		 SixBFlowLogBean bean=new SixBFlowLogBean();
		 bean.setDatasource("1");//发卡来源
		 bean.setPanflagno(panflagno);
         // 构造分页对象
        int count=sixbflowlogDao.getCount(roleno,dept_no_node,bean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
        List sixbflowlogList = sixbflowlogDao.queryBFlowLogList(roleno,dept_no_node,pageBean,bean);
         // 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
       if (sixbflowlogList != null && !sixbflowlogList.isEmpty()) {
            request.setAttribute("sixbflowlogList", sixbflowlogList);
        }  
		if(request.getAttribute("info")!=null){
			SixBFlowLogForm SixBFlowLogForm=(SixBFlowLogForm)form;
			SixBFlowLogForm.setPanflagno("");
			request.setAttribute("info", request.getAttribute("info").toString());
		}
    	return mapping.findForward("showPanFlowLogList");
    }
    public ActionForward preAddSixPanInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByForm("0");
		request.setAttribute("kabinList", lst);		
    	return mapping.findForward("addSixPanInfo");
    }
    
    public ActionForward preApplyCardPanInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		//卡BIN可在此处修改
		List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByForm("1");
		request.setAttribute("kabinList", lst);		
		//查询可制卡数量
		CrdEtcPoolDao crdetcpoolDao = new CrdEtcPoolDao();
		SixBFlowLogForm SixBFlowLogForm=(SixBFlowLogForm)form;
		SixBFlowLogForm.setCountFlagEqZero(crdetcpoolDao.getCount("0"));
    	return mapping.findForward("applyCardPanInfo");
    }
    
    
	@SuppressWarnings("rawtypes")
	public ActionForward addSixPanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sendbuf = "";
		String info = "";
		String flushdo = "closewindow";
		String kabin = ParamUtils.getParameter(request, "batchflag"); // 卡bin
		if (kabin != null && !"".equals(kabin) && kabin.contains("|")) {
			kabin = kabin.substring(0, kabin.indexOf("|"));
		}
		// 修改单独到综合
		// if(kabin!=null&&!"".equals(kabin)&&kabin.contains("|")){
		// kabin=kabin.split("\\|")[0].toString();
		// }
		if (!StringUtils.isNotEmptyStr(kabin)) {
			request.setAttribute("info", "卡BIN不能为空，请选择卡BIN！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("submitinfo");
		}
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByKabin("0", kabin);
		if ((null == lst || lst.size() == 0)) {
			// 无卡BIN映射
			request.setAttribute("info", "无特殊卡BIN映射，请添加特殊卡BIN映射！");
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
				request.setAttribute("info", "无发卡密码映射，请添加发卡密码映射！");
				request.setAttribute("flushdo", flushdo);
				request.setAttribute("result", "1");
				return mapping.findForward("submitinfo");
			} else {
				String pannum = ParamUtils.getParameter(request, "execinfo"); // 卡数量
				String batchflagname = ParamUtils.getParameter(request, "batchflagname");
				String flagnum = ParamUtils.getParameter(request, "flagnum"); // 卡号是否允许带4
																				// 0
																				// 不允许
																				// 1
																				// 允许
				// String passnum= ParamUtils.getParameter(request, "passnum");
				// //是否添加密码处理 0 处理 1不处理
				String yeardate = ParamUtils.getParameter(request, "yeardate"); // 卡有效期
				String defaultDate = SystemConfig.getValue("wanke_default_date");
				// String defaultDate=SystemConfig.getValue("default_date");

				BMangerDao bMangerDao = new BMangerDao();
				BMangerBean bMangerBean = bMangerDao.getBMangerInfoPan(batchflagname);
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				String panflagno = sdf.format(new Date());
				sendbuf = "0010" + " " + panflagno + " " + kabin + " " + pannum + " " + flagnum;
				if (null != yeardate && !"".equals(yeardate) && !"null".equals(yeardate)) {
					yeardate = yeardate.replaceAll("-", "");
					sendbuf = sendbuf + " " + yeardate;
				} else {
					sendbuf = sendbuf + " " + defaultDate;
				}
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
					e.printStackTrace();
				}
				int result = 0;
				try {
					result = callsocket.execShell(ip, port, sendbuf);
				} catch (Exception e) {
					e.printStackTrace();
					result = -2;
				}
				if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {

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
				SixBFlowLogBean bean = new SixBFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag(bMangerBean.getBatchflag());
				request.setAttribute("sixbFlowLogBean", bean);
			}
		}
		return mapping.findForward("submitSixBFlowinfo");
	}
    
	public ActionForward ApplyCardPanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sendbuf = "";
		String info = "";
		String flushdo = "closewindow";
		String kabin = ParamUtils.getParameter(request, "batchflag"); // 卡bin
		if (kabin != null && !"".equals(kabin) && kabin.contains("|")) {
			kabin = kabin.split("\\|")[0].toString();
		}
		if (!StringUtils.isNotEmptyStr(kabin)) {
			request.setAttribute("info", "卡BIN不能为空，请选择卡BIN！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("submitinfo");
		}
		CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
		List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByKabin("1", kabin);
		if ((null == lst || lst.size() == 0)) {
			// 无卡BIN映射
			request.setAttribute("info", "无特殊卡BIN映射，请添加特殊卡BIN映射！");
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
				request.setAttribute("info", "无发卡密码映射，请添加发卡密码映射！");
				request.setAttribute("flushdo", flushdo);
				request.setAttribute("result", "1");
				return mapping.findForward("submitinfo");
			} else {
				String pannum = ParamUtils.getParameter(request, "execinfo"); // 卡数量
				String batchflagname = ParamUtils.getParameter(request, "batchflagname");
				String flagnum = ParamUtils.getParameter(request, "flagnum"); // 卡号是否允许带4
																				// 0
																				// 不允许
																				// 1
																				// 允许
				// String passnum= ParamUtils.getParameter(request, "passnum");
				// //是否添加密码处理 0 处理 1不处理
				String yeardate = ParamUtils.getParameter(request, "yeardate"); // 卡有效期
				String defaultDate = SystemConfig.getValue("etc_default_date");
				// String defaultDate=SystemConfig.getValue("default_date");

				BMangerDao bMangerDao = new BMangerDao();
				BMangerBean bMangerBean = bMangerDao.getBMangerInfoPan(batchflagname);
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				String panflagno = sdf.format(new Date());
				// 命令在此处改
				sendbuf = "0011" + " " + panflagno + " " + kabin + " " + pannum + " " + flagnum;
				if (null != yeardate && !"".equals(yeardate) && !"null".equals(yeardate)) {
					yeardate = yeardate.replaceAll("-", "");
					sendbuf = sendbuf + " " + yeardate;
				} else {
					sendbuf = sendbuf + " " + defaultDate;
				}
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
					e.printStackTrace();
				}
				int result = 0;
				try {
					result = callsocket.execShell(ip, port, sendbuf);
				} catch (Exception e) {
					e.printStackTrace();
					result = -2;
				}
				if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {

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
				SixBFlowLogBean bean = new SixBFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag(bMangerBean.getBatchflag());
				request.setAttribute("sixbFlowLogBean", bean);
			}
		}
		return mapping.findForward("submitSixBFlowinfo");
	}
    
    
	public ActionForward addSixPanInfoAgain(ActionMapping mapping, ActionForm form,
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
				SixBFlowLogBean bean = new SixBFlowLogBean();
				bean.setPanflagno(panflagno);
				bean.setBatchflag("panbatch");
				request.setAttribute("sixbFlowLogBean", bean);

		    	return mapping.findForward("submitSixBFlowinfo");
			}else{
				info="在数据库中未找到受理号为"+panflagno+"发送给cortex系统的发送报文";
				request.setAttribute("info",info);
				request.setAttribute("flushdo", "closewindow");				
				return mapping.findForward("submitsixinfo");				
			}
		}else{
			info="未找到对应的受理号"+panflagno;
			request.setAttribute("info",info);	
			request.setAttribute("flushdo", "closewindow");			
			return mapping.findForward("submitsixinfo");
		}
		
	}

}
