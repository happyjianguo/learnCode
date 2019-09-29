/**
 * SinglehuodongDataServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月30日
 */
package cn.com.jansh.service.data.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudgameinitEntity;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.mapper.game.IGameInitMapper;
import cn.com.jansh.mapper.game.IGameparamMapper;
import cn.com.jansh.service.data.SinglehuodongDataService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.URLAvailability;
import cn.com.jansh.vo.EchartData;
import cn.com.jansh.vo.PVUVDataVO;
import cn.com.jansh.vo.Series;
import cn.com.jansh.vo.SingleActionVO;

/**
 * 单个活动数据IMPL
 * @author gll
 * @version 1.0
 */
@Service
public class SinglehuodongDataServiceImpl implements SinglehuodongDataService {
	
	private static final Logger logger = LogManager.getLogger(SinglehuodongDataServiceImpl.class);
	
	@Autowired
	private IGameparamMapper iGameparamMapper; 	//获取活动信息mapper
	
	@Autowired
	private GlobalProperties globalProperties;
	
	@Autowired
	private IGameInitMapper iGameInitMapper;
	
	/**
	 *  ajax通过接口查询PV、UV
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PVUVDataVO getPvUvData(SingleActionVO params) {
		logger.info("ajax通过接口查询PV、UV",params);
		PVUVDataVO dataVO = new PVUVDataVO();
		String starttime = params.getStartDate();
		String endtime = params.getEndDate();
		String gameid = params.getGameid();
		/**
		 * 判断gameid是否为空
		 */
		if(StringUtils.isEmpty(gameid)){
			List<String> category = showXLineData(starttime, endtime);
			List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "PV", "UV"}));// 数据分组
			List<Series> series = new ArrayList<Series>();// 纵坐标
			series.add(new Series("PV", "line", publicME(starttime, endtime)));
			series.add(new Series("UV", "line", publicME(starttime, endtime)));
			EchartData data = new EchartData(legend, category, series);
			dataVO.setPv(new BigDecimal("0"));
			dataVO.setUv(new BigDecimal("0"));
			dataVO.setEchartData(data);
			return dataVO;
		}
		CloudgameparamEntity gameparam = iGameparamMapper.getGameByGameid(gameid);
		CloudgameinitEntity gameInit = iGameInitMapper.queryById(gameparam.getGametype());
		String url = null;
		//微信渠道
		if(gameparam.getChannel().equals("0")){
			url = gameInit.getWxactionurl();
			//app渠道
		}else if(gameparam.getChannel().equals("1")){
			url = gameInit.getAppactionurl();
		}
		BigDecimal pvtotal ;
		BigDecimal uvtotal ;
		List<BigDecimal> pv = null;//pv
		List<BigDecimal> uv = null;//uv
		//域名+项目名
		String urls = globalProperties.getAcquire();
		//判断URL能否访问
		//if(URLAvailability.isConnect(urls+"?gameid="+gameid)){
		if(URLAvailability.isConnect(urls)){
			Map<String, Object> ttdateCounts = sendHttp(starttime,endtime,gameid,url);
			if(null == ttdateCounts){
				uv = publicME(starttime, endtime);
				pv = publicME(starttime, endtime);
				pvtotal = new BigDecimal("0");
				uvtotal = new BigDecimal("0");
			}else{
				Map<String, Object> map = (Map<String, Object>) ttdateCounts.get("data");
				List li = (List) map.get("list");
				uv = publicM(starttime, endtime,(List<Map<String, Object>>) li.get(0));
				pv = publicM(starttime, endtime,(List<Map<String, Object>>) li.get(1));
				pvtotal = new BigDecimal(map.get("pvtotal").toString());
				uvtotal = new BigDecimal(map.get("uvtotal").toString());
			}
		}else{
			uv = publicME(starttime, endtime);
			pv = publicME(starttime, endtime);
			pvtotal = new BigDecimal("0");
			uvtotal = new BigDecimal("0");
		}
		List<String> category = showXLineData(starttime, endtime);
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "PV", "UV"}));// 数据分组
		List<Series> series = new ArrayList<Series>();// 纵坐标
		series.add(new Series("PV", "line", pv));
		series.add(new Series("UV", "line", uv));
		EchartData data = new EchartData(legend, category, series);
		dataVO.setPv(pvtotal);
		dataVO.setUv(uvtotal);
		dataVO.setEchartData(data);
		return dataVO;
	}
	/**
	 * 根据公众号id获取所有活动
	 */
	@Override
	public List<CloudgameparamEntity> getGame(String appid,String channel) {
		logger.info("根据公众号id获取所有活动");
		Map<String,String> map = new HashMap<String,String>();
		map.put("appid", appid);
		map.put("channel", channel);
		map.put("status", AppCommonsCode.GAMEPARAM_STATUS.value());
		map.put("userid",AppUtil.getUserDetail().getUserid());
		List<CloudgameparamEntity> list = iGameparamMapper.getGameNameAndCode(map);
		return list;
	}
	/**
	 * 发送http
	 * @param starttime
	 * @param endtime
	 * @param gameid
	 * @param url
	 * @return
	 */
	private Map<String, Object> sendHttp(String starttime, String endtime, String gameid, String url){
		Map<String,String> map=new HashMap<String,String>();
		String wxStateJson = null;
		Map<String, Object> newStateMap = null;
		map.put("gameid", gameid);
		map.put("startDate", starttime);
		map.put("endDate", endtime);
		String json;
		try {
			json = JsonUtil.obj2json(map);
		} catch (Exception e) {
			logger.error("map转String发生异常");
			return null;
		}
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		request.setSocketTimeout(80*1000);
		try {
			wxStateJson = HttpClientUtil.httpPost(url,request);
		} catch (Exception e) {
			logger.error("发送http请求异常内部");
			return null;
		}
		try {
			newStateMap = JsonUtil.readMapObject(wxStateJson);
		} catch (Exception e) {
			logger.error("String转map发生异常");
			return null;
		}
		return newStateMap;
	}
	/*异常时转list数组*/
	public List<BigDecimal> publicME(String startDate, String endDate){
		logger.info("转list数组");
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		int days = DateUtil.daysBetween(startDate, endDate);
		BigDecimal countss=new BigDecimal(0);
		for (int i = 0; i <= days; i++) {
			data.add(countss);
		}
		return data;
	}
	/*转list数组*/
	public List<BigDecimal> publicM(String startDate, String endDate,List<Map<String, Object>>dateCounts){
		logger.info("转list数组");
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		Map<String, Object> mapCounts = new HashMap<String, Object>();
		String formateDate = "";
		BigDecimal fcount;
		for (Map<String, Object> map : dateCounts) {
			formateDate = PaseDate((String) map.get("ADATE")+"000000");
			fcount = new BigDecimal( map.get("USERCOUNT").toString());
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
			if(userCount == null){
				data.add(countss);
			}else{
				data.add((BigDecimal)userCount);
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
		logger.info("线数据");
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
	 * 将yyyyMMddHHmmss格式的字符串 to yyyy－MM－dd
	 * @param src
	 * @return
	 */
	public static String PaseDate(String src){
		Date strtodate = null;
		try {
			strtodate = new SimpleDateFormat("yyyyMMddHHmmss").parse(src);
		} catch (ParseException e) {
			logger.error("时间格式转换异常");
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(strtodate);
	}
}
