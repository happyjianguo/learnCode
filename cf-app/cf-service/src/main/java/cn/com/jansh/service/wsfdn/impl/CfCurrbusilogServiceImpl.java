package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.service.wsfdn.CfCurrbusilogService;

@Service
public class CfCurrbusilogServiceImpl implements CfCurrbusilogService {
	
	private final static Logger logger = LogManager.getLogger(CfRechargeServiceImpl.class);
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	@Override
	public void insert(CfCurrbusilogEntity rechargeToCurrbusilog) {
		logger.info("开始插入");
		cfCurrbusilogMapper.insert(rechargeToCurrbusilog);
	}	
	
	@Override
	public String searchLogCount(CfCurrbusilogEntity cfCurrbusilogEntity) {
		logger.info("查询总页数");
		return cfCurrbusilogMapper.searchLogCount(cfCurrbusilogEntity);
	}
	@Override
	public List<CfCurrbusilogEntity> queryCurrbusilog(CfCurrbusilogEntity cfCurrbusilogEntity,String start,String length) {
		logger.info("查询所有流水记录");
	/*	cfCurrbusilogEntity.setBegintime(DateUtil.formatTimestamp(cfCurrbusilogEntity.getBegintime()));
		cfCurrbusilogEntity.setEndtime(DateUtil.formatTimestamp(DateUtil.dateTimeAdd(DateUtil.getDateTime(DateUtil.parseDate(cfCurrbusilogEntity.getEndtime())), 1)));*/
		return cfCurrbusilogMapper.query(cfCurrbusilogEntity,Integer.valueOf(start),Integer.valueOf(length));
	}

	@Override
	public List<CfCurrbusilogEntity> queryAllCurrbusilog(CfCurrbusilogEntity cfCurrbusilogEntity) {
		logger.info("查询所有流水记录（不带分页）");
		return cfCurrbusilogMapper.queryall(cfCurrbusilogEntity);
	}

}
