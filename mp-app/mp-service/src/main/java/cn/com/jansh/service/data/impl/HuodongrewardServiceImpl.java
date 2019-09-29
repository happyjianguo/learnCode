/**
 * HuodongrewardServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月13日
 */
package cn.com.jansh.service.data.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;

import cn.com.jansh.entity.data.HuodongrewardDataEntity;
import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.mapper.data.HuodongrewardMapper;
import cn.com.jansh.mapper.game.IGameInitMapper;
import cn.com.jansh.mapper.game.IGameparamMapper;
import cn.com.jansh.service.data.HuodongrewardService;
import cn.com.jansh.service.system.GlobalPropertiesService;
import cn.com.jansh.util.URLAvailability;

/**
 * 活动获奖情况serviceImpl
 * @author gll
 * @version 1.0
 */
@Service
public class HuodongrewardServiceImpl implements HuodongrewardService {

	private static final Logger logger = LogManager.getLogger(HuodongrewardServiceImpl.class);
	
	@Autowired
	private HuodongrewardMapper huodongrewardMapper;	//活动获奖情况Mapper
	
	@Autowired
	private IGameparamMapper iGameparamMapper; 	//获取活动信息mapper
	
	@Autowired
	private IGameInitMapper iGameInitMapper; 	//获取活动定义mapper
	
	@Autowired
	private GlobalPropertiesService globalPropertiesService;
	
	ObjectMapper mapper = new ObjectMapper();
	/**
	 * 获得所有机构信息
	 */
	@Override
	public List<CloudplatformorginationEntity> getOrg() {
		logger.info("获取所有机构信息");
		return huodongrewardMapper.queryAllDate();
	}
	/**
	 * 根据活动id调取活动获奖接口
	 * @param gameid
	 * @return
	 */
	@Override
	public List<HuodongrewardDataEntity> gethuodongreward(String gameid,String startDate, String endDate) {
		logger.info("根据活动id调取活动获奖接口",gameid);
		CloudgameparamEntity gameparam = iGameparamMapper.getGameByGameid(gameid);
		CloudGameInitEntity gameInit = iGameInitMapper.queryById(gameparam.getGametype());
		String url = null;
		//微信渠道
		if(gameparam.getChannel().equals("0")){
			url = gameInit.getWxrewardurl();
			logger.info("微信渠道",url);
			//app渠道
		}else if(gameparam.getChannel().equals("1")){
			url = gameInit.getApprewardurl();
			logger.info("app渠道",url);
		}
		List<HuodongrewardDataEntity> listEntity = new LinkedList<HuodongrewardDataEntity>();
		//域名+项目名
		String urls = globalPropertiesService.getAcquire();
		//判断URL能否访问
		if(URLAvailability.isConnect(urls)){
			logger.info("发送http请求",url);
			Map<String, Object> ttdateCounts = sendHttp(startDate,endDate,gameid,url);
			String json = (String) ttdateCounts.get("data");
			try {
				//json转list<>
				listEntity=cn.com.jansh.util.JsonUtil.json2list(json, HuodongrewardDataEntity.class);
				for(int i=0;i<listEntity.size();i++){
					String su =listEntity.get(i).getUpdatetime();
					String sc =listEntity.get(i).getCreatetime();
					try{
						//转换日期格式，用于显示
						listEntity.get(i).setCreatetime(DateUtil.formatDateTimestamp(sc));
						listEntity.get(i).setUpdatetime(DateUtil.formatDateTimestamp(su));
					}catch(Exception e){
						logger.error("时间格式转换异常");
						listEntity.get(i).setCreatetime(sc);
						listEntity.get(i).setUpdatetime(su);
					}
				}
				return listEntity;
			} catch (Exception e1) {
				logger.error("json2list转换异常");
				listEntity.add(new HuodongrewardDataEntity());
				return listEntity;
			}
		}else{
			logger.info("URL不可用");
			listEntity.add(new HuodongrewardDataEntity());
			return listEntity;
		}
	}
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的字符串 to yyyyMMddHHmmss
	 * @param src
	 * @return
	 * @throws ParseException 
	 */
	public static String PaseDate(String src) throws ParseException{
		logger.info("将yyyy-MM-dd HH:mm:ss格式的字符串转成yyyyMMddHHmmss",src);
		Date strtodate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(src);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(strtodate);
	}
	/**
	 * 发送http
	 * @param starttime
	 * @param endtime
	 * @param gameid
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> sendHttp(String startDate, String endDate,String gameid, String url){
		logger.info("发送http",url);
		Map<String,String> map=new HashMap<String,String>();
		String wxStateJson = null;
		Map<String, Object> newStateMap = null;
		map.put("gameid", gameid);
		try {
			map.put("startDate", PaseDate(startDate));
			map.put("endDate", PaseDate(endDate));
		} catch (ParseException e1) {
			logger.error("时间格式转换发生异常");
			return null;
		}
		String json;
		try {
			logger.info("map转String");
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
}
