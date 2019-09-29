/**
 * Simulationhost.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-8-5
 */
package cn.com.jansh.ebank.ife.ibank;

import cn.com.jansh.ebank.ife.ibank.common.TradeSet;

/**
 * 模拟主机交易
 * @author YangFan
 * @version 1.0.0
 */
public class Simulationhost {
	
	protected static boolean resultTy = true;
	protected static String hostCode = "000000";
	protected static String hostErrCode = "111111";
	protected static String errMsg = "查询无记录";

	/**
	 * 
	 * 模拟主机交易2110
	 * 账户余额查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData2110(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		//<hostSeqNo>123</><hostCode>000000</><hostMsg>889.20</>
		if(resultTy){
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(buildRandom(3)+".00");
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostErrCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	/**
	 * 
	 * 模拟主机交易1110
	 * 账户验证查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData1110(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		String DrAcc = tradeSet.get("DRACC")+"";
		String CrAcc = tradeSet.get("CrAcc")+"";
		if(DrAcc!=null && DrAcc.length()>0){
			if(DrAcc.substring(0,1).equals("0")){
				hostCode = "111111";
			}else{
				hostCode = "000000";
			}
		}
		if(CrAcc!=null && CrAcc.length()>0){
			if(DrAcc.substring(0,1).equals("0")){
				hostCode = "111111";
			}else{
				hostCode = "000000";
			}
		}
		if(hostCode.equals("000000")){
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append("账户有效");
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	/**
	 * 
	 * 模拟主机交易1111
	 * 账户积分查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData1111(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		if(resultTy){
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(buildRandom(3));
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostErrCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	/**
	 * 
	 * 模拟主机交易2111
	 * 借记卡账户明细查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData2111(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		//<hostSeqNo>123</><hostCode>000000</><hostMsg>20150806#1#3.00#|20150806#1#33.00#|</>
		String BeginDate = tradeSet.get("BEGINDATE")+"";
		String EndDate = tradeSet.get("ENDDATE")+"";
		if(resultTy){
			int numcycle = buildRandom(1);
			StringBuffer msg = new StringBuffer();
			for(int index=0; index<numcycle; index++){
				if(index==0){
					msg.append(BeginDate+"#");
					msg.append("1#");
					msg.append(buildRandom(5)+".00"+"#");
					msg.append("|");
				}else if(index==1){
					msg.append(EndDate+"#");
					msg.append("2#");
					msg.append(buildRandom(2)+".00"+"#");
					msg.append("|");
				}else{
					msg.append(EndDate+"#");
					msg.append("3#");
					msg.append(buildRandom(2)+".00"+"#");
					msg.append("|");
				}
			}
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(msg.toString());
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostErrCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	/**
	 * 
	 * 模拟主机交易3110
	 * 信用卡账单查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData3110(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		//<hostSeqNo>123</><hostCode>000000</><hostMsg>889.20</>
		String FindDate = tradeSet.get("FINDDATE")+"";
		if(resultTy){
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(FindDate+"#");
			result.append(FindDate+"#");
			result.append("人民币/美元账户"+"#");
			result.append(buildRandom(4)+".00"+"#");
			result.append(buildRandom(2)+".00"+"#");
			result.append(buildRandom(3)+"#");
			result.append("|");
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostErrCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	/**
	 * 
	 * 模拟主机交易3111
	 * 信用卡消费查询返回结果
	 * @param tradeSet
	 * @return 核心结果
	 */
	public static String getVmwareData3111(TradeSet tradeSet){
		StringBuffer result = new StringBuffer();
		//<hostSeqNo>123</><hostCode>000000</><hostMsg>889.20</>
		String AccNo = tradeSet.get("ACCNO")+"";
		String BeginDate = tradeSet.get("BEGINDATE")+"";
		String EndDate = tradeSet.get("ENDDATE")+"";
		if(resultTy){
			int numcycle = buildRandom(1);
			StringBuffer msg = new StringBuffer();
			for(int index=0; index<numcycle; index++){
				if(index==0){
					msg.append(BeginDate+"#");
					msg.append(BeginDate+"#");
					msg.append("北京永辉超市有限公司"+"#");
					msg.append(buildRandom(2)+".00"+"#");
					msg.append(AccNo.substring(AccNo.length()-4)+"#");
					msg.append("|");
				}else if(index==1){
					msg.append(EndDate+"#");
					msg.append(EndDate+"#");
					msg.append("北京安达医院"+"#");
					msg.append(buildRandom(3)+".00"+"#");
					msg.append(AccNo.substring(AccNo.length()-4)+"#");
					msg.append("|");
				}else{
					msg.append(EndDate+"#");
					msg.append(EndDate+"#");
					msg.append("BHG(北京)百货有限公司"+"#");
					msg.append(buildRandom(4)+".00"+"#");
					msg.append(AccNo.substring(AccNo.length()-4)+"#");
					msg.append("|");
				}
			}
			
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(msg.toString());
			result.append("</>");
		}else{
			result.append("<hostSeqNo>");
			result.append(buildRandom(2));
			result.append("</>");
			result.append("<hostCode>");
			result.append(hostErrCode);
			result.append("</>");
			result.append("<hostMsg>");
			result.append(errMsg);
			result.append("</>");
		}
		
		return result.toString();
	}
	
	
	
	
	
	
	

	/**
	 * 
	 * 取出一个指定长度大小的随机正整数
	 * @param length
	 * @return
	 */
    public static int buildRandom(int length) {
         int num = 1;
         double random = Math.random();
         if (random < 0.1) {
              random = random + 0.1;
         } for (int i = 0; i < length; i++) {
              num = num * 10;
         }
         return (int) ((random * num));
  }
}
