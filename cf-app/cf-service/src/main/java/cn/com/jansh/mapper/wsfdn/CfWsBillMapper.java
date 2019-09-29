package cn.com.jansh.mapper.wsfdn;

import java.util.List;



import cn.com.jansh.entity.wsfdn.CfWsbillEntity;

public interface CfWsBillMapper {

	/**
	 * 网宿对账查询
	 * @param cfWsbillEntity
	 * @return
	 */
	public List<CfWsbillEntity> querywsBill(CfWsbillEntity cfWsbillEntity);
	
	/**
	 * 插入批量网宿(批量插入)
	 * @param cfWsbillEntity
	 */
	public void insertWsbillinfo(List<CfWsbillEntity> cfWsbillList);
	
	/**
	 * 查询订单编号为spno的订单
	 * @param spno
	 * @return
	 */
	public CfWsbillEntity selectByspno(String spno);		
}
