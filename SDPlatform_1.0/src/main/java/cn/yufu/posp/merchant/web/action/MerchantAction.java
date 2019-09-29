package cn.yufu.posp.merchant.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.web.form.MerchantBoForm;
import cn.yufu.posp.merchant.web.form.MerchantForm;
import cn.yufu.posp.merchant.web.form.MerchantUploadForm;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public class MerchantAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantAction() {

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
			log.info("MerchantAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

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
			MerchantBaseModel queryModel = new MerchantBaseModel();
			// ���̻���Ų�ѯ
			String search = request.getParameter("queryMerid");
			if (search != null)
				queryModel.setMerchantId(search);

			// ���̻�״̬��ѯ
			String search1 = request.getParameter("queryMerstat");
			if (search1 != null)
				queryModel.setMerchantStat(search1);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

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

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		HashMap hashMap = new HashMap();
		try {
			log.info("MerchantAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

			// ��ʼ������
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			MerchantForm newForm = (MerchantForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// �½�һ��Model
			MerchantBaseModel model = (MerchantBaseModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// �õ�Form
			MerchantForm newForm = (MerchantForm) form;

			String limit = newForm.getRefundLimit();
			if (limit == null || "".equals(limit)) {
				limit = "0";
			}
			newForm.setRefundLimit(limit);
			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// �½�һ��Model
			MerchantBaseModel model = new MerchantBaseModel();

			// ��������
			model.setMerchantId(newForm.getMerchantId());
			model.setMcc(newForm.getMcc());
			model.setAbbrCname(newForm.getAbbrCname());
			model.setAbbrEname(newForm.getAbbrEname());
			model.setAddressChn(newForm.getAddressChn());
			model.setAddressEng(newForm.getAddressEng());
			model.setCityCname(newForm.getCityCname());
			model.setCityEname(newForm.getCityEname());
			model.setMerchantCname(newForm.getMerchantCname());
			model.setMerchantEname(newForm.getMerchantEname());
			model.setManager(newForm.getManager());
			model.setPostCode(newForm.getPostCode());
			model.setTelephone(newForm.getTelephone());
			model.setFax(newForm.getFax());
			model.setSettleMerchId(newForm.getSettleMerchId());
			model.setZbank(newForm.getZbank());
			model.setSignBankId(newForm.getSignBankId());
			model.setSettleMode(newForm.getSettleMode());
			model.setSignDate(newForm.getSignDate());
			model.setMerchantStat(newForm.getMerchantStat());
			model.setCcyType(newForm.getCcyType());
			model.setSignHostId(newForm.getSignHostId());
			model.setLockMode(newForm.getLockMode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			// ��������
			model.setSndName(newForm.getSndName());
			model.setSndAcct(newForm.getSndAcct());
			model.setSndBank(newForm.getSndBank());
			model.setRcvName(newForm.getRcvName());
			model.setRcvBank(newForm.getRcvBank());
			model.setRcvAcct1(newForm.getRcvAcct1());
			model.setRcvAcct2(newForm.getRcvAcct2());
			// �˻�
			model.setRefundLimit(newForm.getRefundLimit());
			model.setRefundCheck(newForm.getRefundCheck());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("MerchantAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// �õ�Form
			MerchantForm newForm = (MerchantForm) form;

			String limit = newForm.getRefundLimit();
			if (limit == null || "".equals(limit)) {
				limit = "0";
			}
			newForm.setRefundLimit(limit);
			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// �½�һ��Model
			MerchantBaseModel model = new MerchantBaseModel();

			// ��������
			model.setMerchantId(newForm.getMerchantId());
			model.setMcc(newForm.getMcc());
			model.setAbbrCname(newForm.getAbbrCname());
			model.setAbbrEname(newForm.getAbbrEname());
			model.setAddressChn(newForm.getAddressChn());
			model.setAddressEng(newForm.getAddressEng());
			model.setCityCname(newForm.getCityCname());
			model.setCityEname(newForm.getCityEname());
			model.setMerchantCname(newForm.getMerchantCname());
			model.setMerchantEname(newForm.getMerchantEname());
			model.setManager(newForm.getManager());
			model.setPostCode(newForm.getPostCode());
			model.setTelephone(newForm.getTelephone());
			model.setFax(newForm.getFax());
			model.setSettleMerchId(newForm.getSettleMerchId());
			model.setZbank(newForm.getZbank());
			model.setSignBankId(newForm.getSignBankId());
			model.setSettleMode(newForm.getSettleMode());
			model.setSignDate(newForm.getSignDate());
			model.setMerchantStat(newForm.getMerchantStat());
			model.setCcyType(newForm.getCcyType());
			model.setSignHostId(newForm.getSignHostId());
			model.setLockMode(newForm.getLockMode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			// ��������
			model.setSndName(newForm.getSndName());
			model.setSndAcct(newForm.getSndAcct());
			model.setSndBank(newForm.getSndBank());
			model.setRcvName(newForm.getRcvName());
			model.setRcvBank(newForm.getRcvBank());
			model.setRcvAcct1(newForm.getRcvAcct1());
			model.setRcvAcct2(newForm.getRcvAcct2());
			// �˻�
			model.setRefundLimit(newForm.getRefundLimit());
			model.setRefundCheck(newForm.getRefundCheck());

			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("MerchantAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	/**
	 * ajax��ѯǩԼ�кź�������
	 */
	public org.apache.struts.action.ActionForward findBankandHost(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		log.info("MerchantAction.findMccName()��ʼ���ã�ajax��ѯǩԼ�кź�������");

		UserData ud = getSessionUserData(request);
		// �õ�Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// �õ�Form
		MerchantForm newForm = (MerchantForm) form;
		String bankId = newForm.getSignBankId();
		List<MerchantBaseModel> bankList = logic.findSignBankInfo(bankId, ud);
		if (bankList.size() > 0) {
			PrintWriter out;
			try {

				// response.setHeader("Content-Type",
				// "text/html;charset=GB2312");
				response.setContentType("text/xml;charset=GBK");
				response.setHeader("Cache-Control", "no-cache");

				response.setCharacterEncoding("GBK");
				out = response.getWriter();
				String results = "";
				results = results + "<?xml version='1.0' encoding='GBK'?><response>";
				for (int i = 0; i < bankList.size(); i++) {
					MerchantBaseModel vo = (MerchantBaseModel) bankList.get(i);

					results = results + "<name>" + vo.getSignBankId().replaceAll(" ", "") + "-" + vo.getSignHostId().replaceAll(" ", "") + "-" + vo.getBankName().replaceAll(" ", "") + "</name>";
				}
				//
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

	/**
	 * ajax��ѯ�̻�����
	 */
	public org.apache.struts.action.ActionForward findMccName(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("MerchantAction.findMccName()��ʼ���ã�ajax��ѯ�̻�����");
		UserData ud = getSessionUserData(request);
		// �õ�Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// �õ�Form
		MerchantForm newForm = (MerchantForm) form;
		String mcc = newForm.getMcc();
		List<MerchantBaseModel> bankList = logic.findMccInfo(mcc, ud);
		if (bankList.size() > 0) {
			PrintWriter out;
			try {
				// response.setHeader("Content-Type",
				// "text/html;charset=GB2312");
				response.setContentType("text/xml;charset=GBK");
				response.setHeader("Cache-Control", "no-cache");

				response.setCharacterEncoding("GBK");
				out = response.getWriter();

				String results = "";
				results = results + "<?xml version='1.0' encoding='GBK'?><response>";
				for (int i = 0; i < bankList.size(); i++) {
					MerchantBaseModel vo = (MerchantBaseModel) bankList.get(i);

					results = results + "<param>" + vo.getMcc().replaceAll(" ", "") + "-" + vo.getMccName().replaceAll(" ", "") + "</param>";
				}
				//
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();
				log.info("MerchantAction.findMccName()�������ã�ajax��ѯ�̻�����");
			} catch (IOException e) {
				log.info("MerchantAction.findMccName()ajax��ѯ�̻����ͣ������쳣��");
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

	/**
	 * ajax��ѯ�̻�����
	 * 
	 * @throws Exception
	 */
	public org.apache.struts.action.ActionForward findArea(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		log.info("MerchantAction.findMccName()��ʼ���ã�ajax��ѯ�̻�����");
		UserData ud = getSessionUserData(request);
		// �õ�Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// �õ�Form
		String areaCode = request.getParameter("AreaCode");
		String AreaName = request.getParameter("AreaName");
		AreaCodeInfo area = new AreaCodeInfo();
		area.setAreaCode(areaCode);
		area.setAreaName(AreaName);
		List<AreaCodeInfo> areaList = logic.findArea(area, ud);
		if (areaList.size() > 0) {
			PrintWriter out;
			try {
				// response.setHeader("Content-Type",
				// "text/html;charset=GB2312");
				response.setContentType("text/xml;charset=GBK");
				response.setHeader("Cache-Control", "no-cache");

				response.setCharacterEncoding("GBK");
				out = response.getWriter();

				String results = "";
				results = results + "<?xml version='1.0' encoding='GBK'?><response>";
				for (int i = 0; i < areaList.size(); i++) {
					AreaCodeInfo vo = (AreaCodeInfo) areaList.get(i);
					results = results + "<param>" + vo.getAreaCode().replaceAll(" ", "") + "            -            " + "" + vo.getAreaName().replaceAll(" ", "") + "</param>";
				}
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();
				log.info("MerchantAction.findMccName()�������ã�ajax��ѯ�̻�����");
			} catch (IOException e) {
				log.info("MerchantAction.findMccName()ajax��ѯ�̻����ͣ������쳣��");
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward baseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("MerchantAction.baseInfo()��ʼ���ã���ʾ�̻�������Ϣ�Ľ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			MerchantBaseBo merchantBaseBo = logic.findBaseInfoById(ud.getUserId(), ud);
			if (merchantBaseBo != null) {
				MerchantBoForm merchantBoForm = (MerchantBoForm) form;
				BeanUtils.copyProperties(merchantBoForm, merchantBaseBo);
			}
			log.info("MerchantAction.baseInfo()�������ã���ʾ�̻�������Ϣ�Ľ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.baseInfo()��ʾ�̻�������Ϣ�Ľ���,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("baseInfo");
	}

	/**
	 * �ύ���
	 */
	public org.apache.struts.action.ActionForward submitBaseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("MerchantAction.baseInfo()��ʼ���ã���ʾ�̻�������Ϣ�Ľ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// �õ�Form
			MerchantBoForm newForm = (MerchantBoForm) form;

			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// �½�һ��Model
			MerchantBaseBo model = new MerchantBaseBo();

			BeanUtils.copyProperties(model, newForm);
			// ��������
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			model.setStatus("C");// C:����� N:��ͨ�� Y;ͨ��

			// ����
			logic.saveBaseInfo(model, ud);
			log.info("MerchantAction.baseInfo()�������ã���ʾ�̻�������Ϣ�Ľ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.baseInfo()��ʾ�̻�������Ϣ�Ľ���,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("query");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward saveBaseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.saveBaseInfo()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// �õ�Form
			MerchantBoForm newForm = (MerchantBoForm) form;

			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// �½�һ��Model
			MerchantBaseBo model = new MerchantBaseBo();

			BeanUtils.copyProperties(model, newForm);
			// ��������
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// ����
			logic.saveBaseInfo(model, ud);

			log.info("MerchantAction.saveBaseInfo()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.saveBaseInfo()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("query");

	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward showFindAreaPage(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("selArea");
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
			cell.setCellValue("�밴ģ���������ݣ��̻����͡�ǩԼ�кš�ǩԼ�������ű����ұ�����ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�̻����", "�̻�����", "�̻�����(��)", "�̻����(��)", "�̻�����(Ӣ)", "�̻����(Ӣ)", "ǩԼ�к�", "ǩԼ��������", "���д���" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "5411");
			POIUtils.createTextCell(book, row, (short) 2, "�����̻�����");
			POIUtils.createTextCell(book, row, (short) 3, "�����");
			POIUtils.createTextCell(book, row, (short) 4, "MerchantName");
			POIUtils.createTextCell(book, row, (short) 5, "Abbr");
			POIUtils.createTextCell(book, row, (short) 6, "000001");
			POIUtils.createTextCell(book, row, (short) 7, "00");
			POIUtils.createTextCell(book, row, (short) 8, "1000");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�̻��������ϵ���ģ��", "UTF-8");
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
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("��ʼ��������");
		// �õ�Logic
		MerchantLogicInterface merchantLogic = (MerchantLogicInterface) getBean("MerchantLogic");

		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		int number = 0;
		Set merchantSet = new HashSet();
		try {
			writer = response.getWriter();
			MerchantUploadForm uf = (MerchantUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// �½�һ��Model
					MerchantBaseModel newModel = new MerchantBaseModel();
					newModel.setUpdateOper(ud.getUserId());
					newModel.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					newModel.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					newModel.setMerchantId(merchantId);
					newModel.setMcc(POIUtils.getStringFromExcelCell(row.getCell(1)));
					String cname = POIUtils.getStringFromExcelCell(row.getCell(2));
					newModel.setMerchantCname(cname.length() > 20 ? cname.substring(0, 20) : cname);
					String abbrC = POIUtils.getStringFromExcelCell(row.getCell(3));
					newModel.setAbbrCname(abbrC.length() > 6 ? abbrC.substring(0, 6) : abbrC);
					String ename = POIUtils.getStringFromExcelCell(row.getCell(4));
					newModel.setMerchantEname(ename.length() > 12 ? ename.substring(0, 12) : ename);
					String abbrE = POIUtils.getStringFromExcelCell(row.getCell(5));
					newModel.setAbbrEname(abbrE.length() > 8 ? abbrE.substring(0, 8) : abbrE);
					newModel.setSignBankId(POIUtils.getStringFromExcelCell(row.getCell(6)));
					newModel.setSignHostId(POIUtils.getStringFromExcelCell(row.getCell(7)));
					newModel.setCityCname(POIUtils.getStringFromExcelCell(row.getCell(8)));
					// Ĭ��
					newModel.setRefundCheck("N");
					newModel.setMerchantStat("Y");
					newModel.setRefundLimit("0");
					newModel.setSettleMode("2");
					newModel.setZbank("000001");
					newModel.setSettleMerchId(merchantId);
					// �����µ���Ϣ
					try {
						merchantLogic.createItem(newModel, ud);
						number++;
					} catch (Exception e) {
						merchantSet.add("<br>��" + index + "��,�̻����Ϊ" + merchantId + "�쳣:<br>" + e.getMessage());
						log.error("�������Ӷ�·���쳣: ", e);
					}
				}
				String returnVal = "�����ɹ��� �ѳɹ����� " + number + " ����¼��<br><br><br>";
				if (merchantSet.size() > 0) {
					returnVal += "�̻���Ϣʧ�ܼ�¼��" + merchantSet + "<br>";
				}
				// ����
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("���������̻�����Excel�ļ�Action�쳣: {}", e);
			try {
				jsonObj.put("result", "����ʧ�ܣ�");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("���������̻�����Excel�ļ�Action�쳣: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("���������̻�����Excel�ļ�,�ر�IO��");
				} catch (Exception e) {
					log.error("���������̻�����Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return null;
	}
}
