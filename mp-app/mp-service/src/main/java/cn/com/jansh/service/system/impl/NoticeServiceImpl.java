package cn.com.jansh.service.system.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.system.NoticeEntity;
import cn.com.jansh.mapper.system.NoticeMapper;
import cn.com.jansh.model.system.NoticeManageModel;
import cn.com.jansh.service.system.NoticeService;
import cn.com.jansh.util.DateUtils;
@Service
public class NoticeServiceImpl implements NoticeService {

	private static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	/**
	 * 初始化数据
	 * @throws ParseException 
	 */
	@Override
	public List<NoticeEntity> selectNotice(NoticeEntity notice) throws ParseException {
		logger.info("公告---数据初始化");
		List<NoticeEntity> li = noticeMapper.selectNotice(notice);
		for(int i=0;i<li.size();i++){
			//处理公告值过长问题
			String value = li.get(i).getNoticevalue();
			if(value.length()>15){
				value = value.substring(0, 15) + "…";
			}
			li.get(i).setNoticevalue(value);
			String begintime = DateUtils.getStringToday2(DateUtils.strToDateLong2(li.get(i).getBegintime()));
			String endtime = DateUtils.getStringToday2(DateUtils.strToDateLong2(li.get(i).getEndtime()));
			li.get(i).setBegintime(begintime);
			li.get(i).setEndtime(endtime);
		}
			
		return li;
	}

	/**
	 * 新增公告
	 * @throws ParseException 
	 */
	@Override
	public void insertNotice(NoticeEntity noticeEntity) throws ParseException {
		logger.info("公告---新增公告:{}",noticeEntity);
		noticeEntity.setNoticeid(IDUtils.getTimeRandon());
		noticeEntity.setCreatetime(DateUtil.getDateTimestamp());
		noticeEntity.setUpdatetime(DateUtil.getDateTimestamp());
		String begintime = DateUtils.getStringToday(DateUtils.strToDateLong(noticeEntity.getBegintime()+" 00:00:00"));
		String endtime = DateUtils.getStringToday(DateUtils.strToDateLong(noticeEntity.getEndtime()+" 23:59:59"));
		noticeEntity.setBegintime(begintime);
		noticeEntity.setEndtime(endtime);
		//将公告状态标记为0，未发布
		noticeEntity.setStatus(ContextCode.NOTICE_STATUS_WEIFABU.value());
		noticeMapper.insertNotice(noticeEntity);
	}

	/**
	 * 修改公告
	 * @throws ParseException 
	 */
	@Override
	public void editinotice(NoticeEntity noticeEntity) throws ParseException {
		logger.info("公告---修改公告:{}",noticeEntity);
		noticeEntity.setUpdatetime(DateUtil.getDateTimestamp());
		String begintime = DateUtils.getStringToday(DateUtils.strToDateLong(noticeEntity.getBegintime()+" 00:00:00"));
		String endtime = DateUtils.getStringToday(DateUtils.strToDateLong(noticeEntity.getEndtime()+" 23:59:59"));
		noticeEntity.setBegintime(begintime);
		noticeEntity.setEndtime(endtime);
		noticeMapper.editinotice(noticeEntity);
	}

	/**
	 * 删除公告
	 */
	@Override
	public void delnotice(String noticeid) {
		logger.info("公告---删除公告:{}",noticeid);
		noticeMapper.delnotice(noticeid);
	}
	
	/**
	 * 发布公告
	 */
	@Override
	public void fabuNotice(String noticeid) {
		logger.info("公告---发布公告:{}",noticeid);
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeEntity.setNoticeid(noticeid);
		//将公告状态设置为1，已发布
		noticeEntity.setStatus(ContextCode.NOTICE_STATUS_YIFABU.value());
		noticeEntity.setUpdatetime(DateUtil.getDateTimestamp());
		noticeMapper.fabuNotice(noticeEntity);
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
	 * 通过公告ID查公告
	 */
	@Override
	public NoticeEntity queryNoticeById(String noticeid) {
		logger.info("公告---通过公告ID查公告:{}",noticeid);
		return noticeMapper.queryNoticeById(noticeid);
	}

	/**
	 * 通过公告ID查公告，回显数据
	 * @throws ParseException 
	 */
	@Override
	public NoticeManageModel selectNoticeById(String noticeid) throws ParseException {
		logger.info("公告---通过公告ID查公告:{}",noticeid);
		NoticeEntity noticeEntity = noticeMapper.queryNoticeById(noticeid);
		NoticeManageModel noticeManageModel = new NoticeManageModel();
		noticeManageModel.setBegintime(DateUtils.parseShortDate(DateUtils.strToDateLong2(noticeEntity.getBegintime())));
		noticeManageModel.setEndtime(DateUtils.parseShortDate(DateUtils.strToDateLong2(noticeEntity.getEndtime())));
		noticeManageModel.setCreatetime(noticeEntity.getCreatetime());
		noticeManageModel.setNoticeid(noticeEntity.getNoticeid());
		noticeManageModel.setNoticename(noticeEntity.getNoticename());
		noticeManageModel.setNoticevalue(noticeEntity.getNoticevalue());
		noticeManageModel.setStatus(noticeEntity.getStatus());
		noticeManageModel.setUpdatetime(noticeEntity.getUpdatetime());
		return noticeManageModel;
	}
}
