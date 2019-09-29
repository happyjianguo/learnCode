package cn.com.jansh.service.wsfdn.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.mapper.wsfdn.CfSupplierpriceMapper;
import cn.com.jansh.service.wsfdn.CfSupplierpriceService;

@Service
public class CfSupplierpriceServiceImpl implements CfSupplierpriceService{
	
	private static final Logger logger = LogManager.getLogger(CfSupplierpriceServiceImpl.class);

	@Autowired
	private CfSupplierpriceMapper cfSupplierpriceMapper;
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;
	
	@Override
	public List<CfSupplierpriceEntity> query(CfSupplierpriceEntity cfSupplierpriceEntity) {
		logger.info("查询供应商报价{}",cfSupplierpriceEntity);
		return cfSupplierpriceMapper.query(cfSupplierpriceEntity);
	}

	@Override
	public void addCFSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity) throws AppException {
		logger.info("增加供应商报价{}",cfSupplierpriceEntity);
		CfSupplierpriceEntity Supplierprice = cfSupplierpriceMapper.querySupplierpriceByName(cfSupplierpriceEntity.getPname());
		if(null != Supplierprice){
			throw new AppException(AppErrorCode.E250001);
		}else{
			cfSupplierpriceMapper.addCFSupplierprice(cfSupplierpriceEntity);
		}
	}

	@Override
	public CfSupplierpriceEntity querySupplierpriceByid(String id) {
		logger.info("查询供应商报价{}",id);
		return cfSupplierpriceMapper.querySupplierpriceByid(id);
	}

	@Override
	public void updateSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity) {
		logger.info("修改供应商报价{}",cfSupplierpriceEntity);
		cfSupplierpriceMapper.updateSupplierprice(cfSupplierpriceEntity);
	}

	@Override
	public void delSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity) throws AppException {
		logger.info("删除供应商报价{}",cfSupplierpriceEntity);
		CfAccesspriceEntity accessprice = new CfAccesspriceEntity();
		accessprice.setIspno(cfSupplierpriceEntity.getIspno());
		accessprice.setIpstype(cfSupplierpriceEntity.getIpstype());
		accessprice.setFacevalue(cfSupplierpriceEntity.getSize());
		accessprice.setProvince(cfSupplierpriceEntity.getPno());
		List<CfAccesspriceEntity> cfAccesspriceEntity = cfAccesspriceMapper.queryAccesspriceByApprice(accessprice);
		if(CollectionUtils.isNotEmpty(cfAccesspriceEntity)){
			throw new AppException(AppErrorCode.E250002);
		}else{
			cfSupplierpriceMapper.delSupplierprice(cfSupplierpriceEntity.getId());
		}
	}

	@Override
	public List<String> queryIpsno() {
		logger.info("查询运营商种类");
		return cfSupplierpriceMapper.queryIpsno();
	}

	@Override
	public List<String> queryIpsType(String ispno) {
		logger.info("查询套餐类型种类{}",ispno);
		return cfSupplierpriceMapper.queryIpsType(ispno);
	}

	@Override
	public List<CfSupplierpriceEntity> queryprovince(CfSupplierpriceEntity cfSupplierpriceEntity) {
		logger.info("查询省份{}",cfSupplierpriceEntity);
		return cfSupplierpriceMapper.queryprovince(cfSupplierpriceEntity);
	}

	@Override
	public List<String> queryFaceValue(CfSupplierpriceEntity cfSupplierpriceEntity) {
		logger.info("查询面值{}",cfSupplierpriceEntity);
		return cfSupplierpriceMapper.queryFaceValue(cfSupplierpriceEntity);
	}

	@Override
	public List<CfSupplierpriceEntity> queryprice() {
		logger.info("查询所有报价");
		return cfSupplierpriceMapper.queryprice();
	}

	@Override
	public CfSupplierpriceEntity queryByName(String name) {
		return cfSupplierpriceMapper.querySupplierpriceByName(name);
	}

	
	
}
