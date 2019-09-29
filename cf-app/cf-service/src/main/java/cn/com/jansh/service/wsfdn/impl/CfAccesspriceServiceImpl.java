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
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.service.wsfdn.CfAccesspriceService;

@Service
public class CfAccesspriceServiceImpl implements CfAccesspriceService {

	private static final Logger logger = LogManager.getLogger(CfAccessclientServiceImpl.class);
	
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;

	@Override
	public List<CfAccesspriceEntity> query(CfAccesspriceEntity cfAccesspriceEntity) {
		logger.info("查询接入者报价{}"+cfAccesspriceEntity);
		return cfAccesspriceMapper.query(cfAccesspriceEntity);
	}

	@Override
	public CfAccesspriceEntity queryAccesspriceByid(String id) {
		logger.info("通过id查询接入者报价{}"+id);
		return cfAccesspriceMapper.queryAccesspriceByid(id);
	}
	
	/**
	 * 需校验是否重复
	 * @throws AppException 
	 */
	@Override
	public void insertAccessprice(CfAccesspriceEntity cfAccesspriceEntity) throws AppException {
		logger.info("新增接入者报价{}"+cfAccesspriceEntity);
		String apid = cfAccesspriceEntity.getApid();
		String price = cfAccesspriceEntity.getPrice();
		cfAccesspriceEntity.setApid("");
		cfAccesspriceEntity.setPrice("");
		List<CfAccesspriceEntity> li = cfAccesspriceMapper.query(cfAccesspriceEntity);
		if(CollectionUtils.isNotEmpty(li)){
			throw new AppException(AppErrorCode.E260001);
		}else{
			cfAccesspriceEntity.setApid(apid);
			cfAccesspriceEntity.setPrice(price);
			cfAccesspriceMapper.insert(cfAccesspriceEntity);
		}
	}

	@Override
	public void updateAccessprice(CfAccesspriceEntity cfAccesspriceEntity) {
		logger.info("修改接入者报价{}"+cfAccesspriceEntity);
		cfAccesspriceMapper.updateAccessprice(cfAccesspriceEntity);
	}
		

	@Override
	public void deldeteAccessprice(String apid) {
		logger.info("删除接入者报价{}"+apid);
		cfAccesspriceMapper.deleteAccessprice(apid);
	}

	@Override
	public List<CfAccesspriceEntity> queryIspno(CfAccesspriceEntity cfAccesspriceEntity) {
		logger.info("获取运营商{}"+cfAccesspriceEntity);
		return cfAccesspriceMapper.queryIspno(cfAccesspriceEntity);
	}

	@Override
	public List<CfAccesspriceEntity> queryprovince(CfAccesspriceEntity cfAccesspriceEntity) {
		logger.info("获取省份{}"+cfAccesspriceEntity);
		return cfAccesspriceMapper.queryprovince(cfAccesspriceEntity);
	}

	@Override
	public CfAccesspriceEntity selectprice(CfAccesspriceEntity cfAccesspriceEntity) {
		logger.info("多条件获取省份接入者");
		return cfAccesspriceMapper.selectprice(cfAccesspriceEntity);
	}

}
