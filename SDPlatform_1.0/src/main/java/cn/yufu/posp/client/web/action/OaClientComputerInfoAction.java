package cn.yufu.posp.client.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.client.domain.logic.OaClientComputerInfoLogicInterface;
import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.client.web.form.OaClientComputerInfoForm;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * @author 石建 客户端管理
 * 
 */
public class OaClientComputerInfoAction extends OABaseDispatchAction {

	public OaClientComputerInfoAction() {

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
			log.info("OaClientComputerInfoAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

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
			OaClientComputerInfoModel queryModel = new OaClientComputerInfoModel();
			// 按MAC地址查询
			String search = request.getParameter("mac_addr");
			if (search != null)
				queryModel.setMacAddr(search);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("OaClientComputerInfoAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				OaClientComputerInfoLogicInterface OaClientComputerInfoLogic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

				OaClientComputerInfoLogic.deleteItem(keysList, ud);
			}
			log.info("OaClientComputerInfoAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("OaClientComputerInfoAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");
			// 得到Form
			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;
			// 新建一个Model
			OaClientComputerInfoModel model = new OaClientComputerInfoModel();

			model.setMacAddr(newForm.getMacAddr());
			model.setIpAddr(newForm.getIpAddr());
			model.setHostName(newForm.getHostName());
			model.setUserId(newForm.getUserId());
			model.setCreateUser(ud.getUserId());
			model.setCreateTimestamp(new Date());
			model.setClientId(ud.getClientId());

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("OaClientComputerInfoAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_Client_ADD);
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
			log.info("OaClientComputerInfoAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;

			if ("".equals(newForm.getMacAddr()) || newForm.getMacAddr() == null) {
				newForm.setUserId(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMacAddr(), ud);

			// 新建一个Model
			OaClientComputerInfoModel model = (OaClientComputerInfoModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("OaClientComputerInfoAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");
			// 得到Form
			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;
			// 新建一个Model
			OaClientComputerInfoModel model = new OaClientComputerInfoModel();

			model.setMacAddr(newForm.getMacAddr());
			model.setIpAddr(newForm.getIpAddr());
			model.setHostName(newForm.getHostName());
			model.setUserId(newForm.getUserId());
			model.setCreateUser(newForm.getCreateUser());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreateTimestamp(sdf.parse(newForm.getCreateTimestamp()));

			model.setClientId(newForm.getClientId());
			model.setUpdateUser(ud.getUserId());
			model.setUpdateTimestamp(new Date());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("OaClientComputerInfoAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

}
