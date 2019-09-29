package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.cortex.dao.MerchantOrgMapper;
import cn.yufu.cortex.entity.MerchantOrg;
import cn.yufu.system.common.utils.Log;

@Service("cortex.MerchantOrgService")
public class MerchantOrgServiceImpl implements MerchantOrgService {
	
	Log log = Log.getLog(MerchantOrgServiceImpl.class);

	@Autowired
	@Qualifier("cortex.MerchantOrgDao")
	private MerchantOrgMapper merchantOrgDao;
	
	@Override
	public MerchantOrg selectByPrimaryKey(String orgId) {
		return merchantOrgDao.selectByPrimaryKey(orgId);
	}

	@Override
	public List<MerchantOrg> getOrgNameAndID() {
		return merchantOrgDao.getOrgNameAndID();
	}

}
