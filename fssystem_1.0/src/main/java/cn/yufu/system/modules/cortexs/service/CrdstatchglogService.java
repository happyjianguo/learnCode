package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.Crdstatchglog;
import cn.yufu.system.modules.cortexs.dao.CrdstatchglogDao;

/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class CrdstatchglogService extends CrudService<CrdstatchglogDao, Crdstatchglog> {

	public Crdstatchglog get(String id) {
		return super.get(id);
	}
	
	public List<Crdstatchglog> findList(Crdstatchglog tTranType) {
		return super.findList(tTranType);
	}
	
	public Page<Crdstatchglog> findPage(Page<Crdstatchglog> page, Crdstatchglog tTranType) {
		return super.findPage(page, tTranType);
	}
	
	@Transactional(readOnly = false)
	public void save(Crdstatchglog tTranType) {
		if(this.get(tTranType)==null){
			tTranType.setIsNewRecord(true);
		}
		super.save(tTranType);
	}
	
	@Transactional(readOnly = false)
	public void delete(Crdstatchglog tTranType) {
		super.delete(tTranType);
	}
	
}