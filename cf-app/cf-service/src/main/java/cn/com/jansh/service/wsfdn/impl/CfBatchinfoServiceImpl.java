package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.mapper.wsfdn.CfBatchinfoMapper;
import cn.com.jansh.service.wsfdn.CfBatchinfoService;
@Service
public class CfBatchinfoServiceImpl implements CfBatchinfoService {

	@Autowired
	private CfBatchinfoMapper cfBatchinfoMapper;
	
	@Override
	public List<CfBatchinfoEntity> queryCfBatchinfo(String status) {
		return cfBatchinfoMapper.queryCfBatchinfo(status);
	}

	@Override
	public List<CfBatchinfoEntity> queryBatchinfoByAccessclient(String acid) {
		return cfBatchinfoMapper.queryCfBatchinfoByAcid(acid);
	}
	@Override
	public List<CfBatchinfoEntity> queryBatchinfoByBatchname(String batchname) {
		return cfBatchinfoMapper.queryCfBatchinfoByBatchname(batchname);
	}
	
}
