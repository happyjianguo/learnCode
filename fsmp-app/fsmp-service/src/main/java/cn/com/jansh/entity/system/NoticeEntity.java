package cn.com.jansh.entity.system;

/**
 * 公告实体
 * 
 * @author gll
 *
 */
public class NoticeEntity {

	/*公告ID*/
	private String noticeid;
	/*公告名称*/
	private String noticename;
	/*公告内容*/
	private String noticevalue;
	/*创建时间*/
	private String createtime;
	/*更新时间*/
	private String updatetime;
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getNoticename() {
		return noticename;
	}
	public void setNoticename(String noticename) {
		this.noticename = noticename;
	}
	public String getNoticevalue() {
		return noticevalue;
	}
	public void setNoticevalue(String noticevalue) {
		this.noticevalue = noticevalue;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "Notice [noticeid=" + noticeid + ", noticename=" + noticename + ", noticevalue=" + noticevalue
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
