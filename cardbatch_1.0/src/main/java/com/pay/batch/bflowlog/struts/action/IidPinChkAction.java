/**
 *包名:com.pay.batch.bflowlog.struts.action
 *描述:package com.pay.batch.bflowlog.struts.action;
 */
package com.pay.batch.bflowlog.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.bflowlog.bean.CrdformatMapBean;
import com.pay.batch.bflowlog.bean.IidPinChkBean;
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.dao.IidPinChkDao;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.batch.bflowlog.struts.form.IidPinChkForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * IidPinChkAction.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年7月4日
 * 描述:发卡密码管理
 */
public class IidPinChkAction extends BaseDispatchAction {

    private static final Logger logger = Logger.getLogger(IidPinChkAction.class);

    
    public ActionForward getIidPinChkList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		// 进入主页前，把查询条件设置为空
		try {
			// 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			IidPinChkDao iidPinChkDao = new IidPinChkDao();
			// 获得终端信息列表记录
			IidPinChkForm iidPinChkForm = (IidPinChkForm) form;
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = iidPinChkDao.getCount(iidPinChkForm, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			request.setAttribute("pageBean", pageBean);
			List iidPinChkList = null;
			if (count > 0){
				iidPinChkList = iidPinChkDao.getIidPinChkList(pageBean, iidPinChkForm);
			}
			if (iidPinChkList != null && !iidPinChkList.isEmpty()) {
				request.setAttribute("iidPinChkList", iidPinChkList);
			}
		} catch (Exception e) {
			logger.error("IidPinChkAction--getIidPinChkList--Exception:", e);
		}
		return mapping.findForward("showIidPinChkList");
	}
    
    public ActionForward delIidPinChk(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) {
		String info = "";
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
		IidPinChkDao iidPinChkDao = new IidPinChkDao();
		String iid= ParamUtils.getParameter(request, "iid");

		int result = iidPinChkDao.delIidPinChk(iid);
		if (result >= 0) {
			info = "卡bin("+iid+")发卡密码映射删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，卡bin("+iid+")发卡密码映射删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetIidPinChkList");
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
	public ActionForward preAddIidPinChk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addIidPinChk.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	public ActionForward addIidPinChk(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String info = "";
			String flushdo = "closewindow";
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
			IidPinChkDao iidPinChkDao = new IidPinChkDao();
			IidPinChkForm iidPinChkForm = (IidPinChkForm) form;
			String iid = iidPinChkForm.getIid();
			// 判断卡Biniid是否重复、是否存在
			if(StringUtils.isNotEmptyStr(iid)){
				IidPinChkForm iForm = new IidPinChkForm();
				iForm.setQueryiid(iid);
				List li = iidPinChkDao.getIidPinChkList(null,iForm);
				if((null == li || li.size() ==0)){
					CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
					CrdformatMapForm crdformatMapForm = new CrdformatMapForm();
					crdformatMapForm.setQueryIid(iid);
					List<CrdformatMapBean> lii = CrdformatMapDao.getCrdformatMapList(null,crdformatMapForm);
					boolean lib = !(null == lii || lii.size() ==0);
					CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
					List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByKabin(null, iid);
					boolean lsb = !(null == lst || lst.size() ==0);
					if(lib || lsb){
						IidPinChkBean IidPinChkBean = new IidPinChkBean();
						IidPinChkBean.setIid(iid);
						IidPinChkBean.setIvrpinfalg(iidPinChkForm.getIvrpinfalg());
						IidPinChkBean.setPospinfalg(iidPinChkForm.getPospinfalg());
						IidPinChkBean.setAdddatetime(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
						IidPinChkBean.setOper(UserUtils.getUserName());
						
						if (UserUtils.getUserName() != null) {
							int result = iidPinChkDao.addIidPinChk(IidPinChkBean);
							if (result >= 0) {
								info = "卡bin(" + iid + ")发卡密码映射添加成功！";
								request.setAttribute("result", "0");
							} else {
								info = "数据库异常，卡bin(" + iid + ")发卡密码映射添加失败！";
								request.setAttribute("result", "1");
							}
							request.setAttribute("info", StringUtils.outerToInner(info));
							request.setAttribute("flushdo", flushdo);
						} else {
							request.setAttribute("info", "操作超时，请重新登录！");
							request.setAttribute("flushdo", flushdo);
							request.setAttribute("result", "1");
						}
					
					}else{
						//卡BIN映射不存在
						info = "卡bin(" + iid + ")映射不存在，请先添加卡BIN映射或者特殊卡BIN映射！";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
						//特殊卡BIN映射不存在
					}
					/*if((null == lii || lii.size() ==0)){
						//卡BIN映射不存在
						info = "卡bin(" + iid + ")映射不存在，请先添加卡BIN映射！";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
					}else if((null == lst || lst.size() ==0)){
						//特殊卡BIN映射不存在
						info = "特殊卡bin(" + iid + ")映射不存在，请先添加特殊卡BIN映射！";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
					}else{
						IidPinChkBean IidPinChkBean = new IidPinChkBean();
						IidPinChkBean.setIid(iid);
						IidPinChkBean.setIvrpinfalg(iidPinChkForm.getIvrpinfalg());
						IidPinChkBean.setPospinfalg(iidPinChkForm.getPospinfalg());
						IidPinChkBean.setAdddatetime(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
						IidPinChkBean.setOper(UserUtils.getUserName());
						
						if (UserUtils.getUserName() != null) {
							int result = iidPinChkDao.addIidPinChk(IidPinChkBean);
							if (result >= 0) {
								info = "卡bin(" + iid + ")发卡密码映射添加成功！";
								request.setAttribute("result", "0");
							} else {
								info = "数据库异常，卡bin(" + iid + ")发卡密码映射添加失败！";
								request.setAttribute("result", "1");
							}
							request.setAttribute("info", StringUtils.outerToInner(info));
							request.setAttribute("flushdo", flushdo);
						} else {
							request.setAttribute("info", "操作超时，请重新登录！");
							request.setAttribute("flushdo", flushdo);
							request.setAttribute("result", "1");
						}
					}*/
				}else{
					//信息已存在
					info = "卡bin(" + iid + ")发卡密码已经存在，请勿重复添加！";
					request.setAttribute("result", "1");
					request.setAttribute("info", StringUtils.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				}
			}else{
				//卡bin为空
				info = "卡bin(" + iid + ")为空，请核对信息！";
				request.setAttribute("result", "1");
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
			}
		} catch (Exception e) {
			logger.error("CrdformatMapAction--addCrdformatMapInfo--Exception:", e);
		}
		/*return mapping.findForward("result.jsp");*/
		return mapping.findForward("submitinfo");
	}
}
