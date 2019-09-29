package cn.com.jansh.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.system.NoticeEntity;
import cn.com.jansh.mapper.system.NoticeMapper;
import cn.com.jansh.service.system.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService {

	private static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	/**
	 * 初始化数据
	 */
	@Override
	public List<NoticeEntity> selectNotice(NoticeEntity notice) {
		logger.info("公告---数据初始化");
		Map<String,Object> map = new HashMap<>();
		map.put("status","1");
		List<NoticeEntity> li = noticeMapper.select(map);
		return li;
	}

	/**
	 * 新增公告
	 */
	@Override
	public void insertNotice(NoticeEntity noticeEntity) {
		logger.info("公告---新增公告:{}",noticeEntity);
		noticeEntity.setNoticeid(IDUtils.getTimeRandon());
		noticeEntity.setCreatetime(DateUtil.getDateTimestamp());
		noticeEntity.setUpdatetime(DateUtil.getDateTimestamp());
		noticeMapper.insertNotice(noticeEntity);
	}

	/**
	 * 通过noticename查公告
	 */
	@Override
	public NoticeEntity selectByNoticename(String noticename) {
		logger.info("公告---通过公告名查公告:{}",noticename);
		return noticeMapper.selectByNoticename(noticename);
	}

	/**
	 * 修改公告
	 */
	@Override
	public void editinotice(NoticeEntity noticeEntity) {
		logger.info("公告---修改公告:{}",noticeEntity);
		noticeEntity.setUpdatetime(DateUtil.getDateTimestamp());
		noticeMapper.editinotice(noticeEntity);
	}

	@Override
	public void delnotice(String noticeid) {
		logger.info("公告---删除公告:{}",noticeid);
		noticeMapper.delnotice(noticeid);
	}
}
