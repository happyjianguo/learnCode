package cn.com.jansh.service.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.HttpClientRequest;
import cn.com.jansh.core.util.HttpClientUtil;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;

@Service
public class WangSuFdnComponent {

	private static final Logger logger = LogManager.getLogger(WangSuFdnComponent.class);

	@Autowired
	private PubsSysBaseMapper pubsSysBaseMapper;
	
	/**
	 * 流量订购
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String order(String phone, String transNo, String cpOrderNos) throws Exception {
		logger.info("流量订购");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("phone", phone);
		paramMap.put("cpOrderNos",cpOrderNos);
		paramMap.put("cpUserName", getCpUserName());
		paramMap.put("transNo", transNo);
		paramMap.put("timestamp", DateUtil.getTimestamp());

		HttpClientRequest request = gethttpClientRequest(paramMap);

		return HttpClientUtil.httpPost(getOrderUrl(), request);
	}

	/**
	 * 用户订购查询接口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryOrder(String orderId, String transNo) throws Exception {
		logger.info("用户订购查询接口");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(orderId)) {
			paramMap.put("orderId", orderId);
		} else if (StringUtils.isNotBlank(transNo)) {
			paramMap.put("transNo", transNo);
		}
		paramMap.put("cpUserName", getCpUserName());

		HttpClientRequest request = gethttpClientRequest(paramMap);
		return HttpClientUtil.httpPost(getQueryOrderUrl(), request);
	}

	/**
	 * 封装请求数据
	 * 
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	public HttpClientRequest gethttpClientRequest(Map<String, String> map) throws JsonProcessingException {
		String json = JsonUtil.obj2json(map);
		String source = json + getApiKey();
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("X-FDN-Auth", digest);
		request.setHeaders(headers);
		request.setBody(json);
		return request;
	}
	/********************************************************************从数据库中获取wangSuFdnComponent组件所需要的参数******************************************************************/
	/**
	 * (网宿)接口apiKey
	 * @return
	 */
	private String getApiKey() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("apiKey".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (网宿)客户CDN账号
	 * @return
	 */
	private String getCpUserName() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("WS_cpUserName".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (网宿)订购接口地址
	 * @return
	 */
	private String getOrderUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("WS_orderUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (网宿)订购查询接口地址
	 * @return
	 */
	private String getQueryOrderUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("WS_queryOrderUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

}
