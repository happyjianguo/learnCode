package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.CrdfeeCkjfFlogDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeeCkjfFlog;

/**
 * 财卡过期积分扣款明细Service接口
 * @author ZQK
 * @version 2018-06-01
 */
@Service
@Transactional(readOnly = true)
public class CrdfeeCkjfFlogService extends CrudService<CrdfeeCkjfFlogDao, CrdfeeCkjfFlog>{
	
	public CrdfeeCkjfFlog get(CrdfeeCkjfFlog crdfeeCkjfFlog) {
		return super.get(crdfeeCkjfFlog);
	}
	
	public Page<CrdfeeCkjfFlog> findPage(Page<CrdfeeCkjfFlog> page, CrdfeeCkjfFlog crdfeeCkjfFlog){
		return super.findPage(page, crdfeeCkjfFlog);
	}
	
	public List<CrdfeeCkjfFlog> findList(CrdfeeCkjfFlog crdfeeCkjfFlog){
		return super.findList(crdfeeCkjfFlog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdfeeCkjfFlog crdfeeCkjfFlog) {
		super.delete(crdfeeCkjfFlog);
	};
	
	@Transactional(readOnly = false)
	public void save(CrdfeeCkjfFlog crdfeeCkjfFlog){
		super.save(crdfeeCkjfFlog);
	}
	
}
