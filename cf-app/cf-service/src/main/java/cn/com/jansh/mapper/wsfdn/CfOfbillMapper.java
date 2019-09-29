package cn.com.jansh.mapper.wsfdn;


import cn.com.jansh.entity.wsfdn.CfOfbillEntity;

public interface CfOfbillMapper {
	/**
	 * 插入欧非对账信息
	 * 
	 */
	public void insert(CfOfbillEntity cfOfbillEntity);
	  /**
	   * 查询订单编号为spno和流水号为cpno的订单
	   */
	public CfOfbillEntity selectBySpno(String spno);	
}
