package cn.yufu.posp.jgmanager.web.action;

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
import cn.yufu.posp.jgmanager.domain.logic.JgLogicInterface;
import cn.yufu.posp.jgmanager.domain.model.JgModel;
import cn.yufu.posp.jgmanager.web.form.JgForm;

/**
 * @author ���� ��������
 * 
 */
public class JgDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("jg");

	/**
	 * @roseuid 44BAF7150119
	 */
	public JgDispatchAction() {

	}

	/**
	 * ���һ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("JgDispatchAction.queryJg()��ʼ���ã����һ���");

			// �õ�Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

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
					pageInfo.setOrderField("jgId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("jgName");

			}

			// ���ò�ѯ����
			JgModel queryModel = new JgModel();
			// �����ƺŲ�ѯ
			String jgId = request.getParameter("_jgId");
			if (jgId != null) {
				if (!jgId.trim().equals("")) {
					queryModel.setJgId(jgId);
				}

			}
			String jgName = request.getParameter("_jgName");
			if (jgName != null) {
				if (!jgName.trim().equals("")) {
					queryModel.setJgName(jgName);
				}

			}

			pageInfo = jgLogic.queryJg(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllJg");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("JgDispatchAction.JgDispatchAction()��ʼ���ã����һ�����Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.JgDispatchAction()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��������Ϣ
	 */
	public org.apache.struts.action.ActionForward deleteJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.deleteJg()��ʼ���ã�ɾ��������Ϣ��");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<String> keysList = new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

				jgLogic.deleteJg(keysList, ud);
			}
			log.info("JgDispatchAction.deleteJg()�������ã�ɾ��������Ϣ��");
		} catch (Exception e) {
			log.error("JgDispatchAction.deleteJg()�������ã�ɾ��������Ϣ�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteJg");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("jgsr");
	}

	/**
	 * �����µĻ�����Ϣ
	 */
	public org.apache.struts.action.ActionForward createJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.createJg()��ʼ����:�����µĻ�����Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			JgLogicInterface officeSuppliesLogic = (JgLogicInterface) getBean("JgLogic");
			// �õ�Form
			JgForm jgForm = (JgForm) form;
			// �½�һ��Model
			JgModel jgModel = new JgModel();
			BeanUtils.copyProperties(jgModel, jgForm);
			// jgModel.setJgId(jgForm.getJgId());

			// ����FORMΪmodel��ֵ
			// jgModel.setSort(jgForm.getSort());
			// jgModel.setMemo(jgForm.getMemo());
			//			
			// jgModel.setDepartment(ud.getCsmc());
			// jgModel.setDepartment_id(ud.getCsbh());

			// �����µĻ�����Ϣ
			officeSuppliesLogic.createJg(jgModel, ud);
			log.info("JgDispatchAction.createJg()��������:�����µĻ�����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.createJg()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_JG_ADD);
		}

		return mapping.findForward("createJg");
	}

	/**
	 * ��ʾ�޸Ļ�����Ϣ����
	 */
	public org.apache.struts.action.ActionForward queryJgByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> jgMap = new HashMap<Object, Object>();
		try {
			log.info("JgDispatchAction.queryJgByKey()��ʼ���ã���ʾ�޸Ļ�����Ϣ����");
			// �õ�Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

			JgForm jgForm = (JgForm) form;

			UserData ud = getSessionUserData(request);

			jgMap = jgLogic.queryJgByKey(jgForm.getJgId(), ud);

			// �½�һ��Model
			JgModel jgModel = (JgModel) jgMap.get("Infolist");
			BeanUtils.copyProperties(jgForm, jgModel);

			log.info("JgDispatchAction.queryJgByKey()�������ã���ʾ�޸Ļ�����Ϣ����");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.queryJgByKey()��ʾ�޸Ļ�����Ϣ����,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("showModifyJg");
	}

	/**
	 * ���������Ϣ
	 */
	public org.apache.struts.action.ActionForward saveJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.saveJg()��ʼ����:�����µĻ�����Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");
			// �õ�Form
			JgForm jgForm = (JgForm) form;
			// �½�һ��Model
			JgModel jgModel = new JgModel();
			// System.out.println("aaaaaaaaaaaaaaaaaaa"+jgForm.getJgId());
			BeanUtils.copyProperties(jgModel, jgForm);
			// jgModel.setSort(JgForm.getSort());
			// jgModel.setMemo(JgForm.getMemo());
			// jgModel.setId(JgForm.getId());
			//			
			// jgModel.setDepartment(ud.getCsmc());
			// jgModel.setDepartment_id(ud.getCsbh());
			//			
			// �����µĻ�����Ϣ
			jgLogic.saveJg(jgModel, ud);
			log.info("JgDispatchAction.saveJg()��������:�����µĻ�����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.saveJg()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveJg");
	}

}
