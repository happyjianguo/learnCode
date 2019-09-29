package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
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

public interface EdcCommonDaoHibernateHQL

{
	/** �����������п� */
	public List<Banktype> queryAllBanktypeItem(Banktype banktype, UserData ud) throws OAException;

	public List<BankInfoId> queryAllBankInfoItem(BankInfo banktype, UserData ud) throws OAException;

	/** ������������ */
	public List<Cardtype> queryAllCardtypeItem(Cardtype cardtype, UserData ud) throws OAException;

	/** ��ѯ�̻������� */
	public List<MerchantCardModel> findMerchantCardModelItemByKey(MerchantCardModel key, UserData ud) throws OAException;

	/** ��ѯ�̻����� */
	public String findMerchantNameByKey(String key, UserData ud) throws OAException;

	/** ��ѯ�������� */
	public List<EdcCard> findEdcCardItemByKey(EdcCard key, UserData ud) throws OAException;

	/** ��ѯ�ն������趨 */
	public List<EdcTerminal> findEdcTerminalItemByKey(EdcTerminal key, UserData ud) throws OAException;

	/** ��֤edcNewfkerminalOrm�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��*/
	public List<EdcNewfkterminalOrm> findMerTerMod(EdcNewfkterminalOrm key, UserData ud) throws OAException;
	
	/** ��֤edcTerminalOrm�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��*/
	public List<EdcTerminalOrm> findMercTermlModu(EdcTerminalOrm key, UserData ud) throws OAException;
	
	/** ��ѯ�ն�ת�� */
	public List<EdcSwitch> findEdcSwitchItemByKey(EdcSwitch key, UserData ud) throws OAException;

	/** ��ѯ�ն�TPDU */
	public List<TPreTpdu> findEdcTPreTpduItemByKey(TPreTpdu key, UserData ud) throws OAException;

	/*** ��ѯ���˻���Ϣ ***/
	public List<CardAcct> findCardAcctItemByKey(String key, UserData ud) throws OAException;

	/*** ��ѯ��������������Ϣ **/
	public List<SysParameter> findSysParameterItemByKey(SysParameter key, UserData ud) throws OAException;

	/** ��ѯ�������� */
	public List<SysIssuInst> queryAllIssuInstItem(SysIssuInst issuInst, UserData ud) throws OAException;
	
	/** ��ѯϵͳ������  */
	public List<SysRespCode> queryAllRespCodeItem(SysRespCode sysRespCode, UserData ud) throws OAException;

}
