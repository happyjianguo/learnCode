package cn.yufu.cortex.service;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.cortex.dao.MerchantMapper;
import cn.yufu.cortex.dao.MerchantXMapper;
import cn.yufu.cortex.dao.MrchAccMapper;
import cn.yufu.cortex.dao.MrchAccXMapper;
import cn.yufu.cortex.dao.MrchClassMapper;
import cn.yufu.cortex.dao.MrchMtdMapper;
import cn.yufu.cortex.entity.Merchant;
import cn.yufu.cortex.entity.MerchantX;
import cn.yufu.cortex.entity.MrchAcc;
import cn.yufu.cortex.entity.MrchAccX;
import cn.yufu.cortex.entity.MrchClass;
import cn.yufu.cortex.entity.MrchMtd;

@Service("cortex.CortexService")
@Transactional("transactionManagerCortex")
public class CortexServiceImpl implements CortexService {
	Log log = Log.getLog(CortexServiceImpl.class);

	@Autowired
	@Qualifier("cortex.MerchantDao")
	private MerchantMapper MerchantDao;
	@Autowired
	@Qualifier("cortex.MerchantXDao")
	private MerchantXMapper MerchantXDao;
	@Autowired
	@Qualifier("cortex.MrchAccDao")
	private MrchAccMapper MrchAccDao;
	@Autowired
	@Qualifier("cortex.MrchClassDao")
	private MrchClassMapper MrchClassDao;
	@Autowired
	@Qualifier("cortex.MrchMtdDao")
	private MrchMtdMapper MrchMtdDao;
	@Autowired
	@Qualifier("cortex.MrchAccXDao")
	private MrchAccXMapper MrchAccXDao;
	
	
	public String synchronousCortexMerchant(MerchantSDMT info) {
		String seqMerchantIdAndSeqMrchAccXId="";
		/*222 MERCHANT ok */
		String seqMerchantId=this.synchronousMerchant(info);
		/*222 MERCHANT_X ok*/
		this.synchronousMerchantX(info,seqMerchantId);
		/*222 MRCHACC*/
		this.synchronousMrchacc(info, seqMerchantId);
		/*222 MRCH_ACC_X*/
		String seqMrchAccXId=this.synchronousMrchaccX(info, seqMerchantId);
		/*222 MRCH_CLASS*/
		this.synchronousMrchClass(info);
		/*222 MRCHMTD*/
		this.synchronousMrchmtd(info, seqMerchantId);
		seqMerchantIdAndSeqMrchAccXId=seqMerchantId+"#"+seqMrchAccXId;		
		return seqMerchantIdAndSeqMrchAccXId;
	}

	public String synchronousMerchant(MerchantSDMT info) {
		String seqMerchantId=MerchantDao.getCortexMerchantId();
		Merchant merchant=new Merchant();
		merchant.setInstId(Long.valueOf(SysConst.CONST_X_INST_ID));
		merchant.setMrchno(info.getMerchantId());
		merchant.setHeadOffice("2014041711");
		if(StringUtils.isNotEmpty(info.getMrchtName())){
			if(info.getMrchtName().length() > 8){
				merchant.setName(info.getMrchtName().substring(0,8));
			}else{
				merchant.setName(info.getMrchtName());
			}
		}else{
			merchant.setName(info.getMrchtName());
		}
		merchant.setTradingAs(" ");
		merchant.setAddress1("AC");
		merchant.setAddress2("AD");
		merchant.setCity("BEIJING");
		merchant.setState("AE");
		merchant.setPostcode("AF");
		merchant.setCountrycode("156");
		merchant.setPhyAddress1("AG");
		merchant.setPhyAddress2("AH");
		merchant.setPhyCity("AI");
		merchant.setPhyState("AJ");
		merchant.setPhyPostcode("AK");
		merchant.setPhyCountrycode("004");
		merchant.setRegAddress1("AL");
		merchant.setRegAddress2("AM");
		merchant.setRegCity("AN");
		merchant.setRegState("AO");
		merchant.setRegPostcode("AP");
		merchant.setRegCountrycode("660");
		merchant.setCurrcode(SysConst.CONST_X_CURRCODE);
		merchant.setMrchtype(0);
		merchant.setAcptbus(Integer.valueOf(info.getMccId()));
		merchant.setContact1("AQ");
		merchant.setContact2("AT");
		merchant.setContact3(info.getManager());
		merchant.setTelno1(info.getTelephone());
		merchant.setTelno2("AU");
		merchant.setTelno3("AX");
		merchant.setFaxno("AS");
		merchant.setEmail("AV");
		merchant.setTelex("AY");
		merchant.setSortcode("B1");
		merchant.setBrncode(SysConst.CONST_X_BRNCODE);
		merchant.setAccno("6225123412341235");
		merchant.setAccnm("AZ");
		merchant.setTaxcode(SysConst.CONST_X_TAXCODE);
		merchant.setStmtfreq("0");
		merchant.setStmtto(1);
		merchant.setStmttoHo(0);
		merchant.setPaymtype("0");
		merchant.setPaymto("0");
		merchant.setPosflag("1");
		merchant.setVipflag("0");
		merchant.setMscCharge("0");
		merchant.setMscCalc("0");
		merchant.setMscTable(15);		
		merchant.setMsc(Double.valueOf("14"));			
		merchant.setContrno("A3");
		merchant.setContrdate(new Date());
		try {
			merchant.setContrcnx(DateUtil.getDateFromString("22630831", "yyyyMMdd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		merchant.setContrsign("yfo");				//
		merchant.setOfficial(SysConst.CONST_X_OFFICIAL);
		merchant.setMrchstat(0);
		merchant.setRnc("A4");
		merchant.setTaxreg("A5");
		merchant.setRetenamt(Double.valueOf("16"));
		merchant.setRetenpc(Double.valueOf("17"));
		merchant.setZonegeog("A9");
		merchant.setZonecomer("AA");
		merchant.setZonepostal("AB");
		merchant.setUsrdata1("A6");
		merchant.setUsrdata2("A7");
		merchant.setUsrdata3("A8");
		merchant.setMemo(" ");
		merchant.setCycbegin(new Date());
		merchant.setCyclen(13);
		merchant.setNoImprntrs(1984);
		merchant.setConaccno("B2");
		merchant.setConcur("B3");
		merchant.setPayoffset(18);
		merchant.setAllowinst(0);
		merchant.setPppymntattr(1);
		merchant.setCatParams("20141107");
		merchant.setId(Long.valueOf(seqMerchantId));			
		merchant.setVernoCtx(1l);
		log.info("X平台查询MerchantDao");
		MerchantDao.insertSelective(merchant);	
		return seqMerchantId;
	}

	public void synchronousMerchantX(MerchantSDMT info,String seqMerchantId) {
		MerchantX merchantX = new MerchantX();
		try
		{
			merchantX.setInstId(Long.valueOf(SysConst.CONST_X_INST_ID));
			merchantX.setMrchno(info.getMerchantId());
			merchantX.setMrchtName(info.getMrchtName());
			merchantX.setAddress(info.getAddress());
			merchantX.setAddDate(info.getAddDate());
			merchantX.setCityNo(Long.valueOf(info.getCityNo()));
			merchantX.setProvince(Long.valueOf(info.getProvince()));
			merchantX.setZone(Long.valueOf(info.getZone()));
			merchantX.setState(Integer.valueOf(0));
			merchantX.setTypeYf(info.getTypeYf());
			merchantX.setAgent(info.getAgent());
			merchantX.setIdType(info.getIdType());
			merchantX.setIdNo(info.getIdNo());
			merchantX.setIdValidity(DateUtil.getDateFromString(info.getIdValidity().toString(), "yyyyMMdd"));
			merchantX.setBusLicNo(info.getBusLicNo());
			merchantX.setBusLicValidity(DateUtil.getDateFromString(info.getBusLicValidity().toString(), "yyyyMMdd"));
			merchantX.setTaxId(info.getTaxId());
			merchantX.setTaxIdValidity(DateUtil.getDateFromString(info.getTaxIdValidity().toString(), "yyyyMMdd"));
			merchantX.setOrgId(info.getOrgId());
			merchantX.setOrgValidity(DateUtil.getDateFromString(info.getOrgValidity().toString(), "yyyyMMdd"));
			merchantX.setTelno1(info.getTelephone());
			merchantX.setPostcode("210000");
			merchantX.setContact3(info.getManager());
//			merchantX.setAccno(info.getAccNo());
			merchantX.setAccno(info.getEnterpriseNo());//企业帐号
			merchantX.setMerchantId(Long.valueOf(seqMerchantId));
			merchantX.setLegalRep(info.getLegalRep());
			merchantX.setLrIdType(info.getLrIdType());
			merchantX.setLrIdNo(info.getLrIdNo());
			merchantX.setLrIdValidity(DateUtil.getDateFromString(info.getLrIdValidity().toString(), "yyyyMMdd"));
			merchantX.setManName(info.getManName());
			merchantX.setIdType1(info.getIdType1());
			merchantX.setIdNo1(info.getIdNo1());
			merchantX.setIdDeadline1(DateUtil.getDateFromString(info.getIdDeadline1().toString(), "yyyyMMdd"));
			merchantX.setAccXName(info.getAccXName());
			merchantX.setMerchant_x_operate(info.getMerchant_x_operate());
			merchantX.setMerchant_x_type(info.getMerchant_x_type());
			merchantX.setMerchant_x_reg_amt(info.getMerchant_x_reg_amt());
			merchantX.setMerchant_x_code(info.getMerchant_x_code());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		log.info("X平台查询MerchantXDao");
		MerchantXDao.insertSelective(merchantX);
	}

	public void synchronousMrchacc(MerchantSDMT info,String seqMerchantId) {
		MrchAcc mrchAcc=new MrchAcc();
		mrchAcc.setMerchantId(Long.valueOf(seqMerchantId));
		mrchAcc.setCurrcode(SysConst.CONST_X_CURRCODE);
		try {
			mrchAcc.setDateLastStmt(DateUtil.getDateFromString("22630831", "yyyyMMdd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mrchAcc.setClosingBal(Double.valueOf("0"));
		mrchAcc.setCurrentBal(Double.valueOf("0"));
		mrchAcc.setLastPostBal(Double.valueOf("0"));
		mrchAcc.setLastPostCom(Double.valueOf("0"));
		mrchAcc.setLastPostTax(Double.valueOf("0"));
		mrchAcc.setVernoCtx(1l);
		log.info("X平台查询MrchAccDao");
		MrchAccDao.insertSelective(mrchAcc);		
	}
	
	public String synchronousMrchaccX(MerchantSDMT info,String seqMerchantId) {
		String id=this.MrchAccXDao.getCortexMrchAccXId();
		MrchAccX mrchAccX=new MrchAccX();
		try {			
			mrchAccX.setId(Long.valueOf(id));
			mrchAccX.setInstId(Long.valueOf(SysConst.CONST_X_INST_ID));
			mrchAccX.setMrchno(info.getMerchantId());
			mrchAccX.setAccno(info.getAccNo());//结算银行
			mrchAccX.setAccName(info.getAccName());
			mrchAccX.setBankNo(info.getBankNo());
			mrchAccX.setBankName(info.getBankName());
			mrchAccX.setAccAddDate(DateUtil.getDateFromString(info.getAddDate().toString().substring(0, 8), "yyyyMMdd"));
			mrchAccX.setAccNickName(info.getAccNickName());
			mrchAccX.setShortNickName(info.getShortNickName());
			mrchAccX.setIndividual(info.getIndividual());
			mrchAccX.setLastSettleDate(DateUtil.getDateFromString(info.getLastSettleDate().toString(), "yyyyMMdd"));
			mrchAccX.setMerchantId(Long.valueOf(seqMerchantId));
			mrchAccX.setAccIntrod(" ");	
			mrchAccX.setIsBjAcct(info.getIsBjAcct());
			mrchAccX.setBis(info.getBis());		
			mrchAccX.setMerchant_x_acc_type1(info.getMerchant_x_acc_type1());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.info("x平台查询MrchAccXDao");
		this.MrchAccXDao.insertSelective(mrchAccX);
		return id;
	}
	
	public void synchronousMrchmtd(MerchantSDMT info,String seqMerchantId) {
		MrchMtd mrchMtd=new MrchMtd();
		mrchMtd.setMerchantId(Long.valueOf(seqMerchantId));
		mrchMtd.setCurrcode(SysConst.CONST_X_CURRCODE);
		mrchMtd.setVernoCtx(1l); 
		mrchMtd.setBtchcntmtd(0);
		mrchMtd.setBtchcntpm(0);
		mrchMtd.setBtchcntytd(0) ;
		mrchMtd.setBtchdrmtd(Double.valueOf("0"));
		mrchMtd.setBtchdrpm(Double.valueOf("0"));
		mrchMtd.setBtchdrytd(Double.valueOf("0")); 
		mrchMtd.setBtchcrmtd(Double.valueOf("0")); 
		mrchMtd.setBtchcrpm(Double.valueOf("0"));
		mrchMtd.setBtchcrytd(Double.valueOf("0")); 
		mrchMtd.setBtchcommtd(Double.valueOf("0"));
		mrchMtd.setBtchcompm(Double.valueOf("0")) ;
		mrchMtd.setBtchcomytd(Double.valueOf("0")); 
		log.info("x平台查询MrchMtdDao");
		this.MrchMtdDao.insertSelective(mrchMtd);		
	}

	public void synchronousMrchClass(MerchantSDMT info) {
		MrchClass mrchClass=new MrchClass();
		mrchClass.setInstId(Long.valueOf(SysConst.CONST_X_INST_ID));
		mrchClass.setMrchno(info.getMerchantId());
		mrchClass.setClassify("2");
		if(Strings.isNullOrEmpty(info.getFmrchno())){
			mrchClass.setFmrchno(info.getMerchantId());
		}else{
			mrchClass.setFmrchno(info.getFmrchno());
		}
		log.info("x平台查询MrchClassDao");
		this.MrchClassDao.insertSelective(mrchClass);
	}

}
