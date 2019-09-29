package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.BankInfo;
import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;
import cn.yufu.posp.terminalmanager.domain.model.SysIssuInst;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysRespCode;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;

public class EdcCommonLogic extends BaseLogic implements EdcCommonLogicInterface {

	private static final Log log = LogFactory.getLog("edcCommon");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcCommonLogic() {

	}

	public List<SysIssuInst> queryAllIssuInstItem(SysIssuInst issuInst, UserData ud) throws OAException {
		List<SysIssuInst> list = new ArrayList<SysIssuInst>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.queryAllIssuInstItem(issuInst, ud);
		} catch (Exception e) {
			log.info("��ѯ��������===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
	
	public List<SysRespCode> queryAllRespCodeItem(SysRespCode SysRespCode, UserData ud) throws OAException {
		List<SysRespCode> list = new ArrayList<SysRespCode>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.queryAllRespCodeItem(SysRespCode, ud);
		} catch (Exception e) {
			log.info("��ѯϵͳ������===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
	
	public List<Banktype> queryAllBanktypeItem(Banktype banktype, UserData ud) throws OAException {
		List<Banktype> list = new ArrayList<Banktype>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.queryAllBanktypeItem(banktype, ud);
		} catch (Exception e) {
			log.info("��ѯ��������===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<BankInfoId> queryAllBankinfo(BankInfo banktype, UserData ud) throws OAException {
		List<BankInfoId> list = new ArrayList<BankInfoId>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.queryAllBankInfoItem(banktype, ud);
		} catch (Exception e) {
			log.info("��ѯ��������===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<Cardtype> queryAllCardtypeItem(Cardtype cardtype, UserData ud) throws OAException {
		List<Cardtype> list = new ArrayList<Cardtype>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.queryAllCardtypeItem(cardtype, ud);
		} catch (Exception e) {
			log.info("��ѯ���������б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<EdcCard> findEdcCardItemByKey(EdcCard key, UserData ud) throws OAException {
		List<EdcCard> list = new ArrayList<EdcCard>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findEdcCardItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�������б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<EdcSwitch> findEdcSwitchItemByKey(EdcSwitch key, UserData ud) throws OAException {
		List<EdcSwitch> list = new ArrayList<EdcSwitch>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findEdcSwitchItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�ն�ת���б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<TPreTpdu> findEdcTPreTpduItemByKey(TPreTpdu key, UserData ud) throws OAException {
		List<TPreTpdu> list = new ArrayList<TPreTpdu>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findEdcTPreTpduItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�ն�TPDU�б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<EdcTerminal> findEdcTerminalItemByKey(EdcTerminal key, UserData ud) throws OAException {
		List<EdcTerminal> list = new ArrayList<EdcTerminal>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findEdcTerminalItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�ն����������б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
	

	public List<EdcNewfkterminalOrm> findMerTerMod(EdcNewfkterminalOrm key, UserData ud) throws OAException {
		List<EdcNewfkterminalOrm> list = new ArrayList<EdcNewfkterminalOrm>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findMerTerMod(key, ud);
		} catch (Exception e) {
			log.info("��֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
	
	public List<EdcTerminalOrm> findMercTermlModu(EdcTerminalOrm key, UserData ud) throws OAException {
		List<EdcTerminalOrm> list = new ArrayList<EdcTerminalOrm>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findMercTermlModu(key, ud);
		} catch (Exception e) {
			log.info("��֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<MerchantCardModel> findMerchantCardModelItemByKey(MerchantCardModel key, UserData ud) throws OAException {
		List<MerchantCardModel> list = new ArrayList<MerchantCardModel>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			list = dao.findMerchantCardModelItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�̻������б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<Banktype> queryAllMerchantBanktypeItemByKey(MerchantCardModel key, UserData ud) throws OAException {
		List<Banktype> list = new ArrayList<Banktype>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			List<Banktype> banktypeList = dao.queryAllBanktypeItem(null, ud);
			List<MerchantCardModel> merchantBankTypeList = dao.findMerchantCardModelItemByKey(key, ud);

			for (int i = 0; i < merchantBankTypeList.size(); i++) {
				String bankType = merchantBankTypeList.get(i).getBankType().trim();
				for (int j = 0; j < banktypeList.size(); j++) {
					if (bankType.endsWith(banktypeList.get(j).getBankType().trim())) {
						list.add(banktypeList.get(j));
						banktypeList.remove(j);
					}

				}
			}

		} catch (Exception e) {
			log.info("��ѯ�̻�--�����б�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public List<Cardtype> queryAllMerchantCardtypeItemByKey(MerchantCardModel key, UserData ud) throws OAException {
		List<Cardtype> list = new ArrayList<Cardtype>();
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			List<Cardtype> cardtypeList = dao.queryAllCardtypeItem(null, ud);
			List<MerchantCardModel> merchantCartTypeList = dao.findMerchantCardModelItemByKey(key, ud);

			for (int i = 0; i < merchantCartTypeList.size(); i++) {
				String cartType = merchantCartTypeList.get(i).getCardType().trim();
				for (int j = 0; j < cardtypeList.size(); j++) {
					if (cartType.endsWith(cardtypeList.get(j).getId().getCardType().trim())) {
						list.add(cardtypeList.get(j));
						cardtypeList.remove(j);
					}

				}
			}

		} catch (Exception e) {
			log.info("��ѯ�̻�-����===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	public String findMerchantNameByKey(String key, UserData ud) throws OAException {

		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			return dao.findMerchantNameByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ�̻�����===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public List<CardAcct> findCardAcctItemByKey(String key, UserData ud) throws OAException {

		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			return dao.findCardAcctItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ���˺�===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public List<SysParameter> findSysParameterItemByKey(SysParameter key, UserData ud) throws OAException {
		try {

			EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			return dao.findSysParameterItemByKey(key, ud);
		} catch (Exception e) {
			log.info("��ѯ����===�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	

}
