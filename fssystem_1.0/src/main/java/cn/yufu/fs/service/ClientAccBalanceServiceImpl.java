package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClientAccBalanceMapper;
import cn.yufu.fs.entity.ClientAccBalance;
import cn.yufu.fs.entity.ClientAccBalanceExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClientAccBalanceService")
 public class ClientAccBalanceServiceImpl implements ClientAccBalanceService {
	Log log = Log.getLog(ClientAccBalanceServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClientAccBalanceDao")
	private ClientAccBalanceMapper ClientAccBalanceDao;

	public int queryCnt(ClientAccBalance queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClientAccBalanceDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClientAccBalanceExample getExampleByModel(ClientAccBalance queryModel){
		ClientAccBalanceExample example = new ClientAccBalanceExample();
		ClientAccBalanceExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getDailyDate()))
			criteria.andDailyDateEqualTo(queryModel.getDailyDate());		
		example.setOrderByClause(" GEN_DT DESC ");
	
		return example;
	}
	
	public List<ClientAccBalance> queryList(ClientAccBalance queryModel, int startResult, int endResult) {
		return ClientAccBalanceDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClientAccBalance> queryList(ClientAccBalance queryModel) {
		return ClientAccBalanceDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClientAccBalance queryInfo(String ClientAccBalanceId) {
		return ClientAccBalanceDao.selectByPrimaryKey(ClientAccBalanceId);
	}
	
	public int UpdateClientAccBalance(ClientAccBalance record){
		return ClientAccBalanceDao.updateByPrimaryKey(record);		
	}
	
	
	public Map<String, Object> edit(ClientAccBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "手续费信息保存成功。");
		ClientAccBalanceDao.updateByPrimaryKeySelective(info);
		return map;
	}
		
	public String getSumAmt(ClientAccBalance queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClientAccBalanceDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
}
