package cn.yufu.cortex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.cortex.dao.SysParameterMapper;
import cn.yufu.cortex.entity.SysParameter;
import cn.yufu.cortex.entity.SysParameterExample;

@Service("cortex.SysParameterService")
public class SysParameterServiceImpl implements SysParameterService {
	Log log = Log.getLog(SysParameterServiceImpl.class);

	@Autowired
	@Qualifier("cortex.SysParameterDao")
	private SysParameterMapper SysParameterDao;

	public List<SysParameter> queryList(String paramType,String paramValue) {
		SysParameterExample example = new SysParameterExample();
		SysParameterExample.Criteria criteria = example.createCriteria();
		if(paramType!=null&&!"".equals(paramType)){
			criteria.andParamTypeEqualTo(paramType);
		}
		if(paramValue!=null&&!"".equals(paramValue)){
			criteria.andParamValueEqualTo(paramValue);
		}		
		example.setOrderByClause(" PARAM_TYPE asc,PARAM_VALUE asc ");
		return SysParameterDao.selectByExample(example);
	}
}
