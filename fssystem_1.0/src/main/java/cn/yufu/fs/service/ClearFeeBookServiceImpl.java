package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearFeeBookMapper;
import cn.yufu.fs.entity.ClearFeeBook;
import cn.yufu.fs.entity.ClearFeeBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearFeeBookService")
 public class ClearFeeBookServiceImpl implements ClearFeeBookService {
	Log log = Log.getLog(ClearFeeBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearFeeBookDao")
	private ClearFeeBookMapper ClearFeeBookDao;

	public int queryCnt(ClearFeeBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearFeeBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearFeeBookExample getExampleByModel(ClearFeeBook queryModel){
		ClearFeeBookExample example = new ClearFeeBookExample();
		ClearFeeBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getGenDate())){
			criteria.andGenDateEqualTo(queryModel.getGenDate());
		}
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDate())){
			criteria.andStartDateGreaterThanOrEqualTo(queryModel.getStartDate());
		}		
		if (!StringUtil.isEmpty(queryModel.getEndDate())){
			criteria.andEndDateLessThanOrEqualTo(queryModel.getEndDate());
		}
		if (!StringUtil.isEmpty(queryModel.getExpressDate())){
			criteria.andExpressDateEqualTo(queryModel.getExpressDate());
		}	
		if (!StringUtil.isEmpty(queryModel.getStatus())){
			criteria.andStatusEqualTo(queryModel.getStatus());
		}		
		if (!StringUtil.isEmpty(queryModel.getStlDate())){
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}	
		if (!StringUtil.isEmpty(queryModel.getIsKp())){
			criteria.andIsKpEqualTo(queryModel.getIsKp());
		}
		example.setOrderByClause(" GEN_DATE DESC,MER_NO DESC ");
	
		return example;
	}
	
	public List<ClearFeeBook> queryList(ClearFeeBook queryModel, int startResult, int endResult) {
		return ClearFeeBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearFeeBook> queryList(ClearFeeBook queryModel) {
		return ClearFeeBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearFeeBook queryInfo(String ClearFeeBookId) {
		return ClearFeeBookDao.selectByPrimaryKey(ClearFeeBookId);
	}
	
	public int UpdateClearFeeBook(ClearFeeBook record){
		return ClearFeeBookDao.updateByPrimaryKey(record);		
	}
	
	
	public Map<String, Object> edit(ClearFeeBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "手续费信息保存成功。");
		ClearFeeBookDao.updateByPrimaryKeySelective(info);
		return map;
	}
		
	public String getSumAmt(ClearFeeBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearFeeBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
}
