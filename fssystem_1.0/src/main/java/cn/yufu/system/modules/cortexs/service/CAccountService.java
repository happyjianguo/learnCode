package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.CAccount;
import cn.yufu.system.modules.cortexs.dao.CAccountDao;

/**
 * CAccount交易类型Service
 * @author zbq
 * @version 2016-11-24
 */
@Service
@Transactional(readOnly = true)
public class CAccountService extends CrudService<CAccountDao, CAccount> {
	@Autowired
	private CAccountDao cAccountDao;
	public CAccount get(String id) {
		return super.get(id);
	}
	
	public List<CAccount> findList(CAccount cAccount) {
		return super.findList(cAccount);
	}
	
	public Page<CAccount> findPage(Page<CAccount> page, CAccount cAccount) {
		return super.findPage(page, cAccount);
	}
	

	@Transactional(readOnly = false)
	public void save(CAccount cAccount) {
		cAccount.preUpdate();
		cAccountDao.cAccountSave(cAccount);
	}
	

}