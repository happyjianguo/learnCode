package cn.yufu.posp.terminalmanager.domain.logic;

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

public interface EdcCommonLogicInterface {
	/** 查找所有银行卡 */
	public List<Banktype> queryAllBanktypeItem(Banktype banktype, UserData ud) throws OAException;

	/** 查找所有银行卡 */
	public List<BankInfoId> queryAllBankinfo(BankInfo banktype, UserData ud) throws OAException;

	/** 查找所有受理卡 */
	public List<Cardtype> queryAllCardtypeItem(Cardtype cardtype, UserData ud) throws OAException;

	/** 查询商户受理卡类 */
	public List<MerchantCardModel> findMerchantCardModelItemByKey(MerchantCardModel key, UserData ud) throws OAException;

	/** 查询商户名称 */
	public String findMerchantNameByKey(String key, UserData ud) throws OAException;

	/** 查询受理卡集合 */
	public List<EdcCard> findEdcCardItemByKey(EdcCard key, UserData ud) throws OAException;

	/** 查询终端资料设定 */
	public List<EdcTerminal> findEdcTerminalItemByKey(EdcTerminal key, UserData ud) throws OAException;
	
	/**验证edcNewfkerminalOrm商户编号、终端号 和模块ID三者的唯一性*/
	public List<EdcNewfkterminalOrm> findMerTerMod(EdcNewfkterminalOrm key, UserData ud) throws OAException;

	/**验证edcTerminalOrm商户编号、终端号 和模块ID三者的唯一性*/
	public List<EdcTerminalOrm> findMercTermlModu(EdcTerminalOrm key, UserData ud) throws OAException;
	
	/** 查询终端转换 */
	public List<EdcSwitch> findEdcSwitchItemByKey(EdcSwitch key, UserData ud) throws OAException;

	/** 查询终端TPDU */
	public List<TPreTpdu> findEdcTPreTpduItemByKey(TPreTpdu key, UserData ud) throws OAException;

	/** 查找商户--受理卡 */
	public List<Banktype> queryAllMerchantBanktypeItemByKey(MerchantCardModel key, UserData ud) throws OAException;

	/** 查找商户--银行 */
	public List<Cardtype> queryAllMerchantCardtypeItemByKey(MerchantCardModel key, UserData ud) throws OAException;

	/*** 查询卡账户信息 ***/
	public List<CardAcct> findCardAcctItemByKey(String key, UserData ud) throws OAException;

	/*** 出现其他参数管理信息 **/
	public List<SysParameter> findSysParameterItemByKey(SysParameter key, UserData ud) throws OAException;

	/** 查询发卡机构 */
	public List<SysIssuInst> queryAllIssuInstItem(SysIssuInst issuInst, UserData ud) throws OAException;
	
	/** 查询系统返回码  */
	public List<SysRespCode> queryAllRespCodeItem(SysRespCode sysRespCode, UserData ud) throws OAException;
}
