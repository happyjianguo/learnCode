package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.MSettleBill;
import cn.yufu.system.modules.cortexs.entity.SysParameterBean;
import cn.yufu.system.modules.cortexs.dao.MSettleBillDao;


/**
 * TLOG交易类型Service
 * @author LLG
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class MSettleBillService extends CrudService<MSettleBillDao, MSettleBill> {
	@Autowired
	private MSettleBillDao mSettleBillDao;
	
	public MSettleBill get(String id) {
		return super.get(id);
	}
	
	public List<MSettleBill> findList(MSettleBill mSettleBill) {
		return super.findList(mSettleBill);
	}
	
	public Page<MSettleBill> findPage(Page<MSettleBill> page, MSettleBill mSettleBill) {
		return super.findPage(page, mSettleBill);
	}
	
	@Transactional(readOnly = false)
	public void save(MSettleBill mSettleBill) {
		mSettleBill.preUpdate();
		mSettleBillDao.update(mSettleBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(MSettleBill mSettleBill) {
		super.delete(mSettleBill);
	}
	
	public List<SysParameterBean> getSysParameterList(String paramType) {
		return this.mSettleBillDao.getSysParameterList(paramType);
	}
	
}