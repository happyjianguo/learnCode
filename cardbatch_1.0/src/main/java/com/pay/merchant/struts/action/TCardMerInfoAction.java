/**
 *包名:com.pay.merchant.struts.action
 *描述:package com.pay.merchant.struts.action;
 */
package com.pay.merchant.struts.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.merchant.bean.MerchantBean;
import com.pay.merchant.bean.TCardMerInfoBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.merchant.dao.TCardMerInfoDao;
import com.pay.merchant.struts.form.TCardMerInfoForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * TCardMerInfoAction.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年6月22日
 * 描述:商户活动卡管理 
 */
public class TCardMerInfoAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(TCardMerInfoAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	/**
	 * 获取商户活动卡管理 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getTCardMerInfoList(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
		try {
	        // 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			String cardNo = ParamUtils.getParameter(request, "cardNo").trim();//卡号
			String merNo = ParamUtils.getParameter(request, "merNo").trim();//商户号
			TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			tCardMerInfoForm.setCardNo(cardNo);
			tCardMerInfoForm.setMerNo(merNo);
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = tCardMerInfoDao.getCount(tCardMerInfoForm,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List<TCardMerInfoBean> tCardMerInfoBeanist = null;
			if(count > 0){
				tCardMerInfoBeanist = tCardMerInfoDao.getTCardMerInfoList(pageBean, tCardMerInfoForm);
			}		
			if (tCardMerInfoBeanist != null && !tCardMerInfoBeanist.isEmpty()) {
				request.setAttribute("merchantOrgList", tCardMerInfoBeanist);
			}
		} catch (Exception e) {
			logger.error("TCardMerInfoAction--getTCardMerInfoList--Exception:", e);
		}
		return mapping.findForward("tCardMerInfoBeanist");
	}
    
	/**
	 * 删除商户活动卡前置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preDeletetCardMerInfo (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String merNo = ParamUtils.getParameter(request, "merNo");
		String cardNo = ParamUtils.getParameter(request, "cardNo");
		request.setAttribute("merNo", merNo);
		request.setAttribute("cardNo", cardNo);
		return mapping.findForward("delTCardMerInfo.jsp");
	}
	/**
	 * 删除商户活动卡
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deltCardMerInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/tCardMerInfo.do?method=getTCardMerInfoList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			String merNo = ParamUtils.getParameter(request, "merNo");
			String cardNo = ParamUtils.getParameter(request, "cardNo");
			TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			tCardMerInfoForm.setCardNo(cardNo);
			tCardMerInfoForm.setMerNo(merNo);
			int result = tCardMerInfoDao.delTCardMerInfo(tCardMerInfoForm, null);
			if (result >= 0) {
				info = "删除商户活动卡(商户号：" + merNo + "卡号" +cardNo+")成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，删除商户活动卡(商户号：" + merNo + "卡号"+cardNo+")失败！";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "操作超时，请重新登录！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("result.jsp");
	}
    /**
	 * 初始化增加商户活动卡
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddTCardMerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addTCardMerInfo.jsp");
	}
    /**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTCardMerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "closewindow";
        if (UserUtils.getUserName()!=null && !"".equals(UserUtils.getUserName())) {
        	TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
        	Merchantdao merchantdao = new Merchantdao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			TCardMerInfoBean tCardMerInfoBean = new TCardMerInfoBean();
        	filledTCardMerInfoBean(tCardMerInfoForm,tCardMerInfoBean);
        	String startno = tCardMerInfoBean.getStartcardNo();
    		String endno = tCardMerInfoBean.getEndcardNo();
    		String merno = tCardMerInfoBean.getMerNo();
    		BigDecimal sno = new BigDecimal(startno); 
			BigDecimal eno = new BigDecimal(endno); 	
//    		判断商户号和起始卡号是否合法
    		if(isInteger(startno) && isInteger(endno) && isInteger(merno)){
    			//判断结束卡号是否大于开始卡号
    			if(eno.compareTo(sno) >= 0 || eno.subtract(sno).intValue()+1 > 1000){
    				int smatch = eno.subtract(sno).intValue()+1;
    				//判断商户号是否存在
    				List<MerchantBean> li = merchantdao.getMerchantBeanListByMrchNoOrName(merno, null, null, null, null, null);
    				//判断卡号是否存在
    				int cu = tCardMerInfoDao.getTCardCount(startno,endno);
    				if(null == li || li.size() ==0 || cu != smatch){
    					info = "商户号或起始卡号所含卡不存在，商户活动卡信息添加失败！";
        	            flushdo = "javascript:history.go(-1)";
        	            request.setAttribute("result", "1");
    				}else{
    					//判断商户活动卡信息是否存在
    					List<TCardMerInfoBean> lis = tCardMerInfoDao.getTCardMerInfoList(null,tCardMerInfoForm);
    					if(null == lis || lis.size() ==0 ){
    						int result = tCardMerInfoDao.addTCardMerInfo(tCardMerInfoBean);
    						if (result >= 0) {
    							info = smatch +"个商户活动卡信息添加成功！";
    							request.setAttribute("result", "0");
    						} else {
    							info = "数据库异常，商户活动卡信息添加失败！";
    							flushdo = "javascript:history.go(-1)";
    							request.setAttribute("result", "1");
    						}
    					}else{
    						info = "该商户号与此卡段中已有商户活动卡信息已经存在，商户活动卡信息添加失败！";
							flushdo = "javascript:history.go(-1)";
							request.setAttribute("result", "1");
    					}
    				}
    			}else{
    				info = "结束卡号小于开始卡号或者所输卡段范围超过1000，商户活动卡信息添加失败！";
    	            flushdo = "javascript:history.go(-1)";
    	            request.setAttribute("result", "1");
    			}
    		}else{
    			info = "商户号或起始卡号不合法，商户活动卡信息添加失败！";
	            flushdo = "javascript:history.go(-1)";
	            request.setAttribute("result", "1");
    		}
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

	private void filledTCardMerInfoBean(TCardMerInfoForm tCardMerInfoForm, TCardMerInfoBean tCardMerInfoBean) {
		tCardMerInfoBean.setEndcardNo(tCardMerInfoForm.getEndcardNo());
		tCardMerInfoBean.setStartcardNo(tCardMerInfoForm.getStartcardNo());
		tCardMerInfoBean.setMerNo(tCardMerInfoForm.getMerNo());
		tCardMerInfoBean.setAddUser(UserUtils.getUserName());
		tCardMerInfoBean.setTimeStamp(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
	}
	/*
	 * 推荐，速度最快 判断是否为整数
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		if(org.apache.commons.lang.StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
