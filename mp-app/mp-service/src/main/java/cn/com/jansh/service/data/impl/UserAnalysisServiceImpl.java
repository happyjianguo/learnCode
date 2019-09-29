package cn.com.jansh.service.data.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.data.AuthAccount;
import cn.com.jansh.entity.data.UserAnalysisTotal;
import cn.com.jansh.mapper.data.UserAnalysisMapper;
import cn.com.jansh.service.data.IUserAnalysisService;
import cn.com.jansh.util.DateUtil;
import cn.com.jansh.util.JsonUtil;
import cn.com.jansh.vo.AttentionAnalysisVO;
import cn.com.jansh.vo.EchartData;
import cn.com.jansh.vo.Series;

/**
 * 用户分析业务实现类
 * @author gll
 * @version 1.0
 */
@Service
public class UserAnalysisServiceImpl implements IUserAnalysisService {
	
	private static final Logger logger = LogManager.getLogger(UserAnalysisServiceImpl.class);
	
	@Autowired
	private UserAnalysisMapper userAnalysisMapper;

	/**
	 * 关注取消关注分析 
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<BigDecimal> getAttentionData(String appid, String startDate, String endDate, String userType) {
		logger.info("-----------------------------关注取消关注分析 --------------{}",appid);
		BigDecimal count;
		List<Map<String, Object>> dateCounts = null;
		//yyyy-MM-dd转yyyy-MM-dd HH:mm:ss
		String startTimeStamp = DateUtil.getDateTime(DateUtil.parseDate(startDate));;
		//日期加1
		String endTimeStamp = DateUtil.getDateTime(DateUtil.addDate(DateUtil.parseDate(endDate),1));
		if (StringUtils.equals(userType, "1")) {
			count = userAnalysisMapper.queryBeforeCountSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionSubCount(startTimeStamp, endTimeStamp,appid);
		} else {
			count = userAnalysisMapper.queryBeforeCountUnSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionUnSubCount(startTimeStamp, endTimeStamp,appid);
		}
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		Map<String, BigDecimal> mapCounts = new HashMap<String, BigDecimal>();
		String formateDate = "";
		BigDecimal fcount;
		for (Map<String, Object> map : dateCounts) {
			if (StringUtils.equals(userType, "1")) {
				formateDate = (String) map.get("ADATE");
			} else {
				formateDate = (String) map.get("ADATE");
			}

			fcount = new BigDecimal(map.get("USERCOUNT").toString());
			if (mapCounts.containsKey(formateDate)) {
				mapCounts.put(formateDate, mapCounts.get(formateDate).add(fcount));
			} else {
				mapCounts.put(formateDate, fcount);
			}
		}
		int days = DateUtil.daysBetween(startDate, endDate);
		String date = "";
		Object userCount = null;
		for (int i = 0; i <= days; i++) {
			BigDecimal countss= new BigDecimal(0);
			date = DateUtil.addDate(startDate, i);
			userCount = mapCounts.get(date);
			if (userCount == null) {
				data.add(countss);
			} else {
				data.add((BigDecimal) userCount);
			}
		}
		return data;
	}
	/**
	 * 获取现在用户关注信息
	 * @param startDate
	 * @param endDate
	 * @param userType
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<BigDecimal> getAttentionDataNow(String appid, String startDate, String endDate, String userType) {
		logger.info("-----------------------------用户现在关注信息--------------{}",appid);
		BigDecimal count;
		List<Map<String, Object>> dateCounts = null;
		//yyyy-MM-dd转yyyy-MM-dd HH:mm:ss
		String startTimeStamp = DateUtil.getDateTime(DateUtil.parseDate(startDate));;
		//日期加1
		String endTimeStamp = DateUtil.getDateTime(DateUtil.addDate(DateUtil.parseDate(endDate),1));
		if (StringUtils.equals(userType, "1")) {
			count = userAnalysisMapper.queryBeforeCountSubNow(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionSubCountNow(startDate, endTimeStamp,appid);
		} else {
			count = userAnalysisMapper.queryBeforeCountUnSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionUnSubCount(startDate, endTimeStamp,appid);
		}
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		Map<String, BigDecimal> mapCounts = new HashMap<String, BigDecimal>();
		String formateDate = "";
		BigDecimal fcount;
		for (Map<String, Object> map : dateCounts) {
			if (StringUtils.equals(userType, "1")) {
				formateDate = (String) map.get("ADATE");
			} else {
				formateDate = (String) map.get("ADATE");
			}
			fcount = new BigDecimal(map.get("USERCOUNT").toString());
			if (mapCounts.containsKey(formateDate)) {
				mapCounts.put(formateDate, mapCounts.get(formateDate).add(fcount));
			} else {
				mapCounts.put(formateDate, fcount);
			}
		}
		int days = DateUtil.daysBetween(startDate, endDate);
		String date = "";
		Object userCount = null;
		for (int i = 0; i <= days; i++) {
			BigDecimal countss=new BigDecimal(0);
			date = DateUtil.addDate(startDate, i);
			userCount = mapCounts.get(date);
			if (userCount == null) {
				data.add(countss);
			} else {
				data.add((BigDecimal) userCount);
			}
			/*if (userCount == null) {
				if (i == 0) {
					data.add(countss);
				} else {
					data.add(data.get(i - 1));
				}
			} else {
				if (i == 0) {
					data.add(countss.add((BigDecimal) userCount));
					countss =countss.add((BigDecimal) userCount);
				} else {
					data.add(data.get(i - 1).add((BigDecimal) userCount));
				}
			}*/
		}
		return data;
	}
	/**
	 * 辅助线
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<String> showXLineData(String startDate, String endDate) {
		logger.info("-----------------------------showXLineData--------------");
		List<String> xLineData = new ArrayList<String>();
		startDate = startDate.substring(0,10);
		endDate = endDate.substring(0, 10);
		int days = DateUtil.daysBetween(startDate, endDate);
		String date = "";
		for (int i = 0; i <= days; i++) {
			date = DateUtil.addDate(startDate, i);
			xLineData.add(date);
		}
		return xLineData;
	}
	/**
	 * 用户关注整体信息
	 * @return
	 */
	@Override
	public UserAnalysisTotal QuerryUserAnalysisTotalData(String startDate,String endDate,String appid) {
		logger.info("-----------------------------用户关注整体信息--------------{}",appid);
		UserAnalysisTotal userAnalysisTotal = new UserAnalysisTotal();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String subscriBetime = df.format(new Date());
		String subscriPttype = "1";
		//yyyy-MM-dd转yyyy-MM-dd HH:mm:ss
		String starttime = DateUtil.getDateTime(DateUtil.parseDate(startDate));
		//日期加1
		String endtime = DateUtil.getDateTime(DateUtil.addDate(DateUtil.parseDate(endDate),1));
		// 查询总关注人数
		BigDecimal analysisTotal= new BigDecimal("0");
		// 查询当前正关注人数
		BigDecimal analysisNow= new BigDecimal("0");
		// 查询当前取消关注的人数
		BigDecimal analysisCancle= new BigDecimal("0");
		if(!StringUtils.isEmpty(appid)){
			logger.info("appid不为空",appid);
			analysisTotal = userAnalysisMapper.QuerryUserAnalysisTotal(starttime,endtime,appid);
			analysisNow = userAnalysisMapper.QuerryUserAnalysisBytype(starttime,endtime,subscriBetime, subscriPttype,appid);
			subscriPttype = "2";
			analysisCancle = userAnalysisMapper.QuerryUserAnalysisBytype(starttime,endtime,subscriBetime, subscriPttype,appid);
		}
		userAnalysisTotal.setSubscribeTotal(analysisTotal);
		userAnalysisTotal.setSubscribeNow(analysisNow);
		userAnalysisTotal.setSubscribeCancle(analysisCancle);
		return userAnalysisTotal;
	}
	//用户关注详细信息
	@Override
	public EchartData QuerryUserAnalysisDataSource(AttentionAnalysisVO params) {
		logger.info("-----------------------------用户关注详细信息--------------{}",params);
		String startDate = params.getStartDate();
		String endDate = params.getEndDate();
		String appid = params.getAppid();
		if (!(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate))) {
			endDate = DateUtil.getDate().substring(0, 10);
			startDate = DateUtil.getLongFirstDay().substring(0, 10);
		}
		List<String> category = showXLineData(startDate, endDate);
		List<BigDecimal> attentUserCount = getAttentionData(appid, startDate, endDate, "1");//累计关注人数
		List<BigDecimal> attentUserCountnNow = getAttentionDataNow(appid, startDate, endDate, "1");//关注人数
		List<BigDecimal> disattentUserCount = getAttentionData(appid, startDate, endDate, "2");//取消关注人数
		List<String> legend = new ArrayList<String>(Arrays.asList(new
		 String[] { "累计关注人数", "关注人数", "取消关注人数" }));// 数据分组
		 List<Series> series = new ArrayList<Series>();// 纵坐标
		 series.add(new Series("累计关注人数", "line", attentUserCount));
		 series.add(new Series("关注人数", "line", attentUserCountnNow));
		 series.add(new Series("取消关注人数", "line", disattentUserCount));
		 return new EchartData(legend, category, series);
	}
	/**
	 * 公众号省份分析
	 */
	@Override
	public Map<String,String> ProvinceSexAna(String appid) {
		logger.info("---------------------------------*****************--------------{}",appid);
		Map<String,String> map = new HashMap<String,String> ();
		if(StringUtils.isEmpty(appid)){
			map.put("man", "0");
			map.put("woman", "0");
			map.put("max", "0");
		}else{
			try {
				String man = JsonUtil.obj2json(userAnalysisMapper.IMUserAna("1",appid));
				String woman = JsonUtil.obj2json(userAnalysisMapper.IMUserAna("2",appid));
				String max = userAnalysisMapper.IMUserAnaMax(appid);
				/*判断man、woman、max等值是否为空*/
				if(StringUtils.isEmpty(man)){
					map.put("man", "0");
				}else{
					map.put("man", man);
				}
				if(StringUtils.isEmpty(woman)){
					map.put("woman", "0");
				}else{
					map.put("woman", woman);
				}
				if(StringUtils.isEmpty(max)){
					map.put("max", "0");
				}else{
					map.put("max", max);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error("分析地区男女分布获取数据失败{}",e);
			}
		}
		return map;
	}
	/**
	 * 获取所有有效公众账号信息
	 */
	@Override
	public List<AuthAccount> getAccount() {
		logger.info("获取所有有效公众账号信息");
		Map<String,String> map = new HashMap<String,String>();
		map.put("status",ContextCode.APPID_STATUS_1.value());
		return userAnalysisMapper.getAccount(map);
	}
}
