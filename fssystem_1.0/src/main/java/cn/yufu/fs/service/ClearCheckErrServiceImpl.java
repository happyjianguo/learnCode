package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearCheckErrMapper;
import cn.yufu.fs.entity.ClearCheckErr;
import cn.yufu.fs.entity.ClearCheckErrExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearCheckErrService")
public class ClearCheckErrServiceImpl implements ClearCheckErrService {
	Log log = Log.getLog(ClearCheckErrServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearCheckErrDao")
	private ClearCheckErrMapper ClearCheckErrDao;

	public int queryCnt(ClearCheckErr queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearCheckErrDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearCheckErrExample getExampleByModel(ClearCheckErr queryModel){
		ClearCheckErrExample example = new ClearCheckErrExample();
		ClearCheckErrExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDtGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDtLessThanOrEqualTo(queryModel.getEndDt());
		}		
		if (!StringUtil.isEmpty(queryModel.getStlFlag())){
			criteria.andStlFlagEqualTo(queryModel.getStlFlag());
		}			
		if (!StringUtil.isEmpty(queryModel.getGenType())){
			criteria.andGenTypeEqualTo(queryModel.getGenType());
		}			
		if (!StringUtil.isEmpty(queryModel.getDcFlag())){
			criteria.andDcFlagEqualTo(queryModel.getDcFlag());
		}			
		if (!StringUtil.isEmpty(queryModel.getCheckTime())){
			criteria.andCheckTimeEqualTo(queryModel.getCheckTime());
		}
		if (!StringUtil.isEmpty(queryModel.getOperDt1())){
			criteria.andOperDt1EqualTo(queryModel.getOperDt1());
		}
		if (!StringUtil.isEmpty(queryModel.getOperDt2())){
			criteria.andOperDt2EqualTo(queryModel.getOperDt2());
		}
		if (!StringUtil.isEmpty(queryModel.getStatus())){
			criteria.andStatusEqualTo(queryModel.getStatus());
		}		
		example.setOrderByClause(" GEN_DT DESC,MER_NO,TERM_NO,TRAN_DATE||TRAN_TIME DESC ");
	
		return example;
	}
	
	public List<ClearCheckErr> queryList(ClearCheckErr queryModel, int startResult, int endResult) {
		return ClearCheckErrDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearCheckErr> queryList(ClearCheckErr queryModel) {
		return ClearCheckErrDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearCheckErr queryInfo(String ClearCheckErrId) {
		return ClearCheckErrDao.selectByPrimaryKey(ClearCheckErrId);
	}
	
	public int UpdateClearCheckErr(ClearCheckErr record){
		return ClearCheckErrDao.updateByPrimaryKey(record);		
	}
	
	public Map<String, Object> save(ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "差错流水信息保存成功。");
		//获取主键
		info.setId(ClearCheckErrDao.getKeyId());
		info.setGenDt(DateUtil.getNowDefault());
		info.setGenType("1");
		info.setAcqtranAmt(info.getCoretranAmt());

		ClearCheckErrDao.insertSelective(info);
		return map;
	}
	
	public Map<String, Object> edit(ClearCheckErr info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "差错流水信息保存成功。");
		ClearCheckErrDao.updateByPrimaryKeySelective(info);
		return map;
	}
	
	public int checkClearCheckErrId(String ClearCheckErrId){
		ClearCheckErrExample example = new ClearCheckErrExample();
		ClearCheckErrExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(ClearCheckErrId);
		List<ClearCheckErr> ClearCheckErr = ClearCheckErrDao.selectByExample(example);
		return ClearCheckErr.size();
	}
	
	public String getSumAmt(ClearCheckErr queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearCheckErrDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
}
