package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.cortex.dao.AreaMapper;
import cn.yufu.cortex.entity.Area;
import cn.yufu.cortex.entity.AreaExample;

@Service("cortex.AreaService")
public class AreaServiceImpl implements AreaService {
	Log log = Log.getLog(AreaServiceImpl.class);

	@Autowired
	@Qualifier("cortex.AreaDao")
	private AreaMapper AreaDao;

	public List<Area> queryList(String fid,String isuse) {
		AreaExample example = new AreaExample();
		AreaExample.Criteria criteria = example.createCriteria();
		criteria.andFidEqualTo(Long.valueOf(fid));
		criteria.andIsuseEqualTo(isuse);
		example.setOrderByClause(" id asc ");
		return AreaDao.selectByExample(example);
	}
}
