package cn.com.jansh.service.system;

import java.util.List;

import cn.com.jansh.entity.system.NoticeEntity;

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
	 */
	public List<NoticeEntity> selectNotice(NoticeEntity notice);

	/**
	 * 新增公告
	 * @param noticeService
	 */
	public void insertNotice(NoticeEntity noticeEntity);
	/**
	 * 通过公告名查公告
	 * @param noticename
	 * @return
	 */
	public NoticeEntity selectByNoticename(String noticename);

	/**
	 * 修改公告
	 * @param noticeEntity
	 */
	public void editinotice(NoticeEntity noticeEntity);

	/**
	 * 删除公告
	 * @param noticeid
	 */
	public void delnotice(String noticeid);
}
