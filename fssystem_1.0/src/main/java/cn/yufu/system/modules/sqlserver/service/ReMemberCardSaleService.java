package cn.yufu.system.modules.sqlserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.sqlserver.dao.ReMemberCardSaleDao;
import cn.yufu.system.modules.sqlserver.entity.ReMemberCardSale;

/**
 * 老福卡管理费退款功能
 * @author ZQK
 * @version 2018-06-25
 */
@Service
@Transactional(readOnly = true)
public class ReMemberCardSaleService extends CrudService<ReMemberCardSaleDao, ReMemberCardSale>{
	
	@Autowired
	private ReMemberCardSaleDao reMemberCardSaleDao;
	
	public ReMemberCardSale get(ReMemberCardSale reMemberCardSale) {
		return super.get(reMemberCardSale);
	}
	
	public Page<ReMemberCardSale> findPage(Page<ReMemberCardSale> page, ReMemberCardSale reMemberCardSale){
		page.setJdbcType("SQLServer");
		return super.findPage(page, reMemberCardSale);
	}
	
	public List<ReMemberCardSale> findList(ReMemberCardSale reMemberCardSale){
		return super.findList(reMemberCardSale);
	}
	
	public ReMemberCardSale getRefuntAmt(ReMemberCardSale reMemberCardSale) {
		return reMemberCardSaleDao.getRefuntAmt(reMemberCardSale);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReMemberCardSale reMemberCardSale) {
		super.delete(reMemberCardSale);
	};
	
	@Transactional(readOnly = false)
	public void save(ReMemberCardSale reMemberCardSale){
		super.save(reMemberCardSale);
	}
	
}
