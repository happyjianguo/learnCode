package cn.yufu.SDMTPlatform.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.commons.utils.StringUtil;
import cn.yufu.SDMTPlatform.dao.MerchantSDMTMapper;
import cn.yufu.SDMTPlatform.dao.TerminalSDMTMapper;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.MerchantSDMTExample;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTExample;
import cn.yufu.cortex.dao.EnckeyMapper;
import cn.yufu.cortex.entity.MerchantXRecord;
import cn.yufu.posp.dao.MerchantBaseMapper;
import cn.yufu.posp.dao.MerchantExtraMapper;
import cn.yufu.posp.dao.MerchantIdentityMapper;
import cn.yufu.posp.dao.MerchantRefundMapper;
import cn.yufu.posp.dao.MerchantXMapper;
import cn.yufu.posp.entity.MerchantBase;
import cn.yufu.posp.entity.MerchantExtra;
import cn.yufu.posp.entity.MerchantIdentity;
import cn.yufu.posp.entity.MerchantRefund;
import cn.yufu.posp.entity.MerchantX;

@Service("sdmtpf.MerchantSDMTService")
public class MerchantSDMTServiceImpl implements MerchantSDMTService {
	Log log = Log.getLog(MerchantSDMTServiceImpl.class);

	@Autowired
	@Qualifier("sdmtpf.MerchantSDMTDao")
	private MerchantSDMTMapper MerchantSDMTDao;
	
	@Autowired
	@Qualifier("posp.MerchantXDao")
	private MerchantXMapper MerchantXDao;
	@Autowired
	@Qualifier("posp.MerchantBaseDao")
	private MerchantBaseMapper MerchantBaseDao;
	@Autowired
	@Qualifier("posp.MerchantExtraDao")
	private MerchantExtraMapper MerchantExtraDao;
	@Autowired
	@Qualifier("posp.MerchantRefundDao")
	private MerchantRefundMapper MerchantRefundDao;	
	@Autowired
	@Qualifier("posp.MerchantIdentityDao")
	private MerchantIdentityMapper MerchantIdentityDao;
	@Autowired
	@Qualifier("cortex.EnckeyDao")
	private EnckeyMapper EnckeyDao;
	@Autowired
	@Qualifier("sdmtpf.TerminalSDMTDao")
	private TerminalSDMTMapper TerminalSDMTDao;
	
	public int queryCnt(MerchantSDMT queryModel) {
		MerchantSDMTExample example = new MerchantSDMTExample();
		MerchantSDMTExample.Criteria criteria = example.createCriteria();
		String flag = queryModel.getIsTerminalAddFlag();
		if (!StringUtil.isEmpty(flag)){
			if("1".equals(flag)){
				criteria.andisTerminalAddFlagEqualTo(flag);
			}
		}
		if (!StringUtil.isEmpty(queryModel.getMerchantId()))
			criteria.andMerchantIdEqualTo(queryModel.getMerchantId());
		if (!StringUtil.isEmpty(queryModel.getMrchtName()))
			criteria.andMrchtNameLike("%"+queryModel.getMrchtName()+"%");
		if (queryModel.getAddDate()!=null)
			criteria.andAddDateGreaterThanOrEqualTo(Long.parseLong(queryModel.getAddDate()+"000000"));	
		if (queryModel.getAddDateEnd()!=null)
			criteria.andAddDateEndLessThanOrEqualTo(Long.parseLong(queryModel.getAddDateEnd()+"235959"));
		if (!StringUtil.isEmpty(queryModel.getAddress()))
			criteria.andAddressLike("%"+queryModel.getAddress()+"%");
		if (!StringUtil.isEmpty(queryModel.getManager()))
			criteria.andManagerLike("%"+queryModel.getManager()+"%");
		if (!StringUtil.isEmpty(queryModel.getSdFlag()))
			criteria.andSdFlagEqualTo(queryModel.getSdFlag());
		if (!StringUtil.isEmpty(queryModel.getxFlag()))
			criteria.andXFlagEqualTo(queryModel.getxFlag());		
		if (!StringUtil.isEmpty(queryModel.getYufuFlag()))
			criteria.andYufuFlagEqualTo(queryModel.getYufuFlag());
		
		return MerchantSDMTDao.countByExample(example);
	}

	public List<MerchantSDMT> queryList(MerchantSDMT queryModel, int startResult, int endResult) {
		MerchantSDMTExample example = new MerchantSDMTExample();
		MerchantSDMTExample.Criteria criteria = example.createCriteria();
		String flag = queryModel.getIsTerminalAddFlag();
		if (!StringUtil.isEmpty(flag)){
			if("1".equals(flag)){
				criteria.andisTerminalAddFlagEqualTo(flag);
			}
		}
		if (!StringUtil.isEmpty(queryModel.getMerchantId()))
			criteria.andMerchantIdEqualTo(queryModel.getMerchantId());
		if (!StringUtil.isEmpty(queryModel.getMrchtName()))
			criteria.andMrchtNameLike("%"+queryModel.getMrchtName()+"%");
		if (queryModel.getAddDate()!=null)
			criteria.andAddDateGreaterThanOrEqualTo(Long.parseLong(queryModel.getAddDate()+"000000"));	
		if (queryModel.getAddDateEnd()!=null)
			criteria.andAddDateEndLessThanOrEqualTo(Long.parseLong(queryModel.getAddDateEnd()+"235959"));
		if (!StringUtil.isEmpty(queryModel.getAddress()))
			criteria.andAddressLike("%"+queryModel.getAddress()+"%");
		if (!StringUtil.isEmpty(queryModel.getManager()))
			criteria.andManagerLike("%"+queryModel.getManager()+"%");
		if (!StringUtil.isEmpty(queryModel.getSdFlag()))
			criteria.andSdFlagEqualTo(queryModel.getSdFlag());
		if (!StringUtil.isEmpty(queryModel.getxFlag()))
			criteria.andXFlagEqualTo(queryModel.getxFlag());		
		if (!StringUtil.isEmpty(queryModel.getYufuFlag()))
			criteria.andYufuFlagEqualTo(queryModel.getYufuFlag());
		example.setOrderByClause("ADD_DATE DESC");
		return MerchantSDMTDao.selectPageByExample(example, startResult, endResult);
	}

	public MerchantSDMT queryInfo(String merchantId) {
		return MerchantSDMTDao.selectByPrimaryKey(merchantId);
	}
	
	public int UpdateMerchantSDMT(MerchantSDMT record){
		return MerchantSDMTDao.updateByPrimaryKey(record);		
	}
	//添加终端是添加商户
	public Map<String, Object> saveMerchantAndTermial(MerchantSDMT info, MerchantXRecord merchantXRecord,boolean b,TerminalSDMT infos) {
		log.debug("同时添加商户和终端");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
//		map.put(SysConst.RESULT_MSG, "商户保存成功。");
		map.put(SysConst.RESULT_MSG, "终端和商户保存成功。");
		boolean saveOk = true;// 逻辑通过可保存
		MerchantSDMTExample example = new MerchantSDMTExample();
		MerchantSDMTExample.Criteria criteria = example.createCriteria();
		criteria.andMerchantIdEqualTo(info.getMerchantId());
		List<MerchantSDMT> merchantList = MerchantSDMTDao.selectByExample(example);
		log.info("判断商户编号重复");
		if (merchantList.size() > 0) {
			// 重复 （发起正常交易、如果存在则重复，撤销不判断）
			saveOk = false;// 异常
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "商户编号重复。");
			return map;
		}
		log.info("判断终端编号重复");
		if (checkTerminal(infos.getMerchantId(),infos.getTermCode()) > 0) {
			// 重复 （发起正常交易、如果存在则重复，撤销不判断）
			saveOk = false;// 异常
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "终端编号重复。");
			return map;
		}
		if (saveOk) {
			log.info("插入商户");
			MerchantSDMTDao.insertSelective(info);
			if(b){
				extendEdit(info,merchantXRecord);
			}
			log.info("插入终端");
			TerminalSDMTDao.insertSelective(infos);
		}
		return map;
	
	}
	public int checkTerminal(String merchantId,String termCode){
		TerminalSDMTExample example = new TerminalSDMTExample();
		TerminalSDMTExample.Criteria criteria = example.createCriteria();
		//criteria.andMerchantIdEqualTo(merchantId);
		criteria.andTermCodeEqualTo(termCode);
		List<TerminalSDMT> TerminalList = TerminalSDMTDao.selectByExample(example);
		return TerminalList.size();
	}
	public Map<String, Object> save(MerchantSDMT info, MerchantXRecord merchantXRecord,boolean b) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "商户保存成功。");
		boolean saveOk = true;// 逻辑通过可保存
		MerchantSDMTExample example = new MerchantSDMTExample();
		MerchantSDMTExample.Criteria criteria = example.createCriteria();
		criteria.andMerchantIdEqualTo(info.getMerchantId());
		List<MerchantSDMT> merchantList = MerchantSDMTDao.selectByExample(example);
		if (merchantList.size() > 0) {
			// 重复 （发起正常交易、如果存在则重复，撤销不判断）
			saveOk = false;// 异常
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "商户编号重复。");
			return map;
		}
		
		if (saveOk) {
			MerchantSDMTDao.insertSelective(info);
			if(b){
				extendEdit(info,merchantXRecord);
			}
		}
		return map;
	}
	
	public Map<String, Object> edit(MerchantSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "商户保存成功。");
		MerchantSDMTDao.updateByPrimaryKeySelective(info);
		return map;
	}
	
	public Map<String, Object> extendEdit(MerchantSDMT info,MerchantXRecord merchantXRecord) {
		log.info("修改商户MERCHANT_BASE");
		//商户基础信息表 MERCHANT_BASE
		this.changeMerchantBase(info);
		log.info("修改商户MERCHANT_EXTRA");
		//MERCHANT_EXTRA
		this.changeMerchantExtra(info);
		log.info("修改商户MERCHANT_IDENTITY");
		//MERCHANT_IDENTITY
		this.changeMerchantIdentity(info);
		log.info("修改商户MERCHANT_REFUND");
		//MERCHANT_REFUND
		this.changeMerchantRefund(info);
		log.info("修改商户MERCHANT_X");
		//商户扩展信息 MERCHANT_X
		this.changeMerchantX(info);
		
		//更新中间表
//		info.setIsTerminalAddFlag(" ");
		MerchantSDMTDao.updateByPrimaryKeySelective(info);
		
		if(null !=merchantXRecord){
			log.debug("修改商户记录日志");		
			EnckeyDao.saveEditMerchantLog(merchantXRecord);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "商户保存成功。");
		return map;
	}
	
	/**
	 * MERCHANT_REFUND
	 * @param info
	 */
	public void changeMerchantRefund(MerchantSDMT info) {
		MerchantRefund merchantRefund=new MerchantRefund();		
		merchantRefund.setMerchantId(info.getMerchantId());
		merchantRefund.setRefundLimit(Double.valueOf("0"));
		merchantRefund.setRefundCheck("N");
		MerchantRefund MerchantRefund = this.MerchantRefundDao.selectByMerchantId(merchantRefund);
		if(null == MerchantRefund){
			this.MerchantRefundDao.insertSelective(merchantRefund);
		}else{
			this.MerchantRefundDao.updateSelective(merchantRefund);
		}
	}

	/**
	 * MERCHANT_IDENTITY
	 * @param info
	 */
	public void changeMerchantIdentity(MerchantSDMT info){
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
		}else{
			this.MerchantIdentityDao.updateSelective(merchantIdentity);
		}
	}

	/**
	 * MERCHANT_EXTRA
	 * @param info
	 */
	public void changeMerchantExtra(MerchantSDMT info) {
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
			this.MerchantExtraDao.insertSelective(merchantExtra);
		}else{
			this.MerchantExtraDao.updateSelective(merchantExtra);
		}
	}
	/**
	 * 商户基础信息表 
	 * MERCHANT_BASE
	 * @param info
	 */
	public void changeMerchantBase(MerchantSDMT info){
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
			this.MerchantBaseDao.insertSelective(merchantBase);
		}else{
			this.MerchantBaseDao.updateSelective(merchantBase);
		}
	}
	/**
	 * 商户扩展信息
	 * MERCHANT_X
	 * @param info
	 */
	public void changeMerchantX(MerchantSDMT info) {
		MerchantX merchantX = new MerchantX();
		merchantX.setContractStartDate(info.getContractStartDate());
		merchantX.setContractRenewalDate(info.getContractRenewalDate());
		merchantX.setMerchantCompanyName(info.getMerchantCompanyName());
		merchantX.setRenewalType(info.getRenewalType());
		merchantX.setGuaranteeLastDate(info.getGuaranteeLastDate());
		merchantX.setGuaranteeAmt(info.getGuaranteeAmt());
		merchantX.setMarketContactPerson(info.getMarketContactPerson());
		merchantX.setMarketContactMobile(info.getMarketContactMobile());
		merchantX.setMerchantDeposit(info.getMerchantDeposit());
		merchantX.setContractEndDate(info.getContractEndDate());
		merchantX.setMerchantArea(info.getMerchantArea());
		merchantX.setStoreManager(info.getStoreManager());
		merchantX.setMerchantAdvisor(info.getMerchantAdvisor());
		merchantX.setMerchantId(info.getMerchantId());//商户编号
		merchantX.setTypeYf(info.getTypeYf());
		merchantX.setMerchantCname(info.getMrchtName());//商户名称 MERCHANT_CNAME
		merchantX.setAddressChn(info.getAddress());//地址 ADDRESS_CHN
		merchantX.setRcvName(info.getAccName());//结算账户开户名RCV_NAME
		merchantX.setRcvBank(info.getBankName());//开户银行名称RCV_BANK
		merchantX.setManName(info.getManName());
		merchantX.setIdType1(info.getIdType1());
		merchantX.setIdNo1(info.getIdNo1());
		merchantX.setIdDeadline1(info.getIdDeadline1());
		merchantX.setAccXName(info.getAccXName());
		Long lo = info.getAddDate();
		Date date = new Date();
		try {
			date = DateUtil.getDateFromString(lo+"","yyyyMMddHHmmss");
		} catch (ParseException e) {
			log.debug("时间转换异常", e);
		}
		merchantX.setCreateDate(date);
		merchantX.setUpdateDate(new Date());
		merchantX.setContractEndDate(info.getContractEndDate());
		MerchantX merchant = MerchantXDao.selectByid(merchantX);
		if(null != merchant){
			this.MerchantXDao.update(merchantX);
		}else{
			this.MerchantXDao.insertSelective(merchantX);
		}
	}
	
	public int checkMerchantId(String merchantId){
		MerchantSDMTExample example = new MerchantSDMTExample();
		MerchantSDMTExample.Criteria criteria = example.createCriteria();
		criteria.andMerchantIdEqualTo(merchantId);
		List<MerchantSDMT> MerchantSDMT = MerchantSDMTDao.selectByExample(example);
		return MerchantSDMT.size();
	}
}
