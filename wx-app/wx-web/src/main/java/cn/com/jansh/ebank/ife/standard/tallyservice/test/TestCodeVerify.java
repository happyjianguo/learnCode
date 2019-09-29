/**
 * TestCodeVerify.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.standard.tallyservice.test;

import cn.com.jansh.ebank.ife.standard.tallyservice.TallyService;
import cn.com.jansh.ebank.ife.standard.tallyservice.common.TradeConstDefine.TradeType;
import cn.com.jansh.ebank.ife.ibank.entity.DocSet;

/**
 * 核心接口调用调试程序 
 * @author YangFan
 * @version 1.0
 */
public class TestCodeVerify extends TallyService{
	public static void getHostResult(DocSet docset){
		TallyService tallyserver = new TallyService();
		int result = -1;
		try {
//			result = tallyserver.tallyTrade(docset,TradeType.doDebitBalTally);//借记卡账户余额查询
//			result = tallyserver.tallyTrade(docset,TradeType.doAccountVerTally);//借记卡账户验证
//			result = tallyserver.tallyTrade(docset,TradeType.doAccountIntTally);//借记卡账户积分
//			result = tallyserver.tallyTrade(docset,TradeType.doDebitDetilTally);//借记卡账户明细查询
//			result = tallyserver.tallyTrade(docset,TradeType.doCreditBillTally);//信用卡账单查询
			result = tallyserver.tallyTrade(docset,TradeType.doCreditConsumTally);//信用卡消费明细查询
			if(result==1){
				System.out.println("交易成功！"+docset.getHostCode()+"|"+docset.getHostMsg());
			}else if(result ==0){
				System.out.println("交易失败！"+" 失败原因："+docset.getHostCode()+"|"+docset.getHostMsg());
			}else{
				System.out.println("交易失败！"+" 失败原因：未知.......");
			}
		} catch (Exception e) {
			System.out.println("请求交易发送异常...");
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]){
		DocSet docset = new DocSet();
		docset.setBusinessId("1111");
		docset.setTallyId("3111");
		docset.setMsgCd("WX.001.001.01");
		docset.setAccNo("4290061985064612");
		
		docset.setTranTyp("1");
		docset.setSRFlg("2");
		docset.setChkIoFlg("0");
		docset.setChkNmFlg("0");
		docset.setChkIDFlg("0");
		docset.setDrAcc("198505230313");
		docset.setDrAccNm("守护天使");
		docset.setDrCertNo("429006198787478475");
		
		docset.setAccTyp("1");
		docset.setAccNm("jansh");
		docset.setBeginDate("20150806");
		docset.setEndDate("20150806");
		docset.setFindDate("20150806");
		docset.setPhoneNm("13466679405");
		
		getHostResult(docset);
	}

	
	
	

}
