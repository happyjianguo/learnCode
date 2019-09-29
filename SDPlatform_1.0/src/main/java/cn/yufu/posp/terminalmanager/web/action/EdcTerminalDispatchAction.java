package cn.yufu.posp.terminalmanager.web.action;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.yufu.posp.merchant.domain.logic.TabBusRoleMenuLogicInterface;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTerminalLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalForm;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalUploadForm;
import net.sf.json.JSONObject;


/**
 * @author zhouya �ն������趨
 * 
 */
public class EdcTerminalDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");

	public EdcTerminalDispatchAction() {
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
			ActionForward actionForward = mapping.findForward("showAllEdcTerminal");
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
	public org.apache.struts.action.ActionForward queryEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

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
			EdcTerminal queryModel = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);

				}

			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					id.setTerminalId(terminalId);
				}

			}

			queryModel.setId(id);

			// ���ն����Ͳ�ѯ
			String softVer = request.getParameter("_softVer");
			if (terminalId != null) {
				queryModel.setSoftVer(softVer);
			}
			
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllEdcTerminal");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcTerminalDispatchAction.queryEdcTerminal()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public org.apache.struts.action.ActionForward deleteEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcTerminal> keysList = new ArrayList<EdcTerminal>();
		try {
			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()��ʼ���ã�ɾ����¼��");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("ɾ����¼ ��������==" + strId);

				if (tt.length == 16) {

					EdcTerminal edcTerminal = new EdcTerminal();
					EdcTerminalId id = new EdcTerminalId();
					id.setMerchantId(tt[0].trim());
					id.setTerminalId(tt[1].trim());
					edcTerminal.setId(id);

					keysList.add(edcTerminal);
				}
			}
			if (keysList.size() > 0) {
				EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()�������ã�ɾ����¼��");
		} catch (Exception e) {

			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()ɾ����¼�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcTerminal");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("edcTerminalSr");
	}

	/**
	 * ����
	 */
	public org.apache.struts.action.ActionForward createEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTerminalDispatchAction.createEdcTerminal()��ʼ���ã�����һ���µ���Ϣ��");

			UserData ud = getSessionUserData(request);
			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// �õ�Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			// �½�һ��Model
			EdcTerminal edcTerminal = new EdcTerminal();

			if (ud != null) {
				if(ud.getUserId().length()>6){
					edcTerminalForm.setUpdateOper(ud.getUserId().substring(0,6));
				}else{
					edcTerminalForm.setUpdateOper(ud.getUserId());
				}
				edcTerminalForm.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
				edcTerminalForm.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcTerminalDispatchAction.createEdcTerminal()�������ã�����һ���µ���Ϣ��");

		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.createEdcTerminal()����һ���µ���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcTerminal");
	}

	/**
	 * ��ʾ�޸��ն������趨��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcTerminalByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()��ʼ���ã���ʾ�޸��ն������趨��Ϣ���档");
			System.out.println("0-----------EdcTerminalDispatchAction.queryEdcTerminalByKey()��ʼ���ã���ʾ�޸��ն������趨��Ϣ���档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");
			System.out.println("1--------"+tt.toString()+"---�ֽ����");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcTerminal edcTerminal = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();

			if (tt.length == 16) {
				id.setMerchantId(tt[0].trim());
				id.setTerminalId(tt[1].trim());
				edcTerminal.setId(id);
				System.out.println("2-----------���ò�ѯ����");

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
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// �õ�Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			System.out.println("3-----------logic.findItemByKey---------"+edcTerminalForm.toString()+"--------before");
			map = logic.findItemByKey(edcTerminal, ud);
			System.out.println("4-----------logic.findItemByKey---"+map.toString()+"--------------end");
			EdcTerminal model = (EdcTerminal) map.get("Infolist");
			System.out.println("5-----------Infolist-"+model.toString()+"----------------before");
			BeanUtils.copyProperties(edcTerminalForm, model);
//			edcTerminalForm =  edcTerminalModelToFrom(model);
			System.out.println("6-------"+edcTerminalForm.toString()+"----EdcTerminalDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�޸��ն������趨��Ϣ���档");
			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�޸��ն������趨��Ϣ���档");

		} catch (Exception e) {

			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()��ʾ�޸��ն������趨��Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("queryEdcTerminalByKey");
	}

	/**
	 * EdcTerminal TO EdcTerminalForm
	 * @param model
	 * @return
	 */
	public EdcTerminalForm edcTerminalModelToFrom(EdcTerminal model) {
		EdcTerminalForm froms = new EdcTerminalForm();
		froms.setId(model.getId());
		/**
		 * EDC�豸���˵��
		 * <p>
		 * �ն������õ�EDC�豸�Ĺ�񡢳��ҡ����ܵ�˵�������֣���������ֲ�ͬ�����ͺŵ�EDC�豸��
		 * </p>
		 **/
		froms.setEdcDoc(model.getEdcDoc());
		/**
		 * ��ӡ������
		 * <p>
		 * EDC�ն������õĴ�ӡ���ͺ�
		 * </p>
		 */
		froms.setPrinterType(model.getPrinterType());
		/** PIN PAD���� */
		froms.setPinpadType(model.getPinpadType());
		/** ��װ���� YYYYMMDD */
		froms.setSetDate(model.getSetDate());
		/**
		 * ��װ�ص�
		 * <p>
		 * EDC�������̻�λ�õ�˵�������֣�����ά��ʱ�ο���
		 * </p>
		 */
		froms.setSetAddr(model.getSetAddr());
		/**
		 * EDC�ն�״̬
		 * <p>
		 * Y��-����,��N��-����<br>
		 * <br>
		 * �����ᡱ��־��EDC�ն˷��ϵĽ��׾ܾ�<br>
		 * </p>
		 */
		froms.setTerminalStat(model.getTerminalStat());
		/**
		 * EDC�豸�ͺ�
		 * <p>
		 * Լ����EDC�豸���ͱ�ʶ�����ݴ˿��б��EDCʹ���������͵ĸ�ʽ���н���������
		 * <p>
		 */
		froms.setEdcType(model.getEdcType());
		/** EDC����汾 */
		froms.setSoftVer(model.getSoftVer());
		/** �������ر�־ */
		froms.setDownloadFlag(model.getDownloadFlag());
		/** ��������ģʽ */
		froms.setDownloadMode(model.getDownloadMode());
		/** ������ */
		froms.setUpdateOper(model.getUpdateOper());
		/** �������� YYYYMMDD */
		froms.setUpdateDate(model.getUpdateDate());
		/** ����ʱ�� hhmmss */
		froms.setUpdateTime(model.getUpdateTime());
		/** �ն�״̬ ---ҳ����ʾ��--- */
		froms.setCh_terminalStat(model.getCh_terminalStat());
		/** ��ǰ��ʱ����� ---ҳ����ʾ��--- */
		froms.setCh_dateAndTime(model.getCh_dateAndTime());
		/** ҵ���ɫ ---ҳ����ʾ��--- */
		froms.setBusRoleId(model.getBusRoleId());
		/** ҵ���ɫ�������� ---ҳ����ʾ��--- */
		froms.setBusRoleName(model.getBusRoleName());
		return froms;
	}
	/**
	 * ��ʾ�ն�������Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryDetailByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalDispatchAction.queryDetailByKey()��ʼ���ã���ʾ�ն�������Ϣ���档");
			
			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("\\|");
			
			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcTerminal edcTerminal = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			
			if (tt.length == 16) {
				id.setMerchantId(tt[0].trim());
				id.setTerminalId(tt[1].trim());
				edcTerminal.setId(id);
			}
			
			UserData ud = getSessionUserData(request);
			
			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// �õ�Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			
			map = logic.findItemByKey(edcTerminal, ud);
			
			EdcTerminal model = (EdcTerminal) map.get("Infolist");
			
			BeanUtils.copyProperties(edcTerminalForm, model);
			
			log.info("EdcTerminalDispatchAction.queryDetailByKey()�������ã���ʾ�ն�������Ϣ���档");
			
		} catch (Exception e) {
			
			log.info("EdcTerminalDispatchAction.queryDetailByKey()��ʾ�ն�������Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		
		return mapping.findForward("queryDetail");
	}

	/**
	 * �޸��ն������趨
	 */
	public org.apache.struts.action.ActionForward saveEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// �½�һ��Model
		EdcTerminal edcTerminal = new EdcTerminal();
		EdcTerminalId id = new EdcTerminalId();
		try {
			log.info("EdcTerminalDispatchAction.saveEdcTerminal()��ʼ���ã��޸��ն������趨��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// �õ�Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;

			if (ud != null) {

				id.setMerchantId(edcTerminalForm.getId().getMerchantId());
				id.setTerminalId(edcTerminalForm.getId().getTerminalId());

				edcTerminal.setId(id);

				edcTerminal.setTerminalStat(edcTerminalForm.getTerminalStat());
				edcTerminal.setEdcType(edcTerminalForm.getEdcType());
				edcTerminal.setSoftVer(edcTerminalForm.getSoftVer());
				edcTerminal.setDownloadFlag(edcTerminalForm.getDownloadFlag());
				edcTerminal.setDownloadMode(edcTerminalForm.getDownloadMode());
				edcTerminal.setEdcDoc(edcTerminalForm.getEdcDoc());
				edcTerminal.setPrinterType(edcTerminalForm.getPrinterType());
				edcTerminal.setPinpadType(edcTerminalForm.getPinpadType());
				edcTerminal.setSetDate(edcTerminalForm.getSetDate());
				edcTerminal.setSetAddr(edcTerminalForm.getSetAddr());
				edcTerminal.setUpdateOper(ud.getUserId());
				edcTerminal.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
				edcTerminal.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

				//�������ݵ��ն�ҵ���ɫ��Ϣ��
				
				edcTerminal.setBusRoleId(edcTerminalForm.getBusRoleId());
				
				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcTerminalDispatchAction.saveEdcTerminal()�������ã��޸��ն������趨��");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.saveEdcTerminal()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcTerminal");
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
			cell.setCellValue("�밴ģ���������ݣ��̻���š��ն˱�š��ն�״̬(Y-����,N-����)�������̻��ű����Ѵ��ڣ��ն�����ֻ��Ϊ��ͨ�ն�(common)");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�̻����", "�ն˱��", "�ն�״̬", "�豸�ͺ�", "�豸���˵��", "��ӡ������", "�ն�����", "��װ����", "��װ�ص�" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createCell(row, (short) 0, "123456789012345");
			POIUtils.createCell(row, (short) 1, "00000001");
			POIUtils.createCell(row, (short) 2, "Y");
			POIUtils.createCell(row, (short) 3, "STD");
			POIUtils.createCell(row, (short) 4, "");
			POIUtils.createCell(row, (short) 5, "950");
			POIUtils.createCell(row, (short) 6, "common");
			POIUtils.createCell(row, (short) 7, "20141001");
			POIUtils.createCell(row, (short) 8, "��������");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�̻��ն˵���ģ��", "UTF-8");
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
			EdcTerminalUploadForm uf = (EdcTerminalUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			List<String> list=new ArrayList<String>();
			List<String> list1=new ArrayList<String>();
			List<Integer> list2=new ArrayList<Integer>();
			List<Integer> list3=new ArrayList<Integer>();
			label1:
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				List<EdcTerminal> edcTerminalList = new ArrayList<EdcTerminal>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// �½�һ��Model
					EdcTerminal edcTerminal = new EdcTerminal();
					edcTerminal.setUpdateOper(ud.getUserId());
					edcTerminal.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					edcTerminal.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					EdcTerminalId edcTerminalId = new EdcTerminalId();
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					edcTerminalId.setMerchantId(merchantId);
					edcTerminalId.setTerminalId((POIUtils.getStringFromExcelCell(row.getCell(1))));
					edcTerminal.setId(edcTerminalId);
					edcTerminal.setTerminalStat(POIUtils.getStringFromExcelCell(row.getCell(2)));
					edcTerminal.setEdcType(POIUtils.getStringFromExcelCell(row.getCell(3)));
					edcTerminal.setEdcDoc(POIUtils.getStringFromExcelCell(row.getCell(4)));
					edcTerminal.setPrinterType(POIUtils.getStringFromExcelCell(row.getCell(5)));
					edcTerminal.setSoftVer("common");
					edcTerminal.setSetDate(POIUtils.getStringFromExcelCell(row.getCell(7)));
					edcTerminal.setSetAddr(POIUtils.getStringFromExcelCell(row.getCell(8)));
					edcTerminal.setDownloadMode("0");
					edcTerminalList.add(edcTerminal);
				}
				int temp=0,temp1=0,temp2=0;
				for(int i = 0; i < edcTerminalList.size(); i++){
					if((edcTerminalList.get(i).getId().getTerminalId().indexOf("A")!=0)&&(edcTerminalList.get(i).getEdcType().indexOf("APP")==0)||
							(edcTerminalList.get(i).getId().getTerminalId().indexOf("A")==0)&&(edcTerminalList.get(i).getEdcType().indexOf("APP")!=0)){
						temp=i;
						list.add(String.valueOf(temp+3)); 
					}
					 for(int j=edcTerminalList.size()-1;j>i;j--){
		                    if(edcTerminalList.get(i).getId().getTerminalId().equals(edcTerminalList.get(j).getId().getTerminalId())&&
		                    		edcTerminalList.get(i).getId().getMerchantId().equals(edcTerminalList.get(j).getId().getMerchantId())){
		                    	temp1=j;
		                    	temp2=i;
		                    	list1.add(String.valueOf(temp2+3)); 
		                    	list1.add(String.valueOf(temp1+3)); 
		                    	//ȥ��List<Object>�������ظ���Ԫ��,Ŀ�ģ�����ѭ��������
		                    	edcTerminalList.remove(j);
		                    }
		                }
					
				}
				
				if(list.size()>0){
					for(int j = 0;j < 1;){
						jsonObj.put("result", "����ʧ�ܣ�Excel�ļ��е�"+list+"�е��ն˱�ź��ն��豸�ͺ�֮�����ƥ��");
						break label1;
					}
				}
				if(list1.size()>0){
					for(int j = 0;j < list1.size();j++){
						list2.add(Integer.valueOf(list1.get(j)));
						if(j+1>=list1.size()){
							Collections.sort(list2);
					        for(Iterator<Integer> it=list2.iterator();it.hasNext();){
					            Object obj=it.next();
					            if(!(list3.contains(obj))){
					            	list3.add((Integer) obj);
					            }
					        }
					        list2.clear();
					        list2.addAll(list3);
							jsonObj.put("result", "����ʧ�ܣ�Excel�ļ��е�"+list2+"�е��̻���ź��ն˱����ͬΥ��Ψһ��������");
							break label1;
						}
					}
				}
				// �õ�Logic
				EdcTerminalLogicInterface edcTerminaLogic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
				edcTerminaLogic.saveUploadItem(edcTerminalList, ud);
				// ����
				jsonObj.put("result", "�����ɹ��� �ѳɹ����� " + edcTerminalList.size() + " ����¼��");
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

	/*** ��������Ҫ�� **/
	public org.apache.struts.action.ActionForward queryEdcTerminalMode(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

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
			EdcTerminal queryModel = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);

				}

			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					id.setTerminalId(terminalId);
				}

			}
			String _downloadMode = request.getParameter("_downloadMode");
			if (_downloadMode != null) {
				if (!_downloadMode.trim().equals("")) {
					queryModel.setDownloadMode(_downloadMode);
				}
				
			}
			queryModel.setId(id);
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));
			request.setAttribute("pageInfoResult", pageInfo);
			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllEdcTerminalMode");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/*** ��������Ҫ����ת **/
	public org.apache.struts.action.ActionForward toQueryEdcTerminalMode(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		PageInfoModel pageInfo = new PageInfoModel();
		request.setAttribute("pageInfoResult", pageInfo);
		return mapping.findForward("showAllEdcTerminalMode");
	}

	/*** ��������Ҫ���б� **/
	public org.apache.struts.action.ActionForward queryEdcTerminalModeList(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		PageInfoModel pageInfo = new PageInfoModel();
		return mapping.findForward("showEdcTerminalModeList");
	}

	/**
	 * ��������Ҫ���趨
	 */
	public org.apache.struts.action.ActionForward setEdcTerminalMode(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		try {
			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()��ʼ���ã���������Ҫ���趨��");

			// �õ�Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			String mode = request.getParameter("mode");

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");
				if (tt.length == 16) {

					EdcTerminal edcTerminal = new EdcTerminal();
					EdcTerminalId id = new EdcTerminalId();
					id.setMerchantId(tt[0].trim());
					id.setTerminalId(tt[1].trim());
					edcTerminal.setId(id);
					map = logic.findItemByKey(edcTerminal, ud);
					EdcTerminal model = (EdcTerminal) map.get("Infolist");
					model.setDownloadMode(mode);
					model.setUpdateOper(ud.getUserId());
					model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					logic.saveItem(model, ud);

				}
			}

			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()�������ã���������Ҫ���趨��");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()��������Ҫ���趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("queryEdcTerminalMode");
	}
	
	/**
	 * ��ȡҵ���ɫ�˵��б�
	 */
	public org.apache.struts.action.ActionForward findBusRoleList(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.findBusRoleList()��ʼ����:��ȡҵ���ɫ�˵��б�" + getSessionUserData(request).getUserId());
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			UserData ud = getSessionUserData(request);
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			List<TabBusRoleMenuModel> list = logic.findBusRoleList(ud);
			String areaStr = "";
			for (int i = 0; i < list.size(); i++) {
				TabBusRoleMenuModel areaCodeInfo = list.get(i);
				areaStr = areaStr + areaCodeInfo.getBusRoleName().trim()+ "|"+ areaCodeInfo.getBusRoleId().trim() + "|";
			}
			
			// ����
			if((areaStr!="")&&(areaStr!=null)){
				out.print(areaStr);		
			}else{
				out.print(false);//������
			}

			log.info("TabBusRoleMenuAction.findBusRoleList()��������:��ȡҵ���ɫ�˵��б�" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.findBusRoleList()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward(null);

	}
}
