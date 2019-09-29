package cn.yufu.posp.common.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WebServiceDocModel implements Serializable 
{
    private String title;//����

	private String docNum;//�ĺ�

	private String deptName;//����
	
	private String sourceDeptName=null;//֪ͨͨ��ĸ�Դ����

	private Date sendTime;//�����¼�

	private String docType;//��������

	private String channelID;//������Ŀ

	private String sender;//������
	
	private Date docEndTime=null;//����ʱ��
	
	private String publishStatus=null;//����״̬
	
	private String editStatus=null;//�༭״̬

	private List files;

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public List getFiles() {
		return files;
	}

	public void setFiles(List files) {
		this.files = files;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDocEndTime() {
		return docEndTime;
	}

	public void setDocEndTime(Date docEndTime) {
		this.docEndTime = docEndTime;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getSourceDeptName() {
		return sourceDeptName;
	}

	public void setSourceDeptName(String sourceDeptName) {
		this.sourceDeptName = sourceDeptName;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}	
	
}
