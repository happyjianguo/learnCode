package cn.com.jansh.service.weixin;

import java.util.Map;

import cn.com.jansh.entity.weixin.UserAnalysisTotal;
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

	UserAnalysisTotal QuerryUserAnalysisTotalData(String appid);
	/**
	 * 用户关注详细信息
	 * @param params
	 * @return
	 */
	EchartData QuerryUserAnalysisDataSource(AttentionAnalysisVO params);
	/**
	 * 用户分布
	 * @param appid
	 * @return
	 */
	public Map<String,String> ProvinceSexAna(String appid);
}
