package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.mapper.wsfdn.CfBatchcurrbusilogMapper;
import cn.com.jansh.service.wsfdn.CfBatchcurrbusilogService;

@Service
public class CfBatchcurrbusilogServiceImpl implements CfBatchcurrbusilogService {

	@Autowired
	private CfBatchcurrbusilogMapper cfBatchcurrbusilogMapper;
	/**
	 * 查询总条数
	 */
	@Override
	public String searchLogCount(CfBatchrechargeEntity cfBatchrechargeEntity) {
		return cfBatchcurrbusilogMapper.searchLogCount(cfBatchrechargeEntity);
	}

	/**
	 * 查询所有批量充值记录
	 */
	@Override
	public List<CfBatchrechargeEntity> batchcurrbusilog(CfBatchrechargeEntity cfBatchrechargeEntity, String start,
			String length) {
		List<CfBatchrechargeEntity> list = cfBatchcurrbusilogMapper.query(cfBatchrechargeEntity,Integer.valueOf(start),Integer.valueOf(length));
		return list;
	}

	@Override
	public List<CfBatchrechargeEntity> batchcurrbusilog(CfBatchrechargeEntity cfBatchrechargeEntity) {
		return cfBatchcurrbusilogMapper.queryall(cfBatchrechargeEntity);
	}

}
