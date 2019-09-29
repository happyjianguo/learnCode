package com.pay.query.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.query.bean.CustBean;
import com.pay.query.dao.CustDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.query.struts.form.CustForm;

public class QueryCust extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(QueryCust.class);
	public ActionForward getCustById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = (String) request.getParameter("id");
	        CustForm cardForm = (CustForm)form;
	        if(!"".equals(id)&&id!=null)
	        	cardForm.setId(id);
	        CustBean cardBean = new CustBean();
			BeanUtils.copyProperties(cardBean,cardForm);
	        CustDao cardDao = new CustDao();
			List cardList = cardDao.getCustList(null, cardBean);
			if(cardList.size()>0){
				BeanUtils.copyProperties(cardForm,cardList.get(0));
			}
		} catch (Exception e) {
			logger.error("QueryCust--getCustById--Exception:", e);
		}
        return mapping.findForward("showCust");
    }
	

}
