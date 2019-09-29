package cn.yufu.posp.merchant.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;
import org.apache.struts.upload.FormFile;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.logManager.domain.logic.LogLogicInterface;
import cn.yufu.posp.logManager.domain.model.OperateLog;
import cn.yufu.posp.logManager.web.form.OperateLogForm;
import cn.yufu.posp.merchant.domain.logic.MerchantIdentityLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;
import cn.yufu.posp.merchant.web.form.FileActionForm;
import cn.yufu.posp.merchant.web.form.MerchantIdentityForm;

public class MerchantIdentityAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantIdentityAction() {

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
	public ActionForward queryAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {

		String pathForward = "";

		try {
			log.info("MerchantIdentityAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

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
			MerchantIdentity queryModel = new MerchantIdentity();
			// 按商户编号查询
			String search = request.getParameter("queryMerid");
			if (search != null)
				queryModel.setMerchantId(search);
			String queryMerstate = request.getParameter("queryMerstat");
			if (queryMerstate != null)
				queryModel.setClassType(queryMerstate);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MerchantIdentityAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.queryAll()调用出现异常。");
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
			log.info("MerchantIdentityAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantIdentityAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantIdentityAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
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
			log.info("MerchantIdentityAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

			// 初始化机构
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			MerchantIdentityForm newForm = (MerchantIdentityForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// 新建一个Model
			MerchantIdentity model = (MerchantIdentity) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantIdentityAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
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
			log.info("MerchantIdentityAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");
			// 得到Form
			MerchantIdentityForm newForm = (MerchantIdentityForm) form;
			// 新建一个Model
			MerchantIdentity model = new MerchantIdentity();

			FormFile biFile = newForm.getBiFile(); // 获取当前的文件
			FormFile taxFile = newForm.getTaxFile(); // 获取当前的文件
			FormFile orgFile = newForm.getOrgFile(); // 获取当前的文件
			if (!"".equals(biFile.getFileName())&&biFile.getFileName() != null) {
				String biName = newForm.getMerchantId() + "_BI_" + Math.round(Math.random() * 100);
				newForm.setBiCardPic(biName);// 文件名
				uploadFile(biFile, biName);
			}
			if (!"".equals(taxFile.getFileName())&&taxFile.getFileName()!= null) {
				String taxName = newForm.getMerchantId() + "_TAX_" + Math.round(Math.random() * 100);
				newForm.setTaxCardPic(taxName);// 文件名
				uploadFile(taxFile, taxName);
			}
			if (!"".equals(orgFile.getFileName())&&orgFile.getFileName() != null) {
				String orgName = newForm.getMerchantId() + "_ORG_" + Math.round(Math.random() * 100);
				newForm.setOrgCardPic(orgName);// 文件名
				uploadFile(orgFile, orgName);
			}

			BeanUtils.copyProperties(model, newForm);
			
			//-----日志
			LogLogicInterface logLogic = (LogLogicInterface) getBean("LogLogic");
			OperateLog logModel = new OperateLog();
			logModel.setType("1");//1商户身份变更
			logModel.setKeyId(model.getMerchantId());
			logModel.setReason(model.getReason());
			logModel.setNewdata(model.toString());
			Map hashMap = logic.findItem(newForm.getMerchantId(), ud);
			// 新建一个Model
			MerchantIdentity oldModel = (MerchantIdentity) hashMap.get("Infolist");
			logModel.setOlddata(oldModel.toString());
			logLogic.createItem(logModel,ud);
			//-----
			model.setUpdateOper(ud.getUserId());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("MerchantIdentityAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	private void uploadFile(FormFile file, String name) throws Exception {
		// 获得系统的绝对路径
		String dir = servlet.getServletContext().getRealPath("../merchantImage");
		// InInputStream是用以从特定的资源读取字节的方法。
		InputStream streamIn = file.getInputStream(); // 创建读取用户上传文件的对象
		// 得到是字节数，即byte,我们可以直接用file.getFileSize(),也可以在创建读取对象时用streamIn.available();
		// int ok=streamIn.available();
		int ok = file.getFileSize();
		String strFee = null;
		// 这个地方是处理上传的为M单位计算时，下一个是以kb,在下一个是byte;
		if (ok >= 1024 * 1024) {
			float ok1 = (((float) ok) / 1024f / 1024f);
			DecimalFormat myformat1 = new DecimalFormat("0.00");
			strFee = myformat1.format(ok1) + "M";
		} else if (ok > 1024 && ok <= 1024 * 1024) {
			double ok2 = ((double) ok) / 1024;
			DecimalFormat myformat2 = new DecimalFormat("0.00");
			strFee = myformat2.format(ok2) + "kb";
		} else if (ok < 1024) {
			System.out.println("aaaaaaaaa");
			strFee = String.valueOf(ok) + "byte";
		}
		log.info(streamIn.available() + "文件大小byte");
		// 这个是io包下的上传文件类
		File uploadFile = new File(dir); // 指定上传文件的位置
		if (!uploadFile.exists() || uploadFile == null) { // 判断指定路径dir是否存在，不存在则创建路径
			uploadFile.mkdirs();
		}
		// 上传的路径+文件名
		String path = uploadFile.getPath() + "/" + name;
		// OutputStream用于向某个目标写入字节的抽象类，这个地方写入目标是path，通过输出流FileOutputStream去写
		OutputStream streamOut = new FileOutputStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// 将数据读入byte数组的一部分，其中读入字节数的最大值是8192，读入的字节将存储到，buffer[0]到buffer[0+8190-1]的部分中
		// streamIn.read方法返回的是实际读取字节数目.如果读到末尾则返回-1.如果bytesRead返回为0则表示没有读取任何字节。
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			// 写入buffer数组的一部分，从buf[0]开始写入并写入bytesRead个字节，这个write方法将发生阻塞直至字节写入完成。
			streamOut.write(buffer, 0, bytesRead);
		}
		// 关闭输出输入流,销毁File流。
		streamOut.close();
		streamIn.close();
		file.destroy();
	}

	// 实现文件的下载
	public ActionForward downFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getParameter("path");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;

		// 如果是从服务器上取就用这个获得系统的绝对路径方法。 String filepath =
		// servlet.getServletContext().getRealPath("/" + path);
		String filepath = servlet.getServletContext().getRealPath("../merchantImage")+"/" +path;
		log.info("文件路径" + filepath);
		File uploadFile = new File(filepath);
		fis = new FileInputStream(uploadFile);
		bis = new BufferedInputStream(fis);
		fos = response.getOutputStream();
		bos = new BufferedOutputStream(fos);
		// 这个就就是弹出下载对话框的关键代码
		//response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(path, "utf-8"));
		int bytesRead = 0;
		// 这个地方的同上传的一样。我就不多说了，都是用输入流进行先读，然后用输出流去写，唯一不同的是我用的是缓冲输入输出流
		byte[] buffer = new byte[8192];
		while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.flush();
		fis.close();
		bis.close();
		fos.close();
		bos.close();
		return null;
	}

}
