package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearRegardSalesDetBookMapper;
import cn.yufu.fs.entity.ClearRegardSalesDetBook;
import cn.yufu.fs.entity.ClearRegardSalesDetBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearRegardSalesDetBookService")
public class ClearRegardSalesDetBookServiceImpl implements ClearRegardSalesDetBookService {
	Log log = Log.getLog(ClearRegardSalesDetBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearRegardSalesDetBookDao")
	private ClearRegardSalesDetBookMapper ClearRegardSalesDetBookDao;
	
	private ClearRegardSalesDetBookExample getExampleByModel(ClearRegardSalesDetBook queryModel){
		ClearRegardSalesDetBookExample example = new ClearRegardSalesDetBookExample();
		ClearRegardSalesDetBookExample.Criteria criteria = example.createCriteria();
		
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}
		
		//购卡区域
		if(queryModel.getSalesCity()!=null){
			criteria.andSalesCityEqualTo(queryModel.getSalesCity());
		}
		
		//购卡区域
		if(!StringUtil.isEmpty(queryModel.getSalesProvince())){
			criteria.andSalesProvinceEqualTo(queryModel.getSalesProvince());
		}
		//购卡区域
		if(null!=queryModel.getSalesCity()){
			criteria.andSalesCityEqualTo(queryModel.getSalesCity());
		}
		//kaleix 
		if(!StringUtil.isEmpty(queryModel.getCrdproduct())){
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		}
				
		example.setOrderByClause("CONSUM_CITY ASC, MER_NO ASC");
	
		return example;
	}
	
	private ClearRegardSalesDetBookExample getExampleByModelSum(ClearRegardSalesDetBook queryModel){
		ClearRegardSalesDetBookExample example = new ClearRegardSalesDetBookExample();
		ClearRegardSalesDetBookExample.Criteria criteria = example.createCriteria();
		
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}
		
		//购卡区域
		if(queryModel.getSalesCity()!=null){
			criteria.andSalesCityEqualTo(queryModel.getSalesCity());
		}
		
		//购卡区域
		if(!StringUtil.isEmpty(queryModel.getSalesProvince())){
			criteria.andSalesProvinceEqualTo(queryModel.getSalesProvince());
		}
		//购卡区域
		if(null!=queryModel.getSalesCity()){
			criteria.andSalesCityEqualTo(queryModel.getSalesCity());
		}
		//kaleix 
		if(!StringUtil.isEmpty(queryModel.getCrdproduct())){
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		}

		return example;
	}
	
	public List<ClearRegardSalesDetBook> queryList(ClearRegardSalesDetBook queryModel) {
		return ClearRegardSalesDetBookDao.selectByExample(this.getExampleByModel(queryModel));
	}
	
	public List<ClearRegardSalesDetBook> queryListSum(ClearRegardSalesDetBook queryModel) {
		return ClearRegardSalesDetBookDao.selectByExampleSum(this.getExampleByModelSum(queryModel));
	}
	
	public ClearRegardSalesDetBook queryInfo(String ClearRegardSalesDetBookId) {
		ClearRegardSalesDetBookExample example = new ClearRegardSalesDetBookExample();
		ClearRegardSalesDetBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(ClearRegardSalesDetBookId))
			criteria.andIdEqualTo(ClearRegardSalesDetBookId);
		
		List<ClearRegardSalesDetBook> list=ClearRegardSalesDetBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
