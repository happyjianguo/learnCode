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
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.service.wsfdn.CfAccessclientService;

/**
 * 接入者service实现
 * 
 * @author gll
 *
 */
@Service
public class CfAccessclientServiceImpl implements CfAccessclientService {

	private static final Logger logger = LogManager.getLogger(CfAccessclientServiceImpl.class);

	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;

	@Override
	public List<CfAccessclientEntity> queryAccessclient() {
		logger.info("初始化接入者信息");
		return cfAccessclientMapper.query();
	}

	@Override
	public List<CfAccessclientEntity> queryAccessclientBy(CfAccessclientEntity cfAccessclientEntity) {
		logger.info("初始化接入者信息");
		cfAccessclientEntity.setId(cfAccessclientEntity.getQid());
		cfAccessclientEntity.setCid(cfAccessclientEntity.getQcid());
		cfAccessclientEntity.setStatus(cfAccessclientEntity.getQstatus());
		return cfAccessclientMapper.queryby(cfAccessclientEntity);
	}
	@Override
	public CfAccessclientEntity queryAccessclientById(String id) {
		logger.info("通过id查询接入者{}", id);
		return cfAccessclientMapper.selectByid(id);
	}

	@Override
	public void insertAccessclient(CfAccessclientEntity cfAccessclientEntity) throws AppException {
		logger.info("新增接入者{}", cfAccessclientEntity.getId());
		List<CfAccessclientEntity> cfAccessclient = cfAccessclientMapper.selectByName(cfAccessclientEntity.getAcname());
		if (CollectionUtils.isNotEmpty(cfAccessclient)) {
			throw new AppException(AppErrorCode.E220001);
		} else {
			cfAccessclientMapper.insert(cfAccessclientEntity);
		}
	}

	@Override
	public void updateAccessclient(CfAccessclientEntity cfAccessclientEntity) {
		logger.info("修改接入者{}", cfAccessclientEntity.getId());
		cfAccessclientMapper.update(cfAccessclientEntity);
	}

	@Override
	public void deleteAccessclient(String id) throws AppException {
		logger.info("删除接入者{}", id);
		List<CfAccesspriceEntity> cfAccessprice = cfAccesspriceMapper.queryAccesspriceByAcid(id);
		if (CollectionUtils.isNotEmpty(cfAccessprice)) {
			logger.error("该接入者正在使用，无法删除");
			throw new AppException(AppErrorCode.E220002);
		} else {
			cfAccessclientMapper.delete(id);
		}
	}

	@Override
	public List<CfAccessclientEntity> queryAccessClientByAcName(String acname) {
		logger.info("查询接入者{}", acname);
		return cfAccessclientMapper.selectByName(acname);
	}

	@Override
	public List<CfAccessclientEntity> queryAccessclientByCustormerId(String id) {
		logger.info("通过客户id查询接入者{}", id);
		return cfAccessclientMapper.queryAccessclientByCustormerId(id);
	}
	@Override
	public List<CfAccessclientEntity> queryAccessclientByCustormerIdAndSta(String id,String status) {
		logger.info("通过客户id查询接入者{}", id);
		return cfAccessclientMapper.queryAccessclientByCustormerIdAndSta(id,status);
	}
	
}
