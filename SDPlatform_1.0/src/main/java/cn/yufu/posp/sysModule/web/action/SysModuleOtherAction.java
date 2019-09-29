package cn.yufu.posp.sysModule.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.sysModule.domain.logic.SysModuleOtherLogicInterface;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;
import cn.yufu.posp.sysModule.web.form.SysModuleForm;

/**
 * @author 石建 客户端管理
 * 
 */
public class SysModuleOtherAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("sys");

	public SysModuleOtherAction() {

	}

	/**
	 * 查找
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("SysModuleOtherAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			SysModuleOtherLogicInterface logic = (SysModuleOtherLogicInterface) getBean("sysModuleOtherLogic");

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
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// 设置查询条件
			SysModuleModel queryModel = new SysModuleModel();
			// 按moduleId查询
			String search = request.getParameter("queryModuleId");
			if (search != null && !"".equals(search))
				queryModel.setQueryModuleId(BigDecimal.valueOf(Long.parseLong(search)));

			// 按progName查询
			String search1 = request.getParameter("queryProgName");
			if (search1 != null && !"".equals(search1))
				queryModel.setQueryProgName(search1);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("SysModuleOtherAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysModuleOtherAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				SysModuleOtherLogicInterface OaClientComputerInfoLogic = (SysModuleOtherLogicInterface) getBean("sysModuleOtherLogic");

				OaClientComputerInfoLogic.deleteItem(keysList, ud);
			}
			log.info("SysModuleOtherAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("SysModuleOtherAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysModuleOtherAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			SysModuleOtherLogicInterface logic = (SysModuleOtherLogicInterface) getBean("sysModuleOtherLogic");
			// 得到Form
			SysModuleForm newForm = (SysModuleForm) form;
			// 新建一个Model
			SysModuleModel model = new SysModuleModel();

			model.setModuleId(BigDecimal.valueOf(Long.parseLong((newForm.getModuleId()))));
			model.setProgDesc(newForm.getProgDesc());
			model.setProgName(newForm.getProgName());
			model.setProgParam(newForm.getProgParam());
			model.setStartMode(newForm.getStartMode());

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("SysModuleOtherAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("SysModuleOtherAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			SysModuleOtherLogicInterface logic = (SysModuleOtherLogicInterface) getBean("sysModuleOtherLogic");

			SysModuleForm newForm = (SysModuleForm) form;

			if ("".equals(newForm.getModuleId()) || newForm.getModuleId() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getModuleId(), ud);

			// 新建一个Model
			SysModuleModel model = (SysModuleModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("SysModuleOtherAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherAction.findItem()显示修改界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysModuleOtherAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			SysModuleOtherLogicInterface logic = (SysModuleOtherLogicInterface) getBean("sysModuleOtherLogic");
			// 得到Form
			SysModuleForm newForm = (SysModuleForm) form;
			// 新建一个Model
			SysModuleModel model = new SysModuleModel();

			model.setModuleId(BigDecimal.valueOf(Long.valueOf(newForm.getModuleId())));
			model.setProgDesc(newForm.getProgDesc());
			model.setProgName(newForm.getProgName());
			model.setProgParam(newForm.getProgParam());
			model.setStartMode(newForm.getStartMode());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("SysModuleOtherAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

}
