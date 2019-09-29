package com.pay.system.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class CheckItem extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String itemno = ParamUtils.getParameter(request, "itemno").trim();
		String flashdo = "resultpage";
		
		//用户管理
		if ("0101".equals(itemno)) {
			flashdo = "101.do";//用户管理
		} else if ("0102".equals(itemno)) {
			flashdo = "102.do";//用户组管理
		} else if ("0103".equals(itemno)) {
			flashdo = "103.do";//角色管理
		} else if ("0104".equals(itemno)) {
			flashdo = "104.do";//权限管理
			
		//监控查询
		} else if ("0201".equals(itemno)) {
			flashdo = "201.do";//交易监控
		} else if ("0202".equals(itemno)) {
			flashdo = "202.do";//历史流水查询
			//报表
		} else if ("0211".equals(itemno)) {
			flashdo = "211.do";//商户明细
		} else if ("0212".equals(itemno)) {
			flashdo = "212.do";//商户汇总
		} else if ("0213".equals(itemno)) {
			flashdo = "213.do";//EPS数据
		} else if ("0214".equals(itemno)) {
			flashdo = "214.do";//本系统数据
		} else if ("0215".equals(itemno)) {
			flashdo = "215.do";//EPS单边帐清单
		} else if ("0216".equals(itemno)) {
			flashdo = "216.do";//EPS时间差明细
		} else if ("0217".equals(itemno)) {
			flashdo = "217.do";//本系统单边帐清单
		} else if ("0218".equals(itemno)) {
			flashdo = "218.do";//本系统时间差明细
		} else if ("0219".equals(itemno)) {
			flashdo = "219.do";//按商户汇总统计时间差
		} else if ("0220".equals(itemno)) {
			flashdo = "220.do";//ICS入账明细
			
		//交易控管
		} else if ("0301".equals(itemno)) {
			flashdo = "301.do";//虚拟柜员管理
		} else if ("0302".equals(itemno)) {
			flashdo = "302.do";//卡段管理
		} else if ("0303".equals(itemno)) {
			flashdo = "303.do";//黑名单管理
		} else if ("0304".equals(itemno)) {
			flashdo = "304.do";//卡段批量导入
			
		//商户终端管理
		} else if ("0401".equals(itemno)) {
			flashdo = "401.do";//商户导入
		} else if ("0402".equals(itemno)) {
			flashdo = "402.do";//未激活商户管理
		} else if ("0403".equals(itemno)) {
			flashdo = "403.do";//已激活商户管理
			
		} else if ("0411".equals(itemno)) {
			flashdo = "411.do";//终端导入
		} else if ("0412".equals(itemno)) {
			flashdo = "412.do";//未激活终端管理
		} else if ("0413".equals(itemno)) {
			flashdo = "413.do";//已激活终端管理			
		
		//系统配置
		}else if("0501".equals(itemno)){
			flashdo="501.do";//系统配置
			
		//无项目处理
		}else {
			request.setAttribute("result", "-1");
			request.setAttribute("info", StringUtils.outerToInner("无此权限!!"));
			request.setAttribute("flushdo", "/jsppage/common/right.jsp");
		}
		return mapping.findForward(flashdo);
	}
}
