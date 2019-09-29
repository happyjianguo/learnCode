package cn.yufu.system.modules.cortexs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeelogBeforeDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeelogBefore;

/**
 * 扣款明细预收Service
 * @author ZQK
 * @version 2017-11-08
 */
@Service
@Transactional(readOnly = true)
public class CrdfeelogBeforeService extends CrudService<CrdfeelogBeforeDao, CrdfeelogBefore>{
	
	@Autowired
	private CrdfeelogBeforeDao crdfeelogBeforeDao;
	
	public CrdfeelogBefore get(String id) {
		return super.get(id);
	}
	
	public List<CrdfeelogBefore> findList(CrdfeelogBefore CrdfeelogBefore) {
		return super.findList(CrdfeelogBefore);
	}
	
	public Page<CrdfeelogBefore> findPage(Page<CrdfeelogBefore> page, CrdfeelogBefore CrdfeelogBefore){
		return super.findPage(page, CrdfeelogBefore);
	}
	
	public BigDecimal getFeeSum(CrdfeelogBefore CrdfeelogBefore) {
		return crdfeelogBeforeDao.getFeeSum(CrdfeelogBefore);
	}
	
	@Transactional(readOnly = false)
	public void save(CrdfeelogBefore CrdfeelogBefore){
		crdfeelogBeforeDao.insert(CrdfeelogBefore);
	}
	
}
