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
	 * 公告查询
	 * @param Map
	 * @return
	 */
	public List<NoticeEntity> select(Map<String, Object> map);
	/**
	 * 新增公告
	 * @param noticeEntity
	 */
	public void insertNotice(NoticeEntity noticeEntity);
	/**
	 * 通过noticename查公告
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
