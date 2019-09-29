//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.batch.bflowlog.struts.action;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.bflowlog.bean.CrdEtcPoolBean;
import com.pay.batch.bflowlog.dao.CrdEtcPoolDao;
import com.pay.batch.bflowlog.struts.form.CrdEtcPoolForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.PageBean;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class CrdEtcPoolAction extends BaseDispatchAction {

    private static final Logger logger = Logger.getLogger(CrdEtcPoolAction.class);

    public ActionForward preShowCrdEtcPoolList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 CrdEtcPoolDao crdetcpoolDao = new CrdEtcPoolDao();
        HttpSession session = request.getSession();       
        String roleno = (String)session.getAttribute("roleno");
		String dept_no_node = (String)session.getAttribute("dept_no_node");
		CrdEtcPoolBean bean=new CrdEtcPoolBean();
		
		//设置查询条件
		CrdEtcPoolForm crdetcpoolForm=(CrdEtcPoolForm)form;
		bean.setStart_etc_pan(crdetcpoolForm.getStart_etc_pan());
		bean.setEnd_etc_pan(crdetcpoolForm.getEnd_etc_pan());
		
		
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		// 构造分页对象
		int count = crdetcpoolDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		
		List crdetcpoolList = null;
		if(count>0)
			crdetcpoolList =crdetcpoolDao.queryCrdEtcPoolList(roleno, dept_no_node,
					pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (crdetcpoolList != null && !crdetcpoolList.isEmpty()) {
			request.setAttribute("crdetcpoolList", crdetcpoolList);
		}
		
		if(request.getAttribute("info")!=null){
			request.setAttribute("info", request.getAttribute("info").toString());
		}
		//获取总条数
		int countTotle=crdetcpoolDao.getCount("0");
		request.setAttribute("countTotle", String.valueOf(countTotle));
		
    	return mapping.findForward("showCrdetcPoolEtcPoolList");
    }
    
 
    public ActionForward Entercrdetcpool(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	 CrdEtcPoolDao crdetcpoolDao = new CrdEtcPoolDao();
        HttpSession session = request.getSession();       
        String roleno = (String)session.getAttribute("roleno");
		String dept_no_node = (String)session.getAttribute("dept_no_node");
		CrdEtcPoolBean bean=new CrdEtcPoolBean();
		
		//设置查询条件
		CrdEtcPoolForm crdetcpoolForm=(CrdEtcPoolForm)form;
		bean.setStart_etc_pan(crdetcpoolForm.getStart_etc_pan());
		bean.setEnd_etc_pan(crdetcpoolForm.getEnd_etc_pan());
		//录入操作
		int total =0,result=0,tempSub=0,tempSubBak=0;
		BigInteger start = BigInteger.ZERO;
		BigInteger startBak = BigInteger.ZERO;
		BigInteger end = BigInteger.ZERO;
		String start_etc_pan=bean.getStart_etc_pan();
		String end_etc_pan=bean.getEnd_etc_pan();
		String crdetcpan = "",crdetcpanTemp=null;
		if(null!=start_etc_pan&&null!=end_etc_pan){
			start=new BigInteger(start_etc_pan);
			startBak=start;
			end=new BigInteger(end_etc_pan);
			java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyyMMdd");
	    	String nowdate=sdf.format(new Date());
	    	java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("HHmmss");
	    	
	    	bean.setInsertdate(nowdate);
	    	bean.setVerno_ctx("0");;
	    	bean.setFlag("0");
	    	bean.setCrdbatch_batch("0");;
	    	bean.setBatchtask_ticket_no("0");
	    	bean.setUpdatedate(" ");
	    	bean.setUpdatetime(" ");
	    	
	    	tempSub=end.subtract(start).intValue()+1;
	    	tempSubBak=tempSub-1;
			if(1<tempSub){
//				for(int i=0;i<tempSub;i++){
//					bean.setEtc_pan(String.valueOf(start));
//					//判断此卡号是否已经存在数据库中。
//					crdetcpanTemp = crdetcpoolDao.getCrdEtcPan(bean.getEtc_pan());
//					start=start.add(BigInteger.ONE);
//				}
//				if(null==crdetcpanTemp){

					try {
						result = crdetcpoolDao.InsertEtcPanTotal(bean,tempSub,tempSubBak,startBak);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					
//				}else{
//					crdetcpan="失败";
//				}
				if(result==-1){
					crdetcpan="失败";
				}
					
			}
			if(1==tempSub){
				bean.setEtc_pan(String.valueOf(start));
				bean.setInserttime(sdf1.format(new Date()));
				//判断此卡号是否已经存在数据库中。如是则跳过并记录卡号。
				crdetcpan = crdetcpoolDao.getCrdEtcPan(bean.getEtc_pan());
				if(null==crdetcpan){
					total = crdetcpoolDao.InsertEtcPanOne(bean);
				}else{
					crdetcpan="失败";
				}
				if(1==total){
					result=result+1;
				}
			}	 
		}
		
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}

		// 构造分页对象
		int count = crdetcpoolDao.getCount(roleno, dept_no_node, bean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		
		List crdetcpoolList = null;
		if(count>0)
			crdetcpoolList =crdetcpoolDao.queryCrdEtcPoolList(roleno, dept_no_node,
					pageBean, bean);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (crdetcpoolList != null && !crdetcpoolList.isEmpty()) {
			request.setAttribute("crdetcpoolList", crdetcpoolList);
		}
		String replyString="";
		if(result==tempSub){
			replyString="成功"+result+"条数据";
		}else{
			replyString="卡号录入失败，卡号部分或全部存在";
		}
		
		request.setAttribute("CrdEtcPoolInfo", replyString);
		
		if(request.getAttribute("info")!=null){
			CrdEtcPoolForm CrdEtcPoolForm=(CrdEtcPoolForm)form;
			request.setAttribute("info", request.getAttribute("info").toString());
		}
    	return mapping.findForward("showCrdetcPoolEtcPoolList");
    }
 

}
