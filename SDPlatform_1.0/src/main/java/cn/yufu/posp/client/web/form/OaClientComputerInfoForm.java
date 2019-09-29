package cn.yufu.posp.client.web.form;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import cn.yufu.core.web.form.BaseForm;

public class OaClientComputerInfoForm extends BaseForm 
{
   
	private String macAddr;	
	private String ipAddr;
	private String hostName;
	private String userId;
	private String createUser;
	private String createTimestamp;
	private Date updateTimestamp;
	private String updateUser;
	private String clientId;
	private String merId;

	//供查询条件用的属性
	private String mac_addr;	

	public String getMacAddr() {
		return macAddr;
	}


	public String getIpAddr() {
		return ipAddr;
	}


	public String getHostName() {
		return hostName;
	}


	public String getUserId() {
		return userId;
	}


	public String getCreateUser() {
		return createUser;
	}


	public String getCreateTimestamp() {
		return createTimestamp;
	}


	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}


	public String getUpdateUser() {
		return updateUser;
	}


	public String getClientId() {
		return clientId;
	}


	public String getMerId() {
		return merId;
	}


	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}


	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}


	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}


	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public void setMerId(String merId) {
		this.merId = merId;
	}


	@Override
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	{

		if(request.getParameter("method").equals("createItem")||request.getParameter("method").equals("saveItem"))
		{
			return super.validate(mapping, request);
		}
		else
		{
			return null;
		}
	}


	public String getMac_addr() {
		return mac_addr;
	}


	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}




	
}
