package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.cortex.dao.NumDescrMapper;
import cn.yufu.cortex.entity.NumDescr;
import cn.yufu.cortex.entity.NumDescrExample;

@Service("cortex.NumDescrService")
public class NumDescrServiceImpl implements NumDescrService {
	Log log = Log.getLog(NumDescrServiceImpl.class);

	@Autowired
	@Qualifier("cortex.NumDescrDao")
	private NumDescrMapper NumDescrDao;

	public List<NumDescr> queryList(String descrtype,String lang) {
		NumDescrExample example = new NumDescrExample();
		NumDescrExample.Criteria criteria = example.createCriteria();
		criteria.andDescrtypeEqualTo(descrtype);
		criteria.andLangEqualTo(lang);
		example.setOrderByClause(" id asc ");
		List<NumDescr> list = NumDescrDao.selectByExample(example);
		return list;
	}
}
