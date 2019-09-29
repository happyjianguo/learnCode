package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.entity.CAccountLog;
import cn.yufu.system.modules.cortexs.dao.CAccountLogDao;

/**
 * CAccountLog交易类型Service
 * @author zbq
 * @version 2016-11-24
 */
@Service
@Transactional(readOnly = true)
public class CAccountLogService extends CrudService<CAccountLogDao, CAccountLog> {

	public CAccountLog get(String pan) {
		return super.get(pan);
	}
	
	public List<CAccountLog> findList(CAccountLog cAccountLog) {
		return super.findList(cAccountLog);
	}
	
	public Page<CAccountLog> findPage(Page<CAccountLog> page, CAccountLog cAccountLog) {
		return super.findPage(page, cAccountLog);
	}
	
//	public List<CAccountLog> findListLog(CAccountLog cAccountLog) {
//		return dao.findListLog(cAccountLog);
//	}
//	@Transactional(readOnly = false)
//	public void save(TTranType tTranType) {
//		if(this.get(tTranType)==null){
//			tTranType.setIsNewRecord(true);
//		}
//		super.save(tTranType);
//	}
//	
//	@Transactional(readOnly = false)
//	public void delete(TTranType tTranType) {
//		super.delete(tTranType);
//	}
//	
}