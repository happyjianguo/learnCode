/**
 * WxInterfaceServiceImple.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月25日
 */
package cn.com.jansh.service.wechat.impl;

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
import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.wechat.AuthAccountMapper;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.model.wechat.WxManageModel;
import cn.com.jansh.service.wechat.WxInterfaceService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 微信管理接口实现类
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class WxInterfaceServiceImple implements WxInterfaceService {

	@Autowired
	private AuthAccountMapper authAccountMapper;
	@Autowired
	private IMUserMapper imusermapper;
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;
	
	private final static Logger logger = LogManager.getLogger(WxInterfaceServiceImple.class);
	/**
	 * 删除用户授权公众号
	 * 
	 */
	public AjaxObj removeAuthorization(String appid){
		AjaxObj ajaxObj = new AjaxObj();
		authAccountMapper.modifyStatus(appid);
		ajaxObj.setResult(1);
		ajaxObj.setSuccess(true);
		ajaxObj.setMsg("删除授权公众号成功！");
		return ajaxObj;
	}
	/**
	 * 获取当前机构下所有的授权公众号
	 * 
	 */
	public WxManageModel getAuthorizationAll(WxManageModel wxManageModel){
		logger.info("开始获取全部授权公众号信息！");
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN user = imusermapper.selectNewByUserid(userid);
		/*构建查询参数map*/
		Map<String, Object> map = new HashMap<>();
		map.put("orgid", user.getOrgid());
		map.put("start", wxManageModel.getStart());
		map.put("length", wxManageModel.getLength());
		logger.info("查询参数是：" + map.toString());
		/*获取查询结果传递到model中*/
		List<AuthAccount> allAuthAccount = authAccountMapper.select(map);
		logger.info("开始获取默认公众号");
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		String defaultAppid = "";
		//获取当前默认公众号
		if(null != defaultAccount){
			defaultAppid=defaultAccount.getAppid();
		}
		for(int i=0; i<allAuthAccount.size(); i++){
			allAuthAccount.get(i).setDefaultappid(defaultAppid);
		}
		wxManageModel.setAuthAccounts(allAuthAccount);
		wxManageModel.setCount(authAccountMapper.selectCount(map));
		
		return wxManageModel;
	}
	/**
	 * 默认指定公众号
	 */
	@Override
	public AjaxObj defaultAuthorization(String appid) {
		logger.info("默认指定公众号Impl");
		AjaxObj ajaxObj = new AjaxObj();
		Map<String, String> map = new HashMap<String, String>();
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		/* 通过userid在关联表中查是否返回null，	表clouddefaultaccount
		 * 如果返回null直接设置默认；
		 * 如果不为null，则删除次条目并设置默认
		 * 查到了则更新数据
		 * 查不到则新增数据
		 */
		logger.info("通过userid查询defaultAccount");
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		map.put("appid", appid);
		map.put("userid", userid);
		//判断是否存在默认公众号
		if(null != defaultAccount){
			//更新数据
			defaultAccountMapper.updateDefaultAccount(map);
		}else{
			//插入数据
			defaultAccountMapper.insertDefaultAccount(map);
		}
		ajaxObj.setResult(1);
		ajaxObj.setSuccess(true);
		ajaxObj.setMsg("默认用户公众号成功！");
		return ajaxObj;
	}
	/**
	 * 取消默认指定公众号
	 */
	@Override
	public AjaxObj undefaultAuthorization(String appid) {
		logger.info("取消默认公众号Impl");
		AjaxObj ajaxObj = new AjaxObj();
		Map<String, String> map = new HashMap<String, String>();
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		map.put("appid", appid);
		map.put("userid", userid);
		//删除数据
		defaultAccountMapper.deleteDefaultAccount(map);
		ajaxObj.setResult(1);
		ajaxObj.setSuccess(true);
		ajaxObj.setMsg("取消默认用户公众号成功！");
		return ajaxObj;
	}
}
