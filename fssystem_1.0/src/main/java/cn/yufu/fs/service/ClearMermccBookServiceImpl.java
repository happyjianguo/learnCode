package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMermccBookMapper;
import cn.yufu.fs.entity.ClearMermccBook;
import cn.yufu.fs.entity.ClearMermccBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearMermccBookService")
public class ClearMermccBookServiceImpl implements ClearMermccBookService {
	Log log = Log.getLog(ClearMermccBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMermccBookDao")
	private ClearMermccBookMapper ClearMermccBookDao;

	/*public int queryCnt(ClearMermccBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMermccBookDao.countByExample(getExampleByModel(queryModel));
	}*/
	
	private ClearMermccBookExample getExampleByModel(ClearMermccBook queryModel){
		ClearMermccBookExample example = new ClearMermccBookExample();
		ClearMermccBookExample.Criteria criteria = example.createCriteria();
		
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
		if(!StringUtil.isEmpty(queryModel.getConsumCity())){
			criteria.andConsumCityEqualTo(queryModel.getConsumCity());
		}
		//消费类型
		if(!StringUtil.isEmpty(queryModel.getConsumType())){
			criteria.andConsumTypeEqualTo(queryModel.getConsumType());
		}
				
		example.setOrderByClause("to_number(CONSUM_TYPE) ASC,CONSUM_CITY ASC");
	
		return example;
	}
	
	public List<ClearMermccBook> queryList(ClearMermccBook queryModel) {
		return ClearMermccBookDao.selectByExample(this.getExampleByModel(queryModel));
	}
	
	public ClearMermccBook queryInfo(String ClearMermccBookId) {
		ClearMermccBookExample example = new ClearMermccBookExample();
		ClearMermccBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(ClearMermccBookId))
			criteria.andIdEqualTo(ClearMermccBookId);
		
		List<ClearMermccBook> list=ClearMermccBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int getCount(ClearMermccBook queryModel) {
		Integer count = ClearMermccBookDao.getCount(queryModel);
		if (count == null) {
			return 0;
		}
		return count;
	}
	
	@Override
	public List<ClearMermccBook> getTotalAmt(ClearMermccBook queryModel) {
		return ClearMermccBookDao.getTotalAmt(queryModel);
	}

	@Override
	public List<ClearMermccBook> getTotalAmtPage(ClearMermccBook queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return ClearMermccBookDao.getTotalAmtPage(map);
	}

	@Override
	public ClearMermccBook getTotal(ClearMermccBook queryModel) {
		return ClearMermccBookDao.getTotal(queryModel);
	}

}
