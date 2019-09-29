package cn.com.jansh.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.weixin.WXDMMapper;
import cn.com.jansh.service.weixin.WXDMService;

/**
 * 微信遮挡service Impl
 * WXDMService Impl
 * @author gll
 * @version 1.0
 */
@Service
public class WXDMServiceImpl implements WXDMService {

	@Autowired
	private WXDMMapper wXDMMapper;
	
	@Autowired
	private IMUserMapper imusermapper;
	
	private final static Logger logger = LogManager.getLogger(WXDMServiceImpl.class);
	/**
	 *
	 * 获取当前用户or机构下全部授权公众号信息
	 * @return
	 *
	 */
	@Override
	public List<AuthAccount> getAppidList() {
		logger.info("开始获取全部授权公众号信息！");
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN user = imusermapper.selectNewByUserid(userid);
		/*构建查询参数map*/
		Map<String, Object> map = new HashMap<>();
		map.put("orgid", user.getOrgid());
		logger.info("查询参数是：" + map.toString());
		return wXDMMapper.getAppidList(map);
	}
}
