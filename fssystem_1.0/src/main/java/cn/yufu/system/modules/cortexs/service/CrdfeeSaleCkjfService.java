package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeeSaleCkjfDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeeSaleCkjf;

/**
 * 财卡积分消费明细Service接口
 * @author ZQK
 * @version 2018-06-01
 */
@Service
@Transactional(readOnly = true)
public class CrdfeeSaleCkjfService extends CrudService<CrdfeeSaleCkjfDao, CrdfeeSaleCkjf>{
	
	public CrdfeeSaleCkjf get(CrdfeeSaleCkjf crdfeeSaleCkjf) {
		return super.get(crdfeeSaleCkjf);
	}
	
	public Page<CrdfeeSaleCkjf> findPage(Page<CrdfeeSaleCkjf> page, CrdfeeSaleCkjf crdfeeSaleCkjf){
		return super.findPage(page, crdfeeSaleCkjf);
	}
	
	public List<CrdfeeSaleCkjf> findList(CrdfeeSaleCkjf crdfeeSaleCkjf){
		return super.findList(crdfeeSaleCkjf);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdfeeSaleCkjf crdfeeSaleCkjf) {
		super.delete(crdfeeSaleCkjf);
	};
	
	@Transactional(readOnly = false)
	public void save(CrdfeeSaleCkjf crdfeeSaleCkjf){
		super.save(crdfeeSaleCkjf);
	}
	
}
