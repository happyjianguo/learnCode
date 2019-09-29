package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ChargeBalanceMapper;
import cn.yufu.fs.entity.ChargeBalance;
import cn.yufu.fs.entity.ChargeBalanceExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.ChargeBalanceService")
public class ChargeBalanceServiceImpl implements ChargeBalanceService {
	
	Log log = Log.getLog(ChargeBalanceServiceImpl.class);

	@Autowired
	@Qualifier("fs.ChargeBalanceDao")
	private ChargeBalanceMapper chargeBalanceDao;
	
	@Override
	public String getSequenceId() {
		String sequenceId = chargeBalanceDao.getSequenceId();
		return DateUtil.getNow("yyyyMMdd") + sequenceId;
	}
	
	@Override
	public int countByExample(ChargeBalance queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = chargeBalanceDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	@Override
	public List<ChargeBalance> selectByExample(ChargeBalance queryModel) {
		return chargeBalanceDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public List<ChargeBalance> selectPageByExample(ChargeBalance queryModel, int startResult, int endResult) {
		return chargeBalanceDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}
	
	@Override
	public ChargeBalance selectByPrimaryKey(String cardTypeId) {
		return chargeBalanceDao.selectByPrimaryKey(cardTypeId);
	}
	
	@Override
	public Map<String, Object> insert(ChargeBalance queryModel) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int insert = chargeBalanceDao.insert(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加调账流水信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> updateByPrimaryKeySelective(ChargeBalance queryModel) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int insert = chargeBalanceDao.updateByPrimaryKeySelective(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "修改调账流水信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> updateByPrimaryKey(ChargeBalance queryModel) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int insert = chargeBalanceDao.updateByPrimaryKey(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "修改调账流水信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> deleteByPrimaryKey(String id) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = chargeBalanceDao.deleteByPrimaryKey(id);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除调账流水信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> cancelByPrimaryKey(String id) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = chargeBalanceDao.cancelByPrimaryKey(id);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "作废调账流水信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private ChargeBalanceExample getExample(ChargeBalance queryModel){
		ChargeBalanceExample example = new ChargeBalanceExample();
		ChargeBalanceExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getId())) {
			criteria.andIdEqualTo(queryModel.getId());
		}
		if (StringUtils.isNotBlank(queryModel.getMerNo())) {
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		}
		if (StringUtils.isNotBlank(queryModel.getTerminalId())) {
			criteria.andTerminalIdEqualTo(queryModel.getTerminalId());
		}
		if (StringUtils.isNotBlank(queryModel.getCardNo())) {
			criteria.andCardNoEqualTo(queryModel.getCardNo());
		}
		if (StringUtils.isNotBlank(queryModel.getRespCode())) {
			criteria.andRespCodeEqualTo(queryModel.getRespCode());
		}
		if (StringUtils.isNotBlank(queryModel.getStartChargeDate())) {
			criteria.andStartChargeDateGreaterThanOrEqualTo(queryModel.getStartChargeDate());
		}
		if (StringUtils.isNotBlank(queryModel.getEndChargeDate())) {
			criteria.andEndChargeDateLessThanOrEqualTo(queryModel.getEndChargeDate());
		}
		if (StringUtils.isNotBlank(queryModel.getStartChargeDate())
				|| StringUtils.isNotBlank(queryModel.getEndChargeDate())) {
			criteria.andRespCodeIsNotNull();
		}
		if (StringUtils.isNotBlank(queryModel.getDelFlag())) {
			criteria.andDelFlagEqualTo(queryModel.getDelFlag());
		} else {
			criteria.andDelFlagEqualTo("0");
		}
		example.setOrderByClause(" A.CREATE_DATE DESC ");
		return example;
	}

}
