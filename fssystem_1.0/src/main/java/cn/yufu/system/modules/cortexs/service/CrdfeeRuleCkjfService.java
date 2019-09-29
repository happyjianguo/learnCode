package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeeRuleCkjfDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeeRuleCkjf;

/**
 * 积分卡BIN管理Service接口
 * @author ZQK
 * @version 2018-06-01
 */
@Service
@Transactional(readOnly = true)
public class CrdfeeRuleCkjfService extends CrudService<CrdfeeRuleCkjfDao, CrdfeeRuleCkjf>{
	
	public CrdfeeRuleCkjf get(CrdfeeRuleCkjf crdfeeRuleCkjf) {
		return super.get(crdfeeRuleCkjf);
	}
	
	public Page<CrdfeeRuleCkjf> findPage(Page<CrdfeeRuleCkjf> page, CrdfeeRuleCkjf crdfeeRuleCkjf){
		return super.findPage(page, crdfeeRuleCkjf);
	}
	
	public List<CrdfeeRuleCkjf> findList(CrdfeeRuleCkjf crdfeeRuleCkjf){
		return super.findList(crdfeeRuleCkjf);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdfeeRuleCkjf crdfeeRuleCkjf) {
		super.delete(crdfeeRuleCkjf);
	};
	
	@Transactional(readOnly = false)
	public void save(CrdfeeRuleCkjf crdfeeRuleCkjf){
		super.save(crdfeeRuleCkjf);
	}
	
}
