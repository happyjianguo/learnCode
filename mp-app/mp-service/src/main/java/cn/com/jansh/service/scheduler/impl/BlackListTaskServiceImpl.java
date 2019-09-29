/**
 * BlackListTaskServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月26日
 */
package cn.com.jansh.service.scheduler.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import com.jansh.comm.util.DateUtil;
import cn.com.jansh.mapper.risk.BlackMapper;
import cn.com.jansh.service.scheduler.BlackListTaskService;

/**
 * 黑名单定时任务Service实现
 * @author gll
 * @version 1.0
 */
@Service
public class BlackListTaskServiceImpl implements BlackListTaskService {

	@Autowired
	private BlackMapper blackMapper;
	/**
	 * 根据当前时间、过期时间对比，定时刷新黑名单状态
	 */
	@Override
	public void updateBlackListByovertime() {
		/*
		1、查出数据库中所有状态为1（生效） 和 现在时间 >= 过期时间 的黑名单
		2、将这些黑名单的状态置为0（过期）
		*/
		Map<String,String> map = new HashMap<String,String>();
		//得到的当前时间为yyyyMMddHHmmss 黑名单数据表中存的时间格式为yyyyMMdd000000
		map.put("nowsTime", DateUtil.getDateTimestamp());
		//状态0（过期）
		map.put("status0", ContextCode.BLACKLIST_STATUS_GUOQI.value());
		//状态1（生效）
		map.put("status1", ContextCode.BLACKLIST_STATUS_SHENGXIAO.value());
		
		blackMapper.updateBlackListByovertime(map);
	}

}
