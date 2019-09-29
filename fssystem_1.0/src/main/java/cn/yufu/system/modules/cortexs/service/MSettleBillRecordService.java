package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.MSettleBillRecordDao;
import cn.yufu.system.modules.cortexs.entity.MSettleBillRecord;

/**
 * TLOG交易类型Service
 * @author ZQK
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class MSettleBillRecordService extends CrudService<MSettleBillRecordDao, MSettleBillRecord>{
	
	@Autowired
	private MSettleBillRecordDao mSettleBillRecordDao;
	
	public MSettleBillRecord get(String id) {
		return super.get(id);
	}
	
	public List<MSettleBillRecord> findList(MSettleBillRecord mSettleBillRecord) {
		return super.findList(mSettleBillRecord);
	}
	
	public List<String> getModifyModule(MSettleBillRecord mSettleBillRecord) {
		return mSettleBillRecordDao.getModifyModule(mSettleBillRecord);
	}
	
	public Page<MSettleBillRecord> findPage(Page<MSettleBillRecord> page, MSettleBillRecord mSettleBillRecord){
		return super.findPage(page, mSettleBillRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(MSettleBillRecord mSettleBillRecord){
		mSettleBillRecordDao.insert(mSettleBillRecord);
	}
	
}
