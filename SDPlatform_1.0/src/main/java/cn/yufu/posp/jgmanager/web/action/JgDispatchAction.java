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
 * @author 吕哲 机构管理
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
	 * 查找机构信息
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
			log.info("JgDispatchAction.queryJg()开始调用：查找机构");

			// 得到Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

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
					pageInfo.setOrderField("jgId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("jgName");

			}

			// 设置查询条件
			JgModel queryModel = new JgModel();
			// 按车牌号查询
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

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllJg");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("JgDispatchAction.JgDispatchAction()开始调用：查找机构信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.JgDispatchAction()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除机构信息
	 */
	public org.apache.struts.action.ActionForward deleteJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.deleteJg()开始调用：删除机构信息。");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<String> keysList = new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

				jgLogic.deleteJg(keysList, ud);
			}
			log.info("JgDispatchAction.deleteJg()结束调用：删除机构信息。");
		} catch (Exception e) {
			log.error("JgDispatchAction.deleteJg()结束调用：删除机构信息。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteJg");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("jgsr");
	}

	/**
	 * 创建新的机构信息
	 */
	public org.apache.struts.action.ActionForward createJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.createJg()开始调用:创建新的机构信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			JgLogicInterface officeSuppliesLogic = (JgLogicInterface) getBean("JgLogic");
			// 得到Form
			JgForm jgForm = (JgForm) form;
			// 新建一个Model
			JgModel jgModel = new JgModel();
			BeanUtils.copyProperties(jgModel, jgForm);
			// jgModel.setJgId(jgForm.getJgId());

			// 根据FORM为model赋值
			// jgModel.setSort(jgForm.getSort());
			// jgModel.setMemo(jgForm.getMemo());
			//			
			// jgModel.setDepartment(ud.getCsmc());
			// jgModel.setDepartment_id(ud.getCsbh());

			// 创建新的机构信息
			officeSuppliesLogic.createJg(jgModel, ud);
			log.info("JgDispatchAction.createJg()结束调用:创建新的机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.createJg()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_JG_ADD);
		}

		return mapping.findForward("createJg");
	}

	/**
	 * 显示修改机构信息界面
	 */
	public org.apache.struts.action.ActionForward queryJgByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> jgMap = new HashMap<Object, Object>();
		try {
			log.info("JgDispatchAction.queryJgByKey()开始调用：显示修改机构信息界面");
			// 得到Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");

			JgForm jgForm = (JgForm) form;

			UserData ud = getSessionUserData(request);

			jgMap = jgLogic.queryJgByKey(jgForm.getJgId(), ud);

			// 新建一个Model
			JgModel jgModel = (JgModel) jgMap.get("Infolist");
			BeanUtils.copyProperties(jgForm, jgModel);

			log.info("JgDispatchAction.queryJgByKey()结束调用：显示修改机构信息界面");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.queryJgByKey()显示修改机构信息界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("showModifyJg");
	}

	/**
	 * 保存机构信息
	 */
	public org.apache.struts.action.ActionForward saveJg(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.saveJg()开始调用:创建新的机构信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			JgLogicInterface jgLogic = (JgLogicInterface) getBean("JgLogic");
			// 得到Form
			JgForm jgForm = (JgForm) form;
			// 新建一个Model
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
			// 创建新的机构信息
			jgLogic.saveJg(jgModel, ud);
			log.info("JgDispatchAction.saveJg()结束调用:创建新的机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.saveJg()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveJg");
	}

}
