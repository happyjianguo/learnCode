/**
 * ImNoticeTaskServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月23日
 */
package cn.com.jansh.service.scheduler.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.mapper.system.NoticeMapper;
import cn.com.jansh.service.scheduler.ImNoticeTaskService;
import cn.com.jansh.util.DateUtils;

/**
 * 公告定时任务Impl
 * @author gll
 * @version 1.0
 */
@Service
public class ImNoticeTaskServiceImpl implements ImNoticeTaskService {

	@Autowired
	private NoticeMapper noticeMapper;
	/**
	 * 定时刷新公告状态
	 */
	@Override
	public void updateNoticeByendtime() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("status",ContextCode.NOTICE_STATUS_GUOQI.value());
		map.put("nowtime",DateUtils.getStringToday());
		noticeMapper.updateNoticeByendtime(map);
	}

}
