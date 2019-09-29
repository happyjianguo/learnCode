package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.TTranType;
import cn.yufu.system.modules.cortexs.dao.TTranTypeDao;

/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class TTranTypeService extends CrudService<TTranTypeDao, TTranType> {

	public TTranType get(String id) {
		return super.get(id);
	}
	
	public List<TTranType> findList(TTranType tTranType) {
		return super.findList(tTranType);
	}
	
	public Page<TTranType> findPage(Page<TTranType> page, TTranType tTranType) {
		return super.findPage(page, tTranType);
	}
	
	@Transactional(readOnly = false)
	public void save(TTranType tTranType) {
		if(this.get(tTranType)==null){
			tTranType.setIsNewRecord(true);
		}
		super.save(tTranType);
	}
	
	@Transactional(readOnly = false)
	public void delete(TTranType tTranType) {
		super.delete(tTranType);
	}
	
}