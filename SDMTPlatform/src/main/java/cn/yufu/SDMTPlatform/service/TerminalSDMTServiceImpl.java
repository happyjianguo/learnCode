package cn.yufu.SDMTPlatform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.commons.utils.StringUtil;
import cn.yufu.SDMTPlatform.dao.TerminalSDMTMapper;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTExample;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTKey;

@Service("sdmtpf.TerminalSDMTService")
public class TerminalSDMTServiceImpl implements TerminalSDMTService {
	Log log = Log.getLog(TerminalSDMTServiceImpl.class);

	@Autowired
	@Qualifier("sdmtpf.TerminalSDMTDao")
	private TerminalSDMTMapper TerminalSDMTDao;

	public int queryCnt(TerminalSDMT queryModel) {
		TerminalSDMTExample example = new TerminalSDMTExample();
		TerminalSDMTExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerchantId()))
			criteria.andMerchantIdEqualTo(queryModel.getMerchantId());
		if (!StringUtil.isEmpty(queryModel.getTermCode()))
			criteria.andTermCodeEqualTo(queryModel.getTermCode());
		if (queryModel.getAddDate()!=null)
			criteria.andAddDateEqualTo(queryModel.getAddDate());		
		if (!StringUtil.isEmpty(queryModel.getTermAddress()))
			criteria.andTermAddressLike("%"+queryModel.getTermAddress()+"%");
		if (!StringUtil.isEmpty(queryModel.getSdFlag()))
			criteria.andSdFlagEqualTo(queryModel.getSdFlag());
		if (!StringUtil.isEmpty(queryModel.getxFlag()))
			criteria.andXFlagEqualTo(queryModel.getxFlag());		
		if (!StringUtil.isEmpty(queryModel.getYufuFlag()))
			criteria.andYufuFlagEqualTo(queryModel.getYufuFlag());		
		return TerminalSDMTDao.countByExample(example);
	}

	public List<TerminalSDMT> queryList(TerminalSDMT queryModel, int startResult, int endResult) {
		TerminalSDMTExample example = new TerminalSDMTExample();
		TerminalSDMTExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerchantId()))
			criteria.andMerchantIdEqualTo(queryModel.getMerchantId());
		if (!StringUtil.isEmpty(queryModel.getTermCode()))
			criteria.andTermCodeEqualTo(queryModel.getTermCode());
		if (queryModel.getAddDate()!=null)
			criteria.andAddDateEqualTo(queryModel.getAddDate());		
		if (!StringUtil.isEmpty(queryModel.getTermAddress()))
			criteria.andTermAddressLike("%"+queryModel.getTermAddress()+"%");
		if (!StringUtil.isEmpty(queryModel.getSdFlag()))
			criteria.andSdFlagEqualTo(queryModel.getSdFlag());
		if (!StringUtil.isEmpty(queryModel.getxFlag()))
			criteria.andXFlagEqualTo(queryModel.getxFlag());		
		if (!StringUtil.isEmpty(queryModel.getYufuFlag()))
			criteria.andYufuFlagEqualTo(queryModel.getYufuFlag());
		example.setOrderByClause("ADD_DATE DESC,MERCHANT_ID DESC,TERM_CODE DESC");
		return TerminalSDMTDao.selectPageByExample(example, startResult, endResult);
	}

	public TerminalSDMT queryInfo(TerminalSDMTKey TerminalId) {
		return TerminalSDMTDao.selectByPrimaryKey(TerminalId);
	}
	
	public int UpdateTerminalSDMT(TerminalSDMT record){
		return TerminalSDMTDao.updateByPrimaryKey(record);		
	}
	
	public Map<String, Object> save(TerminalSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "终端保存成功。");
		boolean saveOk = true;// 逻辑通过可保存
		if (checkTerminal(info.getMerchantId(),info.getTermCode()) > 0) {
			// 重复 （发起正常交易、如果存在则重复，撤销不判断）
			saveOk = false;// 异常
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "终端编号重复。");
			return map;
		}
		
		if (saveOk) {
			TerminalSDMTDao.insertSelective(info);
		}
		return map;
	}
	
	public int checkTerminal(String merchantId,String termCode){
		TerminalSDMTExample example = new TerminalSDMTExample();
		TerminalSDMTExample.Criteria criteria = example.createCriteria();
		//criteria.andMerchantIdEqualTo(merchantId);
		criteria.andTermCodeEqualTo(termCode);
		List<TerminalSDMT> TerminalList = TerminalSDMTDao.selectByExample(example);
		return TerminalList.size();
	}
	
	public Map<String, Object> edit(TerminalSDMT info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "终端保存成功。");
		TerminalSDMTDao.updateByPrimaryKeySelective(info);
		return map;
	}
}
