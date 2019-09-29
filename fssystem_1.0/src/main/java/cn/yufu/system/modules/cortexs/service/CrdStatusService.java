package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.CrdStatus;
import cn.yufu.system.modules.cortexs.dao.CrdStatusDao;

/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class CrdStatusService extends CrudService<CrdStatusDao, CrdStatus> {

	public CrdStatus get(String id) {
		return super.get(id);
	}
	
	public List<CrdStatus> findList(CrdStatus tTranType) {
		return super.findList(tTranType);
	}
	
	public Page<CrdStatus> findPage(Page<CrdStatus> page, CrdStatus tTranType) {
		return super.findPage(page, tTranType);
	}
	
	@Transactional(readOnly = false)
	public void save(CrdStatus tTranType) {
		if(this.get(tTranType)==null){
			tTranType.setIsNewRecord(true);
		}
		super.save(tTranType);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdStatus tTranType) {
		super.delete(tTranType);
	}
	
}