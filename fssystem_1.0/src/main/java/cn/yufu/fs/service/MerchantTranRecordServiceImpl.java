package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.MerchantTranRecordMapper;
import cn.yufu.fs.entity.MerchantTranRecord;
import cn.yufu.system.common.utils.Log;

@Service("fs.MerchantTranRecordService")
public class MerchantTranRecordServiceImpl implements MerchantTranRecordService {
	Log log = Log.getLog(MerchantTranRecordServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.MerchantTranRecordDao")
	private MerchantTranRecordMapper merchantTranRecordMapper;
	
	@Override
	public int queryCnt(MerchantTranRecord merchantTranRecord) {
		if (merchantTranRecord == null) {
			return 0;
		}
		Integer queryCount = merchantTranRecordMapper.queryCount(merchantTranRecord);
		if (queryCount == null) {
			return 0;
		}
		return queryCount;
	}

	@Override
	public List<MerchantTranRecord> queryList(MerchantTranRecord merchantTranRecord, 
			int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", merchantTranRecord);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return merchantTranRecordMapper.getList(map);
	}

	@Override
	public List<MerchantTranRecord> getExcelData(MerchantTranRecord merchantTranRecord) {
		return merchantTranRecordMapper.getExcelData(merchantTranRecord);
	}

}
