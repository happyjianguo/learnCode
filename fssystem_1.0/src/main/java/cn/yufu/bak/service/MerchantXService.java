package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.MerchantX;

public interface MerchantXService {
	
	/**
	 * 根据商户名称获取商户号
	 * @author Administrator
	 *
	 */
	public List<String> getMrchno(String mrchtName);
	
	/**
	 * 根据商户号获取商户名称
	 * @author Administrator
	 *
	 */
	public List<String> getMrchtName(String mrchno);
	
	/**
	 * 根据活跃商户号获取非活跃商户信息
	 * @author Administrator
	 *
	 */
	public List<MerchantX> selectPageMrchList(List<String> mrchno, int startResult, int endResult);
	
	/**
	 * 根据活跃商户号获取非活跃商户总数
	 * @author Administrator
	 *
	 */
	public int getLazyMerchantCounts(List<String> mrchno);
	
	/**
	 * 获取商户信息
	 * @author Administrator
	 *
	 */
	public List<MerchantX> selectMrchList();
	
}
