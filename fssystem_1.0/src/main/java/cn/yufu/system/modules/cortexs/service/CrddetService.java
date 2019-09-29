package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.Crddet;
import cn.yufu.system.modules.cortexs.dao.CrddetDao;

/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class CrddetService extends CrudService<CrddetDao, Crddet> {
	
	@Autowired
	private CrddetDao crddetDao;


	public Crddet get(String id) {
		return super.get(id);
	}
	
	public List<Crddet> findList(Crddet tTranType) {
		return super.findList(tTranType);
	}
	
	public Page<Crddet> findPage(Page<Crddet> page, Crddet tTranType) {
		return super.findPage(page, tTranType);
	}
	
	@Transactional(readOnly = false)
	public void save(Crddet tTranType) {
		/*if(this.get(tTranType)==null){
			tTranType.setIsNewRecord(true);
		}
		super.save(tTranType);*/
		
		crddetDao.crddetUpdate(tTranType);
	}
	
	/*@Transactional(readOnly = false)
	public void save(CAccount cAccount) {
		cAccount.preUpdate();
		cAccountDao.cAccountSave(cAccount);
	}*/
	
	@Transactional(readOnly = false)
	public void delete(Crddet tTranType) {
		super.delete(tTranType);
	}
	
}