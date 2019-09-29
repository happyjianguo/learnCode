package com.pay.query.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.query.bean.CardBean;
import com.pay.query.bean.CardProductBean;
import com.pay.query.bean.CardStatusBean;
import com.pay.query.dao.CardDao;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.query.struts.form.CardForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;

public class QueryCard extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(QueryCard.class);
	public ActionForward getCardList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize, 1);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			
			String sumAmt = String.format("%.2f",Double.valueOf("0"));
			request.setAttribute("sumAmt", sumAmt);
			
			//枚举值
			CommonDao comDao = new CommonDao();
			CardProductBean cardProductBean = new CardProductBean();
			List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			request.setAttribute("cardProductList", cardProductList);
			CardStatusBean cardStatusBean = new CardStatusBean();
			List cardStatusList = comDao.getCardStatusBeanList(null, cardStatusBean  );
			request.setAttribute("cardStatusList", cardStatusList);

		} catch (Exception e) {
			logger.error("QueryCard--getCardList--Exception:", e);
		}
        return mapping.findForward("cardList");
    }
	public ActionForward getCardListkQ(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			// 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			CardForm cardForm = (CardForm)form;
			CardBean cardBean = new CardBean();
			BeanUtils.copyProperties(cardBean,cardForm);
			
			CardDao cardDao = new CardDao();
			int count = cardDao.getCount(cardBean,session,"0");
			
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List cardList = null;
			String sumAmtTemp="0";
			if(count>0){
				cardList = cardDao.getCardList(pageBean, cardBean,"0");
				sumAmtTemp=cardDao.getSumAmt(cardBean,session,"0");
			}
			String sumAmt = String.format("%.2f",Double.valueOf(sumAmtTemp));
			request.setAttribute("sumAmt", sumAmt);
			
			if (cardList != null && !cardList.isEmpty()) {
				request.setAttribute("cardList", cardList);
			}
			//枚举值
			CommonDao comDao = new CommonDao();
			CardProductBean cardProductBean = new CardProductBean();
			List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			request.setAttribute("cardProductList", cardProductList);
			CardStatusBean cardStatusBean = new CardStatusBean();
			List cardStatusList = comDao.getCardStatusBeanList(null, cardStatusBean  );
			request.setAttribute("cardStatusList", cardStatusList);
			
		} catch (Exception e) {
			logger.error("QueryCard--getCardList--Exception:", e);
		}
		return mapping.findForward("cardList");
	}
	public ActionForward getRealCardList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize, 1);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			
			String sumAmt = String.format("%.2f",Double.valueOf("0"));
			request.setAttribute("sumAmt", sumAmt);	
			
			//枚举值
			CommonDao comDao = new CommonDao();
			CardProductBean cardProductBean = new CardProductBean();
			List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			request.setAttribute("cardProductList", cardProductList);
			CardStatusBean cardStatusBean = new CardStatusBean();
			List cardStatusList = comDao.getCardStatusBeanList(null, cardStatusBean  );
			request.setAttribute("cardStatusList", cardStatusList);

		} catch (Exception e) {
			logger.error("QueryCard--getCardList--Exception:", e);
		}
        return mapping.findForward("realCardList");
    }
	public ActionForward getRealCardListkQ(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			// 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			CardForm cardForm = (CardForm)form;
			CardBean cardBean = new CardBean();
			BeanUtils.copyProperties(cardBean,cardForm);
			cardBean.setTypeid("1");//实名用户
			CardDao cardDao = new CardDao();
			int count = cardDao.getCount(cardBean,session,"1");
			
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List cardList = null;
			String sumAmtTemp="0";
			if(count>0){
				cardList = cardDao.getCardList(pageBean, cardBean,"1");
				sumAmtTemp=cardDao.getSumAmt(cardBean,session,"1");
			}
			String sumAmt = String.format("%.2f",Double.valueOf(sumAmtTemp));
			request.setAttribute("sumAmt", sumAmt);	
			
			if (cardList != null && !cardList.isEmpty()) {
				request.setAttribute("cardList", cardList);
			}
			//枚举值
			CommonDao comDao = new CommonDao();
			CardProductBean cardProductBean = new CardProductBean();
			List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			request.setAttribute("cardProductList", cardProductList);
			CardStatusBean cardStatusBean = new CardStatusBean();
			List cardStatusList = comDao.getCardStatusBeanList(null, cardStatusBean  );
			request.setAttribute("cardStatusList", cardStatusList);
			
		} catch (Exception e) {
			logger.error("QueryCard--getCardList--Exception:", e);
		}
		return mapping.findForward("realCardList");
	}
	
	public ActionForward getCardById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        CardForm cardForm = (CardForm)form;
	        CardBean cardBean = new CardBean();	        
			BeanUtils.copyProperties(cardBean,cardForm);
	        CardDao cardDao = new CardDao();
			List cardList = cardDao.getCardList(null, cardBean);
			if(cardList.size()>0){
				BeanUtils.copyProperties(cardForm,cardList.get(0));
			}
		} catch (Exception e) {
			logger.error("QueryCard--getCardById--Exception:", e);
		}
        return mapping.findForward("showCard");
    }
	public ActionForward getRealCardById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CardForm cardForm = (CardForm)form;
			CardBean cardBean = new CardBean();
			BeanUtils.copyProperties(cardBean,cardForm);
			CardDao cardDao = new CardDao();
			List cardList = cardDao.getCardList(null, cardBean);
			if(cardList.size()>0){
				BeanUtils.copyProperties(cardForm,cardList.get(0));
			}
		} catch (Exception e) {
			logger.error("QueryCard--getCardById--Exception:", e);
		}
		return mapping.findForward("showRealCard");
	}
	

}
