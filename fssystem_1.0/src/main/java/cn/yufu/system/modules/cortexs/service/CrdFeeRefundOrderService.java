package cn.yufu.system.modules.cortexs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdFeeRefundOrderDao;
import cn.yufu.system.modules.cortexs.entity.CrdFeeRefundOrder;

/**
 * 新福卡退款Service接口
 * @author ZQK
 * @version 2018-06-29
 */
@Service
@Transactional(readOnly = true)
public class CrdFeeRefundOrderService extends CrudService<CrdFeeRefundOrderDao, CrdFeeRefundOrder>{
	
	@Autowired
	private CrdFeeRefundOrderDao crdFeeRefundOrderDao;
	
	public CrdFeeRefundOrder get(CrdFeeRefundOrder crdFeeRefundOrder) {
		return super.get(crdFeeRefundOrder);
	}
	
	public Page<CrdFeeRefundOrder> findPage(Page<CrdFeeRefundOrder> page, CrdFeeRefundOrder crdFeeRefundOrder){
		return super.findPage(page, crdFeeRefundOrder);
	}
	
	public List<CrdFeeRefundOrder> findList(CrdFeeRefundOrder crdFeeRefundOrder){
		return super.findList(crdFeeRefundOrder);
	}
	
	public BigDecimal getRefundSum(CrdFeeRefundOrder crdFeeRefundOrder) {
		return crdFeeRefundOrderDao.getRefundSum(crdFeeRefundOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdFeeRefundOrder crdFeeRefundOrder) {
		super.delete(crdFeeRefundOrder);
	};
	
	@Transactional(readOnly = false)
	public void save(CrdFeeRefundOrder crdFeeRefundOrder){
		super.save(crdFeeRefundOrder);
	}
	
}
