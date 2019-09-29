package cn.yufu.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.MerchantMapper;

@Service("bak.MerchantService")
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	@Qualifier("bak.MerchantDao")
	private MerchantMapper merchantDao;
	
	@Override
	public List<Integer> getMrchstat(String mrchno) {
		List<Integer> mrchstat = merchantDao.getMrchstat(mrchno);
		if (mrchstat == null) {
			return null;
		}
		return mrchstat;
	}

}
