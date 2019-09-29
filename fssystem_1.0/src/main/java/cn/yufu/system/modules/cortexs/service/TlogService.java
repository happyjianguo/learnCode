package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.TlogDao;
import cn.yufu.system.modules.cortexs.entity.Tlog;

/**
 * 交易流水Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class TlogService extends CrudService<TlogDao, Tlog> {
	@Autowired
	private TlogDao tlogDao;
	public Tlog get(String id) {
		return super.get(id);
	}
	
	public List<Tlog> findList(Tlog tlog) {
		return super.findList(tlog);
	}
	
	public Page<Tlog> findPage(Page<Tlog> page, Tlog tlog) {
		return super.findPage(page, tlog);
	}
	
	public Tlog findSum(Tlog tlog){
		return this.tlogDao.findSum(tlog);
	}
	
	public List<Tlog> findExportList(Tlog tlog){
		return this.tlogDao.findExportList(tlog);
	}
	
}