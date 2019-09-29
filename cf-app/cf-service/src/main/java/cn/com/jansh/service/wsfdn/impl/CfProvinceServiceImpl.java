package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.wsfdn.CfProvinceEntity;
import cn.com.jansh.mapper.wsfdn.CfProvinceMapper;
import cn.com.jansh.service.wsfdn.CfProvinceService;

@Service
public class CfProvinceServiceImpl implements CfProvinceService {
	
	private final static Logger logger = LogManager.getLogger(CfProvinceServiceImpl.class);

	@Autowired
	private CfProvinceMapper cfProvinceMapper;
	
	@Override
	public List<CfProvinceEntity> query() {
		logger.info("查询所有省份");
		return cfProvinceMapper.query();
	}

	@Override
	public CfProvinceEntity selectPnameBypno(String pno) {
		logger.info("查询所有省份");
		return cfProvinceMapper.selectPnameBypno(pno);
	}
}
