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
 * 万事达卡虚拟卡
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
			 // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			// 构造分页对象
			int count = CrdformatMapDao.getCount(null, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);

			List crdformatMapList = null;
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if(count>0){
				crdformatMapList=CrdformatMapDao.getCrdformatMapList(pageBean, null);
			}
			if (crdformatMapList != null && !crdformatMapList.isEmpty()) {
				request.setAttribute("crdformatMapList", crdformatMapList);
			}
			request.setAttribute("queryflag", "0"); // 表示不是查询
		} catch (Exception e) {
			logger.error("CrdformatMapAction--getCrdformatMapList--Exception:",
					e);
		}
		return mapping.findForward("showCrdformatMapList");
	}

	public ActionForward queryCrdformatMapList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 进入主页前，把查询条件设置为空
		try {
			// 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
			// 获得终端信息列表记录
			CrdformatMapForm crdformatMapForm = (CrdformatMapForm) form;
	        // 设置当前页码
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
	 * 初始化增加商户页面
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
		// 获取了两个crdformatMap List
		setTwoKaBinList(request);
		return mapping.findForward("addCrdformatMap");
	}

	/**
	 * 添加商户信息
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
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
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
					info = "卡bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")映射添加成功！";
		            request.setAttribute("result", "0");
		        } else {
					info = "数据库异常，卡bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")映射添加失败！";
		            request.setAttribute("result", "1");
		        }
		        request.setAttribute("info", StringUtils.outerToInner(info));
		        request.setAttribute("flushdo", flushdo);
	        } else {
	        	 request.setAttribute("info", "操作超时，请重新登录！");
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
	 * @TODO 准备修改终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
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
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
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
				info = "卡bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")映射修改成功！";
	            request.setAttribute("result", "0");
	        } else {
				info = "数据库异常，卡bin("+crdformatMapForm.getIid()+","+crdformatMapForm.getFunc_flag()+")映射修改失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	 request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("submitinfo");	

	}
	/**
	 * 设置两个卡bin LIST
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
	 * 设置两个卡bin LIST
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
	 * 删除CrdformatMap
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
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
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
			info = "卡bin("+iid+","+funcFlag+")映射删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，卡bin("+iid+","+funcFlag+")映射删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetCrdformatMapList");
	}	
}
