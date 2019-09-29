package com.pay.merchant.struts.action;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.pay.bank.bean.TBankInfoBean;
import com.pay.bank.dao.TBankInfoDao;
import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.bean.CountryBean;
import com.pay.merchant.bean.FullMerchantBean;
import com.pay.merchant.bean.MCCBean;
import com.pay.merchant.bean.MerchantBean;
import com.pay.merchant.bean.MerchantExportBean;
import com.pay.merchant.bean.MerchantFenrunOrgBean;
import com.pay.merchant.bean.MerchantOrgBean;
import com.pay.merchant.bean.Merchant_xBean;
import com.pay.merchant.bean.MrchAccExportBean;
import com.pay.merchant.bean.Mrch_acc_xBean;
import com.pay.merchant.bean.Mrch_classBean;
import com.pay.merchant.bean.Mrchaccbean;
import com.pay.merchant.bean.Mrchmtdbean;
import com.pay.merchant.bean.WankeMerBookBean;
import com.pay.merchant.dao.MerchantFenrunOrgDao;
import com.pay.merchant.dao.MerchantOrgDao;
import com.pay.merchant.dao.Merchantdao;
import com.pay.merchant.struts.form.MerchantFenrunOrgForm;
import com.pay.merchant.struts.form.MerchantForm;
import com.pay.merchant.struts.form.MerchantForm2;
import com.pay.merchant.struts.form.MerchantOrgForm;
import com.pay.merchant.struts.form.Mrch_acc_xForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.terminal.bean.MrchListBean;
import com.pay.terminal.bean.TerminalBean;
import com.pay.terminal.dao.TerminalDao;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.FileUpload;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtil;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

import cn.yufu.system.common.shiro.UserUtils;

public class MerchantAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(MerchantAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	/**
	 * ��ȡ�̻���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getMerchantList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("��ȡ�̻���Ϣstart");
		try {
			// ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			session.removeAttribute("mrchnoQ");
			String mrchno = ParamUtils.getParameter(request, "mrchnoQ").trim();
			Merchantdao merchantDao = new Merchantdao();
			// ����̻���Ϣ�б����м�¼
			MerchantForm merchantform = (MerchantForm) form;
			merchantform.setMrchno(mrchno);
			beforAddorModMerchant(request, "read");

			Merchantdao mcdao = new Merchantdao();
			// ��ȡ��
			List<AreaBean> city_noList = null;
			if (merchantform.getProvince() != null) {
				city_noList = mcdao.getCityByFid(merchantform.getProvince());
			}
			// ��ȡ��
			List<AreaBean> areaList = null;
			if (merchantform.getCity_no() != null) {
				areaList = mcdao.getCityByFid(merchantform.getCity_no());
			}
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
			if (areaList != null && !areaList.isEmpty()) {
				request.setAttribute("areaList", areaList);
			}

			// ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}

			int count = merchantDao.getCount(merchantform, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List<FullMerchantBean> merchantList = null;
			if (count > 0)
				merchantList = merchantDao.getMerchantList(pageBean, merchantform);
			// �̻��������
			SysParameterDao sysParameterDao = new SysParameterDao();
			List<SysParameterBean> consump_categoryList = sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if (consump_categoryList != null && !consump_categoryList.isEmpty()) {
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			if (merchantList != null && !merchantList.isEmpty()) {
				List<MerchantOrgBean> attribute = (List<MerchantOrgBean>) request.getAttribute("merchantOrgList");
				for (FullMerchantBean full : merchantList) {
					if (StringUtils.isNotEmptyStr(full.getMerchant_org())) {
						for (MerchantOrgBean merchantOrgBean : attribute) {
							if (full.getMerchant_org().equals(merchantOrgBean.getOrgId())) {
								full.setMerchant_org(merchantOrgBean.getOrgName());
								break;
							}
						}
					}
				}
				request.setAttribute("merchantList", merchantList);
			}
		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		logger.info("��ȡ�̻���Ϣend");
		return mapping.findForward("merchantList");
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// ������ҳǰ���Ѳ�ѯ��������Ϊ��
		String mrchno = ParamUtils.getParameter(request, "mrchnoQ").trim();
		Merchantdao merchantDao = new Merchantdao();
		// ����̻���Ϣ�б����м�¼
		MerchantForm merchantform = (MerchantForm) form;
		merchantform.setMrchno(mrchno);
		// List<MerchantExportBean> merchantList =
		// merchantDao.getMerchantList(merchantform);
		List<MerchantExportBean> merchantList = merchantDao.getExportMerchantList(merchantform);
		// �̻��������
		SysParameterDao sysParameterDao = new SysParameterDao();

		Map<String, String> consumpCategoryMap = sysParameterDao.getSysParamMap("CARDBATCH_MER_TYPE");
		MerchantExportBean fb = null;
		if (consumpCategoryMap != null && consumpCategoryMap.size() > 0) {
			for (int i = 0; i < merchantList.size(); i++) {
				fb = (MerchantExportBean) merchantList.get(i);
				if (fb.getType_yf() != null && !"".equals(fb.getType_yf())) {
					fb.setType_yf(consumpCategoryMap.get(fb.getType_yf()) == null ? ""
							: consumpCategoryMap.get(fb.getType_yf()));
				}
			}
		}

		String docsPath = session.getServletContext().getRealPath("docs");
		String fileName = "merchant" + System.currentTimeMillis() + ".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]" + filePath);
		OutputStream out = null;
		ExportExcel<MerchantExportBean> expexl = new ExportExcel<MerchantExportBean>();
		try {
			out = new FileOutputStream(filePath);

			String[] headers = { "�̻���", "�̻�����", "�̻�ժҪ", "�̻�����", "�̻�״̬", "IOS��ӦMCC��", "��ϵ��", "�绰", "����ʱ��", "ͣ��ʱ��",
					"����ʱ��" };
			expexl.exportExcel("�̻���Ϣ", headers, merchantList, out, "yyyy-MM-dd");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		expexl.download(filePath, response);
		return null;
	}

	public ActionForward getMrchaccxList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			// String mrchno = (String)session.getAttribute("mrchnoQ");
			Merchantdao merchantDao = new Merchantdao();
			// ����̻���Ϣ�б����м�¼
			Mrch_acc_xForm mrchaccxform = (Mrch_acc_xForm) form;
			// mrchaccxform.setMrchno(mrchno);
			// ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}

			int count = merchantDao.getCount(mrchaccxform, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List mrchaccxList = null;
			if (count > 0)
				mrchaccxList = merchantDao.getMerchantList(pageBean, mrchaccxform);
			if (mrchaccxList != null && !mrchaccxList.isEmpty()) {
				request.setAttribute("mrchaccxList", mrchaccxList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("mrchaccxList");
	}

	public ActionForward exportExcelOfMrchAcc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Merchantdao merchantDao = new Merchantdao();
		// ����̻���Ϣ�б����м�¼
		Mrch_acc_xForm mrchaccxform = (Mrch_acc_xForm) form;
		List<MrchAccExportBean> list = merchantDao.getMerchantList(mrchaccxform);
		String docsPath = session.getServletContext().getRealPath("docs");
		String fileName = "mrchAcc" + System.currentTimeMillis() + ".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]" + filePath);
		OutputStream out = null;
		ExportExcel<MrchAccExportBean> expexl = new ExportExcel<MrchAccExportBean>();
		try {
			for (int i = 0; i < list.size(); i++) {
				String provinceName = StringUtil.notNull(list.get(i).getAcc_x_province())
						? merchantDao.getAreaXCityName(list.get(i).getAcc_x_province()) : "";
				String cityName = StringUtil.notNull(list.get(i).getAcc_x_city_no())
						? merchantDao.getAreaXCityName(list.get(i).getAcc_x_city_no()) : "";
				list.get(i).setAcc_x_province(provinceName);
				list.get(i).setAcc_x_city_no(cityName);
			}
			out = new FileOutputStream(filePath);

			String[] headers = { "������", "�����˺�", "�����̻�", "�̻��˺�����", "�̻��˺ż��", "���к�", "������������", "��������", "��������", "�ϴν�������",
					"�����˻�����", "�տ��˻�����", "����������ʡ��", "������������", "����" };
			expexl.exportExcel("�˻���Ϣ", headers, list, out, "yyyy-MM-dd");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		expexl.download(filePath, response);
		return null;
	}

	/**
	 * ��ʼ�������̻�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddMcCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		preAddGetSome(request);
		return mapping.findForward("addmerchant.jsp");
	}

	private void preAddGetSome(HttpServletRequest request) {

		beforAddorModMerchant(request, "insert");
		// ��������
		SysParameterDao sysParameterDao = new SysParameterDao();
		List<SysParameterBean> fsCycleList = sysParameterDao.getSysParameterList("FS_CYCLE");
		if (fsCycleList != null && !fsCycleList.isEmpty()) {
			request.setAttribute("fs_cycleList", fsCycleList);
		}
		// ��Ʊ����
		List<SysParameterBean> fsKpCycleList = sysParameterDao.getSysParameterList("FS_KP_CYCLE");
		if (fsKpCycleList != null && !fsKpCycleList.isEmpty()) {
			request.setAttribute("fs_kp_cycleList", fsKpCycleList);
		}
		// �̻��������
		List<SysParameterBean> consump_categoryList = sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
		if (consump_categoryList != null && !consump_categoryList.isEmpty()) {
			request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
		}
		// ע���ʱ������
		List<SysParameterBean> merchant_x_code_typeList = sysParameterDao.getSysParameterList("MERCHANT_X_CODE_TYPE");
		if (merchant_x_code_typeList != null && !merchant_x_code_typeList.isEmpty()) {
			request.setAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		}

	}

	public ActionForward preAddMrchaccxCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		beforAddorModMerchant(request, "insert");
		TBankInfoDao tBankInfoDao = new TBankInfoDao();
		List<TBankInfoBean> bankList = tBankInfoDao.getTBankInfoList();
		request.setAttribute("bankList", bankList);
		return mapping.findForward("addmrchaccx.jsp");
	}

	/**
	 * @TODO ׼���޸��̻���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward preModMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String id = ParamUtils.getParameter(request, "id");

		request.setAttribute("id", id);
		// System.out.println("id----"+request.getAttribute("id"));
		Merchantdao merchantDao = new Merchantdao();
		// ��������б���Ϣ
		FullMerchantBean merchantBean = merchantDao.getMerchantById(id);
		String mrchNo = merchantBean.getMrchno();
		request.setAttribute("mrchNo", mrchNo);

		// �ж��̻���������̻���
		List<WankeMerBookBean> wankeList = merchantDao.findWanKe(mrchNo);
		if (wankeList != null) {
			if (wankeList.size() > 0) {
				String yard_mer_type = wankeList.get(0).getYard_mer_type();
				merchantForm.setYard_mer_type(yard_mer_type);
			}
		}

		beforAddorModMerchant(request, "update");
		if (null != merchantBean.getProvince() && !merchantBean.getProvince().equals("")) {
			List<AreaBean> city_noList = merchantDao.getCityByFid(merchantBean.getProvince());
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		} else {
			List<AreaBean> city_noList = merchantDao.getBJCity();
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		}
		if (null != merchantBean.getCity_no() && !merchantBean.getCity_no().equals("")) {
			List<AreaBean> zoneList = merchantDao.getCityByFid(merchantBean.getCity_no());
			if (zoneList != null && !zoneList.isEmpty()) {
				request.setAttribute("zoneList", zoneList);
			}
		}

		try {
			if (merchantBean != null) {
				MerchantForm2 merchantForm2 = new MerchantForm2();
				BeanUtils.copyProperties(merchantForm2, merchantBean);
				BeanUtils.copyProperties(merchantForm, merchantForm2);
				String reg_amt = merchantBean.getMerchant_x_reg_amt();
				merchantForm.setMerchant_x_reg_amt(new BigDecimal(StringUtils.isNotEmptyStr(reg_amt) ? reg_amt : "0"));
				// ��ȡ����ͼƬ�ļ��ͽ��㼶�𡢸��̻�
				Mrch_classBean mrch_classBean = merchantDao.getMrchClassByMrchno(merchantBean.getMrchno());
				// ��ȡ���̻��ͽ��㼶��
				if (mrch_classBean != null) {
					List<MerchantBean> merchantList = merchantDao.getMerchantBeanList(merchantBean.getMrchno(),
							merchantBean.getInst_id(), "yes");
					if (merchantList != null && !merchantList.isEmpty()) {
						request.setAttribute("mrchList", merchantList);
					}
					merchantForm.setFmrchno(mrch_classBean.getFmrchno());
				}
				merchantForm.setClassify(mrch_classBean.getClassify());
				// ��ȡ����ͼƬ�ļ�
				if (merchantBean != null) {
					if (!"".equals(merchantBean.getBus_lic_pic())) {
						request.setAttribute("bus_id_pic", merchantBean.getBus_lic_pic());
						merchantForm.setBus_lic_pic(merchantBean.getBus_lic_pic());
					}
					if (!"".equals(merchantBean.getTax_id_pic())) {
						request.setAttribute("tax_id_pic", merchantBean.getTax_id_pic());
						merchantForm.setTax_id_pic(merchantBean.getTax_id_pic());
					}
					if (!"".equals(merchantBean.getOrg_id_pic())) {
						request.setAttribute("org_id_pic", merchantBean.getOrg_id_pic());
						merchantForm.setOrg_id_pic(merchantBean.getOrg_id_pic());
					}
				}
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		// ��������
		SysParameterDao sysParameterDao = new SysParameterDao();
		List<SysParameterBean> fsCycleList = sysParameterDao.getSysParameterList("FS_CYCLE");
		if (fsCycleList != null && !fsCycleList.isEmpty()) {
			request.setAttribute("fs_cycleList", fsCycleList);
		}
		// ��Ʊ����
		List<SysParameterBean> fsKpCycleList = sysParameterDao.getSysParameterList("FS_KP_CYCLE");
		if (fsKpCycleList != null && !fsKpCycleList.isEmpty()) {
			request.setAttribute("fs_kp_cycleList", fsKpCycleList);
		}
		// �̻��������
		List<SysParameterBean> consump_categoryList = sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
		if (consump_categoryList != null && !consump_categoryList.isEmpty()) {
			request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
		}
		// ע���ʱ������
		List<SysParameterBean> merchant_x_code_typeList = sysParameterDao.getSysParameterList("MERCHANT_X_CODE_TYPE");
		if (merchant_x_code_typeList != null && !merchant_x_code_typeList.isEmpty()) {
			request.setAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		}
		return mapping.findForward("modifyMerchant.jsp");
	}

	public ActionForward preModMrchaccx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Mrch_acc_xForm mrchaccxForm = (Mrch_acc_xForm) form;
		String id = ParamUtils.getParameter(request, "id");

		request.setAttribute("id", id);
		// System.out.println("id----"+request.getAttribute("id"));
		Merchantdao merchantDao = new Merchantdao();
		// ��������б���Ϣ

		Mrch_acc_xBean mrchaccxBean = merchantDao.getMrchaccxById(id);
		String mrchNo = mrchaccxBean.getMrchno();
		request.setAttribute("mrchNo", mrchNo);
		String bankCode = mrchaccxBean.getBank_name();
		try {
			if (mrchaccxBean != null) {
				BeanUtils.copyProperties(mrchaccxForm, mrchaccxBean);
				mrchaccxForm.setMrchnoSelQ(mrchNo);
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		beforAddorModMerchant(request, "update");
		if (null != mrchaccxBean.getAcc_x_province() && !mrchaccxBean.getAcc_x_province().equals("")) {
			List<AreaBean> city_noList = merchantDao.getAreaXCityByFid(mrchaccxBean.getAcc_x_province());
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		} else {
			List<AreaBean> city_noList = merchantDao.getAreaXBJCity();
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		}

		TBankInfoDao tBankInfoDao = new TBankInfoDao();
		List<TBankInfoBean> bankList = tBankInfoDao.getTBankInfoList();
		request.setAttribute("bankList", bankList);
		return mapping.findForward("modifyMrchaccx.jsp");
	}

	/**
	 * �����޸��̻���Ӧ���ն˵ķ��ʺ����ѳ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preModMrchTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String id = ParamUtils.getParameter(request, "id");
		request.setAttribute("id", id);
		Merchantdao merchantDao = new Merchantdao();
		FullMerchantBean merchantBean = merchantDao.getMerchantById(id);
		try {
			if (merchantBean != null) {
				BeanUtils.copyProperties(merchantForm, merchantBean);
				merchantForm.setAddress1("");
				merchantForm.setMrchstat("");
				merchantForm.setDisabled_date("");
				merchantForm.setEnable_date("");
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		// ���ѳ���
		SysParameterDao sysParameterDao = new SysParameterDao();
		List<SysParameterBean> consump_categoryList = sysParameterDao.getSysParameterList("CONSUMP_CATEGORY");
		if (consump_categoryList != null && !consump_categoryList.isEmpty()) {
			request.setAttribute("consump_categoryList", consump_categoryList);
		}
		// ͨ���̻�ID��ȡ��Ӧ�������ն˺�
		TerminalDao terminalDao = new TerminalDao();
		List<TerminalBean> terminalList = terminalDao.getTerminalListByMerchantId(id);
		if (terminalList != null && !terminalList.isEmpty()) {
			request.setAttribute("terminalList", terminalList);
		}
		return mapping.findForward("modifyMrchTerminal.jsp");

	}

	public ActionForward preQueryMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String id = ParamUtils.getParameter(request, "id");
		Merchantdao merchantDao = new Merchantdao();
		// ��������б���Ϣ
		FullMerchantBean merchantBean = merchantDao.getMerchantById(id);
		String mrchNo = merchantBean.getMrchno();
		request.setAttribute("mrchNo", mrchNo);

		// �ж��̻���������̻���
		List<WankeMerBookBean> wankeList = merchantDao.findWanKe(mrchNo);
		if (wankeList != null) {
			if (wankeList.size() > 0) {
				String yard_mer_type = wankeList.get(0).getYard_mer_type();
				merchantForm.setYard_mer_type(yard_mer_type);
			}
		}

		beforAddorModMerchant(request, "read");
		if (null != merchantBean.getProvince() && !merchantBean.getProvince().equals("")) {
			List<AreaBean> city_noList = merchantDao.getCityByFid(merchantBean.getProvince());
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		}
		if (null != merchantBean.getCity_no() && !merchantBean.getCity_no().equals("")) {
			List<AreaBean> zoneList = merchantDao.getCityByFid(merchantBean.getCity_no());
			if (zoneList != null && !zoneList.isEmpty()) {
				request.setAttribute("zoneList", zoneList);
			}
		}

		try {
			if (merchantBean != null) {
				MerchantForm2 merchantForm2 = new MerchantForm2();
				BeanUtils.copyProperties(merchantForm2, merchantBean);
				BeanUtils.copyProperties(merchantForm, merchantForm2);
				String reg_amt = merchantBean.getMerchant_x_reg_amt();
				merchantForm.setMerchant_x_reg_amt(new BigDecimal(StringUtils.isNotEmptyStr(reg_amt) ? reg_amt : "0"));
				// ��ȡ���̻��ͽ��㼶��
				if (merchantBean != null) {
					List<MerchantBean> merchantList = merchantDao.getMerchantBeanList(merchantBean.getMrchno(),
							merchantBean.getInst_id(), "no");
					if (merchantList != null && !merchantList.isEmpty()) {
						request.setAttribute("mrchList", merchantList);
					}
					merchantForm.setClassify(merchantBean.getClassify());
				}
				// ��ȡ����ͼƬ�ļ�

				if (merchantBean != null) {
					if (!"".equals(merchantBean.getBus_lic_pic())) {
						request.setAttribute("bus_id_pic", merchantBean.getBus_lic_pic());
					}
					if (!"".equals(merchantBean.getTax_id_pic())) {
						request.setAttribute("tax_id_pic", merchantBean.getTax_id_pic());
					}
					if (!"".equals(merchantBean.getOrg_id_pic())) {
						request.setAttribute("org_id_pic", merchantBean.getOrg_id_pic());
					}
				}
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		// ��������
		SysParameterDao sysParameterDao = new SysParameterDao();
		List<SysParameterBean> fsCycleList = sysParameterDao.getSysParameterList("FS_CYCLE");
		if (fsCycleList != null && !fsCycleList.isEmpty()) {
			request.setAttribute("fs_cycleList", fsCycleList);
		}
		// ��Ʊ����
		List<SysParameterBean> fsKpCycleList = sysParameterDao.getSysParameterList("FS_KP_CYCLE");
		if (fsKpCycleList != null && !fsKpCycleList.isEmpty()) {
			request.setAttribute("fs_kp_cycleList", fsKpCycleList);
		}
		// �̻��������
		List<SysParameterBean> consump_categoryList = sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
		if (consump_categoryList != null && !consump_categoryList.isEmpty()) {
			request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
		}
		// ע���ʱ������
		List<SysParameterBean> merchant_x_code_typeList = sysParameterDao.getSysParameterList("MERCHANT_X_CODE_TYPE");
		if (merchant_x_code_typeList != null && !merchant_x_code_typeList.isEmpty()) {
			request.setAttribute("merchant_x_code_typeList", merchant_x_code_typeList);
		}
		return mapping.findForward("queryMerchantInfo.jsp");
	}

	public ActionForward preQueryMrchaccx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Mrch_acc_xForm mrchaccxForm = (Mrch_acc_xForm) form;
		String id = ParamUtils.getParameter(request, "id");

		// request.setAttribute("id", id);
		// System.out.println("id----"+request.getAttribute("id"));
		Merchantdao merchantDao = new Merchantdao();
		Mrch_acc_xBean mrchaccxBean = merchantDao.getMrchaccxById(id);
		request.setAttribute("mrchNo", mrchaccxBean.getMrchno());
		try {
			if (mrchaccxBean != null) {
				BeanUtils.copyProperties(mrchaccxForm, mrchaccxBean);
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		// ��������б���Ϣ
		beforAddorModMerchant(request, "read");
		if (null != mrchaccxBean.getAcc_x_province() && !mrchaccxBean.getAcc_x_province().equals("")) {
			List<AreaBean> city_noList = merchantDao.getAreaXCityByFid(mrchaccxBean.getAcc_x_province());
//			List<AreaBean> city_noList = merchantDao.getCityByFid(mrchaccxBean.getAcc_x_province());
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
		}
		TBankInfoDao tBankInfoDao = new TBankInfoDao();
		List<TBankInfoBean> bankList = tBankInfoDao.getTBankInfoList();
		request.setAttribute("bankList", bankList);
		return mapping.findForward("queryMrchaccxInfo.jsp");
	}

	/**
	 * �̻��˻�����ʱ����������������б���Ϣ�����request��
	 * 
	 * @param request
	 */
	private void beforAddorModMrchaccx(HttpServletRequest request, String readOrinsertOrUpdate) {
		beforAddorModMerchant(request, "insert");
		// //��ȡ����һ�߳���
		// List<AreaBean> provinList = dao.getCityByFid("0");
		// //��ȡ���߳���
		// List<AreaBean> city_noList = dao.getCityByFid("1");
		// if(city_noList==null){
		// city_noList = dao.getBJCity();
		// }
		// if (provinList != null && !provinList.isEmpty()) {
		// request.setAttribute("provinList", provinList);
		// }
		// if (city_noList != null && !city_noList.isEmpty()) {
		// request.setAttribute("city_noList", city_noList);
		// }
		// List<TBankInfoBean> bankList=tBankInfoDao.getTBankInfoList();
		// request.setAttribute("bankList", bankList);
	}

	/**
	 * �̻��������޸�ʱ����������������б���Ϣ�����request��
	 * 
	 * @param request
	 */
	private void beforAddorModMerchant(HttpServletRequest request, String readOrinsertOrUpdate) {
		Merchantdao dao = new Merchantdao();
		// ��ȡ����һ�߳���
//		List<AreaBean> provinList = dao.getCityByFid("0");
		List<AreaBean> provinList = dao.getAreaXCityByFid("0");
		// ��ȡ���߳���
//		List<AreaBean> city_noList = dao.getCityByFid("1");
		List<AreaBean> city_noList = dao.getAreaXCityByFid("1");
		if (city_noList == null) {
//			city_noList = dao.getBJCity();
			city_noList = dao.getAreaXBJCity();
		}
		List<MCCBean> MCCList = dao.getMCCList();

		List<CountryBean> CountryList = dao.getCountryList();

		TerminalDao termdao = new TerminalDao();
		List<MrchListBean> mrchList = null;
		if (request.getAttribute("mrchNo") != null && !"".equals(request.getAttribute("mrchNo"))) {
			if (readOrinsertOrUpdate != null && "read".equals(readOrinsertOrUpdate)) {
				mrchList = termdao.getMrchListBeanList(request.getAttribute("mrchNo").toString(), null, null, "no");
			} else {
				mrchList = termdao.getMrchListBeanList(request.getAttribute("mrchNo").toString(), null, null, "yes");
			}
		}

		if (provinList != null && !provinList.isEmpty()) {
			request.setAttribute("provinList", provinList);
		}
		if (city_noList != null && !city_noList.isEmpty()) {
			request.setAttribute("city_noList", city_noList);
		}
		if (MCCList != null && !MCCList.isEmpty()) {
			request.setAttribute("MCCList", MCCList);
		}
		if (CountryList != null && !CountryList.isEmpty()) {
			request.setAttribute("CountryList", CountryList);
		}

		if (mrchList != null && !mrchList.isEmpty()) {
			request.setAttribute("mrchList", mrchList);
			// request.setAttribute("jsonMrchList",
			// JSONObject.toJSONString(mrchList));
		}

		// ��ȡ�̻�����list
		MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
		MerchantOrgForm merchantOrgForm = new MerchantOrgForm();
		merchantOrgForm.setOrgStat("0");
		List<MerchantOrgBean> list = merchantOrgDao.getMerchantOrgList(null, merchantOrgForm);
		request.setAttribute("merchantOrgList", list);
		// ��ȡ�������list
		MerchantFenrunOrgDao merchantFenrunOrgDao = new MerchantFenrunOrgDao();
		MerchantFenrunOrgForm merchantFenrunOrgForm = new MerchantFenrunOrgForm();
		merchantFenrunOrgForm.setOrgStat("0");
		List<MerchantFenrunOrgBean> merchantFenrunOrgList = merchantFenrunOrgDao.getMerchantOrgList(null,
				merchantFenrunOrgForm);
		request.setAttribute("merchantFenrunOrgList", merchantFenrunOrgList);
	}

	/**
	 * ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryAgentList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// ������ҳǰ���Ѳ�ѯ��������Ϊ��
		HttpSession session = request.getSession();
		session.removeAttribute("mrchnoQ");
		try {
			String mrchno = ParamUtils.getParameter(request, "mrchnoQ").trim();
			Merchantdao merchantDao = new Merchantdao();
			// ����̻���Ϣ�б����м�¼
			MerchantForm merchantform = (MerchantForm) form;
			// ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}

			merchantform.setMrchno(mrchno);
			beforAddorModMerchant(request, "read");
			int count = merchantDao.getCount(merchantform, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List merchantList = null;
			if (count > 0)
				merchantList = merchantDao.getMerchantList(pageBean, merchantform);
			if (merchantList != null && !merchantList.isEmpty()) {
				request.setAttribute("merchantList", merchantList);
			}

		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("merchantList");
	}

	public ActionForward queryMrchaccxList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("mrchnoQ");
		String merchno = ParamUtils.getParameter(request, "mrchnoQ").trim();
		session.setAttribute("mrchnoQ", merchno);
		return mapping.findForward("getMrchaccxList");
	}

	/**
	 * ͨ����ȡ�ļ�����ȡ��width��height�ķ�ʽ�����ж��жϵ�ǰ�ļ��Ƿ�ͼƬ������һ�ַǳ��򵥵ķ�ʽ��
	 * 
	 * @param imageFile
	 * @return
	 */
	public static boolean isImage(FormFile imageFile) {
		if (imageFile.getFileSize() == 0) {
			return true;
		} else {
			Image img = null;
			try {
				img = ImageIO.read(imageFile.getInputStream());
				if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
					return false;
				}
				return true;
			} catch (Exception e) {
				return false;
			} finally {
				img = null;
			}
		}
	}

	/**
	 * ����̻���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addmerchantBeanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "closewindow";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			Merchantdao merchantdao = new Merchantdao();
			MerchantForm merchantForm = (MerchantForm) form;
			// merchantForm.getBus_lic_pic_f(); //Ӫҵִ��ͼƬ
			// merchantForm.getTax_id_pic_f(); //˰��Ǽ�֤ͼƬ
			// merchantForm.getOrg_id_pic_f(); //��֯����֤ͼƬ
			if (!isImage(merchantForm.getBus_lic_pic_f())) {
				info = "Ӫҵִ��ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("result.jsp");
			} else if (!isImage(merchantForm.getTax_id_pic_f())) {
				info = "˰��Ǽ�֤ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("result.jsp");
			} else if (!isImage(merchantForm.getOrg_id_pic_f())) {
				info = "��֯����֤ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("result.jsp");
			} else {
				// �̻�������Ϣ��
				MerchantBean merchantBean = new MerchantBean();
				filledMerchantBean(merchantForm, merchantBean);

				// �̻����׻�����Ϣ
				Mrchmtdbean mrchmtdbean = new Mrchmtdbean();
				filledMrchmtDbean(merchantForm, mrchmtdbean);

				// �̻��˻���Ϣ��
				Mrchaccbean mrchaccbean = new Mrchaccbean();
				filledMrchaccBean(merchantForm, mrchaccbean);

				// �̻�������Ϣ��
				Merchant_xBean merchant_xBean = new Merchant_xBean();
				filledMerchant_xBean(merchantForm, merchant_xBean);

				// �̻��˻�������Ϣ��
				// Mrch_acc_xBean mrch_acc_xBean = new Mrch_acc_xBean();
				// filledMrch_acc_xBean(merchantForm, mrch_acc_xBean);
				//
				// //�̻������
				Mrch_classBean mrch_classBean = new Mrch_classBean();
				filledMrch_classBean(merchantForm, mrch_classBean, "insert");

				// �������̻���
				WankeMerBookBean wanke_MerBookBean = new WankeMerBookBean();
				if (merchantForm.getYard_mer_type() != null) {
					if ("0".equals(merchantForm.getYard_mer_type())) {
						filledWankeMerBookBean(merchantForm, wanke_MerBookBean);
					}
				}

				// ����merchantdao������̻���Ϣ�ķ���
				int result = merchantdao.addMerchantInfo(merchantBean, mrchmtdbean, mrchaccbean, merchant_xBean,
						mrch_classBean, wanke_MerBookBean);
				if (result >= 0) {
					info = "�̻���Ϣ��ӳɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣���̻���Ϣ���ʧ�ܣ�";
					flushdo = "javascript:history.go(-1)";
					request.setAttribute("result", "1");
				}
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("result.jsp");
			}

		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("result.jsp");
		}

		/*
		 * if
		 * (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName()))
		 * { Merchantdao merchantdao = new Merchantdao(); MerchantForm
		 * merchantForm = (MerchantForm) form;
		 * 
		 * //�̻�������Ϣ�� MerchantBean merchantBean = new MerchantBean();
		 * filledMerchantBean(merchantForm,merchantBean);
		 * 
		 * //�̻����׻�����Ϣ Mrchmtdbean mrchmtdbean =new Mrchmtdbean();
		 * filledMrchmtDbean(merchantForm, mrchmtdbean);
		 * 
		 * //�̻��˻���Ϣ�� Mrchaccbean mrchaccbean =new Mrchaccbean();
		 * filledMrchaccBean(merchantForm, mrchaccbean);
		 * 
		 * //�̻�������Ϣ�� Merchant_xBean merchant_xBean = new Merchant_xBean();
		 * filledMerchant_xBean(merchantForm, merchant_xBean);
		 * 
		 * //�̻��˻�������Ϣ�� // Mrch_acc_xBean mrch_acc_xBean = new Mrch_acc_xBean();
		 * // filledMrch_acc_xBean(merchantForm, mrch_acc_xBean); // // //�̻������
		 * Mrch_classBean mrch_classBean = new Mrch_classBean();
		 * filledMrch_classBean(merchantForm, mrch_classBean,"insert");
		 * 
		 * //�������̻��� WankeMerBookBean wanke_MerBookBean = new
		 * WankeMerBookBean(); if(merchantForm.getYard_mer_type()!=null){
		 * if("0".equals(merchantForm.getYard_mer_type())){
		 * filledWankeMerBookBean(merchantForm, wanke_MerBookBean); } }
		 * 
		 * //����merchantdao������̻���Ϣ�ķ��� int result =
		 * merchantdao.addMerchantInfo(merchantBean, mrchmtdbean, mrchaccbean,
		 * merchant_xBean, mrch_classBean,wanke_MerBookBean); if (result >= 0) {
		 * info = "�̻���Ϣ��ӳɹ���"; request.setAttribute("result", "0"); } else {
		 * info = "���ݿ��쳣���̻���Ϣ���ʧ�ܣ�"; flushdo = "javascript:history.go(-1)";
		 * request.setAttribute("result", "1"); } request.setAttribute("info",
		 * StringUtils.outerToInner(info)); request.setAttribute("flushdo",
		 * flushdo); } else { request.setAttribute("info", "������ʱ�������µ�¼��");
		 * request.setAttribute("flushdo", flushdo);
		 * request.setAttribute("result", "1"); }
		 */
		// return mapping.findForward("result.jsp");
	}

	public ActionForward addmrchaccxBeanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "closewindow";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			Merchantdao merchantdao = new Merchantdao();
			Mrch_acc_xForm mrchaccxForm = (Mrch_acc_xForm) form;

			// �̻��˻�������Ϣ��
			Mrch_acc_xBean mrch_acc_xBean = new Mrch_acc_xBean();
			filledMrch_acc_xBean(mrchaccxForm, mrch_acc_xBean, "insert");

			// ����merchantdao������̻���Ϣ�ķ���
			int result = merchantdao.addMrchaccxInfo(mrch_acc_xBean);
			if (result >= 0) {
				info = "�̻��˻���Ϣ��ӳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���̻��˻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		beforAddorModMrchaccx(request, null);
		return mapping.findForward("addmrchaccx.jsp");
	}

	public ActionForward modMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/merchant.do?method=getMerchantList";
		// String flushdo = "closewindow";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			Merchantdao merchantdao = new Merchantdao();
			MerchantForm merchantForm = (MerchantForm) form;
			// merchantForm.getBus_lic_pic_f(); //Ӫҵִ��ͼƬ
			// merchantForm.getTax_id_pic_f(); //˰��Ǽ�֤ͼƬ
			// merchantForm.getOrg_id_pic_f(); //��֯����֤ͼƬ
			if (!isImage(merchantForm.getBus_lic_pic_f())) {
				info = "Ӫҵִ��ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
				request.setAttribute("info", info);
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("resultOfUpdate.jsp");

			} else if (!isImage(merchantForm.getTax_id_pic_f())) {
				info = "˰��Ǽ�֤ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("result", "1");
				request.setAttribute("info", info);
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("resultOfUpdate.jsp");
			} else if (!isImage(merchantForm.getOrg_id_pic_f())) {
				info = "��֯����֤ͼƬ�����̻���Ϣ���ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
				request.setAttribute("info", info);
				request.setAttribute("flushdo", flushdo);
				request.setAttribute("result", "1");
				return mapping.findForward("resultOfUpdate.jsp");
			} else {
				// Merchantdao merchantdao = new Merchantdao();
				// MerchantForm merchantForm = (MerchantForm) form;

				// �̻�������Ϣ��
				MerchantBean merchantBean = new MerchantBean();
				filledMerchantBean(merchantForm, merchantBean);

				// �̻����׻�����Ϣ
				Mrchmtdbean mrchmtdbean = new Mrchmtdbean();
				filledMrchmtDbean(merchantForm, mrchmtdbean);

				// �̻��˻���Ϣ��
				Mrchaccbean mrchaccbean = new Mrchaccbean();
				filledMrchaccBean(merchantForm, mrchaccbean);

				// �̻�������Ϣ��
				Merchant_xBean merchant_xBean = new Merchant_xBean();
				filledMerchant_xBean(merchantForm, merchant_xBean);

				// //�̻��˻�������Ϣ��
				// Mrch_acc_xBean mrch_acc_xBean = new Mrch_acc_xBean();
				// filledMrch_acc_xBean(merchantForm, mrch_acc_xBean);
				//
				// //�̻������
				Mrch_classBean mrch_classBean = new Mrch_classBean();
				filledMrch_classBean(merchantForm, mrch_classBean, "update");

				// ����merchantdao������̻���Ϣ�ķ���

				merchantBean.setId(ParamUtils.getParameter(request, "id"));

				// �޸�����̻���
				WankeMerBookBean wanke_MerBookBean = new WankeMerBookBean();
				filledWankeMerBookBean(merchantForm, wanke_MerBookBean);

				// System.out.println("ID==="+ParamUtils.getParameter(request,
				// "id"));
				int result = merchantdao.updateMerchantInfo(merchantBean, mrchmtdbean, mrchaccbean, merchant_xBean,
						mrch_classBean, wanke_MerBookBean);
				if (result >= 0) {
					info = "�̻���Ϣ�޸ĳɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣���̻���Ϣ�޸�ʧ�ܣ�";
					request.setAttribute("result", "1");
				}
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
				return mapping.findForward("resultOfUpdate.jsp");
			}

		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
			return mapping.findForward("resultOfUpdate.jsp");
		}

		/*
		 * 
		 * if
		 * (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName()))
		 * { Merchantdao merchantdao = new Merchantdao(); MerchantForm
		 * merchantForm = (MerchantForm) form;
		 * 
		 * //�̻�������Ϣ�� MerchantBean merchantBean = new MerchantBean();
		 * filledMerchantBean(merchantForm,merchantBean);
		 * 
		 * //�̻����׻�����Ϣ Mrchmtdbean mrchmtdbean =new Mrchmtdbean();
		 * filledMrchmtDbean(merchantForm, mrchmtdbean);
		 * 
		 * //�̻��˻���Ϣ�� Mrchaccbean mrchaccbean =new Mrchaccbean();
		 * filledMrchaccBean(merchantForm, mrchaccbean);
		 * 
		 * //�̻�������Ϣ�� Merchant_xBean merchant_xBean = new Merchant_xBean();
		 * filledMerchant_xBean(merchantForm, merchant_xBean);
		 * 
		 * // //�̻��˻�������Ϣ�� // Mrch_acc_xBean mrch_acc_xBean = new
		 * Mrch_acc_xBean(); // filledMrch_acc_xBean(merchantForm,
		 * mrch_acc_xBean); // // //�̻������ Mrch_classBean mrch_classBean = new
		 * Mrch_classBean(); filledMrch_classBean(merchantForm,
		 * mrch_classBean,"update");
		 * 
		 * //����merchantdao������̻���Ϣ�ķ���
		 * 
		 * merchantBean.setId(ParamUtils.getParameter(request, "id"));
		 * 
		 * //�޸�����̻��� WankeMerBookBean wanke_MerBookBean = new
		 * WankeMerBookBean(); filledWankeMerBookBean(merchantForm,
		 * wanke_MerBookBean);
		 * 
		 * //System.out.println("ID==="+ParamUtils.getParameter(request, "id"));
		 * int result = merchantdao.updateMerchantInfo(merchantBean,
		 * mrchmtdbean, mrchaccbean, merchant_xBean,
		 * mrch_classBean,wanke_MerBookBean); if (result >= 0) { info =
		 * "�̻���Ϣ�޸ĳɹ���"; request.setAttribute("result", "0"); } else { info =
		 * "���ݿ��쳣���̻���Ϣ�޸�ʧ�ܣ�"; request.setAttribute("result", "1"); }
		 * request.setAttribute("info", StringUtils.outerToInner(info));
		 * request.setAttribute("flushdo", flushdo); } else {
		 * request.setAttribute("info", "������ʱ�������µ�¼��");
		 * request.setAttribute("flushdo", flushdo);
		 * request.setAttribute("result", "1"); }
		 */

		// return mapping.findForward("resultOfUpdate.jsp");
	}

	public ActionForward modMrchaccx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/mrchaccx.do?method=getMrchaccxList";
		// String flushdo = "closewindow";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			Merchantdao merchantdao = new Merchantdao();
			Mrch_acc_xForm mrchaccxForm = (Mrch_acc_xForm) form;

			// �̻��˻�������Ϣ��
			Mrch_acc_xBean mrch_acc_xBean = new Mrch_acc_xBean();
			filledMrch_acc_xBean(mrchaccxForm, mrch_acc_xBean, "update");

			mrch_acc_xBean.setId(ParamUtils.getParameter(request, "id"));
			// System.out.println("ID==="+ParamUtils.getParameter(request,
			// "id"));
			int result = merchantdao.updateMrchaccxInfo(mrch_acc_xBean);
			if (result >= 0) {
				info = "�̻��˻���Ϣ�޸ĳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���̻��˻���Ϣ�޸�ʧ�ܣ�";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("resultOfUpdate.jsp");
	}

	/**
	 * �����޸��̻���Ӧ�ն˵ķ��ʺ����ѳ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward modMrchTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/merchant.do?method=getMerchantList";
		// String flushdo = "closewindow";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			TerminalDao terminalDao = new TerminalDao();
			MerchantForm merchantForm = (MerchantForm) form;

			int result = terminalDao.updTerminalInfo(merchantForm.getMrchno().trim(), merchantForm.getId().trim(),
					merchantForm.getAddress1().trim(), merchantForm.getAddress2().trim(),
					merchantForm.getMrchstat().trim(), merchantForm.getDisabled_date().trim(),
					merchantForm.getEnable_date().trim(), merchantForm.getAddress().trim());
			if (result >= 0) {
				info = "�̻���Ӧ�ն��޸ĳɹ���";
				request.setAttribute("result", "0");
			} else if (result == -2) {
				info = "��������쳣���̻���Ӧ�ն��޸�ʧ�ܣ�";
				request.setAttribute("result", "1");
			} else {
				info = "���ݿ��쳣���̻���Ӧ�ն��޸�ʧ�ܣ�";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("resultOfUpdate.jsp");
	}

	/**
	 * �̻������˻���Ϣ��ֵ
	 * 
	 * @param merchantForm
	 * @param mrch_acc_xBean
	 */
	private void filledMrch_acc_xBean(Mrch_acc_xForm mrchaccxForm, Mrch_acc_xBean mrch_acc_xBean,
			String inserOrUpdate) {
		String merchNo = mrchaccxForm.getMrchno().trim();
		if (merchNo != null && merchNo.trim().length() > 0) {
			Merchantdao merchantdao = new Merchantdao();
			Map<String, String> defaultParams = merchantdao.getDefaultParams();
			MerchantBean merchantB = merchantdao
					.getMerchantBeanListByMrchNoOrName(merchNo, null, null, null, "no", null).get(0);
			mrch_acc_xBean.setInst_id(defaultParams.get("inst_id"));
			mrch_acc_xBean.setMerchant_id(merchantB.getId());
			mrch_acc_xBean.setMrchno(merchNo);
		} else {
			mrch_acc_xBean.setMrchno(null);
		}

		mrch_acc_xBean.setAccno(mrchaccxForm.getAccno());
		mrch_acc_xBean.setAcc_name(mrchaccxForm.getAcc_name());
		mrch_acc_xBean.setBank_no(mrchaccxForm.getBank_no());
		mrch_acc_xBean.setBank_name(mrchaccxForm.getBank_name());
		mrch_acc_xBean.setAcc_add_date(mrchaccxForm.getAcc_add_date());
		mrch_acc_xBean.setAcc_nick_name(mrchaccxForm.getAcc_nick_name());
		mrch_acc_xBean.setShort_nick_name(mrchaccxForm.getShort_nick_name());
		mrch_acc_xBean.setIndividual(mrchaccxForm.getIndividual());
		mrch_acc_xBean.setLast_settle_date(mrchaccxForm.getLast_settle_date());
		mrch_acc_xBean.setAcc_introd(mrchaccxForm.getAcc_introd());
		mrch_acc_xBean.setIs_bj_acct(mrchaccxForm.getIs_bj_acct());
		mrch_acc_xBean.setBis(mrchaccxForm.getBis());
		mrch_acc_xBean.setMerchant_x_acc_type1(mrchaccxForm.getMerchant_x_acc_type1());
		mrch_acc_xBean.setPay_account_type(mrchaccxForm.getPay_account_type());
		mrch_acc_xBean.setAcc_x_province(mrchaccxForm.getAcc_x_province());
		mrch_acc_xBean.setAcc_x_city_no(mrchaccxForm.getAcc_x_city_no());
		mrch_acc_xBean.setAcc_x_text(mrchaccxForm.getAcc_x_text());
	}

	/**
	 * �̻�������Ϣ��ֵ
	 * 
	 * @param merchantForm
	 * @param merchant_xBean
	 */
	private void filledMerchant_xBean(MerchantForm merchantForm, Merchant_xBean merchant_xBean) {
		Merchantdao merchantdao = new Merchantdao();
		Map<String, String> defaultParams = merchantdao.getDefaultParams();
		merchant_xBean.setInst_id(defaultParams.get("inst_id"));
		merchant_xBean.setMrchno(merchantForm.getMrchno());
		merchant_xBean.setMrcht_name(merchantForm.getMrcht_name());
		merchant_xBean.setAddress(merchantForm.getAddress());
		merchant_xBean.setAdd_date(merchantForm.getAdd_date());
		merchant_xBean.setCity_no(merchantForm.getCity_no());
		merchant_xBean.setProvince(merchantForm.getProvince());
		merchant_xBean.setZone(merchantForm.getZone());
		merchant_xBean.setState(merchantForm.getMrchstat());
		merchant_xBean.setType_yf(merchantForm.getType_yf());
		merchant_xBean.setAgent(merchantForm.getAgent());
		merchant_xBean.setId_type(merchantForm.getId_type());
		merchant_xBean.setId_no(merchantForm.getId_no());
		merchant_xBean.setId_validity(merchantForm.getId_validity());
		merchant_xBean.setBus_lic_no(merchantForm.getBus_lic_no());
		merchant_xBean.setBus_lic_validity(merchantForm.getBus_lic_validity());
		merchant_xBean.setTax_id(merchantForm.getTax_id());
		merchant_xBean.setTax_id_validity(merchantForm.getTax_id_validity());
		merchant_xBean.setOrg_id(merchantForm.getOrg_id());
		merchant_xBean.setOrg_validity(merchantForm.getOrg_validity());
		merchant_xBean.setTelno1(merchantForm.getMx_telno1());
		merchant_xBean.setPostcode(merchantForm.getMx_postcode());
		merchant_xBean.setContact3(merchantForm.getMx_contact3());
		merchant_xBean.setAccno(merchantForm.getMx_accno());
		merchant_xBean.setMrchno(merchantForm.getMrchno());
		merchant_xBean.setMan_name(merchantForm.getMan_name());
		merchant_xBean.setId_type1(merchantForm.getId_type1());
		merchant_xBean.setId_no1(merchantForm.getId_no1());
		merchant_xBean.setId_deadline1(merchantForm.getId_deadline1());
		merchant_xBean.setAcc_x_name(merchantForm.getAcc_x_name());
		merchant_xBean.setSettlement_person(merchantForm.getSettlement_person());
		merchant_xBean.setMerchant_email(merchantForm.getMerchant_email());
		merchant_xBean.setIs_send_billdetail(merchantForm.getIs_send_billdetail());
		merchant_xBean.setMerchant_x_operate(merchantForm.getMerchant_x_operate());
		merchant_xBean.setMerchant_x_type(merchantForm.getMerchant_x_type());
		merchant_xBean.setMerchant_x_reg_amt(merchantForm.getMerchant_x_reg_amt());
		merchant_xBean.setMerchant_x_code(merchantForm.getMerchant_x_code());
		merchant_xBean.setCombine_flag(merchantForm.getCombine_flag());
		
		merchant_xBean.setMrch_snippet(merchantForm.getMrch_snippet());
		merchant_xBean.setMerge_money_flag(merchantForm.getMerge_money_flag());
		merchant_xBean.setMerchant_org(merchantForm.getMerchant_org());
		merchant_xBean.setMerchant_fenrunorg(merchantForm.getMerchant_fenrunorg());

		String legal_rep = merchantForm.getLegal_rep();

		if (legal_rep != null && legal_rep.trim().length() > 0) {
			merchant_xBean.setLegal_rep(legal_rep);
		} else {
			merchant_xBean.setLegal_rep("");
		}
		String lr_id_type = merchantForm.getLr_id_type();
		if (lr_id_type != null && lr_id_type.trim().length() > 0) {
			merchant_xBean.setLr_id_type(lr_id_type);
		} else {
			merchant_xBean.setLr_id_type("1");
		}
		String lr_id_no = merchantForm.getLr_id_no();
		if (lr_id_no != null && lr_id_no.trim().length() > 0) {
			merchant_xBean.setLr_id_no(lr_id_no);
		} else {
			merchant_xBean.setLr_id_no("");
		}
		String lr_id_validity = merchantForm.getLr_id_validity();
		if (lr_id_validity != null && lr_id_validity.trim().length() > 0) {
			merchant_xBean.setLr_id_validity(lr_id_validity);
		} else {
			merchant_xBean.setLr_id_validity("2000-07-01");
		}

		FormFile bus_formFile = merchantForm.getBus_lic_pic_f();
		FormFile tax_formFile = merchantForm.getTax_id_pic_f();
		FormFile org_formFile = merchantForm.getOrg_id_pic_f();

		String path = SystemConfig.getValue("upload");
		if (bus_formFile != null && bus_formFile.getFileSize() != 0) {
			// InputStream bus_input = bus_formFile.getInputStream();
			// merchant_xBean.setBus_input(bus_input);
			String busfile = FileUpload.uploadFile(path, bus_formFile);
			if (busfile != null)
				merchant_xBean.setBus_lic_pic(busfile);

		} else {
			if (merchantForm.getBus_lic_pic() != null) {
				merchant_xBean.setBus_lic_pic(merchantForm.getBus_lic_pic());
			} else {
				merchant_xBean.setBus_lic_pic("");
			}
		}
		// merchant_xBean.setTax_id_pic("");
		if (tax_formFile != null && tax_formFile.getFileSize() != 0) {
			// InputStream tax_input = tax_formFile.getInputStream();
			// merchant_xBean.setTax_input(tax_input);
			String taxfile = FileUpload.uploadFile(path, tax_formFile);
			if (taxfile != null)
				merchant_xBean.setTax_id_pic(taxfile);
		} else {
			if (merchantForm.getTax_id_pic() != null) {
				merchant_xBean.setTax_id_pic(merchantForm.getTax_id_pic());
			} else {
				merchant_xBean.setTax_id_pic("");
			}
		}
		// merchant_xBean.setOrg_id_pic("");
		if (org_formFile != null && org_formFile.getFileSize() != 0) {
			// InputStream org_input = org_formFile.getInputStream();
			// merchant_xBean.setOrg_input(org_input);
			String orgfile = FileUpload.uploadFile(path, org_formFile);
			if (orgfile != null)
				merchant_xBean.setOrg_id_pic(orgfile);
		} else {
			if (merchantForm.getOrg_id_pic() != null) {
				merchant_xBean.setOrg_id_pic(merchantForm.getOrg_id_pic());
			} else {
				merchant_xBean.setOrg_id_pic("");
			}
		}
		// ���������Ϣ
		merchant_xBean.setFs_cycle(merchantForm.getFs_cycle());
		merchant_xBean.setFs_cycle_param(merchantForm.getFs_cycle_param());
		merchant_xBean.setLast_settle_date(merchantForm.getLast_settle_date());
		merchant_xBean.setIs_consump_category(merchantForm.getIs_consump_category());
		merchant_xBean.setAmt_consump_category(merchantForm.getAmt_consump_category());
		merchant_xBean.setIs_fmrchno_decide(merchantForm.getIs_fmrchno_decide());
		merchant_xBean.setFs_kp_cycle(merchantForm.getFs_kp_cycle());
		merchant_xBean.setFs_kp_cycle_param(merchantForm.getFs_kp_cycle_param());
		merchant_xBean.setLast_kp_date(merchantForm.getLast_kp_date());
		merchant_xBean.setIs_bj(merchantForm.getIs_bj());
		merchant_xBean.setIs_card_type_group(merchantForm.getIs_card_type_group());
		merchant_xBean.setIs_kp(merchantForm.getIs_kp());
		merchant_xBean.setDisabled_date(merchantForm.getDisabled_date());
		merchant_xBean.setEnable_date(merchantForm.getEnable_date());
	}

	/**
	 * �̻��˻���Ϣ��ֵ
	 * 
	 * @param merchantForm
	 * @param mrchaccbean
	 */
	private void filledMrchaccBean(MerchantForm merchantForm, Mrchaccbean mrchaccbean) {
		Merchantdao merchantdao = new Merchantdao();
		Map<String, String> defaultParams = merchantdao.getDefaultParams();
		mrchaccbean.setCurrcode(defaultParams.get("currcode"));
		mrchaccbean.setDate_last_stmt(merchantForm.getDate_last_stmt());
		mrchaccbean.setClosing_bal(merchantForm.getClosing_bal());
		mrchaccbean.setCurrent_bal(merchantForm.getCurrent_bal());
		mrchaccbean.setLast_post_bal(merchantForm.getLast_post_bal());
		mrchaccbean.setLast_post_com(merchantForm.getLast_post_com());
		mrchaccbean.setLast_post_tax(merchantForm.getLast_post_tax());
		mrchaccbean.setVerno_ctx(merchantForm.getVerno_ctx());
	}

	/**
	 * �̻����׻�����Ϣ��ֵ
	 * 
	 * @param merchantForm
	 * @param mrchmtdbean
	 */
	private void filledMrchmtDbean(MerchantForm merchantForm, Mrchmtdbean mrchmtdbean) {
		Merchantdao merchantdao = new Merchantdao();
		Map<String, String> defaultParams = merchantdao.getDefaultParams();
		mrchmtdbean.setCurrcode(defaultParams.get("currcode"));
		mrchmtdbean.setBtchcntmtd(merchantForm.getBtchcntmtd());
		mrchmtdbean.setBtchcntpm(merchantForm.getBtchcntpm());
		mrchmtdbean.setBtchcntytd(merchantForm.getBtchcntytd());
		mrchmtdbean.setBtchdrmtd(merchantForm.getBtchdrmtd());
		mrchmtdbean.setBtchdrpm(merchantForm.getBtchdrpm());
		mrchmtdbean.setBtchdrytd(merchantForm.getBtchdrytd());
		mrchmtdbean.setBtchcrmtd(merchantForm.getBtchcrmtd());
		mrchmtdbean.setBtchcrpm(merchantForm.getBtchcrpm());
		mrchmtdbean.setBtchcrytd(merchantForm.getBtchcrytd());
		mrchmtdbean.setBtchcommtd(merchantForm.getBtchcommtd());
		mrchmtdbean.setBtchcompm(merchantForm.getBtchcompm());
		mrchmtdbean.setBtchcomytd(merchantForm.getBtchcomytd());
		mrchmtdbean.setBtchntmtd(merchantForm.getBtchntmtd());
		mrchmtdbean.setBtchntpm(merchantForm.getBtchntpm());
		mrchmtdbean.setVerno_ctx(merchantForm.getVerno_ctx());
	}

	/**
	 * �̻�������Ϣ��ֵ
	 * 
	 * @param merchantForm
	 * @param merchantBean
	 */
	private void filledMerchantBean(MerchantForm merchantForm, MerchantBean merchantBean) {
		Merchantdao merchantdao = new Merchantdao();
		Map<String, String> defaultParams = merchantdao.getDefaultParams();
		merchantBean.setInst_id(defaultParams.get("inst_id"));
		merchantBean.setMrchno((merchantForm.getMrchno()));
		merchantBean.setHead_office((merchantForm.getHead_office()));
		// �̻����Ƴ���12���������ȡ
		String name = merchantForm.getMrcht_name();
		if (name != null && name.trim().length() > 8) {
			name = name.substring(0, 8);
		}
		merchantBean.setName(name);
		merchantBean.setTrading_as(name);
		merchantBean.setAddress1((merchantForm.getAddress1()));
		merchantBean.setAddress2((merchantForm.getAddress2()));
		merchantBean.setCity((merchantForm.getCity()));
		merchantBean.setState((merchantForm.getState()));
		merchantBean.setPostcode(merchantForm.getPostcode());
		merchantBean.setCountrycode(merchantForm.getCountrycode());
		merchantBean.setPhy_address1(merchantForm.getPhy_address1());
		merchantBean.setPhy_address2(merchantForm.getPhy_address2());
		merchantBean.setPhy_city(merchantForm.getPhy_city());
		merchantBean.setPhy_state(merchantForm.getPhy_state());
		merchantBean.setPhy_postcode(merchantForm.getPhy_postcode());
		merchantBean.setPhy_countrycode(merchantForm.getPhy_countrycode());
		merchantBean.setReg_address1(merchantForm.getReg_address1());
		merchantBean.setReg_address2(merchantForm.getReg_address2());
		merchantBean.setReg_city(merchantForm.getReg_city());
		merchantBean.setReg_state(merchantForm.getReg_state());
		merchantBean.setReg_postcode(merchantForm.getReg_postcode());
		merchantBean.setReg_countrycode(merchantForm.getReg_countrycode());
		merchantBean.setCurrcode(defaultParams.get("currcode"));
		merchantBean.setMrchtype(merchantForm.getMrchtype());
		merchantBean.setAcptbus(merchantForm.getAcptbus());
		merchantBean.setContact1(merchantForm.getContact1());
		merchantBean.setContact2(merchantForm.getContact2());
		String contact3 = merchantForm.getContact3();
		if (contact3 != null && contact3.trim().length() > 6) {
			contact3 = contact3.substring(0, 6);
		}
		merchantBean.setContact3(contact3);
		merchantBean.setTelno1(merchantForm.getTelno1());
		merchantBean.setTelno2(merchantForm.getTelno2());
		merchantBean.setTelno3(merchantForm.getTelno3());
		merchantBean.setFaxno(merchantForm.getFaxno());
		merchantBean.setEmail(merchantForm.getEmail());
		merchantBean.setTelex(merchantForm.getTelex());
		merchantBean.setSortcode(merchantForm.getSortcode());
		merchantBean.setBrncode(defaultParams.get("brncode"));
		merchantBean.setAccno(merchantForm.getAccno());
		merchantBean.setAccnm(merchantForm.getAccnm());
		merchantBean.setTaxcode(defaultParams.get("taxcode"));
		merchantBean.setStmtfreq(merchantForm.getStmtfreq());
		merchantBean.setStmtto(merchantForm.getStmtto());
		merchantBean.setStmtto_ho(merchantForm.getStmtto_ho());
		merchantBean.setPaymtype(merchantForm.getPaymtype());
		merchantBean.setPaymto(merchantForm.getPaymto());
		merchantBean.setPosflag(merchantForm.getPosflag());
		merchantBean.setVipflag(merchantForm.getVipflag());
		merchantBean.setMsc_charge(merchantForm.getMsc_charge());
		merchantBean.setMsc_calc(merchantForm.getMsc_calc());
		merchantBean.setMsc_table(merchantForm.getMsc_table());
		merchantBean.setMsc(merchantForm.getMsc());
		merchantBean.setContrno(merchantForm.getContrno());
		merchantBean.setContrdate(merchantForm.getContrdate());
		merchantBean.setContrcnx(merchantForm.getContrcnx());
		merchantBean.setContrsign(merchantForm.getContrsign());
		merchantBean.setOfficial(defaultParams.get("official"));
		merchantBean.setMrchstat(merchantForm.getMrchstat());
		merchantBean.setRnc(merchantForm.getRnc());
		merchantBean.setTaxreg(merchantForm.getTaxreg());
		merchantBean.setRetenamt(merchantForm.getRetenamt());
		merchantBean.setRetenpc(merchantForm.getRetenpc());
		merchantBean.setZonegeog(merchantForm.getZonegeog());
		merchantBean.setZonecomer(merchantForm.getZonecomer());
		merchantBean.setZonepostal(merchantForm.getZonepostal());
		merchantBean.setUsrdata1(merchantForm.getUsrdata1());
		merchantBean.setUsrdata2(merchantForm.getUsrdata2());
		merchantBean.setUsrdata3(merchantForm.getUsrdata3());
		merchantBean.setMemo(merchantForm.getMemo());
		merchantBean.setCycbegin(merchantForm.getCycbegin());
		merchantBean.setCyclen(merchantForm.getCyclen());
		merchantBean.setNo_imprntrs(merchantForm.getNo_imprntrs());
		merchantBean.setConaccno(merchantForm.getConaccno());
		merchantBean.setConcur(merchantForm.getConcur());
		merchantBean.setPayoffset(merchantForm.getPayoffset());
		merchantBean.setAllowinst(merchantForm.getAllowinst());
		merchantBean.setPppymntattr(merchantForm.getPppymntattr());
		merchantBean.setCat_params(merchantForm.getCat_params());
		merchantBean.setVerno_ctx(merchantForm.getVerno_ctx());
		
	}

	private void filledMrch_classBean(MerchantForm merchantForm, Mrch_classBean mrch_classBean, String insertOrUpdate) {
		Merchantdao merchantdao = new Merchantdao();
		Map<String, String> defaultParams = merchantdao.getDefaultParams();
		mrch_classBean.setInst_id(defaultParams.get("inst_id"));
		if (merchantForm.getFmrchno() == null || "".equals(merchantForm.getFmrchno().trim())) {
			if (insertOrUpdate != null && "update".equals(insertOrUpdate)) {
				mrch_classBean.setFmrchno(null);
			} else {
				mrch_classBean.setFmrchno(merchantForm.getMrchno());
			}
		} else {
			mrch_classBean.setFmrchno(merchantForm.getFmrchno());
		}
		mrch_classBean.setMrchno(merchantForm.getMrchno());
		mrch_classBean.setClassify(merchantForm.getClassify());

	}

	// �������̻�����
	private void filledWankeMerBookBean(MerchantForm merchantForm, WankeMerBookBean wanke_MerBookBean) {

		wanke_MerBookBean.setYard_mer_no(merchantForm.getMrchno());
		// �̻����Ƴ���12���������ȡ
		String name = merchantForm.getMrcht_name();
		if (name != null && name.trim().length() > 8) {
			name = name.substring(0, 8);
		}

		wanke_MerBookBean.setYard_mer_name(name);

		if (null != merchantForm.getYard_mer_type()) {
			wanke_MerBookBean.setYard_mer_type(merchantForm.getYard_mer_type());
			// ����̻���������
			if ("0".equals(merchantForm.getYard_mer_type())) {
				wanke_MerBookBean.setYard_mer_type_name("��Ƴ���");
			}
			if ("".equals(merchantForm.getYard_mer_type())) {
				wanke_MerBookBean.setYard_mer_type_name("");
			}
		}

	}

	/**
	 * ��ʼ�������̻�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAllotMerOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String merchantIds = ParamUtils.getParameter(request, "merchantIds").trim();
		request.setAttribute("selectMers", merchantIds);
		// ��ȡ�̻�����list
		MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
		// ��ȡ����״̬�µ��̻�����
		MerchantOrgForm merchantOrgForm = new MerchantOrgForm();
		merchantOrgForm.setOrgStat("0");
		List<MerchantOrgBean> list = merchantOrgDao.getMerchantOrgList(null, merchantOrgForm);
		request.setAttribute("merchantOrgList", list);
		return mapping.findForward("allotMerOrg.jsp");
	}

	/**
	 * ��ʼ�������̻�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAllotSettlement_person(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String merchantIds = ParamUtils.getParameter(request, "merchantIds").trim();
		request.setAttribute("selectMers", merchantIds);
		return mapping.findForward("allotSettlement_person.jsp");
	}

	public ActionForward preallotMerchant_fenrunorg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String merchantIds = ParamUtils.getParameter(request, "merchantIds").trim();
		request.setAttribute("selectMers", merchantIds);
		// ��ȡ�������list
		MerchantFenrunOrgDao merchantFenrunOrgDao = new MerchantFenrunOrgDao();
		MerchantFenrunOrgForm merchantFenrunOrgForm = new MerchantFenrunOrgForm();
		merchantFenrunOrgForm.setOrgStat("0");
		List<MerchantFenrunOrgBean> merchantFenrunOrgList = merchantFenrunOrgDao.getMerchantOrgList(null,
				merchantFenrunOrgForm);
		request.setAttribute("merchantFenrunOrgList", merchantFenrunOrgList);
		return mapping.findForward("allotMerchant_fenrunorg.jsp");
	}

	/**
	 * �����޸Ľ���Աҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward allotSettlement_person(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String selectMers = merchantForm.getSelectMers();
		String settlement_person = merchantForm.getSettlement_person();
		Merchantdao merchantDao = new Merchantdao();
		String merchantIdsStr[] = org.apache.commons.lang.StringUtils.split(selectMers, "|");

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/merchant.do?method=getMerchantList";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			for (int i = 0; i < merchantIdsStr.length; i++) {
				String mrchno = merchantIdsStr[i];
				int result = merchantDao.allotSettlement_person(mrchno, settlement_person);
				if (result >= 0) {
					info = "�����޸Ľ���Ա�ɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣�������޸Ľ���Աʧ�ܣ�";
					request.setAttribute("result", "1");
					break;
				}
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("result.jsp");

	}

	public ActionForward allotMerchant_fenrunorg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String selectMers = merchantForm.getSelectMers();
		String merchant_fenrunorg = merchantForm.getMerchant_fenrunorg();
		Merchantdao merchantDao = new Merchantdao();
		String merchantIdsStr[] = org.apache.commons.lang.StringUtils.split(selectMers, "|");

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/merchant.do?method=getMerchantList";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			for (int i = 0; i < merchantIdsStr.length; i++) {
				String mrchno = merchantIdsStr[i];
				int result = merchantDao.allotMerchant_fenrunorg(mrchno, merchant_fenrunorg);
				if (result >= 0) {
					info = "�����޸ķ�������ɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣�������޸ķ������ʧ�ܣ�";
					request.setAttribute("result", "1");
					break;
				}
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("result.jsp");

	}

	/**
	 * �����̻�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward allotMerchantOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantForm merchantForm = (MerchantForm) form;
		String selectMers = merchantForm.getSelectMers();
		String merchantOrg = merchantForm.getMerchant_org();
		Merchantdao merchantDao = new Merchantdao();
		String merchantIdsStr[] = org.apache.commons.lang.StringUtils.split(selectMers, "|");

		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/merchant.do?method=getMerchantList";
		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {
			for (int i = 0; i < merchantIdsStr.length; i++) {
				String mrchno = merchantIdsStr[i];
				int result = merchantDao.allotMerchantOrg(mrchno, merchantOrg);
				if (result >= 0) {
					info = "�̻���ӻ�����Ϣ�ɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣���̻���ӻ�����Ϣʧ�ܣ�";
					request.setAttribute("result", "1");
					break;
				}
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("result.jsp");

	}

	/**
	 * @TODO ��ʾ�̻������¾����̻���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward detailMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String merchantOrg = ParamUtils.getParameter(request, "merchant_org");
		Merchantdao merchantDao = new Merchantdao();
		List<FullMerchantBean> merchantList = merchantDao.detailMerchant(merchantOrg);
		if (merchantList == null) {
			merchantList = new ArrayList<>();
		}
		request.setAttribute("merchantList", merchantList);
		request.setAttribute("counts", merchantList.size());
		return mapping.findForward("detailMerchant.jsp");
	}

	/**
	 * @TODO ��ʾ�̻������¾����̻���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward detailFenrunMerchant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String merchant_fenrunorg = ParamUtils.getParameter(request, "merchant_fenrunorg");
		Merchantdao merchantDao = new Merchantdao();
		/*
		 * List<FullMerchantBean> merchantList =
		 * merchantDao.detailMerchant(merchantOrg);
		 */
		List<FullMerchantBean> merchantList = merchantDao.detailFenrunMerchant(merchant_fenrunorg);
		if (merchantList == null) {
			merchantList = new ArrayList<>();
		}
		request.setAttribute("merchantList", merchantList);
		request.setAttribute("counts", merchantList.size());
		return mapping.findForward("detailFenrunMerchant.jsp");
	}
}
