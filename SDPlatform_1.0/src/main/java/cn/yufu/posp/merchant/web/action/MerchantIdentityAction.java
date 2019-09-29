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
	 * ����
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
			log.info("MerchantIdentityAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

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
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// ���ò�ѯ����
			MerchantIdentity queryModel = new MerchantIdentity();
			// ���̻���Ų�ѯ
			String search = request.getParameter("queryMerid");
			if (search != null)
				queryModel.setMerchantId(search);
			String queryMerstate = request.getParameter("queryMerstat");
			if (queryMerstate != null)
				queryModel.setClassType(queryMerstate);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MerchantIdentityAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantIdentityAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantIdentityAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantIdentityAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		HashMap hashMap = new HashMap();
		try {
			log.info("MerchantIdentityAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");

			// ��ʼ������
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			MerchantIdentityForm newForm = (MerchantIdentityForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// �½�һ��Model
			MerchantIdentity model = (MerchantIdentity) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantIdentityAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantIdentityAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantIdentityLogicInterface logic = (MerchantIdentityLogicInterface) getBean("MerchantIdentityLogic");
			// �õ�Form
			MerchantIdentityForm newForm = (MerchantIdentityForm) form;
			// �½�һ��Model
			MerchantIdentity model = new MerchantIdentity();

			FormFile biFile = newForm.getBiFile(); // ��ȡ��ǰ���ļ�
			FormFile taxFile = newForm.getTaxFile(); // ��ȡ��ǰ���ļ�
			FormFile orgFile = newForm.getOrgFile(); // ��ȡ��ǰ���ļ�
			if (!"".equals(biFile.getFileName())&&biFile.getFileName() != null) {
				String biName = newForm.getMerchantId() + "_BI_" + Math.round(Math.random() * 100);
				newForm.setBiCardPic(biName);// �ļ���
				uploadFile(biFile, biName);
			}
			if (!"".equals(taxFile.getFileName())&&taxFile.getFileName()!= null) {
				String taxName = newForm.getMerchantId() + "_TAX_" + Math.round(Math.random() * 100);
				newForm.setTaxCardPic(taxName);// �ļ���
				uploadFile(taxFile, taxName);
			}
			if (!"".equals(orgFile.getFileName())&&orgFile.getFileName() != null) {
				String orgName = newForm.getMerchantId() + "_ORG_" + Math.round(Math.random() * 100);
				newForm.setOrgCardPic(orgName);// �ļ���
				uploadFile(orgFile, orgName);
			}

			BeanUtils.copyProperties(model, newForm);
			
			//-----��־
			LogLogicInterface logLogic = (LogLogicInterface) getBean("LogLogic");
			OperateLog logModel = new OperateLog();
			logModel.setType("1");//1�̻���ݱ��
			logModel.setKeyId(model.getMerchantId());
			logModel.setReason(model.getReason());
			logModel.setNewdata(model.toString());
			Map hashMap = logic.findItem(newForm.getMerchantId(), ud);
			// �½�һ��Model
			MerchantIdentity oldModel = (MerchantIdentity) hashMap.get("Infolist");
			logModel.setOlddata(oldModel.toString());
			logLogic.createItem(logModel,ud);
			//-----
			model.setUpdateOper(ud.getUserId());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("MerchantIdentityAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	private void uploadFile(FormFile file, String name) throws Exception {
		// ���ϵͳ�ľ���·��
		String dir = servlet.getServletContext().getRealPath("../merchantImage");
		// InInputStream�����Դ��ض�����Դ��ȡ�ֽڵķ�����
		InputStream streamIn = file.getInputStream(); // ������ȡ�û��ϴ��ļ��Ķ���
		// �õ����ֽ�������byte,���ǿ���ֱ����file.getFileSize(),Ҳ�����ڴ�����ȡ����ʱ��streamIn.available();
		// int ok=streamIn.available();
		int ok = file.getFileSize();
		String strFee = null;
		// ����ط��Ǵ����ϴ���ΪM��λ����ʱ����һ������kb,����һ����byte;
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
		log.info(streamIn.available() + "�ļ���Сbyte");
		// �����io���µ��ϴ��ļ���
		File uploadFile = new File(dir); // ָ���ϴ��ļ���λ��
		if (!uploadFile.exists() || uploadFile == null) { // �ж�ָ��·��dir�Ƿ���ڣ��������򴴽�·��
			uploadFile.mkdirs();
		}
		// �ϴ���·��+�ļ���
		String path = uploadFile.getPath() + "/" + name;
		// OutputStream������ĳ��Ŀ��д���ֽڵĳ����࣬����ط�д��Ŀ����path��ͨ�������FileOutputStreamȥд
		OutputStream streamOut = new FileOutputStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// �����ݶ���byte�����һ���֣����ж����ֽ��������ֵ��8192��������ֽڽ��洢����buffer[0]��buffer[0+8190-1]�Ĳ�����
		// streamIn.read�������ص���ʵ�ʶ�ȡ�ֽ���Ŀ.�������ĩβ�򷵻�-1.���bytesRead����Ϊ0���ʾû�ж�ȡ�κ��ֽڡ�
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			// д��buffer�����һ���֣���buf[0]��ʼд�벢д��bytesRead���ֽڣ����write��������������ֱ���ֽ�д����ɡ�
			streamOut.write(buffer, 0, bytesRead);
		}
		// �ر����������,����File����
		streamOut.close();
		streamIn.close();
		file.destroy();
	}

	// ʵ���ļ�������
	public ActionForward downFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getParameter("path");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;

		// ����Ǵӷ�������ȡ����������ϵͳ�ľ���·�������� String filepath =
		// servlet.getServletContext().getRealPath("/" + path);
		String filepath = servlet.getServletContext().getRealPath("../merchantImage")+"/" +path;
		log.info("�ļ�·��" + filepath);
		File uploadFile = new File(filepath);
		fis = new FileInputStream(uploadFile);
		bis = new BufferedInputStream(fis);
		fos = response.getOutputStream();
		bos = new BufferedOutputStream(fos);
		// ����;��ǵ������ضԻ���Ĺؼ�����
		//response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(path, "utf-8"));
		int bytesRead = 0;
		// ����ط���ͬ�ϴ���һ�����ҾͲ���˵�ˣ������������������ȶ���Ȼ���������ȥд��Ψһ��ͬ�������õ��ǻ������������
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
