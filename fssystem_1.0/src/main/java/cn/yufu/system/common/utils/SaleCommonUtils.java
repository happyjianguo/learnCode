package cn.yufu.system.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 15位专属卡号生成16位专属卡号
 * 
 * */
public class SaleCommonUtils {
	
	/***** 15位专属卡号生成16位专属卡号-算法 *******/
	public static Map<String,Object> GenCheckDigit(String csCardNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		Boolean flay = false;
		String content = "";
		if(StringUtils.isNotBlank(csCardNo)&&csCardNo.length() == 15){
			if(StringUtils.isNumeric(csCardNo)){
				//pass
				int i, iSum = 0;
				int iLen;
				char csResult[] = {'0','0','0'};
				char[] stringArr = csCardNo.toCharArray(); // 注意返回值是char数组
				iLen = csCardNo.length();
				for (i = iLen - 1; i >= 0; i--) {
					if ((iLen - i) % 2 != 0 && (i != 0)) {
						//将char直接转化为int，其值就是字符的ascii 
						int s = (((byte)(stringArr[i])-(byte)('0'))*2);
						if((s+"").length() == 2){
							csResult=(s+"").toCharArray();
						}else{
							csResult[0] = '0';
							csResult[1] = (s+"").toCharArray()[0];
						}
						
						iSum += csResult[0] - '0';
						iSum += csResult[1] - '0';

					} else {
						iSum += stringArr[i] - '0';
					}

				}
				flay = true;
//				content = csCardNo + (10 - (iSum % 10));
				content = csCardNo + (((10 - (iSum % 10))+"").length() == 2 ? ((10 - (iSum % 10))+"").substring(0, 1) : ((10 - (iSum % 10))+""));
			}else{
				//必须是15位数字
				content = "必须是15位数字";
			}
		}else {
			//必须是15位
			content = "必须是15位数字";
		}
		map.put("flay",flay);
		map.put("content",content);
		return map;
	}
	
	/**
	 * @param cardNo 15位卡号
	 * @return 
	 * 		验证通过，返回16位卡号；否则，返回null
	 * */
	public static String getCardNo(String cardNo){
		Map<String, Object> checkDigit = null;
		if (StringUtils.isNotEmpty(cardNo)) {
			checkDigit = SaleCommonUtils.GenCheckDigit(cardNo);
			if (null == checkDigit) {
				return null;
			}
			boolean object = (boolean)checkDigit.get("flay");
			if (object) {
				return (String)checkDigit.get("content");
			}
		}
		return null;
	}
	
}
