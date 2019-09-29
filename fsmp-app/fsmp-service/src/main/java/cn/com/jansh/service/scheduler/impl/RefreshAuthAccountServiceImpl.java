/**
 * RefreshAuthAccountServiceImpl.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2017年1月10日
 */
package cn.com.jansh.service.scheduler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.mapper.wechat.AuthAccountMapper;
import cn.com.jansh.service.scheduler.RefreshAuthAccountService;
import cn.com.jansh.service.wechat.WxAuthService;

/**
 * 获取所有公众号刷新权限服务
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class RefreshAuthAccountServiceImpl implements RefreshAuthAccountService {

	private static final Logger logger = LogManager.getLogger(CfOrderServiceImpl.class);
	
	@Autowired
	private AuthAccountMapper  authMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private WxAuthService wxAuthService;
	
	
	/**
	 * 执行定时任务
	 */
	@Override
	public void action() {
		logger.info("开始更新所有公众号信息");
		List<AuthAccount> lists = new ArrayList<>();
		//获取公众号集合
		getAuthList(lists, 0);
		//集合不为空更新公众号信息
		if(CollectionUtils.isNotEmpty(lists)){
			for(AuthAccount account : lists){
				try {
					wxAuthService.getAuthAccessToken(account.getAppid());
				} catch (AppException e) {
					logger.error("更新公众号信息异常："+account.getAppid(),e);
				}
			}
		}
	}
	
	private void  getAuthList(List<AuthAccount> lists , int index){
		logger.info("获取所有有效的公众号");
		if(lists != null){
			Map<String, Object> map = new HashMap<>();
			String count = globalProperties.getPageRecordCount();
			map.put("start", index*Integer.valueOf(count));
			map.put("length", (index+1)*index*Integer.valueOf(count));
			//开始查询公众号
	 		List<AuthAccount> accounts = authMapper.select(map);
	 		//如果不为空，追加获取到的集合递归
	 		if(CollectionUtils.isNotEmpty(accounts)){
	 			lists.addAll(accounts);
	 			getAuthList(lists, index+1);
	 		}
		}
	}
}
