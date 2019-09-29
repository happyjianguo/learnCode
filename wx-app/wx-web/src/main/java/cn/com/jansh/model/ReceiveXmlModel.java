/**
 * ReceiveXmlEntity.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-7-29
 */
package cn.com.jansh.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 接收到的微信xml实体类 
 * @author yangfan
 * @version 1.0.0
 */
@XStreamAlias("xml")
public class ReceiveXmlModel {

    private String ToUserName="";  //开发者微信号
    private String FromUserName="";  //发送方帐号（一个OpenID）
    private String CreateTime="";  //消息创建时间 （整型）
    private String MsgType="";  //消息类型
    private String MsgId="";  //消息id
    private String Event="";  //事件类型
    private String EventKey="";  //事件KEY值
    private String Ticket="";  //二维码的ticket，可用来换取二维码图片
    private String Latitude="";  //地理位置纬度
    private String Longitude="";  //地理位置经度
    private String Precision="";  //地理位置精度
    private String PicUrl="";  //图片链接
    private String MediaId="";  //图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String Title="";  //消息标题
    private String Description="";  //消息描述
    private String Url="";  //消息链接
    private String Location_X="";  //地理位置维度
    private String Location_Y="";  //地理位置经度
    private String Scale="";  //地图缩放大小
    private String Label="";  //地理位置信息
    private String Content="";  //回复的消息内容
    private String Format="";  //语音格式，如amr，speex等
    private String Recognition=""; //语音识别结果
    private String MenuId;//按钮id
    
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String locationX) {
		Location_X = locationX;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String locationY) {
		Location_Y = locationY;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	@Override
	public String toString() {
		return "ReceiveXmlEntity [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MsgId=" + MsgId + ", Event=" + Event + ", EventKey="
				+ EventKey + ", Ticket=" + Ticket + ", Latitude=" + Latitude + ", Longitude=" + Longitude
				+ ", Precision=" + Precision + ", PicUrl=" + PicUrl + ", MediaId=" + MediaId + ", Title=" + Title
				+ ", Description=" + Description + ", Url=" + Url + ", Location_X=" + Location_X + ", Location_Y="
				+ Location_Y + ", Scale=" + Scale + ", Label=" + Label + ", Content=" + Content + ", Format=" + Format
				+ ", Recognition=" + Recognition + "]";
	} 
    
}
