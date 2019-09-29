package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
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

public class EdcCommonDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcCommonDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcCommon");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcCommonDaoHibernateHQLImpl() {
	}

	@SuppressWarnings("unchecked")
	public List<Banktype> queryAllBanktypeItem(Banktype banktype, UserData ud) throws OAException {

		String hql = "from Banktype t order by t.bankType ";
		List<Banktype> list = new ArrayList<Banktype>();
		try {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllBanktypeItem()��ʼ���ã���ѯ���������б�");

			list = findByHQL(hql, ud);

			log.info("���ؽ������С==" + list.size());
			log.info("EdcCommonDaoHibernateHQLImpl.queryAllBanktypeItem()�������ã���ѯ���������б�");
		} catch (Exception e) {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllCardtypeItem()��ѯ���������б������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public List<BankInfoId> queryAllBankInfoItem(BankInfo banktype, UserData ud) throws OAException {

		String hql = "from BankInfoId order by bankType ";
		List<BankInfoId> list = new ArrayList<BankInfoId>();
		try {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllBanktypeItem()��ʼ���ã���ѯ���������б�");

			list = findByHQL(hql, ud);

			log.info("���ؽ������С==" + list.size());
			log.info("EdcCommonDaoHibernateHQLImpl.queryAllBanktypeItem()�������ã���ѯ���������б�");
		} catch (Exception e) {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllCardtypeItem()��ѯ���������б������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Cardtype> queryAllCardtypeItem(Cardtype cardtype, UserData ud) throws OAException {

		String hql = "from Cardtype order by id.cardType";
		List<Cardtype> list = new ArrayList<Cardtype>();
		try {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllCardtypeItem()��ʼ���ã���ѯ���������б�");

			list = findByHQL(hql, ud);

			log.info("���ؽ������С==" + list.size());
			log.info("EdcCommonDaoHibernateHQLImpl.queryAllCardtypeItem()�������ã���ѯ���������б�");
		} catch (Exception e) {

			log.info("EdcCommonDaoHibernateHQLImpl.queryAllCardtypeItem()��ѯ���������б������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<EdcCard> findEdcCardItemByKey(EdcCard edcCard, UserData ud) throws OAException {

		List<EdcCard> edcCardList = new ArrayList<EdcCard>();
		try {
			StringBuffer bufferHql = new StringBuffer("from EdcCard  t where 1=1");
			if (edcCard.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcCard.getId().getMerchantId() != null && !edcCard.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId().trim() + "'");

				// ���������Ͳ�ѯ
				if (edcCard.getId().getCardType() != null && !edcCard.getId().getCardType().equals(""))
					bufferHql.append(" and t.id.cardType = '" + edcCard.getId().getCardType().trim() + "'");

				// ���ն˱�Ų�ѯ
				if (edcCard.getId().getTerminalId() != null && !edcCard.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcCard.getId().getTerminalId().trim() + "'");

			}
			String hql = bufferHql.toString();
			edcCardList = findByHQL(hql, ud);
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return edcCardList;
	}

	@SuppressWarnings("unchecked")
	public List<EdcSwitch> findEdcSwitchItemByKey(EdcSwitch edcSwitch, UserData ud) throws OAException {
		List<EdcSwitch> list = new ArrayList<EdcSwitch>();
		StringBuffer bufferHql = new StringBuffer(" from EdcSwitch  t where 1=1");
		try {
			if (edcSwitch.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcSwitch.getId().getMerchantId() != null && !edcSwitch.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");

				// ���������Ͳ�ѯ
				if (edcSwitch.getId().getBankType() != null && !edcSwitch.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");

				// ���ն˱�Ų�ѯ
				if (edcSwitch.getId().getTerminalId() != null && !edcSwitch.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcSwitch.getId().getTerminalId() + "'");
			}

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TPreTpdu> findEdcTPreTpduItemByKey(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		List<TPreTpdu> list = new ArrayList<TPreTpdu>();
		try {
			StringBuffer bufferHql = new StringBuffer("from TPreTpdu t where 1=1");

			// ��tpdu��ѯ
			if (tPreTpdu.getTpdu() != null && !tPreTpdu.getTpdu().equals(""))
				bufferHql.append(" and t.tpdu = '" + tPreTpdu.getTpdu() + "'");

			String hql = bufferHql.toString();

			list = findByHQL(hql, ud);
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<EdcTerminal> findEdcTerminalItemByKey(EdcTerminal edcTerminal, UserData ud) throws OAException {
		List<EdcTerminal> edcTerminalList = new ArrayList<EdcTerminal>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {
				// �����̻���Ų�ѯ
				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				// �����ն˺Ų�ѯ
				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals("")){
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");
					bufferHql.append(" and t.terminalStat = 'Y'");
				}

			}

			String hql = bufferHql.toString();
			edcTerminalList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return edcTerminalList;
	}

	
	@SuppressWarnings("unchecked")
	public List<EdcNewfkterminalOrm> findMerTerMod(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		List<EdcNewfkterminalOrm> edcNewfkterminalOrmList = new ArrayList<EdcNewfkterminalOrm>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from EdcNewfkterminalOrm  t where 1=1");


				// �����̻���Ų�ѯ
				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				// �����ն˺Ų�ѯ
				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");

				// ����ģ��ID��ѯ
				if (edcNewfkterminalOrm.getModuleId() != null && !edcNewfkterminalOrm.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + edcNewfkterminalOrm.getModuleId() + "'");

		

			String hql = bufferHql.toString();
			edcNewfkterminalOrmList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return edcNewfkterminalOrmList;
	}
	
	@SuppressWarnings("unchecked")
	public List<EdcTerminalOrm> findMercTermlModu(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where 1=1");


				// �����̻���Ų�ѯ
				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				// �����ն˺Ų�ѯ
				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");

				// ����ģ��ID��ѯ
				if (edcTerminalOrm.getModuleId() != null && !edcTerminalOrm.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + edcTerminalOrm.getModuleId() + "'");

		

			String hql = bufferHql.toString();
			edcTerminalOrmList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return edcTerminalOrmList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MerchantCardModel> findMerchantCardModelItemByKey(MerchantCardModel key, UserData ud) throws OAException {
		List<MerchantCardModel> merchantCardModelList = new ArrayList<MerchantCardModel>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from MerchantCardModel  t where 1=1");

			// �����̻���Ų�ѯ
			if (key.getMerchantId() != null && !key.getMerchantId().equals(""))
				bufferHql.append(" and t.merchantId = '" + key.getMerchantId() + "'");

			String hql = bufferHql.toString();
			merchantCardModelList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return merchantCardModelList;
	}

	@SuppressWarnings("unchecked")
	public String findMerchantNameByKey(String key, UserData ud) throws OAException {
		String result = null;
		try {
			StringBuffer bufferHql = new StringBuffer(" from MerchantBaseModel  t where 1=1");

			// �����̻���Ų�ѯ
			if (key != null && !key.equals("")){
				bufferHql.append(" and t.merchantId = '" + key.trim() + "'");
				bufferHql.append(" and t.merchantStat = 'Y'");
			}

			String hql = bufferHql.toString();
			List<MerchantBaseModel> merchantBaseModelList = findByHQL(hql, ud);
			if (merchantBaseModelList.size() > 0) {
				result = merchantBaseModelList.get(0).getMerchantCname();
			}
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<CardAcct> findCardAcctItemByKey(String key, UserData ud) throws OAException {
		List<CardAcct> list = new ArrayList<CardAcct>();
		try {
			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ�����˻���¼��");

			StringBuffer bufferHql = new StringBuffer(" from CardAcct t where 1=1");

			// �����Ų�ѯ
			if (key != null && !key.equals(""))
				bufferHql.append(" and t.cardNo = '" + key.trim() + "'");

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ�����˻���¼��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()��ʾһ�����˻���¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SysParameter> findSysParameterItemByKey(SysParameter sysParameter, UserData ud) throws OAException {
		List<SysParameter> list = new ArrayList<SysParameter>();
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ����������");

			StringBuffer bufferHql = new StringBuffer(" from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamName() != null && !sysParameter.getId().getParamName().equals(""))
					bufferHql.append(" and t.id.paramName = '" + sysParameter.getId().getParamName() + "'");

				// ������״̬��ѯ
				if (sysParameter.getEnable() != null && !sysParameter.getEnable().equals(""))
					bufferHql.append(" and t.enable = '" + sysParameter.getEnable() + "'");

			}
			String hql = bufferHql.toString();

			list = findByHQL(hql, ud);

			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ����������");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ѯ���������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public List<SysIssuInst> queryAllIssuInstItem(SysIssuInst issuInst, UserData ud) throws OAException {
		List<SysIssuInst> list = new ArrayList<SysIssuInst>();
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ����������");

			StringBuffer bufferHql = new StringBuffer(" from SysIssuInst t where 1=1");

			// ���������Ʋ�ѯ
			if (issuInst.getIssuId() != null && !issuInst.getIssuId().equals(""))
				bufferHql.append(" and t.issuId = '" + issuInst.getIssuId() + "'");

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);

			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ����������");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ѯ���������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public List<SysRespCode> queryAllRespCodeItem(SysRespCode sysRespCode, UserData ud) throws OAException {
		List<SysRespCode> list = new ArrayList<SysRespCode>();
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryAllRespCodeItem()��ʼ���ã���ѯϵͳ�����롣");

			StringBuffer bufferHql = new StringBuffer(" from SysRespCode t where 1=1");

			// ���������Ʋ�ѯ
			if (sysRespCode!=null && sysRespCode.getRespCode() != null && !sysRespCode.getRespCode().equals(""))
				bufferHql.append(" and t.respCode = '" + sysRespCode.getRespCode() + "'");

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);
			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryAllRespCodeItem()�������ã���ѯϵͳ�����롣");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryAllRespCodeItem()��ѯϵͳ�����룬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

}
