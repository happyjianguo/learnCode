package cn.com.jansh.mapper.weixin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.entity.weixin.IMUserAna;
/**
 * 用户分析Mapper
 * @author gll
 * @version 1.0
 */
public interface UserAnalysisMapper {
	public BigDecimal queryBeforeCountSub(@Param("startTimeStamp")String startTimeStamp,@Param("appid")String appid);
	public BigDecimal queryBeforeCountUnSub(@Param("startTimeStamp")String startTimeStamp,@Param("appid")String appid);
	public BigDecimal queryBeforeCountSubNow(@Param("startTimeStamp")String startTimeStamp,@Param("appid")String appid);
	public List<Map<String, Object>> queryDateAttentionSubCountNow(@Param("startTimeStamp")String startTimeStamp, @Param("endTimeStamp")String endTimeStamp,@Param("appid")String appid);
	public List<Map<String, Object>> queryDateAttentionSubCount(@Param("startTimeStamp")String startTimeStamp, @Param("endTimeStamp")String endTimeStamp,@Param("appid")String appid);
	public List<Map<String, Object>> queryDateAttentionUnSubCount(@Param("startTimeStamp")String startTimeStamp, @Param("endTimeStamp")String endTimeStamp,@Param("appid")String appid);
	public BigDecimal QuerryUserAnalysisTotal(@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("subscriBetime")String subscriBetime,@Param("appid")String appid);
	public BigDecimal QuerryUserAnalysisBytype(@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("subscriBetime")String subscriBetime, @Param("subscriPttype")String subscriPttype,@Param("appid")String appid);
	
	/**
	 * 根据性别分析
	 * @param sex
	 * @return
	 */
	public List<IMUserAna> IMUserAna(String sex,String appid);
	
	/**
	 * 获取人数上限
	 * @return
	 */
	public String IMUserAnaMax(String appid);
	/**
	 * 获取所有有效公众账号信息
	 * @param map
	 * @return
	 */
	public List<AuthAccount> getAccount(Map<String, String> map);
	/**
	 * 根据机构id获取所有有效公众账号信息
	 * @param map
	 * @return
	 */
	public List<AuthAccount> getAccountByOrgid(Map<String, String> map);
	/**
	 * 根据当前用户获取有效公众号信息
	 * @param map
	 * @return
	 */
	public List<AuthAccount> getDataAccount(Map<String, String> map);
}
