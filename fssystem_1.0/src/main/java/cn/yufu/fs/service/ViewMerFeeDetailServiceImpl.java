package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ViewMerFeeDetailMapper;
import cn.yufu.fs.entity.ViewMerFeeDetail;
import cn.yufu.fs.entity.ViewMerFeeDetailExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ViewMerFeeDetailService")
public class ViewMerFeeDetailServiceImpl implements ViewMerFeeDetailService {
	Log log = Log.getLog(ViewMerFeeDetailServiceImpl.class);

	@Autowired
	@Qualifier("fs.ViewMerFeeDetailDao")
	private ViewMerFeeDetailMapper ViewMerFeeDetailDao;

	public int queryCnt(ViewMerFeeDetail queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ViewMerFeeDetailDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ViewMerFeeDetailExample getExampleByModel(ViewMerFeeDetail queryModel){
		ViewMerFeeDetailExample example = new ViewMerFeeDetailExample();
		ViewMerFeeDetailExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		if (!StringUtil.isEmpty(queryModel.getId()))
			criteria.andIdEqualTo(queryModel.getId());
		
		example.setOrderByClause(" TRAN_DATE||TRAN_TIME DESC ");
	
		return example;
	}
	
	public List<ViewMerFeeDetail> queryList(ViewMerFeeDetail queryModel, int startResult, int endResult) {
		return ViewMerFeeDetailDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ViewMerFeeDetail> queryList(ViewMerFeeDetail queryModel) {
		return ViewMerFeeDetailDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ViewMerFeeDetail queryInfo(String id) {
		ViewMerFeeDetailExample example = new ViewMerFeeDetailExample();
		ViewMerFeeDetailExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(id))
			criteria.andIdEqualTo(id);
		
		List<ViewMerFeeDetail> list=ViewMerFeeDetailDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
		
	public String getSumAmt(String id) {
		if(id==null){
			return "0";
		}
		return ViewMerFeeDetailDao.getSumAmtByExample(id);
	}
	
}
