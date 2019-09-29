package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfSupplierEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.mapper.wsfdn.CfSupplierMapper;
import cn.com.jansh.mapper.wsfdn.CfSupplierpriceMapper;
import cn.com.jansh.service.wsfdn.CfSupplierService;

@Service
public class CfSupplierServiceImpl implements CfSupplierService{
	
	private static final Logger logger = LogManager.getLogger(CfSupplierServiceImpl.class);

	@Autowired
	private CfSupplierMapper cfSupplierMapper;
	@Autowired
	private CfSupplierpriceMapper cfSupplierpriceMapper;
	
	@Override
	public List<CfSupplierEntity> querySupplier() {
		logger.info("初始化供应商信息");
		return cfSupplierMapper.query();
	}

	@Override
	public void insertSupplier(CfSupplierEntity cfSupplierEntity) throws AppException {
		logger.info("新增供应商{}",cfSupplierEntity.getId());
		CfSupplierEntity cfSupplier = cfSupplierMapper.querySupplierByName(cfSupplierEntity.getSname());
		if(null != cfSupplier){
			throw new AppException(AppErrorCode.E230001);
		}else{
			cfSupplierMapper.insert(cfSupplierEntity);	
		}
	}

	@Override
	public CfSupplierEntity querySupplierByid(String id) {
		logger.info("通过id查询供应商信息{}",id);
		return cfSupplierMapper.querySupplierByid(id);
	}

	@Override
	public void updateSupplier(CfSupplierEntity cfSupplierEntity) {
		logger.info("修改供应商{}",cfSupplierEntity.getId());
		cfSupplierMapper.updateSupplier(cfSupplierEntity);
	}

	@Override
	public void deleteSupplier(String id) throws AppException {
		logger.info("通过id删除供应商信息{}",id);
		List<CfSupplierpriceEntity> cfSupplierprice = cfSupplierpriceMapper.selectSupplierPriceBySid(id);
		if(CollectionUtils.isNotEmpty(cfSupplierprice)){
			throw new AppException(AppErrorCode.E230002);
		}else{
			cfSupplierMapper.deleteSupplier(id);
		}
	}
	
}
