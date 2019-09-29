package cn.yufu.posp.queryManager.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.queryManager.domain.logic.CurTranLsWarnLogicInterface;
import cn.yufu.posp.queryManager.domain.model.CurTranLsWarn;

public class CurTranLsWarnDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("query");

	/**
	 * ������ˮ
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
			log.info("CurTranLsDispatchAction.queryAll()��ʼ���ã�������ˮ");

			// �õ�Logic
			CurTranLsWarnLogicInterface curTranLsLogic = (CurTranLsWarnLogicInterface) getBean("curTranLsWarnLogic");

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
			// String sortField = request.getParameter(dParams[2]);
			// if (sortField != null) {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("traceNo");
			// else if (sortField.equals("2"))
			// pageInfo.setOrderField("cardNo");
			// }

			// ���ò�ѯ����
			CurTranLsWarn queryModel = new CurTranLsWarn();
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTranceno(new BigDecimal(traceNo));
				}
			}
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardno(cardNo);
				}
			}
			String merno = request.getParameter("_merno");
			if (merno != null) {
				if (!merno.trim().equals("")) {
					queryModel.setMerno(merno);
				}
			}
			String rulecode = request.getParameter("_rulecode");
			if (rulecode != null) {
				if (!rulecode.trim().equals("")) {
					queryModel.setRulecode(rulecode);
				}
			}

			pageInfo = curTranLsLogic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("CurTranLsDispatchAction.queryAll()�������ã����һ�����Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	// ����
	public org.apache.struts.action.ActionForward exportCurTranLsWarn(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// �õ�Logic
			CurTranLsWarnLogicInterface curTranLsLogic = (CurTranLsWarnLogicInterface) getBean("curTranLsWarnLogic");
			// ���ò�ѯ����
			CurTranLsWarn queryModel = new CurTranLsWarn();
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTranceno(new BigDecimal(traceNo));
				}
			}
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardno(cardNo);
				}
			}
			String merno = request.getParameter("_merno");
			if (merno != null) {
				if (!merno.trim().equals("")) {
					queryModel.setMerno(merno);
				}
			}
			String rulecode = request.getParameter("_rulecode");
			if (rulecode != null) {
				if (!rulecode.trim().equals("")) {
					queryModel.setRulecode(rulecode);
				}
			}

			List list = curTranLsLogic.queryExport(queryModel, getSessionUserData(request));
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headCol = { "POS���κ�", "����", "�̻��� ", "�̻�����", "�ն˺�", "�������", "������", "���׽�� ", "�Ǽ�ʱ��", "���ؽ���ʱ��" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			String[] actionstatus = new String[] { "", "��ע", "�ܾ�", "����" };
			// ��������
			for (int i = 0; i < list.size(); i++) {
				CurTranLsWarn curTranLs = (CurTranLsWarn) list.get(i);
				row = sheet.createRow(i + 1);
				createCell(row, (short) 0, curTranLs.getBatno());
				createCell(row, (short) 1, curTranLs.getCardno());
				createCell(row, (short) 2, curTranLs.getMerno());
				createCell(row, (short) 3, curTranLs.getMername());
				createCell(row, (short) 4, curTranLs.getTermno());
				createCell(row, (short) 5, curTranLs.getTranceno().toString());
				createCell(row, (short) 6, actionstatus[Integer.valueOf(curTranLs.getActionstatus())]);
				createCell(row, (short) 7, curTranLs.getTranamt().toString());
				createCell(row, (short) 8, curTranLs.getCteatetime().toString());
				createCell(row, (short) 9, curTranLs.getLocaltime().toString());
			}

			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�쳣��ˮ��ϸ", "UTF-8");
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

	private void createCell(HSSFRow row, short colNum, String value) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellValue(value);
	}

}
