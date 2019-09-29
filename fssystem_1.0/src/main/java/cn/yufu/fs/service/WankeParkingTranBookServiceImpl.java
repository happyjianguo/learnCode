package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.WankeParkingTranBookMapper;
import cn.yufu.fs.entity.WankeParkingTranBook;
import cn.yufu.fs.entity.WankeParkingTranBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.WankeParkingTranBookService")
public class WankeParkingTranBookServiceImpl implements WankeParkingTranBookService {
	Log log = Log.getLog(WankeParkingTranBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.WankeParkingTranBookDao")
	private WankeParkingTranBookMapper WankeParkingTranBookDao;

	public int queryCnt(WankeParkingTranBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return WankeParkingTranBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private WankeParkingTranBookExample getExampleByModel(WankeParkingTranBook queryModel){
		WankeParkingTranBookExample example = new WankeParkingTranBookExample();
		WankeParkingTranBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
//		//交易时间日期段
//		if (!StringUtil.isEmpty(queryModel.getStartDt())){
//			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getStartDt());
//		}
//		if (!StringUtil.isEmpty(queryModel.getEndDt())){
//			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndDt());
//		}
//		
//		if (!StringUtil.isEmpty(queryModel.getIid()))
//			criteria.andIidLike("%"+queryModel.getIid()+"%");
		
		example.setOrderByClause("MER_NO");
	
		return example;
	}
	
	public List<WankeParkingTranBook> queryList(WankeParkingTranBook queryModel, int startResult, int endResult) {
		return WankeParkingTranBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<WankeParkingTranBook> queryList(WankeParkingTranBook queryModel) {
		return WankeParkingTranBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
			
//	public String getSumAmt(WankeParkingTranBook queryModel) {
//		if(queryModel==null){
//			return "";
//		}
//		return WankeParkingTranBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
//	}
	
}
