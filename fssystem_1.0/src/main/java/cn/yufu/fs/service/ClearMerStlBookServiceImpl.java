package cn.yufu.fs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMerStlBookMapper;
import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ClearMerStlBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
import cn.yufu.system.common.utils.StringUtils;
@Service("fs.ClearMerStlBookService")
public class ClearMerStlBookServiceImpl implements ClearMerStlBookService {
	Log log = Log.getLog(ClearMerStlBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMerStlBookDao")
	private ClearMerStlBookMapper ClearMerStlBookDao;

	public int queryCnt(ClearMerStlBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMerStlBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearMerStlBookExample getExampleByModel(ClearMerStlBook queryModel){
		ClearMerStlBookExample example = new ClearMerStlBookExample();
		ClearMerStlBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getGenDt())){
			criteria.andGenDtEqualTo(queryModel.getGenDt());
		}
		if (!StringUtil.isEmpty(queryModel.getScene())){
			criteria.andSceneEqualTo(queryModel.getScene());
		}
		if (!StringUtil.isEmpty(queryModel.getStlDate())){
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}
		if (!StringUtil.isEmpty(queryModel.getStartStlDate())){
			criteria.andStlDateGreaterThanOrEqualTo(queryModel.getStartStlDate());
		}		
		if (!StringUtil.isEmpty(queryModel.getEndStlDate())){
			criteria.andStlDateLessThanOrEqualTo(queryModel.getEndStlDate());
		}			
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" FINAL_STL_ID DESC,MER_NO DESC, GEN_DT DESC ,ROWID DESC ");
	
		return example;
	}
	
	public List<ClearMerStlBook> queryList(ClearMerStlBook queryModel, int startResult, int endResult) {
		return ClearMerStlBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearMerStlBook> queryList(ClearMerStlBook queryModel) {
		return ClearMerStlBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearMerStlBook queryInfo(String ClearMerStlBookId) {
		return ClearMerStlBookDao.selectByPrimaryKey(ClearMerStlBookId);
	}
	
	public int UpdateClearMerStlBook(ClearMerStlBook record){
		return ClearMerStlBookDao.updateByPrimaryKey(record);		
	}
	
	
	public Map<String, Object> edit(ClearMerStlBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "差错流水信息保存成功。");
		ClearMerStlBookDao.updateByPrimaryKeySelective(info);
		return map;
	}
		
	public String getSumAmt(ClearMerStlBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearMerStlBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}

	public List<ClearMerStlBook> queryExcelList(ClearMerStlBook clearMerStlBook) {
		return this.ClearMerStlBookDao.selectExcelByExample(this.getExampleByModel(clearMerStlBook));
	}
	
	/************************************************************************/
	@Override
	public int queryCount(ClearMerStlBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMerStlBookDao.queryCount(getExample(queryModel));
	}

	@Override
	public List<ClearMerStlBook> selectPageList(ClearMerStlBook queryModel, int startResult, int endResult) {
		return ClearMerStlBookDao.selectPageList(this.getExample(queryModel), startResult, endResult);
	}

	@Override
	public String getSumAmtTotal(ClearMerStlBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearMerStlBookDao.getSumAmtTotal(this.getExample(queryModel));
	}

	@Override
	public List<ClearMerStlBook> getExcelList(ClearMerStlBook queryModel) {
		return this.ClearMerStlBookDao.getExcelList(this.getExample(queryModel));
	}
	
	private ClearMerStlBookExample getExample(ClearMerStlBook queryModel){
		ClearMerStlBookExample example = new ClearMerStlBookExample();
		ClearMerStlBookExample.Criteria criteria = example.createCriteria();
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
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getGenDt())){
			criteria.andGenDtEqualTo(queryModel.getGenDt());
		}
		if (!StringUtil.isEmpty(queryModel.getScene())){
			criteria.andSceneEqualTo(queryModel.getScene());
		}
		if (!StringUtil.isEmpty(queryModel.getStlDate())){
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}
		if (!StringUtil.isEmpty(queryModel.getStartStlDate())){
			criteria.andStlDateGreaterThanOrEqualTo(queryModel.getStartStlDate());
		}		
		if (!StringUtil.isEmpty(queryModel.getEndStlDate())){
			criteria.andStlDateLessThanOrEqualTo(queryModel.getEndStlDate());
		}			
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" FINAL_STL_ID DESC,MER_NO DESC, GEN_DT DESC ,ROWID DESC ");
	
		return example;
	}

	@Override
	public void sendMailUpdate(ClearMerStlBook queryModel) {
		ClearMerStlBookDao.sendMailUpdate(queryModel);
	}
	
}
