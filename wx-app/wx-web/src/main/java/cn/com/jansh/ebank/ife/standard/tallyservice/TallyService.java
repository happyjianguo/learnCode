/**
 * TallyService.java
 * 版权所有(C) 2015 浙江宇信班克信息技术有限公司
 * 创建:YangFan 2015-6-5
 */
package cn.com.jansh.ebank.ife.standard.tallyservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.com.jansh.ebank.ife.ibank.common.TradeSet;
import cn.com.jansh.ebank.ife.ibank.core.CBank;
import cn.com.jansh.ebank.ife.ibank.entity.DocSet;
import cn.com.jansh.ebank.ife.standard.tallyservice.common.ITallyService;
import cn.com.jansh.ebank.ife.standard.tallyservice.common.TradeConstDefine.TradeResult;
import cn.com.jansh.ebank.ife.standard.tallyservice.common.TradeConstDefine.TradeType;


/**
 * 核心交易服务
 * @author YangFan 
 * @version 1.0
 */
public class TallyService implements ITallyService{
	private static final Logger logger = LogManager.getLogger(TallyService.class);
	private DocSet docSet ;

	/**
	 * 账务交易服务
	 * @param object 需要传递的参数 一般为DOCSET
	 * @param type 交易类型
	 * @return 交易返回值(1成功，0失败)
	 * @throws Exception 
	 */
	public int tallyTrade(Object object,TradeType type) throws Exception{
		//模块划分处理
		System.out.println("请求的交易模板类型："+type.getValue());
		logger.info("请求的交易模板类型："+type.getValue());
		docSet = (DocSet) object;
		/*模拟docid*/
		docSet.setBusinessId("123123123");
		switch (type) {
		case doAccountVerTally:
			return doAccountVerTally(docSet);
		case doAccountIntTally:
			return doAccountIntTally(docSet);
		case doDebitBalTally:
			return doDebitBalTally(docSet);
		case doDebitDetilTally:
			return doDebitDetilTally(docSet);
		case doCreditBillTally:
			return doCreditBillTally(docSet);
		case doCreditConsumTally:
			return doCreditConsumTally(docSet);
		default:
			return TradeResult.OTHERREASON;
		}
	}
	
	//账户验证查询
	private int doAccountVerTally(DocSet docSet) throws Exception{
		TradeSet tradeSet = new TradeSet("1110");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("TRANTYP", docSet.getTranTyp());
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("CHKIOFLG", docSet.getChkIoFlg());
		tradeSet.put("CHKNMFLG", docSet.getChkNmFlg());
		tradeSet.put("CHKIDFLG", docSet.getChkIDFlg());
		tradeSet.put("DRACC", docSet.getDrAcc());
		tradeSet.put("DRACCNM", docSet.getDrAccNm());
		tradeSet.put("DRCERTNO", docSet.getDrCertNo());
		tradeSet.put("CRACC", docSet.getCrAcc());
		tradeSet.put("CRACCNM", docSet.getCrAccNm());
		tradeSet.put("CRCERTNO", docSet.getCrCertNo());
		tradeSet.put("PHONENM", docSet.getPhoneNm());
		
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	
	//账户积分查询
	private int doAccountIntTally(DocSet docSet) throws Exception{
		TradeSet tradeSet = new TradeSet("1111");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("TRANTYP", docSet.getTranTyp());
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("DRACC", docSet.getDrAcc());
		tradeSet.put("DRACCNM", docSet.getDrAccNm());
		tradeSet.put("CRACC", docSet.getCrAcc());
		tradeSet.put("CRACCNM", docSet.getCrAccNm());
		
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	
	//借记卡账户余额查询
	private int doDebitBalTally(DocSet docSet) throws Exception{
		//开始加载2110模板
		TradeSet tradeSet = new TradeSet("2110");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("ACCTYP", docSet.getAccTyp());
		tradeSet.put("ACCNO", docSet.getAccNo());
		tradeSet.put("CHKNMFLG", docSet.getChkNmFlg());
		tradeSet.put("ACCNM", docSet.getAccNm());
		
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	
	//借记卡账户明细查询
	private int doDebitDetilTally(DocSet docSet) throws Exception{
		TradeSet tradeSet = new TradeSet("2111");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("ACCTYP", docSet.getAccTyp());
		tradeSet.put("ACCNO", docSet.getAccNo());
		tradeSet.put("CHKNMFLG", docSet.getChkNmFlg());
		tradeSet.put("ACCNM", docSet.getAccNm());
		tradeSet.put("BEGINDATE", docSet.getBeginDate());
		tradeSet.put("ENDDATE", docSet.getEndDate());
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	
	//信用卡账单查询
	private int doCreditBillTally(DocSet docSet) throws Exception{
		TradeSet tradeSet = new TradeSet("3110");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("ACCTYP", docSet.getAccTyp());
		tradeSet.put("ACCNO", docSet.getAccNo());
		tradeSet.put("FINDDATE", docSet.getFindDate());
		
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	
	//信用卡消费查询
	private int doCreditConsumTally(DocSet docSet) throws Exception{
		TradeSet tradeSet = new TradeSet("3111");
		tradeSet.put("DEBUG", "true");
		tradeSet.put("BUSINESSID", docSet.getBusinessId());
		tradeSet.put("MSGCD", docSet.getMsgCd());
		tradeSet.put("TRADECODE", docSet.getTallyId());
		
		tradeSet.put("SRFLG", docSet.getSRFlg());
		tradeSet.put("ACCTYP", docSet.getAccTyp());
		tradeSet.put("ACCNO", docSet.getAccNo());
		tradeSet.put("BEGINDATE", docSet.getBeginDate());
		tradeSet.put("ENDDATE", docSet.getEndDate());
		
		
		//发起原子交易
		int tradeResult = trade(tradeSet);
		if (tradeResult == 1) {
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.SUCCESS;
		}else if(tradeResult == 0){
			docSet.setHostCode(tradeSet.getHostCode());
			docSet.setHostMsg(tradeSet.getHostMsg());
			return TradeResult.FAIL;
		}
		return TradeResult.OTHERREASON;
	}
	

	/**
	 * 原子交易（调用核心IBANK组件）
	 * @param tradeSet 交易对象
	 * @param errorMessage 错误信息
	 * @return  交易成功--TradeResult.SUCCESS， 交易失败--TradeResult.FAIL
	 * @throws Exception
	 */
	private int trade(TradeSet tradeSet) throws Exception{
		TradeSet tradeSetBack = null;
		CBank bank = new CBank();
		try {
			//核心账务登记
			String CLTSEQNO = "111";
			if(CLTSEQNO!=null && CLTSEQNO.length()>0){
				tradeSet.put("CLTSEQNO", CLTSEQNO);//上传本地流水
				tradeSetBack = bank.execTrade(tradeSet);
				tradeSet.put("hostSeqNo", tradeSetBack.get("hostSeqNo"));
				tradeSet.setHostCode(tradeSetBack.getHostCode());
				tradeSet.setHostMsg(tradeSetBack.getHostMsg());
				if(tradeSetBack.getTradeResult()){
					return TradeResult.SUCCESS;
				}else{
					return TradeResult.FAIL;
				}
			}else{
				return TradeResult.OTHERREASON;
			}
		} catch (Exception e) {
			logger.error("与账务接口交易失败:"+e.getMessage());
			throw new Exception("与账务接口交易失败!",e);
		}
	}
	
}
