package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.cortex.dao.TBankInfoMapper;
import cn.yufu.cortex.entity.TBankInfo;
import cn.yufu.cortex.entity.TBankInfoExample;

@Service("cortex.TBankInfoService")
public class TBankInfoServiceImpl implements TBankInfoService {
	Log log = Log.getLog(TBankInfoServiceImpl.class);

	@Autowired
	@Qualifier("cortex.TBankInfoDao")
	private TBankInfoMapper TBankInfoDao;

	public List<TBankInfo> queryList(String bankCode) {
		TBankInfoExample example = new TBankInfoExample();
		TBankInfoExample.Criteria criteria = example.createCriteria();
		if(bankCode!=null&&!"".equals(bankCode)){
			criteria.andBankCodeEqualTo(bankCode);
		}
		example.setOrderByClause(" BANK_CODE asc ");
		return TBankInfoDao.selectByExample(example);
	}
}
