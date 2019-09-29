/**
 * CfInterfaceServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.service.component.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudvirgoodEntity;
import cn.com.jansh.entity.component.bo.CloudorgvirgoodBO;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.mapper.component.CloudorgvirgoodMapper;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.model.cf.CfDataVO;
import cn.com.jansh.model.cf.RecMenu;
import cn.com.jansh.model.component.CloudorgvirgoodModel;
import cn.com.jansh.service.component.CfInterfaceService;

/**
 * 话费流量充值平台接口实现类
 * @author duanmuyn
 * @version 1.0
 */
@Service
public class CfInterfaceServiceImpl implements CfInterfaceService{
	
	private final static Logger logger = LogManager.getLogger(CfInterfaceServiceImpl.class);
	
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private CloudorgvirgoodMapper orgVrMapper;
	@Autowired
	private IMUserMapper imusermapper;
	
	@Override
	public List<CloudvirgoodEntity> queryPack() {
		logger.info("获取接入者套餐信息");
		ObjectMapper obj = new ObjectMapper();
		List<CloudvirgoodEntity> list = new ArrayList<CloudvirgoodEntity>();
		Map<String, String> paramMap = new HashMap<String, String>();
		HttpClientRequest request;
		try {
			request = gethttpClientRequest(paramMap);
			String resultMsg = HttpClientUtil.httpPost(globalProperties.getCfqueryPackurl(), request);
			CfDataVO cf = obj.readValue(resultMsg,CfDataVO.class);
			for(RecMenu re :cf.getData()){
				list.add(RmtoCge(re));
			}
		} catch (JsonProcessingException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
	@Override
	public List<CloudorgvirgoodModel> queryVrGoods(String gameid){
		logger.info("开始获取机构套餐信息");
		/** 获取用户信息 **/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN userNew = imusermapper.selectNewByUserid(userid);
		
		Map<String,String> map = new HashMap<>();
		map.put("orgid", userNew.getOrgid());
		if(StringUtils.isNotBlank(gameid)){
			map.put("gameid", gameid);
		}
		
	    List<CloudorgvirgoodBO> orgVirBOs = orgVrMapper.select(map);
	    List<CloudorgvirgoodModel> models = new ArrayList<>();
	    for(CloudorgvirgoodBO orig : orgVirBOs){
	    	CloudorgvirgoodModel model = new CloudorgvirgoodModel();
	    	 try {
				BeanUtils.copyProperties(model, orig);
				models.add(model);
			} catch (Exception e) {
				logger.error("属性拷贝异常",e);
			} 
	    }
		return models;
	}
	
	/**
	 * 话费流量平台套餐信息model转entity
	 * @param recMenu
	 * @return
	 */
	private CloudvirgoodEntity RmtoCge(RecMenu recMenu){
		CloudvirgoodEntity cloudvirgood = new CloudvirgoodEntity();
		cloudvirgood.setId(IDUtils.getTimeRandon());
		cloudvirgood.setApid(recMenu.getApid());
		cloudvirgood.setPrizename(recMenu.getPname());
		cloudvirgood.setPrizestyle(recMenu.getIpstype());
		cloudvirgood.setCarrier(recMenu.getIspno());
		
		cloudvirgood.setCombovalue(recMenu.getFacevalue());
		cloudvirgood.setPervalue(recMenu.getFacunit());
		
		cloudvirgood.setProvince(recMenu.getProvince());
		cloudvirgood.setUrange(recMenu.getUrange());
		
		//构造以字符串内容为值的BigDecimal类型的变量bd 
		logger.info("单价（元）:"+recMenu.getPrice());
		BigDecimal bd=new BigDecimal(recMenu.getPrice()); 
		bd = bd.multiply(new BigDecimal(1000));
		logger.info("单价（平台币）:"+bd);
		cloudvirgood.setPrizeprice(bd.intValue());
		cloudvirgood.setImagepath("");
		cloudvirgood.setStatus(recMenu.getStatus());
		cloudvirgood.setNote("");
		cloudvirgood.setCreatetime(DateUtil.getDateTimestamp());
		cloudvirgood.setUpdatetime(DateUtil.getDateTimestamp());
		
		return cloudvirgood;
	}

	@Override
	public String order(String cporder,String phone,CloudvirgoodEntity entity) {
		logger.info("直冲接口:{}-->{}",phone,entity.getId());
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cporder", cporder);// 接入者订单号
		paramMap.put("phone", phone);// 手机号
		paramMap.put("localtype", entity.getUrange());// 0-全国，1-本地
		paramMap.put("isptype",entity.getPrizestyle() );// 充值类型
		
		if(entity.getPrizestyle().equals(AppCommonsCode.IPSTYPE_Flow.value())){
			paramMap.put("facevalue", entity.getCombovalue()+entity.getPervalue());// 面额
		}else{
			paramMap.put("facevalue", entity.getCombovalue());// 面额
		}
		
		String resultmsg = "";
		try {
			HttpClientRequest request = gethttpClientRequest(paramMap);
			resultmsg = HttpClientUtil.httpPost(globalProperties.getCforderurl(), request);
			logger.info("直冲返回信息："+resultmsg);
		} catch (JsonProcessingException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return resultmsg;
	}
	
	@Override
	public String queryorder(String cporder) {
		String resultmsg = "";
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("cporder", cporder);
			HttpClientRequest request = gethttpClientRequest(paramMap);
			resultmsg = HttpClientUtil.httpPost(globalProperties.getCfqueryorder(), request);
		} catch (Exception e) {
			logger.error(e);
		}
		return resultmsg;
	}
	
	/**
	 * 获取加密后的请求信息
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	private HttpClientRequest gethttpClientRequest(Map<String, String> map) throws JsonProcessingException {
		Map<String, String> hm = new HashMap<String,String>();
		hm.put("sysid", globalProperties.getCfuserid()); // 接入者id
		hm.putAll(map);
		String json = JsonUtil.obj2json(hm);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + globalProperties.getCfapiKey();//最后一位是接入者密钥
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		return request;
	}
}
