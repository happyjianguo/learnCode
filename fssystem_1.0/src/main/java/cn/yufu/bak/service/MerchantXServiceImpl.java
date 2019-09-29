package cn.yufu.bak.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.MerchantXMapper;
import cn.yufu.bak.entity.MerchantX;

@Service("bak.MerchantXService")
public class MerchantXServiceImpl implements MerchantXService {
	
	@Autowired
	@Qualifier("bak.MerchantXDao")
	private MerchantXMapper merchantXDao;
	
	@Override
	public List<String> getMrchno(String mrchtName) {
		List<String> list = merchantXDao.getMrchno(mrchtName);
		list = list == null || list.size() == 0 ? null :list;
		return list;
	}
	
	@Override
	public List<String> getMrchtName(String mrchno) {
		List<String> list = merchantXDao.getMrchtName(mrchno);
		list = list == null || list.size() == 0 ? null :list;
		return list;
	}

	@Override
	public int getLazyMerchantCounts(List<String> mrchno) {
		Map<String, Object> map = new HashMap<>();
		map.put("mrchnoList", mrchno);
		return merchantXDao.getLazyMerchantCounts(map);
	}

	@Override
	public List<MerchantX> selectPageMrchList(List<String> mrchno, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("mrchnoList", mrchno);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return merchantXDao.selectPageMrchList(map);
	}

	@Override
	public List<MerchantX> selectMrchList() {
		return merchantXDao.selectMrchList();
	}

}
