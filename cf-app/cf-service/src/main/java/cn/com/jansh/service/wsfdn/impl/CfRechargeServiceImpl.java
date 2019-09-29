package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.wsfdn.CfRechargeEntity;
import cn.com.jansh.mapper.wsfdn.CfRechargeMapper;
import cn.com.jansh.service.wsfdn.CfRechargeService;

@Service
public class CfRechargeServiceImpl implements CfRechargeService{
	
	private final static Logger logger = LogManager.getLogger(CfRechargeServiceImpl.class);

	@Autowired
	private CfRechargeMapper cfRechargeMapper;
	
	@Override
	public void insert(CfRechargeEntity cfRechargeEntity) {
		logger.info("单笔充值{}",cfRechargeEntity);
		cfRechargeMapper.insert(cfRechargeEntity);
	}

	@Override
	public List<CfRechargeEntity> selectRechargeByStatus(String status) {
		logger.info("通过status查询充值记录");
		return cfRechargeMapper.selectByStatus(status);
	}
	
}
