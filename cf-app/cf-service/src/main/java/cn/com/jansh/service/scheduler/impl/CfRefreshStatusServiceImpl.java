package cn.com.jansh.service.scheduler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfSupplierpriceMapper;
import cn.com.jansh.service.scheduler.CfRefreshStatusService;
@Service
public class CfRefreshStatusServiceImpl implements CfRefreshStatusService {

	private static final Logger logger = LogManager.getLogger(QCellCoreServiceImpl.class);
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	@Autowired
	private CfSupplierpriceMapper cfSupplierpriceMapper;
	
	@Override
	public void refreshStatus() {
		String time = DateUtil.getDateTime();
			Map<String, Object> acparams = new HashMap<String, Object>(2);
			acparams.put("no", "1");
			acparams.put("time", time);
			acparams.put("wherestatus", AppCommonsCode.STATUS_OK.value());
			acparams.put("paramstatus", AppCommonsCode.STATUS_NO.value());
			logger.info("更新接入者状态");
			cfAccessclientMapper.batchUpdate(acparams);
			logger.info("更新供应商报价状态");
			cfSupplierpriceMapper.batchUpdate(acparams);
			
			Map<String, Object> acparams1 = new HashMap<String, Object>(2);
			acparams1.put("ok", "1");
			acparams1.put("time", time);
			acparams1.put("wherestatus", AppCommonsCode.STATUS_NO.value());
			acparams1.put("paramstatus", AppCommonsCode.STATUS_OK.value());
			logger.info("更新接入者状态");
			cfAccessclientMapper.batchUpdate(acparams1);
			logger.info("更新供应商报价状态");
			cfSupplierpriceMapper.batchUpdate(acparams1);
	}
}
