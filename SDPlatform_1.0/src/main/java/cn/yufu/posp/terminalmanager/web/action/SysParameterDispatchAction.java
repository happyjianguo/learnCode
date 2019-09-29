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
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.web.form.SysParameterForm;

/**
 * @author zhouya ������������
 * 
 */
public class SysParameterDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("sysParameter");

	public SysParameterDispatchAction() {
	}

	/*** ��ѯ��������������Ϣ **/
	public org.apache.struts.action.ActionForward querySysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("SysParameterDispatchAction.querySysParameter()��ʼ����:����������������");

			// �õ�Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("id.paramType");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.paramName");
				if (sortField.equals("3"))
					pageInfo.setOrderField("paramChinese");
				if (sortField.equals("4"))
					pageInfo.setOrderField("paramValue");
				if (sortField.equals("5"))
					pageInfo.setOrderField("paramNotes");
				if (sortField.equals("6"))
					pageInfo.setOrderField("enable");
			}

			// ���ò�ѯ����
			SysParameter queryModel = new SysParameter();
			SysParameterId id = new SysParameterId();

			// �������Ʋ�ѯ
			String paramName = request.getParameter("_paramName");
			if (paramName != null) {
				if (!paramName.trim().equals("")) {
					id.setParamName(paramName);
				}
			}
			// ������������
			String paramType = request.getParameter("_paramType");
			if (paramType != null) {
				if (!paramType.trim().equals("")) {
					id.setParamType(paramType);
				}
			}
			// �����ñ�־��ѯ
			String enable = request.getParameter("_enable");
			if (enable != null) {
				if (!enable.trim().equals("")) {
					queryModel.setEnable(enable);
				}
			}
			queryModel.setId(id);
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllSysParameter");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("SysParameterDispatchAction.querySysParameter()��������:����������������");
		} catch (Exception e) {
			log.info("SysParameterDispatchAction.querySysParameter()���������������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ�������������� */
	public org.apache.struts.action.ActionForward deleteSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.deleteSysParameter()��ʼ����:ɾ��������������");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<SysParameter> keysList = new ArrayList<SysParameter>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {

				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("ɾ����¼��������==" + strId + "��");

				if (tt.length == 3) {
					SysParameter model = new SysParameter();
					SysParameterId id = new SysParameterId();
					id.setParamType(tt[0].trim());
					id.setParamName(tt[1].trim());

					model.setId(id);
					keysList.add(model);
				}
			}
			if (keysList.size() > 0) {
				SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("SysParameterDispatchAction.deleteSysParameter()��������:ɾ��������������");
		} catch (Exception e) {

			log.info("SysParameterDispatchAction.deleteSysParameter()ɾ�������������������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteSysParameter");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("sysParameterSr");
	}

	/**
	 * ����������������
	 */
	public org.apache.struts.action.ActionForward createSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.createSysParameter()��ʼ����:����������������");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			// �õ�Form
			SysParameterForm sysParameterForm = (SysParameterForm) form;
			// �½�һ��Model
			SysParameter sysParameter = new SysParameter();
			BeanUtils.copyProperties(sysParameter, sysParameterForm);

			// �����µ�������������
			logic.createItem(sysParameter, ud);

			log.info("SysParameterDispatchAction.createSysParameter()��������:����������������");

		} catch (Exception e) {
			log.info("SysParameterDispatchAction.createSysParameter()���������������������쳣����");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createSysParameter");
	}

	/**
	 * ��ʾ�޸���������������Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward querySysParameterByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		SysParameter queryModel = new SysParameter();
		SysParameterId id = new SysParameterId();
		try {
			log.info("SysParameterDispatchAction.querySysParameterByKey()��ʼ���ã���ʾ�޸���������������Ϣ���档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			if (tt.length == 3) {
				id.setParamType(tt[0].trim());
				id.setParamName(tt[1].trim());
				queryModel.setId(id);

				UserData ud = getSessionUserData(request);
				// �õ�Logic
				SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
				// �õ�Form
				SysParameterForm sysParameterForm = (SysParameterForm) form;

				map = logic.findItemByKey(queryModel, ud);
				// �½�һ��Model
				SysParameter model = (SysParameter) map.get("Infolist");

				BeanUtils.copyProperties(sysParameterForm, model);

			}

			log.info("SysParameterDispatchAction.querySysParameterByKey()�������ã���ʾ�޸���������������Ϣ���档");

		} catch (Exception e) {
			log.info("SysParameterDispatchAction.querySysParameterByKey()��ʾ�޸���������������Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("querySysParameterByKey");
	}

	/**
	 * ����������������
	 */
	public org.apache.struts.action.ActionForward saveSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.saveSysParametery()��ʼ���ã��޸�������������");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			// �õ�Form
			SysParameterForm sysParameterForm = (SysParameterForm) form;
			// �½�һ��Model
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamName(sysParameterForm.getId().getParamName());
			id.setParamType(sysParameterForm.getId().getParamType());

			sysParameter.setId(id);
			sysParameter.setEnable(sysParameterForm.getEnable());
			sysParameter.setParamChinese(sysParameterForm.getParamChinese());
			sysParameter.setParamNotes(sysParameterForm.getParamNotes());
			sysParameter.setParamValue(sysParameterForm.getParamValue());

			// �����ն���Ϣ
			logic.saveItem(sysParameter, ud);

			log.info("SysParameterDispatchAction.saveSysParametery()�������ã��޸�������������");
		} catch (Exception e) {
			log.info("SysParameterDispatchAction.saveSysParametery()�޸������������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveSysParameter");
	}

}
