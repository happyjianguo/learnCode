package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMermccDetailBookMapper;
import cn.yufu.fs.entity.ClearMermccDetailBook;
import cn.yufu.fs.entity.ClearMermccDetailBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearMermccDetailBookService")
public class ClearMermccDetailBookServiceImpl implements ClearMermccDetailBookService {
	Log log = Log.getLog(ClearMermccDetailBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMermccDetailBookDao")
	private ClearMermccDetailBookMapper ClearMermccDetailBookDao;
	
	private ClearMermccDetailBookExample getExampleByModel(ClearMermccDetailBook queryModel){
		ClearMermccDetailBookExample example = new ClearMermccDetailBookExample();
		ClearMermccDetailBookExample.Criteria criteria = example.createCriteria();
		
		
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}
		//消费省份
		if(!StringUtil.isEmpty(queryModel.getConsumProvince())){
			criteria.andConsumProvinceEqualTo(queryModel.getConsumProvince());
		}
		//消费区域
		if(null!=queryModel.getConsumCity()){
			criteria.andConsumCityEqualTo(queryModel.getConsumCity());
		}
		//消费类型
		if(!StringUtil.isEmpty(queryModel.getConsumType())){
			criteria.andConsumTypeEqualTo(queryModel.getConsumType());
		}
		
		example.setOrderByClause("CONSUM_CITY ASC,MER_NO ASC");
	
		return example;
	}
	
	private ClearMermccDetailBookExample getExampleByModelSum(ClearMermccDetailBook queryModel){
		ClearMermccDetailBookExample example = new ClearMermccDetailBookExample();
		ClearMermccDetailBookExample.Criteria criteria = example.createCriteria();
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}
		//消费省份
		if(!StringUtil.isEmpty(queryModel.getConsumProvince())){
			criteria.andConsumProvinceEqualTo(queryModel.getConsumProvince());
		}
		//消费区域
		if(queryModel.getConsumCity()!=null){
			criteria.andConsumCityEqualTo(queryModel.getConsumCity());
		}
		//消费类型
		if(!StringUtil.isEmpty(queryModel.getConsumType())){
			criteria.andConsumTypeEqualTo(queryModel.getConsumType());
		}
		
	
		return example;
	}
	
	public List<ClearMermccDetailBook> queryList(ClearMermccDetailBook queryModel) {
		return ClearMermccDetailBookDao.selectByExample(this.getExampleByModel(queryModel));
	}
	
	public List<ClearMermccDetailBook> queryListSum(ClearMermccDetailBook queryModel) {
		return ClearMermccDetailBookDao.selectByExampleSum(this.getExampleByModelSum(queryModel));
	}
	
	public ClearMermccDetailBook queryInfo(String ClearMermccDetailBookId) {
		ClearMermccDetailBookExample example = new ClearMermccDetailBookExample();
		ClearMermccDetailBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(ClearMermccDetailBookId))
			criteria.andIdEqualTo(ClearMermccDetailBookId);
		
		List<ClearMermccDetailBook> list=ClearMermccDetailBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
}
