package cn.yufu.posp.terminalmanager.web.action;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcNewfkterminalOrmLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.web.form.EdcNewfkterminalOrmForm;
import cn.yufu.posp.terminalmanager.web.form.EdcNewfkterminalOrmUploadForm;

public class EdcNewfkterminalOrmDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");

	public EdcNewfkterminalOrmDispatchAction() {
	}
	/**
	 * ��ʼҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public ActionForward page(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("MerchantAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}
	/*** ��ѯ�ն������趨 **/
	public org.apache.struts.action.ActionForward queryEdcNewfkterminalOrm(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcNewfkterminalOrm()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");

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
					pageInfo.setOrderField("id.merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.terminalId");
				if (sortField.equals("3"))
					pageInfo.setOrderField("terminalStat");
				if (sortField.equals("4"))
					pageInfo.setOrderField("edcType");
				if (sortField.equals("5"))
					pageInfo.setOrderField("updateOper");
				if (sortField.equals("6"))
					pageInfo.setOrderField("updateDate");
			}

			// ���ò�ѯ����
			EdcNewfkterminalOrm queryModel = new EdcNewfkterminalOrm();
			EdcNewfkterminalOrmForm queryForm = (EdcNewfkterminalOrmForm) form;
			// ���̻���Ų�ѯ
			//String merchantId = request.getParameter("_merchantId");
			String merchantId = queryForm.getQueryMerchantId();
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			//String terminalId = request.getParameter("_terminalId");
			String terminalId = queryForm.getQueryTerminalId();
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//String logonStatus = request.getParameter("_logonStatus");
			String logonStatus = queryForm.getQueryLogonStatus();
			if (logonStatus != null) {
				if (!logonStatus.trim().equals("")) {
					queryModel.setLogonStatus(logonStatus);
				}
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcNewfkterminalOrm()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcNewfkterminalOrm()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public org.apache.struts.action.ActionForward deleteEdcNewfkterminalOrm(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		List<EdcNewfkterminalOrm> keysList = new ArrayList<EdcNewfkterminalOrm>();
		try {
			log.info("EdcNewfkterminalOrmDispatchAction.deleteEdcNewfkterminalOrm()��ʼ���ã�ɾ����¼��");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("ɾ����¼ ��������==" + strId);

				if (tt.length == 4) {

					EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();
					edcTerminal.setMerchantId(tt[0].trim());
					edcTerminal.setTerminalId(tt[1].trim());
					edcTerminal.setModuleId(tt[2].trim());

					// edcTerminal.setTerminalStat(tt[2].trim());
					// edcTerminal.setEdcType(tt[3].trim());
					// edcTerminal.setSoftVer(tt[4].trim());
					// edcTerminal.setDownloadFlag(tt[5].trim());
					// edcTerminal.setDownloadMode(Integer.parseInt(tt[6].trim()));
					// edcTerminal.setUpdateOper(tt[7].trim());
					// edcTerminal.setUpdateDate(tt[8].trim());
					// edcTerminal.setUpdateTime(tt[9].trim());

					keysList.add(edcTerminal);
				}
			}
			if (keysList.size() > 0) {
				EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcNewfkterminalOrmDispatchAction.deleteEdcNewfkterminalOrm()�������ã�ɾ����¼��");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDispatchAction.deleteEdcNewfkterminalOrm()ɾ����¼�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ����
	 */
	public org.apache.struts.action.ActionForward createEdcNewfkterminalOrm(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDispatchAction.createEdcNewfkterminalOrm()��ʼ���ã�����һ���µ���Ϣ��");

			UserData ud = getSessionUserData(request);
			// �õ�Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
			// �õ�Form
			EdcNewfkterminalOrmForm edcTerminalForm = (EdcNewfkterminalOrmForm) form;
			// �½�һ��Model
			EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();

			if (ud != null) {

				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcNewfkterminalOrmDispatchAction.createEdcNewfkterminalOrm()�������ã�����һ���µ���Ϣ��");

		} catch (Exception e) {
			log.info("EdcNewfkterminalOrmDispatchAction.createEdcNewfkterminalOrm()����һ���µ���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸��ն������趨��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcNewfkterminalOrmByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcTerminalByKey()��ʼ���ã���ʾ�޸��ն������趨��Ϣ���档");
			EdcNewfkterminalOrmForm newForm = (EdcNewfkterminalOrmForm) form;
			// System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				log.info("EdcNewfkterminalOrmDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�����ն������趨��Ϣ���档");
				return mapping.findForward("add");
			}
			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();

			if (tt.length == 4) {
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());
				edcTerminal.setModuleId(tt[2].trim());

				// edcTerminal.setTerminalStat(tt[2].trim());
				// edcTerminal.setEdcType(tt[3].trim());
				// edcTerminal.setSoftVer(tt[4].trim());
				// edcTerminal.setDownloadFlag(tt[5].trim());
				// edcTerminal.setDownloadMode(Integer.parseInt(tt[6].trim()));
				// edcTerminal.setUpdateOper(tt[7].trim());
				// edcTerminal.setUpdateDate(tt[8].trim());
				// edcTerminal.setUpdateTime(tt[9].trim());

			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
			// �õ�Form
			EdcNewfkterminalOrmForm edcTerminalForm = (EdcNewfkterminalOrmForm) form;

			map = logic.findItemByKey(edcTerminal, ud);

			EdcNewfkterminalOrm model = (EdcNewfkterminalOrm) map.get("Infolist");

			BeanUtils.copyProperties(edcTerminalForm, model);

			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�޸��ն������趨��Ϣ���档");

		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDispatchAction.queryEdcTerminalByKey()��ʾ�޸��ն������趨��Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * �޸��ն������趨
	 */
	public org.apache.struts.action.ActionForward saveEdcNewfkterminalOrm(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// �½�һ��Model
		EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();
		try {
			log.info("EdcNewfkterminalOrmDispatchAction.saveEdcNewfkterminalOrm()��ʼ���ã��޸��ն������趨��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
			// �õ�Form
			EdcNewfkterminalOrmForm edcTerminalForm = (EdcNewfkterminalOrmForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcNewfkterminalOrmDispatchAction.saveEdcNewfkterminalOrm()�������ã��޸��ն������趨��");
		} catch (Exception e) {
			log.info("EdcNewfkterminalOrmDispatchAction.saveEdcNewfkterminalOrm()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	// ����
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("��ʼ����excelģ��");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("�밴ģ���������ݣ��̻���š��̻��ն˱��ϵͳ������ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = {"�̻����","�̻��ն˱��","���б�ʶ","�����̻���","�����ն˺�","ģ��ID","ϵͳ��ˮ��","������ˮ��","���κ�"};
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "00000001");
			POIUtils.createTextCell(book, row, (short) 2, "99999999");
			POIUtils.createTextCell(book, row, (short) 3, "007110154114660");
			POIUtils.createTextCell(book, row, (short) 4, "01047321");
			POIUtils.createTextCell(book, row, (short) 5, "66");
			POIUtils.createTextCell(book, row, (short) 6, "0");
			POIUtils.createTextCell(book, row, (short) 7, "0");
			POIUtils.createTextCell(book, row, (short) 8, "0");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�¸����ն˵���ģ��", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsDispatchAction.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

	// �ϴ�
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("��ʼ��������");
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		try {
			writer = response.getWriter();
			EdcNewfkterminalOrmUploadForm uf = (EdcNewfkterminalOrmUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				List<EdcNewfkterminalOrm> edcNewfkterminalOrmList = new ArrayList<EdcNewfkterminalOrm>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// �½�һ��Model
					EdcNewfkterminalOrm edcNewfkterminalOrm = new EdcNewfkterminalOrm();
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					edcNewfkterminalOrm.setMerchantId(merchantId);
					edcNewfkterminalOrm.setTerminalId(POIUtils.getStringFromExcelCell(row.getCell(1)));
					edcNewfkterminalOrm.setBankId(POIUtils.getStringFromExcelCell(row.getCell(2)));
					edcNewfkterminalOrm.setBankMerchantId(POIUtils.getStringFromExcelCell(row.getCell(3)));
					edcNewfkterminalOrm.setBankTerminalId(POIUtils.getStringFromExcelCell(row.getCell(4)));
					edcNewfkterminalOrm.setModuleId(POIUtils.getStringFromExcelCell(row.getCell(5)));
					edcNewfkterminalOrm.setSysTrace(POIUtils.getStringFromExcelCell(row.getCell(6)));
					edcNewfkterminalOrm.setBankTrace(POIUtils.getStringFromExcelCell(row.getCell(7)));
					edcNewfkterminalOrm.setBatchNo(POIUtils.getStringFromExcelCell(row.getCell(8)));
					
					edcNewfkterminalOrm.setPinFmt("2");//PIN�㷨��ʶ 1: ANSI X98��ʽ���������˺ţ� 2: ANSI X98�㷨�������˺ţ�
					edcNewfkterminalOrm.setEncMethod("6");//�����㷨 0: DES 6: 3DES
					edcNewfkterminalOrm.setMacFlag("1");//MAC�����־ 0: �����ն˲��� 1: ����
					edcNewfkterminalOrm.setSettStatus("0");//����״̬ 0: ��������״̬ 1: ��Ҫ���� 2: ���ʽ�����
					edcNewfkterminalOrm.setLogonStatus("0");//�������POS�ն�ǩ��״̬ 0: ǩ��״̬ 1: ��ǩ�� 2:ǩ���쳣
					edcNewfkterminalOrm.setFlag("1");//��ͨ��־ 1��������ͨ  0��δ��ͨ
					edcNewfkterminalOrmList.add(edcNewfkterminalOrm);
				}
				// �õ�Logic
				EdcNewfkterminalOrmLogicInterface edcTerminaOrmLogic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
				edcTerminaOrmLogic.saveUploadItem(edcNewfkterminalOrmList, ud);
				// ����
				jsonObj.put("result", "�����ɹ��� �ѳɹ����� " + edcNewfkterminalOrmList.size() + " ����¼��");
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e);
			try {
				jsonObj.put("result", "����ʧ�ܣ�");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("���������̻��ն˱���Excel�ļ�,�ر�IO��");
				} catch (Exception e) {
					log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return null;
	}
	
}