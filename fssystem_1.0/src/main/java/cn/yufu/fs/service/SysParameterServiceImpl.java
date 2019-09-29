package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.SysParameterMapper;
import cn.yufu.fs.entity.SysParameter;
import cn.yufu.fs.entity.SysParameterExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.SysParameterService")
public class SysParameterServiceImpl implements SysParameterService {
	Log log = Log.getLog(SysParameterServiceImpl.class);

	@Autowired
	@Qualifier("fs.SysParameterDao")
	private SysParameterMapper SysParameterDao;

	/*public int queryCnt(ClearMermccBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMermccBookDao.countByExample(getExampleByModel(queryModel));
	}*/
	
	private SysParameterExample getExampleByModel(SysParameter queryModel){
		SysParameterExample example = new SysParameterExample();
		SysParameterExample.Criteria criteria = example.createCriteria();
		
		
		//消费类型
		if(!StringUtil.isEmpty(queryModel.getParamType())){
			criteria.andParamTypeEqualTo(queryModel.getParamType());
		}
				
		example.setOrderByClause("to_number(PARAM_ID) ASC");
	
		return example;
	}
	
	public List<SysParameter> queryList(SysParameter queryModel) {
		return SysParameterDao.selectByExample(this.getExampleByModel(queryModel));
	}


}
