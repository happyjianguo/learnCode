package cn.yufu.bak.service;

import java.util.List;

public interface MerchantService {
	
	/**
	 * 根据商户号与商户名匹配商户状态
	 * @author Administrator
	 *
	 */
	public List<Integer> getMrchstat(String mrchno);
}
