package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeeTopCkjfDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeeTopCkjf;

/**
 * 财卡积分充值明细Service接口
 * @author ZQK
 * @version 2018-06-01
 */
@Service
@Transactional(readOnly = true)
public class CrdfeeTopCkjfService extends CrudService<CrdfeeTopCkjfDao, CrdfeeTopCkjf>{
	
	public CrdfeeTopCkjf get(CrdfeeTopCkjf crdfeeTopCkjf) {
		return super.get(crdfeeTopCkjf);
	}
	
	public Page<CrdfeeTopCkjf> findPage(Page<CrdfeeTopCkjf> page, CrdfeeTopCkjf crdfeeTopCkjf){
		return super.findPage(page, crdfeeTopCkjf);
	}
	
	public List<CrdfeeTopCkjf> findList(CrdfeeTopCkjf crdfeeTopCkjf){
		return super.findList(crdfeeTopCkjf);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdfeeTopCkjf crdfeeTopCkjf) {
		super.delete(crdfeeTopCkjf);
	};
	
	@Transactional(readOnly = false)
	public void save(CrdfeeTopCkjf crdfeeTopCkjf){
		super.save(crdfeeTopCkjf);
	}
	
}
