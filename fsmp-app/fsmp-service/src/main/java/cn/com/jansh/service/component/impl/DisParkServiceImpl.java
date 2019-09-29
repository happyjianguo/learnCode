/**
 * ClgameparamServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */
package cn.com.jansh.service.component.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.WXConstant;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.janshpay.util.Sign;
import cn.com.jansh.mapper.component.CloudgameparamMapper;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;
import cn.com.jansh.mapper.wechat.AuthAccountMapper;
import cn.com.jansh.model.wechat.OfferTokenModel;
import cn.com.jansh.service.component.DisParkService;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.vo.JsonVO;

/**
 * 游戏配置表接口实现
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class DisParkServiceImpl implements DisParkService {
	
	private static final Logger logger = LogManager.getLogger(DisParkServiceImpl.class);

	@Autowired
	private CloudgameparamMapper gameparamMapper;
	@Autowired
	private AuthAccountMapper authAccountMapper;
	@Autowired
	private WxAuthService wxauthService;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private PubsSysBaseMapper systemBaseMapper;
	
	
	/**
	 * 提供第三方平台接口调用权限
	 * @throws AppException 
	 */
	@Override
	public JsonVO offerComponentToken(HttpServletRequest request) throws AppException {
		logger.info("开始对传入信息进行校验");
		JsonVO vo = new JsonVO();
		String sign = request.getHeader("sign");
		String gameid = request.getParameter("gameid");
		
		if(StringUtils.isBlank(gameid)){
			vo.setResult(0);
			vo.setMsg("活动id不能为空！");
			return vo;
		}
		
		if(StringUtils.isBlank(sign)){
			vo.setResult(0);
			vo.setMsg("验签不能为空！");
			return vo;
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("gameid", gameid);
		String paramSign = Sign.getSign(map, globalProperties.getTokenSecret());
		if(!paramSign.equals(sign)){
			return null;
		}
		logger.info("开始获取公众号和第三方信息");
		OfferTokenModel  offerTokenModel = new OfferTokenModel();
		CloudgameparamEntity gameparam = gameparamMapper.selectByGameid(gameid);
		if(gameparam !=null){
			AuthAccount account = authAccountMapper.selectOneByAppid(gameparam.getAppid());
			if(account!=null){
				logger.info("获取信息成功");
				PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
				String componentAccessToken = wxauthService.getComponentAccessToken();
				String authAccessToken = wxauthService.getAuthAccessToken(gameparam.getAppid());
				offerTokenModel.setAppid(gameparam.getAppid());
				offerTokenModel.setAuthAccessToken(authAccessToken);
				offerTokenModel.setAccesstoken(componentAccessToken);
				offerTokenModel.setComponent_appid(component_appid.getSysBaseValue());
				vo.setData(offerTokenModel);
				vo.setSuccess(true);
				vo.setResult(1);
				vo.setMsg("获取公众号信息成功");
				return vo;
			}else{
				vo.setResult(0);
				vo.setMsg("提供的活动id无效！");
				return vo;
			}
			
		}else{
			vo.setResult(0);
			vo.setMsg("提供的活动id无效！");
			return vo;
		}
	}
}
