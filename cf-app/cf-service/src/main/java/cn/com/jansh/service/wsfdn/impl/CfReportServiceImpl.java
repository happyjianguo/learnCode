package cn.com.jansh.service.wsfdn.impl;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.jansh.entity.wsfdn.CfReportEntity;
import cn.com.jansh.mapper.wsfdn.CfReportMapper;
import cn.com.jansh.service.wsfdn.CfReportService;

@Service
public class CfReportServiceImpl implements CfReportService {

	private static final Logger logger = LogManager.getLogger(CfReportServiceImpl.class);
	@Autowired
	private CfReportMapper cfReportMapper;

	public String searchLogCount(CfReportEntity cfReportEntity) {
		logger.info("查询总页数");
		return cfReportMapper.searchLogCount(cfReportEntity);
	}
	@Override
	public List<CfReportEntity> queryAllReport(CfReportEntity cfReportEntity) {
		logger.info("查询所有报表记录（不带分页）");
		return cfReportMapper.queryall(cfReportEntity);
	}	
	@Override	
	public List<CfReportEntity> queryReport(CfReportEntity cfReportEntity,String start,String length){
		logger.info("查询所有报表记录");
	 	List<CfReportEntity> cfReportList =  cfReportMapper.query(cfReportEntity,Integer.valueOf(start),Integer.valueOf(length));
		return cfReportList;
	}
}
