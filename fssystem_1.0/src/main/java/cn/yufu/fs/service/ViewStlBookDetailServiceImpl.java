package cn.yufu.fs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ViewStlBookDetailMapper;
import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.entity.ViewStlBookDetailExample;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
import cn.yufu.system.common.utils.StringUtils;
@Service("fs.ViewStlBookDetailService")
public class ViewStlBookDetailServiceImpl implements ViewStlBookDetailService {
	Log log = Log.getLog(ViewStlBookDetailServiceImpl.class);

	@Autowired
	@Qualifier("fs.ViewStlBookDetailDao")
	private ViewStlBookDetailMapper ViewStlBookDetailDao;

	public int queryCnt(ViewStlBookDetail queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ViewStlBookDetailDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ViewStlBookDetailExample getExampleByModel(ViewStlBookDetail queryModel){
		ViewStlBookDetailExample example = new ViewStlBookDetailExample();
		ViewStlBookDetailExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		if (!StringUtil.isEmpty(queryModel.getId()))
			criteria.andIdEqualTo(queryModel.getId());
		
		example.setOrderByClause(" TRAN_DATE||TRAN_TIME DESC ");
	
		return example;
	}
	
	public List<ViewStlBookDetail> queryList(ViewStlBookDetail queryModel, int startResult, int endResult) {
		return ViewStlBookDetailDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ViewStlBookDetail> queryList(ViewStlBookDetail queryModel) {
		return ViewStlBookDetailDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ViewStlBookDetail queryInfo(String id) {
		ViewStlBookDetailExample example = new ViewStlBookDetailExample();
		ViewStlBookDetailExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(id))
			criteria.andIdEqualTo(id);
		
		List<ViewStlBookDetail> list=ViewStlBookDetailDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
		
	public String getSumAmt(String id) {
		if(id==null){
			return "0";
		}
		return ViewStlBookDetailDao.getSumAmtByExample(id);
	}

	@Override
	public List<ViewStlBookDetail> getList(ViewStlBookDetail queryModel, int startResult, int endResult) {
		return ViewStlBookDetailDao.getList(this.getExample(queryModel), startResult, endResult);
	}

	@Override
	public String getSumAmtTotal(String id) {
		if(id==null){
			return "0";
		}
		return ViewStlBookDetailDao.getSumAmtTotal(id);
	}
	
	private ViewStlBookDetailExample getExample(ViewStlBookDetail queryModel){
		ViewStlBookDetailExample example = new ViewStlBookDetailExample();
		ViewStlBookDetailExample.Criteria criteria = example.createCriteria();
		//需要过滤的商户号
		List<String> asList = new ArrayList<>();
		String config = Global.getConfig("merno");
		if(StringUtils.isNoneBlank(config)) {
			 String[] merchantNumberArray = config.split(",");
		     asList = Arrays.asList(merchantNumberArray);
        }
		if (asList != null && asList.size() > 0) {
			criteria.andMerNoNotIn(asList);
		}
				
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		if (!StringUtil.isEmpty(queryModel.getId()))
			criteria.andIdEqualTo(queryModel.getId());
		
		example.setOrderByClause(" TRAN_DATE||TRAN_TIME DESC ");
	
		return example;
	}
	
}
