package cn.com.jansh.service.weixin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import cn.com.jansh.entity.weixin.IMUserAna;
import cn.com.jansh.entity.weixin.UserAnalysisTotal;
import cn.com.jansh.mapper.weixin.UserAnalysisMapper;
import cn.com.jansh.service.weixin.IUserAnalysisService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.JsonUtil;
import cn.com.jansh.vo.AttentionAnalysisVO;
import cn.com.jansh.vo.EchartData;
import cn.com.jansh.vo.Series;

/**
 * 用户分析业务接口Impl
 * @author gll
 * @version 1.0
 */
@Service()
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
		BigDecimal count;
		List<Map<String, Object>> dateCounts = null;
		String startTimeStamp = startDate;
		String endTimeStamp = (DateUtil.addDate(endDate, 1));
		if (StringUtils.equals(userType, "1")) {
			count = userAnalysisMapper.queryBeforeCountSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionSubCount(startDate, endTimeStamp,appid);
		} else {
			count = userAnalysisMapper.queryBeforeCountUnSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionUnSubCount(startDate, endTimeStamp,appid);
		}
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		Map<String, Object> mapCounts = new HashMap<String, Object>();
		String formateDate = "";
		BigDecimal fcount;
		for (Map<String, Object> map : dateCounts) {
			if (StringUtils.equals(userType, "1")) {
				formateDate = (String) map.get("ADATE");
			} else {
				formateDate = (String) map.get("ADATE");
			}

			fcount =new BigDecimal(map.get("USERCOUNT").toString());
			if (mapCounts.containsKey(formateDate)) {
				mapCounts.put(formateDate, ((BigDecimal) mapCounts.get(formateDate)).add(fcount));
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
			}else{
				data.add((BigDecimal) userCount);
			}
			/*
			 * if (userCount == null) {
				if (i == 0) {
					data.add(countss);
				} else {
					data.add(data.get(i - 1));
				}
			} else {
				if (i == 0) {
					data.add(countss.add((BigDecimal) userCount));
					countss = countss.add((BigDecimal) userCount);
				} else {
					data.add(data.get(i - 1).add((BigDecimal) userCount));
				}
			}
			*/
		}
		return data;
	}
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param userType
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<BigDecimal> getAttentionDataNow(String appid, String startDate, String endDate, String userType) {
		BigDecimal count;
		List<Map<String, Object>> dateCounts = null;
		String startTimeStamp = startDate;
		String endTimeStamp = (DateUtil.addDate(endDate, 1));
		if (StringUtils.equals(userType, "1")) {
			count = userAnalysisMapper.queryBeforeCountSubNow(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionSubCountNow(startDate, endTimeStamp,appid);
		} else {
			count = userAnalysisMapper.queryBeforeCountUnSub(startTimeStamp,appid);
			dateCounts = userAnalysisMapper.queryDateAttentionUnSubCount(startDate, endTimeStamp,appid);
		}
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		Map<String, Object> mapCounts = new HashMap<String, Object>();
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
				mapCounts.put(formateDate, ((BigDecimal) mapCounts.get(formateDate)).add(fcount));
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
			}else{
				data.add((BigDecimal) userCount);
			}
			/*
			 * if (userCount == null) {
				if (i == 0) {
					data.add(countss);
				} else {
					data.add(data.get(i - 1));
				}
			} else {
				if (i == 0) {
					data.add(countss.add((BigDecimal) userCount));
					countss = countss.add((BigDecimal) userCount);
				} else {
					data.add(data.get(i - 1).add((BigDecimal) userCount));
				}
			}
			*/
		}
		return data;
	}
	public List<String> showXLineData(String startDate, String endDate) {
		List<String> xLineData = new ArrayList<String>();
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
	public UserAnalysisTotal QuerryUserAnalysisTotalData(String appid) {
		UserAnalysisTotal userAnalysisTotal = new UserAnalysisTotal();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
//		String subscriBetime = df.format(new Date());
//		String subscriPttype = "1";
		// 查询总关注人数
//		BigDecimal analysisTotal = userAnalysisMapper.QuerryUserAnalysisTotal(subscriBetime,appid);
//		userAnalysisTotal.setSubscribeTotal(analysisTotal);
		// 查询当前正关注人数
//		BigDecimal analysisNow = userAnalysisMapper.QuerryUserAnalysisBytype(subscriBetime, subscriPttype,appid);
//		userAnalysisTotal.setSubscribeNow(analysisNow);
		// 查询当前取消关注的人数
//		subscriPttype = "2";
//		BigDecimal analysisCancle = userAnalysisMapper.QuerryUserAnalysisBytype(subscriBetime, subscriPttype,appid);
//		userAnalysisTotal.setSubscribeCancle(analysisCancle);
		return userAnalysisTotal;
	}
	//用户关注详细信息
	@Override
	public EchartData QuerryUserAnalysisDataSource(AttentionAnalysisVO params) {
		String startDate = params.getStartDate();
		String endDate = params.getEndDate();
		String appid = params.getAppid();
		if (!(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate))) {
			endDate = DateUtil.getDateDay();
			startDate = DateUtil.addDate(endDate, -30);
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
		 EchartData data = new EchartData(legend, category, series);
		return data;
	}
	@Override
	public Map<String,String> ProvinceSexAna(String appid) {
		logger.info("---------------------------------*****************--------------{}",appid);
		Map<String,String> map = new HashMap<String,String> ();
		List<IMUserAna> man = userAnalysisMapper.IMUserAna("1",appid);
		List<IMUserAna> woman = userAnalysisMapper.IMUserAna("2",appid);
		try {
			map.put("man", JsonUtil.obj2json(man));
			map.put("woman", JsonUtil.obj2json(woman));
			map.put("max", userAnalysisMapper.IMUserAnaMax(appid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("分析地区男女分布获取数据失败{}",e);
		}
		return map;
	}
}
