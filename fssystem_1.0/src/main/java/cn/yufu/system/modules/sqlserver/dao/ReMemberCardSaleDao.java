package cn.yufu.system.modules.sqlserver.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.sqlserver.entity.ReMemberCardSale;

/**
 * 老福卡管理费退款功能
 * @author ZQK
 * @version 2018-06-25
 */
@MyBatisDao
public interface ReMemberCardSaleDao extends CrudDao<ReMemberCardSale> {
	
	public ReMemberCardSale getRefuntAmt(ReMemberCardSale reMemberCardSale);
	
}