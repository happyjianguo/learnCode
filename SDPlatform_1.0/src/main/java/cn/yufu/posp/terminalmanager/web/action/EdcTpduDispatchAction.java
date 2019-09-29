package cn.yufu.posp.terminalmanager.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTpduLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;
import cn.yufu.posp.terminalmanager.web.form.EdcTpduForm;

/**
 * @author zhouya 终端TPDU信息
 * 
 */
public class EdcTpduDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTpdu");

	public EdcTpduDispatchAction() {
	}

	/*** 查询终端TPDU信息 **/
	public org.apache.struts.action.ActionForward queryEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTpduDispatchAction.queryEdcTpdu()开始调用：查找终端TPDU信息。");

			// 得到Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");

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
				if (sortField.equals("1"))
					pageInfo.setOrderField("tpdu");
				if (sortField.equals("2"))
					pageInfo.setOrderField("posLinkType");
				if (sortField.equals("3"))
					pageInfo.setOrderField("chnlno");
				if (sortField.equals("4"))
					pageInfo.setOrderField("moduleId");
				if (sortField.equals("5"))
					pageInfo.setOrderField("packType");
				if (sortField.equals("6"))
					pageInfo.setOrderField("packTypeno");
			}

			// 设置查询条件
			TPreTpdu queryModel = new TPreTpdu();
			// 按ID查询
			String tpdu = request.getParameter("_tpdu");
			if (tpdu != null) {
				if (!tpdu.trim().equals("")) {
					queryModel.setTpdu(tpdu.trim());

				}

			}
			// 按连接类型
			String posLinkType = request.getParameter("_posLinkType");
			if (posLinkType != null) {
				if (!posLinkType.trim().equals("")) {
					queryModel.setPosLinkType(posLinkType.trim());
				}

			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllEdcTpdu");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcTpduDispatchAction.queryEdcTpdu()结束调用：查找终端TPDU信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduDispatchAction.queryEdcTpdu()查找终端TPDU信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端TPDU信息 */
	public org.apache.struts.action.ActionForward deleteEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()开始调用：删除终端TPDU信息。");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<String> keysList = new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()结束调用：删除终端TPDU信息。");
		} catch (Exception e) {
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()删除终端TPDU信息，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcTpdu");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		// 查枚举值
		SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
		SysParameter sysParameter = new SysParameter();
		SysParameterId id = new SysParameterId();
		id.setParamType("MODULE_ID");
		sysParameter.setId(id);
		List moduleIdList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
		request.setAttribute("moduleIdList", moduleIdList);
		return mapping.findForward("edcTpduSr");
	}

	/**
	 * 创建终端TPDU信息
	 */
	public org.apache.struts.action.ActionForward createEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.createEdcTpdu()开始调用:创建新的终端TPDU信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// 得到Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;
			// 新建一个Model
			TPreTpdu tPreTpdu = new TPreTpdu();
			BeanUtils.copyProperties(tPreTpdu, edcTpduForm);

			// 创建新的终端TPDU信息
			logic.createItem(tPreTpdu, ud);

			log.info("EdcTpduDispatchAction.createEdcTpdu()结束调用:创建新的终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.createEdcTpdu()创建新的终端TPDU信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcTpdu");
	}

	/**
	 * 显示修改终端TPDU信息信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcTpduByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()开始调用：显示修改终端TPDU信息信息界面。");
			// 得到Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// 得到Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;

			UserData ud = getSessionUserData(request);

			map = logic.findItemByKey(edcTpduForm.getTpdu(), ud);

			// 新建一个Model
			TPreTpdu model = (TPreTpdu) map.get("Infolist");

			BeanUtils.copyProperties(edcTpduForm, model);
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("MODULE_ID");
			sysParameter.setId(id);
			List moduleIdList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("moduleIdList", moduleIdList);
			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()结束调用：显示修改终端TPDU信息信息界面。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()显示修改终端TPDU信息信息界面，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryEdcTpduByKey");
	}

	/**
	 * 保存终端TPDU信息
	 */
	public org.apache.struts.action.ActionForward saveEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.saveEdcTpdu()创建终端TPDU信息信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// 得到Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;
			// 新建一个Model
			TPreTpdu tPreTpdu = new TPreTpdu();

			tPreTpdu.setModuleId(edcTpduForm.getModuleId());
			tPreTpdu.setPackType(edcTpduForm.getPackType());
			tPreTpdu.setPackTypeno(edcTpduForm.getPackTypeno());
			tPreTpdu.setPosLinkType(edcTpduForm.getPosLinkType());
			tPreTpdu.setChnlno(edcTpduForm.getChnlno());
			tPreTpdu.setTpdu(edcTpduForm.getTpdu());

			// BeanUtils.copyProperties(tPreTpdu,edcTpduForm);

			// 创建终端信息
			logic.saveItem(tPreTpdu, ud);

			log.info("EdcTpduDispatchAction.saveEdcTpdu()结束调用：创建终端TPDU信息信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.saveEdcTpdu()创建终端TPDU信息信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcTpdu");
	}

}
