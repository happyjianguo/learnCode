package cn.yufu.posp.cardtype.web.action;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.cardtype.domain.logic.Card1TypeLogicInterface;
import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.cardtype.domain.model.CardtypeId1;
import cn.yufu.posp.cardtype.web.form.Card1TypeForm;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * @author 田江瑜 卡类型管理
 * 
 */
public class Card1TypeDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF7150119
	 */
	public Card1TypeDispatchAction() {

	}

	/**
	 * 查找卡类型信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("Card1TypeDispatchAction.queryCardType()开始调用：查找卡类型");

			// 得到Logic
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				pageInfo.setOrderField("id.cardType");
				/*
				 * if (sortField.equals("1"))
				 * pageInfo.setOrderField("id.cardId"); else if
				 * (sortField.equals("2"))
				 * pageInfo.setOrderField("id.cardNoId"); else if
				 * (sortField.equals("3"))
				 * pageInfo.setOrderField("id.cardName"); else if
				 * (sortField.equals("4"))
				 * pageInfo.setOrderField("id.cardType"); else if
				 * (sortField.equals("5"))
				 * pageInfo.setOrderField("id.bankType"); else if
				 * (sortField.equals("6"))
				 * pageInfo.setOrderField("id.bankCode");
				 */

			}

			// 设置查询条件
			Cardtype1 queryModel = new Cardtype1();
			CardtypeId1 queryModelId = new CardtypeId1();
			// 按卡类型号查询
			String jgId = request.getParameter("queryCardType");
			if (jgId != null && !"".equals(jgId)) {
				int i = jgId.length();
				if (i < 4) {
					for (int j = 1; j <= 4 - i; j++) {
						jgId += " ";
					}
				}
				queryModelId.setQueryCardType(jgId);

			}
			String jgName = request.getParameter("queryTypeName");
			if (jgName != null) {
				if (!jgName.trim().equals("")) {
					queryModelId.setQueryTypeName(jgName);
				}

			}
			queryModel.setId(queryModelId);

			pageInfo = jgLogic.queryCardType(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllCardType");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("Card1TypeDispatchAction.queryCardType()开始调用：查找机构信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.queryCardType()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除卡类型信息
	 */
	public org.apache.struts.action.ActionForward deleteCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("Card1TypeDispatchAction.deleteCardType()开始调用：删除机构信息。");
			UserData ud = getSessionUserData(request);
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			String keyStr = request.getParameter("selectItems");
			// List<String> keysList= new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			// System.out.println(InfoIdStrs.length);
			Cardtype1 tr = null;
			CardtypeId1 tri = null;
			for (int i = 0; i < InfoIdStrs.length; i++) {
				tri = new CardtypeId1();
				tr = new Cardtype1();
				String[] str = InfoIdStrs[i].split("_");

				tri.setCardType(str[0]);
				tri.setTypeName(str[1]);
				tri.setTypeEname(str[2]);
				tr.setId(tri);
				// System.out.println(tr);
				jgLogic.deleteCardType(tr, ud);
				// keysList.add(InfoIdStrs[i]);
			}
			// if(keysList.size()>0){
			// 得到Logic

			// }
			log.info("Card1TypeDispatchAction.deleteCardType()结束调用：删除机构信息。");
		} catch (Exception e) {
			log.error("Card1TypeDispatchAction.deleteCardType()结束调用：删除机构信息。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("createCardType");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("Card1TypeDispatchAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			Card1TypeLogicInterface logic = (Card1TypeLogicInterface) getBean("card1TypeLogic");

			Card1TypeForm newForm = (Card1TypeForm) form;

			if ("".equals(newForm.getCardType()) || newForm.getCardType() == null) {
				return mapping.findForward("addCardType");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.queryCardTypeByKey(newForm.getCardType(), ud);

			// 新建一个Model
			Cardtype1 model = (Cardtype1) hashMap.get("Infolist");
			if (model != null) {
				BeanUtils.copyProperties(newForm, model.getId());
			} else {
				throw new OAException("您要修改的信息不存在！");
			}

			log.info("Card1TypeDispatchAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("showModifyCardType");
	}

	/**
	 * 创建新的卡类型信息
	 */
	public org.apache.struts.action.ActionForward createCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("Card1TypeDispatchAction.createCardType()开始调用:创建新的机构信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			Card1TypeLogicInterface officeSuppliesLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			// 得到Form
			Card1TypeForm jgForm = (Card1TypeForm) form;
			// 新建一个Model
			Cardtype1 jgModel = new Cardtype1();
			CardtypeId1 jgModelid = new CardtypeId1();
			BeanUtils.copyProperties(jgModelid, jgForm);
			jgModel.setId(jgModelid);
			// 创建新的机构信息
			officeSuppliesLogic.createCardType(jgModel, ud);

			log.info("结束调用:创建新的机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.createCardType()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// /throw new
			// OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

	/**
	 * 修改卡类型信息
	 */
	public org.apache.struts.action.ActionForward saveCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("开始调用:修改卡类型信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			// 得到Form
			Card1TypeForm jgForm = (Card1TypeForm) form;
			CardtypeId1 tri = new CardtypeId1();
			Cardtype1 tr = new Cardtype1();
			tri.setCardType(jgForm.getCardType());
			tri.setTypeName(jgForm.getTypeName());
			tri.setTypeEname(jgForm.getTypeEname());
			tr.setId(tri);
			jgLogic.saveCardType(tr, ud);

			log.info("卡类型管理的修改保存");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.saveCardType()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

}
