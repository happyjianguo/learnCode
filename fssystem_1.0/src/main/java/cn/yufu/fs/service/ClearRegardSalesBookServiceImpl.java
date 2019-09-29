package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearRegardSalesBookMapper;
import cn.yufu.fs.entity.ClearRegardSalesBook;
import cn.yufu.fs.entity.ClearRegardSalesBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearRegardSalesBookService")
public class ClearRegardSalesBookServiceImpl implements ClearRegardSalesBookService {
	Log log = Log.getLog(ClearRegardSalesBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearRegardSalesBookDao")
	private ClearRegardSalesBookMapper ClearRegardSalesBookDao;
	
	private ClearRegardSalesBookExample getExampleByModel(ClearRegardSalesBook queryModel){
		ClearRegardSalesBookExample example = new ClearRegardSalesBookExample();
		ClearRegardSalesBookExample.Criteria criteria = example.createCriteria();
		
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}
		//购卡区域
		if(!StringUtil.isEmpty(queryModel.getSalesProvince())){
			criteria.andSalesProvinceEqualTo(queryModel.getSalesProvince());
		}
		//购卡区域
		if(!StringUtil.isEmpty(queryModel.getSalesCity())){
			criteria.andSalesCityEqualTo(queryModel.getSalesCity());
		}
		//kaleix 
		if(!StringUtil.isEmpty(queryModel.getCrdproduct())){
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		}
				
		example.setOrderByClause("SALES_CITY ASC, CONSUM_CITY ASC");
	
		return example;
	}
	
	public List<ClearRegardSalesBook> queryList(ClearRegardSalesBook queryModel) {
		return ClearRegardSalesBookDao.selectByExample(this.getExampleByModel(queryModel));
	}
	
	public ClearRegardSalesBook queryInfo(String ClearRegardSalesBookId) {
		ClearRegardSalesBookExample example = new ClearRegardSalesBookExample();
		ClearRegardSalesBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(ClearRegardSalesBookId))
			criteria.andIdEqualTo(ClearRegardSalesBookId);
		
		List<ClearRegardSalesBook> list=ClearRegardSalesBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
