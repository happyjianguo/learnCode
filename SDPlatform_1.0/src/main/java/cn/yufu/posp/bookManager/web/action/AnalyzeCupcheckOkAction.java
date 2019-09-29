package cn.yufu.posp.bookManager.web.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.bookManager.domain.logic.AnalyzeCupcheckOkLogicInterface;
import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckOk;
import cn.yufu.posp.bookManager.web.form.AnalyzeCupcheckOkForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.domain.model.SysRespCode;

public class AnalyzeCupcheckOkAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("book");

	/**
	 * 查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public ActionForward queryAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			log.info("AnalyzeCupcheckOkAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			AnalyzeCupcheckOkLogicInterface logic = (AnalyzeCupcheckOkLogicInterface) getBean("AnalyzeCupcheckOkLogic");

			PageInfoModel pageInfo = new PageInfoModel();
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置查询条件
			AnalyzeCupcheckOk queryModel = new AnalyzeCupcheckOk();

			String _checkDate = request.getParameter("_checkDate");
			if (!StringUtil.isNull(_checkDate))
				queryModel.setCheckDate(_checkDate);

			String _checkFlag = request.getParameter("_checkFlag");
			if (!StringUtil.isNull(_checkFlag))
				queryModel.setCheckFlag(new BigDecimal(_checkFlag));

			String _merchantId = request.getParameter("_merchantId");
			if (!StringUtil.isNull(_merchantId))
				queryModel.setMerchantId(_merchantId);

			String _localSysDate = request.getParameter("_localSysDate");
			if (!StringUtil.isNull(_localSysDate))
				queryModel.setLocalSysDate(_localSysDate);

			String _terminalId = request.getParameter("_terminalId");
			if (!StringUtil.isNull(_terminalId))
				queryModel.setTerminalId(_terminalId);

			String _cardNo = request.getParameter("_cardNo");
			if (!StringUtil.isNull(_cardNo))
				queryModel.setCardNo(_cardNo);

			String _tranType = request.getParameter("_tranType");
			if (!StringUtil.isNull(_tranType))
				queryModel.setTranType(new BigDecimal(_tranType));

			String _traceNo = request.getParameter("_traceNo");
			if (!StringUtil.isNull(_traceNo))
				queryModel.setTraceNo(new BigDecimal(_traceNo));

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);
			
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("AnalyzeCupcheckOkAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckOkAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 查询明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryDetail(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		Map hashMap = new HashMap();
		try {
			log.info("AnalyzeCupcheckOkAction.queryDetail()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());

			AnalyzeCupcheckOkLogicInterface logic = (AnalyzeCupcheckOkLogicInterface) getBean("AnalyzeCupcheckOkLogic");
			AnalyzeCupcheckOkForm newForm = (AnalyzeCupcheckOkForm) form;

			String id = request.getParameter("id");
			UserData ud = getSessionUserData(request);
			AnalyzeCupcheckOk model = logic.queryDetail(id, ud);

			BeanUtils.copyProperties(newForm, model);
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
			request.setAttribute("cardTypeList", cardtypeList);
			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			request.setAttribute("bankTypeList", banktypeList);
			List<SysRespCode> respCodeList = commonLogic.queryAllRespCodeItem(null, null);
			request.setAttribute("respCodeList", respCodeList);
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId spid = new SysParameterId();
			spid.setParamType("TRAN_TYPE");
			sysParameter.setId(spid);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			log.info("AnalyzeCupcheckOkAction.queryDetail()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckOkAction.queryDetail()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("detail");
	}
}
