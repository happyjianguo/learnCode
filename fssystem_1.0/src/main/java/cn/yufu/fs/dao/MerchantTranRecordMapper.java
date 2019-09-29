package cn.yufu.fs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.MerchantTranRecord;

/**
 * MerchantTranRecordMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:ZQK
 * 时间:20179月4日
 * 描述:商户交易明细Dao
 */
@Repository("fs.MerchantTranRecordDao")
public interface MerchantTranRecordMapper {

	public Integer queryCount(MerchantTranRecord merchantTranRecord);
	
	public List<MerchantTranRecord> getList(Map<String, Object> map);
	
	public List<MerchantTranRecord> getExcelData(MerchantTranRecord merchantTranRecord);
	
}
