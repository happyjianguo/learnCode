package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfCustomerMapper;
import cn.com.jansh.service.wsfdn.CfCustomerService;

@Service
public class CfCustomerServiceImpl implements CfCustomerService {
	
	private final static Logger logger = LogManager.getLogger(CfCustomerServiceImpl.class);
	@Autowired
	private CfCustomerMapper cfCustomerMapper;
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	@Override
	public List<CfCustomerEntity> queryCustomer() {
		logger.info("初始化客户信息");
		return cfCustomerMapper.query();
	}

	@Override
	public List<CfCustomerEntity> queryCustomerBy(CfCustomerEntity cfCustomerEntity) {
		logger.info("初始化客户信息");		
		cfCustomerEntity.setId(cfCustomerEntity.getQid());
		cfCustomerEntity.setMname(cfCustomerEntity.getQmname());		
		return cfCustomerMapper.queryby(cfCustomerEntity);
	}
	@Override
	public void insertCustomer(CfCustomerEntity cfCustomerEntity) throws AppException {
		logger.info("新增客户{}",cfCustomerEntity.getId());
		List<CfCustomerEntity> cfCustomer = cfCustomerMapper.selectByName(cfCustomerEntity.getCname());
		if(CollectionUtils.isNotEmpty(cfCustomer)){
			throw new AppException(AppErrorCode.E210001);
		}else{
			cfCustomerMapper.insert(cfCustomerEntity);
		}
	}

	@Override
	public CfCustomerEntity selectCustomerById(String id) {
		logger.info("通过id查看客户{}",id);
		return cfCustomerMapper.selectCustomerById(id);
	}

	@Override
	public void updateCustomer(CfCustomerEntity cfCustomerEntity) {
		logger.info("修改客户{}",cfCustomerEntity.getId());
		cfCustomerMapper.updateCustomer(cfCustomerEntity);

	}

	@Override
	public void deleteCustomerById(String id) throws AppException {
		logger.info("删除客户{}",id);
		List<CfAccessclientEntity> cfAccessclient = cfAccessclientMapper.queryAccessclientByCustormerId(id);
		if(CollectionUtils.isNotEmpty(cfAccessclient)){
			throw new AppException(AppErrorCode.E210002);
		}else{
			cfCustomerMapper.deleteCustomer(id);
		}
	}

	@Override
	public List<CfCustomerEntity> queryCustomerByAcName(String cname) {
		logger.info("删除客户{}",cname);
		return cfCustomerMapper.selectByName(cname);
	}

}
