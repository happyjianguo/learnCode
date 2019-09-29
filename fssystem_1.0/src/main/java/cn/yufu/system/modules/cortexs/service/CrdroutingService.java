package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.Crdrouting;
import cn.yufu.system.modules.cortexs.dao.CrdroutingDao;

/**
 * 卡BIN信息Service
 * @author LLG
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class CrdroutingService extends CrudService<CrdroutingDao, Crdrouting> {

	public Crdrouting get(String id) {
		return super.get(id);
	}
	
	public List<Crdrouting> findList(Crdrouting crdrouting) {
		return super.findList(crdrouting);
	}
	
	public Page<Crdrouting> findPage(Page<Crdrouting> page, Crdrouting crdrouting) {
		return super.findPage(page, crdrouting);
	}
	
	@Transactional(readOnly = false)
	public void save(Crdrouting crdrouting) {
		super.save(crdrouting);
	}
	
	@Transactional(readOnly = false)
	public void delete(Crdrouting crdrouting) {
		super.delete(crdrouting);
	}
	
}