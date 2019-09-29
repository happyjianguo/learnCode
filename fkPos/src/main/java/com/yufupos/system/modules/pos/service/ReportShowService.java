package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.ReportShowDao;
import com.yufupos.system.modules.pos.entity.ReportShow;

/**
 * 报表展示Service
 * 
 * @author zqk
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class ReportShowService extends CrudService<ReportShowDao, ReportShow> {
	
	@Autowired
	private ReportShowDao reportShowDao;
	
	public ReportShow get(String id) {
		return super.get(id);
	}
	
	public List<ReportShow> getOneData(String terminalId, String merchantId){
		ReportShow reportShow = new ReportShow();
		reportShow.setMerchantId(merchantId);
		reportShow.setTerminalId(terminalId);
		return super.getOneData(reportShow);
	}
	
	public List<ReportShow> findList(ReportShow reportShow) {
		return super.findList(reportShow);
	}
	
	public List<ReportShow> findAllList(ReportShow reportShow) {
		return reportShowDao.findAllList(reportShow);
	}
	
	public Page<ReportShow> findPage(Page<ReportShow> page, ReportShow reportShow) {
		return super.findPage(page, reportShow);
	}
	
	
}