package cn.yufu.posp.sysparam.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.sysparam.domain.logic.SysParamLogicInterface;
import cn.yufu.posp.sysparam.domain.model.SysParam;
import cn.yufu.posp.sysparam.domain.model.SysParamId;
import cn.yufu.posp.sysparam.web.form.SysParamForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;

/**
 * @author 吕哲 机构管理
 * 
 */
public class SysParamDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("sysparam");

	/**
	 * @roseuid 44BAF7150119
	 */
	public SysParamDispatchAction() {

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

	/**
	 * 显示修改机构信息界面
	 */
	public org.apache.struts.action.ActionForward querySysParamByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> jgMap = new HashMap<Object, Object>();
		try {
			log.info("JgDispatchAction.queryJgByKey()开始调用：显示修改机构信息界面。");
			// 得到Logic
			SysParamLogicInterface jgLogic = (SysParamLogicInterface) getBean("sysLogic");

			SysParamForm jgForm = (SysParamForm) form;

			UserData ud = getSessionUserData(request);
			// 此处需要修改主键等
			jgMap = jgLogic.querySysParamByKey(jgForm.getBankId(), ud);

			// 新建一个Model
			SysParam jgModel = (SysParam) jgMap.get("Infolist");
			BeanUtils.copyProperties(jgForm, jgModel.getId());

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
			BankInfoId bi = null;

			List<Banktype> blist = new ArrayList<Banktype>();
			List<BankInfoId> bilist = new ArrayList<BankInfoId>();
			List<BankInfoId> bilist1 = new ArrayList<BankInfoId>();
			Banktype bbb = null;
			boolean bb = true;
			for (int i = 0; i < banktypeList.size(); i++) {
				bbb = (Banktype) banktypeList.get(i);
				if (bb) {
					if (bbb.getBankType().trim().equals(jgForm.getBankType().trim())) {
						bb = false;
						blist.add(0, bbb);
					} else {
						blist.add(bbb);
					}
				} else {
					blist.add(bbb);
				}
			}

			bb = true;
			boolean cc = true;
			for (int i = 0; i < bankInfoList.size(); i++) {

				bi = (BankInfoId) bankInfoList.get(i);
				if (bb) {
					if (bi.getBankId().trim().equals(jgForm.getAdmBankId().trim())) {
						bb = false;
						bilist.add(0, bi);
					} else {
						bilist.add(bi);
					}
				} else {
					bilist.add(bi);
				}
				if (cc) {
					if (bi.getBankId().trim().equals(jgForm.getBankId().trim())) {
						cc = false;
						bilist1.add(0, bi);
					} else {
						bilist1.add(bi);
					}
				} else {
					bilist1.add(bi);
				}
			}
			request.setAttribute("bankInfoList", bilist1);
			request.setAttribute("admbankInfoList", bilist);
			request.setAttribute("bankTypeList", blist);

			// jgForm.setCreChkFlag("aaaa");
			log.info("JgDispatchAction.queryJgByKey()结束调用：显示修改机构信息界面。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.queryJgByKey()显示修改机构信息界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("updateSys");
	}

	/**
	 * 保存机构信息
	 */
	public org.apache.struts.action.ActionForward saveSysParam(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.saveJg()开始调用:创建新的机构信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			SysParamLogicInterface jgLogic = (SysParamLogicInterface) getBean("sysLogic");
			// 得到Form
			SysParamForm jgForm = (SysParamForm) form;
			// 新建一个Model
			SysParam jgModel = new SysParam();
			SysParamId modelId = new SysParamId();
			// System.out.println("aaaaaaaaaaaaaaaaaaa"+jgForm.getJgId());
			BeanUtils.copyProperties(modelId, jgForm);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			modelId.setUpdateOper(ud.getUserId());
			modelId.setUpdateDate(df.format(dt));
			modelId.setUpdateTime(df1.format(dt));

			if (ud.getUserId() != null) {
				jgForm.setUpdateOper(ud.getUserId());
				jgForm.setUpdateDate(df.format(dt));
				jgForm.setUpdateTime(df1.format(dt));
				jgModel.setId(modelId);
			}
			// 创建新的机构信息
			jgLogic.saveSysParam(jgModel, ud);
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
			BankInfoId bi = null;

			List<Banktype> blist = new ArrayList<Banktype>();
			List<BankInfoId> bilist = new ArrayList<BankInfoId>();
			List<BankInfoId> bilist1 = new ArrayList<BankInfoId>();
			Banktype bbb = null;
			boolean bb = true;
			for (int i = 0; i < banktypeList.size(); i++) {
				bbb = (Banktype) banktypeList.get(i);
				if (bb) {
					if (bbb.getBankType().trim().equals(jgForm.getBankType().trim())) {
						bb = false;
						blist.add(0, bbb);
					} else {
						blist.add(bbb);
					}
				} else {
					blist.add(bbb);
				}
			}

			bb = true;
			boolean cc = true;
			for (int i = 0; i < bankInfoList.size(); i++) {

				bi = (BankInfoId) bankInfoList.get(i);
				if (bb) {
					if (bi.getBankId().trim().equals(jgForm.getAdmBankId().trim())) {
						bb = false;
						bilist.add(0, bi);
					} else {
						bilist.add(bi);
					}
				} else {
					bilist.add(bi);
				}
				if (cc) {
					if (bi.getBankId().trim().equals(jgForm.getBankId().trim())) {
						cc = false;
						bilist1.add(0, bi);
					} else {
						bilist1.add(bi);
					}
				} else {
					bilist1.add(bi);
				}
			}
			request.setAttribute("bankInfoList", bilist1);
			request.setAttribute("admbankInfoList", bilist);
			request.setAttribute("bankTypeList", blist);
			log.info("SysParamDispatchAction.saveJg()结束调用:修改参数信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.saveJg()修改参数信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("updateSys");
	}

}
