package cn.com.jansh.service.data;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.data.AuthAccount;
import cn.com.jansh.entity.data.UserAnalysisTotal;
import cn.com.jansh.vo.AttentionAnalysisVO;
import cn.com.jansh.vo.EchartData;

/**
 * 用户分析业务接口
 * @author gll
 * @version 1.0
 */
public interface IUserAnalysisService {
	/**
	 * 关注取消关注分析
	 * @param params 
	 * @return
	 */
	UserAnalysisTotal QuerryUserAnalysisTotalData(String startDate,String endDate,String appid);
	/**
	 * 用户关注详细信息
	 * @param params
	 * @return
	 */
	EchartData QuerryUserAnalysisDataSource(AttentionAnalysisVO params);
	/**
	 * 省份分析
	 * @param appid
	 * @return
	 */
	public Map<String,String> ProvinceSexAna(String appid);
	/**
	 * 公众账号初始化
	 * @return
	 */
	public List<AuthAccount> getAccount();
}
