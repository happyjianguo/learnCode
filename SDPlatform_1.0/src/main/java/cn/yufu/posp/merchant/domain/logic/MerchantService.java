package cn.yufu.posp.merchant.domain.logic;

public interface MerchantService {

	/**
	 * ͬ���̻�������Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public String syncMerchant(String jsonString);

	/**
	 * ͬ���̻��ն���Ϣ
	 * 
	 * @param jsonString
	 * @return
	 */
	public String syncEdcTerminal(String jsonString);
}
