package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.system.NoticeEntity;

/**
 * 公告管理Mapper
 * 
 * @author gll
 *
 */
public interface NoticeMapper {

	/**
	 * 初始化公告查询
	 * @param notice
	 * @return
	 */
	public List<NoticeEntity> selectNotice(NoticeEntity notice);

	/**
	 * 新增公告
	 * @param noticeEntity
	 */
	public void insertNotice(NoticeEntity noticeEntity);

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

	/**
	 * 发布公告
	 * @param noticeEntity
	 */
	public void fabuNotice(NoticeEntity noticeEntity);
	
	/**
	 * 通过noticeid查公告
	 * @param noticeid
	 * @return
	 */
	public NoticeEntity queryNoticeById(String noticeid);
	
	/**
	 * 通过noticename查公告
	 * @param noticename
	 * @return
	 */
	public NoticeEntity selectByNoticename(String noticename);

	/**
	 * 定时刷新公告状态
	 * @param map
	 */
	public void updateNoticeByendtime(Map<String, String> map);

}
