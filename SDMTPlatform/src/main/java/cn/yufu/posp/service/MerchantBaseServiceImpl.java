package cn.yufu.posp.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.posp.dao.MerchantBaseMapper;
import cn.yufu.posp.dao.MerchantExtraMapper;
import cn.yufu.posp.dao.MerchantIdentityMapper;
import cn.yufu.posp.dao.MerchantRefundMapper;
import cn.yufu.posp.dao.MerchantXMapper;
import cn.yufu.posp.entity.MerchantBase;
import cn.yufu.posp.entity.MerchantBaseExample;
import cn.yufu.posp.entity.MerchantExtra;
import cn.yufu.posp.entity.MerchantIdentity;
import cn.yufu.posp.entity.MerchantRefund;
import cn.yufu.posp.entity.MerchantX;


@Service("posp.MerchantBaseService")
@Transactional("transactionManagerPosp")
public class MerchantBaseServiceImpl implements MerchantBaseService {
	Log log = Log.getLog(MerchantBaseServiceImpl.class);

	@Autowired
	@Qualifier("posp.MerchantBaseDao")
	private MerchantBaseMapper MerchantBaseDao;
	@Autowired
	@Qualifier("posp.MerchantXDao")
	private MerchantXMapper MerchantXDao;
	@Autowired
	@Qualifier("posp.MerchantExtraDao")
	private MerchantExtraMapper MerchantExtraDao;
	@Autowired
	@Qualifier("posp.MerchantRefundDao")
	private MerchantRefundMapper MerchantRefundDao;	
	@Autowired
	@Qualifier("posp.MerchantIdentityDao")
	private MerchantIdentityMapper MerchantIdentityDao;
	
	public List<MerchantBase> queryList(String merchantId) {
		MerchantBaseExample example = new MerchantBaseExample();
		MerchantBaseExample.Criteria criteria = example.createCriteria();
		criteria.andMerchantIdEqualTo(merchantId);
		return MerchantBaseDao.selectByExample(example);
	}
	
	public void synchronousPospMerchant(MerchantSDMT info) {
		/*192.168.6.12:1521:fksd Posp MERCHANT_BASE表*/
		this.synchronousMerchantBase(info);
		/*192.168.6.12:1521:fksd Posp MERCHANT_EXTRA表*/
		this.synchronousMerchantExtra(info);
		/*192.168.6.12:1521:fksd Posp MERCHANT_IDENTITY*/
		this.synchronousMerchantIdentity(info);
		/*192.168.6.12:1521:fksd Posp MERCHANT_REFUND*/
		this.synchronousMerchantRefund(info);
		// MERCHANT_X TODO
		this.synchronousMerchantX(info);
	}
	// MERCHANT_X
	public void synchronousMerchantX(MerchantSDMT info) {
		MerchantX merchantX = new MerchantX();
		merchantX.setContractRenewalDate(info.getContractRenewalDate());
		merchantX.setContractStartDate(info.getContractStartDate());
		merchantX.setMerchantCompanyName(info.getMerchantCompanyName());
		merchantX.setRenewalType(info.getRenewalType());
		merchantX.setGuaranteeLastDate(info.getGuaranteeLastDate());
		merchantX.setGuaranteeAmt(info.getGuaranteeAmt());
		merchantX.setMarketContactMobile(info.getMarketContactMobile());
		merchantX.setMarketContactPerson(info.getMarketContactPerson());
		merchantX.setFinancialContactPerson(info.getFinancialContactPerson());
		merchantX.setFinancialContactMobile(info.getFinancialContactMobile());
		merchantX.setMerchantDeposit(info.getMerchantDeposit());
		merchantX.setMerchantArea(info.getMerchantArea());
		merchantX.setStoreManager(info.getStoreManager());
		merchantX.setMerchantAdvisor(info.getMerchantAdvisor());
		merchantX.setMerchantId(info.getMerchantId());//商户编号
		merchantX.setMerchantCname(info.getMrchtName());//商户名称 MERCHANT_CNAME
		merchantX.setAddressChn(info.getAddress());//地址 ADDRESS_CHN
		merchantX.setRcvName(info.getAccName());//结算账户开户名RCV_NAME
		merchantX.setRcvBank(info.getBankName());//开户银行名称RCV_BANK
		merchantX.setCreateDate(new Date());
		merchantX.setUpdateDate(new Date());
		merchantX.setContractEndDate(info.getContractEndDate());
		merchantX.setTypeYf(info.getTypeYf());
		merchantX.setManName(info.getManName());
		merchantX.setIdType1(info.getIdType1());
		merchantX.setIdNo1(info.getIdNo1());
		merchantX.setIdDeadline1(info.getIdDeadline1());
		merchantX.setAccXName(info.getAccXName());
		MerchantXDao.delete(merchantX);
		MerchantXDao.insertSelective(merchantX);
	}

	/*192.168.6.12:1521:fksd Posp MERCHANT_BASE表*/
	public void synchronousMerchantBase(MerchantSDMT info){
		MerchantBase merchantBase=new MerchantBase();
		merchantBase.setMerchantId(info.getMerchantId());
		merchantBase.setMcc("0763");//info.getMccId()
		if(StringUtils.isNotEmpty(info.getMrchtName())){
			if(info.getMrchtName().length() > 8){
				merchantBase.setMerchantCname(info.getMrchtName().trim().substring(0,8));
			}else{
				merchantBase.setMerchantCname(info.getMrchtName().trim());
			}
		}else{
			merchantBase.setMerchantCname(info.getMrchtName().trim());
		}
		merchantBase.setAbbrCname(info.getAbbrCname().trim());
		merchantBase.setMerchantEname(info.getMerchantEname().trim());
		merchantBase.setAbbrEname(info.getAbbrEname().trim());
		if(StringUtils.isNotEmpty(info.getAddress())){
			if(info.getAddress().length() > 10){
				merchantBase.setAddressChn(info.getAddress().trim().substring(0,10));
			}else{
				merchantBase.setAddressChn(info.getAddress().trim());
			}
		}else{
			merchantBase.setAddressChn(info.getAddress().trim());
		}
		//TODO
		merchantBase.setCityCname("1000");//1000-固定值
		merchantBase.setManager(info.getManager().trim());
		merchantBase.setTelephone(info.getTelephone().trim());
		merchantBase.setZbank("000001");
		merchantBase.setSignBankId("000001");
		merchantBase.setSignHostId("00");
		merchantBase.setMerchantStat("Y");
		merchantBase.setSettleMode("0");
		merchantBase.setSignDate(info.getAddDate().toString().substring(0, 8));		
		merchantBase.setUpdateOper("admin");
		merchantBase.setUpdateDate(info.getAddDate().toString().substring(0, 8));
		merchantBase.setUpdateTime(info.getAddDate().toString().substring(8));
		MerchantBase MerchantBase = MerchantBaseDao.selectByMerchantId(merchantBase);
		if(null == MerchantBase){
			MerchantBaseDao.insertSelective(merchantBase);
		}
	}

	/*192.168.6.12:1521:fksd Posp MERCHANT_EXTRA表*/
	public void synchronousMerchantExtra(MerchantSDMT info) {
		/*
		info.getAccName();//结算账户开户名
		info.getBankName();//开户银行名称
		*/
		MerchantExtra merchantExtra=new MerchantExtra();		
		merchantExtra.setMerchantId(info.getMerchantId());
		if(StringUtils.isNotEmpty(info.getAccName())){
			if(info.getAccName().length() > 10){
				merchantExtra.setRcvName(info.getAccName().trim().substring(0,10));
			}else{
				merchantExtra.setRcvName(info.getAccName().trim());
			}
		}else{
			merchantExtra.setRcvName(info.getAccName().trim());
		}
		merchantExtra.setRcvAcct1(info.getAccNo().trim());
		if(StringUtils.isNotEmpty(info.getBankName())){
			if(info.getBankName().length() > 10){	
				merchantExtra.setRcvBank(info.getBankName().trim().substring(0,10));
			}else{
				merchantExtra.setRcvBank(info.getBankName().trim());
			}
		}else{
			merchantExtra.setRcvBank(info.getBankName().trim());
		}
		MerchantExtra MerchantExtra = MerchantExtraDao.selectByMerchantId(merchantExtra);
		if(null == MerchantExtra){
			MerchantExtraDao.insertSelective(merchantExtra);
		}
	}
	
	/*192.168.6.12:1521:fksd Posp MERCHANT_REFUND*/
	public void synchronousMerchantRefund(MerchantSDMT info){
		MerchantRefund merchantRefund=new MerchantRefund();		
		merchantRefund.setMerchantId(info.getMerchantId());
		merchantRefund.setRefundLimit(Double.valueOf("0"));
		merchantRefund.setRefundCheck("N");
		MerchantRefund MerchantRefund = this.MerchantRefundDao.selectByMerchantId(merchantRefund);
		if(null == MerchantRefund){
			this.MerchantRefundDao.insertSelective(merchantRefund);
		}
	}

	/*192.168.6.12:1521:fksd Posp MERCHANT_IDENTITY*/
	public void synchronousMerchantIdentity(MerchantSDMT info){
		MerchantIdentity merchantIdentity=new MerchantIdentity();	
		merchantIdentity.setMerchantId(info.getMerchantId());
		if(StringUtils.isNotEmpty(info.getMrchtName())){
			if(info.getMrchtName().length() > 8){	
				merchantIdentity.setMerchantCname(info.getMrchtName().trim().substring(0,8));
			}else{
				merchantIdentity.setMerchantCname(info.getMrchtName().trim());
			}
		}else{
			merchantIdentity.setMerchantCname(info.getMrchtName().trim());
		}
		merchantIdentity.setStatus("0");
		MerchantIdentity MerchantIdentity = this.MerchantIdentityDao.selectByMerchantId(merchantIdentity);
		if(null == MerchantIdentity){
			this.MerchantIdentityDao.insertSelective(merchantIdentity);
		}
	}
}
