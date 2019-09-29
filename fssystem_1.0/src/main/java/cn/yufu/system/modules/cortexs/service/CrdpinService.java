package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.Crdpin;
import cn.yufu.system.modules.cortexs.dao.CrdpinDao;

/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class CrdpinService extends CrudService<CrdpinDao, Crdpin> {

	@Autowired
	private CrdpinDao crdpinDao;
	
	public Crdpin get(String id) {
		return super.get(id);
	}
	
	public List<Crdpin> findList(Crdpin tTranType) {
		return super.findList(tTranType);
	}
	
	public Page<Crdpin> findPage(Page<Crdpin> page, Crdpin tTranType) {
		return super.findPage(page, tTranType);
	}
	
	@Transactional(readOnly = false)
	public void save(Crdpin tTranType) {
		/*if(this.get(tTranType)==null){
			tTranType.setIsNewRecord(true);
		}
		super.save(tTranType);*/
		
		crdpinDao.crdpinUpdate(tTranType);
	}
	
	@Transactional(readOnly = false)
	public void delete(Crdpin tTranType) {
		super.delete(tTranType);
	}
	
}