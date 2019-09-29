package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.FkStlDayEndStepMapper;
import cn.yufu.fs.entity.FkStlDayEndStep;
import cn.yufu.fs.entity.FkStlDayEndStepExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.FkStlDayEndStepService")
public class FkStlDayEndStepServiceImpl implements FkStlDayEndStepService {
	Log log = Log.getLog(FkStlDayEndStepServiceImpl.class);

	@Autowired
	@Qualifier("fs.FkStlDayEndStepDao")
	private FkStlDayEndStepMapper FkStlDayEndStepDao;

	public int queryCnt(FkStlDayEndStep queryModel) {
		if(queryModel==null){
			return 0;
		}
		return FkStlDayEndStepDao.countByExample(getExampleByModel(queryModel));
	}
	
	private FkStlDayEndStepExample getExampleByModel(FkStlDayEndStep queryModel){
		FkStlDayEndStepExample example = new FkStlDayEndStepExample();
		FkStlDayEndStepExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getDailydate()))
			criteria.andDailydateEqualTo(queryModel.getDailydate());
		if (!StringUtil.isEmpty(queryModel.getStepname()))
			criteria.andStepnameLike("%"+queryModel.getStepname()+"%");		
		example.setOrderByClause(" id asc ");
	
		return example;
	}

	public List<FkStlDayEndStep> queryList(FkStlDayEndStep queryModel) {
		return FkStlDayEndStepDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public FkStlDayEndStep queryInfo(String id) {
		FkStlDayEndStepExample example = new FkStlDayEndStepExample();
		FkStlDayEndStepExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(id))
			criteria.andIdEqualTo(new BigDecimal(id));
		
		List<FkStlDayEndStep> list=FkStlDayEndStepDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
}
