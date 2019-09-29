package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearUnMerStlBookMapper;
import cn.yufu.fs.entity.ClearUnMerStlBook;
import cn.yufu.fs.entity.ClearUnMerStlBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearUnMerStlBookService")
public class ClearUnMerStlBookServiceImpl implements ClearUnMerStlBookService {
	Log log = Log.getLog(ClearUnMerStlBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearUnMerStlBookDao")
	private ClearUnMerStlBookMapper ClearUnMerStlBookDao;

	public int queryCnt(ClearUnMerStlBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearUnMerStlBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearUnMerStlBookExample getExampleByModel(ClearUnMerStlBook queryModel){
		ClearUnMerStlBookExample example = new ClearUnMerStlBookExample();
		ClearUnMerStlBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		
		//是否需要结算
		if (!StringUtil.isEmpty(queryModel.getStlNeedFlag())){
			criteria.andStlNeedFlagEqualTo(queryModel.getStlNeedFlag());
		}
		//单据登记时间
		if (!StringUtil.isEmpty(queryModel.getGenDt())){
			criteria.andGenDtEqualTo(queryModel.getGenDt());
		}
		//截止结算日
		if (!StringUtil.isEmpty(queryModel.getLaststlDate())){
			criteria.andLaststlDateEqualTo(queryModel.getLaststlDate());
		}
		if (!StringUtil.isEmpty(queryModel.getStartunstlDate())){
			criteria.andStartunstlDateGreaterThanOrEqualTo(queryModel.getStartunstlDate());
		}		
		if (!StringUtil.isEmpty(queryModel.getEndunstlDate())){
			criteria.andEndunstlDateEqualTo(queryModel.getEndunstlDate());
		}
		example.setOrderByClause(" MER_NO DESC,ENDUNSTL_DATE DESC, LASTSTL_DATE DESC ");
	
		return example;
	}
	
	public List<ClearUnMerStlBook> queryList(ClearUnMerStlBook queryModel, int startResult, int endResult) {
		return ClearUnMerStlBookDao.selectPageByExample(getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearUnMerStlBook> queryList(ClearUnMerStlBook queryModel) {
		return ClearUnMerStlBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearUnMerStlBook queryInfo(String ClearUnMerStlBookId) {
		return ClearUnMerStlBookDao.selectByPrimaryKey(ClearUnMerStlBookId);
	}
	
	public int UpdateClearUnMerStlBook(ClearUnMerStlBook record){
		return ClearUnMerStlBookDao.updateByPrimaryKey(record);		
	}
	
	
	public Map<String, Object> edit(ClearUnMerStlBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "未结算信息保存成功。");
		ClearUnMerStlBookDao.updateByPrimaryKeySelective(info);
		return map;
	}
		
	public String getSumAmt(ClearUnMerStlBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearUnMerStlBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
}
