package cn.yufu.posp.merchant.domain.logic;

public interface MerchantService {

	/**
	 * 同步商户基本信息
	 * 
	 * @param userId
	 * @return
	 */
	public String syncMerchant(String jsonString);

	/**
	 * 同步商户终端信息
	 * 
	 * @param jsonString
	 * @return
	 */
	public String syncEdcTerminal(String jsonString);
}
