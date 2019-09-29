package cn.yufu.system.modules.cortexs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeelogDao;
import cn.yufu.system.modules.cortexs.entity.Crdfeelog;

/**
 * 扣款明细Service
 * @author ZQK
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class CrdfeelogService extends CrudService<CrdfeelogDao, Crdfeelog>{
	
	@Autowired
	private CrdfeelogDao crdfeelogDao;
	
	public Crdfeelog get(String id) {
		return super.get(id);
	}
	
	public List<Crdfeelog> selectByPrimaryKey(Crdfeelog crdfeelog) {
		return crdfeelogDao.selectByPrimaryKey(crdfeelog);
	}
	
	public List<Crdfeelog> findList(Crdfeelog crdfeelog) {
		return super.findList(crdfeelog);
	}
	
	public Page<Crdfeelog> findPage(Page<Crdfeelog> page, Crdfeelog crdfeelog){
		return super.findPage(page, crdfeelog);
	}
	
	public BigDecimal getConsumeSum(Crdfeelog crdfeelog) {
		return crdfeelogDao.getConsumeSum(crdfeelog);
	}
	
	public BigDecimal getRefundSum(Crdfeelog crdfeelog) {
		return crdfeelogDao.getRefundSum(crdfeelog);
	}
	
	@Transactional(readOnly = false)
	public void save(Crdfeelog crdfeelog){
		crdfeelogDao.insert(crdfeelog);
	}
	
	@Transactional(readOnly = false)
	public void refund(Crdfeelog crdfeelog){
		crdfeelogDao.refund(crdfeelog);
	}
	
}
