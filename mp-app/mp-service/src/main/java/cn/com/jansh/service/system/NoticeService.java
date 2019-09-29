package cn.com.jansh.service.system;

import java.text.ParseException;
import java.util.List;

import cn.com.jansh.entity.system.NoticeEntity;
import cn.com.jansh.model.system.NoticeManageModel;

/**
 * 公告service
 * 
 * @author gll
 *
 */
public interface NoticeService {

	/**
	 * 初始化公告数据
	 * @param notice
	 * @return
	 * @throws ParseException 
	 */
	public List<NoticeEntity> selectNotice(NoticeEntity notice) throws ParseException;

	/**
	 * 新增公告
	 * @param noticeService
	 * @throws ParseException 
	 */
	public void insertNotice(NoticeEntity noticeEntity) throws ParseException;

	/**
	 * 修改公告
	 * @param noticeEntity
	 * @throws ParseException 
	 */
	public void editinotice(NoticeEntity noticeEntity) throws ParseException;

	/**
	 * 删除公告
	 * @param noticeid
	 */
	public void delnotice(String noticeid);

	/**
	 * 发布公告
	 * @param noticeid
	 */
	public void fabuNotice(String noticeid);
	
	/**
	 * 通过公告名查公告
	 * @param noticename
	 * @return
	 */
	public NoticeEntity selectByNoticename(String noticename);
	
	/**
	 * 根据公告ID查询公告
	 * @param noticeid
	 * @return
	 */
	public NoticeEntity queryNoticeById(String noticeid);

	/**
	 * 根据公告ID查询公告
	 * @param noticeid
	 * @return
	 * @throws ParseException 
	 */
	public NoticeManageModel selectNoticeById(String noticeid) throws ParseException;
}
